<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
    hr{
		margin: 10px;
	} 
</style>
</head>
<body>
 <c:if test="${!empty infoNeed}">
           <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料编号：</font>
				<label id="addman-lb" class="col-sm-4">${infoNeed.linno}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料名称：</font>
				   <label id="addman-lb" class="col-sm-4">${infoNeed.infoname}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">字符长度限制：</font>
				<label id="addman-lb" class="col-sm-4">${infoNeed.charlength}字符</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料类型：</font>
				<c:if test="${infoNeed.infotype eq 1}">
				   <label id="addman-lb" class="col-sm-4">图片</label>
				</c:if>
				<c:if test="${infoNeed.infotype eq 2}">
				   <label id="addman-lb" class="col-sm-4">文本</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">引用对象：</font>
				   <label id="addman-lb" class="col-sm-4">${infoNeed.quoteobject}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">是否启用：</font>
				<c:if test="${infoNeed.isneed eq 1}">
				  <label id="addman-lb" class="col-sm-4">是</label>
				</c:if>
				<c:if test="${infoNeed.isneed eq 0}">
				  <label id="addman-lb" class="col-sm-4">否</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">添加人：</font>
				<label id="addman-lb" class="col-sm-4">${infoNeed.addman}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">操作时间：</font>
				<label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${infoNeed.addtime}" type="both"/></label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">备注：</font>
				<label id="addman-lb" class="col-sm-4">${infoNeed.remark}</label>
		   </div>
       </c:if>
       <c:if test="${empty infoNeed}">
           <label>暂无数据!</label>
       </c:if>
</body>
</html>