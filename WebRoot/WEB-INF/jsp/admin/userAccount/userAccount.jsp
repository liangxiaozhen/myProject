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
	});
	/*
	 * 分页查询所有人
	 */
	function queryAllPerson(pageNum, pageSize) {
		var accountnumber = $("#accountnumber").val();
		var mobilephone = $("#mobilephone").val();
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		var f = document.getElementById('userForm');
		f.action = "accountInfoList.action";
		f.submit();
	}
	//刷新页面
	function refurbish() {
		queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
	}
	/**
	 * 冻结跳转
	 */
	function djfun(baseid) {
		var action = "${pageContext.request.contextPath}/admin/userAccount/usrFreezeBgList.action";
		var param = {
			"baseid" : baseid
		}
		$.post(action, param, function(returndata) {
			$(".modal-body").html(returndata);
		});
	}
	/* 
	 * 数字验证!验证输入的金额的
	 */
	/* onblur="checkMoney()" onkeyup="checkUp()" */
	//当前元素失去焦点时触发的事件 
	function checkBlur(obj) {
		//为了去除最后一个. 
		obj.value = obj.value.replace(/\.$/g, "");
		var djAmount = $("#djAmount").val();
		if (djAmount != null || djAmount != "") {
			$("#amountlb").text("");
		}
	}
	//当键盘上某个按键被按放开时触发的事件
	function checkUp(event, obj) {
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证只有出现一个.而没有多个. 
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
	}

	function submitDJYZ() {
		var baseid = $("#baseidlb").val();
		var djAmount = $("#djAmountlb").val();
		var action = "${pageContext.request.contextPath}/admin/userAccount/usrFreezeDjyz.action";
		var param = {
			"baseid" : baseid
		}
		$.post(action, param, function(data) {
			var obj = $.parseJSON(data);
			var avlbalance = obj.avlbalance
			if (djAmount == null || djAmount == "") {
				$("#amountlb").text("请输入合法的金额");
			} else {
				if (djAmount > avlbalance) { //冻结金额大于可用余额
					$("#amountlb").text("金额输入错误请重新输入");
					$("#djAmount").val("");
				} else {
					submitDJ();
					$("#frozen").removeAttr("onclick").text("冻结中...");
				}
			}
		});
	}
	/**
	 * 确定冻结
	 */
	function submitDJ() {
		var baseid = $("#baseidlb").val();
		var djAmount = $("#djAmountlb").val();
		var remark = $("#remarklb").val();
		var action = "${pageContext.request.contextPath}/admin/userAccount/usrFreezeBgManager.action"
		var params = {
			"baseid" : baseid,
			"djAmount" : djAmount,
			"remark" : remark
		}
		$
				.post(
						action,
						params,
						function(data) {
							var str = $.parseJSON(data);
							if (str.result == "success") {
								$("#frozen-content").html("");
								$("#frozen-content")
										.html(
												"<font style='color:green;font-size:30px'>冻结成功</font>");
								$("#frozen").attr("onclick", "refurbish()")
										.text("完成");
							}
						});
	}
	//跳转到冻结解冻列表页面
	function usrFreezeSuccess() {
		location.href = "${pageContext.request.contextPath}/admin/UsrUnFreeze/jdqueryAllList.action";
	}
</script>

<title>Insert title here</title>
</head>

<body style="padding: 20px">
	<div class="container" style="width: 100%;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form action="" id="userForm">
					用户名:<input type="text" id="loginname" name="loginname"
						value="${loginname}" /> 真实姓名:<input type="text" id="realname"
						name="realname" value="${realname}" /> 用户号:<input type="text"
						id="accountnumber" name="accountnumber" value="${accountnumber}" />
					手机号:<input type="text" id="mobilephone" name="mobilephone"
						value="${mobilephone}" />
					<%-- 邮箱:<input type="text" id="email" name="email" value="${email}" /> --%>
					<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
						type="hidden" id="pageSize" name="pageSize" value="" />
					<button id="query_all_person" class="btn btn-default">查询</button>
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
								<!-- <th>邮箱</th> -->
								<td>总资产</td>
								<td>可用金额</td>
								<td>冻结金额</td>
								<td>待收金额</td>
								<td>生利宝</td>
								<td>总积分</td>
								<td>可用积分</td>
								<td>冻结积分</td>
								<td>总红包</td>
								<td>可用红包</td>
								<td>冻结红包</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagehelper.list}" var="user"
								varStatus="status">
								<tr  class="text-center">
									<td>${status.index + 1}</td>
									<%-- <td>${user.id }</td> --%>
									<td>${user.ubai.loginname }</td>
									<td>${user.ubai.realname }</td>
									<td>${user.ubai.accountnumber }</td>
									<td>${user.ubai.mobilephone }</td>
									<%-- <td>${user.ubai.email }</td> --%>
									<td>${df.format(user.balance)}</td>
									<td>${df.format(user.avlbalance)}</td>
									<td>${df.format(user.freezebalance )}</td>
									<td>${df.format(user.pendingprincipal )}</td>
									<td>${df.format(user.investProfit )}</td>
									<td>${user.bonuspoints }</td>
									<td>${user.avlbonuspoints }</td>
									<td>${user.freezebonuspoints }</td>
									<td>${df.format(user.redenvelope) }</td>
									<td>${df.format(user.avlredenvelope) }</td>
									<td>${df.format(user.freezeredenvelope )}</td>
									<td><input type="button" class="btn btn-default" data-toggle="modal"
										data-target="#myModal" value="冻结"
										onclick="djfun('${user.baseid}')" /></td>
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

	<!-- 冻结模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">冻结资金</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="submitDJYZ()" id="frozen">冻结</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<!-- 		
<div class="modal fade" id="usrFreezeBgModal" tabindex="-1" role="dialog"
		aria-labelledby="rechargeModalable" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="rechargeModalable">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示
					</h3>
				</div>
				<div id="withdraw-modal-body" class="modal-body" align="center">
					<font size="3">请在冻结完成前不要关闭该窗口</font>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="usrFreezeFail()">选择其他方式</button>
					<button type="button" class="btn btn-primary"
						onclick="usrFreezeSuccess()">已完成冻结</button>
				</div>
			</div>
		</div>
	</div> -->

</body>

</html>