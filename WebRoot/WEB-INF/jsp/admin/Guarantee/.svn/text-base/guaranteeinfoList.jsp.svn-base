<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
th {
	text-align: center;
	background: #ccc;
}

td {
	text-align: center;
}

body {
	font-family: "微软雅黑"
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
				//				$(this).attr("title", objString);
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

	/* 分页查询担保公司资料列表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#guaranteeForm").submit()
	}

	var guaraList = {
		/* 新增页面 */
		insert : function() {
			window.location.href = "${pageContext.request.contextPath}/guarantee/toInsert.action";
		},
		/* 查看担保公司资料详情 */
		detail : function(id, obj) {
			var url = "${pageContext.request.contextPath}/guarantee/queryGuaranteeDetail.action";
			var obj = $(obj).text();
			var params = {
				"id" : id,
				"obj" : obj
			}
			var callback = function(data) {
				$("#myModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#modal-body").html(data);
			}
			$.post(url, params, callback);
		},
		/* 修改担保公司资料 */
		edit : function(id, obj) {
			var url = "${pageContext.request.contextPath}/guarantee/queryGuaranteeDetail.action";
			var obj = $(obj).text();
			var params = {
				"id" : id,
				"obj" : obj
			}
			var callback = function(data) {
				$("#area_div").html(data);
			}
			$.post(url, params, callback);
		},
		/* 删除担保公司资料 */
		dele : function(id, obj) {
			var result = confirm('是否删除！');
			if (result) {
				var url = "${pageContext.request.contextPath}/guarantee/deleteGuarantee.action";
				var param = {
					"id" : id
				}
				var callback = function(data) {
					var obj = $.parseJSON(data);
					if (obj.result == "success") {
						alert(obj.result);
						$("#guara_tr_" + obj.guaraId).remove();
					} else {
						alert(obj.result);
					}
				}
				$.post(url, param, callback);
			}
		},
		/* 重置查询条件 */
		reset : function() {
			document.getElementById("name").value = '';
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<form method="post" id="guaranteeForm"
						action="${pageContext.request.contextPath}/guarantee/queryGuaranteeList.action">
						<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
							type="hidden" id="pageSize" name="pageSize" value="" />
						<div class="col-md-4 input-group">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span></span> <input type="text"
								name="name" id="name" class="form-control" value="${name}"
								placeholder="请输入公司名称搜索..."> <span
								class="input-group-addon"><button type="submit"
									style="border-style: none">搜索</button></span>
							<div class="col-md-2 col-md-offset-3">
								<button type="button" class="btn" onclick="guaraList.reset()">重置</button>
							</div>
						</div>
					</form>
					<div align="right">
						<input type="button" class="btn" value="新增"
							onclick="guaraList.insert()" />
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-condensed">
							<caption>
								<strong>用户使用券表</strong>
							</caption>
							<thead>
								<tr>
									<th>担保公司名称</th>
									<th>公司网站</th>
									<th>注册时间</th>
									<th>联系电话</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<c:if test="${!empty pagehelper.list}">
								<c:forEach items="${pagehelper.list}" var="guara">
									<tr id="guara_tr_${guara.id }">
										<td>${guara.name}</td>
										<td>${guara.website}</td>
										<td>${guara.regtimeStr}</td>
										<td>${guara.phone}</td>
										<td><p data-toggle="tooltip" limit="5"
												title="<h5>${guara.remark}</h5>">${guara.remark}</p></td>
										<td><div class="btn-group" role="group" aria-label="...">
												<button type="button" class="btn"
													onclick="guaraList.detail('${guara.id}',this)">查看详情</button>
												<button type="button" class="btn"
													onclick="guaraList.edit('${guara.id}',this)">修改</button>
												<button type="button" class="btn"
													onclick="guaraList.dele('${guara.id}',this)">删除</button>
											</div></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty pagehelper.list}">
								<tr>
									<td colspan="6">没有相关数据</td>
								</tr>
							</c:if>
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
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">担保公司资料详情</h4>
				</div>
				<div class="modal-body" id="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>