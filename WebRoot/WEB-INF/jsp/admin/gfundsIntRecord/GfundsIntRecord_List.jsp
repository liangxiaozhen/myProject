<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title></title>
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
body {font-family: "微软雅黑";font-size: 13px;}
label {font-weight: normal;}
td {border: 1px solid #666;vertical-align: middle !important;}
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
		$("[limit]").limit();
		/*** 备注tips */
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		/*** 重置查询条件 */
		$("#reset").click(function() {
			$("#loginname").val('');
		})
	})

	/*** 分页查询标的站岗利息记录表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#fundsForm").submit()
	}
	
	var gfundsIntRecord = {
		/*** 查看标的站岗利息记录详情 */
		query_fund_detail : function(id,obj){
			var action = "<%=basePath%>admin/gfundsIntRecord/forwardView.action";
			var param = {
				"id" : id
			};
			var callback = function(data) {
				$("#detailModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#detailModal-body").html(data);
			};
			$.post(action, param, callback);
		},
		/** 跳转至审核页面 */
		forwardAuditPage : function(id,obj){
			var action = "<%=basePath%>admin/gfundsIntRecord/forwardAudit.action";
			var param = {
					"id" : id
				};
			var callback = function(data) {
				$("#auditModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#auditModal-body").html(data);
			};
			$.post(action, param, callback);
		},
		/** 审核操作 */
		audit : function(obj){
			var gfiorderno = $("#gfiorderno").text();
			if(confirm("确定"+$(obj).text()+"流水号"+gfiorderno+"?")){
				var url = "<%=basePath%>admin/gfundsIntRecord/audit.action";
				var param = {
						"gfiorderno" : gfiorderno,
						"isaudit" : $(obj).data("audit"),
						"remark" : $("#remark").val()
					};
				var callback = function(data){
					var json = $.parseJSON(data);
					if(json.result == "success"){
						alert("操作成功！")
					}else if(json.result == "fail"){
						alert("操作失败！")
					}else if(json.result == "error"){
						alert("参数错误！")
					}
					window.location.href="<%=basePath%>admin/gfundsIntRecord/queryGfundsIntRecord.action";
				};
				$.post(url, param, callback);
			}
		},
		queryByTno : function(obj){
			$("#tenderid").val(obj);
			$("#fundsForm").submit();
		}
	}
	
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<form method="post" role="form" id="fundsForm"
						action="<%=basePath%>admin/gfundsIntRecord/queryGfundsIntRecord.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" /> <input
							type="hidden" id="tenderid" name="tenderid" value="" />
						<ul class="list-inline">
							<li><label>投资人：</label> <input type="text" id="loginname"
								name="investor.loginname" value="${echodata.investor.loginname}" /></li>
							<li><input type="submit" value="查询" class="btn" /> <input
								type="button" value="重置" class="btn" id="reset" /></li>
						</ul>
					</form>
					<div class="table-responsive">
						<table class="table text-center table-hover table-condensed">
							<caption>
								<strong>标的站岗利息记录表</strong>
							</caption>
							<thead>
								<tr style="background: #ccc;">
									<td>序号</td>
									<td>站岗利息流水号</td>
									<td>站岗利息编号</td>
									<td>标号</td>
									<td>投资人</td>
									<td>投标金额</td>
									<td>奖励金额</td>
									<td>奖励方式</td>
									<td>是否发放</td>
									<td>是否审核</td>
									<td>操作</td>
									<td>备注</td>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="funds"
										varStatus="varStatus">
										<tr>
											<td>${varStatus.index+1}</td>
											<td>${funds.gfiorderno}</td>
											<td>${funds.guardinterest.gfundsintno}</td>
											<td><a
												onclick="gfundsIntRecord.queryByTno('${funds.tenderid}')"
												style="cursor: pointer;">${funds.tender.tno}</a></td>
											<td>${funds.investor.loginname}</td>
											<td><c:if test="${!empty funds.tenderamount}">${df.format(funds.tenderamount)}</c:if></td>
											<td><c:if test="${!empty funds.rewardamount}">${df.format(funds.rewardamount)}</c:if></td>
											<td><c:if test="${!empty payouttypemap}">
													<c:forEach items="${payouttypemap}" var="ptype">
														<c:choose>
															<c:when test="${funds.payouttype eq ptype.key}">${ptype.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td><c:if test="${!empty isgrantmap}">
													<c:forEach items="${isgrantmap}" var="grant">
														<c:choose>
															<c:when test="${funds.isgrant eq grant.key}">${grant.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td><c:if test="${!empty isauditmap}">
													<c:forEach items="${isauditmap}" var="audit">
														<c:choose>
															<c:when test="${funds.isaudit eq audit.key}">${audit.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td>
												<div class="btn-group" role="group" aria-label="...">
													<button class="btn"
														onclick="gfundsIntRecord.query_fund_detail('${funds.id}',this)">查看详情</button>
													<c:if test="${funds.isaudit eq 1}">
														<button class="btn"
															onclick="gfundsIntRecord.forwardAuditPage('${funds.id}',this)">审核</button>
													</c:if>
												</div>
											</td>
											<td><p data-toggle="tooltip" limit="5"
													title="<h5>${funds.remark}</h5>">${funds.remark}</p></td>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看标的站岗利息记录详情
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
		<div class="modal-dialog">
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
						onclick="gfundsIntRecord.audit(this)" data-audit="2">审核通过</button>
					<button type="button" class="btn btn-warning"
						onclick="gfundsIntRecord.audit(this)" data-audit="3">审核不通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>