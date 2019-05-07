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
<title>我要理财</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
label{font-weight: normal;}
</style>
</head>
<body>
	<div class="container" style="width: 60%; margin-top: 50px;">
		<form id="tender" class="form-horizontal"
<%-- 			action="${pageContext.request.contextPath}/user/initiativeBid/initiativeTender.action" --%>
			action="${pageContext.request.contextPath}/user/tenderBase/initiativeTender.action"
			method="post" target="_blank" role="form">
			<input id="tenderId" name="id" hidden="hidden" value="${item.id}" />
			<input id="isfaketender" name="isfaketender" type="hidden"
				value="${item.isfaketender}" /> <input id="iscancheattender"
				name="iscancheattender" type="hidden" value="${iscancheattender}" />
			<div class="form-group" style="line-height: 30px;">
				<div class="col-md-5 text-right">
					<label>项目可投余额</label>
				</div>
				<div class="col-md-4">
					<label><fmt:formatNumber type="CURRENCY"
							value="${surplusAmout}" /></label>
				</div>
			</div>
			<div class="form-group" style="line-height: 30px;">
				<div class="col-md-5 text-right">
					<label>我的可用余额</label>
				</div>
				<div class="col-md-4">
					<label><fmt:formatNumber type="CURRENCY"
							value="${avlbalance}" /></label> <a
						href="<%=basePath%>/user/userRecharge/rechargeList.action"
						style="margin-left: 20px;">去充值</a>
				</div>
			</div>
			<div class="form-group" style="line-height: 30px;">
				<div class="col-md-5 text-right">
					<label>请输入投标金额</label>
				</div>
				<div class="col-md-3">
					<div class="input-group">
						<input type="text" id="tenderMoney" name="amount"
							class="form-control" /><span class="input-group-addon">元</span>
					</div> <label id="tips" style="color: red;"></label>
				</div>
			</div>
			<c:if test="${item.isappointtender eq 0}">
				<div class="form-group" style="line-height: 30px;">
					<div class="col-md-5 text-right">
						<label>约标码</label>
					</div>
					<div class="col-md-3">
						<input name="tpass" class="form-control">
					</div>
				</div>
			</c:if>
			<c:if test="${item.isaplus eq 1}">
				<div class="form-group" style="line-height: 30px;">
					<div class="col-md-5 text-right"><label>加息券编号</label></div>
					<div class="col-md-4">
						<c:choose>
							<c:when test="${!empty interestRateCoupons}">
								<c:if test="${!empty interestRateCoupons}">
									<c:forEach items="${interestRateCoupons}" var="coupon">
										<span class="form-control" style="border: none;"><input
											type="checkbox" name="uircid" value="${coupon.id}">${coupon.uircno} 券利率${coupon.icrate}</span>
									</c:forEach>
								</c:if>
							</c:when>
							<c:otherwise>
								<span class="form-control" style="color: #ff9800;">*暂无可用加息券</span>
								<br />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="form-group" style="line-height: 30px;">
					<div class="col-md-5 text-right"><label>红包编号</label></div>
					<div class="col-md-4">
						<c:choose>
							<c:when test="${!empty likeVouchers || !empty fakeVouchers}">
								<c:if test="${!empty likeVouchers}">
									<c:forEach items="${likeVouchers}" var="like">
										<span class="form-control" style="border: none;"><input
											type="checkbox" name="ureid" value="${like.id}">${like.ureno} 金额${like.redenvelope}</span>
									</c:forEach>
								</c:if>
								<c:if test="${!empty fakeVouchers}">
									<c:forEach items="${fakeVouchers}" var="fake">
										<span class="form-control" style="border: none;"><input
											type="checkbox" name="ureid" value="${fake.id}">${fake.ureno} 金额${fake.redenvelope}</span>
									</c:forEach>
								</c:if>
							</c:when>
							<c:otherwise>
								<span class="form-control" style="color: #ff9800;">*暂无可用红包</span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:if>
			<div class="form-group" style="line-height: 100px;">
				<div class="col-md-7 text-right">
					<button type="button" class="btn btn-success"
						onclick="toTender(this)">立即投资</button>
					<button type="button" class="btn btn-success" onclick="back()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>