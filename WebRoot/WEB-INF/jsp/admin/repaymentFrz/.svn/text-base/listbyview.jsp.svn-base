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
									<button class="btn btn-default" onclick="searchByView(this)"
										id="repaymentFrz_search">查询</button>
									<button class="btn btn-default"
										onclick="repaymentFrz.low_reset(this)" id="repayMent_reset">重置</button>
 								</li>
							</ul>
						</div>
					</div>
 					<div id="queryall_list" style="margin-top:40px;">
	 					<jsp:include page="/WEB-INF/jsp/admin/repaymentFrz/listTemplatebyview.jsp"></jsp:include>	  
	  				</div>
  				</div>
 
 			</div>
   		</div>
	</div>
 </body>
</html>