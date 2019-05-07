
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
						"<em class='fff fs_12'>您好，</em><a href='"+httpsUrl+"/member/Investors.html' class='mar_r10'>"+nwdUser+"</a>" +
								"<a href='"+httpUrl+"/loginOut.html' id='loginOut' class='mar_r10'>退出</a>");
	} else {
		$('.login_bt').html("<a href='"+httpsUrl+"/login.html' title='登录' class='mar_r10'>登录</a>" +
				"<a href='http://www.niwodai.com/event.mhtml?artId=3800000642908480' title='注册' class='mar_r10'>注册</a>");
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
});
