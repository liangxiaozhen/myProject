var phoneFlag = false;
var verifyFlag = false;
var idcardFlag = false;
var async = true;
$(document).ready(function(){
	$("#bindPhone").blur(function(){
		var bindPhone = $("#bindPhone").val();
		var reg = regexInfo.mobileRegex;
		if(bindPhone==""){
			phoneFlag = false;
			$('#phoneMSG').css("display","");
			$("#phoneMSG").html("<i></i>手机号码不为空");
		}else if(!reg.test(bindPhone)){
			phoneFlag = false;
			$('#phoneMSG').css("display","");
			$("#phoneMSG").html("<i></i>手机号码格式不正确");
		}else{
			$.ajax({
        	type: "post",
       	 	url: "/member/checkPhoneByMember.do",
        	dataType: "json",
        	data:{
        		bindPhone:$("#bindPhone").val()
       		 },
        	async: async,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 0){
        		 	phoneFlag = true;
					$('#phoneMSG').css("display","none");
					$("#phoneMSG").html("");
        		}else if(msg==1){
        			phoneFlag = false;
					$('#phoneMSG').css("display","");
					$("#phoneMSG").html("<i></i>您输入的手机号已被绑定");
        		}
        	}   
      }); 	
		}
 	});
 	
 	$("#bindVerify").blur(function(){
		var bindVerify = $("#bindVerify").val();
		var reg = /^\S{6}$/;
		if(bindVerify==""){
			verifyFlag = false;
			$('#verifyMSG_bd').css("display","");
			$("#verifyMSG_bd").html("<i></i>验证码不为空");
		}else if(!reg.test(bindVerify)){
			verifyFlag = false;
			$('#verifyMSG_bd').css("display","");
			$("#verifyMSG_bd").html("<i></i>请输入正确的验证码");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/verificationCode2.do",
	        	dataType: "json",
	        	data:{
	        		phone:$("#bindPhone").val(),
	        		verify:$("#bindVerify").val(),
	        		type:"phone"
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){
						verifyFlag = true;
						$('#verifyMSG_bd').css("display","none");
						$("#verifyMSG_bd").html("");
	        		}else if(msg==0){
	        			verifyFlag = false;
	        			$('#verifyMSG_bd').css("display","");
	        			$("#verifyMSG_bd").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入正确的验证码");
	        		}else if(msg==-1){
	        			verifyFlag = false;
	        			$('#verifyMSG_bd').css("display","");
	        			$("#verifyMSG_bd").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码超时，请重新获取");
	        		}
	        	}
			});
		}
 	});
});

function click1(imgCode){
	$("#bindPhone").blur();
	if(phoneFlag == true){
		/**
		 * 提交时没有token。通过访问URL直接提交Bug修复
		 */
	    var stok = "";
	    if(document.getElementById ("stok")){
	        stok  = document.getElementById ("stok").value;
	    }
		$.ajax({
        	type: "post",
       	 	url: "/member/sendMsg1.do",
        	dataType: "json",
        	data:{
        		phone:$("#bindPhone").val(),
        		imgCode:imgCode,
        		type:"changePhone",
        		stok:stok
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){
        		 	$("#click1").hide();
					$("#countDown").show();
					countDown(120);//倒计时120秒
					// 弹框优化 | zhenhua.xi | 20140915
					// showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
        			$('#verifyMSG_bd').css("display","");
					$("#verifyMSG_bd").removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
        		}else if(msg==0){
        			$('#verifyMSG_bd').css("display","");
					$("#verifyMSG_bd").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，请点击！");
        		}else if(msg==2){
        			$('#verifyMSG_bd').css("display","");
					$("#verifyMSG_bd").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，验证码错误，请再次点击！");
        		}
        	}   
      }); 
	}
}

function countDown(time){
	$("#countDown").html(time+"秒");
	 	time = time - 1;
	if(time>=0){
    	setTimeout("countDown("+time+")",1000);
	}else{
    	$("#countDown").hide();
    	$("#click1").show();
	}
}

$("#binding").click(function(){
	$("#bindPhone").blur();
	$("#bindVerify").blur();
	if(phoneFlag==true && verifyFlag==true){
		$.ajax({
	    	type: "post",
	   	 	url: "/member/bindingPhoneSave.do",
	    	dataType: "json",
	    	data:{
	    		phone:$("#bindPhone").val(),
	    		verify:$("#bindVerify").val()
	   		 },
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(msg){
	    		if(msg == 1){
					$('#bindPhoneStep').addClass('none');
					$('#sucessBindPhone').removeClass('none');
					$('#phoneMSG').css("display","");
					$('#verifyMSG_bd').css("display","");
					setTimeout(function(){
						$('#sucessBindPhone').parents('tr').addClass('none');
						$('#bindPhoneStep').removeClass('none');
						$('#sucessBindPhone').addClass('none');
						window.location.reload();
					},2000)
					//window.location.href='/member/bindingPhoneSave.do?phone='+$("#bindPhone").val();
	    		}else if(msg==0){
	    			
	    		}
	    	}
	    });
	}
});