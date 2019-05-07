<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>编辑</title>

</head>
<body style="font-family: 微软雅黑">
	<form>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
				<font color="red"><b>充值账户</b></font>
			</div>
			<hr>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				充值用户名：<label class="detailslb" id="applyman">${userRecharge.applyman}</label>
			</div>
			<hr>
		</div>
		<div class="row" style="line-height: 0px; display: none">
			<div class="col-md-8 col-md-offset-1">
				充值订单号：<input class="detailslb" id="rechargeno" name="rechargeno"
					value="${userRecharge.rechargeno}">
			</div>
			<hr>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
				<font color="red"><b>充值信息</b></font>
			</div>
			<hr>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				充&nbsp;值&nbsp;金&nbsp;额 ：<label class="detailslb"><input
					name="amount" value="${df1.format(userRecharge.amount)}"
					id="amountlb" />元</label>
			</div>
		</div>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				充值手续费 ：<label class="detailslb"><input name="recharfee"
					value="${df1.format(userRecharge.recharfee)}" id="feelb" />元</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-8 col-md-offset-1">
				充&nbsp;值&nbsp;方&nbsp;式:&nbsp;&nbsp;<label class="detailslb">
					<select name="rechargetype" id="rechargetypelb">
						<option value="0"
							<c:if test="${userRecharge.rechargetype =='1'}">selected='selected'</c:if>>网银</option>
						<option value="4"
							<c:if test="${userRecharge.rechargetype =='3'}">selected='selected'</c:if>>企业网银</option>
						<option value="2"
							<c:if test="${userRecharge.rechargetype =='2'}">selected='selected'</c:if>>快捷支付</option>
						<option value="3"
							<c:if test="${userRecharge.rechargetype =='4'}">selected='selected'</c:if>>汇款充值</option>
				</select>
				</label>
			</div>
			<div class="col-md-8 col-md-offset-1">
				充&nbsp;值&nbsp;银&nbsp;行:&nbsp; <label class="detailslb"> <select
					name="bankname" id="banknamelb">
						<option value="ICBC"
							<c:if test="${userRecharge.bankname =='ICBC'}">selected="selected"</c:if>>中国工商银行</option>
						<option value="ABC"
							<c:if test="${userRecharge.bankname =='ABC'}">selected="selected"</c:if>>中国农业银行</option>
						<option value="CMB"
							<c:if test="${userRecharge.bankname =='CMB'}">selected="selected"</c:if>>招商银行</option>
						<option value="CCB"
							<c:if test="${userRecharge.bankname =='CCB'}">selected="selected"</c:if>>中国建设银行</option>
						<option value="BCCB"
							<c:if test="${userRecharge.bankname =='BCCB'}">selected="selected"</c:if>>北京银行</option>
						<option value="BJRCB"
							<c:if test="${userRecharge.bankname =='BJRCB'}">selected="selected"</c:if>>北京农村商业银行</option>
						<option value="BOC"
							<c:if test="${userRecharge.bankname =='BOC'}">selected="selected"</c:if>>中国银行</option>
						<option value="BOCOM"
							<c:if test="${userRecharge.bankname =='BOCOM'}">selected="selected"</c:if>>交通银行</option>
						<option value="CMBC"
							<c:if test="${userRecharge.bankname =='CMBC'}">selected="selected"</c:if>>中国民生银行</option>
						<option value="BOS"
							<c:if test="${userRecharge.bankname =='BOS'}">selected="selected"</c:if>>上海银行</option>
						<option value="CBHB"
							<c:if test="${userRecharge.bankname =='CBHB'}">selected="selected"</c:if>>渤海银行</option>
						<option value="CEB"
							<c:if test="${userRecharge.bankname =='CEB'}">selected="selected"</c:if>>中国光大银行</option>
						<option value="CIB"
							<c:if test="${userRecharge.bankname =='CIB'}">selected="selected"</c:if>>兴业银行</option>
						<option value="CITIC"
							<c:if test="${userRecharge.bankname =='CITIC'}">selected="selected"</c:if>>中信银行</option>
						<option value="CZB"
							<c:if test="${userRecharge.bankname =='CZB'}">selected="selected"</c:if>>浙商银行</option>
						<option value="GDB"
							<c:if test="${userRecharge.bankname =='GDB'}">selected="selected"</c:if>>广发银行</option>
						<option value="HKBEA"
							<c:if test="${userRecharge.bankname =='HKBEA'}">selected="selected"</c:if>>东亚银行</option>
						<option value="HXB"
							<c:if test="${userRecharge.bankname =='HXB'}">selected="selected"</c:if>>华夏银行</option>
						<option value="HZCB"
							<c:if test="${userRecharge.bankname =='HZCB'}">selected="selected"</c:if>>杭州银行</option>
						<option value="NJCB"
							<c:if test="${userRecharge.bankname =='NJCB'}">selected="selected"</c:if>>南京银行</option>
						<option value="PINGAN"
							<c:if test="${userRecharge.bankname =='PINGAN'}">selected="selected"</c:if>>平安银行</option>
						<option value="PSBC"
							<c:if test="${userRecharge.bankname =='PSBC'}">selected="selected"</c:if>>中国邮政储蓄银行</option>
						<option value="SDB"
							<c:if test="${userRecharge.bankname =='SDB'}">selected="selected"</c:if>>深圳发展银行</option>
						<option value="SPDB"
							<c:if test="${userRecharge.bankname =='SPDB'}">selected="selected"</c:if>>浦东发展银行</option>
						<option value="SRCB"
							<c:if test="${userRecharge.bankname =='SRCB'}">selected="selected"</c:if>>上海农村商业银行</option>
				</select>
				</label>
			</div>
		</div>
		<hr>

		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 20px;">
				<font color="red"><b>状态</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-1 ">
				是否异常： <label class="detailslb"> <select name="isexceptions"
					id="isexceptionsEdit">
						<option value="1"
							<c:if test="${userRecharge.isexceptions =='1'}">selected="selected"</c:if>>异常</option>
						<option value="0"
							<c:if test="${userRecharge.isexceptions =='0'}">selected="selected"</c:if>>正常</option>
				</select>
				</label>
			</div>
			<hr />
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				充值状态：<label class="detailslb"> <select name="status"
					id="statusEdit">
						<option value="1"
							<c:if test="${userRecharge.status =='1'}">selected="selected"</c:if>>成功</option>
						<option value="2"
							<c:if test="${userRecharge.status =='2'}">selected="selected"</c:if>>失败</option>
						<option value="3"
							<c:if test="${userRecharge.status =='3'}">selected="selected"</c:if>>取消</option>
						<option value="4"
							<c:if test="${userRecharge.status =='4'}">selected="selected"</c:if>>待充值</option>
				</select>
				</label>
			</div>
		</div>
	</form>
</body>
</html>