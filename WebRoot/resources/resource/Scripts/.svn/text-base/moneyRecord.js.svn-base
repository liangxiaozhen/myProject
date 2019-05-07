
$(document).ready(function(){
	var moreCode = $("#moreCode").val();
	if(moreCode != undefined && moreCode != ""){
		if(moreCode != '-1' && moreCode != '0' && moreCode != '1' && moreCode != '10' && moreCode != '13' 
			&& moreCode != '19' && moreCode != '38' && moreCode != '11' && moreCode != '19,38'){
			//没选中这些，就显示出更多选项
			$('.a_btn .more').text('隐藏').siblings().children('a').show();
		}
	}
	
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
	
       var cur_dh = $('#my_nwd_5');
        cur_dh.addClass('active');
        cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
        
        $("#startdate").blur(function(){
     	   var regex = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
     	   var dateValue = $("#startdate").val();
     	   if (dateValue != '' && !regex.test(dateValue)) {
//     	    	showMsg("日期格式不正确！(正确格式：2012-12-12)");
     		  $("#msgContent").html("日期格式不正确！(正确格式：2012-12-12)");
     		  showCon_0();
     	    	$("#startdate").val("");
     	    	return false;
     	    } 
        });
        
        $("#enddate").blur(function(){
     	   var regex = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
     	   var dateValue = $("#enddate").val(); 
     	   if (dateValue != '' && !regex.test(dateValue)) {
//     	    	showMsg("日期格式不正确！(正确格式：2012-12-12)");
     		  $("#msgContent").html("日期格式不正确！(正确格式：2012-12-12)");
     		  showCon_0();
     	    	$("#enddate").val("");
     	    	return false;
     	    } 
        });
        	//                开始时间                       结束时间                       整月                                                               类型外部                        
        condition($('#startdate'),$('#enddate'),$("#conditionMore a"),$("[id$='type']"));
        	
       
    });


function condition(startdate,enddate,timeCondition,moreCode,moreType){
	startdate.change(function(){
		
		$("#all,#week,#month").removeClass("active");
		
		$("#startdate").blur();
		$("#enddate").blur();
		if(compareDate($("#startdate").val(),$("#enddate").val())){
			if(startdate.val() != '' && enddate.val()){
				$("#week").removeClass().attr("class","fc_6 mar_l5 mar_r5");
				$("#month").removeClass().attr("class","fc_6 mar_l5 mar_r5");
				$("#threeMonth").removeClass().attr("class","fc_6 mar_l5 mar_r5");
				$("#timeCondition").attr("value","");
				document.forms[1].submit();
			
		    	/*$.ajax({
		    		url:contextpath+"/user/activity/queryByDate.action",
		    		async:false,
		    		data:{
		    			"startdate":$("#startdate").val(),
		    			"enddate":$("#enddate").val()
		    		},
		    		success:function(data){
		    			//$("#box1").html(data);
		    		}
		    	});*/
			}
		}
	});

	enddate.change(function(){
		
		$("#all,#week,#month").removeClass("active");
		
		if(compareDate($("#startdate").val(),$("#enddate").val())){
		if(startdate.val() != '' && enddate.val()){
			$("#week").removeClass().attr("class","fc_6 mar_l5 mar_r5");
			$("#month").removeClass().attr("class","fc_6 mar_l5 mar_r5");
			$("#threeMonth").removeClass().attr("class","fc_6 mar_l5 mar_r5");
			$("#timeCondition").attr("value","");
			document.forms[1].submit();
			
			
	    	/*$.ajax({
	    		url:contextpath+"/user/activity/queryByDate.action",
	    		async:false,
	    		data:{
	    			"startdate":$("#startdate").val(),
	    			"enddate":$("#enddate").val()
	    		},
	    		success:function(data){
	    			//$("#box1").html(data);
	    		}
	    	});*/
		}
		}
	});

	moreCode.click(function(){
		$("#moreType").val("更多");
		$("#moreCode").val($(this).attr("value"));
		document.forms[1].submit();
		return false;
	});
	timeCondition.click(function(){
		$("#timeCondition").attr("value",""+$(this).attr("id"));
		document.forms[1].submit();
		return false;
	});

//	//得到类型select框中的值
//	moreType.change(function(){
//		$(".condition a_btn a").removeClass().attr("class","lei kuai");//select框有变动，去除a标签中的active
//		$("#moreCode").val($(this).find("option:selected").val());
//		document.forms[0].submit();
//		return false;
//	});
}

//function selectAll(){
//	if(compareDate($("#startdate").val(),$("#enddate").val())){
//		var flag = $(this).hasClass("checkbox checked");//判断全选按钮的状态
//		var value = "";
//		if(flag){
//			value = "-1";
//		}else{
//			$("[id$='name']").each(function () {
//				var flag1 = $("#"+this.id).parents("span").hasClass("checkbox checked");
//				if(flag1){
//					value = value + "," + $("#"+this.id).val();
//				}
//			});
//		}
//		$("#attrCode").attr("value",value);
//		document.forms[0].submit();
//	}
	
//}


function compareDate(checkStartDate, checkEndDate) {      
    var arys1= new Array();      
    var arys2= new Array();      
    if(checkStartDate != null && checkEndDate != null){      
    	arys1=checkStartDate.split('-');      
    	var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
    	arys2=checkEndDate.split('-');      
    	var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);      
    	if(sdate > edate){      
//    		showMsg("日期开始时间不能大于结束时间！");    
    		$("#msgContent").html("日期开始时间不能大于结束时间！");
   		  	showCon_0();
    		return false;         
    	}else{    
    		return true;      
    	}   
    }      
} 

function recharge(){
	var isBlackUser = $("#blackUser").val();
	if(isBlackUser=="1"){
		var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，您已不能充值。</p>";
		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
		$(".plusBank1 .content").html(msg);
		$(".plusBank1 button.btn").unbind('click');
		$(".plusBank1 button.btn").click(function() {
			// 关闭提示信息弹出框
			//window.location.reload();
			closeAll_1();
		});
		showCon_1();
		return;
	}else{
		window.location.href="/member/rechargeStep.do";
	}
}

//zhuzy 2014-12-22 TASK#2379 黑名单操作受限与页面提现提示
function withdrawDeposit(){
	var isBlackUser = $("#blackUser").val();
	if(isBlackUser=="1"){
		var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，";
		msg+="您的提现资金可能会退回到充值使用的银行卡。无法为您提现到指定银行卡，对此深表歉意。</p>";
		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
		$(".plusBank1 .content").html(msg);
		$(".plusBank1 button.btn").unbind('click');
		$(".plusBank1 button.btn").click(function() {
			// 关闭提示信息弹出框
			//window.location.reload();
			closeAll_1();
			window.location.href="/member/carry.html";
		});
		showCon_1();
		return;
	}else{
		window.location.href="/member/carry.html";
	}
}

//function excelMoneyRecord(){
//	window.location  = "/member/moneyRecordExcel.do?"+$("#fundsLog").serialize();
//};
