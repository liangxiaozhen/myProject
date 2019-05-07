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
	src="${pageContext.request.contextPath}/js/aheadrepay/insertAheadRepay.js"></script>
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
	$("#picompensateon").hide();
	$("#pluscompensateon").hide();
	$("#forplatformon").hide();
	$("#type5").hide();
	$("#jlpt").hide();
	
	
	$("#jie_kuan_ren").hide();
	$("#ping_tai").hide();
	$("#penaltyquota").hide();
	$("#penaltyrate").hide();
	$("#maxpenalty").hide();
	
	$("#zypt").hide();
	
	$("#zheng_yi_ping_tai_fa_jin").hide();
	$("#zheng_yi_ping_tai_zhang_li").hide();
	
	$("#pluspenaltyquota").hide();
	$("#pluspenaltyrate").hide();
	
	$("#awardplatquota").hide();
	$("#awardplatrate").hide();
	$("#awardplatrate").hide();
	
	$("#setting").hide();
	$("setting2").hide();
	/*总开关的change事件 */
	$("#ispicompensateon").change(function(){
		var is=$(this).val();
		if("0"==is||""==is){
			$("#picompensateon input").val("");
			$("#picompensateon").hide();
		}else if("1"==is){
			$("#picompensateon").show();
		}
	});
	
	
	/*总开关的change事件 */
		$("#ispluscompensateon").change(function(){
			var is=$(this).val();
			if("0"==is||""==is){
				$("#pluscompensateon input").val("");
				$("#pluscompensateon").hide();
			}else if("1"==is){
				$("#pluscompensateon").show();
			}
		});
	
		/*还款人补偿总开关的change事件 */
		$("#isforplatformon").change(function(){
			var is=$(this).val();
			if("0"==is||""==is){
				$("#forplatformon input").val("");
				$("#forplatformon").hide();
				
				$("#type5 select").val("");
				$("#type5").hide();
				
				$("#jlpt input").val("");  
				$("#jlpt").hide();
			}else if("1"==is){
				$("#forplatformon").show();
				$("#type5").show();
			}
		});
		
	 
	
	
	/*补偿投资人欠收利息设置中借款人罚金奖励类型(类型/百分比)的change */
	$("#type").change(function(){
		if($(this).val()=="iequota"){
			$("#setting").show();
			$("#penaltyrate input").val("");
			$("#penaltyrate").hide();
			$("#penaltyquota").show();
		}else if($(this).val()=="iepercent"){
			$("#setting").show();
			$("#penaltyrate").show();
			$("#penaltyquota input").val("");
			$("#penaltyquota").hide();
		}else{
			$("#setting").hide();
			$("#penaltyrate").hide();
			$("#penaltyquota").hide();
		}
	});
	
	
					/* 补偿投资人欠收利息设置中奖励方式的change事件*/
					$("#awardtype").change(function(){
						//惩罚借款人
						if($(this).val()=="1"){
							$("#ping_tai input").val("");
							$("#ping_tai").hide();
							$("#jie_kuan_ren").show();
							//平台奖励
						}else if($(this).val()=="2"){
							$("#jie_kuan_ren input").val("");
							$("#jie_kuan_ren select").val("");
							$("#ping_tai").show();
							$("#setting").hide();
							$("#jie_kuan_ren").hide();
							//惩罚借款人且平台奖励
						}else if($(this).val()=="3"){
							$("#ping_tai").show();
							$("#jie_kuan_ren").show();
						}else{
							$("#jie_kuan_ren").hide();
							$("#ping_tai").hide();
						}
					});
	
					/*补偿投资人增益利息设置的奖励方式的change事件*/
									$("#plusawardtype").change(function(){
										//平台罚金
										if($(this).val()=="1"){
											$("#zheng_yi_ping_tai_zhang_li input").val("");
											$("#zheng_yi_ping_tai_zhang_li").hide();
											$("#zheng_yi_ping_tai_fa_jin").show();
										//平台奖励
										}else if($(this).val()=="2"){
											$("#zheng_yi_ping_tai_fa_jin input").val("");
											$("#zheng_yi_ping_tai_fa_jin select").val("");
											$("#zheng_yi_ping_tai_fa_jin").hide();
											$("#setting2").hide();
											$("#zheng_yi_ping_tai_zhang_li").show();
										//平台罚金且平台奖励
										}else if($(this).val()=="3"){
											$("#zheng_yi_ping_tai_fa_jin").show();
											$("#zheng_yi_ping_tai_zhang_li").show();
										}else{
											$("#zheng_yi_ping_tai_fa_jin").hide();
											$("#zheng_yi_ping_tai_zhang_li").hide();
										}
									});
							/*补偿投资人增益利息设置的定额与百分比的change事件*/
									$("#type2").change(function(){
										if($(this).val()=="iequota"){
											$("#setting2").show();
											$("#pluspenaltyrate input").val("");
											$("#pluspenaltyrate").hide();
											
											$("#plusmaxpenalty input").val("");
											$("#plusmaxpenalty").hide();
											
											$("#pluspenaltyquota").show();
										}else if($(this).val()=="iepercent"){
											$("#setting2").show();
											$("#pluspenaltyquota input").val("");
											$("#pluspenaltyquota").hide();
											
											$("#pluspenaltyrate").show();
											$("#plusmaxpenalty").show();
										}else{
											$("#setting2").val("");
											$("#setting2").hide();
										}
									});
							
							
							
							/* 借款人补偿平台设置定额与百分比的change事件 */
									$("#type3").change(function(){
										if($(this).val()=="iequota"){
											$("#awardplatrate input").val("");
											$("#awardplatrate").hide();
											$("#awardplatquota").show();
										}else if($(this).val()=="iepercent"){
											$("#awardplatrate").show();
											$("#awardplatquota input").val("");
											$("#awardplatquota").hide();
										}else{
											$("#awardplatrate input").val("");
											$("#awardplatquota input").val("");	
											$("#awardplatquota").hide();
											$("#awardplatrate").hide();
										}
									});
									$("#submitBtu").click(function(){
										/* 通过标的设置来到这里的就有nextPage，不会回调回来 */
										if($("#nextPage").val()=="nextPage"){
											if(check().form()){
												$("form:first").submit();
											}
										}else{
											if(!check().form()){
												return;
											}
											//这样保存就有回调：情况1，普通设置   情况2，最后一个设置
												var url="${pageContext.request.contextPath}/aheadRepay/insertAheadRepay.action";
												var sendData = $("form").serialize();
										        $.post(url,sendData,function(backData){
										        	var $backData=$.trim(backData);
										        	if($backData=="标设置完成"){
										        		alert($backData);
										        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
										        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
										        	}else{
										        		/* 直接新建标的相关设置成功后返回的页面*/
										        		window.location.href="${pageContext.request.contextPath }/aheadRepay/selectAheadRepayByCondition.action";
										        	}
										        });
										}
									});
});



