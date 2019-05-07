<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>	
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">var base_path="<%=basePath%>"</script>
<script type="text/javascript" src="calendar/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/award/updateAward.js"></script>
<script type="text/javascript">

	//当body加载时
	function load(){
		
		/* //先隐藏加息对象
		var a=${award.attribute};
		if(a==6){
			$("#rateTarget").css('display','block');
		} */
		
		//定向编号   定向标题
		var businessName = "${snl.businessName}";//定向名单  标题
		var businessNo = "${snl.businessNo}";//定向名单编号
		var bn = document.getElementById("businessNo");
		for(var i=0;i<bn.length;i++){
			if(businessNo == bn[i].value){
				$("option[value="+businessNo+"]").attr("selected","selected");
				$("#bn_show").css("display","block");
				$("#businessName").html(businessName);
				break;
			}
		}
		
		//指定的标号
		var tiMap = "${tiMap}";
		var tiMapStr = tiMap.substring(tiMap.indexOf("{")+1,tiMap.indexOf("}"));
		//alert("截取后的标号》》》"+tiMapStr);
		if(tiMapStr!=""){
			/* $('input[name="bid_type"]').removeAttr('checked'); */
			$('input[name="bid_type"][value="1"]').attr("checked","checked");//指定的标选中
			$("#appoint").css("display","block");
			$("#unappoint").css("display","none");//未指定的标隐藏 
			
			var arr1 = new Array();
			var arr2 = new Array();
			arr1 = tiMapStr.split(",");
			for(var i=0;i<arr1.length;i++){
				//alert("arr1[i]>>>"+arr1[i]);
				arr2=arr1[i].split("=");
				for(var j=0;j<1;j++){
					//alert("arr2[j]>>>"+arr2[j]);
					$("#bid_name_show").css("display","block");
					var html = '<button type="button" name="bid_bt" class="btn btn-default"  onclick="deleteBns(this);" style="margin-right: 15px;">'+
								arr2[1]+'<span style="color:red" class="glyphicon glyphicon-remove"></span><input type="hidden" name="specialtnos" value="'+arr2[0]+'"/></button>'
					$("#bid_name").append(html);
				}
			}
		}else{
			/* $('input[name="bid_type"]').removeAttr('checked'); */
			$('input[name="bid_type"][value="2"]').attr("checked","checked");//指定的标选中
			$("#unappoint").css("display","block");//未指定的标隐藏
			$("#appoint").css("display","none");
		}
		
		//添加指定的标号
		$("#add_bid_number").click(function(){
			
			var flag = true;
			var action ="${pageContext.request.contextPath}/admin/award/getTNameByTNo.action";
			//获取输入的标号
			var specialtno = $("#appointed_bid").val();//管理员输入的标号
			//alert("标号》》》"+specialtno);
			var tbArr=  new Array();
			$('input[name="tattribute"]:checked').each(function(){//投标的属性限制
				tbArr.push($(this).val());
			});
						
			//需要获取该标号的名称
			var params = {
				"specialtno":specialtno,
				"tbArrJson":JSON.stringify(tbArr)
			}
			var callback = function(data){
				
				if(data=="该标号错误"){
					flag = false;
					alert(data);
				}
				
				//遍历添加的标号的名称
				$("input[name='specialtnos']").each(function(){
					if($(this).val()==specialtno){
						flag = false;
						alert("该标号已添加");
					}
				});
				
			 	if(flag){
					//该标号存在
					$("#bid_name_show").css("display","block");
					var html = '<button type="button" name="bid_bt" class="btn btn-default"  onclick="deleteBns(this);" style="margin-right: 15px;">'+
								data+'<span style="color:red" class="glyphicon glyphicon-remove"></span><input type="hidden" name="specialtnos" value="'+specialtno+'"/></button>'
								
					$("#bid_name").append(html);
					$("#appointed_bid").val("");	
				} 
			}
			$.post(action,params,callback,"json");
		});
		
	}

	//删除指定标号的添加按钮
	function deleteBns(bt){
		bt.remove();
		if($("button[name='bid_bt']").length==0){
			$("#bid_name_show").css("display","none");
		}
	}
	
	/* function getAttributes(){
		var atype=$("#atype2").val();
		var url="${pageContext.request.contextPath}/admin/award/findAttributeDataByAtype.action";
		var callback=function(data){
			var data=eval("("+data+")");
			   document.getElementById("attribute2").options.length=1;
			   $.each(data,function(i,n){
				  	$("#attribute2").append("<option value="+i+">"+n+"</option>");
			   });		   
		};
		var data={
			"atype":atype
		};
		$.post(url,data,callback);
	} */
	
	//返回列表
	function gotoAwardList(pageNum){
		window.location.href="${pageContext.request.contextPath }/admin/award/selectAwardByCondition.action?pageNum="+pageNum;
	}
	
	//定向名单选择   获取定向标题的名称
	function active_object_select(){
		var businessNo = $("#businessNo").find("option:selected").val();
		if(businessNo==0){
			$("#bn").html("请选择");
			$("#bn_show").css("display","none");
		}else{
			$("#bn").html("");
			var action = "${pageContext.request.contextPath}/admin/manual/activeObjectSelect.action";
			//alert("businessNo>>>"+businessNo);
			if(businessNo!=0){
				$("#bn_show").css("display","block");
				var params = { 
					"businessNo":businessNo	
				};
				var callback = function(data){
					//var str = data.substring(0,data.lastIndexOf(","))+"(共"+data.substring(data.lastIndexOf(",")+1)+"人)";
					var str = data.substring(0,data.lastIndexOf(","))
					$("#businessName").html(str);
				};
				$.post(action,params,callback,"json");
			}else{
				$("#businessName").html("");
			}
		}
	}
	
