<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="<%=basePath%>calendar/WdatePicker.js"></script>



<title>spring + springmvc + mybatis Demo -- personList</title>
<script type="text/javascript">
	$(function() {
		/*
		 * 查询所有人
		 */
		$("#query_all_person").click(function() {
			var pageNum = "1";
			var pageSize = "20";
			queryAllPerson(pageNum, pageSize);
		});
		/*
		 * 增加人
		 */
		$("#add_person").click(function() {
			$("#edit_area_div").html("数据正在玩命加载中......");
			$("#edit_area_div").load("jsp/insertPerson.jsp");
		});
	});
	/*
	 * 分页查询所有人
	 */
	function queryAllPerson(pageNum, pageSize){
		
		var name = $("#name").val();
		var usersalary = $("#usalary").val();
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		//var action = "user/query.action?pageNum="+pageNum+"&pageSize="+pageSize+"&name="+name+"&usersalary="+usersalary;
		var f = document.getElementById('testForm');
		f.action =  "user/query.action";
		//alert(f.action);
		f.submit();
	}
	
	/*
	 * 点击编辑按钮执行此函数
	 * 	函数功能：把选中的一行改为可编辑状态
	 */
	function editFun(id) {
		$("#personList_table td input").attr("disabled", true);

		$("#personList_table td #userName_" + id).attr("disabled", false);
		$("#personList_table td #userSalary_" + id).attr("disabled", false);
		$("#personList_table td #userBirthday_" + id).attr("disabled", false);
	}
	/*
	 * 点击修改按钮执行此函数
	 * 	函数功能：提交编辑后的内容	ajax请求
	 */
	function modifyFun(id) {
		var name = $("#userName_" + id).val();
		var age = $("#userSalary_" + id).val();
		var birthday = $("#userBirthday_" + id).val();
		var action = "user/update.action";
		var params = {
			"id" : id,
			"userName" : userName,
			"userSalary" : userSalary,
			"userBirthday" : userBirthday
		};
		$.post(action, params, modifyPerson_success_callback);
	}
	/*
	 * 修改成功后的回调函数
	 */
	function modifyPerson_success_callback(returnData) {
		$("#personList_table td input").attr("disabled", true);

		//alert(returnData);
		var obj = $.parseJSON(returnData);
		alert(obj.result);
	}
	/*
	 * 点击删除按钮执行此函数
	 * 	函数功能：提交	ajax请求
	 */
	function deleteFun(id) {
		var action = "user/delete.action";
		var params = {
			"id" : id
		};
		$.post(action, params, deletePerson_success_callback);
	}
	/*
	 * 删除成功后的回调函数
	 */
	function deletePerson_success_callback(returnData) {
		//alert(returnData);
		var obj = $.parseJSON(returnData);
		if (obj.result == "success") {
			$("#person_tr_" + obj.personId).remove();
		} else {
			alert(obj.result);
		}
	}
</script>
</head>

<body>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3>All Person</h3>

				<form action="" id="testForm">
					<button id="query_all_person">query all person</button>
					name:<input type="text" id="name" name="name" value="${name}" />
					user_salary:<input type="text" id="usalary" name="usalary"
						value="${usalary}" /> <input type="hidden" id="pageNum"
						name="pageNum" value="" /> <input type="hidden" id="pageSize"
						name="pageSize" value="" /> <br /> <br />
					<table class="table" id="personList_table">
						<thead>
							<tr>
								<td>序号</td>
								<td>ID</td>
								<td>姓名</td>
								<td>薪水</td>
								<td>生日</td>
								<td>编辑</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagehelper.list}" var="user"
								varStatus="status">

								<tr id="person_tr_${user.id }">
									<td>${ status.index + 1}</td>
									<td><input type="text" id="id_${user.id }" name="id"
										value="${user.id }" disabled /></td>
									<td><input type="text" id="userName_${user.id }"
										name="userName" value="${user.userName }" disabled /></td>
									<td><input type="text" id="userSalary_${user.id }"
										name="userSalary" value="${user.userSalary }" disabled /></td>
									<td><input type="text" id="userBirthday_${user.id }"
										name="userBirthday" class="Wdate"
										value="${user.userBirthdayStr}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										disabled readOnly /></td>
									<td>
										<button id="edit_btn" onclick="editFun('${user.id }');">编辑</button>
										<button id="modify" onclick="modifyFun('${user.id }');">修改</button>
										<button id="modify" onclick="deleteFun('${user.id }');">删除</button>
									</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<div id="page_div">
						<%@ include file="./../common/pagehelper.jsp"%>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
