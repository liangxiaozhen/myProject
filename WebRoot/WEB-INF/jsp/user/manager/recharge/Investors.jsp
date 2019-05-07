<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>账户详情_你我贷会员中心</title>
	
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script charset="utf-8" src="${pageContext.request.contextPath}/resources/resource/Scripts/v.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/VisitorAPI-1.2.1-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/AppMeasurement-1.2.1-min.js"></script>
<style type="text/css">#YSF-BTN-HOLDER{position: fixed;max-width:70px;max-height:70px;right: 30px; bottom: 0px; cursor: pointer; overflow: visible; filter: alpha(opacity=100);opacity:1;z-index: 9990} #YSF-BTN-HOLDER:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-HOLDER img{ display: block;overflow: hidden; } #YSF-BTN-CIRCLE{display: none;position: absolute;right: -5px;top: -5px;width: auto;min-width: 12px;height: 20px;padding: 0 4px;background-color: #f00;font-size: 12px;line-height: 20px;color: #fff;text-align: center;white-space: nowrap;font-family: sans-serif;border-radius: 10px;z-index:1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-CIRCLE{top: -5px;} #YSF-BTN-BUBBLE{display: none;position: absolute;left: -274px;bottom:0px;width: 278px;height: 80px;box-sizing: border-box;padding: 14px 22px;filter: alpha(opacity=100);opacity:1;background: url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg2x.png) no-repeat;background:url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg.png)9; background-size: 278px 80px; z-index: 1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-BUBBLE{bottom:-6px;} #YSF-BTN-BUBBLE:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-CONTENT{height:45px;padding: 0;white-space: normal;word-break: break-all;text-align: left;font-size: 14px;line-height: 1.6;color: #222;overflow: hidden;z-index: 0;} #YSF-BTN-ARROW{ display: none; } #YSF-BTN-CLOSE{position: absolute; width:15px; height:15px;right: 4px;top: -3px; filter: alpha(opacity=90); opacity:.9; cursor: pointer; background: url(https://qiyukf.com/sdk//res/img/sdk/btn-close.png) no-repeat;z-index: 1} #YSF-BTN-CLOSE:hover{filter: alpha(opacity=100); opacity: 1;} #YSF-PANEL-CORPINFO.ysf-chat-layeropen{ width: 511px; height: 470px; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-CORPINFO{ position: fixed; bottom: 0px; right: 20px; width: 0; height: 0; z-index: 99999; } #YSF-PANEL-INFO.ysf-chat-layeropen{ width: 311px; height: 470px; filter: alpha(opacity=100);opacity:1; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-INFO{ position: fixed; bottom: 0px; right: 20px; width: 0px; height: 0px; filter: alpha(opacity=0);opacity:0;z-index: 99999;} #YSF-PANEL-INFO .u-btn{background-color: #F2A654} #YSF-CUSTOM-ENTRY{background-color: #F96868;} #YSF-CUSTOM-ENTRY-0{position: relative;bottom: 24px;width:auto;background-color: #F2A654;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-1{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 14px; box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-2{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 0;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-3{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-4{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-5{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 5px;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-6{position: relative;bottom: 0px;width:auto;background-color: #F2A654;border-radius:5px;border-bottom-left-radius: 0;border-bottom-right-radius: 0;} #YSF-CUSTOM-ENTRY-0 img{max-width: 300px;height:auto;} #YSF-CUSTOM-ENTRY-1 img{width:28px;height:auto;} #YSF-CUSTOM-ENTRY-2 img{width:58px;height:auto;} #YSF-CUSTOM-ENTRY-3 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-4 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-5 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-6 img{width:115px;height:auto;} #YSF-IFRAME-LAYER{ border:0; outline:none; } .ysf-online-invite-mask{z-index:10000;position:fixed;_position:absolute;top:0;left:0;right:0;bottom:0;width:100%;height:100%;background:#fff;-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";filter:alpha(opacity=0);opacity:0;} .ysf-online-invite-wrap{z-index:10001;position:fixed;_position:absolute;top:50%;left:50%;width:250px;} .ysf-online-invite{position:relative;top:-50%;left:-50%;cursor:pointer;} .ysf-online-invite img{display:block;width:250px;} .ysf-online-invite .text{position:absolute;top:-11px;left:0;right:0;overflow:hidden;margin: 36px 20px 0 67px;line-height:140%;color:#526069;font-size:14px;font-family:"Microsoft YaHei","微软雅黑",tahoma,arial,simsun,"宋体";text-align:left;white-space:normal;word-wrap:break-word;} .ysf-online-invite .close{position:absolute;top:-6px;right:-6px;width:32px;height:32px;background:url(https://qiyukf.com/sdk/res/img/invite-close.png) no-repeat;cursor:pointer;}</style></head>
<body>

	<!-- 公共头部 -->
	<include file="Index/header" />
	 <!--侧边  -->
	<include file="Message/mes" /> 
	<!-- 右侧 -->
	<div class="fl perCerterR  bor_r bor_l">
					
    <div class="fl perCerterR bor_l bor_r wid_w960">
		<!-- 网站公告 s  -->
		<div class="site_msg bor_b " id="msgDiv">
			<p class="fl pad_l10 pad_r10 pad_l20"><a href="javascript:;"><span class="nwd_icon nwd_icon_gonggao mar_r10">&nbsp;</span>
			<span id="msgInfo" class="vertical_middle">欢迎加入你我贷，您的"新客有礼"大礼包已送达:1212元红包可在投资指定产品时做现金抵用，快去账户中...</span></a></p>
			<!-- <ul class="fl">
				<li><a href="#">公告一</a></li>
				<li><a href="#">公告一</a></li>
			</ul> -->
			<span class="fr nwd_icon icon_24 nwd_icon_close ggClose mar_t10 mar_r10" style="top:-3px">&nbsp;</span>
		</div>
		<!-- 网站公告 e  -->
		<div class="pad_l20 pad_t30 pad_r20">
			<!-- 账户余额 -->
			<div class="pad_l30 clearfix pad_r20 pad_b10">
				<div class="fl moneyall clearfix">
					<div class="fs_16 pad_b10 title_manIndex"><span class="title_word fc_3">账户余额</span></div>
					<div class="pad_t10 clearfix">
						
							<p class="fl fc_6 fs_16 w320  dlDecimal">
								<em class="mar_r0 fs_28 fc_3 Numfont" id="accAmount"></em><i class="fs_16 mar_r5">200.00</i><em class="fs_14 fc_3"></em>
							</p>						
							
							<p class="fl">
								<a href="#" class="btn btn_bgf60 btn_size100 mar_r20" onclick="javascript:window.location.href='{:U('Message/step')}'">充值</a>
								<a href="#" class="btn btn_bgfff btn_size100" onclick="javascript:window.location.href='{:U('Message/carry')}'">提现</a>
							</p>
					</div>
					<div class="pad_t10 clearfix">
						<p class="fc_6 pad_b5  fl">可用余额：<i class="fc_6 mar_r20 Numfont fs_16" id="balanceAmount">200</i>冻结资金：<i class="fc_6 Numfont fs_16" id="freezeAmount">200</i>
					    </p>
					    <!-- <p  class="fc_green fl"><em class="nwd_icon_dun nwd_icon mar_r5"></em>账户资金安全由银行保障</p> -->
					</div>
							
				</div>
			</div>
			<div class="mya-line linehight0"><div class="ui-dbline icon_user icon_user_line "></div></div>
			<!-- 积分红包加息券体验 -->
			<div class="pad_l30 clearfix pad_r20 mya-profit">
				<p class="hongbaoCon pad_t10 pad_b10 clearfix">
						<input type="hidden" value='on' id="inteBidSwitch" name="inteBidSwitch"/>
						<span class="index_jifeng"><i class="nwd_icon icon_25 nwd_icon_jifen mar_r10">&nbsp;</i>积分<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/member/exchange.html" id="mCount"></a><a class="wordlink mar_l10" href="javascript:;" id="dhHref">如何获得？</a></span>
						<!-- <span><i class="nwd_icon icon_25 nwd_icon_hongbao mar_r10">&nbsp;</i>红包<a class="wordlink fc_9 mar_l10" href="#">如何获得红包？</a></span> -->
						<span class="index_hongbao"><i class="nwd_icon icon_25 nwd_icon_hongbao mar_r10">&nbsp;</i>红包<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/member/coupon.do" id="coupons">3254</a><a class="wordlink mar_l10" href="{:U('Message/coupon')}" id="couponsHref">使用</a></span>
						<span class="index_jiaxigquan"><i class="nwd_icon icon_25 nwd_icon_jiaxiquan mar_r10">&nbsp;</i>加息券<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="{:U('Activity/activity')}" id="plusCoupons"></a><a class="wordlink mar_l10" href="{:U('Activity/activity')}" id="plusCouponsHref">如何获得？</a></span>
						<span class="index_jianxiquan"><i class="nwd_icon icon_25 nwd_icon_jianxiquan mar_r10">&nbsp;</i>减息券<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="javascript:;" id="reduceCoupons"></a></span>
						<span class="index_tiyanjin"><i class="nwd_icon icon_25 nwd_icon_tiyanjin mar_r10">&nbsp;</i>体验金<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="javascript:;" id="tyjCount"></a><a class="wordlink mar_l15" href="javascript:;" id="tyjHref"></a></span>
				</p>
			</div>
				
			<!-- 操作列表 -->
				<div class="tab indexTab clearfix indexHandle ui-select-listBox" style="width:auto;">
					<ul class="tab_title clearfix" style="border-bottom:none;">
						<li class="fl ui-select-listBox-list  ui-select-listBox-list--now" style="border-bottom:none"><a class="pad_b10" href="javascript:;" onclick="javascript:;" value="transactionAttr">最近交易</a></li>
						<li class="fl ui-select-listBox-list" style="border-bottom:none "><a class="pad_b10" href="javascript:;" onclick="javascript:;"  value="rechargeAttr">充值记录</a></li>
						<li class="fl ui-select-listBox-list" style="border-bottom:none"><a class="pad_b10" href="javascript:;" onclick="javascript:;" value="withdrawAttr">提现记录</a></li>
						<li class="fl ui-select-listBox-list" style="border-bottom:none"><a class="pad_b10" href="javascript:;" onclick="javascript:;" value="refundRecord">收款记录</a></li>
						
					</ul>
					<div class="ui-select-listBox-line">
			            <div class="ui-select-listBox-l-blue" style="left: -1px; width: 80px;"></div>
			        </div>
					<a class="pad_l20  pad_b10 tradeRecord" href="{:U('Message/fundsLog')}"><em class="nwd_icon nwd_icon__list mar_r10"></em>资金记录</a>
				</div>
				<div class="tab_content clearfix">
					<div class="tab_box">
						<table class="table" cellspacing="0" cellpadding="0" id="transactionTable">
							<tr class="">
								<th class=""><span>时间</span></th>
								<th class="">类型</th>
								<th class="">金额</th>
								<th class="">状态</th>
								<th class="">操作</th>
							</tr>
							<tr class="tr_contrnt">
								<td class="Numfont table_timelog">2017-03-05 21:14:20</td>
								<td class="">月月升</td>
								<td class="">2000</td>
								<td class="">待审核</td>
								<td class="">操作</td>
							</tr>
						</table>
					</div>
					<div class="tab_box hide">
						<table class="table" cellspacing="0" cellpadding="0" id="crushTable">
							<tr class="">
								<th class="">充值时间</th>
								<th class="">充值金额</th>
								<th class="">手续费</th>
								<th class="">充值方式</th>
								<th class="">处理状态</th>
								<th class="">处理时间</th>
								<th class="">备注</th>
							</tr>
							<tr class="tr_contrnt">
								<td class="Numfont table_timelog">2017-03-05 21:14:20</td>
								<td class="Numfont">1.00</td>
								<td class="Numfont">0.00</td>
								<td>系统充值</td>
								<td>充值成功</td>
								<td class="Numfont table_timelog"></td>
								<td><span class="explain_td">无</span></td>
							</tr>
						</table>
					</div>
					<div class="tab_box hide">
						<table class="table" cellspacing="0" cellpadding="0" id="carryTable">
							<tr class="">
								<th class="">提现时间</th>
								<th class="">提现金额</th>
								<th class="">手续费</th>
								<th class="">处理状态</th>
								<th class="">处理时间</th>
							</tr>
							<tr char="tr_contrnt">
								<td class="Numfont table_timelog">2017-03-05 21:14:20</td>
								<td class="Numfont">1.00</td>
								<td class="Numfont">0.00</td>
								<td>提现成功</td>
								<td class="Numfont table_timelog">2017-03-05 21:14:20</td>
							</tr>
						</table>
					</div>
					<div class="tab_box hide">
						<table class="table" cellspacing="0" cellpadding="0" id="refundRecord">
							<tr class="">
								<th class="">收款时间</th>
								<th class="">收款类型</th>
								<th class="">收款金额</th>
								<th class="">备注</th>
							</tr>
							<tr class="tr_contrnt">
								<td class="Numfont table_timelog">2017-03-05 21:14:20</td>
								<td class="">月月升</td>
								<td class="">222</td>
								<td class="">完成</td>
							</tr>
						</table>
					</div>
					
				</div>
				<div class="mya-line mar_t20"><div class="ui-dbline icon_user icon_user_line "></div></div>
			</div>
		</div>
	</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/new.js"></script>


</body>
</html>