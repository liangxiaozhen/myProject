<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$("#update-name").focus(function() {
			$("#update-name-lb").html("");
		});
	})
	function validateUpdateForm() {
		if ($("#update-name").val() == "") {
			$("#update-name-lb").html("* 请输入小名单");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>名单编辑</b></font> <input type="hidden"
					name="nameno" value="${rName.nameno }"> <input
					type="hidden" name="nametype" value="${rName.nametype }">
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				大名单：&nbsp;&nbsp;&nbsp; <label>${rName.nametype}</label>

			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				小名单：&nbsp;&nbsp;&nbsp; <input placeholder="子目录"
					style="width: 120px; text-align: center; line-height: 18px;"
					type="text" id="update-name" name="name" value="${rName.name}">
				&nbsp;&nbsp;&nbsp; <font color='red'><label
					id="update-name-lb"></label></font>
			</div>
		</div>
	</form>
</body>
</html>