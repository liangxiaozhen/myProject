<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table align="center" style="color:#1E88C7;">
	    <tr>
			<td>ID：</td>
			<td>${aheadRepayPlatform.id}</td>
		</tr>
		<tr>
			<td>标号ID：</td>
			<td>${aheadRepayPlatform.tid}</td>
		</tr>
		<tr>
			<td>提前还款奖品奖励编号：</td>
			<td>${aheadRepayPlatform.aheadrepaypno}</td>
		</tr>
		<tr>
			<td>奖励平台收款人：</td>
			<td>${aheadRepayPlatform.awardrecman}</td>
		</tr>
		<tr>
			<td>奖励平台投资人总欠收最小利息：</td>
			<td>${aheadRepayPlatform.minallnoreceiveint}</td>
		</tr>
		<tr>
			<td>奖励平台投资人总欠收最高利息：</td>
			<td>${aheadRepayPlatform.maxallnoreceiveint}</td>
		</tr>
		<tr>
			<td>奖励平台定额：</td>
			<td>${aheadRepayPlatform.awardplatquota}</td>
		</tr>
		<tr>
			<td>奖励平台百份比：</td>
			<td>${aheadRepayPlatform.awardplatrate}</td>
		</tr>
		<tr>
			<td>奖励平台最小值：</td>
			<td>${aheadRepayPlatform.awardplatminmoney}</td>
		</tr>
		<tr>
			<td>奖励平台最大值：</td>
			<td>${aheadRepayPlatform.awardplatmaxmoney}</td>
		</tr>
		<tr>
			<td>资金清算是否需要审核：</td>
			<td><c:if test="${aheadRepayPlatform.isaudit==1}">是</c:if> <c:if
					test="${aheadRepayPlatform.isaudit==0}">否</c:if></td>
		</tr>
		<tr>
			<td>是否为模板：</td>
			<td><c:if test="${aheadRepayPlatform.istemplet==1}">是</c:if>
				<c:if test="${aheadRepayPlatform.istemplet==0}">否</c:if></td>
		<tr>
			<td>添加人：</td>
			<td>${aheadRepayPlatform.addman}</td>
		</tr>
		<tr>
			<td>设置时间：</td>
			<td><fmt:formatDate value="${aheadRepayPlatform.addtime}" /></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td>${aheadRepayPlatform.remark}</td>
		</tr>
	</table>
</body>
</html>