<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的分期还款计划列表</title>
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
	src="${basePath}/js/admin/dividedPayments/gj_dividedPayments.js"></script>
</head>
<body>
	<div style="width: 85%;"  class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
								<li><label>是否逾期:</label> <select id="isoverdue"
									name="isoverdue">
										<option value=''>--请选择--</option>
										<option value="0">否</option>
										<option value="1">是</option>
								</select></li>
								<li><label>是否还款:</label> <select id="iscomplete"
									name="iscomplete">
										<option value=''>--请选择--</option>
										<option value="0">未还款</option>
										<option value="1">已还款</option>
										<option value="2">处理中</option>
										<option value="3">部分还款</option>
								</select></li>
								<li><label>标号:</label> <input type="text" name=""
									id="userBaseInfo_tno" /></li>
								<li><label>期数:</label> <input type="text" name=""
									id="userBaseInfo_periods" /></li>
								<li>
									<button class="btn btn-default" onclick="search(this)"
										id="baseAndFsa_search">查询</button>
									<button class="btn btn-default"
										onclick="dividedPayments.low_reset(this)"
										id="baseAndFsa_reset">重置</button>
									<button class="btn btn-default"
										onclick="dividedPayments.low_findAll(this)" id="findAll">查询全部</button>
								</li>
							</ul>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/dividedPayments/listTemplate.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!--  -->	
<div class="modal" id="normalCompensatoryPart">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">正常代偿还款选择</h4>
      </div>
      <div class="modal-body">
        	<p>选择部分代偿还款或者全部代偿还款</p>
        	<input type="hidden" id="normalCompensatoryPartRepayMentOpid"/>
        	<input type="hidden" id="normalCompensatoryAllRepayMentOpid"/>
        	
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" onclick="dividedPayments.low_normalCompensatoryPartRepayMent(this)" id="normalCompensatoryPartRepayMent">部分代偿</button>
        <button type="button" class="btn btn-default" onclick="dividedPayments.low_normalCompensatoryAllRepayMent(this)" id="normalCompensatoryAllRepayMent">全部代偿</button>
       </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>
