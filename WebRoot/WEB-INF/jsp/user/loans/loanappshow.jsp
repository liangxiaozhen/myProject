<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借款信息展示</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".ugrade").each(function(i) {
		var num = $(this).text();
		if (num.length > 5) {
			$(this).text(num.substr(0, 5) + "...");
		}
	})
});

//详情
function toDetail(id){
	var action="${pageContext.request.contextPath}/user/loan/deleteByid.action";
	var param={
		"id":id
	}
	var callback=function(data){
		$("#myModal").modal({
    		backdrop : 'static',
			keyboard : false
    	});
    	$("#modal-body").html(data);
	}
	$.post(action,param,callback);
}

//补充资料上传
function toupload(id,baseid){
	var bid=id;
	var action="${pageContext.request.contextPath}/user/loan/tobeginupload.action";
	//var url="${pageContext.request.contextPath}/user/loan/usercommonbucho.action?id="+id;
	var param={
		"baseid":baseid
	}
	var callback=function(data){
		if(data == "fail"){
			alert("提示：请先上传公共资料!");
			window.location.href="${pageContext.request.contextPath}/user/loan/jumpusercommon.action?baseid="+bid;
		}
		if(data == "succ"){
			window.location.href="${pageContext.request.contextPath}/user/loan/usercommonbucho.action?id="+bid;
		}
	}
	$.post(action,param,callback,'json');
}

//查看公共资料
function lookData(baseid,loanno){
	var url="${pageContext.request.contextPath}/user/loan/lookData.action?baseid="+baseid+"&loanno="+loanno;
	window.location.href=url;
}
</script>
<style type="text/css">
#ht{
margin-left: 40%;
}
</style>
</head>
<body style="font-family: 微软雅黑;font-size: 13px;">
<div id="ht"><h2><span class="glyphicon glyphicon-user"></span><em>我的借款记录</em></h2></div>
<div class="col-md-12 column tab-pane fade in active" id="home" >
	<table  class="table table-bordered  table-hover text-center">
		<tr>
		<td><strong>序号</strong></td>
		<td><strong>借款编号</strong></td>
		<td><strong>借款人</strong></td>
		<td><strong>借款金额</strong></td>
		<td><strong>借款类型</strong></td>
		<td><strong>状态</strong></td>
		<td><strong>申请方式</strong></td>
		<td><strong>借款资料</strong></td>
		<td><strong>查看资料</strong></td>
		<td><strong>申请时间</strong></td>
		<td><strong>操作</strong></td>
		</tr>
		<c:forEach var="item" items="${listloanapp}" varStatus="loansta">
		<tr>
		    <td>${loansta.count}</td>
			<td>${item.loanno}</td>
			<td>${user.realname}</td>
			<td>${item.loanamount}</td>
			<td>${item.loantypestr}</td>
			<td>
			<c:choose>
			   <c:when test="${item.appstatus eq 0}">审核中</c:when>
			   <c:when test="${item.appstatus eq 1}">审核成功</c:when>
			   <c:when test="${item.appstatus eq 2}">审核失败</c:when>
			   <c:when test="${item.appstatus eq 3}">投标中</c:when>
			   <c:when test="${item.appstatus eq 4}">已流标</c:when>
			   <c:when test="${item.appstatus eq 5}">还款中</c:when>
			   <c:when test="${item.appstatus eq 6}">已发布</c:when>
			</c:choose>
			</td>
			<td>
			  <c:choose>
			    <c:when test="${item.apptype eq 1}">自申请</c:when>
			    <c:when test="${item.apptype eq 2}">代申请</c:when>
			    <c:when test="${item.apptype eq 3}">接口申请</c:when>
			  </c:choose>
			</td>
			<td>
			  <c:choose>
			    <c:when test="${item.mastatus eq 1}">未填写</c:when>
			    <c:when test="${item.mastatus eq 2}">待审核</c:when>
			    <c:when test="${item.mastatus eq 3}">审核中</c:when>
			    <c:when test="${item.mastatus eq 4}">审核成功</c:when>
			    <c:when test="${item.mastatus eq 5}">审核失败</c:when>
			  </c:choose>
			</td>
		    <td> 
		       <a href="#" onclick="lookData('${item.baseid}','${item.loanno}')">查看补充资料</a>
			</td>
			<td><fmt:formatDate value="${item.apptime}" type="date" pattern="yyyy-MM-dd"/></td>
			<td><button class="btn btn-primary" onclick="toDetail('${item.id}')">详情</button>
			<c:if test="${item.mastatus eq 1}">
			   <button class="btn btn-primary" onclick="toupload('${item.id}','${item.baseid}')">上传补充资料</button>
			</c:if>
			<c:if test="${item.mastatus eq 5}">
			   <button class="btn btn-primary" onclick="toupload('${item.id}','${item.baseid}')">重新上传</button>
			</c:if>   
			</td>
		</tr>
		</c:forEach>
		</table>
		<!-- 模态框 -->
				<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"></h4>
							</div>
							<div class="modal-body" id="modal-body">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
							</div>
						</div>
					</div>
				</div>
	</div>
</body>
</html>