<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://219.133.11.55:8887/ptpjx/moneymoremore/UserAccountRegister/doRegister.action" method="post">
	注册类型	: <input type="text" name="RegisterType"  /> <br /><br />
	账户类型	:<input type="text" name="AccountType" /> <br /><br />
	手机号	:<input type="text" name="Mobile"/> <br /><br />
	邮箱	:	<input type="text" name="Email" /> <br /><br />
	真实姓名/企业名	:<input type="text" name="RealName"/> <br /><br />
	身份证号/营业执照号	:<input type="text" name="IdentificationNo" /> <br /><br />
	用户在网贷平台的账号	:<input type="text" name="LoanPlatformAccount" value="123456780"/> <br /><br />
	平台乾多多标识:	<input type="text" name="PlatformMoneymoremore"/> <br /><br />
	随机时间戳:	<input type="text" name="RandomTimeStamp" /> <br /><br />
	后台通知网址	:<input type="text" name="NotifyURL" /><br /><br />
	页面返回网址	:<input type="text" name="ReturnURL" /><br /><br />
	自定义备注:	<input type="text" name="Remark1" /><br /><br />
	自定义备注	:<input type="text" name="Remark2" /><br /><br />
	自定义备注	:<input type="text" name="Remark3" /><br /><br />
 		<input value="提交" type="submit" />
 	</form>

</body>
</html>