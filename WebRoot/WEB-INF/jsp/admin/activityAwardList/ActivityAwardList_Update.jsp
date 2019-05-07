<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>ActivityAwardList_Update</title>
<script type="text/javascript">
function activityAwardList(){
	   window.location.href="${pageContext.request.contextPath }/activityAwardList/selectActivityAwardListByCondition.action";
}

</script>
<style type="text/css">
#id {
	margin: 40px;
}

hr {
	margin: 10px;
}
</style>
</head>
<body style="font-family: '微软雅黑';">
	<form
		action="${pageContext.request.contextPath}/activityAwardList/updateActivityAwardList.action"
		method="post" id="updateAwardForm">
		<input type="hidden" value="${activityAwardList.id }" name="id">
		<div style="margin-bottom: 33px;"></div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">ID</font>&nbsp;&nbsp;：<label id="addman-lb">${activityAwardList.id }</label>
			</div>
		</div>

		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1 ">
				<font size="3">活动编号ID</font>&nbsp;&nbsp;： <label id="addtime-lb">${activityAwardList.actid }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">用户ID</font>&nbsp;&nbsp;： <label id="addman-lb">
					${activityAwardList.baseid } </label>
			</div>
		</div>

		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1 ">
				<font size="3">用户名</font>&nbsp;&nbsp;： <label id="addtime-lb">
					${activityAwardList.username } </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">奖品编号</font>&nbsp;&nbsp;： <label id="addman-lb">
					${activityAwardList.awardno } </label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1 ">
				<font size="3">奖品名称</font>&nbsp;&nbsp;： <label id="addtime-lb">
					${activityAwardList.awardname } </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">获奖金额</font>&nbsp;&nbsp;： <label id="addman-lb">
					${activityAwardList.awardmoney } </label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1 ">
				<font size="3">奖品个数</font>&nbsp;&nbsp;： <label id="addtime-lb">
					${activityAwardList.awardquantity } </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				<font size="3">奖品状态</font>&nbsp;&nbsp;： <label id="addtime-lb">
					${activityAwardList.status } </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				<font size="3">制作人</font>&nbsp;&nbsp;： <label id="addman-lb">
					${activityAwardList.mademan } </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				<font size="3">制作时间</font>&nbsp;&nbsp;： <label id="addtime-lb">
					<fmt:formatDate value='${activityAwardList.madetime }' type='both' />
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				<font size="3">审核人</font>&nbsp;&nbsp;： <label id="addman-lb">
					${activityAwardList.auditman } </label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">审核时间</font>&nbsp;&nbsp;： <label id="addtime-lb">
					<fmt:formatDate value='${activityAwardList.audittime}' type='both' />
				</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				<font size="3">处理人</font>&nbsp;&nbsp;： <label id="addman-lb">
					${activityAwardList.dealman } </label>
			</div>
		</div>

		<hr>


		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				<font size="3">处理时间</font>&nbsp;&nbsp;： <label><fmt:formatDate
						value='${activityAwardList.dealtime }' type='both' /></label>
			</div>
		</div>
		<hr>


		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				<font size="3">备注</font>&nbsp;&nbsp;： <label><p
						id="remarkOld">${activityAwardList.remark }</p></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				<font size="3">继续备注</font>&nbsp;&nbsp;： <input id="remarkNew"
					type="text" name="remark"
					style="width: 200px; height: 25px; font-size: 15px" />
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<button class="btn btn-primary" type="button"
					style="margin-left: 200px" onclick="updateActivityAwardList()">修改</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="activityAwardList()">返回列表</button>
			</div>
		</div>

	</form>
	<script type="text/javascript">
				function updateActivityAwardList(){
					var remarkOld=$("#remarkOld").text();
					var remarkNew=$("#remarkNew").val();
					var remark=remarkOld+"  "+remarkNew;
					$("#remarkNew").val(remark);
					$("form:first").submit();
					
				}
			</script>
</body>
</html>
