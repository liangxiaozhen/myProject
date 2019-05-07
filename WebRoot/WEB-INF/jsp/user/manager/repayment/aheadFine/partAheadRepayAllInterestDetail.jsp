<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <table class="table fc_6 mar_t5 bor_t">
   		<thead>
   			<tr>
   				<th class="fc_3">用户名</th>
    			<th class="fc_3">本金</th>
   				<th class="fc_3">计划利息</th>
    			<th class="fc_3">实际利息</th>
    			<th class="fc_3">欠收利息罚金</th>
     		</tr>
   		</thead>
   		
   		<tbody>
	   		<c:forEach var="repayMentFineDetail" items="${repayMentFineDetails}">
	   			<tr>
	   				<th class="fc_3">${repayMentFineDetail.username}</th>
	   				<th class="fc_3">${repayMentFineDetail.ramount}</th>
	   				<th class="fc_3">${repayMentFineDetail.rinterest}</th>
	    			<th class="fc_3">${repayMentFineDetail.holdrinterest}</th>
	   				<th class="fc_3">${repayMentFineDetail.harvestfine}</th>
	     		</tr>
     		 </c:forEach>
   		</tbody>
</table>
	<div style="margin-top:15px;"></div>
	<span>合计：${count}</span><br /><br />
	<button type="button" class="btn btn_bgf60 btn_size100" onclick="userloanApp.low_aheadPartRepayMentTask(this)" data-token = "${token}">确定提前还款</button>

 