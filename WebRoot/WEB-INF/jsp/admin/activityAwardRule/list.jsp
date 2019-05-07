<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的活动奖励规则列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript"
	src="${basePath}/js/admin/activityAwardRule/gj_activityAwardRule.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-5 input-group">
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-search"></span></span> <input type="text"
							name="search_username" id="search_username" class="form-control"
							placeholder="请输入活动编号搜索..."> <span
							class="input-group-addon"><a
							href="javascript:search(this)">搜索</a></span>
						<div class="col-md-2 col-md-offset-3">
							<button class="btn btn-default"
								onclick="activityAwardRule.low_findAll(this)">查询全部</button>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/activityAwardRule/listTemplate.jsp"></jsp:include>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>