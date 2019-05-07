function openEmail(id){
		var e = $("#email").val().split("@");
		if(e[1] == '163.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.163.com/";
		}else if(e[1] == 'qq.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.qq.com/";
		}else if(e[1] == '126.com'){
			document.getElementById("openEmail"+id+"").href = "http://126.com/";
		}else if(e[1] == '188.com'){
			document.getElementById("openEmail"+id+"").href = "http://188.com/";
		}else if(e[1] == 'yeah.net'){
			document.getElementById("openEmail"+id+"").href = "http://yeah.net/";
		}else if(e[1] == 'sina.com.cn'){
			document.getElementById("openEmail"+id+"").href = "http://mail.sina.com.cn/";
		}else if(e[1] == 'foxmail.com'){
			document.getElementById("openEmail"+id+"").href = "http://foxmail.com/";
		}else if(e[1] == 'tom.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.tom.com/";
		}else if(e[1] == 'sohu.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.sohu.com/";
		}else if(e[1] == 'yahoo.com.cn'){
			document.getElementById("openEmail"+id+"").href = "http://mail.yahoo.com.cn/";
		}else if(e[1] == 'gmail.com'){
			document.getElementById("openEmail"+id+"").href = "http://gmail.com/";
		}else if(e[1] == 'live.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.live.com/";
		}else if(e[1] == 'eyou.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.eyou.com/";
		}else if(e[1] == '35.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.35.com/";
		}else if(e[1] == 'china.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.china.com/";
		}else if(e[1] == 'china.com.cn'){
			document.getElementById("openEmail"+id+"").href = "http://mail.china.com.cn/";
		}else if(e[1] == '139.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.139.com/";
		}else if(e[1] == '21cn.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.21cn.com/";
		}else if(e[1] == 'aol.com'){
			document.getElementById("openEmail"+id+"").href = "http://mail.aol.com/";
		}else if(e[1] == '71.com'){
			document.getElementById("openEmail"+id+"").href = "http://www.71.com/";
		}else if(e[1] == 'hotmail.com'){
			document.getElementById("openEmail"+id+"").href = "http://www.hotmail.com/";
		}else{
			document.getElementById("openEmail"+id+"").href = "http://exmail.qq.com/";
		}
		//window.location.href='/member/nwdEmailVcodeRecordStep.do?step=3';
	}

	function sendEmail2(){
		$.ajax({
	        	type: "post",
	       	 	url: "/member/sendEmail.do",
	        	dataType: "json",
	        	data:{
	        		recordEmail:$("#email").val()
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){
						alert("邮件已重新发送，请您查收！");
	        		}else if(msg==0){
	        			alert("邮件重发失败，请稍后重试！");
	        		}
	        	}   
	      });
	}