<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		$("#insert-ugrade-text").change(function() {
			$("#insert-ugrade-lb").html("");
		});
		$("#insert-amount-text").change(function() {
			$("#insert-amount-lb").html("");
		});
		$("#insert-expirytime-text").change(function() {
			$("#insert-expirytime-lb").html("");
		});
		$("#insert-isexpspecific-select").change(function() {
			$("#insert-isexpspecific-lb").html("");
		});
	})
	function validateInsert() {
		var ugrade = $("#insert-ugrade-text").val();
		if (ugrade == "" || ugrade == null) {
			$("#insert-ugrade-text").focus();
			$("#insert-ugrade-lb").html("*必须选择等级名称");
			return false;
		}
		if (parseInt(ugrade > 100)) {
			$("#insert-ugrade-text").focus();
			$("#insert-ugrade-lb").html("*等级序号不能超过100");
			return false;
		}
		var amount = $("#insert-amount-text").val();
		if (amount == "" || amount == null) {
			$("#insert-amount-text").focus();
			$("#insert-amount-lb").html("*必须填写购买金额");
			return false;
		}
		var expirytime = $("#insert-expirytime-text").val();
		if (expirytime == '' || expirytime == null) {
			$("#insert-expirytime-text").focus();
			$("#insert-expirytime-lb").html("*必须填写有效期");
			return false;
		}
		var isexpspecific = $("#insert-isexpspecific-select").val();
		if (isexpspecific == "" || isexpspecific == null) {
			$("#insert-isexpspecific-lb").html("*必须选择体验会员是否定向");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>体验会员等级设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				等级名称：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<select id="insert-ugrade-text" name="ugrade">
					<option value="">选择等级名称</option>
					<c:forEach items="${userGradeList }" var="item">
						<option value="${item.ugrade }">${item.ugradename }</option>
					</c:forEach>
				</select> <span style="color: red;"><label id="insert-ugrade-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				购买金额：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="text" placeholder="请输入购买金额"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="amount" id="insert-amount-text">&nbsp;&nbsp;<span
					style="color: red;"><label id="insert-amount-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				有 效 期 ：</div>
			<div>
				<input type="text" placeholder="请输入有效期(天)"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="expirytime" id="insert-expirytime-text">&nbsp;&nbsp;
				<span style="color: red;"><label id="insert-expirytime-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				状 态 ：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="radio" name="status" value="1" checked="checked">启用
				&nbsp;&nbsp;&nbsp; <input type="radio" name="status" value="0">停用
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				体验会员定向：</div>
			<div>
				<select name="isexpspecific" id="insert-isexpspecific-select">
					<option value="">---请选择---</option>
					<c:forEach items="${testSpecialNameList }" var="item">
						<option value="${item.id }">${item.businessName}</option>
					</c:forEach>
					<option value="-1">---不定向升级---</option>
				</select> &nbsp;&nbsp;<span style="color: red;"><label
					id="insert-isexpspecific-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" onkeydown="LimitTextArea(this)"
					onkeyup="LimitTextArea(this)" style="resize: none;"
					class="form-control" name="remark" id="insert-remark-text"></textarea>
			</div>
		</div>
	</form>
</body>
</html>