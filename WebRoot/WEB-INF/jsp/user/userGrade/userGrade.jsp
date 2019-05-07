<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
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

<title>会员专区</title>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	function buyGrade() {
		var action = "buyGrade.action";
		var callback = function(data) {
			if (data.indexOf("fail") < 0) {
				var newwin = window.open('', '', '');
				newwin.opener = null;
				newwin.document.write(data);
				newwin.document.close();
				location.href = "${pageContext.request.contextPath}/user/userGrade/userGrade.action";
			} else {
				alert("提示：操作失败。");
			}
		};
		$.post(action, null, callback);
	}
	/*
	 * 跳转积分兑换界面
	 */
	function forwardBonusPointsUI() {
		var action = "bonusPoints_UI.action";
		var callback = function(data) {
			$("#bonusPoints-modal-body").html(data);
		};
		$.post(action, null, callback);
	}
	/*
	 * 积分兑换会员等级
	 */
	function exchange() {
		var action = "exchange.action";
		var params = {
			"rankno" : $("#rankno-select").val()
		};
		var callback = function(data) {
			if (data == "success") {
				alert("提示：兑换成功。");
				location.href = "${pageContext.request.contextPath}/user/userGrade/userGrade.action";
			}
			if (data == "fail") {
				alert("提示：兑换失败。");
			}
		};
		$.post(action, params, callback, 'json');
	}
	/*
	 * 现金购买会员等级
	 */
	function forwardCashUI() {
		var action = "cash_UI.action";
		var callback = function(data) {
			$("#cash-modal-body").html(data);
		};
		$.post(action, null, callback);
	}
	/*
	 * 购买会员
	 */
	function cash() {
		var ugradeType = $("#ugradetype-select").val();
		var rankNo = $("#cash-ugrade-select").val();
		if (ugradeType != "" && rankNo != "") {
			var action = "cash.action";
			var params = {
				"uGradeType" : ugradeType,
				"rankno" : rankNo
			};
			var callback = function(data) {
				if (data.indexOf("fail") < 0) {
					var newwin = window.open('', '', '');
					newwin.opener = null;
					newwin.document.write(data);
					newwin.document.close();
					location.href = "${pageContext.request.contextPath}/user/userGrade/userGrade.action";
				} else {
					alert("提示：操作失败。");
				}
			}
			$.post(action, params, callback);
		}
	}
	/*
	 * 停止体验会员等级
	 */
	function backGrade() {
		if (confirm("确定停止当前体验会员吗？")) {
			var action = "backGrade.action";
			var callback = function(data) {
				if (data == "success") {
					alert("提示：停止体验会员，回调成功。");
					location.href = "${pageContext.request.contextPath}/user/userGrade/userGrade.action";
				}
				if (data == "fail") {
					alert("提示：停止体验会员，回调失败。");
				}
			};
			$.post(action, null, callback, 'json');
		}
	}
</script>
</head>
<body id="buy-body">
	<div style="width: 200px; margin: 50px;">
		${userName}
		<hr>
		当前身份：${uGradeName }<br> 会员类型：
		<c:if test="${uGradeType eq 1}">普通会员</c:if>
		<c:if test="${uGradeType eq 2}">体验会员</c:if>
		<hr>
		当前积分：${bonusPoints }<br> <br>
		<button onclick="buyGrade()" class="btn btn-default">普通会员</button>
		&nbsp;&nbsp;
		<button class="btn btn-default">体验会员</button>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<div align="right">
					<button class="btn btn-default" data-backdrop="static"
						data-toggle="modal" data-target="#bonusPointsModal"
						onclick="forwardBonusPointsUI()">积分兑换</button>
					&nbsp;&nbsp;&nbsp;
					<button class="btn btn-default" data-backdrop="static"
						data-toggle="modal" data-target="#cashModal"
						onclick="forwardCashUI()">现金购买</button>
					<table class="table table-hover">
						<thead>
							<tr class="text-center" style="background: #ccc;">
								<td>序号</td>
								<td>当前等级</td>
								<td>体验会员</td>
								<td>到期时间</td>
								<td>购买时间</td>
								<td>上一等级</td>
								<td>购买方式</td>
								<td>特权详情</td>
								<td>当前积分总额</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list }" varStatus="status">
								<tr class="text-center">
									<td>${status.count }</td>
									<td>${item.ugradename }</td>
									<td><c:if test="${item.dealmode != 2}">否</c:if> <c:if
											test="${item.dealmode eq 2}">是</c:if></td>
									<td><c:if test="${empty item.expirydate }">永久</c:if> <c:if
											test="${!empty item.expirydate }">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${item.expirydate }" />
										</c:if></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.dealtime }" /></td>
									<td>${item.oldugradename }</td>
									<td><c:if test="${item.paytype eq 1}">
									金额
								</c:if> <c:if test="${item.paytype eq 2}">
									积分
								</c:if> <c:if test="${item.paytype eq 3}">
									系统自动
								</c:if> <c:if test="${item.paytype eq 4}">
									管理员手动
								</c:if></td>
									<td><button class="btn btn-default">查看</button></td>
									<td>${item.bonuspoints}</td>
									<td><c:if test="${status.count !=1}">无</c:if> <c:if
											test="${status.count == 1}">
											<c:if test="${item.dealmode != 2}">无</c:if>
											<c:if test="${item.dealmode eq 2}">
												<button class="btn btn-default" onclick="backGrade()">停止体验</button>
											</c:if>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 积分兑换模态框 -->
	<div class="modal fade" id="bonusPointsModal" tabindex="-1"
		role="dialog" aria-labelledby="bonusPointsModalLabel">
		<div id="detail-modal" class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="bonusPointsModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行积分兑换操作
					</h4>
				</div>
				<div id="bonusPoints-modal-body" class="modal-body"></div>

				<div class="modal-footer">
					<button id="btn-bonusPoints" onclick="exchange()" type="button"
						class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 现金购买模态框 -->
	<div class="modal fade" id="cashModal" tabindex="-1" role="dialog"
		aria-labelledby="cashModalLabel">
		<div id="detail-modal" class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cashModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行现金购买操作
					</h4>
				</div>
				<div id="cash-modal-body" class="modal-body"></div>

				<div class="modal-footer">
					<button id="btn-cash" onclick="cash()" type="button"
						class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>