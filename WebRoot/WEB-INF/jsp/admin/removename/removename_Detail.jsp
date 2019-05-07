<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>名单信息</b></font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			用 户 名 &nbsp;&nbsp;： <label>${removename.loginname}</label>
		</div>
		<div class="col-md-5">
			真实姓名&nbsp;&nbsp;： <label>${removename.realname}</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			大 名 单 &nbsp;&nbsp;： <label>${removename.nametype}</label>
		</div>
		<div class="col-md-7 ">
			小 名 单 &nbsp;&nbsp;： <label>${removename.name}</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>设置信息</b></font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			设置人员&nbsp;&nbsp;： <label>${removename.addman}</label>
		</div>
		<div class="col-md-7 ">
			设置时间&nbsp;&nbsp;： <label><fmt:formatDate
					pattern="yyyy-MM-dd HH:mm:ss" value="${removename.addtime}" /></label>
		</div>
	</div>
</body>
</html>