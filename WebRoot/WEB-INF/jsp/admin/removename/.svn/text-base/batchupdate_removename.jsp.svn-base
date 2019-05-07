<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function() {
	$("#batchUpdate-name-select").change(function() {
		$("#batchUpdate-name-lb").html("");
	});
	$("#batchUpdate-nametype-select").change(function() {
		$("#batchUpdate-nametype-lb").html("");

	});
})
function validateUpdateForm() {
	var $nametype = $("#batchUpdate-nametype-select").val();
	if ($nametype == '' || $nametype == null) {
		$("#batchUpdate-nametype-lb").html("<font color='red'>* 请选择大名单</font>");
		return false;
	}
	var $name = $("#batchUpdate-name-select").val();
	if ($name == '' || $name == null) {
		$("#batchUpdate-name-lb").html("<font color='red'>* 请选择小名单</font>");
		return false;
	}
	return true;
}
//
function change() {
	var nameType = $("#batchUpdate-nametype-select").val();
	var action = "getName.action";
	var params = {
		"nameType" : nameType
	}
	var callback = function(data) {
		var names = document.getElementById("batchUpdate-name-select");
		names.options[0] = new Option("---请选择---", '');
		names.options[0].selected = true;
		for (var i = 0; i < data.length; i++) {
			names.options[names.length] = new Option(data[i].name,
					data[i].name);
		}
	}
	document.getElementById("batchUpdate-name-select").length = 0;
	$.post(action, params, callback, 'json');
}
</script>
</head>
<body>
	<form id="batchUpdate-form">
		<input id="batchUpdate-ids" name="ids" type="hidden" value="${ids }">
		<center>
			总共选择了 <font color='red'><label>${count }</label></font> 条记录。
		</center>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				大名单：&nbsp;&nbsp;&nbsp; <select name="nametype"
					id="batchUpdate-nametype-select" onchange="change()">
					<option value="">---请选择---</option>
					<c:forEach var="item" items="${nameTypes }">
						<option value="${item.nametype }">${item.nametype }</option>
					</c:forEach>
				</select> <label id="batchUpdate-nametype-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				小名单：&nbsp;&nbsp;&nbsp; <select name="name"
					id="batchUpdate-name-select">
					<option value="">---请选择---</option>
				</select> &nbsp; <label id="batchUpdate-name-lb"></label>
			</div>
		</div>
	</form>
</body>
</html>