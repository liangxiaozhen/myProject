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
	 *更新
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
</script>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class=" column">
				<h3>短信内容模板设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
				</form>
				<table class="table table-hover">
					<tr class="text-center" style="background: #ccc;">
						<td>编号</td>
						<td>业务名称</td>
						<td>短信内容</td>
						<td>可用变量</td>
						<td>备注</td>
						<td>操作</td>
					</tr>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.name }</td>
								<td>${item.content }</td>
								<td>${item.variable }</td>
								<td>${item.remark }</td>
								<td><button class="btn btn-default" class="btn btn-default"
										data-toggle="modal" data-target="#updateModal" id="update-btn"
										data-backdrop="static"
										onclick="forwardUpdateUI('${item.id}') ">编辑</button></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>