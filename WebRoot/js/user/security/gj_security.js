/**************************************安全中心对象******************************************************/ 
 var Security = {
	//1修改密保问题
	Update_Security:function(Update_Security_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/question.action",
			  data:Update_Security_Params,
			  success:Update_Security_Callback
		 });
	},
	//2修改交易密码
	Update_TrandingPassword:function(Update_TrandingPassword_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/userPin.action",
			  data:Update_TrandingPassword_Params,
			  success:Update_TrandingPassword_Callback
		 });
	},
	//3修改登录密码
	Update_LoginPwd:function(Update_LoginPwd_Params){
 		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/updatePassword.action",
			  data:Update_LoginPwd_Params,
			  success:Update_LoginPwd_Callback
		 });
	},
	//4设置手机号码
	Insert_Phone:function(Insert_Phone_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserPhoneResetPhone.action",
			  data:Insert_Phone_Params,
			  success:Insert_Phone_Callback
		 });
	},
	//5更改交易密码发送短信
	Send_Ssm:function(Send_Ssm_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/tellCode.action",
			  data:Send_Ssm_Params,
			  success:Send_Ssm_Callback
		 });
	},
	//6密保问题修改 短信验证码校验
	Phone_Code_Check:function(Phone_Code_Check_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/checkphoneCode.action",
			  data:Phone_Code_Check_Params,
			  success:Phone_Code_Check_Callback
		 });
	},
	//7原密保问题 校验比对
	Former_Answer_Check:function(Former_Answer_Check_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/checkAnswerOld.action",
			  data:Former_Answer_Check_Params,
			  success:Former_Answer_Check_Callback
		 });
	},
	//8密保问题修改 发送短信
	Update_Security_Send_Ssm:function(Update_Security_Send_Ssm){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/tellCode.action",
			  data:Update_Security_Send_Ssm,
			  success:Update_Security_Send_Ssm_Callback
		 });
	},
	//9交易密码修改 原密码
	Update_TrandingPassword_Old:function(Update_TrandingPassword_Old_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateTrandingPasswordOld.action",
			  data:Update_TrandingPassword_Old_Params,
			  success:Update_TrandingPassword_Old_Callback
		 });
	},
	//10用户手机号码修改 手机验证码校验比对
	Update_UserPhone_CheckCode:function(Update_UserPhone_CheckCode_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserPhoneCheckCode.action",
			  data:Update_UserPhone_CheckCode_Params,
			  success:Update_UserPhone_CheckCode_Callback
		 });
	},
	//11用户手机号码修改 手机验证码发送
	Update_UserPhone_SendSsm:function(Update_UserPhone_SendSsm_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/tellCode.action",
			  data:Update_UserPhone_SendSsm_Params,
			  success:Update_UserPhone_SendSsm_Callback
		 });
	},
	//12用户手机号码修改 原密保问题答案校验
	Update_UserPhone_CheckAnswer:function(Update_UserPhone_CheckAnswer_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/checkAnswerOld.action",
			  data:Update_UserPhone_CheckAnswer_Params,
			  success:Update_UserPhone_CheckAnswer_Callback
		 });
	},
	//13用户手机号码修改 重新设置手机号
	Update_UserPhone_ResetPhone:function(Update_UserPhone_ResetPhone_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserPhoneResetPhone.action",
			  data:Update_UserPhone_ResetPhone_Params,
			  success:Update_UserPhone_ResetPhone_Callback
		 });
	},
	//14用户手机号码修改 重新设置手机号 发送新手机号短信验证码
	Update_UserPhone_ResetPhone_SendSsm:function(Update_UserPhone_ResetPhone_SendSsm_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserPhoneResetPhoneSendSsm.action",
			  data:Update_UserPhone_ResetPhone_SendSsm_Params,
			  success:Update_UserPhone_ResetPhone_SendSsm_Callback
		 });
	},
	//15用户手机号码設置  发送新手机号短信验证码
	Insert_UserPhone_ResetPhone_SendSsm:function(Insert_UserPhone_ResetPhone_SendSsm_Params){
 		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserPhoneResetPhoneSendSsm.action",
			  data:Insert_UserPhone_ResetPhone_SendSsm_Params,
			  success:Insert_UserPhone_ResetPhone_SendSsm_Callback
		 });
	},
	//16用户邮箱設置  发送邮件验证链接
	Insert_UserEmail_Send:function(Insert_UserEmail_Send_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/InsertUserEmailSendCode.action",
			  data:Insert_UserEmail_Send_Params,
			  success:Insert_UserEmail_Send_Callback
		 });
	},
	//17用户邮箱設置  绑定邮箱
	Bind_UserEmail_Send:function(Bind_UserEmail_Send_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/BindUserEmailSend.action",
			  data:Bind_UserEmail_Send_Params,
			  success:Bind_UserEmail_Send_Callback
		 });
	},
	//18用户邮箱修改  发送邮件验证邮件
	Update_UserEmail_SendEamil:function(Update_UserEmail_SendEamil_Params){
		var submitDom = document.getElementById("update_userEmail_PassedOldEmail_submit");
		submitDom.innerHTML="邮件发送中...";
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserEmailSendEamil.action",
			  error:function(){submitDom.innerHTML="发送验证邮箱"},
			  data:Update_UserEmail_SendEamil_Params,
			  success:Update_UserEmail_SendEamil_Callback
		 });
	},
	//19用户邮箱修改  发送邮件重置链接
	Update_UserEmail_SendResetEamil:function(Update_UserEmail_SendResetEamil_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserEmailSendResetEamil.action",
			  data:Update_UserEmail_SendResetEamil_Params,
			  success:Update_UserEmail_SendResetEamil_Callback
		 });
	},
	//20用户邮箱修改  发送手机短信验证码
	Update_UserEmail_SendPhoneCode:function(Update_UserEmail_SendPhoneCode_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/tellCode.action",
			  data:Update_UserEmail_SendPhoneCode_Params,
			  success:Update_UserEmail_SendPhoneCode_Callback
		 });
	},
	//21用户邮箱修改  校验手机短信验证码
	Update_UserEmail_CheckPhoneCode:function(Update_UserEmail_CheckPhoneCode_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/UpdateUserEmailCheckPhoneCode.action",
			  data:Update_UserEmail_CheckPhoneCode_Params,
			  success:Update_UserEmail_CheckPhoneCode_Callback
		 });
	},
	//22实名制验证
	InsertRealNameCheck:function(InsertRealNameCheck_Params){
		$.ajax({
			  type:"post",
			  url:basePath+"/user/securitycenter/InsertRealNameCheck.action",
			  data:InsertRealNameCheck_Params,
			  success:Insert_RealName_Check_Callback
		 });
	}
};
/**************************************ajax回调函数******************************************************/
//1修改密保问题回调函数	
function Update_Security_Callback(data){
	  var obj = $.parseJSON(data);
	  if(obj.result =="logout"){
		 window.location.href=basePath+"/user/tologin.action";
	  }else if(obj.result =="success"){
		  alert("设置成功");
		 window.location.href=basePath+"/user/securitycenter/list.action";
	  }else if (obj.result =="fail"){
		  alert("保存失败");
	  }else if (obj.result =="error"){
		  alert("网络异常,请稍后再试...");
	  }
};
//2修改交易密码回调函数
function Update_TrandingPassword_Callback(data){
 		var obj = $.parseJSON(data);
		if(obj.result=="logout"){
			window.location.href=basePath+"/user/tologin.action";
		}else if(obj.result == "success"){
			alert("操作成功！");
			window.location.href=basePath+"/user/securitycenter/list.action";
		}else if(obj.result == "fail"){
			$("#gj_Update_TrandingPassword_submitErr").text("保存失败！请稍后重新操作...");
		}else if(obj.result =="code_null"){
			$("#gj_Update_TrandingPassword_submitErr").text("验证码或密码为空！！请重新输入...");
		}else if(obj.result == "code_error"){
			$("#gj_Update_TrandingPassword_submitErr").text("您输入的验证码错误！！请重新输入...");
		}
};

