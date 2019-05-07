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
<title>Insert title here</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
<style type="text/css">
ul li {
	font-size: 16px;
}

#withdraw_btn_tip {
	text-decoration: none;
}

.account_table2 {
	width: 760px;
	min-height: 630px;
	margin-top: 20px;
}

.account_notice {
	width: 760px;
	height: auto;
	padding: 20px;
	border: #d7d4d5 solid 1px;
	position: relative;
}

.table {
	border: #d7d4d5 solid 1px;
}

#tab1 tr td {
	border: #d7d4d5 solid 1px;
}

#tab2 tr th {
	text-align: center;
}

.tab-content {
	margin-top: 20px;
}

.font-red {
	color: red;
}

.left {
	text-align: right;
}

.deposit_title {
	width: 161px;
	height: 40px;
}

.deposit_content {
	width: 638px;
	height: 40px;
}

.right {
	text-align: left;
}
</style>
<script type="text/javascript">
	//用户取消提现
	function cancelWithdraw(cashno) {
		if (confirm("提示：您确定取消提现吗？")) {
			var action = "cancelWithdraw.action";
			var params = {
				"cashno" : cashno
			};
			var callback = function(data) {
				switch (data) {
				case "success":
					queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
					break;
				case "fail":
					alert("提示:操作失败");
					break;
				}
			};
			$.post(action, params, callback, 'json');
		}
	};
	//提现校验
	function drawmoney() {
		var amount = $("#txt_Amount").val();
		var max=${userAccount.balance };
		if (parseInt(amount) < 100) {
			$("#txt_Amount_span").text("提现金额不能少于100");
			return false;
		}
		if (parseInt(amount) >5000000) {
			$("#txt_Amount_span").text("提现金额不能大于5,000,000");
			return false;
		}
		if (parseInt(amount) > max) {
			$("#txt_Amount_span").text("余额不足，请重新输入。");
			return false;
		}
		if (amount === "" || amount == null || isNaN(amount)) {
			$("#txt_Amount_span").text("请输入正确的提现金额，如1063.20。");
			return false;
		}
		if ($("#txt_smsCode").val() == "") {
			$("#txt_smsCode").focus();
			$("#txt_smsCode_span").text("您好,验证码格式错误,请输入正确的验证码！");
			return false;
		}
		return true;
	}
	function withCash() {
		if (drawmoney()) {
			var action = "checkDrawMoney.action";
			var params = $("#withcashform").serialize();
			var callback=function(data){
				if(data.info=="success")
				{
					$("#withDrawModal").css({
						margin : '200px auto'
					}).modal({
						backdrop : 'static',	
						keyboard : false
					}).modal('show');
				window.open("drawMoney.action?params="+data.code);
				}else{
					alert(data.info);
				}
			}
			$.post(action,params,callback,'json');
		}
	}
	function withdrawSuccess() {
		location.href = "${pageContext.request.contextPath }/user/userAccInExRecord/queryAll.action";
	}
	function withdrawFail() {
		location.href = "withdraw.action";
	}
	//显示备注信息
	function showMessage(msg) {
		alert(msg);
	}
	//跳转页面	
	function queryAllPerson(pageNum, pageSize) {
		location.href = "withdraw.action?pageNum=" + pageNum + "&pageSize="
				+ pageSize;
	}
</script>
</head>
<body>
	<div style="margin-left: 400px; margin-top: 50px;">
		<input type="hidden" id="tabVal" value="${tabDiv }">
		<!-- Nav tabs -->
		<ul id="withdrawtab" class="nav nav-tabs" role="tablist"
			style="width: 760px; height: auto;">
			<li role="presentation" class="active"><a href="#withdraw"
				aria-controls="home" role="tab" data-toggle="tab">马上提现</a></li>
			<li role="presentation"><a href="#withdrawnote"
				aria-controls="profile" role="tab" data-toggle="tab">提现记录</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane fade in active" id="withdraw">
				<jsp:include page="/WEB-INF/jsp/user/withdraw/userwithdraw.jsp"></jsp:include>
			</div>
			<!-- 提现记录 -->
			<div role="tabpanel" class="tab-pane fade" id="withdrawnote">
				<jsp:include page="/WEB-INF/jsp/user/withdraw/userwithdrawnote.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<!-- 确认提现   模态框	-->
	<div class="modal fade" id="withDrawModal" tabindex="-1" role="dialog"
		aria-labelledby="withDrawModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="withDrawModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示
					</h3>
				</div>
				<div id="withdraw-modal-body" class="modal-body" align="center">
					<font size="3">请在新打开的汇付天下页面进行提现,提现完成前不要关闭该窗口</font>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="withdrawFail()">提现遇到问题</button>
					<button type="button" class="btn btn-primary"
						onclick="withdrawSuccess()">已完成提现</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
	<script type="text/javascript">
		var tabVal = $("#tabVal").val();
		if (tabVal != "")
			$("#withdrawtab a:last").tab('show');
		$("#txt_Amount").change(function() {
			$("#txt_Amount_span").text("");
		});
		$("#txt_Password").change(function() {
			$("#txt_Password_span").text("");
		});
		$("#txt_smsCode").change(function() {
			$("#txt_smsCode_span").text("");
		});
	</script>
</body>
</html>