<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function validateInsert(){
		var levelLimitNum=$("#insert-levellimitnum-text").val();
		if(levelLimitNum==''){
			$("#insert-levellimitnum-lb").html("* 请输入限制人数");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>推广层级人数设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				人数限制：&nbsp;&nbsp;&nbsp; <input type="text"
					id="insert-levellimitnum-text" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" name="levellimitnum"
					style="text-align: center; width: 90px;"> <span
					style="color: red;"><label id="insert-levellimitnum-lb"></label></span>
			</div>
		</div>
	</form>
</body>
</html>