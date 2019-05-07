<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">var basePath="${basePath}";</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/userdebtattorn/userdebtattornlist.js"></script>
<style type="text/css">
body {
	font-family: "微软雅黑";
}

th {
	text-align: center;
	background: #ccc;
}

li:hover {
	cursor: pointer;
}

table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
//点击债转列表
function userdebtrron(id){
		var action =basePath+"/user/userdebtattorn/userdebtrron.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
	}
//点击承接人列表	
function cjdebtrron(id){
		var action =basePath+"/user/undertakedebtattorn/cjdebtrron.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
	}
//点击进行总的投资	
function usertender(id){
	  var action =basePath+"/user/tender/tenderRecord2.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
  }
</script>
</head>
<body>
	<div class="container" style="width: 90%;">
		<h1>投资记录</h1>
		<ul id="myTab" class="nav nav-tabs">
			<li onclick="usertender(1)" id="1"><a data-toggle="tab">
					进行中的投资 </a></li>
			<li id="2"><a data-toggle="tab">已完成的投资</a></li>
			<li class="active" onclick="userdebtrron(3)" id="3"><a
				data-toggle="tab">债券转让列表</a></li>
			<li id="4"><a data-toggle="tab">债券承接列表</a></li>
		</ul>
		<div id="myTabContent" class="tab-content" style="margin-top: 20px;">
			<jsp:include page="/WEB-INF/jsp/user/userdebtattorn/userdebtrron.jsp"></jsp:include>
		</div>
	</div>

	<!-- 设置模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">债转设置</h4>
				</div>
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="ReleaseYanz()" id="frozen">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 详情模态框 -->
	<div class="modal fade" id="myModaldstails" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body" id="myModaldstail"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 审核模态框 -->
	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
		aria-labelledby="auditModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="auditModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行审核操作
					</h4>
				</div>
				<div id="audit-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="auditSuccess()">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>