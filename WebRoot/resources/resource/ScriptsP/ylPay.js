var ylPay = ylPay || {};
ylPay.init = function(){
	ylPay.initialize();
};

ylPay.initialize = function(){
	//银行卡号失去焦点事件处理
	 $("#mbbCardNo").blur(function(){
		 if(rechargeBase.CHANNELID!=CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K){
		    	return;
		 }
	 	if(llPay.checkCardNo()){//借助连连支付平台的接口去验卡格式
	 		//卡格式验证通过，再去判断当前是否已经经过了易连认证，如果是需要展示手机号码
	 		var flag = ylPay.verify.query($(this).val(),undefined);
	 		if(flag){
	 			$("#tab_info tr:eq(5),#tab_info tr:eq(6)").show();
	 			$("#phone").val($("#mbPhone").val()).attr("disabled","disabled");
	 		}
	 	}
	 });
	
	
	$("#ylLinkSuccess").click(ylPay.firstPayResultHandle);//首充弹出框“完成充值”按钮事件绑定
	/*var trrows = $("#tab_info tr").length;
	$("#tab_info tr:eq("+ (trrows - 2) +")").css("display","");
	$("#tab_info tr:eq("+ (trrows - 3) +")").css("display","");*/
	$("#tab_info tr:eq(5),#tab_info tr:eq(6)").show();
	//手机号码文本框事件处理
    $("#phone").focus(function(){$(".tip_default2").css("display","none");});
    $("#phone").blur(function(){
    	if($(this).val().length == 0)
    		$(".tip_default2").css("display","");
    });
};


/*
 * 显示易连支付说明:"了解语音支付"
 */
ylPay.showYiLianInstruction = function(index){
	 $("#ylVerifyAmount").html($("#amount").val());
	 //$("#ylVerifyCardNo").html(function(){
	 	//var cardNo = $("#mbbCardNo").val().replace(/\s*/g,'');
	 	//return cardNo.substr(cardNo.length - 4,cardNo.length);
	 //});
	
	 //$('.plusBankBg').show();//先显示全局遮盖层
     //$('#yilian_instruction'+index).slideDown();//再显示"了解语音支付"的弹出层;
	 
	 var attr = new Attention( popUp , '#yilian_instruction'+index ,  true );
	 attr.event();
};

/*
 * 隐藏易连支付说明:"了解语音支付"
 */
ylPay.hideYiLianInstruction = function(index){
	 $('.plusBankBg').hide();//先取消全局遮盖层
     $('#yilian_instruction'+index).slideUp();//再取消"了解语音支付"的弹出层;
};


/*
 * 获取易连短信验证码
 */
ylPay.getYLDyn = function(selectli){
	var cardNo;
	if ($("#choseBankFlg").val() == '1' && ($("#mbbCardNo").val() == null || $("#mbbCardNo").val() == '')) {
		cardNo = $(selectli).parent().parent().next().val();
	} else {
		cardNo = $("#mbbCardNo").val();
	}
	$.post("/pay/yilian/yiLianSendMsg.do",
			{
				mbbCardNo:cardNo,
				selectedBid:selectli.val(),
				selectMbbId: selectli.attr("alt"),
				factamount: $("#factamount").val(),
				phone:$("#phone").val(),
				rechargeCode:$("#rechargeCode").val()
			},
			function(data){
				if(data!=null && data['status'] == 1){
					$("#rechargeCode").val(data['rechargeCode']);//回填创建成功的充值申请单号
					//$("#sendDynMSG").html(""+("短信已发送")).show();
					console.dir("短信已发送");
				}else{
					$("#sendDynMSG").html("<i></i>"+data.msg).show();
				}
			},"json");
};


/*
 * 易连验卡，判断是否已经绑过当前卡了
 * mbbCardNo: 银行卡号
 * mbbId:你我贷绑卡记录的主键(存在你我贷绑卡了,易连未绑的情况)
 * 两个参数不同时传递！
 */
