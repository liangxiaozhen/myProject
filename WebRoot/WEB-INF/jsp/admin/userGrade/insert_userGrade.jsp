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
		$("#insert-rankno-text").change(function() {
			$("#insert-rankno-lb").html("");
		});
		/*
		$("#insert-ugrade-text").change(function() {
			$("#insert-ugrade-lb").html("");
		});
		 */
		$("#insert-ugradename-text").change(function() {
			$("#insert-ugradename-lb").html("");
		});
		$("#insert-amount-text").change(function() {
			$("#insert-amount-lb").html("");
		});
		$("#insert-needpoints-text").change(function() {
			$("#insert-needpoints-lb").html("");
		});
		$("#insert-isspecify-select").change(function() {
			$("#insert-isspecify-lb").html("");
		});

		/*
		$("#insert-ugrade-text").blur(function() {
			checkUgrade();
		});*/
		$("#insert-ugradename-text").blur(function() {
			checkUgradeName();
		});
	})
	/*
	 *新增验证
	 */
	function validateInsert() {
		var rankNo = $("#insert-rankno-text").val();
		if (rankNo == "" || rankNo == null) {
			$("#insert-rankno-text").focus();
			$("#insert-rankno-lb").html("*必须填写排列序号");
			return false;
		}
		if (parseInt(rankNo) < 1) {
			$("#insert-rankno-text").focus();
			$("#insert-rankno-lb").html("*排列序号必须大于0");
			return false;
		}
		/*
		var ugrade = $("#insert-ugrade-text").val();
		if (ugrade == "" || ugrade == null) {
			$("#insert-ugrade-lb").html("*必须填写等级编号");
			return false;
		}
		if (parseInt(ugrade) < 1) {
			$("#insert-ugrade-lb").html("*等级序号必须大于1");
			return false;
		}
		checkUgrade();
		 */
		var ugradename = $("#insert-ugradename-text").val();
		if (ugradename == "" || ugradename == null) {
			$("#insert-ugradename-text").foucs();
			$("#insert-ugradename-lb").html("*必须填写等级名称");
			return false;
		}
		var isspecify = $("#insert-isspecify-select").val();
		if (isspecify == "" || isspecify == null) {
			$("#insert-isspecify-lb").html("*必须选择是否定向升级");
			return false;
		}
		var needPoints = $("#insert-needpoints-text").val();
		if (needPoints == "" || needPoints == null) {
			$("#insert-needpoints-text").focus();
			$("#insert-needpoints-lb").html("*必须填写购买积分");
			return false;
		}
		var amount = $("#insert-amount-text").val();
		if (amount == "" || amount == null) {
			$("#insert-amount-text").focus();
			$("#insert-amount-lb").html("*必须填写购买金额");
			return false;
		}
		return true;
	}
	/*
	 *验证等级序号

	function checkUgrade() {
		var action = "verify.action";
		var params = {
			"ugrade" : $("#insert-ugrade-text").val()
		};
		var callback = function(data) {
			if (data == "fail") {
				$("#insert-ugrade-lb").html("*等级编号已存在");
				$("#btn-insert").attr("disabled", true);
			}
			if (data == "success") {
				$("#insert-ugrade-lb").html("");
				$("#btn-insert").attr("disabled", false);
			}
		};
		$.post(action, params, callback, 'json');
	}
	 */
	/*
	 *验证等级名称
	 */
	function checkUgradeName() {
		var ugradeName = $("#insert-ugradename-text").val();
		if (ugradeName != null || ugradeName != '') {
			var action = "verifyUgrade.action";
			var params = {
				"ugradename" : ugradeName
			};
			var callback = function(data) {
				if (data == "fail") {
					$("#insert-ugradename-text").focus();
					$("#insert-ugradename-lb").html("*等级名称已存在");
					$("#btn-insert").attr("disabled", true);
				}
				if (data == "success") {
					$("#insert-ugradename-lb").html("");
					$("#btn-insert").attr("disabled", false);
				}
			};
			$.post(action, params, callback, 'json');
		}
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>会员等级设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				排列序号：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="text" placeholder="请输入排列序号"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="rankno" id="insert-rankno-text">&nbsp;&nbsp;<span
					style="color: red;"><label id="insert-rankno-lb"></label></span>
				<div class="col-md-offset-3" style="margin-top: 15px;">
					<span style="color: red;">*注：排列序号为数字，如 2,3,4,5</span>
				</div>
			</div>

		</div>
		<!-- 
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				等级编号：&nbsp;&nbsp;&nbsp; <input type="text" placeholder="请输入等级编号"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="ugrade" id="insert-ugrade-text">&nbsp;&nbsp;<span
					style="color: red;"><label id="insert-ugrade-lb"></label></span>
			</div>
		</div> -->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">等级名称：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="text" placeholder="请输入等级名称"
					style="width: 120px; text-align: center; line-height: 18px;"
					name="ugradename" id="insert-ugradename-text">&nbsp;&nbsp;<span
					style="color: red;"><label id="insert-ugradename-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">定向升级：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<select name="isspecify" id="insert-isspecify-select">
					<option value="">---请选择---</option>
					<c:forEach items="${normalSpecialNameList }" var="item">
						<option value="${item.id }">${item.businessName}</option>
					</c:forEach>
					<option value="-1">---不定向升级---</option>
				</select> <span style="color: red;"><label id="insert-isspecify-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">状   
				态 ：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="radio" name="status" value="1" checked="checked">
				启用 &nbsp;&nbsp;&nbsp; <input type="radio" name="status" value="0">
				停用 &nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">购买积分：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="text" placeholder="请输入购买积分"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="needpoints" id="insert-needpoints-text">&nbsp;&nbsp;<span
					style="color: red;"><label id="insert-needpoints-lb"></label></span>
			</div>
			<div class="col-md-offset-3" style="margin-top: 15px;">
				<span style="color: red;">*注：购买积分为0，表示不能用积分购买</span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">购买金额：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="text" placeholder="请输入购买金额"
					style="width: 120px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="needamount" id="insert-amount-text">&nbsp;&nbsp;<span
					style="color: red;"><label id="insert-amount-lb"></label></span><br>
			</div>
			<div class="col-md-offset-3" style="margin-top: 15px;">
				<span style="color: red;">*注：购买金额为0，表示不能用现金购买</span>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control" name="remark"
					id="insert-remark-text" onkeyup="LimitTextArea(this)"
					onkeydown="LimitTextArea(this)"></textarea>
			</div>
		</div>
	</form>
</body>
</html>