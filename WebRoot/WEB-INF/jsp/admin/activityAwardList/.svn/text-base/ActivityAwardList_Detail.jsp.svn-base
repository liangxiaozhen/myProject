<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%-- <div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">ID:</font>
	<font class="col-md-4" id="addman-lb">${activityAwardList.id }</font>
	<hr/>
</div> --%>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">活动编号:</font> 
	<font>${activityAwardList.actid }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">用户ID:</font>
	<font id="addman-lb">${activityAwardList.baseid }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">用户名:</font>
	<font id="addtime-lb">${activityAwardList.username }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品编号:</font>
	<font id="addman-lb">${activityAwardList.awardno }</font>
	<hr/>
</div>


<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品名称:</font>
	<font id="addtime-lb">${activityAwardList.awardname }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品属性:</font>
	<font>${attribute}</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">获奖金额:</font>
	<font>
		<c:choose>
			<c:when test="${!empty activityAwardList.awardmoney}">
				${activityAwardList.awardmoney}
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品个数:</font>
	<font id="addtime-lb">${activityAwardList.awardquantity }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品状态:</font>
	<font id="addtime-lb">
		<c:choose>
			<c:when test="${activityAwardList.status==1 }">待审核</c:when>
			<c:when test="${activityAwardList.status==2 }">待处理</c:when>
			<c:when test="${activityAwardList.status==3 }">已领取</c:when>
			<c:when test="${activityAwardList.status==4 }">待确认</c:when>
			<c:when test="${activityAwardList.status==5 }">已经确认</c:when>
			<c:when test="${activityAwardList.status==6 }">发货中</c:when>
			<c:when test="${activityAwardList.status==7 }">领取失败</c:when>
			<c:when test="${activityAwardList.status==9 }">待发放</c:when>
		</c:choose>
	</font>
	<hr/>	
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">制作人:</font>
	<font id="addman-lb">${activityAwardList.mademan }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">制作时间</font>
	<font id="addtime-lb"><fmt:formatDate value='${activityAwardList.madetime }' type='both'/></font>
	<hr/>	
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">审核人:</font>
	<font>
		<c:if test="${!empty activityAwardList.auditman }">
			${activityAwardList.auditman }
		</c:if>
		<c:if test="${empty activityAwardList.auditman }">
			--
		</c:if>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">审核时间:</font>
	<font>
		<c:choose>
			<c:when test="${!empty activityAwardList.audittime}">
				<fmt:formatDate value='${activityAwardList.audittime}' type='both' /></font>
			</c:when>
			<c:otherwise>
				--
			</c:otherwise>
		</c:choose>
	<hr/>	
</div>

<div class="row">
		<font class="col-md-3 text-right">备注:</font>
		<font>
			<c:choose>
				<c:when test="${!empty activityAwardList.remark}">
					${activityAwardList.remark }
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
</div>
					
					
