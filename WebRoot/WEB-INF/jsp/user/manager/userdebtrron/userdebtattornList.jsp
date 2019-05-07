<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"> --%>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">--%>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userdebtattorn/list.js"></script>
<style>
.pagination {
	display: inline-block;
	padding-left: 0;
	margin: 20px 0;
	border-radius: 4px;
}

.pagination>li {
	display: inline;
}

.pagination>li>a, .pagination>li>span {
	position: relative;
	float: left;
	padding: 6px 12px;
	margin-left: -1px;
	line-height: 1.42857143;
	color: #337ab7;
	text-decoration: none;
	background-color: #fff;
	border: 1px solid #ddd;
}

.pagination>li:first-child>a, .pagination>li:first-child>span {
	margin-left: 0;
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px;
}

.pagination>li:last-child>a, .pagination>li:last-child>span {
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
}

.pagination>li>a:hover, .pagination>li>span:hover, .pagination>li>a:focus,
	.pagination>li>span:focus {
	z-index: 3;
	color: #23527c;
	background-color: #eee;
	border-color: #ddd;
}

.pagination>.active>a, .pagination>.active>span, .pagination>.active>a:hover,
	.pagination>.active>span:hover, .pagination>.active>a:focus,
	.pagination>.active>span:focus {
	z-index: 2;
	color: #fff;
	cursor: default;
	background-color: #337ab7;
	border-color: #337ab7;
}

.pagination>.disabled>span, .pagination>.disabled>span:hover,
	.pagination>.disabled>span:focus, .pagination>.disabled>a, .pagination>.disabled>a:hover,
	.pagination>.disabled>a:focus {
	color: #777;
	cursor: not-allowed;
	background-color: #fff;
	border-color: #ddd;
}

.pagination-lg>li>a, .pagination-lg>li>span {
	padding: 10px 16px;
	font-size: 18px;
	line-height: 1.3333333;
}

.pagination-lg>li:first-child>a, .pagination-lg>li:first-child>span {
	border-top-left-radius: 6px;
	border-bottom-left-radius: 6px;
}

.pagination-lg>li:last-child>a, .pagination-lg>li:last-child>span {
	border-top-right-radius: 6px;
	border-bottom-right-radius: 6px;
}

.pagination-sm>li>a, .pagination-sm>li>span {
	padding: 5px 10px;
	font-size: 12px;
	line-height: 1.5;
}

.pagination-sm>li:first-child>a, .pagination-sm>li:first-child>span {
	border-top-left-radius: 3px;
	border-bottom-left-radius: 3px;
}

.pagination-sm>li:last-child>a, .pagination-sm>li:last-child>span {
	border-top-right-radius: 3px;
	border-bottom-right-radius: 3px;
}

.pager {
	padding-left: 0;
	margin: 20px 0;
	text-align: center;
	list-style: none;
}

.pager li {
	display: inline;
}

.pager li>a, .pager li>span {
	display: inline-block;
	padding: 5px 14px;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 15px;
}

.pager li>a:hover, .pager li>a:focus {
	text-decoration: none;
	background-color: #eee;
}

.pager .next>a, .pager .next>span {
	float: right;
}

.pager .previous>a, .pager .previous>span {
	float: left;
}

.pager .disabled>a, .pager .disabled>a:hover, .pager .disabled>a:focus,
	.pager .disabled>span {
	color: #777;
	cursor: not-allowed;
	background-color: #fff;
}
</style>
<title>itemList</title> 


	<div id="itemList_table" style="font-family: 微软雅黑">
		<div>
			<div>
				<form method="post" id="userDebtAttorn-from">
					<div style="margin-left: 20px">
						<input type="hidden" id="pageNum" name="pageNum" /> 
						<input type="hidden" id="pageSize" name="pageSize" />
					</div>	
				</form>
			</div>
			<table class="table table-hover">
				<tr class="text-center"
					style="background: #ccc;">
						<td>序号</td>
						<td>投资日期</td>
						<td>债权ID</td>
						<td>待收本金</td>
						<td>借款年利率</td>
						<td>操作</td>
					</tr>
				<tbody id="text" style="text-align: center;">
					<!-- 这里面${item.id }是点的model里面的属性 -->
					<c:forEach items="${pagehelper.list}" var="item" varStatus="status">

						<tr id="item_tr_${item.id }">
							<%-- <td>${item.id }</td> --%>
							<td>${status.count}</td>
							<td><c:if test="${!empty item.tbegintime}">${sf.format(item.tbegintime)}</c:if></td>
							<td><c:if test="${!empty item.tenderid}">${item.tenderid}</c:if></td>
							<td>
								<c:if test="${!empty item.utprinamount}">${df.format(item.utprinamount)}</c:if>
								<c:if test="${empty item.utprinamount}">0.00</c:if>
							</td>	
							
							<td>
								<c:if test="${!empty item.yearprofit}">${df.format(item.yearprofit)}</c:if>
								<c:if test="${empty item.yearprofit}">0.00</c:if>
							</td>
							<td>
							<button class="btn" data-toggle="modal" data-dismiss="modal" data-target="#myModal">详情</button> 	<%-- onclick="details('${item.daorderno}')" --%>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<div id="page_div" style="margin-left: 35px;">
				<%@ include file="../../../common/pagehelper.jsp"%>
			</div>
		</div>
	</div>

	<!-- 详情模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body" id="mymodal"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
