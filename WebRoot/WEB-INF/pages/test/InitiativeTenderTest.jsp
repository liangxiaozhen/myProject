<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投标测试</title>
</head>
<body onload="javascript:document.froms[0].submit()">
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
		FreezeOrdId:<input type="text" name="FreezeOrdId"
			value="${FreezeOrdId}" /><br /> IsFreeze:<input type="text"
			name="IsFreeze" value="${IsFreeze}" /><br /> RetUrl:<input
			type="text" name="RetUrl" value="${RetUrl}" /><br /> BgRetUrl:<input
			type="text" name="BgRetUrl" value="${BgRetUrl}" /><br /> MerPriv:<input
			type="text" name="MerPriv" value="${MerPriv}" /><br /> ReqExt:<input
			type="text" name="ReqExt" value="${ReqExt}" /><br /> AcctId:<input
			type="text" name="AcctId" value="${AcctId}" /><br /> VocherAmt:<input
			type="text" name="VocherAmt" value="${VocherAmt}" /><br />
		<br /> CharSet:<input type="text" name="CharSet" value="${CharSet}" /><br />
		ChkValue:<input type="text" name="ChkValue" value="${ChkValue}" /><br />
		<input value="提交" type="submit">
	</form>
</body>
</html>