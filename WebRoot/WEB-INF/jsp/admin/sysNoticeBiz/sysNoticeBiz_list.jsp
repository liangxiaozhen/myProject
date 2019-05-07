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
	 * 显示关闭模态框，并绑定业务名称和ID
	 */
	function forwardCloseUI(id, name) {
		$("#close-bizname-lb").html(name);
		$("#close-id").val(id);
	}
	/*
	 * 显示开启模态框，并绑定业务名称和ID
	 */
	function forwardOpenUI(id, name) {
		$("#open-bizname-lb").html(name);
		$("#open-id").val(id);
	}
	/*
	 * 开启短信发送开关
	 */
	function openSMS() {
		var id = $("#open-id").val();
		var action = "open.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			returnDate(data);
		};
		$.post(action, params, callback, 'json');
	}
	/*
	 * 关闭短信发送开关
	 */
	function closeSMS() {
		var id = $("#close-id").val();
		var action = "close.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			returnDate(data);
		};
		$.post(action, params, callback, 'json');
	}
	/*
	 *跳转编辑页面
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
	 * 更新
	 */
	function update() {
		var action = "update.action";
		var callback = function(data) {
			returnDate(data);
		};
		$.post(action, $('#update-form').serialize(), callback, 'json');
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
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>系统通知业务设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
				</form>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>编号</td>
							<td>业务名称</td>
							<td>短信编号</td>
							<td>定向排除</td>
							<td>通道公司</td>
							<td>发送开关</td>
							<td>备注</td>
							<td>操作</td>
							<td>设置人</td>
							<td>设置时间</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.bizname }</td>
								<td><c:if test="${empty item.smsChannel.key }">默认</c:if> <c:if
										test="${!empty item.smsChannel.key }">${item.smsChannel.key }</c:if></td>
								<td><c:if test="${empty item.snlname }">无</c:if> <c:if
										test="${!empty item.snlname }">${item.snlname }</c:if></td>
								<td>${item.smsChannel.smsccompany }</td>
								<td><c:if test="${item.isopen eq 0}">
										<span style="color: red;">关闭</span>
									</c:if> <c:if test="${item.isopen eq 1}">
										<span style="color: green;">开启</span>
									</c:if></td>
								<td>${item.remark }</td>
								<td>
									<div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}') ">编辑</button>
										<c:if test="${item.isopen eq 0 }">
											<button class="btn btn-default" data-toggle="modal"
												data-target="#openModal" id="del-btn" data-backdrop="static"
												onclick="forwardOpenUI('${item.id}','${item.bizname }')">开启</button>
										</c:if>
										<c:if test="${item.isopen eq 1 }">
											<button class="btn btn-default" data-toggle="modal"
												data-target="#closeModal" id="del-btn"
												data-backdrop="static"
												onclick="forwardCloseUI('${item.id}','${item.bizname }')">关闭</button>
										</c:if>
									</div>
								</td>
								<td>${item.addman }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${item.addtime }" /></td>
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
	<!-- 开启 modal -->
	<div class="modal fade" id="openModal" tabindex="-1" role="dialog"
		aria-labelledby="openModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="openModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行发送开关设置操作
					</h4>
				</div>
				<div class="modal-body" align="center">
					<input type="hidden" id="open-id"> 您是否开启 <font color='red'><label
						id="open-bizname-lb"></label></font> 短信发送开关？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="openSMS()">开启</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 关闭 modal -->
	<div class="modal fade" id="closeModal" tabindex="-1" role="dialog"
		aria-labelledby="closeModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="closeModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行发送开关设置操作
					</h4>
				</div>
				<div class="modal-body" align="center">
					<input type="hidden" id="close-id"> 您是否关闭 <font color='red'><label
						id="close-bizname-lb"></label></font> 短信发送开关？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="closeSMS()">关闭</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>