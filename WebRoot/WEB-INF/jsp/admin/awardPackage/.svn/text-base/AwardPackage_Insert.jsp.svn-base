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
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">	
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/css/bootstrapValidator.min.css">	
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>	
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>	
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
	
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<style type="text/css">
	#id {
		margin: 40px;
	}
	
	hr {
		margin: 5px;
	}

	/***错误提示样式***/ 
	/* #insertAwardPackageForm label.error { 
		color: red; 
		font-style: italic; 
	}  */
 
</style>
<script type="text/javascript">
	$(function(){
		
		var count = 0;
		var index = 1;
		$("#add_award").click(function(){
			
			//获取奖品类型的value值
			var apAwardType = $("#add_award_type").find("option:selected").val();
			//alert("apAwardType>>>"+apAwardType);
			//获取奖品类型的text值
			var apAwardTypeStr = $("#add_award_type").find("option:selected").text();
			//alert("apAwardTypeStr>>>"+apAwardTypeStr);
			//获取奖品名称的value值(text值)
			var awardName = $("#add_award_name").find("option:selected").val();
			//alert("奖品名称》》》"+awardName);
			//获取奖品的份数
			var awardQTY = $("#add_award_quantity").find("option:selected").val();
			//alert("奖品份数》》》"+awardQTY);
			
			var subDiv = $('<div class="form-group  has-feedback sub_div" ></div>');
			subDiv.html(
					'<div class="row">'+
						'<font class="control-label col-md-3 index" style="line-height:10px;">'+index+'</font>'+
						'<div class="col-md-2">'+
							'<input  name="apdList['+count+'].apAwardType"  value='+apAwardType+' type="hidden"><font>'+apAwardTypeStr+'</font>'+
						'</div>'+
						'<div class="col-md-2">'+
							'<input class="awardName" name="apdList['+count+'].awardName"  value='+awardName+' type="hidden"><font>'+awardName+'</font>'+
						'</div>'+
						'<div class="col-md-2">'+
							'<input  name="apdList['+count+'].awardQTY"  value='+awardQTY+' type="hidden"><font>'+awardQTY+'</font>'+
						'</div>'+
						'<button type="button" class="btn btn-default delete_award" name="delete_award">删除</button>'+
					'</div>'
					);
			if(apAwardType==0 || awardName==0 || awardQTY==0){
				alert("请选择奖品");
			}else{
				$("#select_award").append(subDiv);
				count++;
				index++;
				//恢复select的值
				var objs = document.getElementsByTagName("select");
		        for (var i = 0; i < objs.length; i++) {
		            with (objs[i]) {
		                value = "0";
		            }
		        }
			}
		});
		
		//删除按钮时处理索引
		$("body").on("click",".delete_award",function(e){
			
			var $row = $(this).parents(".sub_div"); //删除按钮的父亲
			var x = $row.find(".index").html();//通过父亲再找到儿子（索引的value值）
			//遍历所有的class = sub_div的元素  i从0开始   如果i=删除的索引   就将i写到下一个元素的索引里面
			$(".sub_div").each(function(i){
				if(i==x){
					$(this).find(".index").html(i);
					x++;
				}
			}); 
			$row.remove();
			index--;
		});
	});
