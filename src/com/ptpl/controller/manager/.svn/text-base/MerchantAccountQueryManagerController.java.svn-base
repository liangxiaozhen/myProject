package com.ptpl.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AcctDetails;
import com.ptpl.model.QueryAccts;
import com.ptpl.web.util.HttpClientUtil;

@Controller
@RequestMapping("/admin/merchantaccountquery")
public class MerchantAccountQueryManagerController extends BaseController{
	
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//1,发起请求
		String Verion = "10";
		String CmdId = "QueryAccts";
		String MerCustId = "6000060004166478";
	    Map<String, String> params = new HashMap<String, String>();
        params.put("Version", Verion);
        params.put("CmdId", CmdId);
        params.put("MerCustId", MerCustId);
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(Verion)).append(StringUtils.trimToEmpty(CmdId))
	 	.append(StringUtils.trimToEmpty(MerCustId));
		String plainStr = sb.toString();
		params.put("ChkValue", SignUtils.encryptByRSA(plainStr));
		//2,接收请求并传递向页面
		 HttpClientUtil http = new HttpClientUtil();
		 String result = http.doPost(params);
		 System.out.println(result);
		 JSONObject json = JSON.parseObject(result); //解析汇付返回AcctDetails
		 QueryAccts queryAccts = JSON.toJavaObject(json,QueryAccts.class);//把json转为java对象
		 ModelAndView mav = new ModelAndView();
		// String str = json.getString("RespCode");
		 if(queryAccts.getRespCode().equals("000")){
			// JSONArray str1 = json.getJSONArray("AcctDetails");
			 List<AcctDetails> acctDetails = queryAccts.getAcctDetailsList();
			 mav.addObject("acctDetails", acctDetails);
			 mav.addObject("df", df);
			 mav.setViewName("admin/merchantaccount/merchantAccountList");
			/* Iterator<Object> it = str1.iterator(); 
			 while(it.hasNext()){ // 迭代AcctDetails数组
					JSONObject json2 = (JSONObject) it.next();
					System.out.println(json2);
			 } */
		 }
		return mav;
		
	}
}
