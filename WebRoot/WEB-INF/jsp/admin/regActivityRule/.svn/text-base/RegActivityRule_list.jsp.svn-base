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
<meta http-equiv="cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/regactivity/ActivityList.js"></script>
<style type="text/css">
td {
	border: 1px solid #666;
	padding: 4px 0px !important;
	vertical-align: middle !important;
}

hr {
	height: 3px;
	border: none;
	border-top: 1px double #666;
	margin: 4px 0px;
}

body {
	font-family: "微软雅黑";
	font-size: 13px;
}

label {
	font-weight: normal;
}
</style>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 30px">
					<form id="regActivityRuleForm" method="post"
						action="${pageContext.request.contextPath}/admin/activity/queryActivityList.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<div class="col-md-3 input-group" style="float: left;">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span></span> <input type="text"
								name="actno" id="actno" class="form-control"
								value="${echodata.actno}" placeholder="请输入注册活动编号搜索...">
							<span class="input-group-addon"><button type="submit"
									style="border-style: none">搜索</button></span>
						</div>
						<div class="col-md-2">
							<button type="button" class="btn" onclick="regList.reset(this)">重置</button>
						</div>
					</form>
					<div align="right">
						<input type="button" class="btn" value="手动新增"
							onclick="regList.insert_activity_rule(this)" /> <input
							type="button" class="btn" value="模板新增"
							onclick="regList.insertByTemplet(this)" />
					</div>
					<div class="table-responsive">
						<table class="table table-hover text-center table-condensed">
							<caption>
								<strong>注册活动规则表</strong>
							</caption>
							<thead>
								<tr style="background: #ccc;">
									<td>序号</td>
									<td>活动名称
										<hr />活动编号
									</td>
									<td>活动状态</td>
									<td>活动生效日期
										<hr />活动截止日期
									</td>
									<td>获奖名单是否审核</td>
									<td>操作</td>
									<td>详情</td>
									<td>活动备注</td>
								</tr>
							</thead>
							<c:if test="${!empty pagehelper.list}">
								<c:forEach items="${pagehelper.list}" var="activity"
									varStatus="status">
									<tr id="activity_tr_${activity.id}">
										<td>${status.index+1}</td>
										<td>${activity.actname}<hr />${activity.actno}</td>
										<td><c:if test="${!empty status_map}">
												<c:forEach items="${status_map}" var="status">
													<c:choose>
														<c:when test="${activity.status eq status.key}">
															<c:if
																test="${activity.status eq 0 || activity.status eq 1}">
																<font>${status.value}</font>
															</c:if>
															<c:if test="${activity.status eq 2}">
																<font color="blue">${status.value}</font>
															</c:if>
															<c:if test="${activity.status eq 3}">
																<font color="green">${status.value}</font>
															</c:if>
															<c:if
																test="${activity.status eq 4 || activity.status eq 5 || activity.status eq 6}">
																<font color="red">${status.value}</font>
															</c:if>
														</c:when>
													</c:choose>
												</c:forEach>
											</c:if></td>
										<td><fmt:formatDate value="${activity.actbtime}"
												pattern="yyyy-MM-dd HH:mm:ss" />
											<hr /> <fmt:formatDate value="${activity.actetime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><c:if test="${!empty isauditalist_map}">
												<c:forEach items="${isauditalist_map}" var="isauditalist">
													<c:choose>
														<c:when
															test="${activity.isauditalist eq isauditalist.key}">${isauditalist.value}</c:when>
													</c:choose>
												</c:forEach>
											</c:if></td>
										<td><div class="btn-group" role="group" aria-label="...">
												<c:if test="${activity.status eq 0}">
													<button type="button" class="btn"
														onclick="regList.continueFinishRule('${activity.id}',this)">继续完善</button>
												</c:if>
												<c:if test="${activity.status eq 1 || activity.status eq 2}">
													<button type="button" class="btn"
														onclick="regList.updateStatus('${activity.id}',this)"
														data-operation="disabled">停用</button>
												</c:if>
												<c:if test="${activity.status eq 4}">
													<button type="button" class="btn"
														onclick="regList.updateStatus('${activity.id}',this)"
														data-operation="enable">启用</button>
												</c:if>
												<c:if
													test="${activity.status eq 1 || activity.status eq 2 || activity.status eq 4}">
													<button type="button" class="btn"
														onclick="regList.updateStatus('${activity.id}',this)"
														data-operation="invalid">作废</button>
												</c:if>
												<c:if test="${activity.status ne 2 && activity.status ne 3}">
													<button type="button" class="btn"
														onclick="regList.editRule('${activity.id}',this)">编辑</button>
												</c:if>
												<c:if test="${activity.status ne 2 && activity.status ne 3}">
													<button type="button" class="btn"
														onclick="regList.deleteRule('${activity.id}',this)">删除</button>
												</c:if>
											</div></td>
										<td><button type="button" class="btn"
												onclick="regList.queryDetail('${activity.actno}',this)">查看</button></td>
										<td><p data-toggle="tooltip"
												title="<h5>${activity.remark}</h5>" limit="5">${activity.remark}</p></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty pagehelper.list}">
								<tr>
									<td colspan="100">没有相关数据</td>
								</tr>
							</c:if>
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
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看注册活动规则详情
					</h4>
				</div>
				<div class="modal-body" id="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 模板模态框（Modal） -->
	<div class="modal fade bs-example-modal-lg" id="templetModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<form method="post"
				action="${pageContext.request.contextPath}/admin/activity/setByTemplet.action">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看注册活动规则详情
						</h4>
					</div>
					<div class="modal-body" id="templetModal-body"></div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">确定生成</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>