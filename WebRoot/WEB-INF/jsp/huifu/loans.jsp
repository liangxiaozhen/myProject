<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
input {
	width: 1200px;
}
</style>
</head>
<body>
	<form name="Loans"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		Version:<input type="text" name="Version" value="${Version}" /><br />
		CmdId:<input type="text" name="CmdId" value="${CmdId}" /><br />
		MerCustId:<input type="text" name="MerCustId" value="${MerCustId}" /><br />
		OrdId:<input type="text" name="OrdId" value="${OrdId}" /><br />
		OrdDate:<input type="text" name="OrdDate" value="${OrdDate}" /><br />
		OutCustId:<input type="text" name="OutCustId" value="${OutCustId}" /><br />
		TransAmt:<input type="text" name="TransAmt" value="${TransAmt}" /><br />
		Fee:<input type="text" name="Fee" value="${Fee}" /><br /> SubOrdId:<input
			type="text" name="SubOrdId" value="${SubOrdId}" /><br />
		SubOrdDate:<input type="text" name="SubOrdDate" value="${SubOrdDate}" /><br />
		InCustId:<input type="text" name="InCustId" value="${InCustId}" /><br />
		DivDetails:<input type="text" name="DivDetails" value="${DivDetails}" /><br />
		DivCustId:<input type="text" name="DivCustId" value="${DivCustId}" /><br />
		DivAcctId:<input type="text" name="DivAcctId" value="${DivAcctId}" /><br />
		DivAmt:<input type="text" name="DivAmt" value="${DivAmt}" /><br />
		FeeObjFlag:<input type="text" name="FeeObjFlag" value="${FeeObjFlag}" /><br />

		IsDefault:<input type="text" name="IsDefault" value="${IsDefault}" /><br />
		IsUnFreeze:<input type="text" name="IsUnFreeze" value="${IsUnFreeze}" /><br />
		UnFreezeOrdId:<input type="text" name="UnFreezeOrdId"
			value="${UnFreezeOrdId}" /><br /> FreezeTrxId:<input type="text"
			name="FreezeTrxId" value="${FreezeTrxId}" /><br /> BgRetUrl:<input
			type="text" name="BgRetUrl" value="${BgRetUrl}" /><br /> ReqExt:<input
			type="text" name="ReqExt" value="${ReqExt}" /><br /> ChkValue:<input
			type="text" name="ChkValue" value="${ChkValue}" /><br />
		loansVocherAmt:<input type="text" name="loansVocherAmt"
			value="${loansVocherAmt}" /><br /> ProId:<input type="text"
			name="ProId" value="${ProId}" /><br /> <input type="submit"
			value="提交" style="width: 50px; height: 30px;" /><br>

	</form>
</body>
</html>