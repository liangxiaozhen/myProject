<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/userRecharge/remitrecharge.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<title>莫邪科技</title>
<style>
#all {
	width: 70%;
	height: auto;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<div class="container" id="all">
		<div style="margin-left: 45%">
			<h2>提交汇款充值信息</h2>
		</div>
		<div class="col-md-12 column">
			<form
				action="${pageContext.request.contextPath}/user/userRecharge/saveremitRecharge.action"
				method="post" class="form-horizontal"
				onsubmit="return validataFrom()">
				<input type="hidden" value="${user.id}" name="baseid" />
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2">充值订单号：</label>
					<div class="col-sm-9">
						<input type="text" name="rechargeno" class="form-control"
							id="rechargenolb" onblur="rechargenoBlur()" /> <span
							id="rechargeno_lb"></span>
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2">充值银行：</label>
					<div class="col-sm-9">
						<select name="bankname" id="bankname_select"
							onblur="banknameBlur()">
							<option value="">请选择--</option>
							<option value="ICBC">中国工商银行</option>
							<option value="ABC">中国农业银行</option>
							<option value="CMB">招商银行</option>
							<option value="CCB">中国建设银行</option>
							<option value="BCCB">北京银行</option>
							<option value="BJRCB">北京农村商业银行</option>
							<option value="BOC">中国银行</option>
							<option value="BOCOM">交通银行</option>
							<option value="CMBC">中国民生银行</option>
							<option value="BOS">上海银行</option>
							<option value="CBHB">渤海银行</option>
							<option value="CEB">中国光大银行</option>
							<option value="CIB">兴业银行</option>
							<option value="CITIC">中信银行</option>
							<option value="CZB">浙商银行</option>
							<option value="GDB">广发银行</option>
							<option value="HKBEA">东亚银行</option>
							<option value="HXB">华夏银行</option>
							<option value="HZCB">杭州银行</option>
							<option value="NJCB">南京银行</option>
							<option value="PINGAN">平安银行</option>
							<option value="PSBC">中国邮政储蓄银行</option>
							<option value="SDB">深圳发展银行</option>
							<option value="SPDB">浦东发展银行</option>
							<option value="SRCB">上海农村商业银行</option>
						</select> <span id="bankname_lb"></span>
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2">银行卡号：</label>
					<div class="col-sm-9">
						<input type="text" name="cardno" class="form-control"
							id="cardnolb" onblur="cardnoBlur()" /> <span id="cardno_lb"></span>
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2">汇款金额：</label>
					<div class="col-sm-9">
						<input type="text" name="amount" id="amountlb"
							class="form-control" onblur="checkBlur(this)"
							onkeyup="checkUp(event,this)" maxlength="9" /> <span
							id="amount_lb"></span>
					</div>
				</div>

				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2"></label>
					<div class="col-sm-4">
						<button type="submit" class="btn btn-warning">
							<span class="glyphicon glyphicon-pencil"></span>提交
						</button>
					</div>
					<div class="col-sm-4">
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-refresh"></span>重置
						</button>
					</div>
				</div>
			</form>
		</div>
		<p>${message}</p>
	</div>
</body>
</html>