<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
body {
	font-family: "微软雅黑";
	font-size: 15pxp;
}

img {
	width: 50px;
	height: 50px;
}
</style>
<script type="text/javascript">
	
	/* 加载图片 */
	$(document).ready(function() {
		$("#qualificationspic").attr("src", "/upload/" + "${picName1}");
		$("#licencepic").attr("src", "/upload/" + "${picName2}");
		$("#orgcodepic").attr("src", "/upload/" + "${picName3}");
	})
</script>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>担保公司名称:</label>${detail.name}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>注册资金:</label>${detail.regfunds}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>注册时间:</label>${detail.regtimeStr}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>公司网站:</label>${detail.website}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>公司地址:</label>${detail.addr}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>联系电话:</label>${detail.phone}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>上传时间:</label>${detail.addtimeStr}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 20px;">
		<div class="col-md-4 col-md-offset-1">
			<label>担保公司资质图片:</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			<img alt="无缩略图" src="" id="qualificationspic">
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>营业执照图片:</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			<img alt="无缩略图" src="" id="licencepic">
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<label>组织机构代码图片:</label>
		</div>
		<div class="col-md-4 col-md-offset-1">
			<img alt="无缩略图" src="" id="orgcodepic">
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 10px;">
		<div class="col-md-4 col-md-offset-1">
			<label>担保公司说明:</label>${detail.description}
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 10px;">
		<div class="col-md-4 col-md-offset-1">
			<label>备注:</label>${detail.remark}
		</div>
	</div>
</body>
</html>