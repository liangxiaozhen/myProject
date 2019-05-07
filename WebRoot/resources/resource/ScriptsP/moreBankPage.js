var moreBankPage = moreBankPage || {};
moreBankPage.init = function(){
	moreBankPage.initialize();
};

moreBankPage.initialize = function(){
	
};


//弹出框选银行点确认
//弹出框选银行点确认
moreBankPage.surebank1 = function(){
	if($("#areaBank li.curronli").length == 0){rechargeBase.cancel();return;}
	
	//判断是否有选择的银行
	var sect = $(".curronli").html();
	if(sect==null || sect==""){
		var dis = $(".inputbank").css("display");
		if(dis!="none"){
			var htm = "<i></i>请输入支持银行的银行卡号！";
			//提示选择银行
			$("#choseBankNoMSG").html(htm);
			$('#choseBankNoMSG').hide();
			$('#choseBankNoMSG').fadeIn("slow");
			
			$("#choseBankMSG").html("");
			$('#choseBankMSG').css("display","none");
		}else{
			//提示选择银行
			var htm = "<i></i>请选择充值银行！";
			$("#choseBankMSG").html(htm);
			$('#choseBankMSG').hide();
			$('#choseBankMSG').fadeIn("slow");
			
			$("#choseBankNoMSG").html("");
			$('#choseBankNoMSG').css("display","none");
		}
		
	}else{
		
		//如果输入了卡号，带过去
		var inCardNum = $("#i_bank").val();
		if(inCardNum!=null && inCardNum!="" && inCardNum.length>10){
			$('#mbbCardNo').val(inCardNum);
			$("#showDiv1").html("");
			$('.bankMegTab').show();
			$('#myotherbank').show();
			var trid = this.selectBankAndShow();
			selectBankDef($(trid + ' ul li:eq(0)')[0],2);//2表示“新用户选银行”
			closeAll_5();			
			//checkCardNo();//已经验证过，无需再次验证
		}else{
			$('#mbbCardNo').val('');//新选了银行，需要清空用户之前输入的卡号
			
			//$("#showDiv1").html("");//清空之前“老用户”的绑定卡信息
			$('#myotherbank').show();//显示“新用户登录充值”的大表格，包含身份、姓名、卡号等元素
			var trid = this.selectBankAndShow();
			// 新选了银行，清空用户所输入的充值金额，同时删除渠道
			if ($("#amount").val() != undefined) {
				$("#amount").val("");
				$(trid + ' ul li.curronli:eq(0) span input').attr("title", "");
			}
			
			selectBankDef($(trid + ' ul li.curronli:eq(0)')[0],2);//2表示“新用户选银行”
			closeAll_5();
		}
		
		
	}
	$('.input_bank').focus();
	$("#mbbCardNoMSG").css("display","none");
	
	//"选择其他银行"的弹出层恢复默认状态
	$(".unknows").show();
	$(".inputbank").hide();
	$(".allbank").show();
	
	//恢复“更多银行”的文字
	$('.citySelect dl dt').html('<i class="fr mar_t10 icon_base icon_base_selectdown mar_r10 mar_l5"></i><span class="sp pad_l10 fc_blue"><b>更多银行</b></span>');
	

};



//输入卡号后系统会智能识别银行文本框的事件处理
moreBankPage.fmCardNo = function(){
	$("#i_bank").keyup(function(event) {
    	this.value =this.value.replace(/\s/g,'').replace(/(\d{4})(?=\d)/g,"$1 ");
        var str = $('#i_bank').val();
        $(".j_bank").html(str);
        $(".touzi01 i").fadeTo(300,1);
        $(".touzi01 i").show();
    });
};

//输入卡号后系统会智能识别银行文本框的事件处理
moreBankPage.fmCardNOblue = function(){
	$(".touzi01 i").hide();//银行卡方法显示的层隐藏
	llPay.getBankNo();//调接口加载输入的银行卡对应的银行信息
};


/*
 * 弹出框中银行选中事件处理
 */
moreBankPage.selectBank = function(bid){
	$("#choseBankMSG").html("");
	$('#choseBankMSG').css("display","none");
	$("#choseBankNoMSG").html("");
	$('#choseBankNoMSG').css("display","none");
	
	$('.new-shortcut-new ul li').removeClass('curronli');
	$('.new-shortcut ul li').removeClass('curronli');
	
	$("#"+bid+"areaBank").addClass('curronli').siblings().removeClass('curronli');
};



