<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<style>
table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 2px solid #666;
}
</style>
<title>userRechargeList</title>
<script type="text/javascript">
	//刷新页面
	function refurbish() {
		//window.location.href = "${pageContext.request.contextPath}/rechargeRstr/queryAll.action?pageNum=${pagehelper.pageNum}";
		queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
	}
	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#form-select").submit();
	}
	/* 编辑*/
	function editFun(id) {
		var action = "${pageContext.request.contextPath}/admin/userGrade/queryEdits.action";
		var params = {
			"id" : id,
		};
		var callback = function(data) {
			$("#myupdatebody").html(data);
		}
		$.post(action, params, callback);
	}
	//修改
	function update_userGrade() {
		var action = "${pageContext.request.contextPath}/admin/userGrade/update.action";
		var ugrade = $("#ugrade_code").val();
		var idlb = $("#idlb").val();
		var ugradename = $("#ugradenamelb").val();
		var remark = $("#remarklb").val();
		var params = {
			"id":idlb,
			"ugrade" : ugrade,
			"ugradename" : ugradename,
			"remark" : remark
		};
		$.post(action, params, function updateUserGrade_success_callback(returnData){
			refurbish();
		});
	}
	/*
	 * 点击删除按钮执行此函数
	 * 	函数功能：提交	ajax请求
	 */
	function deleteFun(id) {
		/* 删除的时候弹出一个对话框,选择确定的时候执行删除,选择取消的时候返回空*/
		if (confirm("你正在删除id为" + id + "的数据")) {
			window.location.href = "${pageContext.request.contextPath}/admin/userGrade/queryEdits.action?id="
				+ id;
		}
	}
	/*
	 * 删除成功后的回调函数 returnData标识contorller返回的jsonStr字符串
	 */
	function deleteRechargeRate_success_callback(returnData) {
		var obj = $.parseJSON(returnData);
		if (obj.result == "success") {
			$("#rechargeRate_tr_" + obj.rechargeRateId).remove();
			refurbish();

		} else {
			alert("删除失败!");
			alert(obj.result);
		}
	}
	$(function() {
		$("#ugrade_select").val("${userGrade.ugrade}");
		$("#addtime_select").val("${userGrade.addtimeStr}");
			$("#insert_userGrade").click(function() {
				var action = "admin/userGrade/save.action";
				var ugrade = $("#ugrade").val();
				var ugradename = $("#ugradename").val();
				var remark = $("#remark").val();
				if ((ugrade != null && ugrade != '')&& (ugradename != null && ugradename != '')) {
					var params = {
						"ugrade" : ugrade,
						"ugradename" : ugradename,
						"remark" : remark
					};
				}else{
					$("#ugrade_bixu").hide();
					$("#ugradename_bixu").hide();
					$("#ugrade_validata").show();
					$("#provingname").show();
					return false;
				}
				$.post(action, params, saveUserGrade_success_callback);

			});
		function saveUserGrade_success_callback(returnData) {
			$.parseJSON(returnData);
			refurbish();
		}
		
		$("[limit]").limit();
	});
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				$(this).attr("title", objString);
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}
	/* onblur="checkMoney()" onkeyup="checkUp()" */
	//当前元素失去焦点时触发的事件 
	function checkBlur(obj) {
		//为了去除最后一个. 
		obj.value = obj.value.replace(/\.$/g, "");
		var params = $("#ugrade").val();
		if (params != null && params != '') {
			$("#ugrade_bixu").hide();
			$.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/admin/userGrade/queryByuGrade.action",
						data : {
							"params" : params
						},
						success : function(data) {
							var obj = $.parseJSON(data);
							if (obj.result == "error" || params > 30) {
								$("#ugrade_validata").show();
								$("#ugradename").attr('disabled', true);
							} else {
								$("#ugrade_validata").hide();
								$("#ugradename").attr('disabled', false);

							}
						}
					});
		} else {
			$("#ugrade_validata").show();
			$("#ugradename").attr('disabled', true);
		}

	}
	//验证会员名称
	function checkBlur2(obj) {
		var params = $("#ugradename").val();
		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/admin/userGrade/queryByuGradename.action",
					data : {
						"params" : params
					},
					success : function(data) {
						var obj2 = $.parseJSON(data);
						if (obj2.result == "error" || params == null
								|| params == '') {
							$("#ugradename_bixu").show();
							$("#provingname").show();
						} else {
							$("#provingname").hide();
							$("#ugradename_bixu").hide();
						}
					}
				});
	}
/* 	function checkBlur5(obj) {
		var params = $("#remark").val();
		if (params == null || params == '') {
			$("#remarkError").show();
		} else {
			$("#remarkError").hide();
		}

	} */
	//当键盘上某个按键被按放开时触发的事件
	function checkUp(event, obj) {
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//保证输入的金额不是0
		obj.value = obj.value.replace(/^0*/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证只有出现一个.而没有多个. 
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	}
</script>
</head>

<body style="font-family: 微软雅黑">

	<div class="container">
		<h3>商户账户查询</h3>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-hover" id="userGradeList_table">
					<thead>
						<tr class="text-center2" style="background-color: #CCCCCC">
							<td>账户类型</td>
							<td>子账户</td>
							<td>总额</td>
							<td>可用余额</td>
							<td>冻结金额</td>
						</tr>
					</thead>
					<tbody>
						<!-- 这里面${userRecharge.id }是点的model里面的属性 -->
						<c:forEach items="${acctDetails}" var="mac">

							<tr class="text-center2">
								<td><c:if test="${mac.accttype eq 'MERDT'}">专属借记账户</c:if> <c:if
										test="${mac.accttype eq 'SPEDT'}">专属账户</c:if> <c:if
										test="${mac.accttype eq 'BASEDT'}">基本借记账户</c:if></td>

								<td><c:if test="${mac.subacctid eq 'MDT000001'}">莫邪软件</c:if>
									<c:if test="${mac.subacctid eq 'SDT000001'}">担保账户1</c:if> <c:if
										test="${mac.subacctid eq 'SDT000002'}">风险保证金账户1</c:if> <c:if
										test="${mac.subacctid eq 'BASEDT'}">BASEDT</c:if></td>

								<td>${df.format(mac.acctbal)}</td>

								<td>${df.format(mac.avlbal)}</td>

								<td>${df.format(mac.frzbal)}</td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
