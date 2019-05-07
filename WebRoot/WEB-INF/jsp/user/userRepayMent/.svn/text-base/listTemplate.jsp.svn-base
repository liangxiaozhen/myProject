<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>还款流水号</td>
			<td>标号</td>
			<td>标的名称</td>
			<td>代还款人</td>
			<td>还款期数</td>
			<td>是否债转还款</td>
			<td>还款模式</td>
			<td>还款金额（本息）</td>
			<td>还款金额（本金）</td>
			<td>还款金额（利息）</td>
			<td>还款时间</td>
			<td>审核状态</td>
			<td>还款状态</td>
			<!--      			 	<td>操作</td> -->
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td>${user.rorderno}</td>
				<td>${user.tenderitemtenderid.tno}</td>
				<td>${user.tenderitemtenderid.tname}</td>
				<td>${user.userproxyaccountid.loginname }</td>
				<td>${user.periods}</td>
				<td>${user.isdarepay == 1 ? "是" :"否"}</td>
				<td><c:choose>
						<c:when test="${user.rmode == 1}">人工</c:when>
						<c:when test="${user.rmode == 2}">系统</c:when>
						<c:when test="${user.rmode == 3}">线下</c:when>
					</c:choose></td>
				<td>${user.rprincipalint}</td>
				<td>${user.ramount}</td>
				<td>${user.rinterest}</td>
				<td>${gj:formatDate(user.rtime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td><c:choose>
						<c:when test="${user.isaudit == 0 }">不需要审核</c:when>
						<c:when test="${user.isaudit == 1 }">审核处理中</c:when>
						<c:when test="${user.isaudit == 2 }">审核已通过</c:when>
						<c:when test="${user.isaudit == 3 }">审核不通过</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${user.repaystatus == 1 }">未还款</c:when>
						<c:when test="${user.repaystatus == 2 }">已还款</c:when>
						<c:when test="${user.repaystatus == 3 }">已提前还款</c:when>
					</c:choose></td>
				<!--   				 		<td> -->
				<%--   				 			<c:if test="${user.isaudit == 3 }"> --%>
				<!-- 	  				 			<div class="btn-group"> -->
				<%-- 									<button class="btn btn-default" onclick="repayMent.low_detail(this)" data-opid="${user.id}">还款</button> --%>
				<!-- 	 							</div> -->
				<%--    				 			</c:if> --%>
				<!--  				 		</td>  -->
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>
