<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-box">
	<div class="verification-from" id="gj_safequestion_answer_edit">
		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b> 身份证姓名:
			</span> 
			<span class="verification-2"> 
				<input type="text" class="input-text-2" id="insert_RealName_name" />
			</span> 
			  <span class="verification-3" style="color: red;"
				id="insert_RealName_name_error"></span>
		</div>
		
		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b> 身份证号码:
			</span> 
			<span class="verification-2"> 
				<input type="text" class="input-text-2" id="insert_RealName_number" />
			</span> 
			<span class="verification-3" style="color: red;" id="insert_RealName_number_error"></span>
		</div>
		<div class="item clearfloat">
			<span class="verification-1"></span> <span class="verification-2">
				<a href="javascript:void(0)" class="submit" onclick="RealNameSubmit(this)"
				id="insert_RealName_submit">确定</a>
			</span> <span class="verification-3" style="color: red;"
				id="insert_RealName_submit_error"></span>
		</div>
	</div>
</div>

<script type="text/javascript">
 	 
 function RealNameSubmit(obj){
	 var insert_RealName_name = $("#insert_RealName_name").val();
	 var insert_RealName_number = $("#insert_RealName_number").val();
	 if(isEmpty(insert_RealName_name)){
		 $("#insert_RealName_name_error").text("用户名不能为空");
		 return ;
	 }else{
		 $("#insert_RealName_name_error").text("");
	 }
	 
	 if(isEmpty(insert_RealName_number)){
		 $("#insert_RealName_number_error").text("身份证号码不能为空");
		 return ;
	 }else{
		 $("#insert_RealName_number_error").text("");
	 }
 	 
	 $("#insert_RealName_submit").removeAttr("onclick").text("提交中...");
	 var params = {"RealName":insert_RealName_name,"RealNameNumber":insert_RealName_number};
	 Security.InsertRealNameCheck(params);
 }
 
 
</script>