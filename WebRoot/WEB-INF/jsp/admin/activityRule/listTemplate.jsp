<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>活动编号</td>
			<td>活动名称</td>
			<td>活动类型</td>
			<td>设置日期</td>
			<td>活动生效日期</td>
			<td>活动截止日期</td>
			<td>累投计算方式</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody id="queryall_list">
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td class="gj_keyword"><span>${user.actno}</span></td>
				<td>${user.actname}</td>
				<td>${user.acttype == 1?"累投":"单标"}</td>
				<td class="tzui-tips"
					tip="${gj:formatDate(user.settime,'yyyy-MM-dd HH:dd:ss')}">${gj:formatDate(user.settime,"yyyy-MM-dd")}</td>
				<td class="tzui-tips"
					tip="${gj:formatDate(user.actbtime,'yyyy-MM-dd HH:dd:ss')}">${gj:formatDate(user.actbtime,"yyyy-MM-dd")}</td>
				<td class="tzui-tips"
					tip="${gj:formatDate(user.actetime,'yyyy-MM-dd HH:dd:ss')}">${gj:formatDate(user.actetime,"yyyy-MM-dd")}</td>
				<td>${user.tctype==1?"标内":"全局"}</td>
				<td class="tzui-tips"
					tip="${ user.remark == null ? '暂无备注信息' : user.remark }">${gj:getSubStr(user.remark,'20')}</td>
				<td>
					<div class="btn-group">
						<button class="btn btn-default"
							onclick="activityRule.low_detail(this)" data-opid="${user.id}">详情</button>
						<button class="btn btn-default"
							onclick="activityRule.low_update(this)" data-opid="${user.id}">修改</button>
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


