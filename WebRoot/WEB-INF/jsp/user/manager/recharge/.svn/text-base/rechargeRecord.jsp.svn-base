<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css"> --%>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
.pagination {
	display: inline-block;
	padding-left: 0;
	margin: 20px 0;
	border-radius: 4px;
}

.pagination>li {
	display: inline;
}

.pagination>li>a, .pagination>li>span {
	position: relative;
	float: left;
	padding: 6px 12px;
	margin-left: -1px;
	line-height: 1.42857143;
	color: #337ab7;
	text-decoration: none;
	background-color: #fff;
	border: 1px solid #ddd;
}

.pagination>li:first-child>a, .pagination>li:first-child>span {
	margin-left: 0;
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px;
}

.pagination>li:last-child>a, .pagination>li:last-child>span {
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
}

.pagination>li>a:hover, .pagination>li>span:hover, .pagination>li>a:focus,
	.pagination>li>span:focus {
	z-index: 3;
	color: #23527c;
	background-color: #eee;
	border-color: #ddd;
}

.pagination>.active>a, .pagination>.active>span, .pagination>.active>a:hover,
	.pagination>.active>span:hover, .pagination>.active>a:focus,
	.pagination>.active>span:focus {
	z-index: 2;
	color: #fff;
	cursor: default;
	background-color: #337ab7;
	border-color: #337ab7;
}

.pagination>.disabled>span, .pagination>.disabled>span:hover,
	.pagination>.disabled>span:focus, .pagination>.disabled>a, .pagination>.disabled>a:hover,
	.pagination>.disabled>a:focus {
	color: #777;
	cursor: not-allowed;
	background-color: #fff;
	border-color: #ddd;
}

.pagination-lg>li>a, .pagination-lg>li>span {
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.3333333;
}

.pagination-lg>li:first-child>a, .pagination-lg>li:first-child>span {
	border-top-left-radius: 6px;
	border-bottom-left-radius: 6px;
}

.pagination-lg>li:last-child>a, .pagination-lg>li:last-child>span {
	border-top-right-radius: 6px;
	border-bottom-right-radius: 6px;
}

.pagination-sm>li>a, .pagination-sm>li>span {
	padding: 5px 10px;
	font-size: 12px;
	line-height: 1.5;
}

.pagination-sm>li:first-child>a, .pagination-sm>li:first-child>span {
	border-top-left-radius: 3px;
	border-bottom-left-radius: 3px;
}

.pagination-sm>li:last-child>a, .pagination-sm>li:last-child>span {
	border-top-right-radius: 3px;
	border-bottom-right-radius: 3px;
}

.pager {
	padding-left: 0;
	margin: 20px 0;
	text-align: center;
	list-style: none;
}

.pager li {
	display: inline;
}

.pager li>a, .pager li>span {
	display: inline-block;
	padding: 5px 14px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 15px;
}

.pager li>a:hover, .pager li>a:focus {
	text-decoration: none;
	background-color: #eee;
}

.pager .next>a, .pager .next>span {
	float: right;
}

.pager .previous>a, .pager .previous>span {
	float: left;
}

.pager .disabled>a, .pager .disabled>a:hover, .pager .disabled>a:focus,
	.pager .disabled>span {
	color: #777;
	cursor: not-allowed;
	background-color: #fff;
}
</style>

	<div class="account_notice">
		<div class="tab-content">
			<form id="form-select">
				<div style="margin-bottom: 20px">
					<input type="hidden" id="pageNum" name="pageNum" /> 
					<input type="hidden" id="pageSize" name="pageSize" /> 
				
				</div>
			</form>
			<!-- 先前的table的class样式table table-striped -->
			<table class="table table-hover">
				<tr class="text-center" style="background: #ccc;">
					<!-- <td>序号</td>
					<td>订单号</td>
					<td>手续费类型</td> -->
					<td>充值时间</td>
					<td>充值金额</td>
					<td>手续费</td>
				<!-- 	<td>实际入账</td> -->
					<td>充值方式</td>
					<!-- <td>银行卡号</td> -->
					<!-- <td>银行</td>
					<td>是否勾兑</td> -->
					<td>处理状态</td>
					<td>操作</td>
					<!-- <td>操作</td> -->
				</tr>
				<tbody>
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">
						<tr class="text-center">
						<%-- 	<td>${status.count}</td>
							<td>${item.rechargeno}</td>
							<td>
								<c:if test="${item.feeobjflag eq 'U'}">自付</c:if>
								<c:if test="${item.feeobjflag eq 'M'}">他付</c:if>
								<c:if test="${item.feeobjflag eq 'I'}">不付</c:if>
							</td> --%>
							<%-- <td>${item.ubai.realname}</td> --%>
							<td>
								<c:if test="${!empty item.starttime}">${sf.format(item.starttime)}</c:if>
							</td>
							<td>
								<c:if test="${!empty item.amount}">${df.format(item.amount)}</c:if>
								<c:if test="${empty item.amount}">0.00</c:if>
							</td>
							<td>
								<c:if test="${!empty item.recharfee}">${df.format(item.recharfee)}</c:if>
								<c:if test="${empty item.recharfee}">0.00</c:if>
							</td>
							<%-- <td style="color: blue">
								<c:if test="${!empty item.theamountcredited}">${df.format(item.theamountcredited)}</c:if>
								<c:if test="${empty item.theamountcredited}">0.00</c:if>
							</td> --%>
							<td>
								<c:if test="${item.rechargetype eq '1'}">个人网银</c:if>
								<c:if test="${item.rechargetype eq '2'}">快捷支付</c:if>
								<c:if test="${item.rechargetype eq '3'}">企业网银</c:if>
								<c:if test="${item.rechargetype eq '4'}">汇款充值</c:if>
							</td>
							<%-- <td>
								<c:if test="${!empty item.cardno}">${item.cardno}</c:if>
								<c:if test="${empty item.cardno}">-------</c:if>
							</td> --%>
							<%-- <td>${item.banknameStr}</td> --%>
							<%-- <td>${item.uuid}</td> --%>
						<%-- 	<td>
								<c:if test="${item.isblending==1 or item.ismanblending==1}">
									<font style="color: green">已勾兑</font>
								</c:if> 
								<c:if test="${item.isblending==0 and item.ismanblending==0}">
									<font style="color:">未勾兑</font>
								</c:if>
							</td> --%>
							<td>
								<c:if test="${item.status==1}">
									<font style="color: green">成功</font>
								</c:if> 
								<c:if test="${item.status==2}">
									<font style="color: red">失败</font>
								</c:if> 
								<c:if test="${item.status==3}">
									取消
								</c:if> 
								<c:if test="${item.status==4}">
									<font style="color: blue">待充值</font>
								</c:if>
							</td>
							<td>
							
								<c:if test="${item.isblending==1 or item.ismanblending==1}">
									<c:if test="${item.status==3 and item.urid eq 0 and item.isexceptions==0}">
										<input type="button" onclick="rechargeagain(${item.id})" style="color: blue;" value="再次充值" />
									</c:if>
								</c:if> 
								<c:if test="${item.urid eq 1}">
									已再次充值
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="page_div">
				<%@ include file="../../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>