<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">ID：</label><label id="actAwardId"
		class="col-md-4">${aals.id}</label>
	<hr />
</div>

<div class="row">
	<label class="col-md-3 text-right">领奖规则：</label> <label><font
		color="red">${award.awardRules}</font></label>
	<hr />
</div>

<div class="row">
	<label class="col-md-3 text-right">管理员备注：</label>
	<div class="col-md-8 text-left">
		<label><font color="red">${aals.adminRemark}</font></label>
	</div>
</div>
<hr />

<div class="row">
	<label class="col-md-3 text-right">备注：</label>
	<div class="col-md-8">
		<textarea id="actAwardRemark" class="form-control"
			value="${aals.remark}">${aals.remark}</textarea>
		<label for="demand"><input id="demand" type="checkbox"
			onclick="agree()">我已按要求填写</input></label>
	</div>
</div>
