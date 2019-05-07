<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		$("#insert-key-text").change(function() {
			$("#insert-key-lb").html("");
		});
		$("#insert-smsccompany-select").change(function() {
			$("#insert-smsccompany-lb").html("");
		});
		$("#insert-pusername-text").change(function() {
			$("#insert-pusername-lb").html("");
		});
		$("#insert-ppassword-text").change(function() {
			$("#insert-ppassword-lb").html("");
		});
		$("#insert-isuse-select").change(function() {
			$("#insert-isuse-lb").html("");
		});
		$("#insert-smsurl-text").change(function() {
			$("#insert-smsurl-lb").html("");
		});

	});
	function validateInsert() {
		var smsKey = $("#insert-key-text").val();
		if (smsKey == null || smsKey == '') {
			$("#insert-key-lb").html("*请填写短信编号");
			return false;
		}
		var smsccompany = $("#insert-smsccompany-select").val();
		if (smsccompany == null || smsccompany == '') {
			$("#insert-smsccompany-lb").html("*请选择通道公司");
			return false;
		}
		var username = $("#insert-pusername-text").val();
		if (username == null || username == '') {
			$("#insert-pusername-lb").html("*请填写平台登录名");
			return false;
		}
		var password = $("#insert-ppassword-text").val();
		if (password == null || password == '') {
			$("#insert-ppassword-lb").html("*请填写平台密码");
			return false;
		}
		var smsurl = $("#insert-smsurl-text").val();
		if (smsurl == null || smsurl == '') {
			$("#insert-smsurl-lb").html("*请填写通道URL");
			return false;
		}
		var isuse = $("#insert-isuse-select").val();
		if (isuse == null || isuse == '') {
			$("#insert-isuse-lb").html("*请选择是否引用");
			return false;
		}
		return true;
	}
</script>
<body>
	<form id="insert-form" method="post">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>短信通道设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				短信编号：<input type="text" name="key" placeholder="填写短信号码"
					style="text-align: center; line-height: 18px;" id="insert-key-text"><span
					style="color: red"><label id="insert-key-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				通道公司：<select id="insert-smsccompany-select" name="smsccompany">
					<option value="">---请选择通道公司---</option>
					<option value="短信通">短信通</option>
					<option value="创蓝云通讯">创蓝云通讯</option>
				</select> <span style="color: red"><label id="insert-smsccompany-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				登 录 名 ： <input type="text" id="insert-pusername-text"
					style="text-align: center; line-height: 18px;" name="pusername">
				<span style="color: red"><label id="insert-pusername-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				登录密码： <input type="text" id="insert-ppassword-text"
					style="text-align: center; line-height: 18px;" name="ppassword">
				<span style="color: red"><label id="insert-ppassword-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				通道URL： <input type="text" id="insert-smsurl-text"
					style="text-align: center; line-height: 18px;" name="smsurl">
				<span style="color: red"><label id="insert-smsurl-lb"></label></span>
			</div>
		</div>
		<!--  
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				标识信息： <input type="text" id="insert-action-text"
					style="text-align: center; line-height: 18px;" name="action">
				<span style="color: red"><label id="insert-action-lb"></label></span>
				<div style="margin-top: 15px;">
					<span style="color: red">*注：填写短信平台标识信息，如send。</span>
				</div>
			</div>
		</div>-->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				扩 展 号 ： <input type="text" id="insert-extno-text"
					style="text-align: center; line-height: 18px;" name="extno">
				<div style="margin-top: 15px;">
					<span style="color: red">*注：如果没有，则不需要填写</span>
				</div>
			</div>

		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				是否引用：<select id="insert-isuse-select" name="isuse">
					<option value="">---请选择---</option>
					<option value="1">可引用</option>
					<option value="0">不可引用</option>
				</select> <span style="color: red"><label id="insert-isuse-lb"></label></span>
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
					id="insert-remark-text"></textarea>
			</div>
		</div>
	</form>
</body>
</html>