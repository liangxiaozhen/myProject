<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center" style="background: #ccc;">
			<td>ID</td>
			<td>用户名</td>
			<td>全名</td>
			<td>电话号码</td>
			<td>邮箱账号</td>
			<td>注册时间</td>
			<td>最后登录时间</td>
			<td>禁止登录状态</td>
			<td>用户角色</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td>${user.id}</td>
				<td id="gj_username" class="gj_keyword"><span>${user.username}</span></td>
				<td>${user.fullname}</td>
				<td>${user.mobilephone}</td>
				<td>${user.email}</td>
				<td>${user.regdatestr}</td>
				<td>${user.logintimestr}</td>
				<td id="gj_forbid_${user.id}"><span
					class="${user.forbid==0?'green':'red'}">${user.forbid==0?'未禁止':'禁止登录'}</span>
				</td>
				<gj:adminuserRole opid="${user.id}" var="adminuser">
					<td class="tzui-tips"
						tip="${adminuser.systemRole.rolename==null?'暂无角色':adminuser.systemRole.rolename}">${gj:getSubStr(adminuser.systemRole.rolename,'10')}</td>
				</gj:adminuserRole>
				<td>
					<button class="btn" data-target="#updatepswModal"
						data-toggle="modal" onclick="bindID('${user.id}')">重置密码</button>
					<button class="btn" onclick="save(this)" data-opid="${user.id}"
						data-forbid="${user.forbid}" data-uana="${user.username}">
						<span class="${user.forbid==0?'red':'green'}">${user.forbid==0?'设置禁止登录':'设置允许登录'}</span>
					</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


