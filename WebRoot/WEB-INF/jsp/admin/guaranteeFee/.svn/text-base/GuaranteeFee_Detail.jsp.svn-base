<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuaranteeFee_Detail</title>
<style type="text/css">
    hr{
		margin: 10px;
	} 
</style>
</head>
<body>
      <c:if test="${!empty guaranteeFee}">
        <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保费编号：</font>
				<label id="addman-lb" class="col-sm-4">${guaranteeFee.guaranteefeeno}</label>
		</div>
		<hr>
		<div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保公司：</font>
				<c:if test="${!empty usrname}">
				<label id="addman-lb" class="col-sm-4">${usrname}</label>
				</c:if>
		</div>
		<hr>
		 <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保服务费收款人：</font>
				<label id="addman-lb" class="col-sm-4">${guaranteeFee.gfrecman}</label>
		</div>
		<hr>
		<c:if test="${guaranteeFee.chargetype eq 1}">
		   <c:forEach items="${guaranteeFees}" var="fees" >
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">结标分段金额低值-高值：</font>
				<label id="addman-lb" class="col-sm-4">${fees.minnmmoney}~${fees.maxnmmoney}元</label>
		  </div>
		  <hr>
		  <c:if test="${!empty fees.gfquota}">
		  <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保费定额：</font>
				<label id="addman-lb" class="col-sm-4">${fees.gfquota}元</label>
		  </div>
		  <hr>
		  </c:if>
		  <c:if test="${!empty fees.gfpercent}">
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保费百份比：</font>
				<label id="addman-lb" class="col-sm-4">${fees.gfpercent*100}%</label>
		  </div>
		  <hr>
		  </c:if>
		  <c:if test="${!empty fees.mingffee}">
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">该段最低担保收费金额：</font>
				<label id="addman-lb" class="col-sm-4">${fees.mingffee}元</label>
		  </div>
		  <hr>
		  </c:if>
		  <c:if test="${!empty fees.maxgffee}">
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">该段最高担保收费金额：</font>
				<label id="addman-lb" class="col-sm-4">${fees.maxgffee}元</label>
		  </div>
		  <hr>
		  </c:if>
		  </c:forEach>
		</c:if>
		<c:if test="${guaranteeFee.chargetype eq 2}">
		  <c:forEach items="${guaranteeFees}" var="tee">
		    <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保费费率：</font>
				<label id="addman-lb" class="col-sm-4">${tee.gfrate*100}%</label>
		  </div>
		  <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保费最低收费：</font>
				<label id="addman-lb" class="col-sm-4">${tee.mingfamount}元</label>
		  </div>
		  <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">担保费最高收费：</font>
				<label id="addman-lb" class="col-sm-4">${tee.maxgfamount}元</label>
		  </div>
		  <hr>
		  </c:forEach>
		</c:if>
		  <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资金清算是否需要审核：</font>
				<c:if test="${guaranteeFee.isaudit eq 1}">
				<label id="addman-lb" class="col-sm-4">是</label>
				</c:if>
				<c:if test="${guaranteeFee.isaudit eq 0}">
				<label id="addman-lb" class="col-sm-4">否</label>
				</c:if>
		  </div>
		  <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">是否为模板：</font>
				<c:if test="${guaranteeFee.istemplet eq 1}">
				<label id="addman-lb" class="col-sm-4">是</label>
				</c:if>
				<c:if test="${guaranteeFee.istemplet eq 0}">
				<label id="addman-lb" class="col-sm-4">否</label>
				</c:if>
		  </div>
		  <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">添加人：</font>
				<label id="addman-lb" class="col-sm-4">${guaranteeFee.addman}</label>
		  </div>
		  <hr>
		   <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">设置时间：</font>
				<label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${guaranteeFee.addtime}" type="both"/></label>
		  </div>
		  <hr>
		  <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">备注：</font>
				<label id="addman-lb" class="col-sm-4">${guaranteeFee.remark}</label>
		  </div>
		  </c:if>
		  <c:if test="${empty guaranteeFee}">
		     <label>暂无数据！</label>
		  </c:if>
</body>
</html>