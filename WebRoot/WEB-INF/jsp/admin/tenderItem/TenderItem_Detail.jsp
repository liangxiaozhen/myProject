<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <title>TenderItem</title>

    <script type="text/javascript">
        $(function () {
            $("#valuedate").hide();
            var $timode = $("#timode").val();
            if ($timode == 4) {
                $("#valuedate").show();
            }
        });
        function gotoTenderItemList() {
            window.location.href = "${pageContext.request.contextPath }/admin/tenderItem/selectTenderItemByCondition.action";
        }

    </script>

</head>
<body style="font-family:'微软雅黑'; ">
<div class="row text-center" style="line-height: 0px;">
    <font size="4">标基本信息</font>
    <hr/>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">借款申请ID：</font>
    <font class="col-md-4">${tenderItem.loanappid}</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标ID：</font>
    <font class="col-md-4">${tenderItem.id}</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标号：</font>
    <font class="col-md-4">${tenderItem.tno}</font>
    <hr>
</div>


<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标的名称：</font>
    <font class="col-md-4">${tenderItem.tname}</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">建标时间：</font>
    <font class="col-sm-4"><fmt:formatDate value="${tenderItem.addtime}" pattern="yyyy-MM-dd HH:mm"/></font>
    <hr/>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">借款周期：</font>
    <font class="col-md-4">${tenderItem.loantime}${tenderItem.dayormonth}</font>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">起息日：</font>
    <c:if test="${tenderItem.valuerule == 1 }">
        <font class="col-md-4">生成还款日当天</font>
    </c:if>
    <c:if test="${tenderItem.valuerule == 2 }">
        <font class="col-md-4">生成还款日次日</font>
    </c:if>
    <c:if test="${tenderItem.valuerule == 3 }">
        <font class="col-md-4">生成还款日指定时间点前后</font>
    </c:if>
    <hr>
</div>
<c:if test="${tenderItem.valuerule eq 3}">
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">起息日时间点：</font>
        <font class="col-md-4">${tenderItem.valuepoint}</font>
        <hr>
    </div>
</c:if>


<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标的金额：</font>
    <font class="col-md-4">${tenderItem.tamountstr}元</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标收益：</font>
    <font class="col-md-4">${tenderItem.tinterest}%</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">已完成投标金额：</font>
    <font class="col-md-4">${tenderItem.finishtamount}元</font>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标的类型：</font>
    <font class="col-md-4">${tproStr}</font>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">投标期：</font>
    <font class="col-md-8"><fmt:formatDate value="${tenderItem.tbegintime}"
                                           type="both"/>一<fmt:formatDate
            value="${tenderItem.tendtime}" type="both"/></font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">投标人设置：</font>
    <c:if test="${tenderItem.investerrange==1}"><font class="col-md-4">任何人皆可投标</font></c:if>
    <c:if test="${tenderItem.investerrange==2}"><font class="col-md-4">只允许新手投标</font></c:if>
    <c:if test="${tenderItem.investerrange==3}"><font class="col-md-4">按会员等级设置</font></c:if>
    <c:if test="${tenderItem.investerrange==4}"><font class="col-md-4">按定向名单设置</font></c:if>
    <hr>
</div>

<c:if test="${tenderItem.investerrange==3}">
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">允许投标的会员等级：</font>
        <font class="col-md-4">${tenderItem.ugrestrict}</font>
        <hr>
    </div>
</c:if>

<c:if test="${tenderItem.investerrange==4}">
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">允许投标的定向名单：</font>
        <font class="col-md-4">${tenderItem.snlid}-${tenderItem.snlno}</font>
        <hr>
    </div>
</c:if>


<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">首次还款-最后还款日期：</font>
    <font class="col-md-4"><fmt:formatDate value="${tenderItem.retdate}"
                                           type="both"/>一<fmt:formatDate
            value="${tenderItem.lastretdate}" type="both"/></font>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">单笔投资金额范围：</font>
    <font class="col-md-4">${tenderItem.minoncetamount}~${tenderItem.maxoncetamount}元</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">单人投标次数限制：</font>
    <font class="col-md-4">${tenderItem.onettimes}</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否起投金额整数倍：</font>
    <c:if test="${tenderItem.ismultiple eq 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.ismultiple eq 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标状态：</font>
    <font class="col-md-4">${tstatus}</font>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标上架状态：</font>
    <font class="col-md-4">${onanddown}</font>
    <hr>
</div>


