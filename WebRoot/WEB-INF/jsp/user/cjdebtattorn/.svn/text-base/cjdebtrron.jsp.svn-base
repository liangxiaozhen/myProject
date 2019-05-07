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
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<script type="text/javascript">var basePath="${basePath}";</script>
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

#operationts0 {
	padding-top: 8px;
	height: 30px;
}

#operationts51 {
	margin-left: 10px;
	width: 80px;
	border: 1px #FFFFFF solid;
	text-align: center;
	border-radius: 5px 5px 5px 5px;
	background: #FF9900;
	cursor: pointer;
	padding-top: 5px;
	height: 30px;
}

#operationts1 {
	margin-left: 10px;
	width: 80px;
	border: 1px #FFFFFF solid;
	text-align: center;
	border-radius: 5px 5px 5px 5px;
	cursor: pointer;
	padding-top: 5px;
	height: 30px;
}

#operationts2 {
	margin-left: 10px;
	width: 80px;
	border: 1px #FFFFFF solid;
	text-align: center;
	border-radius: 5px 5px 5px 5px;
	cursor: pointer;
	padding-top: 5px;
	height: 30px;
}

#operationts3 {
	margin-left: 10px;
	width: 80px;
	border: 1px #FFFFFF solid;
	text-align: center;
	border-radius: 5px 5px 5px 5px;
	cursor: pointer;
	padding-top: 5px;
	height: 30px;
}

#operationts4 {
	margin-left: 10px;
	width: 80px;
	border: 1px #FFFFFF solid;
	text-align: center;
	border-radius: 5px 5px 5px 5px;
	cursor: pointer;
	padding-top: 5px;
	height: 30px;
}

#operationts div {
	float: left;
	margin-bottom: 10px
}

#transferable {
	margin-top: 20px
}
</style>
<script type="text/javascript">

//点击剩余四个小标签的时候跳转到UserDebtAttornController类中去操作
function operation(id){
	var action =basePath+"/user/undertakedebtattorn/queryAllRecord.action";
	var params ={
			"id":id
	}
	$.post(action,params,function(data){
		$("#transferable").html("");
		$("#transferable").html(data);
	});
}
//下面是点击小标签的时候更换背景颜色
$("#operationts51").click(function(){
	  $("#operationts51").css("background","#FF9900");
	  $("#operationts1").css("background","#FFFFFF");
	  $("#operationts2").css("background","#FFFFFF");
	  $("#operationts3").css("background","#FFFFFF");
});
$("#operationts1").click(function(){
	  $("#operationts1").css("background","#FF9900");
	  $("#operationts51").css("background","#FFFFFF");
	  $("#operationts2").css("background","#FFFFFF");
	  $("#operationts3").css("background","#FFFFFF");
});
$("#operationts2").click(function(){
	  $("#operationts2").css("background","#FF9900");
	  $("#operationts1").css("background","#FFFFFF");
	  $("#operationts51").css("background","#FFFFFF");
	  $("#operationts3").css("background","#FFFFFF");
});
$("#operationts3").click(function(){
	  $("#operationts3").css("background","#FF9900");
	  $("#operationts1").css("background","#FFFFFF");
	  $("#operationts2").css("background","#FFFFFF");
	  $("#operationts51").css("background","#FFFFFF");
});

</script>
</head>
<body>
	<div id="operationts">
		<div id="operationts0">筛选：</div>
		<div onclick="operationt(51)" id="operationts51">全部</div>
		<div onclick="operation(1)" id="operationts1">正常回款</div>
		<div onclick="operation(2)" id="operationts2">逾期</div>
		<div onclick="operation(3)" id="operationts3">已完成</div>
		<!-- <span>
					持有时间:<select> 
								<option>30天</option>
								<option>60天</option>
								<option>90天</option>
							</select>
							<button>查询</button>
					 </span>  -->
	</div>
	<div id="transferable">
		<jsp:include
			page="/WEB-INF/jsp/user/cjdebtattorn/cjdebtrron_status51.jsp"></jsp:include>
	</div>
</body>
</html>