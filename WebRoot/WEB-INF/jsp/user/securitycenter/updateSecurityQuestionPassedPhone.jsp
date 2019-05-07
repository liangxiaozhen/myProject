<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="step step-1">
	<span id="safequestion_title_1_1">获取手机验证码</span> <span
		id="safequestion_title_1_2">重新设置安全问题</span> <span
		id="safequestion_title_1_3">修改成功</span>
</div>
<div class="verification-from">
	<div class="item clearfloat">
		<span class="verification-1"> 手机号码: </span> <span
			class="verification-2">${userBaseAccountInfo.mobilephonestr}</span> <span
			class="verification-3"> <a href="javascript:void(0)"
			id="gj_editphone_code_submit_Send" class="button-orange-1"
			style="display: block;" onclick="gj_editphone_code_submit_Send(this)">
				发送短信验证码 </a> <a id="gj_editphone_code_submit_tips"
			href="javascript:void(0)" class="button-ash-3" style="display: none;">正在发送</a>
		</span> <span class="verification-3" style="color: red;"
			id="gj_editphone_code_submit_Send_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 手机验证码:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="gj_editphone_code_submit_tellCode"
			name="tellCode" maxlength="20"
			onkeyup="value=value.replace(/[^\d]/g,'') "
			onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
			placeholder="请输入手机验证码...">
		</span> <span class="verification-3" style="color: red;"
			id="gj_editphone_code_submit_tellCodeErr"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"></span> <span class="verification-2">
			<a href="javascript:void(0)" class="submit"
			id="gj_editphone_code_submit">确定</a>
		</span> <span class="verification-3" style="color: red;"
			id="gj_editphone_code_submitErr"></span>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		//发送
		 $("#gj_editphone_code_submit").click(function(){
			  var code = $("#gj_editphone_code_submit_tellCode").val();
			  if(isEmpty(code)){
				  $("#gj_editphone_code_submit_tellCode").focus();
				  $("#gj_editphone_code_submit_tellCodeErr").text("请输入手机验证码...");
				  return false;
			  }else{
				  $("#gj_editphone_code_submit_tellCodeErr").text("");
			  }
			  var params = {"code":code};
			  Security.Phone_Code_Check(params);
		 });
  	});
	//发送短信
	function gj_editphone_code_submit_Send(){
 		var cod = getCookieValue("secondsremained") ? getCookieValue("secondsremained"):0;//获取cookie值
  		 if(cod > 0){
  	    	 $("#gj_editphone_code_submit_Send").removeAttr("onclick");
  	    	 $("#gj_editphone_code_submit_Send_error").text("短信发送失败...");
  	        //开始倒计时
 	        timer(cod,"gj_editphone_code_submit_Send","gj_editphone_code_submit_tips","gj_editphone_code_submit_Send");
 	     }else{
  	    	 $("#gj_editphone_code_submit_Send").removeAttr("onclick");
 	    	 $("#gj_editphone_code_submit_Send_error").text("");
  	    	 Security.Update_Security_Send_Ssm();
 	     }
 	}
</script>
