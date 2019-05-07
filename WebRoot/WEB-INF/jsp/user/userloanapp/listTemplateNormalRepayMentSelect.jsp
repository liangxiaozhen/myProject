<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table fc_6 mar_t20 bor_t">
	<thead>
		<tr class="table fc_6 mar_t5 bor_t">
			<th class="fc_3">编号</th>
			<th class="fc_3">选择</th>
			<th class="fc_3">用户名</th>
			<th class="fc_3">还款本金</th>
			<th class="fc_3">还款利息</th>
			<th class="fc_3">审核状态</th>
			<th class="fc_3">期数</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="repay" items="${pagehelper.list}" varStatus="status">
			<tr class="table fc_6 mar_t20 bor_t">
				<td class="fc_3">${status.index+1}</td>
				<td class="fc_3"><c:if test="${repay.isaudit == 0 or repay.isaudit == 2}">
						<input type="checkbox" class="normalRepay" data-opid="${repay.id}" />
					</c:if></td>
				<td class="fc_3">${repay.username}</td>
				<td class="fc_3">${repay.ramount + repay.rvoucher}</td>
				<td class="fc_3">${repay.rinterest + repay.rvoucherint}</td>
				<td class="fc_3">
 				<c:choose>
					<c:when test="${repay.isaudit == 0}">待审核</c:when>
					<c:when test="${repay.isaudit == 1}">客服审核中</c:when>
					<c:when test="${repay.isaudit == 2}">审核通过</c:when>
					<c:when test="${repay.isaudit == 3}">审核不通过</c:when>
 				</c:choose>
				</td>
				<td class="fc_3">${repay.periods}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div style="margin-top:20px;">

	<label><input type="checkbox" id="allNormalRepay"
		onclick="userloanApp.low_allNormalRepaySelect(this)">&nbsp;全选</label>

	<button type="button"
		onclick="userloanApp.low_doRepayMentNormalPart(this)"
		class="btn btn_bgf60 btn_size100" id="low_normal">立即还款</button>

	<span class="red" id="submit_error"></span>
</div>