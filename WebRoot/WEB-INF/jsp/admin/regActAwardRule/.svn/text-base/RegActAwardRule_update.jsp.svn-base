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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/regactivity/updateActAward.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
	font-size: 15px
}

label {
	font-weight: normal;
}
</style>
</head>
<body style="margin: 50px;">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form method="post" id="updateRegActAwardForm"
					class="form-horizontal"
					action="${pageContext.request.contextPath}/admin/actAward/updateRegAwardRule.action">
					<div class="well text-center"
						style="font-size: 14px; background: #d6e9c6;">请先完成注册活动规则设置,然后再进行注册活动奖励规则设置</div>
					<input type="hidden" name="id" value="${info.id}" /> <input
						type="hidden" name="actid" value="${actid}" /> <input
						type="hidden" id="business" name="business" value="${business}" />
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">指定业务</label> <label
								class="col-sm-3">${work}</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<input type="hidden" id="keylist" value="${info.crestrict}" /> <label
								class="col-sm-4 text-right">客户端限制</label>
							<div class="col-sm-5">
								<c:if test="${!empty crestrict_map}">
									<c:forEach items="${crestrict_map}" var="limit">
										<label class="checkbox-inline"> <input type="checkbox"
											name="crestrict" value="${limit.key}"> ${limit.value}
										</label>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
					<c:if test="${judge eq 1}">
						<div class="form-group" id="limit_time" hidden="hidden">
							<div class="col-md-10">
								<label class="col-sm-4 text-right">限定时间</label>
								<div class="col-sm-2">
									<div class="input-group">
										<input type="text" name="finishtime" class="form-control"
											value="${info.finishtime}" /> <span
											class="input-group-addon">小时</span>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">奖品编号</label>
							<div class="col-sm-3">
								<input type="text" id="awardno" name="awardno"
									class="form-control" value="${ano}"
									onblur="regUpdate.queryname(value)" /><input type="hidden"
									id="awardid" name="awardid" /><small id="number"
									style="color: #A94442;"></small>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">获品的名称</label>
							<div class="col-sm-4">
								<label id="aname">${info.awardname}</label> <input type="hidden"
									id="awardname" name="awardname" value="${info.awardname}" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">奖品份数</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" id="awardcopies" name="awardcopies"
										class="form-control" value="${info.awardcopies}"
										onblur="regUpdate.queryCopies(value)" /> <span
										class="input-group-addon">份</span>
								</div>
								<small id="copies" style="color: #A94442;"></small>
							</div>
						</div>
					</div>
					<div id="cost" class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">奖励金额</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" id="quota" name="quota" class="form-control"
										value="${info.quota}" /> <span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">备注</label>
							<div class="col-sm-4">
								<textarea rows="5" name="remark" class="form-control">${info.remark}</textarea>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-top: 50px;">
						<div class="col-md-10">
							<div class="col-sm-9 text-right">
								<c:if test="${yc ne 'yc'}">
									<input type="submit" class="btn" value="继续编辑"
										style="margin-right: 20px;" />
								</c:if>
								<c:if test="${show eq 'show'}">
									<input type="button" class="btn" value="新增子活动"
										style="margin-right: 20px;"
										onclick="regUpdate.insertRule(this)" />
								</c:if>
								<input type="button" class="btn" value="保存本活动"
									style="margin-right: 20px;" onclick="regUpdate.save(this)" />
								<input type="button" class="btn" value="返回上一页"
									onclick="regUpdate.previousPage(this)"
									style="margin-right: 20px;" /> <input type="button"
									class="btn" value="删除本页子活动"
									onclick="regUpdate.removeRule(this)" />
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>