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
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
td {
	border: 1px solid #666;
	vertical-align: middle !important;
}

body {
	font-family: "微软雅黑";
	font-size: 13px;
}
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
			$("#tno").val('');
		})
	})

	/*** 分页查询待放款的标的 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#pendingTenderForm").submit();
	}

	/** 查询该项目下待放款的订单 */
	function goLoans(tno){
		var url = "${pageContext.request.contextPath}/admin/tenderLoans/queryPendingLoans.action";
		var param = {
				"tno" : tno
		};
		var callback = function(data){
			$("#myModal").modal({
				backdrop : 'static',
				keyboard : false
			});
			$("#Modal-body").html(data);
		}
		$.post(url, param, callback);
	}
	
	/** 确认是否放款 */
	function confirmLoan(){
		$("#affirmLoan").removeAttr('onclick').html('处理中......');
		if(confirm("是否确认放款？")){
			var action="${pageContext.request.contextPath}/admin/tenderLoans/goLoans.action";
			var params = $("#pendingLoanForm").serializeArray();
			var callback = function(data){
				var json = $.parseJSON(data);
				alert(json.result);
				$("#affirmLoan").attr('onclick','confirmLoan(this)').text("确认放款");
				$("#myModal").modal('toggle');
			}
			$.post(action, params, callback);
		}
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-lg-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 30px">
					<form method="post" role="form" id="pendingTenderForm"
						action="${pageContext.request.contextPath}/admin/tenderLoans/queryAllForLoan.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<div class="col-md-3 input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span></span> <input type="text"
								name="tno" id="tno" class="form-control" value="${echodata.tno}"
								placeholder="请输入标号搜索..."> <span
								class="input-group-addon"
								style="border-bottom-right-radius: 4px; border-top-right-radius: 4px;"><button
									type="submit" style="border-style: none">搜索</button></span>
							<div class="col-md-2">
								<input type="button" value="重置" class="btn" id="reset" />
							</div>
						</div>
					</form>
					<div class="table-responsive">
						<table class="table table-hover table-condensed text-center">
							<caption>
								<strong>待放款标的</strong>
							</caption>
							<thead>
								<tr style="background: #ccc;">
									<td>序号</td>
									<td>标号</td>
									<td>标的名称</td>
									<td>标的类型</td>
									<td>借款周期</td>
									<td>标的金额</td>
									<td>已完成投标金额</td>
									<td>标的状态</td>
									<td>标的利息</td>
									<td>投标类型</td>
									<td>放款</td>
									<td>备注</td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="tender"
										varStatus="varStatus">
										<tr>
											<td>${varStatus.index+1}</td>
											<td>${tender.tno}</td>
											<td>${tender.tname}</td>
											<td><c:if test="${tender.tpro==1}">信用标</c:if> <c:if
													test="${tender.tpro==2}">抵押标</c:if> <c:if
													test="${tender.tpro==3}">其它标</c:if></td>
											<td>${tender.loantime}${tender.dayormonth}</td>
											<td>${tender.tamount}</td>
											<td>${tender.finishtamount}</td>
											<td><c:if test="${!empty statusMap}">
													<c:forEach items="${statusMap}" var="map">
														<c:choose>
															<c:when test="${tender.tstatus eq map.key}">${map.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td>${tender.tinterest}</td>
											<td><c:if test="${tender.ttype eq 0}">及时投标</c:if> <c:if
													test="${tender.ttype eq 1}">冻结投标</c:if></td>
											<td><button class="btn"
													onclick="goLoans('${tender.tno}',this)">去放款</button></td>
											<td><p limit="5" data-toggle="tooltip"
													title="<h5>${tender.remark}</h5>">${tender.remark}</p></td>
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
	<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在进行放款操作
					</h4>
				</div>
				<div class="modal-body" id="Modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" id="affirmLoan"
						onclick="confirmLoan(this)">确认放款</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>