//3修改登录密码回调函数	
function Update_LoginPwd_Callback(data){
 	  $("#update_verification_password_error").text("");
	  var obj = $.parseJSON(data);
	  if(obj.result =="logout"){
		 window.location.href=basePath+"/user/tologin.action";
	  }else if(obj.result =="success"){
		  alert("保存成功");
		 window.location.href=basePath+"/user/securitycenter/list.action";
	  }else if (obj.result =="fail"){
		  $("#update_verification_password_error").text("保存失败,请稍后再试...");
	  }else if (obj.result == "password_null"){
		  $("#update_verification_password_error").text("原登录密码或新登录密码为空...");
	  }else if(obj.result=="password_error"){
		 $("#update_verification_password_error").text("原登录密码错误...");
	 }
};

//4設置手机号码回调函数	
function Insert_Phone_Callback(data){
	$("#insert_user_phone_passedPhone_submit_error").text("");
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
		alert("保存成功");
		window.location.href=basePath+"/user/securitycenter/list.action";
	}else if(obj.result=="tellPhone_null"){
		$("#insert_user_phone_passedPhone_submit_error").text("请输入新手机号码...");
	}else if(obj.result=="code_null"){
		$("#insert_user_phone_passedPhone_submit_error").text("请输入新手机验证码...");
	}else if(obj.result=="ncode_null"){
		$("#insert_user_phone_passedPhone_submit_error").text("请点击发送短信按钮发送短信...");
 	}else if(obj.result =="code_error"){
		$("#insert_user_phone_passedPhone_submit_error").text("您输入的短信验证码有误...");
  	}else if(obj.result =="fail"){
		$("#insert_user_phone_passedPhone_submit_error").text("保存失败,请稍后再试...");
   	}else if(obj.result =="tellPhone_no"){
		$("#insert_user_phone_passedPhone_submit_error").text("手机号码已经注册过了....");
    }
};
 //5更改交易密码发送短信回调函数	
