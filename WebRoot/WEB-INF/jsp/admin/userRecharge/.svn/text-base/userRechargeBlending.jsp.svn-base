<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="calendar/WdatePicker.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<title>userRechargeList</title>
<script type="text/javascript">
function dis(id){
	var  action = "admin/userRecharge/queryDetail.action";
	var params = {
			"id" : id
		};
		$.post(action, params, queryRechargeRate_success_callback);
}
/*
 *详情 的回调函数 returnData标识contorller返回的jsonStr字符串
 */
function queryRechargeRate_success_callback(returnData) {
	$("#mymodal").html(returnData);
}
/*
 *详情 的回调函数 returnData标识contorller返回的jsonStr字符串
 */
function requestQuery_success_callback(returnData) {
	$("#requestQuery").html(returnData);
}
/*
 *详情 的回调函数 returnData标识contorller返回的jsonStr字符串
 */
function respsneResult_success_callback(returnData) {
	$("#respsneResult").html(returnData);
}
//编辑修改
function Edit(id){
	var  action = "admin/userRecharge/updateUserRecharge.action";
	var params = {
			"id" : id
		};
		$.post(action, params, updateUserRecharge_success_callback);
}
function updateUserRecharge_success_callback(returnData) {
	$("#editModal").html(returnData);
}
//编辑完后保存
$(function() {
	 $("#updateRecharge").click(function(){
		var action = "admin/userRecharge/update.action";
		var isexceptions = $("#isexceptionsEdit").val();
		var status = $("#statusEdit").val();
		var rechargeno = $("#rechargeno").val();
		var amount = $("#amountlb").val();
		var recharfee = $("#feelb").val();
		var rechargetype = $("#rechargetypelb").val();
		var bankname = $("#banknamelb").val();
		var params = {
				"isexceptions":isexceptions,
				"status":status,
				"rechargeno":rechargeno,
				"recharfee":recharfee,
				"amount":amount,
				"rechargetype":rechargetype,
				"bankname":bankname
		}
		$.post(action,params,function(returnData){
			$.parseJSON(returnData);
			refurbish();
		});
});
	
});
function saveReconciliation(){
	// window.open("${pageContext.request.contextPath}/admin/userRecharge/saveReconciliation.action");
	var action = "${pageContext.request.contextPath}/admin/userRecharge/saveReconciliation.action";
	$.post(action,function(data){
		refurbish();
	}); 
}
function blend(id){//人工勾兑
	if (confirm("请问确定要勾兑吗？")) {
	var  action = "admin/userRecharge/personSaveReconciliation.action";
	var params = {
			"id" : id
		};
		$.post(action, params,function(data){
			refurbish();
		});
	}	
}
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
	}) 
	
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
	$(function(){
		$("#rechargetype_select").val("${userRecharge.rechargetype}");
		$("#originclient_select").val("${userRecharge.originclient}");
		$("#bankname_select").val("${userRecharge.bankname}");
		$("#status_select").val("${userRecharge.status}");
		$("#isexceptions_select").val("${userRecharge.isexceptions}");
		$("#isblending_select").val("${userRecharge.isblending}");
		$("#ismanblending_select").val("${userRecharge.ismanblending}");
		$("#rechargeno_select").val("${userRecharge.rechargeno}");
		$("#bankreturnno_select").val("${userRecharge.bankreturnno}");
		$("#starttime_select").val("${userRecharge.starttimeStr}");
		$("#endtime_select").val("${userRecharge.endtimeStr}");
		$("#syschktime_select").val("${userRecharge.syschktimeStr}");
		$("#checktime_select").val("${userRecharge.checktimeStr}");
		$("#paycompany_select").val("${userRecharge.paycompany}");
	});
	
</script>
</head>

