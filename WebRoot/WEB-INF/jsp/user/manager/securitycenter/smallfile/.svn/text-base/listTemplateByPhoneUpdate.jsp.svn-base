<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	.nwd_icon_email{
		    background-position: -4px -1525px;
		    width: 30px;
		    height: 30px;
		    margin: 18px auto;
	}
	
	.nwd_icon_security{
		    background-position: -4px -1686px;
		    width: 30px;
		    height: 30px;
		    margin: 18px auto;
	}
	
	.steps .active .sz{
		background-position: 54% -113px;
	}

</style>
<!-- 手机号码修改 start -->
<tr class="validation-status clearfix">
	<td class="left wid_w120 bor_b">手机号码</td>
	<td class="wid_w480 fc_9 bor_b">保障账户与资金安全，是您在你我贷重要的身份凭证</td>
	<!--pos1 -->
	<td class="wid_w100 fc_9 bor_b"><i
		class="icon_base icon_16 icon_base_tipright16 vertical_middle mar_r10"></i><span
		class="vertical_middle">已设置</span></td>
	<td class="bor_b wid_w200"><div class="pad_l30">
			<span class="mar_r10 fc_6">${userBaseAccountInfo.mobilephonestr}</span><a title="修改"
				class="showNext phonexiugai" href="javascript:;">修改</a>
		</div></td>
