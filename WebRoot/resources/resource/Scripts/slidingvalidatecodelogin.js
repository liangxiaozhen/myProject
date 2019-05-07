/*
 * 2015.12.17  
 * new by sunyang
 * */
var div_geetest_lib="div_geetest_two";
var id="div_id_embed_two";
var tab=$(".tab_u a").attr("title");
	if("手机动态密码登录"==tab){
		id="div_id_embed";
		div_geetest_lib="div_geetest_lib";
	}else if("使用普通方式登录"==tab){
		id="div_id_embed_two";
		div_geetest_lib="div_geetest_two";
	}

function geetest_ajax_results() {
	$.ajax({
		url : "/validatecodenew/refresh.do?random="+(new Date().getTime().toString(36)),
		type : "post",
		data : {
		geetest_challenge : $("#"+id).find($("input[name='geetest_challenge']")).val(),
		geetest_validate : $("#"+id).find($("input[name='geetest_validate']")).val(),
		geetest_seccode :$("#"+id).find($("input[name='geetest_seccode']")).val(),
		channel : "3"
		}, 
		dataType : 'JSON',
		success : function(result) {
			if("手机动态密码登录"==tab){
				$("#imgCode").val(result);
				$("#errorMsg").html("");
			}else if("使用普通方式登录"==tab){
				
				$("#imgquickcode").val(result);
				$('#quickLoginErrorMsg').html("");
				$("#mobile").removeClass("red");
				$("#smCode").removeClass("red");
			}
		}
	});
}
				
var gtFailbackFrontInitial = function(result) {
	var s = document.createElement('script');
	s.id = 'gt_lib';
	s.src = 'https://static.geetest.com/static/js/geetest.0.0.0.js';
	s.charset = 'UTF-8';
	s.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(s);
	var loaded = false;
	s.onload = s.onreadystatechange = function() {
		if (!loaded
				&& (!this.readyState
						|| this.readyState === 'loaded' || this.readyState === 'complete')) {
			loadGeetest(result);
			loaded = true;
		}
	};
}

	var loadGeetest = function(config) {
		window.gt_captcha_obj = new window.Geetest({
			gt : config.gt,
			challenge : config.challenge,
			product : 'float',
			https : true,
			offline : !config.success
		});

		gt_captcha_obj.appendTo("#"+id);
		gt_captcha_obj.onSuccess(function() {
			geetest_ajax_results();
		});
	}

s = document.createElement('script');
s.src = 'https://api.geetest.com/get.php?callback=gtcallback';
$("#"+div_geetest_lib).append(s);

var gtcallback =( function() {
	var status = 0, result, apiFail;
	
	return function(r) {
		status += 1;
		if (r) {
			result = r;
			setTimeout(function() {
				if (!window.Geetest) {
					apiFail = true;
					gtFailbackFrontInitial(result)
				}
			}, 1000)
		}
		else if(apiFail) {
			return
		}
		if (status == 2) {
			loadGeetest(result);
		}
	}
})()

     $.ajax({
			url : "/validatecodenew/StartCaptcha.htm?random="+(new Date().getTime().toString(36)),
			type : "get",
			data : {channel : "3"},
			dataType : 'JSON',
			success : function(result) {
				gtcallback(result)
			}
		});

function abc(){
    $.ajax({
		url : "/validatecodenew/StartCaptcha.htm?random="+(new Date().getTime().toString(36)),
		type : "get",
		data : {channel : "3"},
		dataType : 'JSON',
		success : function(result) {
			gtcallback(result)
		}
	});
}
