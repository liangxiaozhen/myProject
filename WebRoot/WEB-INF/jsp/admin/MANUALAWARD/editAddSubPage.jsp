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
<style type="text/css">
.aos {
	width: 240px;
	height: 32px;
}
</style>
<script type="text/javascript">

	//页面加载完毕时
	window.onload=function(){
		
		var isContinue ="${isContinue}";
		var actMType = "${mam.actMType}";//活动生成方式  1.手动 2.模板
		//alert("活动的生成方式》》》"+actMType);
		//如果是继续添加子活动
		if(actMType==1 && isContinue==1){
			$("#apNo").val("");//奖品编号
			//$("#awardOrPackage").html("");//奖品编号后面的名称
			$("#aOrp").val("");//奖品名称  用来向后台传值
			$("#quantity").val("");//礼品包份数
			$("#awardamount").val("");//奖品金额
			$("#awardRemark").val("");//子活动颁奖说明
			$("#awardRemark").text("");//子活动颁奖说明
		}
		
		//如果是礼品包选中的话，奖品金额就隐藏
		var awardType="${manualAward.awardType}";
		//alert("奖品类型》》》"+awardType);
		if(awardType==1){
			$("#giftBag").attr("checked","checked");
			$("#award_Amount").css("display","none");
		}else{
			//$("#award_Amount").css("display","block");
		}
		
	}
	
	//当输入奖品编号时，验证奖品或奖品包是否有剩余数和下架
	function getAwardName(){
		var action ="<%=basePath%>admin/manual/getAwardName.action";
		//获取奖品或者礼品包输入框中的value值
		var awardNo =$("#apNo").val().trim();
		var awardType=$("input[name='awardType']:checked").val();
		if(awardNo!=null && awardNo!=undefined && awardNo!=''){
			var param={
				"awardno":awardNo,
				"awardType":awardType
			};
			var callback = function(data){
				var json = $.parseJSON(data);
				//alert("奖品是否自定义》》》"+json["isDefineAmount"]);
				//$("#awardOrPackage").html("");
				//$("#awardOrPackage").html(json["result"]);//奖品编号后面的名称
				$("#aOrp").val(json["result"]); //奖品名称  用来向后台传值
				//var apSpan=$("#awardOrPackage").text();
				var apSpan=$("#aOrp").val();
				if(apSpan=="奖品不存在"||apSpan=="礼品包不存在"){
					$("#continueToAdd").attr("disabled",true);
				}else{
					$("#continueToAdd").attr("disabled",false);
				}
				var cashorvoucher = json["cashorvoucher"];
				if(cashorvoucher=="" || cashorvoucher==null || cashorvoucher==undefined ){
					//alert("cashorvoucher===="+cashorvoucher);
					if(json["atype"]==1){
						//$("#award_Amount").css("display","block");
					}else{
						$("#award_Amount").css("display","none");
					}
					
				}else{
					$("#award_Amount").css("display","none");
					
				}
				if(json["atype"]!=1){
					$("#award_Amount").css("display","none");
				}
				
				var iscancel = json["iscancel"];//1.上架   0.下架
				if(iscancel==0){
					$("#continueToAdd").attr("disabled",true);
					$("#saveActivity").attr("disabled",true);
					alert("已下架")
				}else{
					$("#continueToAdd").attr("disabled",false);
					$("#saveActivity").attr("disabled",false);
				}
			};
			$.post(action,param,callback);
		}else{
			$("#aOrp").val("");
		}
	}
	
	 //当奖品和礼品包切换时，清除奖品编号和后面的提示
	function eliminate(){
		 $("#apNo").val("");//奖品编号
		//$("#awardOrPackage").html("");//奖品编号后面的名称
		$("#aOrp").val("");//奖品名称  用来向后台传值
		$("#quantity").val("");//奖品份数
		$("#awardamount").val("");//奖品金额
		$("#awardRemark").text("");//子活动颁奖说明
		$("#awardRemark").val(""); 
		var awardType=$("input[name='awardType']:checked").val();
		if(awardType==1){
			$("#award_Amount").css("display","none");
			$("#gift_package_number").css("display","block");
			$("#prize_number").css("display","none");
		}else{
			$("#prize_number").css("display","block");
			//$("#award_Amount").css("display","block");
			$("#gift_package_number").css("display","none");
		}
	} 
	
	//检测活动名称是否存在
	function actNameChecked(){
		var action = "<%=basePath%>manual/actNameChecked.action";
		var activityName =$("input[name='activityName']").val();
		var param = {
			"activityName":	activityName
		};
		var callback = function(data){
			var json = $.parseJSON(data);
			$("#actNameId").html(json["result"]);
			if(json["result"]!=""){
				$("#continueToAdd").attr("disabled",true);
			}else{
				$("#continueToAdd").attr("disabled",false);
			}
		};
		$.post(action,param,callback);
	}
	
	//验证奖品是否足够
	function isAwardEnough(){
		var flag=true;//开关
		//查询奖品是否足够
		var action = "${pageContext.request.contextPath}/admin/manual/isAwardEnough.action";
		var awardno =$("input[name='awardno']").val();//奖品或礼品包的编号
		//alert("奖品或礼品包编号》》》"+awardno);
		var businessNo=$("#businessNo").val();//定向名单的编号
		//alert("定向名单的编号》》》"+businessNo);
		var param = {
			"awardno":awardno,
			"businessNo":businessNo
		};
		var callback = function(data){
			var json = $.parseJSON(data);
			if(json["result"]!="奖品足够"){
				alert(json["result"]);
				flag=false;
			}
		};
		$.post(action,param,callback);
		return flag;
	}
	
	//点击三个按钮
	$(function(){
		
		 /*验证金额*/
		 jQuery.validator.addMethod("validateAmount",function(value,element){
		 		 
			var flag = true;
		 	var awardamount = $("#awardamount").val();//金额
			var index = awardamount.indexOf("0");//第一个为0的位置
			if(index==0 && awardamount.length>1){/*0开头的数字串*/
				var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
					if(!reg.test(awardamount)){
						flag = false;
					}else{
						flag = true;
					}
			}else{
				 var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/; 
				 if(!reg.test(awardamount)){
					 flag = false;
				 }else{
					 flag = true;
				 }
			}
			
			return flag;
		 },"请输入正确的金额,最多保留小数点两位");
		 
		 /*不通过submit来验证表单*/
		function validateForm(){
			
			return $("#defaultForm").validate({
				
				rules:{
					businessNo:"required",
					awardno:"required",
					quantity:"required",
					awardamount:{
						required:true,
						validateAmount:true
					}
					
				}
			});
		};
		
		$(validateForm());//这里已经实现匿名函数的作用
		
		//点击继续添加子活动
		$("#continueToInsert").click(function(e){
			//alert("123");
			if(validateForm().form()){
				if(window.confirm("确定要继续添加吗？")){
					//奖品是否足够
					var flag=isAwardEnough();
					if(flag){
						document.getElementById("defaultForm").action= "${pageContext.request.contextPath}/admin/manual/continueToInsert.action";
						document.getElementById("defaultForm").submit();
					}
				}
			}
		});
		
		//保存本活动时，事件触发时，自动传入事件对象
		$("#saveActivity").click(function(e){
			if(validateForm().form()){
				
				if(window.confirm("确定要保存吗？")){
					//奖品是否足够
					var flag=isAwardEnough();
					if(flag){
						var action ="${pageContext.request.contextPath}/admin/manual/insertmedalproject.action?saveActivity=2";
						var callback = function(data){
							$("#saveModal").modal({
								backdrop:'static',
								keyboard:false
							});
							$("#saveModal-body").html(data);
						};
						$.post(action,$("#defaultForm").serialize(),callback);
					}
				}
			}
		});
		
		//返回上一页
		$("#goPreviousPage").click(function(e){
			var actno = $("input[name='actno']").val();//子活动编号
			//alert("子活动编号》》》"+actno);
			var action = "${pageContext.request.contextPath}/admin/manual/goPreviousPage.action?actno="+actno;
			window.location.href = action;
		});
		
		//删除本活动
		$("#invalidActivity").click(function(e){
			var flage= window.confirm("确定删除吗？");
			if(flage){
				var action = "${pageContext.request.contextPath}/admin/manual/invalidActivity.action";
				//获取主活动编号
				var activityNo=$("input[name='activityNo']").val();
				//alert("主活动编号》》》"+activityNo);
				var param={
					"activityNo":activityNo
				};
				var callback = function(data){
					var json = $.parseJSON(data);
					alert(json["result"]);
					window.location.href="${pageContext.request.contextPath}/admin/manual/manualActivityQuery.action";
				};
				$.post(action,param,callback);
			}
		});
		
	});
	
	 //当关闭保存成功页面时，跳转到查询页面
	function closeDownActivity(){
		window.location.href="${pageContext.request.contextPath}/admin/manual/manualActivityQuery.action";
	}
	 
	//活动对象选择
	function active_object_select(){
		var action = "${pageContext.request.contextPath}/admin/manual/activeObjectSelect.action";
		var businessNo = $("#businessNo").find("option:selected").val();
		//alert("businessNo>>>"+businessNo);
		if(businessNo!=""){
			var params = { 
				"businessNo":businessNo	
			};
			var callback = function(data){
				$("#businessName").html(data.split(",")[0]+"(共"+data.split(",")[1]+"人)");
			};
			$.post(action,params,callback,"json");
		}else{
			$("#businessName").html("");
		}
	}
