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
</style>
<script type="text/javascript">
	$(function() {
		$("#query-proxyGrade").val("${agentGradePromoAuth.proxygrade}");
		$("#query-thirdPartyName").val("${agentGradePromoAuth.thirdpartyname}");
	})
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		}
		$.post(action, null, callback);
	}
	function insert() {
		if (validateInsert()) {
			var action = "insert.action";
			var callback = function(data) {
				if (data == "success") {
					queryAllPerson("${pagehelper.pageNum}",
							"${pagehelper.pageSize}");
					alert("提示：操作成功。");
				} else if (data == "fail") {
					alert("提示：操作失败，第三方公司名称已存在");
				}
			}
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	function forwardDelUI(id, name) {
		var action = "del_UI.action";
		var params = {
			"id" : id,
			"thirdPartyName" : name
		};
		var callback = function(data) {
			$("#del-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	function delByID() {
		var action = "del.action";
		var params = {
			"id" : $("#del-id").val(),
			"thirdPartyName" : $("#del-name").val()
		};
		var callback = function(data) {
			queryAllPerson("${pagehelper.pageNum}", "${pagehelper.pageSize}");
			alert(data);
		};
		$.post(action, params, callback, 'json');
	}
	function forwardCancelUI(id, count) {
		$("#cancelID").val(id);
		$("#cancel_count_lb").html(count);
	}
	function forwardIsUseUI(id, count) {
		$("#isUseID").val(id);
		$("#isUse_count_lb").html(count);
	}
	function cancelUse() {
		var id = $("#cancelID").val();
		var action = "cancel.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			queryAllPerson("${pagehelper.pageNum}", "${pagehelper.pageSize}");
			alert(data);
		};
		$.post(action, params, callback, 'json');
	}
	function isUse() {
		var id = $("#isUseID").val();
		var action = "isUse.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			queryAllPerson("${pagehelper.pageNum}", "${pagehelper.pageSize}");
			alert(data);
		};
		$.post(action, params, callback, 'json');
	}
	function forwardUpdateUI(id) {
		var action = "update_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	function update() {
		if (validateUpdate()) {
			var action = "update.action";
			var callback = function(data) {
				if (data == "success") {
					queryAllPerson("${pagehelper.pageNum}",
							"${pagehelper.pageSize}");
					alert("提示：操作成功。");
				} else if (data == "fail") {
					alert("提示：操作失败，第三方公司名称已存在");
				}
			}
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>第三方推广设置</h3>
				<br>
				<form id="form-select" action="queryAll.action" method="post">
					<input type="hidden" name="pageSize" id="pageSize"> <input
						type="hidden" name="pageNum" id="pageNum"> 第三方公司名称：<select
						id="query-thirdPartyName" name="thirdpartyname">
						<option value="">--请选择--</option>
						<c:forEach items="${nameList }" var="item">
							<option value="${item.thirdpartyname }">${item.thirdpartyname }</option>
						</c:forEach>
					</select> <br>
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
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
							<td>第三方公司链接地址</td>
							<td>第三方公司名称</td>
							<td>允许开关</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.thirdpartycode }</td>
								<td>${item.thirdpartyname }</td>
								<td><c:if test="${item.isopen eq 0 }">
										<button class="btn btn-default" data-backdrop="static"
											data-toggle="modal" data-target="#IsuseModal"
											onclick="forwardIsUseUI('${item.id}','${status.count }')">
											<span class="green">启用</span>
										</button>
									</c:if> <c:if test="${item.isopen eq 1 }">
										<button class="btn btn-default" data-backdrop="static"
											data-toggle="modal" data-target="#CancelModal"
											onclick="forwardCancelUI('${item.id}','${status.count }')">
											<span class="red">停用</span>
										</button>
									</c:if></td>
								<td>
									<div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" data-backdrop="static"
											id="update-btn" onclick="forwardUpdateUI('${item.id}')">编辑</button>
										<button class="btn btn-default" data-toggle="modal"
											data-target="#delModal" data-backdrop="static" id="del-btn"
											onclick="forwardDelUI('${item.id }','${item.thirdpartyname }')">删除</button>
									</div>
								</td>
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
	<!-- 停用  推广链接-->
	<div class="modal fade" id="CancelModal" tabindex="-1" role="dialog"
		aria-labelledby="CancelModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="CancelModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<input type="hidden" id="cancelID" /> 您是否停用序号为 <span
						style="color: red;"><label id="cancel_count_lb"></label></span>
					的第三方设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 启用  推广链接 -->
	<div class="modal fade" id="IsuseModal" tabindex="-1" role="dialog"
		aria-labelledby="IsuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="IsuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<input type="hidden" id="isUseID" /> 您是否启用序号为 <span
						style="color: red;"><label id="isUse_count_lb"></label></span>
					的第三方设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>