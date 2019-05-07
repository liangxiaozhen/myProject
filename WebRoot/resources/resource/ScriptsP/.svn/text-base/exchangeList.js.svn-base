/**
 * 
 */
//金钱兑换积分
function exchange(){
	//可用金钱获取
	var money = $("#money").val();
	if(parseInt(money) <= 0){
		//10月22号 调整交互 editby:sunyang
		$("#exchangeOk").css("display", "");
		$("#exchangeOk").removeClass().addClass("prompt_2 remind_2").html("<i class='mar_l5 mar_r5'></i><span class='vertical_top'>抱歉，你的金钱不足！</span>");
	} else{
		//保存
		var url = "/member/integralGet.do";
        var stok = "";
        if(document.getElementById ("stok")){
            stok  = document.getElementById ("stok").value;
        }
        $.post(url,{
			"money":money,
            stok:stok
		},function(result) {
			if (result == 'sucess') {
				$("#exchangeOk").css("display", "none");
				//兑换成功
				$("#money").val("0");
				$("#canUseMoney").html("0");
				$("#canExchange").html("0");
				$("#exchangeOk").append("已成功兑换"+money+"积分");
				$("#exchangeOk").show();
				//积分刷新
				var gr = parseInt($("#grade").val())+parseInt(money);
				$("#grade").val(gr);
				$("#emgrade").text(gr);
			} else {
				//10月22号 调整交互 editby:sunyang
				$("#exchangeOk").css("display", "");
				$("#exchangeOk").removeClass().addClass("prompt_2 remind_2").html("<i class='mar_l5 mar_r5'></i><span class='vertical_top'>抱歉，你的金钱不足！</span>");
			}
		});
	}
}

//红包兑换
//function convert(type){
//	//可用积分
//	var grade = $("#grade").val();
//	
//	//算出单张所需积分
//	var oneUse = willUse(type,1);
//	if(parseInt(oneUse) > parseInt(grade)){
//		//设置消息标题
//		$("#msgTitle").html("消息");
//		//设置消息内容
//		$("#msgContent").html("抱歉，您的可用积分不足！");
//		showCon_0();
//		return false;
//	}else{
//		//设置礼品名称
//		if(type == 1){
//			//礼品类型说明
//			$('#typeText').html("5元投资红包");
//		}else if(type == 2){
//			//礼品类型说明
//			$('#typeText').html("10元投资红包");
//		}else if(type == 3){
//			//礼品类型说明
//			$('#typeText').html("20元投资红包");
//		}else if(type == 4){
//			//礼品类型说明
//			$('#typeText').html("50元投资红包");
//		}else if(type == 5){
//			//礼品类型说明
//			$('#typeText').html("提现券");
//		}
//		//设置礼品类别
//		$('#type').val(type);
//		//设置兑换份数
//		$('#num').val(1);
//		
//		//设置消耗积分
//		$('#useIntegral').html(oneUse);
//		//剩余积分计算
//		var residueIntegral = parseInt(grade) - parseInt(oneUse);
//		//设置剩余积分
//		$('#residueIntegral').html(residueIntegral);
//		
//		//初始化Message清空
//		$("#errMsg").html("");
//		$("#errNumMsg").html("");
//		
//		//弹出确认页面
//		showCon_1();
//	}
//}

//判断提现券是否还有
function checkNum(residueNum,type){
	if(residueNum <= 0){
		//设置消息标题
		$("#msgTitle").html("消息");
		//设置消息内容
		$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_18'>抱歉，提现券已兑完，明天再来抢兑吧~</span>");
		
		showCon_0();
		return false;
	}
	return exchangeSave(type);
}

