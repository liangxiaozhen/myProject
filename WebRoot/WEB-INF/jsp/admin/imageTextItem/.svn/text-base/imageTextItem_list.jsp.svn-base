<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
</style>
<script type="text/javascript">
	$(function() {
		$(".remark-p").each(function() {
			var num = $(this).html();
			if (num.length > 5) {
				$(this).html(num.substr(0, 5) + "...");
			}
		});
	});
	/*
	 * 新增页面
	 */
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		};
		$.post(action, null, callback);
	}
	/**
	 * 编辑页面
	 */
	function forwardUpdateUI(id) {
		var action = "update_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	/*
	 * 删除页面
	 */
	function forwardDelUI(id) {
		var action = "del_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#del-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	/*
	 *新增
	 */
	function insert() {
		var name = $("#insert-itiname-text").val();
		var action = "checkITIName.action";
		var params = {
			"itiname" : name
		};
		var callback = function(data) {
			$("#insert-itiname-lb").html("");
			if (data != null && data.indexOf("exist") >= 0) {
				$("#insert-itiname-lb").html("*该项目名称已经存在");
				return;
			}
			if (name == null || name == "") {
				$("#insert-itiname-lb").html("*必须填写项目名称");
				$("#insert-itiname-text").focus();
				return;
			}
			if (name.length > 15) {
				$("#insert-itiname-lb").html("*长度限制15字数以内");
				$("#insert-itiname-text").focus();
				return;
			}
			/*
			 * 校验后新增
			 */
			var action = "insert.action";
			var callback = function(data) {
				returnDate(data);
			}
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		};
		$.post(action, params, callback, 'json');
	}

	/*
	 * 编辑
	 */
	function update() {
		if (validateUpdate()) {
			var action = "update.action";
			var callback = function(data) {
				if (data == "success") {
					alert("提示：操作成功！");
					queryAllPerson('${pagehelper.pageNum}', '');
				}
				if (data == "fail") {
					alert("提示：操作失败！")
				}
				if (data == "exsit") {
					$("#update-itiname-text").focus();
					$("#update-itiname-lb").html("*项目名称已存在。");
				}
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 删除
	 */
	function delByID() {
		var action = "delete.action";
		var params = {
			"id" : $("#del-id").val()
		};
		var callback = function(data) {
			returnDate(data);
		}
		$.post(action, params, callback, 'json');
	}
	/*
	 * 回调
	 */
	function returnDate(data) {
		if (data == "success") {
			alert("提示：操作成功！");
			queryAllPerson('${pagehelper.pageNum}', '');
		}
		if (data == "fail") {
			alert("提示：操作失败！")
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>图文项目设置</h3>
				<br>
				<form id="form-select" method="post" action="list.action">
					<input type="hidden" id="pageNum" name="pageNum"> <input
						type="hidden" id="pageSize" name="pageSize">
				</form>
				<div align="right">
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#insertModal" id="add-btn" data-backdrop="static"
						onclick="forwardInsertUI()">新增</button>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>ID</td>
							<td>项目名称</td>
							<td>是否可用</td>
							<td>排列顺序</td>
							<td>添加时间</td>
							<td>设置人</td>
							<td>操作</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count}</td>
								<td>${item.id }</td>
								<td>${item.itiname }</td>
								<td><c:if test="${item.isuse eq 0}">
										<span style="color: red;">不可用</span>
									</c:if> <c:if test="${item.isuse eq 1}">
										<span style="color: green;">可用</span>
									</c:if></td>
								<td><c:if test="${item.sort eq  0 }">
										正序</c:if> <c:if test="${item.sort eq  1 }">
										反序</c:if></td>
								<td><fmt:formatDate value="${item.addtime }"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${item.addman }</td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#updateModal" data-backdrop="static"
										onclick="forwardUpdateUI('${item.id}')">编辑</button>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#delModal" data-backdrop="static"
										onclick="forwardDelUI('${item.id}')">删除</button></td>
								<td><p class="remark-p text-center" title="${item.remark }">${item.remark }</p></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>