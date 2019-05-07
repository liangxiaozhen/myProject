var CONST_PAYCHANNELS={
	SINA_WEIBOPAY:{       K: '-6',      	V: '/member/crush.html'} , //新浪微博钱包
	TENPAY_WEB:{          K:  '6',          V: '/member/crushTen.html'},  //财付通[网银]
	YINTONG_AUTH_PAY:{    K:  '9',    		V: '/member/crushYintong.html'},  //连连认证
	YINTONG_QUICK_PAY:{   K: '10', 			V: '/member/crushYintongQuick.html'},  //连连快捷
	YINTONG_WEB_PAY:{     K: '11',    		V: '/member/crushYintongWeb.html'},  //连连网银
	KUAIQIAN_QUICK_PAY:{  K: '12',			V: '/member/crushKQquick.html'}, //快钱快捷
	KFT_QUICK_PAY:{  K: '23',			V: '/member/crushKftquick.html'}, //快付通快捷
	KUAIQIAN__WEB_PAY:{   K: '13',   		V: '/member/crushKQweb.html'}, //快钱网银
	YILIAN_QUICK_PAY:{    K: '15',   		V: '/member/crushYLQuick.html'}, //易连快捷
	TENPAY_PLATFORM:{     K: 'CFT',  		V: '/member/crushTen.html'}, //财付通平台
	WEIBO_PLATFORM:{      K: 'WBQB',		V: '/member/crush.html'}, //微博钱包平台
	BAOFU_QUICK_PAY:{     K: '28',			V: '/member/crushBfquick.html'}, //宝付快捷
	CHINABANK_PLATFORM:{  K: 'WYZX',        V: '/member/crushChinabank.html'}//网银在线平台
};

var rechargeBase = rechargeBase || {};
rechargeBase.init = function(){
	rechargeBase.initialize();
};

rechargeBase.initialize = function(){
	//设置左边导航“我要充值”加粗
    $('#my_nwd_3').addClass('active');
    
    //手机认证
    this.phoneCertification();
    
    if($("#myquickbank li").length>1){//大于1是因为还有一个“选择其他银行”的<li>标签
		//用户有已绑定银行卡要默认勾选第一张银行卡
    	$("#myquickbank li:eq(0)").trigger("click");
	}
	
	//记住用户刷新页面前选择的选项卡[快捷支付/网银支付/支付平台]
    //$('.module .tab_u span:eq('+$.cookie("selRechargeTabIndex")+')').trigger("click").trigger("mousedown");

	//充值弹框遇到问题关闭刷新页面
	$(".refreshPage").click(function(){
	    location.reload(true);
	});
	
	//动态验证码事件绑定
	$("#btnSendDyn").click(this.btnSendDynClick);
	
	//Tab切换
	$('.selecttab').click(function(){
		//隐藏前一次提示的错误信息
		$(".errorthird").hide();
		$(".errorTips").hide();
					
		$("#amountMSG").html("");
		$("#amountMSG").css("display","none");
		
		$("#bank_MSG").html("");
		$("#bank_MSG").css("display","none");
		
		$("#bank_MSG_0").html("");
		$("#bank_MSG_0").css("display","none");
		
		$("#bank_MSG_1").html("");
		$("#bank_MSG_1").css("display","none");
		
		$("#bank_MSG_2").html("");
		$("#bank_MSG_2").css("display","none");
		
		//var trrows = $("#tab_info tr").length;
		
		/*
		 * $("#tab_info")，表格一共有 8 行
		 * 0：真实姓名
		 * 1：证件号码
		 * 2：充值金额
		 * 3：手续费[始终隐藏]
		 * 4：实际支付金额[始终隐藏]
		 * 5：手机号码
		 * 6：验证码
		 * 7：确认充值按钮
		 * ==============后续如果该表格tr内容发生变更需要留意==================
		 */
		
		
		if($(this).index()>0){
			//网银和平台支付不需要显示手机号、验证码文本框
			//$("#tab_info tr:eq("+ (trrows - 2) +")").css("display","none");
			//$("#tab_info tr:eq("+ (trrows - 3) +")").css("display","none");
			$("#tab_info tr:eq(5),#tab_info tr:eq(6)").hide();
			//如果网银和平台支付已经实名认证了，也不需要显示实名认证信息了
			if($("#realNameIdenTag").val()!='iden'){
				$("#tab_info tr:eq(0),#tab_info tr:eq(1)").hide();
			}
			
			
			if($(this).index()==1){
				$(".sixteen img").attr("src",$("#recentBankImg").val());//为了解决一个BUG!
				 //如果是新用户首次充值，在网银充值界面，默认展现出全部的银行供用户选择
			    if($("#bankRadioDef").val().length==0){
			    	$(".fc_blue.select").trigger("click");
			    }
			}
		}else{
			$("#tab_info tr:eq(0),#tab_info tr:eq(1)").show();//显示实名认证信息
			if($("#myquickbank li").length>1){//大于1是因为还有一个“选择其他银行”的<li>标签
				//用户有已绑定银行卡要默认勾选第一张银行卡
		    	//$("#myquickbank li:eq(0)").trigger("click");
			}
		}
		$.cookie("selRechargeTabIndex",$(this).index());//0 1 2 ;记住用户选择的选项卡，这样刷新页面后就不用重复点了
	});
};//rechargeBase.initialize end!


