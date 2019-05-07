<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			订单编号：<font color="red"><label id="cancelCashNo">${uwc.cashno }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			银行名称：<font color="red"><label>${uwc.bankname }</label></font>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			开户名称：<font color="red"><label>${uwc.ubai.realname }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			银行卡号：<font color="red"><label>${uwc.cardno }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			用 户 名 ：<font color="red"><label>${uwc.ubai.loginname }</label></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			取现金额：<font color="red"><label>${df.format(uwc.amount) }</label></font>
		</div>
	</div>
</body>
</html>