<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">代偿人：</font>
    <font class="col-md-4">${tenderItem.compensatoryman}</font>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">投标客户端来源：</font>
    <font class="col-md-4">${tenderItem.crestrict}</font>
    <hr>
</div>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否可以假投标：</font>
    <c:if test="${tenderItem.isfaketender eq 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.isfaketender eq 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否可以自动投标：</font>
    <c:if test="${tenderItem.isautotender eq 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.isautotender eq 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>--%>


<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">投标是否允许撤回：</font>
    <c:if test="${tenderItem.isacancel eq 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.isacancel eq 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>--%>

<%--<c:if test="${tenderItem.isacancel eq 1}">
    <div class="row">
        <font class="col-sm-4 text-right">允许撤回的会员等级：</font>
        <font class="col-md-4">${tenderItem.allowcugrade}</font>
        <hr>
    </div>
</c:if>--%>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">还款审核：</font>
    <c:if test="${tenderItem.repaymenttype == 1}">
        <font class="col-md-4">不审核</font>
    </c:if>
    <c:if test="${tenderItem.repaymenttype == 2}">
        <font class="col-md-4">审核</font>
    </c:if>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">计息方式：</font>
    <c:if test="${tenderItem.repaymentpro eq 1}">
        <font class="col-md-4">一次还本付息</font>
    </c:if>
    <c:if test="${tenderItem.repaymentpro eq 2}">
        <font class="col-md-4">等额本金</font>
    </c:if>
    <c:if test="${tenderItem.repaymentpro eq 3}">
        <font class="col-md-4">等额本息</font>
    </c:if>
    <c:if test="${tenderItem.repaymentpro eq 4}">
        <font class="col-md-4">按期付息到期还本</font>
    </c:if>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">自动还款设置：</font>
    <c:if test="${tenderItem.repaysetmode == 1}">
        <font class="col-md-4">允许</font>
    </c:if>
    <c:if test="${tenderItem.repaysetmode == 0}">
        <font class="col-md-4">不允许</font>
    </c:if>
    <hr>
</div>
<c:if test="${tenderItem.repaysetmode == 1}">
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">还款时间点：</font>
        <font class="col-md-4">${tenderItem.repaytimepoint}</font>
        <hr>
    </div>
</c:if>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">还款人设置：</font>
    <font class="col-md-4">${tenderItem.repayman}</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否支持部分还款：</font>
    <c:if test="${tenderItem.isapartrepay eq 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.isapartrepay eq 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">正常还款代偿开关：</font>
    <c:if test="${tenderItem.iscompensatory eq 1}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.iscompensatory eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>

<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">提前还款代偿开关：</font>
    <c:if test="${tenderItem.isaheadcompensatory eq 1}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.isaheadcompensatory eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>
<c:if test="${tenderItem.isaheadcompensatory eq 1}">
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">正常还款代偿人：</font>
        <font class="col-md-4">${tenderItem.aheadcompensatoryman}</font>
        <hr>
    </div>
</c:if>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">逾期宽限期：</font>
    <font class="col-md-4">${tenderItem.graceperiod}天</font>
    <hr>
</div>


<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否审核：</font>
    <c:if test="${tenderItem.isaudit eq 1}">
        <font class="col-md-4">必须审核</font>
    </c:if>
    <c:if test="${tenderItem.isaudit eq 0}">
        <font class="col-md-4">不用审核</font>
    </c:if>
    <hr>
</div>--%>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">标的描述：</font>
    <font class="col-md-4">${tenderItem.tdesc}</font>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否为模板：</font>
    <c:if test="${tenderItem.istemplet eq 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.istemplet eq 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>

<%--<div class="row text-center" style="line-height: 0px;">
    <font size="4">约标设置</font>
    <hr/>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否为约标：</font>
    <c:if test="${tenderItem.isappointtender == 1}">
        <font class="col-md-4">是</font>
    </c:if>
    <c:if test="${tenderItem.isappointtender == 0}">
        <font class="col-md-4">否</font>
    </c:if>
    <hr>
</div>
<c:if test="${tenderItem.isappointtender == 1}">
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">约标码：</font>
        <font class="col-md-4">${tenderItem.tpass}</font>
        <hr>
    </div>
</c:if>--%>
<div class="row text-center" style="line-height: 0px;">
    <font size="4">其它设置</font>
    <hr/>