rechargeBase.checkBlackUser = function(){
	//zhuzy TASK#2379黑名单
	var isBlackUser = $("#blackUser").val();
	if(isBlackUser=="1"){
		var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，您已不能充值。</p>";
		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
		$("#tipsContent").html(msg);
		$("#btnOk").unbind('click');
		$("#btnOk").click(function() {
			// 关闭提示信息弹出框
			//window.location.reload();
			rechargeBase.closeTips();
			return;
		});
		rechargeBase.showTips();
		return;
	}
};

rechargeBase.showTips = function(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('#tips');
	plusBankBg.show();
	showForm1.slideDown();
	if($('.inputFocus1').size()>0){
		$('.inputFocus1').focus();
	}
};

rechargeBase.closeTips = function(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('#tips');
	plusBankBg.hide();
	showForm1.slideUp();
};


/*
 * 新的实名认证函数
 */
rechargeBase.checkCertIdentity = function(){
	if($("#xiangdao").val() ==1 && $("#realNameIdenTag").val()=='iden') {
		centIdentitySave();
		if(certIdentityResultFlag == true){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
};


/*
 * 手机认证
 */
rechargeBase.phoneCertification = function (){
	var flag = $("#flag").val();
    if(flag == 'phone'){//手机认证
    	$("#plusBankContainer1").css("display","none");
		var html = '<div class="plusBankBg"></div>';
		html += '<div class="plusBank1 mini">';
		html += '<div class="topper clearfix">';
		html += '<span class="fl fs_18"></span>';
		html += '<a class="fr" onClick="closePhone()"></a>';
		html += '</div>';
		html += '<div class="middle">';
		html += '<div class="content">';
		html += '<i class="ico_all size24 img_false24"></i>';
		html += '为保障您的账户安全，充值前需要手机认证！';
		html += '</div>';
		html += '<div class="btnbox"><input id="ljrz_09171611" type="submit" class="btn btnSize_1 btn_blue" value="立即认证" /></div>';
		html += '</div>';
		html += '</div>';
		$("#plusBankContainer3").html(html);
		$("#ljrz_09171611").click(function(){
			location.href="/member/safetyLevel.do?doWhat=sl";
		});
		showCon_1();
		$("#tips").css("display","none");//zhuzy 黑名单-由于弹出框样式都一样，防止弹出其他弹出框时也会弹出黑名单提示框
    }
};

//关闭手机认证弹框
rechargeBase.closePhone = function(){
	location.href="/member/safetyLevel.do?doWhat=sl";
};



//弹出框选银行XX和取消
rechargeBase.cancel = function(){
	$('.new-shortcut-allbank li.curronli').removeClass("curronli");//清除弹出层里面用户选择的银行[恢复默认]
	//"选择其他银行"的弹出层恢复默认状态
	$(".unknows").show();
	$(".inputbank").hide();
	$(".allbank").show();
	//恢复“更多银行”的文字
	$('.citySelect dl dt').html('<i class="fr mar_t10 icon_base icon_base_selectdown mar_r10 mar_l5"></i><span class="sp pad_l10 fc_blue"><b>更多银行</b></span>');
	closeAll_5();
};


/*
 * 计算实际充值金额
 * [某些情况下会收手续费等情况]
 */
rechargeBase.setEffectiveFee = function(){
	var resultFlag = false;
	var amount = $("#amount").val();
	if(undefined==amount){
		return true;
	}
	amount=amount.replace(/\D/g, '');
    $("#amount").val(amount);
    if(amount==""||isNaN(amount)){
        $("#amountMSG").html("<i></i>请输入充值金额");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return false;
    }
    if(!amount.match(/^[1-9]\d{0,10}$/)){
		$("#amountMSG").html("<i></i>充值金额必须为整数！且充值金额不大于11位数");
		$('#amountMSG').hide();
		$('#amountMSG').fadeIn("slow");
		return false;
	}
    amount=parseInt(amount);
    if(amount<1){
		$("#amountMSG").html("<i></i>单笔充值金额至少1元！");
		$('#amountMSG').hide();
		$('#amountMSG').fadeIn("slow");
		return false;
	}
    
		$('#amountMSG').css("display","none");
		
		var SXFRate=$("#SXFRate").val();
		//计算充值手续费
		if(SXFRate){
			SXFRate==parseFloat(SXFRate);
			var poundage=(amount*SXFRate).toFixed(2);
			var factamount=parseFloat(amount)+parseFloat(amount*SXFRate);
			$('#factamountView').html(factamount.toFixed(2));
			$('#factamount').val(factamount.toFixed(2));
			if(SXFRate==0){
				//是否有充值特权
				var isPrivilege = $('#isPrivilege').val();
				if(isPrivilege == "1"){
					$('#serviceFee1').html("<font>免费(充值特权)</font>");
				}else{
					$('#serviceFee1').html("<font>免费</font>");
				}
				$('#serviceFee1Other').html("");
                $('#helpSpan').hide();
			}else{
				$('#serviceFee1').html(poundage);
				$('#serviceFee1Other').html("元");
	            $('#helpSpan').show();
	            $(".serviceFeeTR").show();//存在手续费就要显示出来，否则不用显示
			}
		}
		resultFlag = true;
		/*$.ajax({
	        	type: "post",
	       	 	url: "/member/rechargePoundage.do",
	        	dataType: "json",
	        	data:{
	        		amount:amount
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(data){
	        		if(data['state'] == '0'){
	        			$('#amountMSG').css("display","");
						$("#amountMSG").html("<i></i>充值金额不大于11位数");
	        		}else if(data['state'] == '1'){
	        			$('#factamountView').html(""+data['SJCZ']+"");
	        			$('#factamount').val(""+data['SJCZ']+"");
	        			if(""+data['SXF']+""=="0"||""+data['SXF']+""=="0.0"||""+data['SXF']+""=="0.00"){
	        				//是否有充值特权
	        				var isPrivilege = $('#isPrivilege').val();
	        				if(isPrivilege == "1"){
	        					$('#serviceFee1').html("<font size='3'>免费(充值特权)</font>");
	        				}else{
	        					$('#serviceFee1').html("<font size='3'>免费</font>");
	        				}
	        				$('#serviceFee1Other').html("");
                            $('#helpSpan').hide();
	        			}else{
	        				$('#serviceFee1').html(""+data['SXF']+"");
	        				$('#serviceFee1Other').html("元");
                            $('#helpSpan').show();
                            $(".serviceFeeTR").show();//存在手续费就要显示出来，否则不用显示
	        			}
	        		}
	        		resultFlag = true;
	        	}
	      }); */
	return resultFlag;
};//rechargeBase.setEffectiveFee end!

/*
 * 快钱、易连短信验证码下发事件处理
 */
rechargeBase.btnSendDynClick = function(){
	//隐藏前一次所有的错误提示
	$(".errorthirdTip").hide();
	$(".errorTips").hide();
	$("#sendDynMSG").hide();
	
	//判断元素是否完整
	if(!sendDynCheck()){
		return;//卡或者手机需要有值
	}
	
	//检查是否实名认证
	if(!rechargeBase.checkCertIdentity()){
		console.dir("实名未通过,请先进行实名认证！");
		$("#identityMSG").html("实名未通过,请先进行实名认证！").show();
		return;//实名未通过，直接结束流程
	}
	
	var selectli = $(".curronli").find("input[name='bankRadio']");
	var channelId = selectli.attr("title");
	if(rechargeBase.setEffectiveFee()){
		$("#btnSendDyn").removeClass('yzm-fous').addClass("yzm-time");
		if(channelId == CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K){
			kqPay.smsCountdown.down($("#btnSendDyn"));
			kqPay.getKQDyn(selectli);
		}else if(channelId == CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K){
			ylPay.smsCountdown.down($("#btnSendDyn"));
			ylPay.getYLDyn(selectli);
		}else if(channelId == CONST_PAYCHANNELS.KFT_QUICK_PAY.K){
			kftPay.smsCountdown.down($("#btnSendDyn"));
			kftPay.getKFTDyn(selectli);
		}else if(channelId == CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K){
			bfPay.smsCountdown.down($("#btnSendDyn"));
			bfPay.getBfDyn(selectli);
		}
	}
};


rechargeBase.confirmBut = {
	reset:function(){
		window.clearInterval(confirmButStateSetTime); 
		if(window.location.pathname=="/member/rechargeStepBuy.do"){
			$("#confirmBut").val("确认购买").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
		}else{
			$("#confirmBut").val("确认充值").removeClass("btn_gray1").addClass("btn_orange").removeAttr("disabled");
		}
	}
};

// added by Chen.Guiyang 20151217 充值渠道路由规则优化 start
rechargeBase.rechargeRouteChange = function(amount, element){
	// 针对快捷支付且非新手产品(财神道、万元户、理财宝)
	if ($("#type").val() == '0' && $("#versionNo").val() == '2'
			|| $("#type").val() == '3' || $("#type").val() == '4') {
		return;
	}
	// 这里顺序很重要，先调用rechargeBase.setEffectiveFee()，再判断是否是快捷支付，否则factamount没有被赋值，充值失败。
	if (rechargeBase.setEffectiveFee() && $(".ui-select-listBox-list--now").index()=='0') {
		var cardNo;
		if ($("#choseBankFlg").val() == '1' && ($("#mbbCardNo").val() == null || $("#mbbCardNo").val() == '')) {
			cardNo = $(element).parent().next().val();
		} else {
			cardNo = $("#mbbCardNo").val();
		}
		
		if(cardNo == ""){			
			return;
		}
		
		$.ajax({
	    	type: "post",
	    	async: false,
	   	 	url: "/pay/yintong/autoChangeRechargeChannel.do",
	    	data:{
	    		// 银行卡号 如果老用户未换银行卡号则取已经绑定的银行卡号，否则无论是老用户换了银行卡号或是新用户都取输入的银行卡号
	    		mbbCardNo : cardNo,
	    		bankNum : $(".curronli").find("input[class='bankNum']").length > 0 ? $(".curronli").find("input[class='bankNum']").val() : $("#bankNum0").val(),
				amount : amount,// 实际充值金额
				type : 0// 设为0:快捷支付
			},
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(res){
				if(res==null || res=="null"){
					$("#amountMSG").html("<i></i>充值渠道路由出错！");
					$('#amountMSG').hide();
					$('#amountMSG').fadeIn("slow");
				}else{
		    		if (res['status']==1) {
		    			if (res['bid'] != undefined) {
		    				$(element).find('input[name="bankRadio"]').attr("id",res['bid']+"bankName");
		    				$(element).find('input[name="bankRadio"]').attr("value",res['bid']);
		    			}
		    			if (res['channelid'] != undefined) {
		    				$(element).find('input[name="bankRadio"]').attr("title",res['channelid']);
		    				
	    					// 如果先前已经有了属性alt="${bindbank.mbb_id}",则删除。
	    					$(element).find('input[name="bankRadio"]').removeAttr("alt");
		    				// 当路由的新渠道和绑卡成功的渠道相同时，元素加上alt="${bindbank.mbb_id}"属性，以正确调用pciQuery.do方法。
//		    				var lastChannel = $(element).parent().nextAll('input[name="hiddenChannelId"]').eq(0).val();
//		    				if (lastChannel!= undefined && lastChannel.indexOf(res['channelid']) != -1) {
//		    					var mbbId = $(element).parent().nextAll('input[name="hiddenMbbId"]').eq(0).val();
//		    					$(element).find('input[name="bankRadio"]').attr("alt",mbbId);
//		    				}
		    				
		    				var mbbId= $(element).parent().nextAll('input[name="hiddenMbbId"]').eq(0).val();
	    					$(element).find('input[name="bankRadio"]').attr("alt",mbbId);
		    			}
		    			
	    				//var cardBindFlg = ($("#mbbCardNo").val() == null || $("#mbbCardNo").val() == '') ? '0' : '1';
		    			var cardBindFlg = $("#choseBankFlg").val();
	    				// 绑定过的银行卡
	    				if(cardBindFlg == '1'){
	    					selectBankDef($('.new-shortcut ul li.curronli')[0], 1);
	    				// 未绑定过的银行卡
	    				}else{
	    					selectBankDef($('#banktrNewUser li.curronli')[0], 2);
	    				}
		    		} else if (res['status']==2) {
						$("#amountMSG").html("<i></i>"+res['msg']);
						$('#amountMSG').hide();
						$('#amountMSG').fadeIn("slow");
		    		}
				}
	    	}   
		});
	}
}

rechargeBase.rechargeRouteChangeForWY = function(bankid,amount, element){
	$.ajax({
		type: "post",
		async: false,
		url: "/pay/yintong/autoChangeRechargeChannel.do",
		data:{
			bankid : bankid,
			amount : amount,// 实际充值金额
			type : 1// 设为1:网银支付
		},
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success: function(res){
			if(res==null || res=="null"){
				$("#amountMSG").html("<i></i>充值渠道路由出错！");
				$('#amountMSG').hide();
				$('#amountMSG').fadeIn("slow");
			}else{
				if (res['status']==1) {
					$(element).attr('title',res['channelid']);
				}
			}
		}
	});
}
// added by Chen.Guiyang 20151217 充值渠道路由规则优化 end

//表单验证：获取验证码时
function sendDynCheck(){
	//未绑卡用户或者已绑卡用户选择新的卡，防止未输入直接请求
	if ($("#choseBankFlg").val() == '0' || $("#addBank-btn").hasClass("selectli")) {
		if($('#mbbCardNo').val() == ""){
			$("#mbbCardNoMSG").html("<i></i>请输入银行卡号！");
			$('#mbbCardNoMSG').hide();
			$('#mbbCardNoMSG').fadeIn("slow");
			return false;
		}
		
		if($("#phone").val().length != 11){
			$("#sendDynMSG").html("<i></i>请输入正确的手机号码").show();
			return false;
		}else{
			var phoneRe= /^((13[0-9])|(14[5|7])|(15[0|1|2|3|5|6|7|8|9])|(17[3|6|7|8])|18[0-9])\d{8}|(170\d{8})$/;
			if(!phoneRe.test($("#phone").val())){
				$("#sendDynMSG").html("<i></i>请输入正确的手机号码").show();
				return false;
			}			
		}
    }
			
	return true;
}
