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
		$("#update-ugrade-select").change(function() {
			$("#update-ugrade-lb").html("");
		});
	});

	function validateUpdateForm() {
		var ugrade = $("#update-ugrade-select").val();
		if (ugrade == '' || ugrade == null) {
			$("#update-ugrade-lb").html(
					"<font style='color:red;'>*请选择用户等级</font>");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="batchUpdate-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>用户等级信息</b></font>
			</div>
		</div>
		<hr>
		<input id="batchUpdate-ids" name="ids" type="hidden" value="${ids }">
		<div align="center" style="width: 100%">
			总共选择了 <font color='red'><label>${count }</label></font> 条记录。
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				会员等级&nbsp;&nbsp;：<select id="update-ugrade-select" name="ugrade">
					<option value="">---请选择---</option>
					<c:forEach items="${userGradeList }" var="userGrade">
						<option value="${userGrade.ugrade }">${userGrade.ugradename }</option>
					</c:forEach>
				</select> <label id="update-ugrade-lb"></label>
			</div>
		</div>
	</form>
</body>
</html>