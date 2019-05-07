<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--原安全问题 开始 -->
<div class="step step-1">
	<span id="safequestion_title_1_1">回答安全问题</span> <span
		id="safequestion_title_1_2">设置新手机号</span> <span
		id="safequestion_title_1_3">修改成功</span>
</div>
<div class="verification-from" id="gj_safequestion_answer_edit">
	<div class="item clearfloat">
		<span class="verification-1"> 问题一: </span> <span
			class="verification-2"> ${userAccountSafeInfo.question1}</span> <span
			class="verification-3" style="color: red;"
			id="gj_editphone_code_submit_Send_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 答案一
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="update_UserPhone_PassedAnswer_answer1" />
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_PassedAnswer_answer1_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> 问题二: </span> <span
			class="verification-2"> ${userAccountSafeInfo.question2}</span> <span
			class="verification-3" style="color: red;"
			id="gj_editphone_code_submit_Send_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 答案二
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="update_UserPhone_PassedAnswer_answer2" />
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_PassedAnswer_answer2_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> 问题三: </span> <span
			class="verification-2"> ${userAccountSafeInfo.question3}</span> <span
			class="verification-3" style="color: red;"
			id="gj_editphone_code_submit_Send_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 答案三
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="update_UserPhone_PassedAnswer_answer3" />
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_PassedAnswer_answer3_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"></span> <span class="verification-2">
			<a href="javascript:void(0)" class="submit"
			id="update_UserPhone_PassedAnswer_Submit">确定</a>
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_PassedAnswer_Submit_error"></span>
	</div>
</div>
<!-- 原安全问题 结束 -->
<script type="text/javascript">
$(function(){
 	$("#update_UserPhone_PassedAnswer_Submit").click(function(){
 		var answer1 = $("#update_UserPhone_PassedAnswer_answer1").val();
		var answer2 = $("#update_UserPhone_PassedAnswer_answer2").val();
		var answer3 = $("#update_UserPhone_PassedAnswer_answer3").val();
		if(isEmpty(answer1)){
			$("#update_UserPhone_PassedAnswer_answer1").focus();
			$("#update_UserPhone_PassedAnswer_answer1_error").text("请输入安全问题答案一...");
			return false;
		}else{
			$("#update_UserPhone_PassedAnswer_answer1_error").text("");
 		}
		if(isEmpty(answer2)){
			$("#update_UserPhone_PassedAnswer_answer2").focus();
			$("#update_UserPhone_PassedAnswer_answer2_error").text("请输入安全问题答案二...");
			return false;
		}else{
			$("#update_UserPhone_PassedAnswer_answer2_error").text("");
 		}
		if(isEmpty(answer3)){
			$("#update_UserPhone_PassedAnswer_answer3").focus();
			$("#update_UserPhone_PassedAnswer_answer3_error").text("请输入安全问题答案三...");
			return false;
		}else{
			$("#update_UserPhone_PassedAnswer_answer3_error").text("");
 		}
       	var params = {"answer1":answer1,"answer2":answer2,"answer3":answer3};
		Security.Update_UserPhone_CheckAnswer(params);
	});
});

</script>





