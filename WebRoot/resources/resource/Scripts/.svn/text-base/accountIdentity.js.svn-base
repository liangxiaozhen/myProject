$(document).ready(function(){
	//账户中心左侧头像下面的小图标
	 $.ajax({
			type : "POST", 
			url : "/member/findMemberCerInfo.do", 
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result) { 
					if(result.member==undefined){
						window.location.href= '/login.html';
					}
					var ifcertIdentity=false;//是否实名
					var isBindPhone = false;//是否绑定手机号
					var isBindingBank = false;//是否绑银行卡
					var isBindingEmail = false;//是否绑email
					var isUpdatePwd = false;//是否修改密码
					if(result.member.certIdentity == 1) {
						ifcertIdentity = true;
					}
					if(result.member.certPhone == 1) {
						isBindPhone = true;
					}
					if(result.bindingBank == 1) {
						isBindingBank = true;
					}
					if(result.repwdFlag==1){
						isUpdatePwd = true;
					}
					var str="{'isRealName' :"+ifcertIdentity+",'isBindPhone':"+isBindPhone+",'level':"+result.level+" ,'phoneNoShort':'"+result.phoneHidden+"','isRealMail':"+isBindingEmail+", 'isRealBank':"+isBindingBank+"}"
					
					var levelStr='<span class="vip_icon step'+result.level+'_14 mar_r5">&nbsp;</span><span>'+result.levelName+'</span>';
					$("#memberLevelDiv").html(levelStr);
					
					$("#memberLevelName").html(result.levelName);
					$("#memberLevelIcon").addClass("fl vipCon-rank"+(result.level-1)+" mar_r20 vip-icon mar_t10 txt_center fc_6");
					//$("#phoneInfo").html(result.phoneHidden);//手机号码
					assetsCommon.init2(eval("(" + str + ")"));
					//账户中心消息
					$("#mmsg").html(result.memberMsgCount);//未读信息
					
					/*$("#memberLevelGrade").text(result.grade)//积分
					
					$("#memberGrowth").html(result.growth);//成长值
*/					
					//$("#bigPresent").html(result.levelName.substring(0,2)+"大礼包");
					
					//delete by luobin 移至  js\member\accountDetail.js
					//线下用户资产开关
//					var excellenceWealthSwitch = result.excellenceWealthSwitch;
//					var excellenceWealth = result.excellenceWealth;//线下用户的资产
//					var mbProperty = result.mbProperty;//线下用户标识
//					var balance = (result.fun.asset/1000);//线上资产
//					//alert('excellenceWealthSwitch:'+excellenceWealthSwitch+":excellenceWealth:"+excellenceWealth+":mbProperty:"+mbProperty);
//					if(excellenceWealthSwitch == true && (mbProperty == 2 || mbProperty == 3) && excellenceWealth > 0){
//						$("#excellenceLi").show();//账户中心首页卓越资产（有线下用户有资产才展示信息）
//						$("i[name=zyzqBanlance]").text(formatNum(excellenceWealth/1000));//线下用户资产
//						$("#excellenceNav").show();//卓越专区--左边导航
//						if(balance > 0){
//							$("#fundRecordLi").text("线上资金记录");//资金记录--有线下资产的用户需变更该导航文案
//							$("#incomeRecordLi").text("线上收益明细");//收益明细--有线下资产的用户需变更该导航文案
//							$("#incomeP").text("线上投资累计收益");//账户中心首页
//						}
//						if((excellenceWealth/1000) >0){
//							$("#myBill").hide();//账单隐藏
//						}
//						
//						$("#zyzqAmount").text(formatNum(excellenceWealth/1000));
//					}
					
					//用户头像显示 start
					var photo = result.baseInfo.photo;
					if(photo){
						$("#ph").val(photo);
						$("#bigtx-mask").attr("src",staticCss+"/manage/img/bigtx.png");
						$("#exists").attr("src","/member1/memberPhotoView.do?imgPath="+photo);
						$("#exists").css("display","inline-block");
						$("#noExists").css("display","none");
						$("#chooseImgShow").attr("src","/member1/memberPhotoView.do?imgPath="+photo);
						$("#smallImg").attr("src","/member1/memberPhotoView.do?imgPath="+photo);
					} else {
						$("#exists").css("display","none");
						$("#noExists").attr("src",staticCss+"/manage/images/touxiang.jpg");
					}
					//用户头像显示 end
			//	}
			}

	 });
	 
	 /** 
		* @name 底部合作伙伴 轮播 
		* @import js/nwd_common.js 
		* @author lihe 
		* @revise time:2016-04-011 10:07 
		*/ 
		new Playimgone("firedBox",{ 
			time:0, 
			navo:"" 
		});		
});


//图像处理
var popUp = null; 
seajs.use('popup',function(popup){ 
	popUp = popup; 
});
$(function(){
	$(".pop-close").click(function(){
		popUp.hideLayer($("#myphoto-pop-manage"));
		}
	); 
})

//头像编辑
$(document).on('click', '#myphototip', function(){ 
	var attr = new Attention( popUp , '#myphoto-pop-manage' , true ); 
	attr.event(); 
}); 



function savembphoto(){
	
	var _photo = $('#mbphoto').val();
    if(!_photo) {
		alert('请选择需要上传的图片！');
	}else{
		alert("进入");
		var rightFileType = new Array("jpg","JPG","BMP","bmp","GIF","gif","PNG","png","JPEG","jpeg");  
		var fileType = _photo.substring(_photo.lastIndexOf(".") + 1); 
	    alert(fileType);
		if (false) {
		
			// $.inArray(fileType,rightFileType) == -1
			$("#fileMSG").html("只支持图片上传！");
			$("#fileMSG").attr("display","");
		}else{
		    $("#fileMSG").attr("value","");
			$("#fileMSG").attr("display","none");
			$('#mbPhoto').ajaxSubmit({
				type: 'post',  
                success: function(data){
					if(data == ''){
						alert("上传失败");
					}else if(data == "1"){
						alert("头像不超过2M！");
					}                	
					// 增加文件类型判断 | zhenhua.xi | 20140826
                	else if(data == '2'){
                		$('#fileMSG2').css("display","");
                    	$("#fileMSG2").removeClass().addClass("prompt_1 error_1").html("<i></i>请上传以格式为jpg、png、bmp的图片！");
                	}else{
						$("#ph1").attr("value",data);
						$("#ph").attr("value",data);
						$('#exists').attr('src', "/member1/memberPhotoView.do?imgPath="+data+"");
						$('#noExists').attr('src', "/member1/memberPhotoView.do?imgPath="+data+"");
						$("#chooseImgShow").attr('src', "/member1/memberPhotoView.do?imgPath="+data+"");
						$("#smallImg").attr('src', "/member1/memberPhotoView.do?imgPath="+data+"");
						
						$('#myphoto-pop-manage').hide();
						$('#myphoto-pop-manage').slideUp();
						$('.windowmask').hide();
						
						$('#mbphoto').val("");
					}
              	}
			});
		}
	}
}
