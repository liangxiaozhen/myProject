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
	text-align: center;
	vertical-align: middle !important;
	border: 1px solid #666;
}
</style>

</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>提现限制设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>会员等级：
					</label> <select id="select-ugrade" name="ugrade">
						<option value="">---请选择---</option>
						<c:forEach items="${ugrades}" var="item">
							<option value="${item.ugrade }">${item.ugradename}</option>
						</c:forEach>
					</select> &nbsp;&nbsp;&nbsp;<label>是否可以取消提现： </label> <select
						name="iscancel" id="select-iscancel">
						<option value=>---请选择---</option>
						<option value=1>是</option>
						<option value=2>否</option>
					</select>&nbsp;&nbsp;&nbsp;<label>分段金额：</label><input type="text"
						style="text-align: center;" size="8" name="lowestmoney"
						id="select-lowestmoney" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)">&nbsp;&nbsp;-&nbsp;&nbsp;<input
						size="8" type="text" style="text-align: center;"
						name="highestmoney" id="select-highestmoney"
						onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"><br />
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
				</form>
				<div align="right">
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#insertModal" id="add-btn" data-backdrop="static"
						onclick="forwardInsertUI()">新增</button>
				</div>
				<table class="table table-hover table-condensed">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td rowspan="2">序号</td>
							<td rowspan="2">会员等级</td>
							<td colspan="2">单笔金额限制</td>
							<td rowspan="2">日提现金额限制</td>
							<td rowspan="2">日提现次数限制</td>
							<td rowspan="2">详情</td>
							<td rowspan="2">操作</td>
						</tr>
						<tr class="text-center" style="background: #ccc;">
							<td>最低金额</td>
							<td>最高金额</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td><p class="remark-p text-center tzui-tips"
										title="${item.ugradeStr }">${item.ugradeStr }</p></td>
								<td><fmt:formatNumber pattern="###,##0.00"
										value="${item.lowestmoney }" /></td>
								<td><fmt:formatNumber pattern="###,##0.00"
										value="${item.highestmoney }" /></td>
								<td><fmt:formatNumber pattern="###,##0.00"
										value="${item.daymoneyrest }" /></td>
								<td>${item.daytimesrest }</td>
					
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">查看</button></td>
								<td>
									<div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" data-backdrop="static"
											id="update-btn" onclick="forwardUpdateUI('${item.id}')">编辑</button>
										<button class="btn btn-default" data-toggle="modal"
											data-target="#delModal" data-backdrop="static" id="del-btn"
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
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>


	<script type="text/javascript">
		$(function() {
			$("#select-ugrade").val("${wdcRstr.ugrade}");
			$("#select-iscancel").val("${wdcRstr.iscancel}");
			$("#select-lowestmoney").val("${wdcRstr.lowestmoney}");
			$("#select-highestmoney").val("${wdcRstr.highestmoney}");
		});
		function forwardDetailUI(id) {// 跳转详情页面
			var action = "queryById.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#detail-modal-body").html(data);
			}
			$.post(action, params, callback);
		}

		function forwardUpdateUI(id) {// 跳转编辑页面
			var action = "update_UI.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#update-modal-body").html(data);
			}
			$.post(action, params, callback);
		}
		function forwardInsertUI() {// 跳转新增页面
			var action = "insert_UI.action";
			var callback = function(data) {
				$("#insert-modal-body").html(data);
			}
			$.post(action, null, callback);
		}

		function forwardDelUI(id) {// 跳转 删除 UI
			var action = "del_UI.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#del-modal-body").html(data);
			};
			$.post(action, params, callback);
		}

		function update() {// 编辑更新
			if (validateUpdateForm()) {
				var action = "update.action"
				var callback = function(data) {
					alert(data);
					if (data.indexOf("成功") > 0) {
						queryAllPerson("", "");
					}
				}
				$.post(action, $('#update-form').serialize(), callback, 'json');
			}
		}

		function insert() {// 保存
			if (validateForm()) {
				var action = "insert.action"
				var callback = function(data) {
					alert(data);
					if (data.indexOf("成功") > 0) {
						queryAllPerson("", "");
					}
				}
				$.post(action, $('#insert-form').serialize(), callback, 'json');
			}
		}

		function delByID() {// 删除
			var id = $("#del-id").val();
			var action = "delete.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				alert(data);
				if (data.indexOf("成功") > 0) {
					queryAllPerson("", "");
				}
			};
			$.post(action, params, callback, 'json');
		}
		function show(id) {// 详情DIV 显示 隐藏
			$("#" + id).toggle();
		}
	</script>
</body>
</html>