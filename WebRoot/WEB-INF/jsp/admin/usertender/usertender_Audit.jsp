<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row" style="line-height: 0px;">
	<div class="col-md-8 col-md-offset-1">
		<input type="hidden" id="ordernos" value="${orderno}"> 总笔数 ：<font
			color="red"><label>${count}</label></font> 笔
	</div>
	<hr>
</div>
<form action="${pageContext.request.contextPath}/admin/tender/checkTenderRecord.action" method="post" id="auditForm" target="_blank">
			<input type="hidden" name="orderno" value="${orderno}">
			<input type="hidden" name="isaudit" value="2">
			<input type="hidden" name="bankDate" value="${bankDate}">
<c:if test="${count eq 1}">
	<div class="row" style="line-height: 0px;">
		<div class="col-md-8 col-md-offset-1">
			订单号 ：<label>${orderno}</label>
		</div>
		<hr>
	</div>
</c:if>
</form>
<div class="form-group">
	<label for="message-text" class="control-label">备注:</label>
	<textarea rows="2" class="form-control" name="remark"></textarea>
</div>