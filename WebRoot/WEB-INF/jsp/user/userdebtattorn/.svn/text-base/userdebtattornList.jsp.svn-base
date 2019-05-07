<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/dateproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/userdebtattorn/list.js"></script>
<title>itemList</title>
<style>
table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
//刷新页面
function refurbish() {
	//window.location.href = "${pageContext.request.contextPath}/admin/rechargeRate/query.action?pageNum=${pagehelper.pageNum}";
  queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
}
//查询所有数据
function queryAllPerson(pageNum, pageSize) {
	var ispartda = $("#ispartda_select").val();
	var dastatus = $("#dastatus_select").val();
	var setstarttime = $("#start_settime").val();
	var setendtime = $("#end_settime").val();
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	var f = document.getElementById('userDebtAttorn-from');
	f.action =  "${pageContext.request.contextPath}/user/userdebtattorn/queryAllRecord.action";
	f.submit();
}
$(function(){
	$("#ispartda_select").val("${userdebtattorn.ispartda}");
	$("#dastatus_select").val("${userdebtattorn.dastatus}");
	$("#start_settime").val("${userdebtattorn.setstarttimeStr}");
	$("#end_settime").val("${userdebtattorn.setendtimeStr}");
});
</script>
</head>

<body>

	<div id="itemList_table" style="font-family: 微软雅黑">
		<br />
		<div style="margin-left: 45px; font-weight: bold; font-size: 20px">债转列表</div>
		<div>
			<div
				style="float: left; margin-left: 25px; width: 95%; margin-top: 20px">
				<form method="post" id="userDebtAttorn-from">
					<div style="margin-left: 20px">
						<input type="hidden" id="pageNum" name="pageNum" /> <input
							type="hidden" id="pageSize" name="pageSize" /> <label>是否全部:</label>
						<select name="ispartda" id="ispartda_select">
							<option value="">请选择--</option>
							<option value="0">全部</option>
							<option value="1">部分</option>
						</select> <label>债转状态:</label> <select name="dastatus" id="dastatus_select">
							<option value="">请选择--</option>
							<option value="1">待审核</option>
							<option value="2">已上架</option>
							<option value="3">已下架</option>
							<option value="4">已完成</option>
						</select> <label style="margin-left: 20px">设置时间:</label> <input type="text"
							class="Wdate" name="setstarttime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							id="start_settime" /> ---<input type="text" class="Wdate"
							name="setendtime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							id="end_settime" />
						<button type="submit" class="btn">查询</button>
						<button type="reset" class="btn">重置</button>
					</div>

				</form>
				<!-- <div style=" margin-left:100%;margin-bottom: 5px;float: right">
			     <button id="add_userdebtattorn" class="btn" onclick="insertUserDebtattorn()">新增债转设置</button>
			</div> -->
			</div>
			<table class="table table-hover" id="tableterm"
				style="width: 95%; text-align: center; margin-left: 35px">
				<thead>
					<tr style="background-color: #CCCCCC;" class="text-center2">
						<td>序号</td>
						<td>标号</td>
						<td>债转订单号</td>
						<td>定向债转码</td>
						<td>投标订单号</td>
						<!-- <td>转让人用户名</td> -->
						<td>投标金额</td>
						<td>债转金额</td>
						<td>是否全部</td>
						<td>债转系数</td>
						<td>债转状态</td>
						<td>债转次数</td>
						<td>查看</td>
						<!-- <td>设置人</td>
							<td>设置时间</td>
							<td>审核人</td>
							<td>审核时间</td> -->
					</tr>
				</thead>
				<tbody id="text" style="text-align: center;">
					<!-- 这里面${item.id }是点的model里面的属性 -->
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

						<tr id="item_tr_${item.id }" class="text-center2">
							<%-- <td>${item.id }</td> --%>
							<td>${status.count}</td>
							<td>${item.tenderid}</td>
							<td>${item.daorderno}</td>
							<td>${item.udapass}</td>
							<td>${item.torderno}</td>
							<%-- <td>${item.baseid}</td> --%>
							<td><c:if test="${!empty item.amount}">${df.format(item.amount)}</c:if></td>
							<td><c:if test="${!empty item.daamount}">${df.format(item.daamount)}</c:if></td>

							<td><c:if test="${item.ispartda eq 0}">全部</c:if> <c:if
									test="${item.ispartda eq 1}">部分</c:if></td>
							<td>${item.coefficient}</td>
							<td><c:if test="${item.dastatus eq 1}">待审核</c:if> <c:if
									test="${item.dastatus eq 2}">已上架</c:if> <c:if
									test="${item.dastatus eq 3}">已下架</c:if> <c:if
									test="${item.dastatus eq 4}">已完成</c:if></td>
							<td>第${item.datimes}次</td>
							<td><button class="btn" data-toggle="modal"
									data-dismiss="modal" data-target="#myModal"
									onclick="details('${item.daorderno}')">详情</button></td>
							<%-- <td>${item.setman}</td>
								<td><c:if test="${!empty item.settime}">${sf.format(item.settime)}</c:if></td>
								<td>${item.auditman}</td>
								<td><c:if test="${!empty item.audittime}">${item.audittime}</c:if></td> --%>

						</tr>

					</c:forEach>
				</tbody>
			</table>
			<div id="page_div" style="margin-left: 35px;">
				<%@ include file="./../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>

	<!-- 详情模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
