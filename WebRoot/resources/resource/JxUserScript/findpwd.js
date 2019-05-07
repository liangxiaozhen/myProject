//   $(".two,.three").hide();
//  $("#submit").click(function(){
//    $(".two").show();
//    $(".one").hide();
//  })
//  function next(){
//     $(".three").show();
//     $(".two").hide();
//  }
 

/*var email_flag = false;
$(function(){
	$("#email").blur(function(){
		var email = $("#email").val();
		if(email){
			//判断是否符合邮件地址格式
			var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			if(!reg.test(email)){
				email_flag = false;
				$("#emailMSG").html("<i></i>邮件地址格式错误");
			}else{
				email_flag = true;
				$("#emailMSG").html("");
			}
		}else{
			email_flag = true;
			$("#emailMSG").html("<i></i>邮件地址不能为空");
		}
	});
});*/
var FindPwd = (function(){
	return {
		//检查用户名\手机号\邮箱是否正确
		userSubmit:function(obj){
			 var uname = document.getElementById("uname").value;
			 if(isEmpty(uname)){
				 $("#unameMSG").show().text("请输入用户名");
				 return false;
			 }else{
				 $("#unameMSG").text("").hide();
			 }
			 var imgcodeVal = $('#imgcode').val();
			 if(imgcodeVal == "success"){
				 
			 }else{
				 $("#imgCodeMSG").css("display","block");
				 $("#imgCodeMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请拖动滑块完成验证！");
				 return false;
			 }
 			  $.ajax({
				  type:"post",
				  url:basePath + "/findpwd/finduser.action",
				  data:{"uname":uname},
				  error:function(){},
				  success:function(data){
					  $("#unameMSG").text("").hide();
 					 var obj = $.parseJSON(data);
					 if(obj.result =="uname_null"){
 						 $("#unameMSG").show().text("请输入用户名");
 					 }else if(obj.result == "uname_error"){
 						$("#uname").val(""); 
 						$("#unameMSG").show().text("请检查用户名\手机号\邮箱是否正确");
 					 }else if(obj.result == "success"){
  						 window.location.href = basePath + "/findpwd/pwdtypeList.action";
 					 }
				  }
				  
			  });
 		},
 		//选择方式找回密码
 		pwdtype:function(obj){
 			$(".contentError").hide(); 
 			$("#contentError").text("");
 			var mark = $(obj).data("mark");
  			$.ajax({
 				type:"post",
 				url:basePath+"/findpwd/pwdtype.action",
 				data:{"mark":mark},
 				error:function(){},
 				success:function(data){
 					//alert(data);
 					$("#contentError").text("");
 					 var obj = $.parseJSON(data);
 					 if(obj.result == "user_error"){
 						$(".contentError").show(); 
 						$("#contentError").text("操作超时，重新验证用户名");
 						setTimeout(function(){
  							window.location.href = basePath +"/user/findpwd.action";
 						},2000);
 					 }else if(obj.result == "params_error"){
 						$(".contentError").show();
 						$("#contentError").text("参数非法!，请重新操作!");
 						setTimeout(function(){
  							window.location.href = basePath +"/user/findpwd.action";
 						},2000);
 					 }else if(obj.result == "email_fail"){
 						$(".contentError").show();
 						$("#contentError").text("邮箱未开通，请选择其他方式找回密码");
 					 }else if(obj.result == "email_success"){
 						window.location.href = basePath +"/findpwd/emailpwd.action";
 					 }else if(obj.result == "mibao_fail"){
 						$(".contentError").show();
 						$("#contentError").text("未设置密保问题，请选择其他方式找回密码");
 					 }else if(obj.result == "mibao_success"){
 						window.location.href = basePath +"/findpwd/mibaopwd.action";
 					 }else if(obj.result == "phone_fail"){
 						$(".contentError").show();
 						$("#contentError").text("手机未开通，请选择其他方式找回密码");
 					 }else if(obj.result == "phone_success"){
 						window.location.href = basePath +"/findpwd/phonepwd.action";
 					 }
 				}
  			});
  		},
  		//检查邮箱是否正确
  		emailPwdCheck:function(obj){
  			$("#emailMSG").text("").hide();
  			var email = $("#email").val();
  			//alert("邮箱地址： "+email);
  			/*if(email==""){
  				$("#emailMSG").show().text("请填写邮箱地址");
  				return false;
  			}*/
  			/*if(!email_flag){
  				return false;
  			}*/
  			$.ajax({
  				type:"post",
  				url:basePath + "/findpwd/emailPwdCheck.action",
  				data:{"email":email},
  			    success:function(data){
  			    	$("#emailMSG").text("").hide();
  			    	var obj = $.parseJSON(data);
  			    	if(obj.result == "user_error"){
  			    		$("#emailMSG").show().text("操作超时，重新验证用户名");
  			    		setTimeout(function(){
  							window.location.href = basePath +"/user/findpwd.action";
 						},2000);
  			    	}else if(obj.result == "email_null"){
  			    		$("#emailMSG").show().text("请输入邮箱!");
  			    	}else if(obj.result == "email_error"){
  			    		$("#emailMSG").show().text("请填写正确的邮箱地址!");
  			    	}else if(obj.result == "success"){
   			    		 $("#emailPwdOne").hide();
  			    		 $("#emailPwdTwo").show();
  			    		$(".steps_3").find("ul").siblings().eq(1).addClass("active");
  			    		$("#myEmail").text(obj.email);
  			    	}else if(obj.result == "fail"){
  			    		$("#emailMSG").show().text("邮件发送失败！请重新操作");
  			    		setTimeout(function(){
  							window.location.href = basePath +"/findpwd/emailpwd.action";
 						},2000);
  			    	}
  			    }
  			});
  		}
	};
})();

function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}