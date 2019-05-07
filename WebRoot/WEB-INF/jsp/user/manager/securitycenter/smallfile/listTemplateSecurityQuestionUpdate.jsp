<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!-- 安全保护问题 start -->
<tr class="validation-status clearfix">
	<td class="left wid_w120 bor_b">安全保护问题</td>
	<td class="wid_w480 fc_9 bor_b">保障隐私安全，也是修改个人信息、快速找回密码的重要途径</td>
	<td class="wid_w100 fc_9 bor_b"><i
		class="icon_base icon_16 icon_base_tipright16 vertical_middle mar_r10"></i><span
		class="vertical_middle">已设置</span></td>
	<td class="bor_b wid_w200 fc_6"><div class="pad_l30">
			<a class="showNext qus_shez" title="重置" href="javascript:;">重置</a>
		</div></td>
</tr>
<tr class="only none qus_shez_box">
	<td colspan="4">
		<div class="mar_auto safe-protect">
			<span id="doWhatUpdate" style="display: none;"></span>
			<div class="steps steps_4 clearfix pad_t20 flowstep pad_b20">
				<ul id="safeQuestionUpdateStep1"
					class="active  wid_w160 fl step-first txt_center">
					<li class="sz step-no fc_f"><span>1</span></li>
					<li class="sm txt_center pad_t10">1.验证身份</li>
				</ul>
				<ul id="safeQuestionUpdateStep2" class="wid_w160 fl txt_center">
					<li class="sz step-no fc_9"><span>2</span></li>
					<li class="sm txt_center pad_t10">2.重置安全保护问题</li>
				</ul>
				<ul id="safeQuestionUpdateStep3" class="wid_w160 fl txt_center">
					<li class="sz step-no fc_9"><span>3</span></li>
					<li class="sm txt_center pad_t10">3.确认安全保护问题</li>
				</ul>
				<ul id="safeQuestionUpdateStep4" class="wid_w160 fl step-last txt_center">
					<li class="sz step-no fc_9"><span>4</span></li>
					<li class="sm txt_center pad_t10">4.完成</li>
				</ul>
			</div>
			<!--安全问题 step1 Start-->
			<div id="questionUpdateStep1" class="bank_step step1 pad_t20">
				<table class="table_child fc_3 fs_14 form" width="100%"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th class="leftside pad_b20" width="92">手机号</th>
							<th class="rightside fs_14 pad_b20">${userBaseAccountInfo.mobilephonestr }</th>
						</tr>
						<tr>
							<th valign="top" class="leftside pad_t10 pad_b20" width="92">验证码</th>
							<th valign="top"
								class="rightside  input-text input-text-cur pad_b20"><input
								type="text" maxlength="6" id="questionUpdateCode"
								class="input_a100 ui-input w180-input itxt"> <span
								style="display: block;" class="inputVlue">请填写验证码</span>
								<button onclick="SecuritycenterUpate.sendPhoneCodeByquestionUpdate(this)" id="sendPhoneCodeByquestionUpdate"
									class="button button-w120 mar_l10">点击获取</button>
								<button id="sendPhoneCodeByquestionUpdateTip" style="display: none"
									class="button button-w120 mar_l10" type="button"></button> <br>
							<span style="display: none;" id="questionUpdateMSG6"
								class="prompt_1 error_1"><em></em></span></th>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<th class="rightside">
								<button id="questionUpdateCheckCode" class="btn btn_36c btn_size120" onclick="SecuritycenterUpate.questionUpdateCheckCode(this);">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--安全问题 step1 end-->

			<!--安全问题 step2 Start-->
			<div id="questionUpdateStep2" class="bank_step step2 none" >
				<table class="table_child fc_3 fs_14 form" width="100%"
					cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th class="leftside pad_b20" colspan="2">
								<div class="clearfix wid_466 mar_auto pad_t30">
									<div id="allMsgSite" class="yellow clearfix">
										<span
											class="icon_base icon_16 icon_base_tipnote16 vertical_middle"></span>
										<span id="allUpdateMsgError" class="fs_14 vertical_middle">密保问题可用于找回登录密码、交易密码等操作</span>
									</div>
								</div>
							</th>
						</tr>
						<tr>
							<th class="leftside pad_b20" width="92">问题一</th>
							<th class="rightside pad_b20"><select id="questionUpdateOne"
								class="w212 ui-input w320-input">
									<option id="questionUpdate1" name="question" value="-1">请选择问题</option>
									<option value="1">您母亲的生日是？</option>
									<option value="2">您母亲的姓名是？</option>
									<option value="3">您父亲的生日是？</option>
									<option value="4">您父亲的姓名是？</option>
									<option value="5">您孩子的生日是？</option>
									<option value="6">您孩子的姓名是？</option>
									<option value="7">您配偶的生日是？</option>
									<option value="8">您配偶的姓名是？</option>
									<option value="9">您的出生地是哪里？</option>
									<option value="10">您最喜欢什么颜色？</option>
									<option value="11">您是什么学历？</option>
									<option value="12">您的属相是什么的？</option>
									<option value="13">您小学就读的是哪所学校？</option>
									<option value="14">您最崇拜谁？</option>
									<option value="15">您打字经常用什么输入法？</option>
									<option value="16">您是什么时间参加工作的？</option>
							</select></th>
						</tr>
						<tr>
							<th class="leftside pad_b20">答案</th>
							<th class="rightside pad_b20"><input type="text"
								id="answerUpdate1" name="answer" maxlength="20"
								class="input_all ui-input w320-input"></th>
						</tr>
						<tr>
							<th class="leftside pad_b20">问题二</th>
							<th class="rightside pad_b20"><select id="questionUpdateTwo"
								class="w212 ui-input w320-input">
									<option id="questionUpdate2" name="question" value="-1">请选择问题</option>
									<option value="1">您母亲的生日是？</option>
									<option value="2">您母亲的姓名是？</option>
									<option value="3">您父亲的生日是？</option>
									<option value="4">您父亲的姓名是？</option>
									<option value="5">您孩子的生日是？</option>
									<option value="6">您孩子的姓名是？</option>
									<option value="7">您配偶的生日是？</option>
									<option value="8">您配偶的姓名是？</option>
									<option value="9">您的出生地是哪里？</option>
									<option value="10">您最喜欢什么颜色？</option>
									<option value="11">您是什么学历？</option>
									<option value="12">您的属相是什么的？</option>
									<option value="13">您小学就读的是哪所学校？</option>
									<option value="14">您最崇拜谁？</option>
									<option value="15">您打字经常用什么输入法？</option>
									<option value="16">您是什么时间参加工作的？</option>
							</select></th>
						</tr>
						<tr>
							<th class="leftside pad_b20">答案</th>
							<th class="rightside pad_b20"><input type="text"
								id="answerUpdate2" name="answer" maxlength="20"
								class="input_all ui-input w320-input"></th>
						</tr>
						<tr>
							<th class="leftside pad_b20">问题三</th>
							<th class="rightside pad_b20"><select id="questionUpdateThree"
								class="w212 ui-input w320-input">
									<option id="questionUpdate3" name="question" value="-1">请选择问题</option>
									<option value="1">您母亲的生日是？</option>
									<option value="2">您母亲的姓名是？</option>
									<option value="3">您父亲的生日是？</option>
									<option value="4">您父亲的姓名是？</option>
									<option value="5">您孩子的生日是？</option>
									<option value="6">您孩子的姓名是？</option>
									<option value="7">您配偶的生日是？</option>
									<option value="8">您配偶的姓名是？</option>
									<option value="9">您的出生地是哪里？</option>
									<option value="10">您最喜欢什么颜色？</option>
									<option value="11">您是什么学历？</option>
									<option value="12">您的属相是什么的？</option>
									<option value="13">您小学就读的是哪所学校？</option>
									<option value="14">您最崇拜谁？</option>
									<option value="15">您打字经常用什么输入法？</option>
									<option value="16">您是什么时间参加工作的？</option>
							</select></th>
						</tr>
						<tr>
							<th class="leftside pad_b20">答案</th>
							<th class="rightside pad_b20"><input type="text"
								id="answerUpdate3" name="answer" maxlength="20"
								class="input_all ui-input w320-input"></th>
						</tr>
						<tr>
							<th></th>
							<th class="rightside">
								<button id="securityQuestionUpdateTwo" type="button"
									class="btn btn_36c btn_size120" onclick="SecuritycenterUpate.securityQuestionUpdateTwo(this)">下一步</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--安全问题 step2 end-->

			<!--安全问题 step3 Start-->
			<div id="questionUpdateStep3" class="bank_step step3 none">
				<table id="table_55" class="form table_child" width="100%"
					cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">问题一</th>
							<th valign="top" class="rightside pad_b20"><input type=""
								autocomplete="off" class="input_all ui-input w320-input"
								id="questionUpdate2-1" readonly="readonly" value=""></th>
						</tr>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">答案</th>
							<th valign="top" class="rightside pad_b20"><input type=""
								autocomplete="off" class="input_all ui-input w320-input"
								id="answerUpdate2-1" readonly="readonly" value=""></th>
						</tr>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">问题二</th>
							<th valign="top" class="rightside pad_b20"><input type=""
								autocomplete="off" class="input_all ui-input w320-input"
								id="questionUpdate2-2" readonly="readonly" value=""></th>
						</tr>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">答案</th>
							<th valign="top" class="rightside pad_b20"><input type=""
								autocomplete="off" class="input_all ui-input w320-input"
								id="answerUpdate2-2" readonly="readonly" value=""></th>
						</tr>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">问题一</th>
							<th valign="top" class="rightside pad_b20"><input type=""
								autocomplete="off" class="input_all ui-input w320-input"
								id="questionUpdate2-3" readonly="readonly" value=""></th>
						</tr>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">答案</th>
							<th valign="top" class="rightside pad_b20"><input type=""
								autocomplete="off" class="input_all ui-input w320-input" name=""
								id="answerUpdate2-3" readonly="readonly" value=""></th>
						</tr>
						<tr>
							<th valign="top" width="92" class="leftside pad_b20 pad_t10">&nbsp;</th>
							<th valign="top" class="rightside pad_b20">
								<button id="securityQuestionUpdateThree" type="button" class="btn btn_36c btn_size120" onclick="SecuritycenterUpate.securityQuestionUpdateThree(this)">确定</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<!--安全问题 step3 end-->

			<!--安全问题 step4 Start-->
			<div id="questionUpdateStep4" class="bank_step step4 pad_t20 pad_b20 none">
				<div class="sucess clearfix" style="margin-left: 200px;">
					<div class="fl icon_base icon_50 icon_base_tipright50 mar_r10"></div>
					<div class="fl">
						<div class="fs_18 pad_b20">安全保护问题设置成功！</div>
						<div class="queding">
							<a title="确定" href="${basePath}/user/securitycenter/list.action"
								class="btn btn_size100 btn_blue">确定</a>
						</div>
					</div>
				</div>
			</div>
			<!--安全问题 step4 end-->
		</div>
	</td>
</tr>
<!-- 安全保护问题 end -->