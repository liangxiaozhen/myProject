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
	src="${pageContext.request.contextPath}/js/overduecompensate/insertOverdueCompensate.js"></script>
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
		$("#precoveryon").hide();
		$("#upayon").hide();
		/* 把定额和百分比隐藏 */
		$("#occquota").hide();
		$("#toccrate").hide();
		/*初始化默认选中全部等级 */
		$("#selectAll").attr("checked","checked");
		$("#insert-ugrade-checkbox-div").hide();
		
		
	/* 是否设置平台追偿开关change事件 */
		$("#isprecoveryon").change(function(){
			var isprecoveryon=$(this).val();
			if("0"==isprecoveryon||""==isprecoveryon){
				$("#precoveryon input").val("");
				$("#precoveryon").hide();
			}else if("1"==isprecoveryon){
				$("#precoveryon").show();
			}
		});
		
		/* 是否开通会员垫付开关change事件 */
		$("#isupayon").change(function(){
			var isupayon=$(this).val();
			if(isupayon=="0"||isupayon==""){
				$("#upayon input").val("");
				$("#insert-ugrade-checkbox-div :checkbox").each(function(){
					this.checked=false;
				});
				$("#upayon").hide();
			}else if(isupayon=="1"){
				$("#selectAll").val("1");
				$("#selectActivity").val("2");
				$("#upayon").show();
			}
		});
		
	
	/*定额与百分比的change事件  */
	$("#type").change(function(){
		if($(this).val()=="iequota"){ 
			$("#toccrate input").val("");
			$("#toccrate").hide();
			$("#occquota").show();
		}else if($(this).val()=="iepercent"){
			$("#occquota input").val("");
			$("#occquota").hide();
			$("#toccrate").show();
		}else{
			$("#occquota").hide();
			$("#toccrate").hide();
		}
	});
	
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
						var url="${pageContext.request.contextPath}/overdueCompensate/insertOverdueCompensate.action";
						var sendData = $("form").serialize();
				        $.post(url,sendData,function(backData){
				        	var $backData=$.trim(backData);
				        	if($backData=="标设置完成"){
				        		alert($backData);
				        		/* 通过标的设置来到这里，且是最后一个标的相关设置，成功后返回的页面 */
				        		window.location.href="${pageContext.request.contextPath }/tenderItem/selectTenderItemByCondition.action";
				        	}else{
				        		/* 直接新建标的相关设置成功后返回的页面*/
				        		 window.location.href="${pageContext.request.contextPath }/overdueCompensate/selectOverdueCompensateByCondition.action";
				        	}
				        });
		}
	});
	
});



