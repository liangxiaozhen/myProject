<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		var size = $("#size").val();
		if (size == 0) {
			$("#top_div").hide();
			$("#foot_hr").hide();
		} else {
			$("#top_div").show();
			$("#foot_hr").show();
		}
	})
	/*
	 *	全选/全不选
	 */
	function selectUserID(obj) {
		var checkboxs = document.getElementsByName("userID");
		for (i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
	}
</script>
</head>
<body>
	<table class="table table-hover">
		<tr class="text-center" style="background: #ccc;">
			<td><input type="checkbox" onclick="selectUserID(this)"></td>
			<td>序号</td>
			<td>用户名</td>
			<td>真实姓名</td>
			<td>会员等级</td>
		</tr>
		<c:forEach items="${rNames}" var="item" varStatus="status">
			<tr class="text-center">
				<td><input name="userID" type="checkbox"
					value="${item.baseid }"></td>
				<td>${status.count }</td>
				<td>${item.loginname }</td>
				<td>${item.realname }</td>
				<td>${item.ugrade.ugradename }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>