<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${!empty rule }">
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">编号：</label><label id="ptype"
				class="col-md-4">${rule.id }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">级别：</label><label id="ptype"
				class="col-md-4">${rule.grade }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">升级方式：</label><label id="ptype"
				class="col-md-4">${methods}</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">时间有效期：</label><label id="point"
				class="col-md-4">${rule.effecttime }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">支付金额：</label><label id="ptime"
				class="col-md-4">${rule.payamount}</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">需要积分：</label><label id="pdtime"
				class="col-md-4">${rule.needbonuspoints }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">单位(年/月/日)：</label><label
				id="pdtime" class="col-md-4">${rule.unit }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">备注：</label><label id="pstatus"
				class="col-md-4">${rule.remark }</label>
		</div>
	</c:if>
	<!-- ======== -->
	<c:if test="${!empty ruletwo }">
		<form
			action="${pageContext.request.contextPath}/admin/gradeRule/updateRule.action"
			method="post" role="form">
			<div class="row" style="margin-top: auto">
				<input type="hidden" value="${ruletwo.id }" name="id"> <label
					class="col-md-4 text-right">级别：</label> <input type="text"
					value="${ruletwo.grade }" name="grade" class="col-md-4 "
					disabled="disabled">
			</div>
			<div class="row" style="margin-top: 10px;">
				<label class="col-md-4 text-right">升级方式：</label> <select
					name="method" id="method" class="col-md-4">
					<c:if test="${!empty  methodmaps}">
						<c:forEach items="${methodmaps}" var="methmap">
							<c:if test="${methmap.key!=3 }">
								<option value="${methmap.key}">${methmap.value}</option>
							</c:if>
						</c:forEach>
					</c:if>
				</select>
			</div>
			<div class="row" style="margin-top: 10px;">
				<label class="col-md-4 text-right">时间有效期：</label> <input type="text"
					value="${ruletwo.effecttime }" name="effecttime" class="col-md-4 "
					maxlength="25" />
			</div>
			<div class="row" style="margin-top: 10px;">
				<label class="col-md-4 text-right">支付金额：</label> <input type="text"
					value="${ruletwo.payamount }" name="payamount" class="col-md-4 "
					maxlength="25" />
			</div>
			<div class="row" style="margin-top: 10px;">
				<label class="col-md-4 text-right">需要积分：</label> <input type="text"
					value="${ruletwo.needbonuspoints }" name="needbonuspoints"
					class="col-md-4 " />
			</div>
			<div class="row" style="margin-top: 10px;">
				<label class="col-md-4 	 text-right">备注：</label> <input type="text"
					value="${ruletwo.remark }" name="remark" class="col-md-4 " />
			</div>
			<div class="row" style="margin-top: 10px;">
				<label class="col-md-4 text-right"></label> <input type="submit"
					value="修改" class=" btn btn-primary btn-lg " />
			</div>
		</form>
	</c:if>
</body>
</html>