</tr>
<tr class="only phonexiugai_box none">
	<td colspan="4">
		<!--修改手机 StepWaysStart-->
		<div class="change_phone" id="stepWays">
			<ul class="clearfix wid_w600 pad_t20 mar_auto">
				<li class="fl wid_w120 txt_center mar_r20">
					<div class="change_phone_top">通过原手机修改</div>
					<div class="nwd_icon nwd_icon_phone"></div>
					<div>
						<a class="btn btn_size100 btn_blue  now_xiugai1"
							href="javascript:;" title="立即修改" onclick="SecuritycenterUpate.changePhoneDetail(this).showUpPhoneByOld(this)">立即修改</a>
					</div>
				</li>
				
				<li class="fl wid_w120 txt_center mar_r20">
					<div class="change_phone_top">通过邮箱修改</div>
					<div class="nwd_icon nwd_icon_email"></div> <span><a
						class="btn btn_size100 btn_blue now_xiugai2" href="javascript:;"onclick="SecuritycenterUpate.changePhoneDetail(this).showUpPhoneByOldEmail(this)"
						title="立即修改">立即修改</a></span>
				</li>
				
				<li class="fl wid_w120 txt_center mar_r20">
					<div class="change_phone_top">通过密保修改</div>
					<div class="nwd_icon nwd_icon_security"></div> <span><a
						class="btn btn_size100 btn_blue now_xiugai2" href="javascript:;"
						title="立即修改">立即修改</a></span>
				</li>
				
				<li class="fl wid_w120 txt_center mar_r20">
					<div class="change_phone_top">通过人工修改</div>
					<div class="nwd_icon nwd_icon_manual"></div> <span><a
						class="btn btn_size100 btn_blue now_xiugai2" href="javascript:;"
						title="立即修改">立即修改</a></span>
				</li>
			</ul>
		</div> 
		
		<!--修改手机 StepWays-END-->
		<div id="changePhone" class="fc_3 flowstep clearfix phone-protect mar_auto none">
		
			<div id="headStep" class="steps steps_3 pad_t20 clearfix pad_b20 none">
				<ul class="active  wid_w160 fl step-first txt_center"
					id="changeOldStep1">
					<li class="sz step-no fc_f"><span>1</span></li>
					<li class="sm txt_center pad_t10">1.核实原手机号码</li>
				</ul>
				<ul class="wid_w160 fl txt_center" id="changeOldStep2">
					<li class="sz step-no fc_9"><span>2</span></li>
					<li class="sm txt_center pad_t10">2.输入新手机号码</li>
				</ul>
				<ul class="wid_w160 fl step-last txt_center" id="changeOldStep3">
					<li class="sz step-no fc_9"><span>3</span></li>
					<li class="sm txt_center pad_t10">3.完成</li>
				</ul>
			</div>
			<!--验证原手机号码 step1 Start-->
			<div id="oldStep1" class="bank_step step1 none">
				<table class="table_child fc_3 fs_14 form" width="100%"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th class="leftside pad_t10 pad_b20" valign="top">原手机号</th>
							<th class="rightside fs_14 pad_b20" valign="top"><input
								type="text" name="phoneUpdate" id="phoneUpdate"
								class="input_all ui-input w320-input" maxlength="11"> <br>
							<span class="prompt_1 error_1" id="phoneMSG"
								style="display: none"></span></th>
						</tr>
						<tr>
							<th class="leftside pad_t10 pad_b20" valign="top">验证码</th>
							<th class="rightside pad_b20" valign="top"><input
								type="text" id="verifyTest"
								class="input_a100 ui-input w180-input itxt" maxlength="6">
								<button class="button button-w120 mar_l10" id="click3"
									onclick="SecuritycenterUpate.sendSSMByOldPhone(this);">点击获取</button>
								<button class="button button-w120 mar_l10" style="display: none"
									id="countDown3"></button> <br>
							<span class="prompt_1 error_1" id="verifyMSG3"
								style="display: none"></span></th>
						</tr>
 						<tr>
							<th></th>
							<th class="rightside">
								<button class="btn btn_36c btn_size120 next_pre newPhoneNext" onclick="SecuritycenterUpate.updatePhoneByOldPhoneOne(this)" id="updatePhoneByOldPhoneOne">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--验证原手机号码 step1 END-->
 			
  			<div id="OldEmailheadStep" class="steps steps_3 pad_t20 clearfix pad_b20 none">
				<ul class="active  wid_w160 fl step-first txt_center"
					id="changePhoneByOldEmailStep1">
					<li class="sz step-no fc_f"><span>1</span></li>
					<li class="sm txt_center pad_t10">1.邮箱验证</li>
				</ul>
				<ul class="wid_w160 fl txt_center" id="changeOldStep2ByOldEmail">
					<li class="sz step-no fc_9"><span>2</span></li>
					<li class="sm txt_center pad_t10">2.输入新手机号码</li>
				</ul>
				<ul class="wid_w160 fl step-last txt_center" id="changeOldStep3ByOldEmail">
					<li class="sz step-no fc_9"><span>3</span></li>
					<li class="sm txt_center pad_t10">3.完成</li>
				</ul>
			</div>
			
			<!--验证原邮箱 step1 Start-->
			<div id="OldEmailStep1" class="bank_step step1 none">
				<table class="table_child fc_3 fs_14 form" width="100%"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th class="leftside pad_t10 pad_b20" valign="top">原邮箱账号</th>
							<th class="rightside  pad_b20">
							<span style="position: relative;top:3px;">${userBaseAccountInfo.emailstr}</span>
 							<span class="prompt_1 error_1" id="phoneMSG"
								style="display: none"></span></th>
						</tr>
 						 
						<tr>
							<th class="leftside pad_t10 pad_b20" valign="top">邮件验证码</th>
							<th class="rightside pad_b20" valign="top">
							<input
								type="text" id="codeByOldEmail"
								class="input_a100 ui-input w180-input itxt" maxlength="6">
								<button class="button button-w120 mar_l10" id="oldemailclick3"
									onclick="SecuritycenterUpate.sendEmailByOldEmail(this);">点击获取</button>
								<button class="button button-w120 mar_l10" style="display: none"
									id="oldemailcountDown3"></button> <br>
							<span class="prompt_1 error_1" id="verifyMSG3"
								style="display: none"></span></th>
						</tr>
						 
						<tr>
							<th></th>
							<th class="rightside">
								<button class="btn btn_36c btn_size120 next_pre newPhoneNext" onclick="SecuritycenterUpate.updatePhoneByOldEmailOne(this)" id="updatePhoneByOldEmailOne">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--验证原邮箱step1 END-->
 
			<!--验证新手机号码 step2 Start-->
			<div id="oldStep2" class="bank_step step2 none ">
				<table class="table_child fc_3 fs_14 form" width="100%"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th class="leftside pad_t10 pad_b20" valign="top">新手机号：</th>
							<th class="rightside fs_14 pad_b20" valign="top"><input
								type="text" name="newPhone" id="newPhone"
								class="input_all ui-input w320-input" maxlength="11"> <br>
							<span id="phoneMSG4" class="prompt_1 error_1" style="display: none"></span></th>
						</tr>
						<tr>
							<th class="leftside pad_t10 pad_b20" valign="top">验证码：</th>
							<th class="rightside input-text input-text-cur pad_b20"
								valign="top"><input type="text" name="newPhoneVerify"
								id="newPhoneVerify" class="input_a100 ui-input w180-input itxt"
								maxlength="6"> <span style="display: block;"
								class="inputVlue">请填写验证码</span>
								<button type="button" class="button button-w120 mar_l10"
									id="click4" onclick="SecuritycenterUpate.sendSSMByNewPhone(this);" value="">点击获取</button>
								<button type="button" class="button button-w120 mar_l10"
									style="display: none" id="countDown4"></button> <br>
							<span id="verifyMSG4" style=""></span></th>
						</tr>
						<tr>
							<th></th>
							<th class="rightside">
								<button type="button"
									class="btn btn_36c btn_size120 newPhoneNext" onclick="SecuritycenterUpate.updatePhoneByOldPhoneTwo(this)" id="updatePhoneByOldPhoneTwo">确定</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--验证新手机号码 step2 END-->

			<!--修改成功 step3 Start-->
			<div id="oldStep3" class="bank_step step4 wid_w240 pad_t20 none">
				<div class="sucess clearfix">
					<div class="icon_base icon_50 icon_base_tipright50 mar_r10 fl"></div>
					<div class="fl">
						<div class="fs_18">
							手机号码修改成功！<br>请牢记新号码！
						</div>
						<div class="ok_close">
							<a title="确定" href="${basePath}/user/securitycenter/list.action"
								class="btn btn_size100 btn_blue">确定</a>
						</div>
					</div>
				</div>
			</div>
			<!--修改成功 step3 End-->
		</div>
		<!-- 手机修改 end -->
		 
	</td>
</tr>