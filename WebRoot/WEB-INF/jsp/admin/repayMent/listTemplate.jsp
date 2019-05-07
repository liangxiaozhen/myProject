<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center" style="background: #ccc;">
 			<td>编号</td>
			<td>标号</td>
			<td>还款批次号</td>
 			<td>还款流水</td>
			<td>借款用户</td>
			<td>投资用户</td>
 			<td>还款期数</td>
			<td>是否债转</td>
			<td>还款时间</td>
			<td>还款状态</td>
  			<td>操作</td>
		</tr>
	</thead>
	<tbody id="queryall_list">
		<c:forEach items="${pagehelper.list}" var="user" varStatus="index">
			<tr class="text-center">
 				<td>${index.index+1}</td>
				<td><a href="javascript:void(0)" style="color:blue;" onclick="repayMent.bulrTno(this)" data-tno = "${user.tenderitemtenderid.tno}">${user.tenderitemtenderid.tno}</a></td>
				<td><a href="javascript:void(0)" style="color:blue;" onclick="repayMent.bulrRbatchno(this)" data-rbatchno = "${user.rbatchno}">${user.rbatchno}</a></td>
 				<td class="gj_keyword">${user.rorderno}</td>
 				<td>${user.useroutaccountid.loginname}-${user.useroutaccountid.realname}</td>
				<td>${user.userinaccountid.loginname}-${user.userinaccountid.realname}</td>
  				<td>${user.periods}</td>
				<td>${user.isdarepay ==1 ? '是':'否'}</td>
				<td>${gj:formatDate(user.rtime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td>
					<c:choose>
						<c:when test="${user.repaystatus == 1}">待还款</c:when>
						<c:when test="${user.repaystatus == 2}">审核中</c:when>
						<c:when test="${user.repaystatus == 3}">待处理</c:when>
						<c:when test="${user.repaystatus == 4}">处理中</c:when>
						<c:when test="${user.repaystatus == 5}">已还款</c:when>
						<c:when test="${user.repaystatus == 6}">还款失败</c:when>
						<c:when test="${user.repaystatus == 7}">审核失败</c:when>
   						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</td>
  				<td>
					<div class="btn-group">
						<button class="btn btn-default"
							onclick="repayMent.low_detail(this)" data-opid="${user.id}">详情</button>
 					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>
 
