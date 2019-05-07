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
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#normal-rankno-text").change(function() {
			$("#normal-rankno-lb").html("");
		});
	});
	/*
	 * 跳转编辑页面
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
	 *编辑
	 */
	function update() {
		if (validateUpdate()) {
			var action = "updateForStop.action";
			var callback = function(data) {
				if (data == "success") {
					alert("提示：操作成功。");
					queryAllPerson('${pagehelper.pageNum}', '');
				}
				if (data == "fail") {
					alert("提示：操作失败。");
				}
			};
			$.post(action, $("#update-form").serialize(), callback, 'json');
		}
	}

	/*
	 *跳转转为正常会员界面
	 */
	function forwardNormalUI(id, name) {
		$("#normal-id").val(id);
		$("#normal-ugradename").html(name);
	}
	/*
	 * 转为正常
	 */
	function toNormal() {
		var rankNo = $("#normal-rankno-text").val();
		var id = $("#normal-id").val();
		if (rankNo == '' || rankNo == null) {
			$("#normal-rankno-lb").html("*必须填写排列序号");
			return false;
		}
		if (parseInt(rankNo) < 1) {
			$("#normal-rankno-lb").html("*排列序号必须大于1");
			return false;
		}

		var action = "toNormal.action";
		var params = {
			"id" : id,
			"rankNo" : rankNo
		};
		var callback = function(data) {
			if (data == "success") {
				alert("提示：操作成功。");
				queryAllPerson('${pagehelper.pageNum}', '');
			}
			if (data == "fail") {
				alert("提示：操作失败。");
			}
		};
		$.post(action, params, callback, 'json');
	}
	/*
	 * 查看详情UI
	 */
	function forwardDetailUI(id) {
		var action = "queryById.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			$("#detail-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
</script>
</head>
<body>
	<div style="width: 80%;" class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>废弃会员等级</h3>
				<br>
				<form id="form-select" method="post" action="queryAllStop.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
					<!-- 
					<button class="btn btn-default" type="submit">查询</button>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="reset">重置</button> -->
				</form>
				<div style="width: 150px; margin-left: 1200px;">
					<ol class="breadcrumb">
						<li><a href="queryAll.action">正常等级</a></li>
						<li class="active">废弃等级</li>
					</ol>
				</div>
				<table class="table table-hover table-condensed">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>等级名称</td>
							<td>积分购买</td>
							<td>现金购买</td>
							<td>状态</td>
							<td>定向升级</td>
							<td>详情</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${item.ugradename }</td>
								<td>${item.needpoints }</td>
								<td><fmt:formatNumber pattern="###,###,##0.00"
										value="${item.needamount }" /></td>
								<td><c:if test="${item.status eq 2 }">
										<button class="btn btn-default" data-backdrop="static"
											data-toggle="modal" data-target="#userGradeNormalModal"
											onclick="forwardNormalUI('${item.id}','${item.ugradename}')">
											<span class="green">转为正常</span>
										</button>
									</c:if></td>
								<td><c:if test="${item.isspecify eq 2 }">无</c:if> <c:if
										test="${item.isspecify eq 1 }">
										<button class="btn btn-default">查看</button>
									</c:if></td>
								<td>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">详情</button>
								</td>
								<td><div class="btn-group" role="group" aria-label="...">
										<button class="btn btn-default" data-toggle="modal"
											data-target="#updateModal" id="update-btn"
											data-backdrop="static"
											onclick="forwardUpdateUI('${item.id}') ">编辑</button>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="./../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
	<!-- 转为正常   模态框 -->
	<div class="modal fade" id="userGradeNormalModal" tabindex="-1"
		role="dialog" aria-labelledby="userGradeNormalModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="userGradeNormalModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
							<font color="red"><b>转为正常会员</b></font> <input type="hidden"
								id="normal-id">
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-3 col-md-offset-1"  style=" line-height: 18px;">
							等级名称：&nbsp;&nbsp;&nbsp;</div>
						<div>
							<label id="normal-ugradename"></label>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-3 col-md-offset-1" style=" line-height: 18px;">
							排列序号：&nbsp;&nbsp;&nbsp;
						</div>
						<div>
							<input type="text" placeholder="请输入排列序号"
								style="width: 120px; text-align: center; line-height: 18px;"
								onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
								id="normal-rankno-text">&nbsp;&nbsp;<span
								style="color: red;"><label id="normal-rankno-lb"></label></span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="toNormal()">确定</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 引入模态框 -->
	<%@ include file="../../common/modal.jsp"%>
</body>
</html>
