/**
 * 
 */

//点击取消清空关闭
function colseClean(){
	$(".plusBank1").attr("style","");
	$(".plusBank1 .topper").html(
			'<span class="fl fs_18">提示</span>'
	        +'<a class="fr plus_c" onClick="closeAll_1();"></a>'
	);
	$(".plusBank1 .content").html("");
	$(".plusBank1 .btnSize_1").attr("onClick","closeAll_1();");
	closeAll_1();
}

//换一张图片验证码
function refush(){
	var d = new Date();
	var src = "/validatecode/refresh.htm?date=" + d.getTime();
	$("#code").attr("src",src);
}

function sureSend(type){
	//取得用户输入的验证码
	var imgCode = $("#imgCode").val();
	if(imgCode == ''){//验证码为空
		$('#imgCodeMSG').css("display","");
    	$('#imgCodeMSG').removeClass().addClass("prompt_1 error_1").html("<i></i>验证码不能为空");
	}else{
		if("zhmm" == type){//找回密码
			click2(imgCode);
		}
		if("bdsj" == type){//绑定手机号
			click1(imgCode);
		}
		if("zhsj1" == type){//修改手机号旧号码发送
			click3(imgCode);
		}
		if("zhsj2" == type){//修改手机号新号码发送
			click4(imgCode);
		}
		if("tjyhk" == type){//添加银行卡和修改银行卡
			click5(imgCode);
		}
		if("aqbh" == type){//安全保护问题
			clickquestionGain(imgCode);
		}
		if("rgxgsj" == type){//人工修改手机号码
			click2(imgCode);
		}
		colseClean();
	}
}

//弹框
function openWid(type){
	//设置弹出框样式
	$(".plusBank1").attr("style","width:410px; margin-left:-205px;");
	//设置弹出框的标题和关闭
	$(".plusBank1 .topper").html(
			'<span class="fl fs_18">提示</span>'
	        +'<a class="fr nwd_icon plus_c" onClick="colseClean()"></a>'
	);
	//设置弹出框的内容
	$(".plusBank1 .content").html(
			'<table class="form table_child wid_w400 mar_auto">'
			+'<tr>'
			+'<th class="leftside pad_t10 vertical_top">验证码</th>'
			+'<td>'
			+'<div class="inputOut">'
			+'<input type="text" class="input_all ui-input w120-input itxt" style="width:120px;" name="imgCode" id="imgCode" maxlength="4">'
			+'<span class="yzmPic wid_w100 mar_l10 mar_r5 safe_mb8 inline_block"><img src="" width="90" height="36" name="code" id="code" style="vertical-align:middle;" alt="图形验证码" /></span>'
			+'<a href="javascript:;" class="blue safe_mb8 inline_block" id="refush" onClick="refush()">换一张</a><br>'
			+'<div class="txt_left mar_t5"><span id="imgCodeMSG"></span></div>'
			+'</div>'
			+'</td>'
			+'<tr>'
			+'</table>'
	);
	//设置弹出框的确定按钮
	//$(".plusBank1 .btnSize_1").attr("onClick","sureSend('"+type+"');");
	$("#btn_0919").unbind("click");
	$("#btn_0919").click(function(){
		sureSend(type);
	});
	
	//人工服务添加方法
	$("#humanServices").unbind("click");
	$("#humanServices").click(function(){
		sureSend(type);
	});
	//alert('sssssssssss:'+$(".plusBank1 .btnSize_1").attr("onClick"));
	//取验证码
	refush();
	//显示弹出框
	showCon_1();
}

//提现绑定银行卡图片验证码弹框
function openWid1(type){
	//取得现有的弹框内容
	var divHtml = $(".plusBank1 middle").html();
	
	//设置弹出框样式
	$(".plusBank1").attr("style","width:410px; margin-left:-205px;");
	//设置弹出框的标题和关闭
	$(".plusBank1 .topper").html(
			'<span class="fl fs_18">提示</span>'
	        +'<a class="fr plus_c" onClick="colseClean()"></a>'
	);
	//重写内容
	$(".plusBank1 middle").html(
			'<div class="content">'
			+'<table>'
			+'<tr>'
			+'<th>验证码</th>'
			+'<td>'
			+'<div class="inputOut">'
			+'<input type="text" class="input_all" style="width:50px;" name="imgCode" id="imgCode" maxlength="4">'
			+'<img src="" width="90" height="36" name="code" id="code" style="vertical-align:middle; margin-right:5px;" alt="图形验证码" />'
			+'<a href="javascript:;" class="blue" id="refush" onClick="refush()">换一张</a><br>'
			+'<div><span id="imgCodeMSG"></span></div>'
			+'</div>'
			+'</td>'
			+'<tr>'
			+'</table>'
			+'</div>'
			+'<div class="btnbox"><button class="btn btnSize_1 btn_blue" onClick="sureSend(\'txbdyhk\');">确认</button></div>'
	);
	//取验证码
	refush();
	//显示弹出框
	showCon_1();
}
