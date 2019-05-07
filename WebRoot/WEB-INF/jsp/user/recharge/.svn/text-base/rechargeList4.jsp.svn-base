<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/userRecharge/css/rechargebuju4.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/moneyproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/userRecharge/recharge.js"></script>
</head>
<body style="font-family: 微软雅黑">
	<div id="allDiv">
		<div id="numberOne">
			<div id="chongzhi">
				<img />充值
			</div>
			<div id="chongzhirecord">
				<a id="czjl">充值记录</a>
			</div>
		</div>


		<div id="middle">
			<form id="userRechargeFrom">
				<input type="hidden" id="pageNum" name="pageNum" /> <input
					type="hidden" id="pageSize" name="pageSize" />
		
					<div id="grwangy_nr">
						<ul class="bank_list">
							<li class="userrecharge">
								<input checked="checked" name="cz"  type="radio" style="margin-top: -5px" value="0">
								<span style="font-size: 20px;margin-top: 15px;margin-left: 5px">网银充值</span>
							</li>
							<li class="userrecharge">
								<input  name="cz"  type="radio" style="margin-top: -5px" value="2">
								<span style="font-size: 20px;margin-top: 15px;margin-left: 5px">快捷充值</span>
							</li>
							<li class="userrecharge">
							 	<input  name="cz"  type="radio" style="margin-top: -5px" value="4">
							 	<span style="font-size: 20px;margin-top: 15px;margin-left: 5px">企业网银充值</span>
							 </li>
							<li class="userrecharge">
								<input  name="cz"  type="radio" style="margin-top: -5px" value="3">
								<span style="font-size: 20px;margin-top: 15px;margin-left: 5px">汇款充值</span>
							</li>
						</ul>
					</div>


				<div id="kjnr_div3">
					<div id="kjnr_div3_1">
					   <div>
						总资产:<span style="font-size: 25px; color: red;"> 
							  <c:if test="${!empty uc.balance}">${df.format(uc.balance)}</c:if> 
							  <c:if test="${empty uc.balance}">0.00</c:if>
					   			</span>元
					   </div>
					   <div>
							&nbsp;&nbsp;&nbsp;&nbsp; 可用余额:
							<span style="font-size: 25px; color: red">
								<c:if test="${!empty uc.avlbalance}">${df.format(uc.avlbalance)}</c:if>
								<c:if test="${empty uc.avlbalance}">0.00</c:if>
							</span>元
						</div>
						<div>
							&nbsp;&nbsp;&nbsp;&nbsp; 冻结余额:
							<span style="font-size: 25px; color: red"> 
								<c:if test="${!empty uc.freezebalance}">${df.format(uc.freezebalance)}</c:if>
								<c:if test="${empty uc.freezebalance}">0.00</c:if>
							</span>元
						</div>
					</div>
					<div id="kjnr_div3_2">
						充值优惠券: <select id="select"><option>暂无优惠券可用</option></select>
						<br/>
						充值金额: 
						<input name="TransAmt" id="transAmt" onblur="checkBlur(this)" onkeyup="checkUp(event,this)" maxlength="9" />元 
						<span id="txt_Amount_span" style="color: red; margin-left: 10px; font-size: 15px"></span>
						<br/>	
						<input  type="hidden" id="accountType" value="${accountType}"/>
						<button type="button" onclick="mmmdrawmoney()">确认充值</button>
					</div>
					
				<!-- 	<div id="kjnr_div3_3">
						充值金额: <input name="TransAmt" id="transAmt"
							onblur="checkBlur(this)" onkeyup="checkUp(event,this)"
							maxlength="9" />元 <span id="txt_Amount_span"
							style="color: red; margin-left: 10px; font-size: 15px"></span>
					</div> -->
					
					<!-- <div id="kjnr_div3_4">
						<button type="button" onclick="drawmoney()">确认充值</button>
					</div> -->
				</div>
			</form>
		</div>
		<div>
			个人用户不能使用企业网页充值,企业用户不能使用快捷支付充值!
		
		
		</div>

		<div id="czjl_nr">
			<%@include file="/WEB-INF/jsp/user/recharge/rechargeRecord.jsp"%>
		</div>
	</div>
	<!-- 充值提示框 -->
	<div class="modal fade" id="rechargeModal" tabindex="-1" role="dialog"
		aria-labelledby="rechargeModalable" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="rechargeModalable">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示
					</h3>
				</div>
				<div id="withdraw-modal-body" class="modal-body" align="center">
					<font size="3">请在新打开的汇付天下页面进行充值,充值完成前不要关闭该窗口</font>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="rechargeFail()">选择其他方式</button>
					<button type="button" class="btn btn-primary"
						onclick="rechargeSuccess()">已完成充值</button>
				</div>
			</div>
		</div>
	</div>


</body>

</html>