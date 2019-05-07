<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借款人资料详情页</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
</head>
<body>
	<div id="ht">
		<h2>
			<span class="glyphicon glyphicon-user"></span><em>资料明细</em>
		</h2>
	</div>
	<div class="col-md-12 column tab-pane fade in active" id="home">
		<table class="table table-bordered  table-hover">
			<tr>
				<th>用户编号：</th>
				<td>${gfundsInt.baseid}</td>
			</tr>
			<tr>
				<th>用户姓名：</th>
				<td>${gfundsInt.userbaseAccountInfo.realname }</td>
			</tr>
			<tr>
				<th>用户登录名：</th>
				<td>${gfundsInt.userbaseAccountInfo.loginname }</td>
			</tr>
			<tr>
				<th>教育程度：</th>
				<td>${(gfundsInt.education==1 )? '初中':''}
					${(gfundsInt.education==2 )? '职高':''} ${(gfundsInt.education==3 )? '高中':''}
					${(gfundsInt.education==4 )? '专科':''} ${(gfundsInt.education==5 )? '本科':''}
					${(gfundsInt.education==6 )? '硕士':''} ${(gfundsInt.education==7 )? '博士':''}
					${(gfundsInt.education==8 )? '其他':''}</td>
			</tr>
			<tr>
				<th>毕业院校：</th>
				<td>${gfundsInt.gradinst}</td>
			</tr>
			<tr>
				<th>婚姻状况：</th>
				<td>${gfundsInt.maritalstatus==0 ? '已婚':'未婚'}</td>
			</tr>
			<tr>
				<th>公司名称：</th>
				<td>${gfundsInt.company}</td>
			</tr>
			<tr>
				<th>月薪：</th>
				<td>${gfundsInt.salary}元</td>
			</tr>
			<tr>
				<th>收入说明：</th>
				<td>${gfundsInt.salaryinfo}</td>
			</tr>
			<tr>
				<th>车辆信息：</th>
				<td><c:choose>
						<c:when test="${gfundsInt.iscarow==0}">${gfundsInt.carinfo}</c:when>
						<c:otherwise>无</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th>房产信息：</th>
				<td><c:choose>
						<c:when test="${gfundsInt.ishouseow==0}">${gfundsInt.houseinfo}</c:when>
						<c:otherwise>无</c:otherwise>
					</c:choose></td>
			<tr>
			<tr>
				<th>其他资产说明：</th>
				<td>${gfundsInt.other}</td>
			</tr>
			<tr>
				<th>紧急联系人：</th>
				<td>${gfundsInt.contactsman}</td>
			</tr>
			<tr>
				<th>紧急联系人电话：</th>
				<td>${gfundsInt.contactsphone}</td>
			</tr>
			<tr>
				<th>紧急联系人关系：</th>
				<td>${(gfundsInt.contactsration==0 )? '父子':''}
					${(gfundsInt.contactsration==1 )? '母子':''}
					${(gfundsInt.contactsration==2 )? '兄妹':''}
					${(gfundsInt.contactsration==3 )? '兄弟':''}
					${(gfundsInt.contactsration==4 )? '亲朋':''}
					${(gfundsInt.contactsration==5 )? '其他':''}</td>
			</tr>
			<tr>
				<th>申请时间：</th>
				<td><fmt:formatDate value="${gfundsInt.addtime}" type="date"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>审核状态：</th>
				<td>${gfundsInt.auditstatus ==1  ? '审核成功':''}
					${gfundsInt.auditstatus ==0  ? '审核中':''} ${gfundsInt.auditstatus ==2  ? '审核失败':''}
				</td>
			</tr>
		</table>
	</div>
	<p>
		<a href="#" onClick="javascript :history.go(-1);">返回上一页</a>
	</p>
</body>
</html>