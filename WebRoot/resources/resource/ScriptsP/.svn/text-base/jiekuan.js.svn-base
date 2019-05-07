var errorCount = 0;
var mobileFlag=false,//手机号码验证通过标识
imgCodeFlag=false,//图形验证码验证通过标识
fucodeNeedFlag = false;
fucodeFlag = false;//手机短信验证码验证通过标识
var mid = $('#mbId').val();
getNewStok();
$.ajax({  
	type : "post", 
	url : "/index.do?method=setGotoUrl", 
	contentType : "application/x-www-form-urlencoded; charset=UTF-8", 
	data:{"gotourl":encodeURIComponent(window.location.href)}, 
	dataType:"json", 
	success : function(data) { 
	} 
	}); 

	// 绑定各个输入框失去焦点事件
	$("#realName").blur(function() {
		var realName = $(this).val();
		if(validateFunction.isEmpty($(this).val())) {
			$("#realNameError").html("真实姓名不能为空");
			$("#realNameError").show();
			errorCount++;
		} else {
			if (!validateFunction.isChinese(realName) || !validateFunction.lengthCheck(realName, 2, 15)) {
				$("#realNameError").html("真实姓名必须为2-15个汉字");
				$("#realNameError").show();
				errorCount++;
			} else {
				$("#realNameError").hide();
			}
		}
	});
	
	// 移动电话
	$("#mobile").blur(function() {
		var mobile = $(this).val();
		if(validateFunction.isEmpty(mobile)) {
			$("#mobileError").html("移动电话不能为空");
			$("#mobileError").show();
			mobileFlag = false;
			errorCount++;
		} else {
			if (!validateFunction.isMobile(mobile)) {
				$("#mobileError").html("请输入11位手机号码");
				$("#mobileError").show();
				mobileFlag = false;
				errorCount++;
			} else if(!mobile.match("^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[3|6|7|8])|18[0-9])\\d{8}|(170\\d{8})$")){//手机号码格式错误
				mobileFlag = false;
				$("#mobileError").html("手机号码格式错误");
				$("#mobileError").show();
			}else{//以上验证通过
				$("#mobileError").hide();
				if(mid>0){
					mobileFlag = true;
				} 
				
			}
		}
	});
	

	$("#imgcode").blur(function(){
		var imgCodeVal = $("#imgcode").val();
		if(imgCodeVal == ''){//输入为空
			imgCodeFlag = false;
			$("#imgcodeError").html("验证码不能为空");
			$("#imgcodeError").show();
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
		    			$("#imgcodeError").hide();
		        		$.ajax({  
		    				type : "post",  
		    				url : "/index.do?method=checkUname",  
		    				data : "username=" + $("#mobile").val()+"&flag=pc&imgcode=" + $("#imgcode").val(),  
		    				async : false,  
		    				success : function(msg1){ 
		    					if (msg1 == 0) {
		    						mobileFlag = true;
		    		            }else if(msg1 == -10){
		    		            	$("#imgcodeError").html("验证码不能为空");
		    		    			$("#imgcodeError").show();
		    		            	mobileFlag = false;
		    		            } else if(msg1 == -11){
		    		            	$("#imgcodeError").html("图形验证码错误");
		    		    			$("#imgcodeError").show();
		    		            	mobileFlag = false;
		    		            } else if(msg1 == -12){
		    		            	$("#imgcodeError").html("图形验证码过期");
		    		    			$("#imgcodeError").show();
		    		            	mobileFlag = false;
		    		            } else {
		    		            	mobileFlag = false;
		    		            	window.location.href="/login.html";
		    		            	$("#mobile").val("");
		    		            }  
		    				}     
		    		     });
		            }else{
		            	refreshcode();
		            	imgCodeFlag = false;
		            	$("#imgcodeError").html("图形验证码错误");
		    			$("#imgcodeError").show();
		            	
		            }  
				}
		     });
		}
//	}).click(function(){
//		alert(2222);
//		mobileFlag = false;
	});
	
	// 校验借款金额
	$("#loanAmount").blur(function() {
		var amount = $(this).val();
		if (validateFunction.isEmpty(amount)) {
			$("#amountError").html("借款金额不能为空");
			$("#amountError").show();
			errorCount++;
		} else {
			if (!validateFunction.isCurrency(amount)) {
				$("#amountError").html("借款金额格式有误");
    			$("#amountError").show();
				errorCount++;
			} else if (amount < 20000 || amount > 500000) {
    			$("#amountError").html("借款金额必须为2-50万之间");
    			$("#amountError").show();
    			errorCount++;
    		} else {
    			$("#amountError").hide();
    		}
		}
	});
	
	
	$("#save").click(function() {
		errorCount = 0;
		// 校验居住城市
		var city = $("#city").val();
		if (validateFunction.isEmpty(city)) {
			$("#cityError").show();
			errorCount++;
		}
		var age = $("#age").val();
		if (age==0) {
			$("#ageError").show();
			errorCount++;
		}
		
		var $applyForm = $("#applyForm");
		$applyForm.find("input[type='text']").each(function() {
			$(this).blur();
		});
		
		if(mid>0){
			if (errorCount == 0) {
				setTimeout(function(){
					$applyForm.submit();
				},800);
			}
		} else {
			$('#mobile').trigger("blur");
			$('#fucode').trigger("change");
			fucodeNeed();
			 
			var result = mobileFlag && fucodeFlag
						&& fucodeNeedFlag;
			if(!result){
				return false;
			}
			/*
			 * 所有的页面元素都验证通过后，异步请求提交表单
			 */
			$.post("/index.do?method=doRegisterAction", {
					mobile: $('#mobile').val(),
					uname : $('#uname').val(),
					pwd : $('#pwd').val(),
					repwd : $('#repwd').val(),
					verify: $('#fucode').val(),
					regFrom: $("#regFrom").val(),
					type:'reg',
					quick:'quick',
					recommendCode:$('#recommendCode').val(),
					flag:'apply',
					giveIntegral:0
		    },
		    function (data){
		    	data=$.parseJSON(data);
		   	 	if(data['msg'] == '0'){
		   	 	alert("注册失败，请重新注册！");
				}else if(data['msg'] == '-1'){
					alert("用户名已注册！");
				}else if(data['msg'] == '-2'){
					alert("该手机号已被注册");
				}else if(data['msg'] == '1'){
					alert("表单验证失败,请重新填写!");
				}else if(data['msg'] == 'verifyNo'){
					alert("请输入正确的手机验证码!");
				}else if(data['msg'] == 'verifyVoid'){
					alert("验证码超时，请点击重新发送!");
				}else if(data['msg'] == 'unsent'){
					alert("请稍后重新点击获取验证码!");
				}else if(data['msg'] == 'fucodeNeed'){
					alert("请点击获取手机验证码!");
				}else if(data['msg'] == undefined ||data['msg'] == 'undefined'){
					alert("请刷新页面重新注册");
				}else {
					var $applyForm = $("#applyForm");
						if (errorCount == 0) {
							setTimeout(function(){
								$applyForm.submit();
							},800);
						}
				}
		    });
		}
		
		
	});

	function fucodeNeed(){
		var nmame = $('#uname').val();
		var pwd = $('#pwd').val();
		var repwd = $('#repwd').val();
		if(nmame=='' || pwd=='' || repwd==''){
			alert("请点击获取手机验证码!");
			fucodeNeedFlag = false;
		}else{
			fucodeNeedFlag = true;
		}
	}