function Send_Ssm_Callback(data){
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
		//添加cookie记录,有效时间60s
        addCookie("secondsremained",60,60); 
		//几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
		timer(60,"gj_Update_TrandingPassword_sms","gj_Update_TrandingPassword_tips","Update_TrandingPassword_Code_SSm");
	}else if(obj.result == "fail"){
		alert("没有找到用户电话号码！短信发送失败！");
	} 
};

//6密保问题修改 短信验证码校验回调函数
function Phone_Code_Check_Callback(data){
 		$("#gj_editphone_code_submitErr").text("");
		var obj = $.parseJSON(data);
		if(obj.result=="logout"){
			window.location.href=basePath+"/user/tologin.action";
		}else if(obj.result == "success"){
			Phone_Code_Check_SeccessCallback();
		}else if(obj.result == "code_error"){
			$("#gj_editphone_code_submitErr").text("请点击发送短信按钮发送短信...");
		}else if(obj.result == "code_null"){
			$("#gj_editphone_code_submitErr").text("请输入短信验证码...");
		}else if(obj.result == "fail"){
			$("#gj_editphone_code_submitErr").text("您输入短信验证码错误...");
		}
};
//密保问题修改 短信验证码校验成功后调用function
function Phone_Code_Check_SeccessCallback(){
	$("#gj_verification_updateone").show();
	$("#gj_verification_updateone").load(basePath+"/user/securitycenter/PhoneCodeCheckSeccessCallback.action?v="+Math.random());
};
//7原密保问题校验回调函数
function Former_Answer_Check_Callback(data){
 		$("#question_step1_answer_submit_Err").text("");
		$("#question_step1_answer_answer1_Err").text("");
		$("#question_step1_answer_answer2_Err").text("");
		$("#question_step1_answer_answer3_Err").text("");
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
		Former_Answer_Check_SeccessCallback();
		}else if(obj.result =="answer_null"){
			$("#question_step1_answer_submit_Err").text("请输入密保答案...");
		}else if(obj.result =="answer1_error"){
			$("#question_step1_answer_answer1_Err").text("密保答案一错误");
		}else if(obj.result =="answer2_error"){
			$("#question_step1_answer_answer2_Err").text("密保答案二错误");
		}else if(obj.result =="answer3_error"){
			$("#question_step1_answer_answer3_Err").text("密保答案三错误");
		}
};
//原密保问题校验成功后调用function
function Former_Answer_Check_SeccessCallback(){
	$("#gj_verification_updateone").show();
	$("#gj_verification_updateone").load(basePath+"/user/securitycenter/FormerAnswerCheckSeccessCallback.action?v="+Math.random());
};
//8密保问题修改 发送短信 回调函数
function Update_Security_Send_Ssm_Callback(data){
 	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
	  addCookie("secondsremained",60,60);
 	  //几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
 	  timer(60,"gj_editphone_code_submit_Send","gj_editphone_code_submit_tips","gj_editphone_code_submit_Send");
 	}else if(obj.result == "fail"){
		alert("没有找到用户电话号码！短信发送失败！");
	} 
};
//9交易密码修改 原密码回调函数
function Update_TrandingPassword_Old_Callback(data){
	$("#gj_Update_TrandingPassword_submitErr").text("");
 	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
		alert("保存成功");
		window.location.href=basePath+"/user/securitycenter/list.action";
   	}else if(obj.result == "fail"){
 		$("#gj_Update_TrandingPassword_submitErr").text("保存失败,请稍后再试...");
	}else if(obj.result =="oldPsw_null"){
		$("#gj_Update_TrandingPassword_submitErr").text("请输入原交易密码...");
	}else if(obj.result =="newPsw_null"){
		$("#gj_Update_TrandingPassword_submitErr").text("请输入新交易密码...");
	}else if(obj.result =="oldPsw_error"){
		$("#gj_Update_TrandingPassword_submitErr").text("原交易密码错误...");
	}
};
//10用户手机号码修改 手机验证码校验 回调函数
function Update_UserPhone_CheckCode_Callback(data){
 	$("#update_user_phone_passedPhone_submit_error").text("");
 	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
		Update_UserPhone_CheckCode_SuccessCallback();
    }else if(obj.result=="code_null"){
   		$("#update_user_phone_passedPhone_submit_error").text("请输入手机短信验证码...");
   	}else if(obj.result=="tellCode_null"){
   		$("#update_user_phone_passedPhone_submit_error").text("请点击发送短信验证码按钮发送短信...");
   	}else if(obj.result =="code_error"){
   		$("#update_user_phone_passedPhone_submit_error").text("您输入的短信验证码错误...");
    }
};

