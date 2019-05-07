<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>编号</td>
			<td>用户名</td>
			<td>真实名字</td>
			<td>用户手机</td>
			<td>用户邮箱</td>
			<td>用户类型</td>
			<td>注册时间</td>
			<td>是否开通托管账号</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody id="queryall_list">
		<c:forEach items="${pagehelper.list}" var="user" varStatus="index">
			<tr class="text-center">
				<td>${index.index+1}</td>
				<td class="gj_keyword">${user.loginname}</td>
				<td class="gj_keyword">${user.realname}</td>
				<td class="gj_keyword">${user.mobilephone}</td>
				<td class="gj_keyword">${user.email}</td>
				<td>${user.accounttype == 1 ? "个人" : "企业"}</td>
				<td>${gj:formatDate(user.regdate,'yyyy-MM-dd HH:mm:dd')}</td>
				<td>
				<c:choose>
 						<c:when test="${not empty user.userfsaccountinfo and user.userfsaccountinfo.isopenfsinfo == 1}"> 
				 			 是
				 		</c:when>
				 		<c:otherwise>否</c:otherwise>
					</c:choose></td>
				<td>
					<button type="button" class="btn btn-default"
						data-opid="${user.id}" onclick="baseAndFsa.low_detail(this)"
						id="baseAndFsa_detail">详情</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


