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
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		//选择会员等级div隐藏
		//$("#insert-ugrade-checkbox-div").hide();
		//$("#rname_div").hide();
		//$("#ctime_div").hide();
		//$("#stime_div").hide();
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
		//});
		//名单 radio change监听事件
		//$(".insert-rname-radio").change(function() {
		//	var $radioVal = $("input[name='removenameno']:checked").val();
		//当选中全部等级时 选择等级div隐藏，反之显示
		//	if ($radioVal == 2) {
		//		$("#rname_div").show();
		//	} else {
		//		$("#rname_div").hide();
		//取消提示语
		//		$("#insert-rname-lb").html("");
		//	}
		//});
		//关闭时间 radio change监听事件
		//$(".insert-ctime-radio").change(function() {
		//	var $radioVal = $("input[name='closetimeno']:checked").val();
		//当选中全部等级时 选择等级div隐藏，反之显示
		//	if ($radioVal == 2) {
		//		$("#ctime_div").show();
		//	} else {
		//		$("#ctime_div").hide();
		//取消提示语
		//		$("#insert-ctime-lb").html("");
		//	}
		//});
		//特定时间 radio change监听事件
		//$(".insert-stime-radio").change(function() {
		//	var $radioVal = $("input[name='specialtimeno']:checked").val();
		//当选中全部等级时 选择等级div隐藏，反之显示
		//	if ($radioVal == 2) {
		//		$("#stime_div").show();
		//	} else {
		//		$("#stime_div").hide();
		//取消提示语
		//		$("#insert-stime-lb").html("");
		//	}
		//});
		//单击 选择等级checkbox 取消提示语
		//$(".checkbox-inline").click(function() {
		//	$("#insert-ugrade-lb").html("");
		//});
		$("#insert-ugrade-select").change(function() {
			$("#insert-ugrade-lb").html("");
		});
		//单笔金额范围
		$("#insert-lowestmoney-text").change(function() {
			$("#insert-money-lb").html("");
		});
		$("#insert-highestmoney-text").change(function() {
			$("#insert-money-lb").html("");
		});
		//日提现金额限制
		$("#insert-daymoneyrest-text").change(function() {
			$("#insert-daymoneyrest-lb").html("");
		});
		//日提现次数限制
		$("#insert-daytimesrest-text").change(function() {
			$("#insert-daytimesrest-lb").html("");
		});
	})
	function validateForm() {
		// 保存验证
		//var $ugradeVal = $("input[name='ugrade']:checked").val();
		// 验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
		//if ($ugradeVal == 2
		//		&& $("input[name='ugrades']:checked").size() == 0) {
		//	$("#insert-ugrade-lb").html(
		//			"<font color='red'> * 请选择会员等级</font>");
		//	return false;
		//}
		var $ugradeVal = $("#insert-ugrade-select").val();
		if ($ugradeVal == "" || $ugradeVal == null) {
			$("#insert-ugrade-lb").html("<font color='red'>* 请选择会员等级 </font>");
			return false;
		}

		// 单笔金额范围
		var $lowestmoney = $("#insert-lowestmoney-text").val();
		if ($lowestmoney == null || $lowestmoney == "") {
			$("#insert-lowestmoney-text").focus();
			$("#insert-money-lb").html("<font color='red'> * 请输入金额范围</font>");

			return false;
		}
		var $highestmoney = $("#insert-highestmoney-text").val();
		if ($highestmoney == null || $highestmoney == "") {
			$("#insert-highestmoney-text").focus();
			$("#insert-money-lb").html("<font color='red'> * 请输入金额范围</font>");
			return false;
		}
		if (parseFloat($lowestmoney) >= parseFloat($highestmoney)) {
			$("#insert-highestmoney-text").focus();
			$("#insert-money-lb").html(
					"<font color='red'> * 单笔最低金额必须大于最高金额</font>");
			return false;
		}
		var $daymoneyrest = $("#insert-daymoneyrest-text").val();
		if ($daymoneyrest == null || $daymoneyrest == "") {
			$("#insert-daymoneyrest-text").focus();
			$("#insert-daymoneyrest-lb").html(
					"<font color='red'> * 请输入日提现金额</font>");
			return false;
		}
		var $daytimesrest = $("#insert-daytimesrest-text").val();
		if ($daytimesrest == null || $daytimesrest == "") {
			$("#insert-daytimesrest-text").focus();
			$("#insert-daytimesrest-lb").html(
					"<font color='red'> * 请输入日提现次数</font>");
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
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				会员等级：
				<!--  会员等级： <label class="radio-inline"> <input
					class="insert-ugrade-radio" type="radio" name="ugrade" value="1"
					checked="checked"> 全部等级
				</label> <label class="radio-inline"> <input
					class="insert-ugrade-radio" type="radio" name="ugrade" value="2">
					选择等级
				</label> &nbsp;&nbsp;-->
			</div>
			<div>
				<select name="ugrade" id="insert-ugrade-select">
					<option value="">--请选择会员等级--</option>
					<option value=99>全部会员等级</option>
					<c:forEach items="${uGrades }" var="item">
						<option value="${item.ugrade }">${item.ugradename }</option>
					</c:forEach>
				</select>&nbsp;&nbsp;<label id="insert-ugrade-lb"></label>
			</div>
		</div>
		<!--  
		<div class="row">
			<div class="col-md-12 col-md-offset-2">
				<div id="insert-ugrade-checkbox-div" style="margin-top: 10px;">
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
		</div>-->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">单笔金额范围：</div>
			<div>
				<input type="text"
					style="width: 85px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="lowestmoney" id="insert-lowestmoney-text"
					placeholder="最低限制金额">&nbsp;&nbsp;-&nbsp;&nbsp; <input
					type="text" name="highestmoney"
					style="width: 85px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					id="insert-highestmoney-text" placeholder="最高限制金额">&nbsp;&nbsp;
				<label id="insert-money-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				日提现金额限制&nbsp;&nbsp;：</div>
			<div>
				<input placeholder="日提现金额" type="text"
					style="width: 85px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="daymoneyrest" id="insert-daymoneyrest-text">&nbsp;&nbsp;
				<label id="insert-daymoneyrest-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				日提现次数限制&nbsp;&nbsp;：</div>
			<div>
				<input placeholder="日提现次数" type="text"
					style="width: 85px; text-align: center; line-height: 18px;"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					name="daytimesrest" id="insert-daytimesrest-text">&nbsp;&nbsp;
				<label id="insert-daytimesrest-lb"></label>
			</div>
		</div>
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				手续费类型&nbsp;&nbsp;：&nbsp;&nbsp; <label class="radio-inline">
					<input type="radio" class="proxypay" checked="checked"
					name="proxypay" value=1> 自付
				</label> <label class="radio-inline"> <input type="radio"
					name="proxypay" class="proxypay" value=2> 代付
				</label>
			</div>
		</div> -->
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				是否需要审核&nbsp;&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp; <label
					class="radio-inline"> <input type="radio" name="ismanaudit"
					value=1 checked="checked"> 是
				</label> <label class="radio-inline"> <input
					class="insert-ugrade-radio" type="radio" name="ismanaudit" value=2>
					否
				</label> &nbsp;&nbsp; <label id="insert-ismanaudit-lb"></label>
			</div>
		</div>-->
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">是否可以取消提现&nbsp;&nbsp;：</div>
			<div>
				<label class="radio-inline"> <input
					class="insert-iscancel-radio" type="radio" name="iscancel" value=1
					checked="checked"> 是
				</label> <label class="radio-inline"> <input
					class="insert-iscancel-radio" type="radio" name="iscancel" value=2>
					否
				</label> &nbsp;&nbsp; <label id="insert-iscancel-lb"></label>
			</div>
		</div>
		-->
		<!--   名单列表
		<hr>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				名单列表： <label class="radio-inline"> <input
					class="insert-rname-radio" type="radio" name="removenameno"
					value="1" checked="checked"> 全部名单
				</label> <label class="radio-inline"> <input
					class="insert-rname-radio" type="radio" name="removenameno"
					value="2"> 选择名单
				</label> <label class="radio-inline"> <input
					class="insert-rname-radio" type="radio" name="removenameno"
					value="">不选择名单
				</label> &nbsp;&nbsp; <label id="insert-rname-lb"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2" id="rname_div"
				style="margin-top: 10px;">
				<c:forEach items="${rNames }" var="item" varStatus="status">
					<label class="checkbox-inline"> <input type="checkbox"
						name="rnamenos" value="${item.nameno }"> <font color='red'>${item.nametype}</font>&nbsp;${item.name}
					</label>
					<c:if test="${status.count%3==0 }">
						<br />
					</c:if>
				</c:forEach>
			</div>
		</div>-->
		<!-- 关闭时间 
		<hr>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				关闭时间： <label class="radio-inline"> <input
					class="insert-ctime-radio" type="radio" name="closetimeno"
					value="1" checked="checked"> 全部时间
				</label> <label class="radio-inline"> <input
					class="insert-ctime-radio" type="radio" name="closetimeno"
					value="2"> 选择时间
				</label> <label class="radio-inline"> <input
					class="insert-ctime-radio" type="radio" name="closetimeno" value="">不选择时间
				</label> &nbsp;&nbsp; <label id="insert-ctime-lb"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2" id="ctime_div"
				style="margin-top: 10px;">
				<c:forEach items="${cTimes }" var="item" varStatus="status">
					<label class="checkbox-inline"> <input type="checkbox"
						name="ctimenos" value="${item.timeno }"> ${item.timename}
					</label>
					<c:if test="${status.count%4==0 }">
						<br />
						<br />
					</c:if>
				</c:forEach>
			</div>
		</div>-->
		<!-- 特定时间 
		<hr>
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				特定时间： <label class="radio-inline"> <input
					class="insert-stime-radio" type="radio" name="specialtimeno"
					value="1" checked="checked"> 全部时间
				</label> <label class="radio-inline"> <input
					class="insert-stime-radio" type="radio" name="specialtimeno"
					value="2"> 选择时间
				</label> <label class="radio-inline"> <input
					class="insert-stime-radio" type="radio" name="specialtimeno"
					value="">不选择时间
				</label> &nbsp;&nbsp; <label id="insert-stime-lb"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2" id="stime_div"
				style="margin-top: 10px;">
				<c:forEach items="${sTimes }" var="item" varStatus="status">
					<label class="checkbox-inline"> <input type="checkbox"
						name="stimenos" value="${item.timeno }"> <font color='red'>${item.timetype}</font>&nbsp;${item.timename}
					</label>
					<c:if test="${status.count%4==0 }">
						<br />
						<br />
					</c:if>
				</c:forEach>
			</div>
		</div>-->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
			<div class="row" style="line-height: 0px; margin-top: 12px;">
				<div class="col-md-10 col-md-offset-1">
					<textarea class="form-control" name="remark"
						id="insert-remark-text" placeholder="请输入备注："></textarea>
				</div>
			</div>
		</div>
	</form>
</body>
</html>