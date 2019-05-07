<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/dateproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/userdebtattorn/userdebtattornlist.js"></script>
<title>itemList</title>
<style>
table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 1px solid #666;
}
</style>
</head>

<body>

	<div id="itemList_table" style="font-family: 微软雅黑">
		<br />
		<div style="margin-left: 45px; font-weight: bold; font-size: 20px">债转设置</div>
		<div>
			<div
				style="float: left; margin-left: 25px; width: 95%; margin-top: 20px">
				<form method="post" id="form-select">
					<div style="margin-left: 20px">
						<input type="hidden" id="pageNum" name="pageNum" /> <input
							type="hidden" id="pageSize" name="pageSize" />
					</div>

				</form>
				<!-- <div style=" margin-left:100%;margin-bottom: 5px;float: right">
			     <button id="add_userdebtattorn" class="btn" onclick="insertUserDebtattorn()">新增债转设置</button>
			</div> -->
			</div>
			<table class="table table-hover" id="tableterm"
				style="width: 95%; text-align: center; margin-left: 35px">
				<thead>
					<tr style="background-color: #CCCCCC;" class="text-center2">
						<td>序号</td>
						<td>投标订单号</td>
						<td>投标金额</td>
						<td>剩余本金</td>
						<td>债转金额范围</td>
						<td>是否全部</td>
						<td>债转系数范围</td>
						<td>可债转次数</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="text" style="text-align: center;">
					<!-- 这里面${item.id }是点的model里面的属性 -->
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

						<tr id="item_tr_${item.id }" class="text-center2">
							<td>${status.count}</td>
							<td>${item.usertender.orderno}</td>
							<td><c:if test="${!empty item.usertender.amount}">${df.format(item.usertender.amount)}</c:if>
							</td>
							<td><c:if test="${!empty item.repayment.restprincipal}">${df.format(item.repayment.restprincipal)}</c:if>
								<c:if test="${empty item.repayment.restprincipal}">50</c:if></td>
							<td><c:if test="${!empty item.attornmoneylow}">${df.format(item.attornmoneylow)}</c:if>--
								<c:if test="${!empty item.attornmoney}">${df.format(item.attornmoney)}</c:if>
							</td>
							<td><c:if test="${item.isasplit eq 0}">全部</c:if> <c:if
									test="${item.isasplit eq 1}">部分</c:if></td>
							<td>${item.minattornratio}--${item.maxattornratio}</td>
							<td>${item.datimes}次</td>
							<td>
								<button class="btn" data-toggle="modal" data-dismiss="modal"
									data-target="#myModal" onclick="install('${item.id}')">设置</button>

							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<div id="page_div" style="margin-left: 35px;">
				<%@ include file="./../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>

	<!-- 设置模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">债转设置</h4>
				</div>
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="ReleaseYanz()" id="frozen">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 审核模态框 -->
	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
		aria-labelledby="auditModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="auditModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行审核操作
					</h4>
				</div>
				<div id="audit-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="auditSuccess()">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 启用 -->
	<div class="modal fade" id="isuseModal" tabindex="-1" role="dialog"
		aria-labelledby="isuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="isuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="isuseid"> 您是否启用 ID : <label
						id="isuseidlb"></label> 的设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 停用  -->
	<div class="modal fade" id="cancelUseModal" tabindex="-1" role="dialog"
		aria-labelledby="cancelUseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cancelUseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="canceluseid"> 您是否停用 ID : <label
						id="canceluseidlb"></label> 的记录？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
