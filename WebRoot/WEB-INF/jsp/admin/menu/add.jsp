<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${resource.fathermenuname !=null }">
	<div class="row">
		<div class="col-md-8">
			<label class="col-sm-2 text-right">上级菜单</label>
			<div class="col-sm-6">
				<input type="hidden" value="${resource.fathernumber}"
					id="fatheropid" /> <input type="text" class="form-control"
					readonly="readonly" value="${resource.fathermenuname}">
			</div>
		</div>
	</div>
</c:if>
<div class="row">
	<div class="col-md-8">
		<label class="col-sm-2 text-right">菜单名称</label>
		<div class="col-sm-6">
			<input type="hidden" value="${ resource.id}" id="opid" /> <input
				type="text" class="form-control" id="menuname" name="menuname"
				value="${resource.menuname}" placeholder="请输入菜单名称...">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8">
		<label class="col-sm-2 text-right">菜单链接</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="resourceurl"
				name="resourceurl" value="${resource.resourceurl}"
				placeholder="请输入菜单链接...">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8">
		<label class="col-sm-2 text-right">菜单编号</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="numbercode"
				name="numbercode" value="${resource.numbercode}"
				placeholder="请输入菜单编号">
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-8">
		<div class="col-sm-6">
			<button class="btn" onclick="menu.menu_save()">保存</button>
			<button class="btn" onclick="menu.menu_back()">返回</button>
		</div>
	</div>
</div>