//红包兑换提交
function exchangeSave(type){
	//取得红包类别
	//var type = $('#type').val();
	//获取兑换数量
	var numId = "#num"+type;
	var num = $(numId).val();
	if(num == ''){
		num = 0;
	}
	//获取剩余积分
	var residueIntegral = $('#residueIntegral').html();
	//剩余积分<0
	if(parseInt(residueIntegral) < 0){
		$("#errMsg").html("抱歉，您的可用积分不足！");
		return false;
	}else{
		$("#errMsg").html("");
	}
	//输入份数不为空
	if(num <= 0){
		$("#errNumMsg").html("请输入有效兑换份数！");
		return false;
	}else{
		$("#errNumMsg").html("");
	}
	
	//保存
	var url="/member/exchangeGift.do";
	$.post(url,{
		"type":type,
		"num":num
	},function(result){
		if (result == 'sucess') {
			// Adobe | Begin | zhenhua.xi | 20141103
			adobeSend4jfdh(type,num);
			// Adobe | End
			
			//设置消息标题
			$("#msgTitle").html("兑换成功");
			$("#openMsg").removeClass("mini");
			$("#msgCloseAll").addClass("plus_c");
			
			
			//设置消息内容
			$("#msgContent").html("恭喜您，兑换成功！");
			//设置 X 关闭调用刷新
			$("#msgCloseAll").removeAttr("onclick");
			$("#msgCloseAll").attr("onclick","allClose()");
			//设置 确定 关闭调用刷新
			$("#msgClose").removeAttr("onclick");
			$("#msgClose").attr("onclick","allClose()");
			
			if(type == 5){
				$("#gktouzhi").remove();
			}
			
			//隐藏掉常用提示
			$("#chenggong").show();
			//显示兑换成功提示
			$("#changyong").hide();
			
			//关闭兑换弹出框
			closeAll_1();
			//弹出兑换成功框
			showCon_0();
			
			/*art.dialog({
				title: '兑换成功',
			    content: '<div class="clearfix page200" style="width:300px;"><div align="center"><span >恭喜您兑换成功！</span></div><div align="center" style="margin-top:20px;margin-bottom: 30px">	<a style="font-size:14px;" class="btn btn-mini btn-blue btn-b" onclick="okClose();">确定</a>&nbsp;&nbsp;<a class="blue" onclick="openNew();" style="cursor:pointer;">查看兑换记录</a></div></div>',
			    close: function () {
			    	window.location.reload();
			    }
			});*/

		} else if(result == 'noPoint'){
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_18'>很抱歉，您没有兑换权限！</span>");
		} else if(result == 'notMuch'){
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_18'>很遗憾，提现券剩余数量不足，请重新兑换！</span>");
		} else if(result == 'unlawful'){
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_18'>用户信息有误,无法兑换！</span>");
		} else if(result == 'numZero'){
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_18'>兑换数量有误，无法兑换！</span>");
		} else if(result == 'notHave'){
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_18'>很遗憾，今日提现券兑换结束，请明日再来！</span>");
		} else {
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("<i class='ico_all size24 img_false24'></i><span class='fs_14'>积分不足兑换失败！</span>");
		}
		showCon_0();
	});
}

//生日券领取
function exchangeBirth(){
	// 保存
	var url = "/member/exchangeBirth.do";
	
	$.post(url,function(result) {
		if (result == 'sucess') {
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("恭喜您，领取成功！");
			//设置 X 关闭调用刷新
			$("#msgCloseAll").removeAttr("onclick");
			$("#msgCloseAll").attr("onclick","allClose()");
			//设置 确定 关闭调用刷新
			$("#msgClose").removeAttr("onclick");
			$("#msgClose").attr("onclick","allClose()");
		} else if(result == 'unlawful'){
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("用户信息有误，无法领取！");
		} else {
			//设置消息标题
			$("#msgTitle").html("消息");
			//设置消息内容
			$("#msgContent").html("领取失败，您已领取生日红包或您没有领取权限！");
		}
		showCon_0();
	});

}

//赶快去投资
function touzhi(){
	//关闭成功框
	closeAll_0();
	//页面刷新
	window.location.reload();
	//打开待借列表页面
	window.open("http://www.niwodai.com/xiangmu/");
}

//查看兑换记录
function openNew(){
	//关闭成功框
	closeAll_0();
	//页面刷新
	window.location.reload();
	//打开兑换记录页面
	window.open("/member/integralView.html");
}

//消息框关闭 刷新页面
function allClose(){
	closeAll_0();
	//页面刷新
	window.location.reload();
}

