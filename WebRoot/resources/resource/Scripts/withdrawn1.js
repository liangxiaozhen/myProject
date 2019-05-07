//var yyMsg = alertMsgInfo.yyMsg;
var amountFlag = false;
var pwdFlag = false;
var msgFlag = false;
//var showForm=$('.plusBank1');//老版本确认提现弹出框
var showForm=$('#isgetmoney-pop-tx');//新版本确认提现弹出框
var showDiv=$('.plusBankBg');
var openDiv = $('.plusBank').html();
$(document).ready(function(){
    var cur_dh = $('#my_nwd_4');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
    
    var flag = $("#flag").val();
//    if(flag == 'bank'){//绑定银行
//    	art.dialog({
//    	content: '为了保障您的账户安全，提现前需绑定银行卡！',
//    	lock:true,
//    	ok:function(){
//    	    window.location.href='/member/addBankzStep.do?doWhat=add';
//    	    return false;
//    	},
//    	close:function(){
//    	    window.location.href='/member/addBankzStep.do?doWhat=add';
//    	    return false;
//    	}
//    	});
//    }else

    if(flag == 'iden'){
    	/* 老的
        art.dialog({
            content: '为了保障您的账户安全，提现前需实名认证！',
            lock:true,
            ok:function(){
                window.location.href='/member/identityAuthentication.do';
                return false;
            },
            close:function(){
                window.location.href='/member/identityAuthentication.do';
                return false;
            }
        });
        */
    	
    	//$("#isgetmoney-pop-tx").css("display","none");
    	var html = '<div class="plusBankBg"></div>';
    	html += '<div class="plusBank1 mini">';
    	html += '<div class="topper clearfix">';
    	html += '<span class="fl fs_18"></span>';
    	html += '<a class="fr" onClick="closeAll_1()"></a>';
    	html += '</div>';
    	html += '<div class="middle">';
    	html += '<div class="content">';
    	html += '<i class="ico_all size24 img_false24"></i>';
    	html += '为保障您的账户安全，提现前需要实名认证！';
    	html += '</div>';
    	html += '<div class="btnbox"><button id="ljrz_09171557" type="submit" class="btn btn_36c btn_size120">立即认证</button></div>';
    	html += '</div>';
    	html += '</div>';
    	$("#isgetmoney-pop-tx1").css("display","block");
    	$("#isgetmoney-pop-tx1").html(html);
    	$("#ljrz_09171557").click(function(){
    		location.href="/member/identityAuthentication.do";
    	});
    	showCon_1();
    	$("#tips").css("display","none");//zhuzy 黑名单-由于弹出框样式都一样，防止弹出其他弹出框时也会弹出黑名单提示框

    }else{
//        $("#boxTitle").html("信息提示:");
//        $("#boxContext").html("<div class='w485 fc_3'>为了保障您的账户安全，提现前需实名认证！<br><br></div>" +
//            " <div class='btnbox'><button class='btn btnSize_1 btn_blue plus_c'>确认</button></div>");
//        showCon_0();
    }

    $("#amount").blur(function(){
        var amount1 = $("#amount").val();
        var amount = amount1.replace(/(^\s*)|(\s*$)/g,'');
        var amountShiJi = $("#memberAmount5").val();//可提现金额
        //使用提现券 St
        //获取是否使用提现券
        var ckPutBond = $("#ckPutBond").attr('checked');
        //使用提现券 End
        
        dlSxfTip();

        if(($('#drawnFlag').val()!=null || $('#drawnFlag').val()!="") && $('#drawnFlag').val()=="0"){
            $('#errorOpt').hide();
            $('#errorOpt').fadeIn("slow");
            recoverButton();
            return;
        }
        if($("input[name='bankRadio']:checked").val()==undefined||$("input[name='bankRadio']:checked").val()==""){
            $("#mbbCardNoMSG4out").html("<i></i>请输选择银行！");
            $('#mbbCardNoMSG4out').hide();
            $('#mbbCardNoMSG4out').fadeIn("slow");
            recoverButton();
            return;
        }
        if(amount==""){
            amountFlag = false;
            $("#amountMSG").html("<i></i>请输入提现金额");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
            return;
        }
        if(!amount.match(/^(?:0\.\d{1,2}|(?!0)\d+(?:\.\d{1,2})?)$/)){
            amountFlag = false;
            $("#amountMSG").html("<i></i>请输入正确的金额(如果有小数位最多2位)");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
            return;
        }
        if(amount <= 0){
            amountFlag = false;
            $("#amountMSG").html("<i></i>提现金额必须大于0元");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
            return;
        }
         if(amount <= 2){
            amountFlag = false;
            $("#amountMSG").html("<i></i>提现金额需2元以上");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
            return;
        }
        if(parseFloat(amountShiJi) < parseFloat(amount)){
            amountFlag = false;
            $("#amountMSG").html("<i></i>账户余额不足");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
            return;
        }
        var whiteFlag = $("#whiteFlag").val();
        if(whiteFlag==0){
        	if(parseFloat(amount)*100 > 1000000*100){
                amountFlag = false;
                 $("#amountMSG").html("<i></i>单笔提现最高不可超过100万");
                 $('#amountMSG').hide();
                 $('#amountMSG').fadeIn("slow");
                 return;
             }
        }
        
                
        //使用提现券 St
        if(ckPutBond == 'checked' && 1==2){/* 1==2 ？ 之所以这样是为因为代码需求变更,这样改下让该分支永远不执行,省的删除了这段代码后续需求再换过来又要大改*/
            amountFlag = false;
            $("#amountMSG").html("<i></i>使用提现券提现金额不得高于20万");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
            //使用提现券 End
        }else{
            //使用提现券 St
            var putBond = 0;
            if(ckPutBond == 'checked'){
                putBond = 1;
            }
            //使用提现券 End
            if(parseFloat(amountShiJi) < parseFloat(amount)){//可提现金额<填写金额
                amountFlag = false;
                $("#amountMSG").html("<i></i>账户余额不足");
                $('#amountMSG').hide();
                $('#amountMSG').fadeIn("slow");
            }else{
                $.ajax({
                    type: "post",
                    url: "/member/amountCompare.do",
                    dataType: "json",
                    data:{
                        amount:amount,
                        //使用提现券 St
                        putBond:putBond,
                        //使用提现券 End
                        cardNo:$("input[name='bankRadio']:checked").attr("useCardNo"),
                        //提现类型 0：普通   1：快捷
                        carryType:$('input[name="carrytype"]:checked').val()
                    },
                    async: false,
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function(date){
                        if(date['msg']=="0"){
                            amountFlag = false;
                            $('#amountMSG').css("display","");
                            $('#msg').css("display","none");
                            $("#amountMSG").html("<i></i>可用额不足");
                        }else if(date['msg']=="-1"){
                            amountFlag = false;
                            $('#amountMSG').css("display","");
                            $('#msg').css("display","none");
                            $("#amountMSG").html("<i></i>提现金额必须大于0元");
                            $('#realityAmount').html(0.00);
                        }else if(date['msg']=="-4"){
                            amountFlag = false;
                            $('#amountMSG').css("display","");
                            $('#msg').css("display","none");
                            $("#amountMSG").html("<i></i>单笔提现最高不可超过100万");
                            $('#realityAmount').html(0.00);
                        }else if(date['msg']=="-2"){
                    		var showMsg = "";
                    		var outAmount1 = date['rstAmount'];
                    		if(outAmount1< 0 ){
                    			outAmount1 = 0;
                    		}
                    		var outAmount = outAmount1.toFixed(2);
                    		if(date['rstCase']=="1"){
                    			showMsg = "<i></i>当前银行卡的提现金额不能超过"+outAmount+"元，请使用<a style='color:blue;' href='javascript:void(0)' onclick='bindCard(1,"+date['rstMbbId']+");'>尾号为"+date['rstCard']+"</a>的银行卡提现，详询400-7910-888";
                    		}else if(date['rstCase']=="2"){
                    			showMsg = "<i></i>当前银行卡的提现金额不能超过"+outAmount+"元，请再绑定<a style='color:blue;' href='javascript:void(0)' onclick='bindCard(2,-1);'>尾号为"+date['rstCard']+"的银行卡</a>后再使用新卡提现，详询400-7910-888";
                    		}else if(date['rstCase']=="4"){
                    			showMsg = "<i></i>当前银行卡提现金额不能超过"+outAmount+"元，详询400-7910-888";
                    		}
                            amountFlag = false;
                            msgFlag = true;
                            $('#amountMSG').css("display","");
                            $('#msg').css("display","none");
                            $("#amountMSG").html(showMsg);
                            $('#realityAmount').html(0.00);
                        }else if(date['msg']=="-3"){
                        	addBank('1');
                        } else if(date['msg']=="-5"){
                        	amountFlag = false;
                            $('#amountMSG').css("display","");
                            $('#msg').css("display","none");
                            $("#amountMSG").html("<i></i>快捷提现金额大于 当日剩余快捷提现额度，请重新设置快捷提现金额或选择普通提现");
                        }
                        else{
                            amountFlag = true;
                            $('#amountMSG').css("display","none");
                            $("#amountMSG").html("");
                            $('#msg').css("display","");
                            /*使用提现券 注释
                             $('#realityAmount').html(date['realityAmount']);
                             $('#poundage1').html(date['poundage']);
                             $('#poundage2').html(date['poundage']);
                             */
                            //使用提现券 St
                            if(ckPutBond == 'checked'){
                                $('#realityAmount').html(amount.toFixed(2));
                                $('#poundage1').html("0.00");
                                $('#poundage2').html("0.00");
                            }else{
                                $('#realityAmount').html(date['realityAmount'].toFixed(2));
                                $('#poundage1').html(date['poundage'].toFixed(2));
                                $('#poundage2').html(date['poundage'].toFixed(2));
                            }
                            //使用提现券 End
                        }
                    }
                });
            }

        }
    });

    $("#pwd").blur(function(){
        //阉割掉这个验证，交互不合理
        var pwd = $("#pwd").val();
        if(pwd==""){
            pwdFlag = false;
            $("#pwdMSG").html("<i></i>请输入交易密码");
            $('#pwdMSG').hide();
            $('#pwdMSG').fadeIn("slow");
        }else{
            pwdFlag = true;
            $('#pwdMSG').css("display","none");
            $("#pwdMSG").html("");
            $('#msg').css("display","");
        }
    });
    
    $('input[name="carrytype"]').click(function(){
    	$("#amount").blur();
    });
    
});
var checkBankFlag = false;
var blackUserTipFlag = true;

