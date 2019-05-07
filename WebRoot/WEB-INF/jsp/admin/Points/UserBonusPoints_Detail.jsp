<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row text-center" style="line-height: 0px;">
	<font size="4">基本信息</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">用户名：</font><font class="col-md-4">${pointdetail.userBaseAccountInfo.loginname}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分编号：</font><font class="col-md-4">${pointdetail.ubpno}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分来源：</font><font class="col-md-4">${bptype}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分类型：</font><font class="col-md-4">
		<c:if test="${pointdetail.bpUseType eq 4}">交易积分</c:if>
		<c:if test="${pointdetail.bpUseType eq 5}">系统积分</c:if>
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分数：</font><font class="col-md-4">${pointdetail.bonuspoints}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分发放时间：</font><font class="col-md-4">${pointdetail.bpstimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分处理时间：</font><font class="col-md-4">${pointdetail.bpdealtimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">积分状态：</font><font class="col-md-4">${status}</font>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">是否审核：</font><font class="col-md-4">${isaudit}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">审核人：</font><font class="col-md-4">${pointdetail.auditman}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">审核时间：</font><font class="col-md-4">${pointdetail.audittimeStr}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">备注：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty pointdetail.remark}">
				${pointdetail.remark}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
</div>