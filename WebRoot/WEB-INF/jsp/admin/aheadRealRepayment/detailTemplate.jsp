<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 <%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提前实际还款记录实体详情列表</title>
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
					    <label class="col-sm-2 text-right">还款流水号</label>
 				    	<span>${aheadRealRepayment.rorderno}</span>
 				  	</div>
				</div>
  				
 				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款批次号</label>
 				    	<span>${aheadRealRepayment.rrealbatchno}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款状态</label>
  				    		<c:choose>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 1}">待还款</c:when>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 2}">审核中</c:when>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 3}">待处理</c:when>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 4}">处理中</c:when>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 5}"><span style="color:blue;">已还款</span></c:when>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 6}"><span style="color:red;">还款失败</span></c:when>
	 			 				<c:when test="${aheadRealRepayment.repaymentDetail.repaystatus == 7}"><span style="color:red;">审核失败</span></c:when>
  	 			 			</c:choose>
  				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际总金额</label>
 				    	<span>${aheadRealRepayment.rprincipalint}</span>
 				  	</div>
				 </div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际总利息</label>
 				    	<span>${aheadRealRepayment.rptotalint}</span>
 				  	</div>
				</div>
 				
 				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际真现金</label>
 				    	<span>${aheadRealRepayment.rptotalamount}</span>
 				  	</div>
				</div>
 				
 				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际类现金</label>
 				    	<span>${aheadRealRepayment.rvoucher}</span>
 				  	</div>
				</div>
 				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际本金利息</label>
 				    	<span>${aheadRealRepayment.rinterest}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际类现金利息</label>
 				    	<span>${aheadRealRepayment.rvoucherint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">欠收总利息</label>
 				    	<span>${aheadRealRepayment.norectotalint}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">欠收本金利息</label>
 				    	<span>${aheadRealRepayment.norecrinterest}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">欠收类现金利息</label>
 				    	<span>${aheadRealRepayment.norecrvoucherint}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际假现金利息</label>
 				    	<span>${aheadRealRepayment.rlvoucherint}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">欠收假现金利息</label>
 				    	<span>${aheadRealRepayment.norecrlvoucherint}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际加息劵利息</label>
 				    	<span>${aheadRealRepayment.rintfee}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">欠收加息劵利息</label>
 				    	<span>${aheadRealRepayment.norecrintfee}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">实际作废类现金利息</label>
 				    	<span>${aheadRealRepayment.discardvoucherint}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">欠收作废类现金利息</label>
 				    	<span>${aheadRealRepayment.norecdiscardvoucherint}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">利息管理费</label>
 				    	<span>${aheadRealRepayment.interestexpense}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否系统勾兑</label>
 				    	<span>${aheadRealRepayment.isblending == 1 ? '是':'否'}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否人工勾兑</label>
 				    	<span>${aheadRealRepayment.ismanblending == 1 ? '是':'否'}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">系统勾兑时间</label>
 				    	<span>${gj:formatDate(aheadRealRepayment.sysbtime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">人工勾兑时间</label>
 				    	<span>${gj:formatDate(aheadRealRepayment.manbtime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				 
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">系统勾兑接收数据时间</label>
 				    	<span>${gj:formatDate(aheadRealRepayment.sysrectime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">人工勾兑接收数据时间</label>
 				    	<span>${gj:formatDate(aheadRealRepayment.receivetime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">制表时间</label>
 				    	<span>${gj:formatDate(aheadRealRepayment.addtime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">请求数据包</label>
 				    	<span>${aheadRealRepayment.reqquerydata}</span>
 				  	</div>
				</div>
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">接收数据包</label>
 				    	<span>${aheadRealRepayment.recresultdata}</span>
 				  	</div>
				</div>
				 
				
				 <div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">备注</label>
 				    	<span>${aheadRealRepayment.remark}</span>
 				  	</div>
				</div>
				 
			</div>
   		</div>
	</div>
 </body>
</html>