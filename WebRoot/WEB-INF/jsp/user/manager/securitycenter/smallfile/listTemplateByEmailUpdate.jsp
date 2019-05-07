<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#emailUpdateboxSet3 a{color:#2577e3;}
</style>
<tr class="validation-status clearfix">
	<td class="left wid_w120 bor_b">电子邮箱</td>
	<td class="wid_w480 fc_9 bor_b">邮件接收账户通知，及时了解账户信息变动情况</td>
	<td class="wid_w100 fc_9 bor_b"><i
		class="icon_base icon_16 icon_base_tipright16 vertical_middle mar_r10"></i><span
		class="vertical_middle">已设置</span></td>
	<td class="bor_b wid_w200">
 		<div class="pad_l30">
			 ${userBaseAccountInfo.emailstr }&nbsp;&nbsp;<a title="修改" class="showNext emailUpdate" href="javascript:;">修改</a>
		</div>
	</td>
</tr> 
<tr class="none only emailUpdate_box">
	<td colspan="4" class="f3f">
		<div class="fc_3 mar_auto email-protect">
			<div class="flowstep">
				<div class="steps steps_3 pad_t20 clearfix pad_b20">
					<ul class="active  wid_w160 fl step-first txt_center" id="emailUpdateStep_1">
						<li class="sz step-no fc_f"><span>1</span></li>
						<li class="sm txt_center pad_t10">1.验证原邮箱</li>
					</ul>
					<ul class="wid_w160 fl txt_center" id="emailUpdateStep_2">
						<li class="sz step-no fc_9"><span>2</span></li>
						<li class="sm txt_center pad_t10">2.输入新邮箱</li>
					</ul>
					<ul class="wid_w160 fl step-last txt_center" id="emailUpdateStep_3">
						<li class="sz step-no fc_9"><span>3</span></li>
						<li class="sm txt_center pad_t10">3.完成</li>
					</ul>
				</div>
			</div>
			<!--绑定邮箱 step1 Start-->
			<div class="bank_step step1" id="emailUpdateboxSet1"
				style="padding-left: 36px;">
				<table class="table_child fc_3 fs_14 form" cellpadding="0"
					cellspacing="0" width="100%" id="emailUpdateboxSutep1">
					<tbody>
						<tr>
							<th class="leftside pad_t10 pad_b20" width="120" valign="top">输入原邮箱地址</th>
							<th class="rightside pad_b20" valign="top"><input
								type="text" name="emailUpdateCode" id="emailUpdateCode"
								class="input_all ui-input w320-input"> <br>
							<span id="emailUpdateCodeMSG" class="prompt_1 error_1"
								style="display: none;"><em></em></span></th>
						</tr>
						<tr>
							<th></th>
							<th class="rightside">
								<button type="button" class="btn btn_36c btn_size120 next_email" onclick="SecuritycenterUpate.UpdateEmailCheck(this);" id="UpdateEmailCheck">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--step1 End-->
			
			<!--绑定邮箱 step2 Start-->
			<div class="bank_step step1 none" id="emailUpdateboxSet2"
				style="padding-left: 36px;">
				<table class="table_child fc_3 fs_14 form" cellpadding="0"
					cellspacing="0" width="100%" id="emailUpdatenewboxSutep1">
					<tbody>
						<tr>
							<th class="leftside pad_t10 pad_b20" width="120" valign="top">输入新邮箱地址</th>
							<th class="rightside pad_b20" valign="top"><input
								type="text" name="emailUpdateNewCode" id="emailUpdateNewCode"
								class="input_all ui-input w320-input"> <br>
							<span id="emailUpdateNewCodeMSG" class="prompt_1 error_1"
								style="display: none;"><em></em></span></th>
						</tr>
						<tr>
							<th></th>
							<th class="rightside">
								<button type="button" class="btn btn_36c btn_size120 next_email" onclick="SecuritycenterUpate.sendEmailByNewEmail(this);" id="sendEmailByNewEmail">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>

			<!--step3 Start-->
			<div class="bank_step step2 fs_14 none" id="emailUpdateboxSet3">
				<input type="hidden" name="email" id="email" value="">
				<script type="text/javascript">
                      function openMailUrl(obj){
                          var mailStr =document.getElementById("emailUpdateLink").innerHTML;
                          var mailSplitStr = mailStr.split("@");
                          window.open('http://mail.'+mailSplitStr[1]);
                      }
                 </script>
				<p>
					邮件已发送至您的邮箱： <a id="emailUpdateLink" href="javascript:;"
						onclick="openMailUrl();" class="blue" title="去验证"></a>
					请在24小时内登录邮箱并点击邮件中的链接，完成重置。
				</p>
				<br>
				<p>没收到邮件?</p>
				<ul class="email">
					<li>• 请查看邮箱地址有没有写错</li>
					<li>• 检查邮件是否在垃圾箱里</li>
					<li>• 让系统给您 
						<a href="javascript:;" onclick="SecuritycenterUpate.againSendEmailByUpdateEmail(this);"class="blue" title="重新发送邮件" id="againSendEmailByUpdateEmail">重新发送邮件</a>
						<a href="javascript:;" class="blue" style="display:none;" id="againSendEmailByUpdateEmailTip"></a>
					</li>
				</ul>
			</div>
			<!--step3 End-->
			
			<!--step4 Start-->
			<div class="bank_step step4 w280 none" id="emailUpdateboxSet4">
				<div class="sucess mb_10 clearfix">
					<div class="left fl"></div>
					<div class="right fl">
						<span class="fs_18">邮箱重置成功！</span> <span class="ok_close"><a
							class="btn btn_36c btn_size120" href="${basePath}/user/securitycenter/list.action" title="确定">确定</a></span>
					</div>
				</div>
			</div>
			<!--绑定邮箱 step4 End-->
 		</div>
	</td>
</tr>
<!-- 电子邮箱end -->