<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
 	#loginmima1 .color{background-color: #2a80bb;}
</style>
<!-- 登录密码 start -->
<tr class="validation-status clearfix">
	<td class="left wid_w120 bor_b">登录密码</td>
	<td class="wid_w480 fc_9 bor_b">保障账户安全，建议您定期更换密码</td>
	<!--pos1 -->
	<td class="wid_w100 fc_9 bor_b"><i
		class="icon_base icon_16 icon_base_tipright16 vertical_middle mar_r10"></i><span
		class="vertical_middle">已设置</span></td>
	<td class="bor_b wid_w200"><div class="pad_l30">
			<a title="修改" class="showNext xiugai" href="javascript:;">修改</a>
		</div></td>
</tr>
<c:choose >
	<c:when test="${not empty userBaseAccountInfo and userBaseAccountInfo.ismobileverify == 1 }">
			<tr class="only none xiugai_box">
				<td class="pad_t20 pad_b20" colspan="4">
					<table id="loginPwd" class="table_child form" width="100%"
						cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">手机号码</th>
								<th class="rightside pad_b20" valign="top">
								<button class="input_all ui-input w320-input"> ${userBaseAccountInfo.mobilephonestr}</button>
									&nbsp;&nbsp;<button class="btn btn_bgf60 btn_size100" style="height:38px;" id="sc_sendSSMByLoginPwd" onclick="securitycenter.sendSSMByLoginPassWord(this)">发送短信验证码</button>
							         <button class="btn btn_bgf60 btn_size400" style="height:38px;display:none;" id="sc_sendSSMByLoginPwdTip">发送短信</button>
 			 				</tr>
			 				<tr>
								<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">手机验证码</th>
								<th class="rightside pad_b20" valign="top"><input
									type="text" id="sc_LoginPwdCode" name="sc_LoginPwdCode"
									class="input_all ui-input w320-input" autocomplete="off">
									&nbsp;&nbsp; <span id="sc_sendSSMByLoginPwdMassage" style="display:none;"></span>
 			 				</tr>
							<tr>
								<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">设置新密码</th>
								<th class="rightside pad_b20" valign="top"><input
									type="password" id="sc_loginPwd" name="sc_loginPwd"
									onkeyup="pwStrength(this.value)" onblur="pwStrength(this.value)"
									class="input_all ui-input w320-input" autocomplete="off">
 								    <span id="sc_loginPwdMSG" style="display: none"></span>
									<div class="mima" id="loginmima1" style="margin-top:10px;display: none;">
										<span id="weak">弱</span> <span id="centre">中</span> <span
											id="strong">强
										</span>
									</div>
								</th>
							</tr>
							<tr>
								<th class="leftside wid_w190 pad_b20 pad_t10" valign="top">确认新密码</th>
								<th class="rightside pad_b20" valign="top"><input
									type="password" id="sc_loginPwd0" name="sc_loginPwd0"
									class="input_all ui-input w320-input" autocomplete="off">
 			 					<span id="sc_loginPwd0MSG" class="prompt_1"  style="display: none"></span></th>
							</tr>
							<tr>
								<th>&nbsp;</th>
								<th class="rightside">
									<button class="btn btn_36c btn_size120" onclick="securitycenter.loginPwdChange(this);" id="loginPwdChange">修改登录密码</button>
								</th>
							</tr>
						</tbody>
					</table> <!-- 成功页面 start -->
					<div class="sucess clearfix wid_w240 none" id="sucessLoginPwd">
						<div class="icon_base icon_50 icon_base_tipright50 mar_r10 fl"></div>
						<div class="fl">
							<div class="fs_18 pad_b20">登录密码修改成功！</div>
							<div>
								<a class="btn btn_size100 btn_blue" href="${basePath}/user/securitycenter/list.action" title="确定">确定</a>
							</div>
						</div>
					</div> <!-- 成功页面 end -->
				</td>
			</tr>
			<!--  登录密码 end -->
 	</c:when>
	<c:otherwise>
		<tr class="only none xiugai_box" id="loginpassbox">
			 <td colspan="4">
			 	<!--修改手机 StepWaysStart-->
				<div class="change_phone" id="stepWays">
					<ul class="clearfix wid_w300 pad_t20 mar_auto">
						<li class="fl wid_w120 txt_center mar_r20 mar_r30">
							<div class="change_phone_top">未验证手机号码</div>
							<div class="nwd_icon nwd_icon_phone"></div>
							<div>
								<a class="btn btn_size100 btn_blue"
									href="javascript:void(0);"onclick="validationPhone(this);" title="立即验证">立即验证</a>
							</div>
						</li>
 					</ul>
				</div>
			</td>
		</tr>	
	</c:otherwise>
</c:choose>
