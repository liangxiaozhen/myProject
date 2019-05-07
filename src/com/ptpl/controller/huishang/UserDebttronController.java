package com.ptpl.controller.huishang;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("huishang/debttron")
public class UserDebttronController extends BaseController {

	@RequestMapping(value = "/userDrbttron",method = {RequestMethod.POST,RequestMethod.GET})
	public void bidApplyPost(HttpServletRequest request,HttpServletResponse response){
		LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
		//reqMap.put("VERSION", "1.1"); // 接口版本号——调用的接口版本，固定1.1
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——8位YYYYMMDD
		String TRXTIME = StringUtil.formatDate(date, "hhmmss"); // 交易时间TRXTIME——6为HHmmss
		reqMap.put("TRXDATE", TRXDATE); // 交易日期TRXDATE——8位YYYYMMDD
		reqMap.put("TRXTIME", TRXTIME); // 交易时间TRXTIME——6为HHmmss
		reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号COINSTCODE——800114
		reqMap.put("COINSTCHANNEL", "000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
		reqMap.put("CARDNBRI", "9930040050240300018"); // 承接方电子账号
		reqMap.put("SERI_NO", HSignUtil.COINSTCODE+StringUtil.getNo()); // (债转流水号)申请流水号SERI_NO——必填；用于交易的唯一性标识
		System.out.println(HSignUtil.COINSTCODE+StringUtil.getNo());
		reqMap.put("OLDSEQNO", "20170410163302906833"); // (投标流水号)申请流水号
		reqMap.put("CARDNBRO", "9930040050240500013"); //转让方电子账号
		reqMap.put("TBALACE", "50.00"); //总共可转让金额 :由转让人在不超过总原投标份额内进行控制
		reqMap.put("AMOUNT", "20.00"); //转让份额:本次转让份额+累计已转让份额不能大于总共可转让份额
		reqMap.put("TRPRICE", "21.00"); //转让价格:购买转让份额所需要的金额
		reqMap.put("INTDATE", "20170412"); //起息日
		reqMap.put("YIELD", "0.20000"); //转让后预期年化收益率
		reqMap.put("FEEWAY", "0"); //手续费扣费方式;0：指定金额；1：同产品设置
		reqMap.put("FEE", "5.00"); //转让手续费:手续费扣款方式为0时生效，可为0；手续费从转让方收取；
		reqMap.put("REMARK", "债转"); //备注
		reqMap.put("TRDRESV", ""); //第三方保留使用，原样返回
		reqMap.put("FORGERPWD_URL", ""); //用于债权转让页面中的忘记密码跳转 
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		reqMap.put("TRANSACTION_URL", basePath+"/user/tologin.action"); //返回交易页面链接
		// 动态获取项目路径
		reqMap.put("REBACK_URL", basePath+"/huishang/debttron/userDrbttronCallBack.action"); // 后台响应链接REBACK_URL——用于接收后台响应——选填
		reqMap.put("SUCCESSRESULT_URL", ""); //交易成功跳转链接
		
		
		try {
			String sign = HSignUtil.getRASSign(reqMap);
			reqMap.put("SIGN", sign);
			System.out.println(sign);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try {
			request.setAttribute("reqMap", reqMap);
			request.getRequestDispatcher("/WEB-INF/pages/userDettron/userdebttron.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/userDrbttronCallBack",method = {RequestMethod.POST,RequestMethod.GET})
	private void bidApplyCallBack(){
		String bgData=request.getParameter("bgData");
		Map<Object, Object> map=JSONObject.fromObject(bgData);
		Iterator  keys = map.entrySet().iterator();
		while(keys.hasNext()){
			System.out.println(keys.next());
		}
	}

	
}
