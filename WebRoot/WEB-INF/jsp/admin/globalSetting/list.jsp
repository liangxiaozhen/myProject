<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全局设置主页面</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	//添加操作
	function add(obj) {
		$("#queryall_list")
				.load(basePath + "/admin/globalSetting/toadd.action");
	}

	//删除操作
	function gs_delete(obj) {
		var opid = $(obj).data("opid");
		var username = $(obj).data("username");
		$.tzAlert({
			"content" : "您確定刪除<span class='red'>" + username + "</span>数据嗎？",
			"title" : "删除提示？",
			callback : function(ok) {
				if (ok) {
					$.tzAjax.request({
						"model" : "admin/globalSetting",
						"method" : "/delete.action",
						callback : function(data) {
							var obj = $.parseJSON(data);
							if (obj.result == "success") {
								window.location.href = basePath
										+ "/admin/globalSetting/sove.action";
							} else if (obj.result == "fail") {
								loading("刪除失敗,請重新操作", 4);
							}
						}
					}, {
						"id" : opid
					});
				}
			}
		});
	}

	
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="btn-group">
					<%--<button class="btn btn-default" onclick="add(this)">添加全局设置</button>--%>
				</div>
				<div id="queryall_list" style="margin-top: 30px;">
					<jsp:include page="/WEB-INF/jsp/admin/globalSetting/updata.jsp"></jsp:include>
				</div>
				<div id="gj_ifrma"></div>
			</div>
		</div>
	</div>
</body>
</html>