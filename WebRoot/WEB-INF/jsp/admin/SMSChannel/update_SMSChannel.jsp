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
		$("#update-isuse-select").val("${smsChannel.isuse}");
		$("#update-smsccompany-select").val("${smsChannel.smsccompany}");
		$("#update-smsccompany-select").change(function() {
			$("#update-smsccompany-lb").html("");
		});
		$("#update-pusername-text").change(function() {
			$("#update-pusername-lb").html("");
		});
		$("#update-ppassword-text").change(function() {
			$("#update-ppassword-lb").html("");
		});
		$("#update-isuse-select").change(function() {
			$("#update-isuse-lb").html("");
		});
		$("#update-key-text").change(function() {
			$("#update-key-text").html("");
		});
		$("#update-smsurl-text").change(function() {
			$("#update-smsurl-text").html("");
		});
	});
	function validateUpdate() {
		var smsKey = $("#update-key-text").val();
		if (smsKey == null || smsKey == '') {
			$("#update-key-lb").html("*请填写短信编号");
			return false;
		}

		var smsccompany = $("#update-smsccompany-select").val();
		if (smsccompany == null || smsccompany == '') {
			$("#update-smsccompany-lb").html("*请选择通道公司");
			return false;
		}
		var username = $("#update-pusername-text").val();
		if (username == null || username == '') {
			$("#update-pusername-lb").html("*请填写平台登录名");
			return false;
		}
		var password = $("#update-ppassword-text").val();
		if (password == null || password == '') {
			$("#update-ppassword-lb").html("*请填写平台密码");
			return false;
		}
		var smsurl = $("#update-smsurl-text").val();
		if (smsurl == null || smsurl == '') {
			$("#update-smsurl-lb").html("*请填写通道URL");
			return false;
		}
		var isuse = $("#update-isuse-select").val();
		if (isuse == null || isuse == '') {
			$("#update-isuse-lb").html("*请选择是否引用");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form" method="post">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>短信通道设置</b></font> <input type="hidden"
					name="id" value="${smsChannel.id }">
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				短信编号：<input type="text" name="key" placeholder="填写短信号码"
					style="text-align: center; line-height: 18px;"
					value="${smsChannel.key }" id="update-key-text"> <span
					style="color: red"><label id="update-key-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				通道公司：<select id="update-smsccompany-select" name="smsccompany">
					<option value="">---请选择通道公司---</option>
					<option value="短信通">短信通</option>
					<option value="创蓝云通讯">创蓝云通讯</option>
				</select> <span style="color: red"><label id="update-smsccompany-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				登 录 名 ： <input type="text" id="update-pusername-text"
					style="text-align: center; line-height: 18px;"
					value="${smsChannel.pusername}" name="pusername"> <span
					style="color: red"><label id="update-pusername-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				登录密码： <input type="text" id="update-ppassword-text"
					style="text-align: center; line-height: 18px;"
					value="${smsChannel.ppassword}" name="ppassword"> <span
					style="color: red"><label id="update-ppassword-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				通道URL： <input type="text" id="update-smsurl-text"
					style="text-align: center; line-height: 18px;" name="smsurl"
					value="${smsChannel.smsurl}"> <span style="color: red"><label
					id="update-smsurl-lb"></label></span>
			</div>
		</div>
		<!--  
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				标识信息： <input type="text" id="update-action-text"
					style="text-align: center; line-height: 18px;" value="${smsChannel.action}" name="action"> 
				<span style="color: red"><label id="update-isuse-lb"></label></span>
				<div style="margin-top: 15px;">
					<span style="color: red">*注：填写短信平台标识信息，如send。</span>
				</div>
			</div>
		</div>-->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				扩 展 号 ： <input type="text" id="update-extno-text"
					style="text-align: center; line-height: 18px;"
					value="${smsChannel.extno}" name="extno"> <label
					id="update-extno-lb"></label>
				<div style="margin-top: 15px;">
					<span style="color: red">*注：如果没有，则不需要填写</span>
				</div>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				是否引用：<select id="update-isuse-select" name="isuse">
					<option value="">---请选择---</option>
					<option value="1">可引用</option>
					<option value="0">不可引用</option>
				</select> <span style="color: red"><label id="update-isuse-lb"></label></span>
				<div style="margin-top: 15px;">
					<span style="color: red">*注：如果设置为可引用，则系统通知业务设置可以获取。</span>
				</div>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control" name="remark"
					id="update-remark-text">${smsChannel.remark}</textarea>
			</div>
		</div>
	</form>
</body>
</html>