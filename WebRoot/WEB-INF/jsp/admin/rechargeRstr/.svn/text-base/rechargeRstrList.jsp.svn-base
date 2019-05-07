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
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/dateproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/moneyproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/rechargerstr/rechargerstr.js"></script>
<title>itemList</title>
<style>
table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
  /* 验证备注显示个数*/
$(function(){
  		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
  		$(".remark-p").each(function(i) {
			var num = $(this).html();
			if (num.length > 5) {
				$(this).html(num.substr(0, 5) + "...");
			}
		});
			$("#ugrade-serlect").val("${rechargeRstr.ugrade}");
			$("#rechartype_select").val("${rechargeRstr.rechartype}");
			$("#paycompany_select").val("${rechargeRstr.paycompany}");
			$("#daymoneyrest_select").val("${rechargeRstr.daymoneyrest}");
			$("#lowestmoney_select").val("${rechargeRstr.lowestmoney}");
			$("#hightestmoney_select").val("${rechargeRstr.hightestmoney}");
			$("#addtime_select").val("${rechargeRstr.addtimeStr}");
			$("#audittime_select").val("${rechargeRstr.audittimeStr}");
			$("#isuse_select").val("${rechargeRstr.isuse}");
			$("#isaudit_select").val("${rechargeRstr.isaudit}");
 });
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
</script>
</head>

