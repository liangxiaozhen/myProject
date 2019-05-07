<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Datachange.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="form-horizontal">
      <input type="hidden" id="LoanMaterialID" value="${id}"/>
      <!-- 自填类文本 -->
      <c:if test="${!empty z}">
      <input type="hidden" id="medium" value="${z}"/>
          <div class="form-group">
             <label class="control-label col-sm-3">${quotename}:</label>
             <div class="col-sm-5">
             <c:if test="${charlength>20}">
                 <textarea rows="3" class="form-control" id="materialContent" required="required">${materialContent}</textarea>
                 <span style="color:red;">字符长度限制为${charlength}字符!</span>
             </c:if>
             <c:if test="${charlength<20}">
             <input type="text" value="${materialContent}" maxlength="${charlength}" id="materialContent" class="form-control" required="required"/>
             <span style="color:red;">字符长度限制为${charlength}字符!</span>
             </c:if>
             </div>
          </div>
      </c:if>
      <!-- 自填类图片 -->
      <c:if test="${!empty p}">
          <input type="hidden" id="medium" value="${p}"/>
      <form role="form" enctype="multipart/form-data" method="post" class="form-horizontal" id="picupdateform" action="${pageContext.request.contextPath}/user/loan/updatpic.action">
           <input type="hidden" name="id" value="${id}"/>
           <input type="hidden" name="baseid" value="${baseid}"/>
           <input type="hidden" name="loanno" value="${loanno}"/>
           <div class="form-group">
             <label class="control-label col-sm-3">${quotename}:</label>
             <div class="col-sm-5">
             <input type="file" name="${quotename}" class="form-control" required="required"/>
             </div>
             <div class="col-sm-2">
                  <button type="submit" class="btn btn-primary">上传</button>
             </div>
           </div>
      </form>
      </c:if>
      <!-- 选择类(单选) -->
      <c:if test="${!empty litt}">
      <input type="hidden" id="medium" value="${litt}"/>
           <div class="form-group">
              <label class="control-label col-sm-3">${infoname}:</label>
              <div class="col-sm-3">
              <select id="materialContent" class="form-control" required="required">
                  <c:forEach items="${contentSets}" var="cont">
                     <option value="${cont.optionname}">${cont.optionname}</option>
                  </c:forEach>
              </select>
              </div>
          </div>
      </c:if>
      <!-- 选择类(多选) -->
      <c:if test="${!empty more}">
      <form id="moreform" role="form" method="post" class="form-horizontal">
            <input type="hidden" id="medium" value="${more}"/>
            <input type="hidden" name="id" value="${id}"/>
            <div class="form-group">
              <label class="control-label col-sm-3">${infoname}:</label>
              <div class="col-sm-6">
                 <c:forEach items="${contentSets}" var="cont" >
                  <label class="checkbox-inline" style="width:100px;">
			   	 		<input type="checkbox" name="materialcontent" value="${cont.optionname}"/>${cont.optionname}
			   	  </label>
			   	  </c:forEach>
              </div>
            </div>
        </form>
      </c:if>
      </div>
</body>
</html>