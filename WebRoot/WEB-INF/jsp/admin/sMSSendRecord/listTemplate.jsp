<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<script type="text/javascript">
//短信重新发送
function sendAgain(id) {
	if(confirm("确定要补发短信吗？")){
	var action="${pageContext.request.contextPath}/admin/sMSSendRecord/sendAgain.action";
	var params={"id":id};
	var callback=function(data){
		alert(data);
		queryAllPerson('${pagehelper.pageNum}','');
	};
	$.post(action,params,callback,'json');
	}
}
</script>
<table class="table table-hover table-condensed">
	<thead>
		<tr class="text-center"
			style="background: #CCCCCC; color: #333333; font-size: 14px;">
			<td>用户名</td>
			<td>发送方式</td>
			<td>通道公司</td>
			<td>发送时间</td>
			<td>补发时间</td>
			<td>发送电话号码</td>
			<td>短信验证码</td>
			<td>发送的短信内容</td>
			<td>发送状态</td>
			<td>操作</td>
			<td>备注</td>
		</tr>
	</thead>
	<tbody id="queryall_list">
		<c:forEach items="${pagehelper.list}" var="user">
			<tr class="text-center">
				<td class="gj_keyword">${user.username ==null? '无':user.username}</td>
				<td>${user.sendtype == 1 ? '手工' : '系统'}</td>
				<td>${user.smsccompany }</td>
				<td>${gj:formatDate(user.sendtime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td>${gj:formatDate(user.reissuetime,'yyyy-MM-dd HH:mm:ss')}</td>
				<td id="gj_username" class="gj_keyword"><span>${user.mobile}</span></td>
				<td>${user.vercode}</td>
				<td class="tzui-tips"
					tip="${user.smscontent == null ? '暂无短信信息' : user.smscontent }">${gj:getSubStr(user.smscontent,'30')}</td>
				<td><c:if test="${user.status eq 1}">
						<span style="color: green">成功</span>
					</c:if> <c:if test="${user.status eq 0}">
						<span style="color: blue">失败</span>
					</c:if></td>
				<td><button class="btn btn-default" onclick="sendAgain('${user.id}')">补发</button></td>
				<td class="tzui-tips"
					tip="${user.remark == null ? '暂无备注信息' : user.remark }">${gj:getSubStr(user.remark,'20')}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id="page_div">
	<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
</div>


