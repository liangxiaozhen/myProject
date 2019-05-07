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
	#ht{
		margin-left: 45%;
	}
	.aos{ 
		width:240px;	
		height:32px;
	}
</style>
<script type="text/javascript">

	//页面加载完毕时
	window.onload=function(){
		
		var continueToEdit = "${remainActNum}";
		if(continueToEdit==1){
			alert("这是最后一个子活动");
			//将继续编辑子活动隐藏
			$("#continueToEdit").css("display","none");
			$("#newlyAdd").css("display","block");
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
		
		var businessName = "${snl.businessName}";//定向名单  标题
		var businessNo = "${snl.businessNo}";//定向名单编号
		var bn = document.getElementById("businessNo");
		for(var i=0;i<bn.length;i++){
			if(businessNo == bn[i].value){
				$("option[value="+businessNo+"]").attr("selected","selected");
				$("#businessName").html(businessName+"(共"+"${count}"+"人)");
				break;
			}
		}
		
		var number = "${number}";
		var subANum = "${subActivityNum}";
		if(number==subANum){
			//最后一个子活动
			$("#continue_edit").css("display","none");
			$("#newly_add").css("display","block");
		}
		
		/*编辑时奖品的显示和隐藏*/
		var cashorvoucher = "${award.cashorvoucher}"
		//alert("cashorvoucher===="+cashorvoucher);
		var atype = "${award.atype}"
		//alert("atype===="+atype);
		if(cashorvoucher==''||cashorvoucher==null){
			if(atype==1){
				//document.getElementById("award_Amount").style.display = "block";
			}else{
				document.getElementById("award_Amount").style.display = "none";
			}
		}
	}; 

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
				/* $("#awardOrPackage").html("");
				$("#awardOrPackage").html(json["result"]);//奖品编号后面的名称 */
				$("#aOrp").val(json["result"]); //奖品名称  用来向后台传值
				var apSpan=$("#aOrp").val();
				if(apSpan=="奖品不存在"||apSpan=="礼品包不存在"){
					$("#continueToEdit").attr("disabled",true);
					$("#newlyAdd").attr("disabled",true);
				}else{
					$("#continueToEdit").attr("disabled",false);
					$("#newlyAdd").attr("disabled",false);
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
	
	//验证奖品是否足够
	function isAwardEnough(){
		
		$.ajaxSetup({
	        async: false
	    });
		
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
				flag=false;
				alert(json["result"]);
			}
		};
		$.post(action,param,callback);
		return flag;
	}
	
	//点击三个按钮
	$(function(){
		
		var awardType = "${manualAward.awardType}"//奖品类型  1.礼品包   2.奖品
		if(awardType==1){		
			$("#award").click(function(e){
				//$("#award_Amount").css("display","block");
				$("#apNo").val("");//奖品编号
				/* $("#awardOrPackage").html("");//奖品编号后面的名称 */
				$("#aOrp").val("");//奖品名称  用来向后台传值
				$("#quantity").val("");//奖品份数
				$("#awardamount").val("");//奖品金额
				$("#awardRemark").text("");//子活动颁奖说明
				$("#awardRemark").val(""); //子活动颁奖说明
				
			});
			$("#giftBag").click(function(e){
				//$("#award_Amount").css("display","none");
				var awardno = "${manualAward.awardno}";//奖品编号
				var awardname = "${manualAward.awardname}";//奖品名称
				var quantity = "${manualAward.quantity}";//奖品份数
				var awardamount = "${manualAward.awardamount}";//奖品金额
				var awardremark = "${manualAward.awardremark}";//子活动颁奖说明
				//奖品
				$("#apNo").val(awardno);
				/* $("#awardOrPackage").html(awardname); */
				$("#aOrp").val(awardname);
				$("#quantity").val(quantity);
				$("#awardamount").val(awardamount);
				$("#awardRemark").val(awardremark);
				$("#awardRemark").text(awardremark);
			});
	
		}
		
		if(awardType==2){		
			$("#award").click(function(e){
				//$("#award_Amount").css("display","block");
				var awardno = "${manualAward.awardno}";//奖品编号
				var awardname = "${manualAward.awardname}";//奖品名称
				var quantity = "${manualAward.quantity}";//奖品份数
				var awardamount = "${manualAward.awardamount}";//奖品金额
				var awardremark = "${manualAward.awardremark}";//子活动颁奖说明
				//奖品
				$("#apNo").val(awardno);
				/* $("#awardOrPackage").html(awardname); */
				$("#aOrp").val(awardname);
				$("#quantity").val(quantity);
				$("#awardamount").val(awardamount);
				$("#awardRemark").val(awardremark);
				$("#awardRemark").text(awardremark);
			});
			$("#giftBag").click(function(e){
				$("#award_Amount").css("display","none");
				$("#apNo").val("");//奖品编号
				/* $("#awardOrPackage").html("");//奖品编号后面的名称 */
				$("#aOrp").val("");//奖品名称  用来向后台传值
				$("#quantity").val("");//奖品份数
				$("#awardamount").val("");//奖品金额
				$("#awardRemark").text("");//子活动颁奖说明
				$("#awardRemark").val(""); //子活动颁奖说明
			});
	
		}
		
		/*不通过submit来验证表单*/
		function validateForm(){
			
			return $("#defaultForm").validate({
				rules:{
					businessNo:"required",
					awardno:"required",
					quantity:"required",
					awardamount:"required"
					
				}
			});
		};
		
		$(validateForm());
		
		//点击继续编辑子活动
		$("#continueToEdit").click(function(e){
			
			if(validateForm().form()){
				//奖品是否足够
				var flag=isAwardEnough();
				if(flag){
					if(window.confirm("确定要继续编辑吗？")){
						document.getElementById("defaultForm").action= "${pageContext.request.contextPath}/admin/manual/continueToEditSub.action";
						document.getElementById("defaultForm").submit();
					} 
				}
			}
		}); 
			
		//保存本活动时，事件触发时，自动传入事件对象
		$("#saveSubActivity").click(function(e){
			
			if(validateForm().form()){
			
				if(window.confirm("确定要保存吗？")){
					//奖品是否足够
					var flag=isAwardEnough();
					if(flag){
						var action ="${pageContext.request.contextPath}/admin/manual/saveSubActivity.action";
						var callback = function(data){
							alert(data);
							window.location.href = "${pageContext.request.contextPath }/admin/manual/manualActivityQuery.action";
						};
						$.post(action,$("#defaultForm").serialize(),callback,"json");
					}
				}
			}
		});
		
		//点击新增子活动
		$("#newlyAdd").click(function(e){
			var actno = $("input[name='actno']").val();
			//alert("actno>>>"+actno);
			var businessNo = $("#businessNo").find("option:selected").val();
			var quantity =$("#quantity").val();
			//alert("个人奖品份数》》》"+quantity);
			if(window.confirm("确定新增吗？")){
				//奖品是否足够
				var flag=isAwardEnough();
				if(flag){
					window.location.href = "${pageContext.request.contextPath}/admin/manual/newlyAddActivity.action?actno="+actno+"&businessNo="+businessNo+"&quantity="+quantity;
				}
			}
		});
	
		//删除本子活动
		$("#invalidSubActivity").click(function(e){
			var flage= window.confirm("确定删除吗？");
			if(flage){
				var action = "${pageContext.request.contextPath}/admin/manual/invalidSubActivity.action";
				var actno = $("input[name='actno']").val();
				//alert("actno>>>"+actno);
				var params = {
					"actno":actno
				};
				var callback = function(data){
					alert(data);
					window.location.href="${pageContext.request.contextPath}/admin/manual/manualActivityQuery.action";
				};
				$.post(action,params,callback,"json");
			}
		});
		
		//返回上一页
		$("#return_previous_page").click(function(e){
			var actno = $("input[name='actno']").val();
			//alert("actno>>>"+actno);
			var number = $("input[name='number']").val();
			var subActivityNum = $("input[name='subActivityNum']").val();
			window.location.href = "${pageContext.request.contextPath}/admin/manual/returnPreviousPage.action?actno="+actno+"&number="+number+"&subActivityNum="+subActivityNum;
		});
		
	});
	
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
				//$("#businessName").html(data.split(",")[0]+"(共"+data.split(",")[1]+"人)");
				var str = data.substring(0,data.lastIndexOf(","))+"(共"+data.substring(data.lastIndexOf(",")+1)+"人)";
				$("#businessName").html(str);
			};
			$.post(action,params,callback,"json");
		}else{
			$("#businessName").html("");
		}
	}
	