function gotoAheadRepayList(){
	   window.location.href="${pageContext.request.contextPath }/aheadRepay/selectAheadRepayByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form
		action="${pageContext.request.contextPath}/aheadRepay/insertAheadRepay.action"
		method="post" id="insertAheadRepayForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置提前还款参数</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">补偿投资人欠收利息设置</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">欠收利息补偿开关</font>&nbsp;&nbsp;： <select
					name="ispicompensateon" id="ispicompensateon"
					style="width: 200px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">开</option>
					<option value="0">关</option>
				</select>
			</div>
		</div>
		<hr>
		<div id="picompensateon">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-10 col-md-offset-1">
					<font size="3">单个投资人累计本金欠收最小利息和最高利息</font>&nbsp;&nbsp;： <input
						type="text" name="minnoreceiveint"
						style="width: 100px; height: 25px; font-size: 15px" />元- <input
						type="text" name="maxnoreceiveint"
						style="width: 100px; height: 25px; font-size: 15px" /> 元
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">奖励方式</font>&nbsp;&nbsp;： <select name="awardtype"
						id="awardtype" style="width: 200px; height: 25px; font-size: 15px">
						<option value="">请选择</option>
						<option value="1">惩罚借款人</option>
						<option value="2">平台奖励</option>
						<option value="3">惩罚借款人且平台奖励</option>
					</select>
				</div>
			</div>
			<hr>
			<div id="jie_kuan_ren">
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						<font size="3">借款人罚金名称</font>&nbsp;&nbsp;：<input type="text"
							name="loanpenaltyname"
							style="width: 100px; height: 25px; font-size: 15px" />
					</div>
					<div class="col-md-2 " class="col-md-2">
						<font size="3">类型</font> <select id="type" name="type"
							style="width: 80px; height: 25px; font-size: 15px">
							<option value="">请选择</option>
							<option value="iequota">定额</option>
							<option value="iepercent">百份比</option>
						</select>
					</div>
					<div id="setting">
						<div class="col-md-4" id="penaltyquota">
							<font size="3">罚金定额</font>&nbsp;&nbsp;：<input type="text"
								name="penaltyquota"
								style="width: 100px; height: 25px; font-size: 15px" />元
						</div>
						<div id="penaltyrate">
							<div class="col-md-2">
								<font size="3">罚金百分比</font>&nbsp;&nbsp;：<input type="text"
									name="penaltyrate"
									style="width: 60px; height: 25px; font-size: 15px" />%
							</div>
							<div class="col-md-3">
								<font size="3">罚金最大值</font>&nbsp;&nbsp;：<input type="text"
									name="maxpenalty"
									style="width: 60px; height: 25px; font-size: 15px" />元
							</div>
						</div>
					</div>
				</div>

				<hr>
			</div>
			<div id="ping_tai">
				<div class="row">
					<div class="col-md-5 col-md-offset-1">
						<font size="3">平台奖励名称</font>&nbsp;&nbsp;： <select
							name="pawardname"
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
		</div>


		<div class="row">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">补偿投资人增益利息设置</font></label>
			</div>
		</div>

		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">增益利息补偿开关</font>&nbsp;&nbsp;： <select
					name="ispluscompensateon" id="ispluscompensateon"
					style="width: 200px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">开</option>
					<option value="0">关</option>
				</select>
			</div>
		</div>
		<hr>
		<div id="pluscompensateon">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-10 col-md-offset-1">
					<font size="3">单个投资人累计增益欠收最小利息和最高利息</font>&nbsp;&nbsp;： <input
						type="text" name="minplusnoreceiveint"
						style="width: 100px; height: 25px; font-size: 15px" />元- <input
						type="text" name="maxplusnoreceiveint"
						style="width: 100px; height: 25px; font-size: 15px" /> 元
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">增益奖励方式</font>&nbsp;&nbsp;： <select
						name="plusawardtype" id="plusawardtype"
						style="width: 200px; height: 25px; font-size: 15px">
						<option value="">请选择</option>
						<option value="1">平台罚金</option>
						<option value="2">平台奖励</option>
						<option value="3">平台罚金且平台奖励</option>
					</select>
				</div>
			</div>
			<hr>

			<div id="zheng_yi_ping_tai_fa_jin">
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						<font size="3">增益奖励名称</font>&nbsp;&nbsp;：<input type="text"
							name="pluspenaltyname"
							style="width: 100px; height: 25px; font-size: 15px" />
					</div>
					<div class="col-md-2 ">
						<font size="3">类型</font> <select id="type2" name="type2"
							style="width: 80px; height: 25px; font-size: 15px">
							<option value="">请选择</option>
							<option value="iequota">定额</option>
							<option value="iepercent">百份比</option>
						</select>
					</div>
					<div id="setting2">
						<div class="col-md-2" id="pluspenaltyquota">
							<font size="3">定额</font>&nbsp;&nbsp;：<input type="text"
								name="pluspenaltyquota"
								style="width: 100px; height: 25px; font-size: 15px" />元
						</div>
						<div id="pluspenaltyrate">
							<div class="col-md-2 ">
								<font size="3">百分比</font>&nbsp;&nbsp;：<input type="text"
									name="pluspenaltyrate"
									style="width: 60px; height: 25px; font-size: 15px" />%
							</div>
							<div class="col-md-2">
								<font size="3">最大值</font>&nbsp;&nbsp;：<input type="text"
									name="plusmaxpenalty"
									style="width: 60px; height: 25px; font-size: 15px" />元
							</div>
						</div>
					</div>
				</div>
				<hr>
			</div>

			<div id="zheng_yi_ping_tai_zhang_li">
				<div class="row">
					<div class="col-md-5 col-md-offset-1">
						<font size="3">增益平台奖励名称</font>&nbsp;&nbsp;： <select
							name="pawardname"
							style="width: 150px; height: 25px; font-size: 15px">
							<option value="">请选择</option>
							<option>类现金红包</option>
							<option>假现金红包</option>
							<option>加息卷</option>
							<option>手机充值券</option>
							<option>手机</option>
							<option>电影券</option>
						</select>
					</div>
				</div>
				<hr>
			</div>
		</div>



		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">借款人补偿平台设置</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">借款人补偿平台开关</font>&nbsp;&nbsp;： <select
					id="isforplatformon" id="isforplatformon" name="isforplatformon"
					style="width: 80px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">开</option>
					<option value="0">关</option>
				</select>
			</div>
		</div>
		<hr>
		<div id="forplatformon">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">平台收款人</font>&nbsp;&nbsp;：<input type="text"
						name="awardrecman"
						style="width: 200px; height: 25px; font-size: 15px" />
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<font size="3">投资人总欠收最小与最高利息</font>&nbsp;&nbsp;：<input type="text"
						name="minallnoreceiveint"
						style="width: 100px; height: 25px; font-size: 15px" />元 -<input
						type="text" name="maxallnoreceiveint"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>


			</div>
			<hr>
		</div>

		<div id="type5">
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
					<font size="3">类型</font> <select id="type3" name="type3"
						style="width: 80px; height: 25px; font-size: 15px">
						<option value="">请选择</option>
						<option value="iequota">定额</option>
						<option value="iepercent">百份比</option>
					</select>
				</div>
				<div id="awardplatquota" class="col-md-3">
					<font size="3">定额</font>&nbsp;&nbsp;：<input type="text"
						name="awardplatquota"
						style="width: 50px; height: 25px; font-size: 15px" />元
				</div>
				<div id="awardplatrate">
					<div class="col-md-3">
						<font size="3">百份比</font>：<input type="text" name="awardplatrate"
							style="width: 60px; height: 25px; font-size: 15px" />%&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div class="col-md-4">
						<font size="3">最小值与最大值</font> ：<input type="text"
							name="awardplatminmoney"
							style="width: 60px; height: 25px; font-size: 15px" /> 元-<input
							type="text" name="awardplatmaxmoney"
							style="width: 60px; height: 25px; font-size: 15px" />元
					</div>
				</div>
			</div>
			<hr>
		</div>



		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否为模板</font>&nbsp;&nbsp;： <select id="istemplet"
					name="istemplet" style="width: 80px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<input class="btn btn-primary" type="button" id="submitBtu"
					style="margin-left: 200px" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="gotoAheadRepayList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
