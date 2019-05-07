<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批次还款记录列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 <%@include file="/WEB-INF/jsp/common/public.jsp" %>
 <style>
 	.text-center td{vertical-align: text-top!important;border: 1px solid #666;}
 	.input-group-addon a{text-decoration:none;}
  </style>
<script type="text/javascript" src="${basePath}/js/admin/repaymentFrz/gj_repaymentFrz.js"></script>
 </head>
<body>
 	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top:15px;">
				<div id="box">
					 <div class="col-md-12 input-group column">
						<div class="col-md-12">
							<ul class="list-inline">
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
								<li><label>是否提前:</label> <select id="isahead"
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
 								<li><label>状态:</label> <select id="status"
									name="status">
										<option value=''>--请选择--</option>
										<option value="0">取消</option>
										<option value="1">冻结成功</option>
										<option value="2">冻结失败</option>
										<option value="3">审核中</option>
										<option value="4">待处理</option>
										<option value="5">处理中</option>
										<option value="6">处理成功</option>
										<option value="7">审核失败</option>
										<option value="8">解冻成功</option>
  								</select></li>
								<li><label>标号</label> <input type="text" name="tno"
									id="tno" /></li>
								<li><label>标名称</label> <input type="text" name="tname"
								id="tname" /></li>
 								<li><label>借款人</label> <input type="text" name="outloginname"
								id="outloginname" /></li>
 								<li><label>还款批次号</label> <input type="text" name="rbatchno"
								id="rbatchno" /></li><br /><br />
								 <li>
									<button class="btn btn-default" onclick="search(this)"
										id="repaymentFrz_search">查询</button>
									<button class="btn btn-default"
										onclick="repaymentFrz.low_reset(this)" id="repayMent_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
 					<div id="queryall_list" style="margin-top:40px;">
	 					<jsp:include page="/WEB-INF/jsp/admin/repaymentFrz/listTemplate.jsp"></jsp:include>	  
	  				</div>
  				</div>

			<!-- 审核模态框  -->
			<div class="modal fade" id="isauditModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">审核处理</h4>
						</div>
						<div class="modal-body">
							 <div class="row">
								<div class="col-md-12">
									<label class="col-sm-4 text-left">批次还款流水号：</label> <span id="isauditModal_batchno"></span>
								</div>
								<div class="col-md-12">
									<label class="col-sm-4 text-left">标的编号</label> <span id="isauditModal_tno"></span>
								</div>
								<div class="col-md-12">
									<label class="col-sm-4 text-left">借款人</label> <span id="isauditModal_loginname"></span>-<span id="isauditModal_realname"></span>
								</div>
 								<div class="col-md-12">
									<label class="col-sm-4 text-left">备注:</label>
									<textarea style="min-height:80px;max-width:360px;min-width:280px;"></textarea>
								</div>
								<input type="hidden" id="isauditModal_opid"/>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-success" onclick="repaymentFrz.low_isaudit(this)" data-status="1" id="low_isaudit_1">审核通过</button>
							<button type="button" class="btn btn-danger" onclick="repaymentFrz.low_isaudit(this)" data-status="0" id="low_isaudit_0">审核失败</button>
 							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
				<!-- modal  end -->

				</div>
			</div>
   		</div>
	</div>
 </body>
</html>