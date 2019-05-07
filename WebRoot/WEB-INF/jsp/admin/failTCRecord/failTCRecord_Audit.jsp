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
	<label class="col-md-4 text-right">标号：</label><label class="col-md-8">${detail.tenderitem.tno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">标的名称：</label><label class="col-md-8">${detail.tenderitem.tname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">投标金额：</label><label class="col-md-8"><c:if
			test="${!empty detail.tenderamount}">${df.format(detail.tenderamount)}</c:if></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>流标补偿基本信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">流标补偿流水号：</label><label
		class="col-md-8" id="ftcorderno">${detail.ftcorderno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">流标补偿编号：</label><label
		class="col-md-8">${detail.failtcompensate.failtcno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">平台杂项付款人：</label><label
		class="col-md-8">${detail.pmiscpayman}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">投资人：</label><label class="col-md-8">${detail.userbaseinfo.loginname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">奖励金额：</label><label class="col-md-8"><c:if
			test="${!empty detail.rewardamount}">${df.format(detail.rewardamount)}</c:if></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">奖励方式：</label><label class="col-md-8">${payouttype}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">奖品名称：</label><label class="col-md-8">${detail.awardname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">奖品编号：</label><label class="col-md-8">${detail.awardno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">奖额：</label><label class="col-md-8"><c:if
			test="${!empty detail.awardamount}">${df.format(detail.awardamount)}</c:if></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">记录生成方式：</label><label
		class="col-md-8">${createway}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">补偿是否发放：</label><label
		class="col-md-8">${isgrant}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">创建时间：</label><label class="col-md-8">${detail.madetimeStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">清算日期：</label><label class="col-md-8">${detail.dealdateStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">备注：</label>
	<div class="col-md-8">
		<textarea rows="" cols="" class="form-control" id="remark">${detail.remark}</textarea>
	</div>
</div>