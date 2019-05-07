<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${tender.utproperty eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">原始投标基本信息</div>
</c:if>
<c:if test="${tender.utproperty eq 2}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">债转投标基本信息</div>
</c:if>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">标号：</label><label class="col-md-3">${tender.tenderitem.tno}</label>
	<label class="col-md-2 text-right">标的名称：</label><label class="col-md-4">${tender.tenderitem.tname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">投标属性：</label><label class="col-md-3"><c:if
			test="${tender.utproperty eq 1}">原始投标</c:if> <c:if
			test="${tender.utproperty eq 2}">债转投标</c:if></label> <label
		class="col-md-2 text-right">起息日：</label><label class="col-md-4"><fmt:formatDate
			value="${tender.valuedate}" pattern="yyyy-MM-dd HH:mm:ss" /> <c:if
			test="${tender.valuedate eq null}">--</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">投标订单号：</label><label
		class="col-md-3">${tender.orderno}</label> <label
		class="col-md-2 text-right">标的属性：</label><label class="col-md-4">${empty tproperty ? '--' : tproperty}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">投资方：</label><label class="col-md-3">${tender.outaccount.loginname}</label>
	<label class="col-md-2 text-right">借款方：</label><label class="col-md-4">${tender.inaccount.loginname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">投标开始时间：</label><label
		class="col-md-3">${tender.tbegintimeStr}</label> <label
		class="col-md-2 text-right">投标完成时间：</label><label class="col-md-4">${empty tender.tendtimeStr ? '--' : tender.tendtimeStr}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">投标方式：</label><label class="col-md-3">${tendertype}</label>
	<label class="col-md-2 text-right">投标通道公司：</label><label
		class="col-md-4">${tender.paycompany}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">转账类型：</label><label class="col-md-3">${transfertype}</label>
	<label class="col-md-2 text-right">投标状态：</label><label class="col-md-4">${tstatus}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">能否债转：</label><label class="col-md-3">${empty isallowda ? '--' : isallowda}</label>
	<label class="col-md-2 text-right">是否债转：</label><label class="col-md-4">${isda}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">投标总金额：</label><label
		class="col-md-3"><fmt:formatNumber value="${tender.amount}"
			minFractionDigits="2" /><span>元</span></label> <label
		class="col-md-2 text-right">手续费：</label><label class="col-md-4"><fmt:formatNumber
			value="${tender.fee}" minFractionDigits="2" /><span>元</span></label>
</div>
<c:if test="${tender.utproperty eq 1}">
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">本金金额：</label><label
			class="col-md-3"><c:if test="${empty tender.realamount}">--</c:if>
			<c:if test="${!empty tender.realamount}">
				<fmt:formatNumber minFractionDigits="2" value="${tender.realamount}" />
				<span>元</span>
			</c:if></label> <label class="col-md-2 text-right">类现金金额：</label><label
			class="col-md-4" id="amount"><c:if
				test="${empty tender.voucheramount}">--</c:if> <c:if
				test="${!empty tender.voucheramount}">
				<fmt:formatNumber minFractionDigits="2"
					value="${tender.voucheramount}" />
				<span>元</span>
			</c:if></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">假现金金额：</label><label
			class="col-md-3"><c:if
				test="${empty tender.likevoucheramount}">--</c:if> <c:if
				test="${!empty tender.likevoucheramount}">
				<fmt:formatNumber minFractionDigits="2"
					value="${tender.likevoucheramount}" />
				<span>元</span>
			</c:if></label> <label class="col-md-2 text-right">居间费：</label><label
			class="col-md-4"><fmt:formatNumber minFractionDigits="2"
				value="${tender.mediacyfee}" /><span>元</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">担保费：</label><label class="col-md-3"><fmt:formatNumber
				minFractionDigits="2" value="${tender.guaranteefee}" /><span>元</span></label>
		<label class="col-md-2 text-right">风险保证金费：</label><label
			class="col-md-4"><fmt:formatNumber minFractionDigits="2"
				value="${tender.riskguarantyfee}" /><span>元</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">是否冻结：</label><label
			class="col-md-3">${isfreeze}</label>
		<label class="col-md-2 text-right">约标码：</label><label class="col-md-4">${empty tender.appointtenderpass ? '--' : tender.appointtenderpass}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">投标申请授权码：</label><label class="col-md-3">${empty tender.authcode ? '--' : tender.authcode}</label>
		<label class="col-md-2 text-right">还款完成：</label><label
			class="col-md-4">${isrepayend}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">投标设备来源：</label><label
			class="col-md-3">${tender.originclient}</label>
	</div>
	<c:if test="${!empty tender.auditman}">
		<div class="well text-center"
			style="font-size: 14px; background: #d6e9c6; line-height: 0px;">审核信息</div>
		<div class="row" style="line-height: auto;"> <label
				class="col-md-3 text-right">审核人：</label><label class="col-md-3">${tender.auditman}</label>
			<label class="col-md-2 text-right">审核时间：</label><label
				class="col-md-4">${tender.audittimeStr}</label>
		</div>
	</c:if>
</c:if>
<c:if test="${tender.utproperty eq 2}">
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">原订单号：</label><label
			class="col-md-3">${tender.olddaorderno}</label> <label
			class="col-md-2 text-right">原投标持有天数：</label><label class="col-md-4">${tender.holddate}<span>天</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">平台杂项收款人：</label><label
			class="col-md-3">${tender.pmiscrecman}</label> <label
			class="col-md-2 text-right">承接总金额：</label><label class="col-md-4"><fmt:formatNumber
				value="${tender.totalamount}" minFractionDigits="2" /><span>元</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">承接本金：</label><label
			class="col-md-3"><fmt:formatNumber
				value="${tender.utprinamount}" minFractionDigits="2" /><span>元</span></label>
		<label class="col-md-2 text-right">承接利息：</label><label
			class="col-md-4"><fmt:formatNumber
				value="${tender.utintamount}" minFractionDigits="2" /><span>元</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">滞纳金：</label><label class="col-md-3"><c:if
				test="${empty tender.ocamount}">--</c:if> <c:if
				test="${!empty tender.ocamount}">
				<fmt:formatNumber value="${tender.ocamount}" minFractionDigits="2" />
				<span>元</span>
			</c:if></label> <label class="col-md-2 text-right">剩余金额：</label><label
			class="col-md-4"><fmt:formatNumber
				value="${tender.restamount}" minFractionDigits="2" /><span>元</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">承接时年化收益率：</label><label
			class="col-md-3"><fmt:formatNumber
				value="${tender.yearprofit}" type="percent" minFractionDigits="2" /></label>
		<label class="col-md-2 text-right">债转数：</label><label class="col-md-4">${tender.datimes}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">手续费收取模式：</label><label
			class="col-md-3"><c:if test="${tender.feemode eq 0}">初始</c:if>
			<c:if test="${tender.feemode eq 1}">根据用户等级</c:if> <c:if
				test="${tender.feemode eq 2}">根据投标持有天数</c:if></label> <label
			class="col-md-2 text-right">债转手续费：</label><label class="col-md-4"><fmt:formatNumber
				value="${tender.dahfee}" minFractionDigits="2" /><span>元</span></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">债转手续费收取类型：</label><label
			class="col-md-3"><c:if test="${tender.datype eq 0}">初始</c:if>
			<c:if test="${tender.datype eq 1}">定额</c:if> <c:if
				test="${tender.datype eq 2}">百分比</c:if> <c:if
				test="${tender.datype eq 3}">最低</c:if> <c:if
				test="${tender.datype eq 4}">最高</c:if></label> <label
			class="col-md-2 text-right">债转类型：</label><label class="col-md-4">--</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">转让时间：</label><label
			class="col-md-3"><c:if test="${empty tender.dadate}">--</c:if>
			<c:if test="${!empty tender.dadate}">
				<fmt:formatDate value="${tender.dadate}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</c:if></label><label class="col-md-2 text-right">债转属性：</label><label
			class="col-md-4"><c:if test="${tender.daproperty eq 1}">正常债转</c:if>
			<c:if test="${tender.daproperty eq 2}">逾期债转</c:if></label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">是否禁止债转上架：</label><label
			class="col-md-3"><c:if test="${tender.isdisableda eq 0}">否</c:if>
			<c:if test="${tender.isdisableda eq 1}">是</c:if> <c:if
				test="${empty tender.isdisableda}">--</c:if></label>
	</div>
</c:if>
<c:if test="${tender.isblending eq 1 || tender.ismanblending eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">勾兑信息</div>
</c:if>
<c:if test="${tender.isblending eq 1}">
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">是否系统勾兑：</label><label
			class="col-md-3">${isblending}</label> <label
			class="col-md-2 text-right">系统勾兑时间：</label><label class="col-md-4">${tender.sysbtimeStr}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-4 text-left">系统勾兑接收数据时间 第一次：</label><label
			class="col-md-4">${tender.sysrectimeStr}</label>
	</div>
</c:if>
<c:if test="${tender.ismanblending eq 1}">
	<div class="row" style="line-height: auto;">
		<label class="col-md-3 text-right">是否人工勾兑：</label><label
			class="col-md-3">${ismanblending}</label> <label
			class="col-md-2 text-right">人工勾兑时间：</label><label class="col-md-4">${tender.manbtimeStr}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-4 text-left">人工勾兑接收数据时间 第一次：</label><label
			class="col-md-4">${tender.receivetimeStr}</label>
	</div>
</c:if>
<div class="row" style="line-height: auto;">
	<label class="col-md-3 text-right">备注：</label><label class="col-md-8">${empty tender.remark ? '--' : tender.remark}</label>
</div>