function Update_UserPhone_CheckCode_SuccessCallback(){
	$("#update_user_phone_updateone").show();
	$("#update_user_phone_updateone").load(basePath+"/user/securitycenter/UpdateUserPhoneCheckCodeSuccessCallback.action?v="+Math.random());
 };

//11用户手机号码修改 手机验证码发送 回调函数
function Update_UserPhone_SendSsm_Callback(data){
 	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
	  addCookie("secondsremained",60,60);
	  //几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
 	  timer(60,"update_user_phone_passedPhone_submit_Send","update_user_phone_passedPhone_submit_tips","update_user_phone_passedPhone_submit_Send");
 	}else if(obj.result == "fail"){
		alert("没有找到用户电话号码！短信发送失败！");
	} 
};

//12用户手机号码修改 原密保问题答案校验回调函数
function Update_UserPhone_CheckAnswer_Callback(data){
		$("#update_UserPhone_PassedAnswer_Submit_error").text("");
		$("#update_UserPhone_PassedAnswer_answer1_error").text("");
		$("#update_UserPhone_PassedAnswer_answer2_error").text("");
		$("#update_UserPhone_PassedAnswer_answer3_error").text("");
		var obj = $.parseJSON(data);
		if(obj.result=="logout"){
			window.location.href=basePath+"/user/tologin.action";
		}else if(obj.result == "success"){
 			Update_UserPhone_CheckAnswer_SuccessCallback();
		}else if(obj.result =="answer_null"){
			$("#update_UserPhone_PassedAnswer_Submit_error").text("请输入密保答案...");
		}else if(obj.result =="answer1_error"){
			$("#update_UserPhone_PassedAnswer_answer1_error").text("密保答案一错误");
		}else if(obj.result =="answer2_error"){
			$("#update_UserPhone_PassedAnswer_answer2_error").text("密保答案二错误");
		}else if(obj.result =="answer3_error"){
			$("#update_UserPhone_PassedAnswer_answer3_error").text("密保答案三错误");
		}
			
};

function Update_UserPhone_CheckAnswer_SuccessCallback(){
	$("#update_user_phone_updateone").show();
	$("#update_user_phone_updateone").load(basePath+"/user/securitycenter/UpdateUserPhoneCheckAnswerSuccessCallback.action?v="+Math.random());
};

