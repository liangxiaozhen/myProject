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
		<font class="col-md-3 text-right">标题:</font> <font>${snl.businessName}</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">定向编号:</font> <font>${snl.businessNo }</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">定向类型:</font> <font id=""> <c:forEach
				items="${directionmold}" var="dm">
				<c:if test="${snl.businessType eq dm.key}">
					${dm.value}
				</c:if>
			</c:forEach>
		</font>

	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">业务名称:</font> <font id=""> <c:choose>
				<c:when test="${!empty snl.systemBusTypeStr}">
					${snl.systemBusTypeStr}
				</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>

		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">短信通知:</font> <font id=""> <c:choose>
				<c:when test="${!empty snl.smsTypeStr}">
					${snl.smsTypeStr}
				</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</font>
	</div>
	<hr>


	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">定向方式:</font> <font> <c:forEach
				items="${direction_type}" var="dt">
				<c:if test="${snl.nameMode eq dt.key }">
					${dt.value}
				</c:if>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">启用状态:</font> <font> <c:forEach
				items="${use_status}" var="ut">
				<c:if test="${snl.isUse eq ut.key }">
					${ut.value}
				</c:if>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">是否引用:</font> <font> <c:forEach
				items="${quote_status}" var="qs">
				<c:if test="${snl.isQuote eq qs.key }">
					${qs.value}
				</c:if>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">生成方式:</font> <font> <c:forEach
				items="${rectype_map}" var="rm">
				<c:if test="${snl.generateType eq rm.key }">
					${rm.value}
				</c:if>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">是否为模板:</font> <font> <c:forEach
				items="${template_judge}" var="tj">
				<c:if test="${snl.isTemplet eq tj.key }">
					${tj.value}
				</c:if>
			</c:forEach>
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">添加时间:</font> <font> <fmt:formatDate
				value="${snl.addTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
		</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">设置人:</font> <font>${snl.addMan}</font>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<font class="col-md-3 text-right">备注:</font> <font> <c:choose>
				<c:when test="${!empty snl.remark}">
					${snl.remark}
				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>
		</font>
	</div>
</body>