//计算消耗积分
function willUse(type,num){
 	var score = 0;
	if(type == 1 || type == 5){//5元券和提现券
		score = num * 500;
	}else if(type == 2){//10元券
		score = num * 1000;
	}else if(type == 3){//20元券
		score = num * 2000;
	}else if(type == 4){//50元券
		score = num * 5000;
	}else if(type == 7){//100元券
		score = num * 10000;
	}else if(type == 8){//200元券
		score = num * 20000;
	}else if(type == 9){//500元券
		score = num * 50000;
	}else if(type == 10){//1000元券
		score = num * 100000;
	}
	return score;
}

//减号
function minus(type){
	var num = 0;
	var numId = "#num"+type;
	var useIntegralMsg = "#useIntegral"+type;
	if($(numId).val() != ''){
		num = parseInt($(numId).val()) - 1;
	}
	if(num < 1){
		num = 1;
	}
	$(numId).val(num);
	//取得红包类别
	//var type = $('#type').val();
	//计算消耗积分
	var use = willUse(type,num);
	//设置消耗积分显示
	$(useIntegralMsg).html(use);
	//取得可用积分
	var grade = $("#grade").val();
	//剩余积分计算
	var residueIntegral = parseInt(grade) - parseInt(use);
	
	//剩余积分<0
	if(residueIntegral < 0){
		//设置剩余积分
		$('#residueIntegral').html("0");
		$("#errMsg").html("抱歉，您的可用积分不足！");
	}else{
		//设置剩余积分
		$('#residueIntegral').html(residueIntegral);
		$("#errMsg").html("");
	}
	//输入份数不为空
	if(num <= 0){
		$("#errNumMsg").html("请输入有效兑换份数！");
	}else{
		$("#errNumMsg").html("");
	}
}

//加号
function add(type){
	var num = 0;
	var numId = "#num"+type;
	var useIntegralMsg = "#useIntegral"+type;
	if($(numId).val() != ''){
		num = parseInt($(numId).val()) + 1;
	}else{
		num = num + 1;
	}
	
	//一次最多兑换99份
	if(num > 99){
		num = 99;
	}
	$(numId).val(num);
	//取得红包类别
	//var type = $('#type').val();
	//计算消耗积分
	var use = willUse(type,num);
	//设置消耗积分显示
	$(useIntegralMsg).html(use);
	//取得可用积分
	var grade = $("#grade").val();
	//剩余积分计算
	var residueIntegral = parseInt(grade) - parseInt(use);
	
	//剩余积分<0
	if(residueIntegral < 0){
		//设置剩余积分
		$('#residueIntegral').html("0");
		$("#errMsg").html("抱歉，您的可用积分不足！");
	}else{
		//设置剩余积分
		$('#residueIntegral').html(residueIntegral);
		$("#errMsg").html("");
	}
	//输入份数不为空
	if(num <= 0){
		$("#errNumMsg").html("请输入有效兑换份数！");
	}else{
		$("#errNumMsg").html("");
	}
}

//兑换份数输入验证
function chkPrice(obj){
	//保证输入的为正整数
	obj.value = obj.value.replace(/[^\d]|^0/g,"");
	if(obj.value > 99){
		obj.value = 99;
		return false;
	}
}

//兑换份数和消耗积分/剩余积分联动
function change(obj,type){
	//取得兑换份数
	var num = obj.value.replace(/[^\d]/g,"");
	var useIntegralMsg = "#useIntegral"+type;
	//去的红包类别
	//var type = $('#type').val();
	//取得消耗积分
	var score = $(useIntegralMsg).html();
	if(num == ''){
		num = 0;
	}
	//计算消耗积分
	var use = willUse(type,num);
	//取得可用积分
	var grade = $("#grade").val();
	//剩余积分计算
	var residueIntegral = parseInt(grade) - parseInt(use);
	if(score != use){
		//设置消耗积分
		$(useIntegralMsg).html(use);
		if(residueIntegral < 0){
			//设置剩余积分
			$('#residueIntegral').html("0");
		}else{
			$('#residueIntegral').html(residueIntegral);
		}
	}
	//剩余积分<0
	if(residueIntegral < 0){
		$("#errMsg").html("抱歉，您的可用积分不足！");
	}else{
		$("#errMsg").html("");
	}
	//输入份数不为空
	if(num <= 0){
		$("#errNumMsg").html("请输入有效兑换份数！");
	}else{
		$("#errNumMsg").html("");
	}
	
	//setTimeout("change()",100);
}

