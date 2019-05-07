<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center" style="background: #ccc;">
			<td>序号</td>
			<td>标号</td>
			<td>标名称</td>
 			<td>还款批次号</td>
			<td>还款流水号</td>
   			<td>投资人</td>
			<td>利息管理费</td>
			<td>利息管理费收取类型</td>
			<td>是否处理</td>
 			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user" varStatus="index">
			<tr class="text-center">
				<td>${index.index  + 1 }</td>
				<td><a href="javascript:void(0)" style="color:blue;" onclick="interestExpenseRecord.low_tnobulr(this)" data-tno = "${user.tenderItem.tno }">${user.tenderItem.tno }</a></td>
				<td>${user.tenderItem.tname }</td>
				<td><a href="javascript:void(0)" style="color:blue;" onclick="interestExpenseRecord.low_batchnobulr(this)" data-batchno = "${user.batchno}">${user.batchno}</a></td>
				<td>${user.rorderno}</td>
   				<td>${user.investoridaccount.loginname}-${user.investoridaccount.realname}</td>
				<td>${user.intexpfee}</td>
				<td><c:choose>
						<c:when test="${user.ietype == 1}">百分比</c:when>
						<c:when test="${user.ietype == 2}">最高</c:when>
 					</c:choose></td>
				<td>${user.isdeal  == 1 ? '是' : '否'}</td>
 				<td>
					<div class="btn-group">
						<button class="btn btn-default"
							onclick="interestExpenseRecord.low_detail(this)"
							data-opid="${user.id}">详情</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


