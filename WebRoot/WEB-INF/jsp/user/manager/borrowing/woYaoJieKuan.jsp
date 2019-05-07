<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var basePath = "${pageContext.request.contextPath}";
</script>
<link href="${pageContext.request.contextPath}/resources/resource/Css/borrowing.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/Js/jiekuan/jiekuan.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/Js/jiekuan/jquery.validate.js"></script>
<script>
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
</script>
<style>
    .high {
        color: red;
    }

    .onError {
        color: red;
    }

    .onSuccess {
        color: green;
    }
</style>
<!-- layout start -->
<div class="bor_l bor_r bor_b " style="font-family: 微软雅黑, arial,sans-serif;font-size: 14px">
    <div>
        <!-- 头部-->
        <include file="Index/header"/>
        <!--左侧-->
        <include file="Message/mes"/>
        <!--left nav end-->
        <div class="fl perCerterR  bor_r bor_l">
            <div class="fl pad_t30 pad_r30 pad_b40 pad_l30 wid_w900 min_height clearfix">
                <div class="loadDiv fc_9 clearfix">
                    <i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要借款</span>
                </div>
                <div id="finishBaseInfo">
                    <form action="${pageContext.request.contextPath}/user/loan/insertloanapp.action" method="post"
                          id="defaultForm" class="form-horizontal">
                        <table>
                            <tr>
                                <td class="duiqi"><label class="albel">标题名称：</label></td>
                                <td><input type="text" name="loanname" id="Loanname" class="required"/></td>
                            </tr>
                            <tr>
                                <td class="duiqi"><label class="albel">借款期限：</label></td>
                                <td>
                                    <input type="text" name="appday" style="width: 100px;" id="Appday"
                                           class="required"/>
                                    <select name="unit" class="albel" style="width: 100px;">
                                        <option value="">--请选择--</option>
                                        <option value="天">天</option>
                                        <option value="月">月</option>
                                        <option value="年">年</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="duiqi">
                                    <label class="albel">借款类型：</label>
                                </td>
                                <td>
                                    <select name="loantype" class="albel">
                                        <option value="">请选择</option>
                                        <c:if test="${!empty objectQuotes}">
                                            <c:forEach items="${objectQuotes}" var="quota">
                                                <option value="${quota.serialno}">${quota.objectname}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </td>
                            </tr>


                            <%-- <tr>
                                 <td class="duiqi">
                                     <label class="control-label col-sm-2 albel">是否约标：</label>
                                 </td>
                                 <td>
                                     <select name="isappointtender" class="form-control albel">
                                         <option value="">请选择</option>
                                         <option value="1">是</option>
                                         <option value="0">否</option>
                                     </select>
                                 </td>
                             </tr>--%>

                            <%-- <tr>
                                 <td class="duiqi">
                                     <label class="control-label col-sm-2 albel">是否拆标：</label>
                                 </td>
                                 <td>
                                     <select name="ismulttender" id="IsMultTender" class="form-control albel">
                                         <option value="0">请选择</option>
                                         <option value="1">是</option>
                                         <option value="0">否</option>
                                     </select>
                                 </td>
                             </tr>

                             <tr id="chabiao">
                                 <td class="duiqi">
                                     <label class="albel">拆标数：</label>
                                 </td>
                                 <td>
                                     <input type="text" name="splitnum" class="albel" id="Splitnum"/>
                                 </td>
                             </tr>--%>

                            <tr>
                                <td class="duiqi">
                                    <label class="control-label col-sm-2 albel">借款金额：</label>
                                </td>
                                <td>
                                    <input type="text" name="loanamount" class="albel required" id="Loanamount"
                                           onkeyup="clearNoNum(this)">
                                </td>
                            </tr>
                            <tr>
                                <td class="duiqi">
                                    <label class="albel">还款方式：</label>
                                </td>
                                <td>
                                    <select name="repaymenttype" class="albel">
                                        <option selected="selected" value="">--请选择--</option>
                                        <option value="1">一次还本付息</option>
                                        <option value="2">等额本金</option>
                                        <option value="3">等额本息</option>
                                        <option value="4">按期付息到期还本</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="duiqi">
                                    <label class="albel">用途描述：</label>
                                </td>
                                <td>
                                    <textarea class="albel" rows="3" name="loanpurposedesc"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="duiqi">
                                </td>
                                <td>
                                    <button type="sumit" class="btn" id="send">添加</button>
                                    <button type="reset" class="redbtn" style="margin-left: 80px;">重置</button>
                                </td>
                            </tr>
                        </table>
                        <input type="hidden" name="baseid" value="${user.id}">
                        <input type="hidden" name="apptype" value="1"/><%--申请方式(1.自申请2.代申请3.接口申请)--%>
                        <input type="hidden" name="isappointtender" value="0"/> <%--是否约标：默认否--%>
                        <input type="hidden" name="ismulttender" value="0"/><%--是否拆标：默认否--%>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>