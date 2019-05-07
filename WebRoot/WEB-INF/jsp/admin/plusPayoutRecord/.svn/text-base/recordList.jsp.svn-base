<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的增益清算记录列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
body {
	padding-bottom: 40px;
}
.input-group-addon a {
	text-decoration: none;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<script type="text/javascript"
	src="${basePath}/js/admin/plusPayoutRecord/gj_plusPayoutRecordList.js"></script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
								<li><label>用户名:</label>
									 <input type="text" id="inname" />
								</li>
								<li>
									<label>审核状态:</label>
									 <select id="isaudit" name="isaudit">
										<option value=''>--请选择--</option>
										<option value="0">未审核</option>
										<option value="1">审核通过</option>
										<option value="2">审核不通过</option>
									</select>
								</li>
								<li>
									<label>是否发放:</label>
									 <select id="isgrant" name="isgrant">
										<option value=''>--请选择--</option>
										<option value="0">未发放</option>
										<option value="1">已发放</option>
										<option value="2">处理中</option>
									</select>
								</li>
 								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="repayMent_search">查询</button>
									<button class="btn btn-default"
										onclick="plusPayoutRecordList.low_reset(this)" id="repayMent_reset">重置</button>
									<button class="btn btn-default"
										onclick="plusPayoutRecordList.low_findAll(this)" id="findAll">查询全部</button>
  								</li>
							</ul>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/plusPayoutRecord/recordListTemplate.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>