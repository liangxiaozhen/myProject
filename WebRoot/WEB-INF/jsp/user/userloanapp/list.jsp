<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借款记录</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<script type="text/javascript"
	src="${basePath}/js/user/userloanApp/gj_userloanApp.js"></script>
<style>
.container {
	margin-top: 50px;
}

#box .item .item_title {
	margin-bottom: 15px;
}

#box .item_title .item_title_sx {
	line-height: 40px;
	color: #666;
}

#box .item_title li span {
	font-size: 12px;
}

#box .item_title li a {
	font-size: 12px;
}

td {
	padding: 4px 0px !important;
	vertical-align: middle !important;
}

hr {
	height: 3px;
	border: none;
	border-top: 1px double #FFF;
	margin: 10px 0px;
}
</style>
</head>
<body data-opts="horizontal">
	<div class="container">
		<div class="row" id="nav_box_title">
			<div class="nav title">
				<ul class="nav nav-pills">
					<li class="active"><a href="javascript:void(0)">进行中的借款</a></li>
					<li><a href="javascript:void(0)">逾期借款</a></li>
					<li><a href="javascript:void(0)">已完成借款</a></li>
				</ul>
			</div>
		</div>
		<div class="row" style="margin-top: 15px;">
			<div id="box">
			 
				<div class="item">
					<div class="item_title" id="box_going_repay">
						<ul class="nav nav-pills">
							<li><span class="item_title_sx">筛选&nbsp;&nbsp;：</span></li>
							<li class="active"><a href="javascript:void(0)"
								onclick="userloanApp.low_conditions(this)">全部(${pagehelper.total})</a></li>
							<li><a href="javascript:void(0)"
								onclick="userloanApp.low_conditions(this)" data-appstatus="0">审核中(${appstatus_0 == null ? 0:appstatus_0})</a></li>
							<li><a href="javascript:void(0)"
								onclick="userloanApp.low_conditions(this)" data-appstatus="3">投标中(${appstatus_3 == null ? 0:appstatus_3})</a></li>
							<li><a href="javascript:void(0)"
								onclick="userloanApp.low_conditions(this)" data-appstatus="4">流标(${appstatus_4 == null ? 0:appstatus_4})</a></li>
							<li><a href="javascript:void(0)"
								onclick="userloanApp.low_conditions(this)" data-appstatus="5">还款中(${appstatus_5 == null ? 0:appstatus_5})</a></li>
						</ul>
					</div>
					<div class="item-content" id="item-content">
						<jsp:include
							page="/WEB-INF/jsp/user/userloanapp/listTemplateGoingRepay.jsp"></jsp:include>
					</div>
					<div id="item-repaycontent"></div>
				</div>
			 
				<div class="item" style="display: none;">的地方</div>
			 
				<div class="item" style="display: none;">> 发的发的发</div>

				<!-- modal start -->
				<div class="modal fade" id="myRepayModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">还款选择框</h4>
							</div>
							<div class="modal-body" id="myRepayModal_body"></div>
							<div class="modal-footer" style="margin-top: 35px;">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</div>
				<!-- modal end -->

			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			 $("#nav_box_title").find(".nav-pills li").on("click",function(){
			 	 $(this).addClass("active").siblings().removeClass("active");
			 	 var index = $(this).index();
 			 	 $("#box").find(".item").hide().eq(index).show();
			 });			 
		});
	</script>
</body>
</html>