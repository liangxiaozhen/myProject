<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<title>ActivityAwardList_List</title>
<style type="text/css">
.remark-p {
	width: 85px;
}

td {
		text-align: center;
		vertical-align: text-top !important;
		border: 1px solid #666;
	}
</style>
<script type="text/javascript">
	
	/* 限制备注显示字符个数 */
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
	})
	
	/* 备注tips */
	$(function() {
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
	});

	$(function() {
		$(".ugrade").each(function(i) {
			var num = $(this).html();
			if (num.length > 7) {
				$(this).html(num.substr(0, 7) + "...");
			}
		});
		
		//重置
		$("#reset_").click(function(){
			document.getElementById("actno").value="";
			document.getElementById("actname").value="";
			document.getElementById("status").options[0].selected=true;
		});
	});

	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		selectActivityListByCondition(pageNum,pageSize);
	}
	function selectActivityListByCondition(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectActivityListByCondition").submit();
	}
	
	//活动详情
	function actListDetail(obj){
		var action="${pageContext.request.contextPath}/admin/manual/actListDetail.action";
		var actno = $(obj).data("actno");
		var param = {
			"actno":actno	
		};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#detailModal-body").html(data);
		};
		$.post(action,param,callback);
	}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div class="container" style="margin-top:20px">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<h3>活动列表查看</h3>
				<form id="selectActivityListByCondition" method="post"
					action="${pageContext.request.contextPath}/admin/manual/activityListQuery.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>活动编号：</label><input
						type="text" name="actno" id="actno" value="${al.actno}"
						placeholder="--请输入活动编号--"> <label>活动名称：</label><input
						type="text" name="actname" id="actname" value="${al.actname}"
						placeholder="--请输入活动名称--"> <label>活动状态：</label><select
						name="status" id="status">
						<option value="">--请选择--</option>
						<c:if test="${!empty statusmaps}">
							<c:forEach items="${statusmaps}" var="sm">
								<c:choose>
									<c:when test="${al.status eq sm.key}">
										<option value="${sm.key}" selected="selected">${sm.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${sm.key}">${sm.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</select>
					<button id="query_btn" class="btn btn-default"
						onclick="selectActivityListByCondition(1,9)">查询</button>
					<button type="button" id="reset_" class="btn btn-default">重置</button>
				</form>
				<table class="table  table-hover" id="tb_personlist">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<!-- <td>活动ID</td> -->
							<td>活动编号</td>
							<td>活动名称</td>
							<td>活动类型</td>
							<td>活动状态</td>
							<td>获奖人审核</td>
							<td>生成方式</td>
							<td>手动执行</td>
							<td>执行状态</td>
							<!-- <td>执行时间</td> -->
							<td>获奖人次/人数</td>
							<!-- <td>开始时间</td>
								<td>结束时间</td> -->
							<!-- <td>活动备注</td> -->
							<td>活动详情</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list}" var="actList"
							varStatus="status">
							<tr class="text-center">
								<!-- 序号 -->
								<td>${status.index+1}</td>
								<!-- 活动ID -->
								<%-- <td>${actList.id}</td> --%>
								<!-- 活动编号 -->
								<td>${actList.actno}</td>
								<!-- 活动名称 -->
								<td>${actList.actname}</td>
								<!-- 活动类型 -->
								<td><c:if test="${!empty typemaps}">
										<c:forEach items="${typemaps}" var="tm">
											<c:choose>
												<c:when test="${actList.acttype eq tm.key}">
														${tm.value}
													</c:when>
											</c:choose>
										</c:forEach>
									</c:if></td>
								<!-- 活动状态 -->
								<td><c:forEach items="${statusmaps}" var="actStstus">
										<c:choose>
											<c:when test="${actList.status eq actStstus.key }">
													${actStstus.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 获奖人审核 -->
								<td><c:forEach items="${auditmaps}" var="am">
										<c:choose>
											<c:when test="${actList.islistaudit eq am.key}">
													${am.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 生成方式 -->
								<td><c:forEach items="${rectypemaps}" var="rm">
										<c:choose>
											<c:when test="${actList.generatetype eq rm.key}">
													${rm.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 手动执行 -->
								<td><c:forEach items="${executemaps}" var="em">
										<c:choose>
											<c:when test="${actList.allowmanual eq em.key}">
													${em.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 执行状态 -->
								<td><c:forEach items="${executestatus}" var="et">
										<c:choose>
											<c:when test="${actList.executestatus eq et.key}">
													${et.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 执行时间 -->
								<%-- <td><fmt:formatDate value="${actList.executetime}" type="both" pattern="yyyyMMdd HH:mm:ss"/></td> --%>
								<!-- 开始时间 -->
								<%-- <td>
										<fmt:formatDate value="${actList.actbegintime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>
									</td> --%>
								<!-- 结束时间 -->
								<%-- <td>
										<fmt:formatDate value="${actList.actendtime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>
									</td> --%>
								<!-- 获奖人次/人数 -->
								<td><c:if test="${!empty actList.awardnumber}">
											${actList.awardtimes}/${actList.awardnumber}
										</c:if></td>
								<!-- 活动备注 -->
								<%-- <td>${actList.remark}</td> --%>
								<!-- 活动详情 -->
								<td>
									<button type="button" class="btn" onclick="actListDetail(this)"
										data-actno="${actList.actno}">详情</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<!-- 详情模态框（Modal）class="modal-dialog" style="width:200px"-->
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看活动列表详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>