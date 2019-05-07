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
		$("#update-btime-text").blur(function() {
			$("#update-btime-lb").html("");
		});
		$("#update-etime-text").blur(function() {
			$("#update-etime-lb").html("");
		});
	})
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
				<input type="hidden" name="id" value="${closetime.id }">
				时间名称：&nbsp;&nbsp;&nbsp; <label>${closetime.timename }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				时间类型：&nbsp;&nbsp;&nbsp; <label>${closetime.timetype }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				开始时间：&nbsp;&nbsp;&nbsp; <input id="update-btime-text" type="text"
					name="btime" class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${closetime.btimeStr }"> <label id="update-btime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				结束时间：&nbsp;&nbsp;&nbsp; <input id="update-etime-text" type="text"
					name="etime" class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					value="${closetime.etimeStr }"> <label id="update-etime-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="update-remark-text">${closetime.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>