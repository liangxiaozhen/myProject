$(document).ready(function(){ 
	var cur_dh = $('#xinxi_nwd_8'); 
	cur_dh.addClass('active'); 
	cur_dh.parent('ul').prev('h4').attr('class','blue-minus'); 
});

$("#quverify").blur(function(){
	var verify = $("#quverify").val();
	var reg = /^\S{6}$/;
	if(verify==""){
		$('#verifyMSG6').removeClass().addClass("prompt_1 error_1").css("display","block");
		$("#verifyMSG6").html("<em></em>请输入验证码！");
	}else if(!reg.test(verify)){
		$('#verifyMSG6').removeClass().addClass("prompt_1 error_1").css("display","block");
		$("#verifyMSG6").html("<em></em>验证码有误！");
	}else{
		$('#verifyMSG6').css("display","none");
		$("#verifyMSG6").html("");
	}
	});


$("#questionNext").click(function(){
	$("#quverify").blur();
		$.ajax({
        	type: "post",
       	 	url: "pwdGuardQuestion.do?method=gainValidate",
        	dataType: "json",
        	data:{
        		verify:$("#quverify").val()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){//相等
        			if($("#doWhatUpdate").text() == 'update'){
        				$('#questionStep1').addClass('none');
	        			$('#questionStep2').removeClass('none');
	        			$('#safeQuestionStep1').attr("class","visited_a");
	        			$('#safeQuestionStep2').attr("class","active");
        				//window.location = "pwdGuardQuestion.do?method=findQuestion&doWhat=update";
        			}else {
        				$('#questionStep1').addClass('none');
	        			$('#questionStep2').removeClass('none');
	        			$('#safeQuestionStep1').attr("class","visited_a");
	        			$('#safeQuestionStep2').attr("class","active");
        				//window.location = "pwdGuardQuestion.do?method=findQuestion";
        			}
        		}else if(msg == 0){
        			$('#verifyMSG6').css("display","");
					$("#verifyMSG6").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
        		}else if(msg == -1){
        			$('#verifyMSG6').css("display","");
					$("#verifyMSG6").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码超时，请点击重新发送！");
        		}
        	}   
      });
});
//发送验证码
//$("#questionGain").click(function(){
function clickquestionGain(imgCode){
	/**
	 * 提交时没有token。通过访问URL直接提交借款Bug修复
	 */
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
	$.ajax({
    	type: "post",
   	 	url: "pwdGuardQuestion.do?method=gain",
    	dataType: "json",
    	data:{
    		imgCode:imgCode,
    		stok:stok
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == 1){
			 	$("#questionGain").hide();
				$("#countDown6").show();
				countDown6(120);//倒计时120秒
				// 弹框优化 | zhenhua.xi | 20140915
				// showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
    			$('#verifyMSG6').css("display","");
				$("#verifyMSG6").removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
    		}else if(msg==0){
    			$('#verifyMSG6').css("display","");
				$("#verifyMSG6").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，请点击！");
    		}else if(msg==2){
    			$('#verifyMSG6').css("display","");
				$("#verifyMSG6").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，验证码错误，请再次请点击！");
    		}
    	}   
  }); 
};
function countDown6(time){
	$("#countDown6").html(time+"秒");
	 	time = time - 1;
	if(time>=0){
    	setTimeout("countDown6("+time+")",1000);
	}else{
    	$("#countDown6").hide();
    	$("#questionGain").show();
	}
}