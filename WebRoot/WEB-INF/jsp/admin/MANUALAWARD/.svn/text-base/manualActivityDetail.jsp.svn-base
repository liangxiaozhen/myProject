<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<body style="font-family: '微软雅黑';">

	<div class="well text-center"
		style="font-size: 16px; background: #d6e9c6; line-height: 0px;">
		手动颁奖活动详情</div>

	<%-- <div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">活动ID:</font><font id="">${maMain.id}</font>
	</div> --%>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">活动编号:</font><font
			id="addman-lb">${maMain.activityNo}</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">活动名称:</font><font
			id="">${maMain.activityName}</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">活动状态:</font> <font>
			<c:if test="${!empty statusmaps}">
				<c:forEach items="${statusmaps}" var="sm">
					<c:choose>
						<c:when test="${sm.key eq maMain.isDealMain}">
								${sm.value}
							</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">子活动数量:</font> <font>${maMain.subActivityNum}</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">生成方式:</font> <font>
			<c:if test="${!empty rectypemaps}">
				<c:forEach items="${rectypemaps}" var="rp">
					<c:choose>
						<c:when test="${rp.key eq maMain.actMType}">
							${rp.value}
						</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">手动执行:</font> <font>
			<c:if test="${!empty executemaps}">
				<c:forEach items="${executemaps}" var="em">
					<c:choose>
						<c:when test="${em.key eq maMain.isManual}">
							${em.value}
						</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">执行状态:</font> <font>
			<c:if test="${!empty executemaps}">
				<c:forEach items="${executemaps}" var="em">
					<c:choose>
						<c:when test="${em.key eq maMain.executeStatus}">
							${em.value}
						</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">执行时间:</font> <font>
			<fmt:formatDate value='${maMain.activityTime}' type="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">设置时间:</font> <font>
			<fmt:formatDate value='${maMain.addTimeMain}' type="date"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">是否为模板:</font> <font>
			<c:if test="${!empty templatemaps}">
				<c:forEach items="${templatemaps}" var="tm">
					<c:choose>
						<c:when test="${tm.key eq maMain.isTempletMain}">
							${tm.value}
						</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">是否需审核:</font> <font>
			<c:if test="${maMain.isAudit eq 1}">是</c:if> <c:if
				test="${maMain.isAudit eq 2}">否</c:if>
		</font>
	</div>

	<div class="row" style="line-height: auto;">
		<font class="col-md-offset-1 col-md-3 text-right">活动设置人:</font> <font>${maMain.addManMain}</font>
	</div>

	<%-- <div class="row" style="line-height: auto;">
		<font class="col-md-3 text-right">备注:</font>
		<font id="addman-lb">${maMain.remark}</font>
	</div> --%>

	<c:if test="${!empty maList}">
		<div class="well text-center"
			style="font-size: 14px; background: #d6e9c6; line-height: 0px;">手动颁奖子活动详情</div>
		<c:forEach items="${maList}" var="ml" varStatus="vs">
			<font>子活动${vs.index+1}</font>
			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">子活动编号:</font> <font>${ml.actno}</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">设置时间:</font> <font>
					<fmt:formatDate value='${ml.settime}' type="date"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">定向方式:</font> <font>
					<c:if test="${!empty directiontype}">
						<c:forEach items="${directiontype}" var="dt">
							<c:choose>
								<c:when test="${dt.key eq ml.objectSetting}">
										${dt.value}
									</c:when>
							</c:choose>
						</c:forEach>
					</c:if>
				</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">奖品名称:</font> <font>${ml.awardname}</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">奖品编号:</font> <font>${ml.awardno}</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">奖品份数:</font> <font>${ml.quantity}</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">奖品金额:</font> <font>
					<c:if test="${!empty ml.awardamount}">
							${ml.awardamount}
						</c:if> <c:if test="${empty ml.awardamount}">
							--
						</c:if>
				</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">设置人:</font> <font>${ml.addman}</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">奖品类型:</font> <font>
					<c:if test="${!empty awardtype}">
						<c:forEach items="${awardtype}" var="at">
							<c:choose>
								<c:when test="${at.key eq ml.awardType}">
										${at.value}
									</c:when>
							</c:choose>
						</c:forEach>
					</c:if>
				</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">获奖人数:</font> <font>${ml.awardNum}</font>
			</div>

			<div class="row" style="line-height: auto;">
				<font class="col-md-offset-1 col-md-3 text-right">颁奖说明:</font> <font>${ml.awardremark}</font>
			</div>
		</c:forEach>
	</c:if>
</body>