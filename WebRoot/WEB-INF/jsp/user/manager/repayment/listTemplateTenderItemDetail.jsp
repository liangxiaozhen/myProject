<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 <table class="table fc_6 mar_t5 bor_t">
	<thead>
		<tr>
			<td  class="fc_3">序号</td>
			<td  class="fc_3">标的名称</td>
			<td  class="fc_3">标的金额</td>
			<td  class="fc_3">已入账金额</td>
			<td  class="fc_3">进度</td>
 			<td  class="fc_3">标的状态</td>
 			<td  class="fc_3">还款类型</td>
 			<td  class="fc_3">还款计划</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="tenderItems" items="${pagehelper.list}" varStatus="index">
			<tr>
				<td  class="fc_3">${index.index + 1 }</td>
				<td class="fc_3" title="${tenderItems.tname}">${gj:getSubStr(tenderItems.tname,'10')}</td>
				<td  class="fc_3"><fmt:formatNumber value="${tenderItems.tamount}" type="currency" /></td>
				<td  class="fc_3"><fmt:formatNumber value="${tenderItems.finishtamount}" type="currency" /></td>
				<td  class="fc_3"><fmt:formatNumber value="${tenderItems.finishtamount/tenderItems.tamount}" type="percent" /></td>
 				<td  class="fc_3">
 					<c:choose>
 						<c:when test="${tenderItems.tstatus == 1}">待录入</c:when>
						<c:when test="${tenderItems.tstatus == 2}">待投标</c:when>
						<c:when test="${tenderItems.tstatus == 3}">投标中</c:when>
						<c:when test="${tenderItems.tstatus == 4}">已流标</c:when>
						<c:when test="${tenderItems.tstatus == 5}">待放款</c:when>
						<c:when test="${tenderItems.tstatus == 6}">待生成还款计划</c:when>
						<c:when test="${tenderItems.tstatus == 7}">还款中</c:when>
						<c:when test="${tenderItems.tstatus == 8}">已完成</c:when>
						<c:when test="${tenderItems.tstatus == 9}">录入失败</c:when>
						<c:when test="${tenderItems.tstatus == 10}">录入过期</c:when>
						<c:when test="${tenderItems.tstatus == 11}">录入放弃</c:when>
						
   					</c:choose>
  
 				</td>
 				<td class="fc_3" title="${tenderItems.repaymenttype == 1 ? '及时还款 无需客服审核直接提交还款' : '冻结还款 提交成功后客服审核提交还款' }">
 					<c:choose>
 						<c:when test="${tenderItems.repaymenttype == 1 }">及时还款</c:when>
 						<c:otherwise>冻结还款</c:otherwise>
 					</c:choose>
 				</td>
				<td  class="fc_3">
				<c:choose> 
					<c:when test="${ tenderItems.tstatus == 7 }">
 						<button type="button"
							onclick="userloanApp.low_showRepaymentPlanDateil(this)" data-opid="${tenderItems.id}"
							class="btn" id="showRepaymentPlanDateil" style="width: 100px;height: 32px;color:#fff;background:#217AFF;">查看</button>
 					</c:when>
 					<c:when test="${ tenderItems.tstatus == 8 }">
 						<button type="button" class="btn btn_bgf60" style="width: 100px;height: 32px;">已还款</button>
 					</c:when>
 					<c:otherwise>
 							<button type="button" class="btn btn_bgf60" style="width: 100px;height: 32px;">未生成</button>
 					</c:otherwise>
 				</c:choose>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${pagehelper.size > 0  }">
	 <div id="page_div" style="margin-top:20px;">
		<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
	</div>
</c:if>
