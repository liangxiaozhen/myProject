<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.aos{ 
		width:90px;		
		height:32px;
	}
</style>
</head>
<body style="font-family: 微软雅黑;">

	<div style="line-height: 0px;text-align: center;">
		<font size="3">定向名单的定向方式</font>
	</div>
	<hr>
	<div style="text-align: center;">
		<div  class="row" style="line-height: 0px;">
			<font class="control-label">请选择定向方式：</font>
			<select class="aos" style="border-radius:4px;" id="namemode" name="namemode">
				<option value="0">--请选择--</option>
				<option value="1">大小名单</option>
				<option value="2">会员等级</option>
			</select>
		</div>
		<hr>
		
	
		<div  class="row" style="line-height: 0px;">
			<font class="control-label">请选择定向类型：</font>
			<select class="aos" style="border-radius:4px;" id="businesstype" name="businesstype">
				<option value="0">--请选择--</option>
				<option value="1">系统业务</option>
				<option value="2">短信通知</option>
			</select>
		</div>
	</div>
	
</body>
</html>