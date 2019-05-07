package com.ptpl.controller.huifu;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.controller.BaseController;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: InitiativeTenderTest 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(投标测试 无用) 
* @author chenjiaming
* @date 2016年9月27日 上午11:48:36 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/InitiativeTenderTest")
public class InitiativeTenderTest extends BaseController{
//版本号 Version定长 2 位的String必须2.0 接口中此字段的值为 20，如版本升级，能向前兼容
//消息类型 CmdId 变长 String 必须每一种消息类型代表一种交易，此处为InitiativeTender
//商户客户号 MerCustId变长 16 位的String必须 由汇付生成，商户的唯一性标识
//订单号 OrdId变长 30 位的String必须 由商户的系统生成，必须保证唯一，请使用纯数字
//订单日期 OrdDate定长 8 位的String必须 格式为 YYYYMMDD，例如：20130307
//交易金额 TransAmt变长 14 位的String必须1) 泛指交易金额，如充值、支付、取现、冻结和解冻金额（金额格式必须是###.00）比如 2.00，2.012) 如果使用代金券，则此金额包含代金券的金额，为投资人实际投资金额
//用户客户号 UsrCustId变长 16 位的String必须 由汇付生成，用户的唯一性标识
//最大投资手续费率MaxTenderRate变长 6 位的String 必须
//借款人信息BorrowerDetails变长 array 必须
//支持传送多个借款人信息，使用 json 格式传送
//	/*[{"BorrowerCustId":"6000010000000014"，
//	"BorrowerAmt": "20.01"，
//	"BorrowerRate":"0.18"，
//	"ProId":"0000000000000001" }，
//	{"BorrowerCustId": "6000010000000014"，
//	"BorrowerAmt":"20.01"， "BorrowerRate":"0.18" ,
//	"ProId":"0000000000000002" }，
//	{"BorrowerCustId":"6000010000000014"，
//	"BorrowerAmt": "20.01"， "BorrowerRate":
//	"0.18" , "ProId":"0000000000000003" }]*/
//借款人客户号BorrowerCustId变长 16 位的String必须 BorrowerDetails 参数下的二级参数借款人客户号，由汇付生成，用户的唯一性标识
//借款金额BorrowerAmt变长 12 位的String必须BorrowerDetails 参数下的二级参数借款金额借款手续费率BorrowerRate变长 6 位的String必须
 //项目 ID ProId变长 16 位的String必须BorrowerDetails 参数下的二级参数
//是否冻结 IsFreeze定长 1 位的String必须Y--冻结N--不冻结
//冻结订单号FreezeOrdId变长 30 位的String可选
//如果 IsFreeze 参数传 Y，那么该参数不能为空
//页面返回 URL RetUrl变长 128 位的 String可选
 //商户后台应答地址BgRetUrl变长 128 位的 String必须
//商户私有域 MerPriv变长 120 位的 String可选
//入参扩展域 ReqExt变长 512 位的 String可选用于扩展请求参数
//	/*{"Vocher":{"AcctId":"MDT000001","Vo
//	*/cherAmt":"5.00"}}
//代金券出账子账户 AcctId变长 12 位的String可选 商户为用户代金券出账的子账户号
//代金券金额 VocherAmt变长 12 位的String可选
//代金券金额（金额格式必须是###.00）比如 2.00,2.01
	@RequestMapping("/doinittest")
	public void doinittest(HttpServletRequest request ,HttpServletResponse response){
		HuifuParams huifuParams = new HuifuParams();
		huifuParams.setVersion("20");//版本号 Version定长 2 位的String必须2.0 接口中此字段的值为 20，如版本升级，能向前兼容
		huifuParams.setCmdId("InitiativeTender");//消息类型 CmdId 变长 String 必须每一种消息类型代表一种交易，此处为InitiativeTender
		//商户客户号 MerCustId变长 16 位的String必须 由汇付生成，商户的唯一性标识
		huifuParams.setOrdId(StringUtil.getNo());//订单号 OrdId变长 30 位的String必须 由商户的系统生成，必须保证唯一，请使用纯数字
		huifuParams.setOrdDate(StringUtil.formatDate(new Date(), "yyyyMMdd"));//订单日期 OrdDate定长 8 位的String必须 格式为 YYYYMMDD，例如：20130307
		huifuParams.setTransAmt("30.00");//交易金额 TransAmt变长 14 位的String必须1) 泛指交易金额，如充值、支付、取现、冻结和解冻金额（金额格式必须是###.00）比如 2.00，2.012) 如果使用代金券，则此金额包含代金券的金额，为投资人实际投资金额
		huifuParams.setUsrCustId("6000060004191057");//用户客户号 UsrCustId变长 16 位的String必须 由汇付生成，用户的唯一性标识
		huifuParams.setMaxTenderRate("0.50");//最大投资手续费率MaxTenderRate变长 6 位的String 必须
 		//支持传送多个借款人信息，使用 json 格式传送
			/*[{"BorrowerCustId":"6000010000000014"，
			"BorrowerAmt": "20.01"，
			"BorrowerRate":"0.18"，
			"ProId":"0000000000000001" }，
			{"BorrowerCustId": "6000010000000014"，
			"BorrowerAmt":"20.01"， "BorrowerRate":"0.18" ,
			"ProId":"0000000000000002" }，
			{"BorrowerCustId":"6000010000000014"，
			"BorrowerAmt": "20.01"， "BorrowerRate":
			"0.18" , "ProId":"0000000000000003" }]*/
		huifuParams.setBorrowerCustId("6000060004194928");//借款人客户号BorrowerCustId变长 16 位的String 必须 BorrowerDetails 参数下的二级参数借款人客户号，由汇付生成，用户的唯一性标识
		huifuParams.setBorrowerAmt("30.00");//借款金额BorrowerAmt变长 12 位的 String 必须 BorrowerDetails 参数下的二级参数借款金额借款手续费率BorrowerRate变长 6 位的String必须
		huifuParams.setBorrowerRate("1.00");//借款手续费率BorrowerRate变长 6 位的String 必须 BorrowerDetails 参数下的二级参数
		huifuParams.setProId("gjbd2016100812"); //项目 ID ProId变长 16 位的String 必须 BorrowerDetails 参数下的二级参数
		String str = "[{\"BorrowerCustId\":\""+huifuParams.getBorrowerCustId()+"\","+
					 "\"BorrowerAmt\":\""+huifuParams.getBorrowerAmt()+"\","+
					 "\"BorrowerRate\":\""+huifuParams.getBorrowerRate()+"\","+
					 "\"ProId\":\""+huifuParams.getProId()+"\"}]";
		String str1 = "[{&quot;BorrowerCustId&quot;:&quot;"+huifuParams.getBorrowerCustId()+"&quot;,"+
					  "&quot;BorrowerAmt&quot;:&quot;"+huifuParams.getBorrowerAmt()+"&quot;,"+
					  "&quot;BorrowerRate&quot;:&quot;"+huifuParams.getBorrowerRate()+"&quot;,"+
					  "&quot;ProId&quot;:&quot;"+huifuParams.getProId()+"&quot;}]";
		huifuParams.setBrowserStr(str1);
		huifuParams.setBorrowerDetails(str);//借款人信息BorrowerDetails变长 array 必须
		huifuParams.setIsFreeze("Y");//是否冻结 IsFreeze定长 1 位的String必须Y--冻结N--不冻结
		huifuParams.setFreezeOrdId(StringUtil.getNo());//冻结订单号FreezeOrdId变长 30 位的String可选 如果 IsFreeze 参数传 Y，那么该参数不能为空
 		//页面返回 URL RetUrl变长 128 位的 String可选
		huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/InitiativeTenderTest/callback.action"); //商户后台应答地址BgRetUrl变长 128 位的 String必须
		//huifuParams.setMerPriv("");//商户私有域 MerPriv变长 120 位的 String可选
		//入参扩展域 ReqExt变长 512 位的 String可选用于扩展请求参数
			/*{"Vocher":{"AcctId":"MDT000001","Vo
			cherAmt":"5.00"}}*/
		//代金券出账子账户 AcctId变长 12 位的String可选 商户为用户代金券出账的子账户号
		//代金券金额 VocherAmt变长 12 位的String可选
		//代金券金额（金额格式必须是###.00）比如 2.00,2.01
		try {
			getparams(huifuParams, request, response);
		} catch (ServletException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
	}
	 
 public void getparams(HuifuParams huifuParams,HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException{
	 /*Version +CmdId + MerCustId + OrdId +OrdDate + TransAmt + UsrCustId +
	 MaxTenderRate + BorrowerDetails + IsFreeze+FreezeOrdId+ RetUrl +BgRetUrl + 
	 MerPriv+ReqExt+ PageType*/
	 StringBuffer buffer = new StringBuffer();
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getMaxTenderRate()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getBorrowerDetails()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getIsFreeze()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getFreezeOrdId()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
	 buffer.append(StringUtils.trimToEmpty(huifuParams.getPageType()));
 	 String str = buffer.toString();
	 String ChkValue ="";
	 try {
		ChkValue = SignUtils.encryptByRSA(str);
		if(StringUtil.isNotEmpty(ChkValue)){
			huifuParams.setChkValue(ChkValue);
		}
	} catch (Exception e) {
 		e.printStackTrace();
	}
	 
	 request.setAttribute("Version", huifuParams.getVersion());
	 request.setAttribute("CmdId", huifuParams.getCmdId());
	 request.setAttribute("MerCustId", huifuParams.getMerCustId());
	 request.setAttribute("OrdId", huifuParams.getOrdId());
	 request.setAttribute("OrdDate", huifuParams.getOrdDate());
	 request.setAttribute("TransAmt", huifuParams.getTransAmt());
	 request.setAttribute("UsrCustId", huifuParams.getUsrCustId()); 
	 request.setAttribute("MaxTenderRate", huifuParams.getMaxTenderRate());
	 request.setAttribute("BorrowerDetails", huifuParams.getBrowserStr());
	 request.setAttribute("BorrowerCustId", huifuParams.getBorrowerCustId());
	 request.setAttribute("BorrowerAmt", huifuParams.getBorrowerAmt());
	 request.setAttribute("BorrowerRate", huifuParams.getBorrowerRate());
	 request.setAttribute("ProId", huifuParams.getProId());
	 request.setAttribute("IsFreeze", huifuParams.getIsFreeze());
	 request.setAttribute("FreezeOrdId", huifuParams.getFreezeOrdId());
	 request.setAttribute("IsFreeze", huifuParams.getIsFreeze());
	 request.setAttribute("RetUrl", huifuParams.getRetUrl());
	 request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());
	 request.setAttribute("MerPriv", huifuParams.getMerPriv());
	 request.setAttribute("ReqExt", huifuParams.getReqExt());
	 request.setAttribute("AcctId", huifuParams.getAcctId());
	 request.setAttribute("VocherAmt", huifuParams.getVocherAmt());
	 request.setAttribute("ChkValue", huifuParams.getChkValue());
	 request.setAttribute("CharSet", huifuParams.getCharSet());
	 
	 request.getRequestDispatcher("/WEB-INF/pages/test/InitiativeTenderTest.jsp").forward(request, response);
  }
	@RequestMapping("/callback")
	public  void  callback(HuifuParams huifuParams,HttpServletRequest request ,HttpServletResponse response){
		System.out.println("=======CmdId==========="+huifuParams.getCmdId());
		System.out.println("=======RespCode==========="+huifuParams.getRespCode());
		System.out.println("=======RespDesc==========="+huifuParams.getRespDesc());
		System.out.println("=======MerCustId==========="+huifuParams.getMerCustId());
		System.out.println("=======OrdId==========="+huifuParams.getOrdId());
		System.out.println("=======OrdDate==========="+huifuParams.getOrdDate());
		System.out.println("=======TransAmt==========="+huifuParams.getTransAmt());
		System.out.println("=======UsrCustId==========="+huifuParams.getUsrCustId());
		System.out.println("=======TrxId==========="+huifuParams.getTrxId());
		System.out.println("=======IsFreeze==========="+huifuParams.getIsFreeze());
		System.out.println("=======FreezeOrdId==========="+huifuParams.getFreezeOrdId());
		System.out.println("=======FreezeTrxId==========="+huifuParams.getFreezeTrxId());
		System.out.println("=======RetUrl==========="+huifuParams.getRetUrl());
		System.out.println("=======BgRetUrl==========="+huifuParams.getBgRetUrl());
 		
		//消息类型 CmdId 变长 String 必须每一种消息类型代表一种交易，此处为InitiativeTender
		//应答返回码 RespCode定长 3 位的String必须000--调用成功
		//应答描述 RespDesc 变长 String 必须 返回码的对应中文描述
		//商户客户号 MerCustId变长 16 位的String必须 由汇付生成，商户的唯一性标识
		//订单号 OrdId变长 30 位的String必须 由商户的系统生成，必须保证唯一，请使用纯数字
		//订单日期 OrdDate定长 8 位的String必须 格式为 YYYYMMDD，例如：20130307
		//交易金额 TransAmt变长 14 位的String必须泛指交易金额，如充值、支付、取现、冻结和解冻金额（金额格式必须是###.00）比如 2.00，2.01
		//用户客户号 UsrCustId变长 16 位的String必须 由汇付生成，用户的唯一性标识
		//本平台交易唯一标识TrxId定长 18 位的String可选 组成规则为：8 位本平台日期+10 位系统流水号
		//是否冻结 IsFreeze定长 1 位的String必须Y--冻结N--不冻结
		//冻结订单号FreezeOrdId变长 30 位的String可选 冻结订单号
		//冻结标识 FreezeTrxId定长 18 位的String可选 组成规则为：8 位本平台日期+10 位系统流水号
		//页面返回 URL RetUrl变长 128 位的 String可选
		//商户后台应答地址BgRetUrl变长 128 位的 String必须
		//输出 RECV_ORD_ID 字样的字符串，表明商户已经
 	}
	
	@RequestMapping("/dotest")
	public void  doTenertest(){
		HuifuParams huifuParams = new HuifuParams();
		huifuParams.setVersion("20");//版本号 Version定长 2 位的String必须2.0 接口中此字段的值为 20，如版本升级，能向前兼容
		huifuParams.setCmdId("InitiativeTender");//消息类型 CmdId 变长 String 必须每一种消息类型代表一种交易，此处为InitiativeTender
		//商户客户号 MerCustId变长 16 位的String必须 由汇付生成，商户的唯一性标识
		huifuParams.setOrdId(StringUtil.getNo());//订单号 OrdId变长 30 位的String必须 由商户的系统生成，必须保证唯一，请使用纯数字
		huifuParams.setOrdDate(StringUtil.formatDate(new Date(), "yyyyMMdd"));//订单日期 OrdDate定长 8 位的String必须 格式为 YYYYMMDD，例如：20130307
		huifuParams.setTransAmt("100.00");//交易金额 TransAmt变长 14 位的String必须1) 泛指交易金额，如充值、支付、取现、冻结和解冻金额（金额格式必须是###.00）比如 2.00，2.012) 如果使用代金券，则此金额包含代金券的金额，为投资人实际投资金额
		huifuParams.setUsrCustId("6000060005590042");//用户客户号 UsrCustId变长 16 位的String必须 由汇付生成，用户的唯一性标识
		huifuParams.setMaxTenderRate("0.50");//最大投资手续费率MaxTenderRate变长 6 位的String 必须
 		huifuParams.setBorrowerCustId("6000060005590006");//借款人客户号BorrowerCustId变长 16 位的String 必须 BorrowerDetails 参数下的二级参数借款人客户号，由汇付生成，用户的唯一性标识
		huifuParams.setBorrowerAmt("100.00");//借款金额BorrowerAmt变长 12 位的 String 必须 BorrowerDetails 参数下的二级参数借款金额借款手续费率BorrowerRate变长 6 位的String必须
		huifuParams.setBorrowerRate("1.00");//借款手续费率BorrowerRate变长 6 位的String 必须 BorrowerDetails 参数下的二级参数
		huifuParams.setProId("gjbd2016100912"); //项目 ID ProId变长 16 位的String 必须 BorrowerDetails 参数下的二级参数
		String str = "[{\"BorrowerCustId\":\""+huifuParams.getBorrowerCustId()+"\","+
					 "\"BorrowerAmt\":\""+huifuParams.getBorrowerAmt()+"\","+
					 "\"BorrowerRate\":\""+huifuParams.getBorrowerRate()+"\","+
					 "\"ProId\":\""+huifuParams.getProId()+"\"}]";
 		huifuParams.setBorrowerDetails(str);//借款人信息BorrowerDetails变长 array 必须
		huifuParams.setIsFreeze("Y");//是否冻结 IsFreeze定长 1 位的String必须Y--冻结N--不冻结
		huifuParams.setFreezeOrdId(StringUtil.getNo());//冻结订单号FreezeOrdId变长 30 位的String可选 如果 IsFreeze 参数传 Y，那么该参数不能为空
 		//页面返回 URL RetUrl变长 128 位的 String可选
		huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/InitiativeTenderTest/callback.action");
		singInitativeTener(huifuParams);
	}
	
	public void singInitativeTener(HuifuParams huifuParams){
		/*Version +CmdId + MerCustId + OrdId +OrdDate + TransAmt + UsrCustId +
		 MaxTenderRate + BorrowerDetails + IsFreeze+FreezeOrdId+ RetUrl +BgRetUrl + 
		 MerPriv+ReqExt+ PageType*/
		 StringBuffer buffer = new StringBuffer();
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMaxTenderRate()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getBorrowerDetails()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getIsFreeze()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getFreezeOrdId()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
		 buffer.append(StringUtils.trimToEmpty(huifuParams.getPageType()));
	 	 String str = buffer.toString();
		 String ChkValue ="";
		 try {
			ChkValue = SignUtils.encryptByRSA(str);
			if(StringUtil.isNotEmpty(ChkValue)){
				huifuParams.setChkValue(ChkValue);
			}
		} catch (Exception e) {
	 		e.printStackTrace();
		}
		 Map<String,String> hashMap = new HashMap<String,String>();
		 hashMap.put("Version", huifuParams.getVersion());
		 hashMap.put("CmdId", huifuParams.getCmdId());
		 hashMap.put("MerCustId", huifuParams.getMerCustId());
		 hashMap.put("OrdId", huifuParams.getOrdId());
		 hashMap.put("OrdDate", huifuParams.getOrdDate());
		 hashMap.put("TransAmt", huifuParams.getTransAmt());
		 hashMap.put("UsrCustId", huifuParams.getUsrCustId()); 
		 hashMap.put("MaxTenderRate", huifuParams.getMaxTenderRate());
		 hashMap.put("BorrowerDetails", huifuParams.getBorrowerDetails());
		 hashMap.put("BorrowerCustId", huifuParams.getBorrowerCustId());
		 hashMap.put("BorrowerAmt", huifuParams.getBorrowerAmt());
		 hashMap.put("BorrowerRate", huifuParams.getBorrowerRate());
		 hashMap.put("ProId", huifuParams.getProId());
		 hashMap.put("IsFreeze", huifuParams.getIsFreeze());
		 hashMap.put("FreezeOrdId", huifuParams.getFreezeOrdId());
		 hashMap.put("IsFreeze", huifuParams.getIsFreeze());
		 hashMap.put("RetUrl", huifuParams.getRetUrl());
		 hashMap.put("BgRetUrl", huifuParams.getBgRetUrl());
		 hashMap.put("MerPriv", huifuParams.getMerPriv());
		 hashMap.put("ReqExt", huifuParams.getReqExt());
		 hashMap.put("AcctId", huifuParams.getAcctId());
		 hashMap.put("VocherAmt", huifuParams.getVocherAmt());
		 hashMap.put("ChkValue", huifuParams.getChkValue());
		 hashMap.put("CharSet", huifuParams.getCharSet());
		 String result;
		try {
			result = doPost(hashMap);
			System.out.println("============="+result);
		} catch (ClientProtocolException e) {
 			e.printStackTrace();
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
