<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>图文內容详情</b></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			发布人：<label>${imageTextSetting.operator}</label>
		</div>
		<div class="col-md-6">
			发布日期： <label><fmt:formatDate
					value="${imageTextSetting.issuetime }"
					pattern="yyyy-MM-dd HH:mm:ss" /></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			PC端：<label><c:if test="${imageTextSetting.pcterminal eq 0}">
					<span style="color: red;">不可用</span>
				</c:if> <c:if test="${imageTextSetting.pcterminal eq 1}">
					<span style="color: green;">可用</span>
				</c:if></label>
		</div>
		<div class="col-md-6">
			Android端： <label><c:if
					test="${imageTextSetting.androidterminal eq 0}">
					<span style="color: red;">不可用</span>
				</c:if> <c:if test="${imageTextSetting.androidterminal eq 1}">
					<span style="color: green;">可用</span>
				</c:if></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			IOS端：<label><c:if test="${imageTextSetting.iosterminal eq 0}">
					<span style="color: red;">不可用</span>
				</c:if> <c:if test="${imageTextSetting.iosterminal eq 1}">
					<span style="color: green;">可用</span>
				</c:if></label>
		</div>
		<div class="col-md-6">
			WAP端： <label><c:if test="${imageTextSetting.wapterminal eq 0}">
					<span style="color: red;">不可用</span>
				</c:if> <c:if test="${imageTextSetting.wapterminal eq 1}">
					<span style="color: green;">可用</span>
				</c:if></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			副标题2：<label>${imageTextSetting.subtitle2}</label>
		</div>
		<div class="col-md-6">
			副标题3： <label>${imageTextSetting.subtitle3 }</label>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-11 col-md-offset-1">
			超链接：<label>${imageTextSetting.hyperlink}</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">标题图：</div>
		<div class="col-md-5">
			<c:if test="${!empty imageTextSetting.titlepic}">
				<img id="img-detail">
				<script type="text/javascript">
					$("#img-detail")
							.attr("src", "${imageTextSetting.titlepic}");
				</script>
			</c:if>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-11 col-md-offset-1">
			内容简介： <label>${imageTextSetting.resume }</label>
		</div>
	</div>
</body>
</html>