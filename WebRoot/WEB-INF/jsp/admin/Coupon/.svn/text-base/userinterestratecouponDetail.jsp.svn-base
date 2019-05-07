<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>基本信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">用户名：</font><font class="col-md-4">${detail.userBaseAccountInfo.loginname}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券编号：</font><font class="col-md-4">${detail.uircno}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券的类型：</font><font class="col-md-4">${uirctype}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券的来源：</font><font class="col-md-4">${ictype}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券状态：</font><font class="col-md-4">${status}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">是否使用：</font><font class="col-md-4">${isuse}</font>
	<hr />
</div>

<%-- <div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">抵用金额：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty detail.vouchercash}">
				${detail.vouchercash}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>

	</font>
	<hr />
</div> --%>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券利率：</font><font class="col-md-4">
		<c:choose>
			<c:when test="${!empty detail.icrate}">
				${detail.icrate}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">使用时间：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty detail.usedateStr}">
				${detail.usedateStr}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券发放时间：</font><font class="col-md-4">${detail.ictimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券处理时间：</font><font class="col-md-4">${detail.icdealtimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">券失效时间：</font><font class="col-md-4">${detail.icfailtimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">用途：</font><font class="col-md-4">${detail.purpose}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>审核信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">是否审核：</font><font class="col-md-4">${isaudit}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">审核人：</font><font class="col-md-4">${detail.auditman}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">审核时间：</font><font class="col-md-4">${detail.audittimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">备注：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty detail.remark}">
				${detail.remark}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
</div>