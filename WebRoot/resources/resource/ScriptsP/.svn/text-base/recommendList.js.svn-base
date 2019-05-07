/**
 * 
 */
$(document).ready(function(){
	var cur_dh = $('#tj_nwd_4');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');

	$(".tab_u span").click(function(){
		var actionUrl = $(this).attr("action");
		if (actionUrl) {
			window.location.href = actionUrl;
		}
	});
	
	$("#typeCondition .fc_6").click(function() {
		var type = $(this).attr("value");
		if (!type) {
			type = -1;
		}
	 
		// 将数据状态值赋给status隐藏域
		$("#type").val(type);
		// 表单提交
		$("#recommendList").submit();
	});
	
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
			dateFormat : "yy-mm-dd",
			minDate: new Date($("#startdate").val())//最小日期
     });
	
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
    	//                开始时间                       结束时间                       整月
        condition($('#startdate'),$('#enddate'));

});

function condition(startdate,enddate){
	startdate.change(function(){
		$("#startdate").blur();
		if(compareDate($("#startdate").val(),$("#enddate").val())){
			$("#startdate").blur();
			if(startdate.val() != ''){
				document.forms[0].submit();
			}
		}else{
			$("#startdate").val("");
		}
	});

	enddate.change(function(){
		$("#enddate").blur();
		if(compareDate($("#startdate").val(),$("#enddate").val())){
			$("#enddate").blur();
			if(enddate.val() != ''){
				document.forms[0].submit();
			}
		}else{
			$("#enddate").val("");
		}
	});

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
    		showMsg("日期开始时间不能大于结束时间！");       
    		return false;         
    	}else{    
    		return true;      
    	}   
    }      
}