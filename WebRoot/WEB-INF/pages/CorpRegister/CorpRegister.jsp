<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汇付天下 企业开户</title>
</head>
<body onload="javascript:document.forms[0].submit()">
	<form name="CorpRegister"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		<input type="hidden" name="Version" value="${Version}" /> <input
			type="hidden" name="CmdId" value="${CmdId}" /> <input type="hidden"
			name="MerCustId" value="${MerCustId}" /> <input type="hidden"
			name="UsrId" value="${UsrId}" /> <input type="hidden" name="UsrName"
			value="${UsrName}" /> <input type="hidden" name="InstuCode"
			value="${InstuCode}" /> <input type="hidden" name="BusiCode"
			value="${ BusiCode}" /> <input type="hidden" name="TaxCode"
			value="${TaxCode}" /> <input type="hidden" name="MerPriv"
			value="${MerPriv}" /> <input type="hidden" name="GuarType"
			value="${GuarType}" /> <input type="hidden" name="BgRetUrl"
			value="${BgRetUrl}" /> <input type="hidden" name="ReqExt"
			value="${ReqExt}" /> <input type="hidden" name="CharSet"
			value="${CharSet}" /> <input type="hidden" name="ChkValue"
			value="${ChkValue}" />
	</form>
</body>
</html>