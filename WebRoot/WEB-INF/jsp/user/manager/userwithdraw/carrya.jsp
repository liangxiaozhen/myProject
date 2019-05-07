<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/resource/Css/carry.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/common/common.js"></script>
</head>
<body>
	<!-- 头部 -->
	<%@include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧 -->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<div class="fl perCerterR  bor_r bor_l">
				<form>
					<div class="fl pad_30 wid_w900 min_height clearfix tx">
						<div class="loadDiv fc_9 clearfix">
							<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要提现</span>
						</div>
						<div class="m2-userCentermanage-drawBox" style="margin-top: 10px;">

							<!-- 徽商银行start -->
							<div class="m2-userCentermanage-drawBox2" style="border: none;">

								<!--已经有银行卡start-->
								<div class="m2-userCentermanage-drawBox-cardTure"
									style="display: block;">
									<div class="m2-userCentermanage-drawBox-cardTit m2-txtRig">
										<span>提现银行卡：</span>
									</div>
									<ul class="m2-userCentermanage-drawBox-cardSel"
										style="position: relative;">
										<div class="m2-userBank-chose"
											style="position: absolute; left: 250px; top: 5px;">
											<span id="addother"><a
												href="${pageContext.request.contextPath }/user/bankcard/bindCard.action">选择其他银行卡</a></span>
											<!-- <a href="#">查看充值限额&raquo;</a> -->
											<b class="m2-userBank-choWarn"
												style="display: inline-block; width: 320px; height: 44px; line-height: 22px; top: 28px;">您只有在徽商银行电子交易账户余额与在投资金均为零时<br>才能切换银行卡
											</b>
										</div>
										<li class="m2-userCentermanage-drawBox-bank"><i></i>
											<p class="m2-userCentermanage-drawBox-bankName"
												id="card_name">${bankName }</p> <span
											class="m2-userCentermanage-drawBox-cardNumber">${bankCode }</span>
											<em class="m2-userCentermanage-bank-sel"></em> <!-- <b class="m2-userCentermanage-bank-close"></b> -->
										</li>

									</ul>
								</div>


								<div class="m2-userCentermanage-explainBox"
									style="border: none;">
									<ul>
										<li style="margin: 10px 0;"><span class="m2-txtRig">账户余额：</span>
											<span class="m2-userCenterdraw-serTot"
											style="color: #2577e3;"><fmt:formatNumber
													value="${avlbalance }" pattern="0.00" />元</span></li>
									</ul>
								</div>
								<div class="m2-userCentermanagedraw-numInput">
									<div class="m2-userCentermanage-drawBox-cardTit m2-txtRig">
										<span>提现金额：</span>
									</div>
									<div class="m2-userCentermanage-drawNum">
										<input type="text" class="m2-userCentermanagedraw-num"
											placeholder="请输入提现金额" id="withdrawmoney"
											onblur="checkNum(this)" onkeyup="checkNoNum(event,this)">
										<input type="button" class="m2-userCentermanagedraw-submit"
											value="提&nbsp;现" id="withdrawbutton">
									</div>
								</div>
								<div class="m2-userCentermanagedraw-numInput m2-userHs-bankName"
									style="display: none;">
									<div class="m2-userCentermanage-drawBox-cardTit m2-txtRig">
										<span>开户支行：</span>
									</div>
									<div class="m2-userCentermanage-drawNum">
										<div style="position: relative;">
											<input type="text" class="m2-userCentermanagedraw-num"
												placeholder="开户支行名称" id="bankname"> <b
												class="m2-userHs-iptWarn"> <!-- 错误提示信息 -->
											</b> <a href="http://www.lianhanghao.com/" target="_blank"
												class="m2-userHs-drawForget">忘记开户支行？</a>
										</div>
									</div>
								</div>
								<div class="m2-userHs-selBox">
									<ul class="m2-userHs-selList">
										<li class="m2-userHslist-unsel active m2-userHs-list1"
											value="1"><i></i><span>实时</span></li>
										<li class="m2-userHslist-unsel m2-userHs-list2" id="dae"
											value="2"><i></i><span>大额</span></li>
									</ul>
									<p class="m2-userHs-exp">
										<i></i> <span id="dae-span" style="display: none">支持20万元（含20万元）以下资金提现，实时到账。20万元以上请使用大额提现。</span>
										<span id="now-span">支持单笔5万及以下，单日20万及以下实时提现到账。单日20万以上请用大额提现。</span>
									</p>
								</div>

								<!--已经有银行卡end -->

								<div class="m2-userCentermanage-explainBox">
									<ul>
										<li><span class="m2-txtRig">手续费：</span> <span
											class="m2-userCenterdraw-serTot" id="withdrawfee">0元</span> <!--  <b
											class="m2-userCentermanagedraw-moreDetail"> <u> <em
													class="m2-draw-arr"></em> <em class="m2-draw-arrBg"></em>
													使用徽商银行电子交易账户提现无需支付任何手续费。
											</u>
										</b>--></li>
									</ul>
								</div>
								<div class="m2-recharge-tips">
									<h3>
										<i></i>温馨提示：
									</h3>
									<ul>
										<li><i class="m2-recharge-tips01"></i><b>请勿使用360浏览器兼容模式进行提现操作。</b></li>
										<li><i class="m2-recharge-tips02"></i><b>徽商银行电子交易账户采用原卡进出设置，资金只能提现至您本人充值的银行卡，提现无需支付任何手续费。</b></li>
										<li><i class="m2-recharge-tips03"></i><b>实时提现：支持单笔5万及以下，单日20万及以下实时提现到账。</b></li>
										<li><i class="m2-recharge-tips04"></i><b>大额提现：支持单日20万以上资金提现，工作日9:00-16:00。到账时间为30分钟左右，依据发卡行不同可能略有差异。</b></li>
										<li></li>
										<li><i class="m2-recharge-tips05"></i><b>提现页面暂不支持360浏览器兼容模式，请切换至急速模式，推荐使用火狐浏览器访问。</b></li>
									</ul>
								</div>
							</div>
							<!-- 徽商银行end -->
						</div>
					</div>
					<!--提现 -->

				</form>
			</div>
		</div>
	</div>
	<!-- 底部 
	<include file="Index/footer" />-->
	<script type="text/javascript" src="/Public/Scripts/jquery.js"></script>
	<script type="text/javascript"
		src="/Public/Scripts/jquery-1.7.2.min.js"></script>

	<div class="m2-userCentercommon-confirm" style='display: none;'
		id='msgdialog1'>
		<span class="m2-userCentercommon-confirmClose"></span>
		<p class="m2-userCommon-confirmWar">
			<i></i>
		</p>
		<p class="m2-userCommon-confirmBtn">
			<a class="m2-user-confirmBtn" href="javascript:void(0)">确&nbsp;定</a>
		</p>
	</div>
	<div class="m2-userCentercommon-confirm" style='display: none;'
		id='msgdialog2'>
		<span class="m2-userCentercommon-confirmClose"></span>
		<p class="m2-userCommon-confirmSuc">
			<i></i>
		</p>
		<p class="m2-userCommon-confirmBtn">
			<a class="m2-user-confirmBtn" href="javascript:void(0)">确&nbsp;定</a>
		</p>
	</div>
	
	<div class="panelbox wid_w480" id="identification" style="display: none;width: 300px;height:100px;left: 40%;top: 40%;'">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
         <span class="text">友情提示</span>
         <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="closefun();"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item clearfix">
		                   <p class="txt_center txt_msg"></p>
	                    </div>
		                <div class="item clearfix txt_center">
		                    <a href="javascript:void(0)" class="btn btn_36c btn_size120 btn_confirmBtn">确认</a>
	                    </div>
		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
	<script type="text/javascript">
		var avlbalance = "${avlbalance }";
		var bigAmount = parseFloat(50000);
		$('.m2-userCentercommon-confirmClose,.m2-user-confirmBtn')
				.click(
						function() {
							$('.m2-userCentercommon-confirm').hide();
							$('.m2-userCentercommon-bg').hide();
							var msg = $('.m2-userCommon-confirmWar').html();
							if (msg.indexOf('请先开通托管账户') >= 0) {
								location.href = "${pageContext.request.contextPath }/user/securitycenter/findopenAnAccountList.action";
							}
							if (msg.indexOf('请先设置交易密码') >= 0) {
								location.href = "${pageContext.request.contextPath }/user/securitycenter/list.action";
							}

						});
		$('.btn_confirmBtn')
		.click(
				function() {
					$("#identification").hide();
					$(".bg").hide();
					var msg = $('.txt_msg').html();
					if (msg.indexOf('请先开通托管账户') >= 0) {
						location.href = "${pageContext.request.contextPath }/user/securitycenter/findopenAnAccountList.action";
					}
					if (msg.indexOf('请先设置交易密码') >= 0) {
						location.href = "${pageContext.request.contextPath }/user/securitycenter/list.action";
					}

				});
		$("#withdrawbutton").click(function() {
			cupwithdraw();
		});
		/*
		function showMsg(msg, flag) {
			if (!flag) {
				$('.m2-userCommon-confirmWar').html('<i></i>' + msg);
				$('#msgdialog1').show();
				$('.m2-userCentercommon-bg').show();
			} else {
				$('.m2-userCommon-confirmSuc').html('<i></i>' + msg);
				$('#msgdialog2').show();
				$('.m2-userCentercommon-bg').show();
			}
		}*/
		function showMsg(msg) {
				$(".txt_msg").html('<i></i>' + msg);
				$("#identification").show();
				$(".bg").show();
		}
		function cupwithdraw() {
			var money = $("#withdrawmoney").val();
			if (money == '') {
				showMsg("请输入取现金额");
				return;
			}
			if (money <= 0) {
				showMsg("请输入正确的金额");
				return;
			}
			if (parseFloat(money) > parseFloat(avlbalance)) {
				showMsg("超出可取现金额。");
				return;
			}
			var isdae = $('#dae').hasClass("active");
			if (isdae) {
				showMsg('大额提现只能在工作日9:00-16:00使用，请谅解！');
				return;
			} else {
				if (parseFloat(money) > bigAmount) {
					showMsg('实时提现不能大于5万');
					return;
				}
			}
			///location.href="${pageContext.request.contextPath }/user/userwithdrawscash/drawmoney.action?amount="+money;
			checkDrawMoney(money);
		}
		function checkDrawMoney(money) {
			var action = "checkDrawMoney.action";
			var params = {
				"money" : money
			};
			var callback = function(data) {
				if (data.indexOf("Pass") > 0) {
					location.href = "${pageContext.request.contextPath }/user/userwithdrawscash/drawmoney.action?amount="
							+ money;
				} else {
					showMsg(data);
				}
			}
			$.post(action, params, callback, 'json');
		}
		function checkNoNum(event, obj) {
			// 响应鼠标事件，允许左右方向键移动
			event = window.event || event;
			if (event.keyCode == 37 | event.keyCode == 39) {
				return;
			}

			// 先把非数字的都替换掉，除了数字和.
			obj.value = obj.value.replace(/[^\d.]/g, "");
			// 必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g, "");
			// 保证只有出现一个.而没有多个.
			obj.value = obj.value.replace(/\.{2,}/g, ".");
			// 保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".", "$#$").replace(/\./g, "")
					.replace("$#$", ".");
			if (parseFloat(obj.value) > avlbalance) {
				obj.value = "0";
				$("#withdrawfee").html("0元");
				return;
			}
			checkFee(obj.value);
		}
		function checkFee(money) {
			var action = "${pageContext.request.contextPath }/user/userwithdrawscash/checkFee.action";
			var params = {
				"money" : money
			};
			var callback = function(data) {
				$("#withdrawfee").html(data + "元");
			}
			$.post(action, params, callback, 'json');
		}
		function closefun(){
			$("#identification").hide();
			$(".bg").hide();
	  }
	</script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							// 实时  大额
							$(".m2-userHs-selList li")
									.click(
											function() {
												$("#withdrawmoney").val("");
												$("#bankname").val("");
												$(".m2-userHs-selList li")
														.removeClass('active');
												$(this).addClass("active");
												var value = $(
														".m2-userHs-selList li.active")
														.attr("value");
												if (value == 1) {
													$(
															".m2-userCentermanagedraw-numInput.m2-userHs-bankName")
															.hide();
													$(
															".m2-userHs-exp #dae-span")
															.hide();
													$(
															".m2-userHs-exp #now-span")
															.show();
												} else {
													$(
															".m2-userCentermanagedraw-numInput.m2-userHs-bankName")
															.show();
													$(
															".m2-userHs-exp #dae-span")
															.show();
													$(
															".m2-userHs-exp #now-span")
															.hide();
												}
											});
							$(".m2-userCentermanage-drawBox-bank i").addClass(
									"m2-userBank-12");

							var isopen = "${isopen}";
							if (isopen != 'success') {
								showMsg("请先开通托管账户");
							}
							var tradePass = "${tradePass}";
							if (tradePass == "0") {
								showMsg("请先设置交易密码");
							}
						})
	</script>
</body>
</html>