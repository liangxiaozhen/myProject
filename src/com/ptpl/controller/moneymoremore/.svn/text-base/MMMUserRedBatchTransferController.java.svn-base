package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.httpClient.HttpClientHandler;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.HttpClientUtil;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.moneymoremore.model.LoanReturnInfoBean;
import com.ptpl.controller.moneymoremore.model.LoanSubmitInfoBean;
import com.ptpl.controller.moneymoremore.model.LoanTransferReturnBean;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/moneymoremore/transfer")
public class MMMUserRedBatchTransferController {
	
	@RequestMapping("/user999RedAddressNotify")
	public void user999RedNotifyAddress(HttpServletRequest request,HttpServletResponse response,LoanTransferReturnBean ltrb){
		
		System.out.println("ltrb: "+ltrb);
		
		/*JSONArray jsonArray = JSONArray.parseArray(Common.UrlDecoder(ltrb.getLoanJsonList(), "utf-8"));
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
			String LoanOutMoneymoremore = jsonObject.getString("LoanOutMoneymoremore");
			String LoanInMoneymoremore = jsonObject.getString("LoanInMoneymoremore");
			String LoanNo = jsonObject.getString("LoanNo");
			String OrderNo = jsonObject.getString("OrderNo");
			String BatchNo = jsonObject.getString("BatchNo");
			String Remark = jsonObject.getString("Remark");
			System.out.println("LoanOutMoneymoremore: "+LoanOutMoneymoremore);
			System.out.println("LoanInMoneymoremore: "+LoanInMoneymoremore);
			System.out.println("LoanNo: "+LoanNo);
			System.out.println("OrderNo: "+OrderNo);
			System.out.println("BatchNo: "+BatchNo);
			System.out.println("Remark: "+Remark);
		}*/
		
		
		try {
			request.setCharacterEncoding("UTF-8");
			String loanJsonList = Common.UrlDecoder(ltrb.getLoanJsonList(), "utf-8");
			if(ltrb.getAction()==null){
				ltrb.setAction("");
			}
			
			String dataStr = loanJsonList.trim() + ltrb.getPlatformMoneymoremore().trim() +ltrb.getAction().trim()+ ltrb.getResultCode().trim();
					
			RsaHelper rsa = RsaHelper.getInstance();
			boolean flag = rsa.verifySignature(ltrb.getSignInfo(), dataStr, RsaHelper.publicString);
			
			System.out.println("验签flag是否成功==="+flag);
			System.out.println("ResultCode返回码===="+ltrb.getResultCode());
			
			if("88".equals(ltrb.getResultCode().trim())){
				
				response.setContentType("text/plain;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				try {
					response.getWriter().write("SUCCESS");
					response.getWriter().flush();
					response.getWriter().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
}
