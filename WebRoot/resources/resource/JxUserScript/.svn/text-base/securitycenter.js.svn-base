$(function(){
   	 $(".side_nav").find("dl").siblings().removeClass("navcurron").eq(4).addClass("navcurron");
   });

var pwdFlag = false,repwdFlag = false,questionFlag = false,questionUpdateFlag = false;
;var securitycenter = (function(){
	return{
		//判断开户条件是否满足
		openAnAccountCheck:function(obj){
			$.ajax({
				type:"post",
				url:basePath + "/user/securitycenter/openAnAccountCheck.action",
				success:function(data){
					gj_logout(data);
 					var obj = $.parseJSON(data);
					if(obj.result == "loginname_null"){
 						
					}else if(obj.result == "phone_null"){
 						tipBoxConf.showPhoneConf();
					}else if(obj.result == "email_null"){
						tipBoxConf.showEmailConf();
					}else if(obj.result == "realName_null"){
						tipBoxConf.showRealNameConf();
 					}else if(obj.result == "question_null"){
 						tipBoxConf.showQuestionConf();
					}else if(obj.result == "success"){
 						 window.location.href = basePath + "/user/securitycenter/getopenAnAccountList.action";
					}
					
				}
			});
		},
//  		//开户
// 		openAnAccount:function(obj){
// 			var code = $("#code").val();
// 			if(isEmpty(code)){
// 				$("#openAnAccountCodeError").show().text("请输入短信验证码");
// 				$("#code").focus();
// 				return ;
// 			}else{
// 				$("#openAnAccountCodeError").text("").hide();
// 			}
// 			 $.ajax({
// 				type:"post",
// 				data:{"code":code},
// 				url:basePath + "/user/securitycenter/openAnAccount.action",
//  				success:function(data){
//  					gj_logout(data);
// 					$("#sc_openAnAccountMassage").text("").hide();
// 					var obj = $.parseJSON(data);
// 					if(obj.result == "code_null"){
//  						alert("请输入验证码");
// 					}else if(obj.result == "code_error"){
//  						alert("输入的验证码不一致!请输入正确的验证码");
// 					}else if(obj.result == "fail"){
//  						alert("因网络异常开户不成功,请重新操作！");
// 					}else if(obj.result == "success"){
// 						alert("开户成功！");
// 						window.location.href = basePath + "/user/securitycenter/list.action";
// 					}else if(obj.result == "tocode_null"){
//  						alert("请点击短信发送");
// 					}
// 				}
// 			});
//			
//		},
		
//		//发送短信（开户）
//		sendSSMByOpenAnAccount:function(obj){
// 			var optimer = getCookieValue("opendAnAccount");
//			if(optimer > 0){
//				cookieTimer("60", "openAnaccountSendSSM", "openAnaccountSendSSMTip", "securitycenter.sendSSMByOpenAnAccount", "opendAnAccount");
//				return ;
//			}
// 			$.ajax({
//				type:"post",
//				url:basePath + "/user/securitycenter/sendSSMByOpenAnAccount.action",
// 				success:function(data){
// 					gj_logout(data);
//  					$("#sc_openAnaccountSendSSMMassage").text("").hide();
// 					var obj = $.parseJSON(data);
//					if(obj.result == "success"){
//   						addCookie("opendAnAccount", 60, 60);
// 						$("#sc_openAnAccountMassage").show().css({"color":"red"}).text("短信发送成功！");
// 						cookieTimer("60", "openAnaccountSendSSM", "openAnaccountSendSSMTip", "securitycenter.sendSSMByOpenAnAccount", "opendAnAccount");
//					}else if(obj.result == "fail"){
//						$("#sc_openAnaccountSendSSMMassage").show().css({"color":"red"}).text("短信发送失败,请重新点击发送短信按钮");
//					}
//				}
//				
//			});
//		},
		//发送短信（修改登录密码）
		sendSSMByLoginPassWord:function(obj){
 			var logiTimer = getCookieValue("LoginPassWord");
 			if(logiTimer > 0){
 				cookieTimer(60, "sc_sendSSMByLoginPwd", "sc_sendSSMByLoginPwdTip", "securitycenter.sendSSMByLoginPassWord", "LoginPassWord");	
				return ;
			}
 			$.ajax({
				type:"post",
 				url:basePath + "/user/securitycenter/sendSSMByLoginPassWord.action",
 				success:function(data){
 					gj_logout(data);
 					var obj = $.parseJSON(data);
 					if(obj.result == "success"){
  						addCookie("LoginPassWord", 60, 60);
 						cookieTimer(60, "sc_sendSSMByLoginPwd", "sc_sendSSMByLoginPwdTip", "securitycenter.sendSSMByLoginPassWord", "LoginPassWord");	
 					}else if(obj.result == "fail"){
 						alert("短信发送失败！请重新发送！");
 					}else if(obj.result == "params_error"){
 						alert("参数错误,请重新操作！");
 					}
 					
 				}
			});
		},
		//修改登录密码
		loginPwdChange:function(obj){
 			var sc_LoginPwdCode = $("#sc_LoginPwdCode").val();
			var sc_loginPwd 	= $("#sc_loginPwd").val();
			var sc_loginPwd0	= $("#sc_loginPwd0").val();
 			if(isEmpty(sc_LoginPwdCode)){
				$("#sc_sendSSMByLoginPwdMassage").show().css({"color":"red"}).text("请输入短信验证码");
				return ;
			}else{
				$("#sc_sendSSMByLoginPwdMassage").text("").hide();
			}
 			blurAll();
  			 
 			if(!pwdFlag){
				return ;
			}
			
			if(!repwdFlag){
				return ;
			}
			$.ajax({
				type:"post",
				data:{"code":sc_LoginPwdCode,"newpwd":sc_loginPwd},
				url:basePath + "/user/securitycenter/loginPwdChange.action",
				success:function(data){
					gj_logout(data);
 					var obj = $.parseJSON(data);
					if(obj.result == "code_null"){
						alert("请输入验证码");
					}else if(obj.result == "newpwd_null"){
						alert("请输入新登录密码");
					}else if(obj.result == "toCode_null"){
						alert("请点击短信发送按钮发送短信");
					}else if(obj.result == "code_error"){
						alert("请输入正确的短信验证码");
					}else if(obj.result == "success"){
						$("#loginPwd").hide();
						$("#sucessLoginPwd").show();
					}else if(obj.result == "fail"){
						alert("因网络异常！登录密码修改失败,请重新操作!");
					}
				}
			});
		},
		//发送手机验证码 （手机号码验证）
		sendSSMByPhoneInsert:function(obj){
 			var phone = $("#sc_phoneInsert").val();
			if(isEmpty(phone)){
				$("#sc_phoneInsertMassage").show().addClass("error_1").text("请输入手机号码");
				$("#sc_phoneInsert").focus();
				return ;
			}else{
				$("#sc_phoneInsertMassage").text("").hide();
			}
			var timer = getCookieValue("PhoneInsert");
			if(timer > 0){
 				cookieTimer(60, "sc_phoneInsertSendSSM", "sc_phoneInsertSendSSMTip", "securitycenter.sendSSMByPhoneInsert", "PhoneInsert");
				return ;
			}
			$.ajax({
				type:"post",
				data:{"phone":phone},
 				url:basePath + "/user/securitycenter/sendSSMByPhoneInsert.action",
 				success:function(data){
 					gj_logout(data);
  					var obj = $.parseJSON(data);
 					if(obj.result == "phone_null"){
 						alert("请输入手机号码");
 					}else if(obj.result =="phone_error"){
 						alert("请输入正确的手机号码");
 					}else if(obj.result =="success"){
 						alert("短信发送成功");
 						addCookie("PhoneInsert",60,60);
  					    cookieTimer(60, "sc_phoneInsertSendSSM", "sc_phoneInsertSendSSMTip", "securitycenter.sendSSMByPhoneInsert", "PhoneInsert");
 					}else if(obj.result =="fail"){
 						alert("因网络异常，短信发送不成功！请重新操作！");
 					}else if(obj.result =="phoneed"){
 						alert("手机号码已注册！有问题请找客服！");
 					}else if(obj.result =="network_error"){
 						alert("因网络异常，短信发送不成功！请重新操作！有问题请找客服！");
 					}else if(obj.result =="authenticated"){
 						alert("手机已验证过了");
 					}    
 				}
			});
		},
		//手机验证
		phoneInsertSave:function(obj){
			var phone = $("#sc_phoneInsert").val();
			var code = $("#sc_phoneInsertCode").val();
			if(isEmpty(phone)){
				$("#sc_phoneInsertMassage").show().addClass("error_1").text("请输入手机号码");
				$("#sc_phoneInsert").focus();
				return ;
			}else{
				$("#sc_phoneInsertMassage").text("").hide();
			}
			
			if(isEmpty(code)){
				$("#sc_phoneInsertCodeMassage").show().css({"color":"red"}).text("请输入手机短信验证码");
				$("#sc_phoneInsertCodeMassage").focus();
				return ;
			}else{
				$("#sc_phoneInsertCodeMassage").text("").hide();
			}
 			$.ajax({
				type:"post",
				url:basePath + "/user/securitycenter/phoneInsertSave.action",
				data:{"phone":phone,"code":code},
				success:function(data){
					gj_logout(data);
					
					var obj = $.parseJSON(data);
 					if(obj.result == "phone_null"){
 						alert("请输入手机号码");
 					}else if(obj.result =="code_null"){
 						alert("请输入验证码");
 					}else if(obj.result =="tocode_null"){
 						alert("请发送短信验证码");
  					}else if(obj.result =="fail"){
 						alert("手机验证失败！");
 					}else if(obj.result =="success"){
 						alert("手机验证成功");
 						window.location.href = basePath +"/user/securitycenter/list.action";
 					}else if(obj.result =="tophone_null"){
 						alert("请输入发送短信验证码的手机号");
 					}else if(obj.result =="code_error"){
 						alert("短信验证码错误！");
 					} 
				}
			});
		},
		//发送邮件（邮箱验证）
		sendEmailByBindEmail:function(obj){
			var email = $("#emailInserCode").val();
			if(isEmpty(email)){
				$("#emailInserCodeMSG").show().html("<em></em>请输入邮箱账号");
				$("#emailInserCode").focus();
				return ;
			}else{
				if(!is_email(email)){
					$("#emailInserCodeMSG").show().html("<em></em>请输入正确是邮箱账号");
					$("#emailInserCode").focus();
					return ;
				}
				$("#emailInserCodeMSG").html("").hide();
			}
			$("#sendEmailByBindEmail").removeAttr("onclick").text("提交处理中...");
 			$.ajax({
				type:"post",
				url:basePath + "/user/securitycenter/sendEmailByBindEmail.action",
				error:function(){$("#sendEmailByBindEmail").attr("onclick","securitycenter.sendEmailByBindEmail(this)").text("下一步");},
				data:{"email":email},
				success:function(data){
					gj_logout(data);
					$("#emailInserCodeMSG").html("").hide();
					$("#sendEmailByBindEmail").attr("onclick","securitycenter.sendEmailByBindEmail(this)").text("下一步");
 					var obj = $.parseJSON(data);
 					if(obj.result == "email_null"){
 						$("#emailInserCodeMSG").show().html("<em></em>请输入邮箱账号");
 					}else if(obj.result =="email_error"){
 						$("#emailInserCodeMSG").show().html("<em></em>请输入正确的邮箱账号");
 					}else if(obj.result =="fail"){
 						$("#emailInserCodeMSG").show().html("<em></em>因网络异常,邮件发送失败！请重新操作");
 					}else if(obj.result =="success"){
  						$("#mailboxSet1").hide();
 						$("#mailboxSet2").show();
 						$(".flowstep").find("#step_2").addClass("active");
  			    		$("#EmailInsertLink").text(obj.email);
  					}else if(obj.result =="email_registered"){
  						$("#emailInserCodeMSG").show().html("<em></em>你输入的邮箱已注册");
 					}else if(obj.result =="network_error"){
 						$("#emailInserCodeMSG").show().html("<em></em>网络异常,请重新操作");
 					}  
				}
			});
		},
		//重新发送邮件
		againSendEmailByBindEmail:function(){
 			var email = document.getElementById("EmailInsertLink").innerHTML;
 			var timer = getCookieValue("againSendEmailByBindEmail");
 			if(timer > 0){
				cookieTimer3(60, "againSendEmailByBindEmail", "againSendEmailByBindEmailTip", "securitycenter.againSendEmailByBindEmail", "againSendEmailByBindEmail");
   				return ;
 			}
 			$.ajax({
				type:"post",
				url:basePath + "/user/securitycenter/sendEmailByBindEmail.action",
				data:{"email":email},
				success:function(data){
					gj_logout(data);
 					var obj = $.parseJSON(data);
 					if(obj.result == "email_null"){
 						alert("请输入邮箱账号");
 					}else if(obj.result =="email_error"){
 						alert("请输入正确的邮箱账号");
 					}else if(obj.result =="fail"){
 						alert("因网络异常,邮件发送失败！请重新操作");
 					}else if(obj.result =="success"){
 						alert("邮件重发成功！");
 						addCookie("againSendEmailByBindEmail", 60, 60);
 						cookieTimer3(60, "againSendEmailByBindEmail", "againSendEmailByBindEmailTip", "securitycenter.againSendEmailByBindEmail", "againSendEmailByBindEmail");
  					}else if(obj.result =="email_registered"){
 						alert("邮箱已注册");
 					}else if(obj.result =="network_error"){
 						alert("网络异常");
 					} 
				}
			});
		},
		//发送短信（设置密保问题）
		sendSSmBySQInsert:function(obj){
 			var timer = getCookieValue("SQInsert");
			if(timer > 0){
 				cookieTimer2(60, "questionInsertSSM", "questionInsertSSMTip", "securitycenter.sendSSmBySQInsert", "SQInsert");
				return ;
			}
			$.ajax({
				type:"post",
  				url:basePath + "/user/securitycenter/sendSSmBySQInsert.action",
 				success:function(data){
 					gj_logout(data);
  					var obj = $.parseJSON(data);
 					 if(obj.result =="success"){
  						addCookie("PhoneInsert",60,60);
  					    cookieTimer2(60, "questionInsertSSM", "questionInsertSSMTip", "securitycenter.sendSSmBySQInsert", "SQInsert");
 					}else if(obj.result =="fail"){
 						alert("因网络异常，短信发送不成功！请重新操作！");
 					}else if(obj.result == "phone_error"){
 						alert("手机未验证");
 					}    
 				}
			});
		},
		//密保问题设置 验证短信验证码
		securityQuestionInsertOne:function(obj){
			var code = $("#quverify").val();
			if(isEmpty(code)){
				alert("请输入手机短信验证码");
				$(".inputVlue").hide();
				$("#quverify").focus();
				return ;
			}
			$("#securityQuestionInsertOne").removeAttr("onclick").text("处理中...");
			$.ajax({
				type:"post",
				data:{"code":code},
  				url:basePath + "/user/securitycenter/securityQuestionInsertOne.action",
  				error:function(){$("#securityQuestionInsertOne").attr("onclick","securitycenter.securityQuestionInsertOne(this);").text("下一步");},
 				success:function(data){
 					$("#securityQuestionInsertOne").attr("onclick","securitycenter.securityQuestionInsertOne(this);").text("下一步");
 					gj_logout(data);
  					var obj = $.parseJSON(data);
 					 if(obj.result =="success"){
 						 $("#safeQuestionStep2").addClass("active");
 						 $("#questionStep1").hide();
 						 $("#questionStep2").show();
   					}else if(obj.result =="tocode_null"){
 						alert("请点击短信发送按钮");
 					}else if(obj.result == "code_error"){
 						alert("请输入正确的手机短信验证码");
 					}else if(obj.result == "code_null"){
 						alert("请输入手机短信验证码");
 					}    
 				}
			});
		},
		//密保问题设置 选择密保问题和答案
		securityQuestionInsertTwo:function(obj){
			checkQuestionInsertTwo();
			if(!questionFlag){
				return;
			}
			var question1 = $("#IquestionOne").val(); 
 			var question2 = $("#IquestionTwo").val(); 
			var question3 = $("#IquestionThree").val(); 
			var answer1   = $("#Ianswer1").val();
			var answer2	  = $("#Ianswer2").val();
			var answer3	  = $("#Ianswer3").val();
			var params = {"question1":question1,"question2":question2,"question3":question3,"answer1":answer1,"answer2":answer2,"answer3":answer3};
			$("#securityQuestionInsertTwo").removeAttr("onclick").text("处理中...");
			$.ajax({
				type:"post",
				data:params,
  				url:basePath + "/user/securitycenter/securityQuestionInsertTwo.action",
  				error:function(){$("#securityQuestionInsertTwo").attr("onclick","securitycenter.securityQuestionInsertTwo(this);").text("下一步");},
 				success:function(data){
 					$("#securityQuestionInsertTwo").attr("onclick","securitycenter.securityQuestionInsertTwo(this);").text("下一步");
 					gj_logout(data);
  					$("#allQuestionMsgError").text("密保问题可用于找回登录密码操作");
   					var obj = $.parseJSON(data);
 					if(obj.result =="success"){
 						$("#questionStep2").hide();
 						$("#questionStep3").show();
 						$("#Iquestion2-1").val(obj.question1);
 						$("#Iquestion2-2").val(obj.question2);
 						$("#Iquestion2-3").val(obj.question3);
 						$("#Ianswer2-1").val(obj.answer1);
 						$("#Ianswer2-2").val(obj.answer2);
 						$("#Ianswer2-3").val(obj.answer3);
 						$("#safeQuestionStep3").addClass("active");
  					}else if(obj.result =="question1_null"){
 						$("#allQuestionMsgError").text("请选择问题一");
  					}else if(obj.result == "question2_null"){
 						$("#allQuestionMsgError").text("请选择问题二");
  					}else if(obj.result == "question3_null"){
 						$("#allQuestionMsgError").text("请选择问题三");
  					} else if(obj.result == "answer1_null"){
 						$("#allQuestionMsgError").text("请输入问题一答案");
  					}else if(obj.result == "answer2_null"){
 						$("#allQuestionMsgError").text("请输入问题二答案");
  					}else if(obj.result == "answer3_null"){
 						$("#allQuestionMsgError").text("请输入问题三答案");
  					}else if(obj.result == "timer_out"){
 						$("#allQuestionMsgError").text("短信验证码失效,请返回第一步重新验证!");
  					}    
 				}
			});
		},
		////密保问题设置  确认密保问题和答案
		securityQuestionInsertThree:function(obj){
			var question1 =	$("#Iquestion2-1").val();
			var question2 =	$("#Iquestion2-2").val();
			var question3 =	$("#Iquestion2-3").val();
			var answer1   = $("#Ianswer2-1").val();
			var answer2   =	$("#Ianswer2-2").val();
			var answer3 =	$("#Ianswer2-3").val();
			var params = {"question1":question1,"question2":question2,"question3":question3,"answer1":answer1,"answer2":answer2,"answer3":answer3};
			$("#securityQuestionInsertThree").removeAttr("onclick").text("处理中...");
			$.ajax({
				type:"post",
				data:params,
  				url:basePath + "/user/securitycenter/securityQuestionInsertThree.action",
  				error:function(){$("#securityQuestionInsertThree").attr("onclick","securitycenter.securityQuestionInsertThree(this);").text("确定");},
 				success:function(data){
 					$("#securityQuestionInsertThree").attr("onclick","securitycenter.securityQuestionInsertThree(this);").text("确定");
 					gj_logout(data);
     				var obj = $.parseJSON(data);
    				if(obj.result =="params_error"){
    					  alert("参数错误,请重新操作！");
    					  window.location.href = basePath + "/user/securitycenter/list.action";
  					}else if(obj.result == "success"){
  						$("#questionStep3").hide();
  						$("#questionStep4").show();
  						$("#safeQuestionStep4").addClass("active");
  					}else if(obj.result == "timer_out"){
  						alert("短信验证码失效,请返回第一步重新验证!");
  					}else if(obj.result == "fail"){
 						 alert("因网络响应不及时,保存失败,请重新操作！");
  					}
    				
 				}
			});
		}
};
	
})();

