<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

</head>
<body style="font-family:'微软雅黑'; ">
<c:if test="${!empty loanapp}">
    <div class="row text-center" style="line-height: 0px;">
        <font size="4">借款申请基本信息</font>
        <hr/>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">借款编号：</font>
        <font class="col-md-4">${loanapp.loanno}</font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">申请人：</font>
        <font class="col-md-4">${loanapp.loginname}-${loanapp.realname}</font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">借款金额：</font>
        <font class="col-md-4">${loanapp.loanamount}元</font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">已入账借款金额：</font>
        <font class="col-md-4">${loanapp.receiptsamount}元</font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">借款类型：</font>
        <font class="col-md-4">
            <c:forEach items="${objectQuotes}" var="ob">
                <c:if test="${loanapp.loantype==ob.serialno}">${ob.objectname}</c:if>
            </c:forEach>
        </font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">状态：</font>
        <font class="col-md-4">
            <c:choose>
                <c:when test="${loanapp.appstatus eq 1}">待审核</c:when>
                <c:when test="${loanapp.appstatus eq 2}">审核失败</c:when>
                <c:when test="${loanapp.appstatus eq 3}">待建标</c:when>
                <c:when test="${loanapp.appstatus eq 4}">待录入</c:when>
                <c:when test="${loanapp.appstatus eq 5}">待投标</c:when>
                <c:when test="${loanapp.appstatus eq 6}">投标中</c:when>
                <c:when test="${loanapp.appstatus eq 7}">已流标</c:when>
                <c:when test="${loanapp.appstatus eq 8}">待放款</c:when>
                <c:when test="${loanapp.appstatus eq 9}">待生成还款计划</c:when>
                <c:when test="${loanapp.appstatus eq 10}">还款中</c:when>
                <c:when test="${loanapp.appstatus eq 11}">已完成</c:when>

                <c:when test="${loanapp.appstatus eq 12}">录入失败</c:when>
                <c:when test="${loanapp.appstatus eq 13}">录入过期</c:when>
                <c:when test="${loanapp.appstatus eq 14}">录入放弃</c:when>
            </c:choose>
        </font>
        <hr>
    </div>
  <%--  <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">接口申请来源：</font>
        <font class="col-md-4">${loanapp.ifapporigin}</font>
        <hr>
    </div>--%>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">申请方式：</font>
        <font class="col-md-4">
            <c:choose>
                <c:when test="${loanapp.apptype eq 1}">自申请</c:when>
                <c:when test="${loanapp.apptype eq 2}">代申请</c:when>
                <c:when test="${loanapp.apptype eq 3}">接口申请</c:when>
            </c:choose>
        </font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">借款资料：</font>
        <font class="col-md-4">
            <c:choose>
                <c:when test="${loanapp.mastatus eq 1}">未填写</c:when>
                <c:when test="${loanapp.mastatus eq 2}">待审核</c:when>
                <c:when test="${loanapp.mastatus eq 3}">审核中</c:when>
                <c:when test="${loanapp.mastatus eq 4}">审核成功</c:when>
                <c:when test="${loanapp.mastatus eq 5}">审核失败</c:when>
            </c:choose>
        </font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">申请时间：</font>
        <font class="col-md-4"><fmt:formatDate value="${loanapp.apptime}" type="both"/></font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">完成时间：</font>
        <font class="col-md-4"><fmt:formatDate value="${loanapp.finishtime}" type="both"/></font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">借款周期：</font>
        <font class="col-md-4">${loanapp.appday}${loanapp.unit}</font>
        <hr>
    </div>
   <%-- <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">借款利率：</font>
        <font class="col-md-4">${loanapp.loanrate}</font>
        <hr>
    </div>--%>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">还款方式：</font>
        <font class="col-md-4">
            <c:choose>
                <c:when test="${loanapp.repaymenttype eq 1}">一次性还本付息</c:when>
                <c:when test="${loanapp.repaymenttype eq 2}">等额本金</c:when>
                <c:when test="${loanapp.repaymenttype eq 3}">等额本息</c:when>
                <c:when test="${loanapp.repaymenttype eq 4}">按期付息到期还本</c:when>
            </c:choose>
        </font>
        <hr>
    </div>
<%--    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">是否为约标：</font>
        <font class="col-md-4">
            <c:choose>
                <c:when test="${loanapp.isappointtender eq 1}">是</c:when>
                <c:when test="${loanapp.isappointtender eq 0}">否</c:when>
            </c:choose>
        </font>
        <hr>
    </div>--%>
   <%-- <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">是否同意自动还款：</font>
        <font class="col-md-4">
            <c:choose>
                <c:when test="${loanapp.isautorepay eq 0}">是</c:when>
                <c:when test="${loanapp.isautorepay eq 1}">否</c:when>
            </c:choose>
        </font>
        <hr>
    </div>--%>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">申请备注：</font>
        <font class="col-md-4">${loanapp.loanpurposedesc}</font>
        <hr>
    </div>
   <%-- <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">审核备注：</font>
        <font class="col-md-4">${loanapp.remark}</font>
        <hr>
    </div>--%>
    <%--<div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">风控值：</font>
        <font class="col-md-4">${loanapp.riskmvalue}</font>
        <hr>
    </div>--%>
    <%--<div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">风控来源：</font>
        <font class="col-md-4">${loanapp.riskmorigin}</font>
        <hr>
    </div>--%>
    <%--<div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">风控等级：</font>
        <font class="col-md-4">${loanapp.riskmlevel}</font>
        <hr>
    </div>--%>
   <%-- <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">担保公司：</font>
        <font class="col-md-4"> </font>
        <hr>
    </div>--%>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">审核人：</font>
        <font class="col-md-4">${loanapp.auditman}</font>
        <hr>
    </div>
    <div class="row" style="line-height: 0px;">
        <font class="col-sm-4 text-right">审核时间：</font>
        <font class="col-md-4"><fmt:formatDate value="${loanapp.audittime}" type="both"/></font>
    </div>
</c:if>
<c:if test="${empty loanapp}">
    <font class="col-md-4">暂无数据</font>
</c:if>
</body>
</html>