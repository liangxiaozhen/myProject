<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<!-- saved from url=(0047)https://member.niwodai.com/member/exchange.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户管理-我的积分</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script>
<link href="${pageContext.request.contextPath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${pageContext.request.contextPath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${pageContext.request.contextPath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/resource/Css/new.css" rel="stylesheet" type="text/css">	
<link href="${pageContext.request.contextPath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />	
<style type="text/css">#YSF-BTN-HOLDER{position: fixed;max-width:70px;max-height:70px;right: 30px; bottom: 0px; cursor: pointer; overflow: visible; filter: alpha(opacity=100);opacity:1;z-index: 9990} #YSF-BTN-HOLDER:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-HOLDER img{ display: block;overflow: hidden; } #YSF-BTN-CIRCLE{display: none;position: absolute;right: -5px;top: -5px;width: auto;min-width: 12px;height: 20px;padding: 0 4px;background-color: #f00;font-size: 12px;line-height: 20px;color: #fff;text-align: center;white-space: nowrap;font-family: sans-serif;border-radius: 10px;z-index:1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-CIRCLE{top: -5px;} #YSF-BTN-BUBBLE{display: none;position: absolute;left: -274px;bottom:0px;width: 278px;height: 80px;box-sizing: border-box;padding: 14px 22px;filter: alpha(opacity=100);opacity:1;background: url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg2x.png) no-repeat;background:url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg.png)9; background-size: 278px 80px; z-index: 1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-BUBBLE{bottom:-6px;} #YSF-BTN-BUBBLE:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-CONTENT{height:45px;padding: 0;white-space: normal;word-break: break-all;text-align: left;font-size: 14px;line-height: 1.6;color: #222;overflow: hidden;z-index: 0;} #YSF-BTN-ARROW{ display: none; } #YSF-BTN-CLOSE{position: absolute; width:15px; height:15px;right: 4px;top: -3px; filter: alpha(opacity=90); opacity:.9; cursor: pointer; background: url(https://qiyukf.com/sdk//res/img/sdk/btn-close.png) no-repeat;z-index: 1} #YSF-BTN-CLOSE:hover{filter: alpha(opacity=100); opacity: 1;} #YSF-PANEL-CORPINFO.ysf-chat-layeropen{ width: 511px; height: 470px; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-CORPINFO{ position: fixed; bottom: 0px; right: 20px; width: 0; height: 0; z-index: 99999; } #YSF-PANEL-INFO.ysf-chat-layeropen{ width: 311px; height: 470px; filter: alpha(opacity=100);opacity:1; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-INFO{ position: fixed; bottom: 0px; right: 20px; width: 0px; height: 0px; filter: alpha(opacity=0);opacity:0;z-index: 99999;} #YSF-PANEL-INFO .u-btn{background-color: #F2A654} #YSF-CUSTOM-ENTRY{background-color: #F96868;} #YSF-CUSTOM-ENTRY-0{position: relative;bottom: 24px;width:auto;background-color: #F2A654;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-1{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 14px; box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-2{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 0;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-3{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-4{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-5{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 5px;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-6{position: relative;bottom: 0px;width:auto;background-color: #F2A654;border-radius:5px;border-bottom-left-radius: 0;border-bottom-right-radius: 0;} #YSF-CUSTOM-ENTRY-0 img{max-width: 300px;height:auto;} #YSF-CUSTOM-ENTRY-1 img{width:28px;height:auto;} #YSF-CUSTOM-ENTRY-2 img{width:58px;height:auto;} #YSF-CUSTOM-ENTRY-3 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-4 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-5 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-6 img{width:115px;height:auto;} #YSF-IFRAME-LAYER{ border:0; outline:none; } .ysf-online-invite-mask{z-index:10000;position:fixed;_position:absolute;top:0;left:0;right:0;bottom:0;width:100%;height:100%;background:#fff;-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";filter:alpha(opacity=0);opacity:0;} .ysf-online-invite-wrap{z-index:10001;position:fixed;_position:absolute;top:50%;left:50%;width:250px;} .ysf-online-invite{position:relative;top:-50%;left:-50%;cursor:pointer;} .ysf-online-invite img{display:block;width:250px;} .ysf-online-invite .text{position:absolute;top:-11px;left:0;right:0;overflow:hidden;margin: 36px 20px 0 67px;line-height:140%;color:#526069;font-size:14px;font-family:"Microsoft YaHei","微软雅黑",tahoma,arial,simsun,"宋体";text-align:left;white-space:normal;word-wrap:break-word;} .ysf-online-invite .close{position:absolute;top:-6px;right:-6px;width:32px;height:32px;background:url(https://qiyukf.com/sdk/res/img/invite-close.png) no-repeat;cursor:pointer;}
	.yihang{
		display: inline-block;
	}

	.yihang  .fs_28.Numfont {
 		 width: 70px;
	}
