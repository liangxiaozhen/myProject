<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

</head>
<script type="text/javascript">
	$(function(){
		$(".restricttype[value='${promoTotal.restricttype}']").attr('checked',true);
	})
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		}
		$.post(action, null, callback);
	}
	function insert() {
		if (validateInsert()) {
			var action = "insert.action";
			var callback = function(data) {
					alert(data);
					location.href = "queryAll.action";
			};
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	function forwardUpdateUI(id) {
		var action = "update_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) 
		{
			$("#update-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	function update() {
		if (validateUpdate()) 
		{
			var action = "update.action";
			var callback = function(data) {
				alert(data);
				location.href = "queryAll.action";
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	function updateTotal()
	{
		var action="updateTotal.action";
		var callback=function(data){
			alert(data);
			location.href="queryAll.action";
		}
		$.post(action,$("#save-form").serialize(),callback,'json');
	}
</script>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>推广限制设置</h3>
				<br>
				<form id="save-form">
					推广限制：<input type="radio" class="restricttype" name="restricttype"
						checked="checked" value=1><label>推广总人数限制</label>
					&nbsp;&nbsp;&nbsp; <input type="radio" class="restricttype"
						name="restricttype" value=2><label>推广层级人数限制</label><br>
					最大推广层级（层） &nbsp;&nbsp;：<input type="text" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)" name="levellimit"
						placeholder="请填写最大推广层级" style="text-align: center; width: 85px;"
						value="${promoTotal.levellimit }"><br> <input
						type="hidden" name="id" value="1">
					<c:if test="${promoTotal.restricttype eq 1 }">
					推广总人数限制（人）：<input type="text" onblur="checkNum(this)"
							onkeyup="clearNoNum(event,this)" name="totalnumlimit"
							placeholder="请填写推广人数限制" style="text-align: center; width: 85px;"
							value="${promoTotal.totalnumlimit }">&nbsp;&nbsp;&nbsp;&nbsp;
						<br>
					</c:if>
					<button type="button" onclick="updateTotal()"
						class="btn btn-default">保存</button>
				</form>
				<c:if test="${promoTotal.restricttype eq 2 }">
					<div align="right">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#insertModal" id="add-btn" data-backdrop="static"
							onclick="forwardInsertUI()">新增</button>
					</div>
					<table class="table  table-hover">
						<thead>
							<tr class="text-center" style="background: #ccc;">
								<td>序号</td>
								<td>推广层级</td>
								<td>推广人数限制</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="item" varStatus="status">
								<tr class="text-center">
									<td>${status.count }</td>
									<td><c:choose>
											<c:when test="${item.promolevel eq 2 }">
									第二层
								</c:when>
											<c:when test="${item.promolevel eq 3 }">
									第三层
								</c:when>
											<c:when test="${item.promolevel eq 4 }">
									第四层
								</c:when>
											<c:when test="${item.promolevel eq 5 }">
									第五层
								</c:when>
											<c:when test="${item.promolevel eq 6 }">
									第六层
								</c:when>
											<c:when test="${item.promolevel eq 7 }">
									第七层
								</c:when>
											<c:when test="${item.promolevel eq 8 }">
									第八层
								</c:when>
											<c:when test="${item.promolevel eq 9 }">
									第九层
								</c:when>
											<c:when test="${item.promolevel eq 10 }">
									第十层
								</c:when>
											<c:when test="${item.promolevel eq 11 }">
									第十一层
								</c:when>
											<c:when test="${item.promolevel eq 12 }">
									第十二层
								</c:when>
											<c:when test="${item.promolevel eq 13 }">
									第十三层
								</c:when>
											<c:when test="${item.promolevel eq 14 }">
									第十四层
								</c:when>
											<c:when test="${item.promolevel eq 15 }">
									第十五层
								</c:when>
											<c:when test="${item.promolevel eq 16 }">
									第十六层
								</c:when>
											<c:when test="${item.promolevel eq 17 }">
									第十七层
								</c:when>
											<c:when test="${item.promolevel eq 18 }">
									第十八层
								</c:when>
											<c:when test="${item.promolevel eq 19 }">
									第十九层
								</c:when>
											<c:when test="${item.promolevel eq 20 }">
									第二十层
								</c:when>
										</c:choose></td>
									<td>${item.levellimitnum }</td>
									<td><button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}') ">编辑</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>