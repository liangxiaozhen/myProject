
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/login.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>

<%-- <script  type="text/javascript">
 	window.location.href = "<%=basePath%>user/tologin.action";
</script> --%>

</head>
<body>

</body>


</html>