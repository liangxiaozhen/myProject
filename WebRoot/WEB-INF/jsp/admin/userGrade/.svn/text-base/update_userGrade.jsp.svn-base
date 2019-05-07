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
		$("#update-rankno-text").change(function() {
			$("#update-rankno-lb").html("");
		});
		$("#update-ugradename-text").change(function() {
			$("#update-ugradename-lb").html("");
		});
		$("#update-amount-text").change(function() {
			$("#update-amount-lb").html("");
		});
		$("#update-needpoints-text").change(function() {
			$("#update-needpoints-lb").html("");
		});
		$("#update-isspecify-select").change(function() {
			$("#update-isspecify-lb").html("");
		});
		$("#update-ugradename-text").blur(function() {
			checkUgradeName();
		});
		var isSpecify = "${userGrade.isspecify}";
		$("#update-isspecify-select").val("${userGrade.snlid}");
		if (isSpecify == 2) {
			$("#update-isspecify-select").val("-1");
		}
		var status = "${userGrade.status}";
		$("input[name='status'][value=" + status + "]").attr("checked", true);

	})
	/*
	 *编辑验证
	 */
	function validate() {
		var no = "${userGrade.rankno}";
		if (no != 0) {
			var rankNo = $("#update-rankno-text").val();
			if (rankNo == "" || rankNo == null) {
				$("#update-rankno-text").focus();
				$("#update-rankno-lb").html("*必须填写排列序号");
				return false;
			}
			if (parseInt(rankNo) < 1) {
				$("#update-rankno-text").focus();
				$("#update-rankno-lb").html("*排列序号必须大于0");
				return false;
			}
			if (validateUpdate()) {
				return true;
			}
		} else {
			var ugradename = $("#update-ugradename-text").val();
			if (ugradename == "" || ugradename == null) {
				$("#update-ugradename-text").focus();
				$("#update-ugradename-lb").html("*必须填写等级名称");
				return false;
			}
			return true;
		}
	}
	function validateUpdate() {
		var ugradename = $("#update-ugradename-text").val();
		if (ugradename == "" || ugradename == null) {
			$("#update-ugradename-text").focus();
			$("#update-ugradename-lb").html("*必须填写等级名称");
			return false;
		}
		var isspecify = $("#update-isspecify-select").val();
		if (isspecify == "" || isspecify == null) {
			$("#update-isspecify-lb").html("*必须选择是否定向升级");
			return false;
		}
		var needPoints = $("#update-needpoints-text").val();
		if (needPoints == "" || needPoints == null) {
			$("#update-needpoints-text").focus();
			$("#update-needpoints-lb").html("*必须填写购买积分");
			return false;
		}
		var amount = $("#update-amount-text").val();
		if (amount == "" || amount == null) {
			$("#update-amount-text").focus();
			$("#update-amount-lb").html("*必须填写购买金额");
			return false;
		}
		return true;
	}
	/*
	 *验证等级名称
	 */
	function checkUgradeName() {
		var ugradeName = $("#update-ugradename-text").val();
		if (ugradeName != null || ugradeName != '') {
			var action = "verifyUpdate.action";
			var params = {
				"id" : $("#hidden-id").val(),
				"ugradename" : ugradeName
			};
			var callback = function(data) {
				if (data == "fail") {
					$("#update-ugradename-text").focus();
					$("#update-ugradename-lb").html("*等级名称已存在");
					$("#btn-update").attr("disabled", true);
				}
				if (data == "success") {
					$("#update-ugradename-lb").html("");
					$("#btn-update").attr("disabled", false);
				}
			};
			$.post(action, params, callback, 'json');
		}
	}
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>会员等级编辑</b></font> <input type="hidden"
					id="hidden-id" name="id" value="${userGrade.id }">
			</div>
		</div>
		<c:if test="${userGrade.status != 2 }">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
					排列序号：&nbsp;&nbsp;&nbsp;</div>
				<div>
					<c:if test="${userGrade.rankno eq 0 }">
						<input type="hidden" name="rankno" value="0">
						<label>0</label>
					</c:if>
					<c:if test="${userGrade.rankno != 0 }">
						<input type="text" placeholder="请输入排列序号"
							style="width: 120px; text-align: center; line-height: 18px;"
							onblur="checkNum(this)" value="${userGrade.rankno }"
							onkeyup="clearNoNum(event,this)" name="rankno"
							id="update-rankno-text">&nbsp;&nbsp;<span
							style="color: red;"><label id="update-rankno-lb"></label></span>
					</c:if>
				</div>
			</div>
		</c:if>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				等级名称：&nbsp;&nbsp;&nbsp;</div>
			<div>
				<input type="text" placeholder="请输入等级名称"
					style="width: 120px; text-align: center; line-height: 18px;"
					name="ugradename" id="update-ugradename-text"
					value="${userGrade.ugradename }">&nbsp;&nbsp;<span
					style="color: red;"><label id="update-ugradename-lb"></label></span>
			</div>
		</div>
		<hr>
		<c:if test="${userGrade.rankno != 0 }">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
					定向升级：&nbsp;&nbsp;&nbsp;</div>
				<div>
					<select name="isspecify" id="update-isspecify-select">
						<option value="">---请选择---</option>
						<c:forEach items="${normalSpecialNameList }" var="item">
							<option value="${item.id }">${item.businessName}</option>
						</c:forEach>
						<option value="-1">---不定向升级---</option>
					</select> <span style="color: red;"><label id="update-isspecify-lb"></label></span>
				</div>
			</div>

			<hr>
			<c:if test="${userGrade.status != 2 }">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
						状 态 ：&nbsp;&nbsp;&nbsp;</div>
					<div>
						<input type="radio" name="status" value="1" checked="checked">启用
						&nbsp;&nbsp;&nbsp; <input type="radio" name="status" value="0">停用
						&nbsp;&nbsp;&nbsp;
						<c:if test="${userGrade.status !=1}">
							<input type="radio" name="status" value="2">废弃
						</c:if>
					</div>
				</div>
				<hr>
			</c:if>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">购买积分：&nbsp;&nbsp;&nbsp;
				</div>
				<div>
					<input type="text" placeholder="请输入购买积分"
						style="width: 120px; text-align: center; line-height: 18px;"
						onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
						name="needpoints" id="update-needpoints-text"
						value="${userGrade.needpoints }">&nbsp;&nbsp;<span
						style="color: red;"><label id="update-needpoints-lb"></label></span>
				</div>
				<div class="col-md-offset-3" style="margin-top: 15px;">
					<span style="color: red;">*注：购买积分为0，表示不能用积分购买</span>
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
						name="needamount" id="update-amount-text"
						value="${userGrade.needamount }">&nbsp;&nbsp;<span
						style="color: red;"><label id="update-amount-lb"></label></span>
				</div>
				<div>
					<div class="col-md-offset-3" style="margin-top: 15px;">
						<span style="color: red;">*注：购买金额为0，表示不能用现金购买</span>
					</div>
				</div>
			</div>
			<hr>
		</c:if>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control"
					onkeydown="LimitTextArea(this)" onkeyup="LimitTextArea(this)"
					name="remark" id="update-remark-text">${userGrade.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>