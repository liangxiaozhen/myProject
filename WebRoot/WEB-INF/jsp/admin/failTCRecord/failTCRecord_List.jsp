<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Insert title here</title>
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
		$("#investor").val('');
	})
})

/*** 分页查询标的流标补偿记录表 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#failTCRForm").submit();
}

var failTCRecord = {
	/** 详情模态框 */
	queryDetailForView : function(id,obj){
		var action = "<%=basePath%>admin/failTCRecord/forwardView.action";
		var param = {"id" : id};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop : 'static',
				keyboard : false
			});
			$("#detailModal-body").html(data);
		};
		$.post(action, param, callback);
	},
	/** 审核模态框 */
	queryDetailForAudit : function(id,obj){
		var action = "<%=basePath%>admin/failTCRecord/forwardAudit.action";
		var param = {"id" : id};
		var callback = function(data){
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
		var ftcorderno = $("#ftcorderno").text();
		if(confirm("确定"+$(obj).text()+"流水号"+ftcorderno+"?")){
			var url = "<%=basePath%>admin/failTCRecord/audit.action";
			var param = {"ftcorderno" : ftcorderno,"isaudit" : $(obj).data("audit"),"remark" : $("#remark").val()};
			var callback = function(data){
				var json = $.parseJSON(data);
				if(json.result == "success"){
					alert("操作成功！")
				}else if(json.result == "fail"){
					alert("操作失败！")
				}else if(json.result == "error"){
					alert("参数错误！")
				}
				window.location.href="<%=basePath%>admin/failTCRecord/queryFailTCRecordLsit.action";
			};
			$.post(url, param, callback);
		}
	},
	queryByTno : function(obj){
		$("#tenderid").val(obj);
		$("#failTCRForm").submit();
	},
	grant : function(id){
		if(confirm("是否确认发货？")){
			var url = "<%=basePath%>admin/failTCRecord/grant.action";
			var param = {id : id};
			var callback = function(data){
				alert(data);
			};
			$.post(url, param, callback);
		}
	},
	selectAll : function(obj){
		var checkboxs = document.getElementsByName("ftcorderno");
		for (i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
	}
};
</script>
</head>
<body>
	<div class="container" style="width: 90%;">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<form id="failTCRForm" method="post"
						action="<%=basePath%>admin/failTCRecord/queryFailTCRecordLsit.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" /> <input
							type="hidden" id="tenderid" name="tenderid" value="" />
						<ul class="list-inline">
							<li><label>投资人：</label><input type="text" id="investor"
								name="userbaseinfo.loginname"
								value="${echodata.userbaseinfo.loginname}" /></li>
							<li><input type="submit" class="btn" value="查询" /></li>
							<li><input type="button" class="btn" value="重置" id="reset" /></li>
						</ul>
					</form>
					<div>
						<input type="button" class="btn" value="批量操作"
							onclick="failTCRecord.batchAudit(this)" />
					</div>
					<div class="table-responsive">
						<table class="table text-center table-hover table-condensed">
							<caption>标的流标补偿记录</caption>
							<thead>
								<tr style="background: #ccc;">
									<td><input type="checkbox" id="all" onclick="failTCRecord.selectAll(this)"></td>
									<td>序号</td>
									<td>流标补偿流水号</td>
									<td>流标补偿编号</td>
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
									<c:forEach items="${pagehelper.list}" var="fail"
										varStatus="varStatus">
										<tr>
											<td><input type="checkbox" id="single" name="ftcorderno" value=""></td>
											<td>${varStatus.index+1}</td>
											<td>${fail.ftcorderno}</td>
											<td>${fail.failtcompensate.failtcno}</td>
											<td><a
												onclick="failTCRecord.queryByTno('${fail.tenderid}')"
												style="cursor: pointer;">${fail.tenderitem.tno}</a></td>
											<td>${fail.userbaseinfo.loginname}</td>
											<td>${fail.tenderamount eq null ? '--' : df.format(fail.tenderamount)}</td>
											<td>${fail.rewardamount eq null ? '--' : df.format(fail.rewardamount)}</td>
											<td><c:if test="${!empty payouttypemap}">
													<c:forEach items="${payouttypemap}" var="ptype">
														<c:choose>
															<c:when test="${fail.payouttype eq ptype.key}">${ptype.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td><c:if test="${!empty isgrantmap}">
													<c:forEach items="${isgrantmap}" var="grant">
														<c:choose>
															<c:when test="${fail.isgrant eq grant.key}">${grant.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td><c:if test="${!empty isauditmap}">
													<c:forEach items="${isauditmap}" var="audit">
														<c:choose>
															<c:when test="${fail.isaudit eq audit.key}">${audit.value}</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<td>
												<button class="btn"
													onclick="failTCRecord.queryDetailForView('${fail.id}',this)">查看详情</button>
												<c:if test="${fail.isaudit eq 1}">
													<button class="btn"
														onclick="failTCRecord.queryDetailForAudit('${fail.id}',this)">审核</button>
												</c:if>
												<c:if test="${fail.isgrant eq 3}">
												<button class="btn"
													onclick="failTCRecord.grant('${fail.id}',this)">去发货</button>
												</c:if>
												</td>
											<td><p data-toggle="tooltip" limit="5"
													title="<h5>${fail.remark}</h5>">${fail.remark}</p></td>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看标的流标补偿记录详情
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
						onclick="failTCRecord.audit(this)" data-audit="2">审核通过</button>
					<button type="button" class="btn btn-warning"
						onclick="failTCRecord.audit(this)" data-audit="3">审核不通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>