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
	<font class="col-md-4">${reli.id }</font>
	<hr/>
</div> --%>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">用户ID:</font>
	<font>${reli.baseid }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">用户名称:</font>
	<font>${reli.username }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">流水号:</font> 
	<font>${reli.orderno }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">红包编号:</font>
	<font id="addman-lb">${reli.redenvelopeno }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">红包名称:</font>
	<font>${reli.redenvelopename }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">金额:</font>
	<font>${reli.reamount }元</font>
	<hr/>
</div>


<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">业务类型:</font>
	<font>${reli.businesstype }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">处理时间:</font>
	<font>
		<fmt:formatDate value='${reli.sendtime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">系统勾兑:</font>
	<font>
		<c:choose>
			<c:when test="${reli.isblending eq 0}">未勾兑</c:when>
			<c:when test="${reli.isblending eq 1}">已勾兑</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">人工勾兑:</font>
	<font>
		<c:choose>
			<c:when test="${reli.ismanblending eq 0}">未勾兑</c:when>
			<c:when test="${reli.ismanblending eq 1}">已勾兑</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">系统勾兑时间:</font>
	<font>
		<c:choose>
			<c:when test="${!empty reli.sysbtime}">
				<fmt:formatDate value='${reli.sysbtime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
			</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">人工勾兑时间:</font>
	<font>
		<c:choose>
			<c:when test="${!empty reli.manbtime}">
				<fmt:formatDate value='${reli.manbtime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
			</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">托管公司:</font>
	<font id="addtime-lb">${reli.paycompany }</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">系统勾兑接收数据时间:</font>
	<font>
		<c:choose>
			<c:when test="${!empty reli.sysrectime}">
				<fmt:formatDate value='${reli.sysrectime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
			</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">人工勾兑接收数据时间:</font>
	<font>
		<c:choose>
			<c:when test="${!empty reli.receivetime}">
				<fmt:formatDate value='${reli.receivetime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
			</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
	<hr/>
</div>


<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">请求数据包:</font>
	<hr/>
</div>

<div class="row" style="line-height: auto;word-break:break-all;padding-left:45px;padding-right:45px;">
	<font>
		<c:choose>
			<c:when test="${!empty reli.reqquerydata }">${reli.reqquerydata }</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
		
	</font>
</div>
<hr/>
<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">接受数据包:</font>
	<hr/>	
</div>

<div class="row" style="line-height: auto;word-break:break-all;padding-left:45px;padding-right:45px;">
	<font>
		<c:choose>
			<c:when test="${!empty reli.recresultdata }">${reli.recresultdata }</c:when>
			<c:otherwise>--</c:otherwise>
		</c:choose>
	</font>
</div>
<hr/>

<div class="row">
		<font class="col-md-3 text-right">备注:</font>
		<font>
			<c:choose>
				<c:when test="${!empty reli.remark}">
					${reli.remark }
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
</div>
					
					
