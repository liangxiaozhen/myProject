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
	src="${pageContext.request.contextPath}/js/guaranteefee/insertGuaranteeFee.js"></script>
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
	$("#chan_shu").hide();
	//截标后收取与投标时收取选择改变时的事件
	$("#chargetype").change(function() {
		if ($(this).val() == 1) {
			//隐藏投标时收取的div 并清空文本值和checkbox的已选中状态
			$("#chargetype2").hide();
			$("#chargetype2 :input").val("");
			$("#chargetype1").show();
			$("#chan_shu").hide();
		} else if($(this).val() == 2){
			//隐藏结标收取的div 并清空文本值
			$("#chargetype1").hide();
			$("#chan_shu").hide();
			$("#chargetype1 :input").val("");
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
				var url="${pageContext.request.contextPath}/guaranteeFee/insertGuaranteeFee.action";
				var sendData = $("form").serialize();
		        $.post(url,sendData,function(backData){
		        	var $backData=$.trim(backData);
		        	if($backData=="标设置完成"){
		        		alert($backData);
		        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
		        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
		        	}else{
		        		/* 直接新建标的相关设置成功后返回的页面*/
		        		window.location.href="${pageContext.request.contextPath }/guaranteeFee/selectGuaranteeFeeByCondition.action";
		        	}
		        });
		}
	});
});
function gotoGuaranteeFeeList(){
	   window.location.href="${pageContext.request.contextPath }/guaranteeFee/selectGuaranteeFeeByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>
	<form
		action="${pageContext.request.contextPath}/guaranteeFee/insertGuaranteeFee.action"
		method="post" id="insertGuaranteeFeeForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置担保参数</font></label>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<font size="3">担保公司:</font>&nbsp;&nbsp;
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div style="padding-left: 30px; padding-top: 6px;">
					<c:forEach items="${guaranteeList}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="guaranteeid" value="${item.id }">${item.name }
						</label>
						<c:if test="${status.count%8==0 }">
							<br />
							<br />
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-7 col-md-offset-1">
				<font size="3">担保服务费收款人</font>&nbsp;&nbsp;：<input type="text"
					name="gfrecman" style="width: 200px; height: 25px; font-size: 15px" />
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">收费类型</font>&nbsp;&nbsp;： <select name="chargetype"
					id="chargetype" style="width: 120px; height: 25px; font-size: 15px">
					<option value="1">截标后收取</option>
					<option value="2">投标时收取</option>
				</select>
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
				<script type="text/javascript">
										$("#type").change(function(){
											if($(this).val()=="iequota"){
												$("#chan_shu").show();
												$("#iepercent input").val("");
												$("#iepercent").hide();
												$("#iequota").show();
											}else if($(this).val()=="iepercent"){
												$("#chan_shu").show();
												$("#iequota input").val("");
												$("#iequota").hide();
												$("#iepercent").show();
											}else{
												$("#chan_shu").hide();
											}
										});
									</script>
				<div>
					<font size="3">结标金额段：</font>&nbsp;&nbsp;<input type="text"
						name="minnmmoney"
						style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
						type="text" name="maxnmmoney"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
			</div>
			<hr>
		</div>

		<div id="chan_shu">
			<div class="row">
				<div class="col-md-4 col-md-offset-1" id="iequota">
					<font size="3">定额</font>&nbsp;&nbsp;：<input type="text"
						name="gfquota" style="width: 100px; height: 25px; font-size: 15px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>

				<div id="iepercent">
					<div class="col-md-3 col-md-offset-1">
						<font size="3">百份比</font>&nbsp;&nbsp;：<input type="text"
							name="gfpercent"
							style="width: 60px; height: 25px; font-size: 15px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div class="col-md-8">
						<font size="3">担保费最低与最高：</font>&nbsp;&nbsp;<input type="text"
							name="mingffee"
							style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
							type="text" name="maxgffee"
							style="width: 100px; height: 25px; font-size: 15px" />元
					</div>
				</div>
			</div>
			<hr>
		</div>
		<div id="chargetype2">
			<div class="row">
				<div class="col-md-3 col-md-offset-1">
					<font size="3">担保费费率</font>&nbsp;&nbsp;：<input type="text"
						name="gfrate" style="width: 60px; height: 25px; font-size: 15px" />
				</div>
				<div class="col-md-7">
					<font size="3">担保费最低与最高：</font>&nbsp;&nbsp;<input type="text"
						name="mingfamount"
						style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
						type="text" name="maxgfamount"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
			</div>
			<hr>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否为模板</font>&nbsp;&nbsp;： <select name="istemplet"
					id="istemplet">
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
					type="button" onclick="gotoGuaranteeFeeList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
