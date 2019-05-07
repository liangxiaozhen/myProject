<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的信息录入</title>
<!-- onload="javascript:document.forms[0].submit()" -->
</head>
<body>
	<form name="AddBidInfo"
		action="http://mertest.chinapnr.com/muser/publicRequests"
		method="post">
		<input type="submit" value="提交" /><br /> 版本号：${Version}<br />
		消息类型：${CmdId}<br /> 商户客户号：${MerCustId}<br /> 项目 ID：${ProId}<br />
		标的名称：${BidName}<br /> 标的类型：${BidType}<br /> 发标金额：${BorrTotAmt}<br />
		发标年化利率：${YearRate}<br /> 应还款总利息：${RetInterest}<br />
		最后还款日期：${LastRetDate}<br /> 计划投标开始日期：${BidStartDate}<br />
		计划投标截止日期：${BidEndDate}<br /> 借款期限：${LoanPeriod}<br /> 还款方式：${RetType}<br />
		应还款日期：${RetDate}<br /> 标的产品类型：${BidProdType}<br /> 借款人类型：${BorrType}<br />
		借款人 ID：${BorrCustId}<br /> 借款人名称：${BorrName}<br />
		借款人手机号码：${BorrMobiPhone}<br /> 借款用途：${BorrPurpose}<br />
		编码集：${CharSet}<br /> 商户后台应答地址：${BgRetUrl}<br /> 签名：${ChkValue}<br /> <br />
		<br /> 本息保障(可选)：${GuarantType}<br /> 风险控制方式(可选)：${RiskCtlType}<br />
		限定最低投标份数(可选)：${LimitMinBidAmt}<br /> 限定每份投标金额(可选)：${LimitBidSum}<br />
		限定最多投标金额(可选)：${LimitMaxBidSum}<br /> 限定最少投标金额(可选)：${LimitMinBidSum}<br />
		逾期是否垫资(可选)：${BidPayforState}<br /> 借款企业营业执照编号(可选)：${BorrBusiCode}<br />
		借款人证件类型：${BorrCertType}<br /> 借款人证件号码：${BorrCertId}<br />
		借款人固定电话：${BorrPhone}<br /> 借款人工作年限(可选)：${BorrWorkYear}<br />
		借款人税后月收入(可选)：${BorrIncome}<br /> 借款人婚姻状况(可选)：${BorrMarriage}<br />
		借款人电子邮箱(可选)：${BorrEmail}<br /> 商户私有域(可选)：${MerPriv}<br />
		入参扩展域(可选)：${ReqExt}<br /> <input type="hidden" name="Version"
			value="${Version}" /> <input type="hidden" name="CmdId"
			value="${CmdId}" /> <input type="hidden" name="MerCustId"
			value="${MerCustId}" /> <input type="hidden" name="ProId"
			value="${ProId}" /> <input type="hidden" name="BidName"
			value="${BidName}" /> <input type="hidden" name="BidType"
			value="${BidType}" /> <input type="hidden" name="BorrTotAmt"
			value="${BorrTotAmt}" /> <input type="hidden" name="YearRate"
			value="${YearRate}" /> <input type="hidden" name="RetInterest"
			value="${RetInterest}" /> <input type="hidden" name="LastRetDate"
			value="${LastRetDate}" /> <input type="hidden" name="BidStartDate"
			value="${BidStartDate}" /> <input type="hidden" name="BidEndDate"
			value="${BidEndDate}" /> <input type="hidden" name="LoanPeriod"
			value="${LoanPeriod}" /> <input type="hidden" name="RetType"
			value="${RetType}" /> <input type="hidden" name="RetDate"
			value="${RetDate}" /> <input type="hidden" name="BidProdType"
			value="${BidProdType}" /> <input type="hidden" name="BorrType"
			value="${BorrType}" /> <input type="hidden" name="BorrCustId"
			value="${BorrCustId}" /> <input type="hidden" name="BorrName"
			value="${BorrName}" /> <input type="hidden" name="BorrMobiPhone"
			value="${BorrMobiPhone}" /> <input type="hidden" name="BorrPurpose"
			value="${BorrPurpose}" /> <input type="hidden" name="CharSet"
			value="${CharSet}" /> <input type="hidden" name="BgRetUrl"
			value="${BgRetUrl}" /> <input type="hidden" name="ChkValue"
			value="${ChkValue}" /> <input type="hidden" name="BorrCertType"
			value="${BorrCertType}" /> <input type="hidden" name="BorrCertId"
			value="${BorrCertId}" />


		<%-- <input type="hidden" name="GuarantType" value="${GuarantType}"/>
		<input type="hidden" name="RiskCtlType" value="${RiskCtlType}"/>
		<input type="hidden" name="LimitMinBidAmt" value="${LimitMinBidAmt}"/>
		<input type="hidden" name="LimitBidSum" value="${LimitBidSum}"/>
		<input type="hidden" name="LimitMaxBidSum" value="${LimitMaxBidSum}"/>
		<input type="hidden" name="LimitMinBidSum" value="${LimitMinBidSum}"/>
		<input type="hidden" name="BidPayforState" value="${BidPayforState}"/>
		<input type="hidden" name="BorrBusiCode" value="${BorrBusiCode}"/>
		<input type="hidden" name="BorrCertType" value="${BorrCertType}"/>
		<input type="hidden" name="BorrCertId" value="${BorrCertId}"/>
		<input type="hidden" name="BorrPhone" value="${BorrPhone}"/>
		<input type="hidden" name="BorrWorkYear" value="${BorrWorkYear}"/>
		<input type="hidden" name="BorrIncome" value="${BorrIncome}"/>
		<input type="hidden" name="BorrMarriage" value="${BorrMarriage}"/>
		<input type="hidden" name="BorrEmail" value="${BorrEmail}"/>
		<input type="hidden" name="MerPriv" value="${MerPriv}"/>
		<input type="hidden" name="ReqExt" value="${ReqExt}"/> --%>




	</form>
</body>
</html>