//安全中心修改
var SecuritycenterUpate = (function(){
		return {
			//根据原手机修改 下一步
			updatePhoneByOldPhoneOne:function(obj){
				var verifyTest  = $("#verifyTest").val();
				var phoneUpdate = $("#phoneUpdate").val();
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/updatePhoneByOldPhoneOne.action",
					data:{"code":verifyTest,"phone":phoneUpdate},
					success:function(data){
						gj_logout(data);
						
						var obj = $.parseJSON(data);
						if(obj.result == "phone_null"){
							alert("请输入手机号码");
						}else if(obj.result == "code_null"){
							alert("请输入手机短信验证码");
						}else if(obj.result == "phone_format"){
							alert("请输入正确的手机号码");
						}else if(obj.result == "tocode_null"){
							alert("请点击短信验证码发送按钮");
						}else if(obj.result == "code_error"){
							alert("短信验证码错误");
						}else if(obj.result == "phone_error"){
							alert("输入的手机号与原手机号不一致");
						}else if(obj.result == "success"){
							$("#changeOldStep2").addClass("active");
							$("#oldStep1").hide();
							$("#oldStep2").show();
						}
						
					}
				});
			},
			// 发送手机短信（根据原手机修改手机号）
			sendSSMByOldPhone:function(obj){
 				var phoneUpdate = $("#phoneUpdate").val();
				if(isEmpty(phoneUpdate)){
					$("#phoneMSG").show().text("请输入手机号码");
					$("#phoneUpdate").focus();
					return;
				}else{
					if(!is_phone(phoneUpdate)){
						$("#phoneMSG").show().text("请输入正确的手机号码");
						$("#phoneUpdate").focus();
						return;
					}
					$("#phoneMSG").text("").hide();
				}
				
				var cookieTimer = getCookieValue("sendSSMByOldPhone");
				if(cookieTimer > 0){
					 cookieTimer2(cookieTimer, "click3", "countDown3", "SecuritycenterUpate.sendSSMByOldPhone", "sendSSMByOldPhone");
					 return;
				}
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/sendSSMByOldPhone.action",
					data:{"phone":phoneUpdate},
					success:function(data){
						gj_logout(data);
						
						var obj = $.parseJSON(data);
						if(obj.result == "phone_null"){
							 alert("请输入原手机号码");
						}else if(obj.result == "phone_format"){
							 alert("请输入正确的手机号码");
						}else if(obj.result == "phone_error"){
							 alert("请输入原来的手机号码");
						}else if(obj.result == "success"){
							 alert("短信发送成功");
							 addCookie("sendSSMByOldPhone", 60, 60);
							 cookieTimer2(60, "click3", "countDown3", "SecuritycenterUpate.sendSSMByOldPhone", "sendSSMByOldPhone");
						}else if(obj.result == "fail"){
							alert("短信发送失败,请重新操作！");
						}
					}
				});
			},
			//修改手机号码 保存新手机号码（根据原手机号修改）
			updatePhoneByOldPhoneTwo:function(obj){
 				var newPhone = $("#newPhone").val();
 				var code 	 = $("#newPhoneVerify").val();
 				$.ajax({
 					type:"post",
 					url:basePath +"/user/securitycenter/updatePhoneByOldPhoneTwo.action",
 					data:{"phone":newPhone,"code":code},
 					success:function(data){
 						gj_logout(data);
 						
 						var obj = $.parseJSON(data);
 						if(obj.result == "phone_null"){
 							alert("请输入手机号码");
 						}else if(obj.result == "code_null"){
 							alert("请输入短信验证码");
 						}else if(obj.result == "tocode_null"){
 							alert("操作超时请重新操作");
 							window.location.href = basePath + "/user/securitycenter/list.action";
 						}else if(obj.result == "code_error"){
 							alert("请正确的短信验证码");
 						}else if(obj.result == "phone_error"){
 							alert("请输入发送短信的手机号码");
 						}else if(obj.result == "fail"){
 							alert("保存失败,请重新操作");
 						}else if(obj.result == "success"){
 							$("#changeOldStep3").addClass("active");
 							$("#changeOldStep3ByOldEmail").addClass("active");
  							$("#oldStep2").hide();
 							$("#oldStep3").show();
 						}
 					}
 				});
			},
			//发送手机短信验证码（新手机）
			sendSSMByNewPhone:function(obj){
				var newPhone = $("#newPhone").val();
				if(isEmpty(newPhone)){
					$("#phoneMSG4").show().text("请输入手机号码");
					$("#newPhone").focus();
					return;
				}else{
					if(!is_phone(newPhone)){
						$("#phoneMSG4").show().text("请输入正确的手机号码");
						$("#newPhone").focus();
						return;
					}
					$("#phoneMSG4").text("").hide();
				}
				
				var cookieTimer = getCookieValue("sendSSMByNewPhone");
				if(cookieTimer > 0){
					 cookieTimer2(cookieTimer, "click4", "countDown4", "SecuritycenterUpate.sendSSMByNewPhone", "sendSSMByNewPhone");
					 return;
				}
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/sendSSMByNewPhone.action",
					data:{"phone":newPhone},
					success:function(data){
						gj_logout(data);
 						var obj = $.parseJSON(data);
						if(obj.result == "phone_null"){
							 alert("请输入手机号码");
						}else if(obj.result == "phone_format"){
							 alert("请输入正确的手机号码");
						}else if(obj.result == "phone_existing"){
							 alert("手机号已注册");
						}else if(obj.result == "params_error"){
							 alert("请点击短信发送按钮发送短信验证码");
 						}else if(obj.result == "success"){
							 alert("短信发送成功");
							 addCookie("sendSSMByNewPhone", 60, 60);
							 cookieTimer2(60, "click4", "countDown4", "SecuritycenterUpate.sendSSMByNewPhone", "sendSSMByNewPhone");
						}else if(obj.result == "fail"){
							alert("短信发送失败,请重新操作！");
						}
					}
				});
			},
			//修改手机号码 发送邮件验证码（根据原邮箱修改）
			sendEmailByOldEmail:function(obj){
				var cookieTimer = getCookieValue("sendEmailByOldEmail");
				if(cookieTimer > 0){
					cookieTimer2(60, "oldemailclick3", "oldemailcountDown3", "SecuritycenterUpate.sendEmailByOldEmail", "sendEmailByOldEmail");
 					return ;
				}
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/sendEmailByOldEmail.action",
					success:function(data){
						gj_logout(data);
						var obj = $.parseJSON(data);
						if(obj.result == "params_error"){
							alert("参数错误！邮箱未验证或邮箱为空");
							window.location.href = basePath + "/user/securitycenter/list.action";
						}else if(obj.result == "success"){
							alert("邮箱验证码发送成功！");
							addCookie("sendEmailByOldEmail", 60, 60);
							cookieTimer2(60, "oldemailclick3", "oldemailcountDown3", "SecuritycenterUpate.sendEmailByOldEmail", "sendEmailByOldEmail");
						}else if(obj.result == "fail"){
							alert("邮箱验证码发送失败！请重新操作");
						}
					}
					
				});
			},
			//修改手机号码下一步（根据原邮箱修改）
			updatePhoneByOldEmailOne:function(data){
				var codeByOldEmail = $("#codeByOldEmail").val();
				if(isEmpty(codeByOldEmail)){
					alert("请输入邮箱验证码");
					$("#codeByOldEmail").focus();
					return ;
				}
				$.ajax({
					type:"post",
					data:{"code":codeByOldEmail},
					url:basePath + "/user/securitycenter/updatePhoneByOldEmailOne.action",
					success:function(data){
						gj_logout(data);
 						var obj = $.parseJSON(data);
						if(obj.result == "code_null"){
							alert("请输入邮箱验证码");
						}else if(obj.result == "tocode_null"){
							alert("请点击邮箱验证码发送按钮");
						}else if(obj.result == "code_error"){
							alert("请输入正确的邮箱验证码");
						}else if(obj.result == "success"){
							$("#changeOldStep2ByOldEmail").addClass("active");
							$("#OldEmailStep1").hide();
 							$("#oldStep2").show();
 						}
					}
					
				});
			},
			//检查原邮箱是否正确
			UpdateEmailCheck:function(obj){
				var emailUpdateCode = $("#emailUpdateCode").val();
				if(isEmpty(emailUpdateCode)){
					$("#emailUpdateCode").focus();
					$("#emailUpdateCodeMSG").show().text("请输入原邮箱账号");
					return ;
				}else{
					if(!is_email(emailUpdateCode)){
						$("#emailUpdateCode").focus();
						$("#emailUpdateCodeMSG").show().text("您输入的邮箱格式错误！请输入正确的邮箱");
						return ;
					}
					$("#emailUpdateCodeMSG").text("").hide();
 				}
				$("#UpdateEmailCheck").removeAttr("onclick").text("处理中...");
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/UpdateEmailCheck.action",
					data:{"email":emailUpdateCode},
					error:function(){$("#UpdateEmailCheck").attr("onclick","SecuritycenterUpate.UpdateEmailCheck(this);").text("下一步");},
					success:function(data){
						$("#UpdateEmailCheck").attr("onclick","SecuritycenterUpate.UpdateEmailCheck(this);").text("下一步");
						gj_logout(data);
						var obj = $.parseJSON(data);
						if(obj.result == "email_null"){
							alert("请输入邮箱账号");
						}else if(obj.result == "email_format"){
							alert("您输入的邮箱账号格式错误");
						}else if(obj.result == "email_error"){
							alert("您输入的邮箱账号与原邮箱账号不一致");
						}else if(obj.result == "success"){
							$("#emailUpdateStep_2").addClass("active");
							$("#emailUpdateboxSet1").hide();
							$("#emailUpdateboxSet2").show();
						}
					}
				});
			},
			//邮箱修改 给新邮箱账号发送邮箱重置链接
			sendEmailByNewEmail:function(obj){
				var emailUpdateCode = $("#emailUpdateNewCode").val();
				if(isEmpty(emailUpdateCode)){
					$("#emailUpdateNewCode").focus();
					$("#emailUpdateNewCodeMSG").show().text("请输入新邮箱账号");
					return ;
				}else{
					if(!is_email(emailUpdateCode)){
						$("#emailUpdateNewCode").focus();
						$("#emailUpdateNewCodeMSG").show().text("您输入的邮箱格式错误！请输入正确的邮箱");
						return ;
					}
					$("#emailUpdateNewCodeMSG").text("").hide();
 				}
				 
				$("#sendEmailByNewEmail").removeAttr("onclick").text("处理中...");
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/sendEmailByNewEmail.action",
					data:{"email":emailUpdateCode},
					error:function(){$("#sendEmailByNewEmail").attr("onclick","SecuritycenterUpate.sendEmailByNewEmail(this);").text("下一步");},
					success:function(data){
						$("#sendEmailByNewEmail").attr("onclick","SecuritycenterUpate.sendEmailByNewEmail(this);").text("下一步");
						gj_logout(data);
						var obj = $.parseJSON(data);
						if(obj.result == "email_null"){
							alert("请输入邮箱账号");
						}else if(obj.result == "email_format"){
							alert("您输入的邮箱账号格式错误");
						}else if(obj.result == "email_existing"){
							alert("您输入的邮箱已注册，请换个账号");
						}else if(obj.result == "success"){
 							$("#emailUpdateboxSet2").hide();
							$("#emailUpdateboxSet3").show();
							$("#emailUpdateLink").text(obj.email);
						}else if(obj.result == "fail"){
							 alert("因网络响应不及时,邮件发送失败请重新操作");
						}else if(obj.result == "emailcode_error"){
							alert("因操作不及时,请返回上一步重新操作");
							window.location.href = basePath + "/user/securitycenter/list.action";
						}
					}
				});
			},
			//重发邮件
			againSendEmailByUpdateEmail:function(obj){
 				var email = document.getElementById("emailUpdateLink").innerHTML;
 				var timer = getCookieValue("againSendEmailByUpdateEmail");
 				if(timer > 0){
 					cookieTimer3(60, "againSendEmailByUpdateEmail", "againSendEmailByUpdateEmailTip", "SecuritycenterUpate.againSendEmailByUpdateEmail", "againSendEmailByUpdateEmail");
 					return ;
 				}
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/sendEmailByNewEmail.action",
					data:{"email":email},
					error:function(){},
					success:function(data){
  						gj_logout(data);
						var obj = $.parseJSON(data);
						if(obj.result == "email_null"){
							alert("请输入邮箱账号");
						}else if(obj.result == "email_format"){
							alert("您输入的邮箱账号格式错误");
						}else if(obj.result == "email_existing"){
							alert("您输入的邮箱已注册，请换个账号");
						}else if(obj.result == "success"){
  							alert("邮件重发成功");
  							addCookie("againSendEmailByUpdateEmail", 60, 60);
  							cookieTimer3(60, "againSendEmailByUpdateEmail", "againSendEmailByUpdateEmailTip", "SecuritycenterUpate.againSendEmailByUpdateEmail", "againSendEmailByUpdateEmail");
						}else if(obj.result == "fail"){
							 alert("因网络响应不及时,邮件发送失败请重新操作");
						}else if(obj.result == "emailcode_error"){
							alert("因操作不及时,请返回上一步重新操作");
							window.location.href = basePath + "/user/securitycenter/list.action";
						}
					}
				});
			},
			//发送手机短信验证码 修改密保问题
			sendPhoneCodeByquestionUpdate:function(obj){
				var timer = getCookieValue("sendPhoneCodeByquestionUpdate");
				if(timer > 0){
					cookieTimer2(60, "sendPhoneCodeByquestionUpdate", "sendPhoneCodeByquestionUpdateTip", "SecuritycenterUpate.sendPhoneCodeByquestionUpdate", "sendPhoneCodeByquestionUpdate");
					return ;
				}
				$.ajax({
					type:"post",
					url:basePath + "/user/securitycenter/sendSSmBySQInsert.action",
					success:function(data){
						gj_logout(data);
						var obj = $.parseJSON(data);
						if(obj.result == "fail"){
							alert("发送短信验证码失败!请重新操作");
						}else if(obj.result == "success"){
							alert("发送短信验证码成功！");
							addCookie("sendPhoneCodeByquestionUpdate", 60, 60);
							cookieTimer2(60, "sendPhoneCodeByquestionUpdate", "sendPhoneCodeByquestionUpdateTip", "SecuritycenterUpate.sendPhoneCodeByquestionUpdate", "sendPhoneCodeByquestionUpdate");
 						}else if(obj.result == "phone_error"){
							alert("手机未验证，请先验证手机号");
							window.location.href = basePath + "/user/securitycenter/list.action";
						} 
					}
					
				});
			},
			//验证短信验证码
			questionUpdateCheckCode:function(obj){
				var code = $("#questionUpdateCode").val();
				if(isEmpty(code)){
					$("#questionUpdateCode").focus();
					$("#questionUpdateMSG6").show().text("请输入短信验证码");
					return ;
				}else{
					$("#questionUpdateMSG6").text("").hide();
				}
				
				$("#questionUpdateCheckCode").removeAttr("onclick").text("处理中...");
				$.ajax({
					type:"post",
					data:{"code":code},
					error:function(){$("#questionUpdateCheckCode").attr("onclick","SecuritycenterUpate.questionUpdateCheckCode(this);").text("下一步");},
					url:basePath + "/user/securitycenter/securityQuestionInsertOne.action",
					success:function(data){
						gj_logout(data);
						$("#questionUpdateCheckCode").attr("onclick","SecuritycenterUpate.questionUpdateCheckCode(this);").text("下一步");
						var obj = $.parseJSON(data);
						if(obj.result == "code_null"){
							alert("请输入手机短信验证码");
						}else if(obj.result == "tocode_null"){
							alert("请点击验证码发送");
						}else if(obj.result == "code_error"){
							alert("请输入正确的手机短信验证码");
						}else if(obj.result == "success"){
							$("#safeQuestionUpdateStep2").addClass("active");
							$("#questionUpdateStep1").hide();
 							$("#questionUpdateStep2").show();
						}
					}
				});
			},
			//密保问题重置 选择密保问题和答案
			securityQuestionUpdateTwo:function(obj){
				checkQuestionUpdateTwo();
				if(!questionUpdateFlag){
					return;
				}
				var question1 = $("#questionUpdateOne").val(); 
	 			var question2 = $("#questionUpdateTwo").val(); 
				var question3 = $("#questionUpdateThree").val(); 
				var answer1   = $("#answerUpdate1").val();
				var answer2	  = $("#answerUpdate2").val();
				var answer3	  = $("#answerUpdate3").val();
				var params = {"question1":question1,"question2":question2,"question3":question3,"answer1":answer1,"answer2":answer2,"answer3":answer3};
				$("#securityQuestionUpdateTwo").removeAttr("onclick").text("处理中...");
				$.ajax({
					type:"post",
					data:params,
	  				url:basePath + "/user/securitycenter/securityQuestionInsertTwo.action",
	  				error:function(){$("#securityQuestionUpdateTwo").attr("onclick","securitycenter.securityQuestionUpdateTwo(this);").text("下一步");},
	 				success:function(data){
	 					$("#securityQuestionUpdateTwo").attr("onclick","securitycenter.securityQuestionUpdateTwo(this);").text("下一步");
	 					gj_logout(data);
	  					$("#allUpdateMsgError").text("密保问题可用于找回登录密码操作");
	   					var obj = $.parseJSON(data);
	 					if(obj.result =="success"){
	 						$("#questionUpdateStep2").hide();
	 						$("#questionUpdateStep3").show();
	 						$("#questionUpdate2-1").val(obj.question1);
	 						$("#questionUpdate2-2").val(obj.question2);
	 						$("#questionUpdate2-3").val(obj.question3);
	 						$("#answerUpdate2-1").val(obj.answer1);
	 						$("#answerUpdate2-2").val(obj.answer2);
	 						$("#answerUpdate2-3").val(obj.answer3);
	 						$("#safeQuestionUpdateStep3").addClass("active");
	  					}else if(obj.result =="question1_null"){
	 						$("#allUpdateMsgError").text("请选择问题一");
	  					}else if(obj.result == "question2_null"){
	 						$("#allUpdateMsgError").text("请选择问题二");
	  					}else if(obj.result == "question3_null"){
	 						$("#allUpdateMsgError").text("请选择问题三");
	  					} else if(obj.result == "answer1_null"){
	 						$("#allUpdateMsgError").text("请输入问题一答案");
	  					}else if(obj.result == "answer2_null"){
	 						$("#allUpdateMsgError").text("请输入问题二答案");
	  					}else if(obj.result == "answer3_null"){
	 						$("#allUpdateMsgError").text("请输入问题三答案");
	  					}else if(obj.result == "timer_out"){
	 						$("#allUpdateMsgError").text("短信验证码失效,请返回第一步重新验证!");
	  					}    
	 				}
				});
			},
			//密保问题重置 确认密保问题和答案（保存密保问题和答案）
			securityQuestionUpdateThree:function(obj){
				var question1 =	$("#questionUpdate2-1").val();
				var question2 =	$("#questionUpdate2-2").val();
				var question3 =	$("#questionUpdate2-3").val();
				var answer1   = $("#answerUpdate2-1").val();
				var answer2   =	$("#answerUpdate2-2").val();
				var answer3 =	$("#answerUpdate2-3").val();
				var params = {"question1":question1,"question2":question2,"question3":question3,"answer1":answer1,"answer2":answer2,"answer3":answer3};
				$("#securityQuestionUpdateThree").removeAttr("onclick").text("处理中...");
				$.ajax({
					type:"post",
					data:params,
	  				url:basePath + "/user/securitycenter/securityQuestionInsertThree.action",
	  				error:function(){$("#securityQuestionUpdateThree").attr("onclick","SecuritycenterUpate.securityQuestionUpdateThree(this);").text("确定");},
	 				success:function(data){
	 					$("#securityQuestionUpdateThree").attr("onclick","SecuritycenterUpate.securityQuestionUpdateThree(this);").text("确定");
	 					gj_logout(data);
	     				var obj = $.parseJSON(data);
	    				if(obj.result =="params_error"){
	    					  alert("参数错误,请重新操作！");
	    					  window.location.href = basePath + "/user/securitycenter/list.action";
	  					}else if(obj.result == "success"){
	  						$("#questionUpdateStep3").hide();
	  						$("#questionUpdateStep4").show();
	  						$("#safeQuestionUpdateStep4").addClass("active");
	  					}else if(obj.result == "timer_out"){
	  						alert("短信验证码失效,请返回第一步重新验证!");
	  					}else if(obj.result == "fail"){
	 						 alert("因网络响应不及时,保存失败,请重新操作！");
	  					}
	    				
	 				}
				});
			},
 			//修改手机号码选择方式
 			 changePhoneDetail:function(obj){
				 return {
					 //根据原手机修改
					 showUpPhoneByOld:function(obj){
 						  $("#stepWays").hide();
						  $("#changePhone").show();
						  $("#headStep").show();
						  $("#oldStep1").show();
					 },
					 //根据原邮箱修改
					 showUpPhoneByOldEmail:function(obj){
 						  $("#stepWays").hide();
						  $("#changePhone").show();
						  $("#OldEmailheadStep").show();
						  $("#OldEmailStep1").show();
					 }
					 
				 }
			 }
		};
 })();

