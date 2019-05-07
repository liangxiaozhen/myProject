package com.huifu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServlet;
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

import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;

public class LoansServlet extends HttpServlet {
    private static final long serialVersionUID = -1969188938538316979L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 1. 接收异步返回的消息
    	// 2. 对于后台返回方式，为了表示商户订单系统已经收到交易应答，商户必须在应答接收页面输出一段特殊的字符串,
    	//    特殊字符串的具体说明可参见《商户专属平台接口规范》的5.1.3交易类接口应答接受规范
        // 3. 注意，异步返回对信息做了url encode，在进行验签前需要先decode处理
        String cmdId = request.getParameter("CmdId");
        String respCode = request.getParameter("RespCode");
        String respDesc = request.getParameter("RespDesc");
        String merCustId = request.getParameter("MerCustId");
        String ordId = request.getParameter("OrdId");
        String ordDate = request.getParameter("OrdDate");
        String outCustId = request.getParameter("OutCustId");
        String outAcctId = request.getParameter("OutAcctId");
        String transAmt = request.getParameter("TransAmt");
        String fee = request.getParameter("Fee");
        String inCustId = request.getParameter("InCustId");
        String inAcctId = request.getParameter("InAcctId");
        String subOrdId = request.getParameter("SubOrdId");
        String subOrdDate = request.getParameter("SubOrdDate");
        String isDefault = request.getParameter("IsDefault");
        String bgRetUrl = request.getParameter("BgRetUrl");
        try {
            bgRetUrl = URLDecoder.decode(bgRetUrl, "UTF-8");
            
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String openBankId = request.getParameter("OpenBankId");
        String openAcctId = request.getParameter("OpenAcctId");
        String merPriv = request.getParameter("MerPriv");
        try {
            merPriv = URLDecoder.decode(merPriv, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String chkValue = request.getParameter("ChkValue");

        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(cmdId)).append(StringUtils.trimToEmpty(respCode))
            .append(StringUtils.trimToEmpty(merCustId)).append(StringUtils.trimToEmpty(ordId))
            .append(StringUtils.trimToEmpty(ordDate)).append(StringUtils.trimToEmpty(outCustId))
            .append(StringUtils.trimToEmpty(outAcctId)).append(StringUtils.trimToEmpty(transAmt))
            .append(StringUtils.trimToEmpty(fee))
            .append(StringUtils.trimToEmpty(inCustId)).append(StringUtils.trimToEmpty(inAcctId))
            .append(StringUtils.trimToEmpty(subOrdId)).append(StringUtils.trimToEmpty(subOrdDate))
            .append(StringUtils.trimToEmpty(isDefault))
            .append(StringUtils.trimToEmpty(bgRetUrl)).append(StringUtils.trimToEmpty(openBankId))
            .append(StringUtils.trimToEmpty(openAcctId)).append(StringUtils.trimToEmpty(merPriv));
        String plainStr = buffer.toString();
        System.out.println(plainStr+"-=-=-===============");
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
        merPriv = HttpClientHandler.getBase64Decode(merPriv);
        System.out.println("sdsdfdsfdsf===="+merPriv);

        try {
            if (StringUtils.isNotBlank(ordId)) {
                PrintWriter out = response.getWriter();
                out.print("RECV_ORD_ID_".concat(ordId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
    // 如果关注性能问题可以考虑使用HttpClientConnectionManager
 	public String doPost(Map<String, String> params) throws ClientProtocolException, IOException{
 		String result = null;
 		HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
 		CloseableHttpClient httpclient = HttpClients.createDefault();
 		if (params != null)
 		{
 			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
 			httpPost.setEntity(formEntiry);
 			CloseableHttpResponse response = httpclient.execute(httpPost);
 			try
 			{
 				HttpEntity entity = response.getEntity();
 				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
 					result = EntityUtils.toString(entity, "UTF-8");
 				    EntityUtils.consume(entity);
 			} finally
 			{
 				response.close();
 			}
 		}
 		return result;
 	}
}