</style></head>

	<body>
		<!-- 头部-->
		<!-- <include file="Index/header" /> -->
		<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
		<!--左侧-->
		<!-- <include file="Message/mes" /> -->
		<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
		<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
		<!--右侧-->
			<div class="fl perCerterR bor_l">
					<!-- 我的积分 -->
						<div class="fl pad_30 wid_w900 clearfix">
							<div class="loadDiv fc_9 clearfix">
								<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_6">我的积分</span>
								<a href="{:U('Help/helps')}" target="_blank" class="fc_blue fr fs_14">积分说明</a>  
							</div>
							<div class="pad_b10 pad_t30">
								<div class="pad_l20">
									<ul class="myintegral clearfix">
										<li>
											<input type="hidden" name="grade" id="grade" value="0">
											<input type="hidden" name="money" id="money" value="31">
											<div>
												<p class="fc_9 yihang">用户系统积分</p>
												<p class="fc_f60 yihang"><em class="fs_28 Numfont" id="emgrade">${ua.bonuspoints}</em></p>
												<p class="fc_9 yihang" style="margin-left: 50px;">用户交易积分</p>
												<p class="fc_f60 yihang"><em class="fs_28 Numfont" id="emgrade">${ua.tradePoints}</em></p>
											</div>
											<div>
												<p class="fc_9 yihang">可用交易积分</p>
												<p class="fc_f60 yihang"><em class="fs_28 Numfont" id="emgrade">${ua.avlTradePoints}</em></p>
												<p class="fc_9 yihang" style="margin-left: 50px;">冻结交易积分</p>
												<p class="fc_f60 yihang"><em class="fs_28 Numfont" id="emgrade">${ua.freezeTradePoints}</em></p>
											</div>
											<a href="{:U('Help/helps')}" target="_blank" class="fs_14 fc_blue  mar_l30">(如何赚取更多积分)</a>
										</li>
										<li>
											<p class="fc_9">论坛金钱</p>
											<p class="fc_3"><em class="fs_28 Numfont" id="canUseMoney">31</em><span class="fs_14 fc_9  mar_l20" style="display:inline-block;">(可兑换<span id="canExchange">31</span>积分)</span><a href="{:U('Message/coupon')}" onclick="exchange();" class="fs_14 fc_blue  mar_l10">立即兑换</a><span id="exchangeOk" class="prompt_1 remind_1 mar_l10" style="display:none;"><i class="ico_all size15 img_icon s_dui mr_10"></i></span></p>
										</li>
									</ul>
									
								</div>
							</div>
							<div class="fl pad_b10 pad_t20 tab indexTab clearfix indexHandle ui-select-listBox">
									<ul class="tab_title clearfix">
										<li class="fl ui-select-listBox-list ui-select-listBox-list--now active" value="1"><a onclick="javascript:;" href="javascript:void(0);" class="pad_b10">兑换优惠券</a></li>
										
										<li class="fl ui-select-listBox-list " value="2"><a href="javascript:;" class="pad_b10">积分记录</a></li>
									</ul>
									<div class="ui-select-listBox-line">
							            <div class="ui-select-listBox-l-blue" style="left: 20px; width: 83.3333px;"></div>
							        </div>
							</div>
							<div class="tab_content clearfix">
								<div class="tab_box box box1" style="display: block;">
									<ul class="fl newgiftCon bor_l bor_t mar_t10 clearfix">
										<!--5-->
									  	<li class="fl bor_b bor_r">
									  		<dl class="fl clearfix">
									  			<dd class="fl pad_l20 pad_t20"><span class="icon_integral icon_integral_jf1"></span></dd>
									  			<dt class="fr pad_r20 pad_t20 wid_w308">
									  				<p class="fc_3 fs_16">5元投资抵用券 <span class="fc_9 fs_14 fr">有效期：<em class="fc_f60">自兑换日起3个月</em></span></p>
									  				<p class="fc_6">单笔投资年化金额满500元可使用，详见账户中心-抵用券说明</p>
									  				<div class="giftAmt mar_t10 clearfix"><a class="fs_18 fc_9" href="javascript:;" onclick="minus(1);">-</a><input type="text" id="num1" value="1" class="input" onkeyup="chkPrice(this);change(this,1)" maxlength="3"><a class="fs_18 fc_9" href="javascript:;" onclick="add(1);">+</a>
													</div>
													<p class="fc_3 mar_t5"><span class="fc_9">需消耗： </span><span class="Numfont fc_f60" id="useIntegral1">500</span> 积分</p>
									  				<p class="pad_b20 pad_t10"><a href="{:U('Message/coupon')}" class="btn btn_bgf60 btn_size100" onclick="exchangeSave(1);">立即兑换 </a></p>
									  			</dt>
									  		</dl>
									  	</li>
									  	<!--5 end-->
									  	<!--10-->
									  	<li class="fl bor_b bor_r">
									  		<dl class="fl clearfix">
									  			<dd class="fl pad_l20 pad_t20"><span class="icon_integral icon_integral_jf2"></span></dd>
									  			<dt class="fr pad_r20 pad_t20 wid_w308">
									  				<p class="fc_3 fs_16">10元投资抵用券 <span class="fc_9 fs_14 fr">有效期：<em class="fc_f60">自兑换日起3个月</em></span></p>
									  				<p class="fc_6">单笔投资年化金额满1000元可使用，详见账户中心-抵用券说明</p>
									  				<div class="giftAmt mar_t10 clearfix"><a class="fs_18 fc_9" href="javascript:;" onclick="minus(2);">-</a><input type="text" id="num2" value="1" class="input" onkeyup="chkPrice(this);change(this,2)" maxlength="3"><a class="fs_18 fc_9" href="javascript:;" onclick="add(2);">+</a>
													</div>
													<p class="fc_3 mar_t5"><span class="fc_9">需消耗： </span><span class="Numfont fc_f60" id="useIntegral2">1000</span> 积分</p>
									  				<p class="pad_b20 pad_t10"><a href="{:U('Message/coupon')}" class="btn btn_bgf60 btn_size100" onclick="exchangeSave(2);">立即兑换 </a></p>
									  			</dt>
									  		</dl>
									  	</li>
									  	<!--10 end-->
									  	<!--20-->
									  	<li class="fl bor_b bor_r">
									  		<dl class="fl clearfix">
									  			<dd class="fl pad_l20 pad_t20"><span class="icon_integral icon_integral_jf3"></span></dd>
									  			<dt class="fr pad_r20 pad_t20 wid_w308">
									  				<p class="fc_3 fs_16">20元投资抵用券 <span class="fc_9 fs_14 fr">有效期：<em class="fc_f60">自兑换日起3个月</em></span></p>
									  				<p class="fc_6">单笔投资年化金额满2000元可使用，详见账户中心-抵用券说明</p>
									  				<div class="giftAmt mar_t10 clearfix"><a class="fs_18 fc_9" href="javascript:;" onclick="minus(3);">-</a><input type="text" id="num3" value="1" class="input" onkeyup="chkPrice(this);change(this,3)" maxlength="3"><a class="fs_18 fc_9" href="javascript:;" onclick="add(3);">+</a>
													</div>
													<p class="fc_3 mar_t5"><span class="fc_9">需消耗： </span><span class="Numfont fc_f60" id="useIntegral3">2000</span> 积分</p>
									  				<p class="pad_b20 pad_t10"><a href="{:U('Message/coupon')}" class="btn btn_bgf60 btn_size100" onclick="exchangeSave(3);">立即兑换 </a></p>
									  			</dt>
									  		</dl>
									  	</li>
									  	<!--20 end-->
									  	<!--50-->
									  	<li class="fl bor_b bor_r">
									  		<dl class="fl clearfix">
									  			<dd class="fl pad_l20 pad_t20"><span class="icon_integral icon_integral_jf4"></span></dd>
									  			<dt class="fr pad_r20 pad_t20 wid_w308">
									  				<p class="fc_3 fs_16">50元投资抵用券 <span class="fc_9 fs_14 fr">有效期：<em class="fc_f60">自兑换日起3个月</em></span></p>
									  				<p class="fc_6">单笔投资年化金额满5000元可使用，详见账户中心-抵用券说明</p>
									  				<div class="giftAmt mar_t10 clearfix"><a class="fs_18 fc_9" href="javascript:;" onclick="minus(4);">-</a><input type="text" id="num4" value="1" class="input" onkeyup="chkPrice(this);change(this,4)" maxlength="3"><a class="fs_18 fc_9" href="javascript:;" onclick="add(4);">+</a>
													</div>
													<p class="fc_3 mar_t5"><span class="fc_9">需消耗： </span><span class="Numfont fc_f60" id="useIntegral4">5000</span> 积分</p>
									  				<p class="pad_b20 pad_t10"><a href="javascript:;" class="btn btn_bgf60 btn_size100" onclick="exchangeSave(4);">立即兑换 </a></p>
									  			</dt>
									  		</dl>
									  	</li>
									  	<!--50 end-->
									  	<!--100-->
									  	<li class="fl bor_b bor_r">
									  		<dl class="fl clearfix">
									  			<dd class="fl pad_l20 pad_t20"><span class="icon_integral icon_integral_jf5"></span></dd>
									  			<dt class="fr pad_r20 pad_t20 wid_w308">
									  				<p class="fc_3 fs_16">100元投资抵用券 <span class="fc_9 fs_14 fr">有效期：<em class="fc_f60">自兑换日起3个月</em></span></p>
									  				<p class="fc_6">单笔投资年化金额满10000元可使用，详见账户中心-抵用券说明</p>
									  				<div class="giftAmt mar_t10 clearfix"><a class="fs_18 fc_9" href="javascript:;" onclick="minus(7);">-</a><input type="text" id="num7" value="1" class="input" onkeyup="chkPrice(this);change(this,7)" maxlength="3"><a class="fs_18 fc_9" href="javascript:;" onclick="add(7);">+</a>
													</div>
													<p class="fc_3 mar_t5"><span class="fc_9">需消耗： </span><span class="Numfont fc_f60" id="useIntegral7">10000</span> 积分</p>
									  				<p class="pad_b20 pad_t10"><a href="javascript:;" class="btn btn_bgf60 btn_size100" onclick="exchangeSave(7);">立即兑换 </a></p>
									  			</dt>
									  		</dl>
									  	</li>
									  	
									  </ul>
								</div>								
								<div class="tab_box hide  box box2" style="display: none;">
									<form action="" id="integralView" name="integralView" method="post" class="nwd-formUi" autocomplete="off">
									<div class="searchCon pad_l20">
										入账时间：
										<input type="text" class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime hasDatepicker" id="startdate" name="startdate" readonly="readonly">
										-
										<input type="text" class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime hasDatepicker" id="enddate" name="enddate" readonly="readonly">
										<div class="mar_l30 mar_t20 mar_b20">
										<input type="hidden" name="attrCode" id="attrCode" value="0">
										类型：
											<a href="javascript:void(0)" id="type" value="0" class="fc_6 mar_l0 mar_r5 active">全部</a>
											<a href="javascript:void(0)" id="type" value="1" class="fc_6 mar_l0 mar_r5">投资</a>
											<a href="javascript:void(0)" id="type" value="2" class="fc_6 mar_l0 mar_r5">论坛金钱兑换</a>
											<a href="javascript:void(0)" id="type" value="3" class="fc_6 mar_l0 mar_r5">流标补偿</a>
											<a href="javascript:void(0)" id="type" value="4" class="fc_6 mar_l0 mar_r5">兑换红包</a>
											<a href="javascript:void(0)" id="type" value="5" class="fc_6 mar_l0 mar_r5">兑换提现劵</a>
											<a href="javascript:void(0)" id="type" value="6" class="fc_6 mar_l0 mar_r5">任务</a>
											<a href="javascript:void(0)" id="type" value="7" class="fc_6 mar_l0 mar_r5">抽奖</a>
											<a href="javascript:void(0)" id="type" value="8" class="fc_6 mar_l0 mar_r5">兑换流量</a>
											<a href="javascript:void(0)" id="type" value="9" class="fc_6 mar_l0 mar_r5">活动</a>
										</div>
									</div>
									</form>
									<span class="fr">仅展示3个月以内的记录</span>
									
									<!-- <table cellspacing="0" cellpadding="0" class="table fc_9 bor_t mar_t5">
										<tbody>
											<tr>
												<th class="fc_3">时间</th>
												<th class="fc_3">类型</th>
												<th class="fc_3">积分数</th>
												<th class="fc_3">说明</th>
												<th class="fc_3">操作</th>
											</tr>
											<tr>
												<td class="Numfont">2017-03-05</td>
												<td>任务</td>
												<td class="Numfont fc_green">100</td>
												<td>
												<span class="explain_td" title="首次绑定银行卡，送100积分">首次绑定银行卡，送100积分</span>
												</td>
												<td>32545</td>
											</tr>
											<tr>
												<td class="Numfont">2017-03-05</td>
												<td>任务</td>
												<td class="Numfont fc_green">100</td>
												<td>
	                                    			<span class="explain_td" title="实名认证送100积分">实名认证送100积分</span>
												</td>
												<td>7868</td>
											</tr>
											<tr>
												<td class="Numfont">2017-03-05</td>
												<td>论坛金钱兑换</td>
												<td class="Numfont fc_green">24</td>
												<td>
	                                    			<span class="explain_td" title="论坛抵扣金钱24">论坛抵扣金钱24</span>
												</td>
												<td>456</td>
											</tr>
										</tbody> 
									</table>-->
									
									<!-- <div class="nwd_fy mar_t30">
										分页 str
										<div class="pageout">
											<span class="first"><i></i>上一页</span>
											<span class="active">1</span>
											<span class="last">下一页<i></i></span>
										</div>
										分页 end
										<input type="hidden" value="1" id="curPage">
									</div> -->
                         			
									<div id="integralRecord"></div>
								</div>
								</div>
							</div>
							
						</div>
						</div>
						</div>
				</div>
			</div>
		</div>
	
