<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<style>
.col-center-block {
	position: absolute;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
</head>
<body>
	<div class="row col-center-block" style="line-height: 0px;">
		<font size="3">模板生成手动颁奖活动</font>
	</div>
	<hr>

	<div class="row col-center-block" style="line-height: 0px;">
		<font class="control-label">请选择模板：</font> <select id="templetActName"
			name="activityName">
			<option>--请选择--</option>
			<c:if test="${!empty mamList}">
				<c:forEach items="${mamList }" var="mam">
					<option>${mam.activityName}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
</body>
</div>
</html>