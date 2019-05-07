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
<title>标的活动奖励规则详情列表</title>
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
				<!-- 标的活动奖励规则详情 start -->
				<div class="col-md-12" style="margin-top: 15px;">
					<div class="alert alert-success text-center" role="alert"
						style="font-size: 14px;">标的活动奖励规则详情</div>
					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">累投金额值_低位</label> <span
								class="green">${activityAwardRule.tminmoney}</span>&nbsp;&nbsp;元
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">累投金额值_高位</label> <span
								class="green">${activityAwardRule.tmaxmoney}</span>&nbsp;&nbsp;元
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖品的ID</label> <span>${activityAwardRule.awardid}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖品的名称</label> <span>${activityAwardRule.awardname}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖品类型</label> <span>${activityAwardRule.awardclass == 1 ? '累投' : '单标'}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖项名称</label> <span>${activityAwardRule.awardprize}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖品份数</label> <span
								class="green">${activityAwardRule.awardcopies}</span>&nbsp;&nbsp;份
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖励方式</label> <span>${activityAwardRule.awardtype == 1 ? '定额' : '投资金额的百分比'}</span>&nbsp;&nbsp;
						</div>
					</div>

					<c:if test="${activityAwardRule.awardtype == 1 }">
						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">奖励定额</label> <span
									class="green">${activityAwardRule.quota}</span>&nbsp;&nbsp;元
							</div>
						</div>
					</c:if>

					<c:if test="${activityAwardRule.awardtype == 2 }">
						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">奖励百分比数</label> <span
									class="green">${activityAwardRule.awardratio}</span>&nbsp;&nbsp;%
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">奖励最低值</label> <span
									class="green">${activityAwardRule.awardmin}</span>&nbsp;&nbsp;元
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-3 text-right">奖励最高值</label> <span
									class="green">${activityAwardRule.awardmax}</span>&nbsp;&nbsp;元
							</div>
						</div>
					</c:if>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">发放时机</label>
							<c:choose>
								<c:when test="${activityAwardRule.distributemode == 1}">
									<span>成功</span>&nbsp;&nbsp;
 				    	 	</c:when>
								<c:when test="${activityAwardRule.distributemode == 2}">
									<span>满标</span>&nbsp;&nbsp;
 				    	 	</c:when>
								<c:otherwise>
									<span>活动结束</span>&nbsp;&nbsp;
 				    	 	</c:otherwise>
							</c:choose>
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">发放方式</label> <span>${activityAwardRule.distributetype == 1 ? '系统' : '人工'}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">首投时间限制</label> <span>${gj:formatDate(activityAwardRule.firsttendertime,'yyyy-MM-dd')}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">首投金额限制</label> <span
								class="green">${activityAwardRule.firsttendermoney}</span>&nbsp;&nbsp;元
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">注册时间限制（多少天内）</label> <span>${activityAwardRule.regeditdayrest}</span>&nbsp;&nbsp;天
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖励IP次数限制</label> <span>${activityAwardRule.iprestrict}</span>&nbsp;&nbsp;次
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">奖励cookie次数限制</label> <span>${activityAwardRule.cookierestrict}</span>&nbsp;&nbsp;次
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">是否审核</label> <span>${activityAwardRule.isaudit == 1 ? '需要审核' : '不需要审核'}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">审核人</label> <span>${activityAwardRule.auditman}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">审核时间</label> <span>${gj:formatDate(activityAwardRule.audittime,'yyyy-MM-dd HH:mm:ss')}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">排名类型</label> <span>${activityAwardRule.ranking}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">制表人</label> <span>${activityAwardRule.addman}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">制表时间</label> <span>${gj:formatDate(activityAwardRule.addtime,'yyyy-MM-dd HH:mm:ss')}</span>&nbsp;&nbsp;
						</div>
					</div>

					<div class="row">
						<div class="col-md-8">
							<label class="col-sm-3 text-right">备注</label> <span>${activityAwardRule.remark}</span>&nbsp;&nbsp;
						</div>
					</div>
				</div>
				<!-- 标的活动奖励规则详情 end -->
			</div>
		</div>
	</div>
</body>
</html>