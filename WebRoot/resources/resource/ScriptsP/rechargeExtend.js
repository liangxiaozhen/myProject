
var choseBid="";
$(document).ready(function(){
	
	var bankBid = "";//bid:nwd_bank表的主键
	var bankType = "";//渠道：nwd_bank表的type字段
	var bankMbbId = "";//mbbid:nwd_member_bank表的主键	
	
	// added by Chen.Guiyang 20151221 充值渠道路由规则优化 start
	// 充值页面 对充值金额做渠道验证
    $("#amount").blur(function(){
        rechargeBase.rechargeRouteChange($("#amount").val(), $("li.curronli span"));
    });

    // 针对充值购买页面初期未绑卡的情况
	if ($("#amount").val() == undefined) {
		$("#mbbCardNo").blur(function(){
			if ($(".new-shortcut-new ul li").hasClass("curronli")) {
				llPay.checkCardNo();//借助连连支付平台的接口去验卡格式
			}else {
				$("#bank_MSG_0").html("<i></i>请选择支付银行");
				$('#bank_MSG_0').hide();
				$('#bank_MSG_0').fadeIn("slow");
				// 清空银行卡号，请用户再输入卡号，以便路由充值渠道。
				$("#mbbCardNo").val("");
			}
		});
	}

    // 点击另一个绑定的银行卡
    $(".new-shortcut ul li span").click(function() {
    	// 针对充值页面，清空已经输入的充值金额，同时删除渠道
		if ($("#amount").val() != undefined) {
			$("#amount").val("");
			$('.new-shortcut ul li span input').attr("title", "");
		// 针对充值购买页面，点击另一个绑定的卡号，路由充值渠道
		} else {
			rechargeBase.rechargeRouteChange($("#amountPaValue").text(), $(this));
		}
	});
	// added by Chen.Guiyang 20151221 充值渠道路由规则优化 end

	/*
	 * 页面的[确认充值]按钮事件绑定
	 */
	$("#confirmBut").click(function(e){
		//如果按钮已为  disabled，则不做任何事情
		var disabled = $(this).attr("disabled");
		if(disabled == 'disabled'){
			return false;
		}
		
		//隐藏前一次提示的错误信息
		$(".errorTips").hide();
        $(".errorthirdTip").hide();
		
		//检查是否实名认证
		if(!rechargeBase.checkCertIdentity()){
			console.dir("实名未通过,请先进行实名认证！");
			$("#identityMSG").html("实名未通过,请先进行实名认证！").show();
			//rechargeBase.confirmBut.reset();
			return;//实名未通过，直接结束流程
		}
		
		rechargeBase.checkBlackUser();//检查当前用户是否为黑名单用户

	 	var index = $(".ui-select-listBox-list--now").index(); //判断当前是哪种充值方式：0:快捷支付   1:网银支付    2:支付平台
	 	var rd = "input[name='bankRadio']";//老用户充值：
	 	var bank_MSG_Id = "#bank_MSG";
	 	
	 	var disp = $(".bankclass").css("display");   //老用户充值：绑定银行的集合的展示表格的id号
	 	if(disp=="none"){
	 		bank_MSG_Id = "#bank_MSG_0";		//错误提示层对应的id号： 快捷新用户
	 	}
	 	if(index==1){
	 		rd = "input[name='bankRadio_1']";//网银充值：
	 		bank_MSG_Id = "#bank_MSG_1";  //错误提示层对应的id号：网银充值
	 	}else if(index==2){
	 		rd = "input[name='bankRadio_2']";//支付平台充值：
	 		bank_MSG_Id = "#bank_MSG_2"; //错误提示层对应的id号：支付平台充值
	 	}
	 	
		$(bank_MSG_Id).css("display","none");
		var selectli = $(".curronli").find(rd);  //用户选择了什么银行/平台
		
		if(selectli.length<=0){//说明没有选择
			if(index==1){   //用户在网银支付平台下，没有选，则取默认选中了网银支付
				bankBid = $("#bankRadioDef").val();  //取得设定的默认值
				bankType = $("#bankRadioDefType").val(); //取得设定的默认值
		 	}else{
		 		if(index==2){//提示用户选择其中一种支付平台
	    			$(bank_MSG_Id).html("<i></i>请选择支付平台");
	    		}else{//快捷支付情况下，提示用户选择一家银行，如果有默认值也可以不选了
	    			$(bank_MSG_Id).html("<i></i>请选择支付银行");
	    		}
				$(bank_MSG_Id).hide();
				$(bank_MSG_Id).fadeIn("slow");
				return;
		 	}
		}else{//说明就是选中了具体的银行/平台
			//验证银行
		    bankBid = $(".curronli").find(rd)[0].value;
		    if(index==1){
		    	//如果是网银，调用后台请求路由渠道
		    	rechargeBase.rechargeRouteChangeForWY(bankBid,$("#amount").val(),$(".curronli").find(rd)[0]);
		    }
		    //取得渠道
		    bankType = $(".curronli").find(rd)[0].title;
		    //取得mbbid
		    bankMbbId = $(".curronli").find(rd)[0].alt;
		    
	    	if(bankBid==""){
	    		if(index==2){
	    			$(bank_MSG_Id).html("<i></i>请选择支付平台");
	    		}else{
	    			$(bank_MSG_Id).html("<i></i>请选择支付银行");
	    		}
				$(bank_MSG_Id).hide();
				$(bank_MSG_Id).fadeIn("slow");
	    		return;
			} else {
				$(bank_MSG_Id).css("display","none");
			}
		}
		
		/*银行卡号在用户失去焦点时已经验证过，无需额外验证 ，此处优化下，无需2次重复验证*/
		if(index==0 && (disp !="none")){//快捷新用户，需要验证用户的卡，身份等要素
			var bkCdNoFlg = llPay.checkCardNo();
			if(!bkCdNoFlg){
				return false;
			}			
		}
		
		//卡格式验证、金额、手续费、验证码等验证
		if(bankType == CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K && !kqPay.formCheck()){
			return;
		}else if(bankType == CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K && !ylPay.formCheck()){
			return;
		}else if(bankType == CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K && !bfPay.formCheck()){
			return;		
		}else if(bankType == CONST_PAYCHANNELS.KFT_QUICK_PAY.K && !ylPay.formCheck()){
			return;
			// added by Chen.Guiyang 20160104 充值渠道路由规则优化 start
		}else if(bankType == ""){
			if($("#amount").val() == "") {
				$("#amountMSG").html("<i></i>请填写充值金额！");
			}
			$('#amountMSG').hide();
			$('#amountMSG').fadeIn("slow");
			return;
		}
		// added by Chen.Guiyang 20160104 充值渠道路由规则优化 end
		
		confirmButStateSet($(this));//“确认充值”按钮禁用掉！
		
		if(bankType != CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K && bankType != CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K 
				&& bankType != CONST_PAYCHANNELS.KFT_QUICK_PAY.K && bankType != CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K){
			showCon_1();//非快钱支付时，弹出有关“支付反馈”的遮盖层【该方法属于全局js文件里的】
		}
		if(bankType != CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K && bankType != CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K
				&& bankType != CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K){
			showWyAlert1();//非快钱支付时，弹出有关“支付反馈”的遮盖层【该方法属于全局js文件里的】
		}
		
		$("#tips").css("display","none");//zhuzy 黑名单-由于弹出框样式都一样，防止弹出其他弹出框时也会弹出黑名单提示框
		$('#selectedBid').val(bankBid);
		$('#selectMbbId').val(bankMbbId);
		//根据取得充值平台设置提交地址
		for(var prop in CONST_PAYCHANNELS){
			if(bankType == CONST_PAYCHANNELS[prop].K){
				document.forms[0].action = CONST_PAYCHANNELS[prop].V;
				break;
			}
		}
		
		/** added by Chen.Guiyang 20160106 充值渠道路由规则优化 start */
		//因为渠道可能切换，绑定在其它渠道上的卡号切换到易连，就将作为首次充值。但是卡号不是输入的，是从画面hidden值取出来的。
		if ($("#choseBankFlg").val() == '1' && ($("#mbbCardNo").val() == null || $("#mbbCardNo").val() == '')) {
			$("#mbbCardNo").val($(selectli).parent().parent().next().val());
		}
		/** added by Chen.Guiyang 20160106 充值渠道路由规则优化 end */

		if(bankType != CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K 
			&& bankType != CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K
			&& bankType != CONST_PAYCHANNELS.KFT_QUICK_PAY.K
			&& bankType != CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K){
			//连连等其他快捷支付时按照原来的方式提交
			document.forms[0].submit();
		}
		else
		{	
			if(bankType == CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K){
				kqPay.doKQPay();//快钱支付请求
			}else if(bankType == CONST_PAYCHANNELS.KFT_QUICK_PAY.K){
				kftPay.doKftPay();
			}else if(bankType == CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K){
				bfPay.dobfPay();	
			}else if(bankType == CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K){
				
				//var flag = ylPay.verify.query($("#mbbCardNo").val(),bankMbbId);
				if(!ylPay.verify.status){
        			ylPay.showYiLianInstruction(2);//弹出易连首充遮盖层
        			ylPay.doFirstPay($("#mbbCardNo").val(),bankBid,$("#factamount").val());//发送易连首次代付请求
        		}else{
        			ylPay.doSecondPay($("#rechargeCode").val(),$("#validCode").val(),$("#buyFlag").val(),$("#factamount").val());//发送易连二次代付请求
        		}
			}
			
		}
		return false;
	 });//confirmBut click end!
	 
});//document ready end!

