<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 <%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的利息管理费设置详情列表</title>
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
					    <label class="col-sm-2 text-right">计算方式</label>
 				    	<span>${interestExpense.gfitype == 1 ? '用户等级' : '标的风险等级'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">标的类型</label>
 				    	<span>${interestExpense.ttypestr}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">会员等级</label>
 				    	<span>${interestExpense.ugradestr}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">利息管理费百份比</label>
 				    	<span>${interestExpense.iepercent}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">最高利息管理收费金额</label>
 				    	<span>${interestExpense.maxiefee}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">资金清算是否需要审核</label>
 				    	<span>${interestExpense.isaudit == 1 ? '是' : '否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否为模板</label>
 				    	<span>${interestExpense.istemplet == 1 ? '是' : '否'}</span>
 				  	</div>
				</div>
 				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">添加人</label>
 				    	<span>${interestExpense.addman}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8" >
					    <label class="col-sm-2 text-right">添加时间 </label>
 				    	<span >${interestExpense.addtimestr}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">备注</label>
 				    	<span>${interestExpense.remark}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">制表人 </label>
 				    	<span>${interestExpense.addman}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">备注 </label>
 				    	<span>${interestExpense.remark}</span>
 				  	</div>
				</div>
			</div>
   		</div>
	</div>
 </body>
</html>