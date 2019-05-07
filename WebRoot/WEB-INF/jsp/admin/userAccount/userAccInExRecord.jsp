<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<style type="text/css">
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function() {
		/*
		 * 查询所有人
		 */
		$("#query_all_person").click(function() {
			var pageNum = "1";
			var pageSize = "20";
			queryAllPerson(pageNum, pageSize);
		});
		$("#select-type").val("${type}");
	});
	/*
	 * 分页查询所有人收支记录
	 */
	function queryAllPerson(pageNum, pageSize) {

		var accountnumber = $("#accountnumber").val();
		var mobilephone = $("#mobilephone").val();
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		var f = document.getElementById('userForm');
		f.action = "userAccInExRecord.action";
		f.submit();
	}
</script>

<title>Insert title here</title>
</head>
<body style="padding: 20px">
	<div class="container" style="width: 100%;">
		<div class="row clearfix">
			<div class="col-md-12 column">

				<form action="" id="userForm">
					用 户 名 ： <input type="text" id="loginname" name="loginname"
						value="${loginname}" /> 真实姓名：<input type="text" id="realname"
						name="realname" value="${realname}" /> 用 户 号 ： <input type="text"
						id="accountnumber" name="accountnumber" value="${accountnumber}" />
					手 机 号 ： <input type="text" id="mobilephone" name="mobilephone"
						value="${mobilephone}" /> <br><br>托管账户：<input type="text" id="usrcurstid"
						name="usrcustid" value="${usrcustid}" /> 开始时间：<input type="text"
						class="#date" id="recordtimestart" name="recordtimestart"
						value="${recordtimestart}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 结束时间：<input
						type="text" class="#date" id="recordtimeend" name="recordtimeend"
						value="${recordtimeend}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<%-- 邮箱:<input type="text" id="email" name="email" value="${email}" /> --%>
					<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
						type="hidden" id="pageSize" name="pageSize" value="" /> 业务类型： <select
						id="select-type" name="type">
						<option value="">---请选择类型---</option>
						<option value=1>充值</option>
						<option value=2>充值手续费</option>
						<option value=3>提现</option>
						<option value=4>提现手续费</option>
						<option value=5>投标</option>
						<option value=6>还款</option>
						<option value=7>会员升级费</option>
						<option value=8>提现服务费</option>
						<option value=9>站岗利息款</option>
						<option value=10>流标补偿</option>
						<option value=11>逾期罚金</option>
						<option value=12>提前还款奖励</option>
						<option value=13>提前还款罚金</option>
						<option value=14>债转手续费</option>
						<option value=15>居间费</option>
						<option value=16>担保费</option>
						<option value=17>利息管理费</option>
						<option value=18>保证金</option>
						<option value=19>假现金利息</option>
						<option value=20>类现金利息</option>
						<option value=21>加息券利息</option>
						<option value=22>本金利息</option>
						<option value=23>类现金</option>
						<option value=24>本金</option>
						<option value=25>逾期滞纳金</option>
						<option value=26>失效类现金</option>
						<option value=27>失效滞纳金</option>
						<option value=28>失效类现金滞纳金</option>
						<option value=29>失效加息卷利息</option>
						<option value=30>失效类现金利息</option>
					</select>
					<button id="query_all_person" class="btn btn-primary">查询</button>
					<br /> <br />
					<table class="table table-bordered table-hover">
						<thead>
							<tr class="text-center" style="background: #ccc;">
								<td>序号</td>
								<!-- <th>ID</th> -->
								<td>用户名</td>
								<td>真实姓名</td>
								<td>用户号</td>
								<td>手机</td>
								<td>托管账户</td>
								<!-- <td>邮箱</td> -->
								<td>业务类型</td>
								<td>入账金额</td>
								<td>支出金额</td>
								<td>账户余额</td>
								<td>处理时间</td>
								<td>说明</td>
								<td>备注</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagehelper.list}" var="user"
								varStatus="status">

								<tr class="text-center">
									<td>${status.index + 1}</td>
									<%-- <td>${user.id }</td> --%>
									<td>${user.ubai.loginname }</td>
									<td>${user.ubai.realname }</td>
									<td>${user.ubai.accountnumber }</td>
									<td>${user.ubai.mobilephone }</td>
									<td>${user.ufai.usrcustid}</td>
									<%-- <td>${user.ubai.email }</td> --%>
									<td>${state.getUserAccountInExType(user.type) }</td>
									<td><c:if test="${!empty user.inamount }">${df.format(user.inamount )}</c:if></td>
									<td><c:if test="${!empty user.outamount}">${df.format(user.outamount)}</c:if></td>
									<td><c:if test="${!empty user.balance}">${df.format(user.balance)}</c:if></td>
									<td><fmt:formatDate value="${user.recordtime }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${user.description }</td>
									<td>${user.remark }</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<div id="page_div">
						<%@ include file="./../../common/pagehelper.jsp"%>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>