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

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/easyui/themes/icon.css"
	type="text/css"></link>

<script
	src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script
	src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script
	src="${pageContext.request.contextPath}/js/debtattorn/insertDebtAttorn.js"></script>
<style type="text/css">
#id {
	margin: 20px;
}

hr {
	margin: 5px;
}
</style>
<script type="text/javascript">
$(function(){
		//默认选中全部等级
		$("#selectAllAownergrade").attr("checked","checked");
		$("#selectAllApurchasergrade").attr("checked","checked");
		$("#selectAllUgrade").attr("checked","checked");
		//隐藏所有checkbox
		$("#insert-ugrade-checkbox-div-aownergrade").hide();
		$("#insert-ugrade-checkbox-div-apurchasergrade").hide();
		$("#insert-ugrade-checkbox-div-ugrade").hide();
		
		//隐藏债转手续费设置
		$("#block").hide();
		//隐藏债转手续费设置中的定额 百分比 最大最小值
		$("#changshu").hide();
		//全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio-aownergrade").change(function() {
			var $radioVal = $(".insert-ugrade-radio-aownergrade:checked").val();
			if ($radioVal == 1) {
				$("#insert-ugrade-checkbox-div-aownergrade").hide();
				$("#insert-ugrade-checkbox-div-aownergrade :checkbox").each(function(){
					this.checked=false;
				});
				
			} else {
				$("#insert-ugrade-checkbox-div-aownergrade").show();
			}
		});
		
		//全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio-apurchasergrade").change(function() {
			var $radioVal = $(".insert-ugrade-radio-apurchasergrade:checked").val();
			if ($radioVal == 1) {
				$("#insert-ugrade-checkbox-div-apurchasergrade").hide();
				$("#insert-ugrade-checkbox-div-apurchasergrade :checkbox").each(function(){
					this.checked=false;
				});
				
			} else {
				$("#insert-ugrade-checkbox-div-apurchasergrade").show();
			}
		});
		
		//全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio-ugrade").change(function() {
			var $radioVal = $(".insert-ugrade-radio-ugrade:checked").val();
			if ($radioVal == 1) {
				$("#insert-ugrade-checkbox-div-ugrade").hide();
				$("#insert-ugrade-checkbox-div-ugrade :checkbox").each(function(){
					this.checked=false;
				});
				
			} else {
				$("#insert-ugrade-checkbox-div-ugrade").show();
			}
		});
		
		
	$("#iequota").hide();
	$("#iepercent").hide();
	
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
				var url="${pageContext.request.contextPath}/debtAttorn/insertDebtAttorn.action";
				var sendData = $("form").serialize();
		        $.post(url,sendData,function(backData){
		        	var $backData=$.trim(backData);
		        	if($backData=="标设置完成"){
		        		alert($backData);
		        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
		        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
		        	}else{
		        		/* 直接新建标的相关设置成功后返回的页面*/
		        		window.location.href="${pageContext.request.contextPath }/debtAttorn/selectDebtAttornByCondition.action";
		        	}
		        });
		}
	});
});
function gotoDebtAttornList(){
	   window.location.href="${pageContext.request.contextPath }/debtAttorn/selectDebtAttornByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form
		action="${pageContext.request.contextPath}/debtAttorn/insertDebtAttorn.action"
		method="post" id="insertDebtAttornForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置债权转让参数</font></label>
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
			<div class="col-md-4 col-md-offset-1">
				<font size="3">债权转让审核</font>&nbsp;&nbsp;： <select name="isdebtaudit"
					id="isdebtaudit"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">需要</option>
					<option value="0">不需要</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否支持逾期债转</font>&nbsp;&nbsp;： <select name="isocdebt"
					id="isocdebt" style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">支持</option>
					<option value="0">不支持</option>
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
					size="4">转让人设置信息</font></label>
			</div>
		</div>
		<hr>

		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<font size="3">债转次数限制： </font> <input name="datimes"
					style="width: 80px;"
					style="width:200px;height:25px;font-size: 15px">&nbsp;&nbsp;&nbsp;次
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				允许债权人债转的等级： <label class="radio-inline"><input
					class="insert-ugrade-radio-aownergrade" type="radio"
					name="aownergrade" value="1" id="selectAllAownergrade">
					全部等级</label> <label class="radio-inline"><input
					class="insert-ugrade-radio-aownergrade" type="radio"
					name="aownergrade" value="2" id="selectActivityAownergrade">选择等级
				</label> &nbsp;&nbsp;
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				<div style="padding-left: 30px; padding-top: 12px;"
					id="insert-ugrade-checkbox-div-aownergrade">
					<c:forEach items="${uGrades}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="aownergrades" value="${item.ugrade }">${item.ugradename }
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
			<div class="col-md-4 col-md-offset-1">
				<font size="3">债转排除名单编号:</font>
			</div>
			<div class="col-md-12 col-md-offset-1">
				<div style="padding-left: 30px; padding-top: 12px;">
					<c:forEach items="${removeNames}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="removenameno" value="${item.nameno }">${item.name }
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
			<div class="col-md-4 col-md-offset-1">
				<font size="3">持有时间</font>&nbsp;&nbsp;： <input name="holdday"
					style="width: 80px;"
					style="width:200px;height:25px;font-size: 15px">&nbsp;&nbsp;&nbsp;天
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">距离下个还款日天数</font>&nbsp;&nbsp;： <input
					name="intervalday" style="width: 80px;"
					style="width:200px;height:25px;font-size: 15px">&nbsp;&nbsp;&nbsp;天
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否允许拆分</font>&nbsp;&nbsp;： <select name="isasplit"
					id="isasplit" style="width: 200px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">可拆分（可部分出售）</option>
					<option value="0">不可拆分（全部出售）</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">转让金额</font>&nbsp;&nbsp;： <input type="text"
					name="attornmoney"
					style="width: 200px; height: 25px; font-size: 15px"></input>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<font size="3">转让系数最低与最高</font>&nbsp;&nbsp;： <input type="text"
					name="minattornratio" style="width: 80px;"
					style="width:100px;height:25px;font-size: 15px;" />
				&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input type="text"
					name="maxattornratio" style="width: 80px;"
					style="width:100px;height:25px;font-size: 15px;" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<hr>


		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">购买人设置信息</font></label>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				允许购买人债转的等级： <label class="radio-inline"><input
					class="insert-ugrade-radio-apurchasergrade" type="radio"
					name="apurchasergrade" value="1" id="selectAllApurchasergrade">
					全部等级</label> <label class="radio-inline"><input
					class="insert-ugrade-radio-apurchasergrade" type="radio"
					name="apurchasergrade" value="2" id="selectActivityApurchasergrade">选择等级
				</label> &nbsp;&nbsp;
			</div>
		</div>

		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				<div style="padding-left: 30px; padding-top: 12px;"
					id="insert-ugrade-checkbox-div-apurchasergrade">
					<c:forEach items="${uGrades}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="apurchasergrades" value="${item.ugrade }">${item.ugradename }
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
			<div class="col-md-12 col-md-offset-1">
				<font size="3">不允许购买的用户名单表编号:</font>
			</div>
			<div class="col-md-12 col-md-offset-1">
				<div style="padding-left: 30px; padding-top: 12px;">
					<c:forEach items="${removeNames}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="noapnameno" value="${item.nameno }">${item.name }
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
			<div class="col-md-4 col-md-offset-1">
				<font size="3">购买人允许全额或部分购买</font>&nbsp;&nbsp;： <select
					name="isabuyallorpart" id="isabuyallorpart"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">全额购买</option>
					<option value="0">部分购买</option>
				</select>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">债转手续费设置信息</font></label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">债转手续费开关</font>&nbsp;&nbsp;： <select id="isadafeeon"
					name="isadafeeon"
					style="width: 200px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">开</option>
					<option value="0">关</option>
				</select>
			</div>
		</div>
		<script type="text/javascript">
							$("#isadafeeon").change(function(){
								var isadafeeonVal=$("#isadafeeon").val();
								if(1==isadafeeonVal){
									$("#selectAllUgrade").val("1");
									$("#selectActivityUgrade").val("2");
									$("#block").show();
								}else{
									$("#block input").val("");
									$("#block select").val("");
									$("#insert-ugrade-checkbox-div-ugrade :checkbox").each(function(){
										this.checked=false;
									});
									$("#block").hide();
								}
								
							});
					</script>
		<hr>

		<div id="block">
			<div class="row">
				<div class="col-md-3 col-md-offset-1">
					允许的转让人等级： <label class="radio-inline"><input
						class="insert-ugrade-radio-ugrade" type="radio" name="ugrade"
						value="1" id="selectAllUgrade"> 全部等级</label> <label
						class="radio-inline"><input
						class="insert-ugrade-radio-ugrade" type="radio" name="ugrade"
						value="2" id="selectActivityUgrade">选择等级 </label> &nbsp;&nbsp;
				</div>
			</div>
			<!-- 把会员等级迭代出来 -->
			<div class="row">
				<div class="col-md-12 col-md-offset-1">
					<div style="padding-left: 30px; padding-top: 12px;"
						id="insert-ugrade-checkbox-div-ugrade">
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
																$("#changshu").show();
																$("#iepercent input").val("");
																$("#iepercent").hide();
																$("#iequota").show();
															}else if($(this).val()=="iepercent"){
																$("#changshu").show();
																$("#iepercent").show();
																$("#iequota input").val("");
																$("#iequota").hide();
															}else{
																$("#changshu").hide();
															}
														});
												</script>
				<div class="col-md-7">
					<font size="3">债转金额段：</font>&nbsp;&nbsp;<input type="text"
						name="minattornmoney"
						style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
						type="text" name="maxattornmoney"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
			</div>
			<hr>

			<div id="changshu">
				<div class="row">
					<div>
						<div id="iequota" class="col-md-6 col-md-offset-1">
							<font size="3">定额</font>&nbsp;&nbsp;：<input type="text"
								name="quota" style="width: 100px; height: 25px; font-size: 15px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div id="iepercent">
							<div class="col-md-3 col-md-offset-1">
								<font size="3">百份比</font>&nbsp;&nbsp;：<input type="text"
									name="attornrate"
									style="width: 100px; height: 25px; font-size: 15px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<div class="col-md-6 ">
								<font size="3">手续费最低与最高：</font>&nbsp;&nbsp;<input type="text"
									name="minfee"
									style="width: 100px; height: 25px; font-size: 15px" />元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input
									type="text" name="maxfee"
									style="width: 100px; height: 25px; font-size: 15px" />元
							</div>
						</div>
					</div>
				</div>
				<hr>
			</div>
		</div>



		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<input class="btn btn-primary" type="button" id="submitBtu"
					style="margin-left: 200px" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="gotoDebtAttornList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
