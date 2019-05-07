<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<html>
<head>
    <title>账户管理-安全中心</title>
</head>
<body>
<form id="myForm" class="form-inline remindMessage" name="myForm" method="post" action="">
    <input type="hidden" id="msgId" value="" />
    <a class="btn btn_bord_blue btn_size100 deleteBtn" href="javascript:delete1()"> <em class="nwd_icon nwd_icon_delete"></em><span class="pad_l5">删除</span> </a>
    <a class="btn btn_bord_blue btn_size100 readBtn" href="javascript:batchUpdateIsRead()"> <span class="pad_l5">标为已读</span> </a>
    <!-- 提醒消息 线上 start -->
    <ul class="msg_all clearfix" id="msgUl">
        <li >
            <div class="haead clearfix">
                <span class="col_1"><input type="checkbox" class="all_0 mar_r20" onclick="selectall()" name="checkall" />全选</span>
                <span class="col_3">主题</span>
                <span class="col_4">时间</span>
            </div>
        </li>
        <c:forEach items="${pagehelper.list}" var="userIntMsg">
            <li>
                <div class="haead aHold clearfix aHold">
                    <span class="col_1"><input type="checkbox" value="${userIntMsg.id}" name="checkval" /></span>
                    <c:choose>
                        <c:when test="${userIntMsg.isread==1}">
                            <span class="elli col_3"><i id="${userIntMsg.id}ico" class="nwd_icon nwd_icon_message gift_atten  mar_r20 gray"></i>&nbsp;<a onclick="openMsg(${userIntMsg.id},1)" href="#" class="fc_3">${userIntMsg.msgtitle}</a></span>
                        </c:when>
                        <c:otherwise>
                            <span class="elli col_3"><i id="${userIntMsg.id}ico" class="nwd_icon nwd_icon_message gift_atten  mar_r20"></i>&nbsp;<a onclick="openMsg(${userIntMsg.id},0)" href="#" class="fc_3">${userIntMsg.msgtitle}</a></span>
                        </c:otherwise>
                    </c:choose>
                    <span class="col_4"><a class="fc_3">${userIntMsg.msgtimeString }</a></span>
                </div>
                <div class="content hidden fc_3 bg-gray pad_l30" id="${userIntMsg.id}content">
                        ${userIntMsg.msgcontent}
                </div> </li>
        </c:forEach>
    </ul>
    <div class="page_div">
        <%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
    </div>
</form>
<script>
    function selectall(){
        var ids = document.getElementsByName("checkval");
        var s = [];
        if(document.myForm.checkall.checked) {
            for(var i=0; i<ids.length; i++) {
                ids[i].checked="checked";
                s[s.length]=ids[i].value;
            }
        } else {
            for(var i=0; i<ids.length; i++) {
                ids[i].checked="";
            }
        }
        $("#msgId").val(s.join(","));
    }
</script>
</body>
</html>