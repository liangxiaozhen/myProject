<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center" style="background: #ccc;">
			<td>ID</td>
			<td>角色名称</td>
			<td>菜单名称</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="systemrole">
			<tr class="text-center" id="gj_systemrole_${systemrole.id}">
				<td>${systemrole.id }</td>
				<td><a href="javascript:void(0)">${systemrole.rolename}</a></td>
				<gj:systemrole opid="${systemrole.id}" var="role">
					<td class="tzui-tips"
						tip="${role.systemResource.menuname==null?'暂无菜单':role.systemResource.menuname}">${gj:getSubStr(role.systemResource.menuname,'30')}</td>
				</gj:systemrole>
				<td>
					<div class="btn-group">
						<button class="btn btn-default" data-opid="${systemrole.id }"
							data-username="${systemrole.rolename}"
							onclick="systemrole.gave(this)">授权</button>
						<button class="btn btn-default" data-opid="${systemrole.id }"
							data-username="${systemrole.rolename}"
							onclick="systemrole.gj_update(this)">修改</button>
						<button class="btn btn-default" data-opid="${systemrole.id }"
							data-username="${systemrole.rolename}"
							onclick="systemrole.gj_delete(this)">删除</button>
						<button class="btn btn-default" data-opid="${systemrole.id }"
							data-username="${systemrole.rolename}"
							onclick="systemrole.saveroleuser(this)">分配用户</button>
						<button class="btn btn-default" data-opid="${systemrole.id }"
							data-username="${systemrole.rolename}"
							onclick="systemrole.findroleuser(this)">已分配用户</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


