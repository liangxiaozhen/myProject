<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<title>图片资料查看</title>
<style type="text/css">
#creattype {
	line-height: 300px;
	padding-left: 45%;
}

span.font {
	font-size: 30px;
	font-family: '微软雅黑';
}

#ht {
	margin-left: 45%;
}
</style>
</head>
<body>
	<div id="ht">
		<h2>
			<span class="glyphicon glyphicon-user"></span><em>图片信息</em>
		</h2>
	</div>
	<div class="col-md-12 column tab-pane fade in active" id="home">
		<table class="table table-hover">
			<c:forEach var="item" items="${piclist}">
				<tr>
					<th><span class="glyphicon glyphicon-ice-lolly" id="creattype"><b>${item.certname}：</b></span></th>
					<td><img src="http://localhost:8080/pic/${item.certinfopath}"
						width="300px" height="300px" class="img-rounded"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2" align="center"><a href="#"
					onClick="javascript :history.go(-1);">回到资料审核</a></td>
			</tr>
		</table>
	</div>
</body>
</html>