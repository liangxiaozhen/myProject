<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body onload="javascript:document.forms[0].submit()">
	<form name="UserBindCard"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method='post'>
		<input type='hidden' name='Version' value="${Version}"> <input
			type='hidden' name='CmdId' value="${CmdId}"> <input
			type='hidden' name='MerCustId' value="${MerCustId}"> <input
			type='hidden' name='UsrCustId' value="${UsrCustId}"> <input
			type='hidden' name='BgRetUrl' value="${BgRetUrl}"> <input
			type='hidden' name='MerPriv' value="${MerPriv}"> <input
			type='hidden' name='ChkValue' value="${ChkValue}">
	</form>
</body>
</html>
