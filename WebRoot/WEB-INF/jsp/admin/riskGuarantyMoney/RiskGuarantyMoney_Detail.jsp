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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>RiskGuarantyMoney</title>
    <script type="text/javascript">
        /* 备注显示字符个数限制*/
        jQuery.fn.limit = function () {
            var self = $("[limit]");
            self.each(function () {
                var objString = $(this).text();
                var objLength = $(this).text().length;
                var num = $(this).attr("limit");
                if (objLength > num) {
                    objString = $(this).text(objString.substring(0, num) + "...");
                }
            })
        }

        $(function () {
            $("[limit]").limit();
        })

        /* 备注tips */
        $(function () {
            $("[data-toggle='tooltip']").tooltip({
                html: true
            });
        });
    </script>
    <style type="text/css">
        hr {
            margin: 10px;
        }
    </style>
</head>
<body style="font-family:'微软雅黑'; ">
<div class="row" style="line-height: 10px;">
    <font size="3" class="col-sm-4 text-right">风险保证金编号：</font>
    <label id="addman-lb" class="col-sm-4">${riskGuarantyMoney.riskgmno}</label>
</div>
<hr>
<div class="row" style="line-height: 10px;">
    <font size="3" class="col-sm-4 text-right">风险保证金收款人：</font>
    <label id="addman-lb" class="col-sm-4">${riskGuarantyMoney.rgmrecman}</label>
</div>
<hr>
<div class="row" style="line-height: 10px;">
    <font size="3" class="col-sm-4 text-right">收费类型：</font>
    <c:if test="${riskGuarantyMoney.chargetype eq 1}">
        <label id="addman-lb" class="col-sm-4">结标收取</label>
    </c:if>
    <c:if test="${riskGuarantyMoney.chargetype eq 2}">
        <label id="addman-lb" class="col-sm-4">投标时收取</label>
    </c:if>
</div>
<hr>
<c:if test="${riskGuarantyMoney.chargetype eq 1}">
    <c:forEach items="${riskGuarantyMoneys}" var="risk">
        <div class="row" style="line-height: 10px;">
            <font size="3" class="col-sm-4 text-right">结标分段金额低值-高值：</font>
            <label id="addman-lb" class="col-sm-4">${risk.minrgmmoney}~${risk.maxrgmmoney}元</label>
        </div>
        <hr>
        <c:if test="${!empty risk.rgmquota}">
            <div class="row" style="line-height: 10px;">
                <font size="3" class="col-sm-4 text-right">风险保证金定额：</font>
                <label id="addman-lb" class="col-sm-4">${risk.rgmquota}元</label>
            </div>
            <hr>
        </c:if>
        <c:if test="${!empty risk.rgmpercent}">
            <div class="row" style="line-height: 10px;">
                <font size="3" class="col-sm-4 text-right">风险保证金百份比：</font>
                <label id="addman-lb" class="col-sm-4">${risk.rgmpercent*100}%</label>
            </div>
            <hr>
        </c:if>
        <c:if test="${!empty risk.maxrgmfee}">
            <div class="row" style="line-height: 10px;">
                <font size="3" class="col-sm-4 text-right">该段最高风险保证金额：</font>
                <label id="addman-lb" class="col-sm-4">${risk.maxrgmfee}元</label>
            </div>
            <hr>
        </c:if>
    </c:forEach>
</c:if>
<!-- >>>>>>>>>>>>>>>>>>>><<<<<<<<<< 投标时收取-->
<c:if test="${riskGuarantyMoney.chargetype eq 2}">
    <c:forEach items="${riskGuarantyMoneys}" var="riskt">
        <div class="row" style="line-height: 10px;">
            <font size="3" class="col-sm-4 text-right">会员等级：</font>
            <label class="col-sm-6" limit="24" data-toggle="tooltip"
                   title="<h5>${riskt.ugrade}</h5>">${riskt.ugrade}</label>
        </div>
        <hr>
        <div class="row" style="line-height: 10px;">
            <font size="3" class="col-sm-4 text-right">风险保证金费率：</font>
            <label id="addman-lb" class="col-sm-4">${riskt.rgmrate*100}%</label>
        </div>
        <hr>
        <div class="row" style="line-height: 10px;">
            <font size="3" class="col-sm-4 text-right">风险保证金最高收费：</font>
            <label id="addman-lb" class="col-sm-4">${riskt.maxrgmamount}元</label>
        </div>
        <hr>
    </c:forEach>
</c:if>

<div class="row" style="line-height: 10px;">
    <font size="3" class="col-sm-4 text-right">资金清算是否需要审核：</font>
    <c:if test="${riskGuarantyMoney.isaudit eq 1}">
        <label id="addman-lb" class="col-sm-4">是</label>
    </c:if>
    <c:if test="${riskGuarantyMoney.isaudit eq 0}">
        <label id="addman-lb" class="col-sm-4">否</label>
    </c:if>
</div>
<hr>
<div class="row" style="line-height: 10px;">
    <font size="3" class="col-sm-4 text-right">是否为模板：</font>
    <c:if test="${riskGuarantyMoney.istemplet eq 1}">
        <label class="col-sm-4">是</label>
    </c:if>
    <c:if test="${riskGuarantyMoney.istemplet eq 0}">
        <label class="col-sm-4">否</label>
    </c:if>
</div>
</body>
</html>