<body style="font-family: 微软雅黑">

	<div id="itemList_table">
		<div style="margin-left: 40px; font-weight: bold;">充值设置限制</div>
		<div style="float: left; margin-left: 25px; width: 90%">
			<form id="form-select" method="post"
				action="${pageContext.request.contextPath}/admin/rechargeRstr/queryAll.action">
				<div style="margin-left: 20px">
					<br /> <input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>会员等级:</label>
					<select name="ugrade" id="ugrade-serlect" style="width: 80px">
						<option value="">请选择--</option>
						<c:forEach items="${ugrades}" var="item">
							<option value="${item.ugrade}">${item.ugradename}</option>
						</c:forEach>
					</select> <label>充值方式:</label> <select name="rechartype"
						id="rechartype_select">
						<option value="">请选择--</option>
						<option value="1">网银</option>
						<option value="2">企业网银</option>
						<option value="3">快捷</option>
						<option value="4">汇款充值</option>
					</select> <label>是否启用:</label> <select name="isuse" id="isuse_select">
						<option value="">请选择--</option>
						<option value="0">停用</option>
						<option value="1">启用</option>
					</select> <label>审核状态:</label> <select name="isaudit" id="isaudit_select">
						<option value="">请选择--</option>
						<option value="0">待审核</option>
						<option value="1">审核通过</option>
					</select> <label>日限制:&nbsp;</label><input name="daymoneyrest"
						id="daymoneyrest_select" onblur="checkBlur(obj)"
						onkeyup="checkUp(event,this)" style="width: 90px"
						placeholder="日充值金额" /> <label>金额范围:&nbsp;</label><input
						name="lowestmoney" id="lowestmoney_select" onblur="checkBlur(obj)"
						onkeyup="checkUp(event,this)" style="width: 90px"
						placeholder="单笔最低金额" /> -----<input name="hightestmoney"
						id="hightestmoney_select" style="width: 90px"
						onblur="checkBlur(obj)" onkeyup="checkUp(event,this)"
						placeholder="单笔最高金额" />
				</div>
				<div style="margin-left: 20px; font-size: 10px; font-weight: bold;">
					<button type="submit" class="btn">查询</button>
					<button type="reset" class="btn">重置</button>
					<!--  btn-primary btn-sm -->
				</div>
			</form>
			<div style="margin-left: 20px; margin-bottom: 5px; float: right">
				<button class="btn" style="margin-left: 100%"
					onclick="insertRechargeRstr()">新增设置表</button>
			</div>
		</div>

		<br />
		<br />
		<br />
		<div style="width: 95%; margin-left: 40px">
			<table class="table table-hover" id="tableterm">
				<thead>
					<tr style="background-color: #CCCCCC;" class="text-center2">
						<td>序号</td>
						<td>会员等级</td>
						<td>充值方式</td>
						<td>日总额</td>
						<td>单笔最低
							<hr />单笔最高
						</td>
						<!-- <td>支付公司/银行</td> -->
						<td>代付人</td>
						<td>充值人自付</td>
						<td>平台代付</td>
						<td>手续费类型</td>
						<td>是否模板</td>
						<td>是否审核</td>
						<td>审核状态</td>
						<td>定向名单号</td>
						<td>详情</td>
						<td>备注</td>
						<td>操作</td>
						<td>使用状态</td>
					</tr>
				</thead>
				<tbody id="text">
					<!-- 这里面${item.id }是点的model里面的属性 -->
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

						<tr id="item_tr_${item.id }" class="text-center2">
							<td>${status.count}</td>
							<td>
								<p class="remark-p text-center" data-toggle="tooltip"  title="${item.ugradeStr}">${item.ugradeStr}</p>
							</td>
							<td>
								<c:if test="${item.rechartype eq '0'}">网银</c:if> 
								<c:if test="${item.rechartype eq '1'}">代扣充值</c:if> 
								<c:if test="${item.rechartype eq '2'}">快捷支付</c:if> 
								<c:if test="${item.rechartype eq '3'}">汇款充值</c:if> 
								<c:if test="${item.rechartype eq '4'}">企业网银</c:if>
							</td>
							<td>${df.format(item.daymoneyrest)}</td>
							<td>${df.format(item.lowestmoney)}<hr />${df.format(item.hightestmoney)}</td>
							<td>
								<c:if test="${item.proxypayman eq '0'}">-----</c:if> 
								<c:if test="${item.proxypayman eq 'MDT000001'}">莫邪软件</c:if> 
								<c:if test="${item.proxypayman eq 'SDT000001'}">担保账户1</c:if> 
								<c:if test="${item.proxypayman eq 'SDT000002'}">风险保证金账户1</c:if> 
								<c:if test="${item.proxypayman eq 'BASEDT'}">BASEDT</c:if>
							</td>

							<td>${item.selfpayratio}%</td>
							<td>${item.proxypayratio}%</td>
							<%-- <td>${item.paycompany}</td> --%>
							<td>
								<c:if test="${item.feetype eq '0'}">无</c:if> 
								<c:if test="${item.feetype eq '1'}">自付</c:if> 
								<c:if test="${item.feetype eq '2'}">他付</c:if> 
							</td>
							<td>${item.istempletStr}</td>
							<%-- <td>${item.removenamenoStr}</td> --%>
							<td>
								<c:if test="${item.isaudit eq 1}">
									<p style="color: green">是</p>
								</c:if> 
								<c:if test="${item.isaudit eq 0}">
									<p style="color: red">否</p>
								</c:if></td>
							<td>
								<c:if test="${item.isaudit eq 1}">
									<p style="color: green">${item.isauditStr}</p>
								</c:if> 
								<c:if test="${item.isaudit eq 0}">
									<p style="color: red">${item.isauditStr}</p>
								</c:if>
							</td>
							<td>${item.snlid}</td>
							<td>
								<button class="btn" data-backdrop="static" data-toggle="modal"
									data-dismiss="modal" data-target="#myModal"
									onclick="detalis('${item.id}')">详情</button>
							</td>
							<td>
								<p class="remark-p text-center" data-toggle="tooltip"
									title="${item.remark }">${item.remark }</p>
							</td>

							<td>
								<div class="btn-group" role="group" aria-label="...">
									<c:if test="${ item.isuse eq 1 or item.isuse eq 0}">
										<button class="btn" onclick="editFun('${item.id}')">编辑</button>
									</c:if>
									<c:if test="${item.isaudit eq 0 }">
										<button class="btn" data-toggle="modal"
											data-target="#delModal" data-backdrop="static" id="del-btn"
											onclick="deleteFun('${item.id}')">删除</button>
									</c:if>
									<c:if test="${item.isaudit eq 0 }">
										<button class="btn" data-backdrop="static" data-toggle="modal"
											data-target="#auditModal" id="audit-btn"
											onclick="forwardAuditUI('${item.id }')">审核</button>
									</c:if>
								</div>
							</td>
							<td><c:if test="${item.isaudit eq 1 }">
									<c:if test="${item.isuse eq 0 }">
										<button class="btn" data-backdrop="static" data-toggle="modal"
											data-target="#isuseModal"
											onclick="forwardIsUserUI('${item.id}')">
											<span style="color: green">设置启用</span>
										</button>
									</c:if>
									<c:if test="${item.isuse eq 1 }">
										<button class="btn" data-backdrop="static" data-toggle="modal"
											data-target="#cancelUseModal"
											onclick="forwardCancelUserUI('${item.id}')">
											<span style="color: red">设置停用</span>
										</button>
									</c:if>
								</c:if> 
								<c:if test="${item.isaudit eq 0 }">
										需审核
								</c:if>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="page_div">
				<%@ include file="./../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>

	<!-- 详情模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none">
		<div class="modal-dialog" role="document">
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
	<!-- 审核模态框 -->
	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
		aria-labelledby="auditModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="auditModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行审核操作
					</h4>
				</div>
				<div id="audit-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="auditSuccess()">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 启用 -->
	<div class="modal fade" id="isuseModal" tabindex="-1" role="dialog"
		aria-labelledby="isuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="isuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="isuseid"> 您是否启用 ID : <label
						id="isuseidlb"></label> 的设置？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 停用 充值设置模态框 -->
	<div class="modal fade" id="cancelUseModal" tabindex="-1" role="dialog"
		aria-labelledby="cancelUseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cancelUseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="canceluseid"> 您是否停用 ID : <label
						id="canceluseidlb"></label> 的记录？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
