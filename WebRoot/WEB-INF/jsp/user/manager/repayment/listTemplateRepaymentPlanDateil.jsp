<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table fc_6 mar_t5 bor_t">
	<thead>
		<tr>
			<c:if test="${not empty aheadRepayMode }">
				<td class="fc_3">选择</td>
			</c:if>
			<td class="fc_3">还款流水号</td>
			<td class="fc_3">期数</td>
			<td class="fc_3">还款日</td>
			<td class="fc_3">本息 </td>
			<td class="fc_3">本金</td>
			<td class="fc_3">利息</td>
			<td class="fc_3">剩余本金</td>
			<td class="fc_3">是否还款完成</td>
			<td class="fc_3">是否逾期</td>
			<td class="fc_3">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dividedPayments" items="${dividedPayments}">
			<tr>
				<c:choose>
					<c:when
						test="${not empty aheadRepayMode and dividedPayments.repaybutton == 3}">
						<td class="fc_3"><input type="checkbox" class="allAheadRepaySelect"
							data-opid="${dividedPayments.id }"
							data-periods="${dividedPayments.periods}" onclick="userloanApp.selectPeriodsCheck(this)"/></td>
					</c:when>
					<c:when test="${not empty aheadRepayMode}">
						<td class="fc_3">&nbsp;</td>
					</c:when>
				</c:choose>
				<td class="fc_3">${dividedPayments.dporderno}</td>
				<td class="fc_3">第${dividedPayments.periods}期</td>
				<td class="fc_3">${gj:formatDate(dividedPayments.repayday,'yyyy-MM-dd HH:mm:ss')}</td>
				<td class="fc_3">${dividedPayments.currentpi}</td>
				<td class="fc_3">${dividedPayments.cpprincipal}</td>
				<td class="fc_3">${dividedPayments.cpinterest}</td>
				<td class="fc_3">${dividedPayments.restprincipal}</td>
				<td class="fc_3"><c:choose>
						<c:when test="${dividedPayments.iscomplete == 0}"><span style="color:red;">未还款</span></c:when>
						<c:when test="${dividedPayments.iscomplete == 1}"><span style="color:#366DF0;">已还款</span></c:when>
 						<c:when test="${dividedPayments.iscomplete == 2}">部分还款</c:when>
					</c:choose></td>
				<td class="fc_3"><c:choose>
						<c:when test="${dividedPayments.isoverdue == 0}">未逾期</c:when>
						<c:when test="${dividedPayments.isoverdue == 1}">已逾期</c:when>
					</c:choose></td>
				<td class="fc_3"><c:if
						test="${dividedPayments.iscomplete == 0 or dividedPayments.iscomplete == 3 and dividedPayments.isoverdue == 0}">
						<c:choose>
							<c:when test="${dividedPayments.repaybutton == 1 }">
								<button type="button"
									onclick="userloanApp.low_doRepayMentNormalAll(this)" data-opid="${dividedPayments.id}" data-token = "${token}"
									class="btn btn_bgf60 btn_size100" title="提交后全部还款">正常还款</button>
							</c:when>
							<c:when test="${dividedPayments.repaybutton == 6 }">
								<button type="button"
									onclick="userloanApp.low_doRepayMentSelect(this)" data-opid="${dividedPayments.id}" data-token = "${token}"
									class="btn btn_bgf60 btn_size100" title="提交后选择部分还款或全部还款">正常还款提交</button>
							</c:when>
							<c:when test="${dividedPayments.repaybutton == 2 }">
								<button type="button" class="btn btn_bgf60 btn_size100">未到还款日</button>
							</c:when>
							<c:when test="${dividedPayments.repaybutton == 5 }">
								<button type="button" class="btn btn_bgf60 btn_size100"
								onclick="userloanApp.low_doOverdueRepayMentSelect(this)" data-opid="${dividedPayments.id}">逾期还款</button>
							</c:when>
						</c:choose>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${not empty aheadRepayMode }">
	<div style="margin-top:25px;"></div>
	<label><input type="checkbox" id="allaheadrepay"
		onclick="userloanApp.low_allAheadRepaySelect(this)">&nbsp;全选</label>&nbsp;&nbsp;
	<button type="button"
		onclick="userloanApp.low_aheadRepayMentSelectSubmit(this)"
		class="btn btn_bgf60 btn_size100" id="aheadRepayMentSelectSubmit" style="height:30px;line-height:30px;">提前还款</button>
 </c:if>
