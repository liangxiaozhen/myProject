<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0049)https://member.niwodai.com/member/rechargeStep.do -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>账户详情_你我贷会员中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${pageContext.request.contextPath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${pageContext.request.contextPath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${pageContext.request.contextPath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">	
<link href="${pageContext.request.contextPath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/resource/Css/new.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/userRecharge/recharge.js"></script>
<script type="text/javascript">
	var basepath = "${pageContext.request.contextPath}";
</script>
<style type="text/css">#YSF-BTN-HOLDER{position: fixed;max-width:70px;max-height:70px;right: 30px; bottom: 0px; cursor: pointer; overflow: visible; filter: alpha(opacity=100);opacity:1;z-index: 9990} #YSF-BTN-HOLDER:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-HOLDER img{ display: block;overflow: hidden; } #YSF-BTN-CIRCLE{display: none;position: absolute;right: -5px;top: -5px;width: auto;min-width: 12px;height: 20px;padding: 0 4px;background-color: #f00;font-size: 12px;line-height: 20px;color: #fff;text-align: center;white-space: nowrap;font-family: sans-serif;border-radius: 10px;z-index:1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-CIRCLE{top: -5px;} #YSF-BTN-BUBBLE{display: none;position: absolute;left: -274px;bottom:0px;width: 278px;height: 80px;box-sizing: border-box;padding: 14px 22px;filter: alpha(opacity=100);opacity:1;background: url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg2x.png) no-repeat;background:url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg.png)9; background-size: 278px 80px; z-index: 1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-BUBBLE{bottom:-6px;} #YSF-BTN-BUBBLE:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-CONTENT{height:45px;padding: 0;white-space: normal;word-break: break-all;text-align: left;font-size: 14px;line-height: 1.6;color: #222;overflow: hidden;z-index: 0;} #YSF-BTN-ARROW{ display: none; } #YSF-BTN-CLOSE{position: absolute; width:15px; height:15px;right: 4px;top: -3px; filter: alpha(opacity=90); opacity:.9; cursor: pointer; background: url(https://qiyukf.com/sdk//res/img/sdk/btn-close.png) no-repeat;z-index: 1} #YSF-BTN-CLOSE:hover{filter: alpha(opacity=100); opacity: 1;} #YSF-PANEL-CORPINFO.ysf-chat-layeropen{ width: 511px; height: 470px; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-CORPINFO{ position: fixed; bottom: 0px; right: 20px; width: 0; height: 0; z-index: 99999; } #YSF-PANEL-INFO.ysf-chat-layeropen{ width: 311px; height: 470px; filter: alpha(opacity=100);opacity:1; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-INFO{ position: fixed; bottom: 0px; right: 20px; width: 0px; height: 0px; filter: alpha(opacity=0);opacity:0;z-index: 99999;} #YSF-PANEL-INFO .u-btn{background-color: #F2A654} #YSF-CUSTOM-ENTRY{background-color: #F96868;} #YSF-CUSTOM-ENTRY-0{position: relative;bottom: 24px;width:auto;background-color: #F2A654;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-1{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 14px; box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-2{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 0;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-3{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-4{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-5{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 5px;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-6{position: relative;bottom: 0px;width:auto;background-color: #F2A654;border-radius:5px;border-bottom-left-radius: 0;border-bottom-right-radius: 0;} #YSF-CUSTOM-ENTRY-0 img{max-width: 300px;height:auto;} #YSF-CUSTOM-ENTRY-1 img{width:28px;height:auto;} #YSF-CUSTOM-ENTRY-2 img{width:58px;height:auto;} #YSF-CUSTOM-ENTRY-3 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-4 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-5 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-6 img{width:115px;height:auto;} #YSF-IFRAME-LAYER{ border:0; outline:none; } .ysf-online-invite-mask{z-index:10000;position:fixed;_position:absolute;top:0;left:0;right:0;bottom:0;width:100%;height:100%;background:#fff;-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";filter:alpha(opacity=0);opacity:0;} .ysf-online-invite-wrap{z-index:10001;position:fixed;_position:absolute;top:50%;left:50%;width:250px;} .ysf-online-invite{position:relative;top:-50%;left:-50%;cursor:pointer;} .ysf-online-invite img{display:block;width:250px;} .ysf-online-invite .text{position:absolute;top:-11px;left:0;right:0;overflow:hidden;margin: 36px 20px 0 67px;line-height:140%;color:#526069;font-size:14px;font-family:"Microsoft YaHei","微软雅黑",tahoma,arial,simsun,"宋体";text-align:left;white-space:normal;word-wrap:break-word;} .ysf-online-invite .close{position:absolute;top:-6px;right:-6px;width:32px;height:32px;background:url(https://qiyukf.com/sdk/res/img/invite-close.png) no-repeat;cursor:pointer;}
#bankRemark .tipBox.inline_block.vertical_middle:hover .tips.toortipBox-con.r_tip-con.j_eye-hide{opacity:1!important;visibility:inherit;!important}
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
			<div class="fl pad_30 wid_w900 clearfix" style="position: absolute;">
				<div class="loadDiv fc_9 clearfix">
					<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要充值</span>
				</div>
				<div class="mainbox">
			        <div class="pubTab">
			            <h2>账户充值</h2>
			            <ul class="title" id="tits">
			                <li class="cur" data-value="recharge" value="1">线上充值</li>
			                <li data-value="recharge_offline" value="2">线下充值</li>
			                <li data-value="recharge_record" value="3">充值记录</li>
			            </ul>
			            <form action="" method="post" id="trust_recharge" target="_blank">
			                <div class="userRecharge box box1" id="con_1 ">
			                    <div class="recharge_amount">
			                        <p class="amount_p1">充值金额：
			                            <!-- <input type="text" onkeyup="value=value.replace(/[^0-9Xx|.]/g,'')" id="money" name="money" value="">元 -->
			                            <input type="text" id="money" name="money" value="" onblur="upperCase()">元
			                            <span id="recharge_msg" style="color:red;margin-left:5px;"></span>
			                        </p>
			                        <p class="amount_p2">
			                            <span class="sp_1">当前可用余额<i>${df.format(uc.balance)}</i>元</span>
			                            <span class="spa_2">充值后余额<i class="cr_i">0</i>元</span>
			                        </p>
			                        <p class="amount_p3" style="padding-top:35px;">
			                            <a id="recharge_sub" href="javascript:void(0);" class="a_1" accountnumber="" auto="" autorepay="" sere="">确定充值</a>
			                            <a href="/?user&amp;m=trust/recharge" class="a_2">取消充值</a>
			                        </p>
			                        <p class="amount_p4"><i></i>乾多多是由央行颁发牌照，由银监局监管的第三方在线支付机构。点击"确认充值"后跳转到第三方乾多多支付页面进行下一步操作。</p>
			                        <div class="clear" style="border-bottom: 1px dashed #e5e7eb"></div>
			                        <p class="amount_p5">温馨提示：</p>
			                        <ul class="amount_ul">
			                            <li>·&nbsp;&nbsp;&nbsp;如进行快捷支付充值，银行卡需开通在线支付功能。</li>
			                            <li>·&nbsp;&nbsp;&nbsp;您的充值将会在10分钟内到账，请耐心等候。</li>
			                            <li>·&nbsp;&nbsp;&nbsp;充值额度根据不同银行的不同业务有不同的限制，具体可以参考<a href="javascript:void(0);" class="a_quota">银行充值额度</a>。</li>
			                            <li>
			                                ·&nbsp;&nbsp;&nbsp;如有问题，请与我们联系
			                                <a>400-692-2218</a>，或者咨询在线客服
			                                <a onclick="javascript:window.open(', '_blank', 'height=544, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');" style="cursor:pointer;" class="qq_kf"></a>
			                            </li>
			                        </ul>
			                    </div>
			                </div>
			               	<div class="history box box2" id="con_2" style="display:none;">
				            	<div class="offline_title">
				                </div>
				                <ul class="offline_step">
				                	<li><span><b>1.</b>线下汇款</span>用户通过网银转账到"<b>双乾客户备付金银行账户</b>"；</li>
				                    <li><span><b>2.</b>提交汇单</span>登录壹宝贷平台"我的账户"点击"账户充值"– "线下充值"，填写线下充值金额点击提交汇单，跳转至乾多多填写转入的<b>双乾备付金银行账号、汇款行信息和汇款凭证</b>；</li>
				                    <li class="last"><span><b>3.</b>审核到账</span>待乾多多财务审核通过后，该笔充值资金将会划账至用户乾多多账户，信息同步后即成功完成整个线下充值流程。</li>
				                    <div class="clear"></div>
				                </ul>
			                	<table width="100%" class="bankstable">
			                  <tbody><tr>
			                    <th colspan="4" scope="col">双乾支付备付金账户</th>
			                  </tr>
			                  <tr>
			                    <td class="tdb">乾多多收款账户</td>
			                    <td class="tdb">开户行</td>
			                    <td class="tdb">户名</td>
			                    <td class="tdb">账号</td>
			                  </tr>
			                  <tr>
			                    <td align="center">民生银行</td>
			                    <td align="center">民生银行苏州工业园区支行</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="626230922" size="10" maxlength="10" id="kh1" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh1'))" value="复制" class="khbtn"></td>
			                  </tr>
			                  <tr>
			                    <td align="center">中国银行</td>
			                    <td align="center">中国银行苏州工业园区支行</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="548258434692" size="10" maxlength="10" id="kh2" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh2'))" value="复制" class="khbtn"></td>
			                  </tr>
			                  <tr>
			                    <td align="center">招商银行</td>
			                    <td align="center">招商银行苏州干将路支行</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="512904245710203" size="10" maxlength="10" id="kh3" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh3'))" value="复制" class="khbtn"></td>
			                  </tr>
			                  <tr>
			                    <td align="center">工商银行</td>
			                    <td align="center">工商银行苏州道前支行</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="1102020229000859780" size="10" maxlength="10" id="kh4" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh4'))" value="复制" class="khbtn"></td>
			                  </tr>
			                  <tr>
			                    <td align="center">中信银行</td>
			                    <td align="center">中信银行苏州金鸡湖支行</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="7324810182600031111" size="10" maxlength="10" id="kh5" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh5'))" value="复制" class="khbtn"></td>
			                  </tr>
			                  <tr>
			                    <td align="center">平安银行</td>
			                    <td align="center">平安银行苏州分行营业部</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="11014724076009" size="10" maxlength="10" id="kh6" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh6'))" value="复制" class="khbtn"></td>
			                  </tr>
			                  <tr>
			                    <td align="center">光大银行</td>
			                    <td align="center">光大银行苏州分行</td>
			                    <td>双乾网络支付有限公司客户备付金</td>
			                    <td><input type="text" value="37010188000419581" size="10" maxlength="10" id="kh7" class="khtxt"><input type="button" onclick="copyUrl2(document.getElementById('kh7'))" value="复制" class="khbtn"></td>
			                  </tr>
			                	</tbody></table>
				                <div class="offline_title">
				                	<span>提交汇单信息</span>
				                </div>
				                <ul class="offline_submit">
				                    <form action="/?user&amp;m=trust/offline_recharge/new" method="post" id="trust_offrecharge" target="_blank">
				                	<li><span>线下充值金额：</span><input type="text" id="offmoney" name="money" onkeyup="value=value.replace(/[^0-9Xx|.]/g,'')">元   <span id="recharge_msg2" style="width: 150px;color:red;margin-left:5px;font-size: 12px;"></span></li>
				                    <input type="hidden" name="rechargetype" value="2">
				                    <li><span>充值手续费：</span><em id="offfee">0.00</em>元<i title="如线下充值金额小于2万元，每笔需收取用户20元手续费。"></i></li>
				                    <li><span>实际到账金额：</span><em id="offbalance">0.00</em>元</li>
				                    <li><span>&nbsp;</span><button id="offrecharge_sub" href="javascript:void(0);" accountnumber="" auto="" autorepay="" sere="">提交汇单</button></li>
				                    </form>
				                </ul>
								<p class="amount_p5">温馨提示：</p>
			                    <ul class="amount_ul">
			                        <li>1.线下充值金额每笔需≥20000元，如充值金额≥20000元，不收取手续费，如低于20000元，每笔收取20元手续费。</li>
			                        <li>2.线下充值审核到账以乾多多处理时间为准（乾多多财务工作日审核时间9:00-22:30）</li>
			                    </ul>
			            	</div>
			            	<div class="history box box3" id="con_3 " style="display:none;">
				                <div class="fifter">
				                    <div class="left">起止日期</div>
				                    <div class="right">
				                        <ul class="time">
				                            <li data-value="all" class="on"><a href="javascript:void(0);">全部</a></li>
				                            <li data-value="today"><a href="javascript:void(0);">今天</a></li>
				                            <li data-value="week"><a href="javascript:void(0);">最近一周</a></li>
				                            <li data-value="oneMonth"><a href="javascript:void(0);">1个月</a></li>
				                            <li data-value="threeMonth"><a href="javascript:void(0);">3个月</a></li>
				                            <li data-value="sixMonth"><a href="javascript:void(0);">半年</a></li> 
				                        </ul>
				                        <div class="auto">
				                            <input type="text" name="starttime" id="starttime" class="date_picker" placeholder="起始日期" value="">-
				                            <input type="text" name="endtime" id="endtime" class="date_picker" placeholder="截止日期" value="">
				                            <button type="button" id="search" value="search">搜索</button>
				                        </div>
				                    </div>
				                </div>
				                	<!-- 记录 -->
				                <div class="list">
				                	<div class="tab_content clearfix">
										<div class="tab_box" id="rechargerocord">
										<%@ include file="/WEB-INF/jsp/user/manager/recharge/rechargeRecord.jsp"%>
											<!-- <table class="table" cellspacing="0" cellpadding="0" id="transactionTable"> 
												<tr class="">
													<th class=""><span>时间</span></th>
													<th class="">类型</th>
													<th class="">金额</th>
													<th class="">状态</th>
													<th class="">操作</th>
												</tr>
												<tbody>
													<tr class="tr_contrnt">
														<td class="Numfont table_timelog">2017-03-05 21:14:20</td>
														<td class="">月月升</td>
														<td class="">2000</td> 
														<td class="">待审核</td>
														<td class="">操作</td>
													</tr>
												</tbody>
											</table> -->
										</div>
									</div>
				                </div>
			                </div>
			           
						</form>
			            
			        </div>
		    	</div>
		    </div>	
    		<div class="bg"></div>
		</div>
	</div>
	
     <!--充值 成功 -->
	<div class="messages" style="display:none;z-index:999;background:#fff;padding:20px">
    	<div class="fl pad_b10 tab clearfix">
			<dl class="succ_money fl clearfix">
				<dd class="fl mar_r10 mar_l30 mar_t30"><i class="icon_base icon_50 icon_base_tipright50"></i></dd>
				<dt class="fl mar_l20 mar_t30">
					<p class="fs_18 clearfix">
						您的账户已成功充值<span class="fc_f60 Numfont mar_l5 mar_r5 fb fs_20" id="successMoney">1.00</span>元！
					</p>
					<!--<p class="fs_16 pad_t5 pad_b5 fc_9">你当前账户余额为<span class="mar_l5 mar_r5">2000</span>元，若有问题请咨询客服 <span class="fc_f60 mar_l5 Numfont">400-791-0888</span></p>-->
					<p class="pad_t10 clearfix">
					<a href="javascript:;" class="close btn btn_bord_orang btn_size120">关闭</a></p>
					<!--<p class="pad_t30 clearfix" style="line-height:32px;">您还可以：<br><a href="index.html" class="mar_r20">返回账户详情</a><a href="fundsLog.html">查看资金记录</a></p>-->
				</dt>
			</dl>
		</div>
	</div>	
	<%-- <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%>  --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Css/jquery-ui.css"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/mobiscroll_date.js" charset="gb2312"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/resource/Scripts/mobiscroll.js"></script> 

