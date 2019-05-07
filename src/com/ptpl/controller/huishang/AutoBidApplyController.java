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
@RequestMapping("/huishang/autoBidApply")
public class AutoBidApplyController extends BaseController {

	@RequestMapping(value = "/autoBidApplyPost",method = {RequestMethod.POST,RequestMethod.GET})
	public void autoBidApplyPost(HttpServletRequest request,HttpServletResponse response){
		LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——8位YYYYMMDD
		String TRXTIME = StringUtil.formatDate(date, "hhmmss"); // 交易时间TRXTIME——6为HHmmss
		reqMap.put("TRXDATE", TRXDATE); // 交易日期TRXDATE——8位YYYYMMDD
		reqMap.put("TRXTIME", TRXTIME); // 交易时间TRXTIME——6为HHmmss
		reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号COINSTCODE——800114
		reqMap.put("COINSTCHANNEL", "000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
		reqMap.put("CARDNBR", "9930040050240500013"); // 电子账号CARDNBR
		reqMap.put("SERI_NO", HSignUtil.COINSTCODE+StringUtil.getNo()); // 签约交易流水号SERI_NO——必填；用于交易的唯一性标识，需前台保证唯一性,六位合作单位编号+各平台流水号生成规则
		reqMap.put("AMOUNT", "10000.00"); // 签约最高金额AMOUNT
		reqMap.put("REMARK", ""); // 备注REMARK——选填
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		reqMap.put("FORGERPWD_URL", basePath+"/huishang/tradePassword/tradePsswordList.action"); // 忘记密码跳转链接FORGERPWD_URL——用于投标页面中的忘记密码跳转——选填
		reqMap.put("TRANSACTION_URL", ""); // 返回交易页面链接TRANSACTION_URL——用于投标后，跳转原交易页面
		reqMap.put("REBACK_URL", basePath+"/huishang/bidApply/autoBidApplyCallBack.action"); // 后台响应链接REBACK_URL——用于接收后台响应——选填
		reqMap.put("SUCCESSRESULT_URL",""); // 交易成功跳转链接SUCCESSRESULT_URL——选填
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
			request.getRequestDispatcher("/WEB-INF/pages/AutoTenderPlan/autoTenderPlan.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void autoBidApplyCallBack(){
		System.out.println("进入投标回调");
		String bgData=request.getParameter("bgData");
		Map<Object, Object> map=JSONObject.fromObject(bgData);
		Iterator  keys = map.entrySet().iterator();
		while(keys.hasNext()){
			System.out.println(keys.next());
		}
	}

	
}
