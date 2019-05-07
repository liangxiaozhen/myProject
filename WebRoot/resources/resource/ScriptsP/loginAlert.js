$(function () {
	var mobileFlag=false,//手机号码验证通过标识
	imgCodeFlag=false,//图形验证码验证通过标识
	phonecodeFlag = false;//手机短信验证码验证通过标识
	
	var gotourl =  encodeURIComponent(document.URL);
	$.ajax({ 
		type : "post", 
		url : "/index.do?method=setGotoUrl", 
		contentType : "application/x-www-form-urlencoded; charset=UTF-8", 
		data:{"gotourl":gotourl}, 
		dataType:"json", 
		success : function(data) { 
		}
		});
	
	$("#mobile").blur(function(){
		mobileCh();
	});
	$("#regSubmit").click(function(){
		register();
	});
	/***
	 * 验证码
	 */
	$("#imgcode1").blur(function(){
		imgcodeCk();
	});
    //手机验证码
    $('#gainMessage1').click(function () {
    	getPhoneCode();
    });
    
    /***
     * 验证提示
     * @param id
     * @param msg
     */
    function showErr(id,msg){
    	$("#"+id).html("<i class=\"ico_all size15 img_icon s_cuo\"></i>"+msg);
    	$("#"+id).css("display","block");
    }
    /***
     * 获取手机验证码
     */
    function getPhoneCode(){
    	mobileCh();
    	if(mobileFlag == true){
    		//写临时表
    		$.ajax({  
    			type : "post",  
    			url : "/index.do?method=preReg&quick=quick",  
    			data :{
    	        	regFrom: 899,
    	    		mobile: $('#mobile').val(),
    	    		imgCode: $('#imgcode1').val()
    	        },
    			async : false,  
    			success : function(result){
    				$('#uname').val(result['uname']);
    		    	$('#pwd1').val(result['pwd']);
    		    	$('#repwd').val(result['pwd']);
    	    	    $.post("/index.do?method=sendRegSmsCodeByPhone&interval=60", 
    	    	    		{phone: $('#mobile').val(),imgCode: $("#imgcode1").val()}, function (result){
    	    	    	if(result == 'succ'){
    	    	    		countDownTime(60);//倒计时,60s动画展示
    	    			}else{
    	    				showErr("errorMsg1",result);
    	    				//验证码刷新
    	    		    	refreshcode();
    	    			}
    	    	    });
    	             
    			},error:function(XMLHttpRequest, textStatus, errorThrown){
    				alert("请求失败");
    			}  
    	     });  
    	}else if(mobileFlag == false){
    		showErr("errorMsg1","请填写您的真实手机号码");
    	}else{
    		showErr("errorMsg1","验证出错了");
    	}
    }

    //图片验证码
    $("#refush1").click(function(){
		var d = new Date();
		var src = "/validatecode/refresh.htm?date=" + d.getTime();
		$("#imgc").attr("src",src);
		return false;
	});
    
    
    function countDownTime(time){
		if(!time){
			time=60;
		}
		var t=setInterval(function(){
			time--;
			$("#gainMessage1").attr("disabled",true);//按钮不可用
			$("#gainMessage1").val(time+"秒");
			if(time<0){
				$("#gainMessage1").val("重新获取");
				$("#gainMessage1").attr('disabled',false);//按钮可用
				clearTimeout(t);
			}
		},1000);
	}
    /*
     * 图片验证码输入框失去焦点的校验
     */
    function imgcodeCk(){
    	var imgCodeVal = $("#imgcode1").val();
    	if(imgCodeVal == ''){//输入为空
    		imgCodeFlag = false;
    		showErr("errorMsg1","验证码不能为空");
    		return false;
    	}else{//以上验证通过
    		//异步改同步
    		$.ajax({  
    			type : "post",  
    			url : "/index.do?method=validateImgCode",  
    			data : "imgCode=" + imgCodeVal,  
    			async : false,  
    			success : function(msg){  
    				if (msg == 1) {
    	        		imgCodeFlag = true;
    	        		validatePass("errorMsg1");
    	        		$.ajax({  
    	        			type : "post",  
    	        			url : "/index.do?method=checkPhoneIsUsed",  
    	        			data : {phone: $("#mobile").val(),imgCode: imgCodeVal},  
    	        			async : false,  
    	        			success : function(msg){  
    	        				if (msg == 1) {
    	        					mobileFlag = true;
    	        	        		validatePass("errorMsg1");
    	        	        		return true;
    	        	            }else if(msg == 0){
    	        	            	mobileFlag = false;
    	        	            	showErr("errorMsg1","手机号码已注册！");
    	        	            	refreshcode();
    	        	            	return false;
    	        	            }  else{
    	        	            	mobileFlag = false;
    	        	            	showErr("errorMsg1",msg);
    	        	            	refreshcode();
    	        	            	return false;
    	        	            }
    	        			}  
    	        	     });
    	            }else{
    	            	imgCodeFlag = false;
    	            	showErr("errorMsg1","图形验证码错误");
    	            	refreshcode();
    	            	return false;
    	            }  
    			}  
    	     });
    	}
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
    		showErr("errorMsg1","手机号码不能为空");
    		return false;
    	}else if(mobileVal.length != 11){
    		mobileFlag = false;
    		showErr("errorMsg1","请输入11位手机号码");
    		return false;
    	}else if(!mobileVal.match("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[3|6|7|8])|18[0-9])\\d{8}|(170\\d{8})$")){//手机号码格式错误
    		mobileFlag = false;
    		showErr("errorMsg1","手机号码格式错误");
    		return false;
    	}else{//以上验证通过
    		mobileFlag = true;
    		validatePass("errorMsg1");
    		return true;
    	}
    }
    /***
     * 清除验证提示
     * @param id
     */
    function validatePass(id){
    	$("#"+id).css("display","none");
    }
    /*
     * 换一张图片验证码
     */
    function refreshcode(){
    		var d = new Date();
    		var src = "/validatecode/refresh.htm?date=" + d.getTime();
    		$("#imgc").attr("src",src);
    		//图片验证码输入框清空
			$('#imgcode1').val("");
    }
    /*
     * 注册按钮提交表单的处理
     * 先校验元素的合法性
     */
    function register(){
    	//$("#mobile").blur();
    	//$("#imgcode1").blur();
    	var f1 = mobileCh();
    	if(f1 == false) return false;
    	f1 = imgcodeCk();
    	if(f1 == false) return false;
    	
    	
    	if(mobileFlag&&imgCodeFlag){
    		$("#regSubmit").attr("disabled",true);
    		$("#regSubmit").css("background","#c0bcc9");
    		$.post("/index.do?method=doRegisterAction", {
        		mobile: $('#mobile').val(),
        		uname : $('#uname').val(),
        		pwd : $('#pwd1').val(),
        		repwd : $('#repwd').val(),
        		verify: $('#phoneCode').val(),
        		regFrom: 0,
        		type:'reg',
        		regPage: $('#regPage').val(),
        		quick:'quick',
        		recommendCode: $("#recommendCode").val(),
	    		amount:$.trim($("#investmentAmount").val()),
	    		fpId:$("#fpId").val(),
	    		lid:$("#lid").val()
        },
        function (data){
        	data=$.parseJSON(data);
        	if(data['msg'] == undefined ||data['msg'] == 'undefined'){
        		showErr("errorMsg1","注册出错，请刷新页面重新注册");
        		$("#regSubmit").attr("disabled",false);
        		$("#regSubmit").css("background","#ff7f00");
        	}else if(data['msg'] == '0'){
        	 		showErr("errorMsg1","注册失败，请重新注册！");
        	 		$("#regSubmit").attr("disabled",false);
        	 		$("#regSubmit").css("background","#ff7f00");
        	}else if(data['msg'] == '-1'){
            	showErr("errorMsg1","手机号已注册！");
            	$("#regSubmit").attr("disabled",false);
            	$("#regSubmit").css("background","#ff7f00");
        	}else if(data['msg'] == '1'){
        			showErr("errorMsg1","表单验证失败,请重新填写!");
        			$("#regSubmit").attr("disabled",false);
        			$("#regSubmit").css("background","#ff7f00");
        	}else if(data['msg'] == 'verifyNo'){
            	showErr("errorMsg1","请输入正确的手机验证码!");
            	$("#regSubmit").attr("disabled",false);
            	$("#regSubmit").css("background","#ff7f00");
        	}else if(data['msg'] == 'verifyVoid'){
            	showErr("errorMsg1","验证码超时，请点击重新发送!");
            	$("#regSubmit").css("background","#ff7f00");
            	$("#regSubmit").attr("disabled",false);
        	}else if(data['msg'] == 'unsent'){
            	showErr("errorMsg1","请稍后重新点击获取验证码!");
            	$("#regSubmit").css("background","#ff7f00");
            	$("#regSubmit").attr("disabled",false);
        	}else if(data['msg'] == 'fucodeNeed'){
            	showErr("errorMsg1","请点击获取手机验证码!");
            	$("#regSubmit").attr("disabled",false);
            	$("#regSubmit").css("background","#ff7f00");
        	}else if(data['msg'] == 'succeed'){
            	if(activityUrl){            		
            		setCookie('nwdunamecookie',"nwdunamecookie");
            		window.location.href = activityUrl;
        		}else{
        			window.location.href = "/loading.html";//注册成功的跳转地址
        		}
        	}
        });
     }
    	
   }
    
    /**======================快速注册-推荐码 s ===============================================================**/
	//推荐码
    $(".code").click(function() {
        $('.code_box').slideToggle("500");
        $(".code .arrow").toggleClass('hover');
    });
    
    $(".code_box .inputOut").click(function(event) {
        if($(".tui").val()==""){
            $('.input_sp').hide();       
            $('.input_sp').css('font-family', 'Microsoft YaHei');
            $('.tui').focus();
        }else{
            $(this).css('font-family', 'arial');
            
        } 
    });
    $(".code_box .inputOut input").blur(function(){
        if($(".tui").val()==""){
            $(".input_sp").show();
            $('.input_sp').css('font-family', 'Microsoft YaHei');
        }else{
        	$('.input_sp').hide();       
            $('.input_sp').css('font-family', 'Microsoft YaHei');
    	}
    });    
    
    /**======================快速注册-推荐码 e ===============================================================**/
    
    
	//根据cookie判断是否是新用户,从而显示相应的操作：登录或注册
	var nwdunamecookie = getCookie('nwdunamecookie');
	//有值代表是老用户
	$("#l_tab_btn").removeClass("active");//老手登录
	$("#r_tab_btn").removeClass("active");//新手快捷购买
	$(".log_box").css('display','none'); 
	$(".reg_box").css('display','none');
	//$("#r_tab_btn").text("新手快捷注册");
		
	if(nwdunamecookie){		
		$("#l_tab_btn").addClass("active");//老手登录
		$(".log_box").css('display','block');
	}else{
		$("#r_tab_btn").addClass("active");//新手快捷购买
		$(".reg_box").css('display','block');
	}    
});

//设置登录cookie，用于判断用户是否是新手，从而显示相应的操作：登录或注册
//setCookie('nwdunamecookie',cookieUname);
//var nwdunamecookie = "";

//设置cookie
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

//设置 regTitle 的值，不调用此方法就是默认的值：新手快捷购买 。
function setTitle(name){
	$("#regTitle").text(name);	
}
