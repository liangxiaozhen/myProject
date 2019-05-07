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
			$("#insert-timename-lb").html("");
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
		$("#insert-timetype-select").change(function() {
			$("#insert-timetype-lb").html("");
			var $val = $(this).val();
			if ($val == '奖品') {
				//$("#insert-wlevel-select").attr("value", "默认");
				$("#insert-wlevel-select").val("");
				$("#insert-wlevel-select").attr("disabled", true);
			} else if ($val == '提现') {
				$("#insert-wlevel-select").val("");
				$("#insert-wlevel-select").attr("disabled", false);
			}
		});
		$("#insert-wlevel-select").change(function() {
			$("#insert-wlevel-lb").html("");
		});
		$("#insert-btime-text").blur(function() {
			$("#insert-btime-lb").html("");
		});

		$("#insert-etime-text").blur(function() {
			$("#insert-etime-lb").html("");
		});
		$("#insert-minmoney-text").blur(function() {
			$("#insert-minmoney-lb").html("");
		});
		$("#insert-maxmoney-text").blur(function() {
			$("#insert-maxmoney-lb").html("");
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
		var $wlevel = $("#insert-wlevel-select").val();
		if ($wlevel == "" && $timetype == '提现' || $wlevel == null
				&& $timetype == '提现') {
			$("#insert-wlevel-lb").html("<font color='red'>* 请选择优先级</font>");
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
		var $minmoney = $("#insert-minmoney-text").val();
		if ($minmoney == '' || $minmoney == null) {
			$("#insert-minmoney-lb").html("<font color='red'>*</font>");
			return false;
		}
		var $maxmoney = $("#insert-maxmoney-text").val();
		if ($maxmoney == '' || $maxmoney == null) {
			$("#insert-maxmoney-lb").html("<font color='red'>*</font>");
			return false;
		}
		return true;
	}
	function change() {
		var timeType = $("#insert-timetype-select").val();
		var action = "querySpecialTimeName.action";
		var params = {
			"timeType" : timeType
		}
		var callback = function(data) {
			var timeNames = document.getElementById("insert-timename-select");
			timeNames.options[0] = new Option("---请选择---", '');
			timeNames.options[0].selected = true;
			for (var i = 0; i < data.length; i++) {
				timeNames.options[timeNames.length] = new Option(
						data[i].timename, data[i].timename);
			}
			timeNames.options[timeNames.length] = new Option("---自定义名称---", 'add');
		}
		document.getElementById("insert-timename-select").length = 0;
		$.post(action, params, callback, 'json');
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>特定时间设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				时间包类型：&nbsp;&nbsp;&nbsp; <select name="timetype"
					id="insert-timetype-select" onchange="change()">
					<option value="">---请选择---</option>
					<option value="提现">提现</option>
					<option value="奖品">奖品</option>
				</select> <label id="insert-timetype-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				时间包名称：&nbsp;&nbsp;&nbsp; <select id="insert-timename-select">
					<option value="">---请选择名称---</option>
				</select> &nbsp;<input type="hidden" name="timename"
					id="insert-timename-text"> <label id="insert-timename-lb"></label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				优先级：&nbsp;&nbsp;&nbsp; <select name="wlevel"
					id="insert-wlevel-select">
					<option value="">---请选择---</option>
					<option value=1>1</option>
					<option value=2>2</option>
					<option value=3>3</option>
					<option value=4>4</option>
					<option value=5>5</option>
				</select> <label id="insert-wlevel-lb"></label>
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
				结束时间：&nbsp;&nbsp;&nbsp; <input type="text" name="etime"
					class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <label
					id="insert-etime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				最小提现金额：&nbsp;&nbsp;&nbsp; <input type="text" name="minmoney"
					id="insert-minmoney-text" style="width: 85px; text-align: center;"><label
					id="insert-minmoney-lb"></label>
			</div>
			<div class="col-md-6">
				最大提现金额：&nbsp;&nbsp;&nbsp; <input type="text" name="maxmoney"
					id="insert-maxmoney-text" style="width: 85px; text-align: center;"><label
					id="insert-maxmoney-lb"></label>
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