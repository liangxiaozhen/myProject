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
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}

a:link {
	font-size: 12px;
	color: blue
}

a:visited {
	font-size: 12px;
	color: blue
}

a:hover {
	font-size: 12px;
	color: red
}
</style>
<script type="text/javascript">
	$(function() {
		$("#ugrade-select").val("${ugrade}");
		$("#loginname-text").val("${loginname}");
		$("#realname-text").val("${realname}");
	});

	/*
	 * 跳转编辑界面
	 */
	function forwardUpdateUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	/*
	 *编辑
	 */
	function update() {
		var action = "update.action";
		var callback = function(data) {
			if (data == "success") {
				alert("提示：操作成功。");
				queryAllPerson('${pagehelper.pageNum}', '');
			}
			if (data == "fail") {
				alert("提示：操作失败。");
			}
		};
		$.post(action, $("#update-form").serialize(), callback, 'json');
	}

	/*
	 *	全选/全不选
	 */
	function selectAll(obj) {
		var checkboxs = document.getElementsByName("checkid");
		for (i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
	}

	/*
	 * 批量编辑UI
	 */
	function batchUpdateForUI() {
		var checkboxs = $("input[name='checkid']:checked");
		var size = checkboxs.size();
		if (size > 0) {
			if (size != 1) {
				var ids = "";
				for (i = 0; i < size; i++) {
					ids += checkboxs[i].value + ",";
				}
				var action = "batchUpdateForUI.action";
				var params = {
					"ids" : ids
				};
				$.post(action, params, function(data) {
					$("#batchUpdateModal").modal({
						backdrop : 'static'
					}).modal('show');
					$("#batchUpdate-modal-body").html(data);
				})
			} else {
				$("#updateModal").modal({
					backdrop : 'static'
				}).modal('show');
				forwardUpdateUI(checkboxs[0].value);
			}
		}
	}
	/*
	 *批量编辑  
	 */
	function batchUpdate() {
		if (validateUpdateForm()) {
			var action = "batchUpdate.action";
			var callback = function(data) {
				if (data == "success") {
					alert("提示：操作成功。");
					queryAllPerson('${pagehelper.pageNum}', '');
				}
				if (data == "fail") {
					alert("提示：操作失败。");
				}
			};
			$
					.post(action, $("#batchUpdate-form").serialize(), callback,
							'json');
		}
	}
</script>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>用户等级编辑</h3>
				<br>
				<form id="form-select" method="post" action="list.action">
					<input type="hidden" id="pageSize" name="pageSize" /> <input
						type="hidden" id="pageNum" name="pageNum" /> 用户名：<input
						type="text" id="loginname-text" name="loginname"
						style="text-align: center;" /> &nbsp;&nbsp; 真实姓名：<input
						type="text" id="realname-text" name="realname"
						style="text-align: center;" /> &nbsp;&nbsp; 会员等级：<select
						id="ugrade-select" name="ugrade">
						<option value="">---请选择等级---</option>
						<c:forEach items="${userGradeList }" var="userGrade">
							<option value="${userGrade.id }">${userGrade.ugradename }</option>
						</c:forEach>
					</select> <br>
					<br>
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
				</form>
				<div align="right" style="width: 100%">
					<button style="margin-right: 50px;" class="btn btn-default"
						onclick="batchUpdateForUI()">批量编辑</button>
				</div>
				<table class="table table-hover table-condensed">
					<thead>
						<tr class="text-center" style="background: #ccc">
							<td><input type="checkbox" onclick="selectAll(this)"></td>
							<td>序号</td>
							<td>用户名</td>
							<td>真实姓名</td>
							<td>会员等级</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td><input name="checkid" type="checkbox"
									value="${item.id }"></td>
								<td>${status.count }</td>
								<td>${item.loginname }</td>
								<td>${item.realname}</td>
								<td>${item.uasi.ugradenamestr }</td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#updateModal" id="update-btn"
										data-backdrop="static" onclick="forwardUpdateUI('${item.id}')">编辑</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="./../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>