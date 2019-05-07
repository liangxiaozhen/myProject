<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户奖品邮寄地址</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
/* 分页查询用戶获奖信息 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#selectuserAddress").submit()
}
/* 备注显示字符个数限制*/
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

/* 查看用户站外奖品详情 */
function query_useraddress_detail(id) {
	var action = "${pageContext.request.contextPath}/admin/useraddress/PostAddressDetail.action";
	var param = {
		"id" : id
	}
	var callback = function(data) {
		$("#detailModal").modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#modal-body").html(data);
	}
	$.post(action, param, callback);
}
$(function() {
	/* 重置查询条件 */
	$("#reset").click(function() {
		document.getElementById("isdefaddress").options[0].selected = true;
		$("#login").val(" ");
	})
});
</script>
</head>
<body>
	<!-- 显示收货人的地址 -->
	<div class="container" style="width: 90%;">
		<form
			action="${pageContext.request.contextPath}/admin/useraddress/queryUserAddressList.action"
			method="post" role="form" id="selectuserAddress">
			<input type="hidden" id="pageNum" name="pageNum" /> <input
				type="hidden" id="pageSize" name="pageSize" />
			<ul class="list-inline" style="margin: 10px;">
				<li><label class="">用户名:</label></li>
				<li><input type="text" id="login" placeholder="输入用户姓名,可模糊查询"
					name="userBaseAccountInfo.loginname" id="loginname" /></li>
				<li><label>默认地址:</label> <select name="isdefaddress"
					id="isdefaddress">
						<option value="" selected="selected">--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
				</select></li>
				<li><input type="submit" value="查询" class="btn btn-default"
					id="btn" /></li>
				<li><input type="button" value="重置" class="btn btn-default"
					id="reset" /></li>
			</ul>
		</form>
		<c:if test="${!empty  pagehelper.list }">
			<table class="table table-bordered  table-hover"
				style="border: 1px solid silver;">
				<tr style="background-color: rgba(238, 238, 238, 0.98);"
					class="trcolor">
					<td>用户名</td>
					<td>所在地区</td>
					<td>详细地址</td>
					<td>邮编</td>
					<td>收货人</td>
					<td>电话</td>
					<td>手机</td>
					<td>备注</td>
					<td>操作</td>
					<td>默认地址</td>
				</tr>
				<c:forEach items="${pagehelper.list}" var="adr">
					<tr class="trcolor">
						<td>${adr.userBaseAccountInfo.loginname }</td>
						<td>${adr. addressProvince}&nbsp;${adr. addressCity}&nbsp;${adr.addressDistrict}</td>
						<td>${adr.addressStreet}</td>
						<td>${adr.zipcode }</td>
						<td>${adr.recipients }</td>
						<td>${adr.telephone}</td>
						<td>${adr. mobliephone}</td>
						<td><p limit="10" data-toggle="tooltip"
								title="<h5>${adr.remark}</h5>">${adr.remark}</p></td>
						<td>
							<button type="button" class="btn btn-default"
								onclick="query_useraddress_detail(' ${adr.id}')">查看详情</button>
						</td>
						<c:choose>
							<c:when test="${adr. isdefaddress==1}">
								<td><c:out value="默认地址"></c:out></td>
							</c:when>
							<c:otherwise>
								<td><c:out value=" "></c:out></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty pagehelper.list}">
			<h3 class="col-md-4 col-md-offset-2">还没有收货地址哦</h3>
		</c:if>
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
						<h4 class="modal-title" id="myModalLabel">用户奖品邮寄地址详情</h4>
					</div>
					<div class="modal-body" id="modal-body"></div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div id="page_div">
			<%@ include file="../../common/pagehelper.jsp"%>
		</div>
	</div>
</body>
</html>