<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 <%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>还款批量详情记录详情列表</title>
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
 				    	<span>${repaymentDetail.rorderno}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款批次号</label>
 				    	<span>${repaymentDetail.rbatchno}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">投标/债转订单号</label>
 				    	<span>${repaymentDetail.utorderno}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">标号</label>
 				    	<span>${repaymentDetail.tenderItem.tno}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">标名称</label>
 				    	<span>${repaymentDetail.tenderItem.tname}</span>
 				  	</div>
				</div>
 				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">借款人</label>
 				    	<span>${repaymentDetail.outaccount.loginname}-${repaymentDetail.outaccount.realname}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">投资人</label>
 				    	<span>${repaymentDetail.inaccount.loginname}-${repaymentDetail.inaccount.realname}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">代还款人</label>
 				    	<span>${repaymentDetail.proxyaccount.loginname}-${repaymentDetail.proxyaccount.realname}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款状态 </label>
 				    	<span>
 				    		<c:choose>
	 			 				<c:when test="${repaymentDetail.repaystatus == 1}">待还款</c:when>
	 			 				<c:when test="${repaymentDetail.repaystatus == 2}">审核中</c:when>
	 			 				<c:when test="${repaymentDetail.repaystatus == 3}">待处理</c:when>
	 			 				<c:when test="${repaymentDetail.repaystatus == 4}">处理中</c:when>
	 			 				<c:when test="${repaymentDetail.repaystatus == 5}"><span style="color:blue;">已还款</span></c:when>
	 			 				<c:when test="${repaymentDetail.repaystatus == 6}"><span style="color:red;">还款失败</span></c:when>
	 			 				<c:when test="${repaymentDetail.repaystatus == 7}"><span style="color:red;">审核失败</span></c:when>
  	 			 			</c:choose>
 	 			 		</span>
 				  	</div>
				</div>
				 
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款期数</label>
 				    	<span>${repaymentDetail.periods}</span>
 				  	</div>
				</div>
				 
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款模式 </label>
 				    	<span>
 				    		<c:choose>
	 			 				<c:when test="${repaymentDetail.rmode == 1}">初始</c:when>
	 			 				<c:when test="${repaymentDetail.rmode == 2}">人工</c:when>
	 			 				<c:when test="${repaymentDetail.rmode == 3}">系统</c:when>
	 			 				<c:when test="${repaymentDetail.rmode == 4}">线下</c:when>
  	 			 			</c:choose>
 				    	</span>
 				  	</div>
				</div>
				 
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">总金额(本息) </label>
 				    	<span>${repaymentDetail.rprincipalint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">总金额（不含利息）</label>
 				    	<span>${repaymentDetail.rptotalamount}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">总利息</label>
 				    	<span>${repaymentDetail.rptotalint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">本金</label>
 				    	<span>${repaymentDetail.ramount}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">利息管理费</label>
 				    	<span>${repaymentDetail.interestexpense}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">本金利息</label>
 				    	<span>${repaymentDetail.rinterest}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">类现金</label>
 				    	<span>${repaymentDetail.rvoucher}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">类现金利息</label>
 				    	<span>${repaymentDetail.rvoucherint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">假现金</label>
 				    	<span>${repaymentDetail.rlvoucher}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">假现金利息</label>
 				    	<span>${repaymentDetail.rlvoucherint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">加息劵利息</label>
 				    	<span>${repaymentDetail.rintfee}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余本金</label>
 				    	<span>${repaymentDetail.restprincipal}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">已转让本金（不含增益）</label>
 				    	<span>${repaymentDetail.transferprincipal}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余类现金</label>
 				    	<span>${repaymentDetail.restvoucher}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">失效类现金</label>
 				    	<span>${repaymentDetail.disablevoucher}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">失效类现金利息</label>
 				    	<span>${repaymentDetail.disablevoucherint}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余假现金</label>
 				    	<span>${repaymentDetail.restlvoucher}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">失效假现金</label>
 				    	<span>${repaymentDetail.disablelvoucher}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余滞纳金</label>
 				    	<span>${repaymentDetail.restocamount}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">失效滞纳金</label>
 				    	<span>${repaymentDetail.disableocamount}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余类现金滞纳金</label>
 				    	<span>${repaymentDetail.vrestocamount}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">失效类现金滞纳金</label>
 				    	<span>${repaymentDetail.disablevocamount}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余加息卷收益</label>
 				    	<span>${repaymentDetail.restintprofit}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">失效加息卷收益</label>
 				    	<span>${repaymentDetail.disableintprofit}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余本金利息</label>
 				    	<span>${repaymentDetail.restamountintprofit}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余类现金利息</label>
 				    	<span>${repaymentDetail.restvoucherintprofit}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">剩余假现金利息</label>
 				    	<span>${repaymentDetail.restlvoucherintprofit}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">投标申请授权码</label>
 				    	<span>${repaymentDetail.authcode}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">逾期滞纳金额</label>
 				    	<span>${repaymentDetail.overdueamount}</span>
 				  	</div>
				</div>
			  
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">银行返回码</label>
 				    	<span>${repaymentDetail.retcode}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8" >
					    <label class="col-sm-2 text-right">是否债转</label>
 				    	<span>${repaymentDetail.isdarepay==1?'是':'否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8" >
					    <label class="col-sm-2 text-right">是否逾期</label>
 				    	<span>${repaymentDetail.isoverdue==1?'是':'否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8" >
					    <label class="col-sm-2 text-right">是否提前</label>
 				    	<span>${repaymentDetail.isahead==1?'是':'否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8" >
					    <label class="col-sm-2 text-right">是否代偿</label>
 				    	<span>${repaymentDetail.isproxyrepay==1?'是':'否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否提交</label>
 				    	<span>${repaymentDetail.issubmit == 1 ? '是' : '否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">是否审核</label>
 				    	<span>${repaymentDetail.isaudit == 1 ? '是' : '否'}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">审核人</label>
 				    	<span>${repaymentDetail.auditman}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款时间（计划表生成时间）</label>
 				    	<span>${gj:formatDate(repaymentDetail.rtime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">借款人提交还款时间</label>
 				    	<span>${gj:formatDate(repaymentDetail.submittime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
 				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">提交银行时间</label>
 				    	<span>${gj:formatDate(repaymentDetail.operatetime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">还款实际到账日期</label>
 				    	<span>${gj:formatDate(repaymentDetail.rprealtime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">审核时间</label>
 				    	<span>${gj:formatDate(repaymentDetail.audittime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
					    <label class="col-sm-2 text-right">制表时间</label>
 				    	<span>${gj:formatDate(repaymentDetail.addtime,'yyyy-MM-dd HH:mm:ss')}</span>
 				  	</div>
				</div>
				 
			</div>
   		</div>
	</div>
 </body>
</html>