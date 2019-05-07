<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
<style type="text/css">
.clearfix:after {
	content: ' ';
	display: block;
	clear: both;
	height: 0;
	line-height: 0
}

.clearfix {
	content: ' ';
	display: block;
	clear: both;
	zoom: 1
}

.pad_t20 {
	padding-top: 20px;
	padding-left: 45px;
}

.fl {
	float: left
}

.fb {
	font-weight: 700
}

.fs_16 {
	font-size: 16px
}

.pad_5 {
	padding: 5px
}

.fc_6 {
	color: #666
}

.fs_14 {
	font-size: 14px
}

.fc_f60, .fc_orange {
	color: #f60
}

.mar_l5, .ml_5 {
	margin-left: 5px
}

.fc_9 {
	color: #999
}

.table {
	width: 100%
}

.table th {
	background: #fafafa;
	padding: 15px 0
}

.table td {
	text-align: center;
	padding: 15px 0;
	line-height: 26px;
	color: #666
}

.table tr:nth-child(odd) td {
	background: #fafafa
}

.table .table_time {
	width: 25%
}

.table td.table_timelog, .table th.table_timelog {
	width: 150px
}

.table td.td_time {
	width: 95px
}

.table td.td_morelink {
	width: 80px
}

.table td.td_morelink a {
	display: block
}

.table td .explain_td {
	display: inline-block;
	width: 150px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis
}

.bor_t {
	border-top: solid 1px #eee
}

.mar_t20, .mt_20 {
	margin-top: 20px
}

.fc_f60, .fc_orange {
	color: #f60
}

.Numfont {
	font-family: arial
}
</style>
</head>
<body>
	<!--header-->
	<%@include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧 -->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<div class="fl pad_t20 " style="width: 100%;">
				<dl class="fl " style="width: 100%;">
					<dt class="fl" style="width: 75px;"></dt>
					<dd class="fl" style="width: 800px;">
						<h2 class="fb fs_16">亲爱的用户，您的提现申请已提交</h2>
						<p class="pad_5 fc_6 fs_14">等待放款处理，资金会在1-3个工作日到账，感谢您对你我贷平台关注与支持，谢谢！</p>
						<p class="pad_5 fc_6 fs_14">
							若有问请咨询客服<span class="fc_f60 fs_14 mar_l5">400-791-0888</span>
						</p>
						<table cellspacing="0" cellpadding="0"
							class="table fc_9 bor_t mar_t20">
							<tbody>
								<tr>
									<th>操作时间</th>
									<th>银行名称</th>
									<th>银行卡号</th>
									<th>提现金额</th>
									<th>到账时间</th>
								</tr>
								<tr>
									<td><fmt:formatDate value="${nowTime }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${bankName }</td>
									<td>${bankCode }</td>
									<td><span class="fc_f60 Numfont">${amount }元</span></td>
									<td>1-3个工作日</td>
								</tr>
							</tbody>
						</table>
					</dd>
				</dl>
				<div class="cleafix"></div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%>
</body>
</html>