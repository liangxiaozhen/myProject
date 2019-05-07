<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的信息补录</title>
<!-- onload="javascript:document.forms[0].submit()" -->
</head>
<body>
	<form name=QueryBidInfo
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		<input type="submit" value="提交" /><br /> ${Version}<br /> ${CmdId}<br />
		${MerCustId}<br /> ${ProId}<br /> ${BgRetUrl}<br /> ${ChkValue}<br />

		<input type="text" name="Version" value="${Version}" /> <input
			type="text" name="CmdId" value="${CmdId}" /> <input type="text"
			name="MerCustId" value="${MerCustId}" /> <input type="text"
			name="ProId" value="${ProId}" /> <input type="text" name="BgRetUrl"
			value="${BgRetUrl}" /> <input type="text" name="ChkValue"
			value="${ChkValue}" />



	</form>
</body>
</html>