<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-5">
		<font color="red"><b>保存成功</b></font>
	</div>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">活动名称：</label><label class="col-md-4">${mam.activityName}</label>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">活动编号：</label><label class="col-md-4">${mam.activityNo}</label>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">活动执行时间：</label> <label
		class="col-md-4"> <fmt:formatDate value="${mam.activityTime}"
			type="date" pattern="yyyy-MM-dd" />
	</label>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">子活动数量：</label><label
		class="col-md-4">${mam.subActivityNum}</label>
	<hr />
</div>

<%-- <div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">是否为模板：</label><label
		class="col-md-4"> <c:choose>
			<c:when test="${mam.isTempletMain==1}">是</c:when>
			<c:when test="${mam.isTempletMain==2}">否</c:when>
		</c:choose>
	</label>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">是否容许手动：</label><label
		class="col-md-4"> <c:choose>
			<c:when test="${mam.isManual==1}">容许</c:when>
			<c:when test="${mam.isManual==2}">不容许</c:when>
		</c:choose>
	</label>
	<hr />
</div>

<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">活动设置人：</label><label
		class="col-md-4">${mam.addManMain}</label>
</div> --%>