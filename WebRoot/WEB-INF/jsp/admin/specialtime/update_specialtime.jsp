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
	var type=$("#timeType-lb").html();
	$(function() {
		$("#update-wlevel-select").val("${specialtime.wlevel}");
		$("#update-wlevel-select").change(function() {
			$("#update-wlevel-lb").html("");
		});
		if(type=='奖品'){
			$("#update-wlevel-select").attr("disabled", true);
		}
	})
	function validateUpdateForm() {
		var wlevel = $("#update-wlevel-select").val();
		if (wlevel == ''&&!type=="提现" || wlevel == null&&!type=="提现") {
			$("#update-wlevel-lb").html("<font color='red'>* 请选择优先级</font>");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>关闭时间编辑</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				时间类型：&nbsp;&nbsp;&nbsp; <label id="timeType-lb">${specialtime.timetype }</label><input
					name="timetype" type="hidden" value="${specialtime.timetype }">
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				<input type="hidden" name="id" value="${specialtime.id }"> <input
					type="hidden" name="timename" value="${specialtime.timename }">
				时间名称：&nbsp;&nbsp;&nbsp; <label>${specialtime.timename }</label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				优先级：&nbsp;&nbsp;&nbsp; <select name="wlevel"
					id="update-wlevel-select">
					<option value="">请选择</option>
					<option value=1>1</option>
					<option value=2>2</option>
					<option value=3>3</option>
					<option value=4>4</option>
					<option value=5>5</option>
				</select> <label id="update-wlevel-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				开始时间：&nbsp;&nbsp;&nbsp; <input id="update-btime-text" type="text"
					name="btime" class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${specialtime.btimeStr}"> <label
					id="update-btime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				结束时间：&nbsp;&nbsp;&nbsp; <input type="text" name="etime"
					class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${specialtime.etimeStr}" id="update-etime-text"> <label
					id="update-etime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				最小提现金额：&nbsp;&nbsp;&nbsp; <input type="text" name="minmoney"
					id="update-minmoney-text" style="width: 85px; text-align: center;"
					value='${specialtime.minmoney}'><label
					id="update-minmoney-lb"></label>
			</div>
			<div class="col-md-6">
				最大提现金额：&nbsp;&nbsp;&nbsp; <input type="text" name="maxmoney"
					id="update-maxmoney-text" style="width: 85px; text-align: center;"
					value="${specialtime.maxmoney}"><label
					id="update-maxmoney-lb"></label>
			</div>


		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="update-remark-text">${specialtime.remark}</textarea>
			</div>
		</div>
	</form>
</body>
</html>