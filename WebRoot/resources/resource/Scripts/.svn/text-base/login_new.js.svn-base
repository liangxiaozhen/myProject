var textMsg = alertMsgInfo.textMsg;
var yyMsg = alertMsgInfo.yyMsg;
$(function () {
	//获取cookies
	if(getCookie("nwdUser")){
		$('#username').val(getCookie("nwdUser"));
		$('#usernameShow').css("display","none");
	}
	$("#username").blur(function(){
		validateDate();
		if(!($("#username").val())){
			$('#usernameShow').css("display","block");
		}
    });
	$("#pwd").blur(function(){
		validateDate();
		if(!($("#pwd").val())){
			$('#pwdShow').css("display","block");
		}
    });
/*	$('#pwd').focus(function () {
		validateDate();
	});*/
	
	$(document).keydown(function(e){ 
		var curKey = e.which; 
		if(curKey == 13){ 
			$("#userLoginBtn").click(); 
			return false; 
		} 
	});
	
	function validateDate(){
		var username = $('#username').val();
		if(username==""){
			$('#validatePhone').addClass("hidden");
			$('#validate').addClass("hidden");
			return false;
		}
		var param = {};
		param.username = username;
		$.post("/index.do?method=isValidateCode", param, function(date){
			date=$.parseJSON(date);
            if (date['validate'] == "1") {
            	//短信校验码
            	//$('#validate').addClass("hidden");//
            	$('#validate').removeClass("hidden");//
            	$('#validatePhone').removeClass("hidden");
            	$('#codeType').val("1");
            }else if (date['validate'] == "2") {
            	//图片校验码
            	$('#validatePhone').addClass("hidden");
            	$('#validate').removeClass("hidden");
            	$('#codeType').val("2");
            }else if (date['validate'] == "0"){
            	$('#validatePhone').addClass("hidden");
    			$('#validate').addClass("hidden");
    			$('#codeType').val("0");
    			
    			$('#imgCode').val("");
    			$('#phoneCode').val("");
            }else if (date['validate'] == "9"){
            	$('#validatePhone').addClass("hidden");
    			$('#validate').addClass("hidden");
    			$('#codeType').val("0");
    			//
    			$('#errorMsg').css("display","block");
            	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>手机号或邮箱异常，请使用用户名登录</span></div>");
            	
            	$('#imgCode').val("");
    			$('#phoneCode').val("");
            }
        });
	}
	
	//发送文本验证码。文本验证码只显示第一次。后续都显示语音验证码。文本倒计时为60秒，语音都是120秒
    $('#phoneCodeBut').click(function () {
    	var username = $('#username').val();
    	var imgCode = $('#imgCode').val();
    	if(username==""){
    		$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>用户名不能为空</span></div>");
        	return false;
    	}
    	if(imgCode==""){
    		$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
        	
        	//新增图片验证码输入框的错误样式
        	$('#imgCode').addClass("red");
        	//去除其他框错误样式
        	$('#username').removeClass("red");
        	$('#pwd').removeClass("red");
        	$('#phoneCode').removeClass("red");
        	
        	return false;
    	}
    	$('#errorMsg').html();
        $.post("/login/loginSendTextMessage.do", { username: username,imgCode:imgCode },
            function (result) {
        		if(result=='succ'){
        			/** 20140505 lizhi 修改手机发送将按钮设置为不可以点击样式 **/
        	        //$("#phoneCodeBut").attr('disabled', true);
        			$("#phoneCodeBut").removeClass("yzm").addClass("yzm2").attr('disabled', true);
        			//myDialog(textMsg,Waiting,60);
        			
        	        /** 20140505 lizhi 修改手机发送提示信息, 不再弹出新的对话框 **/
        			//showMsg(textMsg);
        	        $('#errorMsg').css("display","block");
                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>验证码已发送至您的手机，有效期30分钟，请查收！</span></div>");
        	        
        			Waiting(60);
        			
        			$('#imgCode').removeClass("red");
        		}else if(result=='imgOut'){
        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
        			$('#errorMsg').css("display","block");
                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>图片验证码超时,请刷新！</span></div>");
        			
                	//刷新图片验证码
                	$(".gt_refresh_tips").click();
                	//新增图片验证码输入框的错误样式
                	$('#imgCode').addClass("red");
                	//去除其他框错误样式
                	$('#username').removeClass("red");
                	$('#pwd').removeClass("red");
                	$('#phoneCode').removeClass("red");
                	
        		}else if(result=='imgErr'){
        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
        			$('#errorMsg').css("display","block");
                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
        			
                	//刷新图片验证码
                	$(".gt_refresh_tips").click();
                	//新增图片验证码输入框的错误样式
                	$('#imgCode').addClass("red");
                	//去除其他框错误样式
                	$('#username').removeClass("red");
                	$('#pwd').removeClass("red");
                	$('#phoneCode').removeClass("red");
        		}else{
        			/** 20140505 lizhi 修改手机发送将按钮设置为不可以点击样式, 并修改弹出信息显示方式 **/
        			//$("#phoneCodeBut").attr('disabled', false);
        			//showMsg(result);
        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
        			$('#errorMsg').css("display","block");
                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'> "+ result+ "</span></div>");
        			
                	//刷新图片验证码
                	$(".gt_refresh_tips").click();
                	//新增图片验证码输入框的错误样式
                	$('#imgCode').addClass("red");
                	//去除其他框错误样式
                	$('#username').removeClass("red");
                	$('#pwd').removeClass("red");
                	$('#phoneCode').removeClass("red");
        		}
            }
        );
    });
  //发送语音短信验证码   
    $('#yybtn').click(function () {
    	var username = $('#username').val();
    	var imgCode = $('#imgCode').val();
    	if(username==""){
    		$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>用户名不能为空</span></div>");
        	return false;
    	}
    	if(imgCode==""){
    		$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
        	//新增密码输入框的错误样式
        	$('#imgCode').addClass("red");
        	return false;
    	}
    	$('#errorMsg').html();
        $.post("/login/loginSendYuyinMessage.do?interval=60", { username: username,imgCode:imgCode },
        		function (result) {
		    		if(result=='succ'){
		    			/** 20140505 lizhi 修改语音验证码设置为不可以点击样式, 并修改提示信息显示 **/
		    	        /*$("#yybtn").attr('disabled', true);
		    	        //myDialog(yyMsg,yuyinWaiting,120);
		    	        showMsg(yyMsg);*/
		    			
		    			$("#yybtn").removeClass("yzm").addClass("yzm2").attr('disabled', true);
		    			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>验证码已发送，你我贷将通过400-7910-888进行语音播报！</span></div>");
		    			
		    	        yuyinWaiting(120);
		    		}else if(result=='imgOut'){
	        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
	        			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>图片验证码超时,请刷新！</span></div>");
	        			
	                	//刷新图片验证码
	                	$(".gt_refresh_tips").click();
	                	//新增图片验证码输入框的错误样式
	                	$('#imgCode').addClass("red");
	                	//去除其他框错误样式
	                	$('#username').removeClass("red");
	                	$('#pwd').removeClass("red");
	                	$('#phoneCode').removeClass("red");
	                	
	        		}else if(result=='imgErr'){
	        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
	        			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
	        			
	                	//刷新图片验证码
	                	$(".gt_refresh_tips").click();
	                	//新增图片验证码输入框的错误样式
	                	$('#imgCode').addClass("red");
	                	//去除其他框错误样式
	                	$('#username').removeClass("red");
	                	$('#pwd').removeClass("red");
	                	$('#phoneCode').removeClass("red");
	        		}else{
		    			/** 20140505 lizhi 修改语音验证码按钮设置可以点击样式 **/
		    			/*$("#yybtn").attr('disabled', false);
		    			showMsg(result);*/
		    			
		    			$("#yybtn").removeClass("yzm2").addClass("yzm").attr('disabled', false);
		    			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i> <span class='va-m lin_24 pl_5'>"+ result+"</span></div>");
	                	
	                	//刷新图片验证码
	                	$(".gt_refresh_tips").click();
	                	//新增图片验证码输入框的错误样式
	                	$('#imgCode').addClass("red");
	                	//去除其他框错误样式
	                	$('#username').removeClass("red");
	                	$('#pwd').removeClass("red");
	                	$('#phoneCode').removeClass("red");
	        		}
		        }
        );
    });
    $('#yybtn2').click(function () {
    	var username = $('#username').val();
    	var imgCode = $('#imgCode').val();
    	if(username==""){
    		$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>用户名不能为空</span></div>");
        	return false;
    	}
    	if(imgCode==""){
    		$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
        	//新增密码输入框的错误样式
        	$('#imgCode').addClass("red");
        	return false;
    	}
    	$('#errorMsg').html();
        $.post("/login/loginSendYuyinMessage.do?interval=120", { username: username },
        		function (result) {
		    		if(result=='succ'){
		    			/** 20140505 lizhi 修改语音验证码设置为不可以点击样式, 并修改提示信息显示 **/
		    	        /*$("#yybtn2").attr('disabled', true);
		    	        //myDialog(yyMsg,yuyinWaiting2,120);
		    	        showMsg(yyMsg);*/
		    			
		    			$("#yybtn2").removeClass("yzm").addClass("yzm2").attr('disabled', true);
		    			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>验证码已发送，你我贷将通过400-7910-888进行语音播报！</span></div>");
		    			
		    	        yuyinWaiting2(120);
		    		}else if(result=='imgOut'){
	        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
	        			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>图片验证码超时,请刷新！</span></div>");
	        			
	                	//刷新图片验证码
	                	$(".gt_refresh_tips").click();
	                	//新增图片验证码输入框的错误样式
	                	$('#imgCode').addClass("red");
	                	//去除其他框错误样式
	                	$('#username').removeClass("red");
	                	$('#pwd').removeClass("red");
	                	$('#phoneCode').removeClass("red");
	                	
	        		}else if(result=='imgErr'){
	        			$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
	        			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
	        			
	                	//刷新图片验证码
	                	$(".gt_refresh_tips").click();
	                	//新增图片验证码输入框的错误样式
	                	$('#imgCode').addClass("red");
	                	//去除其他框错误样式
	                	$('#username').removeClass("red");
	                	$('#pwd').removeClass("red");
	                	$('#phoneCode').removeClass("red");
	        		}else{
		    			/** 20140505 lizhi 修改语音验证码按钮设置可以点击样式 **/
		    			/*$("#yybtn2").attr('disabled', false);
		    			showMsg(result);*/
		    			
		    			$("#yybtn2").removeClass("yzm2").addClass("yzm").attr('disabled', false);
		    			$('#errorMsg').css("display","block");
	                	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>"+ result+"</span></div>");
	                	
	                	//刷新图片验证码
	                	$(".gt_refresh_tips").click();
	                	//新增图片验证码输入框的错误样式
	                	$('#imgCode').addClass("red");
	                	//去除其他框错误样式
	                	$('#username').removeClass("red");
	                	$('#pwd').removeClass("red");
	                	$('#phoneCode').removeClass("red");
	        		}
		        }
        );
    });
    //登录
    $('#userLoginBtn').click(function () {
    	validateDate();
        var _account = $('#username').val();
        var _pwd = $('#pwd').val();
        var _imgCode = $('#imgCode').val();
        var _phoneCode = $('#phoneCode').val();
        var _codeType = $('#codeType').val();
        if (_account.length < 1) {
        	$('#errorMsg').addClass()
        	$('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>用户名不能为空</span></div>");
        	
        	// 新增用户名输入框的错误样式
        	$('#username').addClass("red");
        	//去除其他框错误样式
        	$('#pwd').removeClass("red");
        	$('#phoneCode').removeClass("red");
        	$('#imgCode').removeClass("red");
        	
            return false;
        } else if (_pwd.length < 1) {
            $('#errorMsg').css("display","block");
        	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>密码不能为空</span></div>");
        	
        	// 新增密码输入框的错误样式
        	$('#pwd').addClass("red");
        	//去除其他框错误样式
        	$('#username').removeClass("red");
        	$('#phoneCode').removeClass("red");
        	$('#imgCode').removeClass("red");
        	
            return false;
        }
        if(_codeType==1){
        	if(_phoneCode.length < 1){
        		$('#errorMsg').css("display","block");
            	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>请输入正确的短信校验码！</span></div>");
            	
            	// 新增短信校验码输入框的错误样式
            	$('#phoneCode').addClass("red");
            	//去除其他框错误样式
            	$('#username').removeClass("red");
            	$('#pwd').removeClass("red");
            	$('#imgCode').removeClass("red");
            	
                return false;
        	}
        }
        if(_codeType==2){
        	if(typeof(_imgCode) == undefined || _imgCode != "success"){
        		$('#errorMsg').css("display","block");
            	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5' ></i><span class='va-m lin_24 pl_5'>请手动滑动图片验证码！</span></div>");
            	
            	// 新增图片验证码输入框的错误样式
            	$('#imgCode').addClass("red");
            	//去除其他框错误样式
            	$('#username').removeClass("red");
            	$('#pwd').removeClass("red");
            	$('#phoneCode').removeClass("red");
            	
                return false;
        	}
        }
        //选择记住用户名     用户名写入cookies 
        if($('#saveUname').attr('checked')=='checked'){
        	setCookie('nwdUser',_account);
        }else{//删除用户名cookie
        	delCookie('nwdUser');
        }
        
        $('#userLoginBtn').attr('disabled', true);
        $('#errorMsg').css("display","block");
    	$('#errorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>正在提交验证，请稍候...</span></div>");
    	
        $('#loginForm').ajaxSubmit({
        	type: "post",
            dataType: "json",
            ContentType:"text/json;charset=utf-8",
            success: function (_data) {
                if (_data['state'] == 1) {//登录成功
                	setCookie("errorCount"+encodeURI(_account)+"",0);
                	setCookie('nwdunamecookie',"nwdunamecookie");
        			//backUrl用于活动页、借款产品、立即投标等共享与登录弹出框，登录后转回原页面
                	/*var backUrl = art.dialog.data("backUrl");*/
//                	var backUrl = window.backUrl;
//                	
//                	if(typeof(backUrl)!='undefined'){
//                		window.location.href = backUrl;
//                	}else if(_data['isback'] == 1){
//                		window.location.href = _data['backURL'];
//                	} else {
//                		window.location.href = "/member/Investors.html";
//                	}
                	
                	//zhuzy判断是否是黑名单用户
                	var isBlackUser = _data['isBlackUser'];
                	if(isBlackUser=="1"){
                		var firstLogin = getCookie(_account+"_firstLogin");
                		if(firstLogin!="1"){
                			var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，您的部分操作已受限。</p>";
                    		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
                    		$(".plusBank1 .content").html(msg);
                    		$(".plusBank1 button.btn").unbind('click');
                    		$(".plusBank1 button.btn").click(function() {
                    			//关闭提示信息弹出框
                    			closeAll_1();
                    			setCookie(_account+"_firstLogin","1",365);
                    			var burl="";
                            	if(_data['backURL']){
                            		burl=_data['backURL'];
                            	}
                            	burl=encodeURIComponent(burl);
                            	if("https:" == document.location.protocol){
                            		window.location.href = "http://www.niwodai.com/index.do?method=loginForward&backUrl="+burl;
                            	}else{
                            		window.location.href = "/index.do?method=loginForward&backUrl="+burl;
                            	}
                    		});
                    		showCon_1();
                		}else{
                			var burl="";
                        	if(_data['backURL']){
                        		burl=_data['backURL'];
                        	}
                        	burl=encodeURIComponent(burl);
                        	if("https:" == document.location.protocol){
                        		window.location.href = "http://www.niwodai.com/index.do?method=loginForward&backUrl="+burl;
                        	}else{
                        		window.location.href = "/index.do?method=loginForward&backUrl="+burl;
                        	}
                		}
                	}else{
                		var burl="";
                    	if(_data['backURL']){
                    		burl=_data['backURL'];
                    	}
                    	burl=encodeURIComponent(burl);
                    	if("https:" == document.location.protocol){
                    		window.location.href = "http://www.niwodai.com/index.do?method=loginForward&backUrl="+burl;
                    	}else{
                    		window.location.href = "/index.do?method=loginForward&backUrl="+burl;
                    	}
                	}
                } else if(_data['state'] == 3){//请输入正确的用户名或密码！
                	if(getCookie("errorCount"+encodeURI(_account)+"")==""){
                		setCookie("errorCount"+encodeURI(_account)+"",0);
                	} else {
                		setCookie("errorCount"+encodeURI(_account)+"",parseFloat(getCookie("errorCount"+encodeURI(_account)+""))+1);
                	}
                	$('#errorMsg').css("display","block");
                	 $('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>"+_data['error']+"</span></div>");
                	 $('#userLoginBtn').attr('disabled', false);
                	 
                	// 新增用户名输入框的错误样式
                 	$('#username').addClass("red");
                 	// 新增密码输入框的错误样式
                	$('#pwd').addClass("red");
                	//去除其他框错误样式
                	$('#phoneCode').removeClass("red");
                	$('#imgCode').removeClass("red");
                	
                	//如果是短信验证码正确，用户名密码错误，3次之后需要重新发送验证码
                	if(_codeType==1){
                		if(_data['phoneMsgState'] == 0){
	                		$('#errorMsg').css("display","block");
	                    	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>用户名或密码错误次数过多，稍后请重新获取验证码！</span></div>");
	                    	$('#userLoginBtn').attr('disabled', false);
	                    	$('#phoneCode').removeClass("red");
	                    	//去除其他框错误样式
	                    	$('#username').removeClass("red");
	                    	$('#pwd').removeClass("red");
	                    	$('#imgCode').removeClass("red");
                		}
                	}
                	
                	//如果是图形验证码正确，用户名密码错误，3次之后刷新图新验证码，后台图形验证码同时销毁
                	if(_codeType==2){
                		//刷新图新验证码
                    	$(".gt_refresh_tips").click();
                    	$('#imgCode').removeClass("red");
                	}
                	
                } else if(_data['state'] == 4){//图形验证码错误
                	$('#errorMsg').css("display","block");
                	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>"+_data['error']+"</span></div>");
                	$('#userLoginBtn').attr('disabled', false);
                	// 新增图形验证码输入框的错误样式
                	$('#imgCode').addClass("red");
                	//去除其他框错误样式
                	$('#username').removeClass("red");
                	$('#pwd').removeClass("red");
                	$('#phoneCode').removeClass("red");
                	//刷新图新验证码
                	$(".gt_refresh_tips").click();
                } else if(_data['state'] == 5){//手机验证码错误
                	$('#errorMsg').css("display","block");
                	$('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>"+_data['error']+"</span></div>");
                	$('#userLoginBtn').attr('disabled', false);
                	// 新增手机验证码输入框的错误样式
                	$('#phoneCode').addClass("red");
                	//去除其他框错误样式
                	$('#username').removeClass("red");
                	$('#pwd').removeClass("red");
                	$('#imgCode').removeClass("red");
            } else if(_data['state'] == -3){
            	$('#errorMsg').css("display","block");
            	 $('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>帐号异常[NWD_ERR_8001]，请联系客服！</span></div>");
            	 $('#userLoginBtn').attr('disabled', false);
            	// 新增用户名输入框的错误样式
             	$('#username').addClass("red");
             	// 新增密码输入框的错误样式
            	$('#pwd').addClass("red");
            	//去除其他框错误样式
	           	$('#phoneCode').removeClass("red");
	           	$('#imgCode').removeClass("red");
            } else {
                	 $('#errorMsg').css("display","block");
                 	 $('#errorMsg').html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>"+_data['error']+"</span></div>");
                 	 $('#userLoginBtn').attr('disabled', false);
                 	 
                 	// 新增用户名输入框的错误样式
                  	$('#username').addClass("red");
                  	// 新增密码输入框的错误样式
                 	$('#pwd').addClass("red");
                 	//去除其他框错误样式
                	$('#phoneCode').removeClass("red");
                	$('#imgCode').removeClass("red");
                }
            }
        });
    });
    
    
    //qq登录 
    $('#qqLogin,#qqLogin1').click(function () { 
    // 兼容ie6 
    setTimeout(function(){ 
    	window.location.href = 'https://member.niwodai.com/qqLogin.html';
    },100); 

    }); 
    //微博登录 
    $('#weiboLogin,#weiboLogin1').click(function () { 
     // 兼容ie6 
    setTimeout(function(){ 
    	window.location.href = 'https://member.niwodai.com/weiboLogin.html';
    },100); 
    });

    //图片验证码
    $("#refush").click(function(){
		var d = new Date();
		var src = "/validatecode/refresh.htm?date=" + d.getTime();
		$("#code").attr("src",src);
		return false;
	});
    
    function setCookie(name, value, d) {
		var Days = 30;
		var exp = new Date();
		if (d > 0)
			Days = d;
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value)
				+ ";path=/;domain=.niwodai.com;expires=" + exp.toGMTString();
	}
	//读取cookies 
	function getCookie(name) {
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(name + "=");
			if (c_start != -1) {
				c_start = c_start + name.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				return unescape(document.cookie.substring(c_start, c_end));
			}
		}
		return "";

	}
	//删除cookies 
	function delCookie(name) {
		setCookie(name, '');
	}
	
	function Waiting(i) {
        if (i > 1) {
            i--;
            /** 20140505 lizhi 设置按钮显示值为时间倒计时 **/
            //$("#phoneCodeBut").html(i + "秒");
            $("#phoneCodeBut").val(i + "秒");
            window.setTimeout(function () {
                Waiting(i);
            }, 1000);
        } else {
        	$("#phoneCodeBut").hide();
        	
        	/** 20140505 lizhi 设置语音验证码按钮显示值, 设置其显示 **/
            //$("#yybtn").html("语音验证码");
        	//$("#yybtn").attr('disabled',false);
        	$("#yybtn").val("语音验证码");
        	$("#yybtn").removeClass("yzm2").addClass("yzm");
        	
        	
            $("#yybtn").show();
            
            //刷新图片验证码
        	$(".gt_refresh_tips").click();
        }
    }
	
	function yuyinWaiting(i) {
        if (i > 1) {
            i--;
            /** 20140505 lizhi 设置按钮显示值为时间倒计时 **/
            //$("#yybtn").html(i + "秒");
            $("#yybtn").val(i + "秒");
            
            window.setTimeout(function () {
            	yuyinWaiting(i);
            }, 1000);
        } else {
        	$("#yybtn").hide();
        	$("#yybtn2").show();
        	
        	//刷新图片验证码
        	$(".gt_refresh_tips").click();
        }
    }
	function yuyinWaiting2(i) {
        if (i > 1) {
            i--;
            /** 20140505 lizhi 设置按钮显示值为时间倒计时 **/
            $("#yybtn2").val(i + "秒");
            window.setTimeout(function () {
            	yuyinWaiting2(i);
            }, 1000);
        } else {
        	/** 20140505 lizhi 设置语音验证码按钮显示值, 设置其显示 **/
        	$("#yybtn2").val("语音验证码");
        	$("#yybtn2").removeClass("yzm2").addClass("yzm").attr('disabled',false);
        	
        	//刷新图片验证码
        	$(".gt_refresh_tips").click();
        }
    }
	
	/**
	 * 用于打开出登录窗口前初始化操作
	 */
	window.clear = function() {
		$('#username').val(getCookie("nwdUser")).removeClass("red");
		$('#pwd').val("").removeClass("red");
		$('#validatePhone').addClass("hidden").removeClass("red");
		$('#validate').addClass("hidden").removeClass("red");
		$('#codeType').val("0");
		
		$("#phoneCodeBut").removeClass("yzm2").addClass("yzm").attr('disabled', false);
		$("#yybtn").removeClass("yzm2").addClass("yzm").attr('disabled', false).hide();
		$("#yybtn2").removeClass("yzm2").addClass("yzm").attr('disabled', false).hide();
		
		$('#errorMsg').css("display","none").html("");
		
		$('#userLoginBtn').attr('disabled', false);
		
		// 设置登录成功的返回页面为当前页
		window.backUrl = window.location.href;
	};
	
	/**======================快速登录 s ===============================================================**/
	/*
	 * added by fanghui 2014.11.17
	 * 点击图形验证码时的事件处理
	 */
	$("#imgc").click(function(event){
		event.preventDefault();//阻止冒泡,因为图形验证码是定义在文本框内部的
		refreshcode();
	});
	
	/*
	 * added by fanghui 2014.11.17
	 * 换一张图片验证码
	 */
	function refreshcode(){
			var d = new Date();
			var src = "/validatecode/refresh.htm?date=" + d.getTime();
			$("#imgc").attr("src",src);
			//图片验证码输入框清空
			$('#imgquickcode').val("");
	}
	
    //手机验证码
    $('#gainMessage').click(function () {
    	getPhoneCode();
    });
	
    /***
     * 获取手机验证码
     */
    function getPhoneCode(){
    	var flag = mobileCh();
		    if (flag == true) {
			$.post("/msg/phoneMsgLoginSendTextMessage.do", {
				phone : $('#mobile').val(),
				imgCode : $("#imgquickcode").val()
			}, function(result) {
				if (result == 'succ') {
					countDownTime(60);// 倒计时,60s动画展示
				} else {
					showErr("quickLoginError", result);
					// 验证码刷新
					$(".gt_refresh_tips").click();
				}
			});
		}
    }
    
    function countDownTime(time){
		if(!time){
			time=60;
		}
		var t=setInterval(function(){
			time--;
			$("#gainMessage").attr("disabled",true);//按钮不可用
			$("#gainMessage").val(time+"秒");
			if(time<0){
				$("#gainMessage").val("重新获取");
				$("#gainMessage").attr('disabled',false);//按钮可用
				clearTimeout(t);
			}
		},1000);
	}
    
    /***
     * 手机号码验证
     * @returns
     */
    function mobileCh(){
    	//取得输入用户名的值
    	var mobileVal = $("#mobile").val();
    	if(mobileVal == ''){//手机号为空
    		//mobileFlag = false;
    		showMsg("quickLoginError","手机号码不能为空");
    		quickLoginHighlight(["mobile"]);
    		return false;
    	}else if(mobileVal.length != 11){
    		//mobileFlag = false;
    		showMsg("quickLoginError","请输入11位手机号码");
    		quickLoginHighlight(["mobile"]);
    		return false;
    	}else if(!mobileVal.match("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[3|6|7|8])|18[0-9])\\d{8}|(170\\d{8})$")){//手机号码格式错误
    		//mobileFlag = false;
    		showMsg("quickLoginError","手机号码格式错误");
    		quickLoginHighlight(["mobile"]);
    		return false;
    	}else{//以上验证通过
    		//mobileFlag = true;
    		validatePass("quickLoginError");
    		return true;
    	}
    }
    
    //快速登录
    $("#quickLogin").click(function(){
    	var mobileflag = mobileCh();
    	if(!mobileflag || mobileflag==false){
    		return false;
    	}
    	
    	//图片验证码
    	var imgCodeText = $("#imgquickcode").val();
    	if(imgCodeText == ''){
    		showMsg("quickLoginError","请手动滑动图片验证码！");
    		quickLoginHighlight(["imgquickcode"]);
    		return false;
    	}
    	//短信验证码
    	var smCode = $("#smCode").val();
    	if(smCode == ''){
    		showMsg("quickLoginError","短信验证码不能为空。");
    		quickLoginHighlight(["smCode"]);
    		return false;
    	}
    	
        //选择记住用户名     用户名写入cookies 
    	var mobileVal = $("#mobile").val();
        if($('#remPhone').attr('checked')=='checked'){
        	setCookie('nwdmobile',mobileVal);
        }else{//删除用户名cookie
        	delCookie('nwdmobile');
        }
        
        $('#quickLogin').attr('disabled', true);
        $('#quickLoginErrorMsg').css("display","block");
    	$('#quickLoginErrorMsg').html("<div class='bg-stan pl_10'><i class='ico_all icon_18 info_icon s_tan mr_5'></i><span class='va-m lin_24 pl_5'>正在提交验证，请稍候...</span></div>");
    	
        $('#phoneForm').ajaxSubmit({
        	type: "post",
            dataType: "json",
            ContentType:"text/json;charset=utf-8",
            success: function (_data) {
                if (_data['state'] == 1) {
                	//登录成功
                	//setCookie("errorCount"+encodeURI(_account)+"",0);
                	//zhuzy判断是否是黑名单用户
                	var isBlackUser = _data['isBlackUser'];
                	if(isBlackUser=="1"){
                		var firstLogin = getCookie(_account+"_firstLogin");
                		if(firstLogin!="1"){
                			var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，您的部分操作已受限。</p>";
                    		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
                    		$(".plusBank1 .content").html(msg);
                    		$(".plusBank1 button.btn").unbind('click');
                    		$(".plusBank1 button.btn").click(function() {
                    			//关闭提示信息弹出框
                    			closeAll_1();
                    			setCookie(_account+"_firstLogin","1",365);
                    			var burl="";
                            	if(_data['backURL']){
                            		burl=_data['backURL'];
                            	}
                            	burl=encodeURIComponent(burl);
                            	if("https:" == document.location.protocol){
                            		window.location.href = "http://www.niwodai.com/index.do?method=loginForward&backUrl="+burl;
                            	}else{
                            		window.location.href = "/index.do?method=loginForward&backUrl="+burl;
                            	}
                    		});
                    		showCon_1();
                		}else{
                			var burl="";
                        	if(_data['backURL']){
                        		burl=_data['backURL'];
                        	}
                        	burl=encodeURIComponent(burl);
                        	if("https:" == document.location.protocol){
                        		window.location.href = "http://www.niwodai.com/index.do?method=loginForward&backUrl="+burl;
                        	}else{
                        		window.location.href = "/index.do?method=loginForward&backUrl="+burl;
                        	}
                		}
                	}else{
                		var burl="";
                    	if(_data['backURL']){
                    		burl=_data['backURL'];
                    	}
                    	burl=encodeURIComponent(burl);
                    	if("https:" == document.location.protocol){
                    		window.location.href = "http://www.niwodai.com/index.do?method=loginForward&backUrl="+burl;
                    	}else{
                    		window.location.href = "/index.do?method=loginForward&backUrl="+burl;
                    	}
                	}
                } else if(_data['state'] == 2){
                	//手机号验证失败
                	showErr("quickLoginError",_data['error']);
                	$('#quickLogin').attr('disabled', false);
                	quickLoginHighlight(["mobile"]);
                } else if(_data['state'] == 3){
                	//图形验证码错误
                	showErr("quickLoginError",_data['error']);
                	$('#quickLogin').attr('disabled', false); 
                	quickLoginHighlight(["imgquickcode"]);                	
                	//刷新图新验证码
                	$("#imgquickcode").click();
                } else if(_data['state'] == 4 || _data['state'] == 5){
                	//手机验证码错误
                	showErr("quickLoginError",_data['error']);
                	$('#quickLogin').attr('disabled', false);
                	// 新增手机验证码输入框的错误样式
                	quickLoginHighlight(["smCode"]);
                }else{
                	//直接显示error
                	showErr("quickLoginError",_data['error']);
                	
                	quickLoginHighlight(null);
                }
            }
        });        
    });
    
	// 输入框只能输入数字
	$(".num").bind("keyup", function() {
		$(this).val($(this).val().replace(/\D/g, ''));
	});
    
    /**
     * 验证错误提示
     * @param id
     * @param msg
     */
    function showErr(id,msg){		
    	$('#'+id+"Msg").html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>"+msg+"</span></div>");
    	$("#"+id+"Msg").css("display","block");
    }
    /***
     * 验证提示
     * @param id
     * @param msg
     */
    function showMsg(id,msg){
    	$("#"+id+"Msg").html("<div class='bg-scuo pl_10'><i class='ico_all icon_18 info_icon s_cuo mr_5'></i><span class='va-m lin_24 pl_5'>"+msg+"</span></div>");
    	$("#"+id+"Msg").css("display","block");
    }
    /***
     * 清除验证提示
     * @param id
     */
    function validatePass(id){
    	$("#"+id+"Msg").css("display","none");
    }
    
    function quickLoginHighlight(addArr){
    	var removeArr = ["mobile","imgquickcode","smCode"];
    	dealwithTextRedClass(removeArr,addArr);
    }
    
    /***
     * 高亮显示文本框
     * @param removeClassArray:要处理的控件集合
     * @param addClassArray:要高亮显示的控件集合
     */
    function dealwithTextRedClass(removeClassArray,addClassArray){
    	if(removeClassArray != null && removeClassArray.length > 0){
    		for (x in removeClassArray){
    			$("#"+removeClassArray[x]).removeClass("red");
    		}
    	}
    	
    	if(addClassArray != null && addClassArray.length > 0){
    		for (x in addClassArray){    			
    			$("#"+addClassArray).addClass("red");
    		}
    	}    	
    }
    
    //获取登录页banner
    $.ajax({ 
    	type : "POST", 
    	url : "/index.do?method=getLoginPageBanner", 
    	dataType:"json", 
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
    	success : function(result) { 
    		if(result!=null){
    			$(".ad_loginCon").attr("href",result.artUrl);
    			$(".ad_loginCon").show(); 
    		}else{
    			$(".ad_loginCon").hide(); 
    		}
    	} 
    	});
    
   
    /**======================快速登录 e ===============================================================**/
	
	
});
function wenhao(wh,tk){
	$(wh).hover(function () {
		$(tk).removeClass("hidden");
	},
	function () {
		$(tk).addClass("hidden");
	});
}; 
new wenhao(".tishik",".tankuan");
/**
 * 点击发送验证码的弹出框，带确认关闭按钮的事件和感叹号标志
 * msg 弹框内容 wait确认关闭按钮的事件函数 time函数中的倒计时间
 */
function myDialog(msg,wait,time){
	art.dialog({
        lock: true,
	    content: msg,
	    ok:function(){wait(time);},
	    close:function(){wait(time);}
	});
    $(".aui_iconBg").attr('style','background: url("'+staticCss+'/artDialog4.1.7/skins/icons/warning.png") repeat scroll 0% 0% transparent;');
    $(".aui_icon").show();
}
$.ajax({ 
	type : "POST", 
	url : "/index.do?method=getMD5IP", 
	data: "", 
	dataType:"json", 
	contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	success : function(result) { 
	$("#ser_num").html(result); 
	} 
	});
$(".tab_u").click(function(){
	$("#div_geetest_lib").empty();
	$("#div_geetest_two").empty();
	$("#div_id_embed").empty();
	$("#div_id_embed_two").empty();
	reloadJs("#slidingLogin", '../js/common/slidingValidateCodeLogin.js');
});

function reloadJs(id, newJS) {
	var oldjs = null;
	var t = null;
	var oldjs = document.getElementById(id);
	if (oldjs)
		oldjs.parentNode.removeChild(oldjs);
	var scriptObj = document.createElement("script");
	scriptObj.src = newJS;
	scriptObj.type = "text/javascript";
	scriptObj.id = id;
	document.getElementsByTagName("head")[0].appendChild(scriptObj);
}
