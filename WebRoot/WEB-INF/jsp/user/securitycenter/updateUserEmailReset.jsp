<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-box">
	<div id="inser_UserEmail_submit_box2">
		<div class="step step-2">
			<span id="safequestion_title_2_1">${text}</span> <span
				id="safequestion_title_2_2">设置新邮箱</span> <span
				id="safequestion_title_2_3">修改成功</span>
		</div>
		<div class="verification-from">
			<div class="item clearfloat">
				<span class="verification-1"> <b class="red-star">*</b> 新邮箱:
				</span> <span class="verification-2"> <input type="text"
					class="input-text-2" id="update_UserEmail_Reset_submit_code"
					placeholder="请输入邮箱账号..." />
				</span> <span class="verification-3" style="color: red;"
					id="uupdate_UserEmail_Reset_submit_codeErr"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"></span> <span class="verification-2">
					<a href="javascript:void(0)" class="submit"
					id="update_UserEmail_Reset_submit_submit">发送邮件</a>
				</span> <span class="verification-3" style="color: red;"
					id="update_UserEmail_Reset_submit_submitErr"></span>
			</div>
		</div>
	</div>

	<div id="inser_UserEmail_submit_box3" style="display: none;">
		<div class="step step-2">
			<span id="safequestion_title_2_1">${text}</span> <span
				id="safequestion_title_2_2">设置新邮箱</span> <span
				id="safequestion_title_2_3">修改成功</span>
		</div>
		<div class="verification-from">
			<div class="item clearfloat">
				<span class="verification-1"></span> <span class="verification-2">
					我们已经把邮件发送到您的邮箱了,请点击邮件的链接完成重置 </span> <span class="verification-3"
					style="color: red;"
					id="update_UserEmail_SendCheckCode_submit_codeErr"></span>
			</div>
			<div class="item clearfloat">
				<span class="verification-1"></span> <span class="verification-2">
					<a href="https://mail.qq.com" target="_blank" class="submit">查看邮件</a>
				</span> <span class="verification-3" style="color: red;"></span>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
 	$(function(){
 		//发送邮箱重置链接
 		$("#update_UserEmail_Reset_submit_submit").click(function(){
 			var email = $("#update_UserEmail_Reset_submit_code").val();
 			if(isEmpty(email)){
 				$("#update_UserEmail_Reset_submit_submitErr").text("请输入邮箱账号...");
 				$("#update_UserEmail_Reset_submit_code").focus();
 				return false;
 			}else{
 				$("#update_UserEmail_Reset_submit_submitErr").text("");
 				var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+){1,3}$/;
				if(reg.test(email)){
					$("#update_UserEmail_Reset_submit_submitErr").text("");
				}else{
					$("#update_UserEmail_Reset_submit_submitErr").text("邮箱格式不正确!请输入正确的邮箱！");
					return false;
				}
 			}
 			var Update_UserEmail_SendResetEamil_Params ={"email":email}; 
 			Security.Update_UserEmail_SendResetEamil(Update_UserEmail_SendResetEamil_Params);
 		});
 		
 	});
 
 </script>