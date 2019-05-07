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
		$("#update-amount-text").change(function() {
			$("#update-amount-lb").html("");
		});
		$("#update-expirytime-text").change(function() {
			$("#update-expirytime-lb").html("");
		});
		$("#update-isexpspecific-select").change(function() {
			$("#update-isexpspecific-lb").html("");
		});
		var status = "${userGradeExp.status}";
		$("input[name='status'][value=" + status + "]").attr("checked", true);
		var isExpSpecify = "${userGradeExp.isexpspecific}";
		$("#update-isexpspecific-select").val("${userGradeExp.expsnlid}");
		if (isExpSpecify == 2) {
			$("#update-isexpspecific-select").val("-1");
		}
	})
	function validateUpdate() {
		var amount = $("#update-amount-text").val();
		if (amount == "" || amount == null) {
			$("#update-amount-text").foucs();
			$("#update-amount-lb").html("*必须填写购买金额");
			return false;
		}
		var expirytime = $("#update-expirytime-text").val();
		if (expirytime == '' || expirytime == null) {
			$("#update-expirytime-text").focus();
			$("#update-expirytime-lb").html("*必须填写有效期");
			return false;
		}
		var isexpspecific = $("#update-isexpspecific-select").val();
		if (isexpspecific == "" || isexpspecific == null) {
			$("#update-isexpspecific-lb").html("*必须选择体验会员是否定向");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<input type="hidden" name="id" value="${userGradeExp.id }">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>体验会员等级设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				等级名称：</div>
			<div>
				<label>${userGradeExp.userGrade.ugradename}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				购买金额：</div>
			<div>
				<input type="text" placeholder="请输入购买金额"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="amount" id="update-amount-text"
					value="${userGradeExp.amount }">&nbsp;&nbsp;<span
					style="color: red;"><label id="update-amount-lb"></label></span>
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
					name="expirytime" id="update-expirytime-text"
					value="${userGradeExp.expirytime}">&nbsp;&nbsp; <span
					style="color: red;"><label id="update-expirytime-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				状 态 ：</div>
			<div>
				<input type="radio" name="status" value="1">启用
				&nbsp;&nbsp;&nbsp; <input type="radio" name="status" value="0">停用
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				体验会员定向：</div>
			<div>
				<select name="isexpspecific" id="update-isexpspecific-select">
					<option value="">---请选择---</option>
					<c:forEach items="${testSpecialNameList }" var="item">
						<option value="${item.id }">${item.businessName}</option>
					</c:forEach>
					<option value="-1">---不定向升级---</option>
				</select> &nbsp;&nbsp;<span style="color: red;"><label
					id="update-isexpspecific-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" onkeydown="LimitTextArea(this)"
					onkeyup="LimitTextArea(this)" class="form-control" name="remark"
					id="update-remark-text">${userGradeExp.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>