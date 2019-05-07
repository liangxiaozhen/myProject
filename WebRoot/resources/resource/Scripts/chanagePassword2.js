var pwdFlag = false;
var repwdFlag = false;
$(document).ready(function(){
	$("#pwd").blur(function(){
		var pwd = $("#pwd").val();
		if(pwd==""){
			pwdFlag = false;
			$('#pwdMSG').css("display","");
			$("#pwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请填写6-16位字符，由字母和数字组成！");
		}else if (!(pwd.match(/^[a-zA-Z0-9]{6,16}$/))||(pwd.length < 6)||(pwd.length > 20)) {
			pwdFlag = false;
			$('#pwdMSG').css("display","");
			$("#pwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请填写6-16位字符，由字母和数字组成！");
		}else{
			//交易密码不能与登录密码相同
			$.ajax({
	        	type: "post",
	       	 	url: "/member/memberCheckPwdAndRePwd.do",
	        	dataType: "json",
	        	data:{
	        		newPwd1:pwd,
	        		type:"repwd"
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 0){
	        			pwdFlag = true;
	        			$('#pwdMSG').css("display","");
	        			$("#pwdMSG").removeClass().html("");
	        		}else if(msg==1){
	        			pwdFlag = false;
	        			$('#pwdMSG').css("display","");
	        			$("#pwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>交易密码不能与登录密码相同！");
	        		}
	        	}   
	      }); 
		}
 	});
 	
 	$("#repwd").blur(function(){
 		var pwd = $("#pwd").val();
		var repwd = $("#repwd").val();
		if (repwd=="") {
			repwdFlag = false;
			$('#repwdMSG').css("display","");
			$("#repwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请再次输入交易密码！");
		}else if(repwd != pwd){
			repwdFlag = false;
			$('#repwdMSG').css("display","");
			$("#repwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>两次输入的密码不匹配！");
		}else{
			repwdFlag = true;
			$('#repwdMSG').css("display","");
			$("#repwdMSG").removeClass().html("");
		}
 	});
});

function SaveNext(){
	$("#pwd").blur();
	$("#repwd").blur();
	if(pwdFlag == true && repwdFlag == true){
        var stok = "";
        if(document.getElementById ("stok")){
            stok = document.getElementById ("stok").value;
        }
		$.ajax({
        	type: "post",
       	 	url: "/member/changePasswordSave.do",
        	dataType: "json",
        	data:{
        		pwd:$("#pwd").val(),
        		repwd:$("#repwd").val(),
        		type:$("#type2").val(),
                stok:stok
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){//保存成功
    				$('#rePwdStep1').attr("class","visited_a");
        			$('#rePwdStep2').attr("class","visited_a");
        			$('#rePwdStep3').attr("class","active");
        			
        			$('#findPwdErrMsg').html("交易密码找回成功！<br/>请牢记新密码");
        			
        			
        			$('#findPhoneStep1').addClass('none');
    				$('#findPhoneStep2').addClass('none');
    				$('#findPhoneTable2').addClass('none');
    				$('#stepHead').addClass('none');
    				$('#findPhoneStep3').removeClass('none');
    				setTimeout(function(){ 
    					location.reload();
        				},2000);
    				
        			//document.forms[0].action="/member/changePasswordNew.do";
        			//document.forms[0].submit();
        		}else if(msg == 0){
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码有误！");
        		}else if(msg == -1){
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码超时，请点击重新发送!");
        		}else if(msg == 2){
    				$('#rePwdStep1').attr("class","visited_a");
        			$('#rePwdStep2').attr("class","visited_a");
        			$('#rePwdStep3').attr("class","active");
        			
        			$('#findPwdErrMsg').html("交易密码找回失败！<br/>两次密码不匹配或密码不符合规则！");
        			
        			$('#findPhoneStep1').addClass('none');
    				$('#findPhoneStep2').addClass('none');
    				$('#findPhoneTable2').addClass('none');
    				$('#stepHead').addClass('none');
    				$('#findPhoneStep3').removeClass('none');
    				
    				setTimeout(function(){ 
    					location.reload();
        				},2000);
        		}else if(msg==-10){
        			//zhuzy 安全bug#10162
        			$('#repwdMSG').css("display","");
        			$("#repwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>验证身份失败，请重新验证！");
        		}
        	}   
      }); 
	
		//document.forms[0].action="/member/changePasswordSave.do?type=phone";
		//document.forms[0].submit();
	}
}