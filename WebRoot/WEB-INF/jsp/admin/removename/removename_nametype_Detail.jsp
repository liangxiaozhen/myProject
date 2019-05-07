<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
$(function() {
	$(".remark-p").each(function(i) {
		var num = $(this).html();
		if (num.length > 6) {
			$(this).html(num.substr(0, 5) + "...");
		}
	});
	
})
	
</script>
</head>
<body>
	<c:if test="${!empty rNames}">
		<div class="row" style="line-height: 0px;">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
					<font color='red'><b>${name}</b></font>&nbsp;&nbsp;&nbsp;&nbsp;${nameType}
				</div>
			</div>
			<hr>
			<c:if test="${!empty modelName }">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-11 col-md-offset-1" style="padding-left: 30px;">
						引用模块： <label>${modelName }</label>
					</div>
					<hr>
				</div>
			</c:if>
			<div
				style="line-height: 0px; margin-top: 20px; margin-left: 2px; margin-right: 2px;"
				class="row">
				<div class="col-md-12 ">
					<table class="table table-hover">
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>用户名</td>
							<td>真实姓名</td>
							<td>会员等级</td>
							<td>设置人员</td>
							<td>设置时间</td>
							<td>备注</td>
						</tr>
						<c:forEach items="${rNames}" var="item" varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.loginname }</td>
								<td>${item.realname }</td>
								<td>${item.ugrade.ugradename }</td>
								<td>${item.addman }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${item.addtime}" /></td>
								<td><p class="remark-p text-center" title="${item.remark }">${item.remark }</p></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${empty rNames}">
		<center>
			<b>暂无用户名单</b>
		</center>
	</c:if>
</body>
</html>