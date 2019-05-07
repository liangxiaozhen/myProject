var emailFlag = false;
var async = true;
$(document).ready(function(){
	$("#recordEmail").blur(function(){
		var email = $("#recordEmail").val();
		var reg = regexInfo.emailRegex;
		if(email==""){
			emailFlag = false;
			$('#errorMSG').css("display","");
			$("#errorMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入邮箱地址！");
			return false;
		}else if(!reg.test(email)){
			emailFlag = false;
			$('#errorMSG').css("display","");
			$("#errorMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>邮箱地址格式有误！");
			return false;
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/memberCheckEmail.do",
	        	dataType: "json",
	        	data:{
	        		email:$("#recordEmail").val()
	       		 },
	        	async: async,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 0){
	        		 	emailFlag = true;
						$('#errorMSG').css("display","");
						$("#errorMSG").removeClass().html("");
	        		}else if(msg==1){
	        			emailFlag = false;
						$('#errorMSG').css("display","");
						$("#errorMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>您输入的邮箱已存在！");
	        		}
	        	},
	        	error:function(){
	        		emailFlag = false;
					$('#errorMSG').css("display","");
					$("#errorMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>操作超时，请重新登录");
	        	}
	      }); 	
		}
 	});
	
});
/*function next(){
	async = false;
	$("#recordEmail").blur();
	async = true;
	if(emailFlag==true){
		submitForm.submit();
	}	
}*/
/* 电子邮箱绑定第一步
 * @athor:added by sunyang 
 * */
function mailboxSutepNext1(){
	var recordEmail = $('#recordEmail').val();
	if(emailFlag==true){
        var stok = "";
        if(document.getElementById ("stok")){
            stok  = document.getElementById ("stok").value;
        }
        $.ajax({
    	type: "post",
   	 	url: "/member/nwdEmailVcodeRecordSend.do",
    	dataType: "json",
    	data:{
    		recordEmail:recordEmail,
            stok:stok
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == 1){//输入邮箱地址  成功
    			$('#email').val(recordEmail);
    			$('#myEmail').text(recordEmail);
    			$('#recordEmail').val('');
    			$('#mailboxSet1').addClass('none');
    			$('#mailboxSet2').removeClass('none');
    			$('#step_1').attr("class","visited_a");
    			$('#step_2').attr("class","active");
    			setTimeout(function(){
    				$('#sucessPwd').parents('tr').addClass('none');
    				$('#loginPwd').removeClass('none');
        			$('#sucessPwd').addClass('none');
    			},2000)
    		}else{//登录密码  失败
    			$(".imgCodeMSG").html('电子邮箱绑定失败!请重试');
    			showCon_1();
    			//window.location.href="/member/changeBasicInfoPwd.do?type=pwd&doWhat=failure";
    		}
    	}   
	});
	}
}