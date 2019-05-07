<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
<script type="text/javascript">
	var basePath = "${basePath}"
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/swfupload/tz_upload.js"></script>
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}

img {
	width: 100px;
	height: 100px;
}

a:link {
	font-size: 12px;
	color: blue
}

a:visited {
	font-size: 12px;
	color: blue
}

a:hover {
	font-size: 12px;
	color: red
}
</style>
<script type="text/javascript">
	$(function() {
		$(".remark-p").each(function() {
			var num = $(this).html();
			if (num.length > 5) {
				$(this).html(num.substr(0, 5) + "...");
			}
		});
		$("#itino-select").val("${imageTextSetting.itino}");
	});
	/*
	 * 新增页面 
	 */
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		};
		$.post(action, null, callback);
	}
	/*
	 * 新增
	 */
	function insert() {
		if (validateInsert()) {
			var action = "insert.action";
			var callback = function(data) {
				returnDate(data);
			};
			$.post(action, $("#insert-form").serialize(), callback, 'json');
		}
	}
	/*
	 *删除页面
	 */
	function forwardDelUI(id) {
		var action = "delete_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#del-modal-body").html(data);
		};
		$.post(action, params, callback);
	}

	/*
	 * 删除
	 */
	function delByID() {
		var action = "delete.action";
		var params = {
			"id" : $("#del-id").val()
		};
		var callback = function(data) {
			returnDate(data);
		}
		$.post(action, params, callback, 'json');
	}
	/*
	 * 回调
	 */
	function returnDate(data) {
		if (data == "success") {
			alert("提示：操作成功！");
			queryAllPerson('${pagehelper.pageNum}', '');
		}
		if (data == "fail") {
			alert("提示：操作失败！")
		}
	}
	/*
	 * 详情
	 */
	function forwardDetailUI(id) {// 跳转详情页面
		var action = "queryById.action";
		var params = {
			"id" : id,
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	/*
	 * 编辑页面
	 */
	function forwardUpdateUI(id) {
		var action = "update_UI.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		};
		$.post(action, params, callback);
	}
	/*
	 * 编辑
	 */
	function update() {
		if (validateUpdate()) {
			var action = "update.action";
			var callback = function(data) {
				returnDate(data);
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}
	/*
	 * 内容查看
	 */
	function forwardContentUI(id) {
		var url = "content_UI.action?id=" + id;
		window.location.href = url;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>图文内容</h3>
				<br>
				<form id="form-select" method="post" action="list.action">
					<input type="hidden" id="pageNum" name="pageNum"> <input
						type="hidden" id="pageSize" name="pageSize"> 项目名称：<select
						name="itino" id="itino-select">
						<option value="">---请选择---</option>
						<c:forEach items="${imageTextItemList }" var="item">
							<option value="${item.id }">${item.itiname }</option>
						</c:forEach>
					</select> <br />
					<button class="btn btn-default">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="button"
						onclick="history.go(-1)">返回</button>
				</form>
				<div align="right">
					<button class="btn btn-primary" data-toggle="modal"
						data-target="#insertModal" id="add-btn" data-backdrop="static"
						onclick="forwardInsertUI()">新增</button>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>编号</td>
							<td>项目名称</td>
							<td>排序</td>
							<td>标题</td>
							<td>副标题1</td>
							<td>是否可用</td>
							<td>内容</td>
							<td>详情</td>
							<td>操作</td>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td><a href="list.action?itino=${item.itino }">${item.imageTextItem.itiname }</a></td>
								<td>${item.serialno }</td>
								<td>${item.title }</td>
								<td>${item.subtitle1 }</td>
								<td><c:if test="${item.isuse eq 0}">
										<span style="color: red;">不可用</span>
									</c:if> <c:if test="${item.isuse eq 1}">
										<span style="color: green;">可用</span>
									</c:if></td>
								<td><button class="btn btn-default"
										onclick="forwardContentUI('${item.id}')">查看</button></td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" data-backdrop="static"
										onclick="forwardDetailUI('${item.id}')">查看</button></td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#updateModal" data-backdrop="static"
										onclick="forwardUpdateUI('${item.id}')">编辑</button>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#delModal" data-backdrop="static"
										onclick="forwardDelUI('${item.id}')">删除</button></td>
								<td><p class="remark-p text-center" title="${item.remark }">${item.remark }</p></td>
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
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>
