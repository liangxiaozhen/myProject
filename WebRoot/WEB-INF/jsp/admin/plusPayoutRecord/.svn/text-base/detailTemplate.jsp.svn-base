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
<title>标的增益清算记录详情列表</title>
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
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">增益清算流水号</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.pporderno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">还款流水号</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.rorderno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">标的增益设置表Id</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.ppid}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">标号</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.tno}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">标名称</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.tname}</span>
					</div>
				</div>
 
				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">付款人</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.payname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">投资人</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.inname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">加息券收益</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.intprofit}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">类现金收益 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.voucherprofit}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">假现金收益 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.likevoucherprofit}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">清算方式 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span> <c:choose>
								<c:when test="${plusPayoutRecord.clearmode == 2}">首期</c:when>
								<c:when test="${plusPayoutRecord.clearmode == 3}">按期</c:when>
								<c:when test="${plusPayoutRecord.clearmode == 4}">尾期</c:when>
								<c:otherwise>结标</c:otherwise>
							</c:choose>
						</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">是否发放 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span> <c:choose>
								<c:when test="${plusPayoutRecord.isgrant == 1}">已发放</c:when>
								<c:when test="${plusPayoutRecord.isgrant == 2}">处理中</c:when>
								<c:otherwise>未发放</c:otherwise>
							</c:choose>

						</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">是否系统勾兑</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.isblending == 1 ? "已勾兑" : "未勾兑"}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">是否人工勾兑 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.ismanblending == 1 ? "已勾兑" : "未勾兑"}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">系统勾兑时间 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.sysbtime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">人工勾兑时间 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.sysbtime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">托管通道公司 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.paycompany}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">系统勾兑接收数据时间 第一次 </label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.sysrectime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">人工勾兑接收数据时间 第一次</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.receivetime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">请求数据包</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.reqquerydata}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">接收数据包</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.recresultdata}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">创建时间</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.madetime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">是否审核</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span> <c:choose>
								<c:when test="${plusPayoutRecord.isaudit == 0}">未审核</c:when>
								<c:when test="${plusPayoutRecord.isaudit == 1}">审核通过</c:when>
								<c:otherwise>审核失败</c:otherwise>
							</c:choose>
						</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">审核时间</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.audittime,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2 text-right">审核人</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.auditman}</span>
					</div>
				</div>


				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">清算时间</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${gj:formatDate(plusPayoutRecord.payoutdate,'yyyy-MM-dd HH:mm:ss')}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-4 text-right">
						<label class="col-sm-2">备注</label>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-8">
						<span>${plusPayoutRecord.remark}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>