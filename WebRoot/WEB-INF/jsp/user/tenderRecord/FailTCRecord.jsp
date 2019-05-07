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
<title>标的流标补偿记录</title>
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

	/*** 分页查询标的流标补偿记录 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#ftcrForm").submit()
	}
</script>
</head>
<body>
	<div class="container">
		<div style="margin-top: 50px;">
			<form action="<%=basePath%>/user/failtcrecord/query.action"
				id="ftcrForm" method="post">
				<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
					type="hidden" id="pageSize" name="pageSize" value="" />
				<ul class="list-inline">
					<li><label>标号：</label><input type="text" id="tno"
						name="tenderitem.tno" value="${echodata.tenderitem.tno}" /></li>
					<li><label>标的名称：</label><input type="text" id="tname"
						name="tenderitem.tname" value="${echodata.tenderitem.tname}" /></li>
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
					<th>流标补偿流水号</th>
					<th>流标补偿编号</th>
					<th>标号</th>
					<th>标名称</th>
					<th>平台杂项付款人</th>
					<th>投标金额</th>
					<th>奖励金额</th>
					<th>奖励方式</th>
					<th>奖品名称</th>
					<th>奖额</th>
					<th>是否发放</th>
					<th>审核状态</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty pagehelper.list}">
					<c:forEach items="${pagehelper.list}" var="ftcr">
						<tr>
							<td>${ftcr.ftcorderno}</td>
							<td>${ftcr.failtcompensate.failtcno}</td>
							<td>${ftcr.tenderitem.tno}</td>
							<td>${ftcr.tenderitem.tname}</td>
							<td>${ftcr.pmiscpayman}</td>
							<td><c:if test="${!empty ftcr.tenderamount}">${df.format(ftcr.tenderamount)}</c:if></td>
							<td><c:if test="${!empty ftcr.rewardamount}">${df.format(ftcr.rewardamount)}</c:if></td>
							<td><c:if test="${!empty payouttypemap}">
									<c:forEach items="${payouttypemap}" var="ptype">
										<c:choose>
											<c:when test="${ftcr.payouttype eq ptype.key}">${ptype.value}</c:when>
										</c:choose>
									</c:forEach>
								</c:if></td>
							<td>${ftcr.awardname}</td>
							<td><c:if test="${!empty ftcr.awardamount}">${df.format(ftcr.awardamount)}</c:if></td>
							<td><c:if test="${!empty isgrantmap}">
									<c:forEach items="${isgrantmap}" var="grant">
										<c:choose>
											<c:when test="${ftcr.isgrant eq grant.key}">${grant.value}</c:when>
										</c:choose>
									</c:forEach>
								</c:if></td>
							<td><c:if test="${!empty isauditmap}">
									<c:forEach items="${isauditmap}" var="audit">
										<c:choose>
											<c:when test="${ftcr.isaudit eq audit.key}">${audit.value}</c:when>
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