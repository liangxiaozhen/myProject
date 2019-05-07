<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 重新设置手机号码 开始 -->
<div class="step step-2">
	<span id="safequestion_title_2_1">${text1}</span> <span
		id="safequestion_title_2_2">设置新手机号</span> <span
		id="safequestion_title_2_3">修改成功</span>
</div>
<div class="verification-from">
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 新手机号码:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="update_UserPhone_Reset_submit_tellPhone"
			onkeyup="value=value.replace(/[^\d]/g,'') "
			onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
		</span> <span class="verification-3"> <a href="javascript:void(0)"
			id="update_UserPhone_Reset_submit_Send" class="button-orange-1"
			style="display: block;"
			onclick="update_UserPhone_Reset_submit_Send(this)"> 发送短信验证码 </a> <a
			id="update_UserPhone_Reset_submit_tips" href="javascript:void(0)"
			class="button-ash-3" style="display: none;">正在发送</a>
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_Reset_submit_Send_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"> <b class="red-star">*</b> 验证码:
		</span> <span class="verification-2"> <input type="text"
			class="input-text-2" id="update_UserPhone_Reset_submit_code"
			onkeyup="value=value.replace(/[^\d]/g,'') "
			onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_Reset_submit_code_error"></span>
	</div>
	<div class="item clearfloat">
		<span class="verification-1"></span> <span class="verification-2">
			<a href="javascript:void(0)" class="submit"
			id="update_UserPhone_Reset_submit">确定</a>
		</span> <span class="verification-3" style="color: red;"
			id="update_UserPhone_Reset_submit_error"></span>
	</div>
</div>
<!-- 重新设置手机号码 结束 -->
<script type="text/javascript">
 	$(function(){
 		$("#update_UserPhone_Reset_submit").click(function(){
 			var tellPhone = $("#update_UserPhone_Reset_submit_tellPhone").val();
 			var code = $("#update_UserPhone_Reset_submit_code").val();
 			if(isEmpty(tellPhone)){
  				$("#update_UserPhone_Reset_submit_tellPhone").focus();
  				$("#update_UserPhone_Reset_submit_Send_error").text("请输入手机号...");
  				return false;
  			}else{
  				$("#update_UserPhone_Reset_submit_Send_error").text("");
  				var reg =/^1\d{10}$/;
  				if(!reg.test(tellPhone)){
  					$("#update_UserPhone_Reset_submit_Send_error").text("请输入11位的手机号码...");
  					return false;
  				}else{
  	  				$("#update_UserPhone_Reset_submit_Send_error").text("");
  				}
   			}
  			
  			if(isEmpty(code)){
  				$("#update_UserPhone_Reset_submit_code").focus();
  				$("#update_UserPhone_Reset_submit_code_error").text("请输入手机验证码...");
  				return false;
  			}else{
  				$("#update_UserPhone_Reset_submit_code_error").text("");
   			}
  			var Update_UserPhone_ResetPhone_Params = {"tellPhone":tellPhone,"code":code};
 			Security.Update_UserPhone_ResetPhone(Update_UserPhone_ResetPhone_Params);
 		});
 	});
 	
 	function update_UserPhone_Reset_submit_Send(){
 		var tellPhone = $("#update_UserPhone_Reset_submit_tellPhone").val();
 		if(isEmpty(tellPhone)){
				$("#update_UserPhone_Reset_submit_tellPhone").focus();
				$("#update_UserPhone_Reset_submit_Send_error").text("请输入手机号...");
				return false;
			}else{
				$("#update_UserPhone_Reset_submit_Send_error").text("");
				var reg =/^1\d{10}$/;
				if(!reg.test(tellPhone)){
					$("#update_UserPhone_Reset_submit_Send_error").text("请输入11位的手机号码...");
					return false;
				}else{
	  				$("#update_UserPhone_Reset_submit_Send_error").text("");
				}
			}
  		var cod = getCookieValue("tellPhone") ? getCookieValue("tellPhone"):0;//获取cookie值
 		 if(cod > 0){
	    	 $("#update_UserPhone_Reset_submit_Send").removeAttr("onclick");
	    	 $("#update_UserPhone_Reset_submit_Send_error").text("短信发送间隔需60秒");
	        //开始倒计时
	        tellTimer(cod,"update_UserPhone_Reset_submit_Send","update_UserPhone_Reset_submit_tips","update_UserPhone_Reset_submit_Send");
	     }else{
 	    //	 $("#update_UserPhone_Reset_submit_Send").removeAttr("onclick");
	    	 $("#update_UserPhone_Reset_submit_Send_error").text("");
	    	 var Update_UserPhone_ResetPhone_SendSsm_Params = {"tellPhone":tellPhone};
	    	 Security.Update_UserPhone_ResetPhone_SendSsm(Update_UserPhone_ResetPhone_SendSsm_Params);
	     }
 	};
 	
 </script>
