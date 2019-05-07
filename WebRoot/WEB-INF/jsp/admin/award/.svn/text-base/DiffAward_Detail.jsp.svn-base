<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<style type="text/css">
		.laber_from {
			font-weight: normal;	
		}
	</style>
</head>
<body>
	<!--奖品属性名称-->
	<div class="row" style="line-height: 0px;">
		<label class="col-md-3 col-md-offset-2 text-right laber_from">奖品属性名称：</label>
		<label class="laber_from">${awardType}</label>
	</div>
	<hr/>
	<!--奖品交易方式总开关-->
	<div class="row" style="line-height: 0px;">
		<label class="col-md-3 col-md-offset-2 text-right laber_from">交易方式总开关：</label>
		<label class="laber_from">
			<c:if test="${das.allSwitch eq 1}">开</c:if>
			<c:if test="${das.allSwitch eq 2}">关</c:if>
		</label>
	</div>
	<hr/>
	<!-- 操作时间 -->
	<div class="row" style="line-height: 0px;">
		<label class="col-md-3 col-md-offset-2 text-right laber_from">操作时间：</label>
		<label class="laber_from">
			<fmt:formatDate value="${das.operateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
		</label>
	</div>
	<hr/>
	
	<!-- 操作人 -->
	<div class="row" style="line-height: 0px;">
		<label class="col-md-3 col-md-offset-2 text-right laber_from">操作人：</label>
		<label class="laber_from">${das.operateMan}</label>
	</div>
	<hr/>
	
	<!--备注-->
	<div class="row" style="line-height: 0px;">
		<label class="col-md-3 col-md-offset-2 text-right laber_from">备注：</label>
		<label class="laber_from">${das.remark}</label>
	</div>
</body>
</html>	
