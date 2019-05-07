
$(function () {
	//获取cookies
	var nwdUser = getCookie("cookieUname");
	if(!window.httpsUrl){
		httpsUrl = "";
	}
	if(!window.httpUrl){ 
		httpUrl = "";
	}
	if(nwdUser){
		//头部登陆信息
		$('.login_bt').html("<input type='hidden' value='' id='log_userid'/>" +
				"<input type='hidden' value='"+nwdUser+"' id='log_username'/>" +
						"<em class='fff fs_12'>您好，</em><a href='"+httpsUrl+"/member/Investors.html' rel='nofollow' class='hello'>"+nwdUser+"</a>" +
								"<a href='"+httpUrl+"/loginOut.html' id='loginOut' class='hello'>退出</a>");
		//banner登陆信息
		$('#static_banner_login').html("<a class='btn_orange' href='"+httpsUrl+"/member/Investors.html' rel='nofollow' title='账户中心'>账户中心</a>");
	} else {
		$('.login_bt').html("<a href='"+httpsUrl+"/login.html' rel='nofollow' title='登录' class='fff'>登录</a>" +
				"<a href='http://www.niwodai.com/register.html' rel='nofollow' title='注册' class='fff'>注册</a>");//老的注册
				//"<a href='http://www.niwodai.com/event.mhtml?artId=3800000642908480' rel='nofollow' title='注册' class='fff'>注册</a>");快速注册
		//$('#static_banner_login').html("<a class='btn_orange' href='http://www.niwodai.com/event.mhtml?artId=3800000642908480' rel='nofollow' title='免费注册'>免费注册</a>" +
	    $('#static_banner_login').html("<a class='btn_orange' href='' rel='nofollow' title='免费注册'>免费注册</a>" +		
				"<a href='"+httpsUrl+"/login.html' class='orange' rel='nofollow' title='马上登录'>马上登录</a>");
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
	
	/**
	 * 愚人节活动时间
	 *
	var endTime="2016-04-16";
	var now = new Date;
	var foolsdayActiveTime = new Date(endTime);
	var foolsdayCookie = getCookie("foolsdayCookie");
	//活动期限内 并且cookie没值的情况下 才弹框
	var flag= now < foolsdayActiveTime;
	//alert("flag:"+flag);
	if(flag && foolsdayCookie!=1){
		$("#fol_div1").show();
		$("#fol_div2").show();
		setCookie("foolsdayCookie","1");
	}
	*/
	
	function setCookie(name, value, d) {
		var Days = 30;
		var exp = new Date();
		if (d > 0)
			Days = d;
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value)
				+ ";path=/;domain=.niwodai.com;expires=" + exp.toGMTString();
	}
	
	$(".index_Close a").click(function(){
		$("#fol_div1").hide();
		$("#fol_div2").hide();
	});
});
