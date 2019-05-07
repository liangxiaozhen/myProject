<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>干将系统全局设置更新</title>
    <%@include file="/WEB-INF/jsp/common/public.jsp" %>
    <style type="text/css">
        tr {
            display: block; /*将tr设置为块体元素*/
            margin: 5px 0; /*设置tr间距为2px*/
        }

        textarea {
            resize: none;
            width: 280px;
            height: 150px;
        }
    </style>

    <script type="text/javascript">

        //修改
        function gj_role_save(obj) {
            var id = $(obj).data("opid");
            var websitename = $("#websitename").val();
            var title = $("#title").val();
            var keyWorld = $("#keyWorld").val();
            var description = $("#description").val();
            var strStatus = $("#strStatus").val();
            var GJIDCode = $("#GJIDCode").val();
            var presetStr = $("#presetStr").val();
            var newerBidRule = $("#newerBidRule").val();

            var annualRate = $("#annualRate").val().trim();
            if (!annualRate.isEmpty()) {
                var patrn = /^[0-9]{1,3}$/;
                if (!patrn.test(annualRate)) {
                    $("#annualRate").select();
                    loading("年率天数最多3位数字", 4);
                    return false;
                }
            }
            var authtimes = $("#authtimes").val().trim();
            if (!authtimes.isEmpty()) {
                var patrn = /^[0-9]{1,3}$/;
                if (!patrn.test(authtimes)) {
                    $("#authtimes").select();
                    loading("为数字,默认三次", 4);
                    return false;
                }
            }

            var UrgentMethod = $("#UrgentMethod").val().trim();
            if (!UrgentMethod.isEmpty()) {
                var patrn = /^[0-9]{1,2}$/;
                if (!patrn.test(UrgentMethod)) {
                    $("#UrgentMethod").select();
                    loading("为数字", 4);
                    return false;
                }
            }

            var GlobalVerifyCode = $("#GlobalVerifyCode").val().trim();
            if (!GlobalVerifyCode.isEmpty()) {
                var patrn = /^[0-9]{1,2}$/;
                if (!patrn.test(GlobalVerifyCode)) {
                    $("#GlobalVerifyCode").select();
                    loading("为数字", 4);
                    return false;
                }
            }

            var UpGrade = $("#UpGrade").val().trim();
            if (!UpGrade.isEmpty()) {
                var patrn = /^[0-9]{1,2}$/;
                if (!patrn.test(UpGrade)) {
                    $("#UpGrade").select();
                    loading("为数字", 4);
                    return false
                }
            }

            var autorptimesltd = $("#autorptimesltd").val().trim();
            if (!autorptimesltd.isEmpty()) {
                var patrn = /^[0-9]*$/;
                if (!patrn.test(autorptimesltd)) {
                    $("#autorptimesltd").select();
                    loading("为数字", 4);
                    return false
                }
            }

            var autorpstinvl = $("#autorpstinvl").val().trim();
            if (!autorpstinvl.isEmpty()) {
                var patrn = /^[0-9]*$/;
                if (!patrn.test(autorpstinvl)) {
                    $("#autorpstinvl").select();
                    loading("为数字", 4);
                    return false
                }
            }
            var pREAccount = $("#pREAccount").val();//红包账户
            if (!pREAccount.isEmpty()) {
                var patrn = /^[1-9]$/;
                if (!patrn.test(pREAccount)) {
                    $("#pREAccount").select();
                    loading("为数字", 4);
                    return false;
                }
            }
            var pFeeAccount = $("#pFeeAccount").val();//手续费账户
            if (!pFeeAccount.isEmpty()) {
                var patrn = /^[1-9]$/;
                if (!patrn.test(pFeeAccount)) {
                    $("#pFeeAccount").select();
                    loading("为数字", 4);
                    return false;
                }
            }

            if (newerBidRule == 1) {

                var newerBidCount = $("#newerBidCount").val();//新手投标次数限制
                if (!newerBidCount.isEmpty()) {
                    var patrn = /^\d{0,2}$/;
                    if (!patrn.test(newerBidCount)) {
                        $("#newerBidCount").select();
                        loading("1-2位数字", 4);
                        return false;
                    }
                }
            }
            if (newerBidRule == 2) {

                var newerBidAmount = $("#newerBidAmount").val();//新手累投金额限制
                if (!newerBidAmount.isEmpty()) {
                    var patrn = /^[0-9]+([.]{1}[0-9]{1,2})?$/;
                    if (!patrn.test(newerBidAmount)) {
                        $("#newerBidAmount").select();
                        loading("为数字", 4);
                        return false;
                    }
                }
            }

            var newerBidDayLimit = $("#newerBidDayLimit").val();//新手投标注册时间天数限制
            if (!newerBidDayLimit.isEmpty()) {
                var patrn = /^[1-9][0-9]{1,4}$/;
                if (!patrn.test(newerBidDayLimit)) {
                    $("#newerBidDayLimit").select();
                    loading("1-5位数字", 4);
                    return false;
                }
            }

            var failTTime = $("#failTTime").val()//流标缓冲时间
            if (!failTTime.isEmpty()) {
                var patrn = /^[1-9][0-9]{1,4}$/;
                if (!patrn.test(failTTime)) {
                    $("#failTTime").select();
                    loading("请填写1-5位数字", 4);
                    return false;
                }
            } else {
                loading("缓冲时间不能为空", 4);
                return false;

            }
            var action = basePath + "/admin/globalSetting/updata.action";
            $.ajax({
                type: "post",
                url: action,
                data: {
                    "id": id,
                    "websitename": websitename,
                    "title": title,
                    "keyWorld": keyWorld,
                    "description": description,
                    "strStatus": strStatus,
                    "annualRate": annualRate,
                    "authtimes": authtimes,
                    "GJIDCode": GJIDCode,
                    "presetStr": presetStr,
                    "UrgentMethod": UrgentMethod,
                    "GlobalVerifyCode": GlobalVerifyCode,
                    "UpGrade": UpGrade,
                    "autorpstinvl": autorpstinvl,
                    "autorptimesltd": autorptimesltd,
                    "pREAccount": pREAccount,
                    "pFeeAccount": pFeeAccount,
                    "newerBidCount": newerBidCount,
                    "newerBidAmount": newerBidAmount,
                    "newerBidDayLimit": newerBidDayLimit,
                    "newerBidRule": newerBidRule,
                    "failTTime": failTTime
                },
                success: function (data) {
                    var obj = $.parseJSON(data);
                    if (obj.result == "success") {
                        alert("修改成功");
                        window.location.href = basePath
                            + "/admin/globalSetting/sove.action";
                    } else if (obj.result == "fiel") {
                        $("#inputForm").select();
                        loading("修改失败", 4);
                    }
                }
            });
        }


        function fd3(Names) {
            $("#newerBidRule").val(Names);
            Names = "mun_x" + Names;
            var Nnews;
            for (var i = 1; i < 3; i++) {
                var tempname = "mun_x" + i;
                var NewsHot = "x" + i;   //  “X”是ID名称，比如：ID命名为“case1”，这里的“X”即为“case”
                if (Names == tempname) {
                    Nnews = document.getElementById(NewsHot);
                    Nnews.style.display = '';
                } else {
                    Nnews = document.getElementById(NewsHot);
                    Nnews.style.display = 'none';
                }

            }
        }
        $(function () {
            var newerBid =${global.newerBidRule};
            if (newerBid == 2) {

                $("#x2").css("display", "block");
            }
            if (newerBid == 1) {

                $("#x1").css("display", "block");
            }
        })


    </script>
