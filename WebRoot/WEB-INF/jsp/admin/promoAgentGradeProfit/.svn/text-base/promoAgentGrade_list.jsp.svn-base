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
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>推广代理资质等级设置</h3>
				<br>
				<form id="form-select" method="post" action="queryGradeAll.action">
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
							<td>推广级别</td>
							<td>推广资质</td>
							<td>设置人</td>
							<td>设置时间</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${item.proxygrade }</td>
								<td>${item.proxygradename }</td>
								<td>${item.addman }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${item.addtime }" /></td>
								<td><c:if test="${item.proxygrade != 0}">
										<div class="btn-group" role="group" aria-label="...">
											<button class="btn btn-default" data-toggle="modal"
												data-target="#updateModal" id="update-btn"
												data-backdrop="static"
												onclick="forwardUpdateUI('${item.id}') ">编辑</button>
											<button class="btn btn-default" data-toggle="modal"
												data-target="#delModal" id="del-btn" data-backdrop="static"
												onclick="forwardDelUI('${item.id}')">删除</button>
										</div>
									</c:if> <c:if test="${item.proxygrade eq 0}">
										<div class="btn-group" role="group" aria-label="...">
											<div class="btn-group" role="group" aria-label="...">
												<button class="btn btn-default" data-toggle="modal"
													data-target="#updateModal" id="update-btn"
													data-backdrop="static"
													onclick="forwardUpdateUI('${item.id}') ">编辑</button>

											</div>
										</div>
									</c:if></td>
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
	<script type="text/javascript">
		/*
		 *跳转设置UI
		 */
		function forwardInsertUI() {
			var action = "insertGrade_UI.action";
			var callback = function(data) {
				$("#insert-modal-body").html(data);
			}
			$.post(action, null, callback);
		}
		/*
		 * 保存
		 */
		function insert() {
			if (validateForm()) {
				var action = "addPromoAgentGrade.action"
				var callback = function(data) {
					returnData(data);
				}
				$.post(action, $('#insert-form').serialize(), callback, 'json');
			}
		}
		/*
		 *跳转编辑页面
		 */
		function forwardUpdateUI(id) {
			var action = "updateGrade_UI.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#update-modal-body").html(data);
			}
			$.post(action, params, callback);
		}
		/*
		 * 编辑更新
		 */
		function update() {
			if (validateUpdateForm()) {
				var action = "updateGrade.action"
				var callback = function(data) {
					returnData(data);
				}
				$.post(action, $('#updateGrade-form').serialize(), callback,
						'json');
			}
		}
		/*
		 * 跳转 删除 UI
		 */
		function forwardDelUI(id) {
			var action = "delGrade_UI.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#del-modal-body").html(data);
			};
			$.post(action, params, callback);
		}
		/*
		 *删除
		 */
		function delByID() {
			var id = $("#del-id").val();
			var action = "delGrade.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				returnData(data);
			};
			$.post(action, params, callback, 'json');
		}
		function returnData(data){
			alert(data);
			queryAllPerson("${pagehelper.pageNum}","${pagehelper.pageSize}");
		}
		
	</script>
</body>
</html>