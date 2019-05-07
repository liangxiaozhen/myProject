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
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}

.left {
	text-align: right;
}

.right {
	text-align: left;
}

.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function() {
		backfilldata();
	});
	function forwardDetailUI(cashno) {// 跳转详情页面
		var action = "queryByCashNo.action";
		var params = {
			"cashNo" : cashno
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//ajax 回调函数
	function update_success_callback(returnData) {
		alert(returnData);
		queryAllPerson("${pagehelper.pageNum}", "${pagehelper.pageSize}");
	}

	//回填数据
	function backfilldata() {
		$("#realname").val("${backfilldata.ubai.realname}");
		$("#operator").val("${backfilldata.operator}");
		$("#amount").val("${backfilldata.amount}");
		$("#applydate").val("${backfilldata.applydateStr}");
		$("#status").val("${backfilldata.status}");
		$("#auditman").val("${backfilldata.auditman}");
		$("#originclient").val("${backfilldata.originclient}");
	}
	//人工对账
	function reconciliation(cashNo) {
		if (confirm("请问确定要勾兑吗？")) {
			var action = "reconciliation.action";
			var params = {
				"cashno" : cashNo
			};
			$.post(action, params, function() {
				queryAllPerson("${pagehelper.pageNum}",
						"${pagehelper.pageSize}");
			});
		}
	}
	//编辑UI
	function forwardUpdateUI(cashNo) {
		var action = "queryByCashNo.action";
		var params = {
			"cashNo" : cashNo,
			"uid" : 2
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//编辑
	function update() {
		var action = "update.action";
		var callback = function(data) {
			update_success_callback(data);
		}
		$.post(action, $('#update-form').serialize(), callback);
	}
</script>
<title>莫邪科技</title>
</head>
<body>
	<div style="width: 85%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>用户提现表</h3>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
					<div class="col-lg-12 column">
						<label>会 员 名 ：</label> <input placeholder="用户名" id="realname"
							type="text" name="ubai.realname"><span
							style="width: 20px;"></span> <label>提现金额：</label> <select
							name="operator" id="operator">
							<option value="">--请选择--</option>
							<option value="小于">小于</option>
							<option value="等于">等于</option>
							<option value="大于">大于</option>
						</select> <input placeholder="提现金额" type="text" onblur="checkNum(this)"
							onkeyup="clearNoNum(event,this)" id="amount" name="amount"><br>
						<label>提现时间：</label> <input type="text" id="applydate"
							name="applydate" class="Wdate"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <label>提现状态：</label>
						<select id="status" name="status">
							<option value="">---请选择提现状态---</option>
							<option value=0>取消</option>
							<option value=1>成功</option>
							<option value=2>失败</option>
						</select> <label>设备来源：</label> <select id=originclient name="originclient">
							<option value="">---请选择设备来源---</option>
							<option value=1>PC端</option>
							<option value=2>IPad</option>
							<option value=3>Android端</option>
							<option value=4>IOS端</option>
						</select> <br>
						<button class="btn btn-default" type="submit">查询</button>
						<button class="btn btn-default" type="reset">重置</button>
					</div>
				</form>
				<br> <br>
				<table class="table  table-hover table-condensed" id="tb_personlist">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<!--  
							<c:if
								test="${action eq 'queryAllForAudit.action' or action eq 'queryAllForCancel.action'}">
								<td><input type="checkbox" onclick="selectAll(this)"></td>
							</c:if>-->
							<td>序号</td>
							<td>订单号</td>
							<td>申请日期</td>
							<td>用户名称</td>
							<td>提现金额</td>
							<td>到账方式</td>
							<!--  <td>提现审核</td>-->
							<td>提现状态</td>
							<td>详情信息</td>
						</tr>
					</thead>
					<tbody id="t-body">
						<c:forEach items="${pagehelper.list }" var="item" varStatus="status">
							<tr class="text-center">
								<td>${status.count}</td>
								<td>${item.cashno }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${item.applydate}" /></td>
								<td>${item.username }</td>
								<td><fmt:formatNumber pattern="##,###,##0.00"
										value="${item.amount }" /></td>
								<td><c:choose>
										<c:when test="${item.cashchl eq 'FAST'}">快速提现</c:when>
										<c:when test="${item.cashchl eq 'GENERAL'}">正常提现</c:when>
										<c:when test="${item.cashchl eq 'IMMEDIATE'}">即时提现</c:when>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${item.status eq 0 }">
											<font color='blue'>取消</font>
										</c:when>
										<c:when test="${item.status eq 1 }">
											<font color='green'>成功</font>
										</c:when>
										<c:when test="${item.status eq 2 }">
											<font color='red'>失败</font>
										</c:when>
									</c:choose></td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" data-backdrop="static"
										onclick="forwardDetailUI('${item.cashno}')">查看</button></td>
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
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</html>