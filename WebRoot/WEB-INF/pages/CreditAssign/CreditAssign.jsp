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
	margin-top: 2px;
}
</style>
</head>
<body>
	<form name="CreditAssign"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		Version:<input type="text" name="Version" value="${Version}" /><br />
		CmdId:<input type="text" name="CmdId" value="${CmdId}" /><br />
		MerCustId:<input type="text" name="MerCustId" value="${MerCustId}" /><br />
		SellCustId:<input type="text" name="SellCustId" value="${SellCustId}" /><br />
		CreditAmt:<input type="text" name="CreditAmt" value="${CreditAmt}" /><br />
		CreditDealAmt:<input type="text" name="CreditDealAmt"
			value="${CreditDealAmt}" /><br /> BidDetails:<input type="text"
			name="BidDetails" value="${BidDetails}" /><br /> BidOrdId:<input
			type="text" name="BidOrdId" value="${BidOrdId}" /><br />
		BidOrdDate:<input type="text" name="BidOrdDate" value="${BidOrdDate}" /><br />
		BidCreditAmt:<input type="text" name="BidCreditAmt"
			value="${BidCreditAmt}" /><br /> BorrowerCustId:<input type="text"
			name="BorrowerCustId" value="${BorrowerCustId}" /><br />
		BorrowerCreditAmt:<input type="text" name="BorrowerCreditAmt"
			value="${BorrowerCreditAmt}" /><br /> PrinAmt:<input type="text"
			name="PrinAmt" value="${PrinAmt}" /><br /> ProId:<input type="text"
			name="ProId" value="${ProId}" /><br /> Fee:<input type="text"
			name="Fee" value="${Fee}" /><br /> DivDetails:<input type="text"
			name="DivDetails" value="${DivDetails}" /><br /> DivAcctId:<input
			type="text" name="DivAcctId" value="${DivAcctId}" /><br /> DivAmt:<input
			type="text" name="DivAmt" value="${DivAmt}" /><br /> BuyCustId:<input
			type="text" name="BuyCustId" value="${BuyCustId}" /><br /> OrdId:<input
			type="text" name="OrdId" value="${OrdId}" /><br /> OrdDate:<input
			type="text" name="OrdDate" value="${OrdDate}" /><br /> RetUrl:<input
			type="text" name="RetUrl" value="${RetUrl}" /><br /> BgRetUrl:<input
			type="text" name="BgRetUrl" value="${BgRetUrl}" /><br /> LcId:<input
			type="text" name="LcId" value="${LcId}" /><br /> TotalLcAmt:<input
			type="text" name="TotalLcAmt" value="${TotalLcAmt}" /><br />
		MerPriv:<input type="text" name="MerPriv" value="${MerPriv}" /><br />
		ReqExt:<input type="text" name="ReqExt" value="${ReqExt}" /><br />
		PageType:<input type="text" name="PageType" value="${PageType}" /><br />
		ChkValue:<input type="text" name="ChkValue" value="${ChkValue}" /><br />
		CharSet:<input type="text" name="CharSet" value="${CharSet}" /><br />
		<input type="submit" value="提交" style="width: 50px; height: 30px;" /><br>
		<!--  		  onload="javascript:document.froms[0].submit()" -->
	</form>
</body>
</html>