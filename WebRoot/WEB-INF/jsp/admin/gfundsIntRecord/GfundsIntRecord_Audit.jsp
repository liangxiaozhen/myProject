<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>标的信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">标号：</label><label class="col-md-8">${detail.tender.tno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">标的名称：</label><label class="col-md-8">${detail.tender.tname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">投标金额：</label><label class="col-md-8"><c:if
			test="${!empty detail.tenderamount}">${df.format(detail.tenderamount)}</c:if></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>奖励信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">奖励金额：</label><label class="col-md-3"><c:if
			test="${!empty detail.rewardamount}">${df.format(detail.rewardamount)}</c:if></label>
	<label class="col-md-3 text-right">奖励方式：</label><label class="col-md-3">${payouttype}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>站岗利息基本信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">站岗利息流水号：</label><label
		id="gfiorderno" class="col-md-4">${detail.gfiorderno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">站岗利息编号：</label><label
		class="col-md-4">${detail.guardinterest.gfundsintno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">平台杂项付款人：</label><label
		class="col-md-4">${detail.pmiscpayman}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">投资人：</label><label class="col-md-8">${detail.investor.loginname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;"></div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">生成方式：</label><label class="col-md-4">${createway}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">创建时间：</label><label class="col-md-4">${detail.madetimeStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">是否发放：</label><label class="col-md-4">${isgrant}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">清算日期：</label><label class="col-md-4">${detail.dealdateStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">备注：</label>
	<div class="col-md-8">
		<textarea cols="" rows="" class="form-control" id="remark">${detail.remark}</textarea>
	</div>
</div>