<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div style="margin-left: 200px;">
		<input type="hidden" name="id" id="del-id" value="${userGradeExp.id }">
		等级名称：<span style="color: red;"><label>${userGradeExp.userGrade.ugradename}</label></span><br>
		购买金额：<span style="color: red;"><label>${userGradeExp.amount }</label></span>
		元<br>
		有 效 期 ：<span style="color: red;"><label>${userGradeExp.expirytime }</label></span>
		天<br> 会员人数：<span style="color: red;"><label>${userGradeExp.number }</label></span>
		人
	</div>
</body>
</html>