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
	<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
	td {
		text-align: center;
		vertical-align: text-top !important;
		border: 1px solid #666;
	}
</style>
<script type="text/javascript">
	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		directionNameList(pageNum,pageSize);
	}
	function directionNameList(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#directionNameList").submit();
	}
	//点击手动新增
	function directionManualAdd(){
		var action = "<%=basePath%>admin/nameList/directionManualAdd.action";
		var callback = function(data){
			$("#newlyModal").modal({
				backdrop:"static",
				keyboard:false
			});
			$("#newlyModal-body").html(data);
		};
		$.post(action,null,callback);
	}
	
	//选择定向方式，并确定
	function sureCreate(){
		var businessType = $("#businesstype").find("option:selected").val();
		//alert("businesstype>>>"+businessType);
		var nameMode =$("#namemode").find("option:selected").val();
		//alert("nameMode>>>"+nameMode);
		if(nameMode!=0 && businessType!=0){
			window.location.href="<%=basePath%>admin/nameList/sureCreate.action?nameMode="+nameMode+"&businessType="+businessType;
		}else{
			alert("请选择");
		}
	}
	//模板新增
	function templetManualAdd(){
		var action = "<%=basePath%>admin/nameList/templetManualAdd.action";
		var callback = function(data){
			$("#templateModal").modal({
				backdrop:"static",
				keyboard:false
			});
			$("#templateModal-body").html(data);
		};
		$.post(action,null,callback);
	}
	//模板新增定向  并确定
	function templateCreate(){
		var businessName = $("#business_name").find("option:checked").text();
		//alert("定向标题》》》"+businessName);
		var url = "<%=basePath%>admin/nameList/templateCreate.action?businessName="+encodeURIComponent(businessName);
		window.location.href=encodeURI(url);
	}
	
	//根据id删除对应的名单定向列表
	function deleteSNL(obj){
		var action ="<%=basePath%>admin/nameList/deleteSpecialNameList.action";
		var id = $(obj).data("opid");
		//alert("id>>>"+id);
		var params={
			"id":id
		};
		var callback = function(data){
			alert(data)
			$("#directionNameList").submit();
		};
		if(window.confirm("确定删除吗？")){
			$.post(action,params,callback,"json");
		}
	}
	//启用
	function modifyIsUse(obj){
		var action ="<%=basePath%>admin/nameList/modifyIsUse.action";
		var businessNo = $(obj).data("businessno");
		//alert("id>>>"+id);
		var isUse = $(obj).data("isuse");
		//alert("isUse>>>"+isUse);
		var params={
			"businessNo":businessNo,
			"isUse":isUse
		};
		var callback = function(data){
			alert(data);
			$("#directionNameList").submit();
		};
		if(window.confirm("确定要修改启用状态吗？")){
			$.post(action,params,callback,"json");
		}
	}
	//查看详情
	function examineDetail(obj){
		var action ="<%=basePath%>admin/nameList/examineDetail.action";
		var id = $(obj).data("opid");
		//alert("定向id》》》"+id);
		var params={
				"id":id
			};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop:"static",
				keyboard:false
			});
			$("#detailModal-body").html(data);
		};
		$.post(action,params,callback);
	}
	//编辑定向名单列表
	function editSNL(obj){
		var id = $(obj).data("opid");
		//alert("定向id》》》"+id);
		var action = "<%=basePath%>admin/nameList/editSpecialNameList.action?id="+id;
		window.location.href=action;
	}
	
	//查看定向名单中的最终人员
	function finalPersonView(obj){
		var action = "<%=basePath%>admin/nameList/finalPersonView.action";
		var id = $(obj).data("opid");
		//alert("id==="+id);
		var params = {
			"id":id
		}
		var callback = function(data){
			$("#personModal").modal({
				backdrop:"static",
				keyboard:false
			});
			$("#personModal-body").html(data);
		}
		$.post(action,params,callback);
	}
	
