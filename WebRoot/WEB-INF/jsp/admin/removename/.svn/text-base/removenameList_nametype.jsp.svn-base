<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#select-nametype").val("${rName.nametype}");
		$("#select-name").val("${rName.name}");
	});

	//跳转详情页面
	function forwardDetailUI(name, nameType) {
		var action = "queryByNameType.action";
		var params = {
			"name" : name,
			"nameType" : nameType
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//跳转编辑页面
	function forwardUpdateUI(name, nametype,nameno) {
		var action = "nameTypForUpdateUI.action";
		var params = {
				"name" : name,
				"nametype" : nametype,
				"nameno":nameno
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//跳转新增页面
	function forwardInsertUI() {
		var action = "insertNameType_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		}
		$.post(action, null, callback);
	}
	//跳转 删除 UI
	function forwardDelUI(name, nametype,nameno) {
		var action = "nameTypForDelUI.action";
		var params = {
			"name" : name,
			"nametype" : nametype,
			"nameno":nameno
		};
		$.post(action, params, function(data) {
			$("#del-modal-body").html(data);
		});
	}
	//编辑更新
	function update() {
		if (validateUpdateForm()) {
			var action = "nameTypeUpdate.action";
			var callback = function(data) {
				switch(data){
				case "fail":
					alert("提示：该小名单已存在。");
					$("#update-name").focus();
					break;
				case "success":
					alert("提示：编辑成功。");
					queryAllPerson("", "");
					break;
				}
				
			}
			$.post(action, $('#update-form').serialize(), callback, 'json');
		}
	}
	//保存
	function insert() {
		if (validateInsertName()) {
			var action = "insertName.action";
			var callback = function(data) {
				switch (data) {
				case "fail":
					alert("提示：该小名单已存在，请重新输入。");
					$("#insert-name").focus();
					break;
				case "success":
					alert("提示：操作成功！");
					queryAllPerson("","");
					break;
				}
			}
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	//删除
	function delByID() {
		var nameno = $("#delNameNo").val();
		var action = "nameTypeDelete.action";
		var params = {
			"nameno" : nameno
		};
		var callback = function(data) {
			alert(data);
			$("#form-select")[0].reset();
			queryAllPerson("", "");
		};
		$.post(action, params, callback, 'json');
	}
	//停用UI
	function forwardCancelUserUI(name, nametype) {
		$("#namelb-cancel").html(name);
		$("#nametypelb-cancel").html(nametype);
	}
	//启用UI
	function forwardIsUserUI(name, nametype) {
		$("#namelb-is").html(name);
		$("#nametypelb-is").html(nametype);
	}
	function isUse() {// 启用
		var name = $("#namelb-is").html();
		var nameType = $("#nametypelb-is").html();
		var action = "isUse.action";
		var params = {
			"name" : name,
			"nameType" : nameType
		};
		$.post(action, params, function(data) {
			alert("提示：操作成功。");
			queryAllPerson('', '');
		});
	}
	function cancelUse() {// 停用
		var name = $("#namelb-cancel").html();
		var nameType = $("#nametypelb-cancel").html();
		var action = "cancelUse.action";
		var params = {
			"name" : name,
			"nameType" : nameType
		};
		$.post(action, params, function(data) {
			alert("提示：操作成功。");
			queryAllPerson('', '');
		});
	}
	//查询子目录
	function selectName(){
		var nameType=$("#select-nametype").val();
		var action="getName.action";
		var params={"nameType":nameType};
		var callback = function(data) {
			var names = document.getElementById("select-name");
			names.options[0] = new Option("---请选择---", '');
			names.options[0].selected = true;
			for (var i = 0; i < data.length; i++) {
				names.options[names.length] = new Option(data[i].name,data[i].name);
			}
		}
		document.getElementById("select-name").length = 0;
		$.post(action, params, callback, 'json');
	}
	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#form-select").submit();
	}
</script>
</head>
<body>
	<div style="width: 60%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>名单</h3>
				<br>
				<form id="form-select" method="post" action="${action }">
					<label>大名单:</label> <select id="select-nametype"
						onchange="selectName()" name="nametype">
						<option value="">---请选择---</option>
						<c:forEach items="${nameTypes }" var="item">
							<option value="${item.nametype}">${item.nametype}</option>
						</c:forEach>
					</select>&nbsp;&nbsp; <label>小名单：</label> <select id="select-name"
						name="name">
						<option value="">---请选择---</option>
						<c:if test="${!empty names }">
							<c:forEach items="${names }" var="item">
								<option value="${item.name}">${item.name}</option>
							</c:forEach>
						</c:if>
					</select><br> <input class="btn btn-default" type="submit" value="查询">&nbsp;&nbsp;
					<input class="btn btn-default" type="reset" value="重置">
				</form>
				<br>
				<c:if test="${action eq 'queryAllNameTypeForUpdate.action' }">
					<div align="right">
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#insertModal" id="add-btn" data-backdrop="static"
							onclick="forwardInsertUI()">新增</button>
					</div>
				</c:if>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
							<td>大名单</td>
							<td>小名单</td>
							<td>设置人</td>
							<td>设置时间</td>
							<td>详情</td>
							<c:if test="${action eq 'queryAllNameTypeForUpdate.action' }">
								<td>操作</td>
							</c:if>
							<td>使用状态</td>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.nameTypeCount }人<br>${item.nametype }</td>
								<td>${item.nameCount}人<br>${item.name }</td>
								<td>${item.addman }</td>
								<td><fmt:formatDate value="${item.addtime }"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static"
										onclick="forwardDetailUI('${item.name}','${item.nametype }')">详情</button></td>
								<c:if test="${action eq 'queryAllNameTypeForUpdate.action' }">
									<td width="200px;">
										<div class="btn-group" role="group" aria-label="...">
											<button class="btn btn-default" data-toggle="modal"
												data-target="#updateModal" id="update-btn"
												data-backdrop="static"
												onclick="forwardUpdateUI('${item.name}','${item.nametype }','${item.nameno }') ">编辑</button>
											<button class="btn btn-default" data-toggle="modal"
												data-target="#delModal" id="del-btn" data-backdrop="static"
												onclick="forwardDelUI('${item.name}','${item.nametype }','${item.nameno }')">删除</button>
										</div>
									</td>
									<td width="150px;"><c:if test="${item.isuse eq 0 }">
											<button class="btn btn-default" data-backdrop="static"
												data-toggle="modal" data-target="#removeNameIsuseModal"
												onclick="forwardIsUserUI('${item.name}','${item.nametype }')">
												<span class="green">设置启用</span>
											</button>
										</c:if> <c:if test="${item.isuse eq 1 }">
											<button class="btn btn-default" data-backdrop="static"
												data-toggle="modal" data-target="#removeNameCancelUseModal"
												onclick="forwardCancelUserUI('${item.name}','${item.nametype }')">
												<span class="red">设置停用</span>
											</button>
										</c:if></td>
								</c:if>
								<c:if test="${action eq 'queryAllNameType.action' }">
									<td><c:if test="${item.isuse eq 0 }">
											<span class="red">停用</span>
										</c:if> <c:if test="${item.isuse eq 1 }">
											<span class="green">启用</span>
										</c:if></td>
								</c:if>
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
	<!-- 停用  小名单目录-->
	<div class="modal fade" id="removeNameCancelUseModal" tabindex="-1"
		role="dialog" aria-labelledby="removeNameCancelUseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="removeNameCancelUseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					您是否停用 <font color='red'><label id="nametypelb-cancel"></label></font>
					中的<font color='red'> <label id="namelb-cancel"></label></font> 名单？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 启用  小名单目录 -->
	<div class="modal fade" id="removeNameIsuseModal" tabindex="-1"
		role="dialog" aria-labelledby="removeNameIsuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="removeNameIsuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					您是否启用 <font color='red'><label id="nametypelb-is"></label></font>
					中的<font color='red'> <label id="namelb-is"></label></font> 名单？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>