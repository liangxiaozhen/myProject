<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/dateproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/moneyproving.js"></script>
<style>
table thead tr th {
	text-align: center;
}

#beizhu {
	width: 100px;
	font-size: 10px
}
</style>
<script>
jQuery.fn.limit=function(){
    var self = $("[limit]");
    self.each(function(){
        var objString = $(this).text();
        var objLength = $(this).text().length;
        var num = $(this).attr("limit");
        if(objLength > num){
            $(this).attr("title",objString);
            objString = $(this).text(objString.substring(0,num) + "...");
        }
    })
}
$(function(){
    $("[limit]").limit();
	$("[data-toggle='tooltip']").tooltip({
			html : true
		 }) ;
});
/**
 * 分页
 */
function queryAllPerson(pageNum, pageSize){
	var loginname = $("#loginname").val();
	var aftorderno = $("#aftorderno").val();
	var amount = $("#amount").val();
	var username = $("#username").val();
	var startrecordtime = $("#start_recordtime").val();
	var endrecordtime = $("#end_recordtime").val();
	var status = $("#status_select").val();
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	var f = document.getElementById('UsrUnFreezeForm');
	f.action =  "${pageContext.request.contextPath}/admin/UsrUnFreeze/UsrUnFreezeRecord.action";
	f.submit();
}

/**
 *导航栏数据回显
 */
$(function(){
	$("#loginname").val("${accountfreezethaw.loginname}");
	$("#aftorderno").val("${accountfreezethaw.aftorderno}");
	$("#amount").val("${accountfreezethaw.amount}");
	$("#username").val("${accountfreezethaw.username}");
	$("#start_recordtime").val("${accountfreezethaw.startrecordtimeStr}");
	$("#end_recordtime").val("${accountfreezethaw.endrecordtimeStr}");
	$("#status_select").val("${accountfreezethaw.status}");
});
/**
 * 详情跳转页面
 */
function xiangqing(aftorderno){
   var	action ="${pageContext.request.contextPath}/admin/UsrUnFreeze/xiangqing.action";
   var param={
		   "aftorderno":aftorderno
   }
   $.post(action,param,function(returndata){
	   $("#myModal2").modal({
			backdrop : 'static',
			keyboard : false
		});
	   $("#xiangqing").html(returndata);
   });
}


</script>
</head>

<body style="font-family: 微软雅黑">
	<div class="container" style="width: 100%;">
		<div class="row clearfix">
			<div class="col-md-12 column">

				<form action="" id="UsrUnFreezeForm">
					<br /> 用户名:<input type="text" id="loginname" name="loginname" />
					姓名:<input type="text" id="username" name="username" /> 订单号:<input
						type="text" id="aftorderno" name="aftorderno" /> 冻结金额:<input
						type="text" id="amount" name="amount" /> 状态:<select name="status"
						id="status_select">
						<option value="">请选择--</option>
						<option value=1>已冻结</option>
						<option value=2>未冻结</option>
						<option value=3>已解冻</option>
					</select> 冻结时间:<input type="text" class="Wdate" name="startrecordtime"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="start_recordtime" /> ---<input type="text" class="Wdate"
						name="endrecordtime"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="end_recordtime" />
					<button id="query_all_person">查询</button>
					<button type="reset">重置</button>
					<br /> <br /> <input type="hidden" id="pageNum" name="pageNum"
						value="" /> <input type="hidden" id="pageSize" name="pageSize"
						value="" />

					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>用户名</th>
								<th>姓名</th>
								<th>订单号</th>
								<th>冻结金额</th>
								<th>可用余额</th>
								<th>冻结总额</th>
								<th>总资产</th>
								<th>状态</th>
								<th>冻结时间</th>
								<th>备注</th>
								<th>查看</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagehelper.list}" var="aft"
								varStatus="status">
								<tr align="center">
									<td>${status.count}</td>
									<td>${aft.loginname}</td>
									<td>${aft.username}</td>
									<td>${aft.aftorderno}</td>
									<td>${df1.format(aft.amount)}</td>
									<td>${df1.format(aft.avlbalance)}</td>
									<td>${df1.format(aft.freezebalance)}</td>
									<td>${df1.format(aft.balance)}</td>
									<td><c:if test="${aft.status eq 1}">已冻结</c:if> <c:if
											test="${aft.status eq 2}">未冻结</c:if> <c:if
											test="${aft.status eq 3}">
											<font style="color: green">已解冻</font>
										</c:if></td>
									<td>${sf.format(aft.recordtime)}</td>
									<td id="beizhu"><p class="remark-p text-center"
											data-toggle="tooltip" title="<h5>${aft.remark}</h5>"
											limit="10">${aft.remark}</p></td>
									<td><input type="button"
										onclick="xiangqing('${aft.aftorderno}')" value="详情" /></td>

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
	<!-- 详情模态框 -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body" id="xiangqing"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 解冻模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">解冻资金</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="submitJD()">解冻</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>
