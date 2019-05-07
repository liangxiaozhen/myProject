<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center" style="background: #ccc;">
			<td>ID</td>
			<td>增益清算流水号</td>
			<td>还款流水号</td>
 			<td>投资人</td>
			<td>加息券收益</td>
			<td>类现金收益</td>
			<td>假现金收益</td>
			<td>审核状态</td>
			<td>是否发放</td>
			<td>创建时间</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td>${user.id}</td>
				<td>${user.pporderno}</td>
				<td>${user.rorderno}</td>
 				<td>${user.inname}</td>
				<td>${user.intprofit}</td>
				<td>${user.voucherprofit}</td>
				<td>${user.likevoucherprofit}</td>
				<td><c:choose>
						<c:when test="${user.isaudit == 1}">
									审核通过
								</c:when>
						<c:when test="${user.isaudit == 2}">
									审核不通过
								</c:when>
						<c:otherwise>
									未审核
								</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${user.isgrant == 1}">
									已发放
								</c:when>
						<c:when test="${user.isgrant == 2}">
									处理中
								</c:when>
						<c:otherwise>
									未发放
								</c:otherwise>
					</c:choose></td>
				<td>${gj:formatDate(user.madetime,"yyyy-MM-dd")}</td>
				<td>
					<div class="btn-group">
						<button class="btn btn-default"
							onclick="plusPayoutRecordList.low_detail(this)"
							data-opid="${user.id}">详情</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


