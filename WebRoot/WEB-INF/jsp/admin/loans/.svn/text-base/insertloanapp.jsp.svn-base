<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <link href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .aos {
            width: 190px;
            height: 32px;
        }
    </style>
</head>
<body style="font-family:'微软雅黑'">
<div class="container" id="">
    <div style="text-align: center;"><h3>￥借款申请</h3></div>
    <div class="col-md-12 column">
        <form class="form-horizontal" id="form" action="<%=basePath%>/admin/loan/insertloanappdt.action" method="post">
            <div class="form-group  has-feedback">
                <font class="control-label col-sm-2">借款人：</font>
                <div class="col-sm-4">
                    <input id="loanUserName"
                           name="loanUserName" value="" class="form-control" type="text"
                           style="width: 50%;display: inline-block;" onblur="handle(this);">
                </div>

                <div class="col-sm-6">
                    <font class="" style="display: inline-block;">常用借款人：</font>
                    <div class="" style="display: inline-block;">
                        <select class="aos" style="border-radius:4px;" id="sbt"
                                onchange="loanManChange(this);">
                            <c:if test="${loginnames !=null}">
                                <option value="" selected="selected">--请选择--</option>
                                <c:forEach items="${loginnames}" var="v">
                                    <option value="${v}">${v}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <%--是否在保存时把该用户加入到常用借款人列表中--%>
                        <input type="hidden" name="isoften" id="isoften" value="0"/>
                    </div>
                </div>
            </div>

            <div class="form-group  has-feedback">
                <font class="control-label col-sm-2">借款标题：</font>
                <div class="col-sm-8">
                    <input class="form-control" name="loanname" type="text">
                </div>
            </div>


            <div class="form-group  has-feedback" style="display: block;">
                <font class="control-label col-sm-2" style="line-height:10px;">借款期限：</font>
                <div class="col-sm-9">
                    <input name="appday"
                           class="form-control" type="text" style="width: 100px;display: inline-block;">
                    <select class="aos" style="border-radius:4px;width: 85px;text-align: center;"
                            name="unit">
                        <option value="">--请选择--</option>
                        <option value="天">天</option>
                        <option value="月">月</option>
                        <option value="年">年</option>
                    </select>
                </div>
            </div>

            <div class="form-group  has-feedback" style="display: block;">
                <font class="control-label col-sm-2" style="line-height:10px;">设置标类型：</font>
                <div class="col-sm-9">
                    <select name="loantype" class="aos" style="border-radius:4px;">
                        <option value="" selected="selected">--请选择--</option>
                        <c:if test="${!empty objectQuotes}">
                            <c:forEach items="${objectQuotes}" var="quota">
                                <option value="${quota.serialno}">${quota.objectname}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
            </div>

            <%-- <div class="form-group  has-feedback" style="display: block;">
                 <font class="control-label col-sm-2" style="line-height:10px;">分期借款：</font>
                 <div class="col-sm-9">
                     <select class="aos fqjk" style="border-radius:4px;" name="ismulttender">
                         <option value="0" selected="selected">--请选择--</option>

                         <option value="1">是</option>

                         <option value="0">否</option>

                     </select>
                     <span class="fqjkk" style="color: red;">

                                 </span>

                 </div>
             </div>--%>

            <%-- <div class="form-group has-success has-feedback" id="splitNum">
                 <label class="control-label col-sm-2">拆标数</label>
                 <div class="col-sm-3">
                     <input type="text" name="splitnum" class="form-control"/>
                 </div>
             </div>--%>


            <div class="form-group  has-feedback" style="display: block;">
                <font class="control-label col-sm-2" style="line-height:10px;">借款金额：</font>
                <div class="col-sm-9">
                    <input value="" name="loanamount"
                           onkeyup="clearNoNum(this)"
                           class="form-control" type="text" style="width: 188px;display: inline-block;">
                    <button type="button" class="btn" style="margin-right:15px;">元</button>
                </div>
            </div>

            <div class="form-group  has-feedback" style="display: block;">
                <font class="control-label col-sm-2" style="line-height:10px;">计息方式：</font>
                <div class="col-sm-9">
                    <select name="repaymenttype" class="aos" style="border-radius:4px;">
                        <option value="" selected="selected">--请选择--</option>
                        <option value="1">一次还本付息</option>
                        <option value="2">等额本金</option>
                        <option value="3">等额本息</option>
                        <option value="4">按期付息到期还本</option>
                    </select>
                </div>
            </div>


            <div class="form-group  has-feedback">
                <font class="control-label col-sm-2">用途描叙：</font>
                <div class="col-sm-8">
                    <textarea type="text" id="remark" name="loanpurposedesc" class="form-control" value=""></textarea>
                </div>
            </div>

            <div class="form-group has-success has-feedback">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-4">
                    <button type="button" id="returnBack" class="btn btn-default" onclick="severCheck();">保存</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" onclick="clearInput();" class="btn btn-default">重置</button>
                </div>
            </div>

            <input type="hidden" name="isappointtender" value="0"/><%--是否约标：默认否--%>
            <input name="ismulttender" type="hidden" value="0"/><%--分期借款:默认为否--%>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>

