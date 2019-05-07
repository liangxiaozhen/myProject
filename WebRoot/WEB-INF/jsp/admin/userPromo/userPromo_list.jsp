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
		$(".remark-p").each(function() {
			var num = $(this).html();
			if (num.length > 5) {
				$(this).html(num.substr(0, 5) + "...");
			}
		});
		$("#query-name").val("${userPromo.name}");
		$("#query-loginname").val("${userPromo.loginname}");
		$("#query-proxygrade").val("${userPromo.proxygrade}");
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>用户推广设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum"> <input
						type="hidden" style="text-align: center;" id="pageSize"
						name="pageSize"> 真实姓名：<input type="text"
						style="text-align: center;" name="name" id="query-name">&nbsp;&nbsp;
					用户名：<input type="text" name="loginname" style="text-align: center;"
						id="query-loginname">&nbsp;&nbsp; 推广资质：<select
						name="proxygrade" id="query-proxygrade">
						<option value="">--请选择--</option>
						<c:forEach items="${gradeNames }" var="item">
							<option value="${item.id }">${item.proxygradename }</option>
						</c:forEach>
					</select> <br>
					<button type="submit" class="btn btn-default">查询</button>
					&nbsp;&nbsp;
					<button type="reset" class="btn btn-default">重置</button>
				</form>
				<div align="right">
					<button style="margin-right: 50px;" class="btn btn-default"
						onclick="batchUpdateForUI()">批量编辑</button>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td><input type="checkbox" onclick="selectAll(this)" /></td>
							<td>序号</td>
							<td>推广资质</td>
							<td>真实姓名</td>
							<td>用户名</td>
							<td>托管账户</td>
							<td>推广标记</td>
							<td>推广人数(有效)</td>
							<td>推广费总收入</td>
							<td>详情</td>
							<td>操作</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td><input type="checkbox" name="selectID"
									value="${item.id }"></td>
								<td>${status.count }</td>
								<td>${item.proxygradename }</td>
								<td>${item.name }</td>
								<td>${item.loginname }</td>
								<td><c:if test="${item.isopenfsinfo eq 0}">
										<font color="red">未开通</font>
									</c:if> <c:if test="${item.isopenfsinfo eq 1}">
										<font color="green">已开通</font>
									</c:if></td>
								<td><c:if test="${item.userspecialflag eq 0 }">
										<font color="blue">普通</font>
									</c:if> <c:if test="${item.userspecialflag eq 1 }">
										<font color="green">特殊</font>
									</c:if></td>
								<td>${item.promonum }<c:if test="${item.validnum eq 0 }">(0)</c:if>
									<c:if test="${item.validnum != 0 }">(${item.validnum })</c:if></td>
								<td><c:if test="${item.promofee eq 0 }">0.00</c:if> <c:if
										test="${item.promofee != 0 }">
										<fmt:formatNumber pattern="0.00" value="${item.promofee }" />
									</c:if></td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">查看</button></td>
								<td>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#updateModal" id="update-btn"
										data-backdrop="static" onclick="forwardUpdateUI('${item.id}')">编辑</button>
								</td>
								<td><p class="remark-p" title="${item.adminremark}">${item.adminremark}</p></td>
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

		function update() {
			var action = "update.action";
			var callback = function(data) {
				queryAllPerson("${pagehelper.pageNum}",
						"${pagehelper.pageSize}");
				alert(data);
			}
			$.post(action, $("#update-form").serialize(), callback, "json");
		}
		function batchUpdateForUI() {
			var checkboxs = $("input[name='selectID']:checked");
			var size = checkboxs.size();
			if (size > 0) {
				if (size != 1) {
					var ids = "";
					for (i = 0; i < size; i++) {
						ids += checkboxs[i].value + ",";
					}
					var action = "batchUpdateForUI.action";
					var params = {
						"ids" : ids
					};
					$.post(action, params, function(data) {
						$("#batchUpdateModal").modal({
							backdrop : 'static'
						}).modal('show');
						$("#batchUpdate-modal-body").html(data);
					})
				} else {
					$("#updateModal").modal({
						backdrop : 'static'
					}).modal('show');
					forwardUpdateUI(checkboxs[0].value);
				}
			}
		} /*
		 *批量编辑  
		 */
		function batchUpdate() {
			if (validateUpdateForm()) {
				var action = "batchUpdate.action";
				$.post(action, $("#batchUpdate-form").serialize(), function(
						data) {
					alert(data);
					queryAllPerson('${pagehelper.pageNum}',
							'${pagehepler.pageSize}');
				}, 'json');
			}
		}
		/*
		 *	全选/全不选
		 */
		function selectAll(obj) {
			var checkboxs = document.getElementsByName("selectID");
			for (i = 0; i < checkboxs.length; i++) {
				checkboxs[i].checked = obj.checked;
			}
		}
	</script>
</body>
</html>