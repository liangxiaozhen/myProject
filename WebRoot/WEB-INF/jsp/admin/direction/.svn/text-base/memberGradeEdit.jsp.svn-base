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
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
	.aos{ 
		width:90px;		
		height:32px;
	}
</style>
<script type="text/javascript">

	/* 系统业务相关 */
	function select_sbt(){
		var sbt = $("#sbt").find("option:selected").val();//系统业务相关下拉框的value值
		//alert("value值》》》"+sbt);
		var sbtText = $("#sbt").find("option:selected").text();//系统业务相关下拉框的text值
		//alert("text值》》》"+sbtText);
		var systemBusType = $("#systemBusType").val();//系统业务展示的value值
		//alert("添加前>>>"+systemBusType);
		var arr= new Array();
	    var str = systemBusType;
	    arr = str.split(",");
	    var bool=false;//判断是否存在
	  	//3.遍历数组进行比较 
	    for(var i=0; i<arr.length; i++){
	    	if(sbt==arr[i]){
	    		bool = false;
	    		break;
	    	}
	    }
	    if(bool){
	    	alert("已添加");
	    }else{
	    	if(sbt!=0){
		    	if(document.getElementById("systemBusType").value !=""){
		    		document.getElementById("systemBusType").value =document.getElementById("systemBusType").value +","+sbt;
		    		//alert("添加后>>>"+document.getElementById("systemBusType").value);
		    	}else{
		    		document.getElementById("systemBusType").value = sbt;
		    		//alert("添加后>>>"+document.getElementById("systemBusType").value);
		    	}
		    	document.getElementById("sbt_show").style.display="block";
		    	var html =  "<div id='sbt_text'  style='display:inline;'>"+
			    			"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteSbt(this)' value='"+sbt+"' style='margin-right:15px;'>"+
	            			""+sbtText+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
	            			"</button></div>";       
	 			$("#sbt_show").append(html);
		    }
	    }
	    if(document.getElementById("systemBusType").value!=""){
	    	$("#sb").html("");
	    }
	}
	//删除系统业务相关按钮
	function deleteSbt(bt){
		var systemBusType = $("#systemBusType").val();//系统业务展示的value值
		//alert("删除前》》》"+systemBusType);
		var btValue=bt.value;//按钮的value值
		//alert("按钮值》》》"+btValue);
		$(bt).parent().remove();//删除按钮
		//按钮删除后需要移除系统业务展示的部分value值
		var arr= new Array();
		var str = systemBusType;
		arr = str.split(",");
		systemBusType="";
		for(var i=0; i<arr.length; i++){
			if(btValue==arr[i]){
				arr.splice(i,1);
			}
		}
		for(var j=0;j<arr.length;j++){
			if(j>0){
				systemBusType = systemBusType+","+arr[j];
			}else{
				systemBusType=arr[j];
			}
		}
		document.getElementById("systemBusType").value=systemBusType;
		//alert("删除后》》》"+document.getElementById("systemBusType").value);
		if(systemBusType==""){
			 document.getElementById("sbt_show").style.display="none";
		}
	}
	
	/* 短信通知相关 */
	function select_ste(){
		var ste = $("#ste").find("option:selected").val();//短信通知相关下拉框的value值
		var steText =$("#ste").find("option:selected").text();//短信通知相关下拉框的text值
		var smsType = $("#smsType").val();//短信通知相关展示的value值
		var arr= new Array();
		arr = smsType.split(",");
		var flag = false;
		for(var i=0;i<arr.length;i++){
			if(ste==arr[i]){
				flag = true;
				break;
			}
		}
		if(flag){
			alert("已添加");
		}else{
			if(ste!=0){
				if(document.getElementById("smsType").value!=""){
					document.getElementById("smsType").value = document.getElementById("smsType").value + ","+ste;
				}else{
					document.getElementById("smsType").value = ste;
				}
				document.getElementById("ste_show").style.display="block";
				var html =  "<div id='ste_text'  style='display:inline;'>"+
			    			 "&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteSte(this)' value='"+ste+"' style='margin-right:15px;'>"+
		           			 ""+steText+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
		            		 "</button></div>";       
      			$("#ste_show").append(html);
			}
		}
		if(document.getElementById("smsType").value!=""){
			$("#mn").html("");
		}
	}
	//删除短信通知相关按钮
	function deleteSte(bt){
		var smsType =$("#smsType").val();//短信通知展示的value值
		//alert("删除前》》》"+smsType);
		var btValue=bt.value;//按钮的value值
		//alert("按钮值》》》"+btValue);
		$(bt).parent().remove();//删除按钮
		//按钮删除后需要移除系统业务展示的部分value值
		var arr= new Array();
		var str = smsType;
		arr = str.split(",");
		smsType="";
		for(var i=0; i<arr.length; i++){
			if(btValue==arr[i]){
				arr.splice(i,1);
			}
		}
		for(var j=0;j<arr.length;j++){
			if(j>0){
				smsType = smsType+","+arr[j];
			}else{
				smsType=arr[j];
			}
		}
		document.getElementById("smsType").value=smsType;
		//alert("删除后》》》"+document.getElementById("smsType").value);
		if(smsType==""){
			 document.getElementById("ste_show").style.display="none";
		}
	}
	
	//添加会员等级
	function select_amg(){
		var amg = $("#add_member_grade").find("option:selected").val();//添加会员等级，下拉框的value值
		var amgText =$("#add_member_grade").find("option:selected").text();//添加会员等级，下拉框的text值
		var memberGrade_show = $("#memberGrade_show").val();//添加会员展示的value值
		var arr = new Array();
		arr = memberGrade_show.split(",");
		var flag = false;
		for(var i=0;i<arr.length;i++){
			if(amg == arr[i]){
				flag = true;
				break;
			}
		}
		if(flag){
			alert("已添加");
		}else{
			if(amg!=-1){
				if(document.getElementById("memberGrade_show").value!=""){
					document.getElementById("memberGrade_show").value = document.getElementById("memberGrade_show").value + "," + amg;
				}else{
					document.getElementById("memberGrade_show").value = amg;
				}
				$("#amg_show").css("display","block");
				var html =  "<div id='amg'  style='display:inline; '>"+
		    				"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteAmg(this)' value='"+amg+"' style='margin-right:15px;'>"+
    						""+amgText+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
     						"</button></div>";       
				$("#amg_show").append(html);
			}
		}
	}
	
	//删除添加会员等级按钮
	function deleteAmg(bt){
		var memberGrade_show = $("#memberGrade_show").val();//添加会员展示的value值
		//alert("删除按钮之前>>>"+memberGrade_show);
		var btValue = bt.value;//按钮value值
		$(bt).parent().remove();//删除按钮
		var arr = new Array();
		arr = memberGrade_show.split(",");
		memberGrade_show ="";
		for(var i=0;i<arr.length;i++){
			if(btValue == arr[i]){
				arr.splice(i,1);
			}
		}
		for(var i=0;i<arr.length;i++){
			if(i>0){
				memberGrade_show = memberGrade_show + "," + arr[i];
			}else{
				memberGrade_show = arr[i];
			}
		}
		document.getElementById("memberGrade_show").value = memberGrade_show;
		//alert("删除按钮之后>>>"+document.getElementById("memberGrade_show").value);
		if(document.getElementById("memberGrade_show").value == ""){
			$("#amg_show").css("display","none");
		}
	}
	
	//添加个人  判断该用户是否存在，其它不判断
	function import_person(){
		var action = "<%=basePath%>admin/nameList/isPersonExist.action";
		var importPerson = $("#add_person").val();//添加个人时，管理员输入的用户名
		//alert("importPerson>>>"+importPerson);
		var params = {
			"importPerson":importPerson	
		};
		var callback = function(data){
			var json = $.parseJSON(data);
			alert(json);
			if(json=="添加成功"){
				//遍历已有的个人展示文本框，防止给同一个用户多次奖品
				var aps = $("#add_person_show").val();//添加个人展示的val值
				var arr = new Array();
				arr = aps.split(",");
				var flag = true;
				for(var i=0;i<arr.length;i++){
					if(importPerson==arr[i]){
						alert("该用户已添加");
						flag = false;
						break;
					}
				}
				if(flag){
					document.getElementById("ap_show").style.display="block";
					if(document.getElementById("add_person_show").value!=""){
						document.getElementById("add_person_show").value = document.getElementById("add_person_show").value + "," +importPerson;
					}else{
						document.getElementById("add_person_show").value = importPerson;
					}
					var html =  "<div id='ap'  style='display:inline;'>"+
			    				"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteAps(this)' value='"+importPerson+"' style='margin-right:15px;'>"+
    			    			""+importPerson+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
     			    			"</button></div>";       
					$("#ap_show").append(html);
					//清除输入框中的值
					document.getElementById("add_person").value="";
				}
			}
		};
		$.post(action,params,callback);
		
	}
	
	//删除 添加个人展示按钮
	function deleteAps(bt){
		var aps = $("#add_person_show").val();//添加个人展示的val值
		//alert("aps>>>"+aps);
		var btValue = bt.value;//按钮值
		//alert("btValue>>>"+btValue);
		$(bt).parent().remove();
		var arr = new Array();
		arr = aps.split(",");
		aps = "";
		for(var i=0;i<arr.length;i++){
			//alert("arr[i]>>>"+arr[i]);
			if(btValue == arr[i]){
				arr.splice(i,1);
			}
		}
		for(var i=0;i<arr.length;i++){
			if(i>0){
				aps = aps + ","+arr[i];
			}else{
				aps = arr[i];
			}
		}
		document.getElementById("add_person_show").value=aps;
		if(aps==""){
			$("#ap_show").css("display","none");
		}
	}
	
	//排除大名单
	function select_dbl(){
		var dbl = $("#debar_big_list").find("option:selected").val();//添加排除大名单的value值
		var dblText = $("#debar_big_list").find("option:selected").text();//添加排除大名单的text值
		var debarBigList_show = $("#debarBigList_show").val();//排除大名单展示的value值
		
		var array = new Array();
		array = debarBigList_show.split(",");
		var bool= false;
		for(var i=0; i<array.length;i++){
			if(dbl == array[i]){
				bool = true;
				break;
			}
		}
		if(bool){
			alert("已添加");
		}else{
			if(dbl!=0){
				if(document.getElementById("debarBigList_show").value !=""){
					document.getElementById("debarBigList_show").value = document.getElementById("debarBigList_show").value + "," +dbl;
				}else{
					document.getElementById("debarBigList_show").value = dbl;
				}
				document.getElementById("dbl_show").style.display="block";
		   		var html =  "<div id='dbl'  style='display:inline;'>"+
	  				    	"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteDbl(this)' value='"+dbl+"' style='margin-right:15px;'>"+
			            	""+dblText+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
			            	"</button></div>";       
	           $("#dbl_show").append(html);
			}
		}
		
	}
	//删除排除大名单按钮
	function deleteDbl(bt){
		var debarBigList_show = $("#debarBigList_show").val();//排除大名单展示的value值
		var btValue = bt.value;//按钮的value值
		$(bt).parent().remove();//删除按钮
		var arr = new Array();
		arr = debarBigList_show.split(",");
		debarBigList_show="";
		for(var i=0;i<arr.length;i++){
			if(btValue == arr[i]){
				arr.splice(i,1);
			}
		}
		for(var j=0;j<arr.length;j++){
			if(j>0){
				debarBigList_show = debarBigList_show + "," + arr[j];
			}else{
				debarBigList_show = arr[j];
			}
		}
		document.getElementById("debarBigList_show").value = debarBigList_show;
		if(debarBigList_show ==""){
			document.getElementById("dbl_show").style.display = "none";
		}
	}
	
	//排除小名单
	function select_dsl1(){
		//排除小名单不能在 排除大名单里面
		var debarBigList_show = $("#debarBigList_show").val();//排除大名单展示的value值
		//alert("debarBigList_show>>>"+debarBigList_show);
		var dsl1 = $("#debar_small_list1").find("option:selected").val();//排除小名单时第一个下拉框选中的value值
		//alert("dsl1>>>"+dsl1);
		var arr = new Array();
		arr = debarBigList_show.split(",");
		var flag = false;
		for(var i =0;i<arr.length;i++){
			if(dsl1 == arr[i]){
				flag = true;
				break;
			}
		}
		if(flag){
			alert("排除大名单中已存在");
		}else{
			var action = "<%=basePath%>admin/nameList/getNameList.action";
			var addOrDebarText = $("#debar_small_list1").find("option:selected").text();//排除小名单时，选中的大名单text值
			//alert("addOrDebarText>>>"+addOrDebarText);
			var params ={
				"addOrDebarText":addOrDebarText
			};
			var callback = function(data){
				var dsl2 = document.getElementById("debar_small_list2");
				dsl2.options[0] = new Option("--请选择--", '0',true);//清空select后需要重新补上
				for(var i=0;i<data.length;i++){
					//alert("data[i].name>>>"+data[i].name);
					dsl2.options[dsl2.length] = new Option(data[i].name,data[i].nameno);
				}
			};
			document.getElementById("debar_small_list2").length = 0;//清空select
			$.post(action,params,callback,"json");
		}
	}
	//排除小名单,并展示出来
	function select_dsl2(){
		var dsl2 = $("#debar_small_list2").find("option:selected").val();//排除小名单第二个下拉框的value值
		//alert("dsl2>>>"+dsl2)
		var dsl2Text = $("#debar_small_list2").find("option:selected").text();//排除小名单第二个下拉框的text值
		var debarSmallList_show = $("#debarSmallList_show").val();//排除小名单展示的value值
		var arr = new Array();
		arr = debarSmallList_show.split(",");
		var flag = false;
		for(var i =0;i<arr.length;i++){
			if(dsl2 == arr[i]){
				flag = true;
				break;
			}
		}
		if(flag){
			alert("已添加");
		}else{
			if(dsl2!=0){
				if(document.getElementById("debarSmallList_show").value !=""){
					document.getElementById("debarSmallList_show").value = document.getElementById("debarSmallList_show").value + "," + dsl2;
				}else{
					document.getElementById("debarSmallList_show").value = dsl2;
				}
				document.getElementById("dsl_show").style.display = "block";
				var html =  "<div id='dsl'  style='display:inline; '>"+
							"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteDsl(this)' value='"+dsl2+"' style='margin-right:15px;'>"+
	        				""+dsl2Text+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
	        				"</button></div>";       
				$("#dsl_show").append(html);
			}
		}
	}
	//删除排除小名单按钮
	function deleteDsl(bt){
		var debarSmallList_show = $("#debarSmallList_show").val();//排除小名单展示的value值
		var btValue = bt.value;//按钮的value值
		$(bt).parent().remove();//删除按钮
		var arr = new Array();
		arr = debarSmallList_show.split(",");
		debarSmallList_show = "";
		for(var i = 0;i<arr.length ; i++){
			if(btValue == arr[i]){
				arr.splice(i,1);
			}
		}
		for(var i =0;i<arr.length ;i++){
			if(i>0){
				debarSmallList_show = debarSmallList_show + "," + arr[i];
			}else{
				debarSmallList_show = arr[i];
			}
		}
		document.getElementById("debarSmallList_show").value = debarSmallList_show;
		if(debarSmallList_show == ""){
			$("#dsl_show").css("display","none");
		}
	}
	
	
	
	//排除个人
	function export_person(){
		var importPerson = $("#debar_person").val();//排除个人时，管理员输入的用户名
		//alert("importPerson>>>"+importPerson);
		var aps = $("#add_person_show").val();//添加个人展示的val值
		var arr = new Array();
		arr= aps.split(",");
		var bool = true;
		for(var i=0;i<arr.length;i++){
			if(importPerson== arr[i]){
				alert("添加个人里已存在，不可又排除");
				bool=false;
				break;
			}
		}
		if(bool){
			var action = "<%=basePath%>admin/nameList/isPersonExist.action";
			var params = {
				"importPerson":importPerson	
			};
			var callback = function(data){
				var json = $.parseJSON(data);
				alert(json);
				if(json=="添加成功"){
					//遍历已有的个人展示文本框，防止给同一个用户多次奖品
					var dps = $("#debar_person_show").val();//排除个人展示的val值
					var arr = new Array();
					arr = dps.split(",");
					var flag = true;
					for(var i=0;i<arr.length;i++){
						if(importPerson==arr[i]){
							alert("该用户已添加");
							flag = false;
							break;
						}
					}
					if(flag){
						document.getElementById("dp_show").style.display="block";
						if(document.getElementById("debar_person_show").value!=""){
							document.getElementById("debar_person_show").value = document.getElementById("debar_person_show").value + "," +importPerson;
						}else{
							document.getElementById("debar_person_show").value = importPerson;
						}
						var html =  "<div id='dp'  style='display:inline;'>"+
				    				"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteDps(this)' value='"+importPerson+"' style='margin-right:15px;'>"+
	    			    			""+importPerson+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
	     			    			"</button></div>";       
						$("#dp_show").append(html);
						//清除输入框中的值
						document.getElementById("debar_person").value="";
					}
				}
			};
			$.post(action,params,callback);
		}
	}
	
	//删除 删除个人展示按钮
	function deleteDps(bt){
		var dps = $("#debar_person_show").val();//排除个人展示的val值
		//alert("dps>>>"+dps);
		var btValue = bt.value;//按钮值
		//alert("btValue>>>"+btValue);
		$(bt).parent().remove();
		var arr = new Array();
		arr = dps.split(",");
		dps = "";
		for(var i=0;i<arr.length;i++){
			//alert("arr[i]>>>"+arr[i]);
			if(btValue == arr[i]){
				arr.splice(i,1);
			}
		}
		for(var i=0;i<arr.length;i++){
			if(i>0){
				dps = dps + ","+arr[i];
			}else{
				dps = arr[i];
			}
		}
		document.getElementById("debar_person_show").value=dps;
		if(dps==""){
			$("#dp_show").css("display","none");
		}
	}
	
	$(function(){
		var generateType = "${snl.generateType}";
		if(generateType==1){
			$("#generateType").val("手动生成");
		}else if(generateType==2){
			$("#generateType").val("模板生成");
		}
		
		//判断模板
		var isTemplet = "${snl.isTemplet}";
		if(isTemplet==2){
			$("input[name='isTemplet']").removeAttr("checked");
			$("#isTemplet2").prop("checked","checked");
		}
		
		//判断风险名单
		var isRisk = "${isRisk}";
		//alert("isRisk: "+isRisk);
		if(isRisk=="1"){
			$("input[name='isRisk']").removeAttr("checked");
			$("#isRisk1").prop("checked","checked");
		}
		
		//判断业务类型
		var businessType = "${snl.businessType}";
		if(businessType==1){//系统业务
			$("#system_business").css("display","block");
			$("#is_risk").css("display","block");
			$("#message_notice").css("display","none");
			
			/*系统业务*/
			var map1 = "${map1}";
			//alert("map1》》》"+map1);
			var sbtStr = map1.substring(map1.indexOf("{")+1,map1.indexOf("}"));
			//alert("sbtStr>>>"+sbtStr);
			if(sbtStr!=""){
				var arr1 = new Array();
				var arr2 = new Array();
				arr1 = sbtStr.split(",");
				for(var i=0;i<arr1.length;i++){
					//alert("arr1>>>"+arr1[i]);
					arr2 = arr1[i].split("=");
					for(var j=0;j<1;j++){//这个地方不能循环两次（全循环）
						if(document.getElementById("systemBusType").value!=""){
							document.getElementById("systemBusType").value= document.getElementById("systemBusType").value + "," +arr2[0];
						}else{
							document.getElementById("systemBusType").value = arr2[0];
						}
						document.getElementById("sbt_show").style.display="block";
				    	var html =  "<div id='sbt_text'  style='display:inline;'>"+
					    			"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteSbt(this)' value='"+arr2[0]+"' style='margin-right:15px;'>"+
			            			""+arr2[1]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
			            			"</button></div>";       
			 			$("#sbt_show").append(html);
					}
				}
				//alert("系统业务最终的value值》》》"+document.getElementById("systemBusType").value);
			}
			
		}else if(businessType==2){//短信通知
			$("#system_business").css("display","none");
			$("#is_risk").css("display","none");
			$("#message_notice").css("display","block");
			//需要将添加大小名单 会员等级  添加个人隐藏
			//$("#addMemberGrade").css("display","none");
			//$("#addPerson").css("display","none");
			
			/*短信通知*/
			var map2 = "${map2}";
			//alert("map2》》》"+map2);
			var stStr = map2.substring(map2.indexOf("{")+1,map2.indexOf("}"));
			//alert("stStr>>>"+stStr);
			if(stStr!=""){
				var arr1 = new Array();
				var arr2 = new Array();
				arr1 = stStr.split(",");
				for(var i=0;i<arr1.length;i++){
					//alert("arr1>>>"+arr1[i]);
					arr2 = arr1[i].split("=");
					for(var j=0;j<1;j++){
						if(document.getElementById("smsType").value!=""){
							document.getElementById("smsType").value= document.getElementById("smsType").value + "," +arr2[0];
						}else{
							document.getElementById("smsType").value = arr2[0];
						}
						
						document.getElementById("ste_show").style.display="block";
						var html =  "<div id='ste_text'  style='display:inline;'>"+
					    			 "&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteSte(this)' value='"+arr2[0]+"' style='margin-right:15px;'>"+
				           			 ""+arr2[1]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
				            		 "</button></div>";       
		      			$("#ste_show").append(html);
						
					}
				}
				//alert("短信通知最终的value值》》》"+document.getElementById("smsType").value);
			}
		}
		
		
		/* 会员等级 */
		var map3 = "${map3}";
		var memberStr = map3.substring(map3.indexOf("{")+1,map3.indexOf("}"));
		//alert("截取后的会员》》》"+memberStr);
		if(memberStr!=""){
			var arr1 = new Array();
			var arr2 = new Array();
			arr1 = memberStr.split(",");
			for(var i=0;i<arr1.length;i++){
				//alert("arr1[i]>>>"+arr1[i]);
				arr2 = arr1[i].split("=");
				for(var j=0;j<1;j++){
					//alert("arr1[j]>>>"+arr1[j]);
					if(document.getElementById("memberGrade_show").value!=""){
						document.getElementById("memberGrade_show").value = document.getElementById("memberGrade_show").value+ ","+ arr2[0];
						//alert("添加后的会员等级value>>"+document.getElementById("memberGrade_show").value);
					}else{
						document.getElementById("memberGrade_show").value = arr2[0];
						//alert("添加后的会员等级value>>"+document.getElementById("memberGrade_show").value);
					}
					document.getElementById("amg_show").style.display="block";
					$("#amg_show").css("display","block");
					var html =  "<div id='amg'  style='display:inline; '>"+
			    				"&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-default' onclick='deleteAmg(this)' value='"+arr2[0]+"' style='margin-right:15px;'>"+
	    						""+arr2[1]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
	     						"</button></div>";       
					$("#amg_show").append(html);
				}
			}
		}
		
		
		/* 添加个人用户 */
		var set3 = "${set3}";
		//alert("获奖个人用户》》》"+set3);
		var awardIndividualUser=set3.substring(set3.indexOf("[")+1,set3.indexOf("]"));
		//alert("截取后的获奖个人用户》》》"+awardIndividualUser);
		var nameTextgere=$("#add_person_show").val();//获奖个人展示文本框的值
		if(awardIndividualUser!=""){
			document.getElementById("add_person_show").value=awardIndividualUser;
			//alert("添加后的获奖用户》》》"+document.getElementById("textgere1").value);
			document.getElementById("ap_show").style.display="block";
			//将大小名单获奖个人用户按逗号切开
			var arr= new Array();
			arr= awardIndividualUser.split(",");
		    //遍历数组进行比较 
			for(var i=0; i<arr.length; i++){
				var html =  "<div id='ap'  style='display:inline;'>"+
	    					"&nbsp;&nbsp;&nbsp;<button type='button' id='' class='btn btn-default' onclick='deleteAps(this)' value='"+arr[i]+"' style='margin-right:15px;'>"+
     						""+arr[i]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
      						"</button></div>";       
				$("#ap_show").append(html);
			}
		}
		
		/* 排除大名单 */
		var set4 = "${set4}";
		//alert("排除大名单》》》"+set4);
		var exclusionBigList = set4.substring(set4.indexOf("[")+1,set4.indexOf("]"));
		//alert("截取后的排除大名单》》》"+exclusionBigList);
		if(exclusionBigList!=""){
			document.getElementById("debarBigList_show").value=exclusionBigList;
			document.getElementById("dbl_show").style.display="block";
			//将排除大名单按逗号切开
			var arr= new Array();
			arr= exclusionBigList.split(",");
		    //遍历数组进行比较 
			for(var i=0; i<arr.length; i++){
			    var html =  "<div id='dbl'  style='display:inline;'>"+
		  				    "&nbsp;&nbsp;&nbsp;<button type='button' id='' class='btn btn-default' onclick='deleteDbl(this)' value='"+arr[i]+"' style='margin-right:15px;'>"+
				            ""+arr[i]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
				            "</button></div>";       
		        $("#dbl_show").append(html);
			}
		}
		
		/* 排除小名单 */
		var set5 ="${set5}";
		//alert("排除小名单》》》"+set5);
		var excludeSmallList =set5.substring(set5.indexOf("[")+1, set5.indexOf("]"));
		if(excludeSmallList!=""){
			//将排除小名单按逗号切开
			var arr= new Array();
			arr= excludeSmallList.split(",");
			var str = "";
			for(var i=0; i<arr.length; i++){
				str += arr[i].split("-")[0]+",";
				 var html =  "<div id='dsl'  style='display:inline; '>"+
					    	    "&nbsp;&nbsp;&nbsp;<button type='button' id='' class='btn btn-default' onclick='deleteDsl(this)' value='"+arr[i].split("-")[0]+"' style='margin-right:15px;'>"+
			        			""+arr[i].split("-")[1]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
			        		"</button></div>";       
				$("#dsl_show").append(html);
			}
			document.getElementById("debarSmallList_show").value=str.substring(0,str.lastIndexOf(","));
			document.getElementById("dsl_show").style.display="block";
		  /*   //遍历数组进行比较 
			for(var i=0; i<arr.length; i++){
			    var html =  "<div id='dsl'  style='display:inline; '>"+
				    	    "&nbsp;&nbsp;&nbsp;<button type='button' id='' class='btn btn-default' onclick='deleteDsl(this)' value='"+arr[i]+"' style='margin-right:15px;'>"+
		           			""+arr[i]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
		           			"</button></div>";       
	   			$("#dsl_show").append(html);
			} */
		}
		
		/* 排除用户名 */
		var set6 = "${set6}";
		//alert("排除用户名》》》"+set6);
		var excludeIndividualUser=set6.substring(set6.indexOf("[")+1, set6.indexOf("]"));
		//alert("截取后的排除用户名》》》"+excludeIndividualUser);
		if(excludeIndividualUser!=""){
			document.getElementById("debar_person_show").value=excludeIndividualUser;
			document.getElementById("dp_show").style.display="block";
			//将排除个人用户按逗号切开
			var arr= new Array();
			arr= excludeIndividualUser.split(",");
		    //遍历数组进行比较 
			for(var i=0; i<arr.length; i++){
				var html =  "<div id='dp'  style='display:inline;'>"+
 							"&nbsp;&nbsp;&nbsp;<button type='button' id='' class='btn btn-default' onclick='deleteDps(this)' value='"+arr[i]+"' style='margin-right:15px;'>"+
 			   				""+arr[i]+"<span style='color:red' class='glyphicon glyphicon-remove'></span>"+
  			    			"</button></div>";       
   				$("#dp_show").append(html);
			}
		}
	
		//点击返回按钮
		$("#returnBack").click(function(){
			window.location.href = "<%=basePath%>admin/nameList/directionNameQuery.action";
		});
		
		//点击保存按钮
		$("#save_edit").click(function(){
			var action = "<%=basePath%>admin/nameList/saveEditSNL.action";
			var businessName = $("#businessName").val().trim();
			var systemBusTypeStr = $("#systemBusType").val();
			var isRisk = $("input[name='isRisk']:checked").val();
			var smsTypeStr = $("#smsType").val();
			var memberGrade_show = $("#memberGrade_show").val();
			var debarBigList_show = $("#debarBigList_show").val();
			var debarSmallList_show = $("#debarSmallList_show").val();
			var add_person_show = $("#add_person_show").val().trim();
			var debar_person_show = $("#debar_person_show").val().trim();
			var params = {
				"businessName" : businessName,//标题
				"systemBusTypeStr" : systemBusTypeStr,//系统业务展示
				"isRisk":isRisk,//是否为风险名单
				"smsTypeStr" : smsTypeStr,//短信通知展示
				"businessNo" : $("#businessNo").val(),//定向编号
				"addMan" :$("#addMan").val(),//设置人
				"isTemplet" :$("input[name='isTemplet']:checked").val(),//是否为设置模板
				"generateType" : $("#generateType").val(),//生成方式
				"memberGrade_show" : memberGrade_show,//添加会员等级展示
				"debarBigList_show" : debarBigList_show,//排除大名单展示
				"debarSmallList_show" : debarSmallList_show,//排除小名单展示
				"add_person_show" : add_person_show,//添加个人展示
				"debar_person_show" : debar_person_show,//排除个人展示
				"remark" : $("#remark").val()//定向备注
			};
			var callback = function(data){
				alert(data);
				window.location.href="<%=basePath%>admin/nameList/directionNameQuery.action";
			}
			
			var flag = true;
			if(businessName==""||businessName==null){
				flag = false;
				$("#bn").html("");
				$("#bn").html("不能为空");
			}
			if(systemBusTypeStr=="" && smsTypeStr==""){
				flag = false;
				$("#sb").html("");
				$("#sb").html("请选择");
				$("#mn").html("");
				$("#mn").html("请选择");
			}
			if(memberGrade_show == "" && debarBigList_show=="" &&debarSmallList_show=="" && add_person_show=="" && debar_person_show==""){
				flag = false;
				alert("名单和个人都为空");
			}
			if(flag){
				$.post(action,params,callback,"json");
			}
		
		});
		
		//判断定向标题是否唯一
		$("#businessName").blur(function(e){
			var action = "<%=basePath%>admin/nameList/editBnIsOnly.action";
			var businessName = $("#businessName").val().trim();//标题
			//alert("定向标题》》》"+businessName);
			var businessNo = $("#businessNo").val();
			var params= {
				"businessName":businessName,
				"businessNo":businessNo
			};
			var flag = true;
			var callback = function(data){
				if(data=="定向标题已存在"){
					$("#save_edit").attr("disabled",true);
					$("#bn").html("");
					$("#bn").html(data);
					flag = false;
				}else{
					$("#save_edit").attr("disabled",false);
					$("#bn").html("");
				}
			};
			
			if(flag){
				$.post(action,params,callback,"json");
			}
		});
		
	});
