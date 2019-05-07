<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>账户管理-安全中心</title>
    <script type="text/javascript">var basePath = "${basePath}"</script>
    <link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${basePath}/resources/resource/Css/layui.css"  media="all">
    <link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/new.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/index.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/claim.css">
    <link href="${pageContext.request.contextPath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
    <style type="text/css">
        .gray {
            -webkit-filter: grayscale(100%);
            -moz-filter: grayscale(100%);
            -o-filter: grayscale(100%);
            filter: grayscale(100%);
        }
    </style>
    <script>
        $(function(){
            $(".side_nav").find("dl").siblings().removeClass("navcurron").eq(4).addClass("navcurron");
        });
    </script>
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
        <%@ include file="membermsgRight.jsp"%>

        <!-- 右侧end -->

    </div>
</div>
<!-- 右侧 -->

<!-- 尾部 -->
<%--<%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%>--%>
</body>
</html>