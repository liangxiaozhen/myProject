<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户管理-安全中心</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
 </head>
<body>
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧 -->
 			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			
			<%@ include file="/WEB-INF/jsp/user/manager/securitycenter/listTemplate.jsp"%>
			
 		</div>
	</div>
<div class="bg"></div>	
<!-- tipBox start -->
<div id="tipBox">
 
 </div>

 <!-- tipBox end -->
<!-- 尾部 -->
<%--  <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/securitycenter.js"></script>
</body>
</html>