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
<style type="text/css">
</style>
<script type="text/javascript">
	$(function() {
		//选择会员等级div隐藏
		$("#insert-ugrade-checkbox-div").hide();
		//比例 div 隐藏
		$("#insert-rate-div").hide();
		//会员等级select选择
		$("#insert-ugrade-select").change(function() {
			$("#insert-ugrade-lb").html("");
		});
		//等级 radio change监听事件
		//$(".insert-ugrade-radio").change(function() {
		//	var $radioVal = $("input[name='ugrade']:checked").val();
		//当选中全部等级时 选择等级div隐藏，反之显示
		//	if ($radioVal == 1) {
		//		$("#insert-ugrade-checkbox-div").hide();
		//取消提示语
		//		$("#insert-ugrade-lb").html("");
		//	} else {
		//		$("#insert-ugrade-checkbox-div").show();
		//	}
		//})
		//收费类型 select change监听事件
		$("#insert-wdcmode-select").change(function() {
			if ($(this).val() == 1) {
				//隐藏比例div 并清空文本值
				$("#insert-rate-div").hide();
				$("#insert-quota-div").show();
				$("#insert-rate-div input").val("");
			} else {
				//隐藏定额div 并清空文本值
				$("#insert-quota-div").hide();
				$("#insert-rate-div").show();
				$("#insert-quota-div input").val("");
			}
		});
		//单击 选择等级checkbox 取消提示语
		$(".checkbox-inline").click(function() {
			$("#insert-ugrade-lb").html("");
		});
		//最小金额text输入 取消提示语
		$("#insert-minmoney-text").change(function() {
			$("#insert-money-lb").html("");
		});
		//最大金额text输入 取消提示语
		$("#insert-maxmoney-text").change(function() {
			$("#insert-money-lb").html("");
		});
		//定额 输入  取消提示语
		$("#insert-quota-text").change(function() {
			$("#insert-quota-lb").html("");
		});
		//提现费率 输入 取消提示语
		$("#insert-wdcrate-text").change(function() {
			$("#insert-wdcrate-lb").html("");
		});
		//最低收费金额 取消提示语
		$("#insert-minfee-text").change(function() {
			$("#insert-minfee-lb").html("");
			$("#insert-maxfee-lb").html("");
		});
		//最高收费金额 取消提示语
		$("#insert-maxfee-text").change(function() {
			$("#insert-maxfee-lb").html("");
			$("#insert-minfee-lb").html("");
		});
	})
	function validateInsertForm() {// 验证表单数据
		//var $ugradeVal = $("input[name='ugrade']:checked").val();
		// 验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
		//if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		//	$("#insert-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
		//	return false;
		//}
		var $ugradeVal = $("#insert-ugrade-select").val();
		if ($ugradeVal == "" || $ugradeVal == null) {
			$("#insert-ugrade-lb").html("<font color='red'>* 请选择会员等级 </font>");
			return false;
		}
		// 验证最小金额
		var $minmoney = $("#insert-minmoney-text").val();
		if ($minmoney == "" || $minmoney == null) {
			$("#insert-minmoney-text").focus();
			$("#insert-money-lb").html("<font color='red'> * 请输入最小金额</font>");
			return false;
		}
		// 验证 最大金额 大于最小金额
		var $maxmoney = $("#insert-maxmoney-text").val();
		if ($maxmoney == "" || $maxmoney == null) {
			$("#insert-maxmoney-text").focus();
			$("#insert-money-lb").html("<font color='red'> * 请输入最大金额</font>");
			return false;
		}
		if (parseFloat($maxmoney) <= parseFloat($minmoney) && $maxmoney != ""
				&& $maxmoney != null) {
			$("#insert-maxmoney-text").focus();
			$("#insert-money-lb").html("<font color='red'> * 金额范围有误</font>");
			return false;
		}
		// 手续费类型
		var $wdcmode = $("#insert-wdcmode-select").val();
		if ($wdcmode == 1) {
			// 验证固定金额
			var $quota = $("#insert-quota-text").val();
			if ($quota == "" || $quota == null) {
				$("#insert-quota-lb").html(
						"<font color='red'> * 请输入固定金额</font>");
				return false;
			}
		}
		if ($wdcmode == 2) {
			// 验证提现费率
			var $wdcrate = $("#insert-wdcrate-text").val();
			if ($wdcrate == "" || $wdcrate == null) {
				$("#insert-wdcrate-text").focus();
				$("#insert-wdcrate-lb").html(
						"<font color='red'> * 请输入提现费率</font>");
				return false;
			}
			if (parseFloat($wdcrate) < 0 || parseFloat($wdcrate) > 100) {
				$("#insert-wdcrate-text").focus();
				$("#insert-wdcrate-lb").html(
						"<font color='red'> * 提现费率正确范围 0~100</font>");
				return false;
			}
			// 验证最小金额
			//var $minfee = $("#insert-minfee-text").val();
			//if ($minfee == "" || $minfee == null) {
			//	$("#insert-minfee-text").focus();
			//	$("#insert-minfee-lb").html(
			//			"<font color='red'> *请输入最小金额 </font>");
			//	return false;
			//}
			// 验证最大金额
			//var $maxfee = $("#insert-maxfee-text").val();
			//if ($maxfee == "" || $maxfee == null) {
			//	$("#insert-maxfee-text").focus();
			//	$("#insert-maxfee-lb").html(
			//			"<font color='red'> *请输入最大金额 </font>");
			//	return false;
			//}
			//if (parseFloat($minfee) > parseFloat($maxfee)) {
			//	$("#insert-maxfee-text").focus();
			//	$("#insert-minfee-lb").html(
			//			"<font color='red'> *最小金额必须小于最大金额  </font>");
			//	return false;
			//}
		}
		return true;
	}
