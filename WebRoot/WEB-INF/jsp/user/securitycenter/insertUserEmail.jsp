<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-box">
	<div class="verification-from" id="inser_UserEmail_submit_bind1">
		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b> 邮箱:
			</span> <span class="verification-2"> <input type="text"
				class="input-text-2" id="inser_UserEmail_submit_email"
				placeholder="请输入邮箱账号...">
			</span> <span class="verification-3" style="color: red;"
				id="inser_UserEmail_submit_email_error"></span>
		</div>
		<div class="item clearfloat">
			<span class="verification-1"></span> <span class="verification-2">
				<a href="javascript:void(0)" class="submit"
				id="inser_UserEmail_submit">发送验证邮件</a>
			</span> <span class="verification-3" style="color: red;"
				id="inser_UserEmail_submitErr"></span>
		</div>
	</div>
	<div class="verification-from" id="inser_UserEmail_submit_bind2"
		style="display: none;">
		<div class="verification-form_email_tips clearfloat">
			<p class="send">验证邮件链接已发送至邮箱</p>
			<p class="click-email">请及时点击邮件链接完善邮箱验证</p>
		</div>
		<div class="item clearfloat">
			<span class="verification-1"> </span> <span class="verification-2">
			</span> <span class="verification-3" style="color: red;"
				id="bind_UserEmail_submit_code_error"></span>
		</div>
		<div class="item clearfloat">
			<span class="verification-1"></span> <span class="verification-2">
				<a href="https://mail.qq.com" target="_blank" class="submit">查看邮件</a>
			</span> <span class="verification-3" style="color: red;"
				id="bind_UserEmail_submitErr"></span>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		//发送邮箱验证链接
		$("#inser_UserEmail_submit").click(function(){
			 var email = $("#inser_UserEmail_submit_email").val();
			 if(isEmpty(email)){
				 $("#inser_UserEmail_submit_email_error").text("请输入邮箱账号...");
				 $("#inser_UserEmail_submit_email").focus();
				 return false;
			 }else{
				 $("#inser_UserEmail_submit_email_error").text("");
				var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+){1,3}$/;
				if(reg.test(email)){
					$("#inser_UserEmail_submit_email_error").text("");
				}else{
					$("#inser_UserEmail_submit_email_error").text("邮箱格式不正确!请输入正确的邮箱！");
					return false;
				}
			 }
			 var Insert_UserEmail_Send_Params = {"email":email};
 			 Security.Insert_UserEmail_Send(Insert_UserEmail_Send_Params);
		});
	});	
  </script>
