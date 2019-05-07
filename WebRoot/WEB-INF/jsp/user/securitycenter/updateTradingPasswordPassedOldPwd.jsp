<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-from" id="gj_safequestion_answer_edit">
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 用户名:
		</span> <span class="verification-2"> ${userBaseAccountInfo.loginname }
		</span> <span class="verification-3" style="color: red;"
			id="Update_TrandingPassword_CodeErr"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 原交易密码:
		</span> <span class="verification-2"> <input type="password"
			class="input-text-2" id="update_Trading_Password_PassedOldPwd_old"
			placeholder="请输入交易密码...">
		</span> <span class="verification-3" style="color: red;"
			id="update_Trading_Password_PassedOldPwd_oldErr"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b>
			设置新交易密码:
		</span> <span class="verification-2"> <input type="password"
			class="input-text-2" id="update_Trading_Password_PassedOldPwd_newpsw"
			placeholder="请输入交易密码...">
		</span> <span class="verification-3" style="color: red;"
			id="update_Trading_Password_PassedOldPwd_newpswErr"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b>
			确认新交易密码:
		</span> <span class="verification-2"> <input type="password"
			class="input-text-2"
			id="update_Trading_Password_PassedOldPwd_newpsw1"
			placeholder="请再输入交易密码...">
		</span> <span class="verification-3" style="color: red;"
			id="update_Trading_Password_PassedOldPwd_newpsw1Err"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> </span> <span class="verification-2">
			<a href="javascript:void(0)"
			class="gj_verification_deal_submit submit"
			id="update_Trading_Password_PassedOldPwd_submit">确定</a>
		</span> <span class="verification-3" style="color: red;"
			id="gj_Update_TrandingPassword_submitErr"> </span>
	</div>
</div>
<script type="text/javascript">
 $(function(){
 	 $("#update_Trading_Password_PassedOldPwd_submit").click(function(){
 		 var oldPsw = $("#update_Trading_Password_PassedOldPwd_old").val();
 		 var newPsw = $("#update_Trading_Password_PassedOldPwd_newpsw").val();
 		 var newPsw1 = $("#update_Trading_Password_PassedOldPwd_newpsw1").val();
 		 if(isEmpty(oldPsw)){
 			$("#update_Trading_Password_PassedOldPwd_old").focus();
 			$("#update_Trading_Password_PassedOldPwd_oldErr").text("请输入原交易密码...");
 			return false;
 		 }else{
  			$("#update_Trading_Password_PassedOldPwd_oldErr").text("");
  		 }
 		 
 		if(isEmpty(newPsw)){
 			$("#update_Trading_Password_PassedOldPwd_newpsw").focus();
 			$("#update_Trading_Password_PassedOldPwd_newpswErr").text("请输入新交易密码...");
 			return false;
 		 }else{
  			$("#update_Trading_Password_PassedOldPwd_newpswErr").text("");
  			var reg=/^(?=.*[a-zA-Z])(?=.*[\d]).{6,16}$/;
  			if(reg.test(newPsw)){
  				$("#update_Trading_Password_PassedOldPwd_newpswErr").text("");
  			}else{
  				$("#update_Trading_Password_PassedOldPwd_newpswErr").text("交易密码格式错误！必须包含6到16位的数字和字母组合");
  				return false;
  			}
  		 }
 		
 		if(isEmpty(newPsw1)){
 			$("#update_Trading_Password_PassedOldPwd_newpsw1").focus();
 			$("#update_Trading_Password_PassedOldPwd_newpsw1Err").text("请再输入新交易密码...");
 			return false;
 		 }else{
  			$("#update_Trading_Password_PassedOldPwd_newpsw1Err").text("");
   		 }
 		 if(isNotEmpty(newPsw) && isNotEmpty(newPsw1)){
 			 if(newPsw != newPsw1){
 				$("#update_Trading_Password_PassedOldPwd_newpswErr").text("交易密码不一致...");
 				$("#update_Trading_Password_PassedOldPwd_newpsw1Err").text("交易密码不一致...");
 				 return false;
 			 }else{
 				$("#update_Trading_Password_PassedOldPwd_newpswErr").text("");
 				$("#update_Trading_Password_PassedOldPwd_newpsw1Err").text("");
 			 }
 		 }
 		 var params = {"oldPsw":oldPsw,"newPsw":newPsw}; 
 		 Security.Update_TrandingPassword_Old(params);
  	 });
 });
 </script>


