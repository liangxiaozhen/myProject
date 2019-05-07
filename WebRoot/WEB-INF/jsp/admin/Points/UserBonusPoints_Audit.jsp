<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>基本信息</b></font>
	</div>
	<input type="hidden" id="bonusId" value="${pointdetail.id}" />
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">用户名：</label><label class="col-md-4">${pointdetail.userBaseAccountInfo.loginname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">积分编号：</label><label class="col-md-4">${pointdetail.ubpno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">积分类型：</label><label class="col-md-4">
		<c:choose>
			<c:when test="${pointdetail.bptype==1}">注册</c:when>
			<c:when test="${pointdetail.bptype==2}">完善资料</c:when>
			<c:when test="${pointdetail.bptype==3}">首投</c:when>
			<c:when test="${pointdetail.bptype==4}">手动颁奖</c:when>
			<c:when test="${pointdetail.bptype==5}">其他</c:when>
		</c:choose>
	</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">积分：</label><label class="col-md-4">${pointdetail.bonuspoints}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">积分发放时间：</label><label
		class="col-md-4"><fmt:formatDate
			value="${pointdetail.bpstime}" type="date" pattern="yyyy-MM-dd" /></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">积分处理时间：</label><label
		class="col-md-4"><fmt:formatDate
			value="${pointdetail.bpdealtime}" type="date" pattern="yyyy-MM-dd" /></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">积分状态：</label><label class="col-md-4">${status}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">备注：</label>
	<div class="col-md-8">
		<textarea class="form-control">${pointdetail.remark}</textarea>
	</div>
</div>