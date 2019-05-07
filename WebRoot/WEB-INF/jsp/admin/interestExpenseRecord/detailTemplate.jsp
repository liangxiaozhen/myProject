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
<title>标的利息管理费记录详情列表</title>
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
						<label class="col-sm-2 text-right">利息管理费流水号</label> <span>${interestExpenseRecord.ieorderno}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款批次号</label> <span>${interestExpenseRecord.batchno}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">还款流水号</label> <span>${interestExpenseRecord.rorderno}</span>
					</div>
				</div>
				
  				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">标名称</label> <span>${interestExpenseRecord.tenderItem.tname}</span>
					</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">标号</label> <span>${interestExpenseRecord.tenderItem.tno}</span>
					</div>
				</div>
  
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">利息管理费</label> <span>${interestExpenseRecord.intexpfee}</span>
					</div>
				</div>



				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否处理</label> <span>${interestExpenseRecord.isdeal == 1? "是":"否"}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">投资人</label> <span>${interestExpenseRecord.investoridaccount.loginname}-${interestExpenseRecord.investoridaccount.realname}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">是否审核</label> <span>${interestExpenseRecord.isaudit == 1? "是":"否"}</span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">收取类型</label> <span>${interestExpenseRecord.ietype == 1? "百分比":"最高"}</span>
					</div>
				</div>
 
				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">审核时间</label> <span>${gj:formatDate(interestExpenseRecord.audittime,"yyyy-MM-dd HH:mm:ss")}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">审核人</label> <span>${interestExpenseRecord.auditman}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">创建时间</label> <span>${gj:formatDate(interestExpenseRecord.madetime,"yyyy-MM-dd HH:mm:ss")}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">清算时间</label> <span>${gj:formatDate(interestExpenseRecord.payoutdate,"yyyy-MM-dd HH:mm:ss")}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">利息管理费收取计划状态 </label> <span>${interestExpenseRecord.planstatus == 1?"有效":"无效"}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">管理费属性 </label> <span>${interestExpenseRecord.ieproperty == 0?"正常投标":"债权转让"}</span>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8">
						<label class="col-sm-2 text-right">备注 </label> <span>${interestExpenseRecord.remark}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>