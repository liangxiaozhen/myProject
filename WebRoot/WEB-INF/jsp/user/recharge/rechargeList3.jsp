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
	href="${pageContext.request.contextPath }/js/userRecharge/css/rechargebuju3.css">
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
				<div id="numberTwo">
					<div id="wangy">
						<input type="hidden" value="1" id="zhifu" name="zhifu" />网银充值
					</div>
					<div id="kuaijie">快捷支付充值</div>
				</div>
				<div id="middleOne">
					<div id="grwangy">
						<input type="hidden" value="1" id="wy" name="wy" />个人网银
					</div>
					<div id="qywangy">企业网银</div>
				</div>
				<div id="middleTwo">
					<div id="grwangy_nr">
						<ul class="bank_list">
							<li class="bank_01"><input name="bank_type"
								class="bank_01_list" value="ICBC" type="radio" checked="checked"></li>
							<li class="bank_02"><input name="bank_type"
								class="bank_02_list" value="CMB" type="radio"></li>
							<li class="bank_03"><input name="bank_type"
								class="bank_03_list" value="CCB" type="radio"></li>
							<li class="bank_04"><input type="radio" name='bank_type'
								class="bank_04_list" value="ABC"></li>
							<li class="bank_05"><input name="bank_type"
								class="bank_05_list" value="BOC" type="radio"></li>
							<li class="bank_06"><input name="bank_type"
								class="bank_06_list" value="BOS" type="radio"></li>
							<li class="bank_07"><input name="bank_type"
								class="bank_07_list" value="SPDB" type="radio"></li>
							<li class="bank_08"><input name="bank_type"
								class="bank_08_list" value="CEB" type="radio"></li>
							<li class="bank_09"><input name="bank_type"
								class="bank_09_list" value="CMBC" type="radio"></li>
							<li class="bank_10"><input name="bank_type"
								class="bank_10_list" value="BOCOM" type="radio"></li>
							<li class="bank_11"><input name="bank_type"
								class="bank_11_list" value="CIB" type="radio"></li>
							<li class="bank_12"><input name="bank_type"
								class="bank_12_list" value="SRCB" type="radio"></li>
							<li class="bank_13"><input name="bank_type"
								class="bank_13_list" value="BCCB" type="radio"></li>
							<li class="bank_14"><input name="bank_type"
								class="bank_14_list" value="BJRCB" type="radio"></li>
							<li class="bank_15"><input name='bank_type'
								class="bank_15_list" value="CBHB" type="radio"></li>
							<li class="bank_16"><input name="bank_type"
								class="bank_16_list" value="CITIC" type="radio"></li>
							<li class="bank_17"><input name="bank_type"
								class="bank_17_list" value="CZB" type="radio"></li>
							<li class="bank_18"><input name="bank_type"
								class="bank_18_list" value="GDB" type="radio"></li>
							<li class="bank_19"><input name="bank_type"
								class="bank_19_list" value="HKBEA" type="radio"></li>
							<li class="bank_20"><input name="bank_type"
								class="bank_20_list" value="HXB" type="radio"></li>
							<li class="bank_21"><input name="bank_type"
								class="bank_21_list" value="HZCB" type="radio"></li>
							<li class="bank_22"><input name="bank_type"
								class="bank_22_list" value="NJCB" type="radio"></li>
							<li class="bank_23"><input name="bank_type"
								class="bank_23_list" value="PINGAN" type="radio"></li>
							<li class="bank_24"><input name="bank_type"
								class="bank_24_list" value="PSBC" type="radio"></li>
							<li class="bank_25"><input name="bank_type"
								class="bank_25_list" value="SDB" type="radio"></li>
							<div style="clear: both;"></div>
						</ul>
					</div>

					<div id="qywangy_nr">
						<ul class="ers_list">
							<li class="bank_001"><input checked="checked"
								name="bank_type_en" value="ICBC" type="radio"></li>
							<li class="bank_002"><input name="bank_type_en" value="ABC"
								type="radio"></li>
							<li class="bank_003"><input name="bank_type_en" value="CCB"
								type="radio"></li>
							<li class="bank_004"><input type="radio" name="bank_type_en"
								value="BOC"></li>

							<li class="bank_005"><input name="bank_type_en" value="CEB"
								type="radio"></li>
							<li class="bank_006"><input name="bank_type_en" value="CIB"
								type="radio"></li>
							<li class="bank_007"><input name="bank_type_en" value="SPDB"
								type="radio"></li>
							<li class="bank_008"><input name="bank_type_en"
								value="PINGAN" type="radio"></li>
							<li class="bank_009"><input name="bank_type_en" value="BOS"
								type="radio"></li>
							<li class="bank_010"><input name="bank_type_en" value="CBHB"
								type="radio"></li>
							<li class="bank_011"><input name="bank_type_en"
								value="CITIC" type="radio"></li>
							<li class="bank_012"><input name="bank_type_en" value="PSBC"
								type="radio"></li>

							<div style="clear: both;"></div>
						</ul>
					</div>
				</div>

				<div id="kjnr">
					<label>快捷支付:</label>
					<div id="kjnr_div1">

						<div id="kjnr_div1_01">快捷卡</div>
						<div id="kjnr_div1_02">${bankname}</div>
						<div id="kjnr_div1_03">${cardNo}</div>
					</div>
					<div id="kjnr_divmoren">
						<p id="p1">您尚未开通快捷支付</p>
						<p id="p2">充值即可开通</p>
					</div>

					<div id="kjnr_div2">
						<p id="p3">什么是快捷支付？</p>
						<p id="p4">1、快捷支付仅支持绑定一张银行卡，绑定后将默认为取现卡，如需修改，请点击解除绑定按钮；</p>
						<p id="p5">2、此前绑定的取现卡将同时作废，暂不支持设置多张快捷支付卡；</p>
						<p id="p6">3、开通快捷支付后，不影响使用本人其他银行卡或他人银行卡代充值。</p>
					</div>
				</div>

				<div id="kjnr_div3">
					<div>
						总资产:<span style="font-size: 25px; color: red"> <c:if
								test="${!empty uc.balance}">${df.format(uc.balance)}</c:if> <c:if
								test="${empty uc.balance}">0.00</c:if>
						</span>元&nbsp;&nbsp;&nbsp;&nbsp; 可用余额:<span
							style="font-size: 25px; color: red"> <c:if
								test="${!empty uc.avlbalance}">${df.format(uc.avlbalance)}</c:if>
							<c:if test="${empty uc.avlbalance}">0.00</c:if>
						</span>元&nbsp;&nbsp;&nbsp;&nbsp; 冻结余额:<span
							style="font-size: 25px; color: red"> <c:if
								test="${!empty uc.freezebalance}">${df.format(uc.freezebalance)}</c:if>
							<c:if test="${empty uc.freezebalance}">0.00</c:if>
						</span>元
					</div>
					<div>
						充值优惠券: <select id="select"><option>暂无优惠券可用</option></select>
					</div>
					<div>
						充值金额: <input name="TransAmt" id="transAmt"
							onblur="checkBlur(this)" onkeyup="checkUp(event,this)"
							maxlength="9" />元 <span id="txt_Amount_span"
							style="color: red; margin-left: 10px; font-size: 15px"></span>
					</div>
					<div>
						<button type="button" onclick="drawmoney()">确认充值</button>
					</div>
				</div>
			</form>
		</div>
		<!--middleTwo-->

		<div id="kjnr_bottom">
			<p id="p7">温馨提示:</p>
			<p>1.充值过程收取相应的费用;</p>
			<p>2.最低充值金额应不少于 1 元；</p>
			<p>3.充值期间，请勿关闭浏览器，待充值成功并返回首页后，所充资金才能入账，如有疑问，请联系客服</p>
			<p>400-888-6268；</p>
			<p>4.网银充值时企业用户只能使用企业网银,个人用户只能使用个人网银</p>
			<p>
				5.支付限额请参照支付说明。（<a href="http://www.baidu.com">
					http://www.baidu.com</a>）
			</p>
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