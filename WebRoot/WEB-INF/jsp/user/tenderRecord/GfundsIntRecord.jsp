<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="UTF-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#reset").click(function() {
			$("#tno").val('');
			$("#tname").val('');
			$("#isgrant").val('');
		})
	})

	/*** 分页查询标的站岗利息记录表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#gfirForm").submit()
	}
</script>
</head>
<body>
	<div class="container">
		<div style="margin-top: 50px;">
			<form
				action="<%=basePath%>/user/gfundsIntNotes/queryMyGfundsIntNotes.action"
				id="gfirForm" method="post">
				<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
					type="hidden" id="pageSize" name="pageSize" value="" />
				<ul class="list-inline">
					<li><label>标号：</label><input type="text" id="tno"
						name="tender.tno" value="${echodata.tender.tno}" /></li>
					<li><label>标的名称：</label><input type="text" id="tname"
						name="tender.tname" value="${echodata.tender.tname}" /></li>
					<li><label>是否发放：</label> <select id="isgrant" name="isgrant"><c:if
								test="${!empty isgrantmap}">
								<option value="">--请选择--</option>
								<c:forEach items="${isgrantmap}" var="grant">
									<c:choose>
										<c:when test="${echodata.isgrant eq grant.key}">
											<option value="${grant.key}" selected="selected">${grant.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${grant.key}">${grant.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
					</select></li>
					<li></li>
					<li></li>
					<li><input type="submit" value="查询" class="btn" /> <input
						type="button" value="重置" class="btn" id="reset" /></li>
				</ul>
			</form>
		</div>
		<table class="table">
			<thead style="background: #ccc;">
				<tr>
					<th>站岗利息流水号</th>
					<th>站岗利息编号</th>
					<th>标号</th>
					<th>标名称</th>
					<th>平台杂项付款人</th>
					<th>投标金额</th>
					<th>奖励金额</th>
					<th>是否发放</th>
					<th>审核状态</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty pagehelper.list}">
					<c:forEach items="${pagehelper.list}" var="fund">
						<tr>
							<td>${fund.gfiorderno}</td>
							<td>${fund.guardinterest.gfundsintno}</td>
							<td>${fund.tender.tno}</td>
							<td>${fund.tender.tname}</td>
							<td>${fund.pmiscpayman}</td>
							<td>${df.format(fund.tenderamount)}</td>
							<td>${df.format(fund.rewardamount)}</td>
							<td><c:if test="${!empty isgrantmap}">
									<c:forEach items="${isgrantmap}" var="grant">
										<c:choose>
											<c:when test="${fund.isgrant eq grant.key}">${grant.value}</c:when>
										</c:choose>
									</c:forEach>
								</c:if></td>
							<td><c:if test="${!empty isauditmap}">
									<c:forEach items="${isauditmap}" var="audit">
										<c:choose>
											<c:when test="${fund.isaudit eq audit.key}">${audit.value}</c:when>
										</c:choose>
									</c:forEach>
								</c:if></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<%@ include file="./../../common/pagehelper.jsp"%>
	</div>
</body>
</html>