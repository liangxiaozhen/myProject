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
	//跳转编辑修改页面
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
	//编辑修改
	function update() {
		if (validateUpdate()) {
			var action = "update.action";
			var callback = function(data) {
				if (data == 'fail') {
					alert("提示：操作失败。");
				}
				if (data == 'success') {
					alert("提示：操作成功。")
					queryAllPerson('', '');
				}
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	/*启用UI
	function forwardIsUserUI(id,type) {
		$("#isUseID").val(id);
		$("#isUse_count_lb").html(type);
	}
	//停用UI
	function forwardCancelUserUI(id,type) {
		$("#cancelID").val(id);
		$("#cancel_count_lb").html(type);
	}*/
	//启用用户名规则设置
	function isUse() {
		var id = $("#isUseID").val();
		var action = "isUse.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			queryAllPerson('', '');
			alert(data);
		};
		$.post(action, params, callback, 'json');
	}
	/* 停用
	function cancelUse() {
		var id = $("#cancelID").val();
		var action = "cancel.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			queryAllPerson('', '');
			alert(data);
		};
		$.post(action, params, callback, 'json');
	}*/
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>注册设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">

				</form>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>注册方式</td>
							<td>规则内容</td>
							<td>用户名内容组成</td>
							<td>预设字符</td>
							<td>随机位数限制</td>
							<td>用户名中文</td>
							<td>用户名字数限制</td>
							<td>状态</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="item" varStatus="status">
							<tr class="text-center">
								<td>${item.registerStr }</td>
								<td>${item.setruledetail }</td>
								<td><c:if test="${empty item.namecontent }">无</c:if> <c:if
										test="${!empty item.namecontent }">${item.namecontent }</c:if>
								</td>
								<td><c:if test="${empty item.presetstr }">无</c:if> <c:if
										test="${!empty item.presetstr }">${item.presetstr }</c:if></td>
								<td><c:if test="${empty item.randomlength }">无</c:if> <c:if
										test="${!empty item.randomlength }">${item.randomlength }</c:if>
								</td>
								<td><c:if test="${empty item.ischinese }">无</c:if> <c:if
										test="${item.ischinese eq 1}">允许</c:if> <c:if
										test="${item.ischinese eq 2}">不允许</c:if></td>
								<td><c:if test="${empty item.usernamelength }">无</c:if> <c:if
										test="${!empty item.usernamelength }">${item.usernamelength } ~ ${item.usernamemaxlength }</c:if>
								</td>
								<td><c:if test="${item.isuse eq 0 }">
								<!--  
										<button class="btn btn-default" data-backdrop="static"
											data-toggle="modal" data-target="#userNameRuleIsuseModal"
											onclick="forwardIsUserUI('${item.id}','${item.registerStr}')">
											<span class="green">设置启用</span>
										</button>-->
										<span class="red">停用</span>
									</c:if> <c:if test="${item.isuse eq 1 }">
											<span class="green">启用</span>
									</c:if></td>
								<td><div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}') ">编辑</button>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>

	<!-- 启用  注册设置 
	<div class="modal fade" id="userNameRuleIsuseModal" tabindex="-1"
		role="dialog" aria-labelledby="userNameRuleIsuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userNameRuleIsuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<input type="hidden" id="isUseID" /> 您是否启用 <span
						style="color: red;"><label id="isUse_count_lb"></label></span>
					注册设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->

	<!-- 停用  注册设置
	<div class="modal fade" id="userNameRuleCancelModal" tabindex="-1"
		role="dialog" aria-labelledby="userNameRuleCancelModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userNameRuleCancelModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<input type="hidden" id="cancelID" /> 您是否停用<span
						style="color: red;"><label id="cancel_count_lb"></label></span>
					注册设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->
</body>
</html>