<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发起开户请求</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
input {
	width: 800px;
	height: 20px;
}
</style>
</head>

<!--   <body onload="javascript:document.forms[0].submit()"> -->
<body>
	<form name="Loans"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method='post'>
		Version:<input type='text' name='Version' value="${Version}"><br>
		CmdId:<input type='text' name='CmdId' value="${CmdId}"><br>
		MerCustId: <input type='text' name='MerCustId' value="${MerCustId}"><br>
		OrdId:<input type='text' name='OrdId' value="${OrdId}"><br>
		OrdDate: <input type='text' name='OrdDate' value="${OrdDate}"><br>
		OutCustId:<input type='text' name='OutCustId' value="${OutCustId}"><br>
		TransAmt: <input type='text' name='TransAmt' value="${TransAmt}"><br>
		Fee: <input type='text' name='Fee' value="${Fee}"><br>
		SubOrdId: <input type='text' name='SubOrdId' value="${SubOrdId}"><br>
		SubOrdDate: <input type='text' name='SubOrdDate' value="${SubOrdDate}"><br>
		InCustId:<input type='text' name='InCustId' value="${InCustId}"><br>
		DivDetails: <input type='text' name='DivDetails' value="${DivDetails}"><br>
		DivCustId: <input type='text' name='DivCustId' value="${DivCustId}"><br>
		DivAcctId: <input type='text' name='DivAcctId' value="${DivAcctId}"><br>
		DivAmt: <input type='text' name='DivAmt' value="${DivAmt}"><br>
		FeeObjFlag: <input type='text' name='FeeObjFlag' value="${FeeObjFlag}"><br>
		IsDefault: <input type='text' name='IsDefault' value="${IsDefault}"><br>
		IsUnFreeze:<input type='text' name='IsUnFreeze' value="${IsUnFreeze}"><br>
		UnFreezeOrdId:<input type='text' name='UnFreezeOrdId'
			value="${UnFreezeOrdId}"><br> FreezeTrxId: <input
			type='text' name='FreezeTrxId' value="${FreezeTrxId}"><br>
		BgRetUrl: <input type='text' name='BgRetUrl' value="${BgRetUrl}"><br>
		ProId: <input type='text' name='ProId' value="${ProId}"><br>
		MerPriv: <input type='text' name='MerPriv' value="${MerPriv}"><br>
		ChkValue: <input type='text' name='ChkValue' value="${ChkValue}">
		<br> ReqExt： <input type='text' name='ReqExt' value="${ReqExt}">
		<br> LoansVocherAmt: <input type='text' name='LoansVocherAmt'
			value="${LoansVocherAmt}"> <br> <input type="submit"
			value="提交" style="width: 50px; height: 30px;" /><br>
	</form>
</body>
</html>
