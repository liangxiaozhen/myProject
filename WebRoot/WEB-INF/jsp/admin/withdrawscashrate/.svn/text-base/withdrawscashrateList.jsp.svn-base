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
<title>莫邪科技</title>
<style type="text/css">
.rate-inputtext {
	text-align: center;
}

.text-center td {
	text-align: center;
	vertical-align: middle !important;
	border: 1px solid #666;
}

.t-center {
	vertical-align: text-top !important;
}
a:link {
	font-size: 12px;
	color: blue
}

a:visited {
	font-size: 12px;
	color: blue
}

a:hover {
	font-size: 12px;
	color: red
}
</style>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h2>提现费率表</h2>
				<br>
				<form id="form-select" method="post" action="queryAll.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>会员等级：</label>
					&nbsp;&nbsp;<select name="ugrade" id="ugrade-select">
						<option value="">--请选择--</option>
						<c:forEach var="ugrade" items="${ugrades }">
							<option value="${ugrade.ugrade}">${ugrade.ugradename}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;<br /> <label> 收费类型：</label>&nbsp; <select
						name="wdcmode" id="wdcmode-select">
						<option value=>--请选择--</option>
						<option value=1>定额</option>
						<option value=2>比例</option>
					</select><br /> <label>分段金额：</label>&nbsp; <input type="text"
						style="text-align: center;" size="8" name="minmoney"
						id="minmoney-select" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)">&nbsp;&nbsp;-&nbsp;&nbsp;<input
						size="8" type="text" style="text-align: center;" name="maxmoney"
						id="maxmoney-select" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)"> &nbsp;&nbsp; <font
						color='red'>* 大于最小金额，小于最大金额 </font> <br>
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
				</form>
				<div align="right">
					<div class="btn-group" role="group" aria-label="...">
						<button class="btn btn-primary" onclick="forwardInsertUI()"
							data-toggle="modal" data-target="#insertModal"
							data-backdrop="static" id="btnAdd">新增</button>
					</div>
				</div>
				<table class="table table-hover table-condensed" id="tb_personlist">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td rowspan="2">序号</td>
							<td rowspan="2">会员等级</td>
							<td colspan="2">提现金额范围</td>
							<td rowspan="2">收费类型</td>
							<td rowspan="2">定额手续费</td>
							<td colspan="3">比例手续费</td>
							<td rowspan="2">详情</td>
							<td rowspan="2">操作</td>
						</tr>
						<tr class="text-center" style="background: #ccc;">
							<td>最低金额</td>
							<td>最高金额</td>
							<td>提现费率</td>
							<td>手续费最低</td>
							<td>手续费最高</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td><a
									href="queryAll.action?ugrade=${item.ugrade }">${item.ugradeStr }</a></td>
								<td><fmt:formatNumber pattern="###,###,##0.00"
										value="${item.minmoney }" /></td>
								<td><fmt:formatNumber pattern="###,###,##0.00"
										value="${item.maxmoney }" /></td>
								<c:if test="${item.wdcmode eq 1 }">
									<td><span style="color: blue">定额</span></td>
									<td><fmt:formatNumber pattern="###,###,##0.00"
											value="${item.quota }" /></td>
									<td>--</td>
									<td>--</td>
									<td>--</td>
								</c:if>
								<c:if test="${item.wdcmode eq 2 }">
									<td><span style="color: green">比例</span></td>
									<td>--</td>
									<td>${item.wdcrate }%</td>
									<td><c:if test="${!empty item.minfee  }">
											<fmt:formatNumber pattern="###,###,##0.00"
												value="${item.minfee }" />
										</c:if> <c:if test="${empty item.minfee }">
											--
											</c:if></td>
									<td><c:if test="${!empty item.maxfee}">
											<fmt:formatNumber pattern="###,###,##0.00"
												value="${item.maxfee }" />
										</c:if> <c:if test="${empty item.maxfee}">
											--
											</c:if></td>
								</c:if>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">查看</button></td>
								<td>
									<div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}')">编辑</button>
										<button class="btn btn-default" data-toggle="modal"
											data-target="#delModal" data-backdrop="static" id="del-btn"
											onclick="forwardDelUI('${item.id}')">删除</button>
									</div>
								</td>
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


	<script type="text/javascript">
		$(function() {
			$("#ugrade-select").val("${wdcRate.ugrade}");
			$("#minmoney-select").val("${wdcRate.minmoney}");
			$("#maxmoney-select").val("${wdcRate.maxmoney}");
			$("#wdcmode-select").val("${wdcRate.wdcmode}");
		});
		function delByID() {// 根据ID删除
			var id = $("#del-id").val();
			var action = "delete.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				alert(data);
				if (data.indexOf("成功") > 0) {
					queryAllPerson("", "");
				}
			};
			$.post(action, params, callback, 'json');
		}

		function forwardDelUI(id) {// 请求转发 删除页面 UI ---1
			var action = "del_UI.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#del-modal-body").html(data);
			};
			$.post(action, params, callback);
		}
		function forwardInsertUI() {// 请求添加页面 UI
			var action = "insert_UI.action";
			var callback = function(data) {
				$("#insert-modal-body").html(data);
			};
			$.post(action, null, callback);
		}

		function forwardDetailUI(id) {// 请求转发 详情页面UI --1
			var action = "queryById.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#detail-modal-body").html(data);
			}
			$.post(action, params, callback);
		}

		function forwardUpdateUI(id) {// 请求转发 修改页面UI --2
			var action = "update_UI.action";
			var params = {
				"id" : id
			};
			var callback = function(data) {
				$("#update-modal-body").html(data);
			}
			$.post(action, params, callback);
		}
		function insert() {// 保存
			if (validateInsertForm()) {
				var action = "insert.action";
				var callback = function(data) {
					alert(data);
					if (data.indexOf("成功") > 0) {
						queryAllPerson("", "");
					}
				}
				$.post(action, $('#insert-rate-form').serialize(), callback,
						'json');
			}
		}
		function update() {// 编辑更新
			if (validateUpdateForm()) {
				var action = "update.action";
				var callback = function(data) {
					alert(data);
					if (data.indexOf("成功") > 0) {
						queryAllPerson("", "");
					}
				}
				$.post(action, $('#update-rate-form').serialize(), callback,
						'json');
			}
		}
		/*
		 * function createAddMoney() {//动态添加 var $tb = $("#tbody"); var $tr = $("<tr>");
		 * var $td = $("<td>"); var $minmoney = $("<input type='text'
		 * class='addminmoney' onblur='checkNum(this)'onkeyup='clearNoNum(event,this)'
		 * name='minmoneys' style='text-align:center;' size='5'>"); var $fspan = $("<span>
		 * 元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;</span>"); var $maxmoney = $("<input
		 * type='text' class='addmaxmoney' name='maxmoneys' size='5'
		 * onblur='checkNum(this)'onkeyup='clearNoNum(event,this)'
		 * style='text-align:center;line-height: 18px' placeholder='无穷'>"); var $aspan =
		 * $("<span> 元&nbsp;&nbsp;&nbsp; </span>"); var $btn = $("<input type='button'
		 * class='btn' value='-' />"); $btn.click(function() {
		 * $(this).parent().parent().remove(); }); $td.append($minmoney);
		 * $td.append($fspan); $td.append($maxmoney); $td.append($aspan);
		 * $td.append($btn); $tr.append($td); $tb.append($tr); }
		 */
	</script>
</body>

</html>