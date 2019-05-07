<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<style type="text/css">
		.label_from {
			font-weight: normal;	
		}
	</style>
</head>
<form class="form-horizontal">
	
	<div class="form-group">
		<label class="col-md-3 control-label label_from">ID：</label>
		<div class="col-sm-8">
			<p id="actAwardId" class="form-control-static">${aals.id}</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-3 control-label label_from">领奖规则：</label>
		<div class="col-sm-8">
			<p class="form-control-static">${award.awardRules}</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-md-3 control-label label_from">备注：</label>
		<div class="col-sm-8">
			<p class="form-control-static">${aals.remark}</p>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-md-3 control-label label_from">管理员备注：</label>
		<div class="col-sm-8">
			<textarea id="adminAwardRemark" class="form-control" value="${aals.adminRemark}">${aals.adminRemark}</textarea>
		</div>
	</div>
</form>
</html>