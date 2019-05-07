<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	function validateUpdateForm() {
		if ($("#update-proxyGradeName-text").val() == "") {
			$("#update-proxyGradeName-lb").html("* 请输入推广资质");
			$("#update-proxyGradeName-text").focus();
			return false;
		}
		if ($("#update-proxyGrade-text").val() == 0) {
			$("#update-proxyGrade-lb").html("* 该级别为默认设置，无法编辑");
			$("#update-proxyGrade-text").focus();
			return false;
		}
		if ($("#update-proxyGrade-text").val() == "") {
			$("#update-proxyGrade-lb").html("* 请输入推广级别");
			$("#update-proxyGrade-text").focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="updateGrade-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>推广级别编辑</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				推广级别：
				<c:if test="${promoAgentGradeProfit.proxygrade eq 0 }">
					<label style="margin-left: 30px;">${promoAgentGradeProfit.proxygrade }</label>
					<input type="hidden" name="proxygrade" value=0>
				</c:if>
				<c:if test="${promoAgentGradeProfit.proxygrade != 0 }">
					<input type="text" name="proxygrade" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)" id="update-proxyGrade-text"
						style="width: 100px; text-align: center; line-height: 18px;"
						value="${promoAgentGradeProfit.proxygrade }" />
				</c:if>
				<span style="color: red;"><label id="update-proxyGrade-lb"></label></span>
				<input type="hidden" name="id" value="${promoAgentGradeProfit.id }">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				推广资质： <input type="text" name="proxygradename"
					id="update-proxyGradeName-text"
					style="width: 100px; text-align: center; line-height: 18px;"
					value="${promoAgentGradeProfit.proxygradename }" /> <span
					style="color: red;"><label id="update-proxyGradeName-lb"></label></span>
			</div>
		</div>
		<c:if test="${fn:length(linkList)>0 }">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
					<font color="red"><b>第三方推广编辑</b></font>
				</div>
				<hr>
				<div class="col-md-11 col-md-offset-1">
					<c:forEach items="${linkList }" var="item">
						<input type="checkbox" name="isopen" value="${item.id }"
							${item.isopen==1?'checked':'' } />${item.name }
				</c:forEach>
				</div>
			</div>
		</c:if>
	</form>
</body>
</html>