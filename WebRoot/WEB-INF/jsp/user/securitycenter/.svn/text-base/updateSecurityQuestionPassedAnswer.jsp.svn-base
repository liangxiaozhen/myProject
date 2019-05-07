<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 原安全问题 开始 -->
<div class="step step-1">
	<span id="safequestion_title_1_1">回答安全问题</span> <span
		id="safequestion_title_1_2">重新设置安全问题</span> <span
		id="safequestion_title_1_3">修改成功</span>
</div>
<div class="verification-from" id="gj_safequestion_answer_edit">
	<div class="item clearfloat">
		<span class="verification-1"> 问题一: </span> <span
			class="verification-2"> ${userAccountSafeInfo.question1} </span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 答案一:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="question_step1_answer_answer1"
			name="answer1" placeholder="请输入答案...">
		</span> <span class="verification-3" style="color: red;"
			id="question_step1_answer_answer1_Err"></span>
	</div>

	<div class="item clearfloat">
		<span class="verification-1"> 问题二: </span> <span
			class="verification-2">${userAccountSafeInfo.question2}</span>
	</div>

	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 答案二:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="question_step1_answer_answer2"
			name="answer2" placeholder="请输入答案...">
		</span> <span class="verification-3" style="color: red;"
			id="question_step1_answer_answer2_Err"></span>
	</div>

	<div class="item clearfloat">
		<span class="verification-1"> 问题三: </span> <span
			class="verification-2"> ${userAccountSafeInfo.question3} </span>
	</div>

	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 答案三:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="question_step1_answer_answer3"
			name="answer3" placeholder="请输入答案...">
		</span> <span class="verification-3" style="color: red;"
			id="question_step1_answer_answer3_Err"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"></span> <span class="verification-2">
			<a id="question_step1_answer" href="javascript:void(0)"
			class="submit">确定</a>
		</span> <span class="verification-3" style="color: red;"
			id="question_step1_answer_submit_Err"></span>
	</div>
</div>
<!-- 原安全问题 结束 -->

<script type="text/javascript">
 	$(function(){
 		$("#question_step1_answer").click(function(){
 			var answer1 = $("#question_step1_answer_answer1").val();
        	var answer2 = $("#question_step1_answer_answer2").val();
        	var answer3 = $("#question_step1_answer_answer3").val();
        	if(isEmpty(answer1)){
        		$("#question_step1_answer_answer1_Err").text("请输入答案一");
        		$("#question_step1_answer_answer1").focus();
        		return false;
        	}else{
        		$("#question_step1_answer_answer1_Err").text("");
        	}
        	if(isEmpty(answer2)){
        		$("#question_step1_answer_answer2_Err").text("请输入答案二");
        		$("#question_step1_answer_answer2").focus();
        		return false;
        	}else{
        		$("#question_step1_answer_answer2_Err").text("");
        	}
        	if(isEmpty(answer3)){
        		$("#question_step1_answer_answer3_Err").text("请输入答案三");
        		$("#question_step1_answer_answer3").focus();
        		return false;
        	}else{
        		$("#question_step1_answer_answer3_Err").text("");
        	}
          	var params = {"answer1":answer1,"answer2":answer2,"answer3":answer3};
          	Security.Former_Answer_Check(params);
 		});
 		 
 	});
</script>

