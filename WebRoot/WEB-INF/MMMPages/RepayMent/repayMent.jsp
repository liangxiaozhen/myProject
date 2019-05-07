<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乾多多用户还款</title>
</head>
<body onload="javascript:document.forms[0].submit()">
 	<form action="${repayMentAction}" method="post">
		<input type="hidden" name="LoanJsonList" value="${LoanJsonList}" /> 
		<input type="hidden" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" /> 
		<input type="hidden" name="TransferAction" value="${TransferAction}" /> 
		<input type="hidden" name="Action" value="${Action}" /> 
		<input type="hidden" name="TransferType" value="${TransferType}" /> 
		<input type="hidden" name="ArrivalTime" value="${ArrivalTime}" /> 
		<input type="hidden" name="NeedAudit" value="${NeedAudit}" /> 
		<input type="hidden" name="DelayTransfer" value="${ DelayTransfer}" /> 
		<input type="hidden" name="RandomTimeStamp" value="${RandomTimeStamp}" /> 
		<input type="hidden" name="NotifyURL" value="${NotifyURL}" />
		<input type="hidden" name="ReturnURL" value="${ReturnURL}" />
		<input type="hidden" name="Remark1" value="${Remark1}" />
		<input type="hidden" name="Remark2" value="${Remark2}" />
		<input type="hidden" name="Remark3" value="${Remark3}" />
  		<input type="hidden" name="SignInfo" value="${SignInfo}" /> 
  	</form>
</body>
</html>