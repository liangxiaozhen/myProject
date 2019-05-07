<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标测试</title>
</head>
<body onload="javascript:document.froms[0].submit()">
	<form name="AddBidInfo"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		Version:<input type="text" name="Version" value="${Version}" /><br />
		CmdId:<input type="text" name="CmdId" value="${CmdId}" /><br />
		MerCustId:<input type="text" name="MerCustId" value="${MerCustId}" /><br />
		ProId:<input type="text" name="ProId" value="${ProId}" /><br />
		BidName:<input type="text" name="BidName" value="${BidName}" /><br />
		BidType:<input type="text" name="BidType" value="${BidType}" /><br />
		BorrTotAmt:<input type="text" name="BorrTotAmt" value="${BorrTotAmt}" /><br />
		YearRate:<input type="text" name="YearRate" value="${YearRate}" /><br />
		RetInterest:<input type="text" name="RetInterest"
			value="${RetInterest}" /><br /> LastRetDate:<input type="text"
			name="LastRetDate" value="${LastRetDate}" /><br /> BidStartDate:<input
			type="text" name="BidStartDate" value="${BidStartDate}" /><br />
		BidEndDate:<input type="text" name="BidEndDate" value="${BidEndDate}" /><br />
		LoanPeriod:<input type="text" name="LoanPeriod" value="${LoanPeriod}" /><br />
		RetType:<input type="text" name="RetType" value="${RetType}" /><br />
		RetDate:<input type="text" name="RetDate" value="${RetDate}" /><br />
		GuarantType:<input type="text" name="GuarantType"
			value="${GuarantType}" /><br /> BidProdType:<input type="text"
			name="BidProdType" value="${BidProdType}" /><br /> RiskCtlType:<input
			type="text" name="RiskCtlType" value="${RiskCtlType}" /><br />
		Recommers:<input type="text" name="Recommer" value="${Recommer}" /><br />
		LimitMinBidAmt:<input type="text" name="LimitMinBidAmt"
			value="${LimitMinBidAmt}" /><br /> LimitBidSum:<input type="text"
			name="LimitBidSum" value="${LimitBidSum}" /><br />
		<br /> LimitMaxBidSum:<input type="text" name="LimitMaxBidSum"
			value="${LimitMaxBidSum}" /><br /> LimitMinBidSum:<input
			type="text" name="LimitMinBidSum" value="${LimitMinBidSum}" /><br />
		OverdueProba:<input type="text" name="OverdueProba"
			value="${OverdueProba}" /><br /> BidPayforState:<input type="text"
			name="BidPayforState" value="${BidPayforState}" /><br /> BorrType:<input
			type="text" name="BorrType" value="${BorrType}" /><br />
		BorrCustId:<input type="text" name="BorrCustId" value="${BorrCustId}" /><br />
		BorrName:<input type="text" name="BorrName" value="${BorrName}" /><br />
		BorrBusiCode:<input type="text" name="BorrBusiCode"
			value="${BorrBusiCode}" /><br /> BorrCertType:<input type="text"
			name="BorrCertType" value="${BorrCertType}" /><br /> BorrCertId:<input
			type="text" name="BorrCertId" value="${BorrCertId}" /><br />
		BorrMobiPhone:<input type="text" name="BorrMobiPhone"
			value="${BorrMobiPhone}" /><br /> BorrPhone:<input type="text"
			name="BorrPhone" value="${BorrPhone}" /><br /> BorrWork:<input
			type="text" name="BorrWork" value="${BorrWork}" /><br />
		BorrWorkYear:<input type="text" name="BorrWorkYear"
			value="${BorrWorkYear}" /><br /> BorrIncome:<input type="text"
			name="BorrIncome" value="${BorrIncome}" /><br /> BorrEdu:<input
			type="text" name="BorrEdu" value="${BorrEdu}" /><br />
		BorrMarriage:<input type="text" name="BorrMarriage"
			value="${BorrMarriage}" /><br /> BorrAddr:<input type="text"
			name="BorrAddr" value="${BorrAddr}" /><br /> BorrEmail:<input
			type="text" name="BorrEmail" value="${BorrEmail}" /><br />
		BorrPurpose:<input type="text" name="BorrPurpose"
			value="${BorrPurpose}" /><br /> CharSet:<input type="text"
			name="CharSet" value="${CharSet}" /><br /> BgRetUrl:<input
			type="text" name="BgRetUrl" value="${BgRetUrl}" /><br /> MerPriv:<input
			type="text" name="MerPriv" value="${MerPriv}" /><br /> ReqExt:<input
			type="text" name="ReqExt" value="${ReqExt}" /><br /> ChkValue:<input
			type="text" name="ChkValue" value="${ChkValue}" /><br /> <input
			value="提交" type="submit">
	</form>
</body>
</html>