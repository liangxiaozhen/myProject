var answer0Flag = false;
var answer1Flag = false;
$(document).ready(function(){
	$("#answerPwd0").blur(function(){
		var answer0 = $("#answerPwd0").val();
		if(answer0==""){
			answer0Flag = false;
			$('#answer0MSG').css("display","");
			$("#answer0MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入问题一答案！");
		}else{
			answer0Flag = true;
			$('#answer0MSG').css("display","none");
			$("#answer0MSG").removeClass().html("");
		}
 	});
	
	$("#answerPwd1").blur(function(){
		var answer1 = $("#answerPwd1").val();
		if(answer1==""){
			answer1Flag = false;
			$('#answer1MSG').css("display","");
			$("#answer1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入问题二答案！");
		}else{
			answer1Flag = true;
			$('#answer1MSG').css("display","none");
			$("#answer1MSG").removeClass().html("");
		}
 	});
});

function questNext(){
	$("#answerPwd0").blur();
	$("#answerPwd1").blur();
	if(answer1Flag==true && answer0Flag==true){
		if(getCookie("answerCount"+$('#mbId').val()+"")==""){//存在
			setCookie("answerCount"+$('#mbId').val()+"",5);//初始化为5
		}
		$.ajax({
        	type: "post",
       	 	url: "/member/questionAndAnswer.do",
        	dataType: "json",
        	data:{
        		answer0:$("#answerPwd0").val(),
        		answer1:$("#answerPwd1").val(),
        		id0:$("#questid1").val(),
        		id1:$("#questid2").val()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){//相等
        			if(getCookie("answerCount"+$('#mbId').val()+"") > 0){
        				$('#rePwdStep1').attr("class","visited_a");
            			$('#rePwdStep2').attr("class","active");
            			
            			$('#findPhoneStep1').addClass('none');
        				$('#findPhoneStep2').removeClass('none');
        				$('#findPhoneTable2').removeClass('none');
        				$('#findQuestionTableStep').addClass('none');
        				
        				$("#type2").val('answer');
        				//document.forms[0].action="/member/changePasswordNew.do";
            			//document.forms[0].submit();
        			}else{
        				$(".plusBank1 .content").html('对不起，您今天已输错5次，请明日再试');
            			showCon_1();
        			}
        		}else if(msg == 0){
        			$('#answer1MSG').css("display","");
        				if(getCookie("answerCount"+$('#mbId').val()+"") <= 0){
        					$(".plusBank1 .content").html('对不起，您今天已输错5次，请明日再试');
                			showCon_1();
                		}else{
                			setCookie("answerCount"+$('#mbId').val()+"",getCookie("answerCount"+$('#mbId').val()+"")-1);
                			$(".plusBank1 .content").html("回答错误，您还剩"+getCookie("answerCount"+$('#mbId').val()+"")+"次输入机会，请重新输入！");
                			showCon_1();
                		}
        		}
        	}   
      }); 
		$("#answerPwd0").blur();
		$("#answerPwd1").blur();
	}
}

function setCookie(name, value, d) {
	var Days = 30;
	var exp = new Date();
	if (d > 0)
		Days = d;
	exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value)
			+ ";path=/;domain=.niwodai.com;expires=" + exp.toGMTString();
}
//读取cookies 
function getCookie(name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(name + "=")
		if (c_start != -1) {
			c_start = c_start + name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return "";

}
//删除cookies 
function delCookie(name) {
	setCookie(name, '');
}
