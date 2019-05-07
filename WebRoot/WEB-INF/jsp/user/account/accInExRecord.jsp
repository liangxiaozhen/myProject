<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<style type="text/css">
body {
	padding: 40px;
	padding-left: 50px;
	font-size: 12px;
}

.fund-bottom-inner {
	width: 100%;
	border-collapse: collapse;
	border-spacing: 0;
}

.fund-bottom-inner tr td {
	height: 60px;
	font-size: 12px;
	text-align: center !important;
}

.fund-bottom-inner tr td.text-left {
	text-align: left !important;
}

.fund-bottom-inner tr th {
	text-align: center;
	height: 50px;
	color: #fff;
	font-size: 14px;
	font-weight: 400;
}

.screen_submit {
	font-size: 14px;
	padding: 4px 10px;
	cursor: pointer;
	color: #fff;
	border-radius: 4px;
	background: #ff2c01;
	border: 0;
	outline: none;
}

.fund-bg {
	height: 61px;
}

.clickRedbg {
	padding: 2px 6px;
	background: #ff5736;
	border-radius: 4px;
	color: #fff;
}

.grayFont {
	width: 230px;
}

a:link, a:visited, a:active {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
	color: #ff5736;
}
</style>
<script type="text/javascript">
	$(function() {
		var type = ${type};
		if (type != 0) {
			$(".fund-middle-title").find("span").each(function(index, value) {
				$(this).removeClass("clickRedbg");
				if (index == type) {
					$(this).addClass("clickRedbg");
				}
			});
		}
	})
	function queryAllPerson(pageNum, pageSize) {
		// 查询所有数据
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#form-select").submit();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<font size="6">收支明细</font>
				<hr>
				<table style="height: 40px;" class="fund-middle-title ">
					<tbody>
						<tr>
							<th
								style="font-weight: bold; font-size: 14px; color: #1a1f25; text-align: left; width: 100px;">资金类型</th>
							<th style="width: 76px;"><a href="queryAll.action"> <span
									class="clickRedbg">全部</span></a></th>
							<th style="width: 76px;"><a href="queryAll.action?type=1"><span>充值</span></a></th>
							<th style="width: 100px;"><a href="queryAll.action?type=2"><span>充值手续费</span></a></th>
							<th style="width: 76px;"><a href="queryAll.action?type=3"><span>提现</span></a></th>
							<th style="width: 100px;"><a href="queryAll.action?type=4"><span>提现手续费</span></a></th>
							<th style="width: 76px;"><a href="queryAll.action?type=5"><span>投标</span></a></th>
							<th style="width: 76px;"><a href="queryAll.action?type=6"><span>还款</span></a></th>
						</tr>
					</tbody>
				</table>
				<form id="form-select" action="queryAll.action" method="get">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <input
						type="hidden" name="type" value="${type }" />
					<table style="height: 40px;">
						<tr>
							<td
								style="font-weight: bold; font-size: 14px; color: #1a1f25; text-align: left; width: 100px;">时间范围</td>
							<td><input type="text" name="btime" class="Wdate"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
								--</td>
							<td><input type="text" name="etime" class="Wdate"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">&nbsp;&nbsp;</td>
							<td><input type="submit" class="screen_submit" value="筛选"></td>
						</tr>
					</table>
				</form>
				<table style="width: 1138px;"
					class="table table-condensed table-striped fund-bottom-inner">
					<tr
						style="background: #ff715f; border-bottom: 1px solid #ff5641; height: 50px;">
						<th style="vertical-align: middle; text-align: center;">订单号</th>
						<th
							style="vertical-align: middle; text-align: center; width: 103px;">类型</th>
						<th
							style="vertical-align: middle; text-align: center; width: 169px;">交易时间</th>
						<th
							style="vertical-align: middle; text-align: center; width: 126px;">交易金额</th>
						<th
							style="vertical-align: middle; text-align: center; width: 126px;">冻结余额</th>
						<th
							style="vertical-align: middle; text-align: center; width: 126px;">可用余额</th>
						<th
							style="vertical-align: middle; text-align: center; width: 126px;">总余额</th>
						<th
							style="vertical-align: middle; text-align: center; width: 97px;">摘要</th>

					</tr>
					<c:forEach var="accIn" items="${pagehelper.list }"
						varStatus="status">
						<tr class="fund-bg">
							<td style="vertical-align: middle; text-align: center;"><span>${accIn.aieorderno }</span></td>
							<td style="vertical-align: middle; text-align: center;"><span>${state.getUserAccountInExType(accIn.type)}</span></td>
							<td style="vertical-align: middle; text-align: center;"><span><fmt:formatDate
										value="${accIn.recordtime }" pattern="yyy-MM-dd HH:mm:ss" /></span></td>
							<td style="vertical-align: middle; text-align: center;"><c:if
									test="${accIn.inamount eq 0 and accIn.outamount != 0 }">
									<span style="color: red">-&nbsp;${df.format(accIn.outamount) }</span>
								</c:if> <c:if test="${accIn.outamount eq 0 and accIn.inamount != 0}">
									<span style="color: green">+&nbsp;${df.format(accIn.inamount) }</span>
								</c:if> <c:if test="${accIn.inamount eq 0 and accIn.outamount eq 0 }">0.00</c:if>
							</td>
							<td style="vertical-align: middle; text-align: center;"><c:if
									test="${empty accIn.freebalance}">0.00</c:if> <c:if
									test="${!empty accIn.freebalance}">${df.format(accIn.freebalance)}</c:if>
							</td>
							<td style="vertical-align: middle; text-align: center;"><c:if
									test="${empty accIn.balance}">0.00</c:if> <c:if
									test="${!empty accIn.balance}">${df.format(accIn.balance )}</c:if>
							</td>
							<td style="vertical-align: middle; text-align: center;"><c:if
									test="${empty accIn.totalbalance}">0.00</c:if> <c:if
									test="${!empty accIn.totalbalance}">${df.format(accIn.totalbalance )}</c:if>
							</td>
							<td style="vertical-align: middle; text-align: center;"><c:choose>
									<c:when test="${accIn.status eq 0 }">冻结</c:when>
									<c:when test="${accIn.status eq 1 }">
										<span style="color: green">成功</span>
									</c:when>
									<c:when test="${accIn.status eq 2 }">
										<span style="color: red">失败</span>
									</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>