/*$(document).ready(function(){
	//金钱兑换积分成功消息隐藏
	$("#exchangeOk").hide();
	
	var cur_dh = $('#xinxi_nwd_9');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus'); 
});*/


// Adobe | Begin | zhenhua.xi | 20141103
function adobeSend4jfdh(type, num){
	var data = [];
	data[0]=[0,"N/A"];
	data[1]=[500,"5元投资抵用券"];
	data[2]=[1000,"10元投资抵用券"];
	data[3]=[2000,"20元投资抵用券"];
	data[4]=[5000,"50元投资抵用券"];
	data[7]=[10000,"100元投资抵用券"];
	data[8]=[20000,"200元投资抵用券"];
	data[9]=[50000,"500元投资抵用券"];
	data[10]=[100000,"1000元投资抵用券"];
	data[5]=[500,"提现券"];
	
	var s = s_gi(s_account);
	s.linkTrackVars = "eVar30,events";
	s.linkTrackEvents = "event17,event18";
	s.eVar30 = data[type][1];						//兑换的相应产品
	s.events = "event17,event18="+data[type][0]*num; //积分兑换事件, 积分兑换消耗的积分数  -- 修改
	npo.tl(this,'o','jfdh');
}
// Adobe | End
/**
 * 积分流量兑换
 */
function exchangeFlow(eleId,productId){
	$("#eleId").val(eleId);
	$("#productId").val(productId);
	$("#sure").show();
	$("#cancel").show();
	$("#continue").hide();
	showCon_0();
}
function doExchangeFlow(){
	$("#sure").html("提交中...");
	$("#sure").attr("disabled",true);
	var eleId=$("#eleId").val();
	var url="/integral/CNC/buyFlow.do";
	var token=$("#token").val();
	var productId=$("#productId").val();
	$("#"+eleId).attr("disabled",true);
	if(productId==undefined||productId==""){
		return;
	}
	$.post(url,{
		"fpId":productId,
		"token":token
	},function(result){
		if(result){
			var error=result.error;
			var success=result.success;
			var orderId=result.orderId;
			var cpOrderNo=result.cpOrderNo;
			$("#sure").hide();
			$("#cancel").hide();
			$("#continue").show();
			if(error){//下单失败
				$("#"+eleId).removeClass("btn_bgf60");
				$("#"+eleId).addClass("btn_f5");
				//设置消息标题
				$("#msgTitle").html("兑换失败");
				//设置消息内容
				$("#msgContent").html(error);
				//弹出框
				showCon_0();
			}else if(success){
				//设置消息标题
				$("#msgTitle").html("兑换成功");
				//设置消息内容
				$("#msgContent").html("兑换申请已提交，运营商承诺48小时内到账，请等待。");
				//弹出框
				showCon_0();
				$("#"+eleId).removeAttr("disabled");
				/*var queryUrl="/integral/CNC/findTransInfo.do";
				var interval=setInterval(function(){
					$.post(queryUrl,{
						"cpOrderNo":cpOrderNo,
						"orderId":orderId
						},function(res){
							var timeOut=res.timeOut;
							var failed=res.failed;
							var success=res.success;
							var processing=res.processing;
							if(timeOut){
								clearInterval(interval);
								//设置消息标题
								$("#msgTitle").html(timeOut);
								//设置消息内容
								$("#msgContent").html(timeOut);
								//弹出框
								showCon_0();
							}
							else if(failed){
								clearInterval(interval);
								//设置消息标题
								$("#msgTitle").html("兑换失败");
								//设置消息内容
								$("#msgContent").html(failed);
								//弹出框
								showCon_0();
							}
							else if(success){
								clearInterval(interval);
								//设置消息标题
								$("#msgTitle").html("兑换成功");
								//设置消息内容
								$("#msgContent").html(success);
								//弹出框
								showCon_0();
							}
							
					},"json");
				},2000);*/
			}
		}
	},"json");
}