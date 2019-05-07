<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_干将会员中心</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
<style type="text/css">
	.disabled{    
		cursor: not-allowed;
	    filter: alpha(opacity=65);
	    -webkit-box-shadow: none;
	    box-shadow: none;
	    opacity: .65;
    }
	.disabled:hover{
 		cursor: not-allowed;
	    filter: alpha(opacity=65);
	    -webkit-box-shadow: none;
	    box-shadow: none;
	    opacity: .65;
	}
	
	
</style>
 </head>
<body>
<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧 -->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- right start -->
			<div class="fl perCerterR bor_l bor_r">
				<div class="fl pad_30 wid_w900 min_h500 clearfix">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_6">安全中心</span><span
							class="fc_6">银行存管开户</span>
					</div>
					<div class="fl pad_t20 clearfix" style="width: 100%;">
						<!---1 start---->
 							<input type="hidden" id="backUrl" value="">
							<table class="table_3 form fs_14">
								<tbody>
									<tr class="pad_t10" valign="top"></tr>
   									<tr>
										<th class="th_l pad_t10 pad_b20 leftside pad_r20" valign="top">真实姓名</th>
										<td class="rightside pad_b20">
										<input type="text" name="realname" id="realname" readonly="readonly" disabled="disabled"
											class="input_all ui-input w320-input" value="${baseAccountInfo.realname }">
 										<br>
										<span id="realnameMSGError" style="display: none"
											class="prompt_1"><i></i></span>
										</td>
									</tr>
									
									<tr>
										<th class="pad_t10 pad_b20 leftside pad_r20" valign="top">身份证号</th>
										<td class="rightside pad_b20">
										<input type="text" disabled="disabled"
											name="identity" id="identity" readonly="readonly"
											class="input_all ui-input w320-input" value="${baseAccountInfo.certificationnumber }">
  										<span id="identityMSGError" style="display: none"
											class="prompt_1 error_1"><i></i></span></td>
									</tr>
									
									<tr>
										<th class="pad_t10 pad_b20 leftside pad_r20" valign="top">银行卡号</th>
										<td class="rightside pad_b20"><input type="text"
											name="BankCard" id="BankCard"
											class="input_all ui-input w320-input" maxlength="19">
  										<span id="openAnAccountBankCardError" style="display: none"
											class="prompt_1 error_1"><i></i></span></td>
									</tr>
									
									<tr>
										<th class="th_l pad_t10 pad_b20 leftside pad_r20" valign="top">开卡手机号码</th>
										<td class="rightside pad_b20"><input type="text"
											name="phone" id="phone" maxlength="11"
											class="input_all ui-input w320-input" > 
										<span id="openAnAccountPhoneMSGError" style="display: none"
											class="prompt_1 error_1"><i></i></span>
										</td>
									</tr>
									 
									<tr>
										<th></th>
										<td>
											<button class="btn btn_blue btn_size120" type="button" value="提交开户" onclick="openAnAccount(this);" id="openAnAccountSubmit">提交开户</button>
 										</td>
									</tr>
								</tbody>
							</table>
 						<div class="cleafix"></div>
 				</div>
			</div>
			<!-- right end -->
 		</div>
	</div>
	</div>
	 <div id="show"></div>
<!-- 尾部 -->
<%--  <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

 
//开户
function openAnAccount(obj){
	var BankCard = $("#BankCard").val();
	var phone 	 = $("#phone").val();
	  
// 	if(isEmpty(BankCard)){
// 		$("#openAnAccountBankCardError").show().text("请输入银行卡号");
// 		$("#BankCard").focus();
// 		return ;
// 	}else{
// 		if(!luhmCheck(BankCard)){
// 			$("#openAnAccountBankCardError").show().text("请输入正确的银行卡号");
// 			$("#BankCard").focus();
// 			return ;
// 		} 
// 		$("#openAnAccountBankCardError").text("").hide();
// 	}
	
// 	if(isEmpty(phone)){
// 		$("#openAnAccountPhoneMSGError").show().text("请输入手机号码");
// 		$("#phone").focus();
// 		return ;
// 	}else{
// 		if(!is_phone(phone)){
// 			$("#openAnAccountPhoneMSGError").show().text("请输入正确的手机号码");
// 			$("#phone").focus();
// 			return ;
// 		}
// 		$("#openAnAccountPhoneMSGError").text("").hide();
//  	}
	$("#openAnAccountSubmit").removeAttr("onclick").text("开户处理中...").addClass("disabled");
   	$.ajax({
		type:"post",
		url:basePath + "/huishang/userRegister/OpenAnAccount.action",
		error:function(){$("#openAnAccountSubmit").attr("onclick","openAnAccount(this)").text("提交开户").removeClass("disabled");},
		data:{"bankcard":BankCard,"phone":phone},
		success:function(data){
			$("#openAnAccountSubmit").attr("onclick","openAnAccount(this)").text("提交开户").removeClass("disabled");;
			 var obj = $.parseJSON(data);
			 if(obj.result == "logout"){
 				show("操作超时!请重新登录","logout(this);");
  			 }else if(obj.result == "success"){
 				 show(obj.Msg,"other(this);");
 			 }else if(obj.result == "fail"){
 				show(obj.Msg,"delhide(this)");
			 }
		}
		
	});
}

