<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-box">
	<div class="verification-from">
		<div class="item clearfloat">
			<span class="verification-1"> 用户名: </span> <span
				class="verification-2"> ${userBaseAccountInfo.loginname} </span> <span
				class="verification-3" style="color: red;" id="tell_code_error"></span>
		</div>

		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b>
				请输入原始密码:
			</span> <span class="verification-2"> <input type="password"
				class="input-text-2" id="oldpassword" placeholder="请输入原始密码...">
			</span> <span class="verification-3" style="color: red;"
				id="oldpassword_error"></span>
		</div>

		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b>
				请输入新密码:
			</span> <span class="verification-2"> <input type="password"
				class="input-text-2" id="newpassword" placeholder="请输入新密码..." />
			</span> <span class="verification-3" style="color: red;"
				id="newpassword_error"></span>
		</div>

		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b>
				请再输入新密码:
			</span> <span class="verification-2"> <input type="password"
				class="input-text-2" id="newpassword1" name="tellCode"
				placeholder="请再输入新密码..." />
			</span> <span class="verification-3" style="color: red;"
				id="newpassword1_error"></span>
		</div>

		<div class="item clearfloat">
			<span class="verification-1"> </span> <span class="verification-2">
				<a href="javascript:void(0)" class="submit"
				id="update_verification_password">确定</a>
			</span> <span class="verification-3" style="color: red;"
				id="update_verification_password_error"></span>
		</div>
	</div>
</div>
<script type="text/javascript">

	$(function(){
		$("#update_verification_password").click(function(){
			var oldpassword = $("#oldpassword").val();
			var newpassword = $("#newpassword").val();
			var newpassword1 = $("#newpassword1").val();
			if(isEmpty(oldpassword)){
				$("#oldpassword_error").text("请输入原密码...");
				$("#oldpassword").focus();
				return false;
			}else{
				$("#oldpassword_error").text("");
			}
			
			if(isEmpty(newpassword)){
				$("#newpassword_error").text("请输入新密码...");
				$("#newpassword").focus();
				return false;
			}else{
				$("#newpassword_error").text("");
 				var reg = /^(?=.*[a-zA-Z])(?=.*[\d]).{6,16}$/;
				if(reg.test(newpassword)){
					$("#newpassword_error").text("");
 				}else{
					$("#newpassword_error").text("密码格式不正确 ! 必须包含 6-16位字母、数字 ");
					return false;
				}
			}
			
			if(isEmpty(newpassword1)){
				$("#newpassword1_error").text("请再输入新密码...");
				$("#newpassword1").focus();
				return false;
			}else{
				$("#newpassword1_error").text("");
			}
			
			if(!isEmpty(newpassword) && !isEmpty(newpassword1)){
				 if(newpassword != newpassword1){
					 $("#newpassword_error").text("两次输入密码不一致...");
					 $("#newpassword1_error").text("两次输入密码不一致...");
					 return false;
				 }else{
					 $("#newpassword_error").text("");
					 $("#newpassword1_error").text("");
				 }
			}
			var params = {"oldpassword":oldpassword,"newpassword":newpassword};
			Security.Update_LoginPwd(params);
		});
	});
</script>