</script>
</head>
<body style="font-family:'微软雅黑'">
<div class="container" id="">
<div style="text-align: center;"><h3>根据会员等级设置定向</h3></div>
	<div class="col-md-12 column">
  		<form class="form-horizontal" id="" action="" method="post" >
  		
	  		<div class="form-group  has-feedback">
		   		<font class="control-label col-sm-2">标题：</font>
				<div class="col-sm-8">
					<input type="text" id="businessName" name="businessName" value="${snl.businessName}" class="form-control"/>
				</div>
				<span id="bn" style="color: red;"></span>
	  		</div>
  		
	  		<div id="system_business" class="form-group  has-feedback">
		   		<font class="control-label col-sm-2" style="line-height:10px;">系统业务相关：</font>
		   		<div class="col-sm-9">
						<select class="aos" style="border-radius:4px;" name="sbt" id="sbt" onchange="select_sbt();">
						<option value="0">--请选择--</option>
						<c:forEach var="sby" items="${sbtype}">
							<option value="${sby.key}">${sby.value}</option>
						</c:forEach>
						</select>
						<span id="sb" style="color: red;"></span>
				</div>
	  		</div>
	  		<div class="form-group  has-feedback" id="sbt_show"  style="display: none">
   				<font class="control-label col-sm-2">系统业务展示：</font>
   				<input type="hidden" name="systemBusType" id="systemBusType">
    			<div id="sbt_text"></div>
	   		</div>
  		
  			<div id="is_risk" class="form-group  has-feedback">
   				<font class="control-label col-sm-2" style="line-height: 12px;">是否为风险：</font>
   				<div class="col-sm-9">
	   				<input type="radio" id="isRisk1" name="isRisk" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;
	   				<input type="radio" name="isRisk" value="0" checked="checked"/>否
   				</div>
	   		</div>
  		
	  		<div id="message_notice" class="form-group  has-feedback">
	   			<font class="col-sm-2 control-label" style="line-height:14px;">短信通知相关：</font>
	   			<div class="col-sm-9">
						<select class="aos" style="border-radius:4px;" name="ste" id="ste" onchange="select_ste();">
						<option value="0">--请选择--</option>
						<c:forEach var="smy" items="${smtype}">
							<option value="${smy.key}">${smy.value}</option>
						</c:forEach>
						</select>
						<span id="mn" style="color: red;"></span>
				</div>
	  		</div>
	  		<div class="form-group  has-feedback" id="ste_show"  style="display: none">
   				<font class="control-label col-sm-2">短信通知展示：</font>
   				<input type="hidden" name="smsType" id="smsType">
    			<div id="ste_text"></div>
	   		</div>
  		
	  		<div class="form-group  has-feedback">
		   		<font class="control-label col-sm-2" style="line-height:10px;">定向编号：</font>
				<div class="col-sm-3">
					<input type="text" id="businessNo" name="businessNo" value="${snl.businessNo}" style="width:400px;border-style:none;" readonly/>
				</div>
	  		</div>
  		
	  		<div class="form-group  has-feedback">
		   		<font class="control-label col-sm-2" style="line-height:10px;">设置人：</font>
				<div class="col-sm-3">
					<input type="text" style="border-style:none;" readonly id="addMan" value="${adminuser.username}"/>
				</div>
	  		</div>
  		
	  		<div id="set_time" class="form-group  has-feedback" style="display:none;">
	   			<font class="col-sm-2 control-label" style="line-height:14px;">设置时间：</font>
	   			<div class="date col-sm-9" id="datetimeActSet">
	   				<input type="text" style="border-radius:5px;line-height:28px;width: 200px" id="addTime" name="addTime" value="${snl.addTime}"/> 
	   			</div>
	  		</div>
  		
	  		<div class="form-group  has-feedback">
		   		<font class="control-label col-sm-2">设置为模板：</font>
		   		<div class="col-sm-9">
					<input type="radio" name="isTemplet" value="1" checked="checked">是
					<input type="radio" id="isTemplet2" name="isTemplet" value="2" >否
		   		</div>
	  		</div>
  		
	  		<div class="form-group  has-feedback">
		   		<font class="control-label col-sm-2" style="line-height:10px;">生成方式：</font>
				<div class="col-sm-3">
					<input type="text" id="generateType" name="generateType" value="${snl.generateType}" style="width:400px;border-style:none;" readonly="true"/>
				</div>
	  		</div>
  			
  			<!-- 添加会员等级 -->
   			<div id="addMemberGrade" class="form-group  has-feedback">
	   			<font class="control-label col-sm-2">添加会员等级：</font>
	   			<div class="col-sm-9">
		   			<select class="aos" style="border-radius:4px;" id="add_member_grade" onchange="select_amg();">
			      		<option value="-1">--请选择--</option>
			      		<%-- <c:forEach var="map" items="${map}">
			      			<option value="${map.key}">${map.value}</option>
			      		</c:forEach> --%>
			      		<c:forEach var="m_" items="${map_}">
							<option value="${m_.value}">
								<c:forEach var="m" items="${map}" >
									<c:if test="${m.key == m_.value}">${m.value}</c:if>
								</c:forEach>
							</option>
						</c:forEach>
		      		</select>
	      		</div>
   			</div>
	   		<div class="form-group has-feedback" id="amg_show" style="display: none">
	   			<font class="control-label col-sm-2">添加会员展示：</font>
	   			<input type="hidden" name="memberGrade_show" id="memberGrade_show">
	    		<div id="amg"></div>
	   		</div>
  			
  			<!-- 添加个人 -->
	   		<div id="addPerson" class="form-group  has-feedback" id="listAdd">
		   		<font class="control-label col-sm-2">添加个人：</font>
		   		<div class="col-sm-5">
		   			<input type="text" class="form-control" id="add_person" >
		      	</div>
		      	<div class="col-sm-4">
		   			<button type="button" onclick="import_person();">新增<span class="glyphicon glyphicon-pencil"></span></button>
		      	</div>
	   		</div>
	   		<div class="form-group  has-feedback" id="ap_show" style="display:none ">
	   			 <font class="control-label col-sm-2">添加个人展示：</font>
	   			 <input type="hidden" name="add_person_show" id="add_person_show">
	   			 <div id="ap"></div>
	   		</div>
	  	
	   		<!-- 排除大名单 -->
	   		<div id="debarBigList" class="form-group  has-feedback" style="display:block">
		   		<font class="control-label col-sm-2" style="line-height:10px;">排除大名单：</font>
		   		<div class="col-sm-9">
			   		<select class="aos" style="border-radius:4px;" id="debar_big_list" onchange="select_dbl();">
						<option value="0" selected="selected">--请选择--</option>
						<c:forEach items="${removeNames}" var="rn">
			      			<option value="${rn.nametype}">${rn.nametype}</option>
			      		</c:forEach>
					</select>
				</div>
  			</div>
	  		<div class="form-group  has-feedback" id="dbl_show" style="display:none ">
	   			 <font class="control-label col-sm-2" style="line-height:10px;">排除大名单展示：</font>
	   			 <input type="hidden" name="debarBigList_show" id="debarBigList_show">
	   			 <div id="dbl" ></div>
	   		</div>
	   		
  			<!-- 排除小名单 -->
	   		<div id="debarSmallList" class="form-group  has-feedback">
		   		<font class="control-label col-sm-2" style="line-height:10px;">排除小名单：</font>
		   		<div class="col-sm-9">
			   		<select class="aos" style="border-radius:4px;" id="debar_small_list1" onchange="select_dsl1();">
				      	<option value="0">--请选择--</option>
				      	<c:forEach items="${removeNames}" var="rn">
				      		<option value="${rn.nametype}">${rn.nametype}</option>
				      	</c:forEach>
			      	</select>
			      	<select class="aos" style="border-radius:4px;" id="debar_small_list2" onchange="select_dsl2();">
			      		<option value="0">--请选择--</option>
			      	</select>
		      	</div>
	   		</div>
	   		<div id="dsl_show" class="form-group  has-feedback" style="display: none">
		   		<font class="control-label col-sm-2">排除小名单展示：</font>
		   		<input type="hidden" name="debarSmallList_show" id="debarSmallList_show">
		    	<div id="dsl"></div>
	   		</div> 
	   		
   			<!-- 排除个人 -->
	   		<div class="form-group  has-feedback" >
		   		<font class="control-label col-sm-2">排除个人：</font>
		   		<div class="col-sm-5">
		   			<input type="text" id="debar_person" class="form-control">
		      	</div>
		      	<div class="col-sm-4">
		   			<button type="button" onclick="export_person();">新增<span class="glyphicon glyphicon-pencil"></span></button>
		      	</div>
	   		</div>
	   		<div class="form-group  has-feedback" id="dp_show" style="display:none ">
	   			 <font class="control-label col-sm-2">排除个人展示：</font>
	   			 <input type="hidden" name="debar_person_show" id="debar_person_show">
	   			 <div id="dp" ></div>
	   		</div>
   		
	  		<div class="form-group  has-feedback">
		   		<font class="control-label col-sm-2">定向备注：</font>
		   		<div class="col-sm-8">
		      		<textarea type="text" id="remark" name="remark" class="form-control" value="${snl.remark}">${snl.remark}</textarea>
		   		</div>
	  		</div>
  		
	  		<div class="form-group has-success has-feedback" >
	  			<label class="control-label col-sm-2"></label>
				
				<div class="col-sm-3">
		   			<button type="button" id="save_edit" class="btn btn-default">保存</button>
				</div>
				<div class="col-sm-3">
		   			<button type="button" id="returnBack" class="btn btn-default">返回</button>
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
					<h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign"></span>提示：活动保存成功</h4>
				</div>
				<div class="modal-body" id="saveModal-body"></div>
				<div class="modal-footer">
					<button type="button" onclick="closeDownActivity();" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
  
</body>
</html>