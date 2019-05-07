<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="verification-box">
	<!-- 标题盒子 -->
	<div class="edit-phone-box clearfloat"
		id="update_Trading_Password_verification_updateone_box">
		<div class="edit-phone change-select"
			id="gj_update_Trading_Password_edit">
			<p class="title">通过原交易密码修改</p>
			<p class="description">
				记得原交易密码请选择此<br>方式
			</p>
			<i></i> <i></i> <i></i>
		</div>
		<c:if test="${userBaseAccountInfo.mobilephonestr != null}">
			<div class="edit-phone change-select"
				id="gj_update_Trading_Password_editphone">
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
	<div class="phone-verification-step"
		id="update_Trading_Password_verification_updateone"
		style="display: none;"></div>
</div>
<script type="text/javascript">
$(function(){
	//安全验证 修改密保问题 验证方式设置选择
	$("#update_Trading_Password_verification_updateone_box").find(".change-select").on("click",function(){
		$(this).addClass("selected-type").siblings().removeClass("selected-type");
			var $id = $(this).attr("id");
			if($id == "gj_update_Trading_Password_edit"){
 				$("#update_Trading_Password_verification_updateone").show();
  				$("#update_Trading_Password_verification_updateone").load(basePath+"/user/securitycenter/updateTradingPasswordPassedOldPwd.action?v="+Math.random());
			}else if($id == "gj_update_Trading_Password_editphone"){
				$("#update_Trading_Password_verification_updateone").show();
				$("#update_Trading_Password_verification_updateone").load(basePath+"/user/securitycenter/insetTradingPwdDivShow.action?v="+Math.random());
			}
	});
});
</script>