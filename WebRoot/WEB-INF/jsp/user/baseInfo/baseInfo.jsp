<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<!-- 省市区/县 -->
<script type="text/javascript" src="<%=basePath%>js/pdata.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="<%=basePath%>calendar/WdatePicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		var url = "verifyLoginName.action";
		//判断用户昵称是否为空
		$("#loginname").blur(function() {
			var loginName = $.trim($("#loginname").val());
			if (loginName == null || loginName == '') {
				$("#loginnameerror").html("请填写用户昵称");
			} else {
				$.post(url, {
					loginname : loginName
				}, function(data) {
					if (data != null && data.indexOf("exist") >= 0) {
						$("#loginnameerror").text("用户昵称已经存在");
						$("#saveButton").attr("disabled", true);
					} else if (data != null && data.indexOf("equal") >= 0) {
						$("#loginnameerror").text("用户名不能与手机号相同");
						$("#saveButton").attr("disabled", true);
					} else if (data != null && data.indexOf("isChinese") >= 0) {
						$("#loginnameerror").text("用户名不能包含中文");
						$("#saveButton").attr("disabled", true);
					} else if (data != null && data.indexOf("length") >= 0) {
						$("#loginnameerror").text("用户字数超出限制");
						$("#saveButton").attr("disabled", true);
					} else {
						$("#loginnameerror").text("");
						$("#saveButton").attr("disabled", false);
					}
				});
			}
		});

	});
</script>
<style type="text/css">
label {
	width: 100px;
}
</style>
<title>${baseInfo}</title>
</head>
<body style="padding: 20px">
	<div class="container" id="finishBaseInfo">
		<div class="col-md-12 column">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">完善资料&nbsp;</label>
				</div>
				<div class="col-sm-3"></div>
				<div class="col-sm-3"></div>
			</form>
		</div>
		<div class="col-md-12 column">
			<c:if test="${!empty ubai.loginname }">
				<div class="form-group">
					<label class="control-label">用户昵称:</label> ${ubai.loginname }
				</div>
			</c:if>
			<c:if test="${empty ubai.loginname }">
				<form class="form-horizontal" role="form"
					action="finishBaseInfo.action" id="finishBaseInfoForm"
					method="post">
					<div class="form-group">
						<label for="loginname" class="col-sm-2 control-label">用户昵称:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="loginname"
								id="loginname" maxlength="15" placeholder="用户昵称4-15个字">
						</div>
						<div class="col-sm-4">
							<span id="loginnameerror" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button id="saveButton" type="submit" class="btn btn-default">保存</button>
						</div>
					</div>
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>