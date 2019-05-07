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
</style>
<script type="text/javascript">
//判断债转系数
function checkBlur(obj) {
	//为了去除最后一个. 
	obj.value = obj.value.replace(/\.$/g, "");
	var isabuyallorpart = $("#isabuyallorpart").val();
	var amount = $(".amount").val();
	var daamount = $("#daamount").val();
	if(amount==null || amount==""){
		$("#amount-text").text("请输入合法值");
	}else if(parseFloat(amount)>parseFloat(daamount)){
		$("#amount-text").text("购买金额过大,请重新输入");
	}else if(parseFloat(amount)>parseFloat(avlbalance)){
		$("#amount-text").text("账户余额不足,请充值后再购买");
	}else if(isabuyallorpart==0){ //只允许部分购买
		if(parseFloat(amount)>=parseFloat(daamount)){
			$("#amount-text").text("此债转标只允许部分购买,请重新输入金额");
		}else{
			$("#amount-text").text("");
		}
	}else if(isabuyallorpart==1){//只允许部分购买
		if(parseFloat(amount)!=parseFloat(daamount)){
			$("#amount-text").text("此债转标只允许全额购买,请重新输入金额");
		}else{
			$("#amount-text").text("");
		}
	}else{
		$("#amount-text").text("");
	}
}
//当键盘上某个按键被按放开时触发的事件
function checkUp(event, obj) {
	//响应鼠标事件，允许左右方向键移动 
	event = window.event || event;
	if (event.keyCode == 37 | event.keyCode == 39) {
		return;
	}
	//先把非数字的都替换掉，除了数字和. 
	obj.value = obj.value.replace(/[^\d.]/g, "");
	//必须保证第一个为数字而不是. 
	obj.value = obj.value.replace(/^\./g, "");
	//保证只有出现一个.而没有多个. 
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	//保证.只出现一次，而不能出现两次以上 
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
			"$#$", ".");
}	

</script>
</head>

<body>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">投标订单号&nbsp;:</span> <span>${userDebtAttorn.torderno}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">债转标号&nbsp;:</span> <span>${userDebtAttorn.daorderno}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">转让金额&nbsp;:</span> <span>${df1.format(userDebtAttorn.daamount)}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">金额债转系数&nbsp;:</span> <span>${df1.format(userDebtAttorn.coefficient)}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">原始转让利息&nbsp;:</span> <span>${df1.format(userDebtAttorn.intamount)}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">实际转让利息&nbsp;:</span> <span>${df1.format(userDebtAttorn.factintamount)}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">利息债转系数&nbsp;:</span> <span>${df1.format(userDebtAttorn.intcoefficient)}</span>
		</div>
	</div>
	<c:if test="${userDebtAttorn.daproperty eq 2}">
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">转让滞纳金&nbsp;:</span> <span>${df1.format(userDebtAttorn.ocamount)}</span>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">实际转让滞纳金&nbsp;:</span> <span>${df1.format(userDebtAttorn.factocamount)}</span>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">滞纳金债转系数&nbsp;:</span> <span>${df1.format(userDebtAttorn.latefeecoefficient)}</span>
			</div>
		</div>
	</c:if>
</body>
</html>