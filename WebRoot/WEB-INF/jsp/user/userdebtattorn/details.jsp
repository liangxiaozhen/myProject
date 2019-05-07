<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">设置人&nbsp;:</span> <span
				id="tenderidlb"> ${ub.setman} </span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">设置时间&nbsp;:</span> <span
				id="tenderidlb"> <c:if test="${!empty ub.settime}">${sf.format(ub.settime)}</c:if>
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">审核人&nbsp;:</span> <span
				id="tenderidlb"> ${ub.auditman} </span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">审核时间&nbsp;:</span> <span
				id="tenderidlb"> <c:if test="${!empty ub.audittime}">${sf.format(ub.audittime)}</c:if>
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">备注&nbsp;:</span> <span
				id="remarklb"> <c:if test="${!empty ub.remark}">${ub.remark}</c:if>
			</span>
		</div>
	</div>
</div>


