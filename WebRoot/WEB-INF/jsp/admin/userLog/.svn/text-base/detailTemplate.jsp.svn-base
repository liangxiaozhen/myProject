<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 <%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户日志表详情列表</title>
 <%@include file="/WEB-INF/jsp/common/public.jsp" %>
 <style>
 	.text-center td{vertical-align: text-top!important;}
 	.input-group-addon a{text-decoration:none;}
   </style>
 </head>
<body>
 	<div class="container">
		<div class="row">
 			<div class="col-md-12" style="margin-top:15px;">
 			
 				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">用户名</label>
   				    	<span>${userLog.username}</span>
					 </div>
  				</div>
 				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">用户类型</label>
 				    	<span>${userLog.usertype == 1 ? '普通用户':'管理员用户'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">IP</label>
 				    	<span>${userLog.ip}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">cookie</label>
 				    	<span>${userLog.cookie}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">登录时间</label>
 				    	<span>${userLog.logintimestr}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">操作时间</label>
 				    	<span>${userLog.opertimestr}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">操作内容</label>
 				    	<span>${userLog.opercontent}</span>
 				  	</div>
				</div>
 			 
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">备注 </label>
 				    	<span>${userLog.remark}</span>
 				  	</div>
				</div>
			</div>
   		</div>
	</div>
 </body>
</html>