/* 	function select_attribute(obj){
		
		$('input[name="cashorvoucher"]').val("");
		$("#awardUnit").removeAttr("selected");
		document.getElementById("awardUnit").options[0].selected = true;
		 
		var attribute =$("#attribute2").val();
		 if(attribute==6){
			//$("#rateTarget").css('display','block');
			$("input[name='raiseIntObject'][value='1']").attr("checked","checked");
		}
	} */	
	
	/*选择标的类型*/
	function select_bt(e){
		
		var bidValue = e.value;//radio的value值
		if(bidValue==1){
			$("#appoint").css("display","block");
			$("#unappoint").css("display","none");
			//需要将未指定的标的参数清除
			$('input[name="tattribute"]').removeAttr("checked");
			$('input[name="tdayLimitl"]').val("");
			$('input[name="tdayrestrict"]').val("");
			$('input[name="tmlrrestrict"]').val("");
			$('[name="tmhrrestrict"]').val("");
			//恢复select的值
			var objs = $('select[name="trtype"]');
	        for (var i = 0; i < objs.length; i++) {
	            with (objs[i]) {
	                value = "";
	            }
	        }
		}else if(bidValue==2){
			$("#appoint").css("display","none");
			$("#unappoint").css("display","block");
			$('button[name="bid_bt"]').remove();
			if($("button[name='bid_bt']").length==0){
				$("#bid_name_show").css("display","none");
			}
		}
	}
	
</script>
	<style type="text/css">
	#id{
		margin:40px;
	} 
    hr{
		margin: 5px;
	} 
</style>

