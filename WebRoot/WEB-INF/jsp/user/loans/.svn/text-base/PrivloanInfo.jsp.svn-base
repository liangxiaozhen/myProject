<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PublicloanInfo</title>
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
<div id="ht"><h2><span class="glyphicon glyphicon-yen"></span><em>文本选择项</em></h2></div>
<a href="${pageContext.request.contextPath}/user/loan/usercommonpic.action">添加图片</a>
 	<form class="form-horizontal text-center" role="form" method="post" action="${pageContext.request.contextPath}/user/common/adduserLoanMaterial.action">
	     <div class="container" style="margin-top: 25px;">
	         <input type="hidden" name="baseid" value="${user.id}"/>
	         <input type="hidden" name="loanno" value="${loanno}"/>
	         <input type="hidden" name="appid" value="${loanappid}"/>
	         <!-- 自填类资料 -->
	         <c:if test="${!empty needs}">
	         <c:forEach items="${needs}" var="need" varStatus="stat">
	             <!-- 引用资料名称 -->
	             <input type="hidden" name="infoNeeds[${stat.index}].materialname" value="${need.infoname}"/>
	             <!-- 引用资料编号 -->
	             <input type="hidden" name="infoNeeds[${stat.index}].linno" value="${need.itemQuote.liqno}"/>
	             <div class="form-group">
	             <!-- 文本选择填写 -->
	             <c:if test="${need.infotype eq 2}">
	     		   <label class="col-sm-3 control-label">${need.infoname}:</label>
	     		   <div class="col-sm-3">
	     		     <!-- 引用资料内容 -->
	     		     <input type="text" name="infoNeeds[${stat.index}].materialcontent" class="form-control" maxlength="${need.charlength}"/>
	     		   </div>
	     		    <div class="col-sm-2">
	     		    <span style="color:red;">长度限制在${need.charlength}个字符之内</span>
	     		  </div>
	             </c:if>
	     	     </div>
	     	  </c:forEach>
	         </c:if>
	         <c:if test="${empty needs}">
	            <label>暂无数据！</label>
	         </c:if>
	         <!-- 选择类资料 -->
	         <c:if test="${!empty presets}">
	            <c:forEach items="${presets}" var="prest" varStatus="prsta">
	             <!-- 引用资料名称 -->
	             <input type="hidden" name="infoPresets[${prsta.index}].materialname" value="${prest.infoname}"/>
	             <!-- 引用资料编号 -->
	             <input type="hidden" name="infoPresets[${prsta.index}].linno" value="${prest.itemQuote.liqno}"/>
	             <div class="form-group">
	     		   <label class="col-sm-3 control-label">${prest.infoname}:</label>
	     		   <!-- 单选 -->
	     		   <c:if test="${prest.oneormulti eq 1}">
	     		    <div class="col-sm-3">
	     		     <select name="infoPresets[${prsta.index}].materialcontent" class="form-control">
	     		         <option value="">请选择</option>
	     		      <c:forEach items="${contentSets}" var="tent">
	     		         <c:if test="${tent.multino  eq prest.multino}">
	     		            <option value="${tent.optionname}">${tent.optionname}</option>
	     		         </c:if>
	     		      </c:forEach>
	     		      </select>
	     		      </div>
	     		   </c:if>
	     		    <!-- 多选 -->
	     		    <c:if test="${prest.oneormulti eq 2}">
	     		    <div class="col-sm-6 text-left">
	     		     <c:forEach items="${contentSets}" var="tent">
	     		         <c:if test="${tent.multino  eq prest.multino}">
	     		            <label class="checkbox-inline">
				 			<input type="checkbox" name="infoPresets[${prsta.index}].materialcontent" id="intugrades" value="${tent.optionname}"/>${tent.optionname}
				 		 </label>
	     		         </c:if>
	     		      </c:forEach>
	     		      </div>
	     		   </c:if>
	     	     </div>
	     	  </c:forEach>
	         </c:if>
	         <c:if test="${empty presets}">
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
				<!-- 	<div class="col-sm-1">
						<button type="button" class="btn btn-success">返回列表</button>
					</div> -->
			</div>
	     </div>
	</form>
</body>
</html>