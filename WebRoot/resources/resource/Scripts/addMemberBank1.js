var verifyFlag = false;
var idCardFlag = true;

$(document).ready(function(){
	
	showDiv();
	function showDiv(obj){
		$(obj).show();
		center(obj);
		$(window).scroll(function(){
			center(obj);
		});
		$(window).resize(function(){
			center(obj);
		}); 
	}
	function center(obj){
		var windowWidth = document.documentElement.clientWidth;   
		var windowHeight = document.documentElement.clientHeight;   
		var popupHeight = $(obj).height();   
		var popupWidth = $(obj).width();    
		$(obj).css({   
			"position": "absolute",   
			"top": (windowHeight-popupHeight)/2+$(document).scrollTop(),   
			"left": (windowWidth-popupWidth)/2   
		});  
	}
	
	//添加
	$("#addBank").click(function(){
	    var flag = $("#flagRe").val();
	    if(flag == 'phone'){//绑定手机
	    	$("#plusBank").html('添加银行卡前，请先开通第三方账号');
			$("#identification,.bg").show();
			showDiv("#identification");
			$("#ascert").attr("href",basePath+"/user/securitycenter/list.action");
	    }else if(flag == 'iden'){
	    	$("#plusBank").html('绑定银行卡之前，请先实名认证！');
			$("#identification,.bg").show();
			showDiv("#identification");
			$("#ascert").attr("href",basePath+"/user/securitycenter/realNameAuthenticationlist.action");
	    }else{
			$("#addBankcard-pop-tx,.bg").show();
			showDiv("#addBankcard-pop-tx");
	    }
	})
	
	//删除
	$("#deleteBank-btn").click(function(){
		$("#deleteBan-pop-manage,.bg").show();
		showDiv("#deleteBan-pop-manage");
	})
	
	// 短信验证码非空校验、正则校验
	$("#verify").blur(function(){
		var verify = $("#verify").val();
		var reg = /^\S{6}$/;
		if(verify==""){
			verifyFlag = false;
			$('#verifyMSG').css("display","");
			$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入验证码！");
		}else if(!reg.test(verify)){
			verifyFlag = false;
			$('#verifyMSG').css("display","");
			$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入6位数短信验证码！");
		}else{
			verifyFlag = true;
			$('#verifyMSG').css("display","none");
			$("#verifyMSG").html("");
		}
 	});
	
//	// 卡号非空校验、正则校验、后台校验
//	$("#idCard").blur(function(){
//		var idCard = $("#idCard").val();
//		var reg = /^(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
//		if(idCard==""){
//			idCardFlag = false;
//			$('#idCardMSG').css("display","");
//			$("#idCardMSG").html("<i></i>请输入您实名认证时填写的身份证号码！");
//		}else if(!reg.test(idCard)){
//			idCardFlag = false;
//			$('#idCardMSG').css("display","");
//			$("#idCardMSG").html("<i></i>身份证号码有误！");
//		}else{
//			$.ajax({
//	        	type: "post",
//	       	 	url: "compareIdCard.action",
//	        	dataType: "json",
//	        	data:{
//	        		idCard:idCard
//	       		 },
//	        	async: false,
//	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
//	        	success: function(msg){
//	        		if(msg == 1){//相等
//	        			idCardFlag = true;
//	        			$('#idCardMSG').css("display","none");
//	        			$("#idCardMSG").html("");
//	        		}else if(msg == 0){
//	        			idCardFlag = false;
//	        			$('#idCardMSG').css("display","");
//	        			$("#idCardMSG").html("<i></i>身份证号码有误！");
//	        		}
//	        	}   
//	      }); 
//		}
// 	});
	
});

//发送验证码
function gain(){
	$.ajax({
    	type: "post",
   	 	url: "sendMsg.action",
    	dataType: "json",
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == 1){
				// 弹框优化 | zhenhua.xi | 20140915
				// showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
    			$('#verifyMSG').css("display","");
				$("#verifyMSG").removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期3分钟，请查收");
				addCookie("banksmscode", 120, 120);
				//几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
				timer(120,"click1","countDown","gain");
    		}else if(msg == 0){
    			$('#verifyMSG').css("display","");
				$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，请再次请点击！");
    		}else if(msg == -1){
    			$('#verifyMSG').css("display","");
				$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>手机号码未验证！请先验证手机号码！");
    		}else if(msg == "logout"){
    			if(confirm("是否重新登录？")){
    				window.location.href=basePath+"/user/tologin.action";
    			}
    		}
    	}   
	}); 
}

/** 身份验证（验证短信验证码） */ 
function addBankNext(){
	$("#verify").blur();
  	if(verifyFlag==true){
		if(isEmpty($("#verify").val())){
        	$("#verifyimgCode").show();
        	$("#verifyimgCode").html("<i></i>请输入短信验证码！");
        	return ;
		}
		//短信验证码校验
		$.ajax({
            type: "post",
            url: "checkSsmCode.action",
            dataType: "json",
            data:{
            	code:$("#verify").val()
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function(msg){
                if(msg == 1){//相等
                    //跳转第二步
            		$(".boxs2").show();
            		$(".boxs1").hide();
            		$("h1.tabs span").removeClass("active-title")
            		$("h1.tabs span[name=2]").addClass("active-title");
                }else if(msg == 0){
                	$("#verifyMSG").show();
                	$("#verifyMSG").html("<i></i>短信验证码错误！");
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
                alert(textStatus);
            }
        });
	}
}



//几秒后可以发送短信 timer时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
function timer(timer,btnId,tipsId,clickName){
	timer = getCookieValue("banksmscode") ? getCookieValue("banksmscode"):timer;
	// 隐藏短信发送框
	$("#"+btnId+"").hide();
	// 显示正在发送框
	$("#"+tipsId+"").show();
	$("#"+tipsId+"").val(timer+"秒");
	var senderTime = setInterval(function(){
		if(timer <= 0 ){
			clearInterval(senderTime);
			//显示短信发送框
			$("#"+btnId+"").show();
			$("#"+tipsId+"").hide();
			$("#"+btnId+"").text("获取验证码");
			//恢复发送短信点击事件
			$("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			return false;
		}else{
			$("#"+tipsId+"").val((timer--)+"秒");
			editCookie("banksmscode",timer,timer+1);
		}
	},1000);
}

//发送手机验证码时添加cookie
function addCookie(name,value,expiresHours){
	var cookieString=name+"="+escape(value); 
	//判断是否设置过期时间,0代表关闭浏览器时失效
	if(expiresHours>0){ 
		var date=new Date(); 
		date.setTime(date.getTime()+expiresHours*1000); 
		cookieString=cookieString+";expires=" + date.toUTCString(); 
	} 
	document.cookie=cookieString; 
}

//修改cookie的值
function editCookie(name,value,expiresHours){ 
	var cookieString=name+"="+escape(value); 
	if(expiresHours>0){ 
		var date=new Date(); 
		date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
		cookieString=cookieString+";expires=" + date.toGMTString(); 
	} 
	document.cookie=cookieString; 
}

//根据名字获取cookie的值
function getCookieValue(name){ 
	var strCookie=document.cookie; 
	var arrCookie=strCookie.split("; "); 
	for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]==name){
			return unescape(arr[1]);
			break;
		} 
	} 
}

/*空判断*/
function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}

/*非空判断*/
function isNotEmpty(val) {
	return !isEmpty(val);
}