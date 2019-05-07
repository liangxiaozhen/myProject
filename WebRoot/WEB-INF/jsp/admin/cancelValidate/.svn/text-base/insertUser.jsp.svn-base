<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加到屏蔽安全验证表</title>
<script type="text/javascript">
	$(function(){
		$("#InputsWrapper").hide();
		$("#CancelType").change(function(){
			if("#CancelType.val()!='' "){
				$("#InputsWrapper").show();
			}
		})
	});
	
	var x = 1;
	function isInsertUser(){
		var flag = true;
		var MaxInputs = 10;
		var InputsWrapper = $("#InputsWrapper");
		var AddButton = $("#AddMoreFileBox");
		var FieldCount = 1;
		var username = $("#usernameCancelValidate").val();
		 if(username == ""){
			flag = false;
			alert("请输入用户名");
			return;
		} 
		
		var action = "isInsertUser.action";
		var params = {
				"username":username
		};
		var callback = function(data){
			if(data !="添加成功!"){
				alert(data);
				flag = false;
			}else{
				if(x <= MaxInputs){
					var username = $("#usernameCancelValidate").val();
					$("input[name='username']").each(function(){
						if($(this).val()==username){
							flag = false;
							alert("用户已添加");
						}
					});
		
		FieldCount++;
		var oDiv = $('<div class="wapperclass"></div>');
		oDiv.html('<div class="form-group">'+
				'<label class="col-sm-3 control-label" id="lb-count">'+x+'</label>'+
                '<div class="col-sm-4">'+
	                  '<div class="input-group">'+
		             	 '<input type="text" name="username" id="usernameCancelValidate" value="'+username+'" class="form-control"/>'+     				              
	                  '</div>'+
                '</div>'+	
                '<div class="col-sm-2">'+
          		'<span><button id="removeButton" class="btn btn-default removeclass" value="'+x+'"><strong>删除</strong></button></span>'+		                      
         		'</div>'+
     			'</div>'); 	
		if(flag){
			$(InputsWrapper).append(oDiv);
			var username = $("#usernameCancelValidate").val("");
			x++;
			}
		}
	}			
};
$.post(action,params,callback,'json');
}
		
		$(document).ready(function(){
			$("body").on("click",".removeclass",function(e){
				var value = $(this).val();
				var $row = $(this).parents('.wapperclass'),
				$option = $row.find('[name="option[]"]');
				$(".wapperclass").each(function(i){
					if(value==i){
						$(this).find("#lb-count").html(value);
						$(this).find("#removeButton").val(value);
						value++;
					}
				});
				$row.remove();
				var size=$(".wapperclass").size();
				if(size==0){
					x=1;
				}else{
					x=$(".wapperclass").size()+1;
				}
				if(size<MaxInputs){
					$("#AddMoreFileBox").attr("disabled", false);
				}
				$('form').bootstrapValidator('removeField', $option);
			});
		});
</script>
</head>
<body>
	<form id="insert-form" class="form-horizontal" role="form" method="post" action="insertUser.action">
		<div class="form-group">
			<label for="clearmethod" class="col-sm-3 control-label">选择取消验证类型</label>
			<div class="col-sm-3" id="xz">
				<select name="canceltype" id="CancelType" class="form-control">
					<option value="">---请选择---</option>
					<option value="1">登录验证码</option>
					<option value="2">注册验证码</option>
					<option value="3">密码控件</option>
					<option value="4">登录U盾</option>
				</select>
			</div>
		</div>
		
		<!-- 添加用户 -->
		<div id="InputsWrapper">
			<div class="form-group">
				<label for="inputusername" class="col-sm-3 control-label">请输入用户名</label>
					
				<div class="col-sm-4">
					<div class="input-group">
						<input type="text" name="user_name" id="usernameCancelValidate" class="form-control" />
					</div>
				</div>
				
				<div class="col-sm-2">
					<span>
						<button id="AddMoreFileBox" type="button"
							class="btn btn-default" onclick="isInsertUser()">
							<strong>添加</strong>
						</button>
					</span>
				</div>
			</div>	
		</div>	
	</form>
</body>
</html>