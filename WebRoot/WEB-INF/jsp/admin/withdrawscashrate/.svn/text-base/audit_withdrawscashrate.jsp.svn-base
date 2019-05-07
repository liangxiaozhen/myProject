<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="rateadd-form" method="post">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<input type="hidden" id="audit-id-text" value="${wdcRate.id} ">
				会员等级：
			</div>
			<div class="col-md-7">
				<label>${wdcRate.ugrade }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">金额范围：</div>
			<div>
				<label>${wdcRate.minmoney} 元 - ${wdcRate.maxmoney } 元</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>类型</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">
				手续费类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div>
				<label> <c:if test="${wdcRate.wdcmode eq 1}">定额</c:if> <c:if
						test="${wdcRate.wdcmode eq 2}">比例</c:if>
				</label>
			</div>
		</div>
		<hr>
		<c:if test="${wdcRate.wdcmode==1}">
			<!-- 定额 -->
			<div id="showquota-div">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
						<font color="red"><b>定额</b></font>
					</div>
				</div>
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-3 col-md-offset-1">
						固定金额&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp;</div>
					<div>
						<label>${wdcRate.quota} 元</label>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${wdcRate.wdcmode==2}">
			<!-- 比例 -->
			<div id="showrate-div">
				<div class="row">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
						<font color="red"><b>比例</b></font>
					</div>
				</div>
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-3 col-md-offset-1">
						提现费率&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp;<label id="showwdcrate-lb">${wdcRate.wdcrate}%</label>
					</div>
				</div>
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-5 col-md-offset-1">
						最低收费金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>${wdcRate.minfee}
							元</label>
					</div>
					<div class="col-md-6 ">
						最高收费金额：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>${wdcRate.maxfee}
							元</label>
					</div>
				</div>
			</div>
		</c:if>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
			<div class="row" style="line-height: 0px; margin-top: 12px;">
				<div class="col-md-10 col-md-offset-1">
					<textarea class="form-control" name="remark" id="audit-remark-text">${wdcRate.remark}</textarea>
				</div>
			</div>

		</div>
	</form>
</body>
</html>