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
//判断债转金额
function checkBlur(obj) {
	//为了去除最后一个. 
	obj.value = obj.value.replace(/\.$/g, "");
	var isabuyallorpart = $("#isabuyallorpart").val();
	var amount = $("#amount").val(); //购买本金
	var daamount = $("#daamount").text();//债转金额
	var daorderno = $("#daorderno").text();
	//var daproperty = $("#daproperty").val();
	if(parseFloat(amount)>parseFloat(daamount)){
		$("#amount-text").text("购买金额过大,请重新输入");
	}else if(parseFloat(amount)>parseFloat(avlbalance)){
		$("#amount-text").text("账户余额不足,请充值后再购买");
	}else if(isabuyallorpart==2){ //只允许部分购买 
		if(parseFloat(amount)>=parseFloat(daamount)){
			$("#amount-text").text("此债转标只允许部分购买,请重新输入金额");
		}else{
			$("#amount-text").text("");
			if(amount!=null || amount!=""){
				var action = basePath+"/user/undertakedebtattorn/buy2.action";
				var params = {
						"amount":amount,
						"daorderno":daorderno
				}
				$.post(action,params,function(data){
					var str = data.split(",");
					if(str[0]=="1"){ //正常债转
						$("#intamount").text(str[1]);
						$("#total").text(str[2]);
					}
					if(str[0]=="2"){//逾期债转
						$("#intamount").text(str[1]);//承接利息
						$("#total").text(str[2]);//承接总金额
						$("#ocamount").text(str[3]);//承接滞纳金
					}
				},'json');
			}else{
				$("#amount-text").text("请输入合法值");
			}
		}
	 }else if(isabuyallorpart==1){//只允许全额购买
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
	<input type="hidden" id="daproperty"
		value="${userDebtAttorn.daproperty}">
	<input type="hidden" id="isabuyallorpart"
		value="${debtattornbuyer.isabuyallorpart}">
	<div class="row" style="margin-top: 10px; display: none">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">购买人ID&nbsp;:</span> <span
				id="userid">${user.id}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">原始标号&nbsp;:</span> <span
				id="torderno">${userDebtAttorn.tenderitem.tno}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">原始投标订单号&nbsp;:</span> <span
				id="torderno">${userDebtAttorn.torderno}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; display: none">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">转让人ID&nbsp;:</span> <span
				id="baseid">${userDebtAttorn.baseid}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">债转订单号&nbsp;:</span> <span
				id="daorderno">${userDebtAttorn.daorderno}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">债转金额&nbsp;:</span> <span
				id="daamount">${userDebtAttorn.daamount}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">金额转让系数&nbsp;:</span> <span
				id="coefficient">${userDebtAttorn.coefficient}</span>
		</div>
	</div>
	<%-- <div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">是否全部购买&nbsp;:</span> 
				<span>${dAttorn.isabuyallorpart}</span>
			</div>
		</div> --%>

	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">利息债转系数&nbsp;:</span> <span
				id="intcoefficient">${userDebtAttorn.intcoefficient}</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">转出利息&nbsp;:</span>
			<c:if test="${debtattornbuyer.isabuyallorpart eq 1}">
				<!-- 全部购买的时候 -->
				<span id="intamount">${df1.format(userDebtAttorn.factintamount)}</span>
			</c:if>
			<c:if test="${debtattornbuyer.isabuyallorpart eq 2}">
				<!-- 部分购买 -->
				<span id="intamount"></span>
			</c:if>
		</div>
	</div>
	<c:if test="${userDebtAttorn.daproperty eq 2}">
		<!-- 逾期债转 -->
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">转出滞纳金&nbsp;:</span>
				<c:if test="${debtattornbuyer.isabuyallorpart eq 1}">
					<!-- 全部购买的时候 -->
					<span id="ocamount">${df1.format(userDebtAttorn.factocamount)}</span>
				</c:if>
				<c:if test="${debtattornbuyer.isabuyallorpart eq 2}">
					<!-- 部分购买 -->
					<span id="ocamount"></span>
				</c:if>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">滞纳金债转系数&nbsp;:</span> <span
					id="latefeecoefficient">${userDebtAttorn.latefeecoefficient}</span>
			</div>
		</div>
	</c:if>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">购买金额&nbsp;:</span>
			<%-- <c:out value="${dAttorn.isabuyallorpart}"></c:out> --%>
			<c:if test="${debtattornbuyer.isabuyallorpart eq 2}">
				<input id="amount" onblur="checkBlur(this)"
					onkeyup="checkUp(event,this)" />
			</c:if>
			<c:if test="${debtattornbuyer.isabuyallorpart eq 1}">
				<input id="amount" value="${df1.format(userDebtAttorn.daamount)}"
					disabled="disabled" />
			</c:if>
			<span id="amount-text" style="color: red"></span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">承接总价&nbsp;:</span>
			<c:if test="${debtattornbuyer.isabuyallorpart eq 1}">
				<!-- 全部购买的时候 -->
				<span id="total">${df1.format(total)}</span>
			</c:if>
			<c:if test="${debtattornbuyer.isabuyallorpart eq 2}">
				<!-- 部分购买的时候 -->
				<span id="total"></span>
			</c:if>
		</div>
	</div>
	<c:if test="${not empty userDebtAttorn.udapass}">
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">定向债转码&nbsp;:</span> <input
					type="text" id="udapasslb" onblur="udapassFun()" /> <input
					type="hidden" id="udapass" value="${userDebtAttorn.udapass}" />
				<!-- 隐藏的定向债转码 -->
				<span id="udapass-text" style="color: red"></span>
			</div>
		</div>
	</c:if>
	<div class="row" style="margin-top: 10px;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">账户余额&nbsp;:</span> <span
				id="avlbalance">${df1.format(useraccount.avlbalance)}</span>
		</div>
	</div>
</body>
</html>