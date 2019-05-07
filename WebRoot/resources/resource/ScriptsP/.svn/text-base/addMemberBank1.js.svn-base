var verifyFlag = false;
var idCardFlag = false;

$(document).ready(function(){
	 var cur_dh = $('#xinxi_nwd_8');
	    cur_dh.addClass('active');
	    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
	    
	    var flag = $("#flagRe").val();
	    if(flag == 'phone'){//绑定手机
	    	$(".plusBank1 .content").html('添加银行卡前，请先绑定手机号！');
			showCon_1();
	    }else if(flag == 'iden'){
	    	$(".plusBank1 .content").html('为保障账户信息安全，绑定银行卡前需要进行实名认证');
	    	//点击确定跳转到实名认证页面 by：cy 2014-09-17
			$(".plusBank1 .topper .plus_c").attr('onclick','closeConfirm();');
			$(".plusBank1 .btnbox").html('<button type="button" class="btn btnSize_1 btn_blue" onclick="popBoxConfirm();">立即认证</button>');
			showCon_1();
	    }
	    
	$("#verifyBank").blur(function(){
		var verify = $("#verifyBank").val();
		var reg = /^\S{6}$/;
		if(verify==""){
			verifyFlag = false;
			$('#verifyMSG5').css("display","");
			$("#verifyMSG5").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入验证码！");
		}else if(!reg.test(verify)){
			verifyFlag = false;
			$('#verifyMSG5').css("display","");
			$("#verifyMSG5").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
		}else{
			verifyFlag = true;
			$('#verifyMSG5').css("display","none");
			$("#verifyMSG5").html("");
		}
 	});
	$("#idCardBank").blur(function(){
		var idCard = $("#idCardBank").val();
		var IdType = $("#IdType").val();
		var reg = /^(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		if(idCard==""){
			idCardFlag = false;
			$('#idCardMSG5').css("display","");
			$("#idCardMSG5").html("<i></i>请输入您实名认证时填写的身份证号码！");
		}else if(!reg.test(idCard) && IdType==0){
			idCardFlag = false;
			$('#idCardMSG5').css("display","");
			$("#idCardMSG5").html("<i></i>身份证号码有误！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/compareIdCard.do",
	        	dataType: "json",
	        	data:{
	        		idCard:idCard
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 1){//相等
	        			idCardFlag = true;
	        			$('#idCardMSG5').css("display","none");
	        			$("#idCardMSG5").html("");
	        		}else if(msg == 0){
	        			idCardFlag = false;
	        			$('#idCardMSG5').css("display","");
	        			$("#idCardMSG5").html("<i></i>身份证号码有误！");
	        		}
	        	}   
	      }); 
		}
 	});
});

