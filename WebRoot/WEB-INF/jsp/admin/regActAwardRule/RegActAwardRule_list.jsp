<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/regactivity/ActAwardList.js"></script>
<style type="text/css">
th {
	text-align: center;
	background: #ccc;
}

body {
	font-family: "微软雅黑"
}

label {
	font-weight: normal;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<form id="regActAwardRuleForm" method="post"
						action="${pageContext.request.contextPath}/admin/actAward/queryActAwardList.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<div class="col-md-2">
							<select id="business" name="business" class="form-control">
								<option value="">--请选择指定业务--</option>
								<c:if test="${!empty work_map}">
									<c:forEach items="${work_map}" var="work">
										<c:choose>
											<c:when test="${echodata.business eq work.key}">
												<option value="${work.key}" selected="selected">${work.value}</option>
											</c:when>
											<c:otherwise>
												<option value="${work.key}">${work.value}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="col-md-3 input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span></span> <input type="text"
								name=regactivityrule.actno id="actno" class="form-control"
								value="${echodata.regactivityrule.actno}"
								placeholder="请输入注册活动编号搜索..."> <span
								class="input-group-addon"><button type="submit"
									style="border-style: none">搜索</button></span>
							<div class="col-md-2 col-md-offset-3">
								<button type="button" class="btn" onclick="regAwardList.reset()">重置</button>
							</div>
						</div>
					</form>
					<div class="table-responsive">
						<table
							class="table table-bordered table-hover table-condensed text-center">
							<caption>
								<strong>注册活动规则奖励表</strong>
							</caption>
							<thead>
								<tr>
									<th>序号</th>
									<th>活动编号</th>
									<th>活动名称</th>
									<th>指定业务</th>
									<th>限定时间</th>
									<th>奖品名称</th>
									<th>活动状态</th>
									<th>备注</th>
									<th>修改</th>
									<th>详情</th>
								</tr>
							</thead>
							<c:if test="${!empty pagehelper.list}">
								<c:forEach items="${pagehelper.list}" var="actAward"
									varStatus="status">
									<tr id="actAward_tr_${actAward.id }">
										<td>${status.index+1}</td>
										<td>${actAward.regactivityrule.actno}</td>
										<td>${actAward.regactivityrule.actname}</td>
										<td><c:if test="${!empty work_map}">
												<c:forEach items="${work_map}" var="work">
													<c:choose>
														<c:when test="${actAward.business eq work.key}">${work.value}</c:when>
													</c:choose>
												</c:forEach>
											</c:if></td>
										<td>${actAward.finishtime}<c:if
												test="${actAward.finishtime != null}">
												<span>小时</span>
											</c:if></td>
										<td>${actAward.awardname}</td>
										<td><c:if test="${!empty status_map}">
												<c:forEach items="${status_map}" var="status">
													<c:choose>
														<c:when test="${actAward.status eq status.key}">${status.value}</c:when>
													</c:choose>
												</c:forEach>
											</c:if></td>
										<td><p data-toggle="tooltip"
												title="<h5>${actAward.remark}</h5>" limit="5">${actAward.remark}</p></td>
										<td><div class="btn-group" role="group" aria-label="...">
												<button type="button" class="btn"
													onclick="regAwardList.editAwardRule('${actAward.id}',this)">编辑</button>
												<button type="button" class="btn"
													onclick="regAwardList.deleteAwardRule('${actAward.id}',this)">删除</button>
											</div></td>
										<td><button type="button" class="btn"
												onclick="regAwardList.queryDetail('${actAward.id}',this)">查看</button></td>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看注册活动详情
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

</body>
</html>