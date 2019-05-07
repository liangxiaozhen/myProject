<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="gj_verification_deal">
	<div class="verification-box">
		<div class="verification-from">
			<div class="item clearfloat">
				<span class="verification-1"> 手机号码: </span> <span
					class="verification-2">
					${userBaseAccountInfo.mobilephonestr} </span> <span class="verification-3">
					<a href="javascript:void(0)" id="gj_Update_TrandingPassword_sms"
					class="button-orange-1"
					onclick="Update_TrandingPassword_Code_SSm(this)"
					style="display: block;"> 发送短信验证码 </a> <a
					id="gj_Update_TrandingPassword_tips" href="javascript:void(0)"
					class="button-ash-3" style="display: none;">正在发送</a>
				</span> <span class="verification-3" style="color: red;"
					id="gj_Update_TrandingPassword_sms_error"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"> <b class="red-star">*</b>
					手机验证码:
				</span> <span class="verification-2"> <input type="text"
					class="input-text-2" id="Update_TrandingPassword_Code"
					placeholder="请输入手机验证码..."
					onkeyup="value=value.replace(/[^\d]/g,'') "
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</span> <span class="verification-3" style="color: red;"
					id="Update_TrandingPassword_CodeErr"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"> <b class="red-star">*</b>
					设置新交易密码:
				</span> <span class="verification-2"> <input type="password"
					class="input-text-2" id="Update_TrandingPassword_Password"
					placeholder="请输入交易密码...">
				</span> <span class="verification-3" style="color: red;"
					id="Update_TrandingPassword_PasswordErr"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"> <b class="red-star">*</b>
					确认新交易密码:
				</span> <span class="verification-2"> <input type="password"
					class="input-text-2" id="Update_TrandingPassword_Password1"
					title="请输入确认交易密码..." placeholder="请输入交易密码...">
				</span> <span class="verification-3" style="color: red;"
					id="Update_TrandingPassword_Password1Err"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"> </span> <span class="verification-2">
					<a href="javascript:void(0)"
					class="gj_verification_deal_submit submit"
					id="gj_Update_TrandingPassword_submit">确定</a>
				</span> <span class="verification-3" style="color: red;"
					id="gj_Update_TrandingPassword_submitErr"> </span>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
 	$(function(){
 		//保存
 		$("#gj_Update_TrandingPassword_submit").click(function(){
 			var code = $("#Update_TrandingPassword_Code").val();
 			var password = $("#Update_TrandingPassword_Password").val();
 			var password1 = $("#Update_TrandingPassword_Password1").val();
 			if(isEmpty(code)){
 				$("#Update_TrandingPassword_CodeErr").text("请输入手机验证码...");
 				$("#Update_TrandingPassword_Code").focus();
 				return false;
 			}else{
 				$("#Update_TrandingPassword_CodeErr").text("");
  			}
 			if(isEmpty(password)){
 				$("#Update_TrandingPassword_PasswordErr").text("请输入交易密码...");
 				$("#Update_TrandingPassword_Password").focus();
 				return false;
 			}else{
 				$("#Update_TrandingPassword_PasswordErr").text("");
				var reg =/^(?=.*[a-zA-Z])(?=.*[\d]).{6,16}$/;
				if(reg.test(password)){
					$("#Update_TrandingPassword_PasswordErr").text("");
 				}else{
					$("#Update_TrandingPassword_PasswordErr").text("交易密码格式不正确 ! 必须包含 6-16位字母、数字 ");
					return false;
				}
   			}
 			if(isEmpty(password1)){
 				$("#Update_TrandingPassword_Password1Err").text("请再输入交易密码...");
 				$("#Update_TrandingPassword_Password1").focus();
 				return false;
 			}else{
 				$("#Update_TrandingPassword_Password1Err").text("");
  			}
 			
 			if(!isEmpty(password) && !isEmpty(password1)){
				 if(password != password1){
					 $("#Update_TrandingPassword_PasswordErr").text("两次输入交易密码不一致...");
					 $("#Update_TrandingPassword_Password1Err").text("两次输入交易密码不一致...");
					 return false;
				 }else{
					 $("#Update_TrandingPassword_PasswordErr").text("");
					 $("#Update_TrandingPassword_Password1Err").text("");
				 }
			}
 			var params={"password":password,"tellCode":code};
 			Security.Update_TrandingPassword(params);
 		});
    });
 	//发送短信
 	function Update_TrandingPassword_Code_SSm(){
   		  //发送短信验证码 无视页面刷新
  		   var v = getCookieValue("secondsremained") ? getCookieValue("secondsremained"):0;//获取cookie值
  		   if(v > 0){
  			   $("#gj_Update_TrandingPassword_sms").removeAttr("onclick");
  			   $("#gj_Update_TrandingPassword_sms_error").text("短信发送失败...");
 	 	       timer(v,"gj_Update_TrandingPassword_sms","gj_Update_TrandingPassword_tips","Update_TrandingPassword_Code_SSm");
  		   }else{
  			    $("#gj_Update_TrandingPassword_sms_error").text("");
	  			$("#gj_Update_TrandingPassword_sms").removeAttr("onclick");
	 			$("#gj_Update_TrandingPassword_sms").hide();
				$("#gj_Update_TrandingPassword_tips").show();
				//发送短信
				 Security.Send_Ssm();
   		   }
   	};
 </script>