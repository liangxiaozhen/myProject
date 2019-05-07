<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">var basePath="${basePath}";</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/userdebtattorn/userdebtattornlist.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
}

li:hover {
	cursor: pointer;
}

#filter li a {
	text-decoration: none;
}

.lively {
	padding: 4px 8px;
	background: #FF9900;
	border-radius: 4px;
	color: black;
}
</style>
<script type="text/javascript">
	//点击债转列表
	function userdebtrron(id){
		var action =basePath+"/user/userdebtattorn/userdebtrron.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
	}
	//点击承接人列表	
	function cjdebtrron(id){
		var action =basePath+"/user/undertakedebtattorn/cjdebtrron.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
	}
	
	/*** 分页查询投标记录 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#trForm").submit();
	}

	$(function() {
		var type = ${type};
		if(type != 0){
			$("#filter").find("span").each(function() {
				$(this).removeClass("lively");
				var status = $(this).data("status");
				if (status == type) {
					$(this).addClass("lively");
				}
			});
		}
	})
	
	function TenderCancle(orderNo){
		if(confirm("是否确定撤销此投标？")){
			var url= basePath+"/huifu/tenderCancle/tenderCancleCheck.action";
			var param = {
					"orderNo" : orderNo
			};
			var callback = function(data){
				var json = $.parseJSON(data);
				if(json.message=="success"){
					window.open(basePath+"/huifu/tenderCancle/tenderCancle.action?orderNo="+orderNo);
				}
			};
			$.post(url, param, callback);
		}
	}
</script>
</head>
<body>
	<div class="container" style="width: 90%;" id="alldiv">
		<h1>投资记录</h1>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a
				href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action">我的投资
			</a></li>
			<li onclick="userdebtrron(3)"><a data-toggle="tab">债券转让列表</a></li>
			<li onclick="cjdebtrron(4)"><a data-toggle="tab">债券承接列表</a></li>
		</ul>
		<div id="myTabContent" class="tab-content" style="margin-top: 20px;">
			<form method="post" role="form" id="trForm"
				action="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action">
				<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
					type="hidden" id="pageSize" name="pageSize" value="" /> <input
					type="hidden" name="type" value="${type}" />
			</form>
			<div>
				<ul class="list-inline" id="filter">
					<li><font>筛选：</font></li>
					<li><a
						href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action"><span
							class="lively" data-status="0">全部</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action?type=1"><span
							data-status="1">投标中</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action?type=7"><span
							data-status="7">已流标</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action?type=9"><span
							data-status="9">回款中</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action?type=10"><span
							data-status="10">已结清</span></a></li>
					<li><a
						href="${pageContext.request.contextPath}/user/tender/queryMyTenderRecord.action?type=11"><span
							data-status="11">逾期中</span></a></li>
				</ul>
			</div>
			<table
				class="table table-condensed table-bordered table-hover text-center">
				<thead>
					<tr style="background: #ccc">
						<td>投标属性</td>
						<td>投资日期</td>
						<td>投标订单号</td>
						<td>标的属性</td>
						<td>投标金额</td>
						<td>借款期限</td>
						<td>投标方式</td>
						<td>状态</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty pagehelper.list}">
						<c:forEach items="${pagehelper.list}" var="tender">
							<tr>
								<td><c:if test="${tender.utproperty eq 1}">原始投标</c:if> <c:if
										test="${tender.utproperty eq 2}">债转投标</c:if> <c:if
										test="${tender.utproperty eq 3}">假投标</c:if></td>
								<td><fmt:formatDate value="${tender.tbegintime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${tender.orderno}</td>
								<td><c:if test="${!empty tpropertymaps}">
										<c:forEach items="${tpropertymaps}" var="ttype">
											<c:choose>
												<c:when test="${tender.tproperty==ttype.key}">${ttype.value}
									</c:when>
											</c:choose>
										</c:forEach>
									</c:if></td>
								<td><fmt:formatNumber value="${tender.amount}" /></td>
								<td>${tender.tenderitem.loantime}${tender.tenderitem.dayormonth}</td>
								<td><c:if test="${tender.tendertype eq 1}">手动</c:if> <c:if
										test="${tender.tendertype eq 2}">自动</c:if></td>
								<td><c:if test="${!empty tstatusmaps}">
										<c:forEach items="${tstatusmaps}" var="status">
											<c:choose>
												<c:when test="${tender.tstatus==status.key}">${status.value}
												</c:when>
											</c:choose>
										</c:forEach>
									</c:if></td>
								<td><c:if test="${tender.isallowrevoke eq 1}">
										<input type="button" class="btn btn-danger" value="撤销"
											onclick="TenderCancle('${tender.orderno}',this)" />
									</c:if></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty pagehelper.list}">
						<tr>
							<td colspan="100">没有相关数据</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<c:if test="${!empty pagehelper.list}">
				<div id="page_div"><%@ include
						file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
			</c:if>
		</div>
	</div>

	<!-- 设置模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document" style="width: 1000px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">债转设置</h4>
				</div>
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="ReleaseYanz()" id="frozen">发布</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 详情模态框 -->
	<div class="modal fade" id="myModaldstails" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body" id="myModaldstail"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 逾期标的模态框 -->
	<div class="modal fade" id="myOverdue" tabindex="-1" role="dialog"
		aria-labelledby="myOverdueLabel" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myOverdueLabel">逾期标</h4>
				</div>
				<div class="modal-body" id="myoverdue"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 审核模态框 -->
	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
		aria-labelledby="auditModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="auditModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行审核操作
					</h4>
				</div>
				<div id="audit-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="auditSuccess()">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>