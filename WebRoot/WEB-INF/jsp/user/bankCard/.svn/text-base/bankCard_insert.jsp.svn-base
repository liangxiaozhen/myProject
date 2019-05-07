<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bankcard/bindBankCard.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
	font-size: 15px
}

.reg-bank-box {
	padding: 14px;
}

.reg-bank-box .prompt-info {
	border: 1px dashed #e2e2e2;
	font-size: 14px;
	padding: 14px;
}

.lh22 {
	line-height: 22px;
}

.font-red {
	color: red !important;
}

.g6 {
	color: #666
}

.reg-bank-box .h36 {
	height: 14px;
}

.reg-bank-box .w2 {
	width: 112px;
}

.reg-input-group .text-input {
	border: 1px solid #e2e2e2;
	border-radius: 2px;
	font-size: 14px;
	line-height: 18px;
	padding: 10px 0;
}

.l {
	float: left;
}

.get_code {
	background: #fffaee none repeat scroll 0 0;
	border: 1px solid #ffecbe;
	border-radius: 2px;
	color: #ffbc1b;
	display: block;
	float: left;
	font-size: 12px;
	height: 30px;
	line-height: 34px;
	margin-left: 10px;
	text-align: center;
	width: 106px;
	line-height: 34px;
}

.get_code:hover {
	backgroud: #fff6e0 none repeat scroll 0 0;
	border: 1px solid #ffe19a
}

.reg-bank-box .validate {
	display: inline-block;
	float: left;
	font-size: 12px;
	line-height: 34px;
	text-decoration: underline;
}

.ml10 {
	margin-left: 10px;
}

.reg-message1 {
	clear: both;
	height: 30px;
	line-height: 30px;
	padding-left: 110px;
}

.reg-bank-box .reg-promt {
	font-size: 12px;
}

.bank-tips-icon {
	width: 18px;
	height: 18px;
	left: 65px;
	top: 2px;
	cursor: pointer;
	display: inline-block;
}

.reg-icons {
	background-color: transparent;
	background-image:
		url("http://www.tuandai.com/user/images/new_tuandai/register_icon.png?v=201601261656");
	background-repeat: no-repeat;
	background-attachment: scroll;
	background-position: 6% 64%;
	background-clip: border-box;
	background-origin: padding-box;
	background-size: auto auto;
}

.abs {
	position: absolute;
}

.ffb {
	color: #ffb80d;
}
</style>
<script type="text/javascript">
	$(function() {
		$("[data-toggle='tooltip']").tooltip({html : true,template : '<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner" style="background-color:white;border:1px solid #ffb80d"></div></div>'});
	});
</script>
</head>
<body>
	<div style="" id="reg-parent" class="reg-bank-box">
		<p class="prompt-info lh22">
			<b class="g6">温馨提示：</b> <span class="font-red">请绑定持卡人本人的银行卡(暂不支持信用卡)</span>，资金快捷充值、提现均使用同一张银行卡；绑定后如需更换，须联系客服进行审核，请慎重选择！<br>
			由于各大支付渠道暂停农行卡支付业务，为了保证您高效快捷的充值和提现体验，不建议绑定农行卡。

		</p>
	</div>
	<form method="post" id="userbankForm">
		<input type="hidden" name="baseid" value="${baseid}" />
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">姓名</label>
				<div class="col-sm-6">
					<input type="text" name="username" id="username"
						value="${username}" disabled="disabled" class="form-control" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">银行</label>
				<div class="col-sm-6">
					<input type="text" name="bankname" id="bankname"
						class="form-control" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">账户</label>
				<div class="col-sm-6">
					<input type="text" name="cardno" id="cardno" maxlength="19"
						onkeyup="this.value=this.value.replace(/\D/g,'')"
						onblur="userbank.checkCardNo()" class="form-control" />
				</div>
				<em id="number" style="color: red"></em>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">开户行省</label>
				<div class="col-sm-6">
					<select name="province" id="province" onchange="userbank.change()"
						class="form-control">
						<option value="">--请选择--</option>
						<c:forEach items="${provinces}" var="ptype">
							<c:choose>
								<c:when test="${select_province==ptype.id}">
									<option value="${ptype.name}" selected="selected">${ptype.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${ptype.name}">${ptype.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">开户行市</label>
				<div class="col-sm-6">
					<select name="city" id="city" class="form-control">
						<option value="">--请选择--</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<i class="abs reg-icons bank-tips-icon"></i> <label
					class="col-sm-2 text-right" data-toggle="tooltip"
					title="<b class='ffb'>请填写准确的开户行网点，开户行信息错误会导致提现失败。</b><b style='color:black'>如忘记开户行网点，您可以拨打银行卡上的客服电话咨询开户行信息，或登录网上银行进行查询。</b>">分支行</label>
				<div class="col-sm-6">
					<input type="text" name="subbranch" id="subbranch"
						class="form-control" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">卡类型</label>
				<div class="col-sm-6">
					<select name="cardtype" id="cardtype" class="form-control">
						<option value="">请选择卡类型</option>
						<c:forEach items="${cardtype}" var="ctype">
							<option value="${ctype.key}">${ctype.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px">
			<div class="col-md-8">
				<label class="col-sm-2 text-right">验证码</label>
				<div class="col-sm-6">
					<input class="w2 h36 text-input l clear-message"
						id="txt_smsCodebank" maxlength="6"
						onkeyup="this.value=this.value.replace(/\D/g,'')" type="text">
					<a href="javascript:;" class="get_code"
						style="vertical-align: 12px;" id="btnSendMsgBank"
						onclick="userbank.send_smscheck(0)">获取短信验证码</a>
				</div>
				<div class="reg-message mw1" id="dvtxt_allMsg"
					style='line-height: 16px; color: red'></div>
			</div>
		</div>
		<div class="reg-message1">
			<div class="reg-promt g6"></div>
		</div>
	</form>
</body>
</html>