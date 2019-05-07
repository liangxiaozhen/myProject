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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/dateproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/moneyproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/rechargerate/rechargerate.js"></script>
<title>itemList</title>
<style>
 table tr td {
	text-align: center;
}
td {
	border: 1px solid #666;
	padding: 4px 0px !important;
	vertical-align: middle !important;
}
.t-center {
	vertical-align: text-top !important;
}


</style>
<script type="text/javascript">
  /* 验证备注显示个数*/
  	$(function(){
		$("[data-toggle='tooltip']").tooltip({
		html : true
	}) ;
		$(".remark-p").each(function(i) {
		var num = $(this).html();
		if (num.length > 5) {
			$(this).html(num.substr(0, 5) + "...");
		}
	});
		$("#ugrade_serlect").val("${rechargeRate.ugrade}");
		$("#rechartype_select").val("${rechargeRate.rechartype}");
		$("#isuse_select").val("${rechargeRate.isuse}");
		$("#isaudit_select").val("${rechargeRate.isaudit}");
		$("#chargetype_select").val("${rechargeRate.chargetype}");
		$("#bankcode_select").val("${rechargeRate.bankcode}");
		$("#minmoney_select").val("${rechargeRate.minmoney}");
		$("#maxmoney_select").val("${rechargeRate.maxmoney}");
		$("#addtime_select").val("${rechargeRate.addtimeStr}");
		$("#audittime_select").val("${rechargeRate.audittimeStr}");
		$("#paycompany_select").val("${rechargeRate.paycompany}");
});
  //刷新页面
	function refurbish() {
		window.location.href = "${pageContext.request.contextPath }/admin/rechargeRate/query.action?pageNum=${pagehelper.pageNum}";
	   //queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
	}
	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#form-select").submit();
	}
	/*详情*/
	 function detalis(id){
			var  action = "${pageContext.request.contextPath}/admin/rechargeRate/queryDetails.action";
			var params = {
					"id" : id
				};
				$.post(action, params,function queryRechargeRate_success_callback(returnData) {
		  			$(".modal-body").html(returnData);
		  		});
		}
		function updateFun(id){
			window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/queryEdits.action?id="+id
		}
			
</script>
</head>

<body style="font-size: 12px;font-family: 微软雅黑">

	<div id="itemList_table" class="container" style="width: 80%;">
		<br />
		<h5>充值费率</h5>
		<br/>
		<div>
			<!--  <div style="">
				
				<div style="margin-left: 100%; margin-bottom: 2px; float: right">
					<button id="add_rechargeRate" class="btn"
						onclick="insertRechargeRate()">新增费率表</button>
				</div>
			</div>  -->
			<table class="table table-hover">
				<thead>
					<tr class="text-center" style="background: #ccc;">
						<td rowspan="2">序号</td>
						<td rowspan="2">充值方式</td>
						<td colspan="2">设置手续费付款人</td>
						<td colspan="3">比例手续费</td>
						<td colspan="2">定额手续费</td>
						<td rowspan="2">详情</td>
						<td rowspan="2">修改</td>
					</tr>
					<tr class="text-center" style="background: #ccc;">
						
						<td>自付比例</td>
						<td>代付比例</td>
						<td>手续费最低</td>
						<td>充值费率</td>
						<td>手续费最高</td>
						<td>金额范围</td>
						<td>手续费定额</td>
					</tr>
				</thead>
				<tbody id="text" style="text-align: center;">
					<!-- 这里面${item.id }是点的model里面的属性 -->
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

						<tr  class="text-center">
							<%-- <td>${item.id }</td> --%>
							<td>${status.count}</td>
							<td>
								<c:if test="${item.rechartype eq 1}">
									个人网银
								</c:if>	
								<c:if test="${item.rechartype eq 2}">
									快捷支付
								</c:if>	
								<c:if test="${item.rechartype eq 3}">
									企业网银
								</c:if>	
								<c:if test="${item.rechartype eq 4}">
									汇款充值
								</c:if>	
							</td>
							<td>${item.selfpayratio}%</td>
							<td>${item.proxypayratio}%</td>
							
							<td>
								<c:if test="${empty item.minfee}">
									---
								</c:if>
								<c:if test="${!empty item.minfee}">
									${df.format(item.minfee)}
								</c:if>
							
							</td>
							<td>
								<c:if test="${empty item.charrate}">
									---
								</c:if>
								<c:if test="${!empty item.charrate}">
									${df.format(item.charrate)}%
								</c:if>
							
							</td>
							<td>
								<c:if test="${empty item.maxfee}">
									---
								</c:if>
								<c:if test="${!empty item.maxfee}">
									${df.format(item.maxfee)}
								</c:if>
							
							</td>
							
							
							<td class="removehr">
								<c:if test="${item.chargetype eq 1}">
									<c:if test="${!empty item.rechargeQuotaFeeList}">
										<c:forEach items="${item.rechargeQuotaFeeList}" var="rqf" varStatus="status">
												<c:if test="${empty rqf.minmoney}">
													---
												</c:if>
												<c:if test="${!empty rqf.minmoney}">
													${df.format(rqf.minmoney)}
												</c:if>
												~
												<c:if test="${empty rqf.maxmoney}">
													---
												</c:if>
												<c:if test="${!empty rqf.maxmoney}">
													${df.format(rqf.maxmoney)}
												</c:if>
											<c:if test="${item.rechargeQuotaFeeList.size() != status.count}">
												<hr style="background-color: black; height: 0.6px"/>
											 </c:if>	
										</c:forEach>
									</c:if>
								</c:if>
								<c:if test="${item.chargetype eq 2}">---</c:if>
								<c:if test="${item.chargetype eq 0}">---</c:if>
							</td>
							<td class="removehr">
								<c:if test="${item.chargetype eq 1}">
									<c:if test="${!empty item.rechargeQuotaFeeList}">
										<c:forEach items="${item.rechargeQuotaFeeList}" var="rqf" varStatus="status">
												<c:if test="${empty rqf.quotafee}">
													---
												</c:if>
												<c:if test="${!empty rqf.quotafee}">
													${df.format(rqf.quotafee)}
												</c:if>
											<c:if test="${item.rechargeQuotaFeeList.size() != status.count}">
												<hr style="background-color: black; height: 0.6px"/>
											 </c:if>	
										</c:forEach>
									</c:if>
								</c:if>
								<c:if test="${item.chargetype eq 2}">---</c:if>
								<c:if test="${item.chargetype eq 0}">---</c:if>
							</td>
							<td>
								<button class="btn" data-backdrop="static" data-toggle="modal"
									data-dismiss="modal" data-target="#myModal"
									onclick="detalis('${item.id}')">查看</button>
							</td>

							<td>
								<div class="btn-group" role="group" aria-label="...">
									<button class="btn" onclick="updateFun('${item.id}')">编辑</button>
									
								</div>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			
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
					<h4 class="modal-title" id="myModalLabel" style="margin-left: 50px;color: red">详情</h4>
				</div>
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 审核模态框 -->
<!-- 	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
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
	</div> -->
	<!-- 启用 -->
<!-- 	<div class="modal fade" id="isuseModal" tabindex="-1" role="dialog"
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
	</div> -->

	<!-- 停用  -->
<!-- 	<div class="modal fade" id="cancelUseModal" tabindex="-1" role="dialog"
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
	</div> -->
</body>
</html>