$("#quverify").click(function(){
	$(".inputVlue").hide();
});

 //点击密码输入框触发
$("#sc_loginPwd").click(
	 	function() {
			pwdFlag = false;
			var sc_loginPwd 	= $("#sc_loginPwd").val();
			if(!isEmpty(sc_loginPwd) && sc_loginPwd.length > 0){
				$('#sc_loginPwdMSG').css("display", "");
				$("#sc_loginPwdMSG").removeClass().addClass("prompt_1 correct_1").html("");
			}else{
 				$('#sc_loginPwdMSG').css("display", "");
				$('#sc_loginPwdMSG').addClass("prompt_1 error_1").html("<i></i>密码长度8～20位字符，必须包含数字和字母，可包含符号");
			}
});

//弹框显示控制
var tipBoxConf = (function(){
	 return {
		 //显示邮箱提示框
 		 showEmailConf:function(obj){
			 $('#tipBox').append("<div class='panelbox wid_w480' id='emailCheckBox' style='display: block;'>"+
						"    <div class='panelbg'></div>"+
						"    <div class='panelwrap'>"+
						"        <div class='paneltitle'>"+
						"            <span class='text'>开户必须验证邮箱账号</span>"+
						"            <span class='panelclose nwd_icon nwd_icon_close pop-close' onclick='tipBoxConf.closeEmailConf(this)'></span>"+
						"        </div>"+
						"        <div class='panelcon pad_t30 pad_b30 pad_l30 pad_r30'>"+
						"            <div class='container'>"+
						"                 <div class='analog-success'>"+
						"	                <div class='form'>"+
						"		                <div class='item txt_center clearfix'>"+
						"		                    <a class='btn btn_36c btn_size120'  onclick='tipBoxConf.validationEmail(this)'>立即验证</a>"+
						"		                    <a class='btn btn_bgf60 btn_size120' onclick='tipBoxConf.closeEmailConf(this)'>取消</a>"+
						"  	                    </div>"+
						" 		            </div>   "+
						"                </div>"+
						"            </div>"+
						"        </div>"+
						"    </div>"+
						"</div>");
				$(".bg").show();
				center("#emailCheckBox");
		 },
		 //立即验证邮箱
		 validationEmail:function(obj){
			 tipBoxConf.closeEmailConf();
			 $("#emailBind").click();
			 var windowWidth = document.documentElement.clientWidth;   
			 var windowHeight = document.documentElement.clientHeight;   
			 var popupHeight = $(obj).height();   
			 var popupWidth = $(obj).width(); 
  			 $("body").animate({"scrollTop":(windowHeight-popupHeight)/2+$(document).scrollTop()+900});
		 },
		 //关闭邮箱提示框
		 closeEmailConf:function(obj){
			 $(".bg").hide();
			 $("#emailCheckBox").remove();
		 },
		 //显示手机提示框
		 showPhoneConf:function(){
			 $('#tipBox').append("<div class='panelbox wid_w480' id='phoneCheckBox' style='display: block;'>"+
						"    <div class='panelbg'></div>"+
						"    <div class='panelwrap'>"+
						"        <div class='paneltitle'>"+
						"            <span class='text'>开户必须完成手机验证</span>"+
						"            <span class='panelclose nwd_icon nwd_icon_close pop-close' onclick='tipBoxConf.closePhoneConf(this)'></span>"+
						"        </div>"+
						"        <div class='panelcon pad_t30 pad_b30 pad_l30 pad_r30'>"+
						"            <div class='container'>"+
						"                 <div class='analog-success'>"+
						"	                <div class='form'>"+
						"		                <div class='item txt_center clearfix'>"+
						"		                    <a class='btn btn_36c btn_size120'  onclick='tipBoxConf.validationPhone(this)'>立即验证</a>"+
						"		                    <a class='btn btn_bgf60 btn_size120' onclick='tipBoxConf.closePhoneConf(this)'>取消</a>"+
						"  	                    </div>"+
						" 		            </div>   "+
						"                </div>"+
						"            </div>"+
						"        </div>"+
						"    </div>"+
						"</div>");
				$(".bg").show();
				center("#phoneCheckBox");
		 },
		//立即验证手机
		 validationPhone:function(){
			 tipBoxConf.closePhoneConf();
			 $("#phoneBind").click();
		 },
		 //关闭手机提示框
		 closePhoneConf:function(){
			 $(".bg").hide();
			 $("#phoneCheckBox").remove();
		 }, 
		 //显示实名制提示框
		 showRealNameConf:function(){
			 $('#tipBox').append("<div class='panelbox wid_w480' id='realnameCheckBox' style='display: block;'>"+
						"    <div class='panelbg'></div>"+
						"    <div class='panelwrap'>"+
						"        <div class='paneltitle'>"+
						"            <span class='text'>开户必须完成实名认证</span>"+
						"            <span class='panelclose nwd_icon nwd_icon_close pop-close' onclick='tipBoxConf.closeRealNameConf(this)'></span>"+
						"        </div>"+
						"        <div class='panelcon pad_t30 pad_b30 pad_l30 pad_r30'>"+
						"            <div class='container'>"+
						"                 <div class='analog-success'>"+
						"	                <div class='form'>"+
						"		                <div class='item txt_center clearfix'>"+
						"		                    <a class='btn btn_36c btn_size120'  href='"+basePath+"/user/securitycenter/realNameAuthenticationlist.action'>立即认证</a>"+
						"		                    <a class='btn btn_bgf60 btn_size120' onclick='tipBoxConf.closeRealNameConf(this)'>取消</a>"+
						"  	                    </div>"+
						" 		            </div>   "+
						"                </div>"+
						"            </div>"+
						"        </div>"+
						"    </div>"+
						"</div>");
				$(".bg").show();
				center("#realnameCheckBox"); 
		 },
 		 //关闭实名提示框
		 closeRealNameConf:function(){
			 $(".bg").hide();
			 $("#realnameCheckBox").remove();
		 },
		//显示密保问题提示框
		 showQuestionConf:function(){
			 $('#tipBox').append("<div class='panelbox wid_w480' id='questionCheckBox' style='display: block;'>"+
						"    <div class='panelbg'></div>"+
						"    <div class='panelwrap'>"+
						"        <div class='paneltitle'>"+
						"            <span class='text'>开户必须设置密保问题</span>"+
						"            <span class='panelclose nwd_icon nwd_icon_close pop-close' onclick='tipBoxConf.closeQuestionConf(this)'></span>"+
						"        </div>"+
						"        <div class='panelcon pad_t30 pad_b30 pad_l30 pad_r30'>"+
						"            <div class='container'>"+
						"                 <div class='analog-success'>"+
						"	                <div class='form'>"+
						"		                <div class='item txt_center clearfix'>"+
						"		                    <a class='btn btn_36c btn_size120'  href='javascript:void(0)' onclick='tipBoxConf.validationQuestion(this)'>立即设置</a>"+
						"		                    <a class='btn btn_bgf60 btn_size120' onclick='tipBoxConf.closeQuestionConf(this)'>取消</a>"+
						"  	                    </div>"+
						" 		            </div>   "+
						"                </div>"+
						"            </div>"+
						"        </div>"+
						"    </div>"+
						"</div>");
				$(".bg").show();
				center("#questionCheckBox");  
		 },
		//立即验证密保问题
		 validationQuestion:function(){
			 tipBoxConf.closeQuestionConf();
			 $("#questionBind").click();
		 },
		 //关闭密保问题提示框
		 closeQuestionConf:function(){
			 $(".bg").hide();
			 $("#questionCheckBox").remove();
		 },
		//显示用户名提示框
		 showLoginNameConf:function(){
			 $('#tipBox').append("<div class='panelbox wid_w480' id='loginNameCheckBox' style='display: block;'>"+
						"    <div class='panelbg'></div>"+
						"    <div class='panelwrap'>"+
						"        <div class='paneltitle'>"+
						"            <span class='text'>开户必须密保问题设置</span>"+
						"            <span class='panelclose nwd_icon nwd_icon_close pop-close' onclick='tipBoxConf.closeLoginNameConf(this)'></span>"+
						"        </div>"+
						"        <div class='panelcon pad_t30 pad_b30 pad_l30 pad_r30'>"+
						"            <div class='container'>"+
						"                 <div class='analog-success'>"+
						"	                <div class='form'>"+
						"		                <div class='item txt_center clearfix'>"+
						"		                    <a class='btn btn_36c btn_size120'  href="+basePath+"'/user/securitycenter/realNameAuthenticationlist.action'>立即认证</a>"+
						"		                    <a class='btn btn_bgf60 btn_size120' onclick='tipBoxConf.closeLoginNameConf(this)'>取消</a>"+
						"  	                    </div>"+
						" 		            </div>   "+
						"                </div>"+
						"            </div>"+
						"        </div>"+
						"    </div>"+
						"</div>");
				$(".bg").show();
				center("#loginNameCheckBox"); 
		 },
		 //关闭用户名提示框
		 closeLoginNameConf:function(){
			 $(".bg").hide();
			 $("#loginNameCheckBox").remove();
		 }
	 };
})();

