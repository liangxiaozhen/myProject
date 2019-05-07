<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>

<table class="table table-hover">
	<thead>
		<tr class="text-center">
			<td>当前角色</td>
			<td>用户名</td>
			<td>邮箱</td>
			<td>已有角色</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody id="checkbox">
		<c:forEach items="${pagehelper.list}" var="roleuser">
			<div id="role_id" data-roleopid="${roleuser.systemRole.id}"></div>
			<tr class="text-center roleuser role_opid_${roleuser.id}">
				<td class="tzui-tips" tip="${roleuser.systemRole.rolename}">${gj:getSubStr(roleuser.systemRole.rolename,'15')}</td>
				<td class="tzui-tips" tip="${roleuser.username}">${gj:getSubStr(roleuser.username,'15')}</td>
				<td class="tzui-tips" tip="${roleuser.email}">${gj:getSubStr(roleuser.email,'15')}</td>
				<gj:adminuserRole opid="${roleuser.id}" var="role">
					<td class="tzui-tips"
						tip="${role.systemRole.rolename==null?'暂无角色':role.systemRole.rolename}">${gj:getSubStr(role.systemRole.rolename,'5') }</td>
				</gj:adminuserRole>
				<td>
					<button class="btn" onclick="delete_findroleuser(this)"
						data-opid="${roleuser.id}"
						data-roleopid="${roleuser.systemRole.id}">移除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>

