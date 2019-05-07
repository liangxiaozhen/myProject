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
<title>用户使用券列表</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
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
	/* 备注显示字符个数限制 */
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
	};

	$(function() {
		$("[limit]").limit();
		/* 备注tips */
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		/* 重置查询条件 */
		$("#reset").click(function() {
			document.getElementById("uirctype").options[0].selected = true;
			document.getElementById("ictype").options[0].selected = true;
			document.getElementById("status").options[0].selected = true;
			document.getElementById("isuse").options[0].selected = true;
			//document.getElementById("isaudit").options[0].selected = true;
			document.getElementById("loginname").value = '';
		})
	})

	/* 分页查询使用券 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#couponform").submit();
	}

	var userInterestRateCoupon = {
			action : "<%=basePath%>admin/coupon/queryCouponDetail.action",
			/* 查看用户使用券详情 */
			query_coupon_detail : function(id,obj){
				var param = {
						"id" : id,
						"text" : $(obj).text()
					}
				var callback = function(data) {
					$("#detailModal").modal({
						backdrop : 'static',
						keyboard : false
					});
					$("#detailModal-body").html(data);
				}
				$.post(this.action, param, callback);
			},
			/* 跳转到审核页面 */
			forwardAuditPage : function(id,obj){
				var param = {
						"id" : id,
						"text" : $(obj).text()
					}
				var callback = function(data) {
					$("#auditModal").modal({
						backdrop : 'static',
						keyboard : false
					});
					$("#auditModal-body").html(data);
				}
				$.post(this.action, param, callback);
			}
			<%-- /** 审核通过 */
			auditPass : function(id){
				var url = "<%=basePath%>admin/coupon/auditPass.action";
				var param = {
					"id" : $("#couponId").val()
				};
				var callback = function(data){
					var json = $.parseJSON(data);
					alert(json.result);
					window.location.href="<%=basePath%>admin/coupon/queryCouponList.action";
				};
				$.post(url, param, callback);
				
			} --%>
	};
	
	//将券作废掉
	function couponInvalid(obj){
		var action = "<%=basePath%>admin/coupon/couponInvalid.action";
		var id= $(obj).data("opid");
		var param ={
			"id":id
		}
		var callback= function (data){
			var json = $.parseJSON(data);
			alert(json["result"]);
			$("#couponform").submit();
		}
		$.post(action,param,callback);
	}
	
</script>
</head>

