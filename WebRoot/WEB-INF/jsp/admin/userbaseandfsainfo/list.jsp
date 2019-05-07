<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户托管账号开户信息列表</title>
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

.input-group-addon a {
	text-decoration: none;
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
</style>
<script type="text/javascript"
	src="${basePath}/js/admin/userbaseandfsainfo/gj_userbaseandfsainfo.js"></script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
								<li><label>用户名:</label> <input type="text" name=""
									id="userBaseInfo_loginname" /></li>
								<li><label>姓名:</label> <input type="text" name=""
									id="userBaseInfo_realname" /></li>
								<li><label>手机号:</label> <input type="text" name=""
									id="userBaseInfo_mobilephone" /></li>
								<li><label>邮箱:</label> <input type="text" name=""
									id="userBaseInfo_email" /></li>
								<li><label>用户类型:</label>
									<select id="accounttype" name="accounttype">
										<option value=>--请选择--</option>
										<option value="1">个人</option>
										<option value="2">企业</option>
 									</select>
								 </li>
								<li><label>是否开通:</label> 
									<select id="isopenfsinfo" name="isopenfsinfo">
										<option value=>--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
 									</select>
 								</li>
									
								<li><label>注册时间:</label> <input type="text" name=""
									class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									id="userBaseInfo_startregdate" /> -- <input type="text"
									name="" class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									id="userBaseInfo_endregdate" /></li>

								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="baseAndFsa_search">查询</button>
									<button class="btn btn-default"
										onclick="baseAndFsa.low_reset(this)" id="baseAndFsa_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/userbaseandfsainfo/listTemplate.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>