</script>
</head>
<body>
	<form id="insert-rate-form" method="post">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				会员等级：
				<!-- <label class="radio-inline">    <input
					class="insert-ugrade-radio" type="radio" name="ugrade" value="1"
					checked="checked"> 全部等级
				</label> <label class="radio-inline"> <input
					class="insert-ugrade-radio" type="radio" name="ugrade" value="2">
					选择等级
				</label>-->

			</div>
			<div>
				<select id="insert-ugrade-select" name="ugrade">
					<option value="">--请选择会员等级--</option>
					<option value=99>全部会员等级</option>
					<c:forEach items="${uGrades }" var="item">
						<option value="${item.ugrade }">${item.ugradename }</option>
					</c:forEach>
				</select> &nbsp;&nbsp; <label id="insert-ugrade-lb"></label>
			</div>
		</div>
		<!--  
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				<div id="insert-ugrade-checkbox-div"
					style="padding-left: 30px; padding-top: 12px;">
					<c:forEach items="${ugrades}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="ugrades" value="${item.ugrade }"> ${item.ugradename }
						</label>
						<c:if test="${status.count%4==0 }">
							<br />
							<br />
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>		-->
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				金额范围：</div>
			<div>
				<input type="text"
					style="width: 100px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					placeholder="请填写金额" name="minmoney" id="insert-minmoney-text">
				元&nbsp;&nbsp;&nbsp;- &nbsp;&nbsp;&nbsp;<input
					style="width: 100px; text-align: center; line-height: 18px"
					type="text" id="insert-maxmoney-text" name="maxmoney"
					onblur="checkNum(this)" placeholder="请填写金额"
					onkeyup="clearNoNum(event,this)"> 元&nbsp;&nbsp;&nbsp; <label
					id="insert-money-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>类型</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">手续费类型：</div>
			<div>
				<select name="wdcmode" id="insert-wdcmode-select">
					<option value="1">定额收费</option>
					<option value="2">比例收费</option>
				</select>
			</div>
		</div>
		<hr>

		<!-- 定额 -->
		<div id="insert-quota-div">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
					固定金额&nbsp;&nbsp;&nbsp;：</div>
				<div>
					<input class="rate-inputtext" name="quota" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)" type="text"
						style="width: 100px; text-align: center; line-height: 18px"
						placeholder="请填写金额" id="insert-quota-text"> 元&nbsp;&nbsp;
					<label id="insert-quota-lb"></label>
				</div>
			</div>
		</div>

		<!-- 比例 -->
		<div id="insert-rate-div">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">提现费率&nbsp;&nbsp;&nbsp;：
				</div>
				<div>
					<input onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
						class="rate-inputtext" onkeypress="keyPress()" type="text"
						style="width: 100px; text-align: center; line-height: 18px;"
						name="wdcrate" placeholder="请填写费率" id="insert-wdcrate-text">
					% &nbsp;&nbsp; <label id="insert-wdcrate-lb"></label>
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
					最低收费金额：</div>
				<div>
					<input type="text" class="rate-inputtext" name="minfee"
						style="width: 100px; text-align: center; line-height: 18px;"
						onblur="checkNum(this)" placeholder="请填写金额"
						onkeyup="clearNoNum(event,this)" id="insert-minfee-text">
					元&nbsp;<label id="insert-minfee-lb"></label>
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
					最高收费金额：</div>
				<div>
					<input type="text" class="rate-inputtext" name="maxfee"
						style="width: 100px; text-align: center; line-height: 18px;"
						onblur="checkNum(this)" placeholder="请填写金额"
						onkeyup="clearNoNum(event,this)" id="insert-maxfee-text">
					元&nbsp;<label id="insert-maxfee-lb"></label>
				</div>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="remark-text"></textarea>
			</div>
		</div>
	</form>
</body>
</html>