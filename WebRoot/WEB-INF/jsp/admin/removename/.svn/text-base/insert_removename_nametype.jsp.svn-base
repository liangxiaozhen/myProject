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
		$("#insert-nameType").focus(function() {
			$("#lb-nameType").html("");
		});
		$("#insert-name").focus(function(){
			$("#lb-name").html("");
		});
	})
	function getNameType() {
		var value = $("#select-nameType").val();
		var nameType = $("#insert-nameType");
		$("#lb-nameType").html("");
		nameType.val("");
		nameType.attr("type", "hidden");
		if (value == "add") {
			nameType.attr("type", "text");
		} else {
			nameType.val(value);
		}
	}
	function validateInsertName() {
		if ($("#select-nameType").val() == -1) {
			$("#lb-nameType").html("<font color='red'>* 请填写大名单</font>");
			return false;
		}
		if ($("#select-nameType").val() == "add") {
			if ($("#insert-nameType").val() == "") {
				$("#lb-nameType").html("<font color='red'>* 请填写大名单</font>");
				return false;
			}
		}

		if ($("#insert-name").val() == "") {
			$("#lb-name").html("<font color='red'>* 请填写小名单</font>");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>目录设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				大名单：&nbsp;&nbsp;&nbsp; <select onchange="getNameType()"
					id="select-nameType">
					<option value="-1">--请选择大名单--</option>
					<c:forEach items="${names }" var="item">
						<option>${item.nametype}</option>
					</c:forEach>
					<option value="add">--自定义大名单--</option>
				</select>&nbsp;&nbsp; <input type="hidden" name="nametype"
					placeholder="请输入大名单" style="text-align: center; line-height: 18px;"
					id="insert-nameType">&nbsp;&nbsp;<label id="lb-nameType"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				小名单：&nbsp;&nbsp;&nbsp; <input type="text" placeholder="请输入小名单"
					style="width: 120px; text-align: center; line-height: 18px;"
					name="name" id="insert-name">&nbsp;&nbsp;<label
					id="lb-name"></label>
			</div>
		</div>
	</form>
</body>
</html>