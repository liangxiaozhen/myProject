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
	<form name="InitiativeTender"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		Version:<input type="text" name="Version" value="${Version}" /><br />
		CmdId:<input type="text" name="CmdId" value="${CmdId}" /><br />
		MerCustId:<input type="text" name="MerCustId" value="${MerCustId}" /><br />
		OrdId:<input type="text" name="OrdId" value="${OrdId}" /><br />
		OrdDate:<input type="text" name="OrdDate" value="${OrdDate}" /><br />
		TransAmt:<input type="text" name="TransAmt" value="${TransAmt}" /><br />
		UsrCustId:<input type="text" name="UsrCustId" value="${UsrCustId}" /><br />
		MaxTenderRate:<input type="text" name="MaxTenderRate"
			value="${MaxTenderRate}" /><br /> BorrowerDetails:<input
			type="text" name="BorrowerDetails" value="${BorrowerDetails}" /><br />
		BorrowerCustId:<input type="text" name="BorrowerCustId"
			value="${BorrowerCustId}" /><br /> BorrowerAmt:<input type="text"
			name="BorrowerAmt" value="${BorrowerAmt}" /><br /> BorrowerRate:<input
			type="text" name="BorrowerRate" value="${BorrowerRate}" /><br />
		ProId:<input type="text" name="ProId" value="${ProId}" /><br />
		IsFreeze:<input type="text" name="IsFreeze" value="${IsFreeze}" /><br />
		FreezeOrdId:<input type="text" name="FreezeOrdId"
			value="${FreezeOrdId}" /><br /> BgRetUrl:<input type="text"
			name="BgRetUrl" value="${BgRetUrl}" /><br /> ReqExt:<input
			type="text" name="ReqExt" value="${ReqExt}" /><br /> ChkValue:<input
			type="text" name="ChkValue" value="${ChkValue}" /><br /> AcctId:<input
			type="text" name="AcctId" value="${AcctId}" /><br /> VocherAmt:<input
			type="text" name="VocherAmt" value="${VocherAmt}" /><br /> <input
			type="submit" value="提交" style="width: 50px; height: 30px;" /><br>

	</form>
</body>
</html>