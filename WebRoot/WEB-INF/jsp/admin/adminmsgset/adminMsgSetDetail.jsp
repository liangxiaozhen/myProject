<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta name="viewport"
          content="width=device-width,user-scalable=no,initial-scale=1">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">id：</label><label class="col-md-4">${userMsgSet.id}</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">用户ID：</label><label class="col-md-4">${userMsgSet.baseid}</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">消息类型：</label><label class="col-md-4">
   	 <c:choose>
         <c:when test="${userMsgSet.msgtype==1}">投资人</c:when>
         <c:when test="${userMsgSet.msgtype==2}">借款人  </c:when>
     </c:choose>
     </label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">项目：</label><label
        class="col-md-4">
	  ${userMsgSet.item}
	</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">站内信：</label><label
        class="col-md-4">
		<c:choose>
                    <c:when test="${userMsgSet.intmsg==1}">开启</c:when>
                    <c:otherwise>关闭</c:otherwise>
        </c:choose>    

</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">短信：</label><label class="col-md-4">
		<c:choose>
                    <c:when test="${userMsgSet.sms==1}">开启</c:when>
                    <c:otherwise>关闭</c:otherwise>
        </c:choose>     

</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">邮件：</label><label class="col-md-4">
<c:choose>
                                        <c:when test="${userMsgSet.email==1}">开启</c:when>
                                        <c:otherwise>关闭</c:otherwise>
                                         </c:choose>    

</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">手机APP：</label><label class="col-md-4">
		<c:choose>
                    <c:when test="${userMsgSet.app==1}">开启</c:when>
                    <c:otherwise>关闭</c:otherwise>
        </c:choose>   
</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">微信服务号：</label><label class="col-md-4">
		<c:choose>
                    <c:when test="${userMsgSet.wechat==1}">开启</c:when>
                    <c:otherwise>关闭</c:otherwise>
        </c:choose>    
</label>
</div>
<div class="row" style="margin-top: auto">
    <label class="col-md-4 text-right">备注：</label><label class="col-md-4">${userMsgSet.remark}</label>
</div>
</body>
</html>