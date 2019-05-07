<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
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
			document.getElementById("activityName").value="";
			document.getElementById("activityNo").value="";
			document.getElementById("isDealMain").options[0].selected=true;
		});
				
	});

	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		manualAwardQuery(pageNum,pageSize);
	}
	function manualAwardQuery(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#manualAwardQuery").submit();
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
	
	//模板生成手动颁奖
	function templetProductActivity(){
		var action="<%=basePath%>admin/manual/templetAwardAdd.action";
		var callback = function(data){
			$("#templetModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#templetModal-body").html(data);
		};
		$.post(action,null,callback);
	}
	
	 //模板生成手动颁奖,确定生成
	function templetCreate(){
		var activityName= $("#templetActName :checked").text();
		//alert("活动名称》》》"+activityName);
		var url="<%=basePath%>admin/manual/templetCreate.action?activityName="+encodeURIComponent(activityName);
		//获取模板名称
		window.location.href=encodeURI(url);
	} 
	 
	//查看活动详情
	function manualActivityDetail(obj){
		var action ="<%=basePath%>admin/manual/manualActivityDetail.action";
		var activityNo = $(obj).data("activityno");
		var param = {
			"activityNo":activityNo
		};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop:"static",
				keyboard:false
			});
			$("#detailModal-body").html(data);
		};
		$.post(action,param,callback);
	}
	//作废手动颁奖主活动
	function manualActivityInvalid(obj){
		var action ="<%=basePath%>admin/manual/manualActivityInvalid.action";
		var activityNo = $(obj).data("activityno");//主活动编号
		var isDealMain= $(obj).data("isdealmain");//主活动状态
		var param = {
			"activityNo":activityNo,
			"isDealMain":isDealMain
		};
		var callback = function(data){
			var json = $.parseJSON(data);
			alert(json["result"]);
			$("#manualAwardQuery").submit();
		};
		if(window.confirm("确定作废吗？")){
			$.post(action,param,callback);
		}
	}
	
	//删除主活动
	function manualActivityDelete(obj){
		var action ="<%=basePath%>admin/manual/manualActivityDelete.action";
		var activityNo = $(obj).data("activityno");//主活动编号
		var isDealMain= $(obj).data("isdealmain");//主活动状态
		var param = {
				"activityNo":activityNo,
				"isDealMain":isDealMain
		};
		var callback = function(data){
			var json = $.parseJSON(data);
			alert(json["result"]);
			$("#manualAwardQuery").submit();
		};
		if(window.confirm("确定删除吗？")){
			$.post(action,param,callback);
		}
	}
	
	//编辑手动颁奖活动
	function manualActivityEdit(obj){
		var activityNo = $(obj).data("activityno");//主活动编号
		//alert("主活动编号》》》"+activityNo);
		var isDealMain= $(obj).data("isdealmain");//主活动状态
		//alert("主活动状态》》》"+isDealMain);
		window.location.href="<%=basePath%>admin/manual/manualActivityEdit.action?activityNo="+activityNo+"&isDealMain="+isDealMain;
	}
	
	//执行手动颁奖活动
	function executeManualActivity(obj){
		var action = "<%=basePath%>admin/manual/executeManualActivity.action";
		var activityNo = $(obj).data("activityno");
		var isAudit  = $(obj).data("isaudit");
		//alert("活动编号》》》"+activityNo);
		//alert("是否审核》》》"+isAudit);
		var params = {
			"activityNo":activityNo,
			"isAudit":isAudit
		};
		var callback = function(data){
			alert(data);
			window.location.href = "${pageContext.request.contextPath }/admin/manual/manualActivityQuery.action";
		};
		$.post(action,params,callback,"json");
	}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div class="container" style="margin-top:20px">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<h3>手动颁奖活动查看</h3>
				<form id="manualAwardQuery" method="post"
					action="<%=basePath%>admin/manual/manualActivityQuery.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>活动名称：</label><input
						type="text" name="activityName" id="activityName"
						value="${mam.activityName}" placeholder="--请输入活动名称--"> <label>活动编号：</label><input
						type="text" name="activityNo" id="activityNo"
						value="${mam.activityNo}" placeholder="--请输入活动编号--"> <label>活动状态：</label><select
						name="isDealMain" id="isDealMain">
						<option value="">--请选择--</option>
						<c:if test="${!empty statusmaps}">
							<c:forEach items="${statusmaps}" var="sm">
								<c:choose>
									<c:when test="${mam.isDealMain eq sm.key}">
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
						onclick="manualAwardQuery(1,9)">查询</button>
					<button type="button" id="reset_" class="btn btn-default">重置</button>
				</form>
				<div style="margin-left: 85%">
					<button
						onclick="window.location.href='<%=basePath%>admin/manual/manualAwardAdd.action';"
						class="btn btn-default">手动新增</button>
					<button onclick="templetProductActivity();" class="btn btn-default">模板新增</button>
				</div>
				<table class="table  table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>活动名称</td>
							<td>活动编号</td>
							<td>活动状态</td>
							<td>子活动数</td>
							<!-- <td>生成方式</td> -->
							<td>手动执行</td>
							<!-- <td>执行状态</td> -->
							<td>执行时间</td>
							<!-- <td>活动备注</td> -->
							<td>操作</td>
							<td>修改</td>
							<td>详情</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list}" var="manualMain"
							varStatus="st">
							<tr class="text-center">
								<!-- 序号 -->
								<td>${st.index+1}</td>
								<!-- 活动名称 -->
								<td>${manualMain.activityName}</td>
								<!-- 活动编号 -->
								<td>${manualMain.activityNo}</td>
								<!-- 活动状态 -->
								<td><c:forEach items="${statusmaps}" var="sm">
										<c:choose>
											<c:when test="${manualMain.isDealMain eq sm.key }">
													${sm.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 子活动数 -->
								<td>${manualMain.subActivityNum}</td>

								<!-- 生成方式 -->
								<%-- <td>
										<c:forEach items="${rectypemaps}" var="rm">
											<c:choose>
												<c:when test="${manualMain.actMType eq rm.key }">
													${rm.value}
												</c:when>										
											</c:choose>
										</c:forEach>
									</td> --%>

								<!-- 手动执行 -->
								<td><c:forEach items="${executemaps}" var="em">
										<c:choose>
											<c:when test="${manualMain.isManual eq em.key }">
													${em.value}
												</c:when>
										</c:choose>
									</c:forEach></td>
								<!-- 执行状态 -->
								<%-- <td>
										<c:forEach items="${executestatus}" var="es">
											<c:choose>
												<c:when test="${manualMain.executeStatus eq es.key }">
													${es.value}
												</c:when>										
											</c:choose>
										</c:forEach>
									</td> --%>
								<!-- 执行时间 -->
								<td><fmt:formatDate value="${manualMain.activityTime}"
										type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<!-- 活动备注 -->
								<%-- <td><p data-toggle="tooltip" class="remark-p text-center" title="<h5>${manualMain.remark }</h5>" limit="8">${manualMain.remark}</p></td> --%>

								<!-- 操作 -->
								<td><c:choose>
										<c:when
											test="${manualMain.isDealMain eq 1 && manualMain.isManual eq 1}">
											<button type="button" class="btn"
												onclick="executeManualActivity(this);"
												data-activityno="${manualMain.activityNo}"
												data-isaudit="${manualMain.isAudit}">执行</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn" disabled="disabled">执行</button>
										</c:otherwise>
									</c:choose> <c:if
										test="${manualMain.isDealMain ne 3 && manualMain.isDealMain ne 6}">
										<button type="button" class="btn"
											onclick="manualActivityInvalid(this);"
											data-activityno="${manualMain.activityNo}"
											data-isdealmain="${manualMain.isDealMain}">作废</button>
									</c:if></td>

								<!-- 修改 -->
								<td><c:choose>
										<c:when
											test="${manualMain.isDealMain eq 0 || manualMain.isDealMain eq 1 || manualMain.isDealMain eq 5}">
											<button type="button" class="btn"
												onclick="manualActivityEdit(this)"
												data-activityno="${manualMain.activityNo}"
												data-isdealmain="${manualMain.isDealMain}">编辑</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn" disabled="disabled">编辑</button>
										</c:otherwise>
									</c:choose> <c:choose>
										<c:when test="${manualMain.isDealMain eq 3}">
											<button type="button" class="btn" disabled="disabled">删除</button>
										</c:when>
										<c:otherwise>
											<button type="button" class="btn"
												onclick="manualActivityDelete(this);"
												data-activityno="${manualMain.activityNo}"
												data-isdealmain="${manualMain.isDealMain}">删除</button>
										</c:otherwise>
									</c:choose></td>
								<!-- 详情 -->
								<td>
									<button type="button" class="btn"
										onclick="manualActivityDetail(this);"
										data-activityno="${manualMain.activityNo}">查看</button>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看手动颁奖活动详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 确定生成模板颁奖（Modal） -->
	<div id="templetModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在选择模板生成活动
					</h4>
				</div>
				<div class="modal-body" id="templetModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="templetCreate()" data-dismiss="modal">确定生成</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>