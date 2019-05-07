<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail.jsp</title>
</head>
<body>
          <c:if test="${!empty loanMaterial}">
           <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">用户名:</font>
	            <label class="col-sm-4">${loanMaterial.id}</label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">登录名:</font>
	            <label class="col-sm-4">${loanMaterial.baseid}</label>
	       </div>
           <hr>
           <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">借款编号:</font>
	         <label class="col-sm-4">${loanMaterial.loanno}</label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">资料类型:</font>
	         <c:if test="${loanMaterial.materialtype eq 1}">
	           <label class="col-sm-4">公共</label>
	         </c:if>
	          <c:if test="${loanMaterial.materialtype eq 2}">
	           <label class="col-sm-4">补充</label>
	         </c:if>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">资料名称:</font>
	         <label class="col-sm-4">${loanMaterial.materialname}</label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">资料内容:</font>
	         <label class="col-sm-4">${loanMaterial.materialcontent}</label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">资料图片:</font>
	         <label class="col-sm-4">${loanMaterial.materialpic}</label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">提交时间:</font>
	         <label class="col-sm-4"><fmt:formatDate value="${loanMaterial.addtime}" type="both"/></label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">审核状态:</font>
	         <c:if test="${loanMaterial.auditstatus eq 1}">
	           <label class="col-sm-4">审核通过</label>
	         </c:if>
	         <c:if test="${loanMaterial.auditstatus eq 0}">
	           <label class="col-sm-4">未审核</label>
	         </c:if>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">审核人:</font>
	         <label class="col-sm-4">${loanMaterial.auditman}</label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">审核时间:</font>
	         <label class="col-sm-4"><fmt:formatDate value="${loanMaterial.audittime}" type="both"/></label>
	       </div>
	       <hr>
	       <div class="row" style="line-height:10px;">
	         <font size="3" class="col-sm-4 text-right">备注:</font>
	         <label class="col-sm-4">${loanMaterial.remark}</label>
	       </div>
	      </c:if>
	      <c:if test="${empty loanMaterial}">
	        <label>赞无数据！</label>
	      </c:if>
</body>
</html>