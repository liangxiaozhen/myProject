<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 		<table class="table table-hover">
	 	 	<thead>
		 		<tr class="text-center" style="background:#ccc;">
			 		<td>序号</td>
 			 		<td>还款流水号</td>
			 		<td>还款批次号</td>
			 	  	<td>还款状态</td>
			 		<td>实际本金</td>
			 		<td>实际利息</td>
 	 		 		<td>欠收利息</td>
   			 		<td>利息的管理费</td>
   			 		<td>操作</td>
		 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 		<c:forEach items="${pagehelper.list}" var="user" varStatus="index"> 
		 	 		<tr class="text-center">
		 	 			<td>${index.index +1}</td>
 				 		<td>${user.rorderno}</td>
				 		<td><a href="javascript:void(0)" style="color:blue;" onclick="aheadRealRepayment.bulrrrealbatchno(this)" data-rrealbatchno = "${user.rrealbatchno}">${user.rrealbatchno}</a></td>
				 		<td>
				 			<c:choose>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 1}">待还款</c:when>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 2}">审核中</c:when>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 3}">待处理</c:when>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 4}">处理中</c:when>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 5}"><span style="color:blue;">已还款</span></c:when>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 6}"><span style="color:red;">还款失败</span></c:when>
	 			 				<c:when test="${user.repaymentDetail.repaystatus == 7}"><span style="color:red;">审核失败</span></c:when>
  	 			 			</c:choose>
				 		</td>
				 		<td>${user.rptotalamount + user.rvoucher}</td>
				 		<td>${user.rinterest + user.rvoucherint}</td>
				 		<td>${user.norecrinterest + user.norecrvoucherint}</td>
				 		<td>${user.interestexpense}</td>
    				 	<td>
  				 			<div class="btn-group">
								<button class="btn btn-default" onclick="aheadRealRepayment.low_detail(this)" data-opid="${user.id}">详情</button>
  							</div>
 				 		</td> 
 		 	 		</tr>
	 	 		</c:forEach>
	  	 	</tbody>
		 </table>
		 <div id="page_div">
			<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
		</div>
		
		
