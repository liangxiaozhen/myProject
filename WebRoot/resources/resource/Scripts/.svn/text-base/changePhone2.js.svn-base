var phoneFlag = false;
var verifyFlag = false;
var async = true;
$(document).ready(function(){
	$("#newPhone").blur(function(){
		var phone = $("#newPhone").val();
		var reg = /^[1]\d{10}$/;
		if(phone==""){
			phoneFlag = false;
			$('#phoneMSG4').css("display","");
			$("#phoneMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入新手机号码！");
		}else if(!reg.test(phone)){
			phoneFlag = false;
			$('#phoneMSG4').css("display","");
			$("#phoneMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>手机格式错误，请检查！");
		}else{
			$.ajax({
        	type: "post",
       	 	url: "/member/checkPhoneByMember.do",
        	dataType: "json",
        	data:{
        		bindPhone:$("#newPhone").val()
       		 },
        	async: async,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 0){
        		 	phoneFlag = true;
					$('#phoneMSG4').css("display","");
					$("#phoneMSG4").removeClass().html("");
        		}else if(msg==1){
        			phoneFlag = false;
					$('#phoneMSG4').css("display","");
					$("#phoneMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>您输入的手机号码已存在！");
        		}
        	}   
      }); 	
		}
 	});
 	
 	$("#newPhoneVerify").blur(function(){
 		var verify = $("#newPhoneVerify").val();
		var reg = /^\S{6}$/;
		if(verify==""){
			verifyFlag = false;
			$('#verifyMSG4').css("display","");
			$("#verifyMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入验证码！");
		}else if(!reg.test(verify)){
			verifyFlag = false;
			$('#verifyMSG4').css("display","");
			$("#verifyMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/verificationCode2.do?type=phone",
	        	dataType: "json",
	        	data:{
	        		verify:$("#newPhoneVerify").val(),
	        		phone:$("#newPhone").val()
	       		 },
	        	async: async,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){
						verifyFlag = true;
						$('#verifyMSG4').css("display","none");
						$("#verifyMSG4").removeClass().html("");
	        		}else if(msg==0){
	        			verifyFlag = false;
	        			$('#verifyMSG4').css("display","");
	        			$("#verifyMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
	        		}else if(msg==-1){
	        			verifyFlag = false;
	        			$('#verifyMSG4').css("display","");
	        			$("#verifyMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码超时，请点击重新发送!");
	        		}
	        	}
			});
		}
 	});
});

function click4(imgCode){
	async = false;
	$("#newPhone").blur();
	async = true;
	if(phoneFlag == true){
		$.ajax({
        	type: "post",
        	url: "/msg/changePhoneSendTextMessage2.do",
        	data:{
        		phone:$("#newPhone").val(),
        		imgCode:imgCode
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 'succ'){
        		 	$("#click4").hide();
					$("#countDown4").show();
					countDown4(120);//倒计时120秒
					// 弹框优化 | zhenhua.xi | 20140915
					// showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
        			$('#verifyMSG4').css("display","");
					$("#verifyMSG4").removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");					
        		}else{
        			$('#phoneMSG4').css("display","");
        			$("#phoneMSG4").removeClass().addClass("prompt_1 error_1").html("<i></i>"+msg+"");
        		}
        	}   
      }); 
	}
}

function countDown4(time){
	$("#countDown4").html(time+"秒");
	 	time = time - 1;
	if(time>=0){
    	setTimeout("countDown4("+time+")",1000);
	}else{
    	$("#countDown4").hide();
    	$("#click4").show();
	}
}

function newPhoneNext(){
	$("#newPhone").blur();
	$("#newPhoneVerify").blur();

	if(phoneFlag==true && verifyFlag==true){
		$.ajax({
	    	type: "post",
	   	 	url: "/member/changeBasicInfoCard.do",
	    	dataType: "json",
	    	data:{
	    		code:$("#newPhoneVerify").val(),
	    		phone:$("#newPhone").val(),
	    		type: "phone"
	   		 },
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(msg){
	    		if(msg == 1){
	    				$('#oldStep1').addClass('none');
	    				$('#oldStep2').addClass('none');
	    				$('#headStep').addClass('none');
	    				$('#oldStep3').removeClass('none');
	    				
	    				$('#changeOldStep1').attr("class","visited_a");
	        			$('#changeOldStep2').attr("class","visited_a");
	        			$('#changeOldStep3').attr("class","active");
	        			
	        			$('#phoneUpdate').val('');
	        			$('#verifyTest').val('');
	        			$('#idcard').val('');
	        			setTimeout(function(){ 
	        				$('#oldStep3').parents('tr').addClass('none'); 
	        				$('#stepWays').removeClass('none');
	        				$('#changePhoneByOld').removeClass('none'); 
	        				$('#oldStep3').addClass('none'); 
	        				location.reload();
	        				},2000)
	    				
	    				
	    		}
	    	}
	    });
		//document.forms[0].action='/member/changeBasicInfoCard.do?code='+$("#newPhoneVerify").val()+'&type=phone';
		//document.forms[0].submit();
	}
}
