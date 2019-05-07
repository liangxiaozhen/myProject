<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 		<table class="table table-hover">
	 	 	<thead>
		 		<tr class="text-center" style="background:#ccc;">
			 		<td>用户名</td>
			 		<td>用户类型</td>
  			 		<td>IP</td>
			 		<td>cookie</td>
			 		<td>登录时间</td>
			 		<td>操作时间</td>
	 		 		<td>操作内容</td>
	 		 		<td>备注</td>
    			 	<td>操作</td>
		 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 		<c:forEach items="${pagehelper.list}" var="user"> 
		 	 		<tr class="text-center">
		 	 			<td>${user.username}</td>
 				 		<td><span>${user.usertype == 1 ? '普通用户' :'管理员用户'}</span></td>
				 		<td>${user.ip}</td>
				 		<td>${user.cookie}</td>
				 		<td>${user.logintimestr}</td>
				 		<td>${user.opertimestr}</td>
				 		<td>${user.opercontent}</td>
				 		<td>${user.remark}</td>
   				 		<td>
  				 			<div class="btn-group">
								<button class="btn btn-default" onclick="userLog.low_detail(this)" data-opid="${user.id}">详情</button>
  							</div>
 				 		</td> 
 		 	 		</tr>
	 	 		</c:forEach>
	  	 	</tbody>
		 </table>
		 <div id="page_div">
			<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
		</div>
		
		
