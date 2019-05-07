<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<script type="text/javascript"
	src="${basePath}/js/swfupload/tz_upload.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script
	src="${pageContext.request.contextPath}/js/guaranteeinfo/insertGuarantee.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
	font-size: 15px
}

input {
	width: 200px;
	height: 25px
}

img {
	width: 50px;
	height: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column" style="margin-top: 50px">
				<form method="post" id="insertguaranteeForm"
					enctype="multipart/form-data">
					<input type="hidden" name="qualificationspic"
						id="qualificationspic" /> <input type="hidden" name="licencepic"
						id="licencepic" /><input type="hidden" name="orgcodepic"
						id="orgcodepic" />
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">担保公司名称:</label>
							<div class="col-md-4">
								<input type="text" name="name" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">注册资金:</label>
							<div class="col-md-4">
								<input type="text" name="regfunds" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">注册时间:</label>
							<div class="col-md-4">
								<input type="text" class="Wdate" name="regtime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="form-control" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">公司网站:</label>
							<div class="col-md-4">
								<input type="url" name="website" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">公司地址:</label>
							<div class="col-md-4">
								<input type="text" name="addr" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">联系电话:</label>
							<div class="col-md-4">
								<input type="tel" name="phone" class="form-control" />
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">担保公司资质图片:</label>
							<div id="upload1"></div>
						</div>
						<div class="col-md-4">
							<img alt="无缩略图" src="" id="quali">
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">营业执照图片:</label>
							<div id="upload2"></div>
						</div>
						<div class="col-md-4">
							<img alt="无缩略图" src="" id="licen">
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">组织机构代码图片:</label>
							<div id="upload3"></div>
						</div>
						<div class="col-md-4">
							<img alt="无缩略图" src="" id="org">
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">担保公司说明:</label>
							<div class="col-md-4">
								<textarea name="description" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label class="col-md-4 text-right">备注:</label>
							<div class="col-md-4">
								<textarea name="remark" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<br /> <br />
					<div align="center" style="margin-top: 20px;">
						<button type="button" class="btn"
							onclick="insertguarantee.save(this)">保存</button>
						<button type="button" class="btn"
							onclick="insertguarantee.returnback(this)">返回</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>