</div>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否设置站岗利息：</font>
    <c:if test="${tenderItem.issetgfundsint eq 1||tenderItem.issetgfundsint eq 2}">
        <font class="col-md-4">设置</font>
    </c:if>
    <c:if test="${tenderItem.issetgfundsint eq 0}">
        <font class="col-md-4">不设置</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">流标利息方式补偿开关：</font>
    <c:if test="${tenderItem.isintcompensateon eq 1||tenderItem.isintcompensateon eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.isintcompensateon eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">流标奖品补偿方式开关：</font>
    <c:if test="${tenderItem.isawardcompensateon eq 1||tenderItem.isawardcompensateon eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.isawardcompensateon eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否允许逾期代偿：</font>
    <c:if test="${tenderItem.isaoverduec eq 1||tenderItem.isaoverduec eq 2}">
        <font class="col-md-4">允许</font>
    </c:if>
    <c:if test="${tenderItem.isaoverduec eq 0}">
        <font class="col-md-4">不允许</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">收取平台追偿费开关：</font>
    <c:if test="${tenderItem.isaocfee eq 1||tenderItem.isaocfee eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.isaocfee eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否允许提前还款：</font>
    <c:if test="${tenderItem.isaaheadrepay eq 1||tenderItem.isaaheadrepay eq 2}">
        <font class="col-md-4">允许</font>
    </c:if>
    <c:if test="${tenderItem.isaaheadrepay eq 0}">
        <font class="col-md-4">不允许</font>
    </c:if>
    <hr>
</div>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">提前还款本金利息补偿开关：</font>
    <c:if test="${tenderItem.ispicompensateon eq 1||tenderItem.ispicompensateon eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.ispicompensateon eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">提前还款增益利息补偿开关：</font>
    <c:if test="${tenderItem.ispluscompensateon eq 1||tenderItem.ispluscompensateon eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.ispluscompensateon eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">提前还款还款人补偿平台开关：</font>
    <c:if test="${tenderItem.isforplatformon eq 1||tenderItem.isforplatformon eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.isforplatformon eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否允许债权转让：</font>
    <c:if test="${tenderItem.isadebtattorn eq 1||tenderItem.isadebtattorn eq 2}">
        <font class="col-md-4">允许</font>
    </c:if>
    <c:if test="${tenderItem.isadebtattorn eq 0}">
        <font class="col-md-4">不允许</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否收取债权手续费：</font>
    <c:if test="${tenderItem.isadebtattornfee eq 1||tenderItem.isadebtattornfee eq 2}">
        <font class="col-md-4">开</font>
    </c:if>
    <c:if test="${tenderItem.isadebtattornfee eq 0}">
        <font class="col-md-4">关</font>
    </c:if>
    <hr>
</div>--%>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否允许使用增益：</font>
    <c:if test="${tenderItem.isaplus eq 1||tenderItem.isaplus eq 2}">
        <font class="col-md-4">允许</font>
    </c:if>
    <c:if test="${tenderItem.isaplus eq 0}">
        <font class="col-md-4">不允许</font>
    </c:if>
    <hr>
</div>--%>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否设置居间费：</font>
    <c:if test="${tenderItem.isamediacy eq 1||tenderItem.isamediacy eq 2}">
        <font class="col-md-4">设置</font>
    </c:if>
    <c:if test="${tenderItem.isamediacy eq 0}">
        <font class="col-md-4">不设置</font>
    </c:if>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否设置担保费：</font>
    <c:if test="${tenderItem.isaguarantee eq 1||tenderItem.isaguarantee eq 2}">
        <font class="col-md-4">设置</font>
    </c:if>
    <c:if test="${tenderItem.isaguarantee eq 0}">
        <font class="col-md-4">不设置</font>
    </c:if>
    <hr>
</div>
<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否设置利息管理费：</font>
    <c:if test="${tenderItem.isaintexp eq 1||tenderItem.isaintexp eq 2}">
        <font class="col-md-4">设置</font>
    </c:if>
    <c:if test="${tenderItem.isaintexp eq 0}">
        <font class="col-md-4">不设置</font>
    </c:if>
    <hr>
</div>
<%--<div class="row" style="line-height: 0px;">
    <font class="col-sm-4 text-right">是否设置风险保证金：</font>
    <c:if test="${tenderItem.isariskgm eq 1||tenderItem.isariskgm eq 2}">
        <font class="col-md-4">设置</font>
    </c:if>
    <c:if test="${tenderItem.isariskgm eq 0}">
        <font class="col-md-4">不设置</font>
    </c:if>
    <hr>
</div>--%>
</body>
</html>
