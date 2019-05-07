<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 		<table class="table table-hover">
	 	 	<thead>
		 		<tr class="text-center" style="background:#ccc;">
		 			<td>序号</td>
		 			<td>标号</td>
			 		<td>还款流水号</td>
 			 		<td>还款批次号</td>
			 		<td>借款人</td>
			 		<td>投资人</td>
 			 		<td>还款状态</td>
			 		<td>本金</td>
 	 		 		<td>利息</td>
     			 	<td>操作</td>
		 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 		<c:forEach items="${pagehelper.list}" var="user" varStatus="index"> 
		 	 		<tr class="text-center">
		 	 			<td>${index.index + 1}</td>
		 	 			<td><a href="javascript:void(0)" style="color:blue;" onclick="repaymentDetail.low_tnobulr(this)" data-tno="${user.tenderItem.tno}">${user.tenderItem.tno}</a></td>
		 	 			<td><a href="javascript:void(0)" style="color:blue;" onclick="repaymentDetail.low_rordernobulr(this)" data-opid="${user.repayment.id}">${user.rorderno}</a></td>
 	 			 		<td><a href="javascript:void(0)" style="color:blue;" onclick="repaymentDetail.low_rbatchnobulr(this)" data-rbatchno="${user.rbatchno}">${user.rbatchno}</a></td>
 				 		<td>${user.outaccount.loginname}-${user.outaccount.realname}</td>
				 		<td>${user.inaccount.loginname}-${user.inaccount.realname}</td>
	 			 		<td>
	 			 			<c:choose>
	 			 				<c:when test="${user.repaystatus == 1}">待还款</c:when>
	 			 				<c:when test="${user.repaystatus == 2}">审核中</c:when>
	 			 				<c:when test="${user.repaystatus == 3}">待处理</c:when>
	 			 				<c:when test="${user.repaystatus == 4}">处理中</c:when>
	 			 				<c:when test="${user.repaystatus == 5}"><span style="color:blue;">已还款</span></c:when>
	 			 				<c:when test="${user.repaystatus == 6}"><span style="color:red;">还款失败</span></c:when>
	 			 				<c:when test="${user.repaystatus == 7}"><span>审核失败</span></c:when>
  	 			 			</c:choose>
						</td>
				 		<td>${user.ramount + user.rvoucher}</td>
				 		<td>${user.rinterest + user.rvoucherint}</td>
 	    			 	<td>
	    			 		<button class="btn btn-default" onclick="repaymentDetail.low_detail(this)" data-opid="${user.id}">详情</button>
						</td>
 		 	 		</tr>
	 	 		</c:forEach>
	  	 	</tbody>
		 </table>
		 <div id="page_div">
			<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
		</div>
		
		