// added by Chen.Guiyang 20151216 充值渠道路由规则优化 start
function quotasIlluMouseOver(obj) {
	$(".tkIn01").removeClass("hidden");
	$(".tkIn01").attr("style", "left:-25px; top:10px;");
};

function quotasIlluMouseOut(obj) {
	$(".tkIn01").addClass("hidden");
	$(".tkIn01").attr("style", "display:none; left:-25px; top:10px;");
};
// added by Chen.Guiyang 20151216 充值渠道路由规则优化 end

/*
 * 充值收银台-选中银行图片框-事件处理
 * ele: li标签dom对象
 * tabIndex: 1：老用户；2：新用户
 */
function selectBankDef(ele,tabIndex,selectFlg){
	if(selectFlg){
		//页面选择新的银行卡时去除原来的银行卡信息和金额信息
		$("#mbbCardNo").val("");
		$("#amount").val("");
	}
	
	$("#bank_MSG").hide();
	if ($("#amount").val() != undefined) {
		$("#amountMSG").hide();
	} else {
		$("#bank_MSG_0").hide();
	}
	$("#sendDynMSG").hide();
	$(".errorTips").css('display','none');
	$(".errorthirdTip").css('display','none');
	$(".tip_default2").css('display','none');//清除文本框默认的文字提示
	
	//var bid=$(ele).find("input").val();
	var channelId=$(ele).find("input").attr("title");
	var mbbId=$(ele).find("input").attr("alt");

	/* changed by Chen.Guiyang 20151229 充值渠道路由规则优化 start */
	var remark;
	// 针对快捷支付
	// 页面初期加载银行的渠道限额未按限额高低排列，故异步获取该银行最高的渠道限额
	if ($(".ui-select-listBox-list--now").index()=='0') {
		$.ajax({
	    	type: "post",
	    	async: false,
	   	 	url: "/pay/yintong/getBankRemark.do",
	    	data:{
	    		bid : $(ele).find("input").val(),
				type : 0// 设为0:快捷支付
			},
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(res){
				if(res==null || res=="null"){
					$("#bank_MSG").html("<i></i>充值渠道限额取得出错！");
					$('#bank_MSG').hide();
					$('#bank_MSG').fadeIn("slow");
				}else{
					remark = res['bankRemark'];
				}
	    	}   
		});
	}
	/* changed by Chen.Guiyang 20151229 充值渠道路由规则优化 end */

	rechargeBase.CHANNELID = channelId;

	var quotasIllustration = '  <span class="fc_blue">限额说明</span><span class="tipBox inline_block vertical_middle"><div class="tipBox-mark nwd_icon nwd_icon_newtootips mar_l5" style="margin-top:-5px;*margin-top:2px;"><div style="left: 25px; top: -25.5px;" class="tips toortipBox-con r_tip-con j_eye-hide"><i class="nwd_icon nwd_icon_toortiparrow"></i><p class="fc_9">该限额为最高可支付金额，若您在银行的设置限额更低，则以低限额为准，提高限额方法见<a href="/member/rechargeFail.do" class="fs_12 fc_blue">帮助中心</a></p></div></div></span>';
	
	//银行支付提示设置
	if(channelId == CONST_PAYCHANNELS.YILIAN_QUICK_PAY.K){//易连支付
		//$.getScript('/js/recharge/ylPay.js');
		ylPay.init();
		kqPay.smsCountdown.reset();//一边在发短信，一边在切换了银行后，短信还在倒计时，所以切换时需要清空定时器
		var yilian_tip = '<p class="yl_tips">该银行使用银联语音支付，时尚又便捷！<a href="javascript:void(0)" onclick="ylPay.showYiLianInstruction(1)" class="yl_tips_dailog">了解语音支付</a></p>';
		$(".bankRemark").html("支付限额："+ remark + quotasIllustration).show();
		$(".bankRemark").append(yilian_tip);
		if(mbbId!=undefined){//mbbId为空则说明是新用户,无历史记录的初始页面点击，需要任何操作！
			var flag = ylPay.verify.query(undefined,mbbId);//flag == true 表示用户已经在易连侧绑卡了
			if(flag && mbbId!=undefined && mbbId!="" && mbbId.length>0){//用户选择了历史充值银行
				$("#phone").val($("#mbPhone").val()).attr("disabled","disabled");
			}else{
				ylPay.verifyInitPage();//隐藏页面手机号和短信验证码
			}
		}else{
			ylPay.verifyInitPage();//隐藏页面手机号和短信页面码
		}
	}else if(channelId == CONST_PAYCHANNELS.KUAIQIAN_QUICK_PAY.K){//快钱支付
		//$.getScript('/js/recharge/kqPay.js');
		kqPay.init();
		ylPay.smsCountdown.reset();//一边在发短信，一边在切换了银行后，短信还在倒计时，所以切换时需要清空定时器
		$(".bankRemark").html("支付限额："+remark+quotasIllustration).show();
		
		if(mbbId!=undefined && mbbId!="" && mbbId.length>0){//用户选择了历史充值银行
			kqPay.setPhoneByKuaiQianPCI(mbbId);//去快钱获取用户首次充值时填写的预留手机号
		}else{//用户新增了一个快钱支持的银行
			$(".tip_default2").css('display','');//文本框默认的文字提示
			$("#phone").attr("disabled",false);//新增银行卡，需要给出手机号文本框可编辑
		}
	}else if(channelId == CONST_PAYCHANNELS.KFT_QUICK_PAY.K){//快付通支付
		//$.getScript('/js/recharge/kftPay.js');
		kftPay.init();
		kftPay.smsCountdown.reset();//一边在发短信，一边在切换了银行后，短信还在倒计时，所以切换时需要清空定时器
		$("#bankRemark").html("支付限额："+remark+quotasIllustration).show();
		
		if(mbbId!=undefined && mbbId!="" && mbbId.length>0){//用户选择了历史充值银行
			kftPay.setPhoneByKft(mbbId);//快付通获取用户首次充值时填写的预留手机号
		}else{//用户新增了一个快付通支持的银行
			$(".tip_default2").css('display','');//文本框默认的文字提示
			$("#phone").val('').attr("disabled",false);//新增银行卡，需要给出手机号文本框可编辑
		}
	}else if(channelId == CONST_PAYCHANNELS.BAOFU_QUICK_PAY.K){//宝付支付
		bfPay.init();
		bfPay.smsCountdown.reset();//一边在发短信，一边在切换了银行后，短信还在倒计时，所以切换时需要清空定时器
		$("#bankRemark").html("支付限额："+remark+quotasIllustration).show();
		
		if(mbbId!=undefined && mbbId!="" && mbbId.length>0){//用户选择了历史充值银行
			bfPay.setPhoneBybf(mbbId);
		}else{//用户新增了一个快付通支持的银行
			$(".tip_default2").css('display','');//文本框默认的文字提示
			$("#phone").val('').attr("disabled",false);//新增银行卡，需要给出手机号文本框可编辑
		}	
	}else{//连连支付
		//$.getScript('/js/recharge/llPay.js');
		llPay.init();
		$(".bankRemark").html("支付限额："+remark+quotasIllustration).show();
	}	
	
	assetsCommon.tipPop();
}


