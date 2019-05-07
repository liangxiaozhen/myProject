<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$("#rankno-select").change(function() {
		var rankNo = $(this).val();
		if (rankNo == "" || rankNo == null) {
			$("#needbonusPoints").html("0");
		} else {
			var action = "getNeedBonusPoints.action";
			var params = {
				"rankno" : rankNo
			};
			var callback = function(data) {
				if(parseInt("${bonusPoints}")<parseInt(data)){
					$("#btn-bonusPoints").attr("disabled",true);
				}else{
					$("#btn-bonusPoints").attr("disabled",false);
				}
				$("#needbonusPoints").html(data);
			};
			$.post(action, params, callback, 'json');
		}
	});
</script>
</head>
<body>
	<form>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>积分兑换会员</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				当前积分：<label>${bonusPoints}</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				会员等级：<select id="rankno-select">
					<option value="">--请选择--</option>
					<c:forEach items="${list }" var="item">
						<option value="${item.rankno }">${item.ugradename }-------${item.needpoints}积分</option>
					</c:forEach>
				</select> <label id="ugrade-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-12 col-md-offset-1">
				所需积分：&nbsp;&nbsp;<label id="needbonusPoints" style="color: red;">0</label>
			</div>
		</div>
	</form>
</body>
</html>