//13用户手机号码修改 重新设置手机号 回调函数
function Update_UserPhone_ResetPhone_Callback(data){
 	$("#update_UserPhone_Reset_submit_error").text("");
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "success"){
		alert("保存成功");
		window.location.href=basePath+"/user/securitycenter/list.action";
	}else if(obj.result=="tellPhone_null"){
		$("#update_UserPhone_Reset_submit_error").text("请输入新手机号码...");
	}else if(obj.result=="code_null"){
		$("#update_UserPhone_Reset_submit_error").text("请输入新手机验证码...");
	}else if(obj.result=="ncode_null"){
		$("#update_UserPhone_Reset_submit_error").text("请点击发送短信按钮发送短信...");
 	}else if(obj.result =="code_error"){
		$("#update_UserPhone_Reset_submit_error").text("您输入的短信验证码有误...");
  	}else if(obj.result =="fail"){
		$("#update_UserPhone_Reset_submit_error").text("保存失败,请稍后再试...");
   	}else if(obj.result =="tellPhone_no"){
		$("#update_UserPhone_Reset_submit_error").text("手机号码已经注册过啦...");
    	}
};
//14用户手机号码修改 重新设置手机号 发送新手机号短信验证码 回调函数
function Update_UserPhone_ResetPhone_SendSsm_Callback(data){
 	$("#update_UserPhone_Reset_submit_error").text(""); 
  	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "tellPhone_null"){
		$("#update_UserPhone_Reset_submit_error").text("请输入新手机号码...");
	}else if(obj.result == "tellPhone_error"){
		$("#update_UserPhone_Reset_submit_error").text("请输入正確的手机号码...");
	}else if(obj.result == "tellPhone_exit"){
		$("#update_UserPhone_Reset_submit_error").text("手机号码已经注册过了...");
	}else if(obj.result=="success"){
		$("#update_UserPhone_Reset_submit_error").text("新手机短信发送成功");
		addCookie("tellPhone", 60, 60);
        tellTimer(60,"update_UserPhone_Reset_submit_Send","update_UserPhone_Reset_submit_tips","update_UserPhone_Reset_submit_Send");
  	}
};
//15用户手机号码設置 发送新手机号短信验证码 回调函数
function Insert_UserPhone_ResetPhone_SendSsm_Callback(data){
 	$("#insert_user_phone_passedPhone_submit_error").text("");  
 	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "tellPhone_null"){
		$("#insert_user_phone_passedPhone_submit_error").text("请输入新手机号码...");
	}else if(obj.result == "tellPhone_error"){
		$("#insert_user_phone_passedPhone_submit_Send_error").text("请输入正確的手机号码...");
	}else if(obj.result == "tellPhone_exit"){
		$("#insert_user_phone_passedPhone_submit_Send_error").text("手机号码已经注册过了...");
	}else if(obj.result=="success"){
		$("#insert_user_phone_passedPhone_submit_error,#insert_user_phone_passedPhone_submit_error").text("新手机短信发送成功");
		addCookie("tellPhone", 60, 60);
        tellTimer(60,"insert_user_phone_passedPhone_submit_Send","insert_user_phone_passedPhone_submit_tips","insert_user_phone_passedPhone_submit_Send");
  	}
};
//16用户邮箱設置 发送验证链接 回调函数
function Insert_UserEmail_Send_Callback(data){
	$("#inser_UserEmail_submitErr").text("");
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result=="email_null"){
		$("#inser_UserEmail_submitErr").text("请输入邮箱账号...");
	}else if(obj.result =="success"){
		Insert_UserEmail_Send_SuccessCallback();
	}else if(obj.result =="fail"){
		$("#inser_UserEmail_submitErr").text("邮箱发送失败！请稍后再试...");
 	}else if(obj.result == "email_nonull"){
 		$("#inser_UserEmail_submitErr").text("该邮箱已被使用，请输入其他邮箱！");
 	}
};

function Insert_UserEmail_Send_SuccessCallback(){
	$("#inser_UserEmail_submit_bind1").hide();
	$("#inser_UserEmail_submit_bind2").show();
 };
//17用户邮箱設置 绑定邮箱 回调函数
function Bind_UserEmail_Send_Callback(data){
	$("#bind_UserEmail_submitErr").text("");
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result=="code_null"){
		$("#bind_UserEmail_submitErr").text("请输入邮箱验证码...");
	}else if(obj.result =="success"){
		alert("邮箱绑定成功");
		window.location.href=basePath+"/user/securitycenter/list.action";
	}else if(obj.result =="fail"){
		$("#bind_UserEmail_submitErr").text("邮箱绑定失败！请稍后再试...");
 	}else if(obj.result == "code_error"){
 		$("#bind_UserEmail_submitErr").text("邮箱验证码错误...");
 	}
};