</head>
<body  style="font-family:'微软雅黑';" onload="load();">
<div id="id"></div>
<form id="updateAwardForm" class="form-horizontal" action="${pageContext.request.contextPath}/admin/award/updateAward.action" method="post">
<input type="hidden" name="id"  value="${award.id }">
<input type="hidden" name="pageNum" value="${pageNum}"/>

	<div class="form-group  has-feedback" >
		<div class="col-md-3 col-md-offset-1" style="margin-top: 1auto;margin-bottom: 1auto;">
			<font size="4">基本信息</font>
		</div>
	</div>	
		
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">奖品编号：</font><input type="text" name="ano" value="${award.ano}" style="width:20auto;height:25px;font-size: 15px;border-style:none;"/>
		</div>
	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1 ">
				<font class="col-md-3 text-right" style="line-height:25px;">奖品名称：</font> <input type="text" name="aname" value="${award.aname }" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;"/>
		</div>
	</div>
		
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
    		<font class="col-md-3 text-right" style="line-height:25px;border-radius:4px;">奖品类型：</font>
    		<c:forEach items="${atype_map }" var="aty">
    			<c:choose>
	    			<c:when test="${award.atype==aty.key }">
	    				${aty.value }
	    			</c:when>
    			</c:choose>
    		</c:forEach>
    		<!-- 奖品类型不容许修改 -->
			<%-- <select name="atype" id="atype2" onchange="getAttributes();" style="border-radius:4px;border:1px solid #999999;">
				<option value="">--请选择--</option>
	    		<c:forEach items="${atype_map }" var="aty">
	    			<c:choose>
		    			<c:when test="${award.atype==aty.key }">
		    				<option selected="selected" value="${aty.key }">${aty.value }</option>
		    			</c:when>
		    			<c:otherwise>
		    				<option value="${aty.key }">${aty.value }</option>
		    			</c:otherwise>
	    			</c:choose>
    			</c:forEach>
  			</select> --%>
		</div>
	</div>	

	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1 ">
			<font class="col-md-3 text-right" style="line-height:25px;border-radius:4px;">奖品属性：</font>
			<c:forEach items="${attributeData_map }" var="attri">
   				<c:choose>
   					<c:when test="${award.attribute==attri.key }">
   						${attri.value }
   					</c:when>
   				</c:choose>
	   		</c:forEach>
			<!-- 奖品属性不容许修改 --> 
			<%-- <select name="attribute" id="attribute2" onchange="select_attribute(this);" style="width:20auto;height:25px;font-size: 15px;border-radius:4px;border:1px solid #999999;">
   				<option value="">请选择</option>
	   			<c:forEach items="${attributeData_map }" var="attri">
	   				<c:choose>
	   					<c:when test="${award.attribute==attri.key }">
	   						<option value="${attri.key }" selected="selected">${attri.value }</option>
	   					</c:when>
	   					<c:otherwise>
	   						<option value="${attri.key }">${attri.value }</option>
	   					</c:otherwise>
	   				</c:choose>
	   			</c:forEach>
   			</select> --%>
		</div>
	</div>

	<!-- <div id="rateTarget" class="form-group  has-feedback"  style="line-height: auto;display:none;">
		<div class="col-md-8 col-md-offset-1 ">
			<font class="col-md-3 text-right" style="line-height:25px;border-radius:4px;">加息对象：</font> 
			<input type="checkbox" name="raiseIntObject" value="1"/>真金 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input type="checkbox" name="raiseIntObject" value="2"/>类现金 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input type="checkbox" name="raiseIntObject" value="3"/>假现金 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<script type="text/javascript">
	    	
	    		var cashSet ="${cashSet}";
    			$.each(JSON.parse(cashSet),function(index,value){
    				 $("input[name='raiseIntObject']").each(function(){
    					 if($(this).val() == value){
    						 $(this).attr("checked",true);
    					} 
    				}); 
    			});
	    		
	    	</script>
		</div>
	</div> -->
	
	<c:if test="${award.atype eq 1}">
		<div id="jiang_e" class="form-group  has-feedback" style="line-height: auto;display:block;">
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">奖额：</font> 
				<input type="text" name="cashorvoucher" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.cashorvoucher }"/>
				<%-- <select name="awardUnit" id="awardUnit" style="width:20auto;height:25px;font-size: 15px;border-radius:4px;">
	    			<option value="">--请选择--</option>
	    			<option id="rmb" value="元" <c:if test="${award.awardUnit eq '元' }">selected="selected"</c:if>>元</option>
	    			<option id="baifenbi" value="%" <c:if test="${award.awardUnit eq '%' }">selected="selected"</c:if>>%</option>
	    			<option value="分" <c:if test="${award.awardUnit eq '分' }">selected="selected"</c:if>>分</option>
	    		</select> --%>
				<span id="span"></span>
			</div>
		</div>
	</c:if>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">买家定向：</font> 
			<select class="aos" style="border-radius:4px;" id="businessNo" name="businessNo" onchange="active_object_select();">
				<option value="0">--请选择--</option>
				<c:forEach items="${snlList}" var="sl">
					<option value="${sl.businessNo}">${sl.businessNo}</option>
				</c:forEach>
			</select>
			<span id="bn" style="color: red;"></span>
		</div>
	</div>
		
	<div id="bn_show" class="form-group  has-feedback" style="display:none;">
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">定向名单名称：</font>
			<font id="businessName" name="businessName"></font>
		</div>
  	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">是否下架：</font> 
				<select name="iscancel" id="iscancel1" style="width:20auto;height:25px;font-size: 15px;border-radius:4px;">
	    			<option value="">请选择</option>
	    			<option value="1" <c:if test="${award.iscancel==1 }">selected="selected"</c:if>>上架</option>	
	    			<option value="0" <c:if test="${award.iscancel==0 }">selected="selected"</c:if>>下架</option>
    			</select>
		</div>
	</div>
	
	<%-- <div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">是否为模板：</font> 
			<input type="radio" name="isTemplet" value="1"  ${award.isTemplet==1?'checked':''}/>是
			<input type="radio" name="isTemplet" value="2"  ${award.isTemplet==2?'checked':''}/>否
		</div>
	</div> --%>
		
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1 ">
			<font class="col-md-3 text-right" style="line-height:25px;border-radius:4px;">交易方式子开关：</font> 
				<select name="subswitch" id="subswitch1" style="width:20auto;height:25px;font-size: 15px;border-radius:4px;border:1px solid #999999;">
	    			<option value="">请选择</option>
		    		<option value="1" <c:if test="${award.subswitch==1 }">selected="selected"</c:if>>开</option>
		    		<option value="0" <c:if test="${award.subswitch==0 }">selected="selected"</c:if>>关</option>
	    		</select>
		</div>
	</div>
		
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">奖品交易方式：</font> 
			<select name="tradetype" id="tradetype1" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;">
				<option value="">--请选择--</option>
				<c:forEach items="${tradetype_map }" var="tm">
					<c:choose>
						<c:when test="${award.tradetype==tm.key }">
							<option selected="selected" value="${tm.key }">${tm.value }</option>
						</c:when>
						<c:otherwise>
							<option value="${tm.key }">${tm.value }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
	</div>
		
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1 ">
				<font class="col-md-3 text-right" style="line-height:25px;">有效期：</font> 
				<input type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="endtime" id="endtime" style="width:20auto;height:25px;font-size: 15px;border-radius:5px;" value="<fmt:formatDate value='${award.endtime }' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		</div>
	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">奖品总份数：</font>  
			<input type="text" name="quantityall" value="${award.quantityall }" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;"/>
			<span>份</span>
		</div>
	</div>
		
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">奖品剩余数：</font> 
			<input type="text" name="quantityrest" class="required number" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.quantityrest }"/>
			<span>份</span>
		</div>
	</div>
	
	<%-- <div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">添加时间：</font> 
			<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="addtime" id="addtime" style="width:20auto;height:25px;font-size: 15px;border-radius:4px;" value="<fmt:formatDate value='${award.addtime }' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>"/>
		</div>
	</div> --%>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">添加人：</font>  
			<input type="text" name="addman" value="${adminuser.username}" style="width:20auto;height:25px;font-size: 15px;border-style:none;" readonly="true" value="${award.addman }"/>
		</div>
	</div>
	
	<c:if test="${award.atype ne 1}">
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">领奖规则：</font>
				<div class="col-md-offset-3"><textarea class="form-control" name="awardRules">${award.awardRules}</textarea></div>
			</div>
		</div>
	</c:if>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">备注：</font>
			<div class="col-md-offset-3"><textarea class="form-control" id="remark" name="remark" value="" onKeyUp="checkLength();" placeholder="--最多输入100个字--">${award.remark }</textarea></div>
		</div>
	</div>	 
		
	<div class="form-group  has-feedback" >
		<div class="col-md-3 col-md-offset-1" style="margin-top: 1auto;margin-bottom: 1auto">
			<font size="3">投标使用限制</font>
		</div>
	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">标的类型：</font> 
			<input type="radio" name="bid_type" value="1" onclick="select_bt(this)"/>指定的标
			<input type="radio" name="bid_type" value="2" onclick="select_bt(this)"/>未指定的标
		</div>
	</div>
	
	
	<div id="appoint">
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">指定的标号：</font> 
				<input type="text" id="appointed_bid" name="appointed_bid" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;"/>
				<button type="button" id="add_bid_number">添加<span class="glyphicon glyphicon-pencil"></span></button>
			</div>
		</div>
		
		<div id="bid_name_show" class="form-group  has-feedback" style="display: none;">
			<div id="bid_name" class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">指定标的名称： </font>
			</div>
		</div>
	</div>
	
	
	<div id="unappoint">
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">投标属性限制：</font> 
		    	<c:forEach items="${biao_attribute}" var = "ba">
			    	<input type="checkbox" name="tattribute" value="${ba.key}"/>${ba.value}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </c:forEach>
		    	
		    	<script type="text/javascript">
		    	
			    	var bidSet = "${bidSet}";
		    	    $.each(JSON.parse(bidSet), function(index, value){
		    	        $("input[name='tattribute']").each(function () {
		    	            if($(this).val() == value) {
		    	                $(this).attr("checked",true);
		    	            }
		    	        });
		    	    });
			    	
	  			</script>
			</div>
		</div>
		
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">投标期限限制最低值： </font>
				<input type="text" name="tdayLimitl" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.tdayLimitl }"/>
				<span>天</span>
			</div>
		</div>
		
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">投标期限限制最高值： </font>
				<input type="text" name="tdayrestrict" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.tdayrestrict }"/>天
			</div>
		</div>
		
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">收益限制最低值：</font> 
				<input type="text" name="tmlrrestrict" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.tmlrrestrict }"/>
				<!-- <span>%</span> -->
			</div>
		</div>	
		
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">收益限制最高值：</font>
				<input type="text" name="tmhrrestrict" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.tmhrrestrict }"/>
				<!-- <span>%</span> -->
			</div>
		</div>
			
		<div class="form-group  has-feedback" >
			<div class="col-md-8 col-md-offset-1">
				<font class="col-md-3 text-right" style="line-height:25px;">投标还款方式：</font> 
				<select name="trtype" id="trtype1" style="width:20auto;height:25px;font-size: 15px;border-radius:4px;">
    			<option value="">--请选择--</option>
	   			<option value="1" <c:if test="${award.trtype==1 }">selected="selected"</c:if>>一次还本付息</option>
	   			<option value="2" <c:if test="${award.trtype==2 }">selected="selected"</c:if>>等额本金</option>
    			<option value="3" <c:if test="${award.trtype==3 }">selected="selected"</c:if>>等额本息</option>
	   			<option value="4" <c:if test="${award.trtype==4 }">selected="selected"</c:if>>按期付息到期还本</option>
	   			</select>
			</div>
		</div>
	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">投标金额限制最低值： </font>
			<input type="text" name="tminmoney" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.tminmoney }"/>
			<span>元</span>
		</div>
	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-8 col-md-offset-1">
			<font class="col-md-3 text-right" style="line-height:25px;">投标金额限制最高值：</font> 
			<input type="text" name="tmaxmoney" style="width:20auto;height:25px;font-size: 15px;border-radius:4px; border:1px solid #999999;" value="${award.tmaxmoney }"/>
			<span>元</span>
		</div>
	</div>
	
	<div class="form-group  has-feedback" >
		<div class="col-md-offset-2">
			<button class="btn btn-default" type="submit" style="margin-left: 20auto">保存</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="backid" class="btn btn-default" name="backid" type="button" onclick="gotoAwardList('${pageNum}');">返回</button>
		</div>
	</div>
	
	</form>
</body>
</html>
