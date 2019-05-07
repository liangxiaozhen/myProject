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
	src="${pageContext.request.contextPath}/js/failtcompensate/insertFailTCompensate.js"></script>
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
	$("#iequota").hide();
	$("#iepercent").hide();
	/*初始化默认选中全部等级 */
	$("#selectAll").attr("checked","checked");
	$("#insert-ugrade-checkbox-div").hide();
	
	$("#intcompensateon").hide();
	$("#awardcompensateon").hide();
	
	$("#minmoney-maxmoney").hide();
	//全部等级与选择等级的change监听事件
	$(".insert-ugrade-radio").change(function() {
		var $radioVal = $(".insert-ugrade-radio:checked").val();
		if ($radioVal == 1) {
			$("#insert-ugrade-checkbox-div").hide();
			$("#insert-ugrade-checkbox-div :checkbox").each(function(){
				this.checked=false;
			});
		} else {
			$("#insert-ugrade-checkbox-div").show();
		}
	});
	
	/*利息方式补偿开关change事件  */
	$("#isintcompensateon").change(function(){ 
		var  isintcompensateon= $(this).val();
		if(isintcompensateon==0||isintcompensateon==""){
			$("#intcompensateon input").val("");
			$("#insert-ugrade-checkbox-div :checkbox").each(function(){
				this.checked=false;
			});
			$("#intcompensateon").hide();
		}else if(isintcompensateon==1){
			$("#selectAll").val("1");
			$("#selectActivity").val("2");
			$("#intcompensateon").show();
			$("#minmoney-maxmoney").show();
		}
		if($("#intcompensateon").is(":hidden")&&$("#awardcompensateon").is(":hidden")){ 
			$("#minmoney-maxmoney").hide();
		}
	});
	
	/*奖品补偿方式开关change事件*/
	$("#isawardcompensateon").change(function(){ 
		var  isawardcompensateon= $(this).val();
		if(isawardcompensateon==0||isawardcompensateon==""){
			$("#awardcompensateon").hide();
		}else if(isawardcompensateon==1){
			$("#awardcompensateon").show();
			$("#minmoney-maxmoney").show();
		}
		if($("#intcompensateon").is(":hidden")&&$("#awardcompensateon").is(":hidden")){ 
			$("#minmoney-maxmoney").hide();
		}
	});
	$("#submitBtn").click(function(){
		/* 通过标的设置来到这里的就有nextPage，不会回调回来 */
		if($("#nextPage").val()=="nextPage"){
			if(check().form()){
				$("form:first").submit();
			}
		}else{
			if(!check().form()){
				return;
			}
			/* 会回调回来 */
			var url="${pageContext.request.contextPath}/failTCompensate/insertFailTCompensate.action";
			var sendData = $("form").serialize();
	        $.post(url,sendData,function(backData){
	        	var $backData=$.trim(backData);
	        	if($backData=="标设置完成"){
	        		alert($backData);
	        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
	        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
	        	}else{
	        		/* 直接新建标的相关设置成功后返回的页面*/
	        	 	window.location.href="${pageContext.request.contextPath }/failTCompensate/selectFailTCompensateByCondition.action";
	        	}
	        });
		}
	});
	
});
function gotoFailTCompensateList(){
	   window.location.href="${pageContext.request.contextPath }/failTCompensate/selectFailTCompensateByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form
		action="${pageContext.request.contextPath}/failTCompensate/insertFailTCompensate.action"
		method="post" id="insertFailTCompensateForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置流标补偿参数</font></label>
			</div>
		</div>
		<hr>
		<div id="minmoney-maxmoney">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-10   col-md-offset-1">
					<font size="3">分段最低及最高金额：</font>&nbsp;&nbsp;<input type="text"
						name="minmoney"
						style="width: 100px; height: 25px; font-size: 15px" /> 元-<input
						type="text" name="maxmoney"
						style="width: 100px; height: 25px; font-size: 15px" /> 元
				</div>
			</div>
			<hr>
		</div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3  col-md-offset-1">
				<font size="3">利息方式补偿开关</font>&nbsp;&nbsp;： <select
					name="isintcompensateon" id="isintcompensateon"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">开</option>
					<option value="0">关</option>
				</select>
			</div>
			<div class="col-md-4  ">
				<font size="3">奖品补偿方式开关</font>&nbsp;&nbsp;： <select
					name="isawardcompensateon" id="isawardcompensateon"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">开</option>
					<option value="0">关</option>
				</select>
			</div>
		</div>
		<hr>
		<div id="intcompensateon">
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					会员等级： <label class="radio-inline"><input
						class="insert-ugrade-radio" type="radio" name="ugrade" value="1"
						id="selectAll"> 全部等级</label> <label class="radio-inline"><input
						class="insert-ugrade-radio" type="radio" name="ugrade" value="2"
						id="selectActivity">选择等级 &nbsp;&nbsp; </label>
				</div>
			</div>
			<!-- 把所有等级迭代出来 -->
			<div class="row">
				<div class="col-md-12 col-md-offset-1">
					<div style="padding-left: 30px; padding-top: 12px;"
						id="insert-ugrade-checkbox-div">
						<c:forEach items="${uGrades}" var="item" varStatus="status">
							<label class="checkbox-inline"> <input type="checkbox"
								name="ugrades" value="${item.ugrade }">${item.ugradename }
							</label>
							<c:if test="${status.count%4==0 }">
								<br />
								<br />
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
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
															$("#iepercent input").val("");
															$("#iepercent").hide();
															$("#iequota").show();
														}else if($(this).val()=="iepercent"){
															$("#iepercent").show();
															$("#iequota input").val("");
															$("#iequota").hide();
														}else{
															$("#iepercent").hide();
															$("#iequota").hide();
														}
													});
											</script>

				<div id="iequota" class="col-md-4">
					<font size="3">定额补偿金</font>&nbsp;&nbsp;：<input type="text"
						name="quota" style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
				<div id="iepercent">
					<div class="col-md-3">
						<font size="3">日奖励费率</font>&nbsp;&nbsp;：<input type="text"
							name="dayawardrate"
							style="width: 100px; height: 25px; font-size: 15px" />
					</div>
					<div class="col-md-5">
						<font size="3">最高补偿金额：</font>&nbsp;&nbsp;<input type="text"
							name="maxcompensate"
							style="width: 100px; height: 25px; font-size: 15px" />
					</div>
				</div>
			</div>
			<hr>
		</div>

		<div id="awardcompensateon">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">奖品名称</font>&nbsp;&nbsp;： <select name="awardname"
						style="width: 150px; height: 25px; font-size: 15px">
						<option value="">请选择</option>
						<option value="1">类现金红包</option>
						<option value="2">假现金红包</option>
						<option value="3">加息卷</option>
						<option value="4">手机充值券</option>
						<option value="5">手机</option>
						<option value="6">电影券</option>
					</select>
				</div>
			</div>
			<hr>
		</div>

		<div style="margin-bottom: 30px"></div>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否设为模板</font>&nbsp;&nbsp;： <select name="istemplet"
					style="width: 200px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<input class="btn btn-primary" type="button" id="submitBtn"
					style="margin-left: 200px" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="gotoFailTCompensateList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
