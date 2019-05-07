<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>用户名</td>
			<td>发送方式</td>
			<td>发送时间</td>
			<td>补发时间</td>
			<td>发送email账号</td>
			<td>发送的email内容</td>
			<td>发送端邮箱</td>
 			<td>备注</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody id="queryall_list">
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td class="gj_keyword">${user.username}</td>
				<td>${user.sendtype == 1 ? '手工' : '系统'}</td>
				<td>${gj:formatDate(user.sendtime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td>${gj:formatDate(user.reissuetime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td id="gj_username" class="gj_keyword"><span>${user.email}</span></td>
				<td class="tzui-tips"
					tip="${user.emailcontent == null ? '暂无邮件内容信息' : user.emailcontent }">${gj:getSubStr(user.emailcontent,'20')}</td>
				<td>${user.sendemail}</td>
				<td class="tzui-tips"
					tip="${user.remark == null ? '暂无备注信息' : user.remark }">${gj:getSubStr(user.remark,'20')}</td>
				<td><c:if test="${not empty user.messagetype}">
						<div class="btn-group">
							<c:choose>
								<c:when test="${user.messagetype == 1 }">
									<button class="btn btn-default"
										onclick="emailRecord.low_repeat(this)" data-opid="${user.id}"
										data-email="${user.email}">补发邮箱验证链接邮件</button>
								</c:when>
								<c:when test="${user.messagetype == 2 }">
									<button class="btn btn-default"
										onclick="emailRecord.low_repeat(this)" data-opid="${user.id}"
										data-email="${user.email}">补发邮箱重置验证邮件</button>
								</c:when>
								<c:when test="${user.messagetype == 3 }">
									<button class="btn btn-default"
										onclick="emailRecord.low_repeat(this)" data-opid="${user.id}"
										data-email="${user.email}">补发邮箱重置检验链接邮件</button>
								</c:when>
								<c:otherwise>
									<%--  		 								<button class="btn btn-default" onclick="emailRecord.low_repeat(this)" data-opid="${user.id}" data-email="${user.email}">补发邮件</button> --%>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


