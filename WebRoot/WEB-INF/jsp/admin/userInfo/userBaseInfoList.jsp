<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
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
});
/*
 * 分页查询所有人
 */
function queryAllPerson(pageNum, pageSize){
	
	//var accountnumber = $("#accountnumber").val();
	//var mobilephone = $("#mobilephone").val();
	//var loginname = $("#loginname").val();
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	//var action = "user/query.action?pageNum="+pageNum+"&pageSize="+pageSize+"&name="+name+"&usersalary="+usersalary;
	var f = document.getElementById('userForm');
	f.action =  "userBaseInfoList.action";
	//alert(f.action);
	f.submit();
}

</script>

<title>用户信息列表</title>
</head>
<body style="padding: 10px">
	<div class="container" style="width: 100%;">
		<div class="row clearfix">
			<div class="col-md-12 column">

				<form action="" id="userForm">

					用户号:<input type="text" id="accountnumber" name="accountnumber" value="${uinfo.accountnumber}" /> 
					手机号:<input type="text" id="mobilephone" name="mobilephone" value="${uinfo.mobilephone}" /> 
					登录名:<input type="text" id="loginname" name="loginname" value="${uinfo.loginname}" />
					<input	type="hidden" id="pageNum" name="pageNum" value="" /> 
					<input	type="hidden" id="pageSize" name="pageSize" value="" />
					<button id="query_all_person">查询</button>
					<br /> <br />
					<table class="table table-bordered table-hover">
						<thead>
							<tr
								style="background: #ccc; vertical-align: text-top !important; border: 1px solid #666;">
								<th>序号</th>
								<th>ID</th>
								<th>姓名</th>
								<th>用户号</th>
								<th>手机</th>
								<th>邮箱</th>
								<th>证件号</th>
								<th>注册日期</th>
								<th>登录名</th>
								<th>注册ip</th>
								<th>注册cookie</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagehelper.list}" var="user"
								varStatus="status">

								<tr>
									<td>${status.index + 1}</td>
									<td>${user.id }</td>
									<td>${user.realname }</td>
									<td>${user.accountnumber }</td>
									<td>${user.mobilephone }</td>
									<td>${user.email }</td>
									<td>${user.certificationnumber }</td>
									<td><fmt:formatDate value="${user.regdate}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${user.loginname }</td>
									<td>${user.regip }</td>
									<td>${user.regcookie }</td>
									<td>
										<button id="edit_btn" onclick="editFun('${user.id }');">编辑</button>
										<button id="modify" onclick="modifyFun('${user.id }');">修改</button>
									</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<div id="page_div">
						<%@ include file="./../../common/pagehelper.jsp"%>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
