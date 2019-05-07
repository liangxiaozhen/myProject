<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</style>
<script type="text/javascript">
	/*
	 * 跳转新增界面
	 */
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		};
		$.post(action, null, callback);
	}
	/*
	 * 跳转编辑页面
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
	 *新增短信通道
	 */
	function insert() {
		if (validateInsert()) {
			var action = "insert.action";
			var callback = function(data) {
				returnDate(data);
			};
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 更新
	 */
	function update() {
		if (validateUpdate()) {
			var action = "update.action";
			var callback = function(data) {
				returnDate(data);
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 跳转删除界面
	 */
	function forwardDelUI(id) {
		var action = "delete_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#del-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	//删除
	function delByID() {
		var id = $("#del-smschannel-id").val();
		var action = "delete.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			returnDate(data);
		};
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
	/*
	 * 跳转详情页面
	 */
	function forwardDetailUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
</script>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class=" column">
				<h3>短信通道设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
				</form>
				<div align="right">
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#insertModal" id="add-btn" data-backdrop="static"
						onclick="forwardInsertUI()">新增</button>
				</div>
				<table class="table table-hover">
					<tr class="text-center" style="background: #ccc;">
						<td>序号</td>
						<td>短信编号</td>
						<td>通道公司</td>
						<td>是否引用</td>
						<td>备注</td>
						<td>操作</td>
						<td>通道注册详情</td>
					</tr>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td><c:if test="${empty item.key }">默认</c:if> <c:if
										test="${!empty item.key }">${item.key }</c:if></td>
								<td>${item.smsccompany }</td>
								<td><c:if test="${item.isuse eq 1 }">
										<span style="color: green">可引用</span>
									</c:if> <c:if test="${item.isuse eq 0 }">
										<span style="color: red">不可引用</span>
									</c:if></td>
								<td>${item.remark }</td>
								<td><div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}') ">编辑</button>
										<button class="btn btn-default" data-toggle="modal"
											data-target="#delModal" id="del-btn" data-backdrop="static"
											onclick="forwardDelUI('${item.id}')">删除</button>
									</div></td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">详情</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>