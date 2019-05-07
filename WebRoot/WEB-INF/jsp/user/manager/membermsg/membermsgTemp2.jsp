<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<script>
    $(document).ready(function(){
        var temp='${userMsgSetList}';
         if(temp=="[]"){
             for(var i=0;i<7;i++){
                 $("input[name='userMsgSetList["+i+"].intmsg']").val(1);
             }
          }
    })
</script>
<body>
<!-- 我是投资人 start -->
<form action="${pageContext.request.contextPath}/user/saveUserMsgSet.action" id="msgSetForm" method="post" class="nwd-formUi">
    <c:forEach items="${userMsgSetList}" var="user" varStatus="status">
        <input type="hidden" name="userMsgSetList[${status.index}].id" value="${user.id}" />
    </c:forEach>
    <input type="hidden" name="baseid" value="${baseId}" />
    <input type="hidden" name="userMsgSetList[0].msgtype" value="1" />
    <input type="hidden" name="userMsgSetList[1].msgtype" value="1" />
    <input type="hidden" name="userMsgSetList[2].msgtype" value="1" />
    <input type="hidden" name="userMsgSetList[3].msgtype" value="1" />
    <input type="hidden" name="userMsgSetList[4].msgtype" value="2" />
    <input type="hidden" name="userMsgSetList[5].msgtype" value="2" />
    <input type="hidden" name="userMsgSetList[6].msgtype" value="2" />
    <input type="hidden" name="userMsgSetList[0].item" value="流标" />
    <input type="hidden" name="userMsgSetList[1].item" value="退标" />
    <input type="hidden" name="userMsgSetList[2].item" value="收到回款" />
    <input type="hidden" name="userMsgSetList[3].item" value="债权转让成功" />
    <input type="hidden" name="userMsgSetList[4].item" value="满标" />
    <input type="hidden" name="userMsgSetList[5].item" value="借款标发生债权转让" />
    <input type="hidden" name="userMsgSetList[6].item" value="还款成功" />
    <table cellspacing="0" cellpadding="0" class="table fc_9 bor_t mar_t5">
        <tbody>
        <tr>
            <th class="fc_6" width="15%">我是投资人</th>
            <th class="fc_6" width="15%">站内信</th>
            <th class="fc_6" width="15%">短信</th>
            <th class="fc_6" width="15%">电子邮件</th>
            <th class="fc_6" width="15%">手机APP</th>
            <th class="fc_6" width="15%">微信</th>
        </tr>
        <tr>
            <td>流标</td>
            <td><input type="checkbox" value="${userMsgSetList[0].intmsg}" name="userMsgSetList[0].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[0].sms}" name="userMsgSetList[0].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[0].email}" name="userMsgSetList[0].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[0].app}" name="userMsgSetList[0].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[0].wechat}" name="userMsgSetList[0].wechat" /></td>
        </tr>
        <tr>
            <td>退标</td>
            <td><input type="checkbox" value="${userMsgSetList[1].intmsg}" name="userMsgSetList[1].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[1].sms}" name="userMsgSetList[1].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[1].email}" name="userMsgSetList[1].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[1].app}" name="userMsgSetList[1].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[1].wechat}" name="userMsgSetList[1].wechat" /></td>
        </tr>
        <tr>
            <td>收到回款</td>
            <td><input type="checkbox" value="${userMsgSetList[2].intmsg}" name="userMsgSetList[2].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[2].sms}" name="userMsgSetList[2].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[2].email}" name="userMsgSetList[2].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[2].app}" name="userMsgSetList[2].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[2].wechat}" name="userMsgSetList[2].wechat" /></td>
        </tr>
        <tr>
            <td>债权转让成功</td>
            <td><input type="checkbox" value="${userMsgSetList[3].intmsg}" name="userMsgSetList[3].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[3].sms}" name="userMsgSetList[3].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[3].email}" name="userMsgSetList[3].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[3].app}" name="userMsgSetList[3].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[3].wechat}" name="userMsgSetList[3].wechat" /></td>
        </tr>
        </tbody>
    </table>
    <!-- 我是投资人 end -->
    <!-- 我是借款人 start -->
    <table cellspacing="0" cellpadding="0" class="table fc_9 bor_t investorList">
        <tbody>
        <tr>
            <th class="fc_6" width="15%">我是借款人</th>
            <th class="" width="15%"></th>
            <th class="" width="15%"></th>
            <th class="" width="15%"></th>
            <th class="" width="15%"></th>
            <th class="" width="15%"></th>
        </tr>
        <tr>
            <td>满标</td>
            <td><input type="checkbox" value="${userMsgSetList[4].intmsg}" name="userMsgSetList[4].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[4].sms}" name="userMsgSetList[4].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[4].email}" name="userMsgSetList[4].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[4].app}" name="userMsgSetList[4].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[4].wechat}" name="userMsgSetList[4].wechat" /></td>
        </tr>
        <tr>
            <td>借款标发生债权转让</td>
            <td><input type="checkbox" value="${userMsgSetList[5].intmsg}" name="userMsgSetList[5].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[5].sms}" name="userMsgSetList[5].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[5].email}" name="userMsgSetList[5].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[5].app}" name="userMsgSetList[5].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[5].wechat}" name="userMsgSetList[5].wechat" /></td>
        </tr>
        <tr>
            <td>还款成功</td>
            <td><input type="checkbox" value="${userMsgSetList[6].intmsg}" name="userMsgSetList[6].intmsg" /></td>
            <td><input type="checkbox" value="${userMsgSetList[6].sms}" name="userMsgSetList[6].sms" /></td>
            <td><input type="checkbox" value="${userMsgSetList[6].email}" name="userMsgSetList[6].email" /></td>
            <td><input type="checkbox" value="${userMsgSetList[6].app}" name="userMsgSetList[6].app" /></td>
            <td><input type="checkbox" value="${userMsgSetList[6].wechat}" name="userMsgSetList[6].wechat" /></td>
        </tr>
        </tbody>
    </table>
    <!-- 我是借款人 end -->
    <div class="txt_center">
        <div class="txt_center">
            <a class="btn btn_36c btn_size100 mar_t20 " id="save">保存设置</a>
        </div>
    </div>
</form>
</body>
</html>
