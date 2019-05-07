<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <c:if test="${!empty contentSet}">
           <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">多选内容资料编号：</font>
				<label id="addman-lb" class="col-sm-4">${contentSet.multino}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">选项名称：</font>
				   <label id="addman-lb" class="col-sm-4">${contentSet.optionname}</label>
		   </div>
		   <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资料类型：</font>
				<c:if test="${contentSet.isneed eq 1}">
				   <label id="addman-lb" class="col-sm-4">需要</label>
				</c:if>
				<c:if test="${contentSet.isneed eq 0}">
				   <label id="addman-lb" class="col-sm-4">不需要</label>
				</c:if>
		   </div>
       </c:if>
       <c:if test="${empty contentSet}">
           <label>暂无数据!</label>
       </c:if>
</body>
</html>