<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的居间费设置详情</title>

</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>基本信息</b></font>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-10 col-md-offset-1">
			标的类型：<label>${mediacyfee.ttypeStr }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-10 col-md-offset-1">
			收费类型：<label><c:if test="${mediacyfee.chargetype eq 1 }">结标收取</c:if>
				<c:if test="${mediacyfee.chargetype eq 2 }">投标收取</c:if></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-10 col-md-offset-1">
			计算方式：<label><c:if test="${mediacyfee.gfitype eq 1 }">会员等级</c:if>
				<c:if test="${mediacyfee.gfitype eq 2 }">标的风险等级</c:if></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-10 col-md-offset-1">
			会员等级：<label>${mediacyfee.ugrade }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-10 col-md-offset-1">
			是否审核：<label><c:if test="${mediacyfee.isaudit eq 0 }">
											否
										</c:if> <c:if test="${mediacyfee.isaudit eq 1 }">
											是
										</c:if></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			居间费率：<label>${mediacyfee.mfrate }</label>
		</div>
		<div class="col-md-6">
			居间费最高收费：<label>${mediacyfee.maxmfamount} 元</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			设置人：<label>${mediacyfee.addman }</label>
		</div>
		<div class="col-md-6">
			设置时间：<label><fmt:formatDate value="${mediacyfee.addtime }"
					pattern="yyyy-MM-dd HH:mm:ss" /></label>
		</div>
		<hr>
	</div>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>备注信息</b></font>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-11 col-md-offset-1">
			备注&nbsp;&nbsp;： <label>${mediacyfee.remark }</label>
		</div>
	</div>
</body>
</html>