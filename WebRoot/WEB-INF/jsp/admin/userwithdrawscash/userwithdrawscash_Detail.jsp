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
<title>莫邪科技</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>银行卡信息</b></font>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			提现银行：<label class="detailslb" id="banklb">${uwc.bankname }</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-12 col-md-offset-1">
			提现卡号：<label class="detailslb" id="cardnolb">${uwc.cardno }</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			开 户 名 ：<label class="detailslb" id="usernamelb">${uwc.ubai.realname }</label>
		</div>
		<hr>
	</div>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>提现信息</b></font>
		</div>
		<hr>
	</div>
	<!--  <div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			提现户名 ：<label class="detailslb">${uwc.ubai.loginname} </label>
		</div>
	
		<div class="col-md-6 col-md-offset-1"></div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			订单号 ：<label class="detailslb">${uwc.cashno }</label>
		</div>
		<hr>
	</div>-->
	<div class="row" style="line-height: 0px;">
		<!--  <div class="col-md-4 col-md-offset-1">
			提现金额 ：<label class="detailslb" id="amountlb"> <c:if
					test="${empty uwc.amount }">0.00</c:if> <c:if
					test="${!empty uwc.amount }"><fmt:formatNumber pattern="##,###,##0.00" value="${uwc.amount }"/></c:if>
					元
			</label>
		</div>-->

		<div class="col-md-4 col-md-offset-1">
			设备来源 ：<label class="detailslb"> <c:choose>
					<c:when test="${uwc.originclient eq 1 }">
										PC
									</c:when>
					<c:when test="${uwc.originclient eq 2 }">
										IPad
										</c:when>
					<c:when test="${uwc.originclient eq 3 }">
										Android
										</c:when>
					<c:when test="${uwc.originclient eq 4 }">
										IOS
										</c:when>
				</c:choose></label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			提现方式 ：<label class="detailslb"> <c:choose>
					<c:when test="${uwc.cashchl eq 'FAST'}">快速提现</c:when>
					<c:when test="${uwc.cashchl eq 'GENERAL'}">正常提现</c:when>
					<c:when test="${uwc.cashchl eq 'IMMEDIATE'}">即时提现</c:when>
				</c:choose>
			</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			提现手续费 ：<label class="detailslb" id="feelb"> <fmt:formatNumber
					pattern="##,###,##0.00" value="${uwc.fee }" />元
			</label>

		</div>
		<div class="col-md-4 col-md-offset-1">
			银行手续费：<label> <fmt:formatNumber pattern="##,###,##0.00"
					value="${uwc.servfee }"></fmt:formatNumber>
			</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>对账信息</b></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-6 col-md-offset-1">
			系统勾兑：<label class="detailslb"> <c:choose>
					<c:when test="${uwc.isblending eq 0 }">未勾兑</c:when>
					<c:when test="${uwc.isblending eq 1 }">已勾兑</c:when>
				</c:choose>
			</label>
		</div>
		<div class="col-md-5 ">
			人工勾兑：<label class="detailslb"> <c:choose>
					<c:when test="${uwc.ismanblending eq 0 }">未勾兑</c:when>
					<c:when test="${uwc.ismanblending eq 1 }">已勾兑</c:when>
				</c:choose>
			</label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-6 col-md-offset-1">
			系统接收数据时间：<label class="detailslb"><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${uwc.sysrectime }" /></label>
		</div>
		<div class="col-md-5 ">
			系统勾兑时间： <label class="detailslb"><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${uwc.syschktime }" /></label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-6 col-md-offset-1">
			人工接收数据时间：<label class="detailslb"><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${uwc.checktime }" /></label>
		</div>
		<div class="col-md-5 ">
			人工勾兑时间： <label class="detailslb"><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${uwc.receivetime }" /></label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-4">
			是否异常： <label class="detailslb"> <c:choose>
					<c:when test="${uwc.isexceptions eq 0}">
						<font color='green'>正常</font>
					</c:when>
					<c:when test="${uwc.isexceptions eq 1}">
						<font color='red'>异常</font>
					</c:when>
				</c:choose>
			</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>备注信息</b></font>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-11 col-md-offset-1">
			备注&nbsp;&nbsp;： <label>${uwc.remark }</label>
		</div>
	</div>
</body>
</html>