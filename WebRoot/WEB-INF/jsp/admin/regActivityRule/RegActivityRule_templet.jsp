<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row text-center" style="margin-top: auto;">
	<font size="3">模板生成注册活动</font>
</div>
<br />
<div class="row text-center" style="margin-top: auto;">
	<font class="control-label">请选择模板</font> <select name="actno">
		<option>--请选择--</option>
		<c:if test="${!empty activityMap}">
			<c:forEach items="${activityMap}" var="map">
				<option value="${map.key}">${map.value}</option>
			</c:forEach>
		</c:if>
	</select>
</div>