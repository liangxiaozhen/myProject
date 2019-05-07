<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<c:if test="${!empty ubaiList}">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>定向名单</b></font>
			</div>
			<div
				style="line-height: 0px; margin-top: 20px; margin-left: 2px; margin-right: 2px;"
				class="row">
				<div class="col-md-12 ">
					<table class="table table-hover">
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>用户名</td>
							<td>真实姓名</td>
						</tr>
						<c:forEach items="${ubaiList}" var="item" varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.loginname }</td>
								<td>${item.realname }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${empty ubaiList}">
		<center>
			<b>暂无用户名单</b>
		</center>
	</c:if>
</body>
</html>