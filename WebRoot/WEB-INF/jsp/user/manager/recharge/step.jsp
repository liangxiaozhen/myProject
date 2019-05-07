<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0049)https://member.niwodai.com/member/rechargeStep.do -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>账户详情_你我贷会员中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">	
<link href="${basePath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/new.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />
<script type="text/javascript" src="${basePath}/js/userRecharge/recharge.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/carry.css">
<script type="text/javascript">
	var basePath = "${basePath}";
</script>
<style type="text/css">
.rack {
    width: 0;
    height: 0;
    border: 8px solid #fff;
    border-color: transparent transparent #fff transparent;
    position: absolute;
    top: -16px;
    left: 102px;
}
.f1.perCenterBg {
    position: relative;
    border-left: solid 220px #fafafa;
    background: #fff;
    display: inline-block;
    box-shadow: -1px 0px 2px rgba(0, 0, 0, 0.10);
}

.m2-onlineCha-li img{width:100%;cursor: pointer;}

</style>
</head>

	<body>
	<!-- 公共头部 -->
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 侧边开始 -->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- 我要充值 -->
			<div class="fl pad_30 perCerterR bor_l bor_r wid_w960 ">
				<div class="loadDiv fc_9 clearfix">
					<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要充值</span>
				</div>
				<div class="mainbox">
			        <div class="pubTab">
			            <h2>账户充值</h2><a></a>
			            <div class="m2-recharge-mainbody">
							<div class="m2-userChahea">
								<div class="m2-userChahea-lef">
									<div class="m2-useChahea-head">
										<a href="${basePath}/user/userRecharge/banklist.action" target="_blank" class="m2-useCha-benDet">收益说明</a>
				                        <h4><i></i>账户余额收益</h4>
				                        <div style="display:inline-block;text-align:center;width:49%;margin:36px 0;">
				                            <p style="font-size:16px;line-height:12px;">0元</p>
				                            <p style="font-size:14px;line-height:30px;">昨日余额收益</p>
				                        </div>
				                        <div style="display:inline-block;text-align:center;width:49%;margin:36px 0;">
				                            <p style="font-size:16px;line-height:12px;">0元</p>
				                            <p style="font-size:14px;line-height:30px;">累计余额收益</p>
				                        </div>
				                    </div>
									<div class="m2-useChahea-bot">
										<div class="m2-useChabot-item">
											<div class="m2-useChabot-tit">
												<span>7日内收益率</span>
				                                <b class="m2-detail-titIcon" style="position:relative;top:6px;right:0;">
				                                    <u>
				                                        <em class="m2-detail-titHide-arr"></em>
				                                        <em class="m2-detail-titHide-arrBg"></em>
				                                        为了使您的理财收益最大化，您账户中的剩余金额系统将自动为您购买国寿货币基金。您持有的基金总额可直接用于投资爱钱帮任意理财产品，以此使您的理财收益最大化。18岁以下用户无法享受货币基金收益，将按银行活期存款利率计算，在提现第二日发放。
				                                    </u>
				                                </b>
											</div>
				                            <div class="m2-useChabot-num">
				                                <span>4.17%</span>
				                            </div>
										</div>
									</div>
				                </div>
				                <div class="m2-userChahea-rig">
				                    <div class="m2-useCharig-line"></div>
				                    <div class="m2-useChaCardbox">
										<div class="m2-useCardbox-head">
				                            <div class="m2-useCardbox-logo"><i></i></div>
				                            <div class="m2-useCardbox-tit"><span>电子交易账户</span><a href="javascript:;"></a></div>
				                        </div>
				                        <div class="m2-useCardbox-mid">${usercustid}</div>
				                        <input id="usercustid" type="hidden" value="${usercustid}"/>
				                        <div class="m2-useCardbox-bot">
				                            <p>开户人：<span>${realname}</span></p>
				                            <p>账户余额：<b id="balance">${df.format(uc.balance)}</b>元</p>
				                            <p>可用余额：<b id="balance">${df.format(uc.avlbalance)}</b>元</p>
				                        </div>
				                           <!--  <div><button style="width: 100px;margin-left: 50px" onclick="queryBlance();">查询</button></div> -->
				                    </div>
				                </div>
			            	</div>
							<h4 class="m2-userCha2-tabHead">选择充值方式</h4>
							<!-- 切换 -->
				            <div class="m2-usercha2-tab">
				                <ul>
				                	<li class="m2-userCha2tab-unsel active" value="1"><span>网银充值</span><b></b></li>
				                    <li class="m2-userCha2tab-unsel" value="2"><span>快捷支付</span><b></b></li>
				                    <li class="m2-userCha2tab-unsel" value="3">
									    <span>支付宝转账</span><b></b>
				                        <div id="alipayalert">直接充值到您本人的<br>电子账户，快速到账</div>
				                        <p class="rack"></p>
									</li>
				                    <li class="m2-userCha2tab-unsel" value="4" id="getinfo"><span>银行转账</span><b></b></li>
				                   <li value="5" style="float: right;height: 42px;line-height: 42px;margin-right:20px;background:#2577e3;color:#fff;">
				                    	<span style="background:#2577e3;color:#fff;display:inline-block;">充值记录</span>
				                    </li>
				                </ul>
				            </div>
				            <!-- 内容 -->
				            <!-- 1 -->
				            <div class="m2-usercha2-contain box box1">	
								<div class="m2-recharge-ent m2-recharge-ipt" style="border-bottom:1px solid #f3f3f3;padding-top:70px;">
									<table cellpadding="0" cellspacing="0" border="0" style="width:538px;">
										<tbody><tr>
											<td align="right" style="width:125px;padding-top:20px;">充值金额：</td>
											<td style="width:270px;padding-top:20px;">
												<input type="text" class="m2-input-ent" id="wangguan_num" placeholder="请输入充值金额" style="width:264px;" 
												onkeyup="this.value=this.value.replace(/(\d*\.\d{2}).*\d/,'$1')"/>
											</td>
											<td style="width:143px;padding-top:20px;"><a href="javascript:;" class="m2-recharge-entChr" id="wangguan_recharge"  style="border-radius:4px;margin-left:8px;">充值</a></td>
										</tr>
										<tr>
											<td align="right" style="padding-top:15px;">充值限额：</td>
											<td style="padding-top:15px;" colspan="2"><span>根据发卡行网银支付限额而定，使用U盾可提高支付限额</span></td>
										</tr>

										<tr>
											<td align="right" style="padding-top:20px;">手续费：</td>
											<td style="padding-top:20px;"><span>0</span>元 </td>
											<td style="padding-top:20px;"></td>
										</tr>
									</tbody></table>
								</div>
								<div class="m2-onlineChargelist">
									<h3>支持银行列表：</h3>
										<ul>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank01040000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank01000000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank01020000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank01050000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03010000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank01000000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03050000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03030000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03060000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03090000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03080000.gif" alt=""></li>
											<li class="m2-onlineCha-li"><img src="${basePath}/resources/resource/Images/bank03040000.gif" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/bank03100000.gif" alt=""></li>
											<!-- <li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="images/bank/onlineBank14.jpg" alt=""></li> -->
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank15.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank16.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank17.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank18.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank19.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank20.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank21.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank22.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank23.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank24.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank25.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/bank04083320.gif" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank27.jpg" alt=""></li>
											
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/bankCZB.gif" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank29.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank30.jpg" alt=""></li>
											<!-- <li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="images/bank/onlineBank32.jpg" alt=""></li> -->
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank31.jpg" alt=""></li>
											<li class="m2-onlineCha-li m2-onlineCha-hid" style="display:none;"><img src="${basePath}/resources/resource/Images/onlineBank33.jpg" alt=""></li>
										</ul>
									<div class="m2-onlineToggle">
										<span class="toggleUp">更多银行<i></i></span>
										<span class="toggleDown" style="display:none;">收起更多<i></i></span>
									</div>
								</div>
								<div class="m2-recharge-tips">
									<h3><i></i>温馨提示：</h3>
									<ul>
									    <li><i class="m2-recharge-tips01"></i><b>请勿使用360兼容模式进行充值操作。</b></li>
			                            <li><i class="m2-recharge-tips02"></i><b>投资人充值不收取手续费。</b></li>
			                            <li><i class="m2-recharge-tips03"></i><b>徽商银行电子交易账户采用原卡进出设置，为了您的资金安全，只能提现至您绑定的银行卡。</b></li>
			                            <li><i class="m2-recharge-tips04"></i><b>更换已绑定的银行卡需在投金额和账户余额都为0，如需换卡或充值过程中遇到问题，请联系客服：4006-777-518。</b></li>
			                            <li><i class="m2-recharge-tips05"></i><b>若您充值后未投资，系统将自动为您认购国寿安保货币基金，您持有的基金总额可直接用于投资爱钱帮任意理财产品，以此使您的理财收益最大化。</b></li>
									</ul>
								</div>
							</div>
							<!-- 2 -->
				            <div class="m2-usercha2-contain box box2" style="display:none;">
				                <div class="m2-recharge-mainbody">
				                    <div class="m2-user-bankSelbox" style="display:none" id="carddiv1">
				                        <div class="m2-userBankitem m2-userBank-unsel m2-userBankitem-add" style="width:255px;height:64px;">
				                            <i class="mo2-userChabank-add"></i>
				                            <span class="m2-userBankitem-bind"> 添加银行卡</span>
										</div>
				                        <div class="m2-userBank-chose" style="padding-top:10px;">
				                            <a href="#">查看充值限额»</a>
				                            <b class="m2-userBank-choWarn">如需换卡，请联系客服：4006-777-518</b>
				                        </div>
				                    </div>
				                    <div class="m2-user-bankSelbox" id="carddiv2">
				                        <div class="m2-userBankitem m2-userBank-sel" style="width:255px;height:64px;">
				                        	<!-- 这里对应银行卡图标见css2488行，修改类名数字即可 -->
				                            <i class="mo2-userChabank-common" id="bankclass"></i>
				                            <span class="m2-userBankitem-det">
				                                <span id="inst_name" style="font-size:15px;">${bankname}</span><br>
				                                <input type="hidden" value="${bankname}" id="banknames"/>
				                                <span id="cardshow">${cardNo}</span>
				                                <input type="hidden" id="cardNo" value="${cardNo}"/>
				                            </span>
				                            <b class="mo2-user-bank-corner"></b>
				                        </div>
				                        <div class="m2-userBank-chose" style="padding-top:10px;">

				                            <span><a href="javascript:;">选择其他银行卡»</a></span>
				                            <!-- <a href="#">查看充值限额&raquo;</a> -->
				                            <b class="m2-userBank-choWarn">如需换卡，请联系客服：400-8888-888</b>
				                        </div>
				                    </div>               
				                    <div class="m2-recharge-ent">
				                        <table cellpadding="0" cellspacing="0" border="0" style="width:600px;">
				                            <tbody><tr>
				                                <td align="right">充值金额：</td>
				                                <td>
				                                	<input type="text" class="m2-input-ent" placeholder="请输入充值金额" id="chargemoney" 
				                                	onkeyup="this.value=this.value.replace(/(\d*\.\d{2}).*\d/,'$1')"/>
				                                		
				                                </td>
				                            </tr>
				                         
				                            <tr style="height:58px;">
				                                <td align="right">验证码：</td>
				                                <td><input type="text" class="m2-input-ent" placeholder="请输入验证码" id="sms_code" value=""></td>
				                                <td><input type="hidden" class="m2-input-ent" placeholder="请输入验证码" id="sms_seq" value=""></td>
				                                <td><input  class="m2-recharge-entChr" type="button" id="btn"  style="width:180px;height:38px;font-size:14px;color: white;line-height:38px;background-color: #2577E3;" value="发送验证码至${phonestr}"/></td>
				                                
				                                <input type="hidden" id="phonestr" value="${phonestr}"/>
				                            </tr>
				                            
				                            <tr>
				                                <td align="right">手续费：</td>
				                                <td class="m2-recharge-cost"><span>0</span>元 </td>
				                            </tr>
				                            <tr>
				                                <td align="right">发卡行限额：</td>
				                                <td colspan="2">
				                                <span id="limitspan">单笔25万，单日25万</span>
				                                
				                                </td>
				                                <td><a class="m2-recha2-limLink" href="${basePath}/user/userRecharge/banklist.action">查看限额</a>或<a class="m2-recha2-limLink" href="javascript:void(0);" id="changbank">使用限额更高的银行转账</a></td>
				                            </tr>
				                            <tr>
				                                <td align="right">到账金额：</td>
				                                <td id="chargemoney1">0元</td>
				                            </tr>
				                            <tr>
				                                <td></td>
				                                <td><a href="javascript:;" class="m2-recharge-entChr" onclick="recharge_yzm()">充值</a></td>
				                            </tr>
				                        </tbody></table>
				                    </div>
				                    
				                    <div class="m2-recharge-tips">
				                        <h3><i></i>温馨提示：</h3>
										<ul>
											    <li><i class="m2-recharge-tips01"></i><b>请勿使用360兼容模式进行充值操作。</b></li>
					                            <li><i class="m2-recharge-tips02"></i><b>投资人充值不收取手续费。</b></li>
					                            <li><i class="m2-recharge-tips03"></i><b>徽商银行电子交易账户采用原卡进出设置，为了您的资金安全，只能提现至您绑定的银行卡。</b></li>
					                            <li><i class="m2-recharge-tips04"></i><b>更换已绑定的银行卡需在投金额和账户余额都为0，如需换卡或充值过程中遇到问题，请联系客服：4006-777-518。</b></li>
					                            <li><i class="m2-recharge-tips05"></i><b>若您充值后未投资，系统将自动为您认购国寿安保货币基金，您持有的基金总额可直接用于投资爱钱帮任意理财产品，以此使您的理财收益最大化。</b></li>
										</ul>
				                    </div>
				                </div>
				            </div>
				            <!-- 3 -->
				            <div class="m2-usercha2-contain box box3" id="alipay" style="display:none;">
								<div class="m2-userCha2-exp">
									<p>您可以使用您的银行卡，通过支付宝的方式将资金充值到您的徽商银行存管账户（支付APP更方便），转账时所需填写信息如下：</p>
									<div class="container">
										<p>收款方户名：<b>${realname}</b></p>
					                    <p>收款方账号：<b>${usercustid }</b></p>
										<p>收款方开户行：<b>徽商银行股份有限公司合肥花园街支行</b></p>
									</div>
								</div>
								<div style="clear:both;"></div>
								<div class="m2-recharge-tips">
									<h3><i></i>温馨提示：</h3>
									<ul>
										<li><i class="m2-recharge-tips01"></i><b>充值过程收取转账费用，以支付宝规定为准，爱钱帮不收取其他任何手续费。</b></li>
										<li><i class="m2-recharge-tips02"></i><b>徽商银行电子交易账户采用原卡进出设置，为了您的资金安全，只能提现至您绑定的银行卡。</b></li>
										<li><i class="m2-recharge-tips03"></i><b>更换已绑定的银行卡需在投金额和账户余额都为0，如需换卡或充值过程中遇到问题，请联系客服：4006-777-518。</b></li>
										<li><i class="m2-recharge-tips04"></i><b>若您充值后未投资，系统将自动为您认购国寿安保货币基金，您持有的基金总额可直接用于投资爱钱帮任意理财产品，以此使您的理财收益最大化。</b></li>
									</ul>
								</div>
								<div style="clear:both;"></div>
								<div id="alipaycontent">
									<ul style="height:54px;">
										<li style="border-top:1px solid #ddd;border-left:1px solid #ddd;border-top-left-radius:4px;" class="alipayselect" data="36">第一步</li>
										<li style="border:1px solid #ddd;" data="35">第二步</li>
										<li style="border-top:1px solid #ddd;border-right:1px solid #ddd;border-top-right-radius:4px;" data="34">第三步</li>
									</ul>
									<img class="now_img" src="${basePath}/resources/resource/Images/onlineBank36.png" width="100%;">
				            	</div>
							</div>
							<!-- 4 -->
				            <div class="m2-usercha2-contain box box4" style="display:none;">
								<div class="m2-userCha2-exp">
				                    <p>您可以向您的徽商银行账户转账，实现账户充值。建议转账方式包括：银行柜台转账、网银转账、手机银行转账。转账时所需填写信息如下： </p>
				                    <p>收款方户名：<b>${realname}</b></p>
				                    <p>收款方账号：<b>${usercustid }</b></p>
				                    <p>收款方开户行：<b>徽商银行股份有限公司合肥花园街支行</b></p>
				                </div>
				                <div class="m2-recharge-tips">
				                    <h3><i></i>温馨提示：</h3>
				                    <ul>
				                        <li><i class="m2-recharge-tips01"></i><b>充值过程收取转账费用，以银行规定为准，爱钱帮不收取其他任何手续费。</b></li>
				                        <li><i class="m2-recharge-tips02"></i><b>徽商银行电子交易账户采用原卡进出设置，为了您的资金安全，只能提现至您绑定的银行卡。</b></li>
				                        <li><i class="m2-recharge-tips03"></i><b>更换已绑定的银行卡需在投金额和账户余额都为0，如需换卡或充值过程中遇到问题，请联系客服：4006-777-518。</b></li>
				                        <li><i class="m2-recharge-tips04"></i><b>若您充值后未投资，系统将自动为您认购国寿安保货币基金，您持有的基金总额可直接用于投资爱钱帮任意理财产品，以此使您的理财收益最大化。</b></li>
				                    </ul>
				                </div>
							</div> 
							<!-- 5 -->
				            	<div class="m2-usercha2-contain box box5" style="display:none;">
				            	<%@ include file="userRechargeRecord.jsp"%>
				            </div>
						</div>
			        </div>
			       
		    	</div>
		    </div>	
    		<div class="bg"></div>
		</div>
	</div>
	<!--充值 弹窗-->
		<div class="messages" style="display:none;z-index:999;background:#fff;padding:20px 80px;">
	    	<div class="fl pad_b10 tab clearfix">
				<dl class="succ_money fl clearfix">
				    
					<dt class="fl mar_l20 mar_t30" style="margin: 0;width: 300px;height: 150px;">
						<p class="fs_18 clearfix jine1" style="font-size:25px;text-align:center;margin-top: 30px"></p>
						<p class="pad_t10 clearfix" style="text-align:center;margin-top: 20px" id="mag">
							<a href="${basePath}/user/userRecharge/rechargeList.action" class="close btn btn_bord_orang btn_size120 queding" style="height: 30px;line-height:30px;">支付完成</a>
						    <a href="javascript:;" class="close btn btn_bord_orang btn_size120" style="height: 30px;line-height:30px;" onclick="conpay();">继续支付</a>
						</p>
						<p class="pad_t10 clearfix" id="passok" style="text-align:center;margin-top: 20px">
							<a href="javascript:;" class="close btn btn_bord_orang btn_size120" style="height: 30px;line-height:30px;">确定</a>
						</p>
						<p class="pad_t10 clearfix" id="passok1" style="text-align:center;margin-top: 20px">
							<a href="${basePath}/user/bankcard/bindCard.action" class="close btn btn_bord_orang btn_size120" style="height: 30px;line-height:30px;">确定</a>
						</p>
					</dt>
				</dl>
			</div>
		</div> 
	
		
	<!-- 绑定银行卡 -->
		<div class="m2-charge2Confirm" style="display:none;margin-left: 0">
        	<b class="m2-cha2Con-close"></b>
	        <div class="m2-cha2Conf-bind">
	            <div class="m2-cha2Conf-tit">
	                <span>绑定银行卡</span>
	            </div>
	            <table cellpadding="0" cellspacing="0">
	                <tbody>
	                    <tr>
	                        <td class="m2-chaConf-tit">
	                            <i class="m2-chaConf-user"></i>
	                            <span>真实姓名</span>
	                        </td>
	                        <td class="m2-chaConf-con">
	                            <span>${realname}</span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="m2-chaConf-tit">
	                            <i class="m2-chaConf-tel"></i>
	                            <span>手机号</span>
	                        </td>
	                        <td class="m2-chaConf-con">
	                            <span>${phone}</span>
	                            <a href="${basePath}/user/securitycenter/list.action">修改</a>
	                            <b class="m2-chaConf-warn" style="color:#999;">认证手机号须与银行卡预留手机号一致 否则无法绑卡</b>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="m2-chaConf-tit">
	                            <i class="m2-chaConf-card"></i>
	                            <span>借记卡</span>
	                        </td>
	                        <td class="m2-chaConf-con">
	                            <input type="text" style="width:265px;" placeholder="" id="cardinput" maxlength="19">
	                            <b class="m2-chaConf-warn" style="color:#999;">该银行卡开户姓名必须为刘清华，否则会提现失败！</b>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	        </div>
	        <div class="m2-cha2Confsubmit">
	            <span class="m2-cha2Confsub-sure"><a href="${basePath}/user/bankcard/bindCard.action">确&nbsp;&nbsp;&nbsp;认</a></span>
	        </div>
	    </div>	
	<!-- 公共底部 -->
	<%-- <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
   <script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Css/jquery-ui.css"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-ui.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/mobiscroll_date.js" charset="gb2312"></script> 
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/mobiscroll.js"></script> 

