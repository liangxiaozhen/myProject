<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 <%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的提前还款奖励单个投资人记录详情列表</title>
 <%@include file="/WEB-INF/jsp/common/public.jsp" %>
 </head>
<body>
 	<div class="container">
		<div class="row">
 			<div class="col-md-12" style="margin-top:15px;">
 			
 			   <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2">借款人</label>
	    				  <span>${aheadRepayOneRecord.bmanidusername}</span>
					 </div>
  				</div>
  				
  				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2">投资人</label>
	    				  <span>${aheadRepayOneRecord.investoridusername}</span>
					 </div>
  				</div>
  				
  				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2">标号</label>
	    				  <span>${aheadRepayOneRecord.tno}</span>
					 </div>
  				</div>
   				
  				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2">标名称</label>
	    				  <span>${aheadRepayOneRecord.tname}</span>
					 </div>
  				</div>
  				
 				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2">提前还款奖励流水号</label>
	    				  <span>${aheadRepayOneRecord.arorderno}</span>
					 </div>
  				</div>
 				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款流水号</label>
 				    	<span>${aheadRepayOneRecord.rorderno}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">本金欠收利息</label>
 				    	<span>${aheadRepayOneRecord.prinpoorint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">借款人罚金</label>
 				    	<span>${aheadRepayOneRecord.bpenalty}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">罚金奖励方式</label>
 				    	<span>
 				    		<c:choose>
 				    			<c:when test="${aheadRepayOneRecord.penaltytype == 1}">定额</c:when>
 				    			<c:when test="${aheadRepayOneRecord.penaltytype == 2}">百分比</c:when>
 				    			<c:when test="${aheadRepayOneRecord.penaltytype == 3}">最高</c:when>
  				    		</c:choose> 
 				    	</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否处理</label>
 				    	<span>
 				    	 	<c:choose>
 				    	 		<c:when test="${aheadRepayOneRecord.isgrant == 0}">未处理</c:when>
 				    	 		<c:when test="${aheadRepayOneRecord.isgrant == 1}">已处理</c:when>
  				    	 	</c:choose>
 				    	</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否审核</label>
 				    	<span>
 				    		<c:choose>
 				    			<c:when test="${aheadRepayOneRecord.isaudit == 0}">未审核</c:when>
 				    			<c:when test="${aheadRepayOneRecord.isaudit == 1}">审核通过</c:when>
 				    			<c:when test="${aheadRepayOneRecord.isaudit == 2}">审核不通过</c:when>
  				    		</c:choose>
 				    	</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">审核时间</label>
 				    	<span>${gj:formatDate(aheadRepayOneRecord.audittime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
 				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">创建时间</label>
 				    	<span>${gj:formatDate(aheadRepayOneRecord.madetime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8" >
					    <label class="col-sm-2 text-right">处理日期</label>
 				    	<span>${gj:formatDate(aheadRepayOneRecord.payoutdate,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">备注 </label>
 				    	<span>${aheadRepayOneRecord.remark}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">审核人 </label>
 				    	<span>${aheadRepayOneRecord.auditman}</span>
 				  	</div>
				</div>
  			</div>
   		</div>
	</div>
 </body>
</html>