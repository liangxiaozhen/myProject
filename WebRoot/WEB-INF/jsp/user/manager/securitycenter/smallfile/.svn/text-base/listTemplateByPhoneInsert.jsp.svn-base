<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <!-- 手机号码验证  start-->
<tr class="validation-status clearfix">
	<td class="left wid_w120 bor_b">手机号码</td>
	<td class="wid_w480 fc_9 bor_b">保障账户与资金安全，是您在你我贷重要的身份凭证</td>
	<!--pos1 -->
	<td class="wid_w100 fc_9 bor_b"><i
		class="icon_base icon_16 icon_base_tipnote16 vertical_middle mar_r10"></i><span
		class="vertical_middle">未验证</span></td>
	<td class="bor_b wid_w200"><div class="pad_l30">
			<a title="立即验证" class="showNext phoneinsert" href="javascript:;" id="phoneBind">立即验证</a>
		</div></td>
</tr>
<tr class="only none phoneinsert_box">
	<td class="pad_t20 pad_b20" colspan="4">
		<table id="loginPwd" class="table_child form" width="100%"
			cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">手机号码</th>
					<th class="rightside pad_b20" valign="top"><input type="text"
						id="sc_phoneInsert" name="sc_phoneInsert"
						class="input_all ui-input w320-input" autocomplete="off">
						&nbsp;&nbsp;
						<button class="btn btn_bgf60 btn_size100" style="height: 38px;"
							id="sc_phoneInsertSendSSM"
							onclick="securitycenter.sendSSMByPhoneInsert(this)">发送短信验证码</button>
						<button class="btn btn_bgf60 btn_size400"
							style="height: 38px; display: none;" id="sc_phoneInsertSendSSMTip">发送短信</button>
							<span id="sc_phoneInsertMassage"
						style="display: none;"></span>
				</tr>
				<tr>
					<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">手机验证码</th>
					<th class="rightside pad_b20" valign="top"><input type="text"
						id="sc_phoneInsertCode" name="sc_phoneInsertCode"
						class="input_all ui-input w320-input" autocomplete="off">
						&nbsp;&nbsp; <span id="sc_phoneInsertCodeMassage"
						style="display: none;"></span>
				</tr>

				<tr>
					<th>&nbsp;</th>
					<th class="rightside">
						<button class="btn btn_36c btn_size120"
							onclick="securitycenter.phoneInsertSave(this);"
							id="sc_phoneInsertSubmit">立即验证</button>
					</th>
				</tr>
			</tbody>
		</table> <!-- 成功页面 start -->
		<div class="sucess clearfix wid_w240 none" id="sucessLoginPwd">
			<div class="icon_base icon_50 icon_base_tipright50 mar_r10 fl"></div>
			<div class="fl">
				<div class="fs_18 pad_b20">登录密码修改成功！</div>
				<div>
					<a class="btn btn_size100 btn_blue"
						href="${basePath}/user/securitycenter/list.action" title="确定">确定</a>
				</div>
			</div>
		</div> <!-- 成功页面 end -->
	</td>
</tr>
<!-- 手机号码验证  end -->