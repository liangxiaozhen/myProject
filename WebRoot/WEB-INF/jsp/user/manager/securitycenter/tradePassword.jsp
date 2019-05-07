<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_干将会员中心</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
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
							class="fc_6">交易密码${message}</span>
					</div>
					<div class="fl pad_t20 clearfix" style="width: 100%;">
						<!---1 start---->
 							<input type="hidden" id="backUrl" value="">
							<table class="table_3 form fs_14">
								<tbody>
									<tr class="pad_t10" valign="top"></tr>
   									<tr>
										<th class="th_l pad_t10 pad_b20 leftside pad_r20" valign="top">开户手机验证码</th>
										<td class="rightside pad_b20"><input type="text"
											name="tradePasswordcode" id="tradePasswordcode"
											class="input_all ui-input w180-input">
											<button onclick="tradePasswordSSM(this);" id="tradePasswordcodeSSM"
													class="button button-w120 mar_l10">点击获取</button>
												<button id="tradePasswordcodeSSMTip" style="display: none"
													class="button button-w120 mar_l10" type="button"></button>
										<span id="tradePasswordcodeMSGError" style="display: none"
											class="prompt_1 error_1"><i></i></span>
										</td>
										
									</tr>
 									<tr>
										<th></th>
										<td>
											<button class="btn btn_blue btn_size120"
												type="button" value="下一步" onclick="tradePasswordcodeSubmit(this);" id="tradePasswordcodeSubmit">下一步</button>
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
<!-- 尾部 -->
<%--  <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function tradePasswordSSM(obj){
		var logiTimer = getCookieValue("tradePasswordSSM");
		if(logiTimer > 0){
			cookieTimer2(60, "tradePasswordcodeSSM", "tradePasswordcodeSSMTip", "tradePasswordSSM", "tradePasswordSSM");	
			return ;
		}
		$.ajax({
			type:"post",
			url:basePath + "/huishang/tradePassword/sendSSm.action",
			success:function(data){
 				var obj = $.parseJSON(data);
				if(obj.result == "success"){
					addCookie("tradePasswordSSM", 60, 60);
					cookieTimer2(60, "tradePasswordcodeSSM", "tradePasswordcodeSSMTip", "tradePasswordSSM", "tradePasswordSSM");	
				}else if(obj.result == "fail"){
					alert("短信发送失败！请重新发送！");
				}else if(obj.result == "params_error"){
					alert("参数错误,请重新操作！");
				}
				
			}
	});
}

function tradePasswordcodeSubmit(obj){
	var code = $("#tradePasswordcode").val();
	if(isEmpty(code)){
		$("#tradePasswordcodeMSGError").show().text("请输入开户手机验证码");
		$("#tradePasswordcode").focus();
		return ;
	}else {
		$("#tradePasswordcodeMSGError").text("").hide();
	}
	$("#tradePasswordcodeSubmit").removeAttr("onclick").text("处理中...");
	$.ajax({
		type:"post",
		url:basePath + "/huishang/tradePassword/dotradePassword.action",
		data:{"code":code},
		error:function(){$("#tradePasswordcodeSubmit").attr("onclick","tradePasswordcodeSubmit(this)").text("下一步");},
		success:function(data){
			$("#tradePasswordcodeSubmit").attr("onclick","tradePasswordcodeSubmit(this)").text("下一步");
			var obj = $.parseJSON(data);
			if(obj.result == "success"){
 				 $("body").html(obj.list);
 				$("#myform").submit();
			}else if(obj.result == "code_null"){
				 alert("请输入开户手机验证码");
			}else if(obj.result == "tocode_null"){
				alert("请点击短信发送按钮发送短信");
			}else if(obj.result == "code_error"){
				alert("请输入正确的短信验证码");
			}else if(obj.result == "params_error"){
				alert("参数错误!请联系客服");
				window.location.href = basePath + "/user/securitycenter/list.action";
			}
 		}
	});
}

//几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId提示多少秒后可以发送ID clickName事件名称 cookieName cookie名称
function cookieTimer2(timer,btnId,tipsId,clickName,cookieName){
	 timer = getCookieValue(cookieName) ? getCookieValue(cookieName):timer;
		//隐藏短信发送框 
		$("#"+btnId+"").hide();
		//显示正在发送框
		$("#"+tipsId+"").show();
		$("#"+tipsId+"").text((timer<=0)?"立即获取":(""+(timer)+"s"));
		var senderTime = setInterval(function(){
		 if(timer <= 0 ){
			 clearInterval(senderTime);
 			 $("#"+btnId+"").show();
			 $("#"+tipsId+"").hide();
			 $("#"+btnId+"").text("立即获取");
			 $("#"+btnId+"Massage").text("");
			 //恢复发送短信点击事件
			 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			 return false;
		 }else{
			 $("#"+tipsId+"").text(""+(timer--)+"s");
			 editCookie(cookieName,timer,timer+1);
		 }
	},1000);
};


function addCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    //判断是否设置过期时间,0代表关闭浏览器时失效
    if(expiresHours>0){ 
        var date=new Date(); 
        date.setTime(date.getTime()+expiresHours*1000); 
        cookieString=cookieString+";expires=" + date.toUTCString(); 
    } 
        document.cookie=cookieString; 
};

function editCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    if(expiresHours>0){ 
      var date=new Date(); 
      date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
      cookieString=cookieString+";expires=" + date.toGMTString(); 
    } 
      document.cookie=cookieString; 
}; 

function getCookieValue(name){ 
      var strCookie=document.cookie; 
      var arrCookie=strCookie.split("; "); 
      for(var i=0;i<arrCookie.length;i++){ 
        var arr=arrCookie[i].split("="); 
        if(arr[0]==name){
          return unescape(arr[1]);
          break;
        } 
      } 
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
</script>
 </body>
</html>