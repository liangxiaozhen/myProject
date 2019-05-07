<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户开户信息详情列表</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<!-- 用户开户信息详情 start -->
				<div class="col-md-12" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">用户名</label> <span>${userBaseAccountInfo.loginname}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">用户类型</label> <span>${userBaseAccountInfo.isreally == 1 ? "个人" : "企业"}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">真实姓名</label> <span>${userBaseAccountInfo.realname}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">用户注册日期</label> <span>${gj:formatDate(userBaseAccountInfo.regdate,'yyyy-MM-dd HH:mm:dd')}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">用户email地址</label><span>${userBaseAccountInfo.email}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">邮件是否验证</label> <span>${userBaseAccountInfo.isemailverify == 1 ? "已验证" : "未验证"}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">手机号</label> <span>${userBaseAccountInfo.mobilephone}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">手机是否验证</label> <span>${userBaseAccountInfo.ismobileverify == 1 ? "已验证" : "未验证"}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">注册日期</label> <span>${gj:formatDate(userBaseAccountInfo.regdate,'yyyy-MM-dd HH:mm:dd')}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">是否实名认证</label> <span>${userBaseAccountInfo.isreally == 1 ? "已认证" : "未认证"}</span>&nbsp;&nbsp;
						</div>
					</div>

					<c:if test="${empty userBaseAccountInfo.userfsaccountinfo}">

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">是否开通第三方托管账号</label> <span>未开通</span>&nbsp;&nbsp;
							</div>
						</div>
					</c:if>
					<c:if test="${not empty userBaseAccountInfo.userfsaccountinfo}">
						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">托管通道标识</label> <span>${userBaseAccountInfo.userfsaccountinfo.channelidentifier}</span>&nbsp;&nbsp;
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">账户类型</label> <span>${userBaseAccountInfo.userfsaccountinfo.accounttype == 1 ? "个人" : "企业"}</span>&nbsp;&nbsp;
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">第三方托管账号开通时间</label> <span>${gj:formatDate(userBaseAccountInfo.userfsaccountinfo.openingtime,'yyyy-MM-dd HH:mm:dd')}</span>&nbsp;&nbsp;
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">用户商户号</label> <span>${userBaseAccountInfo.userfsaccountinfo.usrcustid}</span>&nbsp;&nbsp;
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">用户登录账户</label> <span>${userBaseAccountInfo.userfsaccountinfo.usrloginname}</span>&nbsp;&nbsp;
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">账户类型</label> <span>${userBaseAccountInfo.userfsaccountinfo.accounttype == 1 ? "个人" : "企业"}</span>&nbsp;&nbsp;
							</div>
						</div>

					</c:if>
				</div>
				<!-- 用户开户信息详情 end -->
			</div>
		</div>
	</div>
</body>
</html>