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
		$("#update-name-select").val("${removename.name}");
		$("#update-nametype-select").val("${removename.nametype}");
		$("#update-nametype-select").change(function() {
			$("#update-name-lb").html("");
		});
	})
	function changeSelect() {
		var nameType = $("#update-nametype-select").val();
		var action = "queryRemoveName.action";
		var params = {
			"nameType" : nameType
		}
		var callback = function(data) {
			var names = document.getElementById("update-name-select");
			//names.options[0].selected = true;
			for (var i = 0; i < data.length; i++) {
				names.options[names.length] = new Option(data[i].name,
						data[i].name);
			}
		}
		document.getElementById("update-name-select").length = 0;
		$.post(action, params, callback, 'json');
	}

	function validateUpdateForm() {
		var nameVal = $("#update-name-select").val();
		if (nameVal == '' || nameVal == null) {
			$("#update-name-lb").html("<font color='red'>* 请选择名单名称</font>");
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
				<font color="red"><b>名单编辑</b></font> <input type="hidden" name="id"
					value="${removename.id }">
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-6 col-md-offset-1">
				用 户 名 ： &nbsp;&nbsp;<label>${removename.loginname}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-6 col-md-offset-1">
				真实姓名： &nbsp;&nbsp;<label>${removename.realname}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				大 名 单 ：&nbsp;&nbsp;&nbsp; <select name="nametype"
					id="update-nametype-select" onchange="changeSelect()">
					<c:forEach items="${nameTypes }" var="item">
						<option value="${item.nametype }">${item.nametype }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				小 名 单 ：&nbsp;&nbsp;&nbsp; <select name="name"
					id="update-name-select">
					<c:forEach items="${names}" var="item">
						<option value="${item.name}">${item.name}</option>
					</c:forEach>
				</select>&nbsp;&nbsp; <label id="update-name-lb"></label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="update-remark-text">${removename.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>