<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GfundsInt_Detail</title>
<style type="text/css">
 hr{
   margin: 10px;
 }
</style>
</head>
<body>
                 <c:if test="${!empty gfundsInt}">
                      <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">标号Id：</font>
				       <label id="addman-lb" class="col-sm-4">${gfundsInt.tid}</label>
		              </div>
		              <hr>
		              <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">站岗利息编号：</font>
				       <label id="addman-lb" class="col-sm-4">${gfundsInt.gfundsintno}</label>
		              </div>
		              <hr>
		              <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">清算方式：</font>
				       <c:if test="${!empty gfundsInt.clearmethod}">
				          <c:if test="${gfundsInt.clearmethod eq 1}">
				             <label id="addman-lb" class="col-sm-4">结标清算</label>
				          </c:if>
				       </c:if>
		              </div>
		              <hr>
		              <c:forEach items="${gfundsInts}" var="sats">
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">会员等级：</font>
				         <label id="addman-lb" class="col-sm-4">${sats.ugrade}</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">分段最低-最高投资金额：</font>
				         <label id="addman-lb" class="col-sm-4">${sats.minmoney}~${sats.maxmoney}元</label>
		                </div>
		                <hr>
		                <c:if test="${!empty sats.quota}">
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">定额补偿金：</font>
				         <label id="addman-lb" class="col-sm-4">${sats.quota}元</label>
		                 </div>
		                 <hr>
		                </c:if>
		                 <c:if test="${!empty sats.dayawardrate}">
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">日奖励费率：</font>
				         <label id="addman-lb" class="col-sm-4">${sats.dayawardrate*100}%</label>
		                 </div>
		                 <hr>
		                </c:if>
		                 <c:if test="${!empty sats.maxcompensate}">
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">最高补偿金额：</font>
				         <label id="addman-lb" class="col-sm-4">${sats.maxcompensate}元</label>
		                 </div>
		                 <hr>
		                </c:if>
		              </c:forEach>
		              <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">资金清算是否需要审核：</font>
				         <c:if test="${!empty gfundsInt.isaudit}">
				             <c:if test="${gfundsInt.isaudit eq 1}">
				               <label id="addman-lb" class="col-sm-4">是</label>
				             </c:if>
				             <c:if test="${gfundsInt.isaudit eq 0}">
				               <label id="addman-lb" class="col-sm-4">否</label>
				             </c:if>
				         </c:if>
		                </div>
		                <hr>
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">是否为模板：</font>
				         <c:if test="${!empty gfundsInt.istemplet}">
				             <c:if test="${gfundsInt.istemplet eq 1}">
				               <label id="addman-lb" class="col-sm-4">是</label>
				             </c:if>
				             <c:if test="${gfundsInt.istemplet eq 0}">
				               <label id="addman-lb" class="col-sm-4">否</label>
				             </c:if>
				         </c:if>
		                </div>
		                <hr>
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">添加人：</font>
				               <label id="addman-lb" class="col-sm-4">${gfundsInt.addman}</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">设置时间：</font>
				               <label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${gfundsInt.addtime}" type="both"/></label>
		                </div>
		                <hr>
		                  <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">备注：</font>
				               <label id="addman-lb" class="col-sm-4">${gfundsInt.remark}</label>
		                </div>
                    </c:if>
                    <c:if test="${empty gfundsInt}">
                      <label>暂无数据</label>
                    </c:if>
</body>
</html>