package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 徽商银行电子账户签约卡绑定关系取消
 * @ClassName: RelieveBindCardController
 * @Description: TODO(徽商银行电子账户签约卡绑定关系取消)
 * @author zhenglm
 * @date 2017年4月6日 下午3:31:47
 *
 */
@RequestMapping("/huishang/relieveBindCard")
@Controller
public class RelieveBindCardController extends BaseController{
	 
	/** 用户托管账户信息Service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;

	/** 用户银行卡信息Service */
	@Autowired
	UserBankCardServiceI userBankCardService;
	
	/**
	 * 徽商银行电子账户签约卡绑定关系取消接口调用
	 * @Title: removeBindCard
	 * @Description: TODO(徽商银行电子账户签约卡绑定关系取消接口调用)
	 * @param request
	 * @param response
	 * @return void    返回类型
	 */
	@RequestMapping(value = "/removeBindCard",method = {RequestMethod.POST,RequestMethod.GET})
	public void removeBindCard(HttpServletRequest request,HttpServletResponse response,BigDecimal baseid) {
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		UserBankCard usBankCard = null;
		if(baseid != null){
			usBankCard = userBankCardService.selectBoundCardByBaseId(baseid);
		}
		if(userBaseAccountInfo != null && usBankCard != null && baseid.equals(userBaseAccountInfo.getId())){
			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
			userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);//解密数据库加密信息
			
			LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
			// 请求报文头
			reqMap.put("TRXCODE", "5813"); // 交易代码TRXCODE
			reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
			Date date = new Date();
			String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——YYYYMMDD
			String TRXTIME = StringUtil.formatDate(date, "hhmmss"); // 交易时间	TRXTIME——hhmmss
			reqMap.put("TRXDATE", TRXDATE); // 交易日期TRXDATE——YYYYMMDD
			reqMap.put("TRXTIME", TRXTIME); // 交易时间	TRXTIME——hhmmss
			reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号COINSTCODE——800114
			reqMap.put("COINSTCHANNEL","000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
			reqMap.put("SEQNO",StringUtil.getNo()); // 交易流水号SEQNO——8 ~ 20 位数字流水号
			reqMap.put("SOURCE", HSignUtil.SOURCE); // ESB内部渠道SOURCE——A0
			reqMap.put("RETCODE", ""); // 应答码RETCODE——填空
			reqMap.put("RETMSG", ""); // 应答码描述RETMSG——填空
			reqMap.put("HEADRESERVED", usBankCard.getId().toString()); // 报文头保留域HEADRESERVED——可选
			// 请求报文
			reqMap.put("CARDNBR",userFsAccountInfo.getUsrcustid()); // 电子账号CARDNBR
			reqMap.put("PINFLAG", "0"); // 使用密码标志PINFLAG——（0：不使用密码 、1：使用消费密码、2：使用查询密码）
			reqMap.put("PIN", ""); // 密码PIN
	        reqMap.put("SIG_CARD", getDecrypt(usBankCard.getCardno())); // 绑定卡号SIG_CARD
			reqMap.put("REMARK","解绑"); // 备注
			String result = null;
			try {
				result = HSignUtil.HttpClientTask(reqMap, "");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String code = callBack(result);
			String msg = "";
			if(code.equals("00000000")){
				msg = "删除成功!";
			}else{
				msg = "删除失败!";
				if(code.equals("CA101134")){
					msg = "账户余额不为0，不能解除绑定";
				} 
 			}
			try {
				sendJsonData(response, msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 徽商银行电子账户签约卡绑定请求数据加签加密并发送HttpClient请求
	 * @Title: HttpClientTask
	 * @Description: TODO(徽商银行电子账户签约卡绑定请求数据加签加密并发送HttpClient请求)
	 * @param reqMap
	 * @param version
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 * @return String    返回类型
	 */
	/*private String  HttpClientTask(LinkedHashMap<String, String> reqMap,String version) throws UnsupportedEncodingException, Exception{
		String TaskResult = "";
		if(reqMap.size() > 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("BANKCODE",reqMap.get("BANKCODE")); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
			map.put("COINSTCODE",reqMap.get("COINSTCODE")); // 合作单位编号COINSTCODE——800114
			map.put("APIVERSION",version); // 为报文版本号
			String sign = HSignUtil.getRASSign(reqMap); // 加签抛送银行的的数据
 			reqMap.put("SIGN", sign);
			String data = HSignUtil.getEncryptRSAByte(reqMap); // 进行摘要并对摘要进行加密
			map.put("reqMap",data); // 加密后的json参数
			TaskResult = HSignUtil.doHttpClient(map); // 发送HttpClient请求
		}
		return TaskResult;
	}*/
	
	/**
	 * 拼接徽商银行电子账户签约卡绑定返回数据验证签名并保存用户银行卡信息
	 * @Title: callBack
	 * @Description: TODO(拼接徽商银行电子账户签约卡绑定返回数据验证签名并保存用户银行卡信息)
	 * @param respoResult
	 * @return void    返回类型
	 */
	private String callBack(String respoResult){
		String result = HSignUtil.getDecryptRSAByte(respoResult);//解密
		JSONObject jsonObject = JSONObject.fromObject(result);

		String  TRXCODE 		= jsonObject.getString("TRXCODE");//交易代码 TRXCODE
		String  BANKCODE 		= jsonObject.getString("BANKCODE");//银行代码	BANKCODE
		String  TRXDATE 		= jsonObject.getString("TRXDATE");//交易日期	TRXDATE
		String  TRXTIME 		= jsonObject.getString("TRXTIME");//交易时间	TRXTIME
		String  COINSTCODE 	= jsonObject.getString("COINSTCODE");//合作单位编号	COINSTCODE
		String  COINSTCHANNEL = jsonObject.getString("COINSTCHANNEL");//合作单位渠道	COINSTCHANNEL
		String  SEQNO 		= jsonObject.getString("SEQNO");//交易流水号	SEQNO
		String  SOURCE 		= jsonObject.getString("SOURCE");//ESB内部渠道	SOURCE
		String  RETCODE 		= jsonObject.getString("RETCODE");//应答码	RETCODE
		String  RETMSG 		= jsonObject.getString("RETMSG");//应答码描述	RETMSG
		String  HEADRESERVED 	= jsonObject.getString("HEADRESERVED");//报文头保留域	HEADRESERVED

		String responseSign = jsonObject.getString("SIGN");

		System.out.println("========TRXCODE================"+TRXCODE);
		System.out.println("========BANKCODE================"+BANKCODE);
		System.out.println("========TRXDATE================"+TRXDATE);
		System.out.println("========TRXTIME================"+TRXTIME);
		System.out.println("========COINSTCODE================"+COINSTCODE);
		System.out.println("========COINSTCHANNEL================"+COINSTCHANNEL);
		System.out.println("========SEQNO================"+SEQNO);
		System.out.println("========SOURCE================"+SOURCE);
		System.out.println("========RETCODE================"+RETCODE);
		System.out.println("========RETMSG================"+RETMSG);
		System.out.println("========HEADRESERVED================"+HEADRESERVED);

		String  CARDNBR 		= jsonObject.getString("CARDNBR"); // 电子账户CARDNBR
		String  NAME 			= jsonObject.getString("NAME"); // 持卡人姓名NAME
		String  SIGNFLAG 			= jsonObject.getString("SIGNFLAG"); // 签约状态SIGNFLAG
		String  TXNDATE 			= jsonObject.getString("TXNDATE"); // 签约日期TXNDATE
		String  TXNTIME 			= jsonObject.getString("TXNTIME"); // 签约时间TXNTIME
		String  CANCLDATE 			= jsonObject.getString("CANCLDATE"); // 签约取消日期TXNDATE
		String  CANCLTIME 			= jsonObject.getString("CANCLTIME"); // 签约取消时间TXNTIME
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(TRXCODE));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(SEQNO));
		buffer.append(StringUtils.trimToEmpty(SOURCE));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		buffer.append(StringUtils.trimToEmpty(HEADRESERVED));

		buffer.append(StringUtils.trimToEmpty(CARDNBR));
		buffer.append(StringUtils.trimToEmpty(NAME));
		buffer.append(StringUtils.trimToEmpty(SIGNFLAG));
		buffer.append(StringUtils.trimToEmpty(TXNDATE));
		buffer.append(StringUtils.trimToEmpty(TXNTIME));
		buffer.append(StringUtils.trimToEmpty(CANCLDATE));
		buffer.append(StringUtils.trimToEmpty(CANCLTIME));
		String str = buffer.toString();

		boolean verifyResult = HSignUtil.getVerify(str, responseSign);

		if (!verifyResult){
			System.out.println("验证签名失败...");
		} else {
			System.out.println("验证签名成功");
		}
		if ("00000000".equals(jsonObject.getString("RETCODE"))){
			System.out.println("操作成功");
			UserBankCard userbankcard = userBankCardService.findDetailById(Long.valueOf(HEADRESERVED));
			userbankcard.setBindstatus(Constant.BINDSTATUS_UNBIND);
			userbankcard.setIsfastbindcard(Constant.ISFASTBINDCARD_PUTONG);
			userbankcard.setIsdefaultcard(Constant.ISDEFAULTCARD_NO);
			userBankCardService.updateUserBankInfo(userbankcard);
		} else {
			System.out.println("操作失败,错误代码："+jsonObject.getString("RETCODE"));
		}
		return RETCODE;
	}
	
}
