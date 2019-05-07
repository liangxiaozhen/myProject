<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-box">
	<div id="inser_UserEmail_submit_box1">
		<div class="step step-1">
			<span id="safequestion_title_1_1">验证原邮箱</span> <span
				id="safequestion_title_1_2">设置新邮箱</span> <span
				id="safequestion_title_1_3">修改成功</span>
		</div>
		<div class="verification-from" id="inser_UserEmail_submit_bind1">
			<div class="item clearfloat">
				<p>我们将向您原先绑定的邮箱${userBaseAccountInfo.emailstr}，发送一封重置邮件。</p>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"></span> <span class="verification-2">
					<a href="javascript:void(0)" class="submit"
					id="update_userEmail_PassedOldEmail_submit">发送验证邮箱</a>
				</span> <span class="verification-3" style="color: red;"
					id="update_userEmail_PassedOldEmail_submitErr"></span>
			</div>
		</div>
	</div>

	<div id="inser_UserEmail_submit_box2" style="display: none;">
		<div class="step step-1">
			<span id="safequestion_title_1_1">验证原邮箱</span> <span
				id="safequestion_title_1_2">设置新邮箱</span> <span
				id="safequestion_title_1_3">修改成功</span>
		</div>
		<div class="verification-from">
			<div class="item clearfloat">
				<span class="verification-1"> <!--  				 		<b class="red-star">*</b> -->
					<!-- 						邮箱验证码:  -->
				</span> <span class="verification-2">
					<p>邮件发送成功!请打开邮箱点击邮件链接,设置新邮箱</p> <!-- 						<input type="text" class="input-text-2" id="update_UserEmail_SendCheckCode_submit_code" placeholder="请输入邮箱验证码..." -->
					<!-- 						onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /> -->
				</span> <span class="verification-3" style="color: red;"
					id="update_UserEmail_SendCheckCode_submit_codeErr"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"></span> <span class="verification-2">
					<a href="https://mail.qq.com" target="_blank" class="submit"
					style="margin-left: 20px;">查看邮件</a>
				</span> <span class="verification-3" style="color: red;"
					id="update_UserEmail_SendCheckCode_submitErr"></span>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
 	$(function(){
 		//发送邮箱验证邮件
 		$("#update_userEmail_PassedOldEmail_submit").click(function(){
 			Security.Update_UserEmail_SendEamil();
 		});
  	});	 
 	
 </script>