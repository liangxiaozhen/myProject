<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table table-hover">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>ID</td>
			<td>注册活动ID</td>
			<td>注册后时间限制</td>
			<td>制表时间</td>
			<td>奖品的ID</td>
			<td>奖品的名称</td>
			<td>奖品份数</td>
			<td>奖励方式</td>
			<td>发放方式</td>
			<td>备注</td>
			<td>制表人</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td>${user.id}</td>
				<td class="gj_keyword"><span>${user.actid}</span></td>
				<td>${user.finishtime}</td>
				<td>${gj:formatDate(user.addtime,'yyyy-MM-dd HH:mm:dd')}</td>
				<td>${user.awardid}</td>
				<td>${user.awardname}</td>
				<td>${user.awardcopies}</td>
				<td>${user.awardtype}</td>
				<td>${user.distributetype}</td>
				<td class="tzui-tips"
					tip="${user.remark==null?'暂无备注信息':user.remark}">${gj:getSubStr(user.remark,'8')}</td>
				<td>${user.addman}</td>
				<td>
					<div class="btn-group">
						<button class="btn btn-default"
							onclick="wCCAwardRule.low_detail(this)" data-opid="${user.id}"
							data-actid="${user.actid}">详情</button>
						<button class="btn btn-default"
							onclick="wCCAwardRule.low_edit(this)" data-opid="${user.id}">修改</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


