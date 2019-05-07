<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提前实际还款记录实体列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 <%@include file="/WEB-INF/jsp/common/public.jsp" %>
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
<script type="text/javascript" src="${basePath}/js/admin/aheadRealRepayment/gj_aheadRealRepayment.js"></script>
 </head>
<body>
 	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top:15px;">
				<div id="box">
					 <div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
 								<li><label>还款状态:</label> <select id="aheadRealRepayment_repaystatus"
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
 								<li><label>还款流水号</label> <input type="text" name="rorderno"
								id="aheadRealRepayment_rorderno" /></li>
								<li><label>还款批次号</label> <input type="text" name="rbatchno"
								id="aheadRealRepayment_rbatchno" /></li>
 								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="aheadRealRepayment_search">查询</button>
									<button class="btn btn-default"
										onclick="aheadRealRepayment.low_reset(this)" id="aheadRealRepayment_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
 					<div id="queryall_list" style="margin-top:40px;">
	 					<jsp:include page="/WEB-INF/jsp/admin/aheadRealRepayment/listTemplate.jsp"></jsp:include>	  
	  				</div>
  				</div>

			</div>
   		</div>
	</div>
 </body>
</html>