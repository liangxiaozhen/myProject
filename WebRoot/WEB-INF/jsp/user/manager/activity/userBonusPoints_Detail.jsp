<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
	<style type="text/css">
		div{
			margin:10px 0px
		}
	</style>
</head>
<body style="font-family:'微软雅黑';font-size:14px">
<div style="margin-top:30px;">

	<div style="text-align:center">
		<font size="3">基本信息</font>
		<hr>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">用户名 :</font>
		<font>${pointdetail.userBaseAccountInfo.loginname}</font>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分编号 :</font>
		<font>${pointdetail.ubpno}</font>
		
	</div>
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分来源 :</font>
		<font>${bptype}</font>
	</div>
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分类型:</font>
		<font>
			<c:if test="${pointdetail.bpUseType eq 4}">交易积分</c:if>
			<c:if test="${pointdetail.bpUseType eq 5}">系统积分</c:if>
		</font>
	</div>
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分数:</font>
		<font>${pointdetail.bonuspoints}</font>
	</div>
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分发放时间:</font>
		<font>
			<fmt:formatDate value="${pointdetail.bpstime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
		</font>
	</div>
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分处理时间:</font>
		<font>
			<fmt:formatDate value="${pointdetail.bpdealtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
		</font>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">积分状态:</font>
		<font>${status}</font>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">是否审核:</font>
		<font>
			<c:choose>
				<c:when test="${!empty isaudit}">
					${isaudit}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">审核人:</font>
		<font>
			<c:choose>
				<c:when test="${!empty pointdetail.auditman}">
					${pointdetail.auditman}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">审核时间:</font>
		<font>
			<fmt:formatDate value="${pointdetail.audittime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
		</font>
	</div>
	
	<div>
		<font style="width:150px;text-align:right;display:inline-block;">备注:</font>
		<font>
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
</div>

</body>