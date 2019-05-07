<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>风控详情</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>用户信息 &nbsp;&nbsp;:</b></font>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> 用 户 名 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.username}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> IP &nbsp;： </label>&nbsp;&nbsp;&nbsp; <label>${userRisk.ip}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> cookie &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.cookie}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> mobile &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.mobile}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> 邮箱 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.email}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> 手机编号 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.mac}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> 备注 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.remark}</label>
		</div>
	</div>
	<hr>


	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>设置信息 &nbsp;&nbsp;:</b></font>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> 添加人 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<label>${userRisk.addman}</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-3">
			<label for="male"> 添加时间 &nbsp;： </label>&nbsp;&nbsp;&nbsp; <label><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${userRisk.addtime}" /></label>
		</div>
	</div>
</body>
</html>