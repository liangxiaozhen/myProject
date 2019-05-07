var kqPay = kqPay || {};
kqPay.init = function(){
	kqPay.initialize();
};

kqPay.initialize = function(){
	
	//银行卡号失去焦点事件处理
	 $("#mbbCardNo").blur(function(){
	 	llPay.checkCardNo();//借助连连支付平台的接口去验卡格式
	 });
	
	
	$('form').on('submit', function(){
		//e.preventDefault();
	});
	
	/*var trrows = $("#tab_info tr").length;
	$("#tab_info tr:eq("+ (trrows - 2) +")").css("display","");
	$("#tab_info tr:eq("+ (trrows - 3) +")").css("display","");*/
	//$("#tab_info tr:eq(5),#tab_info tr:eq(6)").show();
	
	$("#validateCodeTr").show();
	$("#phoneTr").show();
	
	//手机号码文本框事件处理
    $("#phone").focus(function(){$(".tip_default2").css("display","none");});
    $("#phone").blur(function(){
    	if($(this).val().length == 0)
    		$(".tip_default2").css("display","");
    });   
};

/*
 * 如果渠道是来自快钱，那么要调快钱的PCI查询接口，
 * 查询快钱那里绑定的卡和我方是否一致，同时取到该卡所对应的银行预留手机号
 */
kqPay.setPhoneByKuaiQianPCI = function(mbbId){
	$.post("/pay/kuaiqian/pciQuery.do",
			{mbbId: mbbId},
			function(data){
				if(data!=null && data.length==11){
					$("#phone").val(data).attr("disabled","disabled");
				}else{
					$("#phone").val('').attr("disabled",false);//取不到手机号，那么应该可以让用户输入手机号！这样保险点！
				}
			},"json");
};  


/*
 * 通过快钱的接口查询卡信息是否支持
 */
kqPay.checkKQcardinfo = function(){
	$.ajax({  
          type : "post",  
          dataType: "json",
          url : "/pay/kuaiqian/checkKQcardinfo.do",  
          data : {mbbCardNo: $("#mbbCardNo").val()},  
          async : false,  
          success : function(data){  
            $.cardinfoCheckFlag = data;
          }  
     });
	return $.cardinfoCheckFlag;
};	


/*
 * 获取快钱短信验证码
 */
kqPay.getKQDyn = function(selectli){
	/** added by Chen.Guiyang 20160106 充值渠道路由规则优化 start */
	var cardNo;
	if ($("#choseBankFlg").val() == '1' && ($("#mbbCardNo").val() == null || $("#mbbCardNo").val() == '')) {
		cardNo = $(selectli).parent().parent().next().val();
	} else {
		cardNo = $("#mbbCardNo").val();
	}
	/** added by Chen.Guiyang 20160106 充值渠道路由规则优化 end */
	$.post("/pay/kuaiqian/getDyn.do",
			{
				mbbCardNo:cardNo,
				selectedBid:selectli.val(),
				selectMbbId: selectli.attr("alt"),
				factamount: $("#factamount").val(),
				phone:$("#phone").val(),
				rechargeCode:$("#rechargeCode").val()
			},
			function(data){
				if(data!=null && data['flag'] == 'success'){
					$("#rechargeCode").val(data['rechargeCode']);//回填创建成功的充值申请单号
					//$("#sendDynMSG").html(""+("短信已发送")).show();
				}else{
					if(data['msg']!=undefined){
						$("#sendDynMSG").html("<i></i>"+(data['msg'])).show();
					}else{
						$("#sendDynMSG").html("<i></i>"+("短信发送失败,请稍后重试")).show();
					}
				}
			},"json");
};



kqPay.doKQPay =  function(){
	if($("#validCode").val().length!=6){
		$("#sendDynMSG").html("<i></i>请输入正确的短信验证码").show();
		return;
	}
	
	$('form').ajaxSubmit({
        	success:function(json){
        		data = $.parseJSON(json);
        		if(data.status==2){ //【1:处理中;2：成功;3：失败】
        			if($("#buyFlag").val()=="1"){
        				window.location.href="/member/waitForResult.do";
        				return;
        			}
        			window.location.href="/pay/kuaiqian/rechargeSucc.do";//刷新当前页面
        			return;
        		}else if(data.status==1){
        			var msgHtml = '资金到账稍有延迟，如长时间未到账请联系客服，<a href="/member/crushlog.html">查看详情</a>';
    				$("#rechargeBtnMSG").html(msgHtml);
    				$(".errorTips").hide();
        		}else if(data.status==3){
        			$("#rechargeBtnMSG").html(data.msg);
					$(".errorTips").show();
        		}
        		$(".errorthirdTip").css("display","inline-block");
				$(".errorTips").css('display','block');
        		$("#validCode").val("");//清空验证码
        		kqPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
        		window.clearInterval(confirmButStateSetTime); 
        		if(window.location.pathname=="/member/rechargeStepBuy.do"){
        			$("#confirmBut").val("确认购买").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
        		}else{
        			$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
        		}
        	}
		});//$('form').ajaxSubmit() end ! 
};

/**
 * 短信倒计时
 */
kqPay.smsCountdown = {
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
            	kqPay.smsCountdown.down(o);
            },
            1000);
        }
	},
	reset:function(){
		if(this.countDownWait>0 && this.countDownWait <60) 
			kqPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
	}
};


/**
 * 支付结果查询倒计时
 * [本函数作废！快钱无需倒计时等待，因为就算是失败处理中，快钱也会立马返回一个结果！]
 */
kqPay.payCountdown = {
	countDownWait:10,
	down:function time() {
		console.dir(this.countDownWait);
        if (this.countDownWait == 0) {
        	//console.dir("15秒已到，去服务器拿结果，成功就跳转，失败就给失败结果");
        	$.ajax({
            	type: "post",
           	 	url: "/pay/kuaiqian/queryPayResult.do",
	           	dataType: "json", 
	           	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
            	data:{
            		rechargeCode:$("#rechargeCode").val()
            		},
            	async: false,
            	success: function(data){
            		if(data.status == 1){
            			//console.dir("拿到结果了，成功跳转");
            			if($("#buyFlag").val()=="1"){
            				window.location.href="/member/waitForResult.do";
            				return;
            			}
            			window.location.href="/pay/kuaiqian/rechargeSucc.do";//刷新当前页面
            		}else{
            			var msg = data.msg;
            			$("#rechargeBtnMSG").html(msg);
    					$(".errorTips").show();
            			$(".errorthirdTip").css("display","inline-block");
                		$("#validCode").val("");//清空验证码
                		kqPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
                		window.clearInterval(confirmButStateSetTime); //去掉“处理中...”的按钮状态
                		$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
            			//console.dir("没拿到结果了失败显示");
            		}
            	}
        	}); //ajax end!
        } else {
            this.countDownWait--;
            setTimeout(function() {
            	kqPay.payCountdown.down();
            },
            1000);
        }
	},
	init: function(){
		this.countDownWait = 10;
	}
};

kqPay.formCheck=function(){
	if($("#mbbCardNo").val().length>0 && !llPay.checkCardNo()){
		return false;
	}
	
	if(!rechargeBase.setEffectiveFee()){
		return false;
	}
	
	if($("#validCode").val().length!=6){
		$("#sendDynMSG").html("<i></i>请输入正确的短信验证码").show();
		return false;
	}
	
	return true;
};

//kqPay.init();