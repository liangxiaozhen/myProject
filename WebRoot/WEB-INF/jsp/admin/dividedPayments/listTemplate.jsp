<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center" style="background: #ccc;">
<!--  			<td>分期还款流水号</td> -->
			<td>标号</td>
			<td>期数</td>
			<td>还款日</td>
			<td>本息</td>
			<td>本金</td>
			<td>利息</td>
			<td>剩余本金</td>
			<td>是否逾期</td>
			<td>是否审核</td>
			<td>是否还款完成</td>
<!-- 			<td>备注</td> -->
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
<%--  				<td id="gj_username" class="gj_keyword"><span>${user.dporderno}</span></td> --%>
				<td><a href="javascript:void(0)" onclick="dividedPayments.bulrTno(this)" data-tno="${user.tenderItem.tno}" style="color:blue;">${user.tenderItem.tno}</a></td>
				<td>${user.periods}</td>
				<td>${gj:formatDate(user.repayday,'yyyy-MM-dd HH:mm:ss')}</td>
				<td>${user.currentpi}</td>
				<td>${user.cpprincipal}</td>
				<td>${user.cpinterest}</td>
				<td>${user.restprincipal}</td>
				<td>
					<c:choose>
						<c:when test="${user.isoverdue ==0}"><span style="color:blue;">否</span></c:when>
						<c:when test="${user.isoverdue ==1}"><span style="color:red;">是</span></c:when>
					</c:choose>
				</td>
				<td><c:choose>
						<c:when test="${user.isaudit ==0}">否</c:when>
						<c:when test="${user.isaudit ==1}">是</c:when>
						<c:when test="${user.isaudit ==2}">已审核</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${user.iscomplete == 0}">未还款</c:when>
						<c:when test="${user.iscomplete == 1}"><span style="color:red;">已还款</span></c:when>
						<c:when test="${user.iscomplete == 2}">处理中</c:when>
						<c:when test="${user.iscomplete == 3}">部分还款</c:when>
					</c:choose></td>
<%-- 				<td>${user.remark}</td> --%>
				<td>
					<div class="btn-group">
 						<c:if test="${not empty user.tenderItem and not empty user.tenderItem.iscompensatory and user.tenderItem.iscompensatory == 1 and user.iscomplete != 1 and user.isoverdue == 0}">
 							<button style="margin-right:5px;"class="btn btn-default" onclick="dividedPayments.low_normalCompensatory(this)" data-opid="${user.id}">正常代偿</button>
						</c:if>
						
						<c:if test="${not empty user.tenderItem and not empty user.tenderItem.iscompensatory and user.tenderItem.iscompensatory == 1 and user.iscomplete != 1 and user.isoverdue == 1}">
 							<button style="margin-right:5px;"class="btn btn-primary">逾期代偿</button>
						</c:if>
						<button style="margin-right:10px;" class="btn btn-default" onclick="dividedPayments.low_detail(this)" data-opid="${user.id}">详情</button>
						<c:if test="${user.iscomplete == 0 or user.iscomplete == 3}">
 							<button class="btn btn-danger" onclick="dividedPayments.low_installOfflinePayment(this)" data-opid="${user.id}">设置线下还款</button>
						</c:if>
   					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


