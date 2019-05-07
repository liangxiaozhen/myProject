var msgTimeOut = 60;
var codeInCount = 0;
$(document).ready(function () {
	//取得session里的值
	var regUname = $("#regUname").val();
	//session失效返回注册页面
	if(regUname == '' || regUname == null){
		window.location.href="/register.html";
	}
	//取得验证码是否发送成功
	var regSdMsgCodeRst = $("#regSdMsgCodeRst").val();
	if((regSdMsgCodeRst != null || regSdMsgCodeRst != '') && regSdMsgCodeRst == 'succ'){
		$('#ckCodeMSG').css("display","");
		$('#ckCodeMSG').removeClass().html("<i class='ico_all size15 img_icon s_dui'></i> 验证码已发送，有效期30分钟，请查收");
		//60秒倒计时
		var messSendTime = $("#messSendTime").val();
		var messSendType = $("#messSendType").val();
		//var mydate = new Date().getTime();
		
		//获取服务器时间
		var mydate = messSendTime;//默认值设为发送时间
		$.get("getSysTime.action?date="+new Date().getTime(),
        function (exist) {
			mydate = exist;
			var countDownTime = Math.round(60 - (mydate - messSendTime)/1000);
			if(messSendType == '2'){
				countDownTime = Math.round(120 - (mydate - messSendTime)/1000);
			}
			if(countDownTime <= 0){
				//显示语音获取按钮
				$("#countDown").hide();
				$("#yybtn").show();
			}else{
				$("#dtmbtn").hide();
				$("#yybtn").hide();
				countDown(countDownTime);
			}
        });
		
	}else{
		$('#ckCodeMSG').css("display","");
		$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码发送失败，请再次发送");
		//显示点击获取按钮
		$("#countDown").hide();
		$("#yybtn").hide();
		$("#dtmbtn").show();
	}
});

//倒计时
function countDown(time){
	$("#dtmbtn").hide();
	$("#yybtn").hide();
	$("#countDown").show();
    $("#countDown").attr("value",time+"秒");
    time = time - 1;
    if(time>=0){
        setTimeout("countDown("+time+")",1000);
    }else{
        $("#countDown").hide();
        $("#yybtn").show();
        msgTimeOut = 60;
    }
}
//语音倒计时
function yyCountDown(time){
	$("#dtmbtn").hide();
	$("#yybtn").hide();
	$("#countDown").show();
    $("#countDown").attr("value",time+"秒");
    time = time - 1;
    if(time>=0){
        setTimeout("yyCountDown("+time+")",1000);
    }else{
        $("#countDown").hide();
        $("#yybtn").show();
    }
}

//验证触发
$('#regChkBtn').click(function () {
	//验证验证码是否输入
	var ckCode = $('#checkCode').val();
	if(ckCode == null || ckCode == ''){
		$('#ckCodeMSG').css("display","");
		$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码不能为空");
	}else{
		    //保存信息
		    $.post("checkMobileCode.action", {
		    	ckCode: ckCode,
		    	num: $('#num').val(),
		    	mobile : $('#regMobile').val()
			},
            function (data){
            	data=$.parseJSON(data);
            	if(data['errCode'] == '4'){
            		if(data['successURL'] == null || data['successURL'] == "null" ) {
            			$("#registerThirdStepsInit").submit();
            		} else {
            			window.location.href = data['successURL'];
            		}
            	}else if(data['errCode'] == '0'){
            		//注册成功
            		$("#registerThirdStepsInit").submit();
            	}else if(data['errCode'] == '1' || data['errCode'] == '2'){
            		//注册失败
            		$('#ckCodeMSG').css("display","");
        			$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>注册失败,请返回第一步重新注册");
            	}else{
            		//用户名验证结果
            		if(data['ckCodeErrMsg'] != null){
            			if(data['ckCodeErrMsg'] == '0'){
            				$('#ckCodeMSG').css("display","");
            				$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码不能为空");
            			}else if(data['ckCodeErrMsg'] == '1'){
            				$('#ckCodeMSG').css("display","");
                			$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码输入有误，请重新输入");
            			}else if(data['ckCodeErrMsg'] == '2'){
            				$('#ckCodeMSG').css("display","");
            				$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码已失效，请重新获取");
            			}else if(data['ckCodeErrMsg'] == '10'){
            				$('#ckCodeMSG').css("display","");
            				$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>输入验证码错误次数过多，请重新获取!");
            			}
            		}
            	}
            });
	}
});