function logout(){
	window.location.href = basePath + "/user/tologin.action";
}

function other(){
	window.location.href = basePath + "/user/tologin.action";
}

/*手机格式*/
function is_phone(str){
	 var regExp = /^(13|15|18|17)\d{9}$/;
	 return regExp.test(str);
};

function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
};

//Luhm校验规则：16位银行卡号（19位通用）:
//1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
//2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
//3.将加法和加上校验位能被 10 整除。
//方法步骤很清晰，易理解，需要在页面引用Jquery.js    
//bankno为银行卡号 banknoInfo为显示提示信息的DIV或其他控件
function luhmCheck(bankno) {
	var lastNum = bankno.substr(bankno.length - 1, 1);//取出最后一位（与luhm进行比较）

	var first15Num = bankno.substr(0, bankno.length - 1);//前15或18位
	var newArr = new Array();
	for (var i = first15Num.length - 1; i > -1; i--) { //前15或18位倒序存进数组
		newArr.push(first15Num.substr(i, 1));
	}
	var arrJiShu = new Array(); //奇数位*2的积 <9
	var arrJiShu2 = new Array(); //奇数位*2的积 >9

	var arrOuShu = new Array(); //偶数位数组
	for (var j = 0; j < newArr.length; j++) {
		if ((j + 1) % 2 == 1) {//奇数位
			if (parseInt(newArr[j]) * 2 < 9)
				arrJiShu.push(parseInt(newArr[j]) * 2);
			else
				arrJiShu2.push(parseInt(newArr[j]) * 2);
		} else
			//偶数位
			arrOuShu.push(newArr[j]);
	}

	var jishu_child1 = new Array();//奇数位*2 >9 的分割之后的数组个位数
	var jishu_child2 = new Array();//奇数位*2 >9 的分割之后的数组十位数
	for (var h = 0; h < arrJiShu2.length; h++) {
		jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
		jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
	}

	var sumJiShu = 0; //奇数位*2 < 9 的数组之和
	var sumOuShu = 0; //偶数位数组之和
	var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
	var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
	var sumTotal = 0;
	for (var m = 0; m < arrJiShu.length; m++) {
		sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
	}

	for (var n = 0; n < arrOuShu.length; n++) {
		sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
	}

	for (var p = 0; p < jishu_child1.length; p++) {
		sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
		sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
	}
//	计算总和
	sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu)
	+ parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);

//	计算Luhm值
	var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
	var luhm = 10 - k;

	if (lastNum == luhm) {
		return true;
	} else {
		return false;
	}
}

function show(text,fn){
	var html = "<div id='mask' class='mask' style='display:block;'></div>"+
	"	<div class='popup' style='display:block;'>"+
	"		<div class='yqtx'>"+
	"		 	<span>友情提示</span>"+
	"		 	<span class='btn_close yqtx_right' onclick='delhide(this)'>x</span>"+
	"		</div>"+
	"	 	"+
	"		<div class='tsnr'>"+
	"		 	<div class=''>"+
	"		 		<div class='tsnr_nr'>"+
	"		 			<p id='content'>"+text+"</p>"+
	"		 		</div>"+
	"		 		"+
	"		 		<div class='tsnr_queding'>"+
	"		 			<p class='btn_close 'onclick='"+fn+"'>确定</p>"+
	"		 		</div>"+
	"		 	</div>"+
	"		</div>"+
	"	</div>";
  	$("#show").append(html);
	center(".popup");
	var $Popup = $('.popup');
  	var $tsnr_queding = $('.tsnr_queding');
   	$tsnr_queding.css({
   		left: ($Popup.width() - $tsnr_queding.width()) / 2+ 'px',
	});
}

function delhide(){
	$(".mask").remove();
	$(".popup").remove();
 }
//居中
function center(obj){
	 var windowWidth = document.documentElement.clientWidth;   
	 var windowHeight = document.documentElement.clientHeight;   
	 var popupHeight = $(obj).height();   
	 var popupWidth = $(obj).width();    
	 $(obj).css({   
	  "position": "absolute",   
	  "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),   
	  "left": (windowWidth-popupWidth)/2   
	 });  
};
</script>
 </body>
</html>