function putyzm(){
	if($("#dtmbtn").attr("disabled") == 'disabled'){
		return false;
	}
	$('#mobile').trigger("blur");
	imgcodeCk();
	
	var result = mobileFlag && imgCodeFlag;
	if(!result){
		return false;
	}
	//写临时表
	$.ajax({  
		type : "post",  
		url : "/index.do?method=preReg&quick=quick",  
		data :{
        	regFrom: 0,
    		mobile: $('#mobile').val(),
    		imgCode: $('#imgcode').val()
        },
        dataType: 'json', 
		async : false,  
		success : function(result){
			$('#uname').val(result.uname);
	    	$('#pwd').val(result.pwd);
	    	$('#repwd').val(result.pwd);
	    	
    	    $.post("/msg/sendRegTextMessage.do?interval=60", {phone: $('#mobile').val(),imgCode: $("#imgcode").val()}, function (result){
    	    	if(result == 'succ'){
    	    		countDown(60);//倒计时,60s动画展示
    			}else if(result == '该手机号码已经注册'){
    				alert('该手机号已注册，请点击<a href="/login.html">登录</a>');
    				//验证码刷新
    		    	refreshcode();
    			}else if(result == '手机号码格式错误'){
    				alert("手机号码格式错误");
    				//验证码刷新
    		    	refreshcode();
    			}else if(result == '图片验证码超时,请刷新！'){
    				alert("验证码已失效，请输入新的图形验证码");
    				//验证码刷新
    		    	refreshcode();
    			}else if(result == '请输入正确的图片验证码'){
    				alert("请输入正确的图片验证码");
    				//验证码刷新
    		    	refreshcode();
    			}else{
    				alert(result);
    				//验证码刷新
    		    	refreshcode();
    			}
    	    });
             
		}  
     });  
}

/*
 * added by fanghui 2014.11.17
 * 图片验证码输入框失去焦点的校验
 */
/*function imgcodeCk(){
	var imgCodeVal = $("#imgcode").val();
	alert("cc"+imgCodeVal);
	if(imgCodeVal == ''){//输入为空
		imgCodeFlag = false;
		$("#imgcodeError").html("验证码不能为空");
		$("#imgcodeError").show();
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
	        		$("#imgcodeError").hide();
	            }else{
	            	imgCodeFlag = false;
	            	$("#imgcodeError").html("图形验证码错误");
	    			$("#imgcodeError").show();
	            	refreshcode();
	            }  
			}  
	     });
	}
}*/

$("#imgc").click(function(event){
	event.preventDefault();//阻止冒泡,因为图形验证码是定义在文本框内部的
	refreshcode();
});

/*
 * added by fanghui 2014.11.17
 * 手机校验码输入框失去焦点的校验
 */
$("#fucode").change(function(){
	var _fucode = $('#fucode').val();
	if(_fucode == ''){
		alert("短信验证码不能为空");
	}else{//以上验证通过
		fucodeFlag = true;
	}
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
		$('#imgcode').val("");
}

/*
 * added by fanghui 2014.11.17
 * 获取手机短信倒计时
 */
function countDown(time){
	$("#dtmbtn").attr('disabled',true);//按钮不可用
    $("#dtmbtn").text(time+"秒");
    time = time - 1;
    if(time>=0){
        setTimeout("countDown("+time+")",1000);
    }else{
    	$("#dtmbtn").text("重新获取");
    	$("#dtmbtn").attr('disabled',false);//按钮可用
    	//验证码刷新
    	refreshcode();
    }
}

function keyupSendMsg(){
	var dis = $("#dtmbtn").attr('disabled');
	if(dis == 'disabled'){
	}else{
		//发送验证码
		putyzm();
	}
}

//生成新token
function getNewStok(){
	$.ajax({
		type: "post",
		url: "/activity/createStokAjax.do",
		data:{},
		dataType:"json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		error:function(res){
		},
		success: function(res){
			$("#stokStr").val(res.stok);
		}
	});
}