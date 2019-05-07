<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#mailboxSet2 a{color:#2577e3;}
</style>
 <!-- 电子邮箱 start -->
<tr class="validation-status clearfix">
	<td class="left wid_w120 bor_b">电子邮箱</td>
	<td class="wid_w480 fc_9 bor_b">邮件接收账户通知，及时了解账户信息变动情况</td>
	<td class="wid_w100 fc_9 bor_b"><i
		class="icon_base icon_16 icon_base_tipnote16 vertical_middle mar_r10"></i><span
		class="vertical_middle">未设置</span></td>
	<td class="bor_b wid_w200"><div class="pad_l30">
			<a title="绑定" class="showNext bangding" href="javascript:;" id="emailBind">绑定</a>
		</div></td>
</tr>
<tr class="none only bangding_box">
	<td colspan="4" class="f3f">
		<div class="fc_3 mar_auto email-protect">
			<div class="flowstep">
				<div class="steps steps_3 pad_t20 clearfix pad_b20">
					<ul class="active  wid_w160 fl step-first txt_center" id="step_1">
						<li class="sz step-no fc_f"><span>1</span></li>
						<li class="sm txt_center pad_t10">1.验证身份</li>
					</ul>
					<ul class="wid_w160 fl txt_center" id="step_2">
						<li class="sz step-no fc_9"><span>2</span></li>
						<li class="sm txt_center pad_t10">2.点击链接</li>
					</ul>
					<ul class="wid_w160 fl step-last txt_center" id="step_3">
						<li class="sz step-no fc_9"><span>3</span></li>
						<li class="sm txt_center pad_t10">3.完成</li>
					</ul>
				</div>
			</div>
			<!--绑定邮箱 step1 Start-->
			<div class="bank_step step1" id="mailboxSet1"
				style="padding-left: 36px;">
				<table class="table_child fc_3 fs_14 form" cellpadding="0"
					cellspacing="0" width="100%" id="mailboxSutep1">
					<tbody>
						<tr>
							<th class="leftside pad_t10 pad_b20" width="90" valign="top">输入邮箱地址</th>
							<th class="rightside pad_b20" valign="top"><input
								type="text" name="emailInserCode" id="emailInserCode"
								class="input_all ui-input w320-input"> <br>
							<span id="emailInserCodeMSG" class="prompt_1 error_1"
								style="display: none;"><em></em></span></th>
						</tr>
						<tr>
							<th></th>
							<th class="rightside">
								<button type="button" class="btn btn_36c btn_size120 next_email" onclick="securitycenter.sendEmailByBindEmail(this);" id="sendEmailByBindEmail">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--step1 End-->

			<!--step2 Start-->
			<div class="bank_step step2 fs_14 none" id="mailboxSet2">
				<input type="hidden" name="email" id="email" value="">
				<script type="text/javascript">
                      function openMailUrl(obj){
                          var mailStr =document.getElementById("EmailInsertLink").innerHTML;
                          var mailSplitStr = mailStr.split("@");
                          window.open('http://mail.'+mailSplitStr[1]);
                      }
                 </script>
				<p>
					邮件已发送至您的邮箱： <a id="EmailInsertLink" href="javascript:;"
						onclick="openMailUrl();" class="blue" title="去验证"></a>
					请在24小时内登录邮箱并点击邮件中的链接，完成验证。
				</p>
				<br>
				<p>没收到邮件?</p>
				<ul class="email">
					<li>• 请查看邮箱地址有没有写错</li>
					<li>• 检查邮件是否在垃圾箱里</li>
					<li>• 让系统给您 
						<a href="javascript:;" id="againSendEmailByBindEmail" onclick="securitycenter.againSendEmailByBindEmail(this);" class="blue" title="重新发送邮件">重新发送邮件</a>
						<a href="javascript:;" class="blue" style="display:none;" id="againSendEmailByBindEmailTip"></a>
 					</li>
				</ul>
			</div>
			<!--step2 End-->
			<!--step3 Start-->
			<div class="bank_step step4 w280 none" id="mailboxSet3">
				<div class="sucess mb_10 clearfix">
					<div class="left fl"></div>
					<div class="right fl">
						<span class="fs_18">邮箱绑定成功！</span> <span class="ok_close"><a
							class="btn btn_36c btn_size120" href="${basePath}/user/securitycenter/list.action" title="确定">确定</a></span>
					</div>
				</div>
			</div>
			<!--绑定邮箱 step3 End-->

		</div>
	</td>
</tr>
<!-- 电子邮箱end -->