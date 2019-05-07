<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="well text-center"
	style="font-size: 14px; background: #d6e9c6; line-height: 0px;">自动投标计划基本信息</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">用户名：</label><label class="col-md-5">${detail.userBaseInfo.loginname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标金额：</label><label class="col-md-5"><c:if
			test="${empty detail.transamt}">--</c:if> <c:if
			test="${!empty detail.transamt}">
			<fmt:formatNumber minFractionDigits="2" value="${detail.transamt}" />
			<span>元</span>
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标号：</label><label class="col-md-5">${empty detail.tender.tno ? '--' : detail.tender.tno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标的名称：</label><label class="col-md-5">${empty detail.tender.tname ? '--' : detail.tender.tname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标计划类型：</label><label
		class="col-md-5"><c:if test="${detail.tenderplantype eq 'P'}">部分授权</c:if>
		<c:if test="${detail.tenderplantype eq 'W'}">完全授权</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标计划状态：</label><label
		class="col-md-5" id="amount"><c:if
			test="${detail.status eq 0}">已关闭</c:if> <c:if
			test="${detail.status eq 1}">已开启</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">自动投标开始时间：</label><label
		class="col-md-5"><c:if test="${empty detail.uatbegintime}">--</c:if>
		<c:if test="${!empty detail.uatbegintime}">
			<fmt:formatDate value="${detail.uatbegintime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">自动投标完成时间：</label><label
		class="col-md-5" id="amount"><c:if
			test="${empty detail.uatendtime}">--</c:if> <c:if
			test="${!empty detail.uatendtime}">
			<fmt:formatDate value="${detail.uatendtime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款通道公司：</label><label
		class="col-md-5">${detail.paycompany}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">操作人：</label><label class="col-md-5"
		id="amount">${detail.operatorman}</label>
</div>
<c:if test="${detail.isblending eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">系统勾兑信息</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">是否系统勾兑：</label><label
			class="col-md-5"><c:if test="${detail.isblending eq 0}">否</c:if>
			<c:if test="${detail.isblending eq 1}">是</c:if></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">系统勾兑时间：</label><label
			class="col-md-5"><fmt:formatDate value="${detail.sysbtime}"
				pattern="yyyy-MM-dd HH:mm:ss" /></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-left">系统勾兑接收数据时间 第一次：</label><label
			class="col-md-5"><fmt:formatDate value="${detail.sysrectime}"
				type="both" /></label>
	</div>
</c:if>
<c:if test="${detail.ismanblending eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">人工勾兑信息</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">是否人工勾兑：</label><label
			class="col-md-5"><c:if test="${detail.ismanblending eq 0}">否</c:if>
			<c:if test="${detail.ismanblending eq 1}">是</c:if></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">人工勾兑时间：</label><label
			class="col-md-5"><fmt:formatDate value="${detail.manbtime}"
				pattern="yyyy-MM-dd HH:mm:ss" /></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-left">人工勾兑接收数据时间 第一次：</label><label
			class="col-md-5"><fmt:formatDate
				value="${detail.receivetime}" type="both" /></label>
	</div>
</c:if>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">备注：</label><label class="col-md-5">${detail.remark}</label>
</div>