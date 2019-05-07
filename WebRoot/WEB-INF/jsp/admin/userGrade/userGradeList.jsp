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
		$("#query-rankno-select").val("${userGrade.rankno}");
		$("#query-ugradename-select").val("${userGrade.ugradename}");
		$("#query-isspecify-select").val("${userGrade.isspecify}");
		$("#query-status-select").val("${userGrade.status}");
	});
	/*
	 * 跳转新增页面
	 */
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		};
		$.post(action, null, callback);
	}
	/*
	 *新增会员等级
	 */
	function insert() {
		if (validateInsert()) {
			var action = "insert.action";
			var callback = function(data) {
				if (data == "success") {
					alert("提示：操作成功！");
					queryAllPerson('${pagehelper.pageNum}', '');
				}
				if (data == "fail") {
					alert("提示：操作失败！")
				}
			};
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 启用绑定ID和会员名称 
	
	function forwardIsUseUI(id, name) {
		$("#isuse-id").val(id);
		$("#isuse-lb").html(name);
	} */
	/*
	 * 停用绑定ID和会员名称 
	
	function forwardCancelUI(id, name) {
		$("#cancel-id").val(id);
		$("#cancel-lb").html(name);
	} */
	/*
	 * 废弃绑定ID和会员名称 
	
	function forwardStopUI(id, name) {
		$("#stop-id").val(id);
		$("#stop-lb").html(name);
	} */
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
				alert("提示：操作失败。");
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
	 * 废弃会员等级
	
	function stop() {
		var action = "stop.action";
		var params = {
			"id" : $("#stop-id").val()
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
	} */
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
	 *编辑
	 */
	function update() {
		if (validate()) {
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
	}
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
	 * 定向查看UI
	 */
	function forwardSNLUI(id) {
		var action = "querySNL.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
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
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>正常会员等级</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> 排列序号：<select
						id="query-rankno-select" name="rankno">
						<option value="">--请选择--</option>
						<c:forEach items="${queryList }" var="item">
							<option value="${item.rankno}">${item.rankno}</option>
						</c:forEach>
					</select> &nbsp;&nbsp; 等级名称：<select id="query-ugradename-select"
						name="ugradename">
						<option value="">--请选择--</option>
						<c:forEach items="${queryList}" var="item">
							<option value="${item.ugradename}">${item.ugradename}</option>
						</c:forEach>
					</select> &nbsp;&nbsp; 定向升级：<select id="query-isspecify-select"
						name="isspecify">
						<option value="">--请选择--</option>
						<option value="1">定向升级</option>
						<option value="2">不定向升级</option>
					</select> &nbsp;&nbsp; 状态：<select id="query-status-select" name="status">
						<option value="">--请选择--</option>
						<option value="0">停用</option>
						<option value="1">启用</option>
					</select> <br>
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="button"
						onclick="history.go(-1)">返回</button>
				</form>
				<div style="width: 150px; margin-left: 1200px; margin-top: -45px;">
					<ol class="breadcrumb">
						<li class="active">正常等级</li>
						<li><a href="queryAllStop.action">废弃等级</a></li>
					</ol>
				</div>
				<div align="right" style="margin-top: -15px;">
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#insertModal" id="add-btn" data-backdrop="static"
						onclick="forwardInsertUI()">新增</button>
				</div>
				<table class="table table-hover table-condensed">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>排列序号</td>
							<td>等级名称</td>
							<td>积分购买</td>
							<td>现金购买</td>
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
								<td>${item.rankno }</td>
								<td><a
									href="queryAll.action?ugradename=${item.ugradename }">${item.ugradename }</a></td>
								<td>${item.needpoints }</td>
								<td><fmt:formatNumber pattern="###,###,##0.00" value="${item.needamount }"/></td>
								
								<td><c:if test="${item.number != 0 }">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#detailsModal" id="details-btn"
											data-backdrop="static"
											onclick="forwardNumber('${item.ugrade}')">${item.number }</button>
									</c:if> <c:if test="${item.number eq 0 }">0</c:if></td>
								<td><c:if test="${item.isspecify eq 2 }">无</c:if> <c:if
										test="${item.isspecify eq 1 }">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#detailsModal" id="details-btn"
											data-backdrop="static" onclick="forwardSNLUI('${item.id}')">查看</button>
									</c:if></td>
								<td><c:if test="${item.status eq 1 }">
										<span style="color: green;">启用</span>
									</c:if> <c:if test="${item.status eq 0 }">
										<span style="color: red;">停用</span>
									</c:if></td>
								<td>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">详情</button>
								</td>
								<td><div class="btn-group" role="group" aria-label="...">
									<!--  
										<c:if test="${item.ugrade != 0 }">
											<c:if test="${item.status eq 0 }">
												<div class="btn-group" role="group" aria-label="...">
													<button class="btn btn-default" data-backdrop="static"
														data-toggle="modal" data-target="#userGradeIsUseModal"
														onclick="forwardIsUseUI('${item.id}','${item.ugradename}')">
														<span class="green">启用</span>
													</button>
													<button class="btn btn-default" data-backdrop="static"
														data-toggle="modal" data-target="#userGradeStopModal"
														onclick="forwardStopUI('${item.id}','${item.ugradename}')">
														废弃</button>
												</div>
											</c:if>
											<c:if test="${item.status eq 1 }">
												<button class="btn btn-default" data-backdrop="static"
													data-toggle="modal" data-target="#userGradeCancelModal"
													onclick="forwardCancelUI('${item.id}','${item.ugradename}')">
													<span class="red">停用</span>
												</button>
											</c:if>
										</c:if>-->
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}')">编辑</button>
									</div></td>
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
	<!-- 启用   模态框 
	<div class="modal fade" id="userGradeIsUseModal" tabindex="-1"
		role="dialog" aria-labelledby="userGradeIsUseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userGradeIsUseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="isuse-id"> 您是否启用 <label
							id="isuse-lb" style="color: red;"></label> ？
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->
	<!-- 停用   模态框
	<div class="modal fade" id="userGradeCancelModal" tabindex="-1"
		role="dialog" aria-labelledby="userGradeCancelModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userGradeCancelModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="cancel-id"> 您是否停用 <label
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
	<!-- 废弃   模态框 
	<div class="modal fade" id="userGradeStopModal" tabindex="-1"
		role="dialog" aria-labelledby="userGradeStopModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userGradeStopModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="stop-id"> 您是否废弃 <label
							id="stop-lb" style="color: red;"></label> ？
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" onclick="stop()">废弃</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>