//发送验证码
function click5(imgCode){
	/**
	 * 提交时没有token。通过访问URL直接提交借款Bug修复
	 */
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
	$.ajax({
    	type: "post",
   	 	url: "/member/sendMsg1.do",
    	dataType: "json",
    	data:{
    		type:"bank",
    		imgCode:imgCode,
    		stok:stok
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == 1){
    		 	$("#click5").hide();
				$("#countDown5").show();
				countDown5(120);//倒计时120秒
				// 弹框优化 | zhenhua.xi | 20140915
				// showMsg("动态密码已经发送到您的手机上，有效期为30分钟，<br/>请注意查收；如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者<br/>拨打400-7910-888");
    			$('#verifyMSG5').css("display","");
				$("#verifyMSG5").removeClass().addClass("prompt_1 remind_1").html("<i></i>验证码已发送至您的手机，有效期30分钟，请查收");				
    		}else if(msg==0){
    			$('#verifyMSG5').css("display","");
				$("#verifyMSG5").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，请点击！");
    		}else if(msg==2){
    			$('#verifyMSG5').css("display","");
				$("#verifyMSG5").removeClass().addClass("prompt_1 error_1").html("<i></i>手机信息发送失败，验证码错误，请再次请点击！");
    		}
    	}   
  }); 
}
function countDown5(time){
	$("#countDown5").html(time+"秒");
	 	time = time - 1;
	if(time>=0){
    	setTimeout("countDown5("+time+")",1000);
	}else{
    	$("#countDown5").hide();
    	$("#yybtn").html("语音验证码");
    	$("#yybtn").attr('disabled',false);
        $("#yybtn").show();
	}
}
/**发送语音短信验证码  start*/ 
$('#yybtn').click(function () {
	//$("#idCardBank").blur();
	//if(idCardFlag == true){
		$.post("/msg/bankSendYuyinMessage.do?interval=60", {phone: $("#addBankPhoneHidden").val()},
        		function (result) {
		    		if(result=='succ'){
		    	        $("#yybtn").attr('disabled', true);		    	        
		    	        yuyinWaiting(120);
						// 弹框优化 | zhenhua.xi | 20140915
						// showMsg("你我贷将通过400官方电话为您播报语音验证，请注意接听！<br/>验证码有效期为30分钟，如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者拨打400-7910-888。<br/>");
		    			$('#verifyMSG5').css("display","");
						$("#verifyMSG5").removeClass().addClass("prompt_1 remind_1").html("<i></i>你我贷已通过400-7910-888播报，请收听");
		    		}else{
		    			$("#yybtn").attr('disabled', false);
		    			showMsg(result);
		    		}
		        }
        );
	//}
});
$('#yybtn2').click(function () {
	$("#idCardBank").blur();
	if(idCardFlag == true){
		$.post("/msg/bankSendYuyinMessage.do?interval=120", {phone: $("#addBankPhoneHidden").val()},
        		function (result) {
		    		if(result=='succ'){
		    	        $("#yybtn2").attr('disabled', true);
		    	        showMsg("你我贷将通过400官方电话为您播报语音验证，请注意接听！<br/>验证码有效期为30分钟，如果未收到请稍候再重试。<br/>如果长时间无法收到验证码，请联系你我贷在线客服或者拨打400-7910-888。<br/>");
		    	        yuyinWaiting2(120);
		    		}else{
		    			$("#yybtn2").attr('disabled', false);
		    			showMsg(result);
		    		}
		        }
        );
	}
});
/**发送语音短信验证码  end*/ 
/**发送语音短信验证码  start*/ 
function yuyinWaiting(i) {
    if (i > 1) {
        i--;
        $("#yybtn").html(i + "秒");
        window.setTimeout(function () {
        	yuyinWaiting(i);
        }, 1000);
    } else {
    	$("#yybtn").hide();
    	$("#yybtn2").show();
    }
}
function yuyinWaiting2(i) {
    if (i > 1) {
        i--;
        $("#yybtn2").html(i + "秒");
        window.setTimeout(function () {
        	yuyinWaiting2(i);
        }, 1000);
    } else {
    	$("#yybtn2").html("语音验证码");
    	$("#yybtn2").attr('disabled',false);
    }
}
/**等待语音短信验证码  end*/ 
function addBankNext(){
	$("#verifyBank").blur();
	$("#idCardBank").blur();
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
	if(verifyFlag==true && idCardFlag==true){
		//图片验证码校验
		$.ajax({
            type: "post",
            url: "/member/verificationCode1.do?type=bank",
            dataType: "json",
            data:{
                verify:$("#verifyBank").val(),
                phone:$("#addBankPhoneHidden").val()
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function(msg){
                if(msg == 1){//相等
                    //跳转第二步
                	$.ajax({
                    	type: "post",
                   	 	url: "/member/addBankyStep.do",
                    	dataType: "json",
                    	data:{
                    		doWhat:$('#doWhat').val(),
                    		mbbId:$('#mbbId').val(),
                    		flag:"flag",
                            stok:stok
                   		 },
                    	async: false,
                    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    	success: function(result){
                    		$('#addBankStep1').addClass('none');
            				$('#addBankStep2').removeClass('none');
            				
            				$('#bankStep1').attr("class","visited_a");
                			$('#bankStep2').attr("class","active");
                    		var realName = result['realName'];
                    		var doWhat = result['doWhat'];
                    		
                    	
                    		
                			$('#realNameSpan #part1').text(realName);
                			$('#doWhat').val(doWhat);
                			if(doWhat == 'edit'){
                				$('#mbbId2').val(result['mbbId']);
                				$('#doWhat2').val(result['doWhat']);
                				$('#mbbCardNo').val(result['memberBank']['cardno']);
                				//$('#mbbCardNo').attr("readonly","readonly");
                				if(result['memberBank']['cardno'].indexOf("*") <= 0){
                					$('#mbbCardNo').attr("readonly","readonly");
                				}
                				$('#mbbOpeningBranch').val(result['memberBank']['branchname']);
                				
                				var noAgree = result['noAgree'];
                        		var noMobileFast = result['noMobileFast'];
                				if((noAgree==null || noAgree.length==0) && (noMobileFast!=null && noMobileFast.length>0)){
                					$("#part1").css("display","none");
                					$("#part2").css("display","inline");
                					$('#realNameSpan #part2').find("input").val(realName);
                				}
                				
                    			provListData = result['provList'];
                    			$(provListData).each(function(index,domEle){
                    				var option;
                    				var pro = 0;
                    				if(result['city1']){
                    					pro=result['city1']['provCode'];
                    				}
                    				var proInd=provListData[index].provCode;
                    				//选中与库中存的省相等的数据
                    				if(pro == proInd){
                    					option = "<option value='"+provListData[index].provCode+"' selected>"+provListData[index].provName+"</option>";
                    				}else{
                    					option = "<option value='"+provListData[index].provCode+"'>"+provListData[index].provName+"</option>";
                    				}
                    				$("#bankProvCode").append(option);
                    			});
                    			
                    			//得到选中的省去查询省下面包含的市的数据
                    			$("#bankCityCode option:gt(0)").remove(); 
                    			var bankProvCode = $("#bankProvCode").val(); 
                    			$.ajax({
                    				type : "POST", 
                    				url : "/member/getProvSelectInterface.do", 
                    				data : "provCode=" + bankProvCode, 
                    				success : function(res) { 
                    					dataqw = res;
                    					//删除ul中全部的li，初始化显示数据，后台保存input
                    					$("#bankCityCode option").remove(); 
                    					$("#cityCode").html("请选择");
                    					$("#cityName").attr("value","-1");
                    					//遍历json的数据
                    					$(dataqw).each(function(index,domEle){ 
                    						var option;
                            				var pro=result['city1']['cityCode'];
                            				var proInd=dataqw[index].cityCode;
                            				//选中与库中存的市相等的数据
                            				if(pro == proInd){
                            					option = "<option value='"+dataqw[index].cityCode+"' selected>"+dataqw[index].cityName+"</option>";
                            				}else{
                            					option = "<option value='"+dataqw[index].cityCode+"'>"+dataqw[index].cityName+"</option>";
                            				}
                            				$("#bankCityCode").append(option); 
                    					});
                    				} 
                    			});
                    			
                    			dataMbb = result['nwdBankList'];
                    			$(dataMbb).each(function(index,domEle){ 
                    				var option;
                    				var pro=result['memberBank']['bankid'];
                    				var proInd=dataMbb[index].bid;
                    				//选中与库中存的银行名称相等的数据
                    				if(pro == proInd){
                    					option = "<option value='"+dataMbb[index].bid+"' selected>"+dataMbb[index].bname+"</option>";
                    				}else{
                    					option = "<option value='"+dataMbb[index].bid+"'>"+dataMbb[index].bname+"</option>";
                    				}
                    				$("#listBankName").append(option); 
                    			});
                				
                			}else if(doWhat == 'add'){
                				$('#doWhat2').val('add');
                				
                				data = result['nwdBankList'];
                    			$(data).each(function(index,domEle){ 
                    				var option = "<option value='"+data[index].bid+"'>"+data[index].bname+"</option>";
                    				$("#listBankName").append(option); 
                    			});
                    			
                    			provListData = result['provList'];
                    			$(provListData).each(function(index,domEle){
                    				var option = "<option value='"+provListData[index].provCode+"'>"+provListData[index].provName+"</option>";
                    				$("#bankProvCode").append(option);
                    			});
                			}
                			
                    	}  
                  });
                }else if(msg == 0){
                    $('#verifyMSG5').css("display","");
                    $("#verifyMSG5").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码有误！");
                }else if(msg == -1){
                    $('#verifyMSG5').css("display","");
                    $("#verifyMSG5").removeClass().addClass("prompt_1 error_1").html("<i></i>验证码超时，请点击重新发送!");
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                alert("error");
                alert(textStatus);
            }
        });
		
		
	}
}