</script>
</head>
<body style="font-family: 微软雅黑;">
	<div class="container" style="margin-top:20px;">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<h3>定向名单列表</h3>
				<form id="directionNameList" method="post"
					action="<%=basePath%>admin/nameList/directionNameQuery.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>定向编号：</label><input
						type="text" name="businessNo" id="businessNo"
						value="${snl.businessNo}" placeholder="--请输入定向编号--"> <label>定向方式：</label><select
						id="nameMode" name="nameMode">
						<option value="">--请选择--</option>
						<c:forEach items="${typemaps}" var="tm">
							<c:choose>
								<c:when test="${snl.nameMode eq tm.key}">
									<option value="${tm.key}" selected>${tm.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${tm.key}">${tm.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <label>定向类型：</label><select id="businessType" name="businessType">
						<option value="">--请选择--</option>
						<c:forEach items="${moldmaps}" var="mm">
							<c:choose>
								<c:when test="${snl.businessType eq mm.key}">
									<option value="${mm.key}" selected>${mm.value}</option>
								</c:when>
								<c:otherwise>
									<option value="${mm.key}">${mm.value}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <label>标题：</label><input type="text" name="businessName"
						id="businessName" value="${snl.businessName}"
						placeholder="--请输入标题--">
					<button id="query_btn" class="btn btn-default"
						onclick="directionNameList(1,9)">查询</button>
					<button type="reset" class="btn btn-default">重置</button>
				</form>
				<div style="margin-left: 85%">
					<button onclick="directionManualAdd(this);" class="btn btn-default">手动新增</button>
					<button onclick="templetManualAdd(this);" class="btn btn-default">模板新增</button>
				</div>
				<table class="table  table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>定向编号</td>
							<td>标题</td>
							<td>定向方式</td>
							<td>定向类型</td>
							<td>引用状态</td>
							<td>启用状态</td>
							<td>操作</td>
							<!-- <td>备注</td> -->
							<td>查看</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list}" var="pl" varStatus="vs">
							<tr class="text-center">
								<!-- 序号 -->
								<td>${vs.index+1}</td>
								<!-- 定向编号 -->
								<td>${pl.businessNo}</td>
								<!-- 标题 -->
								<td>${pl.businessName}</td>
								<!-- 定向方式 -->
								<td><c:forEach items="${typemaps}" var="tm">
										<c:if test="${pl.nameMode eq tm.key }">
										${tm.value}
									</c:if>
									</c:forEach></td>
								<!-- 定向类型 -->
								<td><c:forEach items="${moldmaps}" var="mm">
										<c:if test="${pl.businessType eq mm.key}">
										${mm.value}
									</c:if>
									</c:forEach></td>
								<!-- 引用状态 -->
								<td><c:forEach items="${quotemaps}" var="qm">
										<c:if test="${pl.isQuote eq qm.key}">
										${qm.value}
									</c:if>
									</c:forEach></td>
								<!-- 启用状态 -->
								<td><c:choose>
										<c:when test="${pl.isUse eq 2}">
											<font color="#FF0033;">停用</font>
										</c:when>
										<c:when test="${pl.isUse eq 1}">
											<font color="#00CC00;">启用</font>
										</c:when>
									</c:choose> <%-- <c:forEach items="${usemaps}" var="um">
									<c:if test="${pl.isUse eq um.key}">
										${um.value}
									</c:if>
								</c:forEach> --%></td>
								<!-- 操作 -->
								<td>
									<button type="button" class="btn" onclick="editSNL(this);"
										data-opid="${pl.id}">编辑</button> <c:if
										test="${pl.isQuote ne 1}">
										<button type="button" class="btn" onclick="deleteSNL(this);"
											data-opid="${pl.id}">删除</button>
									</c:if> <c:if test="${pl.isUse == 2}">
										<button type="button" class="btn" onclick="modifyIsUse(this);"
											data-businessno="${pl.businessNo}" data-isuse="${pl.isUse}">启用</button>
									</c:if> <c:if test="${pl.isUse == 1}">
										<button type="button" class="btn" onclick="modifyIsUse(this);"
											data-businessno="${pl.businessNo}" data-isuse="${pl.isUse}">停用</button>
									</c:if>
								</td>
								<!-- 详情 -->
								<td>
									<button type="button" class="btn"
										onclick="examineDetail(this);" data-opid="${pl.id}">详情</button>
									<button type="button" class="btn"
										onclick="finalPersonView(this);" data-opid="${pl.id}">人员查看</button>
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

	<!-- 手动新增 定向 -->
	<div id="newlyModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在选择新增方式
					</h4>
				</div>
				<div class="modal-body" id="newlyModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="sureCreate(this);" data-dismiss="modal">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 模板新增定向-->
	<div id="templateModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在选择新增方式
					</h4>
				</div>
				<div class="modal-body" id="templateModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="templateCreate(this);" data-dismiss="modal">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 查看详情 -->
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看定向名单详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 人员查看 -->
	<div id="personModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看人员信息
					</h4>
				</div>
				<div class="modal-body" id="personModal-body"></div>
			</div>
		</div>
	</div>
</body>
</html>