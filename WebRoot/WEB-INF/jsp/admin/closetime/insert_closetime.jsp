<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		$("#insert-timename-select").change(function() {
			var $val = $(this).val();
			$("#insert-timename-text").val("");
			$("#insert-timename-text").attr("type", "hidden");
			if ($val == "add") {
				$("#insert-timename-text").attr("type", "text");
			} else {
				$("#insert-timename-text").val($val);
			}
		});

		$("#insert-timename-select").change(function() {
			$("#insert-timename-lb").html("");
		});
		$("#insert-timename-text").change(function() {
			$("#insert-timename-lb").html("");
		});
		$("#insert-timetype-select").change(function() {
			$("#insert-timetype-lb").html("");
		});
		$("#insert-btime-text").blur(function() {
			$("#insert-btime-lb").html("");
		});
		$("#insert-etime-text").blur(function() {
			$("#insert-etime-lb").html("");
		});
	})
	function validateInsertForm() {
		var $name = $("#insert-timename-select").val();
		if ($name == '' || $name == null) {
			$("#insert-timename-lb").html("<font color='red'>* 请选择名单名称</font>");
			return false;
		}
		if ($name == 'add'
				&& ($("#insert-timename-text").val() == '' || $(
						"#insert-timename-text").val() == null)) {
			$("#insert-timename-lb").html("<font color='red'>* 请填写名单名称</font>");
			return false;
		}
		var $timetype = $("#insert-timetype-select").val();
		if ($timetype == '' || $timetype == null) {
			$("#insert-timetype-lb").html("<font color='red'>* 请选择类型</font>");
			return false;
		}
		var $btime = $("#insert-btime-text").val();
		if ($btime == '' || $btime == null) {
			$("#insert-btime-lb").html("<font color='red'>* 请输入开始时间</font>");
			return false;
		}
		var $etime = $("#insert-etime-text").val();
		if ($etime == '' || $btime == null) {
			$("#insert-etime-lb").html("<font color='red'>* 请输入结束时间</font>");
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
				<font color="red"><b>关闭时间设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				时间名称：&nbsp;&nbsp;&nbsp; <select id="insert-timename-select">
					<option value="">---请选择名称---</option>
					<c:forEach items="${timeNames}" var="item">
						<option value="${item.timename}">${item.timename}</option>
					</c:forEach>
					<option value="add">--自定义名称--</option>
				</select> &nbsp;<input type="hidden" name="timename"
					id="insert-timename-text"> <label id="insert-timename-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				时间类型：&nbsp;&nbsp;&nbsp; <select name="timetype"
					id="insert-timetype-select">
					<option value="">---请选择---</option>
					<option value="提现">提现</option>
				</select> <label id="insert-timetype-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				开始时间：&nbsp;&nbsp;&nbsp; <input id="insert-btime-text" type="text"
					name="btime" class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <label
					id="insert-btime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				结束时间：&nbsp;&nbsp;&nbsp; <input id="insert-etime-text" type="text"
					name="etime" class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <label
					id="insert-etime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="insert-remark-text"></textarea>
			</div>
		</div>
	</form>
</body>
</html>