function disableButton(){
    $("#conformTx-btn").attr("disabled","disabled");
    $("#conformTx-btn").val("提交中...");
    $("#conformTx-btn").css('background','#808080');
}

function recoverButton(){
    $("#conformTx-btn").removeAttr("disabled");
    $("#conformTx-btn").val("确认提现");
    $("#conformTx-btn").css('background','#2577E3');
}

function sure(){
	//zhuzy 黑名单用户提示信息
	
	var isBlackUser = $("#blackUser").val();
	if(isBlackUser=="1"&&blackUserTipFlag){
		var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，";
		msg+="您的提现资金可能会退回到充值使用的银行卡。无法为您提现到指定银行卡，对此深表歉意。</p>";
		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
		$("#tipsContent").html(msg);
		$("#btnOk").unbind('click');
		$("#btnOk").click(function() {
			// 关闭提示信息弹出框
			//window.location.reload();
			closeTips();
			blackUserTipFlag = false;
		});
		showTips();
		return;
	}
	
	disableButton();
	
    if($(".fl.clearfix dt").hasClass("nobank")){
        $('#mbbIdMSG').css("display","");
        $("#mbbIdMSG").html("<i></i>请选择提现银行卡");
    }else{
        $('#mbbIdMSG').css("display","none");
        $("#mbbIdMSG").html("");
    }
    var bankType = $("input[name='bankRadio']:checked").parent().attr("class");
    var bankName = "";
    if(bankType=='bank10'||bankType=='bank25'){
        bankName = "深发银行";
    }else if(bankType=='bank13'||bankType=='bank31'||bankType=='bank36'||bankType=='bank51'){
        bankName = "邮政储蓄";
    }else if(bankType=='bank101'||bankType=='bank1'||bankType=='bank14'||bankType=='bank27'||bankType=='bank43'){
        bankName = "工商银行";
    }else if(bankType=='bank102'||bankType=='bank5'||bankType=='bank19'||bankType=='bank80'||bankType=='bank90'){
        bankName = "交通银行";
    }else if(bankType=='bank103'||bankType=='bank3'||bankType=='bank17'||bankType=='bank29'||bankType=='bank44'){
        bankName = "中国银行";
    }else if(bankType=='bank104'||bankType=='bank2'||bankType=='bank16'||bankType=='bank28'||bankType=='bank46'){
        bankName = "农业银行";
    }else if(bankType=='bank105'||bankType=='bank4'||bankType=='bank15'||bankType=='bank30'||bankType=='bank45'){
        bankName = "建设银行";
    }else if(bankType=='bank106'||bankType=='bank8'||bankType=='bank20'||bankType=='bank33'||bankType=='bank48'){
        bankName = "民生银行";
    }else if(bankType=='bank107'||bankType=='bank6'||bankType=='bank22'||bankType=='bank34'||bankType=='bank117'){
        bankName = "中信银行";
    }else if(bankType=='bank108'||bankType=='bank12'||bankType=='bank23'||bankType=='bank38'||bankType=='bank50'){
        bankName = "兴业银行";
    }else if(bankType=='bank109'||bankType=='bank7'||bankType=='bank21'||bankType=='bank39'||bankType=='bank47'){
        bankName = "光大银行";
    }else if(bankType=='bank110'||bankType=='bank0'||bankType=='bank26'){
        bankName = "浦发银行";
    }else if(bankType=='bank111'||bankType=='bank11'||bankType=='bank18'||bankType=='bank37'||bankType=='bank53'){
        bankName = "招商银行";
    }else if(bankType=='bank112'||bankType=='bank41'||bankType=='bank42'||bankType=='bank52'){
        bankName = "平安银行";
    }else if(bankType=='bank113'||bankType=='bank40'){
        bankName = "上海银行";
    }else if(bankType=='bank114'||bankType=='bank32'){
        bankName = "北京银行";
    }else if(bankType=='bank115'){
        bankName = "华夏银行";
    }else if(bankType=='bank116'||bankType=='bank9'||bankType=='bank35'||bankType=='bank24'||bankType=='bank49'){
        bankName = "广发银行";
    };
//	var mbbId = $("#mbbId").val();
//	if(mbbId=="-1"){
//
//	}else{
//
//	}
    
    $("#amount").blur();
    $("#pwd").blur();
    
    //判断校验是否正确，错误则恢复按钮可点击
    if(!amountFlag || !pwdFlag){
    	recoverButton();
    }
    
    
    var bankCtiyId = $("#bankCtiyId").val();//选中的银行卡是否完善信息
	var bankBranch = $("#bankBranch").val();
    
    var prov = $("#bankProvCode1").find("option:selected").val();//省
    var cityName = $("#bankCityCode1").find("option:selected").val();//城市
    var branch = $("#mbbOpeningBranch1").val();
    
    if(bankCtiyId!=null || bankCtiyId!=""){
        if(prov=="-1"){
            $('#provMSG1').css("display","");
            $("#provMSG1").html("<i></i>请选择开户省市（省）！");
        }else{
            $('#provMSG1').css("display","none");
            $("#provMSG1").removeClass().html("");
        }
        
         if(cityName=="-1"){
             $('#cityNameMSG1').css("display","");
             $("#cityNameMSG1").html("<i></i>请选择开户省市（市）！");
         }else{
             $('#cityNameMSG1').css("display","none");
             $("#cityNameMSG1").html("");
         }
    }
    if(bankBranch!=null || bankBranch!=""){
    	if(branch ==null || branch ==""){
		   	$('#mbbOpeningBranchMSG1').css("display","");
            $("#mbbOpeningBranchMSG1").html("<i></i>填写开户支行！");
         }else{
             $('#mbbOpeningBranchMSG1').css("display","none");
             $("#mbbOpeningBranchMSG1").html("");
         }
    }
    
    if(amountFlag == true && pwdFlag == true && ($("#mbbId").val() != "-1")){
    	var mbbId = $("input[name='bankRadio']:checked").val();
	    if(mbbId=="" || mbbId==null){
	        $('#mbbNameMSG').css("display","");
	        $("#mbbNameMSG").html("<i></i>请选择银行名称！");
	    }else{
	        $('#mbbNameMSG').css("display","");
	        $("#mbbNameMSG").removeClass().html("");
	    }
	   
        var stok = "";
        if(document.getElementById ("stok")){
            stok  = document.getElementById ("stok").value;
        }
        if(((bankCtiyId == null || bankCtiyId =="" ) || (bankBranch == null && bankBranch =="")) && (mbbId != "")&&(prov != "-1")&&(cityName != "-1") && (branch!="" )){
        	//先完善银行卡信息
        	var editBankFlag = false;
        	var mbbId = $("input[name='bankRadio']:checked").val();
        	var doWhat = "edit";
            $.ajax({
                type: "post",
                url: "/member/saveBindingBank.do",
                dataType: "text",
                data:{
                    mbbName:$("input[name='bankRadio']:checked").attr("useBankName"),
                    mbbCardNo:$("input[name='bankRadio']:checked").attr("useCardNo"),
                    mbbOpeningBranch:$("#mbbOpeningBranch1").val(),
                    cityName : cityName,
                    bankName : bankName,
                    doWhat : doWhat,
                    mbbId : mbbId,
                    stok:stok,
                    withdraw:"withdrawAddBankInfo"
                },
                async: false,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function(msg){
                    if(msg == '2'){//保存成功
                    	// Adobe | zhenhua.xi | 20141031
                    	//alert('操作成功'+msg);
                    }else{//保存失败
                        //跳转第3步
                    	$("#saveError").html("操作失败");
                    	recoverButton();
                    	return false;
                    }
                },
                error:function(){
                	$("#saveError").html("操作失败");
                	recoverButton();
                	return false;
                }
            });
        };
    	

        if(getCookie("countMSG"+$('#mbId').val()+"")==""){//存在
            setCookie("countMSG"+$('#mbId').val()+"",5);//初始化为5
        }
        $.ajax({
            type: "post",
            url: "/member/checkBank.do",
            data:{
                mbbId:$("input[name='bankRadio']:checked").val()
            },
            async: false,
            dataType: "json",
            ContentType:"text/json;charset=utf-8",
            success: function(data){
                if(data['msg']=='succ'){
                    checkBankFlag = true;
                    $("#bankErrorMsg").css("display","none");
                    if(data['canEdit'] == 'true'){
                    	$("#realNameEdit").css("display","inline");
                    	if(data['accountName']!=null && data['accountName'].length>0){
                    		$('#realName1').css("display","none"); 
     	                    $('#bankAccountName').text(data['accountName']);
     	                    $('#bankAccountName').css("display","inline"); 
                    	}
                    }
                }else{
                    checkBankFlag = false;
                    //完善银行卡信息
                    //addBank('1');
                    $("#bankErrorMsg").css("display","block");
                    $("#bankErrorMsg").html('<i></i>'+data['msg']);
                }
            }
        });
        if(!checkBankFlag){
        	recoverButton();
            return false;
        }
        
        if($("#isSmsCodeDispaly").val()==1){
        	if($("#smsCode").val()){
            	//短信验证码
                $.ajax({
                    type: "post",
                    url: "/member/verificationCode1.do?type=withdraw",
                    dataType: "json",
                    data:{
                        verify:$("#smsCode").val()
                    },
                    async: false,
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    success: function(msg){
                        if(msg == 1){//相等
                        	checkBankFlag=true;
                        }else{//不相等
                        	checkBankFlag=false;
                        	$('#smsMSG').css("display","block");
            				$("#smsMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>短信验证码错误！");
                        }
                    }
                });
                if(!checkBankFlag){
                	recoverButton();
                    return false;
                }
            }else{
            	recoverButton();
            	$('#smsMSG').css("display","block");
    			$("#smsMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入短信验证码！");
    			return false;
            }
        }
       
        $.ajax({
            type: "post",
            url: "/member/memberCheckPwd.do",
            dataType: "json",
            data:{
                atPresentPwd:$("#pwd").val(),
                type:"repwd"
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function(msg){
                if(msg['msg'] == 1){
                    if(getCookie("countMSG"+$('#mbId').val()+"") > 0){
//	        				art.dialog.data('mbbId', $("#mbbId").val());
//	            			art.dialog.data('bankInfo', $("#bankInfo").text());
//	            			art.dialog.data('realityAmount', $("#realityAmount").text());
//	            			art.dialog.data('realName', $("#realName").val());
//	            			var _url = '../ftl/withdrawn/confirmAmount.html';
//	            			art.dialog.load(_url, false).title('确认提现信息');
//	            			return false;
//	        				var bankId = 0;
                       // var showForm1=$('.plusBank1');
                       //var showDiv=$('.plusBankBg');
                        $("[id$='bank']").each(function () {
                            if($("#"+this.id).attr("checked")=="checked"){
//	        					bankId = $("#"+this.id).val();
                                $('#bankInfo1').html(bankName+"("+$("input[name='bankRadio']:checked").attr("showCardNo")+")");
                                $('#mbbId').attr("value",$("#"+this.id).val());
//	        					$("#selectBankId").attr("value",$("#"+this.id).val());
                            }
                        });

                        $('#realityAmount1').html($("#realityAmount").text());//提现金额
                        $('#realName1').html($("#realNameWithdrawn").val()); //用户名
                        //showDiv.show();
                        //showForm1.slideDown();
                        var attr = new Attention( popUp , '#isgetmoney-pop-tx' ,  true );
        		        attr.event();
                        $('#tips').css("display","none");//zhuzy设置不显示黑名单提示框，否则会和其他弹出框一起显示因为都使用了plusBankBg样式
                    }
                    else{
                    	$("#saveError").css("display","");
                    	$("#saveError").html("<i></i>对不起，您的交易密码已被锁定，请明日再试");
                    	recoverButton();
                    }
                }
                else if(msg['msg']==0){
                	/*
                    if(getCookie("countMSG"+$('#mbId').val()+"") <= 0){
                        showMsg("对不起，您的交易密码已被锁定，请24小时后再来");
                    }else{
                        setCookie("countMSG"+$('#mbId').val()+"",getCookie("countMSG"+$('#mbId').val()+"")-1);
                        showMsg("交易密码错误,您还剩"+getCookie("countMSG"+$('#mbId').val()+"")+"次输入机会，请重新输入！");
                    }
                    */
                    var countMSG = parseInt(getCookie("countMSG"+$('#mbId').val()));
                    if(countMSG<0){
                    	countMSG = 0
                    }

                    if(countMSG <= 0){
                    	$("#saveError").css("display","");
                    	$("#saveError").html("<i></i>对不起，您的交易密码已被锁定，请24小时后再来");
                    	recoverButton();
                    }else{
                    	setCookie("countMSG"+$('#mbId').val(),countMSG-1);
                    	$("#saveError").css("display","");
                    	$("#saveError").html("<i></i>交易密码错误,您还剩"+parseInt(getCookie("countMSG"+$('#mbId').val()))+"次输入机会，请重新输入！");
                    	recoverButton();
                    }

                }
            }
        });

    }
}

function yes(){
//	closeDialog();
    //window.location.href="/member/withdrawnSave.do?mbbId="+$("#mbbId").val();
	/**
	 * 提交时没有token。通过访问URL直接提交借款Bug修复
	 */
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
    var mbbId = $("#mbbId").val();
    
    var postForm = document.createElement("form");
    postForm.method="post" ; 
    postForm.action = '/member/withdrawnSave.do'; 

    var mbbIdInput = document.createElement("input");
    mbbIdInput.setAttribute("name", "mbbId"); 
    mbbIdInput.setAttribute("value", mbbId); 
    postForm.appendChild(mbbIdInput);
    var stokInput = document.createElement("input");
    stokInput.setAttribute("name", "stok");
    stokInput.setAttribute("value", stok);
    postForm.appendChild(stokInput);
    
    var carryType = document.createElement("input");//提现类型
    carryType.setAttribute("name", "carryType")
    carryType.setAttribute("value", $('input[name="carrytype"]:checked').val()); 
    postForm.appendChild(carryType);
    
    document.body.appendChild(postForm); 
    postForm.submit(); 
    document.body.removeChild(postForm);
    
}

//function no(){
//	closeDialog();
//}

//关闭页面所有弹出层-------------------------------
function closeDialog() {
    var list = $.dialog.list;
    for (var i in list) {
        list[i].close();
    };
}


function setCookie(name, value, d) {
    var exp = new Date();
    if (d > 0)
        Days = d;
    exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value)
        + ";path=/;expires=" + exp.toGMTString();
}
//读取cookies
function getCookie(name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(name + "=");
        if (c_start != -1) {
            c_start = c_start + name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1)
                c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";

}
//删除cookies
function delCookie(name) {
    setCookie(name, '');
}

