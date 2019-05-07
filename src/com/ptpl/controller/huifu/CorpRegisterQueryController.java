package com.ptpl.controller.huifu;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

 
/**
 * 
* @ClassName: CorpRegisterQueryController 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(企业开户状态查询  Controller层) 
* @author chenjiaming
* @date 2016年8月13日 下午4:54:04 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/CorpRegisterQuery")
public class CorpRegisterQueryController extends BaseController {
	/**
	 * 
	* @Title: doCorpRegisterQuery 
	* @Description: TODO(查询企业开户审核状态) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ClientProtocolException
	* @param @throws IOException  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/doCorpRegisterQuery")
	public String doCorpRegisterQuery(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		String busiCode = "330183000147554";//营业执照编号   210000004942310
		Map<String,String> hashMap = getCorpRegisterQueryParams(busiCode, userBaseAccountInfo);
		String result = CorpRegisterQuery(hashMap);
 
		System.out.println(result+"==========");
        StringUtil.sendJsonData(response, result);
		return null;
	}
	
	/**
	 * 
	* @Title: CorpRegisterQuery 
	* @Description: TODO(企业开户状态查询 发送请求) 
	* @param @param hashMap
	* @param @return
	* @param @throws ClientProtocolException
	* @param @throws IOException  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	protected String CorpRegisterQuery(Map<String,String> hashMap) throws ClientProtocolException, IOException{
		String result = null;
    //    List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(hashMap);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        EntityBuilder builder = EntityBuilder.create();
        try {
            HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
          //  builder.setParameters(nvps);
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httpPost);
             try {
                 HttpEntity entity = response.getEntity();
                 if (response.getStatusLine().getReasonPhrase().equals("OK")
                    && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
                   EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
         return result;
	}

	/**
	 * 
	* @Title: getCorpRegisterQueryParams 
	* @Description: TODO(企业开户状态查询 请求参数设置) 
	* @param @param busiCode
	* @param @param userBaseAccountInfo
	* @param @return  参数说明 
	* @return Map<String,String>    返回类型 
	* @author chenjiaming
	* @throws
	 */
	protected Map<String,String>  getCorpRegisterQueryParams(String busiCode,UserBaseAccountInfo userBaseAccountInfo){
 		HuifuParams huifuParams = new HuifuParams();
		huifuParams.setCmdId("CorpRegisterQuery");//消息类型
		huifuParams.setBusiCode(busiCode);//营业执照编号
  		/*Version+CmdId+MerCustId+BusiCode+ReqExt*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));//版本号
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));//消息类型
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));//商户号
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBusiCode()));//营业执照编号
		buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));//营业执照编号
		
	    String str = buffer.toString();
	    String ChkValue ="";
	    try {
			  ChkValue = SignUtils.encryptByRSA(str);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		
	    Map<String,String> hashMap = new HashMap<String,String>();
	    hashMap.put("Version", huifuParams.getVersion());
	    hashMap.put("CmdId", huifuParams.getCmdId());
	    hashMap.put("MerCustId", huifuParams.getMerCustId());
	    hashMap.put("BusiCode", huifuParams.getBusiCode());
	    hashMap.put("CharSet", huifuParams.getCharSet());
	    hashMap.put("ReqExt", huifuParams.getReqExt());
 	    hashMap.put("ChkValue", ChkValue);
  	    return hashMap;
	}
}
