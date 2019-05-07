<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body style="font-family:'微软雅黑'; ">
<div class="row text-center" style="line-height: 0px;">
	<font size="4">标的提前还款方式设置详情</font>
	<hr/>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">ID：</font>
	<font class="col-md-4">${aheadRepayMode.id}</font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">标编号：</font>
	<font class="col-md-4">${aheadRepayMode.tenderitem.tno}</font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">还款方式：</font>
	<font class="col-md-4"><c:if test="${aheadRepayMode.repaytype==1}">一次性还本付息</c:if> <c:if
			test="${aheadRepayMode.repaytype==2}">等额本金</c:if> <c:if
			test="${aheadRepayMode.repaytype==3}">等额本息</c:if> <c:if
			test="${aheadRepayMode.repaytype==4}">按期付息到期还本</c:if></font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">提前还款类型：</font>
	<font class="col-md-4"><c:if test="${aheadRepayMode.arepaymode==1}">全部</c:if> <c:if
			test="${aheadRepayMode.arepaymode==2}">部分</c:if></font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">提前期数：</font>
	<font class="col-md-4">${aheadRepayMode.aperiods}</font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">还利息方式：</font>
	<font class="col-md-4"><c:if test="${aheadRepayMode.intmode==1}">占天利息</c:if> <c:if
			test="${aheadRepayMode.intmode==2}">全额利息</c:if></font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">添加人：</font>
	<font class="col-md-4">${aheadRepayMode.addman}</font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">设置时间：</font>
	<font class="col-md-4"><fmt:formatDate value="${aheadRepayMode.addtime}" /></font>
	<hr>
</div>
<div class="row" style="line-height: 0px;">
	<font class="col-sm-4 text-right">备注：</font>
	<font class="col-md-4">${aheadRepayMode.remark}</font>
	<hr>
</div>
</body>
</html>