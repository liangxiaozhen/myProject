<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		$("#ugradetype-select")
				.change(
						function() {
							var ugradeType = $(this).val();
							var ugradeSelect = document
									.getElementById("cash-ugrade-select");
							ugradeSelect.options.length = 0;
							ugradeSelect.options.add(new Option("--请选择--", ""));
							$("#needAmount").html("0  元");
							if (ugradeType == "1") {
								var action = "getNormalUgrade.action";
								var callback = function(data) {
									for (i = 0; i < data.length; i++) {
										ugradeSelect.options.add(new Option(
												data[i].ugradename + "------"
														+ data[i].needamount
														+ "元", data[i].rankno));
									}
								};
								$.post(action, null, callback, 'json');
							}
							if (ugradeType == "2") {
								var action = "getTasteUgrade.action";
								var callback = function(data) {
									for (i = 0; i < data.length; i++) {
										ugradeSelect.options.add(new Option(
												data[i].userGrade.ugradename
														+ "------"
														+ data[i].amount + "元",
												data[i].userGrade.rankno));
									}
								};
								$.post(action, null, callback, 'json');
							}
						});
		$("#cash-ugrade-select").change(function(data) {
			var ugradeType = $("#ugradetype-select").val();
			var rankNo = $(this).val();
			if (rankNo == "" || rankNo == null) {
				$("#needAmount").html("0  元");
			} else {
				var action = "getNeedAmount.action";
				var params = {
					"uGradeType" : ugradeType,
					"rankno" : rankNo
				};
				var callback = function(data) {
					if(parseFloat("${avlBalance}}")<parseFloat(data)){
						$("#btn-cash").attr("disabled",true);
					}else{
						$("#btn-cash").attr("disabled",false);
					}
					$("#needAmount").html(data+"  元");
				};
				$.post(action, params, callback, 'json');
			}
		});
	});
</script>
</head>
<body>
	<form>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>现金购买会员</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				当前余额：<label>${showAvlBalance}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				会员类型：<select id="ugradetype-select">
					<option value="">--请选择--</option>
					<option value="1">普通会员</option>
					<option value="2">体验会员</option>
				</select> <label id="ugradetype-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				会员等级：<select id="cash-ugrade-select">
					<option value="">--请选择--</option>
				</select> <label id="ugrade-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				所需金额：&nbsp;&nbsp;<label id="needAmount" style="color: red;">0
					元</label>
			</div>
		</div>
	</form>
</body>
</html>