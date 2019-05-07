$(function () {
    $('#removewbAccount').click(function () {
    	/** update 20130509 lizhi 网站改版修改弹出框样式 start **/
    	/*art.dialog({
	    	content: '是否解除第三方账户绑定？',
	    	lock:true,
	    	ok:function(){
	    		window.location.href="/member/removeConnectAccount.do?accountType=2";
	    		return false;
	    	},
	    	close:function(){
	    	    window.location.href='/member/setConnectAccount.do'; 
	    	    return false;
	    	}
	    	});*/
    	
   		$(".plusBank1 .content").html("是否解除第三方账户绑定？");
   		// 弹出框
		showCon_1();
		$("#submit").click(function() {
			/**
			 * 提交时没有token。通过访问URL直接提交借款Bug修复
			 */
			$("#submit").unbind("click");
			closeAll_1();//关闭弹出框
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
	        var url = "/member/removeConnectAccount.do";
	        $.post(url,{
				accountType : 2,
	            stok : stok
			},function(data){
				if(data == 1) {
					$("#msgContent").html("解绑成功！");
					showCon_0();
				}else{
					window.location.reload();
				}
			});
			//window.location.href = "/member/removeConnectAccount.do?accountType=2";
			return false;
		});
		return false;
		/** update 20130509 lizhi 网站改版修改弹出框样式 end **/
    });
    $('#removeqqAccount').click(function () {
    	/** update 20130509 lizhi 网站改版修改弹出框样式 start **/
    	/*art.dialog({
	    	content: '是否解除第三方账户绑定？',
	    	lock:true,
	    	ok:function(){
	    		window.location.href="/member/removeConnectAccount.do?accountType=3";
	    		return false;
	    	},
	    	close:function(){
	    	    window.location.href='/member/setConnectAccount.do';  
	    		return false;
	    	}
	    	});*/
    	$(".plusBank1 .content").html("是否解除第三方账户绑定？");
   		// 弹出框
		showCon_1();
		$("#submit").click(function() {
			/**
			 * 提交时没有token。通过访问URL直接提交借款Bug修复
			 */
			$("#submit").unbind("click");
			closeAll_1();//关闭弹出框
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
	        var url = "/member/removeConnectAccount.do";
	        $.post(url,{
				accountType : 3,
	            stok : stok
			}, function(data) {
				if(data == 1) {
					$("#msgContent").html("解绑成功！");
					showCon_0();
				}else{
					window.location.reload();
				}
			});
			//window.location.href = "/member/removeConnectAccount.do?accountType=3";
			return false;
		});
		return false;
		/** update 20130509 lizhi 网站改版修改弹出框样式 end **/
    });
    $('#setwbAccount').click(function () {
    	checkAccount(1);
//    	window.location.href="/weiboLogin.html";
    	return false;
    });
    $('#setqqAccount').click(function () {
    	checkAccount(2);
//    	window.location.href="/qqLogin.html";
    	return false;
    });
    
});

//延迟刷新页面
function refreshPage(){
	setTimeout('window.location.reload()',600);
}

$(document).ready(function(){
	var cur_dh = $('#xinxi_nwd_7');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
});


//绑定账户确认
function checkAccount(type){
	$(".plusBankBg").css("display", "block");
	var reHtml= "";
	$("#msgTitle").html("验证登录密码");
	$("#msgCloseAll").attr("onclick", "reBackTip()").addClass("plus_c");
	reHtml += "<div class=\"surelogin clearfix\"><p style=\"padding-left: 35px;\"><i class=\"icon_base icon_16 icon_base_tipnote16 vertical_middle mar_r10\"></i><span class=\"vertical_middle\">为了您的账户安全，请先验证登录密码</span></p>";
	reHtml += " <dl><dt>用户名</dt><dd><input type=\"text\" class=\"input_all ui-input w320-input\" value='"+uAccount+"' autocomplete=\"off\" disabled></dd></dl>";
	reHtml += "<dl><dt>密码</dt><dd><input type=\"password\" class=\"input_all ui-input w320-input\"  id=\"pwd\"/><span class=\"prompt_2 error_2 msg-checkpwd txt_left mar_t5\"></span></dd></dl>";
	reHtml += "<dl><dt>&nbsp;</dt><dd class=\"txt_left\"><input type=\"button\" value=\"确定\" class=\"btn btn_36c btn_size120 checkPWD\" onclick=\"save()\"/>";	
	reHtml += "<input type=\"hidden\" id='thirdType' value="+type+" />";
	reHtml += "</dd></dl></div>";
	$("#changyong").html(reHtml);
	$("#openMsg").addClass("page116").removeClass("page53 mini").css("display", "block");
}

//回复提示弹框
function reBackTip(){
	$("#openMsg").css("display", "none").removeClass("page116").addClass("page53 mini");
	var reHtml= "";
	$("#msgTitle").html("信息");
	$("#msgCloseAll").attr("onclick", "refreshPage()").removeClass("plus_c");
	reHtml += "<div class=\"content\" id=\"msgContent\"></div>";
	reHtml += "<div class=\"btnbox\"><button class=\"btn btnSize_1 btn_blue plus_c\" onclick=\"refreshPage()\" id=\"msgClose\">确认</button></div> ";
	$("#changyong").html(reHtml);
}

//第三方绑定账户确认密码 
function save(){
	var _pwd = $('#pwd').val();
	if (_pwd.length < 1) {
        $('.msg-checkpwd').css("display","block").html("<i class='vertical_middle mar_r5'></i><span class='vertical_middle'>密码不能为空</span>");
        return false;
    }
	
    $('.checkPWD').attr("onclick","");
    $('.msg-checkpwd').html("正在提交验证，请稍候...");
    
	$.ajax({
		type : "post",
		url : "/member/checkPWD.do",
		data:{"pwd":$("#pwd").val()},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		dataType:"json",
		success : function(_data) {
        	$('.checkPWD').attr('onclick', "save()");
            if(_data['state'] == 1){
            	$('.msg-checkpwd').html("密码验证通过！");
            	if($("#thirdType").val() == '1'){//微博
            		window.location.href="/weiboLogin.html";
            	} else {
            		//跳转
                	window.location.href="/qqLogin.html";
            	}
            } else {//出错
            	$('.msg-checkpwd').css("display","block").html("<i class='vertical_middle mar_r5'></i>"+"<span class='vertical_middle'>"+_data['msg']+"</span>");
            }
		}
    
	});
	
}