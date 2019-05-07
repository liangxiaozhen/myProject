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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>基本</b></font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			本 标 签 ：<label>${userPromo.promocode }</label>
		</div>
		<div class="col-md-6">
			上 标 签 ：<label>${userPromo.suppromocode}</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-9 col-md-offset-1">
			注册时间：<label><fmt:formatDate value="${userPromo.regdate }"
					pattern="yyyy-MM-dd HH:mm:ss" /></label>
		</div>
	</div>
	<c:if test="${fn:length(list)>0}">
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>第三方推广设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-1"></div>
			<c:forEach items="${list }" var="item">
				<div class="col-md-3">
					<c:if test="${item.isopen eq 0 }">
						<input type="checkbox" id="${item.id }" onchange="isOpen(this)">${item.thirdpartyname }</c:if>
					<c:if test="${item.isopen eq 1 }">
						<input type="checkbox" id="${item.id }" onchange="isOpen(this)"
							checked="checked">${item.thirdpartyname }</c:if>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${fn:length(supList)>0}">
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>推广上级</b></font>
			</div>
		</div>
		<hr>
		<div class="row"
			style="line-height: 0px; margin-top: 20px; margin-left: 2px; margin-right: 2px;">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr class="text-center" style="background: #ccc;">
						<td>序号</td>
						<td>推广资质</td>
						<td>真实姓名</td>
						<td>用户名</td>
						<td>是否有效</td>
						<td>层数</td>
					</tr>
					<c:forEach items="${supList }" var="item" varStatus="status">
						<tr class="text-center">
							<td>${status.count }</td>
							<td>${item.proxygradename }</td>
							<td>${item.name }</td>
							<td>${item.loginname }</td>
							<td><c:if test="${!empty item.isvalid }">
									<span style="color: green">有效</span>
								</c:if> <c:if test="${empty item.isvalid }">
									<span style="color: red">无效</span>
								</c:if></td>
							<td><c:choose>
									<c:when test="${item.promolevels eq 1 }">
									上二级
								</c:when>
									<c:when test="${item.promolevels eq 2 }">
									上三级
								</c:when>
									<c:when test="${item.promolevels eq 3 }">
									上四级
								</c:when>
									<c:when test="${item.promolevels eq 4 }">
									上五级
								</c:when>
									<c:when test="${item.promolevels eq 5 }">
									上六级
								</c:when>
									<c:when test="${item.promolevels eq 6 }">
									上七级
								</c:when>
									<c:when test="${item.promolevels eq 7 }">
									上八级
								</c:when>
									<c:when test="${item.promolevels eq 8 }">
									上九级
								</c:when>
									<c:when test="${item.promolevels eq 9 }">
									上十级
								</c:when>
									<c:when test="${item.promolevels eq 10 }">
									上十一级
								</c:when>
									<c:when test="${item.promolevels eq 11 }">
									上十二级
								</c:when>
									<c:when test="${item.promolevels eq 12 }">
									上十三级
								</c:when>
									<c:when test="${item.promolevels eq 13 }">
									上十四级
								</c:when>
									<c:when test="${item.promolevels eq 14 }">
									上十五级
								</c:when>
									<c:when test="${item.promolevels eq 15 }">
									上十六级
								</c:when>
									<c:when test="${item.promolevels eq 16 }">
									上十七级
								</c:when>
									<c:when test="${item.promolevels eq 17 }">
									上十八级
								</c:when>
									<c:when test="${item.promolevels eq 18 }">
									上十九级
								</c:when>
									<c:when test="${item.promolevels eq 19 }">
									上二十级
								</c:when>
									<c:when test="${item.promolevels eq 20 }">
									上二十一级
								</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:if>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>本级</b></font>
		</div>
	</div>
	<hr>
	<div class="row"
		style="line-height: 0px; margin-top: 20px; margin-left: 2px; margin-right: 2px;">
		<div class="col-md-12">
			<table class="table table-hover">
				<tr class="text-center" style="background: #ccc;">
					<td>序号</td>
					<td>推广资质</td>
					<td>真实姓名</td>
					<td>用户名</td>
					<td>层数</td>
				</tr>
				<tr class="text-center">
					<td><span style="color: blue;">1</span></td>
					<td><span style="color: blue;">${userPromo.proxygradename }</span></td>
					<td><span style="color: blue;">${userPromo.name }</span></td>
					<td><span style="color: blue;">${userPromo.loginname }</span></td>
					<td><span style="color: blue;">本一级</span></td>
				</tr>
			</table>
		</div>
	</div>
	<c:if test="${fn:length(childList)>0}">
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>推广下级</b></font>
			</div>
		</div>
		<hr>
		<div class="row"
			style="line-height: 0px; margin-top: 20px; margin-left: 2px; margin-right: 2px;">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr class="text-center" style="background: #ccc;">
						<td>序号</td>
						<td>推广资质</td>
						<td>真实姓名</td>
						<td>用户名</td>
						<td>是否有效</td>
						<td>层数</td>
					</tr>
					<c:forEach items="${childList }" var="item" varStatus="status">
						<tr class="text-center">
							<td>${status.count }</td>
							<td>${item.proxygradename }</td>
							<td>${item.name }</td>
							<td>${item.loginname }</td>
							<td><c:if test="${!empty item.isvalid }">
									<span style="color: green">有效</span>
								</c:if> <c:if test="${empty item.isvalid }">
									<span style="color: red">无效</span>
								</c:if></td>
							<td><c:choose>
									<c:when test="${item.promolevels eq -1 }">
									下二级
								</c:when>
									<c:when test="${item.promolevels eq -2 }">
									下三级
								</c:when>
									<c:when test="${item.promolevels eq -3 }">
									下四级
								</c:when>
									<c:when test="${item.promolevels eq -4 }">
									下五级
								</c:when>
									<c:when test="${item.promolevels eq -5 }">
									下六级
								</c:when>
									<c:when test="${item.promolevels eq -6 }">
									下七级
								</c:when>
									<c:when test="${item.promolevels eq -7 }">
									下八级
								</c:when>
									<c:when test="${item.promolevels eq -8 }">
									下九级
								</c:when>
									<c:when test="${item.promolevels eq -9 }">
									下十级
								</c:when>
									<c:when test="${item.promolevels eq -10 }">
									下十一级
								</c:when>
									<c:when test="${item.promolevels eq -11 }">
									下十二级
								</c:when>
									<c:when test="${item.promolevels eq -12 }">
									下十三级
								</c:when>
									<c:when test="${item.promolevels eq -13 }">
									下十四级
								</c:when>
									<c:when test="${item.promolevels eq -14 }">
									下十五级
								</c:when>
									<c:when test="${item.promolevels eq -15 }">
									下十六级
								</c:when>
									<c:when test="${item.promolevels eq -16 }">
									下十七级
								</c:when>
									<c:when test="${item.promolevels eq -17 }">
									下十八级
								</c:when>
									<c:when test="${item.promolevels eq -18 }">
									下十九级
								</c:when>
									<c:when test="${item.promolevels eq -19 }">
									下二十级
								</c:when>
									<c:when test="${item.promolevels eq -20 }">
									下二十一级
								</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</c:if>
	<script type="text/javascript">
		function isOpen(obj) {
			var action = "";
			var params = {
				"id" : obj.id
			};
			if (obj.checked) {
				action = "${pageContext.request.contextPath}/admin/userPromoThirdParty/isUse.action;"
			} else {
				action = "${pageContext.request.contextPath}/admin/userPromoThirdParty/cancel.action;"
			}
			var callback = function(data) {
				alert(data);
			}
			$.post(action, params, callback, 'json');
		}
	</script>
</body>
</html>