//18用户邮箱修改  发送验证邮件 回调函数
function Update_UserEmail_SendEamil_Callback(data){
	var submitDom = document.getElementById("update_userEmail_PassedOldEmail_submit");
	submitDom.innerHTML="发送验证邮箱";
 	$("#update_userEmail_PassedOldEmail_submitErr").text("");
	var obj = $.parseJSON(data);
	if(obj.result=="logout"){
		window.location.href=basePath+"/user/tologin.action";
	}else if(obj.result == "fail"){
		$("#update_userEmail_PassedOldEmail_submitErr").text("邮件发送失败！请稍后重试...");
	}else if(obj.result == "success"){
  		$("#inser_UserEmail_submit_box1").hide();
		$("#inser_UserEmail_submit_box2").show();
  	}
 };
 
//19用户邮箱修改  发送邮件重置链接 回调函数
 function Update_UserEmail_SendResetEamil_Callback(data){
 	 $("#update_UserEmail_Reset_submit_submitErr").text("");
	 var obj = $.parseJSON(data);
	 if(obj.result=="logout"){
		 window.location.href=basePath+"/user/tologin.action";
	 }else if(obj.result =="email_null"){
		 $("#update_UserEmail_Reset_submit_submitErr").text("请输入邮箱账号...");
	 }else if(obj.result == "success"){
		 $("#inser_UserEmail_submit_box2").hide();
		 $("#inser_UserEmail_submit_box3").show();
	 }else if(obj.result == "email_nonull"){
		 $("#update_UserEmail_Reset_submit_submitErr").text("邮箱账号已注册了...");
	 }
 };
//20用户邮箱修改  发送手机短信验证码 回调函数
 function Update_UserEmail_SendPhoneCode_Callback(data){
	 	var obj = $.parseJSON(data);
		if(obj.result=="logout"){
			window.location.href=basePath+"/user/tologin.action";
		}else if(obj.result == "success"){
		  addCookie("secondsremained",60,60);
		  //几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
	 	  timer(60,"update_user_email_passedPhone_submit_Send","update_user_email_passedPhone_submit_Send_tips","update_user_email_passedPhone_submit_Send");
	 	}else if(obj.result == "fail"){
			alert("没有找到用户电话号码！短信发送失败！");
		} 
 };
//21用户邮箱修改  校验手机短信验证码回调函数
 function Update_UserEmail_CheckPhoneCode_Callback(data){
	 $("#update_user_email_passedPhone_submit_error").text("");
	 	var obj = $.parseJSON(data);
		if(obj.result=="logout"){
			window.location.href=basePath+"/user/tologin.action";
		}else if(obj.result == "success"){
			alert(1);
			$("#update_user_email_updateone").show();
			$("#update_user_email_updateone").load(basePath+"/user/securitycenter/updateUserEmailResetByPhone.action?v="+Math.random());
 	 	}else if(obj.result =="code_null"){
 	 		$("#update_user_email_passedPhone_submit_error").text("请输入手机验证码...");
 	 	}else if(obj.result =="fail"){
 	 		$("#update_user_email_passedPhone_submit_error").text("请输入正确的手机验证码...");
 	 	}
 };
//22实名制验证回调函数
 function Insert_RealName_Check_Callback(data){
	 $("#insert_RealName_submit").attr("onclick","RealNameSubmit(this)").text("确定");
	 if(data == "logout"){
		 window.location.href=basePath+"/user/tologin.action";
	 }else{
		 $("#insert_RealName_name_error").text("");
		 $("#insert_RealName_number_error").text("");
		 $("#insert_RealName_submit_error").text("");
	  	 var obj = $.parseJSON(data);
		 if(obj.result == "realName_null"){
	 		 $("#insert_RealName_name_error").text("用户名不能为空");
		 }else if(obj.result == "RealNameNumber_null"){
			 $("#insert_RealName_number_error").text("身份证号码不能为空");
		 }else if(obj.result == "RealNameOrNumber_error"){
			 var count = obj.authtimes;
 			 if(count == 3){
				 $("#insert_RealName_submit_error").text("身份证匹配失败");
 			 }else{
				 $("#insert_RealName_submit_error").text("身份证匹配失败,你还可以匹配"+(3-count)+"次");
			 }
 		 }else if(obj.result == "success"){
			 alert("实名制认证成功");
			 window.location.href=basePath+"/user/securitycenter/list.action";
		 }else if(obj.result == "fail"){
			 alert("实名认证失败,请重新操作！")
			 window.location.href=basePath+"/user/securitycenter/list.action";
		 }else if(obj.result == "MoreThanCount"){
			 alert("你提交的资料超出匹配次数,请联系客服！")
			 window.location.href=basePath+"/user/securitycenter/list.action";
		 } 
	 }
  };
