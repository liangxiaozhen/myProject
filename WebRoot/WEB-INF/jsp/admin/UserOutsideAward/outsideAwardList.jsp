<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用戶站外奖品页</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<style type="text/css">
	td {
		text-align: center;
		vertical-align: text-top !important;
		border: 1px solid #666;
	}
</style>
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
	
	//重置
	$("#reset_").click(function(){
		document.getElementById("uoatype").options[0].selected=true;
		document.getElementById("status").options[0].selected=true;
		document.getElementById("uoawardname").value="";
		document.getElementById("loginname").value="";
	});
});

/* 分页查询用戶获奖信息 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#selectuseroutAward").submit()
}


/* 查看用户站外奖品详情 */
function query_useraward_detail(id) {
	var action = "${pageContext.request.contextPath}/admin/outaward/queryOutawardDetail.action";
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

</script>
</head>
<body>
	<div class="container" style="width: 75%;margin-top:20px">
		<div class="row clearfix">
			<div class=" column">
				<h4>
					<font>用户站外奖品信息</font>
				</h4>
				<form id="selectuseroutAward" method="post" role="form"
					action="${pageContext.request.contextPath}/admin/outaward/queryOutawardList.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum }" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize }" />
					<ul class="list-inline">
						<li><label>奖品来源：</label> <select name="uoatype" id="uoatype">
								<option value="">--请选择--</option>
								<c:if test="${!empty uoatypemaps }">
									<c:forEach items="${uoatypemaps}" var="umt">
										<c:choose>
											<c:when test="${outsideAward.uoatype==umt.key }">
												<option value="${umt.key}" selected="selected">${umt.value}</option>
											</c:when>
											<c:otherwise>
												<option value="${umt.key }">${umt.value }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select></li>
						<li><label>发放状态：</label> <select name="status" id="status">
								<option value="">--请选择--</option>
								<c:if test="${!empty statusmaps }">
									<c:forEach items="${statusmaps}" var="smt">
										<c:choose>
											<c:when test="${outsideAward.status==smt.key }">
												<option value="${smt.key}" selected="selected">${smt.value}</option>
											</c:when>
											<c:otherwise>
												<option value="${smt.key }">${smt.value }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
						</select></li>
						<li><label>奖品名称：</label></li>
						<li><input type="text" name="uoawardname" id="uoawardname" value="${outsideAward.uoawardname}" placeholder="--请输入奖品名称--" /></li>

						<%--<li> 
			   <label>是否发放</label>
			<select name="issend"  id="issend">
						<option value="">--请选择--</option>
						<c:if test="${!empty issendmaps}">  
						<c:forEach items="${issendmaps }" var="imt" >
								<c:choose>
									<c:when test="${outsideAward.issend==imt.key }">
										<option value="${imt.key }" selected="selected">${imt.value }</option>
									</c:when>
									<c:otherwise>
								 		<option value="${imt.key }">${imt.value }</option>
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</c:if> 
			</select> 
			</li>--%>
						<%-- 		<li>
			<label>是否审核</label>
			<select name="isaudit"  id="isaudit">
						<option value="">--请选择--</option>
						<c:if test="${!empty isauditmaps}">  
						<c:forEach items="${isauditmaps }" var="idmt" >
								<c:choose>
									<c:when test="${outsideAward.isaudit==idmt.key }">
										<option value="${idmt.key }" selected="selected">${idmt.value }</option>
									</c:when>
									<c:otherwise>
								 		<option value="${idmt.key }">${idmt.value }</option>
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</c:if> 
			</select>
			</li> --%>
						<li><label>用户名:</label></li>
						<li><input type="text" placeholder="--输入用户姓名--"
							name="userBaseAccountInfo.loginname"
							value="${outsideAward.userBaseAccountInfo.loginname}"
							id="loginname" style="margin-bottom: -11.5px;" /></li>
						<li><input type="submit" value="查询" class="btn btn-default" /></li>
						<li><input type="button" id="reset_" value="重置" class="btn btn-default" /></li>
					</ul>
				</form>
				<!-- <div  style="margin: 20px;overflow: hidden;">&nbsp;</div> -->
				<table class="table table-bordered table-hover"
					id="personList_table">
					<thead>
						<tr style="background: #ccc; text-align: center;">
							<td><strong>序号</strong></td>
							<td><strong>用户名</strong></td>
							<td><strong>奖品编号</strong></td>
							<td><strong>发放编号</strong></td>
							<td><strong>奖品名称</strong></td>
							<td><strong>奖品来源</strong></td>
							<td><strong>发放状态</strong></td>
							<td><strong>奖品状态</strong></td>
							<!-- <td><strong>是否审核</strong></td> -->
							<!-- <td><strong>发放时间</strong></td>
							<td><strong>审核时间</strong></td> -->
							<td><strong>操作</strong></td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty pagehelper.list}">
							<c:forEach items="${pagehelper.list }" var="useraward"
								varStatus="vs">
								<tr style="text-align: center;">
									<!-- 序号 -->
									<td>${vs.index+1}</td>
									<!-- 用户名 -->
									<td>${useraward.userBaseAccountInfo.loginname }</td>
									<!-- 奖品编号 -->
									<td>${useraward.uoawardno }</td>
									<!-- 发放编号 -->
									<td>${useraward.sendno }</td>
									<!-- 奖品名称 -->
									<td>${useraward.uoawardname }</td>
									<!-- 奖品类型 -->
									<td><c:forEach items="${uoatypemaps}" var="umt">
											<c:choose>
												<c:when test="${useraward.uoatype==umt.key }">
							                      ${umt.value}
							                  </c:when>
											</c:choose>
										</c:forEach></td>

									<!-- 发放状态 -->
									<td><c:choose>
											<%-- <c:when test="${useraward.status==1}">待审核</c:when>
										<c:when test="${useraward.status==2}">待处理</c:when> --%>
											<c:when test="${useraward.status==3}">已领取</c:when>
											<%-- <c:when test="${useraward.status==4}">待确认</c:when>
										<c:when test="${useraward.status==5}">已经确认</c:when>
										<c:when test="${useraward.status==6}">发货中</c:when> --%>
											<c:when test="${useraward.status==7}">领取失败</c:when>
										</c:choose></td>
									<!-- 奖品状态 -->
									<td><c:choose>
											<c:when test="${useraward.isuse==4}">已使用</c:when>
										</c:choose></td>

									<!-- 是否审核 -->
									<%-- <td>
									<c:choose>
										<c:when test="${useraward.isaudit==0}">是</c:when>
										<c:when test="${useraward.isaudit==1}">否</c:when>
									</c:choose>
								</td> --%>
									<!-- 发放时间 -->
									<%-- <td><fmt:formatDate value="${useraward.uoatime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
									<!-- 审核时间 -->
									<%-- <td><fmt:formatDate value="${useraward.uoatime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
									<!-- 操作 -->
									<td><button type="button" class="btn btn-default"
											onclick="query_useraward_detail('${useraward.id}');">查看详情</button></td>
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
								<h4 class="modal-title" id="myModalLabel">用户站外奖品详情</h4>
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