//密码输入框失去焦点
$("#sc_loginPwd").blur(
		function() {
			// 取得用户输入的密码的值
			var pwdVal = $("#sc_loginPwd").val();
			// var pattern = "([
			// `~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？])|(^[a-zA-Z0-9-]+$)";
			var pattern = /^(?!\D+$)(?![^a-zA-Z]+$)\S{8,20}$/;
			if (pwdVal == '') {// 密码输入为空
				pwdFlag = false;
				$('#sc_loginPwdMSG').css("display", "");
				$('#sc_loginPwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>密码不能为空");
			} else if (pwdVal.length < 8 || pwdVal.length > 20) {// 密码长度为6~20位字符
				pwdFlag = false;
				$('#sc_loginPwdMSG').css("display", "");
				$('#sc_loginPwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>密码长度为8～20位字符");
			} else if (!pwdVal.match(pattern)) {// 密码只能包含英文、数字、符号
				pwdFlag = false;
				$('#sc_loginPwdMSG').css("display", "");
				$('#sc_loginPwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>必须包含数字和字母");
			} else if (!pwdVal.match(/^\S+$/)) {// 密码不能包含空格
				pwdFlag = false;
				$('#sc_loginPwdMSG').css("display", "");
				$('#sc_loginPwdMSG').removeClass().addClass("prompt_1 error_1").html(
						"<i></i>密码不能包含空格");
			} else {
				pwdFlag = true;
				$('#sc_loginPwdMSG').css("display", "");
				$("#sc_loginPwdMSG").removeClass().addClass("prompt_1 correct_1").html(
						"<i></i>");
			}
});

$(".rightside").click(function(){
	$(".inputVlue").hide();
 })
 
//点击输入显示再次输入密码规则提示
$("#sc_loginPwd0").click(function() {
	repwdFlag = false;
	var sc_loginPwd0	= $("#sc_loginPwd0").val();
	if(!isEmpty(sc_loginPwd0) && sc_loginPwd0.length > 0){
		$('#sc_loginPwd0MSG').css("display", "");
		$("#sc_loginPwd0MSG").removeClass().addClass("prompt_1 correct_1").html("");
	}else{
		$('#sc_loginPwd0MSG').css("display", "");
		$('#sc_loginPwd0MSG').addClass("prompt_1 error_1").html("<i></i>请输入确认新登录密码！");
	}
 
});

// 再次密码输入框失去焦点
$("#sc_loginPwd0")
		.blur(
				function() {
					// 取得用户输入的密码的值
					var pwdVal = $("#sc_loginPwd").val();
					var repwdVal = $("#sc_loginPwd0").val();
					// 密码只能包含英文、数字、符号 表达式
					var pattern = "([ `~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？])|(^[a-zA-Z0-9-]+$)";
					if (repwdVal != pwdVal) {// 两次输入的密码不一致
						repwdFlag = false;
						$('#sc_loginPwd0MSG').css("display", "");
						$('#sc_loginPwd0MSG').removeClass().addClass(
								"prompt_1 error_1").html("<i></i>两次输入的密码不一致");
					} else {
						if (repwdVal == '') {// 密码输入为空
							repwdFlag = false;
							$('#sc_loginPwd0MSG').css("display", "");
							$('#sc_loginPwd0MSG').removeClass().addClass(
									"prompt_1 error_1").html("<i></i>重复密码不能为空");
						} else if (repwdVal.length < 8 || repwdVal.length > 20) {// 密码长度为6~20位字符
							repwdFlag = false;
							$('#sc_loginPwd0MSG').css("display", "");
							$('#sc_loginPwd0MSG').removeClass().addClass(
									"prompt_1 error_1").html(
									"<i></i>密码长度为8~20位字符");
						} else if (!repwdVal.match(pattern)) {// 密码只能包含英文、数字、符号
							repwdFlag = false;
							$('#sc_loginPwd0MSG').css("display", "");
							$('#sc_loginPwd0MSG').removeClass().addClass(
									"prompt_1 error_1").html(
									"<i></i>密码只能包含英文、数字、符号");
						} else if (!repwdVal.match(/^\S+$/)) {// 密码不能包含空格
							repwdFlag = false;
							$('#sc_loginPwd0MSG').css("display", "");
							$('#sc_loginPwd0MSG').removeClass().addClass(
									"prompt_1 error_1").html("<i></i>密码不能包含空格");
						} else {
							repwdFlag = true;
							$('#sc_loginPwd0MSG').css("display", "");
							$("#sc_loginPwd0MSG").removeClass().addClass(
									"prompt_1 correct_1").html("<i></i>");
						}
					}
});

//触发修改登录密码input框
function blurAll() {
	$("#sc_loginPwd").blur();
	$("#sc_loginPwd0").blur();
 }
 
//密码弱中强
function pwStrength(pwd) {
  	if (pwd == null || pwd == '') {
		$("#weak").removeClass();
		$("#centre").removeClass();
		$("#strong").removeClass();
		$("#loginmima1").css("display","none");
	} else {
 		S_level = checkStrong(pwd);
 		switch (S_level) {
		case 0:
			$("#weak").removeClass();
			$("#centre").removeClass();
			$("#strong").removeClass();
			$("#loginmima1").css("display","none");
			break;
		case 1:
			$("#loginmima1").css("display","");
			$("#weak").removeClass().addClass("color");// 弱
			$("#centre").removeClass();
			$("#strong").removeClass();
			break;
		case 2:
 			$("#loginmima1").css("display","");
			$("#weak").removeClass().addClass("color");
			$("#centre").removeClass().addClass("color");// 中
			$("#strong").removeClass();
			break;
		default:
			$("#loginmima1").css("display","");
			$("#weak").removeClass().addClass("color");
			$("#centre").removeClass().addClass("color");// 中
			$("#strong").removeClass().addClass("color");// 强
			break;
		}
	}
};

//checkStrong函数
//返回密码的强度级别
function checkStrong(sPW) {
	if (sPW.length <= 5)
		return 0; // 密码太短
	Modes = 0;
	for (var i = 0; i < sPW.length; i++) {
		// 测试每一个字符的类别并统计一共有多少种模式.
		Modes |= CharMode(sPW.charCodeAt(i));
	}
	return bitTotal(Modes);
};

function CharMode(iN) {
	if (iN >= 48 && iN <= 57) // 数字
		return 1;
	if (iN >= 65 && iN <= 90) // 大写字母
		return 2;
	if (iN >= 97 && iN <= 122) // 小写
		return 4;
	else
		return 8; // 特殊字符
};

function bitTotal(num) {
	modes = 0;
	for (var i = 0; i < 4; i++) {
		if (num & 1)
			modes++;
		num >>>= 1;
	}
	return modes;
};

//显示手机验证框
function validationPhone(obj){
 	var $tr = $(obj).parents("tr");
 	var $next = $tr.next();
 	$next.find(".rightside").children("input").val("");
 	$next.find(".error_1").hide();
 	$tr.siblings(".only").addClass("none");
 	$tr.toggleClass("none").siblings(".phoneinsert_box").removeClass("none");
 
};

//密保问题校验(重置)
function checkQuestionUpdateTwo(){
 	var question1 = $("#questionUpdateOne").val(); 
	var question2 = $("#questionUpdateTwo").val(); 
	var question3 = $("#questionUpdateThree").val(); 
	var answer1   = $("#answerUpdate1").val();
	var answer2	  = $("#answerUpdate2").val();
	var answer3	  = $("#answerUpdate3").val();
   	
 	if(!(question1 > 0)){
		$("#allUpdateMsgError").text("请选择问题一");
		questionUpdateFlag = false;
		return ;
	}
 	
 	if(isEmpty(answer1)){
		$("#allUpdateMsgError").text("请输入答案一");
		questionUpdateFlag = false;
		return ;
	}
	
	if(!(question2 > 0)){
		$("#allUpdateMsgError").text("请选择问题二");
		questionUpdateFlag = false;
		return ;
	}
	
	if(isEmpty(answer2)){
		$("#allUpdateMsgError").text("请输入答案二");
		questionUpdateFlag = false;
		return ;
	}
	
	if(!(question3 > 0)){
		$("#allUpdateMsgError").text("请选择问题三");
		questionUpdateFlag = false;
		return ;
	}
  	
	if(isEmpty(answer3)){
		$("#allUpdateMsgError").text("请输入答案三");
		questionUpdateFlag = false;
		return ;
	}
	
	
	if(question1 == question2 || question2 == question3 || question1 == question3){
		$("#allUpdateMsgError").text("选择的密保问题不能一样");	
		questionUpdateFlag = false;
		return ;
	}
	
	questionUpdateFlag = true;
	$("#allUpdateMsgError").text("密保问题可用于找回登录密码操作");
};

//密保问题校验
function checkQuestionInsertTwo(){
	var question1 = $("#IquestionOne").val(); 
	var question2 = $("#IquestionTwo").val(); 
	var question3 = $("#IquestionThree").val(); 
	var answer1   = $("#Ianswer1").val();
	var answer2	  = $("#Ianswer2").val();
	var answer3	  = $("#Ianswer3").val();
   	
 	if(!(question1 > 0)){
		$("#allQuestionMsgError").text("请选择问题一");
		questionFlag = false;
		return ;
	}
 	
 	if(isEmpty(answer1)){
		$("#allQuestionMsgError").text("请输入答案一");
		questionFlag = false;
		return ;
	}
	
	if(!(question2 > 0)){
		$("#allQuestionMsgError").text("请选择问题二");
		questionFlag = false;
		return ;
	}
	
	if(isEmpty(answer2)){
		$("#allQuestionMsgError").text("请输入答案二");
		questionFlag = false;
		return ;
	}
	
	if(!(question3 > 0)){
		$("#allQuestionMsgError").text("请选择问题三");
		questionFlag = false;
		return ;
	}
  	
	if(isEmpty(answer3)){
		$("#allQuestionMsgError").text("请输入答案三");
		questionFlag = false;
		return ;
	}
	
	
	if(question1 == question2 || question2 == question3 || question1 == question3){
		$("#allQuestionMsgError").text("选择的密保问题不能一样");	
		questionFlag = false;
		return ;
	}
	
	questionFlag = true;
	$("#allQuestionMsgError").text("密保问题可用于找回登录密码操作");
};
//几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId提示多少秒后可以发送ID clickName事件名称 cookieName cookie名称
function cookieTimer3(timer,btnId,tipsId,clickName,cookieName){
	 timer = getCookieValue(cookieName) ? getCookieValue(cookieName):timer;
		//隐藏短信发送框 
		$("#"+btnId+"").hide();
		//显示正在发送框
		$("#"+tipsId+"").show();
		$("#"+tipsId+"").text((timer<=0)?"重新发送邮件":(""+(timer)+"s"));
		var senderTime = setInterval(function(){
		 if(timer <= 0 ){
			 clearInterval(senderTime);
 			 $("#"+btnId+"").show();
			 $("#"+tipsId+"").hide();
			 $("#"+btnId+"").text("重新发送邮件");
			 $("#"+btnId+"Massage").text("");
			 //恢复发送短信点击事件
			 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			 return false;
		 }else{
			 $("#"+tipsId+"").text(""+(timer--)+"s");
			 editCookie(cookieName,timer,timer+1);
		 }
	},1000);
};

//几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId提示多少秒后可以发送ID clickName事件名称 cookieName cookie名称
function cookieTimer2(timer,btnId,tipsId,clickName,cookieName){
	 timer = getCookieValue(cookieName) ? getCookieValue(cookieName):timer;
		//隐藏短信发送框 
		$("#"+btnId+"").hide();
		//显示正在发送框
		$("#"+tipsId+"").show();
		$("#"+tipsId+"").text((timer<=0)?"立即获取":(""+(timer)+"s"));
		var senderTime = setInterval(function(){
		 if(timer <= 0 ){
			 clearInterval(senderTime);
 			 $("#"+btnId+"").show();
			 $("#"+tipsId+"").hide();
			 $("#"+btnId+"").text("立即获取");
			 $("#"+btnId+"Massage").text("");
			 //恢复发送短信点击事件
			 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			 return false;
		 }else{
			 $("#"+tipsId+"").text(""+(timer--)+"s");
			 editCookie(cookieName,timer,timer+1);
		 }
	},1000);
};

//几秒后可以发送短信 timer 时间 ,btnId发送短信ID,tipsId提示多少秒后可以发送ID clickName事件名称 cookieName cookie名称
function cookieTimer(timer,btnId,tipsId,clickName,cookieName){
	 timer = getCookieValue(cookieName) ? getCookieValue(cookieName):timer;
		//隐藏短信发送框 
		$("#"+btnId+"").hide();
		//显示正在发送框
		$("#"+tipsId+"").show();
		$("#"+tipsId+"").text((timer<=0)?"发送短信验证码":(""+(timer)+"秒后可以再发送短信验证码"));
		var senderTime = setInterval(function(){
		 if(timer <= 0 ){
			 clearInterval(senderTime);
 			 $("#"+btnId+"").show();
			 $("#"+tipsId+"").hide();
			 $("#"+btnId+"").text("发送短信验证码");
			 $("#"+btnId+"Massage").text("");
			 //恢复发送短信点击事件
			 $("#"+btnId+"").attr("onclick",""+clickName+"(this)");
			 return false;
		 }else{
			 $("#"+tipsId+"").text(""+(timer--)+"秒后可以再发送短信验证码");
			 editCookie(cookieName,timer,timer+1);
		 }
	},1000);
};

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

function editCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    if(expiresHours>0){ 
      var date=new Date(); 
      date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
      cookieString=cookieString+";expires=" + date.toGMTString(); 
    } 
      document.cookie=cookieString; 
}; 

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
 
 $(function(){
	 	$(window).scroll(function(){
	 		center("#emailCheckBox");
	 		center("#phoneCheckBox");
	 		center("#realnameCheckBox");
	 		center("#questionCheckBox");
	 		center("#loginNameCheckBox");
	 		
		});
		
		$(window).resize(function(){
			center("#emailCheckBox");
	 		center("#phoneCheckBox");
	 		center("#realnameCheckBox");
	 		center("#questionCheckBox");
	 		center("#loginNameCheckBox");
		});
});
 /*邮件格式*/ 
 function is_email(str){ 
     var regExp = /^([\w\.])+@\w+\.([\w\.])+$/;
     return regExp.test(str);
 };
 
 /*手机格式*/
 function is_phone(str){
	 var regExp = /^(13|15|18)\d{9}$/;
	 return regExp.test(str);
 };

//居中
 function center(obj){
	 var windowWidth = document.documentElement.clientWidth;   
	 var windowHeight = document.documentElement.clientHeight;   
	 var popupHeight = $(obj).height();   
	 var popupWidth = $(obj).width();    
	 $(obj).css({   
	  "position": "absolute",   
	  "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),   
	  "left": (windowWidth-popupWidth)/2   
	 });  
};

//重新登录
function gj_logout(data){
	if(data == "logout"){
		alert("操作超时！请重新登录操作！");
		window.location.href = basePath +"/user/tologin.action";
	}
};
 
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