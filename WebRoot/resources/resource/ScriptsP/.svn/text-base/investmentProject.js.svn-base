$(document).ready(function(){
       $("#startdate").datepicker({
    	   changeMonth: true,
           changeYear: true,
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
       
       var cur_dh = $('#tz_nwd_1');
       cur_dh.addClass('active');
       cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
       
       $("input.nullflag").change(function() {
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
       
       var dateTypeButtons = $(".a_btn .lei");
       
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
   	
       $.ajax({
	   		type : "POST", 
	   		url : "/member/ajax/investmentCount.do", 
	   		data: "startdate="+$("#startdate").val()+"&enddate="+$("#enddate").val()+"&lState="+$("#lState").val(),
	   		dataType:"json",
	   		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   		success : function(result) { 
	   			
	   			var tjdsbj=result.dsbj;
	   			if(tjdsbj>0){
	   				$("#tjdsbj").text(formatNum(tjdsbj/1000));
	   			}
	   			var tjdslx=result.dslx;
	   			if(tjdslx>0){
	   				$("#tjdslx").text(formatNum(tjdslx/1000));
	   			}
	   			var tjdstx=result.dstx;
	   			if(tjdstx>0){
	   				$("#tjdstx").text(formatNum(tjdstx/1000));
	   			}
	   			var tjysbj=result.ysbj;
	   			if(tjysbj>0){
	   				$("#tjysbj").text(formatNum(tjysbj/1000));
	   			}
	   			var tjyslx=result.yslx;
	   			if(tjyslx>0){
	   				$("#tjyslx").text(formatNum(tjyslx/1000));
	   			}
	   			var tjystx=result.ystx;
	   			if(tjystx>0){
	   				$("#tjystx").text(formatNum(tjystx/1000));
	   			}
	   		} 
	   	});
       
       /**   delete by luobin 20160621 去回款表
       $.ajax({
	   		type : "POST", 
	   		url : "/member/ajax/investmentInfo.do", 
	   		data: "",
	   		dataType:"json",
	   		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   		success : function(result) { 
	   			var invzqzc=result.invdesc.zqzc;//债券资产
	   			var totalCreditoProfit = result.totalCreditoProfit;//历史累计收益
	   			var yesterdayCredtioProfit = result.yesterdayCredtioProfit;//昨日收益
	   			var nextGatheringInfoAmount=result.nextGatheringInfo.Amount;//下期收款金额
	   			var nextReturnShouldTime = result.nextGatheringInfo.SHOULD_TIME;//下期收款日期
	   			//alert('invzqzc:'+invzqzc+':totalCreditoProfit:'+(totalCreditoProfit)+';yesterdayCredtioProfit;'+yesterdayCredtioProfit+';nextGatheringInfoAmount;'+nextGatheringInfoAmount+';nextReturnShouldTime;'+nextReturnShouldTime);
	   			if(yesterdayCredtioProfit>0){
	   				$("#yesterdayCredtioProfit").text(yesterdayCredtioProfit);
	   			}
	   			if(totalCreditoProfit>0){
	   				$("#totalCreditoProfit").text(formatNum(totalCreditoProfit/1000));
	   			}
	   			if(invzqzc>0){
	   				$("#invzqzc").text(formatNum(invzqzc/1000));
	   			}
	   			if(nextGatheringInfoAmount>0){
	   				$("#nextGatheringInfoAmount").text(formatNum(nextGatheringInfoAmount/1000));
	   			}
	   			if(nextReturnShouldTime!=null){
	   				$("#nextReturnShouldTime").text(nextReturnShouldTime);
	   			}
	   			
	   			dealwithDecimal();
	   		} 
	   	});*/

//明细已移入祥情中。此处不需了
if(false){
       $.ajax({
	   		type : "POST", 
	   		url : "/member/ajax/interestInfo.do", 
	   		data: "lids="+$("#lids").val(),
	   		dataType:"json",
	   		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   		success : function(result) { 
	   			result = eval(result);
	   			var content = "";
	   			var contentd = "";
	   			var lid = "";
	   			if(result.size>0){
	   				for(var j = 0; j < result.size; j++){  
		   				lid = result[j][0].lid;
		   				if(result[j][1]['data'].length>0){
		   					
		   					for(var i = 0; i < result[j][1]['data'].length; i++){
		   						if(result[j][1]['data'][i].QJJX>0){
		   							contentd+= "全局"+(result[j][1]['data'][i].QJJX)/100+"%";
		   						}
		   						if(result[j][1]['data'][i].QJJX>0 && result[j][1]['data'][i].PTJX>0 ){
		   							contentd+= "+";
		   						}
		   						if(result[j][1]['data'][i].PTJX>0 ){
		   							contentd+= "加息券"+(result[j][1]['data'][i].PTJX)/100+"%";
		   						}
		   						if(result[j][1]['data'][i].PTJX>0){
		   							contentd+= "加息券"+(result[j][1]['data'][i].PTJX)/100+"%";
		   						}
		   						if(result[j][1]['data'][i].PTJX==null && result[j][1]['data'][i].QJJX==null){
		   							contentd+= "无";
		   						}
		   						content += "<td>"+result[j][1]['data'][i].JOINTIME+"</td>" +
		   								"<td>"+formatNum(result[j][1]['data'][i].JOINAMOUNT/1000)+"元</td>" +
		   								"<td><i class=\"fc_orange\">"+contentd+" </i></td></tr>"; 
		   						contentd= "";
		   					}
		   				} else {
		   					var i = 0;
		   					if(result[j][1]['data'][i].QJJX>0){
	   							contentd+= "全局"+(result[j][1]['data'][i].QJJX)/100+"%";
	   						}
	   						if(result[j][1]['data'][i].QJJX>0 && result[j][1]['data'][i].PTJX>0 ){
	   							contentd+= "+";
	   						}
	   						if(result[j][1]['data'][i].PTJX>0 ){
	   							contentd+= "加息券"+(result[j][1]['data'][i].PTJX)/100+"%";
	   						}
	   						if(result[j][1]['data'][i].PTJX>0){
	   							contentd+= "加息券"+(result[j][1]['data'][i].PTJX)/100+"%";
	   						}
	   						if(result[j][1]['data'][i].PTJX==null && result[j][1]['data'][i].QJJX==null){
	   							contentd+= "无";
	   						}
	   						content += "<td>"+result[j][1]['data'][i].JOINTIME+"</td>" +
	   								"<td>"+formatNum(result[j][1]['data'][i].JOINAMOUNT/1000)+"元</td>" +
	   								"<td><i class=\"fc_orange\">"+contentd+" </i></td></tr>"; 
	   						contentd= "";
		   				}
		   				$("#interestDesc_"+lid).html("<tr><th>加入时间</th><th>单笔加入金额</th><th>年利率浮动</th></tr><tr>" +content+"</tr>");
		   				content="";
		   			} 
	   			}
	   			
	   		} 
	   	});
}
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


function invState(state){
	if(compareDate($("#startdate").val(),$("#enddate").val())){
		$("#lState").val(state);
		if(state==3 ||state==4){
			$("#investment").attr("action", "/member/myAcceptList.html");
		}
		document.forms[0].submit();
	}
}

//借款协议
function lockAgreement(lidStr){
	//window.showModalDialog("/member/ajax/loanAgreement.do?lidStr="+lidStr,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
	window.open("/member/ajax/loanAgreement.do?lidStr="+lidStr,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
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

function getDicDate(day){
	  var d = new Date();
	  d.setDate(d.getDate()-day);
	  var day = d.getDate();
	  var year = d.getFullYear();
	  var month = d.getMonth()+1;
	  return year+"-"+month+"-"+day;
}
