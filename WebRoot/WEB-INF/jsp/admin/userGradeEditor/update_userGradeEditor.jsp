<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		$("#update-ugrade-select").val("${ubai.uasi.ugrade}");
	});
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>用户等级信息</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
			<input type="hidden" name="id" value="${ubai.id }" />
				用 户 名 &nbsp;&nbsp;： <label>${ubai.loginname}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				真实姓名&nbsp;&nbsp;： <label>${ubai.realname}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				会员等级&nbsp;&nbsp;：<select id="update-ugrade-select" name="ugrade">
					<c:forEach items="${userGradeList }" var="userGrade">
						<option value="${userGrade.ugrade }">${userGrade.ugradename }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</body>
</html>