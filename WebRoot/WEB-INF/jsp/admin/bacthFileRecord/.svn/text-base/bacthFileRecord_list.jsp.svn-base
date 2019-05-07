<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量文件记录</title>
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
		selectBacthFileRecord(pageNum, pageSize);
	}
	function selectBacthFileRecord(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectBacthFileRecord").submit();
	}

	/*控制备注里面的备注*/
	$(function() {

		//用以为jQuery扩展一个实例函数，jQuery对象可以调用此函数。
		jQuery.fn.limit = function() {
			var self = $(".remark-p");
			self.each(function() {
				var objString = $(this).text();
				var objLength = $(this).text().length;
				var num = $(this).attr("limit");
				if (objLength > num) {
					objString = $(this).text(
							objString.substring(0, num) + "...");
				}
			})
		}
		//调用
		$(".remark-p").limit();
		//激活tooltip工具，并向提示工具插入html
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		//重置查询条件
		$("#reset")
				.click(
						function() {
							document.getElementsByName("sendFileName")[0].value = '';
							document.getElementsByName("getFileName")[0].value = '';
							document.getElementsByName("batchNo")[0].value = '';
							document.getElementsByName("fileType")[0].options[0].selected = true;
							document.getElementsByName("isSend")[0].options[0].selected = true;
							document.getElementsByName("sendResult")[0].options[0].selected = true;
							document.getElementsByName("isDealResult")[0].options[0].selected = true;
						});

	});

	//查看详情
	function view_details(obj) {
		var id = $(obj).data("opid");
		var action = "${pageContext.request.contextPath}/admin/batchFile/viewDetails.action";
		var params = {
			"id" : id
		}
		var callback = function(data) {
			$("#myModal").modal({
				backdrop : 'static',
				keyboard : false
			});
			$("#modal-body").html(data);
		}
		$.post(action, params, callback);
	}
	//下载文件
	function downloadFile(obj) {
		var id = $(obj).data("opid");
		var action = "${pageContext.request.contextPath}/admin/tender/downloadFile.action";
		var params = {
			"id" : id
		}
		var callback = function(data) {
            if(data==""){
                alert("没有要处理的文件");
            }else{
                alert(data);
            }
		}
		$.post(action, params, callback);
	}
	//手动上传文件
	function upload_file(obj) {
		var id = $(obj).data("opid");
		var action = "${pageContext.request.contextPath}/admin/batchFile/uploadFile.action";
		var params = {
			"id" : id
		}
		var callback = function(data) {
			var obj = JSON.parse(data);
			var str = obj["result"];
			if (str == 'success') {
				//文件上传成功
				alert("文件上传成功");
				queryAllPerson('${pagehelper.pageNum}',
						'${pagehelper.pageSize}');
			} else {
				alert("文件上传失败");
			}
		}
		$.post(action, params, callback);
	}
	//手动下载文件
	function process_file(obj) {
		var id = $(obj).data("opid");
		var action = "${pageContext.request.contextPath}/admin/batchFile/processFile.action";
		var params = {
			"id" : id
		}
		var callback = function(data) {
			var obj = JSON.parse(data);
			var str = obj["result"];
			if (str == "success") {
				//文件处理成功
				alert("文件处理成功");
				queryAllPerson('${pagehelper.pageNum}',
						'${pagehelper.pageSize}');
			} else {
				alert("文件处理失败");
			}
		}
		$.post(action, params, callback);
	}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<!-- <h3>批量文件记录</h3> -->
	<div class="container" style="width: 80%; margin-top: 20px;">
		<form id="selectBacthFileRecord" class="form-inline"
			action="${pageContext.request.contextPath}/admin/batchFile/queryList.action"
			method="post">
			<input type="hidden" id="pageNum" name="pageNum"
				value="${pagehelper.pageNum}" /> <input type="hidden" id="pageSize"
				name="pageSize" value="${pagehelper.pageSize}" />
			<div class="form-group">
				<label>上传文件名称：</label> <input type="text"
					class="form-control input-sm" name="sendFileName"
					value="${bfr.sendFileName}" placeholder="请输入上传文件名称">
			</div>
			<div class="form-group">
				<label>下载文件名称：</label> <input type="text"
					class="form-control input-sm" name="getFileName"
					value="${bfr.getFileName}" placeholder="请输入下载文件名称">
			</div>
			<div class="form-group">
				<label>业务文件批次号：</label> <input type="text"
					class="form-control input-sm" name="batchNo"
					value="${bfr.batchNo}" placeholder="请输入业务文件批次号">
			</div>
			<div class="form-group">
				<label>业务文件类型：</label> <select class="form-control input-sm"
					name="fileType">
					<option value="">--请选择--</option>
					<c:forEach items="${businesstype}" var="bt">
						<c:choose>
							<c:when test="${bfr.fileType eq bt.key}">
								<option value="${bt.key}" selected="selected">${bt.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${bt.key}">${bt.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>是否已发送到银行：</label> <select class="form-control input-sm"
					name="isSend">
					<option value="">--请选择--</option>
					<c:forEach items="${sendbank}" var="sb">
						<c:choose>
							<c:when test="${bfr.isSend eq sb.key}">
								<option value="${sb.key}" selected="selected">${sb.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${sb.key}">${sb.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>发送结果：</label> <select class="form-control input-sm"
					name="sendResult">
					<option value="">--请选择--</option>
					<c:forEach items="${sendresult}" var="sr">
						<c:choose>
							<c:when test="${bfr.sendResult eq sr.key}">
								<option value="${sr.key}" selected="selected">${sr.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${sr.key}">${sr.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>是否已处理：</label> <select class="form-control input-sm"
					name="isDealResult">
					<option value="">--请选择--</option>
					<c:forEach items="${isdealfile}" var="idf">
						<c:choose>
							<c:when test="${bfr.isDealResult eq idf.key}">
								<option value="${idf.key}" selected="selected">${idf.value}</option>
							</c:when>
							<c:otherwise>
								<option value="${idf.key}">${idf.value}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-default btn-sm">查询</button>
			<button type="button" id="reset" class="btn btn-default btn-sm">重置</button>
		</form>
		<div style="margin:15px 0;"></div>
		<table class="table  table-hover">
			<thead>
				<tr class="text-center" style="background: #ccc;">
					<td>序号</td>
					<td>文件路径</td>
					<td>上传文件名称</td>
					<td>下载文件名称</td>
					<td>业务文件类型</td>
					<td>是否已发送到银行</td>
					<td>发送结果</td>
					<td>是否已处理结果文件</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pagehelper.list}" var="pl" varStatus="status">
					<tr class="text-center">
						<td>${status.index+1}</td>
						<td><p data-toggle="tooltip" class="remark-p text-center"
								title="<h5>${pl.filePath}</h5>" limit="80">${pl.filePath }</p></td>
						<td><p data-toggle="tooltip" class="remark-p text-center"
								title="<h5>${pl.sendFileName}</h5>" limit="80">
								<c:if test="${empty pl.sendFileName}">--</c:if>
								<c:if test="${!empty pl.sendFileName}">${pl.sendFileName}</c:if></td>
						<td><p data-toggle="tooltip" class="remark-p text-center"
								title="<h5>${pl.getFileName}</h5>" limit="80">${pl.getFileName}</p></td>
						<!-- 业务文件类型 -->
						<td><c:forEach items="${businesstype}" var="bt">
								<c:choose>
									<c:when test="${pl.fileType eq bt.key}">${bt.value}</c:when>
								</c:choose>
							</c:forEach></td>
						<!-- 是否已发送到银行 -->
						<td><c:forEach items="${sendbank}" var="sb">
								<c:choose>
									<c:when test="${pl.isSend eq sb.key}">${sb.value}</c:when>
								</c:choose>
							</c:forEach></td>
						<!-- 发送结果 -->
						<td><c:forEach items="${sendresult}" var="sr">
								<c:choose>
									<c:when test="${pl.sendResult eq sr.key}">${sr.value}</c:when>
								</c:choose>
							</c:forEach></td>
						<!-- 是否已处理结果文件 -->
						<td><c:forEach items="${isdealfile}" var="idf">
								<c:choose>
									<c:when test="${pl.isDealResult eq idf.key}">${idf.value}</c:when>
								</c:choose>
							</c:forEach></td>
						<td>
							<button type="button" class="btn btn-default"
								data-opid="${pl.id}" onclick="view_details(this);">查看</button>

							<c:if
								test="${pl.isSend ne 1}">
								<button type="button" class="btn btn-default"
									data-opid="${pl.id}" onclick="upload_file(this);">手动上传</button>
							</c:if> <c:if
								test="${pl.isSend eq 1 && pl.isDealResult ne 1 && pl.isDealResult ne 2}">
								<c:choose>
									<c:when
										test="${pl.fileType eq 3}">
									<button type="button" class="btn btn-default"
											data-opid="${pl.id}" onclick="downloadFile(this);">下载</button>
									</c:when>
									<c:otherwise>
										<button type="button" class="btn btn-default"
												data-opid="${pl.id}" onclick="process_file(this);">手动下载</button>
									</c:otherwise>
								</c:choose>

							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page_div">
			<%@ include file="../../common/pagehelper.jsp"%>
		</div>
	</div>

	<!-- 模态框 -->
	<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看批量文件详情
					</h4>
				</div>
				<div class="modal-body" id="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>