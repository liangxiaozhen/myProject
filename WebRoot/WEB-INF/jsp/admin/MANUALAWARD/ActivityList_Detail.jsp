<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<body style="font-family: 微软雅黑;">
	<div class="row" style="line-height: 0px; text-align: center;">
		<font color="red" size="4">基本信息</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动ID:</font><font>${al.id }</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动编号:</font><font>${al.actno }</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动名称:</font><font>${al.actname }</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动类型:</font> <font> <c:forEach
				items="${typemaps}" var="tm">
				<c:choose>
					<c:when test="${al.acttype eq tm.key}">
						${tm.value}
					</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动状态:</font> <font> <c:forEach
				items="${statusmaps}" var="sm">
				<c:choose>
					<c:when test="${al.status eq sm.key}">
						${sm.value}
					</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">获奖人审核:</font> <font> <c:forEach
				items="${auditmaps}" var="am">
				<c:choose>
					<c:when test="${al.islistaudit eq am.key}">
						${am.value}
					</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">生成方式:</font> <font> <c:forEach
				items="${rectypemaps}" var="rm">
				<c:choose>
					<c:when test="${al.generatetype eq rm.key}">
						${rm.value}
					</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">手动执行:</font> <font> <c:forEach
				items="${executemaps}" var="em">
				<c:choose>
					<c:when test="${al.allowmanual eq em.key}">
						${em.value}
					</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">执行状态:</font> <font> <c:forEach
				items="${executestatus}" var="et">
				<c:choose>
					<c:when test="${al.executestatus eq et.key}">
						${et.value}
					</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">执行时间:</font> <font> <c:choose>
				<c:when test="${!empty al.executetime}">
					<fmt:formatDate value='${al.executetime}' type="date"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</c:when>
				<c:otherwise>
						--
					</c:otherwise>
			</c:choose>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">获奖人次:</font> <font>${al.awardtimes}</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">获奖人数:</font> <font>${al.awardnumber}</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动开始时间:</font> <font> <c:choose>
				<c:when test="${!empty al.actbegintime}">
					<fmt:formatDate value='${al.actbegintime}' type="date"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</c:when>
				<c:otherwise>
						--
					</c:otherwise>
			</c:choose>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">活动结束时间:</font> <font> <c:choose>
				<c:when test="${!empty al.actendtime}">
					<fmt:formatDate value='${al.actendtime}' type="date"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</c:when>
				<c:otherwise>
						--
					</c:otherwise>
			</c:choose>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">制作人:</font> <font>${al.mademan}</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">制作时间:</font> <font id="addtime-lb">
			<fmt:formatDate value='${al.madetime}' type="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">备注:</font> <font> <c:choose>
				<c:when test="${!empty al.remark}">
					${al.remark}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
</body>
