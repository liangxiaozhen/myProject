<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function(){
		$("#batchupdate-proxygrade").change(function(){
			$("#proxygrade-lb").html("");
		});
	})
	function validateUpdateForm()
	{
		var proxygrade=$("#batchupdate-proxygrade").val();
		if(proxygrade==''){
			$("#proxygrade-lb").html("* 请选择推广资质");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="batchUpdate-form">
		<input type="hidden" name="ids" value="${ids }">
		<div align="center">
			总共选择了<span style="color: red;"><label>${count }</label></span>条记录
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">
				推广资质：<select name="proxygrade" id="batchupdate-proxygrade">
					<option value="">--请选择--</option>
					<c:forEach items="${proxygradeNames }" var="item">
						<option value="${item.id }">${item.proxygradename }</option>
					</c:forEach>
				</select>&nbsp;&nbsp;<span style="color: red"><label
					id="proxygrade-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">备注 ：</div>
			<div class="row" style="line-height: 0px; margin-top: 12px;">
				<div class="col-md-10 col-md-offset-1">
					<textarea class="form-control" name="adminremark" id="remark-text"></textarea>
				</div>
			</div>
		</div>
	</form>
</body>
</html>