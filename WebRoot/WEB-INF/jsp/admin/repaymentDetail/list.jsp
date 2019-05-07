<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>还款批量详情记录列表</title>
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
<script type="text/javascript" src="${basePath}/js/admin/repaymentDetail/gj_repaymentDetail.js"></script>
 </head>
<body>
 	<div style="width: 85%;"  class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top:15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
 								<li><label>是否代偿:</label> <select id="repaymentDetail_isproxyrepay"
									name="isproxyrepay">
										<option value=''>--请选择--</option>
										<option value="1">是</option>
										<option value="2">否</option>
								</select></li>
								<li><label>是否逾期:</label> <select id="repaymentDetail_isoverdue"
									name="isoverdue">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否提前:</label> <select id="repaymentDetail_isahead"
									name="isahead">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否债转:</label> <select id="repaymentDetail_isdarepay"
									name="isdarepay">
										<option value=''>--请选择--</option>
										<option value="1">是</option>
										<option value="0">否</option>
								</select></li>
								<li><label>还款模式:</label> <select id="repaymentDetail_rmode" name="rmode">
										<option value=''>--请选择--</option>
										<option value="0">初始</option>
										<option value="1">人工</option>
										<option value="2">系统</option>
										<option value="3">线下</option>
								</select></li>
								<li><label>是否审核:</label> <select id="repaymentDetail_isaudit"
									name="isaudit">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
 								</select></li>
								<li><label>还款状态:</label> <select id="repaymentDetail_repaystatus"
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
									id="repaymentDetail_tno" /></li>
								<li><label>标名称</label> <input type="text" name="tname"
								id="repaymentDetail_tname" /></li><br /><br />
								<li><label>投资人用户名</label> <input type="text" name="inloginname"
								id="repaymentDetail_inloginname" /></li>
								<li><label>借款人用户名</label> <input type="text" name="outloginname"
								id="repaymentDetail_outloginname" /></li>
								<li><label>代还人用户名</label> <input type="text" name="proxyloginname"
								id="repaymentDetail_proxyloginname" /></li>
								<li><label>投标/债权转让流水号</label> <input type="text" name="utorderno"
								id="repaymentDetail_utorderno" /></li>
 								<li><label>还款流水号</label> <input type="text" name="rorderno"
								id="repaymentDetail_rorderno" /></li><br /><br />
								<li><label>还款批次号</label> <input type="text" name="rbatchno"
								id="repaymentDetail_rbatchno" /></li>
								<li><label>期数</label> <input type="text" name="periods"
									id="repaymentDetail_periods" /></li><br /><br />
								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="repaymentDetail_search">查询</button>
									<button class="btn btn-default"
										onclick="repaymentDetail.low_reset(this)" id="repayMent_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
 					<div id="queryall_list" style="margin-top:40px;">
	 					<jsp:include page="/WEB-INF/jsp/admin/repaymentDetail/listTemplate.jsp"></jsp:include>	  
	  				</div>
  				</div>

			</div>
   		</div>
	</div>
 </body>
</html>