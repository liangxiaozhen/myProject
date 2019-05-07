<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loanInfoPreset_List</title>
<style type="text/css">
    hr{
		margin: 10px;
	} 
</style>
</head>
<body>
<c:if test="${!empty infoPreset}">
           <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料编号：</font>
				<label id="addman-lb" class="col-sm-4">${infoPreset.linno}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料名称：</font>
				   <label id="addman-lb" class="col-sm-4">${infoPreset.infoname}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料类型：</font>
				<c:if test="${infoPreset.infotype eq 1}">
				   <label id="addman-lb" class="col-sm-4">选择</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">多选内容名称的编号：</font>
				   <label id="addman-lb" class="col-sm-4">${infoPreset.multino}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">单选或多选：</font>
				<c:if test="${infoPreset.oneormulti eq 1}">
				  <label id="addman-lb" class="col-sm-4">单选</label>
				</c:if>
				<c:if test="${infoPreset.oneormulti eq 2}">
				  <label id="addman-lb" class="col-sm-4">多选</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">引用对象：</font>
				   <label id="addman-lb" class="col-sm-6">${infoPreset.quoteobject}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">是否启用：</font>
				<c:if test="${infoPreset.isneed eq 1}">
				  <label id="addman-lb" class="col-sm-4">是</label>
				</c:if>
				<c:if test="${infoPreset.isneed eq 0}">
				  <label id="addman-lb" class="col-sm-4">否</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">添加人：</font>
				<label id="addman-lb" class="col-sm-4">${infoPreset.addman}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">操作时间：</font>
				<label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${infoPreset.addtime}" type="both"/></label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">备注：</font>
				<label id="addman-lb" class="col-sm-4">${infoPreset.remark}</label>
		   </div>
       </c:if>
       <c:if test="${empty infoPreset}">
           <label>暂无数据!</label>
       </c:if>
</body>
</html>