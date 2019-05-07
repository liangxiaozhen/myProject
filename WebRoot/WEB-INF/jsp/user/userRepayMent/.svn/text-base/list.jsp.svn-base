<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户还款记录列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
#container_box {
	position: absoulte;
	height: 1400px;
}

.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript"
	src="${basePath}/js/user/userRepayMent/gj_userRepayMent.js"></script>
</head>
<body>
	<div class="container" id="container_box">
		<div class="col-lg-12 col-md-12" style="margin-top: 15px;">
			<div id="box">
				<div class="col-md-12 input-group column">
					<div class="col-md-12">
						<ul class="list-inline">
							<li><label>还款状态:</label> <select
								id="userRepayMent_repaystatus">
									<option value="">--请选择--</option>
									<option value="1">未还款</option>
									<option value="2">已还款</option>
									<option value="3">已提前还款</option>
							</select></li>
							<li><label>审核状态:</label> <select id="userRepayMent_isaudit">
									<option value="">--请选择--</option>
									<option value="0">不需要审核</option>
									<option value="1">审核处理中</option>
									<option value="2">审核已通过</option>
									<option value="3">审核不通过</option>
							</select></li>
							<li><label>还款模式:</label> <select id="userRepayMent_rmode">
									<option value="">--请选择--</option>
									<option value="1">人工</option>
									<option value="2">系统</option>
									<option value="3">线下</option>
							</select></li>
							<li><label>是否债转:</label> <select
								id="userRepayMent_isdarepay">
									<option value="">--请选择--</option>
									<option value="1">是</option>
									<option value="0">否</option>
							</select></li>
							<li><label>标的名称:</label> <input type="text" name=""
								id="userRepayMent_tname" placeholder="请输入标的名称..." /></li>
							<li><label>还款时间范围:</label> <select
								id="userRepayMent_datamark">
									<option value="">--请选择--</option>
									<option value="1">最近8年</option>
									<option value="2">最近5年</option>
									<option value="3">最近3年</option>
									<option value="4">最近1年</option>
									<option value="5">最近6月</option>
									<option value="6">最近3月</option>
									<option value="7">最近1月</option>
									<option value="8">最近7天</option>
									<option value="9">最近3天</option>
									<option value="10">今天</option>
							</select></li>
							<li>
								<button class="btn btn-default" onclick="search(this)"
									id="userRepatMent_search">查询</button>
								<button class="btn btn-default"
									onclick="userRepatMent_reset(this)" id="userRepatMent_reset">重置</button>
								<button class="btn btn-default"
									onclick="userRepayMent.low_findAll(this)" id="findAll">查询全部</button>
							</li>
						</ul>
					</div>
				</div>
				<div id="queryall_list" style="margin-top: 25px;">
					<jsp:include
						page="/WEB-INF/jsp/user/userRepayMent/listTemplate.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>