/**
 * add by lingjs at 20160429 
 */

var commonAlert = commonAlert || {};
commonAlert.init = function(){
	commonAlert.initialize();	
}

commonAlert.initialize = function(){
	//初始化方法	
}

//JS获取指定范围的随机数
commonAlert.GetRandomNum = function(Max,Min){
	var Range = Max - Min;  
    var Rand = Math.random();  
    return(Min + Math.round(Rand * Range));  
}

				commonAlert.alertcontent = '<div class="alertContainer">\
<!-- 遮罩 -->\
<div class="mask" style="display:none"></div>\
<div class="popbox wid_w480 popminbox pad_b30 swl" \
	style="margin-left: -220px; margin-top: -117.5px; display:none;background: #fff;">\
	<div class="topper clearfix">\
		<span class="fl fs_16 fc_3" id="container_title">提示</span><a id="container_close" class="fr icon_user icon_user_bankclose  slClose mar_t10 mar_b10"></a>\
	</div>\
	<div class="middle">\
		<div class="content pad_t30 pad_b30 txt_center">\
			<div class="fs_16 fc_3"><em id="container_img" class="icon_base icon_24 icon_base_isConfim mar_r10"></em>\
				<span id="container_content">提示ddd</span>\
			</div>\
		</div>\
	</div>\
	<div class="bottom txt_center">\
		<a class="btn btn_36c btn_size100 btnclose" id="container_ok">确认</a>\
		<a class="btn btn_borblue btn_size100 btnclose mar_l20 mar_t20" id="container_cancel">取消</a>\
	</div>\
</div>\
</div>\
';

//通常弹框
commonAlert.show = function(){
	if(arguments.length > 0){
		this.baseShow("提示","",arguments[0],"确认","取消",arguments[1],arguments[2],arguments[3]);
	}
}

//通常弹框
commonAlert.showOK = function(){
	if(arguments.length > 0){
		this.baseShow("提示","",arguments[0],"确认","",arguments[1],arguments[2],arguments[3]);
	}
}

//showinfo
commonAlert.showinfo = function(){
	this.baseShow(arguments[1],"",arguments[0],"确认","取消",arguments[2],arguments[3]);
}

//error弹框
commonAlert.showerror = function(){
	
}

//报警弹框
commonAlert.showalarm = function(){
	
}



commonAlert.baseShow = function(){
	//初始化
	if($(".alertContainer")){
		var containhtml = $(".alertContainer").html();
		if(containhtml.length <= 0){
			$(".alertContainer").html(this.alertcontent);
		}
	}
	
	var pre = "#container_";
	//使用 arguments[0] 去获取参数列表。
	
	//设置title文案
	$(pre + "title").html(arguments[0]);
	//设置提示图标
	//$(pre + "img").html(arguments[1]);
	//设置文案
	$(pre + "content").html(arguments[2]);
	//设置ok 文案
	$(pre + "ok").html(arguments[3]);
	//设置cancel 方案
	$(pre + "cancel").html(arguments[4]);	
	if(arguments[4] != ""){
		//$(pre + "cancel").css("display","block");//如果为空则不显示
	}else{
		$(pre + "cancel").css("display","none");//如果为空则不显示
	}
	
	this.bindevent(arguments[5],arguments[6],arguments[7]);
}

commonAlert.showmask = function(){
	$(".swl").slideDown();
	$('.mask').show();
}

commonAlert.showselector = function(){
	$(selector).slideDown();
	$('.mask').show();
}

commonAlert.hidemask = function(){
	$(".swl").hide();
	$('.mask').hide();
}

commonAlert.bindevent = function(fun1,fun2,fun3){
	this.showmask();
	this.dl("#container_ok",fun1);
	this.dl("#container_cancel",fun2);	
	this.dl("#container_close",fun3);
}

//绑定事件
commonAlert.dl = function(id,callback){
	$(id).unbind();
	if(callback != null){
		$(id).bind("click",callback);	
	}	
	$(id).bind("click",this.hidemask);
}

$(function(){
	$("body").append(commonAlert.alertcontent);
});

