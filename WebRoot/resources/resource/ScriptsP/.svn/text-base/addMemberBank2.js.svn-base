var mbbOpeningBranchFlag = false;
var mbbCardNoFlag = false;

$(document).ready(function(){
	var cur_dh = $('#xinxi_nwd_8');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
	
	//回显市列表
	/*var prov = $("#prov").val();
	if(prov!="-1"){
		findCitysList();
	}*/
	
    $("#listBankName").change(function(){$("#mbbCardNo").trigger("blur")});
    
	$("#mbbCardNo").blur(function(){
		var mbbName = $('#listBankName').val();
		var mbbCardNo = $("#mbbCardNo").val();
		if(mbbName == '18'){//招商银行不验证
			if(mbbCardNo==""){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入银行卡号！");
			}else{
				mbbCardNoFlag = true;
    			$('#mbbCardNoMSG').css("display","");
    			$("#mbbCardNoMSG").removeClass().html("");
			}
		}else if(mbbName == '19' && mbbCardNo.length == 17){//交行银行17位卡号不验证
			if(mbbCardNo==""){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入银行卡号！");
			}else{
				mbbCardNoFlag = true;
    			$('#mbbCardNoMSG').css("display","");
    			$("#mbbCardNoMSG").removeClass().html("");
			}
		}else{
			var reg = /^[0-9]{16,19}$/;
			if(mbbCardNo==""){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入银行卡号！");
			}else if(!reg.test(mbbCardNo)){
				mbbCardNoFlag = false;
				$('#mbbCardNoMSG').css("display","");
				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>银行卡号格式有误！");
			}else{
				$.ajax({
		        	type: "post",
		       	 	url: "/member/compareCardNo.do",
		        	dataType: "json",
		        	data:{
		        		mbbCardNo:mbbCardNo
		       		 },
		        	async: false,
		        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        	success: function(msg){
		        		if(msg == 0){//不存在
		        			mbbCardNoFlag = true;
		        			$('#mbbCardNoMSG').css("display","");
		        			$("#mbbCardNoMSG").removeClass().html("");
		        		}else if(msg == 1){//内部已绑定
		        			if($("#doWhat2").val()=="edit"){
		        				mbbCardNoFlag = true;
			        			$('#mbbCardNoMSG').css("display","");
			        			$("#mbbCardNoMSG").removeClass().html("");
		        			}else{
		        				mbbCardNoFlag = false;
			        			$('#mbbCardNoMSG').css("display","");
			        			$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>您已成功添加该张银行卡号，不能重复添加！");
		        			}
		        		}else if(msg == 2){//银行卡格式有误
		        			mbbCardNoFlag = false;
		        			$('#mbbCardNoMSG').css("display","");
		        			$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>银行卡号格式有误！");
		        		}
		        	}   
		      });
					
			}
		}
		
		getBankNo(mbbCardNo);
		
 	});
	
	$("#mbbOpeningBranch").blur(function(){
		var mbbOpeningBranch = $("#mbbOpeningBranch").val();
		//var reg = /^[\u4e00-\u9fa5]{1,200}$/;
		if($.trim(mbbOpeningBranch)==""){
			mbbOpeningBranchFlag = false;
			$('#mbbOpeningBranchMSG').css("display","");
			$('#msg').css("display","none");
			$("#mbbOpeningBranchMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请输入开户支行！");
		}else if(mbbOpeningBranch.length<=0 || mbbOpeningBranch.length>200){
			mbbOpeningBranchFlag = false;
			$('#mbbOpeningBranchMSG').css("display","");
			$('#msg').css("display","none");
			$("#mbbOpeningBranchMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>开户支行不超过200字符！");
		}else{
			mbbOpeningBranchFlag = true;
			$('#msg').css("display","");
			$('#mbbOpeningBranchMSG').css("display","");
			$("#mbbOpeningBranchMSG").removeClass().html("");	
		}
 	});
});

