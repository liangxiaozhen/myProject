<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

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
<style type="text/css">
.title {
	float: left;
	font-size: 20px;
	padding: 5px;
}

.content {
	width: 100%;
	height: auto;
	border: none black solid;
	float: left;
}

.content ul li {
	padding-top: 20px;
	padding-left: 100px;
	font-size: 15px;
	padding-bottom: 5px;
}

.bottom {
	float: left;
	margin-top: 10px;
	border: 1px #ffd18d solid;
	width: 100%;
	height: auto;
	padding-bottom: 10px;
	background: #fff0da none repeat scroll 0 0;
}

.tips {
	padding-left: 10px;
	padding: #fff0da none repeat scroll 0 0;
	color: #ff5736;
	height: 18px;
}

.bottom ul li {
	color: #666;
	font-size: 12px;
	line-height: 28px;
	padding-left: 20px;
}

.bottom p {
	color: #666;
	font-size: 12px;
	padding-left: 30px;
}

.lf_invest {
	display: inline-block;
	text-align: right;
	height: 20px;
	line-height: 20px;
	width: 20%;
}

.te_black {
	padding: 0 5px;
}

.grey_font {
	color: #a6a6a6;
}

.open_btn {
	color: #fff;
	background-color: #ff5641;
	cursor: pointer;
	font-size: 14px;
	margin-right: 400px;
	float: right;
	margin-bottom: 20px;
}

#plan {
	width: 370px;
	height: 300px;
	border: 1px #CCB38C solid;
	border-radius: 10px 10px 10px 10px;
	margin: auto;
	margin-top: 38px;
	font-size: 17px;
}

#status {
	width: 370px;
	height: 50px;
	border-radius: 10px 10px 0px 0px;
	float: left;
	font-size: 17px;
}

#status label {
	font-size: 17px;
	margin-right: 30px;
	margin-top: 20px;
	background-position: 0 -75px;
	cursor: pointer;
	float: right;
	height: 26px;
	margin-right: 5px;
	width: 76px;
}

#plantype {
	width: 370px;
	height: 210px;
	background: #EFEFEF;
	float: left;
	font-size: 30px;
	text-align: center;
	vertical-align: middle;
}

#plantype label {
	font-size: 30px;
	text-align: center;
	line-height: 100px;
}

#edit {
	width: 370px;
	height: 40px;
	border-radius: 0px 0px 10px 10px;
	font-size: 15px;
	text-align: center;
	padding-top: 10px;
	float: left;
}

#edit button {
	font-size: 17px;
	margin-right: 30px;
	margin-left: 30px;
	cursor: pointer;
	border: none;
	background: none;
}
</style>
<script type="text/javascript">
// 	function check(){
// 		var tenderplantype = $("input[type='radio']:checked").val();
// 		if(tenderplantype==undefined){
// 			return false;
// 		}else if(tenderplantype=="P"){
// 			var limitamt = $("#limitamt").val().replaceAll(',','');
// 			var amt = $("#amt").val();
// 			var avlbalance = ${avlbalance};
// 			if(limitamt==""){
// 				$(".money").html("请输入单笔最大金额");
// 				return false;
// 			}else if(parseFloat(limitamt) > parseFloat(avlbalance)){
// 				$(".money").html("单笔最大金额大于可用余额");
// 				return false;
// 			}
// 			$("#transamt").val(limitamt);
// 		}
// 	}
// 	function editPlan(){
// 		$("#editPlan").removeAttr("hidden");
// 		$("#plan").attr("hidden","hidden");
// 	}
// 	function closePlan(){
// 		window.open("${pageContext.request.contextPath}/huifu/autoTenderPlanClose/hfAutoTenderPlanClose.action");
// 	}
</script>
</head>
<body style="background: #F6F7F8">
	<div class="container">
