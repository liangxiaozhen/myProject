<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
	<input type="hidden" id="redId" value="${reddetail.id}" />
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">用户名：</label><label class="col-md-4">${reddetail.name.loginname}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包编号：</label><label class="col-md-4">${reddetail.ureno}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包获取方式：</label><label
		class="col-md-4"><c:choose>
			<c:when test="${reddetail.rectype==1}">注册</c:when>
			<c:when test="${reddetail.rectype==2}">完善资料</c:when>
			<c:when test="${reddetail.rectype==3}">首投</c:when>
			<c:when test="${reddetail.rectype==4}">来源</c:when>
			<c:when test="${reddetail.rectype==5}">手动颁奖</c:when>
			<c:otherwise>其他</c:otherwise>
		</c:choose></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包类型：</label><label class="col-md-4">${redtype}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包发放时间：</label><label
		class="col-md-4"> <fmt:formatDate value="${reddetail.restime}"
			type="date" pattern="yyyy-MM-dd" /></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包：</label><label class="col-md-4">${reddetail.redenvelope}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包状态：</label><label class="col-md-4">${redstatus}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包处理时间：</label><label
		class="col-md-4"> <fmt:formatDate
			value="${reddetail.redealtime}" type="date" pattern="yyyy-MM-dd" /></label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">是否使用：</label><label class="col-md-4">${redisuse}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">使用时间：</label><label class="col-md-4">${reddetail.usedate}</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">红包失效时间：</label><label
		class="col-md-4"> <fmt:formatDate
			value="${reddetail.refailtime}" type="date" pattern="yyyy-MM-dd" />
	</label>
	<hr />
</div>
<div class="row" style="line-height: 0px;">
	<label class="col-md-3 text-right">用途：</label><label class="col-md-4"
		id="ugrade">${reddetail.purpose}</label>
	<hr />
</div>
<div class="row">
	<label class="col-md-3 text-right">备注：</label>
	<div class="col-md-8">
		<textarea class="form-control">${reddetail.remark}</textarea>
	</div>
</div>
<div class="modal-footer">
	<a class="btn btn-success"
		href="<%=basePath%>admin1/bonus/checkPass.action?id=${reddetail.id}">审核通过</a>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>