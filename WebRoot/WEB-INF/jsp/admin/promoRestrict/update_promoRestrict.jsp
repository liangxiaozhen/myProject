<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#update-levellimitnum-text").change(function(){
			$("#update-levellimitnum-lb").html("");
		});
	})
	function validateUpdate(){
		var levelLimitNum=$("#update-levellimitnum-text").val();
		if(levelLimitNum==''){
			$("#update-levellimitnum-lb").html("* 请输入限制人数");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>推广层级人数设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				推广层级：<label style="margin-left: 40px;"> <c:choose>
						<c:when test="${plr.promolevel eq 2 }">
									第二层
								</c:when>
						<c:when test="${plr.promolevel eq 3 }">
									第三层
								</c:when>
						<c:when test="${plr.promolevel eq 4 }">
									第四层
								</c:when>
						<c:when test="${plr.promolevel eq 5 }">
									第五层
								</c:when>
						<c:when test="${plr.promolevel eq 6 }">
									第六层
								</c:when>
						<c:when test="${plr.promolevel eq 7 }">
									第七层
								</c:when>
						<c:when test="${plr.promolevel eq 8 }">
									第八层
								</c:when>
						<c:when test="${plr.promolevel eq 9 }">
									第九层
								</c:when>
						<c:when test="${plr.promolevel eq 10 }">
									第十层
								</c:when>
						<c:when test="${plr.promolevel eq 11 }">
									第十一层
								</c:when>
						<c:when test="${plr.promolevel eq 12 }">
									第十二层
								</c:when>
						<c:when test="${plr.promolevel eq 13 }">
									第十三层
								</c:when>
						<c:when test="${plr.promolevel eq 14 }">
									第十四层
								</c:when>
						<c:when test="${plr.promolevel eq 15 }">
									第十五层
								</c:when>
						<c:when test="${plr.promolevel eq 16 }">
									第十六层
								</c:when>
						<c:when test="${plr.promolevel eq 17 }">
									第十七层
								</c:when>
						<c:when test="${plr.promolevel eq 18 }">
									第十八层
								</c:when>
						<c:when test="${plr.promolevel eq 19 }">
									第十九层
								</c:when>
						<c:when test="${plr.promolevel eq 20 }">
									第二十层
								</c:when>
					</c:choose></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				<input type="hidden" name="id" value="${plr.id }">
				人数限制：&nbsp;&nbsp;&nbsp; <input type="text"
					id="update-levellimitnum-text" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" name="levellimitnum"
					style="text-align: center; width: 90px;"
					value="${plr.levellimitnum }"> <span style="color: red;"><label
					id="update-levellimitnum-lb"></label></span>
			</div>
		</div>
	</form>
</body>
</html>