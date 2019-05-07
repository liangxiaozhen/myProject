var atPresentPwdFlag = false;
	var newPwdFlag = false;
	var newPwd0Flag = false;
	$(document).ready(function(){
		var cur_dh = $('#xinxi_nwd_8');
	    cur_dh.addClass('active');
	    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
		
		$("#atPresentPwd").blur(function(){
			var atPresentPwd = $("#atPresentPwd").val();
			//var reg = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~\+\-\,\_]{6,20}$/;
			if(atPresentPwd==""){
				atPresentPwdFlag = false;
				$('#atPresentPwdMSG').css("display","");
				$("#atPresentPwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入当前登录密码！");
			}else{
				$.ajax({
	        	type: "post",
	       	 	url: "/member/memberCheckPwd.do",
	        	dataType: "json",
	        	data:{
	        		atPresentPwd:$("#atPresentPwd").val(),
	        		type:"pwd"
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg['msg'] == 1){
	        			atPresentPwdFlag = true;
						$('#atPresentPwdMSG').css("display","");
						$("#atPresentPwdMSG").removeClass().html("");
	        		}else if(msg['msg']==0){
	        			atPresentPwdFlag = false;
						$('#atPresentPwdMSG').css("display","");
						$("#atPresentPwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>当前登录密码有误！");
	        		}
	        	}   
	      }); 	
			}
	 	});
		
		$("#newPwd").blur(function(){
			var atPresentPwd = $("#atPresentPwd").val();
			var newPwd = $("#newPwd").val().replace(/(^\s*)|(\s*$)/g,'');
			//var reg = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~\+\-\,\_]{6,20}$/;
			if(newPwd==""){
				newPwdFlag = false;
				$('#newPwdMSG').css("display","");
				$("#newPwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>密码由6~20位英文、数字或标点符号组成");
			}else if(!newPwd.match(regexInfo.pwdRegex)){
				newPwdFlag = false;
				$('#newPwdMSG').css("display","");
				$("#newPwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>密码由6~20位英文、数字或标点符号组成");
			}else if(newPwd == atPresentPwd){
				newPwdFlag = false;
				$('#newPwdMSG').css("display","");
				$("#newPwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>新密码不能与旧密码相同！");
			}else{
				//交易密码不能与登录密码相同
				$.ajax({
		        	type: "post", 
		       	 	url: "/member/memberCheckPwdAndRePwd.do",
		        	dataType: "json",
		        	data:{
		        		newPwd1:newPwd,
		        		type:"pwd"
		       		 },
		        	async: false,
		        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        	success: function(msg){
		        		if(msg == 0){
		        			newPwdFlag = true;
		        			$('#newPwdMSG').css("display","");
		        			$("#newPwdMSG").removeClass().html("");
		        		}else if(msg==1){
		        			newPwdFlag = false;
		        			$('#newPwdMSG').css("display","");
		        			$("#newPwdMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>登录密码不能与交易密码相同！");
		        		}
		        	}   
		      }); 
			}
	 	});
		
		$("#newPwd0").blur(function(){
			var newPwd = $("#newPwd").val().replace(/(^\s*)|(\s*$)/g,'');
			var newPwd0 = $("#newPwd0").val().replace(/(^\s*)|(\s*$)/g,'');
			//var reg = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~\+\-\,\_]{6,20}$/;
			if(newPwd0==""){
				newPwd0Flag = false;
				$('#newPwd0MSG').css("display","");
				$("#newPwd0MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入确认新登录密码！");
			}else if(!newPwd0.match(regexInfo.pwdRegex)){
				newPwd0Flag = false;
				$('#newPwd0MSG').css("display","");
				$("#newPwd0MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>两次密码输入不一致！");
			}else if(newPwd != newPwd0){
				newPwd0Flag = false;
				$('#newPwd0MSG').css("display","");
				$("#newPwd0MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>两次密码输入不一致！");
			}else{
				newPwd0Flag = true;
				$('#newPwd0MSG').css("display","");
				$("#newPwd0MSG").removeClass().html("");	
			}
	 	});
		
		$("#atPresentPwd1").blur(function(){
			var atPresentPwd1 = $("#atPresentPwd1").val();
			//var reg = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~\+\-\,\_]{6,20}$/;
			if(atPresentPwd1==""){
				atPresentPwdFlag = false;
				$('#atPresentPwd1MSG').css("display","");
				$("#atPresentPwd1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入当前交易密码！");
			}else{
				$.ajax({
	        	type: "post",
	       	 	url: "/member/memberCheckPwd.do",
	        	dataType: "json",
	        	data:{
	        		atPresentPwd:$("#atPresentPwd1").val(),
	        		type:"repwd"
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg['msg'] == 1){
	        			atPresentPwdFlag = true;
						$('#atPresentPwd1MSG').css("display","");
						$("#atPresentPwd1MSG").removeClass().html("");
	        		}else if(msg['msg']==0){
	        			atPresentPwdFlag = false;
						$('#atPresentPwd1MSG').css("display","");
						$("#atPresentPwd1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>当前交易密码有误！");
	        		}
	        	}   
	      }); 	
			}
	 	});
		
		$("#newPwd1").blur(function(){
			var atPresentPwd1 = $("#atPresentPwd1").val();
			var newPwd1 = $("#newPwd1").val();
			var reg = /^[a-zA-Z0-9]{6,16}$/;
			if(newPwd1==""){
				newPwdFlag = false;
				$('#newPwd1MSG').css("display","");
				$("#newPwd1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入6~16位的字符，可由英文、数字组成！");
			}else if(!reg.test(newPwd1)){
				newPwdFlag = false;
				$('#newPwd1MSG').css("display","");
				$("#newPwd1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请填写6~16位的字符，可由英文、数字组成");
			}else if(newPwd1 == atPresentPwd1){
				newPwdFlag = false;
				$('#newPwd1MSG').css("display","");
				$("#newPwd1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>新密码不能与旧密码相同");
			}else{
				//交易密码不能与登录密码相同
				$.ajax({
		        	type: "post",
		       	 	url: "/member/memberCheckPwdAndRePwd.do",
		        	dataType: "json",
		        	ContentType:"text/json;charset=utf-8",
		        	data:{
		        		newPwd1:newPwd1,
		        		type:"repwd"
		       		 },
		        	async: false,
		        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        	success: function(msg){
		        		if(msg == 0){
		        			newPwdFlag = true;
		        			$('#newPwd1MSG').css("display","");
		        			$("#newPwd1MSG").removeClass().html("");	
		        		}else if(msg==1){
		        			newPwdFlag = false;
		        			$('#newPwd1MSG').css("display","");
		        			$("#newPwd1MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>交易密码不能与登录密码相同！");
		        		}
		        	}   
		      }); 
			}
	 	});
		
		$("#newPwd2").blur(function(){
			var newPwd1 = $("#newPwd1").val();
			var newPwd2 = $("#newPwd2").val();
			var reg = /^[a-zA-Z0-9]{6,20}$/;
			if(newPwd2==""){
				newPwd0Flag = false;
				$('#newPwd2MSG').css("display","");
				$("#newPwd2MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入确认新交易密码！");
			}else if(!reg.test(newPwd2)){
				newPwd0Flag = false;
				$('#newPwd2MSG').css("display","");
				$("#newPwd2MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>两次密码输入不一致！");
			}else if(newPwd1 != newPwd2){
				newPwd0Flag = false;
				$('#newPwd2MSG').css("display","");
				$("#newPwd2MSG").removeClass().addClass("prompt_1 error_1").html("<em></em>两次密码输入不一致！");
			}else{
				newPwd0Flag = true;
				$('#newPwd2MSG').css("display","");
				$("#newPwd2MSG").removeClass().html("");	
			}
	 	});
		
	});

	//点击修改刷新
	//var showNext=$('.showNext');
	//showNext.bind('click',function(){
	//	showNext.parent().next().children(':first').removeClass('none').sibilings().addClass('none');
	//})
	function pwdChange(type){
		var password = "";
		var rePassword = "";
		var oldPwd = "";
		if(type == 'pwd'){
			$("#atPresentPwd").blur();
			$("#newPwd").blur();
			$("#newPwd0").blur();
			password = $("#newPwd").val();
			rePassword = $("#newPwd0").val();
			oldPwd = $("#atPresentPwd").val();
		}else if(type == 'repwd'){
			$("#atPresentPwd1").blur();
			$("#newPwd1").blur();
			$("#newPwd2").blur();
			password = $("#newPwd1").val();
			rePassword = $("#newPwd2").val();
			oldPwd = $("#atPresentPwd1").val();
		}
		if(atPresentPwdFlag == true && newPwdFlag == true && newPwd0Flag == true){
			$.ajax({
	        	type: "post",
	       	 	url: "/member/memberChangePwdAndRePwd.do",
	        	dataType: "json",
	        	data:{
	        		password:password,
	        		rePassword:rePassword,
	        		oldPwd:oldPwd,
	        		type:type
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){//登录密码  成功
	        			$('#atPresentPwd').val('');
	        			$('#newPwd').val('');
	        			$('#newPwd0').val('');
	        			$('#loginPwd').addClass('none');
	        			$('#sucessPwd').removeClass('none');
	        			setTimeout(function(){
	        				$('#sucessPwd').parents('tr').addClass('none');
	        				$('#loginPwd').removeClass('none');
		        			$('#sucessPwd').addClass('none');
		        			window.location.reload();
	        			},2000)
	        			//window.location.reload();
	        			//$('#sucessPwd').parents('tr').delay('5000').addClass('none');
	        			//window.location.href="/member/changeBasicInfoPwd.do?type=pwd&doWhat=success";
	        		}else if(msg==0){//登录密码  失败
	        			$(".plusBank1 .content").html('登录密码修改失败！');
	        			showCon_1();
	        			//window.location.href="/member/changeBasicInfoPwd.do?type=pwd&doWhat=failure";
	        		}else if(msg == 3){//交易密码  失败
	        			$(".plusBank1 .content").html('交易密码修改失败！');
	        			showCon_1();
	        			//window.location.href="/member/changeBasicInfoPwd.do?type=repwd&doWhat=failure";
	        		}else if(msg == 4){//交易密码  成功
	        			$('#atPresentPwd1').val('');
	        			$('#newPwd1').val('');
	        			$('#newPwd2').val('');
	        			$('#rePwd').addClass('none');
	        			$('#sucessRePwd').removeClass('none');
	        			setTimeout(function(){
	        				$('#sucessRePwd').parents('tr').addClass('none');
	        				$('#rePwd').removeClass('none');
		        			$('#sucessRePwd').addClass('none');
		        			window.location.reload();
	        			},2000)
	        			//window.location.href="/member/changeBasicInfoPwd.do?type=repwd&doWhat=success";
	        		}
	        	}   
	      }); 	
		}
	}

	//====================================
	// 修改密码时，验证密码强度
	// @athor:added by yantianzeng 
	//====================================
	function pwStrength(pwd){
	    if (pwd==null||pwd==''){
	       $("#weak").removeClass();
	       $("#centre").removeClass();
	       $("#strong").removeClass();
	       $("#mima1").css("display","none");
	    }else{
	       S_level=checkStrong(pwd);
	       switch(S_level) {
	          case 0:
	            $("#weak").removeClass();
	            $("#centre").removeClass();
	            $("#strong").removeClass();
	            $("#mima1").css("display","none");
	            break;
	          case 1:
	        	  $("#mima1").css("display","");
	           		$("#weak").removeClass().addClass("bg");//弱
	           		$("#centre").removeClass();
	           		$("#strong").removeClass();
	            break;
	          case 2:
	        	  $("#mima1").css("display","");
	        	  $("#weak").removeClass().addClass("bg");
	        	  $("#centre").removeClass().addClass("bg");//中
	        	  $("#strong").removeClass();
	        	  break;
	          default:
	        	  $("#mima1").css("display","");
	        	  $("#weak").removeClass().addClass("bg");
	        	  $("#centre").removeClass().addClass("bg");//中
	        	  $("#strong").removeClass().addClass("bg");//强
	            break;
	        }
	    }
	}

	//====================================
	//修改密码时，验证密码强度
	//@athor:added by yantianzeng 
	//====================================
	function bitTotal(num){
		modes=0;
		for (var i=0;i<4;i++){
			if (num & 1) modes++;
			num>>>=1;
		}
		return modes;
	}

	//====================================
	//修改密码时，验证密码强度
	//@athor:added by yantianzeng 
	//checkStrong函数
	//返回密码的强度级别
	//====================================
	function checkStrong(sPW){
		if (sPW.length<=5)
		return 0; //密码太短
		Modes=0;
		for (var i=0;i<sPW.length;i++){
			//测试每一个字符的类别并统计一共有多少种模式.
			Modes|=CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}

	//====================================
	//修改密码时，验证密码强度
	//@athor:added by yantianzeng 
	//====================================
	function CharMode(iN){
	if (iN>=48 && iN <=57) //数字
		return 1;
	if (iN>=65 && iN <=90) //大写字母
		return 2;
	if (iN>=97 && iN <=122) //小写
		return 4;
	else
		return 8; //特殊字符
	}