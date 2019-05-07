<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".remark-p").each(function(i) {
			var num = $(this).html();
			if (num.length > 10) {
				$(this).html(num.substr(0, 10) + "...");
			}
		});
		$("#query-loginname").val("${rName.loginname}");
		$("#query-realname").val("${rName.realname}");
		$("#query-remark").val("${rName.remark}");
	});

	//跳转详情页面
	function forwardDetailUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id,
			"uid" : 1
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//跳转编辑页面
	function forwardUpdateUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id,
			"uid" : 2
		};
		var callback = function(data) {
			$("#update-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//跳转新增页面
	function forwardInsertUI() {
		var action = "insert_UI.action";
		var callback = function(data) {
			$("#insert-modal-body").html(data);
		}
		$.post(action, null, callback);
	}
	//跳转 删除 UI
	function forwardDelUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id,
			"uid" : 3
		};
		$.post(action, params, function(data) {
			$("#del-modal-body").html(data);
		});
	}
	//编辑更新
	function update() {
		if (validateUpdateForm()) {
			var action = "update.action"
			var callback = function(data) {
				alert(data);
				queryAllPerson("", "");
			}
			$.post(action, $('#update-form').serialize(), callback, 'json');
		}
	}
	//保存
	function insert() {
		if (validateInsertForm())
		{
			var checkboxs=$("input[name='userID']:checked");
			var size=checkboxs.size();
			if(size>0){
				var userID="";
				for(i=0;i<size;i++){
					userID+=checkboxs[i].value+",";
				}
				var nameType=$("#insert-nametype-select").val();
				var name=$("#insert-name-select").val();
				var remark=$("#insert-remark-text").val();
				var params={"nameType":nameType,"name":name,"userID":userID,"remark":remark};
				var action="batchInsert.action";
				var callback=function(data){
					alert(data);
					queryAllPerson("", "");
				};
				$.post(action,params,callback,'json');
			}
		}
	}
	//删除
	function delByID() {
		var id = $("#del-rname-id").val();
		var action = "delete.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			alert(data);
			$("#form-select")[0].reset();
			queryAllPerson("", "");
		};
		$.post(action, params, callback, 'json');
	}
	/*
	 * 批量删除UI
	 */
	function batchDelForUI() {
		var checkboxs = $("input[name='selectID']:checked");
		var size = checkboxs.size();
		if (size > 0) {
			if (size != 1) {
				var ids = "";
				for (i = 0; i < size; i++) {
					ids += checkboxs[i].value + ",";
				}
				var action = "batchDelForUI.action";
				var params = {
					"ids" : ids
				};
				$.post(action, params, function(data) {
					$("#batchDelModal").modal({
						backdrop : 'static'
					}).modal('show');
					$("#batchDel-modal-body").html(data);
				});
			} else {
				$("#delModal").modal({
					backdrop : 'static'
				}).modal('show');
				forwardDelUI(checkboxs[0].value);
			}
		}
	}
	/*
	 * 批量删除
	 */
	function batchDel() {
		var ids = $("#batchDel-ids").val();
		var action = "batchDel.action";
		var params = {
			"ids" : ids
		};
		$.post(action, params, function(data) {
			alert(data);
			queryAllPerson('', '');
		}, 'json');
	}
	/*
	 * 批量编辑UI
	 */
	function batchUpdateForUI() {
		var checkboxs = $("input[name='selectID']:checked");
		var size = checkboxs.size();
		if (size > 0) {
			if (size != 1) {
				var ids = "";
				for (i = 0; i < size; i++) {
					ids += checkboxs[i].value + ",";
				}
				var action = "batchUpdateForUI.action";
				var params = {
					"ids" : ids
				};
				$.post(action, params, function(data) {
					$("#batchUpdateModal").modal({
						backdrop : 'static'
					}).modal('show');
					$("#batchUpdate-modal-body").html(data);
				})
			} else {
				$("#updateModal").modal({
					backdrop : 'static'
				}).modal('show');
				forwardUpdateUI(checkboxs[0].value);
			}
		}
	}
	/*
	*批量编辑  
	*/
	function batchUpdate(){
		if(validateUpdateForm()){
			var action="batchUpdate.action";
			$.post(action,$("#batchUpdate-form").serialize(),function(data){
				alert(data);
				queryAllPerson('', '');
			},'json');
		}
	}
	/*
	 *	全选/全不选
	 */
	function selectAll(obj) {
		var checkboxs = document.getElementsByName("selectID");
		for (i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
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
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>名单</h3>
				<br>
				<form id="form-select" method="post" action="${action }">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>用户名
						&nbsp;&nbsp;: &nbsp;&nbsp;</label><input type="text"
						style="text-align: center;" id="query-loginname" name="loginname">
					&nbsp;&nbsp;&nbsp;<label>真实姓名：</label><input type="text"
						style="text-align: center;" id="query-realname" name="realname">
					&nbsp;&nbsp; <label>备 注 :</label> <input
						style="text-align: center;" type="text" id="query-remark"
						name="remark"> <br> <input class="btn btn-default"
						type="submit" value="查询">&nbsp;&nbsp; <input
						class="btn btn-default" type="reset" value="重置">
				</form>
				<br>
				<div align="right" style="width: 100%">
					<span style="margin-right: 300px;">总人数：<b>${pagehelper.total}人</b></span>
					<c:if test="${action eq 'queryAllForUpdate.action' }">
						<button style="margin-right: 50px;" class="btn btn-default"
							onclick="batchUpdateForUI()">批量转移</button>
						<button style="margin-right: 50px;" class="btn btn-default"
							onclick="batchDelForUI()">批量删除</button>
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#insertModal" id="add-btn" data-backdrop="static"
							onclick="forwardInsertUI()">新增</button>
					</c:if>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<c:if test="${action eq 'queryAllForUpdate.action'}">
								<td><input type="checkbox" onclick="selectAll(this)"></td>
							</c:if>
							<td>序号</td>
							<td>大名单</td>
							<td>小名单</td>
							<td>用户名</td>
							<td>真实姓名</td>
							<td>会员等级</td>
							<td>详情</td>
							<c:if test="${action eq 'queryAllForUpdate.action' }">
								<td>操作</td>
							</c:if>
							<td>备注</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<c:if test="${action eq 'queryAllForUpdate.action'}">
									<td><input name="selectID" type="checkbox"
										value="${item.id }"></td>
								</c:if>
								<td>${status.count }</td>
								<td>${item.nametype }</td>
								<td>${item.name }</td>
								<td>${item.loginname }</td>
								<td>${item.realname }</td>
								<td>${item.ugrade.ugradename }</td>
								<td><button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">详情</button></td>
								<c:if test="${action eq 'queryAllForUpdate.action' }">
									<td>
										<div class="btn-group" role="group" aria-label="...">
											<button class="btn btn-default" data-toggle="modal"
												data-target="#updateModal" id="update-btn"
												data-backdrop="static"
												onclick="forwardUpdateUI('${item.id}') ">编辑</button>
											<button class="btn btn-default" data-toggle="modal"
												data-target="#delModal" id="del-btn" data-backdrop="static"
												onclick="forwardDelUI('${item.id}')">删除</button>
										</div>
									</td>
								</c:if>
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