function save1(){
	$("#mbbOpeningBranch").blur();
	$("#mbbCardNo").blur();
	var mbbName = $('#listBankName').val();
	if(mbbName=="-1"){
		$('#mbbNameMSG').css("display","");
		$("#mbbNameMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请选择银行名称！");
	}else{
		$('#mbbNameMSG').css("display","");
		$("#mbbNameMSG").removeClass().html("");	
	}
	var prov = $("#bankProvCode").val();
	if(prov=="-1"){
		$('#provMSG').css("display","");
		$("#provMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请选择开户省市（省）！");
	}else{
		$('#provMSG').css("display","");
		$("#provMSG").removeClass().html("");	
	}
	
	var cityName = $("#bankCityCode").val();
	if(cityName=="-1"){
		$('#cityNameMSG').css("display","");
		$("#cityNameMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>请选择开户省市（市）！");
	}else{
		$('#cityNameMSG').css("display","");
		$("#cityNameMSG").removeClass().html("");	
	}
	if(mbbOpeningBranchFlag==true && mbbCardNoFlag==true && ($('#listBankName').val() != "-1")&&($("#bankProvCode").val() != "-1")
			&&($("#bankCityCode").val() != "-1")){
		
		var mbbId = 0;
		if($("#doWhat").val() == 'edit'){
			var mbbId = $("#mbbId2").val();
		}
        var stok = "";
        if(document.getElementById ("stok")){
            stok  = document.getElementById ("stok").value;
        }
		$.ajax({
        	type: "post",
       	 	url: "/member/saveBindingBank.do",
        	dataType: "json",
        	data:{
        		realName:$('#realName').val(),
        		mbbName:$('#listBankName').val(),
        		mbbCardNo:$("#mbbCardNo").val(),
        		mbbOpeningBranch:$("#mbbOpeningBranch").val(),
        		cityName : $("#bankCityCode").val(),
        		bankName : $("#listBankName").text(),
        		doWhat:$("#doWhat").val(),
        		mbbId : mbbId,
                stok:stok
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == '1'){//保存成功
        			$('#addBankStep1').addClass('none');
    				$('#addBankStep2').addClass('none');
    				$('#addBankStep3').removeClass('none');
    				
    				$('#bankStep1').attr("class","visited_a");
        			$('#bankStep2').attr("class","visited_a");
        			$('#bankStep3').attr("class","active");
        			
        			// Adobe | zhenhua.xi | 20141031
        			var s=s_gi(s_account);
        			s.linkTrackVars="events";
        			s.linkTrackEvents="event13";
        			s.events="event13"; //绑定银行卡成功
        			npo.tl(this,'o','bdyhkcg');
        			
        			setTimeout(function(){ 
        				$('#addBankStep3').parents('tr').addClass('none');
        				$('#bankCardAdd').addClass('none');
        				$('#manageBank').removeClass('none');
        				location.reload();
        			},2000)
        		}else if(msg=='0'){//保存失败
        			$(".plusBank1 .content").html('添加银行卡失败!请重试');
        			showCon_1();
//        			document.forms[0].step.value="3-1";
//        			document.forms[0].action="/member/addBankwStep.do?doWhat=add";
//        			document.forms[0].submit();
        		}else if(msg == '2'){//修改成功
        			$('#changeSuccess').text('修改银行卡成功！');
        			$('#addBankStep1').addClass('none');
    				$('#addBankStep2').addClass('none');
    				$('#addBankStep3').removeClass('none');
    				
    				$('#bankStep1').attr("class","visited_a");
        			$('#bankStep2').attr("class","visited_a");
        			$('#bankStep3').attr("class","active");
        			
        			setTimeout(function(){ 
        				$('#addBankStep3').parents('tr').addClass('none');
        				$('#bankCardAdd').addClass('none');
        				$('#manageBank').removeClass('none');
        				location.reload();
        				},2000)
//        			document.forms[0].step.value="3";
//        			document.forms[0].action="/member/addBankxStep.do?doWhat=edit";
//        			document.forms[0].submit();
        		}else if(msg == '3'){//修改失败
        			$(".plusBank1 .content").html('修改失败,您的银行卡状态异常,请联系客服！');
        			showCon_1();
//        			document.forms[0].step.value="3-1";
//        			document.forms[0].action="/member/addBankwStep.do?doWhat=edit";
//        			document.forms[0].submit();
        		}
//        		else if(msg == -2){//修改失败
//        			$(".plusBank1 .content").html('修改失败,您的部分资金需要使用该银行卡才能提现，不能修改卡号。');
//        			showCon_1();
//        		}else if(msg == -3){//修改失败
//        			$(".plusBank1 .content").html('修改失败,您的部分资金正在使用该银行卡提现中，不能修改。');
//        			showCon_1();
//        		}else if(msg==4){
//        			showMsg("不可重复绑定");
//        		}
        		else if(msg=='5'){//验证银行卡无效  10月16  addby:sunyang
                	$('#mbbCardNoMSG').css("display","");
    				$("#mbbCardNoMSG").removeClass().addClass("prompt_1 error_1").html("<em></em>银行卡号无效！");
                }else if(msg==-10){
                	showMsg("验证身份失败，请重新验证！");//安全bug zhuzy bug#10158
                }else{
                	showMsg(msg);
                }
        	}   
      }); 
	}
}

//省
function findCitys(){ 
	$("#bankCityCode option:gt(0)").remove(); 
	var bankProvCode = $("#bankProvCode").val(); 
	$.ajax({
		type : "POST", 
		url : "/member/getProvSelectInterface.do", 
		data : "provCode=" + bankProvCode, 
		success : function(result) { 
			data = result;
			//删除ul中全部的li，初始化显示数据，后台保存input
			$("#bankCityCode option").remove(); 
			$("#cityCode").html("请选择");
			$("#cityName").attr("value","-1");
			//遍历json的数据
			$(data).each(function(index,domEle){ 
				var option = "<option value='"+data[index].cityCode+"'>"+data[index].cityName+"</option>";
				$("#bankCityCode").append(option); 
			});
		} 
	}); 
}
$('#listBankName').change(function(){
	$('#backBank').remove();
});
$('#bankProvCode').change(function(){
	$('#backPro').remove();
});

/* 市选择 */
function city1(code,name){
	$("#cityCode").html(name);
	$("#cityName").attr("value",code);
}

//回显市列表
function findCitysList(){ 
	$("#bankCityCode li:gt(0)").remove(); 
	var bankProvCode = $("#prov").val(); 
	$.ajax({
		type : "POST", 
		url : "/member/getProvSelectInterface.do", 
		data : "provCode=" + bankProvCode, 
		success : function(result) { 
			data = $.parseJSON(result); 
			//遍历json的数据
			$(data).each(function(index,domEle){ 
				var li = "<li onclick='city1(\""+domEle.cityCode+"\",\""+domEle.cityName+"\")' data-value='"+domEle.cityCode+"'>"+domEle.cityName+"</li>";
				$("#bankCityCode").append(li); 
			});
		} 
	}); 
}

//根据卡号取得银行卡编号、
function getBankNo(cardNum){
	$.ajax({
		type : "POST", 
		url : "/pay/yintong/getBankCode.do", 
		data : "banCardNum=" + cardNum, 
		success : function(result) {
			$('#listBankName').val(result);
		} 
	}); 
}
