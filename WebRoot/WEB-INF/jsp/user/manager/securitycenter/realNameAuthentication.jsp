<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_干将会员中心</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧 -->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- right start -->
			<div class="fl perCerterR bor_l bor_r">
				<div class="fl pad_30 wid_w900 min_h500 clearfix">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_6">安全中心</span><span
							class="fc_6">实名认证</span>
					</div>
					<div class="fl pad_t20 clearfix" style="width: 100%;">
						<!---1 start---->
						<form id="idenForm" method="post" class="nwd-formUi">
							<input type="hidden" id="backUrl" value="">
							<table class="table_3 form fs_14">
								<tbody>
									<tr>
										<th colspan="2" class="line_1 pad_b20">
											<div class="yellow clearfix">
												<span
													class="mar_r5 icon_base icon_16 icon_base_tipnote16 vertical_middle"></span>
												<span class="fs_12 vertical_middle">为了保证您的资金安全，请准确填写您的身份信息，实名认证一旦成功将不可修改！</span>
											</div>
										</th>
									</tr>
									<tr>
										<th class="th_l pad_t10 pad_b20 leftside pad_r20" valign="top">真实姓名</th>
										<td class="rightside pad_b20"><input type="text"
											name="realname" id="realname"
											class="input_all ui-input win_w160"> <br>
										<span id="realnameMSGError" style="display: none"
											class="prompt_1"><i></i></span>
										</td>
									</tr>
									<tr>
										<th class="pad_t10 pad_b20 leftside pad_r20" valign="top">身份证号</th>
										<td class="rightside pad_b20"><input type="text"
											name="identity" id="identity"
											class="input_all ui-input w320-input"> <em
											class="ga_icon mar_l10 mar_r5"></em> <span
											class="fc_9 mar_r5 fs_14 vertical_middle">公安部身份认证系统</span> <br>
										<span id="identityMSGError" style="display: none"
											class="prompt_1 error_1"><i></i></span></td>
									</tr>
									<tr>
										<th></th>
										<td>
											<button class="btn btn_blue btn_size120"
												type="button" value="提交认证" onclick="realnameauthentication.save(this);" id="realnameauthenticationSum">提交认证</button>
<!-- 												 <span style="margin-left: 15px;">非大陆人士<a href="javascript:void(0)" class="blue pl10 showNext mar_l10">请点击这里</a></span> -->
										</td>
									</tr>
								</tbody>
							</table>
						</form>
						<div class="cleafix"></div>
						<div class="pad_t30">
							<div class="pad_l20 ">
								<div class="fs_16 fc_3">温馨提示</div>
								<div class="fc_9">
									<div class="mar_t10 mar_b5">•
										你我贷携手“全国公民身份证号码查询服务中心”（NCIIC）推出权威身份认证方式</div>
									<div class="mar_t10 mar_b5">•
										实名认证是您绑定银行卡和使用你我贷核心功能的必要条件,提现时的银行帐户名以您提交的认证信息为准。</div>
									<div class="mar_t10 mar_b5">•
										如果您在验证过程中，出现任何问题，请联系客服400-7910-888。</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- right end -->
 		</div>
	</div>
	
<!-- 尾部 -->
<%--  <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/realnameauthentication.js"></script>
</body>
</html>