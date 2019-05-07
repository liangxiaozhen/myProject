<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>还款记录列表</title>
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

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript"
	src="${basePath}/js/admin/repayMent/gj_repayMent.js"></script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
								<li><label>是否有效:</label> <select id="planstatus"
									name="planstatus">
										<option value=''>--请选择--</option>
										<option value="1">有效</option>
										<option value="2">作废</option>
								</select></li>
								<li><label>是否代偿:</label> <select id="isproxyrepay"
									name="isproxyrepay">
										<option value=''>--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
								</select></li>
								<li><label>是否逾期:</label> <select id="isoverdue"
									name="isoverdue">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否提前还款:</label> <select id="isahead"
									name="isahead">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否债转:</label> <select id="isdarepay"
									name="isdarepay">
										<option value=''>--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
								</select></li>
								<li><label>还款模式:</label> <select id="rmode" name="rmode">
										<option value=''>--请选择--</option>
										<option value="0">初始</option>
 										<option value="1">人工</option>
										<option value="2">系统</option>
										<option value="3">线下</option>
								</select></li>
								<li><label>是否审核:</label> <select id="isaudit"
									name="isaudit">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
 								</select></li>
								<li><label>还款状态:</label> <select id="repaystatus"
									name="repaystatus">
										<option value=''>--请选择--</option>
										<option value="1">待还款</option>
										<option value="2">审核中</option>
										<option value="3">待处理</option>
										<option value="4">处理中</option>
										<option value="5">已还款</option>
										<option value="6">还款失败</option>
										<option value="7">审核失败</option>
   								</select></li>
								<li><label>标号</label> <input type="text" name="tno"
									id="tno" /></li><br /><br />
								<li><label>标名称</label> <input type="text" name="tname"
								id="tname" /></li>
								<li><label>投资人用户名</label> <input type="text" name="inloginname"
								id="inloginname" /></li>
								<li><label>借款人用户名</label> <input type="text" name="outloginname"
								id="outloginname" /></li>
								<li><label>代还人用户名</label> <input type="text" name="proxyloginname"
								id="proxyloginname" /></li>
								<li><label>投标流水号</label> <input type="text" name="utorderno"
								id="utorderno" /></li>
								<li><label>债权转让流水号</label> <input type="text" name="daorderno"
								id="daorderno" /></li><br /><br />
								<li><label>还款流水号</label> <input type="text" name="rorderno"
								id="rorderno" /></li>
								<li><label>还款批次号</label> <input type="text" name="rbatchno"
								id="rbatchno" /></li>
								<li><label>期数</label> <input type="text" name="periods"
									id="periods" /></li><br /><br />
								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="repayMent_search">查询</button>
									<button class="btn btn-default"
										onclick="repayMent.low_reset(this)" id="repayMent_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include page="/WEB-INF/jsp/admin/repayMent/listTemplate.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>