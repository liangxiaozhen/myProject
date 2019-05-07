<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body onload="javascript:document.forms[0].submit()">
	<form name="TenderCancle"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method='post'>
		<input type='hidden' name='Version' value="${Version}"> <input
			type='hidden' name='CmdId' value="${CmdId}"> <input
			type='hidden' name='MerCustId' value="${MerCustId}"> <input
			type='hidden' name='OrdId' value="${OrdId}"> <input
			type='hidden' name='OrdDate' value="${OrdDate}"> <input
			type='hidden' name='TransAmt' value="${TransAmt}"> <input
			type='hidden' name='UsrCustId' value="${UsrCustId}"> <input
			type='hidden' name='IsUnFreeze' value="${IsUnFreeze}"> <input
			type='hidden' name='UnFreezeOrdId' value="${UnFreezeOrdId}">
		<input type='hidden' name='FreezeTrxId' value="${FreezeTrxId}">
		<input type='hidden' name='RetUrl' value="${RetUrl}"> <input
			type='hidden' name='BgRetUrl' value="${BgRetUrl}"> <input
			type='hidden' name='MerPriv' value="${MerPriv}"> <input
			type='hidden' name='ReqExt' value="${ReqExt}"> <input
			type='hidden' name='ChkValue' value="${ChkValue}"> <input
			type='hidden' name='CharSet' value="${CharSet}">
	</form>
</body>
</html>
