<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="verification-box">
	<!-- 标题盒子 -edit-phone-box-right-->
	<div class="edit-phone-box clearfloat" id="update_user_email_box">
		<c:if test="${userBaseAccountInfo.mobilephonestr != null}">
			<div class="edit-phone change-select"
				id="update_user_email_editByOldEmail">
				<p class="title">通过原邮箱验证码修改</p>
				<p class="description">您的邮箱
					${userBaseAccountInfo.emailstr}还在正常使用请选择此方式</p>
				<i></i>
			</div>
		</c:if>

		<c:if test="${userBaseAccountInfo.mobilephonestr != null}">
			<div class="edit-phone change-select"
				id="update_user_email_editByOldPhone">
				<p class="title">通过原手机短信修改</p>
				<p class="description">
					您的手机${userBaseAccountInfo.mobilephonestr}还在正常使用请选择此方式</p>
				<i></i>
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
			</div>
		</c:if>

		<c:if test="${userBaseAccountInfo.mobilephone == null}">
			<div class="edit-phone change-select">
				<p class="title">未绑定手机</p>
				<p class="description">
					请先绑定手机<br> <a href="javascript:void(0)">绑定手机</a>
				</p>
			</div>
		</c:if>
	</div>

	<!-- 根据手机验证码修改 开始 -->
	<div class="phone-verification-step" id="update_user_email_updateone"
		style="display: none;"></div>
</div>
<script type="text/javascript">
	$(function(){
		//安全验证 修改用户手机 验证方式设置选择
		$("#update_user_email_box").find(".change-select").on("click",function(){
				var $id = $(this).attr("id");
				if($id != null){
				    $(this).addClass("selected-type").siblings().removeClass("selected-type");
					if($id == "update_user_email_editByOldEmail"){
		 				$("#update_user_email_updateone").show();
						$("#update_user_email_updateone").load(basePath+"/user/securitycenter/updateUserEmailPassedOldEmail.action?v="+Math.random());
					}else if($id == "update_user_email_editByOldPhone"){
						$("#update_user_email_updateone").show();
		  				$("#update_user_email_updateone").load(basePath+"/user/securitycenter/updateuseremaileditByOldPhone.action?v="+Math.random());
					} 
				}
		});
	});
</script>