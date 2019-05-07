<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<c:choose>
	<c:when test="${pagehelper.total <= 0}">没有数据...</c:when>
	<c:otherwise>
		<table class="table fc_6 mar_t5 bor_t">
			<thead>
				<tr>
					<th class="fc_3">借款标题</th>
					<th class="fc_3">类型</th>
					<th class="fc_3">借款金额</th>
					<th class="fc_3">已借入</th>
					<th class="fc_3">年利率</th>
					<th class="fc_3">借款期限</th>
					<th class="fc_3">发布时间</th>
					<th class="fc_3">状态</th>
					<th class="fc_3">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pagehelper.list}" var="user">
					<tr>
						<td class="fc_3">${user.loanname }</td>
						<td class="fc_3">${user.loantype ==1?"普通借款":"净值标" }</td>
						<td class="fc_3">${user.loanamount}</td>
						<td class="fc_3">${user.receiptsamount}</td>
						<td class="fc_3">${user.loanrate }</td>
						<td class="fc_3">${user.appday }&nbsp;${user.unit }</td>
						<td class="fc_3">${gj:formatDate(user.apptime,'yyyy-MM-dd HH:ss:mm' )}</td>
						<td class="fc_3"><c:choose>
								<c:when test="${user.appstatus == 0}">审核中</c:when>
								<c:when test="${user.appstatus == 1}">成功</c:when>
								<c:when test="${user.appstatus == 2}">失败</c:when>
								<c:when test="${user.appstatus == 3}">投标中</c:when>
								<c:when test="${user.appstatus == 4}">流标</c:when>
								<c:when test="${user.appstatus == 5}">还款中</c:when>
								<c:when test="${user.appstatus == 6}">已发布</c:when>
								<c:when test="${user.appstatus == 7}">已还款</c:when>
							</c:choose></td>
						<td>
							<div>
								<c:if test="${user.appstatus == 5}">
 									<button class="btn btn_bgf60 btn_size100"
										onclick="userloanApp.low_showAllTendItemDetail(this)" data-opid="${user.id}" id="showAllTendItemDetail">详情</button>
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
 
 
