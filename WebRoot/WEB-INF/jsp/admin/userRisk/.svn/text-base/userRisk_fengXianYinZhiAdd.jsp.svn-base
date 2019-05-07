<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增页面</title>
    <script type="text/javascript">
        //必须先选择名单才能进行下面的操作
        $(function () {
            $("#username-text").hide();
            $("#ip-text").hide();
            $("#mobile-text").hide();
            $("#email-text").hide();
            $("#cookie-text").hide();
            $("#mac-text").hide();
            $("#remark-text").hide();
        });

        function changeFengXianLeiXing(e) {
            if ($(e).val() != '') {
                if ($(e).val() == 1 || $(e).val() == 2 || $(e).val() == 4) {
                    $("#username-text").show();
                    $("#ip-text").show();
                    $("#mobile-text").show();
                    $("#email-text").show();
                    $("#cookie-text").show();
                    $("#mac-text").show();
                    $("#remark-text").show();
                    $("#ip-text-br").show()
                } else if ($(e).val() == 3) {
                    $("#username-text").show();
                    $("#mobile-text").show();
                    $("#email-text").show();
                    $("#remark-text").show();
                    $("#ip-text").hide();
                    $("#ip-text-br").hide()
                    $("#cookie-text").hide();
                    $("#mac-text").hide();
                } else {
                    $("#username-text").hide();
                    $("#ip-text").hide();
                    $("#mobile-text").hide();
                    $("#email-text").hide();
                    $("#cookie-text").hide();
                    $("#mac-text").hide();
                    $("#remark-text").hide();
                    $("#ip-text-br").hide()
                }
            }
        }
        /*根据用户名去用户表找用户*/
        function findUserByUsername(e) {
            alert(e.value);
            var userName = this.value;
            $.ajax({
                "type": "post",
                "url": "${pageContext.request.contextPath}/",
                "data": {"userName": userName},
                "success": function (data) {
                    alert(data);
                }
            });
        }
    </script>
    <link rel="stylesheet" href="style.css">

</head>
<body>
<form id="insert-form">
    <div class="row" style="line-height: 0px;">
        <div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
            <font color="red"><b>添加风险因子 &nbsp;&nbsp;:</b></font>
        </div>
    </div>
    <br>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label> 风险类型 &nbsp;： </label>&nbsp;&nbsp; <select
                name="type" id="insert-nametype-select" onchange="changeFengXianLeiXing(this)">
            <option value="">---请选择---</option>
            <option value="4">风险名单</option>
            <option value="3">应急改密名单</option>
            <option value="2">黑名单</option>
            <option value="1">白名单</option>
        </select> &nbsp;&nbsp; <label id="insert-name-lb"></label>
        </div>
    </div>
    <br>

    <div class="row" style="line-height: 0px;" id="username-text">
        <div class="col-md-7 col-md-offset-3">
            <label> 用 户 名 &nbsp;： </label>&nbsp;&nbsp;
            <input type="text" placeholder="---请输入用户名---" style="width: 120px; text-align: center; line-height: 18px;"
                   name="username" id="insert-username-text" onblur="findUserByUsername(this)"/>&nbsp;&nbsp;<span
                style="color: red;"> <label id="insert-username-lb"></label></span>
        </div>
    </div>
    <br>

    <div class="row" style="line-height: 0px;" id="ip-text">
        <div class="col-md-7 col-md-offset-3">
            <label> IP &nbsp;： </label>&nbsp;&nbsp;<input type="text"
                                                          placeholder="---请输入ip---"
                                                          style="width: 120px; text-align: center; line-height: 18px;"
                                                          name="ip" id="insert-ip-text">&nbsp;&nbsp;<span
                style="color: red;"> <label id="insert-ip-lb"></label></span>
        </div>
    </div>
    <br id="ip-text-br">

    <div class="row" style="line-height: 0px;" id="mobile-text">
        <div class="col-md-7 col-md-offset-3">
            <label> mobile &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" placeholder="---请输入mobile---"
                style="width: 120px; text-align: center; line-height: 18px;"
                name="mobile" id="insert-mobile-text">&nbsp;&nbsp;<span
                style="color: red;"> <label id="insert-mobile-lb"></label></span>
        </div>
    </div>
    <br>

    <div class="row" style="line-height: 0px;" id="email-text">
        <div class="col-md-7 col-md-offset-3">
            <label> 邮箱 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" placeholder="---请输入email---"
                style="width: 120px; text-align: center; line-height: 18px;"
                name="email" id="insert-email-text">&nbsp;&nbsp;<span
                style="color: red;"> <label id="insert-email-lb"></label></span>
        </div>
    </div>
    <br>

    <div class="row" style="line-height: 0px;" id="cookie-text">
        <div class="col-md-7 col-md-offset-3">
            <label> cookie &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" placeholder="---请输入cookie---"
                style="width: 120px; text-align: center; line-height: 18px;"
                name="cookie" id="insert-cookie-text">&nbsp;&nbsp;<span
                style="color: red;"> <label id="insert-cookie-lb"></label></span>
        </div>
    </div>
    <br>

    <div class="row" style="line-height: 0px;" id="mac-text">
        <div class="col-md-7 col-md-offset-3">
            <label> 手机编号 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" placeholder="---请输入mac---"
                style="width: 120px; text-align: center; line-height: 18px;"
                name="mac" id="insert-mac-text">&nbsp;&nbsp;<span
                style="color: red;"> <label id="insert-mac-lb"></label></span>
        </div>
    </div>
    <br>

    <div id="remark-text">
        <div class="row" style="line-height: 0px;">
            <div class="col-md-8 col-md-offset-2">
                <font color="red"><b>备注 &nbsp;&nbsp;:</b></font>
            </div>
        </div>
        <div class="row" style="line-height: 0px; margin-top: 12px;">
            <div class="col-md-6 col-md-offset-3">
					<textarea placeholder="请输入备注 :" class="form-control" name="remark"
                              id="insert-remark-text"></textarea>
            </div>
        </div>
    </div>
</form>
</body>
</html>