<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>tenderItem_detail</title>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>

<script
	src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script
	src="${pageContext.request.contextPath}/js/mediacyfee/insertMediacyFee.js"></script>
<style type="text/css">
#id {
	margin: 40px;
}

hr {
	margin: 5px;
}
</style>
<script type="text/javascript">
$(function(){
	
	$("#chargetype2").hide();
	$("#iequota").hide();
	$("#iepercent").hide();
	$("#setting1").hide();
	//结标时收取与投标时收取选择改变时的事件
	$("#chargetype").change(function() {
		if ($(this).val() == 1) {
			//隐藏投标时收取的div 并清空文本值和checkbox的已选中状态
			$("#chargetype2").hide();
			$("#chargetype2 input").val("");
			$("#chargetype1").show();
			$("#setting1").hide();
		} else if($(this).val() == 2){
			//隐藏结标收取的div 并清空文本值
			$("#chargetype1").hide();
			$("#setting1").hide();
			$("#chargetype1 input").val("");
			$("#chargetype1 select").val("");
			$("#chargetype2").show();
		}
	});
	$("#submitBtu").click(function(){
		/* 通过标的设置来到这里的就有nextPage */
		if($("#nextPage").val()=="nextPage"){
			if(check().form()){
				$("form:first").submit();
			}
		}else{
				if(!check().form()){
					return;
				}
				var url="${pageContext.request.contextPath}/mediacyFee/insertMediacyFee.action";
				var sendData = $("form").serialize();
		        $.post(url,sendData,function(backData){
		        	var $backData=$.trim(backData);
		        	if($backData=="标设置完成"){
		        		alert($backData);
		        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
		        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
		        	}else{
		        		/* 直接新建标的相关设置成功后返回的页面*/
		        		window.location.href="${pageContext.request.contextPath }/mediacyFee/selectMediacyFeeByCondition.action";
		        	}
		        });
		}
	});
	/* 类型(定额或百分比)的change事件 */
	$("#type").change(function(){
		if($(this).val()=="iequota"){
			$("#setting1").show();
			$("#iepercent input").val("");
			$("#iepercent").hide();
			$("#iequota").show();
		}else if($(this).val()=="iepercent"){
			$("#setting1").show();
			$("#iequota input").val("");
			$("#iequota").hide();
			$("#iepercent").show();
		}else{
			$("#setting1 input").val("");
			$("#setting1").hide();
		}
	});
});
function gotoMediacyFeeList(){
	   window.location.href="${pageContext.request.contextPath }/mediacyFee/selectMediacyFeeByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form
		action="${pageContext.request.contextPath}/mediacyFee/insertMediacyFee.action"
		method="post" id="insertMediacyFeeForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置居间服务费参数</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">居间服务费收款人</font>&nbsp;&nbsp;：<input type="text"
					name="mrecman" style="width: 200px; height: 25px; font-size: 15px" />
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">收费类型</font>&nbsp;&nbsp;： <select name="chargetype"
					id="chargetype" style="width: 120px; height: 25px; font-size: 15px">
					<option value="1">结标时收取</option>
					<option value="2">投标时收取</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-6 col-md-offset-1">
				<font size="3">结标金额段：</font>&nbsp;&nbsp;<input type="text"
					name="minnmmoney"
					style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
					type="text" name="maxnmmoney"
					style="width: 100px; height: 25px; font-size: 15px" />元
			</div>
		</div>
		<hr>
		<div id="chargetype1">
			<div class="row">
				<div class="col-md-3 col-md-offset-1" style="width: 260px;">
					<font size="3">类型</font> <select id="type" name="type"
						style="width: 80px; height: 25px; font-size: 15px">
						<option value="">请选择</option>
						<option value="iequota">定额</option>
						<option value="iepercent">百份比</option>
					</select>
				</div>
				<div id="setting1">
					<div id="iequota" class="col-md-2">
						<font size="3">定额</font>&nbsp;&nbsp;：<input type="text"
							name="mfquota"
							style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div id="iepercent">
						<div class="col-md-2">
							<font size="3">百份比</font>&nbsp;&nbsp;：<input type="text"
								name="mfpercent"
								style="width: 60px; height: 25px; font-size: 15px;" />%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div class="col-md-6">
							<font size="3">居间费最低与最高：</font>&nbsp;&nbsp;<input type="text"
								name="minmffee"
								style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
								type="text" name="maxmffee"
								style="width: 100px; height: 25px; font-size: 15px" />元
						</div>
					</div>
				</div>
			</div>
			<hr>
		</div>


		<div id="chargetype2">
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">居间费费率</font>&nbsp;&nbsp;：<input type="text"
						name="mfrate" style="width: 60px; height: 25px; font-size: 15px" />%
				</div>
				<div class="col-md-6">
					<font size="3">居间费最低与最高：</font>&nbsp;&nbsp;<input type="text"
						name="minmfamount"
						style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
						type="text" name="maxmfamount"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
			</div>
			<hr>
		</div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否为模板</font>&nbsp;&nbsp;： <select name="istemplet"
					id="istemplet" style="width: 80px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<input class="btn btn-primary" type="button" id="submitBtu"
					style="margin-left: 200px" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="gotoMediacyFeeList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
