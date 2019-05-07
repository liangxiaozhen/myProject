<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户 安全管理中心</title>
<link href="${basePath}/js/css/security_list.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">var basePath = "${basePath}"</script>
<script type="text/javascript"
	src="${basePath}/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${basePath}/js/sg/gj_validator.js"></script>
 </head>
<body>
	<div class="box">
		<div class="box_items">
			<div class="security_box">
				<ul>
					<a href="${basePath}//moneymoremore/repayMent/doAutoRepayMentTransfer.action">还款自动转账测试</a>
					<a href="${basePath}//moneymoremore/repayMent/doManualRepayMentTransfer.action" target="_blank">还款手动转账测试</a>
 					<c:choose>
						<c:when test="${userBaseAccountInfo.isreally > 0}">
							<li>
								<span class="items-1"> 
									<i class="icon-success"></i>
									<span>身份验证</span>
								</span> 
								<span class="items-3"> 
									已认证&nbsp;&nbsp;&nbsp;</span><span class="items-4">
 								</span>
							</li>
						</c:when>
   						<c:otherwise>
 								<li>
 									<span class="items-1"> 
 										<i class="icon-fail"></i>
										<span>身份验证</span>
									</span> 
										<span class="items-3"> 完成身份认证才能投标和借款，实名认证后身份证不允许修改、更换或注销&nbsp;&nbsp;&nbsp;</span>
									<span class="items-4">
 								 		<a href="javascript:void(0)" class="items-4-a gj_Vilidator_insertRealname">立即验证</a>
								    </span>
								</li>
 						</c:otherwise>						
 					</c:choose>
 					
 					<!-- 邮箱验证编辑框开始-->
					<li class="li-height-auto" id="verification-box-10"
						style="display: none">
						<div class="loading-pic">
							<img src="${basePath}/resources/Images/big_load.gif" alt="" />
						</div>
					</li>
  
					<!-- 邮箱验证 开始 -->
					<c:choose>
						<c:when test="${userBaseAccountInfo.isemailverify > 0}">
							<li><span class="items-1"> <i class="icon-success"></i>
									<span>邮箱验证</span>
							</span> <span class="items-3"> ${userBaseAccountInfo.emailstr} </span> <span
								class="items-4"> <a href="javascript:void(0)"
									class="items-4-b gj_Vilidator_updateSecurityEmail">修改</a>
							</span></li>
						</c:when>
						<c:otherwise>
							<li><span class="items-1"> <i class="icon-fail"></i>
									<span>邮箱验证</span>
							</span> <span class="items-2"> 未认证 </span> <span class="items-3">
									邮箱验证后可以实时跟踪账户资金变动情况 </span> <span class="items-4"> <a
									href="javascript:void(0)"
									class="items-4-a gj_Vilidator_insertSecurityEmail">立即验证</a>
							</span></li>
						</c:otherwise>
					</c:choose>

					<!-- 邮箱验证编辑框开始-->
					<li class="li-height-auto" id="verification-box-1"
						style="display: none">
						<div class="loading-pic">
							<img src="${basePath}/resources/Images/big_load.gif" alt="" />
						</div>
					</li>
					<!-- 邮箱验证 结束 -->

					<!--手机验证 开始-->
					<c:if test="${userBaseAccountInfo.mobilephonestr != null}">
						<li><span class="items-1"> <i class="icon-success"></i>
								<span>手机验证</span>
						</span> <span class="items-3">
								${userBaseAccountInfo.mobilephonestr} </span> <span class="items-4">
								<a href="javascript:void(0)"
								class="items-4-b gj_Vilidator_updateSecurityPhone">修改</a>
						</span></li>
					</c:if>

					<c:if test="${userBaseAccountInfo.mobilephonestr == null}">
						<li><span class="items-1"> <i class="icon-fail"></i> <span>手机验证</span>
						</span> <span class="items-2"> 未认证 </span> <span class="items-3">
								手机验证可以提高账号安全性 </span> <span class="items-4"> <a
								href="javascript:void(0)"
								class="items-4-a gj_Vilidator_insertSecurityPhone">立即验证</a>
						</span></li>
					</c:if>
					<!-- 手机验证编辑框开始-->
					<li class="li-height-auto" id="verification-box-2"
						style="display: none">
						<div class="loading-pic">
							<img src="${basePath}/resources/Images/big_load.gif" alt="" />
						</div>
					</li>
					<!-- 手机验证结束-->

					<!-- 安全验证开始 已经验证的-->
					<c:if test="${userAccountSafeInfo.question1 != null}">
						<li><span class="items-1"> <i class="icon-success"></i>
								<span>安全验证</span>
						</span> <span class="items-3"> 已设置 </span> <span class="items-4">
								<a href="javascript:void(0)"
								class="items-4-b gj_Vilidator_updateSecurityQuestion">修改</a>
						</span></li>

						<li class="li-height-auto" id="verification-box-3"
							style="display: none">
							<div class="loading-pic">
								<img src="${basePath}/resources/Images/big_load.gif" alt="" />
							</div>
						</li>
					</c:if>

					<!-- 安全验证开始 没有验证的-->
					<c:if test="${userAccountSafeInfo.question1 ==null}">
						<li><span class="items-1"> <i class="icon-fail"></i> <span>安全验证</span>
						</span> <span class="items-2"> 未认证 </span> <span class="items-3">
								安全问题可以提高密码安全性 </span> <span class="items-4"> <a
								href="javascript:void(0)"
								class="items-4-a gj_Vilidator_insertSecurityQuestion">立即验证</a>
						</span></li>
						<!-- 安全验证编辑框开始-->
						<li class="li-height-auto" id="verification-box-3"
							style="display: none">
							<div class="loading-pic">
								<img src="${basePath}/resources/Images/big_load.gif" alt="" />
							</div>
						</li>
					</c:if>
					<!-- 安全验证结束 没有验证的-->

					<!--交易密码验证 开始-->
					<c:if test="${userAccountSafeInfo.securitypasswordone != null}">
						<li><span class="items-1"> <i class="icon-success"></i>
								<span>交易密码</span>
						</span> <span class="items-3"> 已设置 </span> <span class="items-4">
								<a href="javascript:void(0)"
								class="items-4-b gj_Vilidator_updateTradingPassword_reset">重置</a>
						</span></li>
					</c:if>
					<c:if test="${userAccountSafeInfo.securitypasswordone == null}">
						<li><span class="items-1"> <i class="icon-fail"></i> <span>交易密码</span>
						</span> <span class="items-2"> 未认证 </span> <span class="items-3">
								交易密码可以提高投资，提现的安全 </span> <c:choose>
								<c:when test="${userBaseAccountInfo.mobilephone != null}">
									<span class="items-4"> <a href="javascript:void(0)"
										class="items-4-a gj_Vilidator_insetTradingPassword">立即验证</a>
									</span>
								</c:when>
								<c:otherwise>
									<span class="items-4"> <a href="javascript:void(0)"
										class="items-4-a gj_Vilidator_insertTradingPasswordByPhone">立即验证</a>
									</span>
								</c:otherwise>
							</c:choose></li>
					</c:if>

					<!-- 交易密码编辑框开始 -->
					<li class="li-height-auto" id="verification-box-4"
						style="display: none">
						<div class="loading-pic">
							<img src="${basePath}/resources/Images/big_load.gif" alt="" />
						</div>
					</li>
					<!--交易密码 结束-->

					<!-- 登录密码 -->
					<li><span class="items-1"> <i class="icon-success"></i>
							<span>登录密码</span>
					</span> <span class="items-3"> 已设置 </span> <span class="items-4"> <a
							href="javascript:void(0)"
							class="items-4-b gj_Vilidator_updatepassword">重置</a>
					</span></li>

					<li class="li-height-auto" id="verification-box-5"
						style="display: none;">
						<div class="loading-pic">
							<img src="${basePath}/resources/Images/big_load.gif" alt="" />
						</div>
					</li>
					<!-- 登录密码结束 -->
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${basePath}/js/user/security/gj_security.js"></script>
</body>
</html>