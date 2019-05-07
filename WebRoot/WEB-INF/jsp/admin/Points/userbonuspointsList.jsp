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
<title>管理后台-用户积分</title>
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
th {
	text-align: center;
	background: #ccc;
}

td {
	text-align: center;
	vertical-align: text-top !important;
	border: 1px solid #666;
}

body {
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript">
	/** 备注显示字符个数限制*/
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
		/** 备注tips */
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		/** 重置查询条件 */
		$("#reset").click(function() {
			document.getElementById("bptype").options[0].selected = true;
			document.getElementById("status").options[0].selected = true;
			//document.getElementById("isaudit").options[0].selected = true;
			document.getElementById("loginname").value = '';
		});
	})

	/** 分页查询积分表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#pointsform").submit()
	}

	var userBonusPoints = {
		action : "<%=basePath%>admin/points/queryPointsDetail.action",
		/** 查看用户积分详情 */
		query_points_detail : function(id,obj){
			var param = {
					"id" : id,
					"text" : $(obj).text()
				};
			var callback = function(data) {
				$("#detailModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#detailModal-body").html(data);
			};
			$.post(this.action, param, callback);
		},
		/** 跳转到审核页面 */
		forwardAuditPage : function(id,obj){
			var param = {
					"id" : id,
					"text" : $(obj).text()
				};
			var callback = function(data) {
				$("#auditModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#auditModal-body").html(data);
			};
			$.post(this.action, param, callback);
		},
		/** 审核通过 */
		auditPass : function(){
			var url = "<%=basePath%>admin/points/auditPass.action";
			var param = {
					"id" : $("#bonusId").val()
			};
			var callback = function(data){
				var json = $.parseJSON(data);
				alert(json['result']);
				window.location.href="<%=basePath%>admin/points/queryPointsList.action";
			};
			$.post(url, param, callback);
		}
	}
</script>
</head>

<body>
	<div class="container" id="container" style="width: 75%">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<form method="post" role="form" id="pointsform"
						action="<%=basePath%>admin/points/queryPointsList.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<ul class="list-inline">
							<%-- <li><label>积分ID：</label><input type="text" id="id" name="id"
								value="${ubp.id}" onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="--请输入ID--" /></li> --%>
							<li><label>积分来源：</label><select name="bptype" id="bptype">
									<option value="">--请选择--</option>
									<c:if test="${!empty rectypemap}">
										<c:forEach items="${rectypemap}" var="rm">
											<c:choose>
												<c:when test="${ubp.bptype eq rm.key}">
													<option value="${rm.key}" selected="selected">${rm.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${rm.key}">${rm.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<li><label>积分状态：</label><select name="status" id="status">
									<option value="">--请选择--</option>
									<c:if test="${!empty statusmap}">
										<c:forEach items="${statusmap}" var="pstatus">
											<c:choose>
												<c:when test="${ubp.status eq pstatus.key }">
													<option value="${pstatus.key}" selected="selected">${pstatus.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${pstatus.key}">${pstatus.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<%-- 	<li><label>是否审核：</label><select name="isaudit" id="isaudit">
									<option value="">--请选择--</option>
									<c:if test="${!empty isauditmaps}">
										<c:forEach items="${isauditmaps}" var="paudit">
											<c:choose>
												<c:when test="${ubp.isaudit eq paudit.key }">
													<option value="${paudit.key}" selected="selected">${paudit.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${paudit.key}">${paudit.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li> --%>
							<li><label>用户名：</label><input type="text" id="loginname"
								name="userBaseAccountInfo.loginname"
								value="${ubp.userBaseAccountInfo.loginname}"
								placeholder="--请输入用户名--" /></li>
							<li><input type="submit" value="查询" class="btn" /> <input
								type="button" value="重置" class="btn" id="reset" /></li>
						</ul>
					</form>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th>序号</th>
									<!-- <th>积分ID</th> -->
									<th>积分编号</th>
									<th>用户名</th>
									<th>积分来源</th>
									<th>积分数</th>
									<!-- <th>发放时间</th> -->
									<th>积分状态</th>
									<!-- <th>处理时间</th> -->
									<!-- <th>是否审核</th> -->
									<!-- <th>备注</th> -->
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="points"
										varStatus="vs">
										<tr>
											<td>${vs.index+1}</td>
											<%-- <td>${points.id}</td> --%>
											<td>${points.ubpno}</td>
											<td>${points.userBaseAccountInfo.loginname}</td>
											<!-- 积分来源 -->
											<td><c:forEach items="${rectypemap}" var="rm">
													<c:choose>
														<c:when test="${points.bptype eq rm.key}">
															${rm.value}
														</c:when>
													</c:choose>
												</c:forEach></td>
											<td>${points.bonuspoints}</td>
											<!-- 发放时间 -->
											<%-- <td><fmt:formatDate value="${points.bpstime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
											<td><c:choose>
													<c:when test="${points.status==3}">已领取</c:when>
													<c:when test="${points.status==7}">领取失败</c:when>
												</c:choose></td>
											<!-- 处理时间 -->
											<%-- <td><fmt:formatDate value="${points.bpdealtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
											<%-- <td><c:choose>
													<c:when test="${points.isaudit==0}">是</c:when>
													<c:when test="${points.isaudit==1}">否</c:when>
												</c:choose></td> --%>
											<!-- 备注 -->
											<%-- <td><p limit="8" data-toggle="tooltip" title="<h5>${points.remark}</h5>">${points.remark}</p></td> --%>
											<!-- 操作 -->
											<td><div class="btn-group" role="group" aria-label="...">
													<button class="btn"
														onclick="userBonusPoints.query_points_detail('${points.id}',this)">查看详情</button>
													<%-- <c:if test="${points.isaudit eq 1}">
														<button class="btn" onclick="userBonusPoints.forwardAuditPage('${points.id}',this)">审核</button>
													</c:if>--%>
												</div></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty pagehelper.list}">
									<tr>
										<td colspan="99">没有相关数据</td>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看用户积分详情
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
						onclick="userBonusPoints.auditPass(this)">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>