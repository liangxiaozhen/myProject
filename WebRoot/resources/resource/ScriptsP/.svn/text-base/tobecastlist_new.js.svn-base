var status = '1';
	function showOrderBy(field,order){
	    var o="";
	    if(field=="amount"){
	        if(order=="desc")o=3;else o=7;
	    }
	    if(field=="deadline"){
	        if(order=="desc")o=2;else o=6;
	    }    
	    if(field=="rate"){
	        if(order=="desc")o=1;else o=5;
	    }  
	    if(field=="bidSchedule"){
	        if(order=="desc")o=4;else o=8;
	    }
		$('#sortlist').val(o);
		$('#searchForm').submit();
	}
	
$('#amount').click(function(){
	var art = $(this).attr('class');
	if(art == 'down'){
		showOrderBy('amount','desc');
	}else{
		showOrderBy('amount','asc');
	}
});

$('#deadline').click(function(){
	var art = $(this).attr('class');
    if(art == 'down'){
        showOrderBy('deadline','desc');
   }else{
        showOrderBy('deadline','asc');
   }
});
$('#rate').click(function(){
	var art = $(this).attr('class');
    if(art == 'down'){
    	showOrderBy('rate','desc');
    }else{
    	showOrderBy('rate','asc');
    }
});
$('#bidSchedule').click(function(){
	var art = $(this).attr('class');
    if(art == 'down'){
    	showOrderBy('bidSchedule','desc');
    }else{
    	showOrderBy('bidSchedule','asc');
    }
});

function chooseCheckbox(all,a,b){
	var _str = 'first',_val=',',_str1 = 'cur';
	all.click(function(){
		var O_Class = $(this).attr("class");
		if(O_Class.indexOf(_str) == -1){ 
			all.removeClass("first"); 
			all.children('input').removeAttr('checked'); 
			b.val('');
		}else{ 
			all.addClass("first"); 
			all.children('input').attr('checked','true'); 
			a.each(function(){ 
				$(this).removeClass("first");
				$(this).children('input').removeAttr('checked'); 
			});
			b.val($(this).children('input').val());
		}
		$('#searchForm').submit();	
	}); 
	a.click(function(){ 
		var a_Class = $(this).attr("class"); 
		if(a_Class.indexOf(_str1) == -1){
			$(this).removeClass("cur"); 
			$(this).children('input').removeAttr('checked'); 
			a.each(function(){
				if($(this).hasClass("cur")===true){
					_val = _val + $(this).children('input').val()+',';
				}
			});
		}else{
			all.removeClass("cur"); 
			all.children('input').removeAttr('checked'); 
			$(this).addClass("cur");
			$(this).prev('input').attr('checked','true'); 
			a.each(function(){
				if($(this).hasClass("cur")){
					_val = _val + $(this).children('input').val()+',';
				}
			});			
		}
		b.val(_val);
		$('#searchForm').submit();
	});	
}

$(document).ready(function(){
	chooseCheckbox($('#typeAll'),$("[id$='type1']"),$('#typelist'));	//投标类型
	chooseCheckbox($('#timetypeAll'),$("[id$='timetype']"),$('#timelist'));	//投标期限
	chooseCheckbox($('#retypeAll'),$("[id$='retype']"),$('#relist'));	//还款方式
	chooseCheckbox($('#stypeAll'),$("[id$='stype']"),$('#slist'));	//招标状态
	
	var moreDivVal = $("#moreDiv").val();
	if(moreDivVal=="sq"){
		$(".a_on").html('收起'+' <i></i>').attr('class','a_off');
		$(".a_on").parents('.itmo_off').siblings('.on_off_box').slideDown(200);
		$("#hkfsDiv").removeClass("hidden");
		$("#zbztDiv").removeClass("hidden"); 
	}else{
		$(this).html('更多'+ '<i></i>').attr('class','a_on');
		$(this).parents('.itmo_off').siblings('.on_off_box').slideUp(200);
		$("#hkfsDiv").addClass("hidden");
		$("#zbztDiv").addClass("hidden");
	}
});

//--债权列表筛选的展开收起---
$('.on_off a').toggle(function(){
	$("#moreDiv").val("sq");
},function(){
	$("#moreDiv").val("gd");
});

/*标类型条件*/
$(".bidTypeChose").click(function() {
	$('#typelist').val(','+$(this).children('input').val()+',');
	$('#searchForm').submit();
});
