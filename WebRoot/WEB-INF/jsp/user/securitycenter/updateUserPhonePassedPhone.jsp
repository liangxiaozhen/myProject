<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 原手机号验证码 开始 -->
<div class="step step-1">
	<span id="safequestion_title_1_1">验证旧手机号</span> <span
		id="safequestion_title_1_2">设置新手机号</span> <span
		id="safequestion_title_1_3">修改成功</span>
</div>
<div class="verification-from"
	id="update_user_phone_passedPhone_submit_edit">
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 原手机号码:
		</span> <span class="verification-2">
			${userBaseAccountInfo.mobilephonestr}</span> <span class="verification-3">
			<a href="javascript:void(0)"
			id="update_user_phone_passedPhone_submit_Send"
			class="button-orange-1" style="display: block;"
			onclick="update_user_phone_passedPhone_submit_Send(this)">
				发送短信验证码 </a> <a id="update_user_phone_passedPhone_submit_tips"
			href="javascript:void(0)" class="button-ash-3" style="display: none;">正在发送</a>
		</span> <span class="verification-3" style="color: red;"
			id="update_user_phone_passedPhone_submit_Send_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 验证码:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="update_user_phone_passedPhone_submit_code"
			onkeyup="value=value.replace(/[^\d]/g,'') "
			onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
		</span> <span class="verification-3" style="color: red;"
			id="update_user_phone_passedPhone_submit_code_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"></span> <span class="verification-2">
			<a href="javascript:void(0)" class="submit"
			id="update_user_phone_passedPhone_submit">确定</a>
		</span> <span class="verification-3" style="color: red;"
			id="update_user_phone_passedPhone_submit_error"></span>
	</div>
</div>
<!-- 原安全问题 结束 -->
<script type="text/javascript">
  $(function(){
	  $("#update_user_phone_passedPhone_submit").click(function(){
 		 var code =  $("#update_user_phone_passedPhone_submit_code").val();
 		 if(isEmpty(code)){
 			 $("#update_user_phone_passedPhone_submit_code").focus();
 			 $("#update_user_phone_passedPhone_submit_code_error").text("请输入验证码...");
 			 return false;
 		 }else{
 			 $("#update_user_phone_passedPhone_submit_code_error").text("");
 		 }
		 var params = {"code":code};
		 Security.Update_UserPhone_CheckCode(params);
	  });
   });
  
 //发送短信 
 function update_user_phone_passedPhone_submit_Send(obj){
  	 var cod = getCookieValue("secondsremained") ? getCookieValue("secondsremained"):0;//获取cookie值
		 if(cod > 0){
	    	 $("#update_user_phone_passedPhone_submit_Send").removeAttr("onclick");
	    	 $("#update_user_phone_passedPhone_submit_Send_error").text("短信发送失败...");
	        //开始倒计时
	        timer(cod,"update_user_phone_passedPhone_submit_Send","update_user_phone_passedPhone_submit_tips","update_user_phone_passedPhone_submit_Send");
	     }else{
	    	 $("#update_user_phone_passedPhone_submit_Send").removeAttr("onclick");
	    	 $("#update_user_phone_passedPhone_submit_Send_error").text("");
	    	 Security.Update_UserPhone_SendSsm();
	     }
};
</script>

