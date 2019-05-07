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
<title>提抵卷活动奖励规则设置详情列表</title>
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
		<div class="col-md-12">
			<!-- 提低卷活动 详情 开始 -->
			<div class="col-md-12" style="margin-top: 15px;">
				<h2 class="title">
					<span class="green">提低卷活动规则详情</span>
				</h2>
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">活动编号</label> <span>${coupon.actno}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">活动名称</label> <span>${coupon.actname}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">活动生效日期</label> <span>${gj:formatDate(coupon.actbtime,'yyyy-MM-dd HH:mm:dd')}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">活动截止日期</label> <span>${gj:formatDate(coupon.actetime,'yyyy-MM-dd HH:mm:dd')}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">活动状态</label> <span
									class="${coupon.status==1?'green':'red'}">${coupon.status==1?'启用':'未启用'}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">活动规则</label> <span>${coupon.actrule ==1?'升降级只发一次':'升降级重发，取消之前奖品'}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">执行时间点</label> <span>${coupon.executetime}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">会员等级</label>
								<gj:getUserGradeUsername opid="${coupon.ugrade}" var="userGrade">
									<span>${userGrade.ugradename}</span>
								</gj:getUserGradeUsername>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">排除名单表编号</label> <span
									class="col-sm-4">${coupon.removenameno}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">获奖名单是否需要审核才生成 </label> <span
									class="${coupon.isauditalist==1?'green':'red'}">${coupon.isauditalist==1?'需要审核':'不需要审核'}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">制表时间 </label> <span>${gj:formatDate(coupon.addtime,'yyyy-MM-dd HH:mm:dd')}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">制表人 </label> <span>${coupon.addman}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">备注 </label> <span
									class="col-sm-4">${coupon.remark}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 提低卷活动 详情 结束 -->

			<!-- 提低卷活动奖励 详情 开始 -->
			<div class="col-md-12" style="margin-top: 15px;">
				<h2 class="title">
					<span class="green">提低卷活动奖励详情信息</span>
				</h2>
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">ID</label> <span>${wCCAwardRule.id}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">注册活动ID</label> <span>${wCCAwardRule.actid}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">会员等级</label>
								<gj:getUserGradeUsername opid="${wCCAwardRule.ugrade}"
									var="user">
									<span>${user.ugradename}</span>
								</gj:getUserGradeUsername>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">注册后时间限制</label> <span
									class="red">${wCCAwardRule.finishtime}&nbsp;&nbsp;</span>小时
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">奖品的ID</label> <span>${wCCAwardRule.awardid}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">奖品的名称</label> <span>${wCCAwardRule.awardname}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">奖品份数</label> <span
									class="red">${wCCAwardRule.awardcopies}&nbsp;&nbsp;</span>份
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">奖励方式</label> <span>${wCCAwardRule.awardtype==1?'定额奖励':'投资金额的百分比奖励'}</span>
							</div>
						</div>
						<!-- 定额金额奖励显示 开始 -->
						<c:if test="${wCCAwardRule.awardtype==1}">
							<div class="row">
								<div class="col-md-8">
									<label class="col-sm-4 text-right"><span class="green">奖励金额</span></label>
									<span class="red">${wCCAwardRule.quota}</span>元
								</div>
							</div>
						</c:if>
						<!-- 定额金额奖励显示 结束 -->

						<!-- 投资金额的百分比显示 开始 -->
						<c:if test="${wCCAwardRule.awardtype !=1}">
							<div class="row">
								<div class="col-md-8">
									<label class="col-sm-4 text-right"><span class="green">奖励百分比数</span></label>
									<span class="red">${wCCAwardRule.awardratio}&nbsp;&nbsp;</span>%
								</div>
							</div>
							<div class="row">
								<div class="col-md-8">
									<label class="col-sm-4 text-right"><span class="green">奖励最低值</span></label>
									<span class="red">${wCCAwardRule.awardratio}&nbsp;&nbsp;</span>元
								</div>
							</div>
							<div class="row">
								<div class="col-md-8">
									<label class="col-sm-4 text-right"><span class="green">奖励最高值</span></label>
									<span class="red">${wCCAwardRule.awardratio}&nbsp;&nbsp;</span>元
								</div>
							</div>
						</c:if>
						<!-- 投资金额的百分比显示 结束 -->

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">发放方式 </label> <span>${wCCAwardRule.distributetype==1?'系统发放':'人工发放'}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">制表时间 </label> <span>${gj:formatDate(wCCAwardRule.addtime,'yyyy-MM-dd HH:mm:ss')}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">制表人 </label> <span>${wCCAwardRule.addman}</span>
							</div>
						</div>

						<div class="row">
							<div class="col-md-8">
								<label class="col-sm-4 text-right">备注 </label> <span
									class="col-sm-4">${wCCAwardRule.remark}</span>
							</div>
						</div>
					</div>
				</div>
				<!-- 提低卷活动奖励 详情 结束-->
			</div>
		</div>
	</div>
	<!-- 	<div class="row"> -->
	<!-- 		<div class="btn-group"> -->
	<!-- 	   		<button class="btn btn-default" onclick="wCCAwardRule.low_callback(this)">返回</button> -->
	<!-- 	   	</div> -->
	<!-- 	</div> -->
</body>
</html>