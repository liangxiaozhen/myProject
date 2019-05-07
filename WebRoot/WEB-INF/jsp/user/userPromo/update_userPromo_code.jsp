<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	function validateCode(){
		var code=$("#update-promocode").val();
		var regz = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;  
		if(regz.test(code)){
		return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<input type="hidden" value="${userPromo.id }" name="id" id="update-id">
		<input type="hidden" value="1" name="ismodify"> <input
			type="hidden" value="${userPromo.promocode }" name="oldPromoCode">
		<div class="row" style="line-height: 0px;" align="center">
			<div class="col-md-8 col-md-offset-1">
				邀请码：<input type="text" name="promocode" id="update-promocode"
					placeholder="请填写邀请码" value="${userPromo.promocode }"
					style="text-align: center; line-height: 18px;">
			</div>
		</div>
	</form>
</body>
</html>