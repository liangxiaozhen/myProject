var llPay = llPay || {};
llPay.init = function(){
	llPay.initialize();
};

llPay.initialize = function(){
	//银行卡号失去焦点事件处理
	 $("#mbbCardNo").blur(function(){
	 	llPay.checkCardNo();//借助连连支付平台的接口去验卡格式
	 });
	
	
	//隐藏手机号和短信验证码元素
//	 $("#tab_info tr:eq(5),#tab_info tr:eq(6)").hide();
	$("#validateCodeTr").hide();
	$("#phoneTr").hide();
	/*var trrows = $("#tab_info tr").length;
	$("#tab_info tr:eq("+ (trrows - 2) +")").css("display","none");
	$("#tab_info tr:eq("+ (trrows - 3) +")").css("display","none");*/
};


/*
 * 根据银行卡号取得银行信息
 * 点击【选择其他银行】-->【通过卡号选择银行】，输入卡号以后执行此验证，
 * 获取卡号对应的银行，如果能查到，且将结果带回充值第一个页面
 * 必须同步执行！否则后续的操作可能无法取到接口的返回数据
 */
llPay.getBankNo = function(){
	var staticCss = $("#staticCss").val();
	var cardNum = $("#i_bank").val();
	cardNum=cardNum.replace(/[,]/g,"");
	cardNum=cardNum.replace(/\s/ig,"");
	var flag=false;
	$.ajax({
		type : "POST", 
		async:false,//very important!
		url : "/pay/yintong/getBankName.do", 
		data : {
			banCardNum:cardNum,
			auth:$("#auth").val()
		}, 
		success : function(result) {
			if(result==null || result=="null"){
				if(!cardNum){
					$(".showBankName").html("暂不支持此卡");
					$("#choseBankNoMSG").css("display","");
					$("#choseBankNoMSG").html("银行卡号错误");
				}else{
					$(".showBankName").html("储蓄卡");
					$("#choseBankNoMSG").css("display","");
					$("#choseBankNoMSG").html("银行卡号错误");
				}
			}else{
				$(".showBankName").html("储蓄卡");
				//$("#showBankName").html(result.result[0].bankName);//在文本框尾部显示查询到的银行名称
				$("#choseBankMSG").html("");
				$('#choseBankMSG').css("display","none");
				$("#choseBankNoMSG").html("");
				$('#choseBankNoMSG').css("display","none");
				
				$("#areaBank").html("");
				var li = '<li onclick="moreBankPage.selectBank('+result[0].bankid+')" id="'+result[0].bankid+'areaBank"><a href="javascript:;"><img src="'+staticCss+'/'+result[0].picurl+'"></a><em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em><span><input type="hidden" name="bankRadio" id="'+result[0].bankid+'bankName" value="'+result[0].bankid+'"/><div style="display:none">'+result[0].memo+'</div></span></li>';
	            
	            $("#areaBank").append(li);
	            moreBankPage.selectBank(result[0].bankid);
	            flag= true;
			}
		}
	}); 
	return flag;
};

/*
 * 卡号验证
 * 充值主页面输入银行卡号时、在弹出页面选择了【通过卡号选择银行】录入卡号时
 * 均执行此验证
 * 
 */
llPay.checkCardNo =function(){
	var staticCss = $("#staticCss").val();
	var flg= false;
	var mbbCardNo = $('#mbbCardNo').val();
	if(mbbCardNo==""){
		$("#mbbCardNoMSG").html("<i></i>请输入银行卡号！");
		$('#mbbCardNoMSG').hide();
		$('#mbbCardNoMSG').fadeIn("slow");
		return flg;
	}
	mbbCardNo=mbbCardNo.replace(/[,]/g,"");
	mbbCardNo=mbbCardNo.replace(/\s/ig,"");

	/*
	 * 判断当前用户选择的银行渠道是否为快钱，因为以下代码是针对连连渠道的
	 * 如果是渠道是快钱的银行，需走其他验证卡格式的方法，该方法待定
	 * 
	 */

	$.ajax({
	type : "POST", 
	url : "/pay/yintong/getBankName.do", 
	dataType: "json",
	data : {
		banCardNum:mbbCardNo,
		auth:$("#auth").val()
		}, 
	async: false,
	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	success : function(result) {
			if(result==null || result=="null"){
				if(mbbCardNo != ""){
					$("#mbbCardNoMSG").html("<i></i>银行卡格式有误！");
					$('#mbbCardNoMSG').hide();
					$('#mbbCardNoMSG').fadeIn("slow");
					flg = false;
				}else{
					$("#mbbCardNoMSG").html("<i></i>请输入银行卡号！");
					$('#mbbCardNoMSG').hide();
					$('#mbbCardNoMSG').fadeIn("slow");
					flg = false;
				}
			}else{
//				if(result['status'] == "success"){
					var rtnMsg = result;
					var bid = rtnMsg[0]['bankid'];
					var channel = rtnMsg[0]['channelid'];
					var bankLogo = rtnMsg[0]['picurl'];
					var bankRemark = rtnMsg[0]['memo'];
					$("#mbbCardNoMSG").html("");
					$('#mbbCardNoMSG').css("display","none");
					//如果用户选择的银行卡与用户输入的银行卡号不一致，自动修改用户选择的银行卡图片选择项						
					var hiddenBid=$('.curronli').find("input[type='hidden']").val();
					if(hiddenBid != bid){
						
						/** 显示输入卡号相关的 图片及value 等  lingjs **/
						$("#selectedBank img").attr("src",staticCss+'/' +bankLogo);
						$("#selectedBank input").attr("id",bid+"bankName");
						$("#selectedBank input").attr("value",bid);
						$("#selectedBank .showBankidSpan").text("");						
						$(".banktrOldUser li.curronli").removeClass("curronli");
						$(".banktrOldUser ul li").each(function(){
							var dp = $(this).css("display"); 
							if(dp && dp=='none'){
								$(this).remove();
							}
						});
						
						/** 显示输入卡号相关的 图片及value 等  lingjs **/
						
						
						var new_li_HTML = '<a href="#"><img src="'+staticCss+'/'+bankLogo+'"></a><span><input type="hidden" name="bankRadio" id="'+bid+'bankName" value="'+bid+'"/></span>';
						if ($("#type").val() == '0' && $("#versionNo").val() == '2'
							|| $("#type").val() == '3' || $("#type").val() == '4') {
							new_li_HTML = '<a href="#"><img src="'+staticCss+'/'+bankLogo+'"></a><span><input type="hidden" name="bankRadio" id="'+bid+'bankName" value="'+bid+'" title="'+9+'"/></span>';
						}
						
						$(".banktrOldUser ul").append("<li class='curronli' style='display:none;'>"+new_li_HTML+"</li>");//加一个隐藏的选中 li
						
						$('.new-shortcut-new ul li.curronli').html(new_li_HTML);
						var rechargeLimit = $('#bankRemark').children();
						$('#bankRemark').text("支付限额："+bankRemark);
						$('#bankRemark').append(rechargeLimit);
					}
										
			    	// 针对充值购买页面，选定银行后，就结合还需支付的金额，路由充值渠道。
					if ($("#amount").val() == undefined) {
						rechargeBase.rechargeRouteChange($("#amountPaValue").text(), $("li.curronli span"));
					}
					flg = true;
//				}else{
//					$("#mbbCardNoMSG").html("<i></i>"+result['msg']);
//					$('#mbbCardNoMSG').hide();
//					$('#mbbCardNoMSG').fadeIn("slow");
//					flg = false;
//				}
			}
		}
	}); //ajax end!
	return flg;
};//checkCardNo end! 


//llPay.init();