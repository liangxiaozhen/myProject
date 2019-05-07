<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编辑页面</title>
    <script type="text/javascript">
        $(function () {
            $("#update-nametype-select").val("${userRisk.type}");
            $("#update-nametype-select").change(function () {
                $("#update-name-lb").html("");
            });


    </script>
</head>
<body>
<form id="update-form">
    <div class="row" style="line-height: 0px;">
        <div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
            <font color="red"><b>风控编辑 &nbsp;&nbsp;:</b></font> <input
                type="hidden" value="${userRisk.id}" id="update-rname-id">
            <input type="hidden" value="${userRisk.baseid}"
                   id="update-rname-baseid">
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> 用 户 名 &nbsp;： </label>&nbsp;&nbsp;<input
                type="text" name="username" id="update-username"
                placeholder="---请输入用户名---" value="${userRisk.username}"> <label
                style="color: red" id="username-lb"></label>
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> IP &nbsp;： </label>&nbsp;&nbsp;<input type="text"
                                                                     name="ip" placeholder="---请输入IP---"
                                                                     id="update-rname-ip"
                                                                     value="${userRisk.ip}">
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> cookie &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" name="cookie" placeholder="---请输入cookie---"
                id="update-rname-cookie" value="${userRisk.cookie}">
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> 手机编号 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" name="mac" placeholder="---请输入mac---"
                id="update-rname-mac" value="${userRisk.mac}">
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> mobile &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" name="mobile" placeholder="---请输入mobile---"
                id="update-rname-mobile" value="${userRisk.mobile}">
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> 邮箱 &nbsp;： </label>&nbsp;&nbsp;&nbsp;<input
                type="text" name="email" placeholder="---请输入email---"
                id="update-rname-email" value="${userRisk.email}">
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px;">
        <div class="col-md-7 col-md-offset-3">
            <label for="male"> 风险类型 &nbsp;： </label> &nbsp;&nbsp; <select
                name="type" id="update-nametype-select">
            <option value="4">风险名单</option>
            <option value="3">应急改密名单</option>
            <option value="2">黑名单</option>
            <option value="1">白名单</option>
        </select> &nbsp;&nbsp; <label id="update-name-lb"></label>
        </div>
    </div>
    <hr>

    <div class="row" style="line-height: 0px">
        <div class="col-md-7 col-md-offset-1">
            <font color="red"><b>备注 &nbsp;&nbsp;:</b></font>
        </div>
    </div>
    <div class="row" style="line-height: 0px; margin-top: 12px;">
        <div class="col-md-6 col-md-offset-3">
				<textarea class="form-control" name="remark" placeholder="请输入备注 :"
                          id="update-remark-text">${userRisk.remark}</textarea>
        </div>
    </div>
</form>
</body>
</html>