<script type="text/javascript">
$(function () {
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default = {
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

	$("#starttime,#endtime").mobiscroll($.extend(opt['date'], opt['default']));
	$(".side_nav").find("dl").siblings().removeClass("navcurron").eq(1).addClass("navcurron");
});

//判断结束时间是否小于开始时间
$('.dwb-s .dwb').live("click",function (){
	var onetome=$("#starttime").val();
	var endtime=$("#endtime").val();
	if(onetome!='' && endtime!=''){
		if(endtime<onetome){
			$("#endtime").val('');
			alert('结束时间不能小于开始时间');
		}
	}
})
    	$(document).ready(function(){
    		$(".box2,.box3").hide();
		    $("#tits li").click(function(){
		        $("#tits li").removeClass("cur")
		        var num = $(this).val();
		        $(this).addClass("cur")
		        $(".box").hide();
		        $(".box"+num).show();
		    });
		    //点击确认充值
		   $("#recharge_sub").click(function(){
			   var money = $("#money").val();
				$.ajax({
					type : "POST", 
					url : basepath+"/user/userRecharge/simulatedRecharge.action", 
					data: "money="+money,
					success : function(result) { 
						var arr = result.split(',');
						if(arr[0]=="success"){
							$(".messages,.bg").show();
							$("#successMoney").text(arr[1]);
							 showDiv(".messages");
						}else{
							alert(arr[0]);
							/* $(".mainbox").load(basepath+"/jsp/user/manager/recharge/rechargeFail.jsp"); */
							
						}
						
					}
				})	
				
    		})
    		
    		
		   $(".close").click(function(){
		   		$(this).parents(".messages").hide();
		   		$(".bg").hide()
		   })
		
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
			//$(".bg").show();
	})
	//点击标签的时候变换标签的背景颜色
	$(document).ready(function(){
	$(".time li").click(function(){
		$(".time li").removeClass("on");
		$(".auto button").removeClass("on");
		$(this).addClass("on");
		var num=$(".time li.on").attr("data-value");
		$("#starttime").val('');
   		$("#endtime").val('');
		$.ajax({
				type : "POST", 
				url : basepath+"/user/userRecharge/rechargeScreen.action", 
				data: "moreCode="+num,
				success : function(result) { 
					$("#rechargerocord").empty();
					$("#rechargerocord").html(result);
				}
			})	
		}) 
	});
	//点击搜索按钮
   	$("#search").click(function(){
   		var starttime=$("#starttime").val();
   		var endtime=$("#endtime").val();
		$(this).addClass("on");
		var num=$(".auto .on").attr("value");	
   		$.ajax({
			type : "POST", 
			url : basepath+"/user/userRecharge/rechargeScreen.action", 
			data: {starttime:starttime,endtime:endtime,moreCode:num},
			success : function(result) { 
				$("#rechargerocord").empty();
				$("#rechargerocord").html(result);
			}
		})	
   		
   	})
   	//鼠标离开输入框显示充值后余额
   	function upperCase(){
    
	   var money = $("#money").val();
		$.ajax({
			type : "POST", 
			url : basepath+"/user/userRecharge/addmoney.action", 
			data: "money="+money,
			success : function(result) { 
				$(".spa_2 .cr_i").text(result);
				
			}
		})	
    } 
</script>
</body>
</html>