<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
<style>
.text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>

</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-lg-12 column">
				<h3>特定时间</h3>
				<br>
				<form id="form-select" method="post" action="${action }">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" /> <label>时间类型:</label>
					<select id="select-timetype" name="timetype">
						<option value="">---请选择---</option>
						<option value="提现">提现</option>
						<option value="奖品">奖品</option>
					</select>&nbsp;&nbsp;&nbsp; <label>时间包名称：</label> <select id="select-timeno"
						name="timeno">
						<option value="">---请选择---</option>
						<c:if test="${!empty timeNames }">
							<c:forEach items="${timeNames }" var="item">
								<option value="${item.timeno}">${item.timename}</option>
							</c:forEach>
						</c:if>
					</select>&nbsp;&nbsp;&nbsp; <label>优先级：</label><select name="wlevel"
						id="select-wlevel">
						<option value="">请选择</option>
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
					</select>&nbsp;&nbsp; <label>最小提现金额:</label> <input id="select-minmoney"
						type="text" name="minmoney"
						style="width: 90px; text-align: center;">&nbsp;&nbsp;&nbsp;
					<label>最大提现金额:</label> <input type="text"
						style="width: 90px; text-align: center;" id="select-maxmoney"
						name="maxmoney"> <br /> <label>开始时间:</label> <input
						type="text" name="btime" id="select-btime-text" class="Wdate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					&nbsp;&nbsp; <label>结束时间:</label> <input type="text" name="etime"
						id="select-etime-text" class="Wdate"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <br />
					<input class="btn btn-default" type="submit" value="查询">&nbsp;&nbsp;
					<input class="btn btn-default" type="reset" value="重置">
				</form>
				<c:if test="${action eq 'queryAllForUpdate.action' }">
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
							<td>时间包类型</td>
							<td>时间包名称</td>
							<td>优先级</td>
							<td>开始时间</td>
							<td>结束时间</td>
							<td>提现金额</td>
							<td>备注</td>
							<td>详情</td>
							<c:if test="${action eq 'queryAllForUpdate.action' }">
								<td>操作</td>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="item"
							varStatus="status">
							<tr class="text-center">
								<td>${status.count }</td>
								<td>${item.timetype }</td>
								<td>${item.timename }</td>
								<td><c:choose>
										<c:when test="${empty item.wlevel }">
									默认</c:when>
										<c:otherwise>
								${item.wlevel } </c:otherwise>
									</c:choose></td>
								<td>${item.btimeStr }</td>
								<td>${item.etimeStr }</td>
								<td>${item.minmoney }<br>${item.maxmoney }</td>
								<td><p class="remark-p text-center tzui-tips"
										title="${item.remark }">${item.remark }</p></td>
								<td>
									<button class="btn btn-default" data-toggle="modal"
										data-target="#detailsModal" id="details-btn"
										data-backdrop="static" onclick="forwardDetailUI('${item.id}')">详情</button>
								</td>
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
	$(function() {
		$(".remark-p").each(function(i) {
			var num = $(this).html();
			if (num.length > 5) {
				$(this).html(num.substr(0, 5) + "...");
			}
		});
		$("#select-timetype").val("${specialTime.timetype}");
		$("#select-timeno").val("${specialTime.timeno}");
		$("#select-wlevel").val("${specialTime.wlevel}");
		$("#select-btime-text").val("${specialTime.btimeStr}");
		$("#select-etime-text").val("${specialTime.etimeStr}");
		$("#select-minmoney").val("${specialTime.minmoney }");
		$("#select-maxmoney").val("${specialTime.maxmoney }");
		$("#select-timetype").change(function(){
			var action="querySpecialTimeName.action";
			var params={"timeType":$(this).val()};
			var callback=function(data){
				var timeNames = document.getElementById("select-timeno");
				timeNames.options[0] = new Option("---请选择---", '');
				timeNames.options[0].selected = true;
				for (var i = 0; i < data.length; i++) {
					timeNames.options[timeNames.length] = new Option(
							data[i].timename, data[i].timeno);
				}
			}
			document.getElementById("select-timeno").length = 0;
			$.post(action,params,callback,'json');
		});
		
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
		var action="del_UI.action";
		var params={"id":id};
		var callback=function(data){
			$("#del-modal-body").html(data);
		};
		$.post(action,params,callback);
	}
	//编辑更新
	function update() {
		if (validateUpdateForm()) {
			var action = "update.action"
			var callback = function(data) {
				if (data.result == '1') {
					alert("操作：编辑失败。开始时间必须大于结束时间。");
					$("#update-etime-text").focus();
				} else if (data.result == '2') {
					alert("操作：编辑成功。");
					queryAllPerson("", "");
				} else if (data.result = '3') {
					alert("操作：添加失败,同类型、优先级下存在重叠时间段");
				}
			}
			$.post(action, $('#update-form').serialize(), callback, 'json');
		}
	}
	//保存
	function insert() {
		if (validateInsertForm()) {
			var action = "addSpecialTime.action"
			var callback = function(data) {
				if (data.result == '1') {
					alert("操作：添加失败。开始时间必须大于结束时间。");
					$("#insert-etime-text").focus();
				} else if (data.result == '2') {
					alert("操作：添加成功。");
					queryAllPerson("", "");
				} else if (data.result = '3') {
					alert("操作：添加失败,同类型、优先级下存在重叠时间段");
				}
			}
			$.post(action, $('#insert-form').serialize(), callback, 'json');
		}
	}
	//删除
	function delByID() {
		var id = $("#del-sTime-id").val();
		var action = "delete.action";
		var params = {
			"id" : id
		};
		var callback = function(data) {
			alert(data);
			queryAllPerson("", "");
		};
		$.post(action, params, callback, 'json');
	}
</script>
</body>
</html>