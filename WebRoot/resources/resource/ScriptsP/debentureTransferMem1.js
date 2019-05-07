$(document).ready(function(){

    var cur_dh = $('#tz_nwd_5');
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
		$("#dateType").val(dateType);
	   
		// 表单提交
		document.forms[0].submit();
	});
		
});
//可转让债权
function canTransfer(){
	window.location.href="/member/canDebentureTransfer.do";
}
//转让中债权
function march(){
	window.location.href="/member/marchDebentureTransfer.do";
}
//已转让债权
function already(){
	window.location.href="/member/alreadyDebentureTransfer.do";
}
//转让记录
function record(){
	window.location.href="/member/debentureTransferRecord.do";
}
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
function applyforTransfer(bid, lid){
	$.ajax({
		type: "POST",
		url: "/member/checkTransferLoan.do?r="+new Date(),
		data: $.param({"bid":bid,"lid":lid}),
		dataType:"json",
		contentType:'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(result,status) {
			/*if(result.success == 0){
				$("#middle").html("<div class='content'>暂时不能转让，申请日期必须距离还款日>=24小时</div>");
				var attr = new Attention( popUp , '#tranDiv' ,  true );
			    attr.event();
			}else if(result.success == 1){*/
				$("#zrlid").val(bid);
				var attr = new Attention( popUp , '#tranDiv' ,  true );
			    attr.event();
			   /* var attr1 = new Attention( popUp , '#step1' ,  true );
			    attr1.event();*/
			//}
		}
	});
    //$("#closeDiv").click(function(){ popUp.hideLayer($("#tranDiv"))});
}
function next2(){
	$.ajax({
		type: "post",
	 	url: '/member/confirmAttorn.do',
		async: true,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success: function(_data){
			$("#mbId").val(_data['uid']);
			$("#lid").html(_data['projectId']);
			if(_data['creditorValue']){
				$("#zqjz").html(formatNum(_data['creditorValue']/1000)+"元");
			} else {
				$("#zqjz").html("0.00元");
			}
			if(_data['transferPricePoundage']){
				$("#zrsxf").html(formatNum(_data['transferPricePoundage']/1000)+"元");
			} else {
				$("#zrsxf").html("0.00元");
			}
			if(_data['residueCycle']){
				$("#syqc").html(_data['residueCycle']+"个月");
			} else {
				$("#syqc").html("0个月");
			}
			if(_data['transferPrice']){
				$("#zrjg").html(formatNum(_data['transferPrice']/1000));
				$("#transPriceId").val(formatNum(_data['transferPrice']/1000));
			} else {
				$("#zrjg").html("0.00");
				$("#transPriceId").val(0);
			}
			if(_data['paidTypeName']){
				$("#hkfs").html(_data['paidTypeName']);
			} else {
				$("#hkfs").html("按月还款");
			}
			if(_data['amount']){
				$("#ssje").html(formatNum(_data['amount']/1000)+"元");
			} else {
				$("#ssje").html("0.00元");
			}
			//折扣
			if(_data['tpd'] && parseInt(_data['tpd']) < 100){
				var zk = String(_data['tpd']).substring(0,1);
				$("#zhekou").css("display","inline-block");
				$("#tpd").html(_data['levelname']+"：享"+zk+"折");
			}else{
				$("#zhekou").css("display","none");
			}
			$("#step1Title").removeClass();
			$("#step1Title").addClass("visited_a");
			$("#step2Title").removeClass();
			$("#step2Title").addClass("active");
			$("#step2").removeClass();
			$("#changeOldStep2").addClass("visited_a");
			$("#step1").addClass("hidden");
			
    	}
	});
	
}



