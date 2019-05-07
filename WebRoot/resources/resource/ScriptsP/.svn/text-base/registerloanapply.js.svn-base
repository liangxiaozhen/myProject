$(document).ready(function(){
	$("label[class^='input_text']").addClass(function(index,cla){
		$(this).removeClass();
		return "input_text yzm3";;
	});
	/*
	 * 手机号输入框失去焦点的校验
	 */
	$("#mobile").blur(function(){
		mobileCh();
	});
	/***
	 * 验证码
	 */
	$("#imgcode").blur(function(){
		imgcodeCk();
	});
	/***
	 * 手机验证码
	 */
	$("#getPhonecode").click(function(){
		getPhoneCode();
	});
	/***
	 * 注册
	 */
	$("#submitBtn").click(function(){
		submitBtn();
	});
	//从后台取得是否有登录
	$.post("/baiduFina/getIsLoginFlag.do",function(data){
		if(data=='needLogin'){
			$("#tab1").show();
			$("#tab2").hide();
			mid=0;
		}else{
			mid=1;
			$("#tab1").hide();
			$("#tab2").show();
		}
	});
	$.ajax({  
		type : "post", 
		url : "/index.do?method=setGotoUrl", 
		contentType : "application/x-www-form-urlencoded; charset=UTF-8", 
		data:{"gotourl":encodeURIComponent("http://www.niwodai.com/daikuan/jiekuan.html")}, 
		dataType:"json", 
		success : function(data) { 
		} 
		}); 
	$.ajax({ 
		type : "post", 
		url : "/transactionDetails", 
		contentType : "application/x-www-form-urlencoded; charset=UTF-8", 
		dataType:"json", 
		success : function(data) {
			$("#jkrs1").html(data.totalBorrower);//成功借款人数
			$("#jkrs2").html(data.totalBorrower);//成功借款人数
		} 
		});	
});
var mobileFlag=false,//手机号码验证通过标识
imgCodeFlag=false,//图形验证码验证通过标识
phonecodeFlag = false;//手机短信验证码验证通过标识

var mid=0;


/**
 * 验证错误提示
 * @param id
 * @param msg
 */
function showErr(id,msg){
	$("#"+id+"Msg").html('<i class="ico ico_s14 ico3"></i>'+msg);
	$("#"+id+"Msg").css("display","block");
}
/***
 * 验证提示
 * @param id
 * @param msg
 */
function showMsg(id,msg){
	$("#"+id+"Msg").html(msg);
	$("#"+id+"Msg").css("display","block");
}
/***
 * 清除验证提示
 * @param id
 */
function validatePass(id){
	$("#"+id+"Msg").css("display","none");
	$('.jy_info').css('visibility','hidden');
}

/***
 * 手机号码验证
 * @returns
 */
function mobileCh(){
	//取得输入用户名的值
	var mobileVal = $("#mobile").val();
	if(mobileVal == ''){//手机号为空
		mobileFlag = false;
		$('.jy_info').text('手机号码不能为空').css('visibility','visible');
   	 	return false;
	}else if(mobileVal.length != 11){//密码长度为6~20位字符
		repwdFlag = false;
		$('.jy_info').text('请输入11位手机号码').css('visibility','visible');
   	 	return false;
	}else if(!mobileVal.match("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[6|7|8])|18[0-9])\\d{8}|(170\\d{8})$")){//手机号码格式错误
		mobileFlag = false;
		$('.jy_info').text('手机号码格式错误').css('visibility','visible');
   	 	return false;
	}else{//以上验证通过
		if(mid>0){
			mobileFlag = true;
			$('.jy_info').css('visibility','hidden');
	        return true;
		} 
	}
}
var dropTimeoutId;
/*
 * 获取手机短信倒计时
 */
function countDown(time){
	$("#getPhonecode").attr('disabled',true);//按钮不可用
	$("#getPhonecode").css('display','none');//按钮不可用
	$("#getPhonecoding").attr('disabled',false);//按钮可用
	$("#getPhonecoding").css('display','block');//按钮可用
    $("#getPhonecoding").text(time+"秒后可重发");
    time = time - 1;
    if(time>=0){
    	dropTimeoutId=setTimeout("countDown("+time+")",1000);
    }else{
    	$("#getPhonecode").text("重新获取");
    	$("#getPhonecode").attr('disabled',false);//按钮可用
    	$("#getPhonecode").css('display','block');//按钮可用
    	$("#getPhonecoding").attr('disabled',true);//按钮不可用
    	$("#getPhonecoding").css('display','none');//按钮不可用
    	showMsg("phoneCode2","如果未收到验证码，请重新获取");
    	//验证码刷新
    	//refreshcode(false);
    }
}
/***
 * 停止
 * @param id
 */
