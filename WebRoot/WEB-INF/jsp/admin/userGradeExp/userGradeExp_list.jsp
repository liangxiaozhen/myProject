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
		$("#query-ugradename-select").val("${userGradeExp.ugrade}");
	})
	/*
	 * 新增UI
	 */
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		}
		$.post(action, null, callback);
	}
	/*
	 *新增
	 */
	function insert() {
		if (validateInsert()) {
			var action = "insert.action";
			var callback = function(data) {
				if (data == "success") {
					queryAllPerson('${pagehelper.pageNum}', '');
					alert("提示：操作成功。");
				}
				if (data == "fail") {
					alert("提示：操作失败。");
				}
			};
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 编辑UI
	 */
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
	/*
	 * 编辑
	 */
	function update() {
		if (validateUpdate()) {
			var action = "update.action";
			var callback = function(data) {
				alert(data);
				if (data.indexOf("成功") > 0) {
					queryAllPerson('${pagehelper.pageNum}', '');
				}
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 跳转启用界面
	 
	function forwardIsUseUI(id, name) {
		$("#isuse-id").val(id);
		$("#isuse-lb").html(name);
	}*/
	/*
	 * 跳转停用界面
	 
	function forwardCancelUI(id, name) {
		$("#cancel-id").val(id);
		$("#cancel-lb").html(name);
	}*/
	/*
	 *启用会员等级
	
	function isUse() {
		var action = "isUse.action";
		var params = {
			"id" : $("#isuse-id").val()
		};
		var callback = function(data) {
			if (data == "success") {
				alert("提示：操作成功。");
				queryAllPerson('${pagehelper.pageNum}', '');
			}
			if (data == "fail") {
				alert("提示：操作失败,普通会员状态为停用。");
			}
		};
		$.post(action, params, callback, 'json');
	} */
	/*
	 * 停用会员等级
	 
	function cancelUse() {
		var action = "cancel.action";
		var params = {
			"id" : $("#cancel-id").val()
		};
		var callback = function(data) {
			if (data == "success") {
				alert("提示：操作成功。");
				queryAllPerson('${pagehelper.pageNum}', '');
			}
			if (data == "fail") {
				alert("提示：操作失败。");
			}
		};
		$.post(action, params, callback, 'json');
	}*/
	/*
	 * 查看详情UI
	 */
	function forwardDetailUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	/*
	 * 删除UI
	 */
	function forwardDelUI(id) {
		var action = "del_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#del-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	/*
	 *删除
	 */
	function delByID() {
		var action = "del.action";
		var params = {
			"id" : $("#del-id").val()
		};
		var callback = function(data) {
			if (data == "success") {
				queryAllPerson('${pagehelper.pageNum}', '');
				alert("提示：操作成功。");
			}
			if (data == "fail") {
				alert("提示：操作失败。");
			}
		};
		$.post(action, params, callback, 'json');
	}
	/*
	 * 查看体验定向名单
	 */
	function forwardExpSNLUI(isexpspecific, snlid) {
		var action = "queryExpSNL.action";
		var params = {
			"isExpSpecific" : isexpspecific,
			"expSNLID" : snlid
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	/*
	 * 查看会员等级中的用户
	 */
	function forwardNumber(uGrade) {
		var action = "queryUsers.action";
		var params = {
			"ugrade" : uGrade
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>体验会员等级设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum"> <input
						type="hidden" id="pageSize" name="pageSize"> 等级名称： <select
						id="query-ugradename-select" name="ugrade">
						<option value="">--选择等级名称--</option>
						<c:forEach items="${userGradeList }" var="item">
							<option value="${item.ugrade }">${item.ugradename }</option>
						</c:forEach>
					</select><br>
					<button type="submit" class="btn btn-default">查询</button>
					&nbsp;&nbsp;
					<button type="reset" class="btn btn-default">重置</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="button"
						onclick="history.go(-1)">返回</button>
				</form>
				<div align="right">
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#insertModal" id="add-btn" data-backdrop="static"
						onclick="forwardInsertUI()">新增</button>
				</div>
				<table class="table table-hover table-condensed">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>等级名称</td>
							<td>购买金额</td>
							<td>有效期(天)</td>
							<td>会员人数</td>
							<td>定向升级</td>
							<td>状态</td>
							<td>详情</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td><a href="queryAll.action?ugrade=${item.ugrade }">${item.userGrade.ugradename }</a></td>
								<td><fmt:formatNumber pattern="###,###,##0.00"
										value="${item.amount }" /></td>
								<td>${item.expirytime }</td>
								<td><c:if test="${item.number != 0 }">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#detailsModal" id="details-btn"
											data-backdrop="static"
											onclick="forwardNumber('${item.ugrade}')">${item.number }</button>
									</c:if> <c:if test="${item.number eq 0 }">0</c:if></td>
								<td><c:if test="${item.isexpspecific eq 2 }">无</c:if> <c:if
										test="${item.isexpspecific eq 1 }">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#detailsModal" id="details-btn"
											data-backdrop="static"
											onclick="forwardExpSNLUI('${item.isexpspecific}','${item.expsnlid}')">查看</button>
									</c:if></td>
								<td><c:if test="${item.status eq 0 }">
										<span class="red">停用</span>
									</c:if> <c:if test="${item.status eq 1 }">
										<span class="green">启用</span>
									</c:if></td>
								<td>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">查看</button>
								</td>
								<td>
									<div class="btn-group" role="group" aria-label="...">
										<!--  
										<c:if test="${item.status eq 0 }">
											<button class="btn btn-default" data-backdrop="static"
												data-toggle="modal" data-target="#userGradeExpIsUseModal"
												onclick="forwardIsUseUI('${item.id}','${item.userGrade.ugradename}')">
												<span class="green">启用</span>
											</button>
										</c:if>
										<c:if test="${item.status eq 1 }">
											<button class="btn btn-default" data-backdrop="static"
												data-toggle="modal" data-target="#userGradeExpCancelModal"
												onclick="forwardCancelUI('${item.id}','${item.userGrade.ugradename}')">
												<span class="red">停用</span>
											</button>
										</c:if>
										-->
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}') ">编辑</button>
										<button class="btn btn-default" data-toggle="modal"
											data-target="#delModal" id="del-btn" data-backdrop="static"
											onclick="forwardDelUI('${item.id}')">删除</button>
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
	<!-- 启用   模态框
	<div class="modal fade" id="userGradeExpIsUseModal" tabindex="-1"
		role="dialog" aria-labelledby="userGradeExpIsUseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userGradeExpIsUseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="isuse-id"> 您是否启用体验会员<label
							id="isuse-lb" style="color: red;"></label> ？
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div> -->
	<!-- 停用   模态框
	<div class="modal fade" id="userGradeExpCancelModal" tabindex="-1"
		role="dialog" aria-labelledby="userGradeExpCancelModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userGradeExpCancelModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="cancel-id"> 您是否停用体验会员<label
							id="cancel-lb" style="color: red;"></label> ？
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div> -->
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>