<!-- 尾部 -->
<%-- <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/resource/Scripts/mobiscroll_date.js" charset="gb2312"></script> 
<script src="${pageContext.request.contextPath}/resources/resource/Scripts/mobiscroll.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
$(function () {
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default1 = {
		theme: 'android-ics light', //皮肤样式
		display: 'modal', //显示方式 
		mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
		startYear: currYear - 50, //开始年份
		endYear: currYear + 10 //结束年份
	};

	$("#startdate,#enddate").mobiscroll($.extend(opt['date'], opt['default1']));
	
});

//判断结束时间是否小于开始时间
$('.dwb-s .dwb').live("click",function (){
	var onetome=$("#startdate").val();
	var endtime=$("#enddate").val();
	if(onetome!='' && endtime!=''){
		if(endtime<onetome){
			$("#enddate").val('');
			alert('结束时间不能小于开始时间');
		}
	}
})
//筛选
$(document).on("click",".mar_l30.mar_t20.mar_b20 a",function(){
	$(".mar_l30.mar_t20.mar_b20 a").removeClass("active");
	$(this).addClass("active");
})

	$(document).ready(function(){
		$(".box2").hide();
	    $(".tab_title.clearfix  li").click(function(){
	        $(".tab_title.clearfix  li").removeClass("active");
	        var num = $(this).val();
	        if(num==2){
	        	//积分记录
	        	$.ajax({
	        		url:"${pageContext.request.contextPath}/user/activity/integralRecode.action",
	        		async:false,
	        		success:function(data){
	        			$("#integralRecord").html(data);
	        		}
	        	});
	        	
	        }
	        
	        $(this).addClass("active");
	        $(".box").hide();
	        $(".box"+num).show();
	    });
	    
	    
	    $("#startdate,#enddate").change(function(){
	    	
	    	var startdate = $("#startdate").val();
	    	//alert("开始时间： "+startdate);
	    	var enddate = $("#enddate").val();
	    	//alert("结束时间： "+enddate);
	    	
	    	if(startdate && enddate){
	    		$.ajax({
	    			url:"${pageContext.request.contextPath}/user/activity/integralRecodePeriod.action",
	    			async:false,
	    			data:{
	    				"startdate":startdate,
	    				"enddate":enddate
	    			},
	    			success:function(data){
	    				//alert("ok");
	    				$("#integralRecord").html(data);
	    			}
	    		});
	    	}
	    });
	    	    
	})

	
	