</script>
<title>手动颁奖活动设置</title>
<style type="text/css">
#ht {
	margin-left: 45%;
	}
</style>

</head>
<body style="font-family: '微软雅黑'">
	<div class="container" id="finishBaseInfo">
		<div id="ht">
			<h2>手动颁奖活动页面</h2>
		</div>
		<div class="col-md-12 column">
			<form id="defaultForm"
				action="${pageContext.request.contextPath}/admin/manual/insertmedalproject.action"
				method="post" class="form-horizontal">

				<input type="text" style="visibility: hidden" name="actMType"
					value="${mam.actMType}" />

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" style="line-height: 10px;">活动名称：</font>
					<div class="col-sm-8">
						<input type="text" style="border-style: none;"
							onchange="actNameChecked();" name="activityName"
							value="${mam.activityName}" />
					</div>
					<span id="actNameId"></span>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" style="line-height: 10px;">活动编号：</font>
					<div class="col-sm-5">
						<input type="text" style="width: 400px; border-style: none;"
							readonly="true" name="activityNo" value="${mam.activityNo}" />
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font class="col-sm-2 control-label" style="line-height: 14px;">活动执行时间：</font>
					<div class="date col-sm-9" id="datetimeExecute">
						<input type="text" style="width: 400px; border-style: none;"
							readonly="true" name="activityTimeStr"
							value="${mam.activityTimeStr}" />
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" style="line-height: 10px;">子活动编号：</font>
					<div class="col-sm-5">
						<input type="text" style="width: 400px; border-style: none;"
							readonly="true" name="actno" value="${manualAward.actno}">
					</div>
				</div>

				<div class="form-group  has-feedback" style="display: none;">
					<font class="col-sm-2 control-label" style="line-height: 14px;">子活动设置时间：</font>
					<div class="date col-sm-9" id="subDatetimeSet">
						<input type="text"
							style="border-radius: 5px; line-height: 28px; width: 200px"
							name="" class="Wdate"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <span
							id="tishi3"></span>
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" style="line-height: 10px;">子活动设置人：</font>
					<div class="col-sm-3">
						<input type="text" style="border-style: none;" readonly="true"
							name="addman" value="${adminuser.username}">
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" style="line-height: 10px;">活动对象选择：</font>
					<div class="col-sm-9">
						<select class="aos" style="border-radius: 4px;" id="businessNo"
							name="businessNo" onchange="active_object_select();">
							<option value="">--请选择--</option>
							<c:forEach items="${snlList}" var="sl">
								<option value="${sl.businessNo}">${sl.businessNo}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" style="line-height: 10px;">定向单位名称：</font>
					<div class="col-sm-9">
						<font id="businessName" name="businessName"></font>
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" for="inputSuccess3"
						style="line-height: 10px;">奖品选择：</font>
					<div class="col-sm-9">
						<input type="radio" name="awardType" value="2"
							onclick="eliminate()" checked="checked">奖品 <input
							type="radio" id="giftBag" name="awardType" value="1"
							onclick="eliminate()">礼品包
						<%-- ${manualAward.awardType==1?'checked':''} --%>
					</div>
				</div>

				<div class="form-group  has-feedback">
					<font id="prize_number" class="control-label col-sm-2">奖品编号：</font>
					<font id="gift_package_number" class="control-label col-sm-2"
						style="display: none">礼品包编号：</font>
					<div class="col-sm-5">
						<input type="text" id="apNo" onblur="getAwardName()"
							name="awardno" value="" class="form-control">
					</div>
					<input id="aOrp"
						style="width: 400px; border-style: none; line-height: 30px;"
						readonly="true" name="awardname" value="" />
					<%-- <span id="awardOrPackage" style="line-height:30px;">${manualAward.awardname}</span> --%>
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2">奖品/礼品包份数：</font>
					<div class="col-sm-5">
						<input type="text" id="quantity" name="quantity"
							class="form-control" value="">
					</div>
				</div>

				<div id="award_Amount" class="form-group  has-feedback" style="display:none;">
					<font class="control-label col-sm-2">奖品金额：</font>
					<div class="col-sm-5">
						<input type="text" id="awardamount" name="awardamount"
							class="form-control" value="">
					</div>
					<!-- <select name="awardUnit" id="awardUnit"
						style="width: 20auto; height: 32px; font-size: 15px; border-radius: 4px;">
						<option value="">--请选择--</option>
						<option id="rmb" value="元">元</option>
						<option id="baifenbi" value="%">%</option>
						<option value="分">分</option>
					</select> -->
				</div>

				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2" for="inputSuccess3">子活动颁奖说明：</font>
					<div class="col-sm-8">
						<textarea type="text" id="awardRemark" name="awardremark"
							class="form-control" value=""></textarea>
					</div>
				</div>

				<!-- 模板的主活动编号  自己另取的字段 -->
				<input type="text" style="visibility: hidden" name="maMainNo"
					value="${maMainNo}" class="form-control" />

				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2"></label>
					<div class="col-sm-2">
						<button type="button" id="continueToInsert"
							class="btn btn-default">继续添加子活动</button>
					</div>
					<div class="col-sm-2">
						<button type="button" id="saveActivity" class="btn btn-default">保存本活动</button>
					</div>
					<div class="col-sm-2">
						<button type="button" id="goPreviousPage" class="btn btn-default">返回上一页</button>
					</div>
					<div class="col-sm-2">
						<button type="button" id="invalidActivity" class="btn btn-default">删除本活动</button>
					</div>
				</div>
			</form>
		</div>

		<!-- 保存本活动成功 -->
		<div id="saveModal" class="modal fade bs-example-modal-lg"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-exclamation-sign"></span>提示：活动保存成功
						</h4>
					</div>
					<div class="modal-body" id="saveModal-body"></div>
					<div class="modal-footer">
						<button type="button" onclick="closeDownActivity();"
							class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
</body>

</html>