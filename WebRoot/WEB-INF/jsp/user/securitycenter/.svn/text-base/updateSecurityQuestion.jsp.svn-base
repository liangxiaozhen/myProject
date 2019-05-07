<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="verification-box">
	<!-- 标题盒子 -->
	<div class="edit-phone-box clearfloat" id="gj_edit_phone_box">
		<div class="edit-phone change-select" id="gj_question_editanswer">
			<p class="title">通过原安全问题修改</p>
			<p class="description">
				记得安全问题答案请选择此<br>方式
			</p>
			<i></i> <i></i> <i></i>
		</div>
		<c:if test="${userBaseAccountInfo.mobilephonestr != null}">
			<div class="edit-phone change-select" id="gj_question_editphone">
				<p class="title">通过手机修改</p>
				<p class="description">
					不记得原交易密码，绑定手机<br>${userBaseAccountInfo.mobilephonestr}号还可使用请选择此方式</p>
				<i></i>
			</div>
		</c:if>
		<c:if test="${userBaseAccountInfo.mobilephonestr == null}">
			<div class="edit-phone change-select">
				<p class="title">未绑定手机</p>
				<p class="description">
					请先绑定手机<br> <a href="javascript:Insert_UserPhone_DivShow(this)"
						style="color: red;">绑定手机</a>
				</p>
				<i></i>
			</div>
		</c:if>
	</div>

	<!-- 根据手机验证码修改 开始 -->
	<div class="phone-verification-step" id="gj_verification_updateone"
		style="display: none;">
		<div class="loading-pic">
			<img src="${basePath}/resources/Images/big_load.gif" alt="" />
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	//安全验证 修改密保问题 验证方式设置选择
	$("#gj_edit_phone_box").find(".change-select").on("click",function(){
		$(this).addClass("selected-type").siblings().removeClass("selected-type");
			var $id = $(this).attr("id");
			if($id == "gj_question_editanswer"){
 				$("#gj_verification_updateone").show();
  				$("#gj_verification_updateone").load(basePath+"/user/securitycenter/UpdateSecurityQuestionPassedAnswer.action?v="+Math.random());
			}else if($id == "gj_question_editphone"){
				$("#gj_verification_updateone").show();
				$("#gj_verification_updateone").load(basePath+"/user/securitycenter/UpdateSecurityQuestionPassedPhone.action?v="+Math.random());
			}
	});
});
</script>