/**************************************函数调用******************************************************/
$(function(){
	//设置用户邮箱
	$(".gj_Vilidator_insertSecurityEmail").click(function(){
 		  Insert_UserEmail_DivShow();
	}); 
	
	//重置用户邮箱
	$(".gj_Vilidator_updateSecurityEmail").click(function(){
		Update_UserEmail_DivShow();
	}); 
	
	//设置用户手机
	 $(".gj_Vilidator_insertSecurityPhone").click(function(){
		 Insert_UserPhone_DivShow();
	 });
	
	 //登录密码div显示
	 $(".gj_Vilidator_updatepassword").click(function(){
 		 Update_LoginPwd_DivShow();
	 }); 
	 
	//交易密码div显示
	 $(".gj_Vilidator_insetTradingPassword").click(function(){
		 Insert_TradingPwd_DivShow();
	 });
	 
	 //未注册手机号
	 $(".gj_Vilidator_insertTradingPasswordByPhone").click(function(){
		 Insert_TradingPwd_DivShow1();
	 });
	 
	 //设置密保问题
	 $(".gj_Vilidator_insertSecurityQuestion").click(function(){
		 Insert_Security_Question_DivShow();
 	 });
	 //修改密保问题
	 $(".gj_Vilidator_updateSecurityQuestion").click(function(){
		 Update_Security_Question_DivShow();
 	 }); 
	 //修改交易密码
	 $(".gj_Vilidator_updateTradingPassword_reset").click(function(){
		 Update_TradingPwd_DivShow();
 	 }); 
	 //修改手机号码
	 $(".gj_Vilidator_updateSecurityPhone").click(function(){
		 Update_UserPhone_DivShow();
 	 }); 
	 
	 //实名验证
	 $(".gj_Vilidator_insertRealname").click(function(){
		 Insert_RealName_DivShow();
 	 }); 
	 
	  
  });

/*********************************页面展现function***********************************************************/

//设置用户邮箱
function Insert_UserEmail_DivShow(){
	if(!$("#verification-box-1").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-1").show();
		 $("#verification-box-1").load(basePath+"/user/securitycenter/InsertUserEmailDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-1").hide();
	}
};

function Update_UserEmail_DivShow(){
	if(!$("#verification-box-1").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-1").show();
		 $("#verification-box-1").load(basePath+"/user/securitycenter/UpdateUserEmailDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-1").hide();
	}
};
 
//设置用户手机
function Insert_UserPhone_DivShow(){
	if(!$("#verification-box-2").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-2").show();
		 $("#verification-box-2").load(basePath+"/user/securitycenter/InsertUserPhoneDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-2").hide();
	}
};
//展现修改用户手机 DIV
function Update_UserPhone_DivShow(){
 	if(!$("#verification-box-2").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-2").show();
		 $("#verification-box-2").load(basePath+"/user/securitycenter/UpdateUserPhoneDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-2").hide();
	}
};

//展现设置密保问题DIV
function Insert_Security_Question_DivShow(){
	if(!$("#verification-box-3").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-3").show();
		 $("#verification-box-3").load(basePath+"/user/securitycenter/InsertSecurityQuestionDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-3").hide();
	}
};

//展现修改密保问题DIV
function Update_Security_Question_DivShow(){
	if(!$("#verification-box-3").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-3").show();
		 $("#verification-box-3").load(basePath+"/user/securitycenter/UpdateSecurityQuestionDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-3").hide();
	}
};

//展现设置交易密码DIV
function Insert_TradingPwd_DivShow(){
	if(!$("#verification-box-4").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-4").show();
		 $("#verification-box-4").load(basePath+"/user/securitycenter/insetTradingPwdDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-4").hide();
	}
};

//展现设置交易密码DIV 未注册手机号
function Insert_TradingPwd_DivShow1(){
	if(!$("#verification-box-4").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-4").show();
		 var html = "<div class='verification-box'>"+
					 "<div class='edit-phone-box clearfloat'>"+
						"<div class='edit-phone change-select'>"+
						"	<p class='title'>未绑定手机</p>"+
						"	<p class='description'>请先绑定手机<br><a href='javascript:Insert_UserPhone_DivShow(this)' style='color:red;' class='gj_Vilidator_updatepassword'>绑定手机</a></p>"+
						"	<i></i>"+
						"</div>"+
					"</div>"+
				"</div>";
		 $("#verification-box-4").html(html);
 	}else{
		 $("#verification-box-4").hide();
	}
};

