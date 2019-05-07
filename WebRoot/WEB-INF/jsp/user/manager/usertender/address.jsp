<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">	
</head>
<body>
<form id="updateAwardAddress" class="form-horizontal" action="" method="post">
	<div class="container" style="margin-top: 20px;">
		
		<div class="form-group">
			<label class="col-md-2 control-label">管理员备注：</label>
			<div class="col-md-3">
				<span class="help-block">${ftcr.adminremark}</span>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-md-2 control-label">收货地址：</label>
			<div class="col-md-3">
				<textarea id="remark" class="form-control" name="remark" >${ftcr.remark}</textarea>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-md-3">
				<div class="checkbox">
					<label>
						<input id="chk" type="checkbox" value="456"/>我已按要求填写
					</label>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>