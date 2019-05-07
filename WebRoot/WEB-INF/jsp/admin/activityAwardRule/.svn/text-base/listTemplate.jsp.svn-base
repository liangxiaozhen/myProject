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
			<td>活动编号ID</td>
			<td>发放方式</td>
			<td>审核时间</td>
			<td>制表时间</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td>${user.actid}</td>
				<td><span>${user.distributetype == 1 ? '系统' : '人工'}</span></td>
				<td>${gj:formatDate(user.audittime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td>${gj:formatDate(user.addtime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td class="tzui-tips"
					tip="${user.remark ==null ? '暂无备注信息' : user.remark}">${gj:getSubStr(user.remark,'20')}</td>
				<td>
					<div class="btn-group">
						<button class="btn btn-default"
							onclick="activityAwardRule.low_detail(this)"
							data-opid="${user.id}">详情</button>
						<button class="btn btn-default"
							onclick="activityAwardRule.low_update(this)"
							data-opid="${user.id}">修改</button>
						<!-- 								<button class="btn btn-default">删除</button> -->
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


