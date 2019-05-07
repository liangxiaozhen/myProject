<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script type="text/javascript">

	//页面加载完毕时
	window.onload=function(){
		
		var actMType = "${mam.actMType}";
		//alert("actMType>>>"+actMType);
		if(actMType==1){
			$("#activityType").val("手动生成");
		}else if(actMType==2){
			$("#activityType").val("模板生成");
		}
		
		var isTempletMain = "${mam.isTempletMain}";
		if(isTempletMain=="2"){
			$("input[name='isTempletMain']").removeAttr("checked");
			$("input[name='isTempletMain'][value='2']").prop("checked","checked");
		} 
		
		var isManual = "${mam.isManual}";
		if(isManual=="2"){
			$("input[name='isManual']").removeAttr("checked");
			$("input[name='isManual'][value='2']").prop("checked","checked");
		}
	}	
	
	//点击三个按钮
	$(function(){
		
		jQuery.validator.addMethod("activityNameOnly",function(value,element){
			//验证活动名称的唯一性
			var activityName =$("input[name='activityName']").val().trim();
			//alert("activityName===="+activityName);
			var activityNo =$('input[name="activityNo"]').val().trim();
			//alert("activityNo===="+activityNo);
			var flag = true;
			$.ajax({
				url:"<%=basePath%>admin/manual/updNameChecked.action",
				dataType:"json",
				data:{
					activityName:encodeURI(activityName),//解码
					activityNo:activityNo
				},
				async:false,
				success:function(data){
					
					if(data["result"]=="名称已存在"){
						flag = false;
					}
				}
			});
			return flag;
		},"名称已存在");
		
		function validateForm(){
			return $("#defaultForm").validate({
				rules:{
					activityName:{
						required:true,
						activityNameOnly:true
					},
					activityTimeStr:"required"
				}
			});
			
		};
		
		$(validateForm());
		
		//点击继续编辑子活动
		$("#continueToEdit").click(function(e){
			if(validateForm().form()){
				if(window.confirm("确定要继续编辑吗？")){
					document.getElementById("defaultForm").action= "${pageContext.request.contextPath}/admin/manual/continueToEdit.action?continueToEdit=3";
					document.getElementById("defaultForm").submit();
				}
			}
		}); 
		
		//保存本活动时，事件触发时，自动传入事件对象
		$("#edit_saveManual").click(function(e){
			if(validateForm().form()){
				if(window.confirm("确定要保存吗？")){
					var action ="${pageContext.request.contextPath}/admin/manual/editSaveManual.action";
					var callback = function(data){
						alert(data);
						window.location.href="${pageContext.request.contextPath}/admin/manual/manualActivityQuery.action";
					};
					$.post(action,$("#defaultForm").serialize(),callback,"json");
				}
			}
		});
	});
	
</script>
<title>手动颁奖活动设置</title>
<style type="text/css">
#ht{
	margin-left: 45%;
}
</style>

</head>
<body style="font-family:'微软雅黑'">
<div class="container" id="finishBaseInfo">
<div id="ht"><h3>手动颁奖活动编辑页面</h3></div>
  <div class="col-md-12 column">
  	<form  id="defaultForm" action="${pageContext.request.contextPath}/admin/manual/continueToEdit.action" method="post" class="form-horizontal">
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2">活动名称：</font>
			<div class="col-sm-8">
				<input type="text" onchange="updNameChecked();" name="activityName" value="${mam.activityName}" class="form-control"/>
			</div>
			<span id="actNameId"></span>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" style="line-height: 12px;">活动编号：</font>
	   		<div class="col-sm-5">
	      		<input type="text" style="width:400px;border-style:none;" readonly="true" name="activityNo" value="${mam.activityNo}"/>
	   		</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
   			<font class="col-sm-2 control-label" style="line-height:14px;">活动执行时间：</font>
   			<div class="date col-sm-9" id="datetimeExecute">
   				<input type="text" style="border-radius:5px;line-height:28px;width: 200px"  name="activityTimeStr" value="${mam.activityTimeStr}" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> 
   				<span id="tishi1"></span>
   			</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" style="line-height:10px;">是否作为模板：</font>
	   		<div class="col-sm-9">
		      	<input type="radio" name="isTempletMain" value="1" checked="checked"/>是
				<input type="radio" id="isTempletMain2" name="isTempletMain" value="2"/>否
	   		</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" style="line-height:10px;">是否需要审核：</font>
	   		<div class="col-sm-9">
		      	<input type="radio" name="isAudit" value="1" ${mam.isAudit==1?'checked':''}/>是
				<input type="radio" name="isAudit" value="2" ${mam.isAudit==2?'checked':''}/>否
	   		</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2">活动设置人：</font>
			<div class="col-sm-3">
				<input type="text" style="border-style:none;" readonly="true" name="addManMain" value="${adminuser.username}"/>
			</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" style="line-height:10px;">活动生成方式：</font>
			<div class="col-sm-3">
				<input  type="text" id="activityType" style="width:400px;border-style:none;" readonly="true"/>
			</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" style="line-height:10px;">是否容许手动执行：</font>
	   		<div class="col-sm-9">
		      	<input type="radio" name="isManual" value="1" checked="checked"/>容许
				<input type="radio" name="isManual" value="2"/>不容许
	   		</div>
  		</div>
  		
  		<!-- 活动生成方式  1.手动 2.模板 -->
		<input type="text" style="visibility:hidden;" name="actMType" value="${mam.actMType}" class="form-control"/>
  		
  		<div class="form-group has-success has-feedback" >
  			<label class="control-label col-sm-2"></label>
			<div class="col-sm-3">
	    		<button type="button" id="continueToEdit" class="btn btn-default">继续编辑子活动</button>
			</div>
			<div class="col-sm-3">
	   			<button type="button" id="edit_saveManual" class="btn btn-default">保存本活动</button>
			</div>
		</div>
  	</form>
  </div>
</body>
</html>