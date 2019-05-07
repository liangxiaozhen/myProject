var idCardFlag = false;
var verifyFlag = false;

//找回交易密码选择方式
function chooseWay(way){
	var certIdentity = $("#certIdentity").val();
	if(way == 'phone'){
		if(certIdentity==0){
			// 弹框优化 | zhenhua.xi | 20140917
			$(".plusBank1 .content").html('<i class="ico_all size24 img_false24"></i>为保障您的账户安全，修改手机前需要实名认证！');
			$("#btn_0919").val("立即认证");
			$("#btn_0919").click(function(){
				location.href="/member/identityAuthentication.do";
			});
			showCon_1();
		}else if(certIdentity==1){
			$('#findRePwdChoose').addClass('none');
			$('#findHead').removeClass('none');
			$('#findPhoneStep1').removeClass('none');
			$('#findTableStep1').removeClass('none');
			//点击立即修改清空
			$('#verify').val('');
			$('#idCard').val('');
			$('#verifyMSG').css("display","none");
			$("#verifyMSG").removeClass().html("");
			$('#idCardMSG').css("display","none");
			$("#idCardMSG").removeClass().html("");
			//stepHead添加步骤头部
			$("#stepHead").removeClass('none');
		}
		
	}else if(way == 'question'){
		var pwdQuestion = $("#pwdQuestion").val();
		if(pwdQuestion==0){
			// 弹框优化 | zhenhua.xi | 20140917
			$(".plusBank1 .content").html('<i class="ico_all size24 img_false24"></i>为保障您的账户安全，请先设置安全保护问题！');
			$("#btn_0919").val("确定");
			$("#btn_0919").click(function(){
				location.href="/member/safetyLevel.do?doWhat=sl";
			});
			showCon_1();
		}else if(pwdQuestion==1){
			$('#findRePwdChoose').addClass('none');
			$('#findHead').removeClass('none');
			$('#findQuestionTableStep').removeClass('none');
			//点击立即修改清空
			$('#answerPwd0').val('');
			$('#answerPwd1').val('');
			$('#answer0MSG').css("display","none");
			$("#answer0MSG").removeClass().html("");
			$('#answer1MSG').css("display","none");
			$("#answer1MSG").removeClass().html("");
			//stepHead添加步骤头部
			$("#stepHead").removeClass('none');
			//获取安全问题
			$.ajax({
	        	type: "post",
	       	 	url: "/member/changePasswordVerify.do",
	        	dataType: "json",
	        	data:{
	        		type:'quest'
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(result){
	        		var quest1 = result['0']['question'];
	        		var quest2 = result['1']['question'];
	        		$('#quest1').val(quest1);
	        		$('#quest2').val(quest2);
	        		
	        		$('#questid1').val(result['0']['questionId']);
	        		$('#questid2').val(result['1']['questionId']);
	        		
	        		$('#mbId').val(result['0']['uid']);
	        	}   
	      }); 
		}
	}
}
$(document).ready(function(){
	var flag = $("#flag").val();
    if(flag == 'phone'){//绑定手机
    	$(".plusBank1 .content").html('添加银行卡前，请先绑定手机号！');
		showCon_1();
    }else if(flag == 'iden'){
    	$(".plusBank1 .content").html('添加银行卡前，请先实名认证！');
		showCon_1();
    }
	
 	$("#verify").blur(function(){
		var verify = $("#verify").val();
		var reg = /^\S{6}$/;
		if(verify==""){
			verifyFlag = false;
			$('#verifyMSG').css("display","");
			$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入验证码！");
		}else if(!reg.test(verify)){
			verifyFlag = false;
			$('#verifyMSG').css("display","");
			$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码有误！");
		}else{
			verifyFlag = true;
			$('#verifyMSG').css("display","none");
			$("#verifyMSG").removeClass().html("");
		}
 	});
 	
 	$("#idCard").blur(function(){
		var idCard = $("#idCard").val();
		var IdType = $("#IdType").val();
		var reg = /^(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		if(idCard==""){
			idCardFlag = false;
			$('#idCardMSG').css("display","");
			$("#idCardMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入您实名认证时填写的身份证号码！");
		}else if(!reg.test(idCard) && IdType==0){
			idCardFlag = false;
			$('#idCardMSG').css("display","");
			$("#idCardMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>身份证号码有误！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/compareIdCard.do",
	        	dataType: "json",
	        	data:{
	        		idCard:idCard
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){//相等
	        			idCardFlag = true;
	        			$('#idCardMSG').css("display","");
	        			$("#idCardMSG").removeClass().addClass("prompt prompt-green").html("<em></em>");
	        		}else if(msg == 0){
	        			idCardFlag = false;
	        			$('#idCardMSG').css("display","");
	        			$("#idCardMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>身份证号码有误！");
	        		}
	        	}   
	      }); 
		}
 	});
});

function click2(imgCode){
		/**
		 * 提交时没有token。通过访问URL直接提交借款Bug修复
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
        		phone:$("#phone").val(),
        		type:"findSmsVcodeRecord",
        		imgCode:imgCode,
        		stok:stok
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){
        		 	$("#click2").hide();
					$("#countDown2").show();
					countDown2(120);//倒计时120秒
					// 弹框优化 | zhenhua.xi | 20140915
					// showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
        		}else if(msg==0){
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>手机信息发送失败，请点击！");
        		}else if(msg==2){
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>手机信息发送失败，验证码错误，请再次请点击！");
        		}
        	}   
      }); 
}

function countDown2(time){
	$("#countDown2").html(time+"秒");
	 	time = time - 1;
	if(time>=0){
    	setTimeout("countDown2("+time+")",1000);
	}else{
    	$("#countDown2").hide();
    	$("#click2").show();
	}
}

function next(){
	$("#idCard").blur();
	$("#verify").blur();
	if(idCardFlag==true && verifyFlag==true){
		$.ajax({
        	type: "post",
       	 	url: "/member/verificationCode1.do?type=forg",
        	dataType: "json",
        	data:{
        		verify:$("#verify").val(),
        		phone:$("#phone").val()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){//相等
    				$('#rePwdStep1').attr("class","visited_a");
        			$('#rePwdStep2').attr("class","active");
        			
        			$('#findPhoneStep1').addClass('none');
    				$('#findPhoneStep2').removeClass('none');
    				$('#findPhoneTable2').removeClass('none');
    				
    				$("#type2").val('phone');
        			//document.forms[0].action="/member/changePasswordNew.do";
        			//document.forms[0].submit();
        		}else if(msg == 0){
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码有误！");
        		}else if(msg == -1){
        			$('#verifyMSG').css("display","");
					$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>验证码超时，请点击重新发送!");
        		}
        	}   
      }); 
	}
}
