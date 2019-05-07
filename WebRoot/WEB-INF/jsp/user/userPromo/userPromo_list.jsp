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
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/userPromo/css/userPromo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
<script type="text/javascript">
	function copyUrl() {
		var url = document.getElementById("text-url");
		url.select();
		document.execCommand("Copy"); // 执行浏览器复制命令 
		alert("已复制好，可贴粘。");
	}
	function forwardUpdateCodeUI(id) {
		var action = "updateCode_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#updateCode-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	function forwardUpdateUI(id) {
		var action = "update_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	function updateCode() {
		if (validateCode()) {
			var action = "updateCode.action";
			var callback = function(data) {
				if (data == 'success') {
					alert("提示：操作成功。");
					location.href = "${pageContext.request.contextPath }/user/userPromo/userPromo.action";
				} else if (data == 'fail') {
					alert("提示：操作失败。");
				}
			}
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	function moyePromo() {
		window.open("http://www.ganjiang.com/promo?customer=" + "600012345"
				+ "&id=" + "${userPromo.id}");
	}
	function update() {
		var action = "update.action";
		var callback = function(data) {
			if (data == 'success') {
				alert("提示：操作成功。");
				location.href = "${pageContext.request.contextPath }/user/userPromo/userPromo.action";
			} else if (data == 'fail') {
				alert("提示：操作失败。");
			}
		}
		$.post(action, $("#update-form").serialize(), callback, 'json');
	}
</script>
</head>
<body style="padding: 50px;">
	<c:forEach items="${uptpList }" var="item">
		<c:if test="${item.isopen eq 1 }">
			<a href="${item.thirdpartycode }" target="_blank">${item.thirdpartycode }</a>${item.thirdpartyname}<br>
		</c:if>
	</c:forEach>
	<div class="container" align="center">
		<div class="wcg-ucenter-right-friendlink">
			<div style="width: 798px; height: 60px;" align="center">
				<div style="width: 360px; height: 36px; margin-top: 20px;">
					<font size="5" color="#ff5641">邀请好友赚不停，现金奖励再升级</font>
				</div>
			</div>
			<div style="width: 798px; height: 60px;" align="center">
				<p>
					<span class="span_left"><font size="3">邀请码：</font></span> <span
						class="span_right">${userPromo.promocode}</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${userPromo.ismodify eq 0 }">
						<button class="btn btn-default" data-toggle="modal"
							data-target="#updateCodeModal" id="update-btn"
							data-backdrop="static"
							onclick="forwardUpdateCodeUI('${userPromo.id}')">更改</button>
						<br>
						<span style="color: red">注：邀请码能且只能更改一次。</span>
					</c:if>
				</p>
			</div>
			<div style="width: 527px; height: 50px;" align="center">
				<input id="text-url" class="form-control" type="text"
					readonly="readonly"
					value="<%=basePath%>register/userBaseInfo/toReg.action?promoCode=${userPromo.promocode}">
				<div style="margin-top: 5px;">
					<c:if test=""></c:if>
					&nbsp;&nbsp;
					<button class="btn btn-default" onclick="moyePromo()">莫邪推广</button>
					<button class="btn btn-default" onclick="copyUrl()">复制链接</button>
				</div>
			</div>
		</div>

		<div class="number-box">
			<ul>
				<li>
					<div>获得代金券</div>
					<div>
						<i>0.00</i>元
					</div>
				</li>
				<li><span class="fuhao">+</span></li>
				<li>
					<div>获得红包</div>
					<div>
						<i>0.00</i>元
					</div>
				</li>
				<li><span class="fuhao">=</span></li>
				<li>
					<div>获得奖励总额</div>
					<div>
						<i>0.00</i>元
					</div>
				</li>
			</ul>
		</div>

		<div class="table-box">
			<div class="box-title">
				<span></span> <font>邀请记录</font>
			</div>
			<div class="div-table">
				<div align="right">
					<font style="font-size: 14px; margin-right: 100px;">邀请人数：${fn:length(childList) }人</font>
				</div>
				<table style="width: 100%">
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>用户名</th>
						<th>注册时间</th>
						<th>推广层级</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${childList }" var="item" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${item.name }</td>
							<td>${item.loginname }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
									value="${item.regdate }" /></td>
							<td><c:choose>
									<c:when test="${item.promolevels eq 1 }">
									下二级
								</c:when>
									<c:when test="${item.promolevels eq 2 }">
									下三级
								</c:when>
									<c:when test="${item.promolevels eq 3 }">
									下四级
								</c:when>
									<c:when test="${item.promolevels eq 4 }">
									下五级
								</c:when>
									<c:when test="${item.promolevels eq 5 }">
									下六级
								</c:when>
									<c:when test="${item.promolevels eq 6 }">
									下七级
								</c:when>
									<c:when test="${item.promolevels eq 7 }">
									下八级
								</c:when>
									<c:when test="${item.promolevels eq 8 }">
									下九级
								</c:when>
									<c:when test="${item.promolevels eq 9 }">
									下十级
								</c:when>
									<c:when test="${item.promolevels eq 10 }">
									下十一级
								</c:when>
									<c:when test="${item.promolevels eq 11 }">
									下十二级
								</c:when>
									<c:when test="${item.promolevels eq 12 }">
									下十三级
								</c:when>
									<c:when test="${item.promolevels eq 13 }">
									下十四级
								</c:when>
									<c:when test="${item.promolevels eq 14 }">
									下十五级
								</c:when>
									<c:when test="${item.promolevels eq 15 }">
									下十六级
								</c:when>
									<c:when test="${item.promolevels eq 16 }">
									下十七级
								</c:when>
									<c:when test="${item.promolevels eq 17 }">
									下十八级
								</c:when>
									<c:when test="${item.promolevels eq 18 }">
									下十九级
								</c:when>
									<c:when test="${item.promolevels eq 19 }">
									下二十级
								</c:when>
									<c:when test="${item.promolevels eq 20 }">
									下二十一级
								</c:when>
								</c:choose></td>
							<td>${item.subremark }</td>
							<td><c:if test="${item.promolevels eq 1 }">
									<button class="btn btn-default" data-toggle="modal"
										data-target="#updateModal" id="update-btn"
										data-backdrop="static" onclick="forwardUpdateUI('${item.id}')">编辑</button>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- 编辑 模态框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="editModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行编辑操作
					</h4>
				</div>
				<div id="update-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="update()">编辑</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 编辑邀请码 模态框 -->
	<div class="modal fade" id="updateCodeModal" tabindex="-1"
		role="dialog" aria-labelledby="editModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="editModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行编辑操作
					</h4>
				</div>
				<div id="updateCode-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="updateCode()">编辑</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>