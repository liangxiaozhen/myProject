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
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/js/userdebtattorn/css/cjuserdebtattorn.css"> --%>
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

#all {
	width: 80%;
	height: 500px;
	margin: auto;
	margin-top: 20px
}

#all_bigbar {
	width: 100%;
	height: 38px;
	border-bottom: 1px #CCCCCC solid;
}

#all_bigbar div {
	float: left;
	width: 150px;
	height: 38px;
	text-align: center;
	padding-top: 10px;
	font-size: 20px;
	font-weight: bold;
}

#sanbiao {
	cursor: pointer;
	border-bottom: 3px #FF9900 solid;
}

#zhaiquan {
	cursor: pointer;
}
</style>
<script type="text/javascript">
//点击债转列表
function sanbiao(id){
	$("#zhaiquan").css("border-bottom","1px #CCCCCC solid");
	$("#sanbiao").css("border-bottom","3px #FF9900 solid");
		var action =basePath+"/user/undertakedebtattorn/sanbiao.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
	}
//债券转让列表
function cjdebtattorn(id){
	$("#zhaiquan").css("border-bottom","3px #FF9900 solid");
	$("#sanbiao").css("border-bottom","1px #CCCCCC solid");
		var action =basePath+"/user/undertakedebtattorn/zhaizhuanfb.action";
		var params ={
				"id":id
		}
		$.post(action,params,function(data){
			$("#myTabContent").html("");
			$("#myTabContent").html(data);
		});
	}
//定向债转码的判定
function udapassFun(){
	var udapass = $("#udapass").val();
	var udapasslb = $("#udapasslb").val();
	if(udapass!=null){//定向债转码不为空的情况下,那么意思就是说必须要输入定向债转码才能购买
		if(udapasslb==null || udapasslb==""){
			$("#udapass-text").text("请出入定向债转码");
		}else{
			$("#udapass-text").text("");
			if(udapasslb.toLowerCase()!=udapass.toLowerCase()){
				$("#udapass-text").text("定向债转码输入错误,请重新输入");
			}else{
				$("#udapass-text").text("");
			}
		}
	}else{
		$("#udapass-text").text("");
	}
}	
	
//确认购买的时候验证	
function confirmBuy(){
	var amount = $("#amount").val();
	var daamount = $("#daamount").text();
	var udapass = $("#udapass").val();
	var udapasslb = $("#udapasslb").val();
	var isabuyallorpart = $("#isabuyallorpart").val();
	if(isabuyallorpart==2){
		if(amount==null || amount==""){
			$("#amount-text").text("请输入合法值");
		}else if(parseFloat(amount)>parseFloat(daamount)){
			$("#amount-text").text("购买金额过大,请重新输入");
		}/* else if(parseFloat(amount)>=parseFloat(daamount)){
			$("#amount-text").text("此债转标只允许部分购买,请重新输入金额");
		} */else{
			$("#amount-text").text("");
			if(udapass!=null){//定向债转码不为空的情况下,那么意思就是说必须要输入定向债转码才能购买
				if(udapasslb==null || udapasslb==""){
					$("#udapass-text").text("请出入定向债转码");
				}else{
					$("#udapass-text").text("");
					if(udapasslb.toLowerCase()!=udapass.toLowerCase()){
						$("#udapass-text").text("定向债转码输入错误,请重新输入");
					}else{
						$("#udapass-text").text("");
						affirmBuy();
					}
				}
			}else{
				affirmBuy();
			}
		}
	}
	
  if(isabuyallorpart==1){//只允许全额购买
		$("#amount-text").text("");
		if(udapass!=null){//定向债转码不为空的情况下,那么意思就是说必须要输入定向债转码才能购买
			if(udapasslb==null || udapasslb==""){
				$("#udapass-text").text("请出入定向债转码");
			}else{
				$("#udapass-text").text("");
				if(udapasslb.toLowerCase()!=udapass.toLowerCase()){
					$("#udapass-text").text("定向债转码输入错误,请重新输入");
				}else{
					$("#udapass-text").text("");
					affirmBuy();
				}
			}
		}else{
			affirmBuy();
		}
  }	
}
//确认购买
function affirmBuy(){
/* 	var isabuyallorpart = $("#isabuyallorpart").val();
	var amount = $("#amount").val();
	var daamount = $("#daamount").text();
	var userid = $("#userid").text();//购买人id
	var torderno = $("#torderno").text();//投标订单号
	var coefficient = $("#coefficient").text();
	var baseid = $("#baseid").text();//转让人id
	var intcoefficient = $("#intcoefficient").text();//利息债转系数
	var intamount = $("#intamount").text();//转出利息
	var total = $("#total").text(); */
	var daorderno = $("#daorderno").text();//债转标号
	alert(daorderno);
	/* var action = "${pageContext.request.contextPath}/user/userdebtattorn/creditassign.action";
	var params = {
			"amount":amount,
			"daamount":daamount,
			"userid":userid,
			"torderno":torderno,
			"coefficient":coefficient,
			"baseid":baseid,
			"daorderno":daorderno,
			"total":total
	}  */
 	window.open("${pageContext.request.contextPath}/user/userdebtattorn/creditassign.action?daorderno="+daorderno);
/* 	window.open("${pageContext.request.contextPath}/huifu/CreditAssign/doCreditAssign.action?daorderno="
			+daorderno+"&amount="+amount+"&userid="+userid);  */
	/*  $.post(action,params,function(data){
		 alert(data);
	});  */
}

</script>
</head>
<body>
	<div id="all">
		<div id="all_bigbar">
			<div onclick="sanbiao()" id="sanbiao">散标投资</div>
			<div onclick="cjdebtattorn()" id="zhaiquan">债权转让</div>
		</div>
		<div id="myTabContent" class="tab-content" style="margin-top: 20px;">
			<jsp:include page="/WEB-INF/jsp/user/cjdebtattorn/sanbiao.jsp"></jsp:include>
		</div>
	</div>

	<!-- 购买模态框 -->
	<div class="modal fade" id="myModalBuy" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">购买</h4>
				</div>
				<div class="modal-body" id="mymodalbuy"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="confirmBuy()" id="formee">确认购买</button>
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

</body>
</html>