//网银支付清空已选
function selectBankWeb(ele){
	$('.new-shortcut-2 li').removeClass('curronli');
	var remark=$(ele).find("div").html();
	$("#xeinfo").html(remark).show();//显示银行限额信息	
}

/*
 * 充值按钮异步提交 状态设置
 */
var confirmButStateSetTime;
function confirmButStateSet(id){
	id.attr("disabled", "disabled");
	var loading=Array("处理中","处理中.","处理中..","处理中...");
	var i=0;
	confirmButStateSetTime=setInterval(autoplay,300);
	function autoplay(){
		i++;
		if(i<loading.length){
	        id.val(loading[i]);
		}
		else if(i==loading.length){
	        id.val(loading[0]);
			i=0;
			if(i<loading.length){
		       id.val(loading[i]);
			}
		}
	};
	id.removeClass("btn_orange").addClass("btn_gray1");
	id.css('text-align','left');
    id.css('padding-left','15px');

}

//弹框支付
function showWyAlert1(){
	var attr = new Attention( popUp , '#plusBankContainer1' ,  true );
    attr.event();
}

//银联语音
function showWyAlert2(){
	var attr = new Attention( popUp , '#plusBankContainer111' ,  true );
    attr.event();
}

function showWyAlert3(){
	var attr = new Attention( popUp , '#plusBankContainer111' ,  true );
    attr.event();
}
