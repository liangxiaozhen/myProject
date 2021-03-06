<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">

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
	src="${pageContext.request.contextPath}/js/regactivity/insert.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
	font-size: 13px
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
				<form id="insertRegActivityForm" role="form" class="form-horizontal"
					method="post"
					action="${pageContext.request.contextPath}/admin/activity/insertRegActivityRule.action"
					onsubmit="return regInsert.checkForm()">
					<div class="well text-center"
						style="font-size: 14px; background: #d6e9c6;">请先完成注册活动规则设置,然后再进行注册活动奖励规则设置</div>
					<!-- =====================注册活动规则表===================== -->
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">活动编号</label>
							<div class="col-sm-4">
								<label>${actno}</label> <input type="hidden" name="actno"
									value="${actno}" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">活动名称</label>
							<div class="col-sm-4">
								<input type="text" name="actname" class="form-control"
									placeholder="请输入活动名称" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">活动开始日期</label>
							<div class="col-sm-3">
								<input type="text" id="actbtime" name="actbtime"
									class="Wdate form-control"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:regInsert.check_bDate,minDate:'%y-%M-%d'})"
									placeholder="请选择活动生效日期" /> <small id="btime"
									style="color: #A94442;"></small>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">活动结束日期</label>
							<div class="col-sm-3">
								<input type="text" id="actetime" name="actetime"
									class="Wdate form-control"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:regInsert.check_eDate,minDate:'%y-%M-%d'})"
									placeholder="请选择活动截止日期" /> <small id="etime"
									style="color: #A94442;"></small>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">是否审核</label>
							<div class="col-sm-3">
								<select name="isauditalist" class="form-control">
									<option value="">请选择</option>
									<c:if test="${!empty isaudit_map}">
										<c:forEach items="${isaudit_map}" var="audit">
											<option value="${audit.key}">${audit.value}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">是否判断注册时间</label>
							<div class="col-sm-2">
								<select name="isconsiderregtime" class="form-control">
									<option value="">请选择</option>
									<c:if test="${!empty isconsiderregtime_map}">
										<c:forEach items="${isconsiderregtime_map}"
											var="isconsiderregtime">
											<option value="${isconsiderregtime.key}">${isconsiderregtime.value}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">是否为模板</label>
							<div class="col-sm-2">
								<select name="istemplet" class="form-control">
									<option value="">请选择</option>
									<c:if test="${!empty istemplet_map}">
										<c:forEach items="${istemplet_map}" var="istemplet">
											<option value="${istemplet.key}">${istemplet.value}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">备注</label>
							<div class="col-sm-4">
								<textarea rows="5" name="remark" class="form-control"
									placeholder="注册活动规则备注"></textarea>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-top: 30px;">
						<div class="col-md-12">
							<div class="col-sm-6 text-right">
								<input type="submit" class="btn" value="继续设置奖励规则">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>