/**老版--添加银行卡  start*/
/*function addBank(type){
	var idalt = $(".middle .active").attr("id");
	if("addbankStep1" != idalt){
		 $("#addBankTitleStep2").removeClass();
		 $("#addBankTitleStep3").removeClass();
	     $("#addBankTitleStep1").addClass("active");
	     $("#addbankStep2").css("display","none");
	     $("#addbankStep3").css("display","none");
	     $("#addbankStep1").css("display","");
	     
	     $("#click1").show();
	     $("#imgCode").val("");
	     $("#imgCode").attr("width","50px;");
	     $("#imgCode").show();
	     $("#code").show();
	     $("#refush").show();
	     
         $("#countDown").hide();
         $("#verify").val("");
         $("#verify").css("display","none");
         
         $("#idCard").val("");
         $("#addbankStep1").css("display","");
	}
	
    $.ajax({
        type: "post",
        url: "/member/withdrawnAddBank1.do",
        dataType: "json",
        data:{},
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function(data){
            if(data['flagRe'] == 'phone'){
                showMsg("绑定银行卡前，请先绑定手机号！");
            }else if(data['flagRe'] == 'iden'){
                showMsg("绑定银行卡前，请先实名认证！");
            }else if(data['flagRe'] == 'pass'){
                var showPlus=$('.plusBank');
                var showBamlBg=$('.plusBankBg');
                $("#addBankPhone").html(data['memberPhone']);
                $("#addBankPhoneHidden").attr("value",data['phone']);
                $("#realName").html(data['realName']);
                if(type=="1"){
                	$("#addUpBank").html("完善银行卡信息");
                	var idalt = $(".middle .active").attr("id");
                    $("#nextBtn").attr("onclick","next('1')");
                }else{
                	$("#addUpBank").html("添加银行卡");
                    $("#nextBtn").attr("onclick","next('0')");
                }
                
                showBamlBg.slideDown();
                showPlus.show();
                //刷验证码
                refush();
            }

        }
    });
}*/

