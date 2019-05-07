var gjLogin = (function(){
	return {
 		login:function(){
 			userLoginBtn(this);
		}
	};
 })();

$(document).keydown(function(e){
 		 if(e.keyCode ==13){
			 userLoginBtn();
	 }
});

function userLoginBtn(obj){
  	var username = $("#username").val();
	var pwd 	 = $("#pwd").val();
     if(isEmpty(username)){
    	$("#errorMsg").show().css("color","red").text("请输入用户名");
    	return false;
     }else{
    	$("#errorMsg").hide();
    }
    
    if(isEmpty(pwd)){
    	$("#errorMsg").show().css("color","red").text("请输入用户密码");
    	return false;
    }else{
    	$("#errorMsg").hide();
    }
    
    var mark = $("#rem").is(":checked") ? "1" : "0";
 	$("#userLoginBtn").val("登录中...").parent().removeAttr("onclick");
	$.ajax({
		type:"post",
		url:basePath + "/user/logined.action",
		error:function(){$("#userLoginBtn").val("登录").parent().attr("onclick","gjLogin.login(this);");},
		data:{"loginname":username,"password":pwd,"mark":mark},
		success:function(data){
			$("#userLoginBtn").val("登录").parent().attr("onclick","gjLogin.login(this);");
			$("#errorMsg").show().text("");
			var obj = $.parseJSON(data);
 			if(obj.result == "loginUserName_null"){
				$("#errorMsg").show().css("color","red").text("请输入用户名");
 			}else if(obj.result == "password_null"){
 				$("#errorMsg").show().css("color","red").text("请输入用户密码");
  			}else if(obj.result == "code_null"){
 				$("#errorMsg").show().css("color","red").text("请输入验证码");
  			}else if(obj.result == "code_error"){
 				$("#errorMsg").show().css("color","red").text("请输入正确的验证码");
  			}else if(obj.result == "loginNameOrPsw_error"){
 				$("#errorMsg").show().css("color","red").text("您输入的用户名或密码有误");
  			}else if(obj.result == "userDisable"){
 				$("#errorMsg").show().css("color","red").text("您的账号已经停用,请联系客服");
  			}else if(obj.result == "success"){
  				$("#userLoginBtn").val("登录成功");
  				var referrer = obj.referer;
     			if(isEmpty(referrer)){
   					window.location.href = basePath + "/user/tologin.action";
  				}else{
  					window.location.href = referrer ;
  				}
   			}
		}
		
	});
}

//弹框
var plusBankBg,showForm1,showForm;

function showCon_0(){
	plusBankBg=$('.plusBankBg');
	showForm=$('.plusBank');
	plusBankBg.show();
	showForm.slideDown();
}


function showCon_1(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank1');
	plusBankBg.show();
	showForm1.slideDown();
	if($('.inputFocus1').size()>0){
		$('.inputFocus1').focus();
	}
}
function showCon_2(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank2');
	plusBankBg.show();
	showForm1.slideDown();
}

function closeAll_0(){
	plusBankBg=$('.plusBankBg');
	showForm=$('.plusBank');
	plusBankBg.hide();
	showForm.slideUp();
}

function closeAll_1(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank1');
	plusBankBg.hide();
	showForm1.slideUp();
}
function closeAll_2(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank2');
	plusBankBg.hide();
	showForm1.slideUp();
}
function closeAll_5(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank5');
	plusBankBg.hide();
	showForm1.slideUp();
}

$(function($) {
	$("a.fr").each(function(){
		$(this).click(function(){
			closeAll_0();
			closeAll_1();
			closeAll_2();
			//closeAll_3();
		});
	});
});


	//--输入框内提示-------------
$(".touzi01 .input_1").each(function(){
 var thisVal=$(this).val();
 if(thisVal!=""){
   $(this).siblings("span").hide();
  }else{
   $(this).siblings("span").show();
  }
 $(this).focus(function(){
   $(this).siblings("span").hide();
  }).blur(function(){
	var val=$(this).val();
	if(val!=""){
	 $(this).siblings("span").hide();
	}else{
	 $(this).siblings("span").show();
	} 
  });
});	

function SwapTab(name,title,content,Sub) {
	$(name+' '+title).mousedown(function(){
		var phoneLog = ($(this).attr('data-msg')=='phoneLog')
		var gerLog = ($(this).attr('data-msg')=='gerLog')
		var phoneLogCon = $(content+' '+Sub).eq(0)
		var gerLogCon = $(content+' '+Sub).eq(1)
		if(phoneLog) {
			$(this).html('使用普通方式登录');
			$(this).attr('data-msg','gerLog');
			$(this).attr('title','使用普通方式登录');
			phoneLogCon.addClass('hidden');
			gerLogCon.removeClass('hidden');
		}
		else if(gerLog)
		{
			$(this).html('手机动态密码登录');
			$(this).attr('data-msg','phoneLog')
			$(this).attr('title','手机动态密码登录')
			gerLogCon.addClass('hidden');
			phoneLogCon.removeClass('hidden');
		}
		
	  });
};
new SwapTab(".tab_u",".SwapTabBtn",".login-module-content",".nr");

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