</script>		
		<!-- 我的积分弹窗与线上一样的 start -->
		<div class="plusBankBg" style="height: 1822px;"></div>
		<div id="openMsg" class="plusBank mini page53">
			<div class="topper clearfix">
		    	<span id="msgTitle" class="fl fs_16">消息</span>
		        <a id="msgCloseAll" onclick="allClose()" class="fr nwd_icon plus_c"></a>
		    </div>
			<div id="changyong" class="middle">
					<div id="msgContent" class="content"><i class="icon_base icon_20 icon_base_tipnote16 vertical_middle mar_r10"></i><span class="fs_14">积分不足兑换失败！</span></div>
			        <div class="btnbox"><a id="msgClose" class="btn btn_36c btn_size120" onclick="allClose()">确认</a></div>
			</div>
			<div style="display:none;" id="chenggong" class="clearfix middle">
		            	<p style="text-align:center;height:50px;line-height:50px;font-size:18px;">恭喜您兑换成功</p>
		               	<p style="text-align:center;height:50px;">
			                <span>
				                <a onclick="touzhi();" class="btn btn_36c btn_size120" href="javascript:void(0)" id="gktouzhi">赶快去投资</a>&nbsp;
				                <a onclick="allClose();" class="btn btn_borblue btn_size120 mar_l20" href="javascript:void(0)">继续兑换</a></span>
			                <span>
						</span></p>
						<p style="text-align:center;height:50px;">
							您可能需要：<a onclick="openNew();" class="blue" href="javascript:void(0)">查看兑换记录</a>
						</p>
		    </div>
		</div>
	
		
	
</body>
</html>