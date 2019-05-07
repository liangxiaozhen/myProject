<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
<title>${title}</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
td {
	border: 1px solid #666;
	vertical-align: middle !important;
}

body {
	font-family: "微软雅黑";
	font-size: 13px;
}

label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
	/*** 备注显示字符个数限制 */
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}

	$(function() {
		/*** 备注添加limit属性 */
		$("[limit]").limit();
		/*** 备注tips */
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		/*** 重置查询条件 */
		$("#reset").click(function() {
			$("#loginname").val('');
			$("#tender").val('');
			$("#status").val('');
		})
	})

	/*** 分页查询自动投标计划 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#planForm").submit()
	}

	var tenderPlan = {
		/*** 查看自动投标计划详情 */
		queryPlanDetail : function(id) {
			var url = "${pageContext.request.contextPath }/admin/autoTenderPan/queryPlanDetail.action";
			var param = {
				"id" : id
			};
			var callback = function(data) {
				$("#detailModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#detailModal-body").html(data)
			};
			$.post(url, param, callback);
		},
	};
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<form method="post" role="form" id="planForm"
						action="${pageContext.request.contextPath }/admin/autoTenderPan/queryAutoTender.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<ul class="list-inline">
							<li><label>用户名：</label></li>
							<li><input type="text" id="loginname"
								name="userBaseInfo.loginname"
								value="${echodata.userBaseInfo.loginname}" /></li>
							<li><label>标的名称：</label></li>
							<li>
							<li><input type="text" name="tender.tname" id="tender"
								value="${echodata.tender.tname}" /></li>
							<li>
							<li><label>投标计划状态：</label></li>
							<li><select name="status" id="status">
									<option value="">--请选择--</option>
									<option value="0"
										<c:if test="${echodata.status eq 0}">selected="selected"</c:if>>已关闭</option>
									<option value="1"
										<c:if test="${echodata.status eq 1}">selected="selected"</c:if>>已开启</option>
							</select></li>
							<li><input type="submit" value="查询" class="btn" /> <input
								type="button" value="重置" class="btn" id="reset" /></li>
						</ul>
					</form>
					<div class="table-responsive">
						<table class="table table-hover table-condensed text-center">
							<caption>
								<strong>自动投标计划表</strong>
							</caption>
							<thead>
								<tr style="background: #ccc;">
									<td>序号</td>
									<td>标号</td>
									<td>标的名称</td>
									<td>用户名</td>
									<td>投标计划类型</td>
									<td>投标金额（元）</td>
									<td>投标计划状态</td>
									<td>自动投标开始时间</td>
									<td>自动投标完成时间</td>
									<td>是否系统勾兑</td>
									<td>是否人工勾兑</td>
									<td>详情</td>
									<td>备注</td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="plan"
										varStatus="varStatus">
										<tr>
											<td>${varStatus.index+1}</td>
											<td>${plan.tender.tno}</td>
											<td>${plan.tender.tname}</td>
											<td>${plan.userBaseInfo.loginname}</td>
											<td><c:if test="${plan.tenderplantype eq 'P'}">
													<font>部分授权</font>
												</c:if> <c:if test="${plan.tenderplantype eq 'W'}">
													<font>完全授权</font>
												</c:if></td>
											<td><fmt:formatNumber minFractionDigits="2"
													value="${plan.transamt}"></fmt:formatNumber></td>
											<td><c:if test="${plan.status eq 0}">
													<font>已关闭</font>
												</c:if> <c:if test="${plan.status eq 1}">
													<font color="green">已开启</font>
												</c:if></td>
											<td>${plan.uatbegintime}</td>
											<td>${plan.uatendtime}</td>
											<td><c:if test="${plan.isblending eq 0}">
													<font>未勾兑</font>
												</c:if> <c:if test="${plan.isblending eq 1}">
													<font>已勾兑</font>
												</c:if></td>
											<td><c:if test="${plan.ismanblending eq 0}">
													<font>未勾兑</font>
												</c:if> <c:if test="${plan.ismanblending eq 1}">
													<font>已勾兑</font>
												</c:if></td>
											<td><button class="btn"
													onclick="tenderPlan.queryPlanDetail('${plan.id}')">查看详情</button></td>
											<td><p limit="5" data-toggle="tooltip"
													title="<h5>${plan.remark}</h5>">${plan.remark}</p></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty pagehelper.list}">
									<tr>
										<td colspan="100">没有相关数据</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<div id="page_div">
						<%@ include file="./../../common/pagehelper.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 详情模态框（Modal） -->
	<div id="detailModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看自动投标计划详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>