function cleanTimeout(){
	if(dropTimeoutId != undefined){
		clearTimeout(dropTimeoutId);
	}
}
/***
 * 获取手机验证码
 */
function getPhoneCode(){
	validatePass("phoneCode2");
	if(mobileFlag == true){
		//写临时表
		$.ajax({  
			type : "post",  
			url : "/index.do?method=preReg&quick=quick",  
			data :{
	        	regFrom: 0,
	    		mobile: $('#mobile').val(),
	    		imgCode: $('#imgcode').val()
	        },
			async : false,  
			success : function(result){
				$('#uname').val(result['uname']);
		    	$('#pwd').val(result['pwd']);
		    	$('#repwd').val(result['pwd']);
	    	    $.post("/msg/sendRegTextMessage.do?interval=60", {phone: $('#mobile').val(),imgCode: $("#imgcode").val()}, function (result){
	    	    	if(result == 'succ'){
	    	    		countDown(60);//倒计时,60s动画展示
	    	    		validatePass("phoneCode");
	    	    		showMsg("phoneCode2","短信已成功发送...");
	    			}else if(result == '该手机号码已经注册'){
	    				window.location.href="/login.html";
	    			}else if(result == '手机号码格式错误'){
	    				$('.jy_info').text('手机号码格式错误').css('visibility','visible');
	    				//验证码刷新
	    		    	refreshcode(true);
	    			}else if(result == '图片验证码超时,请刷新！'){
	    				$('.jy_info').text('验证码已失效，请输入新的图形验证码').css('visibility','visible');
	    				//验证码刷新
	    		    	refreshcode(true);
	    			}else if(result == '请输入正确的图片验证码'){
	    				$('.jy_info').text('请输入正确的图片验证码').css('visibility','visible');
	    				//验证码刷新
	    		    	refreshcode(true);
	    			}else{
	    				$('.jy_info').text(result).css('visibility','visible');
	    				//验证码刷新
	    		    	refreshcode(true);
	    			}
	    	    });
	             
			},error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("请求失败");
			}  
	     });  
	}else if(mobileFlag == false){
		$('.jy_info').text("请填写您的真实手机号码").css('visibility','visible');
	}/*else if(mobileFlag == true && imgCodeFlag == false){
		showErr("imgcode","请填写正确的验证码");
	}*/else{
		$('.jy_info').text("验证出错了").css('visibility','visible');
	}
}
/*
 * added by fanghui 2014.11.17
 * 点击图形验证码时的事件处理
 */
$("#imgc").click(function(event){
	event.preventDefault();//阻止冒泡,因为图形验证码是定义在文本框内部的
	refreshcode(true);
});
/*
 * added by fanghui 2014.11.17
 * 换一张图片验证码
 */
function refreshcode(flag){
	    validatePass("phoneCode2");
		var d = new Date();
		var src = "/validatecode/refresh.htm?date=" + d.getTime();
		$("#imgc").attr("src",src);
		if(flag == true){
			//图片验证码输入框清空
			$('#imgcode').val("");
		}
}
/*
 * added by fanghui 2014.11.17
 * 图片验证码输入框失去焦点的校验
 */
