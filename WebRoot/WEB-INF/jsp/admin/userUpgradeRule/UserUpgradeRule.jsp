<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用戶站外奖品页</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
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

/* 分页查询用戶获奖信息 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#selectuserRule").submit()
}


/* 查看用户等级升级规则 */
function query_useraward_detail(id) {
	var action = "${pageContext.request.contextPath}/admin/gradeRule/userRuleDetail.action ";
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

//修改
function  toUpdateUi(id){
	var action = "${pageContext.request.contextPath}/admin/gradeRule/userRuleDetailt.action";
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
};
 
$(function() {
	/* 重置查询条件 */
	$("#reset").click(function() {
		document.getElementById("method").options[0].selected = true;
		$("#gradet").val(" ");
	})
});
</script>
</head>
<body>
	<div class="container" style="width: 90%;">
		<div class="row clearfix">
			<div class=" column">
				<h3>用户等级升级规则</h3>
				<form id="selectuserRule" method="post" role="form"
					action="${pageContext.request.contextPath}/admin/gradeRule/queryRuleList.action">
					<input type="hidden" id="pageNum" name="pageNum" /> <input
						type="hidden" id="pageSize" name="pageSize" />
					<ul class="list-inline">
						<li><label>升级方式:</label> <select name="method" id="method">
								<option value="">--请选择--</option>
								<c:if test="${!empty methodmaps }">
									<c:forEach items="${methodmaps}" var="umt">
										<option value="${umt.key }">${umt.value }</option>
									</c:forEach>
								</c:if>
						</select></li>
						<li><label>级别:</label> <input type="text" id="gradet"
							name="grade" placeholder="按级别查找"
							onkeyup='this.value=this.value.replace(/\D/gi,"")' /></li>
						<li><input type="submit" value="查询" class="btn btn-default"
							id="btn" /></li>
						<li><input type="button" value="重置" class="btn btn-default"
							id="reset" /></li>
						<li><a
							href=" ${pageContext.request.contextPath}/admin/gradeRule/jump.action?flag=jump_left"
							class="btn btn-default">添加</a></li>
					</ul>
				</form>
				<div style="margin: 20px; overflow: hidden;">&nbsp;</div>
				<table class="table table-bordered table-hover"
					id="personList_table">
					<thead>
						<tr style="background: #ccc; text-align: center;">
							<td><strong>级别</strong></td>
							<td><strong>升级方式</strong></td>
							<td><strong>时间有效期</strong></td>
							<td><strong>支付金额</strong></td>
							<td><strong>需要积分</strong></td>
							<td><strong>单位(年/月/日)</strong></td>
							<td><strong>备注</strong></td>
							<td><strong>操作</strong></td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty pagehelper.list}">
							<c:forEach items="${pagehelper.list }" var="userRule">
								<tr style="text-align: center;">
									<td>${userRule.grade }</td>

									<!-- 升级方式 -->
									<td><c:forEach items="${methodmaps }" var="meth">
											<c:choose>
												<c:when test="${userRule.method==meth.key }">${meth.value }</c:when>
											</c:choose>
										</c:forEach></td>

									<td>${userRule.effecttime }</td>

									<td>${userRule.payamount }</td>

									<!-- 需要积分 -->
									<td>${userRule.needbonuspoints }</td>

									<td>${userRule.unit}</td>
									<td><p limit="8" data-toggle="tooltip"
											title="<h5>${userRule.remark}</h5>">${userRule.remark}</p></td>
									<td>
										<button type="button" class="btn btn-default"
											onclick="query_useraward_detail('${userRule.id}')">详情</button>
										<button type="button" class="btn btn-default"
											onclick="toUpdateUi('${userRule.id}')">修改</button> <a
										href="${pageContext.request.contextPath}/admin/gradeRule/deluserRule.action?id=${userRule.id}"
										class="btn  btn-default"
										onclick="return confirm('删除后无法恢复,确定要删除吗')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty pagehelper.list}">
							<tr>
								<td colspan="6">没有相关数据</td>
							</tr>
						</c:if>
					</tbody>
				</table>

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
								<h4 class="modal-title" id="myModalLabel">用户等级升级规则</h4>
							</div>
							<div class="modal-body" id="modal-body"></div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
							</div>
						</div>
					</div>
				</div>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>