//重新计算转让价格
function resetPrice(discount)
{
	$.ajax({
    		type: "post",
   	 		url: '/member/confirmAttorn.do?discount='+$.trim(discount),
    		async: true,
    		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    		success: function(_data){
			if(_data['creditorValue']){
				$("#zqjz").html(formatNum(_data['creditorValue']/1000)+"元");
			} else {
				$("#zqjz").html("0.00元");
			}
			if(_data['transferPricePoundage']){
				$("#zrsxf").html(formatNum(_data['transferPricePoundage']/1000)+"元");
			} else {
				$("#zrsxf").html("0.00元");
			}
			if(_data['residueCycle']){
				$("#syqc").html(_data['residueCycle']+"个月");
			} else {
				$("#syqc").html("0个月");
			}
			if(_data['transferPrice']){
				$("#zrjg").html(formatNum(_data['transferPrice']/1000));
				$("#transPriceId").val(formatNum(_data['transferPrice']/1000));
			} else {
				$("#zrjg").html("0.00");
				$("#transPriceId").val(0);
			}
			if(_data['paidTypeName']){
				$("#hkfs").html(_data['paidTypeName']);
			} else {
				$("#hkfs").html("按月还款");
			}
			if(_data['amount']){
				$("#ssje").html(formatNum(_data['amount']/1000)+"元");
			} else {
				$("#ssje").html("0.00元");
			}
        }
   	});
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
//确认债权转让协议
function okAgreement(){
	
	if($.trim($('#pwd').val()).length <=0)
	{
		$('#errorMsg').show();
   		$('#errorMsg').html("<i></i>请输入交易密码！");
   		$("#pwd").focus();
	}
	else
	{
		//校验转让价格小于50元不能转让
		if($("#transPriceId").val()<50){
			$('#errorMsg').html("债权转让价格小于50元,不能转让！");
			$('#errorMsg').show();
			return;
		}
		var mbId=$('#mbId').val()+"";
		if(getCookie("countMSG"+mbId)==""){//存在
			
			setCookie("countMSG"+mbId,5);//初始化为5
		}
		if(getCookie("countMSG"+mbId)>0){
		    if(0==submitNum)
		    {
			    submitNum = 1;
			    $.ajax({
	        		type: "post",
	       	 		url: "/member/checkPwdFromconfirmAttorn.do",
	       	 		dataType: "json",
	        		data:$.param({"pwd":$.trim($("#pwd").val()),"token":$.trim($("#token").val())}),
	        		async: true,
	        		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        		success: function(_data){
	        			if (_data['state'] == 1) {
	                		//setCookie("countMSG"+mbId,5);//初始化为5
							$.ajax({
        						type: "post",
       	 						url: "/member/accomplishAgreement.do",
        						async: true,
        						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        						success: function(_data){
        							$("#lid2").html(_data['tpld']);
        							if(_data['creditorValue']){
        								$("#zqjz2").html(formatNum(_data['creditorValue']/1000)+"元");
        							} else {
        								$("#zqjz2").html("0.00元");
        							}
        							if(_data['transferPricePoundage']){
        								$("#zrsxf2").html(formatNum(_data['transferPricePoundage']/1000)+"元");
        							} else {
        								$("#zrsxf2").html("0.00元");
        							}
        							if(_data['residueCycle']){
        								$("#syqc2").html(_data['residueCycle']+"个月");
        							} else {
        								$("#syqc2").html("0个月");
        							}
        							if(_data['transferPrice']){
        								$("#zrjg2").html(formatNum(_data['transferPrice']/1000));
        								$("#transPriceId").val(formatNum(_data['transferPrice']/1000));
        							} else {
        								$("#zrjg2").html("0.00");
        								$("#transPriceId").val(0);
        							}
        							if(_data['paidTypeName']){
        								$("#hkfs2").html(_data['paidTypeName']);
        							} else {
        								$("#hkfs2").html("按月还款");
        							}
        							if(_data['amount']){
        								$("#ssje2").html(formatNum(_data['amount']/1000)+"元");
        							} else {
        								$("#ssje2").html("0.00元");
        							}
        							if(_data['discount']){
        								$("#zrzk2").html(formatNum(_data['discount']*100)+"%");
        							} else {
        								$("#zrzk2").html("0.00%");
        							}
        							if(_data['date']){
        								$("#xxrq2").html(_data['date']);
        							} else {
        								$("#xxrq2").html("0.00%");
        							}
        							$("#step2Title").removeClass();
        							$("#changeOldStep2").addClass("visited_a");
        							$("#step3Title").removeClass();
        							$("#step3Title").addClass("active");
        							$("#step3").removeClass();
        							$("#changeOldStep3").addClass("active");
        							$("#step2").addClass("hidden");
            					}
       						});
	                	}else if(_data['state'] == -1){
	                	    $('#errorMsg').show();
	                	    if(_data['errorMsg']){
	                	    	$('#errorMsg').html("<i></i>"+_data['errorMsg']);
	                	    }else{
	       						$('#errorMsg').html("<i></i>系统错误！");
	       					}
	       					$("#pwd").focus();
	       					submitNum = 0;
	       					
	                	}else if(_data['state'] == -2){
	                		$('#errorMsg').show();
	       					$('#errorMsg').html("<i></i>实收金额必须大于0！");
	       					$("#pwd").focus();
	       					submitNum = 0;
	                	}else if(_data['errorCode']){
	                		alert("令牌不匹配，请刷新页面！");
	                	}else {
	                		if(getCookie("countMSG"+mbId) <= 0){
	                			$('#errorMsg').html("对不起，您的交易密码已被锁定，24小时后再来！");
	                		}else{
	                			setCookie("countMSG"+mbId,getCookie("countMSG"+mbId)-1);
	                			$('#errorMsg').html("交易密码错误,您还剩"+getCookie("countMSG"+mbId)+"次输入机会，请重新输入！");
	                		}
	                		$('#errorMsg').show();
	       					$("#pwd").focus();
	       					submitNum = 0;
	                	}
	            	}
	       		});
		    }
		}else{
        	$('#errorMsg').html("对不起，您的交易密码已被锁定，24小时后再来！");
        	$('#errorMsg').show();
        }
	}
}
//关闭页面所有弹出层
function closeDiv() { 
	$("#step1Title").removeClass();
	$("#step1Title").addClass("active");
	$("#step2Title").removeClass();
	$("#step3Title").removeClass();
	$("#step1").removeClass();
	$("#step1").addClass("stepCon");
	$("#step2").removeClass();
	$("#step2").addClass("stepCon hidden");
	$("#step3").removeClass();
	$("#step3").addClass("stepCon hidden");
}

//关闭页面所有弹出层
function closeDialog() { 
	window.location.href="/member/marchDebentureTransfer.do";
}

function transferProjectRevocationFun(tpld)
{
	
	$("#revocationTpld").val(tpld);
	showCon_1();
}

function tpRevocationFun()
{
	$.ajax({
		type: "POST",
		url: "/member/transferProjectRevocation.do",
		data: $.param({"tpld":$.trim($("#revocationTpld").val())}),
		dataType:"json",
		contentType:'application/x-www-form-urlencoded;charset=UTF-8',
		success: function(result,status) {
			if(result.success > 0){
				window.location.href="/member/marchDebentureTransfer.do";
			}else{
				alert("操作失败!");
		    	window.location.href="/member/marchDebentureTransfer.do";
			}
		}
	});
}
//格式化金额
function formatNum(pamenth){
	pamenth = pamenth.toString().replace(/\$|\,/g,'');
    if(isNaN(pamenth)){
    	pamenth = "0";
    }
    var sign = (pamenth == (pamenth = Math.abs(pamenth)));
    pamenth = Math.floor(pamenth*100+0.50000000001);
    var cents = pamenth%100;
    pamenth = Math.floor(pamenth/100).toString();
    if(cents<10){
	    cents = "0" + cents;
    }
	for (var i = 0; i < Math.floor((pamenth.length-(1+i))/3); i++){
	    pamenth = pamenth.substring(0,pamenth.length-(4*i+3))+','+pamenth.substring(pamenth.length-(4*i+3));
	}
	return (((sign)?'':'-') + pamenth + '.' + cents);
}