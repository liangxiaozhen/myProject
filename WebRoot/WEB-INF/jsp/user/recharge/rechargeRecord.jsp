<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/js/userRecharge/recharge.js"></script>
<script type="text/javascript" src="calendar/WdatePicker.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<title>userRechargeList</title>
<script type="text/javascript"> var basePath ="${basePath}"</script>
<script type="text/javascript">
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				$(this).attr("title", objString);
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		});
	}
	$(function() {
		$("[limit]").limit();
		//数据回显
		$("#status_select").val("${ur.status}");
		$("#starttime_select").val("${ur.starttimeStr}");
		$("#endtime_select").val("${ur.endtimeStr}");
	});
	
</script>
<style>
body {
	font-family: "微软雅黑"
}
</style>
</head>
<body>
	<div class="account_notice">
		<div class="account_notice" style="width: 100%">
			<h3>尊敬的干将会员，以下是您在莫邪网的充值记录，敬请审阅！</h3>
		</div>
		<div class="tab-content">
			<form id="form-select">
				<div style="margin-bottom: 20px">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>状态:</label>
					<select name="status" id="status_select" style="height: 30px">
						<option value="">全部</option>
						<option value="1">成功</option>
						<option value="2">失败</option>
						<option value="3">取消</option>
						<option value="4">待处理</option>
					</select> <label>充值开始时间:</label> <input type="text" id="starttime_select"
						name="starttime" class="Wdate" style="height: 30px"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <label>充值结束时间:</label>
					<input type="text" id="endtime_select" name="endtime" class="Wdate"
						style="height: 30px"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <input
						type="button" class="btn btn-primary btn-sm"
						onclick="selectByCondition()" value="查询记录" />
					<button type="reset" class="btn btn-primary btn-sm">重置</button>
				</div>
			</form>
			<!-- 先前的table的class样式table table-striped -->
			<table class="table table-hover">
				<tr class="text-center"
					style="background: #ccc; border-bottom: 2px #666 solid;">
					<td>序号</td>
					<td>订单号</td>
					<td>手续费类型</td>
					<td>金额</td>
					<td>手续费</td>
					<td>实际入账</td>
					<td>时间</td>
					<td>方式</td>
					<!-- <td>银行卡号</td> -->
					<td>银行</td>
					<td>是否勾兑</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
				<tbody>
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">
						<tr class="text-center">
							<td>${status.count}</td>
							<td>${item.rechargeno}</td>
							<td>
								<c:if test="${item.feeobjflag eq 'U'}">自付</c:if>
								<c:if test="${item.feeobjflag eq 'M'}">他付</c:if>
								<c:if test="${item.feeobjflag eq 'I'}">不付</c:if>
							</td>
							<%-- <td>${item.ubai.realname}</td> --%>
							<td>
								<c:if test="${!empty item.amount}">${df.format(item.amount)}</c:if>
								<c:if test="${empty item.amount}">0.00</c:if>
							</td>
							<td>
								<c:if test="${!empty item.recharfee}">${df.format(item.recharfee)}</c:if>
								<c:if test="${empty item.recharfee}">0.00</c:if>
							</td>
							<td style="color: blue">
								<c:if test="${!empty item.theamountcredited}">${df.format(item.theamountcredited)}</c:if>
								<c:if test="${empty item.theamountcredited}">0.00</c:if>
							</td>
							<td>${item.starttimeStr}</td>
							<td>
								<c:if test="${item.rechargetype eq '0'}">网银</c:if>
								<c:if test="${item.rechargetype eq '1'}">代扣</c:if>
								<c:if test="${item.rechargetype eq '2'}">快捷支付</c:if>
								<c:if test="${item.rechargetype eq '3'}">汇款充值</c:if>
								<c:if test="${item.rechargetype eq '4'}">企业网银</c:if>
							</td>
							<%-- <td>
								<c:if test="${!empty item.cardno}">${item.cardno}</c:if>
								<c:if test="${empty item.cardno}">-------</c:if>
							</td> --%>
							<td>${item.banknameStr}</td>
							<%-- <td>${item.uuid}</td> --%>
							<td>
								<c:if test="${item.isblending==1 or item.ismanblending==1}">
									<font style="color: green">已勾兑</font>
								</c:if> 
								<c:if test="${item.isblending==0 and item.ismanblending==0}">
									<font style="color:">未勾兑</font>
								</c:if>
							</td>
							<td>
								<c:if test="${item.status==1}">
									<font style="color: green">${item.statusStr}</font>
								</c:if> 
								<c:if test="${item.status==2}">
									<font style="color: red">${item.statusStr}</font>
								</c:if> 
								<c:if test="${item.status==3}">
									 ${item.statusStr}
								</c:if> 
								<c:if test="${item.status==4}">
									<font style="color: blue">${item.statusStr}</font>
								</c:if>
							</td>
							<td>
							
								<c:if test="${item.isblending==1 or item.ismanblending==1}">
									<c:if test="${item.status==3 and item.urid eq 0 and item.isexceptions==0}">
										<input type="button" onclick="rechargeagain(${item.id})" style="color: blue;" value="再次充值" />
									</c:if>
								</c:if> 
								<c:if test="${item.urid eq 1}">
									已再次充值
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="page_div">
				<%@ include file="../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>