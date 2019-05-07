<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#thirdpartyname-select").val("${uptp.thirdpartyname}");
		$("#loginname-text").val("${uptp.ubai.loginname}");
		$("#realname-text").val("${uptp.ubai.realname}");
	})

	function forwardCancelUI(id, count) {
		$("#cancelID").val(id);
		$("#cancel_count_lb").html(count);
	}
	function forwardIsUserUI(id, count) {
		$("#isUseID").val(id);
		$("#isUse_count_lb").html(count);
	}
	function cancelUse() {
		var id=$("#cancelID").val();
		var action = "cancel.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			queryAllPerson("${pagehelper.pageNum}", "${pagehelper.pageSize}");
			alert(data);
		};
		$.post(action,params,callback,'json');
	}
	function isUse() {
		var id=$("#isUseID").val();
		var action = "isUse.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			queryAllPerson("${pagehelper.pageNum}", "${pagehelper.pageSize}");
			alert(data);
		};
		$.post(action,params,callback,'json');
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>用户推广第三方设置</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum"> <input
						type="hidden" id="pageSize" name="pageSize"> 用户名：<input
						id="loginname-text" type="text" name="ubai.loginname"
						style="text-align: center;">&nbsp;&nbsp; 真实姓名：<input
						id="realname-text" type="text" name="ubai.realname"
						style="text-align: center;">&nbsp;&nbsp; 第三方公司名称：<select
						id="thirdpartyname-select" name="thirdpartyname">
						<option value="">--请选择--</option>
						<option value="双乾支付">双乾支付</option>
						<option value="汇付天下">汇付天下</option>
						<option value="汇潮支付">汇潮支付</option>
					</select> <br>
					<button type="submit" class="btn btn-default">查询</button>
					&nbsp;&nbsp;
					<button type="reset" class="btn btn-default">重置</button>
				</form>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>用户ID</td>
							<td>真实姓名</td>
							<td>用户名</td>
							<td>第三方公司名称</td>
							<td>推广开关</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.upid }</td>
								<td>${item.ubai.realname }</td>
								<td>${item.ubai.loginname }</td>
								<td>${item.thirdpartyname}</td>
								<td><c:if test="${item.isopen eq 0 }">
										<button class="btn btn-default" data-backdrop="static"
											data-toggle="modal"
											data-target="#userPromoThirdPartyIsuseModal"
											onclick="forwardIsUserUI('${item.id}','${status.count }')">
											<span class="green">启用</span>
										</button>
									</c:if> <c:if test="${item.isopen eq 1 }">
										<button class="btn btn-default" data-backdrop="static"
											data-toggle="modal"
											data-target="#userPromoThirdPartyCancelModal"
											onclick="forwardCancelUI('${item.id}','${status.count }')">
											<span class="red">停用</span>
										</button>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 停用  推广链接-->
	<div class="modal fade" id="userPromoThirdPartyCancelModal"
		tabindex="-1" role="dialog"
		aria-labelledby="userPromoThirdPartyCancelModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userPromoThirdPartyCancelModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<input type="hidden" id="cancelID" /> 您是否停用序号为 <span
						style="color: red;"><label id="cancel_count_lb"></label></span>
					的第三方设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 启用  推广链接 -->
	<div class="modal fade" id="userPromoThirdPartyIsuseModal"
		tabindex="-1" role="dialog"
		aria-labelledby="userPromoThirdPartyIsuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userPromoThirdPartyIsuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<input type="hidden" id="isUseID" /> 您是否启用序号为 <span
						style="color: red;"><label id="isUse_count_lb"></label></span>
					的第三方设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>