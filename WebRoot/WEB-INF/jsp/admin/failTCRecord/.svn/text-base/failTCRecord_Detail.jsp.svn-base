<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="well text-center"
	style="font-size: 14px; background: #d6e9c6; line-height: 0px;">标的流标补偿记录基本信息</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标号：</label><label class="col-md-5">${detail.tenderitem.tno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">标的名称：</label><label class="col-md-5">${detail.tenderitem.tname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标订单号：</label><label class="col-md-5">${detail.utorderno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投标金额：</label><label class="col-md-5">${detail.tenderamount eq null ? '--' : df.format(detail.tenderamount)}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">奖励金额：</label><label class="col-md-5">${detail.rewardamount eq null ? '--' : df.format(detail.rewardamount)}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">奖励方式：</label><label class="col-md-5"><c:if test="${detail.payouttype eq 1}">金额</c:if><c:if test="${detail.payouttype eq 2}">奖品</c:if><c:if test="${detail.payouttype eq 3}">金额+奖品</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">流标补偿流水号：</label><label class="col-md-5">${detail.ftcorderno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">平台杂项付款人：</label><label class="col-md-5">${detail.pmiscpayman}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">奖品名称：</label><label class="col-md-5">${detail.awardname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">奖品编号：</label><label class="col-md-5">${detail.awardno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">投资人：</label><label class="col-md-5">${detail.userbaseinfo.loginname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">生成方式：</label><label class="col-md-5"><c:if test="${detail.createway eq 1}">人工生成</c:if><c:if test="${detail.createway eq 2}">系统生成</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">创建时间：</label><label class="col-md-5">${detail.madetimeStr}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">是否发放：</label><label class="col-md-5"><c:if test="${detail.isgrant eq 0}">未发放</c:if><c:if test="${detail.isgrant eq 1}">已发放</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">清算日期：</label><label class="col-md-5">${detail.dealdateStr}</label>
</div>
<c:if test="${detail.isaudit eq 2 or detail.isaudit eq 3}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">审核信息</div>
	<div class="row" style="line-height: auto;">
		<label class="col-md-5 text-right">是否审核：</label><label class="col-md-5">${detail.isaudit}</label>
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