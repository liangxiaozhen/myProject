<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/regactivity/insert.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
	font-size: 15px
}

label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
$(function(){
	if("${result}" != ''){
		alert("${result}");
	}
})
</script>
</head>
<body style="margin: 50px;">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form method="post" id="insertRegActAwardForm"
					class="form-horizontal"
					action="${pageContext.request.contextPath}/admin/actAward/insertRegActAwardRuleByTemplet.action">
					<div class="well text-center"
						style="font-size: 14px; background: #d6e9c6;">请先完成注册活动规则设置,然后再进行注册活动奖励规则设置</div>

					<!-- =====================注册活动规则奖励表===================== -->
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">指定业务</label>
							<div class="col-sm-3">
								<select id="business" name="business" class="form-control"
									onchange="regInsert.queryTemplet(value)">
									<option value="">--请选择指定业务--</option>
									<c:if test="${!empty business_map}">
										<c:forEach items="${business_map}" var="business">
											<c:choose>
												<c:when test="${regActAwardRule.business eq business.key}">
													<option value="${business.key}" selected="selected">${business.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${business.key}">${business.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">客户端限制 <input
								type="hidden" id="keylist" value="${regActAwardRule.crestrict}" /></label>
							<div class="col-sm-5">
								<c:if test="${!empty crestrict_map}">
									<c:forEach items="${crestrict_map}" var="limit">
										<label class="checkbox-inline"> <input type="checkbox"
											name="crestrict" value="${limit.key}"> ${limit.value}
										</label>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
					<c:if test="${judge eq 1}">
						<div class="form-group">
							<div class="col-md-10">
								<label class="col-sm-4 text-right">限定时间</label>
								<div class="col-sm-2">
									<div class="input-group">
										<input type="text" name="finishtime" class="form-control"
											value="${regActAwardRule.finishtime}"
											placeholder="请输入注册后限定几小时内完成业务" /> <span
											class="input-group-addon">小时</span>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">奖品编号</label>
							<div class="col-sm-3">
								<input type="text" id="awardno" name="awardno"
									value="${award.ano}" class="form-control"
									onblur="regInsert.queryname(value)" /> <input type="hidden"
									id="awardid" name="awardid" value="${award.id}" /><input
									type="hidden" id="actid" name="actid" value="${actid}" /><input
									type="hidden" id="templetId" name="templetId"
									value="${templetId}" /><input type="hidden" name="cid"
									value="${cid}" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">获品的名称</label>
							<div class="col-sm-4">
								<input type="text" id="awardname" name="awardname"
									value="${regActAwardRule.awardname}" readonly="readonly"
									class="form-control" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">奖品份数</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" name="awardcopies" class="form-control"
										value="${regActAwardRule.awardcopies}" placeholder="请输入奖品份数"
										onblur="regInsert.queryCopies(value)" /> <span
										class="input-group-addon">份</span>
								</div>
								<small id="copies" style="color: #A94442;"></small>
							</div>
						</div>
					</div>
					<div id="cost" class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">奖励金额</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" name="quota" class="form-control"
										value="${regActAwardRule.quota}" placeholder="请输入奖励定额金额" /> <span
										class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-10">
							<label class="col-sm-4 text-right">备注</label>
							<div class="col-sm-4">
								<textarea rows="5" name="remark" class="form-control"
									placeholder="注册活动奖励规则备注">${regActAwardRule.remark}</textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<div class="col-sm-7 text-right">
								<c:if test="${hide ne 'hide'}">
									<input type="submit" class="btn" value="继续添加制定规则" />
								</c:if>
								<input type="button" class="btn" value="保存本活动"
									onclick="regInsert.save(this)" />
								<c:if test="${cover ne 'cover'}">
									<button type="button" class="btn"
										onclick="regInsert.previousPage(this)">返回上一页</button>
								</c:if>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>