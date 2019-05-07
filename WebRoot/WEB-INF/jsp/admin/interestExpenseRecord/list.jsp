<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的利息管理费记录列表</title>
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
</style>
<script type="text/javascript"
	src="${basePath}/js/admin/interestExpenseRecord/gj_interestExpenseRecord.js"></script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
 								<li><label>是否审核:</label> <select id="isaudit"
									name="isaudit">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否处理:</label> <select id="isdeal" name="isdeal">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>管理费收取类型:</label> <select id="ietype"
									name="ietype">
										<option value=''>--请选择--</option>
										<option value="1">百分比</option>
 										<option value="2">最高</option>
								</select></li>
								<li><label>是否债转:</label> <select id="ieproperty"
									name="ieproperty">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>标号</label> <input type="text" name="tno"
									id="tno" /></li>
								<li><label>标名称</label> <input type="text" name="tname"
									id="tname" /></li>
								<li><label>还款批次号</label> <input type="text" name="batchno"
									id="batchno" /></li>
								<li><label>还款流水号</label> <input type="text" name="rorderno"
									id="rorderno" /></li>
								<li><label>投资人</label> <input type="text" name="loginname"
									id="loginname" /></li><br /><br />
								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="interestExpenseRecord_search">查询</button>
									<button class="btn btn-default"
										onclick="interestExpenseRecord.low_reset(this)"
										id="interestExpenseRecord_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/interestExpenseRecord/listTemplate.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>