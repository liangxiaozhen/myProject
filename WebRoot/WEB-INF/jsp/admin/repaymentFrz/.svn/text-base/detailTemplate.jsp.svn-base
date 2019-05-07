<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批次还款记录详情列表</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
 .text-center td {
	vertical-align: text-top !important;
	border: 1px solid #666;
}

body {
	padding-bottom: 40px;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
.input-group-addon a {
	text-decoration: none;
}
</style>
 </head>
<body>
	<div style="width: 95%;margin-top: 15px; " class="container">
		<div class="row">
		
		
		<!-- 批次还款明细记录 start -->
		<div class="col-md-12" style="margin-top: 15px;">
 			<div class="well text-center" style="font-size: 14px; background: #d6e9c6; line-height: 0px;">批次还款明细记录详情</div>
 				<table class="table table-hover">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td>序号</td>
 							<td>还款流水号</td>
							<td>投标/债权转让订单号</td>
 							<td>投资人</td>
							<td>期数</td>
  							<td>本金</td>
							<td>计划利息</td>
							<td>实际利息</td>
							<td>利息管理费</td>
  							<td>还款状态</td>
							<td>是否提交</td>
 						</tr>
					</thead>
					<tbody>
						<c:forEach items="${repaymentDetails}" var="user" varStatus="index">
							<tr class="text-center">
								<td>${index.index +1 }</td>
 								<td>${user.rorderno }</td>
								<td>${user.utorderno }</td>
 								<td>${user.inaccount.loginname}-${user.inaccount.realname}</td>
								<td>${user.periods}</td>
								<td>${user.ramount+user.rvoucher}</td>
								<td>${user.rinterest+user.rvoucherint}</td>
								<c:choose>
										<c:when test="${not empty  user.aheadRealRepayment}">
 											<td>${user.aheadRealRepayment.rinterest+user.aheadRealRepayment.rvoucherint}</td>
										</c:when>
										<c:otherwise>
 											<td>${user.rinterest+user.rvoucherint}</td>
 										</c:otherwise>
								</c:choose>
								<td>${user.interestexpense}</td>
								<td>
									<c:choose>
										<c:when test="${user.repaystatus == 1}">待还款</c:when>
										<c:when test="${user.repaystatus == 2}">审核中</c:when>
										<c:when test="${user.repaystatus == 3}">待处理</c:when>
										<c:when test="${user.repaystatus == 4}">处理中</c:when>
										<c:when test="${user.repaystatus == 5}">已还款</c:when>
										<c:when test="${user.repaystatus == 6}">还款失败</c:when>
										<c:when test="${user.repaystatus == 7}">审核失败</c:when>
  									</c:choose>
								</td>
								<td>${user.issubmit == 1 ? '已提交':'未提交'}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
 			</div>
 			<!-- 批次还款明细记录end -->
		
 		
			<!-- 本批次还款详情 start -->
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="well text-center" style="font-size: 14px; background: #d6e9c6; line-height: 0px;">本批次还款详情</div>
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">借款人 </label>
	   				     <span>${repayMentFrz.useroutaccountid.loginname}-${repayMentFrz.useroutaccountid.realname}</span>
				 	</div>
  				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">标的编号 </label>
	   				     <span>${repayMentFrz.product}</span>
				 	</div>
  				</div>
 				 
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">还款批次号 </label>
	   				     <span>${repayMentFrz.batchno}</span>
				 	</div>
 				</div> 
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">冻结申请流水号</label>
	   				     <span>${repayMentFrz.serino}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">解冻申请流水号 </label>
	   				     <span>${repayMentFrz.thawserino}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">电子账号 </label>
	   				     <span>${repayMentFrz.cardnbr}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">还款状态 </label>
	   				     <span>
	   				     		<c:choose>
								<c:when test="${repayMentFrz.status == 0}"><span>初始</span></c:when>
								<c:when test="${repayMentFrz.status == 1}"><span>冻结成功</span></c:when>
								<c:when test="${repayMentFrz.status == 2}"><span>冻结失败</span></c:when>
								<c:when test="${repayMentFrz.status == 3}"><span>审核中</span></c:when>
								<c:when test="${repayMentFrz.status == 4}"><span>待处理</span></c:when>
								<c:when test="${repayMentFrz.status == 5}"><span>处理中 </span></c:when>
								<c:when test="${repayMentFrz.status == 6}"><span style="color:blue;">处理成功</span></c:when>
								<c:when test="${repayMentFrz.status == 7}"><span >审核失败</span></c:when>
								<c:when test="${repayMentFrz.status == 8}"><span >解冻成功</span></c:when>
  							</c:choose>
	   				     </span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">冻结银行返回码 </label>
	   				     <span>${repayMentFrz.retcode}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">解冻银行返回码 </label>
	   				     <span>${repayMentFrz.thawretcode}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">冻结金额 </label>
	   				     <span>${repayMentFrz.amount}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">本批次总笔数 </label>
	   				     <span>${repayMentFrz.count}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">成功笔数 </label>
	   				     <span>${repayMentFrz.successcount}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">失败笔数 </label>
	   				     <span>${repayMentFrz.failcount}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否提交 </label>
	   				     <span>${repayMentFrz.issubmit == 1 ? '已提交' : '未提交'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否系统勾兑 </label>
	   				     <span>${repayMentFrz.isblending == 1? '已勾兑' : '未勾兑'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否人工勾兑 </label>
	   				     <span>${repayMentFrz.ismanblending == 1? '已勾兑' : '未勾兑'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否债转 </label>
	   				     <span>${repayMentFrz.ismanblending == 1? '是' : '否'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否提前 </label>
	   				     <span>${repayMentFrz.isahead == 1? '是' : '否'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否代偿 </label>
	   				     <span>${repayMentFrz.isproxyrepay == 1? '是' : '否'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">是否逾期 </label>
	   				     <span>${repayMentFrz.isoverdue == 1? '是' : '否'}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">冻结提交时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.frztime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">冻结返回时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.returntime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">解冻提交时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.thawtime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">解冻返回时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.thawreturntime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">系统勾兑时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.sysbtime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">人工勾兑时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.manbtime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">系统勾兑接收数据时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.sysrectime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">人工勾兑接收数据时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.receivetime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">借款人提交还款时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.submittime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">还款实际到账日期 </label>
	   				     <span>${gj:formatDate(repayMentFrz.rprealtime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">审核时间 </label>
	   				     <span>${gj:formatDate(repayMentFrz.audittime,'yyyy-MM-dd HH:mm:ss')}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">审核人 </label>
	   				     <span>${repayMentFrz.auditman}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">请求数据包 </label>
	   				     <span>${repayMentFrz.reqquerydata}</span>
				 	</div>
 				</div>
 				
 				<div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">接收数据包 </label>
	   				     <span>${repayMentFrz.recresultdata}</span>
				 	</div>
 				</div>	
  				 
  				 <div class="row">
					<div class="col-md-8">
				   		 <label class="col-sm-3">备注 </label>
	   				     <span>${repayMentFrz.remark}</span>
				 	</div>
 				</div>
  				 
			</div>
			<!-- 本批次还款详情 end -->
 
		</div>
	</div>
</body>
</html>