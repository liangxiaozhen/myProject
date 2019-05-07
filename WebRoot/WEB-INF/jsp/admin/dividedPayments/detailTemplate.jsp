<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的分期还款计划详情列表</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">ID（主键）</label> <span>${dividedPayments.id}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">分期还款流水号</label> <span>${dividedPayments.dporderno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">标号</label> <span>${dividedPayments.tenderItem.tno}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">标名称</label> <span>${dividedPayments.tenderItem.tname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">期数(第几期)</label> <span>${dividedPayments.periods}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款日</label> <span>${gj:formatDate(dividedPayments.repayday,'yyyy-MM-dd')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">当期本息 (当期总的本+息)</label> <span>${dividedPayments.currentpi}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">当期本金 当期本金</label> <span>${dividedPayments.cpprincipal}</span>
					</div>
				</div>



				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">当期利息</label> <span>${dividedPayments.cpinterest}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否还款完成 </label> <span>
							${dividedPayments.iscomplete==1?'已完成还款':dividedPayments.iscomplete==2?'处理中':dividedPayments.iscomplete==3?'部分还款':'没有完成还款'}
						</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">备注 </label> <span>${dividedPayments.remark}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否逾期 </label> <span>${dividedPayments.isoverdue ==1 ?'已经逾期':'没有逾期'}</span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否审核 </label> <span> <c:choose>
								<c:when test="${dividedPayments.isaudit ==0}">否</c:when>
								<c:when test="${dividedPayments.isaudit ==1}">是</c:when>
								<c:when test="${dividedPayments.isaudit ==2}">已审核</c:when>
							</c:choose>
						</span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余本金</label> <span>${dividedPayments.restprincipal}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>