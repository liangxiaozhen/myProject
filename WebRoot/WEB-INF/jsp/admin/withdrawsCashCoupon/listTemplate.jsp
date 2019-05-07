<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>ID</td>
			<td>活动编号</td>
			<td>活动名称</td>
			<td>活动生效日期</td>
			<td>活动截止日期</td>
			<td>执行时间点</td>
			<td>制表时间</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td>${user.id}</td>
				<td id="gj_username" class="gj_keyword"><span>${user.actno}</span></td>
				<td>${user.actname}</td>
				<td>${user.actbtimestr}</td>
				<td>${user.actetimestr}</td>
				<td>${user.executetime}</td>
				<td>${user.addtimestr}</td>
				<td>
					<div class="btn-group">
						<button class="btn btn-default" onclick="low.low_detail(this)"
							data-opid="${user.id}">详情</button>
						<button class="btn btn-default" onclick="low.low_edit(this)"
							data-opid="${user.id}">修改</button>
						<c:if test="${user.status == 1}">
							<button class="btn btn-default" onclick="low.low_status(this)"
								data-opid="${user.id}" data-actno="${user.actno}"
								data-status="${user.status}">停用活动</button>
						</c:if>
						<c:if test="${user.status == 0}">
							<button class="btn btn-default" onclick="low.low_status(this)"
								data-opid="${user.id}" data-actno="${user.actno}"
								data-status="${user.status}">启用活动</button>
						</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


