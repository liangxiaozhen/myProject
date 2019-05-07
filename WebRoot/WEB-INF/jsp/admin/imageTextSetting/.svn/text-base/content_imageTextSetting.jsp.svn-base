<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	function saveContent() {
		var action = "saveContent.action";
		var callback = function(data) {
			if (data == "success") {
				alert("提示：操作成功！");
				self.location = document.referrer;
			}
			if (data == "fail") {
				alert("提示：操作失败！");
			}
		};
		$.post(action, $("#content-form").serialize(), callback, 'json');
	}
</script>
</head>
<body>
	<div align="center">
		<h2>${imageTextSetting.title }</h2>
		<form method="post" id="content-form">
			<input type="hidden" name="id" value="${imageTextSetting.id }" />
			<textarea name="content" id="myEditor">
			${imageTextSetting.content}
			</textarea>
			<br />
			<div style="margin-bottom: 10px;">
				<button type="button" class="btn btn-primary"
					onclick="saveContent()">保存</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-default"
					onclick="javascript:history.go(-1);">取消</button>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		//UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
		//一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息 
		var ue = UE.getEditor('myEditor', {
			initialFrameWidth : 1300,
			initialFrameHeight : 350
		});
	</script>
</body>
</html>