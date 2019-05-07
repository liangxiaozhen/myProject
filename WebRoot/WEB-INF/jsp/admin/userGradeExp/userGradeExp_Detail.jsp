<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>体验会员等级信息</b></font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			设置人员&nbsp;&nbsp;： <label>${userGradeExp.addman}</label>
		</div>
		<div class="col-md-7 ">
			设置时间&nbsp;&nbsp;： <label><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${userGradeExp.addtime}" /></label>
		</div>
	</div>
			<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>备注信息</b></font>
		</div>
	</div>
		<hr>
	<div class="row"  >
		<div class="col-md-11 col-md-offset-1">
			备注&nbsp;&nbsp;： <label>${userGradeExp.remark }</label>
		</div>
	</div>
</body>
</html>