<body>
	<div class="container" style="width: 75%">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<h4>用户使用券表</h4>
					<form method="post" role="form" id="couponform"
						action="<%=basePath%>admin/coupon/queryCouponList.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<ul class="list-inline">
							<li><label>券的类型：</label><select name="uirctype"
								id="uirctype">
									<option value="">--请选择--</option>
									<c:if test="${!empty uirctypemaps}">
										<c:forEach items="${uirctypemaps}" var="uirctype">
											<c:choose>
												<c:when test="${echodata.uirctype eq uirctype.key}">
													<option value="${uirctype.key}" selected="selected">${uirctype.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${uirctype.key}">${uirctype.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<li><label>券的来源：</label><select name="ictype" id="ictype">
									<option value="">--请选择--</option>
									<c:if test="${!empty ictypemaps}">
										<c:forEach items="${ictypemaps}" var="ictype">
											<c:choose>
												<c:when test="${echodata.ictype eq ictype.key}">
													<option value="${ictype.key}" selected="selected">${ictype.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${ictype.key}">${ictype.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<li><label>发放状态：</label><select name="status" id="status">
									<option value="">--请选择--</option>
									<c:if test="${!empty statusmaps}">
										<c:forEach items="${statusmaps}" var="sta">
											<c:choose>
												<c:when test="${echodata.status eq sta.key}">
													<option value="${sta.key}" selected="selected">${sta.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${sta.key}">${sta.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<li><label>券的状态：</label><select name="isuse" id="isuse">
									<option value="">--请选择--</option>
									<c:if test="${!empty awardmaps}">
										<c:forEach items="${awardmaps}" var="award">
											<c:choose>
												<c:when
													test="${echodata.isuse != null && echodata.isuse == award.key}">
													<option value="${award.key}" selected="selected">${award.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${award.key}">${award.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<%-- <li><label>是否审核：</label><select name="isaudit" id="isaudit">
									<option value="">--请选择--</option>
									<c:if test="${!empty isauditmaps}">
										<c:forEach items="${isauditmaps}" var="audit">
											<c:choose>
												<c:when test="${echodata.isaudit != null && echodata.isaudit == audit.key}">
													<option value="${audit.key}" selected="selected">${audit.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${audit.key}">${audit.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li> --%>
							
							<li><label>用户名:</label> <input type="text" id="loginname"
								placeholder="--请输入用户名--" name="userBaseAccountInfo.loginname"
								value="${echodata.userBaseAccountInfo.loginname}" /></li>
							<li><input type="submit" value="查询" class="btn" /> <input
								type="button" value="重置" class="btn" id="reset" /></li>
						</ul>
					</form>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th>序号</th>
									<!-- <th>券的ID</th> -->
									<th>用户名</th>
									<!-- th>券编号</th> -->
									<th>券的类型</th>
									<th>券的来源</th>
									<th>发放状态</th>
									<th>券的状态</th>
									<!-- <th>抵用金额（元）</th> -->
									<th>券利率</th>
									<!-- <th>发放时间</th> -->
									<!-- <th>处理时间</th> -->
									<!-- <th>失效时间</th> -->
									<!-- <th>是否审核</th> -->
									<!-- <th>备注</th> -->
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="coupon"
										varStatus="vs">
										<tr>
											<!-- 序号 -->
											<td>${vs.index+1}</td>
											<!-- ID -->
											<%-- <td>${coupon.id}</td> --%>
											<!-- 用户名 -->
											<td>${coupon.userBaseAccountInfo.loginname}</td>
											<!-- 券编号 -->
											<%-- <td>${coupon.uircno}</td> --%>
											<!-- 券的类型 -->
											<td><c:if test="${!empty uirctypemaps}">
													<c:forEach items="${uirctypemaps}" var="uirctype">
														<c:choose>
															<c:when test="${coupon.uirctype==uirctype.key}">
																${uirctype.value}
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<!-- 券的来源 -->
											<td><c:if test="${!empty ictypemaps}">
													<c:forEach items="${ictypemaps}" var="ictype">
														<c:choose>
															<c:when test="${coupon.ictype==ictype.key}">
																${ictype.value}
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<!-- 发放状态 -->
											<td><c:if test="${!empty statusmaps}">
													<c:forEach items="${statusmaps}" var="status">
														<c:choose>
															<c:when test="${coupon.status==status.key}">
																${status.value}
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<!-- 券的状态 -->
											<td><c:if test="${!empty awardmaps}">
													<c:forEach items="${awardmaps}" var="award">
														<c:choose>
															<c:when test="${coupon.isuse==award.key}">
																${award.value}
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td>
											<%-- <td>${coupon.vouchercash}</td> --%>
											<td>${coupon.icrate}</td>
											<%-- <td>${coupon.ictimeStr}</td> --%>
											<%-- <td>${coupon.icdealtimeStr}</td> --%>
											<%-- <td>${coupon.icfailtimeStr}</td> --%>
											<!-- 是否审核 -->
											<%-- <td><c:if test="${!empty isauditmaps}">
													<c:forEach items="${isauditmaps}" var="audit">
														<c:choose>
															<c:when test="${coupon.isaudit==audit.key}">
																${audit.value}
															</c:when>
														</c:choose>
													</c:forEach>
												</c:if></td> --%>

											<!-- 备注 -->
											<%-- <td><p limit="5" data-toggle="tooltip" title="<h5>${coupon.remark}</h5>">${coupon.remark}</p></td> --%>
											<td>
												<button class="btn"
													onclick="userInterestRateCoupon.query_coupon_detail('${coupon.id}',this)">查看详情</button>
												<c:if test="${coupon.status==3&&coupon.isuse!=6}">
													<button class="btn" onclick="couponInvalid(this)"
														data-opid="${coupon.id}">
														<font color="red">作废</font>
													</button>
												</c:if> <%-- <c:if test="${coupon.status eq 2}">
													<button class="btn" onclick="userInterestRateCoupon.forwardAuditPage('${coupon.id}',this)">审核</button>
												</c:if> --%>
											</td>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看用户使用券详情
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
						onclick="userInterestRateCoupon.auditPass(this)">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>