</script>
<script type="text/javascript">

	//返回列表
	function gotoAwardPackageList(){
		window.location.href="${pageContext.request.contextPath }/admin/awardPackage/selectAwardPackageByCondition.action";
	}
	
	//选择奖品类型时
	function select_aat(){
		var action = "${pageContext.request.contextPath }/admin/awardPackage/getAwardNamesByAtype.action";
		//获取奖品类型的value值
		var apAwardType = $("#add_award_type").find("option:selected").val();
		var params = {
			"apAwardType":apAwardType	
		};
		var callback = function(data){
			var aan = document.getElementById("add_award_name");
			aan.options[0] = new Option("--请选择奖品名称--","0");
			for(var i=0;i<data.length;i++){
				aan.options[aan.length] = new Option(data[i].aname,data[i].aname);
			}
		};
		document.getElementById("add_award_name").length = 0;
		$.post(action,params,callback,"json");
	}
	
	//选择奖品名称
	function select_aan(){
		
		var aan =  $("#add_award_name").val();
		var flag = true;
		//遍历奖品名称
		$(".awardName").each(function(){
			if($(this).val()==aan){
				flag = false;
				alert("已添加");
			}
		}); 
		
		if(flag){
			
			document.getElementById("add_award_quantity").length = 0;
			var aaq = document.getElementById("add_award_quantity");
			aaq.options[0] = new Option("--请选择奖品份数--","0",true);
			for(var i=0;i<9;i++){
				aaq.options[aaq.length] = new Option(i+1,i+1);
			}
		}
	}
	
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div id="id"></div>

	<form class="form-horizontal" id="insertAwardPackageForm" role="form" method="post" action="${pageContext.request.contextPath}/admin/awardPackage/insertAwardPackage.action">
	
		<div class="form-group  has-feedback">
			<font class="control-label col-md-2"><font size="4">奖品包</font></font>
		</div>
		<hr>

		<div class="form-group">
			<font class="col-sm-3 control-label" style="line-height: 12px;">奖品包编号：</font>
			<div class="col-sm-2">
				<input type="text" name="apno" value="${ap.apno}"
					style="height: 25px; font-size: 15px; border-style: none;width:400px;"
					readonly="readonly" />
			</div>
		</div>

		<div class="form-group">
			<font class="col-sm-3 control-label">奖品包名称：</font>
			<div class="col-sm-2">
				<input type="text" name="apname"
					value="${ap.apname}" placeholder="--请输入奖品包名称--"
					class="form-control" />
			</div>
			<span id="ap_name"></span>
		</div>

		<div class="form-group  has-feedback">
			<font class="control-label col-md-3" style="line-height: 10px;">是否下架：</font>
			<div class="col-md-8">
				<input type="radio" name="iscancel" value="1" checked="checked" />上架
				<input type="radio" name="iscancel" value="0" />下架
			</div>
		</div>

		<!-- <div class="form-group  has-feedback">
			<font class="control-label col-md-3" style="line-height: 10px;">是否为模板：</font>
			<div class="col-md-8">
				<input type="radio" name="isTemplet" value="1" checked="checked" />是
				<input type="radio" name="isTemplet" value="2" />否
			</div>
		</div> -->

		<div class="form-group">
			<font class="col-sm-3 control-label">奖品包总份数：</font>
			<div class="col-sm-2">
				<input type="text" name="quantityall"
					value="" placeholder="--请输入奖品包总份数--" class="form-control" />
			</div>
			<span id="quantity_all"></span>
		</div>

		<div class="form-group">
			<font class="col-sm-3 control-label">奖品包剩余数：</font>
			<div class="col-sm-2">
				<input type="text"
					name="quantityrest" value="" placeholder="--请输入奖品包剩余数--"
					class="form-control" />
			</div>
			<span id="quantity_rest"></span>
		</div>

		<!-- <div class="form-group">
			<font class="control-label col-md-3">添加时间：</font>
			<div class="col-sm-2">
				<input type="text" name="addtime"
					id="addtime" class="Wdate form-control"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<span id="add_time"></span>
		</div> -->

		<div class="form-group  has-feedback">
			<font class="control-label col-md-3">添加人：</font>
			<div class="col-md-8">
				<input type="text" name="addman" value="${adminuser.username}"
					style="width: 20auto; height: 25px; font-size: 15px; border-style: none;"
					readonly="true" />
			</div>
		</div>

		<div class="form-group">
			<font class="col-sm-3 control-label">备注：</font>
			<div class="col-sm-3">
				<textarea rows="3" class="form-control" name="remark"></textarea>
			</div>
		</div>

		<div class="form-group  has-feedback">
			<font class="control-label col-md-2"><font size="4">奖品包内容</font></font>
		</div>
		<hr>
		<div id="select_award">
			<div class="form-group  has-feedback">
				<div class="row">
					<font class="control-label col-md-3">请选择奖品：</font>
					<div class="col-md-2">
						<select class="form-control" id="add_award_type"
							onchange="select_aat();">
							<option value="0">--请选择奖品类型--</option>
							<c:forEach items="${atype_map}" var="am">
								<option value="${am.key}">${am.value}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-2">
						<select class="form-control" style="border-radius: 4px;"
							id="add_award_name" onchange="select_aan();">
							<option value="0">--请选择奖品名称--</option>
						</select>
					</div>

					<div class="col-md-2">
						<select class="form-control" style="border-radius: 4px;"
							id="add_award_quantity">
							<option value="0">--请选择奖品份数--</option>
						</select>
					</div>
					<button type="button" class="btn btn-default" id="add_award">添加</button>
					<span id="msg"></span>
				</div>
			</div>

		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-1">
				<button id="conserve" type="button" class="btn btn-default">保存</button>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-default"
					onclick="gotoAwardPackageList();">返回列表</button>
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
		
		$().ready(function(){
			
			//奖品包的总数和奖品剩余数比较
			jQuery.validator.addMethod("apNumCompare",function(){
				
				//总份数
				var quantity_all = $( 'input[name="quantityall"]').val();
				//剩余数
				var quantity_rest = $( 'input[name="quantityrest"]').val();
				var flag = true;
				if(parseInt(quantity_rest) > parseInt(quantity_all)){
					flag = false;
				}
				return flag;
				
			},"奖品包剩余数不能大于总份数");
			
			function validateForm(){
			
				return $("#insertAwardPackageForm").validate({
					
					rules:{
						apname:{
							required:true,
							remote:{
								url:"<%=basePath%>admin/awardPackage/checkApnameOnly.action",
								type:"post",
								dataType:"json",
								data:{
									apname:function(){
										return $('input[name="apname"]').val().trim();
									}
								}
							}
						},
						quantityall: {
							  "required":true,
							  "digits":true
						 },
						 quantityrest: {
							  "required":true,
							  "digits":true,
							  "apNumCompare":true
						 },
						 /* addtime: "required", */
						 remark:{
							 maxlength:100
						 }
					} ,
					messages:{
						apname:{
							remote:"名称已存在"
						},
						remark:{
							maxlength: "密码长度不能大于 100 个字符"
						}
					}
				}).form();
			}
			
			$("#conserve").on("click",function() {
				if(validateForm()){
					var num = $("div .sub_div").length;
					if(num > 0){
						$('#insertAwardPackageForm').submit();
					}else{
						alert("请添加奖品");
						return;
					}
				}
			
			});
			
		});
	</script>
	
	<!-- <script type="text/javascript">
	
		$(document).ready(function(){
			$("#insertAwardPackageForm").bootstrapValidator({
				message: 'This value is not valid',
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields:{
		        	apname:{
		        		validators:{
		        			notEmpty:{
		        				message:"必填"
		        			}
		        		}
		        	},
		        	quantityall:{
		        		validators:{
		        			notEmpty:{
		        				message:"必填"
		        			},
		        			greaterThan:{
		        				value:1,
		        				inclusive:true,
		        				message:"请输入大于或等于1的整数"
		        			}
		        		}
		        	},
		        	quantityrest:{
		        		validators:{
		        			notEmpty:{
		        				message:"必填"
		        			},
		        			greaterThan:{
		        				value:0,
		        				inclusive:true,
		        				message:"请输入大于或等于0的整数"
		        			},
		        			callback:{
		        				message:"礼品包的剩余数不能大于总数",
		        				callback:function(value,validator){
		        					var quantityall = $('input[name="quantityall"]').val();//奖品包总份数
		        					var quantity_all = parseInt(quantityall);
		        					return value <= quantity_all;
		        				}
		        			}
		        		}
		        	},
		        	addtime:{
		        		validators:{
		        			notEmpty:{
		        				message:"必填"
		        			}
		        		}
		        	},
		        	remark:{
		        		validators:{
		        			notEmpty:{
		        				message:"必填"
		        			},
		        			stringLength: {
		                        max:100,
		                        message:"不能超过100个字符"
		                    }
		        		}
		        	}
		        }
		        
			});
			
		    $("#conserve").on("click",function() {
		    	
		    	//$('#insertAwardPackageForm').bootstrapValidator('validate');
				//获取表单对象
				var bootstrapValidator = $("#insertAwardPackageForm").data('bootstrapValidator');
				//手动触发验证
				bootstrapValidator.validate();
				alert(bootstrapValidator.isValid());
				if(bootstrapValidator.isValid()){
	 				//alert("进来了");
	 				$('#insertAwardPackageForm').submit();
				} 
			});
			
			$('#addtime').on('change show', function(e) {
				 alert("123");
	            // Revalidate the date when user change it  
	            $('#insertAwardPackageForm').bootstrapValidator('revalidateField', 'addtime');  
		   	});  
	    	
		});
	
	</script> -->
	
</body>
</html>
