<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乾多多投标审核页面</title>
</head>
<body onload="javascript:document.forms[0].submit()">
	<form action="http://test.moneymoremore.com:88/main/loan/toloantransferaudit.action" method="post">
		<input type="hidden" name="LoanNoList" value="${LoanNoList}" /> 
		<input type="hidden" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" /> 
		<input type="hidden" name="AuditType" value="${AuditType}" /> 
		<input type="hidden" name="Remark1" value="${Remark1}" /> 
		<input type="hidden" name="Remark2" value="${Remark2}" /> 
		<input type="hidden" name="Remark3" value="${Remark3}" /> 
		<input type="hidden" name="ReturnURL" value="${ReturnURL}" />
		<input type="hidden" name="NotifyURL" value="${NotifyURL}" />
		<input type="hidden" name="SignInfo" value="${SignInfo}" /> 
 	</form>
</body>
</html>