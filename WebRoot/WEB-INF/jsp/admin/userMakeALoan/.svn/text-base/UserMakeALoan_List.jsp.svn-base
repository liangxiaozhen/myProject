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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
td {border: 1px solid #666;vertical-align: middle !important;}
body {font-family: "微软雅黑";font-size: 13px;}
label {font-weight: normal;}
</style>
<script type="text/javascript">
	/*** 备注显示字符个数限制 */
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
//				$(this).attr("title", objString);
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}

	$(function() {
		/*** 备注添加limit属性 */
		$("[limit]").limit();
		/*** 备注tips */
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		/*** 重置查询条件 */
		$("#reset").click(function() {
			$("#orderno").val('');
			$("#mloanorderno").val('');
			$("#tno").val('');
		})
	})

	
	/*** 分页查询投标记录 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#loanRecordForm").submit()
	}

	var loanRecord = {
		/*** 查看投标放款记录详情 */
		queryDetail : function (id) {
			var url = "${pageContext.request.contextPath}/admin/userMakeALoan/queryloanRecordDetail.action";
			var param = {"id" : id};
			var callback = function(data) {
				$("#detailModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#detailModal-body").html(data)
			};
			$.post(url, param, callback);
		},
		queryByTno : function(obj){
			$("#tenderid").val(obj);
			$("#loanRecordForm").submit();
		}
	};
	
</script>
</head>

<body>
	<div class="container" style="width: 90%;">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 30px">
					<form method="post" role="form" id="loanRecordForm"
						action="${pageContext.request.contextPath}/admin/userMakeALoan/queryAll.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" /> <input
							type="hidden" id="tenderid" name="tenderid" value="" />
						<ul class="list-inline">
							<li>投标订单号：<input type="text" id="orderno" name="orderno"
								value="${echodata.orderno}" /></li>
							<li>放款订单号：<input type="text" id="mloanorderno"
								name="mloanorderno" value="${echodata.mloanorderno}" /></li>
							<li>标号：<input type="text" id="tno" name="tenderitem.tno"
								value="${echodata.tenderitem.tno}" /></li>
							<li><input type="submit" value="查询" class="btn" /></li>
							<li><input type="button" value="重置" class="btn" id="reset" /></li>
						</ul>
					</form>
					<div class="table-responsive">
						<table class="table table-hover table-condensed text-center">
							<caption>
								<strong>投标放款记录表</strong>
							</caption>
							<thead>
								<tr style="background: #ccc;">
									<td>序号</td>
									<td>放款订单号</td>
									<td>标号</td>
									<td>投标订单号</td>
									<td>投资方</td>
									<td>放款金额</td>
									<td>是否解冻</td>
									<td>详情信息</td>
									<td>备注</td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="loans"
										varStatus="varStatus">
										<tr>
											<td>${varStatus.index+1}</td>
											<td>${loans.mloanorderno}</td>
											<td><a
												onclick="loanRecord.queryByTno('${loans.tenderid}')"
												style="cursor: pointer;">${loans.tenderitem.tno}</a></td>
											<td>${loans.orderno}</td>
											<td>${loans.outaccount.loginname}</td>
											<td><fmt:formatNumber minFractionDigits="2"
													value="${loans.amount}" /></td>
											<td><c:if test="${loans.isthaw eq 0}">
													<font>未解冻</font>
												</c:if> <c:if test="${loans.isthaw eq 1}">
													<font color="green">解冻</font>
												</c:if></td>
											<td><input type="button" value="查看详情" class="btn"
												onclick="loanRecord.queryDetail('${loans.id}')" /></td>
											<td><p limit="5" data-toggle="tooltip"
													title="<h5>${loans.remark}</h5>">${loans.remark}</p></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty pagehelper.list}">
									<tr>
										<td colspan="100">没有相关数据</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<div id="page_div">
						<%@ include file="./../../common/pagehelper.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 详情模态框（Modal） -->
	<div id="detailModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看投标放款记录详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 审核模态框（Modal） -->
	<div id="auditModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在进行审核操作
					</h4>
				</div>
				<div class="modal-body" id="auditModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						onclick="userTender.auditPass()">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>