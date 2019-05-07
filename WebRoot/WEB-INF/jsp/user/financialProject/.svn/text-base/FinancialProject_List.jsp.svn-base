<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我要理财</title>
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
body {font-family: "微软雅黑";font-size: 13px;}
td {border: 1px solid #666;vertical-align: middle !important;}
body {font-family: "微软雅黑";}
th {text-align: center;background: #ccc;}
table tr td {text-align: center;vertical-align: middle !important;}
</style>
<script type="text/javascript">
	/** 分页查询理财项目 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#tenderListForm").submit();
	}
	/** 去理财 */
	function forwarTender(id) {
// 		var url = "${pageContext.request.contextPath}/user/initiativeBid/investing.action";
		var url = "${pageContext.request.contextPath}/user/tenderBase/investing.action";
		var param = {"id" : id};
		var callback = function(data) {
			if(data == 'logout'){
				window.location.href="${pageContext.request.contextPath}/user/tologin.action";
				return;
			}
			$("#body").html(data);
		};
		$.post(url, param, callback);
	}
	/** 立即投资 */
	function toTender(){
		var fake = $("#isfaketender").val();
		var cheat = $("#iscancheattender").val();
		if(fake == 1 && cheat == 1){
			$("#tenderModal").modal({
				backdrop : 'static',
				keyboard : false
			});
		}else{
			realTender();
		}
	}
	/** 真投资 */
	function realTender() {
		var self = $(this);
		self.attr('disabled', 'disabled');
		$('#tenderModal').modal('hide');
// 		var url = "${pageContext.request.contextPath}/user/initiativeBid/tenderCheck.action";
		var url = "${pageContext.request.contextPath}/user/tenderBase/tenderCheck.action";
		var param = $('#tender').serializeArray();
		var callback = function(data) {
			var json = $.parseJSON(data);
			if (json.info == "success") {
				$("#tips").text("");
				$("#tender").submit();
			} else {
				$("#tips").text(json.info);
				self.removeAttr('disabled');
			}
		};
		$.post(url, param, callback);
	}
	/** 假投资 */
	function fakeTender(){
		$('#tenderModal').modal('toggle');
	}
	/** 返回列表 */
	function back(){
// 		window.location.href = "${pageContext.request.contextPath}/user/initiativeBid/queryTender.action";
		window.location.href = "${pageContext.request.contextPath}/user/tenderBase/queryTender.action";
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div id="body">
			<form id="tenderListForm"
<%-- 				action="<%=basePath%>/user/initiativeBid/queryTender.action" --%>
				action="<%=basePath%>/user/tenderBase/queryTender.action"
				method="post">
				<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
					type="hidden" id="pageSize" name="pageSize" value="" />
				<h1>理财项目</h1>
				<div class="col-md-3 input-group">
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-search"></span></span><input type="text"
						class="form-control" name="tno" /><span class="input-group-addon"
						style="border-bottom-right-radius: 4px; border-top-right-radius: 4px;"><button
							type="submit" style="border-style: none">搜索</button></span>
					<div class="col-md-2">
						<button type="reset" class="btn">重置</button>
					</div>
				</div>
				<div class="table-responsive">
					<table class="table table-hover table-condensed text-center">
						<caption>
							<strong>投资项目</strong>
						</caption>
						<thead>
							<tr style="background: #ccc;">
								<td>序号</td>
								<td>借款申请ID</td>
								<td>标号</td>
								<td>标的名称</td>
								<td>标的金额</td>
								<td>已完成投标金额</td>
								<td>标的开始时间</td>
								<td>标的结束时间</td>
								<td>借款周期</td>
								<td>投资收益</td>
								<td>标的说明描述</td>
								<td>操作</td>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty pagehelper.list}">
								<c:forEach items="${pagehelper.list}" var="ti"
									varStatus="varStatus">
									<tr>
										<td>${varStatus.index+1}</td>
										<td>${ti.loanappid}</td>
										<td>${ti.tno}</td>
										<td>${ti.tname}</td>
										<td><fmt:formatNumber type="CURRENCY"
												value="${ti.tamount}" /></td>
										<td><fmt:formatNumber type="CURRENCY"
												value="${ti.finishtamount}" /></td>
										<td><fmt:formatDate value="${ti.tbegintime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${ti.tendtime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${ti.loantime}${ti.dayormonth}</td>
										<td><fmt:formatNumber value="${ti.tinterest}"
												minFractionDigits="2" type="percent" /></td>
										<td>${ti.tdesc}</td>
										<td><c:set var="nowDate" value="<%=new Date()%>"></c:set>
											<c:choose>
												<c:when
													test="${!empty ti.finishtamount and ti.tamount eq ti.finishtamount}">
												已满标
											</c:when>
												<c:when test="${nowDate<ti.tendtime}">
													<input type="button" class="btn" value="投资"
														onclick="forwarTender('${ti.id}',this)">
												</c:when>
												<c:otherwise>已过期</c:otherwise>
											</c:choose></td>
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
			</form>
		</div>
	</div>

	<!-- 投标模态框（Modal） -->
	<div id="tenderModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示
					</h4>
				</div>
				<div class="modal-body" id="tenderModal-body">
					<div style="margin: 20px 180px;">
						<label>请选择假投标还是真投标？</label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						onclick="realTender(this)">真投资</button>
					<button type="button" class="btn btn-success"
						onclick="fakeTender(this)">假投资</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>