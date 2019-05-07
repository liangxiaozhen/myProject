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
						<label class="col-sm-2 text-right">活动编号</label> <span>${repayMent.actno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动名称</label> <span>${repayMent.actname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动生效日期</label> <span>${repayMent.actbtimestr}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动截止日期</label> <span>${repayMent.actetimestr}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动状态</label> <span
							class="${repayMent.status==1?'green':'red'}">${repayMent.status==1?'启用':'未启用'}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">活动规则</label> <span>${repayMent.actrule ==1?'升降级只发一次':'升降级重发，取消之前奖品'}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">执行时间点</label> <span>${repayMent.executetime}</span>
					</div>
				</div>



				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">排除名单表编号</label> <span>${repayMent.removenameno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">获奖名单是否需要审核才生成 </label> <span
							class="${repayMent.isauditalist==1?'green':'red'}">${repayMent.isauditalist==1?'已审核':'未审核'}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">制表时间 </label> <span>${repayMent.addtimestr}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">制表人 </label> <span>${repayMent.addman}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">备注 </label> <span>${repayMent.remark}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>