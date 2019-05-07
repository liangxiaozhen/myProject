<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的利息管理费设置列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
body {
	padding-bottom: 40px;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}

.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript"
	src="${basePath}/js/admin/interestExpense/gj_interestExpense.js"></script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
								<li><label>计算方式:</label> <select id="gfitype"
									name="gfitype">
										<option value=''>--请选择--</option>
										<option value="1">用户等级</option>
										<option value="2">标的风险等级</option>
								</select></li>
								<li><label>资金清算是否需要审核 :</label> <select id="isaudit"
									name="isaudit">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否为模板:</label> <select id="istemplet"
									name="istemplet">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
 								</select></li>
								<li>
									<button class="btn btn-default" onclick="interestExpense.low_search(this)"
										id="interestExpense_search">查询</button>
									<button class="btn btn-default"
										onclick="interestExpense.low_reset(this)"
										id="baseAndFsa_reset">重置</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-12 text-right">
						<button class="btn btn-default"
							onclick="interestExpense.low_save_button(this)">设置</button>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/interestExpense/listTemplate.jsp"></jsp:include>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>