<script type="text/javascript">
    var basePath = "${basePath}"
</script>


<script type="text/javascript">

    $().ready(function () {
        /*jQuery.validator.addMethod("abc", function () {
         var flag = true;
         if( $("#loanUserName").val()=="xxyy"){
         flag=false;
         }
         return flag;
         }, "xxxxxxx");*/
    });
    //客户端验证
    function check() {
        return $("#form").validate({
            rules: {
                loanUserName: {
                    required: true,
                },
                loanname: {
                    required: true,
                    maxlength: 60,
                },
                appday: {
                    required: true,
                    digits: true
                },
                unit: {
                    required: true,
                },
                loantype: {
                    required: true,
                },
                loanamount: {
                    required: true,
                },
                repaymenttype: {
                    required: true,
                },
                loanpurposedesc: {
                    required: true,
                    maxlength: 600,
                }


            },
            messages: {
                loanUserName: {
                    required: "必填",
                },
                loanname: {
                    required: "必填",
                    maxlength: "不能超六十个字",
                },
                appday: {
                    required: "必填",
                    digits: "必须为整数",
                },
                unit: {
                    required: "必填",
                },
                loantype: {
                    required: "必填",
                },
                loanamount: {
                    required: "必填",
                },
                repaymenttype: {
                    requried: "必填"
                },
                loanpurposedesc: {
                    required: "必填",
                    maxlength: "不能超600字",
                }

            }
        }).form();
    }
    //服务端验证
    function severCheck() {
        if (check()) {
            var loanUserName = $("#loanUserName").val();
            $("#sbt").each(function () {
                var val = $(this).val();
                if(val!=null){
                    var loginName = val.substring(0, val.indexOf("-"));
                    if (loanUserName != loginName) {
                        var flag = window.confirm("是否把该用户加入到常用借款人列表中");
                        if (flag == true) {
                            $("#isoften").val("1");
                        } else {
                            $("#isoften").val("0");
                        }
                    } else {
                        $("#isoften").val("0");
                    }
                }else {
                    var flag = window.confirm("是否把该用户加入到常用借款人列表中");
                    if (flag == true) {
                        $("#isoften").val("1");
                    } else {
                        $("#isoften").val("0");
                    }
                }

            })
            $.ajax({
                "type": "POST",
                "url": basePath + "/admin/loan/insertloanappdt.action",
                "data": $("#form").serialize(),
                "success": function (backData) {
                    var obj = $.parseJSON(backData);
                    if (obj.result == "不存在该用户名") {
                        alert(obj.result);
                        $("#businessName").val("");
                        $("#businessName").focus();
                    }
                    if (obj.result == "该用户还没有开通托管账户") {
                        alert(obj.result);
                        $("#businessName").val("");
                        $("#businessName").focus();
                    }
                    if (obj.result == "fail") {
                        alert("失败");
                    }
                    if (obj.result == "success") {
                        alert("成功！")
                        window.location.href = basePath + "/admin/loan/selectloanappAudit.action";
                    }
                }
            });
        }
    }
    //借款人onblur事件
    function handle(t) {
        $(t).val($.trim(t.value));
    }

    //常用借款人change事件
    function loanManChange(t) {
        var loginname = $(t).val();
        $("#loanUserName").val(loginname.substring(0, loginname.indexOf("-")));
    }

    //借款金额onkeyup事件
    function clearNoNum(obj) {
        //修复第一个字符是小数点 的情况.
        if (obj.value != '' && obj.value.substr(0, 1) == '.') {
            obj.value = "";
        }
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            if (obj.value.substr(0, 1) == '0' && obj.value.length == 2) {
                obj.value = obj.value.substr(1, obj.value.length);
            }
        }
    }
    //重置事件
    function clearInput() {
        $("#form")[0].reset()
    }
</script>
</body>
</html>

