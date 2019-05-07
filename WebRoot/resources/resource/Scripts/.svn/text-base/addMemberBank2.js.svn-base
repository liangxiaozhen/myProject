var mbbOpeningBranchFlag = false;
var mbbCardNoFlag = false;

$(document).ready(function(){
	
    $("#bankName").change(function(){$("#mbbCardNo").trigger("blur")});
    // 校验银行卡号
	$("#mbbCardNo").blur(function(){
		var mbbName = $('#bankName').val();
		var mbbCardNo = $("#mbbCardNo").val();
		var baseid = $("#baseid").val();
//		var flag = luhmCheck(mbbCardNo);
		var flag = true;
		var reg = /^[0-9]{16,19}$/;
		if(mbbCardNo==""){
			mbbCardNoFlag = false;
			$('#mbbCardNoMSG').css("display","");
			$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入银行卡号！");
		}else if(!reg.test(mbbCardNo) && !flag){
			mbbCardNoFlag = false;
			$('#mbbCardNoMSG').css("display","");
			$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>银行卡号格式有误！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "compareCardNo.action",
	        	dataType: "json",
	        	data:{
	        		mbbCardNo : mbbCardNo,
	        		baseid : baseid
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 0){//不存在
	        			mbbCardNoFlag = true;
	        			$('#mbbCardNoMSG').css("display","");
	        			$("#mbbCardNoMSG").removeClass().html("");
	        		}else if(msg == 1){//内部已绑定
        				mbbCardNoFlag = false;
	        			$('#mbbCardNoMSG').css("display","");
	        			$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>您已成功添加该张银行卡号，不能重复添加！");
	        		}else if(msg == 2){//银行卡格式有误
	        			mbbCardNoFlag = false;
	        			$('#mbbCardNoMSG').css("display","");
	        			$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>银行卡号格式有误！");
	        		}
	        	}   
	      });
				
		}
 	});
	
	$("#mbbOpeningBranch").blur(function(){
		var mbbOpeningBranch = $("#mbbOpeningBranch").val();
		//var reg = /^[\u4e00-\u9fa5]{1,200}$/;
		if($.trim(mbbOpeningBranch)==""){
			mbbOpeningBranchFlag = false;
			$('#mbbOpeningBranchMSG').css("display","");
			$('#msg').css("display","none");
			$("#mbbOpeningBranchMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入开户支行！");
		}else if(mbbOpeningBranch.length<=0 || mbbOpeningBranch.length>200){
			mbbOpeningBranchFlag = false;
			$('#mbbOpeningBranchMSG').css("display","");
			$('#msg').css("display","none");
			$("#mbbOpeningBranchMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>开户支行不超过200字符！");
		}else{
			mbbOpeningBranchFlag = true;
			$('#msg').css("display","");
			$('#mbbOpeningBranchMSG').css("display","");
			$("#mbbOpeningBranchMSG").removeClass().html("");	
		}
 	});
});

function save(){
    $("#mbbOpeningBranch").blur();
	$("#mbbCardNo").blur();
	var mbbName = $('#bankName').val();
	if(mbbName=="-1"){
		$('#mbbNameMSG').css("display","");
		$("#mbbNameMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请选择银行名称！");
	}else{
		$('#mbbNameMSG').css("display","");
		$("#mbbNameMSG").removeClass().html("");	
	}
	var prov = $("#bankProvCode").val();
	if(prov=="0"){
		$('#provMSG').css("display","");
		$("#provMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请选择开户省市（省）！");
	}else{
		$('#provMSG').css("display","");
		$("#provMSG").removeClass().html("");	
	}
	var cityName = $("#bankCityCode").val();
	if(cityName=="0"){
		$('#cityNameMSG').css("display","");
		$("#cityNameMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请选择开户省市（市）！");
	}else{
		$('#cityNameMSG').css("display","");
		$("#cityNameMSG").removeClass().html("");	
	}
	var areaName = $("#bankAreaCode").val();
	if(areaName=="0"){
		$('#areaNameMSG').css("display","");
		$("#areaNameMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请选择开户省市（地区）！");
	}else{
		$('#areaNameMSG').css("display","");
		$("#areaNameMSG").removeClass().html("");	
	}
	if(mbbOpeningBranchFlag==true && mbbCardNoFlag==true && ($('#bankName').val() != "-1") && ($("#bankProvCode").val() != "0")
			&&($("#bankCityCode").val() != "0") && ($("#bankAreaCode").val() != "0")){
		$("#bindCardSave").removeAttr('onclick').text('处理中......');
		$.ajax({
        	type: "post",
       	 	url: basePath +"/huishang/bindCard/saveBindingBankCard.action",
        	dataType: "json",
        	data:{
        		realName:$('#realName').val(),
        		mbbName:$('#bankName').val(),
        		mbbCardNo:$("#mbbCardNo").val(),
        		mbbOpeningBranch:$("#mbbOpeningBranch").val(),
        		cityName : $("#bankCityCode").val(),
        		bankName : $("#bankName").text()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == '1'){//保存成功
        			$("#addBankcard-pop-tx,.bg").hide();
        			show("银行卡绑定成功","bindCard(this);");
         		}else if(msg=='0'){//保存失败
        			$('#errorMsg').css("display","");
        			$("#errorMsg").html('添加银行卡失败!请重试');
        			$("#bindCardSave").attr('onclick',"save()").text('确认添加');
        		}
        		else if(msg=='5'){//验证银行卡无效  10月16  addby:sunyang
                	$('#mbbCardNoMSG').css("display","");
    				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>银行卡号无效！");
        			$("#bindCardSave").attr('onclick',"save()").text('确认添加');
                }
        	}   
      }); 
	}
}

function bindCard(){
	window.location.href=basePath+"/user/bankcard/bindCard.action";
}

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
