<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	$(function() {
		$("#insert-name-select").change(function() {
			if ($(this).val() == "") {
				$("#type_div").hide();
				$("#content_hr").hide();
				$("#insert-type-select").val(-1);
				$("#top_div").hide();
				$("#ugrade_div").hide();
				$("#user_div").hide();
				$("#foot_hr").hide();
				$("#insert-username-text").val("");
			}else{
				$("#type_div").show();
				$("#content_hr").show();
			}
		});
		$("#insert-username-text").change(function() {
			$("#insert-username-lb").html("");
		});
		$("#insert-nametype-select").change(function() {
			$("#insert-nametype-lb").html("");
		});
		$("#insert-type-select").change(function() {
			$("#insert-username-lb").html("");
			$("#insert-ugrade-select").val(-1);
		});
		$("#ugrade_div").hide();
		$("#user_div").hide();
		$("#top_div").hide();
		$("#foot_hr").hide();
		$("#type_div").hide();
		$("#content_hr").hide();
	})
	function validateInsertForm() {
		var $nametype = $("#insert-nametype-select").val();
		if ($nametype == '' || $nametype == null) {
			$("#insert-nametype-lb").html("<font color='red'>* 请选择大名单</font>");
			return false;
		}
		var $name = $("#insert-name-select").val();
		if ($name == '' || $name == null) {
			$("#insert-name-lb").html("<font color='red'>* 请选择小名单</font>");
			return false;
		}
		var $type = $("#insert-type-select").val();
		if ($type != 2) {
			var $username = $("#insert-username-text").val();
			if ($username == '' || $username == null) {
				$("#insert-username-lb").html(
						"<font color='red'>* 请输入用户名</font>");
				return false;
			}
		}
		return true;
	}
	//子目录获取
	function nameChange() {
		var nameType = $("#insert-nametype-select").val();
		var action = "queryRemoveName.action";
		var params = {
			"nameType" : nameType
		}
		var callback = function(data) {
			var names = document.getElementById("insert-name-select");
			names.options[0] = new Option("---请选择---", '');
			names.options[0].selected = true;
			for (var i = 0; i < data.length; i++) {
				names.options[names.length] = new Option(data[i].name,
						data[i].name);
			}
		}
		document.getElementById("insert-name-select").length = 0;
		$.post(action, params, callback, 'json');
	}
	//选择方式
	function typeChange(obj) {
		var value = obj.value;
		$("#user_div").hide()
		$("#ugrade_div").hide();
		$("#foot_hr").hide();
		$("#top_div").hide();
		$("#username").val("");
		if (value == 1) {
			$("#user_div").show();
		}
		if (value == 2) {
			$("#ugrade_div").show();
		}
	}
	//用户名
	function userChange(event, obj) {
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		if (obj.value != "") {
			var nameType = $("#insert-nametype-select").val();
			var name = $("#insert-name-select").val();
			var userName = obj.value;
			var action = "getUser.action";
			var params = {
				"nametype" : nameType,
				"name" : name,
				"loginname" : userName
			};
			$.post(action, params, function(data) {
				$("#content_div").html(data);
			});
		} else {
			$("#top_div").hide();
			$("#foot_hr").hide();
		}
	}
	//会员等级
	function uGradeChange(obj) {
		var ugrade = obj.value;
		if (ugrade != "") {
			var nameType = $("#insert-nametype-select").val();
			var name = $("#insert-name-select").val();
			var action = "getUgrade.action";
			var params = {
				"nameType" : nameType,
				"name" : name,
				"uGrade" : ugrade
			};
			$.post(action, params, function(data) {
				$("#content_div").html(data);
			});
		} else {
			$("#top_div").hide();
			$("#foot_hr").hide();
		}
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>名单设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				大名单：&nbsp;&nbsp;&nbsp; <select name="nametype"
					id="insert-nametype-select" onchange="nameChange()">
					<option value="">---请选择---</option>
					<c:forEach var="item" items="${nameTypes }">
						<option value="${item.nametype }">${item.nametype }</option>
					</c:forEach>
				</select><label id="insert-nametype-lb"></label>
			</div>
			<div class="col-md-6 ">
				小名单：&nbsp;&nbsp;&nbsp; <select name="name" id="insert-name-select">
					<option value="">---请选择---</option>
				</select> &nbsp; <label id="insert-name-lb"></label>
			</div>
		</div>
		<hr>
		<div id="type_div" class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				选择方式 ：&nbsp;&nbsp;&nbsp; <select onchange="typeChange(this)"
					id="insert-type-select">
					<option value=-1>---请选择---</option>
					<option value=1>用户名</option>
					<option value=2>会员等级</option>
				</select> &nbsp; <label id="insert-type-lb"></label>
			</div>
			<div id="user_div" class="col-md-6">
				<div>
					查询用户名 ：&nbsp;&nbsp;&nbsp;<input onkeyup="userChange(event,this)"
						placeholder="用户名" style="text-align: center; line-height: 18px;"
						id="insert-username-text" type="text" /> &nbsp; <label
						id="insert-username-lb"></label>
				</div>
				<div style="margin-top: 20px;">
					<font color='red'>注：只查询当前小名单不存在用户！</font>
				</div>
			</div>
			<div id="ugrade_div" class="col-md-6">
				<div class="col-md-10">
					会员等级：&nbsp;&nbsp;&nbsp;&nbsp;<select id="insert-ugrade-select"
						onchange="uGradeChange(this)">
						<option value=-1>---请选择---</option>
						<c:forEach items="${ uGrades}" var="item">
							<option value=${item.ugrade }>${item.ugradename }</option>
						</c:forEach>
					</select> <label id="insert-ugrade-lb"></label>
				</div>
			</div>
		</div>
		<hr id="content_hr">
		<div id="top_div" style="height: 300px;">
			<div id="content_div"
				style="position: absolute; height: 300px; width: 560px; overflow: auto"
				class="col-md-10"></div>
		</div>
		<hr id="foot_hr">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control" name="remark"
					id="insert-remark-text"></textarea>
			</div>
		</div>
	</form>
</body>
</html>