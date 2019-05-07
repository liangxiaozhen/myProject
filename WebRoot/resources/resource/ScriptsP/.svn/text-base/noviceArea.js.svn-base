$(document).ready(function(){

    var cur_dh = $('#tz_nwd_7');
    cur_dh.addClass('active');
    
	$("#startdate").datepicker({
 	   changeMonth: true,changeYear: true,closeText:'关闭',
			prevText : '&#x3c;上月',
			nextText : '下月&#x3e;',
			currentText : '今天',
			monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			weekHeader : '周',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : true,
			yearSuffix : '年',
			dateFormat : "yy-mm-dd",
			maxDate: new Date()//最小日期
	   });
    $("#enddate").datepicker({
 	   changeMonth: true,changeYear: true,closeText:'关闭',
			prevText : '&#x3c;上月',
			nextText : '下月&#x3e;',
			currentText : '今天',
			monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			weekHeader : '周',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : true,
			yearSuffix : '年',
			dateFormat : "yy-mm-dd"
			// minDate: new Date($("#startdate").val())//最小日期
    });
    
    $(".nwd_icon_inputtime").change(function() {
   		var regex = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
    	   	var dateValue = $(this).val();
    	   	if (dateValue != '' && !regex.test(dateValue)) {
//    	   		showMsg("日期格式不正确！(正确格式：2012-12-12)");
    	   		$("#msgContent").html("日期格式不正确！(正确格式：2012-12-12)");
				showCon_0();
    	    	$(this).val("");
    	    } else {
    	    	if (compareDate($("#startdate").val(),$("#enddate").val())) {
    	    		// 直接输入开始日期或结束日期时，则为自定义日期查询
    	    		// 更改日期类型, 为自定义日期类型
    	    		$("#dateType").val(3);
    				// 表单提交
    	    		document.forms[0].submit();
    			}
    	    }
   	});
    
    var dateTypeButtons = $(".a_btn .fc_6");
    
		dateTypeButtons.click(function () {
		var dateType = 0;
			if(dateTypeButtons.index(this)==0){
				$("#startdate").val();
				$("#enddate").val();
				dateType = -1;
			} else {
				dateType = dateTypeButtons.index(this);
				dateType = dateType-1;
			}
		// 将数据类型值赋给dateType隐藏域
			$(this).addClass('active');
			$(this).siblings('active').removeClass('active');
			$("#dateType").val(dateType);
	   
		// 表单提交
		document.forms[0].submit();
	});
    $(".tab_u span").click(function(){
    	var id=$(this).attr("id");
    	$("#plan").val(id);
    	$("#dateType").val(-1);
    	// 表单提交
		document.forms[0].submit();
    });
});

function compareDate(checkStartDate, checkEndDate) {      
    var arys1= new Array();      
    var arys2= new Array();      
    if(checkStartDate != null && checkEndDate != null){      
    	arys1=checkStartDate.split('-');      
    	var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
    	arys2=checkEndDate.split('-');      
    	var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);      
    	if(sdate > edate){      
    		$("#errorMsg").text("日期开始时间不能大于结束时间！");        
    		return false;         
    	}else{    
    		return true;      
    	}   
    }      
}

var submitNum = 0;
//写入cookies 
function setCookie(name, value, d) {
	var exp = new Date();
	if (d > 0)
		Days = d;
	exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value)
			+ ";path=/;domain=.niwodai.com;expires=" + exp.toGMTString();
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
$(document).ready(function() {
	$("#pwd").keydown(function(e){ 
	var curKey = e.which; 
	if(curKey == 13){ 
		okAgreement(); 
		return false;
	} 
	});
	
});


