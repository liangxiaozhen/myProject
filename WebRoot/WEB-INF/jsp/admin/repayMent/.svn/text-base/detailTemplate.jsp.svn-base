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
<title>还款记录详情列表</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款流水号</label> <span>${repayMent.rorderno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">投标/债权转让订单号</label> <span>${repayMent.utorderno}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款批次号</label> <span>${repayMent.rbatchno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">借款用户</label> <span>${repayMent.useroutaccountid.loginname}-${repayMent.useroutaccountid.realname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">投资用户</label> <span>${repayMent.userinaccountid.loginname}-${repayMent.userinaccountid.realname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">代还款人</label> <span>${repayMent.userproxyaccountid.loginname}-${repayMent.userproxyaccountid.realname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">标号</label> <span>${repayMent.tenderitemtenderid.tno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">标的名称</label> <span>${repayMent.tenderitemtenderid.tname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款期数（第几期）</label> <span>${repayMent.periods}</span>
					</div>
				</div>
 
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款状态</label>
						<c:choose>
							<c:when test="${repayMent.repaystatus == 1}">待还款</c:when>
							<c:when test="${repayMent.repaystatus == 2}">审核中</c:when>
							<c:when test="${repayMent.repaystatus == 3}">待处理</c:when>
							<c:when test="${repayMent.repaystatus == 4}">处理中</c:when>
							<c:when test="${repayMent.repaystatus == 5}">已还款</c:when>
							<c:when test="${repayMent.repaystatus == 6}">还款失败</c:when>
	  						<c:otherwise>未知</c:otherwise>
						</c:choose>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款模式</label>
						<c:choose>
							<c:when test="${repayMent.rmode == 0}">初始</c:when>
							<c:when test="${repayMent.rmode == 1}">人工</c:when>
							<c:when test="${repayMent.rmode == 2}">系统</c:when>
							<c:when test="${repayMent.rmode == 3}">线下</c:when>
						</c:choose>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">总金额</label> <span>${repayMent.rptotalamount}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">总本息</label> <span>${repayMent.rprincipalint}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">总利息</label> <span>${repayMent.rptotalint}</span>&nbsp;元
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">利息管理费</label> <span>${repayMent.interestexpense}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">本金 </label> <span>${repayMent.ramount}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">类现金 </label> <span>${repayMent.rvoucher}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">假现金 </label> <span>${repayMent.rlvoucher}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">本金利息 </label> <span>${repayMent.rinterest}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">类现金利息 </label> <span>${repayMent.rvoucherint}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">假现金的利息 </label> <span>${repayMent.rlvoucherint}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">加息劵利息 </label> <span>${repayMent.rintfee}</span>&nbsp;元
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">已转让本金 </label> <span>${repayMent.transferprincipal}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余本金 </label> <span>${repayMent.restprincipal == null ? 0.00 : repayMent.restprincipal}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余类现金 </label> <span>${repayMent.restvoucher}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余滞纳金 </label> <span>${repayMent.restocamount}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余假现金 </label> <span>${repayMent.restlvoucher}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余类现金滞纳金</label> <span>${repayMent.vrestocamount}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余本金利息</label> <span>${repayMent.restamountintprofit}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余加息卷收益</label> <span>${repayMent.restintprofit}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余类现金产生的利息</label> <span>${repayMent.restvoucherintprofit}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">剩余假现金产生的利息</label> <span>${repayMent.restlvoucherintprofit}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">失效类现金</label> <span>${repayMent.disablevoucher}</span>&nbsp;元
					</div>
				</div>


				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">失效假现金 </label> <span>${repayMent.disablelvoucher}</span>&nbsp;元
					</div>
				</div>


				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">失效滞纳金 </label> <span>${repayMent.disableocamount}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">失效类现金利息 </label> <span>${repayMent.disablevoucherint}</span>&nbsp;元
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">失效类现金滞纳金</label> <span>${repayMent.disablevocamount}</span>&nbsp;元
					</div>
				</div>


				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">失效加息卷收益</label> <span>${repayMent.disableintprofit}</span>&nbsp;元
					</div>
				</div>
  
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否审核</label>
						<c:choose>
							<c:when test="${repayMent.isaudit == 0}">否</c:when>
							<c:when test="${repayMent.isaudit == 1}">是</c:when>
 						</c:choose>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否系统勾兑 </label> 
						<c:choose>
							<c:when test="${repayMent.isblending == 0}">未勾兑</c:when>
							<c:when test="${repayMent.isblending == 1}">已勾兑</c:when>
							<c:otherwise>未知错误</c:otherwise>
 						</c:choose>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否人工勾兑 </label> 
						<c:choose>
							<c:when test="${repayMent.ismanblending == 0}">未勾兑</c:when>
							<c:when test="${repayMent.ismanblending == 1}">已勾兑</c:when>
							<c:otherwise>未知错误</c:otherwise>
 						</c:choose>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否债转</label> <span>${repayMent.isdarepay == 1 ? '是' : '否'}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否逾期</label> <span>${repayMent.isoverdue == 1 ? '是' : '否'}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否代偿</label> <span>${repayMent.isproxyrepay == 1 ? '是' : '否'}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否提前</label> <span>${repayMent.isahead == 1 ? '是' : '否'}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否提交银行</label> <span>${repayMent.issubmit == 1 ? '是' : '否'}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款时间 </label> <span>${gj:formatDate(repayMent.rtime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				 

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">制表时间 </label> <span>${gj:formatDate(repayMent.addtime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">提交银行时间 </label> <span>${gj:formatDate(repayMent.operatetime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">系统勾兑时间 </label> <span>${gj:formatDate(repayMent.sysbtime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">人工勾兑时间</label> <span>${gj:formatDate(repayMent.manbtime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">系统勾兑接收数据时间</label> <span>${gj:formatDate(repayMent.sysrectime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">人工勾兑接收数据时间</label> <span>${gj:formatDate(repayMent.receivetime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">审核时间 </label> <span>${gj:formatDate(repayMent.audittime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">请求数据包</label> 
						<span>${repayMent.reqquerydata}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">接受数据包</label> 
						<span>${repayMent.recresultdata}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否人工勾兑 </label> 
						<c:choose>
							<c:when test="${repayMent.ismanblending == 0}">未勾兑</c:when>
							<c:when test="${repayMent.ismanblending == 1}">已勾兑</c:when>
							<c:otherwise>未知错误</c:otherwise>
 						</c:choose>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">审核人 </label> <span>${repayMent.auditman}</span>
					</div>
				</div>
 
  				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">备注 </label> <span>${repayMent.remark}</span>
					</div>
				</div>
				<c:if test="${not empty aheadRealRepayment }">
					<div class="well text-center"
						style="font-size: 14px; background: #d6e9c6; line-height: 0px;">提前实际还款记录详情</div>
						
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">还款流水号</label> <span>${aheadRealRepayment.rorderno}</span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">还款批次号</label> <span>${aheadRealRepayment.rrealbatchno}</span>
						</div>
					</div>
  
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际真现金</label> <span>${aheadRealRepayment.rptotalamount}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际类现金</label> <span>${aheadRealRepayment.rvoucher}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际总金额（本金+类现金+实际总利息）</label>
							<span>${aheadRealRepayment.rprincipalint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际总利息 (本金利息+类现金利息)</label>
							<span>${aheadRealRepayment.rptotalint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款欠收总利息(本金欠收利息+类现金欠收利息)</label>
							<span>${aheadRealRepayment.norectotalint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际本金利息</label> <span>${aheadRealRepayment.rinterest}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款欠收本金利息</label> <span>${aheadRealRepayment.norecrinterest}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际类现金的利息</label> <span>${aheadRealRepayment.rvoucherint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款欠收类现金的利息</label> <span>${aheadRealRepayment.norecrvoucherint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际假现金的利息</label> <span>${aheadRealRepayment.rlvoucherint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款欠收假现金的利息</label> <span>${aheadRealRepayment.norecrlvoucherint}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款实际加息劵利息</label> <span>${aheadRealRepayment.rintfee}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">提前还款欠收加息劵利息</label> <span>${aheadRealRepayment.norecrintfee}</span>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">投标利息的管理费</label> <span>${aheadRealRepayment.interestexpense}</span>
						</div>
					</div>
					 
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">备注</label> <span>${aheadRealRepayment.remark}</span>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-2 text-right">制表时间</label> <span>${gj:formatDate(aheadRealRepayment.addtime,'yyyy-MM-dd HH:mm:ss')}</span>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>