//选择其他银行
moreBankPage.otherbank = function(){
	/**初始化弹框*/
	$('#addBank-pop-kj').html($('#addBank-pop-kj').html());
	//地址select
	var csDt=$('.citySelect dl dt');
	var csDd=$('.citySelect dl dd a');
	csDt.on('click',function(){
		var oC=$(this).siblings('dd');
		if(oC.is(':visible')){
			oC.hide();
//			oC.css("display","none");
		}else{
			oC.show();
//			oC.css("display","block");
		}
		return false;
	});
	
	$(document).on('click',function(){
		csDt.siblings().hide();
	});
	
	csDd.each(function(){
		$(this).click(function(){
			var _index=($(this).html());
			csDt.html('<i class="fr mar_t10 icon_base icon_base_selectdown mar_r10 mar_l5"></i><span class="sp pad_l10 fc_blue"><b>'+_index+'</b></span>');
			setTimeout(function(){
					csDt.siblings('dd').hide();
					$(".unknows").show();
		    		$(".inputbank").hide();
					$(".allbank").show();
			});
		});
	});
	
	var allbanckshow=$('.allbank a');
	allbanckshow.bind('click',function(){
		$(".unknows").hide();
		$(".inputbank").show();
		$(".allbank").hide();
		
		$("#choseBankMSG").html("");
		$('#choseBankMSG').css("display","none");
		$("#choseBankNoMSG").html("");
		$('#choseBankNoMSG').css("display","none");
	});
	
	//--输入框内提示-------------
	$(".touzi01 .input_1").each(function(){
		 var thisVal=$(this).val();
		 if(thisVal!=""){
		   $(this).siblings("span").hide();
		  }else{
		   $(this).siblings("span").show();
		  }
		 $(this).focus(function(){
		   $(this).siblings("span").hide();
		  }).blur(function(){
			var val=$(this).val();
			if(val!=""){
			 $(this).siblings("span").hide();
			}else{
			 $(this).siblings("span").show();
			} 
		  });
		});
	/**初始化弹框*/
	
	//是否有已经选择的银行
	var sect = $(".curronli").html();
	if(sect!=null && sect!=""){
		var webBankName = $(".curronli input").attr("name");
		if(webBankName == "bankRadio"){
			choseBid = $(".curronli").find("input[name='bankRadio']")[0].value;
		}
	}
	
	var staticCss = $("#staticCss").val();
	$.ajax({
    	type: "post",
   	 	url: "/member/getBankListByArea.do",
    	dataType: "json",
    	data:{
    		district:""
   		},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(data){
    		$("#areaBank").html("");
            $(data).each(function(index,domEle){
                var li = '<li onclick="moreBankPage.selectBank('+domEle.bankid+')" id="'+domEle.bankid+'areaBank"><a href="javascript:;"><img src="'+staticCss+'/'+domEle.picurl+'"></a><em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em><span><input type="hidden" name="bankRadio" id="'+domEle.bankid+'bankName" value="'+domEle.bankid+'"/><div style="display:none">'+domEle.memo+'</div></span></li>';
                $("#areaBank").append(li);
            });
    	}
	});
	  $(".pop-close").click(function(){ 
		    popUp.hideLayer($("#addBank-pop-kj"))});
	//"选择其他银行"的弹出层恢复默认状态
	$(".unknows").show();
	$(".inputbank").hide();
	$(".allbank").show();
	//showCon_5();
};

//选城市查银行
moreBankPage.clickCity = function(city){
	$("#choseBankMSG").html("");
	$('#choseBankMSG').css("display","none");
	
	$("#choseBankNoMSG").html("");
	$('#choseBankNoMSG').css("display","none");
	
	var staticCss = $("#staticCss").val();
	$.ajax({
    	type: "post",
   	 	url: "/member/getBankListByArea.do",
    	dataType: "json",
    	data:{
    		district:city
   		},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(data){
    		$("#areaBank").html("");
            $(data).each(function(index,domEle){
                var li = '<li onclick="moreBankPage.selectBank('+domEle.bankid+')" id="'+domEle.bankid+'areaBank">' +
                		'<a href="javascript:;"><img src="'+staticCss+'/'+domEle.picurl+'"></a>' +
                		'<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>' +
                		'<span>' +
                				'<input type="hidden" name="bankRadio" id="'+domEle.bankid+'bankName" value="'+domEle.bankid+'"/>' +
                		'<div style="display:none">'+domEle.memo+'</div></span></li>';
                $("#areaBank").append(li);
            });
    	}
	});
};

//选择银行之后需要 回显到  界面中  add by lingjs
moreBankPage.selectBankAndShow = function(){
	var index = $(".ui-select-listBox-list--now").index();//  0:快捷支付，1:网银支付
	var trid;
	if(index == '0'){
		//0:快捷支付		
		var choseBankFlg = $("#choseBankFlg").val();//绑定过与未绑定过卡的状态
		//未绑定过卡的
		if(choseBankFlg=='0'){
			trid = "#banktrNewUser";
			$(trid + ' ul').empty();//默认充值页面那15家银行不用显示了
			var newmybank=($('.new-shortcut-allbank li.curronli').html());//拷贝当前用户在弹出层上选择的银行信息，<li><a></a><span></span></li>   选择银行弹出框中选择的  银行li
			$(trid +' ul').append('<li class="curronli">'+newmybank+'</li><li id ="addBank-btn" onclick="moreBankPage.otherbank()" ><a href="##">+更多银行</a></li>');
			$('.new-shortcut-allbank li.curronli').removeClass("curronli");
		}else if(choseBankFlg=='1'){
			trid = "#banktrOldUser";
			$("#selectedBank img").attr("src",$("#areaBank li.curronli").find("img").attr("src"));
			$("#selectedBank input").attr("id",$("#areaBank li.curronli").find("input").attr("id"));
			$("#selectedBank input").attr("value",$("#areaBank li.curronli").find("input").attr("value"));
			$("#selectedBank .showBankidSpan").text($("#areaBank li.curronli").find(".showBankidSpan").text());
			
			$(trid +' li.curronli').removeClass("curronli");
			$(trid +' ul li').each(function(){
				var dp = $(this).css("display"); 
				if(dp && dp=='none'){
					$(this).remove();
				}
			});
			$(trid +' ul').append($(".curronli").css("display","none"));
		}else{
			//不做处理
			
		}		
		
		
		
	}else if(index == '1'){
		//1:网银支付
		
	}
	
	return trid;
}


