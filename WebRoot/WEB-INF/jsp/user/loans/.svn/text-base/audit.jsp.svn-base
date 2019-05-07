<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.text.SimpleDateFormat,java.util.Date" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<script type="text/javascript">
$(function(){
	$(".ugrade").each(function(i) {
		var num = $(this).text();
		if (num.length > 5) {
			$(this).text(num.substr(0, 5) + "...");
		}
	})
});
</script>
<style type="text/css">
#ht {
	margin-left: 40%;
}
</style>
<title>审核信息展示</title>
</head>
<body>
	<div id="ht">
		<h2>
			<span class="glyphicon glyphicon-user"></span><em>我的借款审核信息</em>
		</h2>
	</div>
	<div class="col-md-12 column tab-pane fade in active" id="home">
		<table class="table table-bordered  table-hover">
			<tr>
				<th>姓名</th>
				<th>教育程度</th>
				<th>毕业院校</th>
				<th>婚姻状况</th>
				<th>公司名称</th>
				<th>月薪</th>
				<th>车辆信息</th>
				<th>房产信息</th>
				<th>其他资产说明</th>
				<th>紧急联系人</th>
				<th>紧急联系人电话</th>
				<th>紧急联系人关系</th>
				<th>申请时间</th>
				<th>审核状态</th>
				<th>上传图片</th>
				<th>操作</th>
			</tr>
			<c:forEach var="item" items="${listloan }">
				<tr>
					<th>${user.realname}</th>
					<th><c:choose>
							<c:when test="${item.education  == 1}">初中</c:when>
							<c:when test="${item.education  == 2}">职高</c:when>
							<c:when test="${item.education  == 3}">高中</c:when>
							<c:when test="${item.education  == 4}">专科</c:when>
							<c:when test="${item.education  == 5}">本科</c:when>
							<c:when test="${item.education  == 6}">硕士</c:when>
							<c:when test="${item.education  == 7}">博士</c:when>
							<c:otherwise>其他</c:otherwise>
						</c:choose></th>

					<th class="ugrade">${item.gradinst }</th>
					<th><c:choose>
							<c:when test="${item.maritalstatus  == 0}">已婚</c:when>
							<c:when test="${item.maritalstatus  == 1}">未婚</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose></th>
					<th class="ugrade">${item.company}</th>
					<th>${item.salary}元</th>
					<th><c:choose>
							<c:when test="${item.iscarow ==0}">${item.carinfo }</c:when>
							<c:when test="${item.iscarow ==1}">无</c:when>
						</c:choose></th>
					<th><c:choose>
							<c:when test="${item.ishouseow ==0}">${item.houseinfo }</c:when>
							<c:when test="${item.ishouseow ==1}">无</c:when>
						</c:choose></th>
					<th class="ugrade">${item.other}</th>
					<th>${item.contactsman}</th>
					<th>${item.contactsphone }</th>
					<th><c:choose>
							<c:when test="${item.contactsration ==0}">父子</c:when>
							<c:when test="${item.contactsration ==1}">母子</c:when>
							<c:when test="${item.contactsration ==2}">兄妹</c:when>
							<c:when test="${item.contactsration ==3}">兄弟</c:when>
							<c:when test="${item.contactsration ==4}">亲朋</c:when>
							<c:otherwise>其他</c:otherwise>
						</c:choose></th>
					<th><fmt:formatDate value="${item.addtime}" type="date"
							pattern="yyyy-MM-dd" /></th>
					<th><c:choose>
							<c:when test="${item.auditstatus ==2}">审核失败</c:when>
							<c:when test="${item.auditstatus ==1}">审核成功</c:when>
							<c:otherwise>审核中</c:otherwise>
						</c:choose></th>
					<th><a
						href="<%=basePath%>picpath/picturedetails.action?liano=${item.liano}">查看图片</a></th>
					<th><a class="btn btn-danger"
						href="<%=basePath%>loan/deleteByPrimaryKey.action?id=${item.id}&baseid=${user.id}&liano=${item.liano}">删除</a><a
						class="btn btn-info"
						href="<%=basePath%>loan/selectByPrimaryKey.action?id=${item.id}">详情</a><a
						class="btn btn-success" href="<%=basePath%>loan/tologin.action">新增</a></th>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>