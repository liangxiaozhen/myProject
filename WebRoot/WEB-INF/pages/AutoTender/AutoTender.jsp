<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发起开户请求</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<!--   <body onload="javascript:document.forms[0].submit()"> -->
<body>
	<form name="AutoTender"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method='post'>
		<input type='text' name='Version' value="${Version}"> <input
			type='text' name='CmdId' value="${CmdId}"> <input type='text'
			name='MerCustId' value="${MerCustId}"> <input type='text'
			name='OrdId' value="${OrdId}"> <input type='text'
			name='OrdDate' value="${OrdDate}"> <input type='text'
			name='TransAmt' value="${TransAmt}"> <input type='text'
			name='UsrCustId' value="${UsrCustId}"> <input type='text'
			name='MaxTenderRate' value="${MaxTenderRate}"> <input
			type='text' name='BorrowerDetails' value="${BorrowerDetails}">
		<input type='text' name='BorrowerCustId' value="${BorrowerCustId}">
		<input type='text' name='BorrowerAmt' value="${BorrowerAmt}">
		<input type='text' name='BorrowerRate' value="${BorrowerRate}">
		<input type='text' name='ProId' value="${ProId}"> <input
			type='text' name='IsFreeze' value="${IsFreeze}"> <input
			type='text' name='FreezeOrdId' value="${FreezeOrdId}">
		<%--         <input type='text' name='RetUrl'   value="${RetUrl}"> --%>
		<input type='text' name='BgRetUrl' value="${BgRetUrl}">
		<%--         <input type='text' name='MerPriv'   value="${MerPriv}"> --%>
		<input type='text' name='ChkValue' value="${ChkValue}"> <input
			type="submit" value="提交" />
	</form>
</body>
</html>
