<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>TenderItem</title>
<script type="text/javascript">
$(function(){
		$("#submitBtu").click(function(){
		    var self = $(this);
		    self.attr('disabled','disabled');
			var url = "${pageContext.request.contextPath }/huifu/initiativeTender/tenderCheck.action";
			var param = $('#tender').serializeArray();
			var callback = function(data){
				var json = $.parseJSON(data);
				if(json.info == "success"){
					$("#tips").text("");
					$("#tender").submit();
					$("#submitBtu").unbind("click");
				}else{
					$("#tips").text(json.info);
		            self.removeAttr('disabled');
				}
			};
			$.post(url, param, callback);
		});
			
});

</script>
<style type="text/css">
#id {
	margin: 40px;
}

hr {
	margin: 10px;
}
</style>
</head>
<body style="font-family: '微软雅黑';">
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 " style="margin-top: 10px; margin-bottom: 10px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font
				size="4">标基本设置</font></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">借款申请ID</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.loanappid }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标号</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.tno }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标的名称</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.tname }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标的金额</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.tamount }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">已完成投标金额</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.finishtamount }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标的类型</font>&nbsp;&nbsp;：
			<c:if test="${tenderItem.tpro==1}">
				<label id="addman-lb">信用标</label>
			</c:if>
			<c:if test="${tenderItem.tpro==2}">
				<label id="addman-lb">抵押标</label>
			</c:if>
			<c:if test="${tenderItem.tpro==3}">
				<label id="addman-lb">其它标</label>
			</c:if>

		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标的开始日期</font>&nbsp;&nbsp;：<label id="addman-lb"><fmt:formatDate
					value='${tenderItem.tbegintime }' type='both' /></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标的结束时间</font>&nbsp;&nbsp;：<label id="addman-lb"><fmt:formatDate
					value='${tenderItem.tendtime }' type='both' /></label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">借款周期</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.loantime }${tenderItem.dayormonth }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">投资收益</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.tinterest }%</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1">
			<font size="3">标的说明描述</font>&nbsp;&nbsp;：<label id="addman-lb">${tenderItem.tdesc }</label>
		</div>
	</div>
	<hr>
	<form id="tender"
		action="${pageContext.request.contextPath }/huifu/initiativeTender/tender.action"
		method="post" target="_blank">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1">
				<font size="3">请输入投资金额:<input type="text" id="amount"
					name="amount" /></font>
			</div>
		</div>
		<c:if test="${tenderItem.isappointtender eq 1}">
			<div class="row" style="line-height: 0px; margin-top: 10px;">
				<div class="col-md-4 col-md-offset-1">
					<font size="3">请输入约标码:<input type="text" id="tpass"
						name="tpass" /></font>
				</div>
			</div>
		</c:if>
		<div class="row" style="line-height: 0px; margin-top: 10px;">
			<div class="col-md-4 col-md-offset-1">
				<label id="tips" style="color: red;"></label>
			</div>
		</div>
		<div hidden="hidden">
			<input type="hidden" id="id" name="id" value="${tenderItem.id}" /> <input
				type="text" name="name"
				onkeydown="if(event.keyCode==13) return false;" />
		</div>
	</form>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			<a class="btn btn-primary" id="submitBtu" href="javascript:void(0)"
				type="button" style="margin-left: 200px">投标</a>
		</div>
	</div>
</body>
</html>
