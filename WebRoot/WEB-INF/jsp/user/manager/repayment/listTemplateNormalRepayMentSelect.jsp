<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<table class="table fc_6 mar_t20 bor_t">
	<thead>
		<tr class="table fc_6 mar_t5 bor_t">
			<th class="fc_3">编号</th>
			<th class="fc_3">选择</th>
			<th class="fc_3">用户名</th>
			<th class="fc_3">还款本金</th>
			<th class="fc_3">还款利息</th>
			<th class="fc_3">还款状态</th>
			<th class="fc_3">期数</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="repay" items="${pagehelper.list}" varStatus="status">
			<tr class="table fc_6 mar_t20 bor_t">
				<td class="fc_3">${status.index+1}</td>
				<td class="fc_3">
					<input type="checkbox" class="normalRepay" data-opid="${repay.id}" />
				</td>
				<td class="fc_3">${repay.username}</td>
				<td class="fc_3">${repay.ramount + repay.rvoucher}</td>
				<td class="fc_3">${repay.rinterest + repay.rvoucherint}</td>
				<td class="fc_3">
 					<c:choose>
						<c:when test="${repay.repaystatus == 1}">待还款</c:when>
						<c:when test="${repay.repaystatus == 2}">审核中</c:when>
						<c:when test="${repay.repaystatus == 3}">待处理</c:when>
						<c:when test="${repay.repaystatus == 4}">处理中</c:when>
						<c:when test="${repay.repaystatus == 5}">已还款</c:when>
						<c:when test="${repay.repaystatus == 6}">还款失败</c:when>
						<c:when test="${repay.repaystatus == 7}">审核失败</c:when>
   						<c:otherwise>未知</c:otherwise>
					</c:choose>
 				</td>
				<td class="fc_3">${repay.periods}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div style="margin-top:20px;">
 	<label><input type="checkbox" id="allNormalRepay"
		onclick="userloanApp.low_allNormalRepaySelect(this)">&nbsp;全选</label>

	<button type="button"
		onclick="userloanApp.low_doRepayMentNormalPart(this)"
		class="btn btn_bgf60 btn_size100" id=doRepayMentNormalPart data-token="<%=request.getSession().getAttribute("SessToken")%>">立即还款</button>
 	<span class="red" id="submit_error"></span>
</div>

<c:if test="${pagehelper.size > 0  }">
	 <div id="page_div" style="margin-top:20px;">
		<!-- 页数 -->
		<div class="message">
			共<i class="blue">${pagehelper.total}</i>条记录，当前显示第&nbsp;<i class="blue">${pagehelper.pageNum}/${pagehelper.pages}</i>&nbsp;页
		</div>
		<div style="text-align: center;" id="pageCon">
			<ul class="pagination">
				<!-- <li><a href="#">&laquo;</a></li> -->
				<li id="liOne">
					<a href="javascript:queryNormalAllPerson(${pagehelper.firstPage}, ${pagehelper.pageSize},${opid });">&lt;&lt;首页</a>
				</li>
				<c:if test="${!pagehelper.isFirstPage}">
					<li>
						<a href="javascript:queryNormalAllPerson(${pagehelper.prePage}, ${pagehelper.pageSize},${opid });">&lt;前一页</a>
					</li>
				</c:if>
				<c:forEach items="${pagehelper.navigatepageNums}"
					var="navigatepageNum">
		
					<c:if test="${navigatepageNum==pagehelper.pageNum}">
						<li class="active">
							<a href="javascript:queryNormalAllPerson(${navigatepageNum}, ${pagehelper.pageSize},${opid });">${navigatepageNum}</a>
						</li>
					</c:if>
					<c:if test="${navigatepageNum!=pagehelper.pageNum}">
						<li>
							<a href="javascript:queryNormalAllPerson(${navigatepageNum}, ${pagehelper.pageSize},${opid });">${navigatepageNum}</a>
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${!pagehelper.isLastPage}">
					<li> 
						<a href="javascript:queryNormalAllPerson(${pagehelper.nextPage}, ${pagehelper.pageSize},${opid });">下一页&gt;</a>
					</li>
		
				</c:if>
				<li>
					<a href="javascript:queryNormalAllPerson(${pagehelper.lastPage}, ${pagehelper.pageSize},${opid });">尾页&gt;&gt;</a>
				</li>
				<!-- <li><a href="#">&raquo;</a></li> -->
			</ul>
		</div>

	</div>
</c:if>