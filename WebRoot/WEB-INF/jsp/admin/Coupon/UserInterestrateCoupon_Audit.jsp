<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>基本信息</b></font>
	</div>
	<input type="hidden" id="couponId" value="${detail.id}" />
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">用户名：</label><label class="col-md-4">${detail.userBaseAccountInfo.loginname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券编号：</label><label class="col-md-4">${detail.uircno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券的类型：</label><label class="col-md-4">${uirctype}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券类型：</label><label class="col-md-4">${ictype}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券发放时间：</label><label
		class="col-md-4">${detail.ictimeStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券状态：</label><label class="col-md-4">${status}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">是否使用：</label><label class="col-md-4">${isuse}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-4 text-right">使用时间：</label><label class="col-md-4">${detail.usedateStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">抵用金额：</label><label class="col-md-4">${detail.vouchercash}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券利率：</label><label class="col-md-4">
		<c:choose>
			<c:when test="${!empty detail.vouchercash}">0
				</c:when>
			<c:otherwise>${icrate}
				</c:otherwise>
		</c:choose>
	</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券处理时间：</label><label
		class="col-md-4">${detail.icdealtimeStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">券失效时间：</label><label
		class="col-md-4">${detail.icfailtimeStr}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">用途：</label><label class="col-md-4">${detail.purpose}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">备注：</label>
	<div class="col-md-8">
		<textarea class="form-control">${reddetail.remark}</textarea>
	</div>
</div>