</head>
<body>

<c:set var="id" value="${global.id}"/>
<form id="inputForm" class="form-horizontal">
    <input type="hidden" value="${global.newerBidRule}" id="newerBidRule">

    <div>
        <table>
            <tr>
                <td style="padding-right: 48px">网站名称:</td>
                <td><input type="text" name="websitename"
                           value="${global.websitename }" id="websitename"/></td>
            </tr>

            <tr>
                <td style="padding-right: 48px">网站抬头:</td>
                <td><input type="text" name="title" value="${global.title }"
                           id="title"/></td>
            </tr>

            <tr>
                <td style="padding-right: 35px">网站关键词:</td>
                <td><input type="text" name="keyWorld"
                           value="${global.keyworld }" id="keyWorld"/></td>
            </tr>


            <tr>
                <td style="padding-right: 70px">开&nbsp;关:</td>
                <td><input type="text" name="strStatus"
                           value="${global.strstatus }" id="strStatus"/></td>
            </tr>

            <tr>
                <td style="padding-right: 48px">年率天数:</td>
                <td><input type="text" name="annualRate"
                           value="${global.annualrate }"
                           id="annualRate"/></td>
            </tr>

            <tr>
                <td style="padding-right: 48px">认证次数:</td>
                <td><input type="text" name="authtimes"
                           value="${global.authtimes }" id="authtimes"/></td>
                <td><p>&nbsp;默认3次</p></td>
            </tr>

            <tr>
                <td style="padding-right: 25px">业务编号前缀:</td>
                <td><input type="text" name="presetStr" id="presetStr"
                           value="${global.presetstr} "/></td>
            </tr>
            <tr>
                <td>应急改密方式设置:</td>
                <td><input type="text" name="UrgentMethod" id="UrgentMethod"
                           value="${global.urgentmethod }"/></td>
            </tr>
            <tr>
                <td style="padding-right: 12px">全局验证验证码:</td>
                <td><input type="text" name="GlobalVerifyCode"
                           id="GlobalVerifyCode" value="${global.globalverifycode }"/></td>
                <td><p>&nbsp;1开启全局验证，2关闭全局验证码，3开启正常验证码</p></td>
            </tr>
            <tr>
                <td style="padding-right: 15px">会员升级方向：</td>
                <td><input type="text" name="UpGrade" id="UpGrade"
                           value="${global.upgrade} "/></td>
                <td><p>&nbsp;1.单向 ; 2.双向 </p></td>
            </tr>
            <tr>
                <td style="padding-right: 15px">系统自动提交还款次数限制：</td>
                <td><input type="text" name="autorptimesltd"
                           id="autorptimesltd" value="${global.autorptimesltd} "/></td>
            </tr>
            <tr>
                <td style="padding-right: 15px">系统自动提交还款提交间隔（单位 分钟）：</td>
                <td><input type="text" name="autorpstinvl" id="autorpstinvl"
                           value="${global.autorpstinvl}"/></td>
            </tr>

            <tr>
                <td style="padding-right: 15px">徽商红包账户：</td>
                <td><input type="text" name="pREAccount" id="pREAccount"
                           value="${global.pREAccount}"/></td>
                <td><p>&nbsp;红包账户 </p></td>
            </tr>

            <tr>
                <td style="padding-right: 5px">徽商手续费账户：</td>
                <td><input type="text" name="pFeeAccount" id="pFeeAccount"
                           value="${global.pFeeAccount}"/></td>
                <td><p>&nbsp;手续费账户 </p></td>
            </tr>

            <tr>
                <td style="padding-right: 15px">新手投标定义：</td>
                <td><select onchange="fd3(this.value)">
                    <c:if test="${global.newerBidRule==1}">
                        <option value="1" selected="selected">投标次数+注册时间天数</option>
                        <option value="2">累投金额+注册时间天数</option>

                    </c:if>
                    <c:if test="${global.newerBidRule==2}">
                        <option value="1">投标次数+注册时间天数</option>
                        <option value="2" selected="selected">累投金额+注册时间天数</option>

                    </c:if>
                </select>
                </td>
            </tr>
            <tr id="x2" style="display: none">
                <td style="padding-left: 25px;padding-right: 22px">累投金额：</td>
                <td><input type="text" name="newerBidAmount" id="newerBidAmount"
                           value="${global.newerBidAmount}"/>
                </td>
                <td><p>&nbsp;新手累投金额限制 </p></td>
            </tr>
            <tr id="x1" style="display: none">

                <td style="padding-left: 25px;padding-right: 22px">投标次数：</td>
                <td><input type="text" name="newerBidCount" id="newerBidCount"
                           value="${global.newerBidCount}"/></td>
                <td><p>&nbsp;次以下</p></td>
            </tr>


            <tr>
                <td style="padding-left: 25px;padding-right: 22px">注册天数：</td>
                <td><input type="text" name="newerBidDayLimit" id="newerBidDayLimit"
                           value="${global.newerBidDayLimit}"/></td>
                <td><p>&nbsp;天以内 </p></td>
            </tr>
            <tr>
                <td style="padding-right: 15px">流标缓冲时间：</td>
                <td><input type="text" name="failTTime" id="failTTime"
                           value="${global.failTTime}"/></td>
                <td><p>&nbsp;单位：分钟 自动和手动都需要判断该时间 </p></td>
            </tr>


            <tr>
                <td style="padding-right: 35px">干将识别码:</td>
                <td>
                    <p>
								<textarea rows="5" cols="25" name="GJIDCode" id="GJIDCode"
                                          style="height: 40px">${global.gjidcode }</textarea>
                    </P>
                </td>
            </tr>
            <tr>
                <td style="padding-right: 48px">网站描述:</td>
                <td>
                    <p>
								<textarea rows="5" cols="25" name="description" id="description"
                                          style="height: 40px">${global.description }</textarea>
                    </P>
                </td>
            </tr>

            <tr>
                <td><input type="button" id="btnSubmit"
                           class="btn btn-primary" onclick="gj_role_save(this)" value="提交"
                           data-opid="${global.id}"/></td>
            </tr>
        </table>
    </div>
</form>


</body>
</html>