//展现登录密码DIV
function Update_LoginPwd_DivShow(){
	if(!$("#verification-box-5").is(":visible")){
		$(".li-height-auto").hide();
		$("#verification-box-5").show();
		$("#verification-box-5").load(basePath+"/user/securitycenter/UpdateLoginPwdDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		$("#verification-box-5").hide();
	}
};

//展现修改交易密码DIV
function Update_TradingPwd_DivShow(){
	if(!$("#verification-box-4").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-4").show();
		 $("#verification-box-4").load(basePath+"/user/securitycenter/updateTradingPwdDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-4").hide();
	}
};

//展现实名验证DIV
function Insert_RealName_DivShow(){
	if(!$("#verification-box-10").is(":visible")){
		 $(".li-height-auto").hide();
		 $("#verification-box-10").show();
		 $("#verification-box-10").load(basePath+"/user/securitycenter/insertRealNameDivShow.action?v="+Math.random(),function(data){
			 if(data == "logout"){
 				 window.location.href= basePath + "/user/tologin.action";
			 }
		 });
	}else{
		 $("#verification-box-10").hide();
	}
}

/**************************************工具类******************************************************/
 //几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId正在发送ID clickName事件名称
function timer(timer,btnId,tipsId,clickName){
	 timer = getCookieValue("secondsremained") ? getCookieValue("secondsremained"):timer;
		//显示短信发送框
		$("#"+btnId+"").hide();
		//隐藏正在发送框
		$("#"+tipsId+"").show();
		$("#"+tipsId+"").text((timer<=0)?"发送短信验证码":(""+(timer)+"秒后可以再发送短信验证码"));
		var senderTime = setInterval(function(){
		 if(timer <= 0 ){
			 clearInterval(senderTime);
			//显示短信发送框
			 $("#"+btnId+"").show();
			 $("#"+tipsId+"").hide();
			 $("#"+btnId+"").text("发送短信验证码");
			 $("#"+btnId+"_error").text("");
			 //恢复发送短信点击事件
			 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			 return false;
		 }else{
			 $("#"+tipsId+"").text(""+(timer--)+"秒后可以再发送短信验证码");
			 editCookie("secondsremained",timer,timer+1);
		 }
	},1000);
};

function tellTimer(timer,btnId,tipsId,clickName){
	 timer = getCookieValue("tellPhone") ? getCookieValue("tellPhone"):timer;
		//显示短信发送框
		$("#"+btnId+"").hide();
		//隐藏正在发送框
		$("#"+tipsId+"").show();
		$("#"+tipsId+"").text((timer<=0)?"发送短信验证码":(""+(timer)+"秒后可以发送短信验证码"));
		var senderTime = setInterval(function(){
		 if(timer <= 0 ){
			 clearInterval(senderTime);
			//显示短信发送框
			 $("#"+btnId+"").show();
			 $("#"+tipsId+"").hide();
			 $("#"+btnId+"").text("发送短信验证码");
			 $("#"+btnId+"_error").text("");
			 //恢复发送短信点击事件
			 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			 return false;
		 }else{
			 $("#"+tipsId+"").text(""+(timer--)+"秒后可以发送短信验证码");
			 editCookie("tellPhone",timer,timer+1);
		 }
	},1000);
};

//发送手机验证码时添加cookie
function addCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    //判断是否设置过期时间,0代表关闭浏览器时失效
    if(expiresHours>0){ 
        var date=new Date(); 
        date.setTime(date.getTime()+expiresHours*1000); 
        cookieString=cookieString+";expires=" + date.toUTCString(); 
    } 
        document.cookie=cookieString; 
}; 

//修改cookie的值
function editCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    if(expiresHours>0){ 
      var date=new Date(); 
      date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
      cookieString=cookieString+";expires=" + date.toGMTString(); 
    } 
      document.cookie=cookieString; 
}; 

//根据名字获取cookie的值
function getCookieValue(name){ 
      var strCookie=document.cookie; 
      var arrCookie=strCookie.split("; "); 
      for(var i=0;i<arrCookie.length;i++){ 
        var arr=arrCookie[i].split("="); 
        if(arr[0]==name){
          return unescape(arr[1]);
          break;
        } 
      } 
 };
 
/*空判断*/
function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
};

/*非空判断*/
function isNotEmpty(val) {
	return !isEmpty(val);
};