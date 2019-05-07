var async = true;
function changeEmailNext(){
	async = false;
	$("#mbEmail").blur();
	async = true;
	if(mbEmailFlag==true){
		$.ajax({
        	type: "post",
       	 	url: "/member/changeBasicInfoPwd.do",
        	dataType: "json",
        	data:{
        		type:'email',
        		mbEmail:$('#mbEmail').val()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg['ress'] == 1){
        			$('#emailStep_1').attr("class","visited_a");
        			$('#emailStep_2').attr("class","active");
        			
        			$('#emailChange1').addClass("none");
        			$('#emailChange2').removeClass("none");
        			$('#oEmail').val(msg['mbEmail']);
        		}
        	}   
		});
		//document.forms[0].action="/member/changeBasicInfoPwd.do?type=email";
		//document.forms[0].submit();
	}
}
var mbEmailFlag = false;
function checkEmail(){
	var mbEmail = $("#mbEmail").val();
	var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+(com|net|com.cn|net.cn|cn)$/;
	if(mbEmail==""){
		mbEmailFlag = false;
		$('#mbEmailMSG').css("display","");
		$("#mbEmailMSG").html("<em></em>邮箱不能为空");
	}else if(!reg.test(mbEmail)){
		mbEmailFlag = false;
		$('#mbEmailMSG').css("display","");
		$("#mbEmailMSG").html("<em></em>邮箱格式错误");
	}else{
		$.ajax({
        	type: "post",
       	 	url: "/member/changeEmailCheckEmail.do",
        	dataType: "json",
        	data:{
        		mbEmail:$("#mbEmail").val()
       		 },
        	async: async,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){
        			mbEmailFlag = true;
        			$('#mbEmailMSG').css("display","none");
        			$("#mbEmailMSG").html("");
        		}else if(msg==0){
        			mbEmailFlag = false;
        			$('#mbEmailMSG').css("display","");
        			$("#mbEmailMSG").html("<em></em>邮箱验证错误");
        		}
        	}   
		}); 
	}
}