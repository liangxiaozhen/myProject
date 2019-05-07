<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loanMaterial.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
  <body style="font-family: 微软雅黑;">
<div class="container" style="width:80%">
		<div class="row clearfix">
			<div class="col-md-12 column" style="border-style:none; border-width:5px; border-color:Black; background-color:none;">
      	

	<h3>借款人资料查看</h3>
		<form id="selectgfundsIntByConditionsee" method="post" action="${pageContext.request.contextPath}/admin/loanmaterial/selectAllUserLoanmat.action">
			<input type="hidden" id="pageNum" name="pageNum"/>
			<input type="hidden" id="pageSize" name="pageSize"/> 
		</form>
		<c:if test="${!empty pagehelper.list}">
		<a href="javascript:history.go(-1)">返回上一页</a>
				<table class="table table-bordered table-hover" id="personList_table">
					<thead>
						<tr style="background:#ccc;vertical-align: text-top!important;border:1px solid #666;text-align: center;font-size: 15px;">
							<td>序号</td>
							<td>用户名</td>
							<td>真实姓名</td>
							<td>资料编号</td>
							<td>资料类型</td>
							<td>资料名称</td>
							<td>资料内容</td>					
							<td>提交时间</td>
							<td>审核状态</td>
							<!-- <td>操作</td>  -->
							<td>操作</td> 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="loan" varStatus="sta">
							<tr style="text-align: center;font-size: 13px;">
							    <td>${sta.count}</td>
								<td>
									${loan.accountInfo.loginname}
								</td>
								<td>
									${loan.accountInfo.realname}
								</td>
								
								<td><a href="#" onclick="selectMaterialByloan('${loan.loanno}')">${loan.loanno}</a></td>	
								<td>
								<c:if test="${loan.materialtype eq 1}">公共</c:if> 
								<c:if test="${loan.materialtype eq 2}">补充</c:if>  
								</td>
								<td>${loan.materialname}</td>
								<td>
								<c:if test="${!empty loan.materialcontent}">
								    ${loan.materialcontent}
								</c:if>
								<c:if test="${empty loan.materialcontent}">
								    <a href="#" onclick="open('http://localhost:8080/pic/${loan.materialpic}','介绍','width=500,height=440,left=550,top=250,resizable=no,scrollbars=no,status=yes,toolbar=no,location=no,menubar=no,menu=yes')">查看图片</a>
								</c:if>
								</td>
								<td><fmt:formatDate value="${loan.addtime}" type="date" pattern="yyyy-MM-dd"/></td>
								<td>
								    <span style="color:rgb(217,83,79);">${loan.auditstatus ==3  ? '不合格':''}</span> 
									<span style="color:rgb(94,184,92);">${loan.auditstatus ==2  ? '合格':''}</span>	
									<span>${loan.auditstatus ==0  ? '待审核':''}</span>
									<span style="color: rgb(66,139,202)">${loan.auditstatus ==1  ? '审核中':''}</span> 
								</td>
								<%-- <td><a href="${pageContext.request.contextPath}/loan/selectminute.action?id=${gfundsInt.id}">详细资料</a></td>
								<td><a href="${pageContext.request.contextPath}/picpath/pictureadmin.action?baseid=${gfundsInt.baseid}">点我查看</a></td> --%>
								<%-- <td>
								   <!-- 待审核 -->
								   <c:if test="${loan.auditstatus eq 0}">
									   <button disabled="disabled" style="cursor: not-allowed;color:#A0A0A0;">通过</button>
									   <button disabled="disabled" style="cursor: not-allowed;color:#A0A0A0;">拒绝</button>
								   </c:if>
								   <!-- 审核中 -->
								   <c:if test="${loan.auditstatus eq 1}">
									   <button onclick="pass('${loan.id}','p')">通过</button>
									   <button onclick="pass('${loan.id}','f')">拒绝</button>
									   <button onclick="together('${loan.baseid}','${loan.loanno}')" title="点击可同步公共资料">同步</button>
								   </c:if>
								   <c:if test="${loan.auditstatus eq 3}">
								       <button onclick="tochange('${loan.id}')"  title="修改补充资料">修改</button>
								       <button onclick="together('${loan.baseid}','${loan.loanno}')" title="点击可同步公共资料">同步</button>
								   </c:if>
								</td> --%>
								<td>
								   <button onclick="toDetail('${loan.id}')">详情</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
				</c:if>
			</div>
			<c:if test="${empty pagehelper.list}">
				   <label>暂无数据！</label>
				   <a href="javascript:history.go(-1)">返回上一页</a>
				</c:if>
		</div>
</div>
</body>
</body>
</html>