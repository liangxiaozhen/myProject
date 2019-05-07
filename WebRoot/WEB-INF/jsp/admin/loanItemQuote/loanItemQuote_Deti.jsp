<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loanItemQuote_Inst</title>
<style type="text/css">
    hr{
		margin: 15px;
	} 
</style>
</head>
<body>
       <c:if test="${!empty itemQuote}">
           <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料编号：</font>
				<label id="addman-lb" class="col-sm-4">${itemQuote.liqno}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料属性：</font>
				<c:if test="${itemQuote.infoattribute eq 1}">
				   <label id="addman-lb" class="col-sm-4">公共</label>
				</c:if>
				<c:if test="${itemQuote.infoattribute eq 2}">
				   <label id="addman-lb" class="col-sm-4">补充</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">借款类型：</font>
				<label id="addman-lb" class="col-sm-4">${itemQuote.infotype}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">引用项目名称：</font>
				<label id="addman-lb" class="col-sm-4">${itemQuote.quotename}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">是否必填：</font>
				<c:if test="${itemQuote.isneed eq 1}">
				   <label id="addman-lb" class="col-sm-4">是</label>
				</c:if>
				<c:if test="${itemQuote.isneed eq 0}">
				   <label id="addman-lb" class="col-sm-4">否</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">是否使用：</font>
				<c:if test="${itemQuote.isuse eq 1}">
				   <label id="addman-lb" class="col-sm-4">是</label>
				</c:if>
				<c:if test="${itemQuote.isuse eq 0}">
				   <label id="addman-lb" class="col-sm-4">否</label>
				</c:if>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">添加人：</font>
				<label id="addman-lb" class="col-sm-4">${itemQuote.addman}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">操作时间：</font>
				<label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${itemQuote.addtime}" type="both"/></label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">备注：</font>
				<label id="addman-lb" class="col-sm-4">${itemQuote.remark}</label>
		   </div>
       </c:if>
       <c:if test="${empty itemQuote}">
           <label>暂无数据!</label>
       </c:if>
</body>
</html>