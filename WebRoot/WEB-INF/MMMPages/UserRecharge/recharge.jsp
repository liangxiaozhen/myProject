<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发起请求</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body onload="javascript:document.forms[0].submit()">
	<form name="form" 
		action="http://test.moneymoremore.com:88/main/loan/toloanrecharge.action"
		method="post">
		<input type="hidden" name="RechargeMoneymoremore" value="${RechargeMoneymoremore}" /> 
		<input type="hidden" name="PlatformMoneymoremore" value="${PlatformMoneymoremore}" /> 
		<input type="hidden" name="OrderNo" value="${OrderNo}" /> 
		<input type="hidden" name="Amount" value="${Amount}" />  
		<input type="hidden" name="RechargeType" value="${RechargeType}" /> 
		<input type="hidden" name="FeeType" value="${FeeType}" /> 
		<input type="hidden" name="CardNo" value="${CardNo}" /> 
		<input type="hidden" name="RandomTimeStamp" value="${ RandomTimeStamp}" />
		<input type="hidden" name="Remark1" value="${Remark1}" /> 
	    <input type="hidden" name="Remark2" value="${Remark2}" /> 
		<input type="hidden" name="Remark3" value="${Remark3}" />
		<input type="hidden" name="ReturnURL" value="${ReturnURL}" /> 
		<input type="hidden" name="NotifyURL" value="${NotifyURL}" />
		<input type="hidden" name="SignInfo" value="${SignInfo}" /> 
		
	</form>
</body>
</html>
