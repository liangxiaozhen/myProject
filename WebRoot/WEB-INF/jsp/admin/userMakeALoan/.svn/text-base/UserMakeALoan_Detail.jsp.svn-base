<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="well text-center"
	style="font-size: 14px; background: #d6e9c6; line-height: 0px;">投标放款记录基本信息</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标号：</label><label class="col-md-5">${detail.tenderitem.tno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标的名称：</label><label class="col-md-5">${detail.tenderitem.tname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标订单号：</label><label
		class="col-md-5">${detail.orderno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款订单号：</label><label
		class="col-md-5">${detail.mloanorderno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投资方：</label><label class="col-md-5">${detail.outaccount.loginname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">借款方：</label><label class="col-md-5">${detail.inaccount.loginname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款开始时间：</label><label
		class="col-md-5"><fmt:formatDate
			value="${detail.malbegintime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款完成时间：</label><label
		class="col-md-5"><c:if test="${empty detail.malendtime}">--</c:if>
		<c:if test="${!empty detail.malendtime}">
			<fmt:formatDate value="${detail.malendtime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款金额：</label><label class="col-md-5"
		id="amount"><c:if test="${!empty detail.amount}">${df.format(detail.amount)}<span>元</span>
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">手续费：</label><label class="col-md-5"><c:if
			test="${!empty detail.fee}">${df.format(detail.fee)}<span>元</span>
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">居间费：</label><label class="col-md-5"
		id="amount"><c:if test="${!empty detail.mediacyfee}">${df.format(detail.mediacyfee)}<span>元</span>
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">担保费：</label><label class="col-md-5"><c:if
			test="${!empty detail.guaranteefee}">${df.format(detail.guaranteefee)}<span>元</span>
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">风险保证金：</label><label
		class="col-md-5" id="amount"><c:if
			test="${!empty detail.riskguarantyfee}">${df.format(detail.riskguarantyfee)}<span>元</span>
		</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">是否解冻：</label><label class="col-md-5"><c:if
			test="${detail.isthaw eq 0}">未解冻</c:if> <c:if
			test="${detail.isthaw eq 1}">解冻</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款的状态：</label><label
		class="col-md-5"><c:if test="${detail.malstatus eq 0}">失败</c:if>
		<c:if test="${detail.malstatus eq 1}">成功</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">放款通道公司：</label><label
		class="col-md-5">${detail.paycompany}</label>
</div>
<c:if test="${detail.isblending eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">系统勾兑信息</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">是否系统勾兑：</label><label
			class="col-md-5"><c:if test="${detail.isblending eq 0}">未勾兑</c:if>
			<c:if test="${detail.isblending eq 1}">已勾兑</c:if></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">系统勾兑时间：</label><label
			class="col-md-5"><fmt:formatDate value="${detail.sysbtime}"
				pattern="yyyy-MM-dd HH:mm:ss" /></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">系统勾兑接收数据时间 第一次：</label><label
			class="col-md-5"><fmt:formatDate value="${detail.sysrectime}"
				pattern="yyyy-MM-dd HH:mm:ss" /></label>
	</div>
</c:if>
<c:if test="${detail.ismanblending eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">人工勾兑信息</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">是否人工勾兑：</label><label
			class="col-md-5"><c:if test="${detail.ismanblending eq 0}">未勾兑</c:if>
			<c:if test="${detail.ismanblending eq 1}">已勾兑</c:if></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">人工勾兑时间：</label><label
			class="col-md-5"><fmt:formatDate value="${detail.manbtime}"
				pattern="yyyy-MM-dd HH:mm:ss" /></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">人工勾兑接收数据时间 第一次：</label><label
			class="col-md-5"><fmt:formatDate
				value="${detail.receivetime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
	</div>
</c:if>