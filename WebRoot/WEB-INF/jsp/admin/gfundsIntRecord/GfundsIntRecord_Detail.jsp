<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="well text-center"
	style="font-size: 14px; background: #d6e9c6; line-height: 0px;">标的站岗利息记录基本信息</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标号：</label><label class="col-md-5">${detail.tender.tno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标的名称：</label><label class="col-md-5">${detail.tender.tname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标订单号：</label><label class="col-md-5">${detail.utorderno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标金额：</label><label class="col-md-5">${df.format(detail.tenderamount)}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">奖励金额：</label><label class="col-md-5">${df.format(detail.rewardamount)}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">奖励方式：</label><label class="col-md-5">${payouttype}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">站岗利息流水号：</label><label class="col-md-5">${detail.gfiorderno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">站岗利息编号：</label><label class="col-md-5">${detail.guardinterest.gfundsintno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">平台杂项付款人：</label><label class="col-md-5">${detail.pmiscpayman}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投资人：</label><label class="col-md-5">${detail.investor.loginname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">生成方式：</label><label class="col-md-5">${createway}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">创建时间：</label><label class="col-md-5">${detail.madetimeStr}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">是否发放：</label><label class="col-md-5">${isgrant}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">清算日期：</label><label class="col-md-5">${detail.dealdateStr}</label>
</div>
<c:if test="${detail.isaudit eq 2 or detail.isaudit eq 3}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">审核信息</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">是否审核：</label><label class="col-md-5">${isaudit}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">审核人：</label><label class="col-md-5">${detail.auditman}</label>
	</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">审核时间：</label><label class="col-md-5">${detail.audittimeStr}</label>
	</div>
</c:if>
<c:if test="${detail.isblending eq 1 or detail.ismanblending eq 1}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">勾兑信息</div>
	<c:if test="${detail.isblending eq 1}">
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">是否系统勾兑：</label><label class="col-md-5">${detail.isblending}</label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">系统勾兑时间：</label><label class="col-md-5">${detail.sysbtime}</label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">系统勾兑接收数据时间 第一次：</label><label class="col-md-5">${detail.sysrectime}</label>
		</div>
	</c:if>
	<c:if test="${detail.ismanblending eq 1}">
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">是否人工勾兑：</label><label class="col-md-5">${detail.ismanblending}</label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">人工勾兑时间：</label><label class="col-md-5">${detail.manbtime}</label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">人工勾兑接收数据时间 第一次：</label><label class="col-md-5">${detail.receivetime}</label>
		</div>
	</c:if>
</c:if>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">备注：</label><label class="col-md-5">${detail.remark}</label>
</div>