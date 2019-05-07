var emailFlag = false;
var async = true;
$(document).ready(function(){
	$("#emailNew").blur(function(){
		var email = $("#emailNew").val();
		var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+(com|net|com.cn|net.cn|cn)$/;
		if(email==""){
			emailFlag = false;
			$('#emailMSG').css("display","");
			$("#emailMSG").html("<i></i>邮箱地址不为空");
		}else if(!reg.test(email)){
			emailFlag = false;
			$('#emailMSG').css("display","");
			$("#emailMSG").html("<i></i>邮箱地址格式不正确");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/memberCheckEmail.do",
	        	dataType: "json",
	        	data:{
	        		email:$("#emailNew").val()
	       		 },
	        	async: async,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 0){
	        		 	emailFlag = true;
						$('#emailMSG').css("display","none");
						$("#emailMSG").html("");
	        		}else if(msg==1){
	        			emailFlag = false;
						$('#emailMSG').css("display","");
						$("#emailMSG").html("<i></i>您输入的邮箱已存在");
	        		}
	        	}   
	      }); 	
		}
 	});
	
});


function changeEmailNext2(){
	async = false;
	$("#emailNew").blur();
	async = true;
	if(emailFlag==true){

		$.ajax({
        	type: "post",
       	 	url: "/member/changeBasicInfoName.do",
       	 	dataType:'JSON',
        	data:{
        		type:'email',
        		email:$('#emailNew').val(),
        		oldEmail:$('#oEmail').val()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        			if(msg['code']==0){
        				$('#emailStep_1').attr("class","visited_a");
            			$('#emailStep_2').attr("class","active");
            			
            			$('#emailChange1').addClass("none");
            			$('#emailChange2').addClass("none");
            			$('#emailChange3').removeClass("none");
            			$('#myNewEmail').text(msg['email']);
            			
            			$('#mbEmail').val("");
            			
            			setTimeout(function(){ 
            				$('#emailStep_1').attr("class","active");
                			$('#emailStep_2').attr("class","");
                			$('#emailStep_3').attr("class","");
                			
                			$('#emailSuccess').addClass("none");
                			$('#emailChange3').addClass("none");
                			$('#emailChange1').removeClass("none");
            				},2000);
        			}else{
        				alert('邮箱错误');
        				$('#emailMSG').css("display","");
        				$("#emailMSG").html("<i></i>邮箱验证错误,请返回重新验证");
        			}
        	}   
		});
		//submitForm.action = "/member/changeBasicInfoName.do?type=email";
		//submitForm.submit();
	
		
	}
}
function sendEmail(){
	$.ajax({
    	type: "post",
   	 	url: "/member/memberSendEmailByType.do",
    	dataType: "json",
    	data:{
    		type:"changeEmail"
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == 1){
    			document.forms[0].action="/member/changeBasicInfoPwd.do?type=email";
    			document.forms[0].submit();
    		}else if(msg==0){
    			$('#verifyMSG').css("display","");
				$("#verifyMSG").html("<i></i>邮件发送失败，请再点击！");
    		}
    	}   
  });
}