<!-- 		<form id="autoTenderPlanForm" -->
<%-- 			action="${pageContext.request.contextPath}/user/autoTender/openAutoTender.action" --%>
<!-- 			target="_blank" onsubmit="return check()" method="post"> -->
<!-- 			<div class="title"> -->
<!-- 				<label>自动投标</label> -->
<!-- 			</div> -->
<!-- 			<div class="content"> -->
<%-- 				<c:if test="${tenderPlan.status eq 1}"> --%>
<!-- 					<div id="plan"> -->
<!-- 						<div id="status"> -->
<!-- 							<label>已启用</label> -->
<!-- 						</div> -->
<!-- 						<div id="plantype"> -->
<%-- 							<c:if test="${tenderPlan.tenderplantype eq 'P'}"> --%>
<!-- 								<label>部分授权</label> -->
<!-- 								<br /> -->
<%-- 								<label><c:if test="${!empty tenderPlan.transamt}">授权金额${df.format(tenderPlan.transamt)}</c:if></label> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${tenderPlan.tenderplantype eq 'W'}"> --%>
<!-- 								<label>完全授权</label> -->
<!-- 								<br /> -->
<!-- 								<label>余额可全部用于投资</label> -->
<%-- 							</c:if> --%>
<!-- 						</div> -->
<!-- 						<div id="edit"> -->
<!-- 							<button type="button" onclick="editPlan(this)">修改</button> -->
<!-- 							| -->
<!-- 							<button type="button" onclick="closePlan(this)">关闭</button> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<ul hidden="hidden" id="editPlan"> -->
<%-- 						<li><span class="lf_invest">账户可用余额：</span><span><c:if --%>
<%-- 									test="${!empty avlbalance}">${df.format(avlbalance)}</c:if>&nbsp;&nbsp;</span><span --%>
<!-- 							class="te_black">元</span></li> -->
<!-- 						<li><span class="lf_invest">每次投标金额：</span><input type="radio" -->
<!-- 							value="W" name="tenderplantype" -->
<%-- 							<c:if test="${tenderPlan.tenderplantype eq 'W'}">checked="checked"</c:if> />全部可投资余额</li> --%>
<!-- 						<li><span class="lf_invest"></span><input type="radio" -->
<!-- 							value="P" name="tenderplantype" class="te_black" -->
<%-- 							<c:if test="${tenderPlan.tenderplantype eq 'P'}">checked="checked"</c:if> />单笔最大金额<span></span> --%>
<!-- 							<input type="text" id="limitamt" -->
<%-- 							<c:if test="${!empty tenderPlan.transamt}">value="${df.format(tenderPlan.transamt)}"</c:if> /> --%>
<!-- 							<input name="transamt" id="transamt" hidden="hidden"><span -->
<!-- 							class="te_black">元</span>&nbsp;&nbsp;<span class="te_black money" -->
<!-- 							style="color: red;"></span></li> -->
<!-- 																	<li><span class="lf_invest">收益率范围：</span><input type="text" name=""  /><span class="te_black">%</span>-<b class="info" style="display: none;"></b><input type="text" name="" /><span class="te_black">%</span><span class="grey_font">（年化收益利率范围）</span></li> -->
<!-- 																	<li><span class="lf_invest">借款期限：</span><input type="text" name=""  /><span class="te_black">天</span>-<input type="text" name="" /><span class="te_black">天</span></li> -->
<!-- 						<li><input type="submit" class="open_btn btn" value="修改投标计划" /></li> -->
<!-- 					</ul> -->
<%-- 				</c:if> --%>
<%-- 				<c:if test="${empty tenderPlan || tenderPlan.status ne 1}"> --%>
<!-- 					<ul> -->
<%-- 						<li><span class="lf_invest">账户可用余额：</span><span><c:if --%>
<%-- 									test="${!empty avlbalance}">${df.format(avlbalance)}</c:if>&nbsp;&nbsp;</span><span --%>
<!-- 							class="te_black">元</span></li> -->
<!-- 						<li><span class="lf_invest">每次投标金额：</span><input type="radio" -->
<!-- 							value="W" name="tenderplantype" />全部可投资余额</li> -->
<!-- 						<li><span class="lf_invest"></span><input type="radio" -->
<!-- 							value="P" name="tenderplantype" class="te_black" -->
<!-- 							checked="checked" />单笔最大金额<span></span> <input type="text" -->
<!-- 							name="transamt" id="amt" /><span class="te_black">元</span>&nbsp;&nbsp;<span -->
<!-- 							class="te_black money" style="color: red;"></span></li> -->
<!-- 											<li><span class="lf_invest">收益率范围：</span><input type="text" name=""  /><span class="te_black">%</span>-<b class="info" style="display: none;"></b><input type="text" name="" /><span class="te_black">%</span><span class="grey_font">（年化收益利率范围）</span></li> -->
<!-- 											<li><span class="lf_invest">借款期限：</span><input type="text" name=""  /><span class="te_black">天</span>-<input type="text" name="" /><span class="te_black">天</span></li> -->
<!-- 						<li><input type="submit" class="open_btn btn" value="开启投标" /></li> -->
<!-- 					</ul> -->
<%-- 				</c:if> --%>
<!-- 			</div> -->
<!-- 			<div class="bottom"> -->
<!-- 				<div class="tips">温馨提示:</div> -->
<!-- 				<ul> -->
<!-- 					<li>1.账户可用金额≥10000元，开启自动投标。可用金额低于10000元，“自动投标”功能失效。（借款用户的借款资金不可用于投标）；</li> -->
<!-- 					<li>2.单笔“自动投标”金额不得超过单项目总融资金额的20%，若超过则按照20%比例进行投标；</li> -->
<!-- 					<li>3.单个项目成功的“自动投标”总额不得超过项目融资金额的60%，若超过则按最大可投资额进行投标；</li> -->
<!-- 					<li>4.借款用户在获得借款时会自动关闭自动投标，以避免借款被用作自动投标资金；</li> -->
<!-- 					<li>5.满足自动投标规则的金额小于设定的单笔最大投资金额，也会进行自动投标；</li> -->
<!-- 					<li>6.项目进入“立即投资”状态五分钟后，开始“自动投标”排队。“自动投标”顺序规则如下：</li> -->
<!-- 				</ul> -->
<!-- 				<p>a）投标序列按照开启自动投标的时间先后进行排序；</p> -->
<!-- 				<p>b）每个用户自动投标不限制次数，单次“自动投标”后，重新排队；</p> -->
<!-- 				<p>c）轮到用户投标时没有符合条件的项目，也视为投标一次，需重新排队。</p> -->
<!-- 			</div> -->
<!-- 		</form> -->
		<div style="font-size: 26px;margin-top: 30px;">
			<label>自动投标授权</label>
		</div>
		<hr/>
		<div style="width: 370px;height: 200px;margin: auto;">
			<div style="font-size: 16px;text-align: center;">授权状态：<label>${status eq 1 ? "开启" : "关闭"}</label></div>
		</div>
		<div style="text-align: center;">
			<a class="btn btn-default" href="${pageContext.request.contextPath}/user/autoTender/authorize.action" target="_blank">${status eq 1 ? "关闭授权" : "开启授权"}</a>
		</div>
	</div>
</body>
</html>
