<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container-fluid">
	<div class="row clearfix">
		<div class="col-md-12 column"
			style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
			<div id="area_div">
				<form method="post" role="form" id="pendingLoanForm">
					<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
						type="hidden" id="pageSize" name="pageSize" value="" />
					<div class="table-responsive">
						<table class="table table-hover table-condensed text-center">
							<caption>
								<strong>待放款投标订单</strong>
							</caption>
							<thead>
								<tr style="background: #ccc;">
									<td>序号</td>
									<td>投标订单号</td>
									<td>标号</td>
									<td>标的名称</td>
									<td>标的属性</td>
									<td>投资方</td>
									<td>借款方</td>
									<td>投标金额（元）</td>
									<td>投标的状态</td>
									<td>备注</td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pendingLoans}">
									<c:forEach items="${pendingLoans}" var="tender"
										varStatus="varStatus">
										<tr>
											<td>${varStatus.index+1}</td>
											<td>${tender.orderno}<input hidden="hidden"
												name="orderno" value="${tender.orderno}"></td>
											<td>${tender.tenderitem.tno}</td>
											<td>${tender.tenderitem.tname}</td>
											<td><c:if test="${!empty tproperty}">
													<c:forEach items="${tproperty}" var="ttype">
														<c:choose>
															<c:when test="${tender.tproperty==ttype.key}">${ttype.value}
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td>${tender.outaccount.loginname}</td>
											<td>${tender.inaccount.loginname}</td>
											<td><fmt:formatNumber minFractionDigits="2"
													value="${tender.amount}"></fmt:formatNumber></td>
											<td><c:if test="${tender.tstatus eq 1}">
												成功
												</c:if></td>
											<td><p limit="5" data-toggle="tooltip"
													title="<h5>${tender.remark}</h5>">${tender.remark}</p></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty pendingLoans}">
									<tr>
										<td colspan="100">没有需要放款的投标订单</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>