<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>

<html lang="zh-CN">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪软件</title>
<script type="text/javascript">
	$(function(){
		$("#proxyGrade-select").val("${userPromo.proxygrade}");
		var flag="${userPromo.userspecialflag}";
		$("input[type='radio']").each(function(){
			if($(this).val()==flag){
				$(this).attr("checked","checked");
			}
		});
	})
</script>
</head>
<body>
	<form id="update-form">
		<input type="hidden" name="id" value="${userPromo.id}">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				姓 名 ： <label>${userPromo.name }</label>
			</div>
			<div class="col-md-4 col-md-offset-1">
				用户名：<label>${userPromo.loginname }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5 col-md-offset-1">
				推广资质： <select id="proxyGrade-select" name="proxygrade">
					<c:forEach items="${p }" var="item">
						<option value="${item.id}">${item.proxygradename}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-5">
				推广标记：<input type="radio" value="0" name="userspecialflag">普通
				&nbsp;&nbsp;&nbsp;<input type="radio" value="1"
					name="userspecialflag">特殊
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">备注 ：</div>
			<div class="row" style="line-height: 0px; margin-top: 12px;">
				<div class="col-md-10 col-md-offset-1">
					<textarea class="form-control" name="adminremark" id="remark-text">${userPromo.adminremark }</textarea>
				</div>
			</div>
		</div>
	</form>
</body>
</html>