/**投资记录*/
function loanBidDtl(fp_id){
	$.ajax({
    	type: "post",
   	 	url: "/member/FinaBidLoanV2.do",
    	dataType: "json",
    	data:{
    		fp_id:fp_id
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(dataResult){
    		$("#showDiv").html("");
    		//头
    		var hd = '<div class="topper clearfix">'+
    					'<span class="fl fs_18">投资列表</span>'+
    					'<a class="fr plus_c" onclick="cloShowDiv()"></a>'+
    				'</div>'+
    				'<div class="middle" style="overflow:scroll;height:400px;">'+
    					'<table class="table no_1">';
    		
    		
    		var htmlTr = hd+
    		'<tr>'+
            '<th class="col_1 f">投标时间</th>'+
            '<th class="col_2">项目ID</th>'+
            '<th class="col_3 tr"><span>投资金额</span></th>'+
            '<th class="col_4 tr"><span>待收本息</span></th>'+
            '<th class="col_5">待收期数/总期数</th>'+
            '<th class="col_6">借款年利率</th>'+
            /*'<th class="col_7">状态</th>'+
            '<th class="col_8 l">操作</th>'+*/
			'</tr>';
    		
    		if(dataResult.errCode == '0'){
    			if(dataResult.data.result != ""){
    				for (var i = 0; i < dataResult.data.result.length; i++) {
    					var dtMap = dataResult.data.result[i];
    					//投标时间
    					var crteatDt = dtMap.CREATE_TIME;
    					var CREATE_TIME = "";
    					if(crteatDt!=null && crteatDt!=""){
    						var dtm = new Date(Date.parse(crteatDt.replace(/-/g,"/"))); //转换成Data();
    						var CREATE_TIME = dtm.getFullYear()+"-"+(dtm.getMonth()+1)+"-"+dtm.getDate();
    					}
    					//标id
    					var lidHtml = "";
    					if(dtMap.loan_flag!=null && dtMap.loan_flag!="" && dtMap.loan_flag == 0){
    						lidHtml = '<a target="_blank" class="blue" href="/xiangmu/v-'+dtMap.lidEncrypt+'.html">'+dtMap.lid+'</a>';
						}else if(dtMap.loan_flag!=null && dtMap.loan_flag!="" && dtMap.loan_flag == 1){
							lidHtml = '<a target="_blank" class="blue" href="/zhuanrang/v-'+dtMap.lidEncrypt+'-1.html">'+dtMap.lid+'</a>';
						}else{
							lidHtml = '<a target="_blank" href="javascript:;'+dtMap.lid+'</a>';
						}
    					//投资金额
    					var FORCE_AMOUNT = 0.00;
    					if(dtMap.FORCE_AMOUNT!=null && dtMap.FORCE_AMOUNT!="" && dtMap.FORCE_AMOUNT > 0){
    						FORCE_AMOUNT = dtMap.FORCE_AMOUNT/1000;
						}
    					FORCE_AMOUNT = formatNum(FORCE_AMOUNT);
    					//待收期数
    					var FINISH_STATUS = 0;
    					if(dtMap.FINISH_STATUS!=null && dtMap.FINISH_STATUS!=""){
    						FINISH_STATUS =dtMap.FINISH_STATUS;
    					}
    					//总期数
    					var LOAN_CYCLE = 0;
    					if(dtMap.LOAN_CYCLE!=null && dtMap.LOAN_CYCLE!=""){
    						LOAN_CYCLE =dtMap.LOAN_CYCLE;
    					}
    					//代收本息
    					var SHOULD_CI = 0.00;
    					var sta= dtMap.state;
    					if(sta==3 || sta==9 || sta==20){
    					}else{
    						if(dtMap.SHOULD_CI!=null && dtMap.SHOULD_CI!="" && dtMap.SHOULD_CI > 0){
    							SHOULD_CI = dtMap.SHOULD_CI/1000;
    						}
    					}
    					SHOULD_CI = formatNum(SHOULD_CI);
    					//借款年利率
    					var RATE = 0;
    					if(dtMap.RATE!=null && dtMap.RATE!="" && dtMap.RATE > 0){
    						RATE =dtMap.RATE/100;
    					}
    					//回款详情
    					var huiKuanHtml = '--';
    					//状态
    					var staHtml = "";
    					if(dtMap.loan_flag!=null && dtMap.loan_flag!="" && dtMap.loan_flag == 0){
    						if(dtMap.state!=null && dtMap.state!="" && dtMap.state == 4){
    							if((dtMap.FINISH_STATUS!=null && dtMap.FINISH_STATUS!="" && dtMap.FINISH_STATUS <=0) &&
    									(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId ==0)){
    								staHtml ="已结清";
    							}else{
    								if((dtMap.assignId!=null && dtMap.assignId!="" && dtMap.assignId ==0) &&
        									(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId ==0)){
        								staHtml ="持有中";
        							}else{
        								if(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId ==0){
        									staHtml ="转让中";
        								}else{
        									staHtml ="已转让";
        								}
        							}
    							}
    							
    							// 详情协议
    							if(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId > 0){
    								huiKuanHtml = '<a target="_blank" href="/member/investmentProjectDetail.do?lid='+dtMap.lidEncrypt+'&'+dtMap.fp_id+'" class="blue">回款详情</a>';
    							}else{
    								if(dtMap.assignId!=null && dtMap.assignId!="" && dtMap.assignId ==0){
    									huiKuanHtml = '<a target="_blank" href="/member/investmentProjectDetail.do?lid='+dtMap.lidEncrypt+'&'+dtMap.fp_id+'" class="blue">回款详情</a>';
        							}
    							}
    							
    						}else{
    							if(dtMap.state!=null && dtMap.state!="" && 
    									(dtMap.state ==0 || dtMap.state ==1 || dtMap.state ==3 
    									|| dtMap.state ==9 || dtMap.state ==20 || dtMap.state ==11)){
    								staHtml ="审核中";
    							}else{
    								staHtml ="投标失败";
    							}
    							// 详情协议
    							huiKuanHtml = '--';
    							
    						}
    					}else if(dtMap.loan_flag!=null && dtMap.loan_flag!="" && dtMap.loan_flag == 1){
    						if(dtMap.state!=null && dtMap.state!="" && dtMap.state == 1){
    							if((dtMap.FINISH_STATUS!=null && dtMap.FINISH_STATUS!="" && dtMap.FINISH_STATUS <=0) &&
    									(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId ==0)){
    								staHtml ="已结清";
    							}else{
    								if((dtMap.assignId!=null && dtMap.assignId!="" && dtMap.assignId ==0) &&
        									(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId ==0)){
        								staHtml ="持有中";
        							}else{
        								if(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId ==0){
        									staHtml ="转让中";
        								}else{
        									staHtml ="已转让";
        								}
        							}
    							}
    							
    							// 详情协议
    							if(dtMap.successAssignId!=null && dtMap.successAssignId!="" && dtMap.successAssignId > 0){
    								huiKuanHtml = '<a target="_blank" href="/member/investmentProjectDetail.do?lid='+dtMap.lidEncrypt+'&'+dtMap.fp_id+'" class="blue">回款详情</a>';
    							}else{
    								if(dtMap.assignId!=null && dtMap.assignId!="" && dtMap.assignId ==0){
    									huiKuanHtml = '<a target="_blank" href="/member/investmentProjectDetail.do?lid='+dtMap.lidEncrypt+'&'+dtMap.fp_id+'" class="blue">回款详情</a>';
        							}
    							}
    						}else{
    							if(dtMap.state!=null && dtMap.state!="" && dtMap.state ==0){
    								staHtml ="审核中";
    							}else if(dtMap.state!=null && dtMap.state!="" && dtMap.state ==0){
    								staHtml ="投标失败";
    							}
    							
    							// 详情协议
    							huiKuanHtml = '--';
    						}
    					}
    					
    					htmlTr = htmlTr + '<tr>'+
	                    '<td>'+CREATE_TIME+'</td>'+
	                    '<td>'+lidHtml+'</td>'+
	                    '<td>'+FORCE_AMOUNT+'</td>'+
	                    '<td>'+SHOULD_CI+'</td>'+
	                    '<td>'+FINISH_STATUS+'/'+LOAN_CYCLE+'</td>'+
	                    '<td>'+RATE+'%</td>'+
	                    /*'<td>'+staHtml+'</td>'+
	                    '<td>'+huiKuanHtml+'</td>'+*/
	    				'</tr>';
    				}
    			} else {
    				htmlTr = htmlTr + '<tr>'+
                    '<td colspan="8">无记录</td>'+
    				'</tr>';
    			}
    		}else {
    			htmlTr = htmlTr + '<tr>'+
                '<td colspan="8">无记录</td>'+
				'</tr>';
    		}
    		var fl = '</table>'+
    			'</div>';
    		htmlTr = htmlTr +fl;
    		$("#showDiv").html(htmlTr);
    		$("#rateTitle").html("投资列表");
    		var popUp = null;
		    seajs.use('popup',function(popup){
		        popUp = popup;
		    });
    		var attr = new Attention( popUp , '#csdRateDiv' ,  true );
	        attr.event();
	        $(".pop-close").click(function(){ popUp.hideLayer($("#csdRateDiv"))});
    	}   
	});
}


// 格式化金额
function formatNum(pamenth) {
	pamenth = pamenth.toString().replace(/\$|\,/g, '');
	if (isNaN(pamenth)) {
		pamenth = "0";
	}
	var sign = (pamenth == (pamenth = Math.abs(pamenth)));
	pamenth = Math.floor(pamenth * 100 + 0.50000000001);
	var cents = pamenth % 100;
	pamenth = Math.floor(pamenth / 100).toString();
	if (cents < 10) {
		cents = "0" + cents;
	}
	for (var i = 0; i < Math.floor((pamenth.length - (1 + i)) / 3); i++) {
		pamenth = pamenth.substring(0, pamenth.length - (4 * i + 3)) + ','
				+ pamenth.substring(pamenth.length - (4 * i + 3));
	}
	return (((sign) ? '' : '-') + pamenth + '.' + cents);
}


function exitFinancial(fp_id,fp_name){
	var msg="您持有的" + fp_name + 
	"<br>确定要退出吗？";

	art.dialog.confirm(msg,function(){
		//确定退出
		$.ajax({
	    	type: "post",
	   	 	url: "/member/sureExitFinancial.do",
	    	dataType: "json",
	    	data:{
	    		fp_id:fp_id,
	    		isExitFlag:"true"
	   		 },
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(returnDate){
	    		if(returnDate&&returnDate.result == 'success'){//成功
	    			window.location.reload();//刷新页面
	    		}else{
    				art.dialog.alert("退出失败,请稍后再试或者联系客服!");
    			}
	    	}
		});
	});
	
}

/*
 * 动态改变退出金额一系列值的变化
 * */
function changeMoneyMethod(obj){
	var diogAmountJoin = $.trim($("#diogAmountJoin").text());//理财总金额
	var inputAmount = $.trim($("#inputExitAmount").val());//退出金额
	var holdDay = $.trim($("#gsHoldDay").text());//持有时间
	var rate = $.trim($("#gsRate").text());//产品利息
	var earlyExitRate= $.trim($("#earlyExitRate").text());//未到期退出手续费
	var webExpirTimeFlag= $.trim($("#webExpirTimeFlag").val());//是否在合约期限
	var webPercent= $.trim($("#webPercent").val());//会员折扣比例
	var exitFee = 0;
	if(parseInt(obj) == 3){
		//全部
		inputAmount = diogAmountJoin;
		$("#inputExitAmount").val(inputAmount);
	}
	if(parseInt(inputAmount) < 0){
		$("#exitMsg").text('退出金额必须大于0');
		$("#exitMsg").css('display','block');
		return false; 
	}else if(parseInt(inputAmount) > parseInt(diogAmountJoin)){
		$("#exitMsg").text('退出金额超限');
		$("#exitMsg").css('display','block');
		return false; 
	}else{
		//alert('inputAmount11111:'+inputAmount);
		$("#exitMsg").css('display','none');
		//obj==0 表示减，1表示加
		if(obj==0){
			if(parseInt(inputAmount)-parseInt(100)>=0){
				inputAmount = parseInt(inputAmount)-parseInt(100);
			}
		}else if(obj==1){
			if(parseInt(inputAmount) + parseInt(100) <= parseInt(diogAmountJoin)){
				inputAmount = parseInt(inputAmount) + parseInt(100);
			}
		}
		var interestAmount = (parseInt(inputAmount)*(rate/100)*parseInt(holdDay))/365;//收益
		//alert('interestAmount:'+interestAmount);
		if(webExpirTimeFlag == "true"){
			//合约期限内收取退出费
			exitFee = (earlyExitRate*0.01)*(Number(inputAmount)+Number(interestAmount));
			if(Number(webPercent)!=0){
				exitFee = (exitFee*Number(webPercent)*0.01);
			}
			$("#releaseFeeHtml").css('display','block');
			$("#exitManagerFee").text(exitFee.toFixed(2));//提前退出费用
		}
		$("#inputExitAmount").val(inputAmount);//文本框展示的退出金额
		$("#gsExitAmount").text(inputAmount);//计算公式展示的退出金额
		$("#exitIncomeVal").text(interestAmount.toFixed(2));//退出收益
		$("#actualAmount").text((Number(inputAmount)+Number(interestAmount)-Number(exitFee)).toFixed(2));//退出总到账金额(本金+收益-手续费)
	}
}

/**
 * added by Chen.Guiyang 20160120 新手产品到期自动退出
 */
function showNewAlt() {
	$('.newAltbg').slideDown(1000);
	$('.newAltPop').slideDown();
}

function closeNewAlt() {
	$('.newAltbg').hide();
	$('.newAltPop').slideUp();
	if ($("select.selectStyle").val() != $("#auto_exit").val()) {
		// 当页面修改的到期退出方式和表里的不一样，则修改表中到期退出方式。
		doProcessMode();
	} else {
		alert("到期处理方式未做修改");
	}
}

function doProcessMode(){
	
	var fp_id=$("#fp_id").val();
	if(fp_id==undefined||$.trim(fp_id)==""){
		alert("产品ID不能为空");
		return;
	}
	var processMode=$("#exitType").val();

	var timeToken=$("#timeToken").val();
	if(timeToken==undefined||$.trim(timeToken)==""){
		alert("令牌无效");
		return;
	}
	
	var autoExitedCsd=$("#autoExitedCsd").val();

	if(processMode){
		$.post("/member/doProcessMode.do",{fp_id:fp_id,processMode:processMode,timeToken:timeToken,autoExitedCsd:autoExitedCsd},function(data){
			if(data){
				var message=data['message'];
				if(message){
					if("保存成功"==message){
						document.forms[0].submit();
					} else {
						alert(message);
					}
				}
			}else{
				alert("服务器发生错误！");
			}
		},"json");
	}else{
		alert("到期处理方式不能为空");
		return;
	}
}

//标
function incomeFin(fp_id){
	$.ajax({
    	type: "post",
   	 	url: "/member/incomeFin.do",
    	dataType: "json",
    	data:{
    		fp_id:fp_id
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(dataResult){
    		$("#showDiv").html("");
    		//头
    		var hd = '<div class="topper clearfix">'+
    					'<a class="fr plus_c" onclick="cloShowDiv()"></a>'+
    				'</div>'+
    				'<div class="middle" style="overflow-y:scroll;height:400px;">'+
    					'<table class="table fc_9" cellspacing="0" cellpadding="0">';
    		var htmlTr = hd+
    		'<tr>'+
            '<th class="col_1 f">阶段</th>'+
            '<th class="col_2">利率</th>'+
            '<th class="col_3"><span>持有天数（范围）</span></th>'+
			'</tr>';
    		
    		if(dataResult.errCode == '0'){
    			if(dataResult.jtList != ""){
    				//加息情况
    				var contentd = "";
    				irList = dataResult['jllist'];
    				if(irList.length>0){
	    					if(irList[0].QJJX>0){
								contentd+= " + 全局"+(irList[0].QJJX)/100+"%";
							}
							
							if(irList[0].PTJX>0 ){
								contentd+= " + 加息券"+(irList[0].PTJX)/100+"%";
							}
							if(irList[0].PTJX==null && irList[0].QJJX==null){
								contentd+= "";
							}
    					}
						
    				for (var i = 0; i < dataResult.jtList.length; i++) {
    					var dtMap = dataResult.jtList[i];
    					
    					//借款年利率
    					var interestRate = 0;
    					if(dtMap.interestRate!=null && dtMap.interestRate!="" && dtMap.interestRate > 0){
    						interestRate =dtMap.interestRate/100 ;
    					}
    					//期限
    					var dayHtml = "";
    					if(i==dataResult.jtList.length-1){
    						dayHtml = dtMap.handleStart +'天及更长';
    					}else{
    						dayHtml = dtMap.handleStart +'至'+ dtMap.handleEnd+' 天';
    					}
    					htmlTr = htmlTr + '<tr>'+
	                    '<td>'+dtMap.stage+'</td>'+
	                    '<td>'+interestRate+'%'+contentd+'</td>'+
	                    '<td>'+dayHtml+'</td>'+
	    				'</tr>';
    				}
    			}else {
        			htmlTr = htmlTr + '<tr>'+
                    '<td colspan="4">数据异常，请稍后再试！</td>'+
    				'</tr>';
        		}
    		}else {
    			htmlTr = htmlTr + '<tr>'+
                '<td colspan="4">数据异常，请稍后再试！</td>'+
				'</tr>';
    		}
    		
    		var fl = '</table>'+
    			'</div>';
    		htmlTr = htmlTr +fl;
    		$("#showDiv").html(htmlTr);
    		$("#rateTitle").html("预期年化收益");
    		var popUp = null;
		    seajs.use('popup',function(popup){
		        popUp = popup;
		    });
    		var attr = new Attention( popUp , '#csdRateDiv' ,  true );
	        attr.event();
	        $(".pop-close").click(function(){ popUp.hideLayer($("#csdRateDiv"))});
    		//showCon_1();
    	}
	});
}

function cloShowDiv(){
	closeAll_1();
}