<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
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
<style type="text/css">
	div{
		margin:10px 0px
	}
	.colonAlign{
		width:150px;
		text-align:right;
		display:inline-block
	}
</style>
</head>
<body style="font-family: '微软雅黑';font-size:14px">
<div style="margin-top:30px;">
	
	<div style="text-align: center">
		<font size="4">基本信息</font>
		<hr>
	</div>
	
	<div >
		<font class="colonAlign">用户名：</font>
		<font>${reddetail.name.loginname}</font>
	</div>
	
	<div >
		<font class="colonAlign">红包编号：</font>
		<font>${reddetail.ureno}</font>
	</div>
	
	<div >
		<font class="colonAlign">红包获取方式：</font>
		<font ><c:choose>
				<c:when test="${reddetail.rectype==1}">注册</c:when>
				<c:when test="${reddetail.rectype==2}">累投</c:when>
				<c:when test="${reddetail.rectype==3}">单标</c:when>
				<c:when test="${reddetail.rectype==4}">首投</c:when>
				<c:when test="${reddetail.rectype==5}">手动颁奖</c:when>
				<c:when test="${reddetail.rectype==6}">自主颁奖</c:when>
			</c:choose>
		</font>
	</div>
	
	<div >
		<font class="colonAlign">红包类型：</font>
		<font>${redtype}</font>
	</div>
	
	<div >
		<font class="colonAlign">红包发放时间：</font>
		<font >
			<fmt:formatDate value="${reddetail.restime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>
	
	<div >
		<font class="colonAlign">红包：</font>
		<font >${reddetail.redenvelope}</font>
	</div>
	
	<div >
		<font class="colonAlign">红包状态：</font>
		<font >${redstatus}</font>
	</div>
	
	<div >
		<font class="colonAlign">红包处理时间：</font>
		<font >
			<fmt:formatDate value="${reddetail.redealtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>
	
	<div >
		<font class="colonAlign">是否使用：</font>
		<font >${redisuse}</font>
	</div>
	
	<div >
		<font class="colonAlign">使用时间：</font> 
		<font >
			<c:choose>
				<c:when test="${!empty reddetail.usedateStr}">
					${reddetail.usedateStr}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
	
	<div >
		<font class="colonAlign">红包失效时间：</font><font >
			<fmt:formatDate value="${reddetail.refailtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>
	
	<div >
		<font class="colonAlign">用途：</font>
		<font>${reddetail.purpose}</font>
	</div>
	
	<div >
		<font class="colonAlign">是否审核：</font>
		<font >${redisaudit}</font>
	</div>
	
	<div >
		<font class="colonAlign">审核人：</font> 
		<font >
			<c:choose>
				<c:when test="${!empty reddetail.auditman}">
					${reddetail.auditman}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
	
	<div >
		<font class="colonAlign">审核时间：</font> <font >
			<c:choose>
				<c:when test="${!empty reddetail.audittime}">
					<fmt:formatDate value="${reddetail.audittime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
	<div >
		<font class="colonAlign">备注：</font> 
		<font >
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
</div>
</body>
</html>