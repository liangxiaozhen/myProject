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
<script src="${pageContext.request.contextPath}/js/plus/inserPlus.js"></script>
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
	$("#aint").hide();
	$("#avoucher").hide();
	$("#alikevoucher").hide();
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
				var url="${pageContext.request.contextPath}/plus/insertPlus.action";
				var sendData = $("form").serialize();
		        $.post(url,sendData,function(backData){
		        	var $backData=$.trim(backData);
		        	if($backData=="标设置完成"){
		        		alert($backData);
		        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
		        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
		        	}else{
		        		/* 直接新建标的相关设置成功后返回的页面*/
		        		window.location.href="${pageContext.request.contextPath }/plus/selectPlusByCondition.action";
		        	}
		        });
		}
	});
});
function gotoPlusList(){
	   window.location.href="${pageContext.request.contextPath }/plus/selectPlusByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form
		action="${pageContext.request.contextPath}/plus/insertPlus.action"
		method="post" id="insertPlusForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置增益参数</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">基本信息</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-6 col-md-offset-1">
				<font size="3">单次允许的增益方式</font>&nbsp;&nbsp;： <input
					name="aonceplusmode"
					style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;种
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-6 col-md-offset-1">
				<font size="3">总计允许的增益方式</font>&nbsp;&nbsp;： <input
					name="atotalplusmode"
					style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;种
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">增益清算付款人</font>&nbsp;&nbsp;：<input type="text"
					name="payforplusman"
					style="width: 100px; height: 25px; font-size: 15px" />
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">清算方式</font>&nbsp;&nbsp;： <select name="clearmode"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">结标清算</option>
					<option value="2">首期清算</option>
					<option value="3">每期清算</option>
					<option value="4">尾期清算</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否为模板</font>&nbsp;&nbsp;： <select name="istemplet"
					id="istemplet" style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<hr>



		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">加息卷设置</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否允许加息卷</font>&nbsp;&nbsp;： <select name="isaint"
					id="isaint" style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">允许</option>
					<option value="0">不允许</option>
				</select>
			</div>
		</div>
		<script type="text/javascript">
						$("#isaint").change(function(){
							var is=$(this).val();
							if("0"==is||""==is){
								$("#aint input").val("");
								$("#aint").hide();
							}else if("1"==is){
								$("#aint").show();
							}
						});
					</script>
		<hr>
		<div id="aint">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">单次允许使用加息张数</font>&nbsp;&nbsp;： <input
						name="aonceint"
						style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;张
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">允许使用加息总张数</font>&nbsp;&nbsp;： <input
						name="atotalint"
						style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;张
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">允许单张加息收益</font>&nbsp;&nbsp;：<input type="text"
						name="aoneqrofit"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
			</div>
			<hr>
		</div>


		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">类现金设置</font></label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否允许类现金卷</font>&nbsp;&nbsp;： <select
					name="isavoucher" id="isavoucher"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">允许</option>
					<option value="0">不允许</option>
				</select>
			</div>
		</div>
		<script type="text/javascript">
						$("#isavoucher").change(function(){
							var is=$(this).val();
							if("0"==is||""==is){
								$("#avoucher input").val("");
								$("#avoucher").hide();
							}else if("1"==is){
								$("#avoucher").show();
							}
						});
					</script>
		<hr>
		<div id="avoucher">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">单次允许类现金卷张数</font>&nbsp;&nbsp;： <input
						name="aoncevoucher"
						style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;张
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">总计允许类现金卷张数</font>&nbsp;&nbsp;： <input
						name="atotalvoucher"
						style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;张
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">允许单张类现金额度</font>&nbsp;&nbsp;： <input type="text"
						name="aonevamount"
						style="width: 100px; height: 25px; font-size: 15px">元
				</div>
			</div>
			<hr>
		</div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">假现金设置</font></label>
			</div>
		</div>
		<hr>



		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否允许假现金卷</font>&nbsp;&nbsp;： <select
					name="isalikevoucher" id="isalikevoucher"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">允许</option>
					<option value="0">不允许</option>
				</select>
			</div>
		</div>
		<script type="text/javascript">
						$("#isalikevoucher").change(function(){
							var is=$(this).val();
							if("0"==is||""==is){
								$("#alikevoucher input").val("");
								$("#alikevoucher").hide();
							}else if("1"==is){
								$("#alikevoucher").show();
							}
						});
					</script>
		<hr>
		<div id="alikevoucher">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">单次允许假现金卷张数</font>&nbsp;&nbsp;： <input
						name="aoncelikevoucher"
						style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;张
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">总计允许假现金卷张数</font>&nbsp;&nbsp;： <input
						name="atotallikevoucher"
						style="width: 100px; height: 25px; font-size: 15px">&nbsp;&nbsp;&nbsp;张
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-5 col-md-offset-1">
					<font size="3">允许单张假现金额度</font>&nbsp;&nbsp;： <input type="text"
						name="aonelvamount"
						style="width: 100px; height: 25px; font-size: 15px">元
				</div>
			</div>
			<hr>
		</div>


		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<input class="btn btn-primary" type="button" id="submitBtu"
					style="margin-left: 200px" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="gotoPlusList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
