<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hisun.iposm.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Date,java.text.*"%>
<%@ page import="java.net.*"%>
<%@ page import="com.hisun.merchant.atc.*"%>

<!DOCTYPE html>
<html>
        <head>
                <title>用户开户（页面方式）：register</title>
                
                <link href="sdk.css" rel="stylesheet" type="text/css" />
                <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
        </head>
        <body>
                <%
                String req_url="http://221.239.93.141:9080/bhdep/hipos/payTransaction";
                
              //获取表单提交的各参数
                
                String	char_set="02";
                String 	partner_id="800022000010004";
                String 	version_no = "2.0";
                String	biz_type="RealNameWeb";
                String 	sign_type="RSA";
                String	MerBillNo="21321312312";
                String	OpenType="1";
                String	IdentType="";
                String	IdentNo="37021319920623402X";
                String	UsrName="左晓丛";
                String	MobileNo="18289752379";
                String	OpenBankId="PAB";
                String	OpenAcctId="6216261000000000018";
                String	PageReturnUrl="http://my.ganjiangps.com";
                String	BgRetUrl="http://my.ganjiangps.com";
                String	MerPriv="";
                String	mac="";
                String  merchantCert="";
                
		try{
               
                   String encoding = "";
             			//编码设置
           			if ("00".equals(char_set)) {
           				request.setCharacterEncoding("GBK");
           				encoding = "GBK";
           			} else if ("01".equals(char_set)) {
           				request.setCharacterEncoding("GB2312");
           				encoding = "GB2312";
           			} else if ("02".equals(char_set)) {
           				request.setCharacterEncoding("UTF-8");
           				encoding = "UTF-8";
           			} else {
           				request.setCharacterEncoding("GBK");
           				encoding = "GBK";
           			}
                  
                  //读取CFCA配置参数
                  MerchantConfig.getConfig().loadPropertiesFromSrc();
                  String requestUrl = MerchantConfig.getConfig().getRequestUrl();
                  //String merCert = "";//partner_id + ".pfx"; //商户证书
                  String merchantCertPath = MerchantConfig.getConfig().getMerchantCertPath(); //证书路径
            	  
            	  //out.println("请求的商户证书地址：" + merchantCertPath);
            	  String merchantCertPass = MerchantConfig.getConfig().getMerchantCertPass();//证书密码
            	  //out.println("请求的商户证书密码：" + merchantCertPass);
            	  String rootCertPath = MerchantConfig.getConfig().getRootCertPath();//根证书
            	  //out.println("请求的根证书地址：" + rootCertPath);
            					        
                  //组装待RSA签名数据包
             	  String signData = char_set+partner_id+version_no+biz_type+sign_type+MerBillNo+OpenType+IdentType+IdentNo+UsrName+MobileNo+OpenBankId+OpenAcctId+PageReturnUrl+BgRetUrl+MerPriv;
                  
                  //签名，并获得签名后的消息摘要
                  //out.println("签名原报文："+signData);
                  //out.println("</br>");
                  //HisxfpayUtil util = new HisxfpayUtil();
                  //数据签名，hmac为签名后的消息摘要
                  //String mac = util.MD5Sign(signData , signKey);
                  //生成签名
                  RSASignUtil util = new RSASignUtil(merchantCertPath, merchantCertPass);
	              out.println("签名数据包：[" + signData+"]");
	              out.println("</br>");
	              String merchantSign = util.sign(signData, encoding);
	              mac = merchantSign;
	              merchantCert = util.getCertInfo();
	              out.println("请求数据包中的签名信息：" + merchantSign);
	              out.println("</br>");
	              out.println("证书信息："+ merchantCert);
			            
                   pageContext.setAttribute("char_set",char_set);                        
                   pageContext.setAttribute("partner_id",partner_id);                        
                   pageContext.setAttribute("version_no",version_no);
                   pageContext.setAttribute("biz_type",biz_type);
                   pageContext.setAttribute("sign_type",sign_type);
                   pageContext.setAttribute("MerBillNo",MerBillNo);
                   
                   
                   pageContext.setAttribute("OpenType",OpenType);
                   pageContext.setAttribute("IdentType",IdentType);
                   pageContext.setAttribute("IdentNo",IdentNo);
                   pageContext.setAttribute("UsrName",UsrName);
                   pageContext.setAttribute("MobileNo",MobileNo);
                   pageContext.setAttribute("OpenBankId",OpenBankId);
                   pageContext.setAttribute("OpenAcctId",OpenAcctId);
                   
                   pageContext.setAttribute("PageReturnUrl",PageReturnUrl);
                   pageContext.setAttribute("BgRetUrl",BgRetUrl);                             
                   pageContext.setAttribute("MerPriv",MerPriv);
                   pageContext.setAttribute("mac",merchantSign);
                   pageContext.setAttribute("merchantCert",merchantCert);
                   //out.println(merchantSign);
                 
            
}catch (Exception e){
   e.printStackTrace();
   out.println("交易异常:" + e.getMessage());
}
            out.println("Post Action：[" + req_url + "]");
                %>
         <form method="post" action="<%=req_url%>">
			字符集：<input type="text" name="char_set" value="<%=char_set%>" />   <br/>
			商户号：<input type="text" name="partner_id" value="<%=partner_id%>" /><br/>  
			
			版本号：<input type="text" name="version_no" value="<%=version_no%>" /><br/>  
			消息类型：<input type="text" name="biz_type" value="<%=biz_type%>" /><br/>  
			签名方式：<input type="text" name="sign_type" value="<%=sign_type%>" /><br/>  
			商户流水号：<input type="text" name="MerBillNo" value="<%=MerBillNo%>" /><br/>  
			
			开户类型：<input type="text" name="OpenType" value="<%=OpenType%>" /><br/>  
			证件类型：<input type="text" name="IdentType" value="<%=IdentType%>" /><br/>  
			证件号码：<input type="text" name="IdentNo" value="<%=IdentNo%>" /><br/>  
			姓名：<input type="text" name="UsrName" value="<%=UsrName%>" /><br/>  
			手机号：<input type="text" name="MobileNo" value="<%=MobileNo%>" /><br/>  
			开户银行代号：<input type="text" name="OpenBankId" value="<%=OpenBankId%>" /><br/>  
			开户银行账号：<input type="text" name="OpenAcctId" value="<%=OpenAcctId%>" /><br/>  
			
			页面返回url：<input type="text" name="PageReturnUrl" value="<%=PageReturnUrl%>" /><br/>  
			后台通知url：<input type="text" name="BgRetUrl" value="<%=BgRetUrl%>"/>		<br/>  
			商户保留域：<input type="text" name="MerPriv" value="<%=MerPriv%>" /><br/>  
			签名值：<input type="text" name="mac" value="<%=mac%>" /><br/>  
			商户证书：<input type="text" name="merchantCert" value="<%=merchantCert%>" /><br/>  
			<input type="submit" value="确认" />
		</form>
        </body>
</html>
