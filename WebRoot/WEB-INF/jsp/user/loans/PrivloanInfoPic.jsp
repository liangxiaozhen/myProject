<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PrivloanInfoPic</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
#ht{
	margin-left: 36%;
	}
</style>
</head>
<body>
<div id="ht"><h2><span class="glyphicon glyphicon-yen"></span><em>图片区</em></h2></div>
 	<form class="form-horizontal text-center" role="form" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/user/common/adduserLoanMaterialprc.action">
	     <div class="container" style="margin-top: 25px;">
             <input type="hidden" name="baseid" value="${user.id}"/>
             <input type="hidden" name="loanno" value="${loanno}"/>
             <input type="hidden" name="loanappid" value="${loanappid}"/>
	         <!-- 自填类资料 -->
	         <c:if test="${!empty infoNeedpics}">
	         <c:forEach items="${infoNeedpics}" var="need" varStatus="stat">
	             <!-- 引用资料编号 -->
	             <input type="hidden" name="linno" value="${need.itemQuote.liqno}"/>
	             <div class="form-group">
	             <!-- 文本选择填写 -->
	     		   <label class="col-sm-3 control-label">${need.infoname}:</label>
	     		   <div class="col-sm-3">
	     		     <!-- 引用资料内容 -->
	     		     <input type="file" name="${need.infoname}" class="form-control"/>
	     		   </div>
	     		    <div class="col-sm-2">
	     		    <span style="color:red;">文件大小限制在1M左右</span>
	     		  </div>
	     	     </div>
	     	  </c:forEach>
	         </c:if>
	         <c:if test="${empty infoNeedpics}">
	           <label>暂无数据！</label>
	         </c:if>
	         <div class="form-group">
	           <label class="control-label col-sm-3">备注</label>
	           <div class="col-sm-3">
	               <textarea rows="3" name="remark" class="form-control"></textarea>
	           </div>
	         </div>
	         <div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
					<!-- <div class="col-sm-1">
						<button type="button" class="btn btn-success">返回列表</button>
					</div> -->
			</div>
	       </div>
	   </form>
</body>
</html>