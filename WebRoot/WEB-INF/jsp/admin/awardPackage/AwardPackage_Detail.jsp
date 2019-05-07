<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>AwardPackage</title>
<script type="text/javascript">
	//返回列表页
	function gotoAwardPackageList(){
		window.location.href="${pageContext.request.contextPath }/admin/awardPackage/selectAwardPackageByCondition.action";
	}

</script>
<style type="text/css">
#id {
	margin: 40px;
}

hr {
	margin: 10px;
}
</style>
</head>
<body style="font-family: '微软雅黑';">
	<form class="form-horizontal">

		<div class="form-group">
			<div class="col-sm-1 col-sm-offset-1 text-right">
				<font size="4">奖品包</font>
			</div>
		</div>
		<hr>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">奖品包编号：</font> <font>${ap.apno}</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">奖品包名称：</font> <font>${ap.apname}</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">是否下架：</font> <font> <c:forEach
						items="${iscancelmap}" var="im">
						<c:if test="${ap.iscancel eq im.key}">${im.value}</c:if>
					</c:forEach>
				</font>
			</div>
		</div>

		<%-- <div class="form-group  has-feedback">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">是否为模板：</font> <font> <c:forEach
						items="${istempletmap}" var="im">
						<c:if test="${ap.isTemplet eq im.key}">${im.value}</c:if>
					</c:forEach>
				</font>
			</div>
		</div> --%>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">奖品包总份数：</font> <font>${ap.quantityall}</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">奖品包剩余数：</font> <font>${ap.quantityrest}</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">添加时间：</font> <font> <fmt:formatDate
						value="${ap.addtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
				</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">添加人：</font> <font>${ap.addman}</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-md-offset-2">
				<font class="col-sm-2 text-right">备注：</font> <font>${ap.remark}</font>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-1 col-sm-offset-1 text-right">
				<font size="4">奖品包的内容</font>
			</div>
		</div>
		<hr>


		<div class="container" style="width: 55%">
			<table class="table table-hover">
				<thead>
					<tr align="center" valign="middle">
						<td>奖品编号</td>
						<td>奖品名称</td>
						<td>奖品类型</td>
						<td>奖品份数</td>
						<!-- <td>奖品种类</td> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${apdSet}" var="as">
						<tr align="center" valign="middle">
							<td>${as.awardNO}</td>
							<td>${as.awardName}</td>
							<td><c:forEach items="${atypemap}" var="am">
									<c:if test="${am.key eq as.apAwardType}">
									${am.value}
								</c:if>
								</c:forEach></td>
							<td>${as.awardQTY}</td>
							<%-- <td>${as.awardAttr}</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="form-group">
			<div class="col-sm-1 col-sm-offset-3">
				<button type="button" class="btn btn-default"
					onclick="gotoAwardPackageList();">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