//新版 添加银行卡信息
function addBank(type){
	 $.ajax({
	        type: "post",
	        url: "/member/withdrawnAddBank1.do",
	        dataType: "json",
	        data:{},
	        async: false,
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        success: function(data){
	            if(data['flagRe'] == 'phone'){
	                showMsg("绑定银行卡前，请先绑定手机号！");
	            }else if(data['flagRe'] == 'iden'){
	                showMsg("绑定银行卡前，请先实名认证！");
	            }else if(data['flagRe'] == 'pass'){
	            	$("#addBankPhone").val(data['memberPhone']);
	                //$("#addBankPhoneHidden").val(data['phone']);
	                $("#realName").val(data['realName']);
	                if(type=="1"){
	                	$("#addUpBank").html("完善银行卡信息");
	                    $("#nextBtn").click(function(){next('1');});	                	
	                }else{
	                	$("#addUpBank").html("添加银行卡");
	                	$("#nextBtn").click(function(){next('0');});
	                }
	            	var attr = new Attention( popUp , '#addBankcard-pop-tx' ,  true );
	        	    attr.event();
	                //刷验证码
	                refush();
	            }

	        }
	    });
}


/*第1步表单验证  start*/
var verifyFlag = false;
var idCardFlag = false;
/*第1步表单验证  end*/
/*第2步表单验证  start*/
var mbbOpeningBranchFlag = false;
var mbbCardNoFlag = false;
/*第2步表单验证  end*/
$(document).ready(function(){
    /*第1步表单验证  start*/
    $("#verify").blur(function(){
        var verify = $("#verify").val();
        var reg = /^[A-Za-z0-9]{6}$/;
        if(verify==""){
            verifyFlag = false;
            $('#verifyMSG').css("display","");
            $("#verifyMSG").removeClass().addClass("prompt_1 error_1 lin_20").html("<i></i>请输入验证码！");
        }else if(!reg.test(verify)){
            verifyFlag = false;
            $('#verifyMSG').css("display","");
            $("#verifyMSG").removeClass().addClass("prompt_1 error_1 lin_20").html("<i></i>验证码有误！");
        }else{
            verifyFlag = true;
            $('#verifyMSG').css("display","none");
            $("#verifyMSG").html("");
        }
    });

    $("#idCard").blur(function(){
        var idCard = $("#idCard").val();
        var reg = /^(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
        if(idCard==""){
            idCardFlag = false;
            $("#idCard").addClass("redBord");//添加样式
            $('#idCardMSG').css("display","block");
            $("#idCardMSG").html("<i></i>请输入您实名认证的身份证号码！");
        }/*港澳台外籍无格式验证else if(!reg.test(idCard)){
            idCardFlag = false;
            $('#idCardMSG').css("display","");
            $("#idCardMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>身份证号码有误！");
        }*/else{
        	$("#idCard").removeClass("redBord");//移除样式
            $.ajax({
                type: "post",
                url: "/member/compareIdCard.do",
                dataType: "json",
                data:{
                    idCard:idCard
                },
                async: false,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function(msg){
                    if(msg == 1){//相等
                        idCardFlag = true;
                        $('#idCardMSG').css("display","none");
                        $("#idCardMSG").removeClass().html("");
                    }else if(msg == 0){
                        idCardFlag = false;
                        $("#idCardMSG").html("<i></i>身份证号码有误！");
                        $('#idCardMSG').css("display","block");
                        
                    }
                }
            });
        }
    });

    /**发送语音短信验证码  start*/
    $('#yybtn').click(function () {
        $("#idCard").blur();
        //if(idCardFlag == true){
            $.post("/msg/bankSendYuyinMessage.do?interval=60", {phone: $("#addBankPhoneHidden").val()},
                function (result) {
                    if(result=='succ'){
                        $("#yybtn").attr('disabled', true);
                        // 弹框优化 | zhenhua.xi | 20140905
                        // showMsg(yyMsg);
                        $('#verifyMSG').css("display","");
                        $('#verifyMSG').addClass("prompt_1 remind_1").html("<i></i>你我贷已通过400-7910-888播报，请收听");
                        yuyinWaiting(120);
                    }else{
                        $("#yybtn").attr('disabled', false);
                        showMsg(result);
                    }
                }
            );
        //}
    });
    $('#yybtn2').click(function () {
        $("#idCard").blur();
        //if(idCardFlag == true){
            $.post("/msg/bankSendYuyinMessage.do?interval=120", {phone: $("#addBankPhoneHidden").val()},
                function (result) {
                    if(result=='succ'){
                        $("#yybtn2").attr('disabled', true);
                        // 弹框优化 | zhenhua.xi | 20140905
                        // showMsg(yyMsg);
                        $('#verifyMSG').css("display","");
                        $('#verifyMSG').addClass("prompt_1 remind_1").html("<i></i>你我贷已通过400-7910-888播报，请收听");
                        yuyinWaiting2(120);
                    }else{
                        $("#yybtn2").attr('disabled', false);
                        showMsg(result);
                    }
                }
            );
        //}
    });
    /**发送语音短信验证码  end*/
    /*第1步表单验证  end*/
    /*第2步表单验证  start*/
    //显示省list
    $.ajax({
        type : "POST",
        url : "/member/provList.do",
        data : {d:"d"},
        success : function(result) {
            data = result;
            $("#bankProvCode").append("<option value='-1' selected='selected'>请选择</option>");
            $("#bankProvCode1").append("<option value='-1' selected='selected'>请选择</option>");
            $(data).each(function(index,domEle){
                var option = "<option value='"+domEle.provCode+"'>"+domEle.provName+"</option>";
                $("#bankProvCode").append(option);
                $("#bankProvCode option").click(function () {
                    $(this).html($(this).html());
                });
                
                $("#bankProvCode1").append(option);
                $("#bankProvCode1 option").click(function () {
                    $(this).html($(this).html());
                });
            });
        }
    });

    //显示银行卡list
    $.ajax({
        type : "POST",
        url : "/member/bankNameList.do",
        data : {d:"d"},
        success : function(result) {
            data = result;
            $("#bankName").append("<option value='-1' selected='selected'>请选择</option>");
            $(data).each(function(index,domEle){
                var option = "<option value='"+domEle.bankid+"'>"+domEle.bankname+"</option>";
                $("#bankName").append(option);
                $("#bankName option").click(function () {
                    $(this).html($(this).html());
                });
            });
        }
    });

  $("#bankName").change(function(){$("#mbbCardNo").trigger("blur")});
    
    $("#mbbCardNo").blur(function(){
    	var mbbName = $('#bankName').val();
        var mbbCardNo = $("#mbbCardNo").val();
        if(mbbName == '18'){//招商银行不验证
			if(mbbCardNo==""){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入银行卡号！");
			}else{
				mbbCardNoFlag = true;
    			$('#mbbCardNoMSG').css("display","");
    			$("#mbbCardNoMSG").removeClass().html("");
			}
		}else if(mbbName == '19' && mbbCardNo.length == 17){//交行银行17位卡号不验证
			if(mbbCardNo==""){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入银行卡号！");
			}else{
				mbbCardNoFlag = true;
    			$('#mbbCardNoMSG').css("display","");
    			$("#mbbCardNoMSG").removeClass().html("");
			}
		}else{
			var reg = /^[0-9]{16,19}$/;
			if(mbbCardNo==""){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入银行卡号！");
			}else if(!reg.test(mbbCardNo)){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>银行卡号格式有误！");
			}else{
            $.ajax({
                type: "post",
                url: "/member/compareCardNo.do",
                dataType: "text",
                data:{
                    mbbCardNo:mbbCardNo
                },
                async: false,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function(msg){
                    if(msg == 0){//不存在
                        mbbCardNoFlag = true;
                        $('#mbbCardNoMSG').css("display","");
                        $("#mbbCardNoMSG").removeClass().html("");
                    }else if(msg == 1){//内部已绑定
                    	var onclickId =$("#save").attr("onclick");
                    	if("save1('1')" ==onclickId){
                    		 mbbCardNoFlag = true;
                    	}else{
	                        mbbCardNoFlag = false;
	                        $('#mbbCardNoMSG').css("display","");
	                        $("#mbbCardNoMSG").html("<i></i>您已成功添加该张银行卡号，不能重复添加！");
                    	}
                    }else if(msg == 2){//银行卡格式有误
                        mbbCardNoFlag = false;
                        $('#mbbCardNoMSG').css("display","");
                        $("#mbbCardNoMSG").html("<i></i>银行卡号格式有误！");
                    }else if(msg == 3){//银行卡不支持
                    	mbbCardNoFlag = false;
                        $('#mbbCardNoMSG').css("display","");
                        $("#mbbCardNoMSG").html("<i></i>你我贷暂不支持该银行卡！");
                    }
                }
            });
			}
        }
        
        getBankNo(mbbCardNo);
    });

    $("#mbbOpeningBranch").blur(function(){
        var mbbOpeningBranch = $("#mbbOpeningBranch").val();
        var reg = /^[\u4e00-\u9fa5]{1,200}$/;
        if($.trim(mbbOpeningBranch)==""){
            mbbOpeningBranchFlag = false;
            $('#mbbOpeningBranchMSG').css("display","");
            $("#mbbOpeningBranchMSG").html("<i></i>请输入开户支行！");
        }else if(!reg.test(mbbOpeningBranch)){
            mbbOpeningBranchFlag = false;
            $('#mbbOpeningBranchMSG').css("display","");
            $("#mbbOpeningBranchMSG").html("<i></i>请正确填写开户支行！");
        }else{
            mbbOpeningBranchFlag = true;
            $('#mbbOpeningBranchMSG').css("display","");
            $("#mbbOpeningBranchMSG").html("");
        }
    });
    /*第2步表单验证  end*/
});

/**第一步  next   start*/
function next(type){
    $("#verify").blur();
    $("#idCard").blur();
    var captcha = false;//图形验证码
    var imgCode=$("#imgCode").val();
    if(imgCode == null || imgCode ==""){
    	$('#verifyMSG').css("display","");
        $("#verifyMSG").removeClass().addClass("prompt_1 error_1 lin_20").html("<i></i>请输入验证码!");
    }else{
    	 $.ajax({
    	        type: "post",
    	        url: "/member/compareVerifyCode1.do",
    	        dataType: "json",
    	        data:{
    	        	imgCode:imgCode
    	        },
    	        async: false,
    	        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	        success: function(msg){
    	            if(msg == 1){//相等
    	            	$('#verifyMSG').css("display","none");
    	            	captcha = true;
    	            }else if(msg == 0){
    	                $('#verifyMSG').css("display","block");
    	                $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
    	                return;
    	            }else if(msg == 2){
    	                $('#verifyMSG').css("display","block");
    	                $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码超时，请点击重新发送!");
    	                return;
    	            }else if(msg == 3){
    	                $('#verifyMSG').css("display","block");
    	                $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入验证码!");
    	                return;
    	            }
    	        },
    	        error:function (XMLHttpRequest, textStatus, errorThrown) {
    	            alert("error");
    	            alert(textStatus);
    	            return;
    	        }
    	    });
    	 if(verifyFlag==true && idCardFlag==true && captcha == true){
    	        $.ajax({
    	            type: "post",
    	            url: "/member/verificationCode1.do?type=bank",
    	            dataType: "json",
    	            data:{
    	                verify:$("#verify").val(),
    	                //phone:$("#addBankPhoneHidden").val(),
    	                imgCode:imgCode
    	            },
    	            async: false,
    	            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	            success: function(msg){
    	                if(msg == 1){//相等
    	                    //跳转第二步
    	                	$(".step_sfyz").hide();//tab切换
    	    				$('.step_add').show();
    	    				
    	    				$(".addBankTab h1 span:eq(0)").removeClass();//标题切换
    	    				$(".addBankTab h1 span:eq(1)").addClass("active-title");
    	    			
    	                   if(type=="1"){
    	                    	$("#mbbCardNo").val($("input[name='bankRadio']:checked").attr("useCardNo"));
    	                    	$("#bankName").val($("input[name='bankRadio']:checked").attr("useBankName"));
    	                    	$("#save").attr("onclick","save1('1')");
    	                    }else{
    	                    	$("#mbbCardNo").val("");
    	                    	$("#bankName").val("-1");
    	                    	$("#save").attr("onclick","save1('0')");
    	                    }
    	                    
    	                }else if(msg == 0){
    	                    $('#verifyMSG').css("display","");
    	                    $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
    	                }else if(msg == -1){
    	                    $('#verifyMSG').css("display","");
    	                    $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码超时，请点击重新发送!");
    	                }else if(msg == -2){
    	                    $('#verifyMSG').css("display","");
    	                    $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>图形码有误!");
    	                }else if(msg == 2){
    	                    $('#verifyMSG').css("display","");
    	                    $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>图形验证码超时，请点击重新发送!");
    	                } else if(msg == 3){
    	                    $('#verifyMSG').css("display","");
    	                    $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入图形验证码!");
    	                }
    	            },
    	            error:function (XMLHttpRequest, textStatus, errorThrown) {
    	                alert("error");
    	                alert(textStatus);
    	            }
    	        });
    	    }
    }
   
}
/**第一步  next   end*/

/**第2步   save    start*/
function save1(type){
    $("#mbbOpeningBranch").blur();
    $("#mbbCardNo").blur();
    var mbbName = $("#bankName").find("option:selected").val();
    if(mbbName=="-1"){
        $('#mbbNameMSG').css("display","");
        $("#mbbNameMSG").html("<i></i>请选择银行名称！");
    }else{
        $('#mbbNameMSG').css("display","");
        $("#mbbNameMSG").removeClass().html("");
    }
    var prov = $("#bankProvCode").find("option:selected").val();
    if(prov=="-1"){
        $('#provMSG').css("display","");
        $("#provMSG").html("<i></i>请选择开户省市（省）！");
    }else{
        $('#provMSG').css("display","none");
        $("#provMSG").removeClass().html("").addClass("prompt_1 error_1 lin_20");
    }

    var cityName = $("#bankCityCode").find("option:selected").val();
    if(cityName=="-1"){
        $('#cityNameMSG').css("display","");
        $("#cityNameMSG").html("<i></i>请选择开户省市（市）！");
    }else{
        $('#cityNameMSG').css("display","none");
        $("#cityNameMSG").html("");
    }
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
   
    if(mbbOpeningBranchFlag==true && mbbCardNoFlag==true && (mbbName != "-1")&&(prov != "-1")
        &&(cityName != "-1")){
    	var mbbId = $("input[name='bankRadio']:checked").val();
    	var doWhat = "add";
    	if(type=="1"){
    		doWhat = "edit";
    	}
        $.ajax({
            type: "post",
            url: "/member/saveBindingBank.do",
            dataType: "text",
            data:{
                mbbName:mbbName,
                mbbCardNo:$("#mbbCardNo").val(),
                mbbOpeningBranch:$("#mbbOpeningBranch").val(),
                cityName : cityName,
                bankName : $("#bankName").find("option:selected").text(),
                doWhat : doWhat,
                mbbId : mbbId,
                stok:stok
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function(msg){
                if(msg == '1'){//保存成功
                	// Adobe | zhenhua.xi | 20141031
                	var s = s_gi(s_account);
                	s.linkTrackVars = "events";
                	s.linkTrackEvents = "event13";
                	s.events = "event13"; //绑定银行卡成功
                	npo.tl(this,'o','bdyhkcg');
                	location.reload();//重新加载当前页面
                    //跳转第3步
                 /*   $("#addBankTitleStep2").removeClass().addClass("visited_a");//去除第2步进行中亮，加上完成亮度
                    $("#addBankTitleStep3").addClass("active");
                    $("#addbankStep2").css("display","none");//第2步隐藏
                    $("#suc").css("display","");
                    $("#addbankStep3").css("display","");//第3步显示
*/
                	
                	//$("#close").attr('href','/member/carry.html');
                    
//        			closeDialogs();
//        			var _url = '../ftl/member/setBank3Succ.html';
//        			art.dialog.load(_url,{lock:true,close:function(){
//        			    window.location.href='/member/safetyLevel.do?doWhat=lc'}}, false).title('添加银行卡');
//        			return false;
                }else if(msg=='0'){//保存失败
                    //跳转第3步
                	$("#errorMsg").html('添加失败');
                  /*  $("#addBankTitleStep2").removeClass().addClass("visited_a");//去除第2步进行中亮，加上完成亮度
                    $("#addBankTitleStep3").addClass("active");
                    $("#addbankStep2").css("display","none");//第2步隐藏
                    $("#fai").css("display","");
                    $("#addbankStep3").css("display","");//第3步显示
*///        			closeDialogs();
//        			var _url = '../ftl/member/setBank3Fai.html';
//        			art.dialog.load(_url,{lock:true,close:function(){
//        			    window.location.href='/member/safetyLevel.do?doWhat=lc'}}, false).title('添加银行卡');
//        			return false;
                }else if(msg == '2'){//修改成功
                	//跳转第3步
                    $("#addBankTitleStep2").removeClass().addClass("visited_a");//去除第2步进行中亮，加上完成亮度
                    $("#addBankTitleStep3").addClass("active");
                    $("#addbankStep2").css("display","none");//第2步隐藏
                    $("#suc").css("display","");
                    $("#addbankStep3").css("display","");//第3步显示
                }
//                else if(msg == -2){//修改失败
//                	$('#mbbCardNoMSG').css("display","");
//    				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>修改失败,您的部分资金需要使用该银行卡才能提现，不能修改卡号。");
//        		}else if(msg == -3){//修改失败
//                	$('#mbbCardNoMSG').css("display","");
//    				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>修改失败,您的部分资金正在使用该银行卡提现中，不能修改。");
//        		}
        		else if(msg=='5'){//验证银行卡无效  10月16  addby:sunyang
                	$('#mbbCardNoMSG').css("display","");
    				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>银行卡号无效！");
                }else if(msg==-10){
                	$("#errorMsg").html("验证身份失败，请重新验证！");//安全bug zhuzy bug#10158
                }else{
                	$("#errorMsg").html(msg);
                }
            }
        });
    }
}
/**第2步   save    end*/

/**第3步  start*/
function saveSure(){
    window.location  = "/member/carry.html";
}
/**第3步  end*/

//关闭添加框
function closeSure(){
	$('.plusBank').html(openDiv);
	closeAll_0();
}

/*function closeOpenWid(){
	var addbankStep3Val =$("#addbankStep3") .css("display");
	if(addbankStep3Val!="none"){
		//window.location  = "/member/carry.html";
		location.reload();//重新加载当前页面
	}
}*/

//根据省得到市
//参数：省名称，城市名称
function findCitys(provName,cityName){
    $("#"+cityName+" option:gt(0)").remove();
    var bankProvCode = $("#"+provName).find("option:selected").val();//得到选中的省
    $.ajax({
        type : "POST",
        url : "/member/getProvSelectInterface.do",
        data : "provCode=" + bankProvCode,
        success : function(result) {
            data = result;
            //删除ul中全部的li，初始化显示数据，后台保存input
            $("#"+cityName+" option").remove();
//			$("#cityCode").html("请选择");
//			$("#cityName").attr("value","-1");

            $("#"+cityName).append("<option value='-1' selected='selected'>请选择</option>"); //追加第一个option，默认为请选择
            //遍历json的数据
            $(data).each(function(index,domEle){
                var option = "<option value='"+domEle.cityCode+"'>"+domEle.cityName+"</option>";
                $("#"+cityName).append(option);
                $("#"+cityName+" option").click(function () {
                    $(this).html($(this).html());
                });
            });
        }
    });
}

//发送验证码
function gain(){
	var imgCode = $("#imgCode").val();
	if(imgCode == ''){
		$('#verifyMSG').css("display","");
		$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>图形验证码不能为空！");
		return;
	}
	/**
	 * 提交时没有token。通过访问URL直接提交Bug修复
	 */
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
 
    $.ajax({
        type: "post",
        //url: "/member/sendMsg1.do",
        url: "/member/sendMsg2.do",//新copy的一个方法，里面的验证码验完没有销毁，方便下次验证
        dataType: "json",
        data:{
            type:"bank",
            imgCode:imgCode,
            stok:stok
        },
        async: false,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function(msg){
            if(msg == 1){
                $("#click1").hide();
                $("#countDown").show();
                countDown(120);//倒计时120秒
            	$("#verifyMSG").removeClass().html(""); 
            	
            	/**切换验证码输入框 st*/ 
            	//$("#imgCode").attr("style","display:none;"); 
            	//$("#code").attr("style","display:none;"); 
            	//$("#refush").attr("style","display:none;"); 
            //	$("#verify").attr("style","display:'';"); 
            	/**切换验证码输入框 ed*/ 
            	
                // 弹框优化 | zhenhua.xi | 20140905
                // showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
                $('#verifyMSG').css("display","");
                $('#verifyMSG').addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
            }else if(msg==0){
                $('#verifyMSG').css("display","");
                $("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，请点击！");
            }else if(msg==2){
    			$('#verifyMSG').css("display","");
				$("#verifyMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，验证码错误，请再次请点击！");
				refush();
    		}else{
				$('#smsMSG').css("display","");
				$("#smsMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>"+msg);
			}
        }
    });
}
//发送验证码2
/*function gain2(){
	*//**
	 * 提交时没有token。通过访问URL直接提交Bug修复
	 *//*
	var stok = "";
	if(document.getElementById ("stok")){
		stok  = document.getElementById ("stok").value;
	}
	$.ajax({
		type: "post",
		//url: "/member/sendMsg1.do",
		url: "/member/sendMsg2.do",
		dataType: "json",
		data:{
			type:"withdraw",
			stok:stok
		},
		async: false,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success: function(msg){
			if(msg == 1){
				countDown2(60);//倒计时60秒
				$('#smsMSG').css("display","");
                $('#smsMSG').removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
			}else if(msg==0){
				$('#smsMSG').css("display","");
				$("#smsMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，请点击！");
			}else{
				$('#smsMSG').css("display","");
				$("#smsMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>"+msg);
			}
		}
	});
}*/
function gain2(){
	/*var flag = mobileCh();
    if (flag == true) {*/
	$.post("/msg/withdrawnSendTextMessage.do", {"imgCode" : $("#imgCode").val()}, function(msg) {
		msg=$.parseJSON(msg);
		if (msg.errorCode == 0) { 
			countDown2(60);//倒计时60秒
			$('#smsMSG').css("display","");
            $('#smsMSG').removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");
		} else {
			$('#smsMSG').css("display","");
			$("#smsMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>"+msg.errorMessage);
			// 验证码刷新
			//$(".gt_refresh_tips").click();
		}
	});
}
function countDown2(time){
	$("#smsClick").val(time);
	$("#smsClick").attr("disabled",true);
	time = time - 1;
	if(time>=0){
		setTimeout("countDown2("+time+")",1000);
	}else{
		$("#smsClick").removeAttr("disabled");
		$("#smsClick").val("点击获取");
	}
}
function countDown(time){
	$("#countDown").attr("value",time+"秒");
	time = time - 1;
	if(time>=0){
		setTimeout("countDown("+time+")",1000);
	}else{
		$("#countDown").hide();
		$("#yybtn").html("语音验证码");
		$("#yybtn").attr('disabled',false);
		$("#yybtn").show();
	}
}

function yuyinWaiting(i) {
    if (i > 1) {
        i--;
        $("#yybtn").html(i + "秒");
        window.setTimeout(function () {
            yuyinWaiting(i);
        }, 1000);
    } else {
        $("#yybtn").hide();
        $("#yybtn2").show();
    }
}
function yuyinWaiting2(i) {
    if (i > 1) {
        i--;
        $("#yybtn2").html(i + "秒");
        window.setTimeout(function () {
            yuyinWaiting2(i);
        }, 1000);
    } else {
        $("#yybtn2").html("语音验证码");
        $("#yybtn2").attr('disabled',false);
    }
}


/**添加银行卡  end*/


//使用提现券联动
function changPoundage(){
    $("#amount").blur();
}

//换一张图片验证码
function refush(){
	var d = new Date();
	var src = "/validatecode/refreshforRepeatedly.htm?date=" + d.getTime();
	$("#code").attr("src",src);
}

function bindCard(input,mbbId){
	if(input==1){//匹配卡号
		$("#"+mbbId+"bank").click();
		$("#amount").blur();
	}else if(input==2){//弹出绑定框
		addBank('0');
	}
}

//共通函数干扰
function setNull(cardNo,mbbid){
	$("#"+mbbid+"bank").attr('checked',true);
    var amount1 = $("#amount").val();
    var amount = amount1.replace(/(^\s*)|(\s*$)/g,'');
    var amountShiJi = $("#memberAmount5").val();//可提现金额
    //使用提现券 St
    //获取是否使用提现券
    var ckPutBond = $("#ckPutBond").attr('checked');
    //使用提现券 End
    
    dlSxfTip();

    if(($('#drawnFlag').val()!=null || $('#drawnFlag').val()!="") && $('#drawnFlag').val()=="0"){
        $('#errorOpt').hide();
        $('#errorOpt').fadeIn("slow");
        return;
    }
    if($("input[name='bankRadio']:checked").val()==undefined||$("input[name='bankRadio']:checked").val()==""){
        $("#mbbCardNoMSG4out").html("<i></i>请输选择银行！");
        $('#mbbCardNoMSG4out').hide();
        $('#mbbCardNoMSG4out').fadeIn("slow");
        return;
    }
    if(amount==""){
        amountFlag = false;
        $("#amountMSG").html("<i></i>请输入提现金额");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return;
    }
    if(!amount.match(/^(?:0\.\d{1,2}|(?!0)\d+(?:\.\d{1,2})?)$/)){
        amountFlag = false;
        $("#amountMSG").html("<i></i>请输入正确的金额(如果有小数位最多2位)");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return;
    }
    if(amount <= 0){
        amountFlag = false;
        $("#amountMSG").html("<i></i>提现金额必须大于0元");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return;
    }
     if(amount <= 2){
        amountFlag = false;
        $("#amountMSG").html("<i></i>提现金额至少2元以上");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return;
    }
    if(parseFloat(amountShiJi) < parseFloat(amount)){
        amountFlag = false;
        $("#amountMSG").html("<i></i>账户余额不足");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return;
    }
    if(parseFloat(amount)*100 > 1000000*100){
        amountFlag = false;
        $("#amountMSG").html("<i></i>单笔提现最高不可超过100万");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        return;
   }
        
        
    //使用提现券 St
    if(ckPutBond == 'checked' && amount > 200000 && 1==2){/* 1==2 ？ 之所以这样是为因为代码需求变更,这样改下让该分支永远不执行,省的删除了这段代码后续需求再换过来又要大改*/
        amountFlag = false;
        $("#amountMSG").html("<i></i>使用提现券提现金额不得高于20万");
        $('#amountMSG').hide();
        $('#amountMSG').fadeIn("slow");
        //使用提现券 End
    }else{
        //使用提现券 St
        var putBond = 0;
        if(ckPutBond == 'checked'){
            putBond = 1;
        }
        //使用提现券 End
        if(parseFloat(amountShiJi) < parseFloat(amount)){//可提现金额<填写金额
            amountFlag = false;
            $("#amountMSG").html("<i></i>账户余额不足");
            $('#amountMSG').hide();
            $('#amountMSG').fadeIn("slow");
        }else{
            $.ajax({
                type: "post",
                url: "/member/amountCompare.do",
                dataType: "json",
                data:{
                    amount:amount,
                    //使用提现券 St
                    putBond:putBond,
                    //使用提现券 End
                    cardNo:cardNo,
                    //提现类型 0：普通   1：快捷
                    carryType:$('input[name="carrytype"]:checked').val()
                },
                async: false,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                success: function(date){
                    if(date['msg']=="0"){
                        amountFlag = false;
                        $('#amountMSG').css("display","");
                        $('#msg').css("display","none");
                        $("#amountMSG").html("<i></i>可用额不足");
                    }else if(date['msg']=="-1"){
                        amountFlag = false;
                        $('#amountMSG').css("display","");
                        $('#msg').css("display","none");
                        $("#amountMSG").html("<i></i>提现金额必须大于0元");
                        $('#realityAmount').html(0);
                    }else if(date['msg']=="-4"){
                            amountFlag = false;
                            $('#amountMSG').css("display","");
                            $('#msg').css("display","none");
                            $("#amountMSG").html("<i></i>单笔提现最高不可超过100万");
                            $('#realityAmount').html(0);
                    }else if(date['msg']=="-2"){
                		var showMsg = "";
                		var outAmount1 = date['rstAmount'];
                		if(outAmount1< 0 ){
                			outAmount = 0;
                		}
                		var outAmount = outAmount1.toFixed(2);
                		if(date['rstCase']=="1"){
                			showMsg = "<i></i>当前银行卡的提现金额不能超过"+outAmount+"元，请使用<a style='color:blue;' href='javascript:void(0)' onclick='bindCard(1,"+date['rstMbbId']+");'>尾号为"+date['rstCard']+"</a>的银行卡提现，详询400-7910-888";
                		}else if(date['rstCase']=="2"){
                			showMsg = "<i></i>当前银行卡的提现金额不能超过"+outAmount+"元，请再绑定<a style='color:blue;' href='javascript:void(0)' onclick='bindCard(2,-1);'>尾号为"+date['rstCard']+"的银行卡</a>后再使用新卡提现，详询400-7910-888";
                		}else if(date['rstCase']=="4"){
                			showMsg = "<i></i>当前银行卡提现金额不能超过"+outAmount+"元，详询400-7910-888";
                		}
                        amountFlag = false;
                        msgFlag = true;
                        $('#amountMSG').css("display","");
                        $('#msg').css("display","none");
                        $("#amountMSG").html(showMsg);
                        $('#realityAmount').html(0);
                    }else if(date['msg']=="-3"){
                    	addBank('1');
                    }else{
                        amountFlag = true;
                        $('#amountMSG').css("display","none");
                        $("#amountMSG").html("");
                        $('#msg').css("display","");
                        /*使用提现券 注释
                         $('#realityAmount').html(date['realityAmount']);
                         $('#poundage1').html(date['poundage']);
                         $('#poundage2').html(date['poundage']);
                         */
                        //使用提现券 St
                        if(ckPutBond == 'checked'){
                            $('#realityAmount').html(amount);
                            $('#poundage1').html("0.00");
                            $('#poundage2').html("0.00");
                        }else{
                            $('#realityAmount').html(date['realityAmount']);
                            $('#poundage1').html(date['poundage']);
                            $('#poundage2').html(date['poundage']);
                        }
                        //使用提现券 End
                    }
                }
            });
        }

    }
}
//选择银行卡动态效果
function selectBank(cardNo,mbbid,imgUrl,citycode,branch,withdrawalAmount,z_index){
	$("#"+mbbid+"bank").attr('checked',true);
	
	 var imgstr=$("#newbank").attr("src");
	 var imgstr1 ="";
	 if(imgstr!=""){
		 imgstr1 = imgstr.substring(0,(imgstr.indexOf("manage/")+7));
	 }
	 $("#newbank").attr("src",imgstr1+imgUrl);
	 var cardHiddenNum="***"+cardNo.substring((cardNo.length)-4,(cardNo.length));
	 $("#newbankCardNum").text(cardHiddenNum);
	 //设置两个隐藏表单的值
	 $("#bankCtiyId").val(citycode);//选中银行卡的城市信息
	 $("#bankBranch").val(branch);//选中银行卡的支行信息
	
	 if(citycode==null || citycode==""){
		 $("#disCardNoCityInfo").css("display","block");
	 }else{
		 $("#disCardNoCityInfo").css("display","none"); 
	 }
	 
	 if(branch==null || branch==""){
		 $("#disCardNoBranchInfo").css("display","block");
	 }else{
		 $("#disCardNoBranchInfo").css("display","none");
	 }
	 
	 $("#cardList li:eq("+z_index+")").addClass("curronli").siblings().removeClass("curronli");//添加选中样式
	 
	 //动态改变当前银行卡的额度显示
	 $("#memberAmount5").val((withdrawalAmount*0.001).toFixed(2));
	 $("#amountShiJi").html((withdrawalAmount*0.001).toFixed(2));

	//看用户是否有借款
    if(($('#drawnFlag').val()!=null || $('#drawnFlag').val()!="") && $('#drawnFlag').val()=="0"){
        $('#errorOpt').hide();
        $('#errorOpt').fadeIn("slow");
        return;
    }
    
    
    
}

//根据卡号取得银行卡编号、
function getBankNo(cardNum){
	$.ajax({
		type : "POST", 
		url : "/pay/yintong/getBankCode.do", 
		data : "banCardNum=" + cardNum, 
		success : function(result) {
			$("#bankName").val(result);
		}
	}); 
}

//zhuzy TASK#2379
function showTips(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('#tips');
	plusBankBg.show();
	showForm1.slideDown();
	if($('.inputFocus1').size()>0){
		$('.inputFocus1').focus();
	}
}
//zhuzy TASK#2379
function closeTips(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('#tips');
	plusBankBg.hide();
	showForm1.slideUp();
}

//银行卡管理 修改银行卡--加载银行卡信息
function altBankInfo(id,logourl,realname,cardno,citycode,branch,bankName,bankId,mbbCardNo){
	var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
	$("#bankId").val(id);
	
	var imgstr=$("#loginPic").attr("src");
	var imgstr1 ="";
	if(imgstr!=""){
		 imgstr1 = imgstr.substring(0,(imgstr.indexOf("manage/")+7));
	}
	$("#loginPic").attr("src",imgstr1+logourl);
	//$("#cardRealName").val(realname);
	$("#cardNo").val(cardno);
	$("#openingBranch").val(branch);
	$("#updBankName").val(bankName);
	$("#bankNum").val(bankId);//银行卡编号
	
	$("#mbbCardNo").val(mbbCardNo);
	
	$('#provMSG2').html("");
	$('#cityNameMSG2').css("display","none");
	$('#cityNameMSG2').html(""); 
	$('#openingBranchMSG1').css("display","none");
	$('#openingBranchMSG1').html(""); 
	
	$.ajax({
        	type: "post",
       	 	url: "/member/addBankyStep.do",
        	dataType: "json",
        	data:{
        		doWhat:"edit",
        		mbbId:id,
        		flag:"flag",
                stok:stok
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(result){
        		var provListData = result['provList'];
        		$("#bankProvCode2").append("<option value='-1' selected='selected'>请选择</option>");
        		$("#cardRealName").val(result['realName']);//真是姓名
        		//$("#cardPhone").val(result['memberPhone']);//加密的手机号
    			$(provListData).each(function(index,domEle){
    				var option;
    				var pro = 0;
    				if(result['city1']){
    					pro=result['city1']['provCode'];
    				}
    				var proInd=provListData[index].provCode;
    				//选中与库中存的省相等的数据
    				if(pro == proInd){
    					option = "<option value='"+provListData[index].provCode+"' selected>"+provListData[index].provName+"</option>";
    				}else{
    					option = "<option value='"+provListData[index].provCode+"'>"+provListData[index].provName+"</option>";
    				}
    				$("#bankProvCode2").append(option);
    				
    				//得到选中的省去查询省下面包含的市的数据
        			$("#bankCityCode2 option:gt(0)").remove(); 
        			var bankProvCode = $("#bankProvCode2").val(); 
        			$.ajax({
        				type : "POST", 
        				url : "/member/getProvSelectInterface.do", 
        				data : "provCode=" + bankProvCode, 
        				success : function(res) { 
        					dataqw = res;
        					//删除ul中全部的li，初始化显示数据，后台保存input
        					$("#bankCityCode2 option").remove(); 
        					$("#cityCode").html("请选择");
        					$("#cityName").attr("value","-1");
        					//遍历json的数据
        					$(dataqw).each(function(index,domEle){ 
        						var option;
                				var pro=result['city1']['cityCode'];
                				var proInd=dataqw[index].cityCode;
                				//选中与库中存的市相等的数据
                				if(pro == proInd){
                					option = "<option value='"+dataqw[index].cityCode+"' selected>"+dataqw[index].cityName+"</option>";
                				}else{
                					option = "<option value='"+dataqw[index].cityCode+"'>"+dataqw[index].cityName+"</option>";
                				}
                				$("#bankCityCode2").append(option); 
        					});
        				} 
        			});
    			});
        	}
        });
	var attr = new Attention( popUp , '#altBank-pop-manage' ,  true );
    attr.event();
}


//修改银行卡--确认修改
function updateBankInfo(){
	var cardId=$("#bankId").val();
	/*var cardPhone=$("#cardPhone").val();
	if(cardPhone==null || cardPhone==""){
        $('#cardPhoneMSG').css("display","");
        $("#cardPhoneMSG").html("<i></i>请填写银行预留手机号！");
        return ;
    }else{
        $('#cardPhoneMSG').css("display","");
        $("#cardPhoneMSG").removeClass().html("");
    }*/
    var prov = $("#bankProvCode2").find("option:selected").val();
    if(prov=="-1"){
        $('#provMSG2').css("display","");
        $("#provMSG2").html("<i></i>请选择开户省市（省）！");
        return ;
    }else{
        $('#provMSG2').css("display","none");
        $("#provMSG2").removeClass().html("");
    }
    var cityName = $("#bankCityCode2").find("option:selected").val();
    if(cityName=="-1"){
        $('#cityNameMSG2').css("display","");
        $("#cityNameMSG2").html("<i></i>请选择开户省市（市）！");
        return ;
    }else{
        $('#cityNameMSG2').css("display","none");
        $("#cityNameMSG2").html("");
    }
    var openingBranch=$("#openingBranch").val();//支行
    if(openingBranch==null || openingBranch==""){
        $('#openingBranchMSG1').css("display","");
        $("#openingBranchMSG1").html("<i></i>请选择支行信息！");
        return ;
    }else{
        $('#openingBranchMSG1').css("display","none");
        $("#openingBranchMSG1").html("");
    }
    
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
   // if(mbbOpeningBranchFlag==true && mbbCardNoFlag==true && (mbbName != "-1")&&(prov != "-1")&&(cityName != "-1")){
    	var mbbId = $("#bankId").val();
    	
        $.ajax({
            type: "post",
            url: "/member/saveBindingBank.do",
            dataType: "text",
            data:{
            	realName:$("#cardRealName").val(),
                mbbName:$("#bankNum").val(),//银行卡编号,
                mbbCardNo:$("#mbbCardNo").val(),
                mbbOpeningBranch:openingBranch,
                cityName : cityName,
                bankName : $("#updBankName").val(),
                doWhat : "edit",
                mbbId : mbbId,
                stok:stok,
                withdraw:"withdrawAddBankInfo"
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function(msg){
                if(msg == '2'){//保存成功--修改成功
                	location.reload();//重新加载当前页面
                }else if(msg=='3'){//保存失败
                	$('#updError').css("display","");
    				$("#updError").html("<em></em>修改失败");
                }
//                else if(msg == -2){//修改失败
//                	$('#updError').css("display","");
//    				$("#updError").html("<em></em>修改失败,您的部分资金需要使用该银行卡才能提现，不能修改卡号。");
//        		}else if(msg == -3){//修改失败
//                	$('#updError').css("display","");
//    				$("#updError").html("<em></em>修改失败,您的部分资金正在使用该银行卡提现中，不能修改。");
//        		}
        		else if(msg=='5'){//验证银行卡无效 
                	$('#updError').css("display","");
    				$("#updError").html("<em></em>银行卡号无效！");
                }else if(msg==-10){
                	$('#updError').css("display","");
                	$("#updError").html("验证身份失败，请重新验证！");//安全bug zhuzy bug#10158
                }else{
                	$('#updError').css("display","");
    				$("#updError").html(msg);
                }
            }
        });
   // }
}

//删除银行卡
function deleteBackCard(id,cardNo){
	$("#delCardId").val(id);
	$("#delCardNo").text(cardNo);
	
	$.ajax({
    	type: "post",
   	 	url: "/member/delOrUpCan.do",
    	dataType: "json",
    	data:{
    		mbbId:id
    	},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == -2){
    			$("#delMsg").html('您的部分资金需要使用该银行卡才能提现，不能删除该卡。');
    			$("#operationAction").html('<button class="btn btn_size120 btn_blue" onClick="closeBankManager();">确认</button>');
    			showCon_1();
    			return false;
    		}if(msg == -3){
    			$("#delMsg").html('您的部分资金正在使用该银行卡提现中，不能删除该卡。');
    			$("#operationAction").html('<button class="btn btn_size120 btn_blue" onClick="closeBankManager();">确认</button>');
    			showCon_1();
    			return false;
    		}else{
    			var stok = "";
      		    if(document.getElementById ("stok")){
      		        stok  = document.getElementById ("stok").value;
      		    }
      		  $("#delMsg").html("您确定要删除("+cardNo+")此卡吗?");
      		  var html=' <a class="btn btn_36c btn_size120" id="delSure">确认</a><a class="btn btn_bgfff btn_size120 mar_l20 pop-close" onClick="closeBankManager();">取消</a>';
      		  $("#operationAction").html(html);
      		
      		  $("#delSure").click(function(){
				$.ajax({
			    	type: "post",
			   	 	url: "/member/deleteBank.do",
			    	dataType: "json",
			    	data:{
			    		mbbId:id,
		                stok:stok
			    	},
			    	async: false,
			    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			    	success: function(result){
			    		if(result.status == 1){
			    			closeBankManager();
			    			location.reload();//重新加载当前页面
			    		}
//			    		else if(msg == -2){
//			    			$("#delMsg").html('删除失败,您的部分资金需要使用该银行卡才能提现，不能删除该卡。');
//			    			$("#operationAction").html('<button class="btn btn_size120 btn_blue" onClick="closeBankManager();">确认</button>');
//			    			//showCon_1();
//			    		}else if(msg == -3){
//			    			$("#delMsg").html('删除失败,您的部分资金正在使用该银行卡提现中，不能删除该卡。');
//			    			$("#operationAction").html('<button class="btn btn_size120 btn_blue" onClick="closeBankManager();">确认</button>');
//			    			//showCon_1();
//			    		}
			    		else{
			    			$("#delMsg").html(result.msg);
			    			$("#operationAction").html('<button class="btn btn_size120 btn_blue" onClick="closeBankManager();">确认</button>');
			    			//showCon_1();
			    		}
			    	}   
				});
		    });
    		}
    	}
    });
	var attr = new Attention( popUp , '#deleteBan-pop-manage' ,  true );
    attr.event();
}

function closeBankManager(){
	var delDiv=$('#deleteBan-pop-manage');
	var addDiv=$('#addBankcard-pop-tx');
	var altDiv=$('#altBank-pop-manage');
	var windowmask=$(".windowmask");
	delDiv.hide();
	addDiv.hide();
	altDiv.hide();
	windowmask.hide();
}

function dlSxfTip(){
	//快捷提现时，处理手续费后面的  提示
    var carryTypeVal = $('input[name="carrytype"]:checked').val();
    if( carryTypeVal == "1"){
    	$("#sxfTip").hide();
    }else{
    	$("#sxfTip").show();
    }
}
function showMsg(msg){
	$.dialog.alert(msg).title('提示');
	return false;
} 

