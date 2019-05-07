<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr>
			<c:if test="${not empty aheadRepayMode }">
				<td>选择</td>
			</c:if>
			<td>分期还款流水号</td>
			<td>期数(第几期)</td>
			<td>还款日</td>
			<td>当期本息 (当期总的本+息)</td>
			<td>当期本金</td>
			<td>当期利息</td>
			<td>剩余本金</td>
			<td>是否还款完成</td>
			<td>是否逾期</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dividedPayments" items="${dividedPayments}">
			<tr>
				<c:choose>
					<c:when
						test="${not empty aheadRepayMode and dividedPayments.repaybutton == 3}">
						<td><input type="checkbox" class="allAheadRepaySelect"
							data-opid="${dividedPayments.id }"
							data-periods="${dividedPayments.periods}" onclick="userloanApp.selectPeriodsCheck(this)"/></td>
					</c:when>
					<c:when test="${not empty aheadRepayMode}">
						<td>&nbsp;</td>
					</c:when>
				</c:choose>
				<td>${dividedPayments.dporderno}</td>
				<td>第${dividedPayments.periods}期</td>
				<td>${gj:formatDate(dividedPayments.repayday,'yyyy-MM-dd HH:mm:ss')}</td>
				<td>${dividedPayments.currentpi}</td>
				<td>${dividedPayments.cpprincipal}</td>
				<td>${dividedPayments.cpinterest}</td>
				<td>${dividedPayments.restprincipal}</td>
				<td><c:choose>
						<c:when test="${dividedPayments.iscomplete == 0}">未还款</c:when>
						<c:when test="${dividedPayments.iscomplete == 1}">已还款</c:when>
						<c:when test="${dividedPayments.iscomplete == 2}">处理中</c:when>
						<c:when test="${dividedPayments.iscomplete == 3}">部分还款</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${dividedPayments.isoverdue == 0}">未逾期</c:when>
						<c:when test="${dividedPayments.isoverdue == 1}">已逾期</c:when>
					</c:choose></td>
				<td><c:if
						test="${dividedPayments.iscomplete == 0 or dividedPayments.iscomplete == 3 and dividedPayments.isoverdue == 0}">
						<c:choose>
							<c:when test="${dividedPayments.repaybutton == 1 }">
								<button type="button"
									onclick="userloanApp.low_doRepayMentNormalAll(this)" data-opid="${dividedPayments.id}"
									class="btn btn-primary">正常还款全部还款</button>
							</c:when>
							<c:when test="${dividedPayments.repaybutton == 6 }">
								<button type="button"
									onclick="userloanApp.low_doRepayMentSelect(this)" data-opid="${dividedPayments.id}"
									class="btn btn-primary">正常还款下一步</button>
							</c:when>
							<c:when test="${dividedPayments.repaybutton == 2 }">
								<button type="button" class="btn btn-primary">未到还款日</button>
							</c:when>
							<c:when test="${dividedPayments.repaybutton == 5 }">
								<button type="button" class="btn btn-primary" 
								onclick="userloanApp.low_doOverdueRepayMentSelect(this)" data-opid="${dividedPayments.id}">逾期还款</button>
							</c:when>
						</c:choose>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${not empty aheadRepayMode }">
	<label><input type="checkbox" id="allaheadrepay"
		onclick="userloanApp.low_allAheadRepaySelect(this)">&nbsp;全选</label>
	<button type="button"
		onclick="userloanApp.low_aheadRepayMentSelectSubmit(this)"
		class="btn btn-primary" id="aheadRepayMentSelectSubmit">提前还款</button>
</c:if>
