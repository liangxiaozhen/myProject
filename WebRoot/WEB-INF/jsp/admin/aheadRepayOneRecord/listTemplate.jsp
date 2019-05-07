<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 		<table class="table table-hover">
	 	 	<thead>
		 		<tr class="text-center" style="background: #ccc;">
			 		<td>标号</td>
 			 		<td>标名称</td>
			 		<td>借款人</td>
			 		<td>投资人</td>
<!-- 			 		<td>奖励流水号</td> -->
<!-- 			 		<td>还款流水号</td> -->
	 		 		<td>欠收利息</td>
	 		 		<td>借款人罚金</td>
	 		 		<td>奖励方式</td>
	 		 		<td>是否处理</td>
	 		 		<td>是否审核 </td>
<!-- 	 		 		<td>审核时间</td> -->
<!-- 	 		 		<td>创建时间</td> -->
<!-- 	 		 		<td>处理日期</td> -->
	 		 		<td>审核人</td>
	 		 		<td>备注</td>
     			 	<td>操作</td>
		 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 		<c:forEach items="${pagehelper.list}" var="user"> 
		 	 		 <tr class="text-center">
				 		<td>${user.tno }</td>
	 			 		<td>${user.tname }</td>
				 		<td>${user.bmanidusername }</td>
				 		<td>${user.investoridusername }</td>
<%-- 				 		<td>${user.arorderno }</td> --%>
<%-- 				 		<td>${user.rorderno }</td> --%>
		 		 		<td>${user.prinpoorint }</td>
		 		 		<td>${user.bpenalty }</td>
		 		 		<td>
	 		 		 		<c:choose>
			 		 			<c:when test="${user.penaltytype == 1}">定额</c:when>
			 		 			<c:when test="${user.penaltytype == 2}">百分比</c:when>
			 		 			<c:when test="${user.penaltytype == 3}">最高</c:when>
	 		 		 		</c:choose>
		 		 		</td>
		 		 		<td>
 		 		 			<c:choose>
			 		 			<c:when test="${user.isgrant == 0}">未处理</c:when>
			 		 			<c:when test="${user.isgrant == 1}">已处理</c:when>
 	 		 		 		</c:choose>
		 		 		</td>
		 		 		<td>
		 		 			<c:choose>
			 		 			<c:when test="${user.isaudit == 0}">未审核</c:when>
			 		 			<c:when test="${user.isaudit == 1}">审核通过</c:when>
			 		 			<c:when test="${user.isaudit == 2}">审核不通过</c:when>
  	 		 		 		</c:choose>
  	 		 		 	</td>
<%-- 		 		 		<td>${gj:formatDate(user.audittime,'yyyy-MM-dd HH:mm:ss')}</td> --%>
<%-- 		 		 		<td>${gj:formatDate(user.madetime,'yyyy-MM-dd HH:mm:ss')}</td> --%>
<%-- 		 		 		<td>${gj:formatDate(user.payoutdate,'yyyy-MM-dd HH:mm:ss')}</td> --%>
		 		 		<td>${user.auditman }</td>
		 		 		<td>${user.remark }</td>
	     			 	<td>
	     			 		<button type="button" class="btn btn-default" onclick="aheadRepayOneRecord.low_detail(this)" data-opid="${user.id}">详情</button>
	     			 		<button type="button" class="btn btn-default" onclick="aheadRepayOneRecord.low_audit(this)" data-opid="${user.id}">审核</button>
 	     			 	</td>
		 			</tr>
	 	 		</c:forEach>
	  	 	</tbody>
		 </table>
		 <div id="page_div">
			<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
		</div>
		
		