function imgcodeCk(){
	var imgCodeVal = $("#imgcode").val();
	if(imgCodeVal == ''){//输入为空
		imgCodeFlag = false;
		$('.jy_info').text("验证码不能为空").css('visibility','visible');
	}else{//以上验证通过
		//异步改同步
		$.ajax({  
			type : "post",  
			url : "/index.do?method=validateImgCode",  
			data : "imgCode=" + $("#imgcode").val(),  
			async : false,  
			success : function(msg){  
				if (msg == 1) {
	        		imgCodeFlag = true;
	        		validatePass("imgcode");
	        		$.ajax({  
	    				type : "post",  
	    				url : "/index.do?method=checkUname",  
	    				data : "username=" + $("#mobile").val()+"&flag=pc&imgcode=" + $("#imgcode").val(),  
	    				async : false,  
	    				success : function(msg){  
	    					if (msg == 0) {
	    						mobileFlag = true;
	    		            }else if(msg == -10){
	    		    			$('.jy_info').text("验证码不能为空").css('visibility','visible');
	    		            	mobileFlag = false;
	    		            } else if(msg == -11){
	    		    			$('.jy_info').text("图形验证码错误").css('visibility','visible');
	    		            	mobileFlag = false;
	    		            } else if(msg == -12){
	    		    			$('.jy_info').text("图形验证码过期").css('visibility','visible');
	    		            	mobileFlag = false;
	    		            } else {
	    		            	mobileFlag = false;
	    		            	window.location.href="/login.html";
	    		            	$("#mobile").val("");
	    		            }  
	    				}  
	    		     });
	            }else{
	            	$('.jy_info').text("图形验证码错误").css('visibility','visible');
	            	imgCodeFlag = false;
	            	$("#imgcode").val("");
	            }  
			}  
	     });
	}
}
/***
 * 手机验证码验证
 */
function phonecodeCh(){
	validatePass("phoneCode2");
	var nmame = $('#uname').val();
	var pwd = $('#pwd').val();
	var repwd = $('#repwd').val();
	var phonecod = $('#phoneCode').val();
	if(phonecod != undefined && phonecod != 'undefined' && phonecod.length >0){
		phonecodeFlag = true;
	}else{
		$('.jy_info').text("手机验证码不能为空!").css('visibility','visible');
		phonecodeFlag = false;
	}
	if(nmame=='' || pwd=='' || repwd==''){
		$('.jy_info').text("请点击获手机取验证码!").css('visibility','visible');
		phonecodeFlag = false;
	}else{
		phonecodeFlag = true;
	}
}
/***
 * 注册前验证
 * @returns {Boolean}
 */
function validateInput(){
	mobileCh();
	if(mobileFlag){
		imgcodeCk();
		if(imgCodeFlag){
			phonecodeCh();
			if(phonecodeFlag){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}else{
		return false;
	}
}
/*
 * 注册按钮提交表单的处理
 * 先校验元素的合法性
 */
function submitBtn(){
	if(!validateInput()){
		return false;
	}else{ //所有的页面元素都验证通过后，异步请求提交表单
		$.post("/index.do?method=doRegisterAction", {
			mobile: $('#mobile').val(),
			uname : $('#uname').val(),
			pwd : $('#pwd').val(),
			repwd : $('#repwd').val(),
			verify: $('#phoneCode').val(),
			regFrom: 0,
			type:'reg',
			quick:'quick'
    },
    function (data){
    	validatePass("phoneCode2");
    	data=$.parseJSON(data);
    	if(data['msg'] == undefined ||data['msg'] == 'undefined'){
			$('.jy_info').text("注册出错，请刷新页面重新注册").css('visibility','visible');
		}else if(data['msg'] == '0'){
   	 		$('.jy_info').text("注册失败，请重新注册！").css('visibility','visible');
		}else if(data['msg'] == '-1'){
	    	$('.jy_info').text("用户名已注册！").css('visibility','visible');
		}else if(data['msg'] == '-2'){
			$('.jy_info').text("该手机号已被注册").css('visibility','visible');
		}else if(data['msg'] == '1'){
 			$('.jy_info').text("表单验证失败,请重新填写!").css('visibility','visible');
		}else if(data['msg'] == 'verifyNo'){
	    	$('.jy_info').text("请输入正确的手机验证码!").css('visibility','visible');
		}else if(data['msg'] == 'verifyVoid'){
	    	$('.jy_info').text("验证码超时，请点击重新发送!").css('visibility','visible');
		}else if(data['msg'] == 'unsent'){
	    	$('.jy_info').text("请稍后重新点击获取验证码!").css('visibility','visible');
		}else if(data['msg'] == 'fucodeNeed'){
	    	$('.jy_info').text("请点击获取手机验证码!").css('visibility','visible');
		}else if(data['msg'] == 'succeed'){
			window.location.href='http://www.niwodai.com/daikuan/jiekuan.html';
		}else {
			window.location.href='http://www.niwodai.com/daikuan/jiekuan.html';
		}
    });
	}
}