ylPay.verify = {
	status:false,
	query:function(mbbCardNo,mbbId){
			var bindCardFlag = true;//默认做绑过处理
			$.ajax({
		    	type: "post",
		   	 	url: "/pay/yilian/verifyQuery.do",//查询卡是否验证通过
		    	dataType: "json",
		    	data:{mbbCardNo:mbbCardNo,mbbId:mbbId},
		    	async: false,
		    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    	success: function(data){
			        		if(data!=undefined){
			        			bindCardFlag = data;
			        			ylPay.verify.status = data;
			        		}
			        	}
					});
			return bindCardFlag;
		}
};

/*
 * 易连认证前初始化页面元素
 */
ylPay.verifyInitPage =function(){
	//隐藏手机号和短信验证码元素
	/*var trrows = $("#tab_info tr").length;
	$("#tab_info tr:eq("+ (trrows - 2) +")").css("display","none");
	$("#tab_info tr:eq("+ (trrows - 3) +")").css("display","none");*/
	$("#tab_info tr:eq(5),#tab_info tr:eq(6)").hide();
};

/*
 * 易连首次充值
 */
ylPay.doFirstPay = function(mbbCardNo,selectedBid,factamount){
	$.ajax({
    	type: "post",
   	 	url: "/member/crushYLQuick1.html", // /pay/yilian/firstPay.do
   	 	dataType: "json", 
    	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    	data:$("#queryForm").serialize(),
    	async: false,
    	success: function(data){
    		if(data!=undefined){
				$("#rechargeCode").val(data);//回填创建成功的充值申请单号
    		}
    	}
	}); //ajax end!
};

/*
 * 易连二次充值
 * rechargeCode：下发短信时返回到页面上的充值申请单号
 * smsCode：用户收到的短信验证码
 */
ylPay.doSecondPay = function(rechargeCode,smsCode,buyFlag,factamount){
	if($("#validCode").val().length!=6){
		$("#sendDynMSG").html("<i></i>请输入正确的短信验证码").show();
		return;
	}
	
	$.ajax({
    	type: "post",
   	 	url: "/member/crushYLQuick2.html", // /pay/yilian/secondPay.do
    	dataType: "json",
    	data:{
    		rechargeCode:rechargeCode,
    		smsCode:smsCode,
    		buyFlag:buyFlag,
    		factamount:factamount
    		},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(data){
    		var errorFlag =false;
    		if(data==2){ //【1:处理中;2：成功;3：失败】
    			if($("#buyFlag").val()=="1"){
    				window.location.href="/member/waitForResult.do";
    				return;
    			}
				window.location.href="/pay/yilian/rechargeSucc.do?rechargeCode="+rechargeCode;//刷新当前页面
    		}else if(data==1){
    			ylPay.payCountdown.init();
    			ylPay.payCountdown.down();
    		}else{
    			errorFlag =true;
    		}
    		if(errorFlag){
        		$(".errorthird").css('display','inline-block');
				$(".errorTips").css('display','block');
        		$("#validCode").val("");//清空验证码
        		ylPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
        		window.clearInterval(confirmButStateSetTime); 
        		if(window.location.pathname=="/member/rechargeStepBuy.do"){
        			$("#confirmBut").val("确认购买").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
        		}else{
        			$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
        		}
        	}
    	}
	}); //ajax end!
};


/**
 * 易连短信倒计时
 */
ylPay.smsCountdown = {
	countDownWait:60,
	down:function time(o) {
        if (this.countDownWait == 0) {
        	o.bind('click',rechargeBase.btnSendDynClick);
        	o.removeClass('yzm-time').addClass("yzm-fous");
            o.removeAttr("disabled");         
            o.html("重新获取");
            window.clearInterval(confirmButStateSetTime);
    		if(window.location.pathname=="/member/rechargeStepBuy.do"){
    			$("#confirmBut").val("确认购买").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
    		}else{
    			$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
    		}
            this.countDownWait = 60;
        } else {
        	o.unbind("click"); //移除click
            o.attr("disabled","disabled");
            o.html(this.countDownWait + "秒后重发");
            this.countDownWait--;
            setTimeout(function() {
            	ylPay.smsCountdown.down(o);
            },
            1000);
        }
	},
	reset:function(){
		if(this.countDownWait>0 && this.countDownWait <60) 
			ylPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
	}
};