<body style="font-family: 微软雅黑">

	<div class="container" style="width: 95%; margin-left: 35px">
		<h3>充值记录表</h3>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div style="float: left;">
					<form method="post"
						action="${pageContext.request.contextPath}/admin/userRecharge/queryBlending.action"
						id="form-select">
						<div style="margin-top: 8px">
							<input type="hidden" id="pageNum" name="pageNum" /> <input
								type="hidden" id="pageSize" name="pageSize" /> <label>充值方式:&nbsp;</label>
							<select name="rechargetype" id="rechargetype_select">
								<option value="">请选择--</option>
								<option value="1">网银</option>
								<option value="3">企业网银</option>
								<option value="2">快捷支付</option>
								<option value="4">汇款充值</option>
							</select> <label>充值设备:&nbsp;</label> <select name="originclient"
								id="originclient_select">
								<option value="">请选择--</option>
								<option value="1">pc端</option>
								<option value="2">ipad</option>
								<option value="3">App</option>
							</select> <label>充值银行:&nbsp;</label> <select name="bankname"
								id="bankname_select">
								<option value="">请选择--</option>
								<option value="ICBC">中国工商银行</option>
								<option value="ABC">中国农业银行</option>
								<option value="CMB">招商银行</option>
								<option value="CCB">中国建设银行</option>
								<option value="BCCB">北京银行</option>
								<option value="BJRCB">北京农村商业银行</option>
								<option value="BOC">中国银行</option>
								<option value="BOCOM">交通银行</option>
								<option value="CMBC">中国民生银行</option>
								<option value="BOS">上海银行</option>
								<option value="CBHB">渤海银行</option>
								<option value="CEB">中国光大银行</option>
								<option value="CIB">兴业银行</option>
								<option value="CITIC">中信银行</option>
								<option value="CZB">浙商银行</option>
								<option value="GDB">广发银行</option>
								<option value="HKBEA">东亚银行</option>
								<option value="HXB">华夏银行</option>
								<option value="HZCB">杭州银行</option>
								<option value="NJCB">南京银行</option>
								<option value="PINGAN">平安银行</option>
								<option value="PSBC">中国邮政储蓄银行</option>
								<option value="SDB">深圳发展银行</option>
								<option value="SPDB">浦东发展银行</option>
								<option value="SRCB">上海农村商业银行</option>
							</select> <label>充值状态:&nbsp;</label> <select name="status"
								id="status_select">
								<option value="">请选择--</option>
								<option value="1">成功</option>
								<option value="2">失败</option>
								<option value="3">取消</option>
								<option value="4">待充值</option>
							</select>
						</div>
						<div style="margin-top: 8px">
							<label>是否异常:&nbsp;</label> <select name="isexceptions"
								id="isexceptions_select">
								<option value="">请选择--</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select> <label>是否系统勾兑:&nbsp;</label> <select name=isblending
								id="isblending_select">
								<option value="">请选择--</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select> <label>是否人工勾兑:&nbsp;</label> <select name=ismanblending
								id="ismanblending_select">
								<option value="">请选择--</option>
								<option value="0">否</option>
								<option value="1">是</option>
							</select>
						</div>

						<div style="margin-top: 8px">
							<label>支付公司:&nbsp;</label><input type="text"
								id="paycompany_select" name="paycompany" style="width: 80px"
								placeholder="支付公司" /> <label>充值订单号:&nbsp;</label><input
								type="text" id="rechargeno_select" name="rechargeno"
								style="width: 80px" placeholder="充值订单号" /> <label>银行返回充值订单号:</label><input
								type="text" id="bankreturnno_select" name="bankreturnno"
								style="width: 150px" placeholder="银行返回充值订单号" />
						</div>
						<div style="margin-top: 8px">
							<label>充值开始时间:</label>&nbsp;&nbsp;<input type="text"
								id="starttime_select" class="Wdate" name="starttime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
						<div style="margin-top: 8px">
							<label>充值结束时间:</label>&nbsp;&nbsp;<input type="text"
								id="endtime_select" class="Wdate" name="endtime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
						<div style="margin-top: 8px">
							<label>系统勾兑时间:</label>&nbsp;&nbsp;<input type="text"
								id="syschktime_select" class="Wdate" name="syschktime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
						<div style="margin-top: 8px">
							<label>手工对账时间:</label>&nbsp;&nbsp;<input type="text"
								id="checktime_select" class="Wdate" name="checktime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
						<div style="margin-top: 8px"></div>

						<button type="submit" class="btn">查询</button>
						<button type="reset" class="btn">重置</button>
						<div style="float: right; margin-left: 80px;">
							<button class="btn" onclick="saveReconciliation()">系统对账</button>
						</div>
					</form>
				</div>

				<table class="table table-hover" id="userRechargeList_table">
					<thead>
						<tr style="background-color: #CCCCCC;" class="text-center">
							<td style="display: none">ID</td>
							<td>序号</td>
							<td>充值订单号</td>
							<td>充值金额</td>
							<td>手续费</td>
							<!-- <td>充值费率</td> -->
							<td>开始日期</td>
							<td>结束日期</td>
							<td>充值人</td>
							<td>充值方式</td>
							<td>充值银行</td>
							<td>是否异常</td>
							<td>充值状态</td>
							<td>手续费类型</td>
							<!-- <th>充值来源</th>
							<th>支付公司</th> -->
							<td>系统勾兑</td>
							<td>人工勾兑</td>
							<td>详情</td>
							<td>操作</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<!-- 这里面${userRecharge.id }是点的model里面的属性 -->
						<c:forEach items="${pagehelper.list }" var="userRecharge"
							varStatus="status">

							<tr id="userRecharge_tr_${userRecharge.id }" class="text-center">
								<td>${status.count}</td>
								<td style="display: none">${userRecharge.id }</td>

								<td>${userRecharge.rechargeno }</td>

								<td>${df.format(userRecharge.amount)}</td>

								<td>${df.format(userRecharge.recharfee)}</td>

								<%-- <td>${userRecharge.recharrate }</td> --%>

								<td onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									${userRecharge.starttimeStr }</td>
								<td onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									${userRecharge.endtimeStr }</td>

								<td>${userRecharge.ubai.realname}</td>
								<td>
									<c:if test="${userRecharge.rechargetype eq '1'}">网银</c:if>
									<c:if test="${userRecharge.rechargetype eq '2'}">快捷支付</c:if>
									<c:if test="${userRecharge.rechargetype eq '4'}">汇款充值</c:if>
									<c:if test="${userRecharge.rechargetype eq '3'}">企业网银</c:if>
								</td>

								<td>${userRecharge.banknameStr}</td>
								<td><c:if test="${userRecharge.isexceptions==0}">
										<font style="color: green">正常</font>
									</c:if> <c:if test="${userRecharge.isexceptions==1}">
										<font style="color: red">异常</font>
									</c:if></td>

								<td><c:if test="${userRecharge.status==1}">
										<font style="color: green">成功</font>
									</c:if> <c:if test="${userRecharge.status==2}">
										<font style="color: red">失败</font>
									</c:if> <c:if test="${userRecharge.status==3}">
										<font style="color: blue">取消</font>
									</c:if> <c:if test="${userRecharge.status==4}">
										<font style="color: #D19929">待充值</font>
									</c:if></td>
								<td><c:if test="${userRecharge.feeobjflag eq 'U'}">自付</c:if>
									<c:if test="${userRecharge.feeobjflag != 'U'}">他付</c:if></td>
								<%-- <td>
									${userRecharge.originclientStr}
								</td>
								
								<td>
									${userRecharge.paycompany}
								</td> --%>
								<td><c:if test="${userRecharge.isblending==1}">
										<font style="color: green">已勾兑</font>
									</c:if> <c:if test="${userRecharge.isblending==0}">未勾兑</c:if></td>
								<td><c:if test="${userRecharge.ismanblending==1}">
										<font style="color: green">已勾兑</font>
									</c:if> <c:if test="${userRecharge.ismanblending==0}">未勾兑</c:if></td>

								<%-- <td><p class="remark-td text-center" title="${userRecharge.remark }">${userRecharge.remark }</p></td>  --%>
								<td>
									<button class="btn" data-backdrop="static" data-toggle="modal"
										data-dismiss="modal" data-target="#myModal"
										onclick="dis('${userRecharge.id}')">查看</button>
								</td>
								<td>
									<c:if test="${userRecharge.ismanblending==0}">
										<button class="btn" onclick="blend('${userRecharge.id}')">勾兑</button>
									</c:if>
									<c:if test="${userRecharge.ismanblending==1}">
										已勾兑
									</c:if>
									<c:if test="${userRecharge.isexceptions eq 1 }">
										<button class="btn" data-backdrop="static" data-toggle="modal"
											data-dismiss="modal" data-target="#EditModal"
											onclick="Edit('${userRecharge.id}')">编辑</button>
									</c:if>
								</td>
								<td><p class="remark-p text-center" data-toggle="tooltip"
										title="<h5>${userRecharge.remark}</h5>" limit="5">${userRecharge.remark}</p></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="./../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="EditModal" tabindex="-1" role="dialog"
		aria-labelledby="EditModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="EditModalLabel">编辑</h4>
				</div>
				<div class="modal-body" id="editModal"></div>
				<div class="modal-footer">
					<button type="button" class="btn" id="updateRecharge">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