</script>
<title>手动颁奖活动设置</title>

</head>
<body style="font-family:'微软雅黑'">
<div class="container" id="finishBaseInfo">
<div id="ht"><h2>手动颁奖活动编辑页面</h2></div>
  <div class="col-md-12 column">
  	<form  id="defaultForm" action="${pageContext.request.contextPath}/admin/manual/continueToEditSub.action" method="post" class="form-horizontal">
  		
  		<input type="text" style="visibility:hidden" name="number" value="${number}" class="form-control"/>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" style="line-height: 10px;">子活动编号：</font>
	   		<div class="col-sm-5">
	      		<input type="text" style="width:400px;border-style:none;" readonly="true" name="actno" value="${manualAward.actno}">
	   		</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2">子活动设置人：</font>
			<div class="col-sm-3">
				<input type="text" style="border-style:none;" readonly="true" name="addman" value="${adminuser.username}"/>
			</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
			<font class="control-label col-sm-2" style="line-height:10px;">活动对象选择：</font>
			<div class="col-sm-9">
				<select class="aos" style="border-radius:4px;" id="businessNo" name="businessNo" onchange="active_object_select();">
					<option value="">--请选择--</option>
					<c:forEach items="${snlList}" var="sl">
						<option value="${sl.businessNo}">${sl.businessNo}</option>
					</c:forEach>
				</select>
			</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
			<font class="control-label col-sm-2" style="line-height:10px;">定向单位名称：</font>
			<div class="col-sm-9">
				<font id="businessName" name="businessName"></font>
			</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2" for="inputSuccess3" style="line-height:10px;">奖品选择：</font>
	   		<div class="col-sm-9">
		      	<input type="radio" id="award" name="awardType" value="2"  checked="checked">奖品
				<input type="radio" id="giftBag" name="awardType" value="1">礼品包
	   		</div>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2">奖品/礼品包编号：</font>
	   		<div class="col-sm-5">
	      		<input type="text" id="apNo" onblur="getAwardName();" name="awardno" value="${manualAward.awardno}" class="form-control">
	   		</div>
	      	<%-- <span id="awardOrPackage" style="line-height:30px;">${manualAward.awardname}</span> --%>
	      	<input id="aOrp" name="awardname" style="border-style:none;" value="${manualAward.awardname}"/>
  		</div>
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2">奖品/礼品包份数：</font>
	   		<div class="col-sm-5">
	      		<input type="text" id="quantity" name="quantity" class="form-control" value="${manualAward.quantity}">
	   		</div>
  		</div>
  		
  		
  		<div id="award_Amount" class="form-group  has-feedback" style="display:none;">
	   		<font class="control-label col-sm-2">奖品金额：</font>
	   		<div class="col-sm-5">
	      		<input type="text" name="awardamount" class="form-control" value="${manualAward.awardamount}" id="awardamount">
	   		</div>
	   		<%-- <select name="awardUnit" id="awardUnit" style="width:20auto;height:32px;font-size: 15px;border-radius:4px;">
    			<option value="">--请选择--</option>
    			<option id="rmb" value="元" <c:if test="${manualAward.awardUnit eq '元'}">selected="selected"</c:if>>元</option>
    			<option id="baifenbi" value="%" <c:if test="${manualAward.awardUnit eq '%'}">selected="selected"</c:if>>%</option>
    			<option value="分" <c:if test="${manualAward.awardUnit eq '分'}">selected="selected"</c:if>>分</option>
    		</select> --%>
  		</div>
  		
  		
  		<div class="form-group  has-feedback">
	   		<font class="control-label col-sm-2">子活动颁奖说明：</font>
	   		<div class="col-sm-8">
	      		<textarea type="text" id="awardRemark" name="awardremark" class="form-control" value="${manualAward.awardremark}">${manualAward.awardremark}</textarea>
	   		</div>
  		</div>
  		
  		<div class="form-group has-success has-feedback" >
  			<label class="control-label col-sm-2"></label>
			<div id="continue_edit" class="col-sm-3">
	    		<button type="button" id="continueToEdit" class="btn btn-default">继续编辑子活动</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${number}/${subActivityNum}个
			</div>
			<div id="newly_add" class="col-sm-3" style="display:none;">
	    		<button type="button" id="newlyAdd" class="btn btn-default">新增子活动</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${number}/${subActivityNum}个			   		
			</div>
			<div class="col-sm-2">
	   			<button type="button" id="saveSubActivity" class="btn btn-default">保存本活动</button>
			</div>
			<div class="col-sm-2">
	   			<button type="button" id="invalidSubActivity" class="btn btn-default">删除本子活动</button>
			</div>
			<div class="col-sm-2">
	   			<button type="button" id="return_previous_page" class="btn btn-default">返回上一页</button>
			</div>
			<input type="hidden" name="subActivityNum" value="${subActivityNum}"/>
		</div>
  	</form>
  </div>
  
</body>
</html>