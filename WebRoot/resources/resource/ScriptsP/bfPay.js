var bfPay = bfPay || {};
bfPay.init = function(){
	bfPay.initialize();
};

bfPay.initialize = function(){
	//银行卡号失去焦点事件处理
//	 $("#mbbCardNo").blur(function(){
//	 	llPay.checkCardNo();//借助连连支付平台的接口去验卡格式
//	 });
	
//	$("#tab_info tr:eq(5),#tab_info tr:eq(6)").show();
	 
	$("#validateCodeTr").show();
	$("#phoneTr").show();
	//手机号码文本框事件处理
    $("#phone").focus(function(){$(".tip_default2").css("display","none");});
    $("#phone").blur(function(){
    	if($(this).val().length == 0)
    		$(".tip_default2").css("display","");
    }); 
    
    $("#phone").val("");
};


/*
 * 获取宝付短信验证码
 */
bfPay.getBfDyn = function(selectli){
	/** added by Chen.Guiyang 20160106 充值渠道路由规则优化 start */
	var cardNo;
	if ($("#choseBankFlg").val() == '1' && ($("#mbbCardNo").val() == null || $("#mbbCardNo").val() == '')) {
		cardNo = $(selectli).parent().parent().next().val();
	} else {
		cardNo = $("#mbbCardNo").val();
	}
	/** added by Chen.Guiyang 20160106 充值渠道路由规则优化 end */
	
	//取得mbbid
    var bankMbbId = $(".curronli").find("input[name='bankRadio']")[0].alt;
	
	$.post("/pay/baofu/getDyn.do",
			{
				mbbCardNo:cardNo,
				selectedBid:selectli.val(),
				selectMbbId: selectli.attr("alt"),
				factamount: $("#factamount").val(),
				phone:$("#phone").val(),
				mbbId:bankMbbId,
				rechargeCode:$("#rechargeCode").val()
			},
			function(data){
				if(data!=null && data['flag'] == 'success'){
					$("#rechargeCode").val(data['rechargeCode']);//回填创建成功的充值申请单号					
				}else{
					if(data['msg']!=undefined){
						$("#sendDynMSG").html("<i></i>"+(data['msg'])).show();
					}else{
						$("#sendDynMSG").html("<i></i>"+("短信发送失败,请稍后重试")).show();
					}
				}
			},"json");
};

bfPay.dobfPay =  function(){
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
        			window.location.href="/pay/baofu/rechargeSucc.do";//刷新当前页面
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
        		bfPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
        		window.clearInterval(confirmButStateSetTime); 
        		if(window.location.pathname=="/member/rechargeStepBuy.do"){
        			$("#confirmBut").val("确认购买").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
        		}else{
        			$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
        		}
        	}
		});
};

/**
 * 短信倒计时
 */
bfPay.smsCountdown = {
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
            	bfPay.smsCountdown.down(o);
            },
            1000);
        }
	},
	reset:function(){
		if(this.countDownWait>0 && this.countDownWait <60) 
			bfPay.smsCountdown.countDownWait = 0;//取消下发验证码的倒计时
	}
};

/*
 * 如果渠道是来自宝付，那么要调宝付的PCI查询接口，
 * 查询宝付那里绑定的卡和我方是否一致，同时取到该卡所对应的银行预留手机号
 */
bfPay.setPhoneBybf=function(mbbId){
	$.post("/pay/baofu/bfQuery.do",
			{mbbId: mbbId},
			function(data){
				if(data!=null && data != ""){
					$("#phone").val(data).attr("disabled",true);
				}else{
					$("#phone").val('').attr("disabled",false);//取不到手机号，那么应该可以让用户输入手机号！这样保险点！
				}
			},"json");
	
}	

bfPay.formCheck=function(){
	//mbbCardNo有值代表输入的新卡，已绑卡无需校验
	if($("#mbbCardNo").val().length>0){
		if(!llPay.checkCardNo()){
			return false;
		}
		
	}else{
		//未绑卡用户或者已绑卡用户选择新的卡，防止未输入直接请求
		if ($("#choseBankFlg").val() == '0' || $("#addBank-btn").hasClass("selectli")) {
			$("#mbbCardNoMSG").html("<i></i>请输入银行卡号！");
			$('#mbbCardNoMSG').hide();
			$('#mbbCardNoMSG').fadeIn("slow");
			return false;
		}
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