/**
 * 易连首次充值结果查询
 */
ylPay.firstPayResultHandle = function(){
	$.ajax({
    	type: "post",
   	 	url: "/pay/yilian/queryPayResult.do",
       	dataType: "json", 
       	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    	data:$("#queryForm").serialize(),
    	async: false,
    	success: function(data){
    		if(data!=null && data.status!=undefined && data.status == 1){
    			if($("#buyFlag").val()=="1"){
    				window.location.href="/member/waitForResult.do";
    				return;
    			}
    			window.location.href="/pay/yilian/rechargeSucc.do?rechargeCode="+$("#rechargeCode").val();
    		}else{
    			var msg = data.msg;
    			if(msg == '00A4'){
    				//var msgHtml = '资金到账稍有延迟，如长时间未到账请联系客服，<a href="/member/crushlog.html">查看详情</a>';
    				window.location.href="/member/fundsLog.html";
    			}else{
    				window.location.href="/pay/yilian/rechargeFail.do";
    			}
    		}
    	}
	}); //ajax end!
};

/**
 * 支付结果查询倒计时
 */
ylPay.payCountdown = {
	TIME_INTERVAL: 15,
	countDownWait:this.TIME_INTERVAL,
	down:function time() {
		console.dir(this.countDownWait);
        if (this.countDownWait % 3 == 0) {
        	console.dir("尝试去服务器拿一次结果....");
        	$.ajax({
            	type: "post",
           	 	url: "/pay/yilian/queryPayResult.do",
	           	dataType: "json", 
	           	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
            	data:$("#queryForm").serialize(),
            	async: false,
            	success: function(data){
            		if(data.status == 1){
            			if($("#buyFlag").val()=="1"){
            				window.location.href="/member/waitForResult.do";
            				return;
            			}
            			//console.dir("拿到结果了，成功跳转");
            			window.location.href="/pay/yilian/rechargeSucc.do?rechargeCode="+$("#rechargeCode").val();//刷新当前页面
            		}else if(ylPay.payCountdown.countDownWait == 0){
            			var msg = data.msg;
            			if(msg == '00A4'){
            				var msgHtml = '资金到账稍有延迟，如长时间未到账请联系客服，<a href="/member/crushlog.html">查看详情</a>';
            				$("#rechargeBtnMSG").html(msgHtml);
            				$(".errorTips").hide();
            			}else{
            				$("#rechargeBtnMSG").html(msg);
        					$(".errorTips").show();
            			}
            			$(".errorthirdTip").css("display","inline-block");
                		$("#validCode").val("");//清空验证码
                		ylPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
                		window.clearInterval(confirmButStateSetTime); //去掉“处理中...”的按钮状态
                		if(window.location.pathname=="/member/rechargeStepBuy.do"){
                			$("#confirmBut").val("确认购买").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
                		}else{
                			$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
                		}
            			//console.dir("没拿到结果了失败显示");
            		}else{
            			ylPay.payCountdown.countDownWait--;
                        setTimeout(function() {
                        	ylPay.payCountdown.down();
                        },
                        1000);
            		}
            	}
        	}); //ajax end!
        } else {
            this.countDownWait--;
            setTimeout(function() {
            	ylPay.payCountdown.down();
            },
            1000);
        }
	},
	init: function(){
		this.countDownWait = this.TIME_INTERVAL;
	}
};

ylPay.formCheck=function(){
	if($("#mbbCardNo").val().length>0 && !llPay.checkCardNo()){
		return false;
	}
	
	if(!rechargeBase.setEffectiveFee()){
		return false;
	}
	
	if($("#validCode").closest("tr").is(':visible') && $("#validCode").val().length!=6){
		$("#sendDynMSG").html("<i></i>请输入正确的短信验证码").show();
		return false;
	}
	return true;
};


//ylPay.init();