<script type="text/javascript">
$(function () {
$(".side_nav").find("dl").siblings().removeClass("navcurron").eq(1).addClass("navcurron");
});


$(document).ready(function(){
		$(".box2,.box3,.box4,.box5").hide();
	    $(".m2-usercha2-tab ul li").click(function(){
	        $(".m2-usercha2-tab ul li").removeClass("active")
	        var num = $(this).val();
	        if(num==2){//表示快捷的意思
	        	var cardno = $("#cardNo").val();
	        	var banknames = $("#banknames").val();
	            if(cardno==null || cardno=="" || cardno==undefined){
	            	$(".jine1").text("");
					$(".jine1").text("对不起您没有绑卡");
					$("#mag").hide();//这个是继续支付,支付成功按钮
					$("#passok").hide();
					$("#passok1").show();//链接窗口
					showDiv(".messages");
					$(".messages").show();
					$(".messages,.bg").show();
	            }
	            if(banknames!=null || banknames!="" || banknames!=undefined){
	            	if(banknames.indexOf("工商")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-1");
	            	}
	            	if(banknames.indexOf("农业")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-2");
	            	}
	               if(banknames.indexOf("交通")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-3");
	            	}
	            	if(banknames.indexOf("浦发")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-4");
	            	}
	            	if(banknames.indexOf("邮政")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-5");
	            	}
	            	if(banknames.indexOf("光大")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-6");
	            	}
	            	if(banknames.indexOf("招商")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-7");
	            	}
	            	if(banknames.indexOf("兴业")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-8");
	            	}
	            	if(banknames.indexOf("上海银行")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-9");
	            	}
	            	if(banknames.indexOf("民生")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-10");
	            	}
	            	if(banknames.indexOf("建设")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-11");
	            	}
	            	if(banknames.indexOf("工商")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-12");
	            	}
	            	if(banknames.indexOf("中信")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-13");
	            	}
	            	if(banknames.indexOf("华夏")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-14");
	            	}
	            	if(banknames.indexOf("广发")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-15");
	            	}
	            	if(banknames.indexOf("深圳发展")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-16");
	            	}
	            	if(banknames.indexOf("平安")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-17");
	            	}
	            	if(banknames.indexOf("恒丰")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-18");
	            	}
	            	if(banknames.indexOf("渤海")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-19");
	            	}
	            	if(banknames.indexOf("浙商")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-20");
	            	}
	            	if(banknames.indexOf("北京")>=0){
	            		$("#bankclass").addClass("m2-userBankhf-21");
	            	}
	            	
	            	
	            }
	        } 
	        $(this).addClass("active")
	        $(".box").hide();
	        $(".box"+num).show();
	    });	
	
})
 $(function(){
	var that = ${that};
	if(that=="64"){
		$(".box1,.box2,.box3,.box4").hide();
		 $(".m2-usercha2-tab ul li").removeClass("active")
		  $(".box5").addClass("active")
		$(".box5").show();
	}
}); 
showDiv();
center(".messages")
function showDiv(obj){
	 $(obj).show();
	 center(obj);
	 $(window).scroll(function(){
	  center(obj);
	 });
	 $(window).resize(function(){
	  center(obj);
	 }); 
}
function center(obj){
	 var windowWidth = document.documentElement.clientWidth;   
	 var windowHeight = document.documentElement.clientHeight;   
	 var popupHeight = $(obj).height();   
	 var popupWidth = $(obj).width();    
	 $(obj).css({   
	  "position": "absolute",   
	  "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),   
	  "left": (windowWidth-popupWidth)/2   
	 });  
	  
}
	$(".close").click(function(){
   		$(this).parents(".messages").hide();
   		$(".bg").hide()
   })
   
   function queryBlance(){
		var usercustid= $("#usercustid").val();
		var action = "${basePath}/user/userRecharge/queryBanlance.action";
		var params = {
				"usercustid":usercustid
		}
		$.post(action,params,function(data){
			$("#balance").text(data);
		});
		
	}
   //点击继续支付的时候把验证码输入框滞空
   function conpay(){
	    }
   function callCode(){
	   $.ajax({
			type : "POST", 
			url : "${basePath}/user/userRecharge/phone.action", 
			success : function(result) { 
				$("#sms_seq").val(result); 
			}
	 })	
   }
   //定时器!点击完获取验证码之后进入倒计时
   var wait=60;
   var phoneStr = $("#phonestr").val();
   function settime(o) {
     if (wait == 0) {
      o.removeAttribute("disabled");   
      o.value="获取验证码"+phoneStr;
      wait = 60;
     } else { 
      o.setAttribute("disabled", true);
      o.value="重新发送(" + wait + ")";
      wait--;
      setTimeout(function() {
       settime(o);
      },
      1000)
     }
    }
   //快捷金额输入框
   $("#chargemoney").blur(function(){
	   var money = $("#chargemoney").val();
		var index = money.indexOf("0");//第一个为0的位置
		if(index==0 && money.length>1){/*0开头的数字串*/
			var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
				if(!reg.test(money)){
					$(".jine1").text("");
					$(".jine1").text("充值金额格式错误，请重新输入");
					$("#mag").hide();//这个是继续支付,支付成功按钮
					$("#passok1").hide();
					showDiv(".messages");
					$(".messages").show();
				}
			}else{
				 var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/; 
				 if(!reg.test(money)){
					 var money2 = money.split(".");
						if(typeof(money2[1].substring(2,3)) != "number"){//判断第三位是不是数字
							$(".jine1").text("");
							$(".jine1").text("充值金额格式错误，请重新输入");
							$("#mag").hide();//这个是继续支付,支付成功按钮
							$("#passok1").hide();
							showDiv(".messages");
							$(".messages").show();
						}else{
							money = money2[0]+"."+money2[1].substring(0,2);//小数点只有一位的情况下,如果小数点后面超过2位,就直接把多余的去除
							$("#chargemoney").val(money);
						}
			  	 }
		 }
   });
   //快捷充值输入框
  $("#btn").click(function(){
	  var money = $("#chargemoney").val();
		var falg = false;
		var index = money.indexOf("0");//第一个为0的位置
		if(index==0 && money.length>1){/*0开头的数字串*/
			var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
				if(!reg.test(money)){
					$(".jine1").text("");
					$(".jine1").text("充值金额格式错误，请重新输入");
					$("#mag").hide();//这个是继续支付,支付成功按钮
					$("#passok1").hide();
					showDiv(".messages");
					$(".messages").show();
				}else{
					falg = true;
				}
			}else{
				 var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/; 
				 if(!reg.test(money)){
					 var money2 = money.split(".");
						if(typeof(money2[1].substring(2,3)) != "number"){//判断第三位是不是数字
							$(".jine1").text("");
							$(".jine1").text("充值金额格式错误，请重新输入");
							$("#mag").hide();//这个是继续支付,支付成功按钮
							$("#passok1").hide();
							showDiv(".messages");
							$(".messages").show();
						}else{
							money = money2[0]+"."+money2[1].substring(0,2);//小数点只有一位的情况下,如果小数点后面超过2位,就直接把多余的去除
							$("#chargemoney").val(money);
						}
						
			  	 }else{
			  		 falg = true;
			  	 }
		 }
		if(falg){
		  callCode();	
		  settime(this);
		}
	  
	  });

   //快捷充值按钮
   function recharge_yzm(){
	   var sum=$(".m2-usercha2-tab ul li.active").val();
	   var money = $("#chargemoney").val();
	   var sms_code = $("#sms_code").val();
	   var sms_seq = $("#sms_seq").val();
	   
		var falg = false;
		var index = money.indexOf("0");//第一个为0的位置
		if(index==0 && money.length>1){/*0开头的数字串*/
			var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
				if(!reg.test(money)){
					$(".jine1").text("");
					$(".jine1").text("充值金额格式错误，请重新输入");
					$("#mag").hide();//这个是继续支付,支付成功按钮
					$("#passok1").hide();
					showDiv(".messages");
					$(".messages").show();
				}else{
					falg = true;
				}
			}else{
				 var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/; 
				 if(!reg.test(money)){
					 var money2 = money.split(".");
						if(typeof(money2[1].substring(2,3)) != "number"){//判断第三位是不是数字
							$(".jine1").text("");
							$(".jine1").text("充值金额格式错误，请重新输入");
							$("#mag").hide();//这个是继续支付,支付成功按钮
							$("#passok1").hide();
							showDiv(".messages");
							$(".messages").show();
						}else{
							money = money2[0]+"."+money2[1].substring(0,2);//小数点只有一位的情况下,如果小数点后面超过2位,就直接把多余的去除
							$("#wangguan_num").val(money);
						}
					
			  	 }else{
			  		 falg = true;
			  	 }
		 }
	    if(falg){
	    	/*开始  */
		   if(sms_code==""||isNaN(sms_code)){
				$(".jine1").text("");
				$(".jine1").text("验证码不能为空");
				$("#mag").hide();
				$("#passok1").hide();
				$("#passok").show();
				showDiv(".messages");
				$(".messages").show();
			}else{
				var action = "${basePath}/user/userRecharge/RechargeConfirmation2.action";
				var params = {
					"money" : money,
					"sum" : sum,
					"sms_code" : sms_code,
					"sms_seq" : sms_seq
				}
				$.post(action, params, function(result) {
					if(result=="nosnllink"){
						$(".jine1").text("");
						$(".jine1").text("请管理员设置充值费率");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					}else if (result == "success") {
						$(".jine1").text("");
						$(".jine1").text("请在打开的窗口完成支付");
						$("#mag").show();
						$("#passok").hide();
						$("#passok1").hide();
						showDiv(".messages");
						$(".messages,.bg").show();
						$(".messages").show();
					} else if (result == "theamountofamount") {
						$(".jine1").text("");
						$(".jine1").text("金额出现错误,请联系客服4008001999");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					} else if (result == "rechargeFalse") {
						$(".jine1").text("");
						$(".jine1").text("充值失败");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					} else if (result == "nohas") {
						$(".jine1").text("");
						$(".jine1").text("对不起,您暂时不能充值,请联系客服");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					} else if (result == "notRate") {
						$(".jine1").text("");
						$(".jine1").text("请联系管理员进行充值费率设置");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					} else if (result == "minfalse") {
						$(".jine1").text("");
						$(".jine1").text("输入金额过小,请重新输入");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					} else if (result == "maxfalse") {
						$(".jine1").text("");
						$(".jine1").text("输入金额过大,请重新输入");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					}else{//这个就是根据工具类查询出来的报错信息
						$(".jine1").text("");
						$(".jine1").text(result);
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						$("#passok").show();
						showDiv(".messages");
						$(".messages").show();
					}
				}, 'json');

			}
			/* 结束  */
		}

	}
	//金额校验
	function clearNoNum(obj) {
		if (obj.value != '' && obj.value.substr(0, 1) == '.') {
			obj.value = "";
		}
		obj.value = obj.value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符  
		obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的       
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数       
		if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
			if (obj.value.substr(0, 1) == '0' && obj.value.length == 2) {
				obj.value = obj.value.substr(1, obj.value.length);
			}
		}
	}
		//网银充值金额校验(输入框校验)
	   $("#wangguan_num").blur(function(){
		   var money = $("#wangguan_num").val();
			var index = money.indexOf("0");//第一个为0的位置
			if(index==0 && money.length>1){/*0开头的数字串*/
				var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
					if(!reg.test(money)){
						$(".jine1").text("");
						$(".jine1").text("充值金额格式错误，请重新输入");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						showDiv(".messages");
						$(".messages").show();
					}
				}else{
					 var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/; 
					 if(!reg.test(money)){
						 var money2 = money.split(".");
							if(typeof(money2[1].substring(2,3)) != "number"){
								$(".jine1").text("");
								$(".jine1").text("充值金额格式错误，请重新输入");
								$("#mag").hide();//这个是继续支付,支付成功按钮
								$("#passok1").hide();
								showDiv(".messages");
								$(".messages").show();
							}else{
								money = money2[0]+"."+money2[1].substring(0,2);//小数点只有一位的情况下,如果小数点后面超过2位,就直接把多余的去除
								$("#wangguan_num").val(money);
							}
							
				  	 }
			 }
	   });
	// 网银充值
	$("#wangguan_recharge").click(
			function() {
				//获取是快捷充值还是网银充值 1表示网银 2表示快捷
				var sum = $(".m2-usercha2-tab ul li.active").val();
				//获取充值金额
				var money = $("#wangguan_num").val();
				var falg = false;
				var index = money.indexOf("0");//第一个为0的位置
				if (index == 0 && money.length > 1) {/*0开头的数字串*/
					var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
					if (!reg.test(money)) {
						$(".jine1").text("");
						$(".jine1").text("充值金额格式错误，请重新输入");
						$("#mag").hide();//这个是继续支付,支付成功按钮
						$("#passok1").hide();
						showDiv(".messages");
						$(".messages").show();
					} else {
						falg = true;
					}
				} else {
					var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/;
					if (!reg.test(money)) {
						if(typeof(money2[1].substring(2,3)) != "number"){
							$(".jine1").text("");
							$(".jine1").text("充值金额格式错误，请重新输入");
							$("#mag").hide();//这个是继续支付,支付成功按钮
							$("#passok1").hide();
							showDiv(".messages");
							$(".messages").show();
						}else{
							money = money2[0]+"."+money2[1].substring(0,2);//小数点只有一位的情况下,如果小数点后面超过2位,就直接把多余的去除
							$("#wangguan_num").val(money);
						}
					} else {
						falg = true;
					}
				}
				if (falg) {
	 			/*  $(".jine1").text("");
					$(".jine1").text("请在打开的窗口完成支付");
					$("#mag").show();
					$("#passok").hide();
					$("#passok1").hide();
					showDiv(".messages");
					$(".messages,.bg").show();
					$(".messages").show();
					$("#wangguan_recharge").attr("href","${basePath}/user/userRecharge/RechargeConfirmation.action?money="+ money + "&sum=" + sum);
					$("#wangguan_recharge").attr("target", "_blank");//设定新开一个窗口   */
				
					var action = "${basePath}/user/userRecharge/result.action";
					var params = {
							"money":money,
							"sum":sum
					}
					$.post(action,params,function(data){
						   if(data!="成功"){//校验没有通过
							   $(".jine1").text("");
								$(".jine1").text(data);
								$("#mag").hide();
								$("#passok1").hide();
								showDiv(".messages");
								$(".messages").show();
						   }else{//弹框并且跳转到新页面
								$(".jine1").text("");
								$(".jine1").text("请在打开的窗口完成支付");
								$("#mag").show();
								$("#passok").hide();
								$("#passok1").hide();
								showDiv(".messages");
								$(".messages,.bg").show();
								$(".messages").show(); 
								window.open("${basePath}/user/userRecharge/RechargeConfirmation.action?money="+ money + "&sum=" + sum);
						   }
					},'json'); 
				}
			});
	//银行卡显示更多
	$(document).on("click", ".toggleUp", function() {
		$(".m2-onlineCha-li.m2-onlineCha-hid,.toggleDown").show();
		$(".toggleUp").hide();
	})
	$(document).on("click", ".toggleDown", function() {
		$(".m2-onlineCha-li.m2-onlineCha-hid,.toggleDown").hide();
		$(".toggleUp").show();
	})
	//支付宝转账
	$("#alipaycontent li")
			.click(
					function() {
						var data = $(this).attr("data");

						$("#alipaycontent li").removeClass('alipayselect');
						$(this).addClass("alipayselect");
						if (data == 36) {
							$("#alipaycontent .now_img")
									.attr("src",
											"${basePath}/resources/resource/Images/onlineBank36.png")
						} else if (data == 35) {
							$("#alipaycontent .now_img")
									.attr("src",
											"${basePath}/resources/resource/Images/onlineBank35.png")
						} else {
							$("#alipaycontent .now_img")
									.attr("src",
											"${basePath}/resources/resource/Images/onlineBank34.png")
						}

					});
	//绑定银行卡
	$(".m2-userBank-chose span").click(function() {
		$(".m2-charge2Confirm,.bg").show();
		showDiv(".m2-charge2Confirm");
	})
	$(".m2-cha2Con-close").click(function() {
		$(this).parents(".m2-charge2Confirm").hide();
		$(".bg").hide()
	})

	$(".m2-cha2Con-close").click(function() {
		$(this).parents(".m2-charge2Confirm").hide();
		$(".bg").hide()
	})
</script>
</body>
</html>