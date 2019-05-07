<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style type="text/css">
#numAll {
	width: 60%;
	height: 700px;
	margin-left: auto;
	margin-right: auto;
	background: #F6F7F8;
}

#numOne {
	width: 100%;
	height: 50px;
	border-bottom: 1px #e3e3e3 solid;
}

#numOne_01 {
	width: 40%;
	height: 40px;
	margin-left: 30px;
	margin-top: 10px;
}

#numOne_01 div {
	float: left;
	margin: 5px;
}

#numOne_01 div img {
	width: 33px;
	height: 23px;
}

#numOne_01 div font {
	width: 100px;
	height: 40px;
	font-size: 25px;
	font-weight: bold;
}

#numTwo {
	width: 100%;
	height: auto;
	background: #FFFFFF;
}

#numTwo_01 {
	width: 370px;
	height: 200px;
	border: 1px #CCB38C solid;
	border-radius: 10px 10px 10px 10px;
	margin-left: 85px;
	margin-top: 38px;
	float: left;
}

#numTwo_01_01 {
	width: 370px;
	height: 50px;
	border-radius: 10px 10px 0px 0px;
	float: left;
}

#numTwo_01_01 button {
	font-size: 17px;
	float: right;
	margin-right: 30px;
	margin-top: 20px;
	color: blue;
	cursor: pointer;
	border: none;
	background: none;
}

#numTwo_01_01 label {
	font-size: 17px;
	margin-right: 30px;
	margin-top: 20px;
}

#numTwo_01_01 a {
	font-size: 17px;
	margin-right: 30px;
	margin-top: 20px;
	float: right;
	cursor: pointer;
}

#numTwo_01_02 {
	width: 370px;
	height: 110px;
	background: #EFEFEF;
	float: left;
	font-size: 30px;
	text-align: center;
	vertical-align: middle;
}

#numTwo_01_02 p {
	font-size: 30px;
	text-align: center;
	line-height: 100px;
}

#numTwo_01_03 {
	width: 370px;
	height: 40px;
	border-radius: 0px 0px 10px 10px;
	font-size: 15px;
	text-align: center;
	padding-top: 10px;
	float: left;
}

#numTwo_02 {
	width: 370px;
	height: 200px;
	border: 1px #CCB38C solid;
	border-radius: 10px 10px 10px 10px;
	margin-left: 85px;
	margin-top: 38px;
	float: left;
	cursor: pointer;
	background: #EFEFEF;
}

#numTwo_02_01 {
	width: 370px;
	height: 50px;
	border-radius: 10px 10px 0px 0px;
	float: left;
}

#numTwo_02_02 {
	width: 370px;
	height: 110px;
	float: left;
	text-align: center;
}

#numTwo_02_02 a {
	font-size: 30px;
	line-height: 100px;
}

#numTwo_02_03 {
	width: 370px;
	height: 40px;
	border-radius: 0px 0px 10px 10px;
	font-size: 15px;
	text-align: center;
	padding-top: 10px;
	float: left;
}

#numTwo_03 {
	width: 90%;
	height: 200px;
	border: 1px #CCB38C solid;
	border-radius: 10px 10px 10px 10px;
	margin-left: 50px;
	margin-top: 38px;
	float: left;
	cursor: pointer;
	background: #EFEFEF;
}

#numTwo_03_01 {
	width: 100%;
	height: 50px;
	border-radius: 10px 10px 0px 0px;
	float: left;
}

#numTwo_03_02 {
	font-size: 20px;
	width: 100%;
	height: 110px;
	float: left;
	text-align: center;
}

#numTwo_03_02 a {
	font-size: 20px;
	line-height: 100px;
}

#numTwo_03_03 {
	width: 100%;
	height: 40px;
	border-radius: 0px 0px 10px 10px;
	font-size: 15px;
	text-align: center;
	padding-top: 10px;
	float: left;
}

#numThree {
	width: 100%;
	height: 240px;
	background: #FFF0DA;
	border: 1xp #FFD18D solid;
	margin-top: 20px;
	float: left;
}

#numThree p {
	font-size: 18px;
	margin-left: 35px;
	margin-top: 10px;
	padding-right: 20px;
}
</style>
<script type="text/javascript">
	var bindcard = {
		/* 添加银行卡 */
		addBankCard : function() {
			window.open("${pageContext.request.contextPath}/moneymoremore/UserBindCard/bindCard.action");
		}
	}
</script>
</head>
<body style="background: #F6F7F8">
	<div id="numAll">
		<div id="numOne">
			<div id="numOne_01">
				<div>
					<img alt="" src="">
				</div>
				<div>
					<font>我的银行卡</font>
				</div>
			</div>
		</div>
		<div id="numTwo">
			<c:if test="${!empty userfsaccountinfo}">
				<c:if test="${!empty cardList}">
					<c:forEach items="${cardList}" var="card">
						<div id="numTwo_01">
							<div id="numTwo_01_01">
							</div>
							<div id="numTwo_01_02">
								<p>${card.bankname}</p>
							</div>
							<div id="numTwo_01_03">${card.cardno}</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${isfastbindcard == null}">
					<div id="numTwo_02" onclick="bindcard.addBankCard()">
						<div id="numTwo_02_01"></div>
						<div id="numTwo_02_02">
							<a>点击添加银行卡</a>
						</div>
						<div id="numTwo_02_03"></div>
					</div>
				</c:if>
			</c:if>
			<c:if test="${empty userfsaccountinfo}">
				<div id="numTwo_03" onclick="bindcard.openAccount()">
					<div id="numTwo_03_01"></div>
					<div id="numTwo_03_02">
						您需要在第三方托管平台上进行注册，才可申请充值提现！请<a
							href="${pageContext.request.contextPath}/huifu/UserRegister.action"
							target="_blank">立即注册！ </a>
					</div>
					<div id="numTwo_03_03"></div>
				</div>
			</c:if>
		</div>
		<div id="numThree">
			<p style="color: red">温馨提示:</p>
			<p>1.提现银行卡仅限借记卡，暂不支持信用卡；</p>
			<p>2.提现银行卡开户名需与您在莫邪理财平台实名认证一致；</p>
			<p>3.如果您通过莫邪理财app，使用快捷支付绑定银行卡，为了您的账户安全，那么提现只能使用您的快捷卡，其他提现银行卡将自动停止使用。使用快捷卡，审核时间更快，操作更安全。</p>
			<p>4.如遇特殊情况需要解除或更换快捷卡，
				为了切实保障您的资金安全。您需要登录汇付天下按照要求提供相关资料：上传个人与银行卡资料等，详情请致电客服电话400-888-6268。</p>
		</div>
	</div>
</body>
</html>
