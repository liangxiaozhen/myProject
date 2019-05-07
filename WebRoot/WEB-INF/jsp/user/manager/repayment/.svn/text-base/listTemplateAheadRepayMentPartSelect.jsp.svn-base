<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<table class="table fc_6 mar_t5 bor_t">
	<thead>
		<tr class="table fc_6 mar_t5 bor_t">
			<td class="fc_3">编号</td>
			<td class="fc_3">选择</td>
			<td class="fc_3">用户名</td>
			<td class="fc_3">合计</td>
			<th class="fc_3">期数</th>
			<th class="fc_3">还款状态</th>
 			<td class="fc_3">还款本金</td>
			<td class="fc_3">还款利息</td>
      	</tr>
	</thead>
	<tbody>
		<c:forEach var="repay" items="${repay}" varStatus="status">
			<tr class="table fc_6 mar_t5 bor_t">
				<td class="fc_3">${status.index+1 }</td>
				<td class="fc_3"> 
				<c:choose>
					<c:when test="${repay.hasinvestor == 1 }">
						<input type="checkbox" class="AheadRepayPartSeletUtorderno"
							data-opid="${repay.utorderno}" value="${repay.utorderno}"/>
					</c:when>
					<c:otherwise>
						<span>不能跳期还款,请按期数顺序还款.</span>
					</c:otherwise>
				</c:choose>
 				</td>
				<td class="fc_3">
 					<span>${repay.username}</span>
 				</td>
 				<td class="fc_3"> 
 					${repay.count}
 				</td>
				<td class="fc_3">
					<c:forEach var="repayMent" items="${repay.aheadRepayMentDetail}">
						<c:choose>
							<c:when test="${fn:length(repay.aheadRepayMentDetail) == 1}">
								<span>第${repayMent.period}期</span>
 							</c:when>
							<c:otherwise>
								<span>第${repayMent.period}期</span><hr />
							</c:otherwise>
						</c:choose>
 					</c:forEach>
 				</td>
 				<td class="fc_3">
 				<c:forEach var="repayMent" items="${repay.aheadRepayMentDetail}">
					<c:choose>
						<c:when test="${repayMent.repaystatus == 1}">待还款</c:when>
						<c:when test="${repayMent.repaystatus == 2}">审核中</c:when>
						<c:when test="${repayMent.repaystatus == 3}">待处理</c:when>
						<c:when test="${repayMent.repaystatus == 4}">处理中</c:when>
						<c:when test="${repayMent.repaystatus == 5}">已还款</c:when>
						<c:when test="${repayMent.repaystatus == 6}">还款失败</c:when>
						<c:when test="${repayMent.repaystatus == 7}">审核失败</c:when>
   						<c:otherwise>未知</c:otherwise>
					</c:choose>
					<c:if test="${fn:length(repay.aheadRepayMentDetail) > 1}">
 						<hr />
					</c:if>
				</c:forEach>
				</td>
				<td class="fc_3">
					<c:forEach var="repayMent" items="${repay.aheadRepayMentDetail}">
 						<c:choose>
							<c:when test="${fn:length(repay.aheadRepayMentDetail) == 1}">
								<span>${repayMent.ramount}</span>
 							</c:when>
							<c:otherwise>
								<span>${repayMent.ramount}</span><hr />
							</c:otherwise>
						</c:choose>
  					</c:forEach>
  				</td>
				<td class="fc_3"><c:forEach var="repayMent" items="${repay.aheadRepayMentDetail}">
 						<c:choose>
							<c:when test="${fn:length(repay.aheadRepayMentDetail) == 1}">
								<span>${repayMent.rinterest}</span>
 							</c:when>
							<c:otherwise>
								<span>${repayMent.rinterest}</span><hr />
							</c:otherwise>
						</c:choose>
 					</c:forEach>
 				</td>
 			</tr>
		</c:forEach>
	</tbody>
</table>
<div style="margin-top:15px;"></div>
<div class="col-md-12">
	<label><input type="checkbox" id="AheadPartRepay" onclick="userloanApp.low_AheadRepayPartSelet(this)">&nbsp;全选</label>&nbsp;&nbsp;
	<button type="button" onclick="userloanApp.low_AheadRepayPartSelectSubmit(this)" class="btn btn_bgf60 btn_size100" style="margin-bottom: 20px;" id="AheadRepayPartSelectSubmit">立即提交</button>
<!-- 	<span style="color:red;">提交进行费用试算！</span> -->
	<span class="red" id="submit_error"></span>
</div>