function gotoOverdueCompensateList(){
	   window.location.href="${pageContext.request.contextPath }/overdueCompensate/selectOverdueCompensateByCondition.action";
}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form
		action="${pageContext.request.contextPath}/overdueCompensate/insertOverdueCompensate.action"
		method="post" id="insertOverdueCompensateForm">
		<input type="hidden" name="nextPage" id="nextPage"
			value="${nextPage }" />
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="5">设置逾期参数</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">逾期代偿人</font>&nbsp;&nbsp;：<input type="text"
					name="cmanno" style="width: 200px; height: 25px; font-size: 15px" />
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">逾期宽限期</font>&nbsp;&nbsp;：<input type="text"
					name="graceperiod"
					style="width: 200px; height: 25px; font-size: 15px" />天
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">日滞纳金率</font>&nbsp;&nbsp;：<input type="text"
					name="daylatefeerate"
					style="width: 200px; height: 25px; font-size: 15px" />
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">平台追偿设置信息</font></label>
			</div>
		</div>
		<hr>


		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否设置平台追偿</font>&nbsp;&nbsp;： <select
					name="isprecoveryon" id="isprecoveryon"
					style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<hr>

		<div id="precoveryon">
			<div class="row" style="line-height: 0px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">平台追偿费收款人</font>&nbsp;&nbsp;：<input type="text"
						name="pmiscrecman"
						style="width: 200px; height: 25px; font-size: 15px" />
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-6 col-md-offset-1">
					<font size="3">分段逾期本金最低值与最高值</font>&nbsp;&nbsp;： <input type="text"
						name="ocminmoney"
						style="width: 100px; height: 25px; font-size: 15px" />元-<input
						type="text" name="ocmaxmoney"
						style="width: 100px; height: 25px; font-size: 15px" />元
				</div>
			</div>
			<hr>

			<div class="row" style="line-height: 0px;">
				<div class="col-md-2 col-md-offset-1">
					<font size="3">类型</font> <select id="type" name="type"
						style="width: 80px; height: 25px; font-size: 15px">
						<option value="">请选择</option>
						<option value="iequota">定额</option>
						<option value="iepercent">百份比</option>
					</select>
				</div>
				<div class="col-md-3 " id="occquota">
					<font size="3">追偿定额收费金额</font>&nbsp;&nbsp;：<input type="text"
						name="occquota" style="width: 80px; height: 25px; font-size: 15px" />
				</div>
				<div id="toccrate">
					<div class="col-md-3 ">
						<font size="3">追偿收费费率</font>&nbsp;&nbsp;：<input type="text"
							name="toccrate"
							style="width: 80px; height: 25px; font-size: 15px" />
					</div>
					<div class="col-md-6">
						<font size="3">追偿该段金额最低及最高收费</font>&nbsp;&nbsp;： <input
							type="text" name="mincfees"
							style="width: 80px; height: 25px; font-size: 15px" /> 元-<input
							type="text" name="maxcfees"
							style="width: 80px; height: 25px; font-size: 15px" />
					</div>
				</div>
			</div>
			<hr>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
					size="4">会员垫付设置信息</font></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否开通会员垫付</font>&nbsp;&nbsp;： <select name="isupayon"
					id="isupayon" style="width: 100px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<hr>

		<div id="upayon">
			<div class="row">
				<div class="col-md-4 col-md-offset-1">
					会员等级： <label class="radio-inline"><input
						class="insert-ugrade-radio" type="radio" name="ugrade" value="1"
						id="selectAll"> 全部等级</label> <label class="radio-inline"><input
						class="insert-ugrade-radio" type="radio" name="ugrade" value="2"
						id="selectActivity">选择等级 &nbsp;&nbsp; </label>
				</div>
			</div>
			<!--  -->
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
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1">
					<font size="3">本金垫付比例</font>&nbsp;&nbsp;：<input type="text"
						name="pfprincipalrate"
						style="width: 50px; height: 25px; font-size: 15px" />
				</div>
				<div class="col-md-6">
					<font size="3">本金垫付最高金额</font>&nbsp;&nbsp;：<input type="text"
						name="maxpfprincipal"
						style="width: 100px; height: 25px; font-size: 15px" />
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1">
					<font size="3">利息垫付比例</font>&nbsp;&nbsp;：<input type="text"
						name="pfintrate"
						style="width: 50px; height: 25px; font-size: 15px" />
				</div>
				<div class="col-md-6">
					<font size="3">利息垫付最高金额</font>&nbsp;&nbsp;：<input type="text"
						name="maxpfint"
						style="width: 100px; height: 25px; font-size: 15px" />
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1">
					<font size="3">滞纳金垫付比例</font>&nbsp;&nbsp;：<input type="text"
						name="latefeerate"
						style="width: 50px; height: 25px; font-size: 15px" />
				</div>
				<div class="col-md-6 ">
					<font size="3">滞纳金垫付最高金额</font>&nbsp;&nbsp;：<input type="text"
						name="maxlatefee"
						style="width: 100px; height: 25px; font-size: 15px" />
				</div>
			</div>
			<hr>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">是否为模板</font>&nbsp;&nbsp;： <select name="istemplet"
					style="width: 80px; height: 25px; font-size: 15px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value=2>否</option>
				</select>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-5">
				<input class="btn btn-primary" type="button" id="submitBtu"
					style="margin-left: 200px" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="backid" class="btn btn-success" name="backid"
					type="button" onclick="gotoOverdueCompensateList()">返回列表</button>
			</div>
		</div>
	</form>
</body>
</html>
