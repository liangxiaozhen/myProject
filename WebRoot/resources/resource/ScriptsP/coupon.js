$(document).ready(function(){
	/*var cur_dh = $('#xinxi_nwd_6');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');

	$(".tab_u span").click(function(){
		var actionUrl = $(this).attr("action");
		if (actionUrl) {
			window.location.href = actionUrl;
		}
	});*/		
	
    //现金券激活
    $("#active").click(function() {
		var v = $("#sn1").val() + $("#sn2").val() + $("#sn3").val() + $("#sn4").val();
		if(v.length!=16){
			$("#remark").removeClass().addClass("prompt_1 error_1").html("<i class='icon_base icon_19 icon_base_tipnote16 vertical_middle mar_r5'></i>请输入16位密码");
		}else{
			$(".btn_active").removeAttr("id");	//防止点击第二次激活按钮及提示语显示
			$(".btn_active").html("<a class='btn btn_blue btn_active' style='width: 98px;height: 30px;line-height: 30px;' href='javascript:void(0)'>激活中...</a>");
			$.ajax({
		    	type: "post",
		   	 	url: "/member/cashCouponEffect.do",
		    	data:{pwd:v},
		    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    	success: function(res){
		    		if("succ"==res){
		    			
		    			$("#msgContent").html("恭喜你，激活成功！");
					   	showCon_0();
		    		}else{
		    			$("#remark").removeClass().addClass("prompt_1 error_1 inline_block mar_t5").html("<i class='icon_base icon_19 icon_base_tipnote16 vertical_middle mar_r5'></i>" + res);
		    			$(".btn_active").attr("id","active");	//防止点击第二次激活按钮及提示语显示
		    			$(".btn_active").html("激活");
		    		}
		    	}   
			}); 
		}
    });
    
    //自动聚焦到密码券的第一个输入框
    $("#sn1").focus();
    
    //自动跳到下一个输入框  
    $("input[name^='sn']").each(function() {
        $(this).keyup(function(e) {
            e = window.event || e;
            var k = e.keyCode || e.which;
            if (k == 8) {   //8是空格键
                if ($(this).val().length < 1) {
                    $(this).prev().focus();
                    $(this).prev().focus(function() {
                        var obj = e.srcElement ? e.srcElement: e.target;
                        if (obj.createTextRange) { //IE浏览器
                            var range = obj.createTextRange();
                            range.moveStart("character", 4);
                            range.collapse(true);
                            range.select();
                        }
                    });
                }
            } else {
          	  var v = $(this).val();
          	  if ($(this).val().length == 16) {
          		  $("#sn1").val(v.substr(0,4));
          		  $("#sn2").val(v.substr(4,4));
          		  $("#sn3").val(v.substr(8,4));
          		  $("#sn4").val(v.substr(12,4));
          	  }else if ($(this).val().length > 3) {
          		  $(this).val(v.substr(0,4));
                    $(this).next().focus();
                }
            }
        });
    });

    $("input[type='text'][id^='sn']").bind('keyup',
    function() {
        var len = $("#sn1").val().length + $("#sn2").val().length + $("#sn3").val().length + $("#sn4").val().length;
        //if (len == 16) device_verify();
    });
	/**
	 * 新增 2014-08-29  sunyang start
	 * 抵用券状态条件查询
	 * */
    $("#statusCondition .fc_6").click(function() {
		var status = $(this).attr("value");
		if (!status) {
			status = 0;
		}
	 
		// 将数据状态值赋给status隐藏域
		$("#status").val(status);
		// 表单提交
		$("#searchForm").submit();
	});
    /**
	 * 新增 2014-08-29  sunyang start
	 * 抵用券类型条件查询
	 * */
    $("#typeCondition .fc_6").click(function() {
		var type = $(this).attr("value");
		if (!type) {
			type = -1;
		}
	 
		// 将数据类型值赋给type隐藏域
		$("#type").val(type);
		
		// 表单提交
		$("#searchForm").submit();
	});
    /**
	 * 新增 2014-08-29  sunyang start
	 * 排序查询
	 * */
    function showOrderBy(field,order){
	    var o="";
		if(field=="gtime"){
	        if(order=="desc")o=0;else o=9;
	    }
	    if(field=="otime"){//过期时间仅允许升序
	        if(order=="asc"){
	        	o=3;
	        }
	    }
	    if(field=="amount"){
	        if(order=="desc")o=1;else o=5;
	    }  
	  
		$('#sortlist').val(o);
		$('#searchForm').submit();
	}
    
    $('#gtime').click(function(){
    	var art = $('#gtime').attr('class');
        if(art == 'kuai shang1'){
        	showOrderBy('gtime','desc');
        }else{
        	showOrderBy('gtime','asc');
        }
    });
    
    $('#amount').click(function(){
    	var art = $('#amount').attr('class');
        if(art == 'kuai shang1'){
        	showOrderBy('amount','desc');
        }else{
        	showOrderBy('amount','asc');
        }
    });
    
    $('#otime').click(function(){
    	var art = $('#otime').attr('class');
        showOrderBy('otime','asc');
    });
    
});

//现金券激活----每一条激活  add by lingjs at 20160616
function activeCoupons(v) {
	var certIdentity = $("#certIdentity").val();
	if(certIdentity && certIdentity == 1){
		//已实名过了，可以激活
		$("#id_"+v).removeAttr("id");	//防止点击第二次激活按钮及提示语显示
		$("#id_"+v).html("<a class='btn btn_blue btn_active' style='width: 98px;height: 30px;line-height: 30px;' href='javascript:void(0)'>激活中...</a>");
		$.ajax({
	    	type: "post",
	   	 	url: "/member/cashCouponEffect_byid.do",
	    	data:{certId:v,token:$("#token").val()},
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(res){
	    		if("succ"==res || "null"==res){	    			
	    			//$("#msgContent").html("恭喜你，激活成功！");
	    			//commonAlert.baseShow("提示","","恭喜你，激活成功！","确认","关闭",reloadCurrPage,reloadCurrPage);
	    			commonAlert.showOK("恭喜你，激活成功！",reloadCurrPage,reloadCurrPage,reloadCurrPage);
	    		}else{
	    			var error=res.errorCode;
	    			if(error){
	    				res="请刷新当前页";
	    			}
	    			commonAlert.show(res,reloadCurrPage,reloadCurrPage);
	    			$("#id_"+v).attr("id","active");//防止点击第二次激活按钮及提示语显示	    			
	    			$("#id_"+v).html("激活");
	    		}
	    	}
		});
	}else{
		//未实名
		commonAlert.show("请先实名！现在前往实名？",gotoRealName);
	}
}

//未实名时，点确认 设置要去的地址
function gotoRealName(){
	window.location.href = "/member/identityAuthentication.do"; 
}
//刷新本页面
function reloadCurrPage(){
	location.reload();	
}
 

