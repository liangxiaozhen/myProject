<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="verification-box">
	<!-- 标题盒子 -->
	<div class="edit-phone-box clearfloat" id="update_user_phone_box">
		<c:if test="${userBaseAccountInfo.mobilephonestr != null}">
			<div class="edit-phone change-select"
				id="update_user_phone_editphone">
				<p class="title">通过原手机短信修改</p>
				<p class="description">
					您的手机${userBaseAccountInfo.mobilephonestr}还在正常使用<br>请选择此方式
				</p>
				<i></i> <i></i> <i></i>
			</div>
		</c:if>

		<c:if test="${userAccountSafeInfo.question1 != null}">
			<div class="edit-phone change-select"
				id="update_user_phone_editquestion">
				<p class="title">通过原安全问题修改</p>
				<p class="description">
					原手机号丢失或停用，但记得<br /> 安全问题，请选择此方式
				</p>
				<i></i> <i></i> <i></i>
			</div>
		</c:if>

		<c:if test="${userAccountSafeInfo.question1 == null}">
			<div class="edit-phone change-select">
				<p class="title">未设置安全问题</p>
				<p class="description">
					请先设置安全问题<br> <a
						href="javascript:Insert_Security_Question_DivShow(this)"
						style="color: red;">设置安全问题</a>
				</p>
				<i></i>
			</div>
		</c:if>

		<c:if test="${userBaseAccountInfo.mobilephonestr == null}">
			<div class="edit-phone change-select">
				<p class="title">未绑定手机</p>
				<p class="description">
					请先绑定手机<br> <a href="javascript:void(0)">绑定手机</a>
				</p>
				<i></i>
			</div>
		</c:if>
	</div>

	<!-- 根据手机验证码修改 开始 -->
	<div class="phone-verification-step" id="update_user_phone_updateone"
		style="display: none;">
		<div class="loading-pic">
			<img src="${basePath}/resources/Images/big_load.gif" alt="" />
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		//安全验证 修改用户手机 验证方式设置选择
		$("#update_user_phone_box").find(".change-select").on("click",function(){
				var $id = $(this).attr("id");
				if($id != null){
				    $(this).addClass("selected-type").siblings().removeClass("selected-type");
					if($id == "update_user_phone_editphone"){
		 				$("#update_user_phone_updateone").show();
						$("#update_user_phone_updateone").load(basePath+"/user/securitycenter/updateUserPhonePassedPhone.action?v="+Math.random());
					}else if($id == "update_user_phone_editquestion"){
						$("#update_user_phone_updateone").show();
		  				$("#update_user_phone_updateone").load(basePath+"/user/securitycenter/updateUserPhonePassedAnswer.action?v="+Math.random());
					}
				}
		});
	});
</script>