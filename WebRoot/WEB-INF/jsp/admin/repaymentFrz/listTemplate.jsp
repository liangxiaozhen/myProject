<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 		<table class="table table-hover">
	 	 	<thead>
		 		<tr class="text-center" style="background:#ccc;">
			 		<td>序号</td>
	 		 		<td>标的编号</td>
 	 		 		<td>还款批次号</td>
 	 		 		<td>借款人</td>
	 		 		<td>冻结金额</td>
 			 		<td>还款模式</td>
   	 		 		<td>状态</td>
   	 		 		<td>总笔数/成功笔数/失败笔数</td>
  	 		 		<td>操作</td>
   		 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 		<c:forEach items="${pagehelper.list}" var="user" varStatus="index"> 
		 	 		<tr class="text-center">
		 	 			<td>${index.index +1}</td>
				 		<td><a href="javascript:void(0)" style="color:blue;" onclick="repaymentFrz.low_tnobulr(this)" data-tno = "${user.product}">${user.product}</a></td>
 				 		<td>${user.batchno}</td>
 				 		<td>${user.useroutaccountid.loginname}-${user.useroutaccountid.realname}</td>
				 		<td>${user.amount}</td>
 				 		<td>
				 			<c:choose>
				 				<c:when test="${user.isahead == 0 and user.isproxyrepay == 0 and  user.isoverdue == 0}">正常还款</c:when>
				 				<c:when test="${user.isahead == 1}">提前还款</c:when>
				 				<c:when test="${user.isoverdue == 1}">逾期还款</c:when>
				 			    <c:when test="${user.isproxyrepay == 1}">代偿还款</c:when>
 				 			</c:choose>
				 		</td>
   				 		<td>
							<c:choose>
								<c:when test="${user.status == 0}"><span>取消</span></c:when>
								<c:when test="${user.status == 1}"><span>冻结成功</span></c:when>
								<c:when test="${user.status == 2}"><span>冻结失败</span></c:when>
								<c:when test="${user.status == 3}"><span>审核中</span></c:when>
								<c:when test="${user.status == 4}"><span>待处理</span></c:when>
								<c:when test="${user.status == 5}"><span>处理中 </span></c:when>
								<c:when test="${user.status == 6}"><span style="color:blue;">处理成功</span></c:when>
								<c:when test="${user.status == 7}"><span >审核失败</span></c:when>
								<c:when test="${user.status == 8}"><span >解冻成功</span></c:when>
   							</c:choose>
 						</td>
 						<td>${user.count}/${user.successcount}/${user.failcount}</td>
 						<td>
 							<button class="btn btn-default" onclick="repaymentFrz.low_detail(this)" data-opid="${user.id}">详情</button>
 							<c:if test="${user.status == 3}">
 	 							<button class="btn btn-default" onclick="repaymentFrz.low_isauditModalShow(this)" 
 	 							data-opid="${user.id}" data-tno="${user.product}" data-batchno="${user.batchno}" data-realname="${user.useroutaccountid.realname}"
 	 							data-loginname="${user.useroutaccountid.loginname}">审核</button>
 							</c:if>

 							<c:if test="${user.status == 4}">
 	 							<button class="btn btn-default" onclick="repaymentFrz.low_repayMentFileUpload(this)" 
 	 							data-opid="${user.id}">上传文件</button>
 							</c:if>

							<c:if test="${user.status == 0 or user.status == 1}">
 	 							<button class="btn btn-default" onclick="repaymentFrz.low_unfreezeDeal(this)" id="low_unfreezeDeal_${user.id}"
 	 							data-opid="${user.id}">解冻</button>
 							</c:if>

  						</td>
   		 	 		</tr>
	 	 		</c:forEach>
	  	 	</tbody>
		 </table>
		 <div id="page_div">
			<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
		</div>
		 