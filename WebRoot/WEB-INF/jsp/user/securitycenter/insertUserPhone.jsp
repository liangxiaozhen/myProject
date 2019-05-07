<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="verification-box">
	<div class="verification-from" id="gj_safequestion_answer_edit">
		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b> 新手机号码:
			</span> <span class="verification-2"> <input type="text"
				class="input-text-2"
				id="insert_user_phone_passedPhone_submit_userPhone"
				onkeyup="value=value.replace(/[^\d]/g,'') "
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
			</span> <span class="verification-3"> <a href="javascript:void(0)"
				id="insert_user_phone_passedPhone_submit_Send"
				class="button-orange-1" style="display: block;"
				onclick="insert_user_phone_passedPhone_submit_Send(this)">
					发送短信验证码 </a> <a id="insert_user_phone_passedPhone_submit_tips"
				href="javascript:void(0)" class="button-ash-3"
				style="display: none;">正在发送</a>
			</span> <span class="verification-3" style="color: red;"
				id="insert_user_phone_passedPhone_submit_Send_error"></span>
		</div>
		<div class="item clearfloat">
			<span class="verification-1"> <b class="red-star">*</b> 验证码:
			</span> <span class="verification-2"> <input type="text"
				class="input-text-2" id="insert_user_phone_passedPhone_submit_code"
				onkeyup="value=value.replace(/[^\d]/g,'') "
				onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
			</span> <span class="verification-3" style="color: red;"
				id="insert_user_phone_passedPhone_submit_code_error"></span>
		</div>
		<div class="item clearfloat">
			<span class="verification-1"></span> <span class="verification-2">
				<a href="javascript:void(0)" class="submit"
				id="insert_user_phone_passedPhone_submit">确定</a>
			</span> <span class="verification-3" style="color: red;"
				id="insert_user_phone_passedPhone_submit_error"></span>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		//保存
		$("#insert_user_phone_passedPhone_submit")
				.click(
						function() {
							var tellPhone = $(
									"#insert_user_phone_passedPhone_submit_userPhone")
									.val();
							var code = $(
									"#insert_user_phone_passedPhone_submit_code")
									.val();
							if (isEmpty(tellPhone)) {
								$(
										"#insert_user_phone_passedPhone_submit_userPhone")
										.focus();
								$(
										"#insert_user_phone_passedPhone_submit_Send_error")
										.text("请输入手机号...");
								return false;
							} else {
								$(
										"#insert_user_phone_passedPhone_submit_Send_error")
										.text("");
								var reg = /^1\d{10}$/;
								if (!reg.test(tellPhone)) {
									$(
											"#insert_user_phone_passedPhone_submit_Send_error")
											.text("手机号码格式错误！请输入正确的11位手机号码...");
									return false;
								} else {
									$(
											"#insert_user_phone_passedPhone_submit_Send_error")
											.text("");
								}
							}

							if (isEmpty(code)) {
								$("#insert_user_phone_passedPhone_submit_code")
										.focus();
								$(
										"#insert_user_phone_passedPhone_submit_code_error")
										.text("请输入手机验证码...");
								return false;
							} else {
								$(
										"#insert_user_phone_passedPhone_submit_code_error")
										.text("");
							}
							var Insert_Phone_Params = {
								"tellPhone" : tellPhone,
								"code" : code
							};
							Security.Insert_Phone(Insert_Phone_Params);
						});
	});

	//发送短信 
	function insert_user_phone_passedPhone_submit_Send(obj) {
		var tellPhone = $("#insert_user_phone_passedPhone_submit_userPhone")
				.val();
		if (isEmpty(tellPhone)) {
			$("#insert_user_phone_passedPhone_submit_userPhone").focus();
			$("#insert_user_phone_passedPhone_submit_Send_error").text(
					"请输入手机号...");
			return false;
		} else {
			$("#insert_user_phone_passedPhone_submit_Send_error").text("");
			var reg = /^1\d{10}$/;
			if (!reg.test(tellPhone)) {
				$("#insert_user_phone_passedPhone_submit_Send_error").text(
						"手机号码格式错误！请输入正确的11位手机号码...");
				return false;
			} else {
				$("#insert_user_phone_passedPhone_submit_Send_error").text("");
			}
		}
		var cod = getCookieValue("tellPhone") ? getCookieValue("tellPhone") : 0;//获取cookie值
		if (cod > 0) {
			$("#insert_user_phone_passedPhone_submit_Send").removeAttr(
					"onclick");
			$("#insert_user_phone_passedPhone_submit_Send_error").text(
					"短信发送间隔需60秒");
			//开始倒计时
			timer(cod, "insert_user_phone_passedPhone_submit_Send",
					"insert_user_phone_passedPhone_submit_tips",
					"insert_user_phone_passedPhone_submit_Send");
		} else {
			//  		    	$("#insert_user_phone_passedPhone_submit_Send").removeAttr("onclick");
			$("#insert_user_phone_passedPhone_submit_Send_error").text("");
			var Update_UserPhone_ResetPhone_SendSsm_Params = {
				"tellPhone" : tellPhone
			};
			Security
					.Insert_UserPhone_ResetPhone_SendSsm(Update_UserPhone_ResetPhone_SendSsm_Params);
		}
	};
</script>