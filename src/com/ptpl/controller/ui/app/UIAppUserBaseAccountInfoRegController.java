package com.ptpl.controller.ui.app;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.SMSSend;
import com.ptpl.web.util.StringUtil;

/**
 * Android端 用户注册 和判断用户名是否存在
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年5月5日 下午5:41:43
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/ui/app/userbase")
public class UIAppUserBaseAccountInfoRegController extends BaseController {
	// 用户基本信息Service
	@Autowired
	private UserBaseAccountInfoServiceI ubaiService;
	// 生成用户号
	@Autowired
	private OracleSequenceMaxValueIncrementer accountnumber;

	private Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 校验手机号
	 * 
	 * @param ubai
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyLoginName")
	public void verifyLoginName(String mobilephone) throws Exception {
		map.put("status", 0);
		map.put("Msg", "手机号已存在，无法注册。");
		if (StringUtil.isEmpty(mobilephone)) {
			map.put("Msg", "手机号码不能为空");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		if (mobilephone.length() != 11) {
			map.put("Msg", "请输入11位手机号码");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[0-9])|18[0-9])\\d{8}$");
		Matcher m = p.matcher(mobilephone);
		if (!m.find()) {
			map.put("Msg", "请输入正确的手机号码");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		ubai.setMobilephone(AES.getEncrypt(mobilephone));
		ubai = ubaiService.getUserBaseAccountInfoByOneCondition(ubai);
		if (null == ubai) {
			map.put("Msg", "手机号未注册，可以使用");
			map.put("status", 1);
		}
		sendJsonData(response, JSON.toJSONString(map));
	}

	/**
	 * 向手机发送短信验证码
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月6日 上午10:23:01
	 * @param mobilephone
	 *            手机号码
	 * @throws Exception
	 */
	@RequestMapping(value = "/smssend")
	public void smsSend(String mobilephone) throws Exception {
		map.put("Msg", "短信发送失败");
		map.put("status", 0);
		// 正式使用时根据数据库短信接口表的设置获取
		System.out.println("手机号码:" + mobilephone);
		if (StringUtil.isNotEmpty(mobilephone)) {
			String verifyCode = String.valueOf(StringUtil.getN(6));
			boolean flag = SMSSend.smsSend(mobilephone,
					"您好，你在干将金融的注册验证码为" + verifyCode + ",收到验证码后的30分钟内注册，若非本人操作，请忽略本条短信内容");
			// String verifyCode = "111111";
			if (flag) {
				map.put("status", 1);
				map.put("Msg", "短信发送成功");
				map.put("code", verifyCode);
			}
		}
		sendJsonData(response, JSON.toJSONString(map));
	}

	/**
	 * 注册
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月6日 上午9:58:37
	 * @param mobile
	 *            手机号码
	 * @param password
	 *            密码
	 * @param code
	 *            验证码
	 * @param promoCode
	 *            推荐码
	 * @throws Exception
	 */
	@RequestMapping(value = "/register")
	public synchronized void register(String mobilephone, String password, String code, String promoCode)
			throws Exception {
		map.put("status", 0);
		/*
		 * 验证手机号
		 */
		if (StringUtil.isEmpty(mobilephone)) {
			map.put("Msg", "手机号码不能为空");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		if (mobilephone.length() != 11) {
			map.put("Msg", "请输入11位手机号码");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[0-9])|18[0-9])\\d{8}$");
		Matcher m = p.matcher(mobilephone);
		if (!m.find()) {
			map.put("Msg", "请输入正确的手机号码");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		UserBaseAccountInfo userbase = new UserBaseAccountInfo();
		userbase.setMobilephone(AES.getEncrypt(mobilephone));
		userbase = ubaiService.getUserBaseAccountInfoByOneCondition(userbase);
		if (null != userbase) {
			map.put("Msg", "手机号已注册，注册失败");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		/*
		 * 验证密码
		 */
		if (StringUtil.isEmpty(password)) {
			map.put("Msg", "密码不能为空");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		if (password.length() < 8 || password.length() > 20) {
			map.put("Msg", "密码长度为8～20位字符");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		p = Pattern.compile("^(?!\\D+$)(?![^a-zA-Z]+$)\\S{8,20}$");
		m = p.matcher(password);
		if (!m.find()) {
			map.put("Msg", "密码必须包含数字和字母");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		p = Pattern.compile("^\\S+$");
		m = p.matcher(password);
		if (!m.find()) {
			map.put("Msg", "密码不能包含空格");
			sendJsonData(response, JSON.toJSONString(map));
			return;
		}
		UserBaseAccountInfo ubai = new UserBaseAccountInfo();
		// 用户号
		ubai.setAccountnumber(accountnumber.nextStringValue());
		// 注册IP
		ubai.setRegip(StringUtil.getIpAddr(request));
		// 注册Cookie
		ubai.setRegcookie(setCookie());
		// 用户名
		ubai.setLoginname(AES.getEncrypt("gjjr" + mobilephone));
		// 手机号
		ubai.setMobilephone(AES.getEncrypt(mobilephone));
		// BCrypt 加密密码后存储
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		// 保存用户信息
		int iden1 = ubaiService.insertForRegister(ubai, hashed, (short) 3, (short) 2, promoCode, request, response);
		if (iden1 > 0) {
			// 推广信息保存
			map.put("status", 1);
			map.put("Msg", "注册成功");
			map.put("mobliephone", mobilephone);
			map.put("username", ubai.getLoginname());
			map.put("id", ubai.getId());
		} else {

			map.put("Msg", "注册失败");
			map.put("mobliephone", mobilephone);
		}
		sendJsonData(response, JSON.toJSONString(map));
	}
}
