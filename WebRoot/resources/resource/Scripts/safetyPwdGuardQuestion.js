//以下是设置安全问题JS---START
$(document).ready(function(){
	
	//显示安全问题
    $.ajax({
		type : "POST", 
		url : "pwdGuardQuestion.do?method=findQuestionWin", 
		dataType:"json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(result) { 
			 $.each(result,function(key,values){
				var option = "<option value='"+key+"'>"+values+"</option>";
				$(".w212").append(option);
			}); 
		} 
	});
});
$("#ok").click(function() {
	$('#questionStep1').addClass('none');
	$('#questionStep2').addClass('none');
	$('#questionStep3').addClass('none');
	$('#questionStep4').removeClass('none');
	
	$('#safeQuestionStep1').attr("class","visited_a");
	$('#safeQuestionStep2').attr("class","visited_a");
	$('#safeQuestionStep3').attr("class","visited_a");
	$('#safeQuestionStep4').attr("class","active");
	$('#quverify').val();
	setTimeout(function(){
		 
	     $('#questionStep4').parents('tr').addClass('none');
	     $('#questionStep1').removeClass('none');
		 $('#questionStep4').addClass('none');
		 window.location.reload();
	     },2000)
});
$("#reset").click(function(){
	$("#doWhatUpdate").text('update');
});
function closeDialog() { 
	var list = art.dialog.list; 
	for (var i in list) { 
		list[i].close(); 
	}; 
}
function showMsg(msg,type){
	 if(type==9){
		 $('#msg_err').html('').show();
		 $('#msg_err').html(msg).show();
		 return false;
	 }
	 art.dialog.alert(msg).title('提示');
	 return false;
}

$("#questionSetNext").click(function(){
	var question1 = $("#questionOne option:selected").val();
	var question2 = $("#questionTwo option:selected").val();
	var question3 = $("#questionThree option:selected").val();

	var answer1=$("#answer1").val();
	answer1 = answer1.replace(/[ ]/g,"");
	var answer2=$("#answer2").val();
	answer2 = answer2.replace(/[ ]/g,"");
	var answer3=$("#answer3").val();
	answer3 = answer3.replace(/[ ]/g,"");
	if(question1==-1 || question2==-1 || question3==-1){
		$("#allMsgError").removeClass().addClass("left fs_122 w150").text("请选择密码保护问题!");
		$("#allMsgSite").css("margin-left","79px");
		return false;
	}
	if(answer1=="" || answer2=="" || answer3==""){
		$("#allMsgError").removeClass().addClass("left fs_122 w150").text("答案不能为空!");
		$("#allMsgSite").css("margin-left","79px");
		return false;
	}
	if(question1==question2 || question1==question3 || question3==question2){
		$("#allMsgError").removeClass().addClass("left fs_122 w150").text("密保问题不能相同!");
		$("#allMsgSite").css("margin-left","79px");
		return false;
	}
	var question=question1+","+question2+","+question3;
	var answer=answer1+","+answer2+","+answer3;
	$.ajax({
    	type: "post",
   	 	url: "pwdGuardQuestion.do?method=questionWin",
   	 	dataType:"json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
   	 	data : {question: question,
   	   	 		answer:answer
   	   	},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == 1){
    			$('#questionStep1').addClass('none');
	        	$('#questionStep2').addClass('none');
	        	$('#questionStep3').removeClass('none');
	        	
	        	$('#safeQuestionStep1').attr("class","visited_a");
	        	$('#safeQuestionStep2').attr("class","visited_a");
	        	$('#safeQuestionStep3').attr("class","active");
    			
    			$("#question2-1").val($("#questionOne option:selected").text());
    			$("#question2-2").val($("#questionTwo option:selected").text());
    			$("#question2-3").val($("#questionThree option:selected").text());
    			$("#answer2-1").val(answer1);
    			$("#answer2-2").val(answer2);
    			$("#answer2-3").val(answer3);
    			
    		}else if(msg == 2){
    			$("#allMsgError").removeClass().addClass("left fs_122 w150").text("密保问题不能相同!");
    			$("#allMsgSite").css("margin-left","79px");
    		}else if(msg == 3){
    			$("#allMsgError").removeClass().addClass("left fs_122 w150").text("答案不能为空!");
    			$("#allMsgSite").css("margin-left","79px");
    		}else if(msg == 4){
    			$("#allMsgError").removeClass().addClass("left fs_122 w150").text("请选择密码保护问题!");
    			$("#allMsgSite").css("margin-left","79px");
    		}else if(msg == 5){
    			$("#allMsgError").removeClass().addClass("left fs_122 w150").text("短信验证码失效,请返回第一步重新验证!");
    			$("#allMsgSite").css("margin-left","0px");
    		}else{
    			$("#allMsgError").removeClass().addClass("left fs_122 w150").text("保存失败!");
    			$("#allMsgSite").css("margin-left","79px");
    		}
    	}   
  });
});
//安全问题JS---------END