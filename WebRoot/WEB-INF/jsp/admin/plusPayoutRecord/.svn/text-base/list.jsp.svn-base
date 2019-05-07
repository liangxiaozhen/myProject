<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的增益清算审核列表</title>
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
	src="${basePath}/js/admin/plusPayoutRecord/gj_plusPayoutRecord.js"></script>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
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
										onclick="plusPayoutRecord.low_reset(this)" id="repayMent_reset">重置</button>
									<button class="btn btn-default"
										onclick="plusPayoutRecord.low_findAll(this)" id="findAll">查询全部</button>
  								</li>
							</ul>
						</div>
					<div class="col-md-12">
						<div class="col-md-3 text-left">
							<div style="line-height: 48px;">
								<label><span id="checkAllText">全选</span> &nbsp;&nbsp;<input
									type="checkbox" id="checkAll" /></label>
							</div>
						</div>
						<div class="col-md-6 col-md-push-3 text-right">
							<button class="btn btn-default"
								onclick="plusPayoutRecord.low_batchgo(this)" data-mark="1"
								id="batchgo1">批量审核通过</button>
							<button class="btn btn-default"
								onclick="plusPayoutRecord.low_batchgo(this)" data-mark="0"
								id="batchgo">批量审核不通过</button>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/plusPayoutRecord/listTemplate.jsp"></jsp:include>
					</div>
				</div>
				<!-- 模态框  start-->
				<div class="modal fade" id="myAudit">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title">增益清算审核</h4>
							</div>
							<div class="modal-body">
								<h6 class="modal-title alert alert-danger text-center">通过审核后平台将转账给用户</h6>
								<hr />
								<input type="hidden" id="prd_id" />
								<div class="row">
									<label class="col-md-3 text-right">增益清算流水号:</label> <label
										class="col-md-4" id="prd_pporderno"></label>
								</div>
								<hr />
								<div class="row">
									<label class="col-md-3 text-right">还款流水号:</label> <label
										class="col-md-4" id="prd_rorderno"></label>
								</div>
								<hr />
								<div class="row">
									<label class="col-md-3 text-right">加息券收益:</label> <label
										class="col-md-4" id="prd_intprofit"></label>
								</div>
								<hr />
								<div class="row">
									<label class="col-md-3 text-right">假现金收益:</label> <label
										class="col-md-4" id="prd_likevoucherprofit"></label>
								</div>
								<hr />
								<div class="row">
									<label class="col-md-3 text-right">备注:</label>
									<div class="col-md-8">
										<textarea class="form-control" id="prd_remark"></textarea>
									</div>
								</div>
								<hr />
								<div class="alert alert-block alert-danger text-center"
									id="warning-block" style="margin-top: 5px;">
									<span id="pg_context"></span>
									<button type="button" class="close"
										onclick="$('#warning-block').hide()">
										<span>&times;</span><span class="sr-only">Close</span>
									</button>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									onclick="plusPayoutRecord.low_audit(this)" data-audit="1"
									id="audit_operation1">通过通过</button>
								<button type="button" class="btn btn-primary"
									onclick="plusPayoutRecord.low_audit(this)" data-audit="0"
									id="audit_operation">审核不通过</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- 模态框 end -->
			</div>
		</div>
	</div>
</body>
</html>