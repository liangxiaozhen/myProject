<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>屏蔽安全验证修改验证</title>
<script type="text/javascript">
	$(function(){
		$("#update-nametype-select").val("${cancelValidate.canceltype}");
		$("$update-nametype-select").change(function(){
			$("#update-name-lb").html("");
		});
	})
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height:0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>用户修改&nbsp;&nbsp;:</b></font>
				<input type="hidden" name="id" value="${cancelValidate.id}" id="update-name-id">
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-3">
				<label for="male"> 用 户 名 &nbsp;： </label>&nbsp;&nbsp;<label>${cancelValidate.username}</label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-3">
				<label for="male"> IP &nbsp;： </label>&nbsp;&nbsp;<label>${cancelValidate.ip}</label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-3">
				<label for="male"> cookie &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${cancelValidate.cookie}</label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-3">
				<label for="male"> mobile &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${cancelValidate.mobile}</label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-3">
				<label for="male"> 邮箱 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${cancelValidate.email}</label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-3">
				<label for="male"> 取消验证类型 &nbsp;： </label> &nbsp;&nbsp; <select
					name="canceltype" id="update-nametype-select">
					<option value="1">登录验证码</option>
					<option value="2">注册验证码</option>
					<option value="3">密码控件</option>
					<option value="4">登录U盾</option>
				</select> &nbsp;&nbsp; <label id="update-name-lb"></label>
			</div>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-1">
				<font color="red"><b>备注 &nbsp;&nbsp;:</b></font>
			</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-6 col-md-offset-3">
				<textarea class="form-control" name="remark" placeholder="请输入备注 :"
					id="update-remark-text">${cancelValidate.remark}</textarea>
			</div>
		</div>
	</form>
</body>
</html>