//弹出框发送
$('#send').click(function () {
	//取得输入手机号码
	var mobileVal = $("#mobile").val();
	//取得输入验证码
	var imgCodeVal = $("#imgCode").val();
	if(mobileVal == ''){//手机号为空
		$('#mobileMSG').css("display","");
    	$('#mobileMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>手机号码不能为空");
	}else if(mobileVal.length != 11){//密码长度为6~20位字符
		$('#mobileMSG').css("display","");
    	$('#mobileMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>手机号码长度错误");
	}else if(!mobileVal.match("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[3|6|7|8])|18[0-9])\\d{8}|(170\\d{8})$")){//手机号码格式错误
		$('#mobileMSG').css("display","");
    	$('#mobileMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>手机号码格式错误");
	}else{//以上验证通过
		if(imgCodeVal == ''){//验证码为空
			$('#mobileMSG').css("display","");
	    	$('#mobileMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码不能为空");
		}else{
			//请求修改
	        // $.get("index.do?method=updateMobile", {
			$.post("index.do?method=updateMobile", {
	        	mobile: mobileVal,
	        	imgCode: imgCodeVal
	        },
	        function (exist) {
	            if (exist == '0') {//成功
	            	$("#mobileMSG").removeClass();
	            	$("#regMobile").attr("value",mobileVal);
	            	window.location.reload();
	            } else{//手机号未被注册
	            	$('#mobileMSG').css("display","");
	            	$("#mobileMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>"+exist);
	            	$("#refush").click();
	            }
	        });
		}
	}
});

//发送验证码
$("#dtmbtn").click(function () {
		$.get("/geetest/regSendTextMessage.do?interval=60", {phone: $('#regMobile').val(),imgCode: $('#sendMsgImgCode').val()}, function (result){
			if(result == 'succ'){
        		countDown(60);
        		$('#ckCodeMSG').css("display","");
        		$('#ckCodeMSG').removeClass().html("<i class='ico_all size15 img_icon s_dui'></i> 验证码已发送，有效期30分钟，请查收");
    		}else{
				if(result=='请输入正确的图片验证码' || result=='图片验证码超时,请刷新！'){
					$('#ckCodeMSG').css("display","");
					$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>图片验证码超时,请返回第一步重新注册！");
				} else {
					$('#ckCodeMSG').css("display","");
					$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>"+result);
				}
				
    		}
        });
});

//发送语音短信验证码  初次60秒倒计时/初次以外120秒倒计时
$("#yybtn").click(function () {

		var param = {};
		param.interval = msgTimeOut;
		param.phone = $('#regMobile').val();
		param.imgCode = $('#sendMsgImgCode').val();
		
		$.post("/geetest/regSendYuyinMessage.do", param, function (result){

			if(result == 'succ'){
        		yyCountDown(120);
        		$('#ckCodeMSG').css("display","");
        		$('#ckCodeMSG').removeClass().html("<i class='ico_all size15 img_icon s_dui'></i> 你我贷已通过400-7910-888播报，请收听");
    		}else{
    			if(result=='请输入正确的图片验证码' || result=='图片验证码超时,请刷新！'){
    				$('#ckCodeMSG').css("display","");
    				$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>图片验证码超时,请返回第一步重新注册！");
    			} else {
    				$('#ckCodeMSG').css("display","");
    				$('#ckCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>"+result);
    			}
    		}
        });
		msgTimeOut = 120;
});

//点击取消清空
function colseClean(){
	$("#mobile").attr("value","");
	$("#imgCode").attr("value","");
	closeAll_1();
	window.location.reload();
}

//换一张图片验证码
$("#refush").click(function(){
	var d = new Date();
	var src = "/validatecode/refresh.htm?date=" + d.getTime();
	$("#code").attr("src",src);
	return false;
});

//弹框
function openWid(){
	showCon_1();
	$("#refush").click();
}