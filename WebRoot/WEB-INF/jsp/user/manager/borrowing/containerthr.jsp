<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户管理-充值</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
</head>
<body>
<!-- 头部 -->
<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>

<!-- layout start -->
<div class="nwd_main bor_l bor_r bor_b clearfix">
	<div class="fl perCenterBg">
		<!-- 左侧 -->
		<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
		
		<!-- 右侧 start -->
		<%@ include file="/WEB-INF/jsp/user/manager/borrowing/Publicinformation.jsp"%>
		<!-- 右侧end -->
		
	</div>
</div>
<!-- 右侧 -->

<!-- 尾部 -->
<%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%>
</body>
</html>