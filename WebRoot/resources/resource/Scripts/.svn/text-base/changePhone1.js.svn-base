var phoneFlag = false;
var verifyFlag = false;
var idcardFlag = false;
var async = true;
//var textMsg = alertMsgInfo.textMsg;

//通过原手机修改
function modify1(){
	var certIdentity = $("#certIdentity").val();
	if(certIdentity==0){
		// 弹框优化 | zhenhua.xi | 20140917
		$(".plusBank1 .content").html('<i class="ico_all size24 img_false24"></i>为保障您的账户安全，修改手机前需要实名认证！');
		$("#btn_0919").val("立即认证");
		$("#btn_0919").click(function(){
			location.href="/member/identityAuthentication.do";
		});
		showCon_1();
	}else if(certIdentity==1){
		$('#stepWays').addClass('none');
		$('#changePhoneByOld').removeClass('none');
		$('#oldStep1').removeClass('none');
		
		//window.location.href = '/member/changeBasicInfoEmail.do?type=phone';
	}
	return;
}
//人工修改页面
function modify2(){
	var certIdentity = $("#certIdentity").val();
	if(certIdentity==0){
		// 弹框优化 | zhenhua.xi | 20140917
		$(".plusBank1 .content").html('<i class="ico_all size24 img_false24"></i>为保障您的账户安全，修改手机前需要实名认证！');
		$("#btn_0919").val("立即认证");
		$("#btn_0919").click(function(){
			location.href="/member/identityAuthentication.do";
		});
		showCon_1();
	}else if(certIdentity==1){
		window.location.href = '/member/changeBasicInfoEmail.do?type=phoneManual';
	}
}

$(document).ready(function(){
	$("#phoneUpdate").blur(function(){
		var phone = $("#phoneUpdate").val();
		var reg = regexInfo.mobileRegex;
		if(phone==""){
			phoneFlag = false;
			$('#phoneMSG').css("display","");
			$("#phoneMSG").html("<em></em>手机号码不为空");
		}else if(!reg.test(phone)){
			phoneFlag = false;
			$('#phoneMSG').css("display","");
			$("#phoneMSG").html("<em></em>手机号码格式不正确");
		}else{
			$.ajax({
        	type: "post",
       	 	url: "/member/checkPhoneByMbId.do",
        	dataType: "json",
        	data:{
        		phone:$("#phoneUpdate").val()
       		 },
        	async: async,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){
        		 	phoneFlag = true;
					$('#phoneMSG').css("display","none");
					$("#phoneMSG").html("");
        		}else if(msg==0){
        			phoneFlag = false;
					$('#phoneMSG').css("display","");
					$("#phoneMSG").html("<em></em>请填写正确的手机号码");
        		}
        	}   
      }); 	
		}
 	});
 	
 	$("#verifyTest").blur(function(){
		var verify = $("#verifyTest").val();
		
		var reg = /^\S{6}$/;
		if(verify==""){
			verifyFlag = false;
			
			$('#verifyMSG3').css("display","block");
			$("#verifyMSG3").addClass("prompt_1 error_1").html("<em></em>请输入验证码！");
		}else if(!reg.test(verify)){
			verifyFlag = false;
			$('#verifyMSG3').css("display","block");
			$("#verifyMSG3").addClass("prompt_1 error_1").html("<em></em>验证码有误！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/verificationCode2.do?type=phone",
	        	dataType: "json",
	        	data:{
	        		verify:$("#verifyTest").val(),
	        		phone:$("#phoneUpdate").val()
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){
						verifyFlag = true;
						$('#verifyMSG3').css("display","none");
						$("#verifyMSG3").removeClass().html("");
	        		}else if(msg==0){
	        			verifyFlag = false;
	        			$('#verifyMSG3').css("display","");
	        			$("#verifyMSG3").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码有误！");
	        		}else if(msg==-1){
	        			verifyFlag = false;
	        			$('#verifyMSG3').css("display","");
	        			$("#verifyMSG3").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码超时，请点击重新发送!");
	        		}
	        	}
			});
		}
 	});
 	
 	$("#idcardOld").blur(function(){
		var idcard = $("#idcardOld").val();
		if(idcard==""){
			idcardFlag = false;
			$('#idcardMSG').css("display","");
			$("#idcardMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入您实名认证时填写的身份证号码！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/checkIdcard.do",
	        	dataType: "json",
	        	data:{
	        		idcard:$("#idcardOld").val()
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){
	        			idcardFlag = true;
	        			$('#idcardMSG').css("display","");
	        			$("#idcardMSG").removeClass().html("");
	        		}else if(msg==0){
	        			idcardFlag = false;
	        			$('#idcardMSG').css("display","");
	        			$("#idcardMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>身份证号码有误！");
	        		}
	        	}   
			}); 
		}
 	});
});

function click3(imgCode){
	var phone = $("#phoneUpdate").val();
	if(!regexInfo.mobileRegex.test(phone)){
		phoneFlag = false;
		$('#phoneMSG').css("display","");
		$("#phoneMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>手机号码格式不正确");
		return false;
	}
	/**
	 * 提交时没有token。通过访问URL直接提交借款Bug修复
	 */
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
	var param = {stok:stok,imgCode:imgCode};
	param.interval = 120;
	param.phone = phone;
	$.post("/msg/changePhoneSendTextMessage.do", param, function (result){
    	if(result == 'succ'){
    		$("#click3").hide();
			$("#countDown3").show();
			countDown3(120);//倒计时120秒
			//showMsg(textMsg);
			//$("#verifyMSG3").removeClass().html('<em></em>');
			$('#verifyMSG3').css("display","");
			$('#verifyMSG3').removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
		}else{
			$('#verifyMSG3').css("display","");
			$("#verifyMSG3").removeClass().addClass("prompt_1 error_1").html("<em></em>"+result);
		}
    });
	
}

function countDown3(time){
	$("#countDown3").html(time+"秒");
	 	time = time - 1;
	if(time>=0){
    	setTimeout("countDown3("+time+")",1000);
	}else{
    	$("#countDown3").hide();
    	$("#click3").show();
	}
}

function changePhoneNext(){
	$("#phoneUpdate").blur();
	$("#verifyTest").blur();
	$("#idcardOld").blur();
	if(phoneFlag==true && verifyFlag==true&&idcardFlag==true){
		$.ajax({
	    	type: "post",
	   	 	url: "/member/changeBasicInfoPwd.do",
	    	dataType: "json",
	    	data:{
	    		code:$("#verifyTest").val(),
	    		idcard:$("#idcardOld").val(),
	    		phone:$("#phoneUpdate").val(),
	    		type: "phone"
	   		 },
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(msg){
	    		if(msg == 1){
	    				$('#oldStep1').addClass('none');
	    				$('#oldStep2').removeClass('none');
	    				
	    				$('#changeOldStep1').attr("class","visited_a");
	        			$('#changeOldStep2').attr("class","active");
	    				
	    				
	    		}
	    	}
	    });
		//window.location.href='/member/changeBasicInfoPwd.do?code='+$("#verifyTest").val()+'&idcard='+$("#idcard").val()+'&type=phone&phone='+$("#phoneUpdate").val();
	}
}
