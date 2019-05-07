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
<title>提抵卷活动规则详情列表</title>
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
						<label class="col-sm-2 text-right">活动编号</label> <span>${coupon.actno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动名称</label> <span>${coupon.actname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动生效日期</label> <span>${coupon.actbtimestr}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动截止日期</label> <span>${coupon.actetimestr}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动状态</label> <span
							class="${coupon.status==1?'green':'red'}">${coupon.status==1?'启用':'未启用'}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动规则</label> <span>${coupon.actrule ==1?'升降级只发一次':'升降级重发，取消之前奖品'}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">执行时间点</label> <span>${coupon.executetime}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">会员等级</label>
						<gj:getUserGradeUsername opid="${coupon.ugrade}" var="userGrade">
							<span>${userGrade.ugradename}</span>
						</gj:getUserGradeUsername>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">排除名单表编号</label> <span>${coupon.removenameno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">获奖名单是否需要审核才生成 </label> <span
							class="${coupon.isauditalist==1?'green':'red'}">${coupon.isauditalist==1?'需要审核':'不需要审核'}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">制表时间 </label> <span>${coupon.addtimestr}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">制表人 </label> <span>${coupon.addman}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">备注 </label> <span>${coupon.remark}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>