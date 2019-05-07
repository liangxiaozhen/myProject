<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(function(){
	$("#ugrade").each(function(i) {
		var num = $(this).text();
		if (num.length > 11) {
			$(this).text(num.substr(0, 11) + "...");
		}
	});
});
	
</script>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>基本信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">用户名：</font><font class="col-md-4">${reddetail.name.loginname}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包编号：</font><font class="col-md-4">${reddetail.ureno}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包获取方式：</font><font class="col-md-4"><c:choose>
			<c:when test="${reddetail.rectype==1}">注册</c:when>
			<c:when test="${reddetail.rectype==2}">累投</c:when>
			<c:when test="${reddetail.rectype==3}">单标</c:when>
			<c:when test="${reddetail.rectype==4}">首投</c:when>
			<c:when test="${reddetail.rectype==5}">手动颁奖</c:when>
			<c:when test="${reddetail.rectype==6}">自主颁奖</c:when>
		</c:choose></font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包类型：</font><font class="col-md-4">${redtype}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包发放时间：</font><font class="col-md-4">
		<fmt:formatDate value="${reddetail.restime}" type="date"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包：</font><font class="col-md-4">${reddetail.redenvelope}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包状态：</font><font class="col-md-4">${redstatus}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包处理时间：</font><font class="col-md-4">
		<fmt:formatDate value="${reddetail.redealtime}" type="date"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">是否使用：</font><font class="col-md-4">${redisuse}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">使用时间：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty reddetail.usedateStr}">
				${reddetail.usedateStr}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">红包失效时间：</font><font class="col-md-4">
		<fmt:formatDate value="${reddetail.refailtime}" type="date"
			pattern="yyyy-MM-dd HH:mm:ss" />
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">用途：</font><font class="col-md-4"
		id="ugrade">${reddetail.purpose}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<div class="col-md-offset-2">
		<font color="red"><b>审核信息</b></font>
	</div>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">是否审核：</font><font class="col-md-4">${redisaudit}</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">审核人：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty reddetail.auditman}">
				${reddetail.auditman}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">审核时间：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty reddetail.audittimeStr}">
				${reddetail.audittimeStr}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-md-4 text-right">备注：</font> <font class="col-md-4">
		<c:choose>
			<c:when test="${!empty reddetail.remark}">
				${reddetail.remark}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
</div>