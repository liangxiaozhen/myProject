<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的提前还款奖励单个投资人记录列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
 <%@include file="/WEB-INF/jsp/common/public.jsp" %>
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
 	.text-center td{
 	vertical-align: text-top!important;
 	border: 1px solid #666;
 	}
 	.input-group-addon a{text-decoration:none;}
  </style>
<script type="text/javascript" src="${basePath}/js/admin/aheadRepayOneRecord/gj_aheadRepayOneRecord.js"></script>
 </head>
<body>
 	<div style="width: 85%;" class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top:15px;">
				<div id="box">
				
					 <div class="col-md-5 input-group">
				         <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
				         <input type="text" name="search_username" id="search_username" class="form-control" placeholder="请输入活动编号搜索...">
	 			         <span class="input-group-addon"><a href="javascript:search(this)">搜索</a></span>
					      <div class="col-md-2 col-md-offset-3">
					      	<button class="btn btn-default" onclick="aheadRepayOneRecord.low_findAll(this)">查询全部</button>
					      </div>
				     </div>
				     
					<div id="queryall_list" style="margin-top:40px;">
	 					<jsp:include page="/WEB-INF/jsp/admin/aheadRepayOneRecord/listTemplate.jsp"></jsp:include>	  
	  				</div>
  				</div>
  				
				<!-- model start -->
				<div class="modal fade" tabindex="-1" role="dialog" id="my_audit">
					  <div class="modal-dialog" role="document">
						    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							        <h4 class="modal-title">审核提前还款奖励投资人</h4>
							      </div>
							      <div class="modal-body">
							        <p>One fine body&hellip;</p>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default">审核通过</button>
							        <button type="button" class="btn btn-warning">审核不通过</button>
 							        <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
							      </div>
						    </div><!-- /.modal-content -->
					  </div><!-- /.modal-dialog -->
				 </div><!-- /.modal -->
				<!-- model end -->
				
			</div>
   		</div>
	</div>
 </body>
</html>