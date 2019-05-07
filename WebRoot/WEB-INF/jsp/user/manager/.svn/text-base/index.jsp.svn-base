<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情</title>
<meta name="keywords"
	content="你我贷|网络理财|个人理财|投资理财|P2P理财|互联网金融|投资理财|债权转让|理财计划|嘉财有道|网络贷款|企业贷|商户贷|车贷|网商贷|网贷|小额贷款|薪金贷|POS贷|物业贷|卡易贷|贷款|贷款公司|房产贷款|汽车贷款|个人贷款|无抵押贷款|个人无抵押贷款">
<meta name="description"
	content="你我贷(www.niwodai.com)是中国领先的互联网金融P2P网络借贷平台，为投资理财用户和贷款用户提供透明、安全、高效的互联网金融服务。投资理财用户可通过你我贷平台进行投标、加入嘉财有道理财计划、购买债权等方式进行投资获得安全的高收益；贷款用户可通过平台高效申请企业贷、商户贷、车贷、网商贷、网贷、薪金贷、POS贷、卡易贷、物业贷等小额贷款。" />
<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/IDCardInfo.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript"src="<%=basePath%>resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript"src="<%=basePath%>resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script>
 <link href="<%=basePath%>resources/resource/Css/nwd_common.css?v=20170313040112.css" rel="stylesheet" type="text/css"> 
<link href="<%=basePath%>resources/resource/Css/nwd_percenter.css?v=20170313040112.css" rel="stylesheet" type="text/css"> 
<link href="<%=basePath%>resources/resource/Css/nwd_vipstyle.css?v=20170313040112.css" rel="stylesheet" type="text/css">
<style type="text/css">
</style>
</head>
<body class="index_niwodaiNew">
	<!--header nav start-->
	<script type="text/javascript" desc="Adobe">
		var adobeStatus = '1';
		if (adobeStatus == '0') {
			npo.isSentData = false;
		} else {
			npo.isSentData = true;
		}
		s.prop4 = "已登录";
	</script>
	<script type="text/javascript">
		s.pageName = "null";
	</script>
<script>
function logout(obj){
	 var flag = confirm("您确定退出吗？");
	 if(flag){
		 $.ajax({
			 type:"post",
			 url:"<%=basePath%>user/logout.action",
			 success:function(data){
				
				 if(data=="success"){
					 window.location.href="<%=basePath%>user/tologin.action";
				 }
			 }
		 });
	 }
}
</script>
	<div class="header">
		<div class="nwd_top">
			<div class="nwd_main pad_t10 clearfix">
				<span class="fl fs_16 fc_9"><i class="icon icon_tel">&nbsp;</i>客服热线<em
					class="Numfont mar_l10">&nbsp;400-791-0888</em></span> <span
					class="fr fc_9 mar_l10">市场有风险，投资需谨慎</span> <span class="fr"><a
					href="http://weibo.com/niwodai" target="_blank" class="link_weixin"><i
						class="icon icon_weixin">&nbsp;</i></a></span> <span class="fr mar_r10"><a
					href="javascript:()" class="link_weibo mar_r10" target="_blank"><i
						class="icon icon_weibo">&nbsp;</i>微信客服<img
						src="http://static3.niwodai.com/Public/Static/201603/css/font/topbg_b14925.png"
						class="weixinImg"></a></span> <span class="fr"><a
					href="http://www.niwodai.com/index.do?method=ac&artId=3800000395842909"
					class="link_mobile mar_r20" target="_blank"><i
						class="icon icon_moible">&nbsp;</i>手机客户端<img
						src="https://static.niwodai.com/Public/Static/201603/css/font/topbg_b14926.png"
						usemap="#Map" class="mobileImg">
					<map name="Map">
							<area shape="rect" coords="154,40,256,153"
								href="http://www.niwodai.com/event.mhtml?&artId=3800000395842909"
								target="_blank">
							<area shape="rect" coords="424,41,517,150"
								href="http://www.niwodai.com/event.mhtml?&artId=3800000794998139"
								target="_blank">
						</map> </a></span> <input type="hidden" value="" id="stok" /> <input type="hidden"
					value="3820150020947754" id="log_userid" /> <input type="hidden"
					value="wugtstore" id="log_username" /> <span class="fr fc_9">您好，<a
					href="https://member.niwodai.com/member/Investors.html"
					class="mar_l10 toplogin">admin</a> 
					<a href="javascript:void(0)" onclick="logout(this)"  class="mar_r10 mar_l10 toplogin">退出</a>
				</span>
			</div>
		</div>
		<div class="nwd_menu">
			<div class="nwd_main">
				<h1 class="fl logo mar_t20">
					<a title="给梦想可能" href="http://test.ganjiangps.com/"><img height="52"
						alt="干将软件" src="<%=basePath%>resources/Images/logo.png"></a>
				</h1>
			<div class="fr nwd_navCon  mar_t20">
					<ul class="nav clearfix">
						<li><a title="首页" href="http://www.niwodai.com"
							rel="nofollow" class="nav_link">首页</a></li>
						<li class="two">
							<div class="investCon">
								<div class="invest_name">
									<a href="https://member.niwodai.com/xiangmu" class="fs_16">我要投资</a>
									<em class="icon_base icon_base_navedown mar_l10"></em>
								</div>
								<div class="invest_list">
								 <a href="http://www.niwodai.com/inteBid/loadingInteBidPage.do"
										class="investName_link">有道智投</a> <a
										href="https://member.niwodai.com/baiduFina/newPeople.do"
										class="investName_link">新手专区</a> <a
										href="https://member.niwodai.com/xiangmu"
										class="investName_link">新标专区</a> <a
										href="https://member.niwodai.com/zhuanrang/"
										class="investName_link">转让专区</a> 
										
								</div>
							</div>
						</li>
						<li class="rela">
							<div class="investCon">
								<div class="invest_name">
									<a title="我要借款" href="http://www.niwodai.com/daikuan/"
										class="fs_16">我要借款</a> <em
										class="icon_base  icon_base_navedown  mar_l10"></em>
								</div>
								<div class="invest_list">
									<a href="http://www.niwodai.com/daikuan/jiekuan.html"
										class="investName_link">借款产品</a> <a
										href="http://www.niwodai.com/help/jiekshenqing.html"
										class="investName_link">借款攻略</a>
								</div>
							</div>
						</li>
						<li class="rela"><a title="新手导航"
							href="http://www.niwodai.com/activity.mhtml?artId=3800001227739922&f&h"
							target="_blank" class="nav_link">新手导航 </a></li>
						<li><a title="社区" href="http://club.niwodai.com"
							target="_blank" class="nav_link">社区 </a></li>
						<li class="two"><a
							href="http://static2.niwodai.com/Public/Static/nwd/website/pc/disclosure/index.html"
							class="nav_link">信息披露</a></li>
						<li class="myuserCon" style="margin-right: 0;">

							<div class="LedgerCon wid_w180">
								<a class="navphoto fs_16 fl mar_l10"
									href="https://member.niwodai.com/member/Investors.html"
									title="我的账户"><i class="mar_r5 relative"> <img
										id="small-tx"
										src="http://static3.niwodai.com/Public/Static/201603/manage/img/smalltx.png">
										<img id="smallImg" alt="头像"
										src="http://static3.niwodai.com/Public/Static/201603/manage/images/touxiang.jpg"
										class="smallmytxPic">
								</i></a>
								<div class="Ledger_name pad_t5">
									<a title="我的账户" class="fs_16">我的账户</a> <em
										class="icon_base icon_base_navedown mar_l5"></em>
								</div>
								<div class="Ledger_list clearfix pad_l10 pad_r10">
									<a href="https://member.niwodai.com/member/memberMsg.do"
										class="listName_link"><span class="fl">消息中心</span><em
										id="mmsg"></em></a> <a
										href="https://member.niwodai.com/member/fundsLog.html"
										class="listName_link">资金记录</a> <a
										href="http://www.niwodai.com/inteBid/loadingInteBidPage.do"
										class="listName_link">有道智投</a> <a
										href="https://member.niwodai.com/member/myAcceptList.html"
										class="listName_link">我的债权</a> <a
										href="http://www.niwodai.com/loginOut.html"
										class="listName_link">我要退出</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<script>
		if (self == top) {
			var theBody = document.getElementsByTagName('body')[0];
			theBody.style.display = "block";
		} else {
			top.location = self.location;
		}
		var httpsUrl = 'https://member.niwodai.com';
		var httpUrl = 'http://www.niwodai.com';
	</script>
	<!--header nav end-->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!--left nav start-->
			<div class="fl sidbar" style="position: relative;">
				<div class="my_msgCon mar_t20">
					<div class="my_pic">
						<a href="javascript:;" class="myphoto radius94 platlog_img"
							id="myphototip"> <input type="hidden" id="ph"></input> <input
							type="hidden" id="ph1"></input> <img id="bigtx-mask"
							src="http://static3.niwodai.com/Public/Static/201603/manage/img/bigtx.png">
							<img
							src="http://static3.niwodai.com/Public/Static/201603/manage/images/touxiang.jpg"
							class="bigmytxPic" id="exists" style="display: none"> <img
							src="http://static3.niwodai.com/Public/Static/201603/manage/images/touxiang.jpg"
							class="bigmytxPic" id="noExists"> <span class="xgtx">编辑资料</span>
						</a>
						<p class="my_name mar_t20 fc_9">admin</p>
						<p class="my_tel  pad_t10 fs_16 fc_9" id="phoneInfo"></p>
						<p class="my_tel  pad_t10 fs_14 fc_9">
							<a href="https://member.niwodai.com/member/memberLevelPage.do"
								class="mystepshow" id="memberLevelDiv"></a>
						</p>
					</div>
					<div id="assetUserWelcome" class="u-ext mar_b20"></div>
				</div>
				<div class="side_nav">
					<dl class="nav_name" id="leftnav0">
						<dt>
							<a href="/manager/Investors.html"
								class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">账户中心</a><i>&nbsp;</i>
						</dt>
					</dl>
					<dl class="nav_name" id="leftnav1">
						<dt>
							<a href="javascript:;"
								class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">资金管理</a><i>&nbsp;</i>
						</dt>
						<dd class="bor_r bor_t bor_b">
							<ul>
								<li><a
									href="https://member.niwodai.com/member/rechargeStep.do">我要充值</a></li>
								<li><a href="https://member.niwodai.com/member/carry.html">我要提现</a></li>
								<li><a
									href="https://member.niwodai.com/member/fundsLog.html"
									id="fundRecordLi">资金记录</a></li>
							</ul>
						</dd>
					</dl>
					<dl class="nav_name" id="leftnav2">
						<dt>
							<a href="javascript:;"
								class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">投资管理</a><i>&nbsp;</i>
						</dt>
						<dd class="bor_r bor_t bor_b">
							<ul>
								<li><a
									href="https://member.niwodai.com/member/findinteBidPage.do">有道智投</a></li>
								<li><a
									href="https://member.niwodai.com/member/findFinancial2Page.do">嘉财有道</a></li>
								<li><a
									href="https://member.niwodai.com/member/myAcceptList.html">我的债权</a></li>
								<li><a
									href="https://member.niwodai.com/member/canDebentureTransfer.do">债权转让</a></li>
								<li><a
									href="https://member.niwodai.com/member/noviceArea.do">新手专区</a></li>
								<li id="excellenceNav" style="display: none"><a
									href="https://member.niwodai.com/member/findFinancialNFIPage.do">卓越专区</a></li>
								<li><a
									href="https://member.niwodai.com/member/findFinancialGuessPage.do">嘉猜宝</a></li>
								<!--  <li><a href="addmoney.html">有道添金</a></li> -->
							</ul>
						</dd>
					</dl>

					 <dl class="nav_name">
						<dt>
							<a href="javascript:;"
								class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">借款管理</a><i>&nbsp;</i>
						</dt>
						<dd class="bor_r bor_t bor_b">
							<ul>
								<li><a
									href="https://member.niwodai.com/member/borrowingRecord.do">我的借款</a></li>
								<li><a
									href="https://member.niwodai.com/member/repaymentDetail.do">我要还款</a></li>
							</ul>
						</dd>
					</dl> 
					<dl class="nav_name" id="leftnav3">
						<dt>
							<a href="javascript:;"
								class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">账户管理</a><i>&nbsp;</i>
						</dt>
						<dd class="bor_r bor_t bor_b">
							<ul>
								<li><a
									href="https://member.niwodai.com/member/exchange.html">我的积分</a></li>
								<li><a
									href="https://member.niwodai.com/member/safetyLevel.do?doWhat=sl">安全中心</a></li>
								<li><a
									href="https://member.niwodai.com/member/memberMsg.do">消息中心</a></li>
								<li><!-- <a
									href="https://member.niwodai.com/member/setConnectAccount.do">账户绑定</a></li> -->
									<a
									href="<%=basePath%>account/bingding.action">账户绑定</a></li>
								<li><a
									href="https://member.niwodai.com/member/bindCardInfo.do">银行卡管理</a></li>
							</ul>
						</dd>
					</dl>

					<dl class="nav_name" id="leftnav4">
						<dt>
							<a href="javascript:;"
								class="pad_t10 pad_b10 nwd_icon nwd_icon_navarrow">活动管理</a><i>&nbsp;</i>
						</dt>
						<dd class="bor_r bor_t bor_b">
							<ul>
								<li><a href="https://member.niwodai.com/member/coupon.do">我的礼品</a></li>
								<li><a
									href="https://member.niwodai.com/member/getActivityAwardAjax.do?moreCode=6">活动奖励</a></li>
								<li><a
									href="https://member.niwodai.com/member/recommendDetails.html">推荐有奖</a></li>
								<li><a
									href="https://member.niwodai.com/recommendAward2017/loadingPage.do"
									target="_blank">我要推荐</a></li>
							</ul>
						</dd>
					</dl>
				</div>
				<!-- <div class="leftCode txt_center pad_t30 mar_t20 bor_t pad_b30">
		<img src="http://static3.niwodai.com/Public/Static/201603/css/font/leftCode.png">
		<p class="fc_9 fs_14 mar_t5">你我贷APP 随时理财</p>
	</div> -->
			</div>
			<!--left nav end-->





			<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery-1.7.2.min.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery.vticker-min.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery.form.js"></script>

			<script type="text/javascript" src="http://static2.niwodai.com/Public/Static/201404/js/common.js?v=20170313040112.js"></script>
			<script src="<%=basePath%>resources/resource/Scripts/jquery.validate.js"></script>
			<script src="<%=basePath%>resources/resource/Scripts/messages_cn.js"></script>
			<script src="<%=basePath%>resources/resource/Scripts/jquery.artDialog.js?skin=grey2013" type="text/javascript"></script>
			<script src="<%=basePath%>resources/resource/Scripts/iframetools.js" type="text/javascript"></script>
			<!--时间  s-->
			<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/jquery.ui.datepicker.js"></script>
			<link href="<%=basePath%>resources/resource/Css/jquery-ui.css" rel="stylesheet" type="text/css" />
			<script type="text/javascript"
				src="<%=basePath%>resources/resource/Scripts/jquery-ui.min.js"></script>
			<!--时间  e-->
			<script language="javascript">
				var staticCss = 'http://static3.niwodai.com/Public/Static/201603';
				var cssVersion = '20170313040112';
				var staticUrl = 'http://static4.niwodai.com/';
			</script>
			<!--静态化 - 头部内容-->
			 <script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/sea.js?v=20170313040112.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/nwd_common.js?v=20170313040112.js"></script> 
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/managecommon.js?v=20170313040112.js"></script>
			 <script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/nwd_Sizeani.js?v=20170313040112.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/slide.js?v=20170313040112.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/placeholder.js?v=20170313040112.js"></script>
			
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/popset.js?v=20170313040112.js"></script>
			
			<script type="text/javascript" src="<%=basePath%>resources/resource/Scripts/mymoney.js?v=20170313040112.js"></script>
            <!-- 用户行为日志记录脚本 -->
			<!--<script type="text/javascript" src="http://static3.niwodai.com/Public/Static/201404/js/fingerprint2.min.js?v=20170313040112.js"></script>-->
			<!-- <script type="text/javascript"
				src="http://static4.niwodai.com/Public/Static/201404/js/specter.js?v=20170313040112.js"></script> -->
			<script type="text/javascript" desc="maidian">
				$(document)
						.ready(
								function() {
									var userid = "null";
									var transtype = $("#md_transtype").val();
									userid = "ADZUPFYyVTkFYlRlUDBeaQoxVWQCaAJgBTAFMgBjU2I=";
									var pagetype = s.pageName;
									if (!pagetype) {
										pagetype = "null";
									}
									pagetype = pagetype.replace(
											/(^\s*)|(\s*$)/g, "");
									try {
										buryPoint(userid, pagetype);
										if (transtype != ''
												&& transtype != null
												&& transtype != undefined) {
											var amount = $("#md_amount").val();
											transFunc(userid, pagetype,
													transtype, amount)
										}
									} catch (e) {
									}
								});
			</script>

			
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/nwd_Addmoney.js?v=20170313040112.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/scroll.js?v=20170313040112.js"></script>
			<script type="text/javascript" src="<%=basePath%>resources/resource/ScriptsP/popset.js?v=20170313040112.js"></script>


			<input type="hidden" value="http://static2.niwodai.com/Public/Static/201404"id="staticCss" /> 
			<input type="hidden" name="flag" id="flag">
			<input type="hidden" name="realNameIdenTag" id="realNameIdenTag">
			<input type="hidden" name="xiangdao" id="xiangdao" value="1">
			<input type="hidden" name="mbPhone" id="mbPhone" value="187****4329">
			<input type="hidden" name="SXFRate" id="SXFRate" value="0"> <input
				type="hidden" name="choseBankFlg" id="choseBankFlg" value="0">
			<div class="fl perCerterR  bor_r bor_l">
				<!-- 我要充值 -->
				<div class="fl pad_30 wid_w900 clearfix">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要充值</span>
					</div>

					<div
						class="fl pad_b10 pad_t30 tab indexTab clearfix indexHandle ui-select-listBox">
						<ul class="tab_title clearfix  ">
							<li
								class="fl ui-select-listBox-list  ui-select-listBox-list--now selecttab"><a
								onclick="javascript:;" href="javascript:;" class="pad_b10">快捷支付</a></li>
							<li class="fl ui-select-listBox-list selecttab"><a
								onclick="javascript:;" href="javascript:;" class="pad_b10" >网银支付</a></li>
						</ul>
						<div class="ui-select-listBox-line">
							<div class="ui-select-listBox-l-blue"
								style="left: 20px; width: 80px;"></div>
						</div>
						<div class="pad_l20 fs_14 pad_b10 fr available">
							可用余额 :<span class="fc_f60 Numfont fb fs_16 mar_l5">0.00 </span><span
								class="fc_f60">元</span>
						</div>
					</div>

					<form action="/member/crushYintongQuick.html" name="queryForm"
						id="queryForm" target="_blank" method="post" class="nwd-formUi"
						autocomplete="off">
						<input type="hidden" name="rechargeCode" id="rechargeCode" />
						<!-- 充值申请单号,获取短信验证码成功后得到-->
						<input type="hidden" name="selectedBid" id="selectedBid" value="" />
						<div class="tab_content clearfix">
							<div class="tab_box">
								<div class="pad_l20">
									<div class="bank_tips pad_t20 pad_b20">
										<img
											src="http://static3.niwodai.com/Public/Static/201603/manage/images/bank_tips.png">
										<span class="pad_t5 fc_9 fs_14">如果您想投资新手产品请直接快捷支付购买</span>
									</div>

									<table width="100%" cellpadding="0" cellspacing="0"
										class="table_addmoney">
										<tr style="display:;" class="banktrNewUser  new-shortcut-new"
											id="banktrNewUser">
											<th class="pad_t20 fc_6" style="vertical-align: top;">选择银行</th>
											<td class="pad_t10 pad_b10">
												<div class="first_newbank clearfix pad_t10     ">
													<ul class="clearfix">

														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank01020000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="8bankName" value="8" />
																<div style="display: none">单笔20万，单日20万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank01050000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="9bankName" value="9" />
																<div style="display: none">单笔50万，单日100万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank01030000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="12bankName" value="12" />
																<div style="display: none">单笔5万，单日20万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03080000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="6bankName" value="6" />
																<div style="display: none">单笔5万，单日5万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank03010000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="13bankName" value="13" />
																<div style="display: none">单笔20万，单日20万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank01000000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="14bankName" value="14" />
																<div style="display: none">单笔5000，单日5000</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank01040000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="5bankName" value="5" />
																<div style="display: none">单笔5万，单日30万,单月900万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank03100000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="16bankName" value="16" />
																<div style="display: none">单笔30万，单日30万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03050000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="15bankName" value="15" />
																<div style="display: none">单笔5万，单日15万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03030000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="11bankName" value="11" />
																<div style="display: none">单笔50万，单日100万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank03020000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="7bankName" value="7" />
																<div style="display: none">单笔1万，单日1万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03090000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="10bankName" value="10" />
																<div style="display: none">单笔5万，单日不限</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03070000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="3bankName" value="3" />
																<div style="display: none">单笔99万，单日不限</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank04012900.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="17bankName" value="17" />
																<div style="display: none">单笔10万，单日100万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank03060000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="2bankName" value="2" />
																<div style="display: none">单笔50万，单日100万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03040000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="1bankName" value="1" />
																<div style="display: none">单笔50万，单日100万</div>
														</span></li>
														<li onclick="selectBankDef(this,2,true)"
															class="bankShowLi"><a href="javascript:;"><img
																src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank04031000.gif"></a>
															<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
															<span> <input type="hidden" name="bankRadio"
																id="4bankName" value="4" />
																<div style="display: none">单笔5万，单日100万</div>
														</span></li>

														<li class="morebankli" id="morebank-btn-kj"
															onclick="moreBankPage.otherbank()"
															style="border-style: dashed; line-height: 40px; text-align: center;"><a
															href="JavaScript:;">+更多银行</a></li>
													</ul>
													<div class="fc_9 clearfix   bankRemark" id="bankRemark">
														支付限额：单笔<span class="fc_f60 Numfont">50</span>万，单日<span
															class="fc_f60 Numfont">100</span>万
													</div>
													<span class="prompt_1 error_1" id="bank_MSG"
														style="display: none"></span>
											</td>
										</tr>

										<tr id="myotherbank" class="bankclass" style="display:;">
											<th class="fc_6 vertical_top pad_t10">银行卡号</th>
											<td class="pad_t10 pad_b20"><label
												class="bankmember input_w260"> <input type="text"
													class="input Numfont input_w260 input_bank"
													name="mbbCardNo" id="mbbCardNo" maxlength="24"
													autocomplete="off"> <span class="tip_default">持卡人信息需同实名认证信息一致</span>
											</label> <span class="prompt_1 error_1" id="mbbCardNoMSG"
												style="display: none"><i></i>单笔充值金额至少1元！</span></td>
										</tr>




									</table>
								</div>
							</div>
							<!-- 网银支付 -->
							<div class="tab_box hide">
								<div class="pad_l20 pad_t20">
									<div class="control-group clearfix">
										<table width="100%" cellpadding="0" cellspacing="0"
											class="table_addmoney">
											<tr>
												<th class="fc_6 pad_t10" style="vertical-align: top;">选择银行</th>
												<td class="pad_t10 pad_b10">
													<div class="bankPay pad_r10 ">




														<a href="javascript:;" class="fc_9 Numfont"><img
															id="newbank"
															src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank0.gif"><i
															class="icon_20 nwd_icon nwd_icon_bankarrow">&nbsp;</i></a> <input
															type="hidden" id="recentBankImg"
															value="http://static2.niwodai.com/Public/Static/201404/images/bank/bank0.gif" />
														<!-- 默认给出的最近一次的网银支付过的银行-->
														<input type="hidden" name="bankRadioDef" id="bankRadioDef" />
														<!--bid值-->
														<input type="hidden" name="bankRadioDeftype"
															id="bankRadioDefType" />
														<!--支付渠道，如-6,就代表新浪微博钱包-->
													</div>
													<div
														class="bankPaySlect pad_l20 mar_b20 dailogCon clearfix none">
														<div class="pad_t10 pad_b10 clearfix">
															<span class="fl">充值银行</span>
														</div>
														<div class="new-shortcut-wy">
															<ul class="select clearfix">
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank01020000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="8bankName" value="8" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank01020000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="9">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="4">工商银行</td>
																					<td rowspan="3">卡种</td>
																					<td colspan="2" rowspan="2">U盾客户</td>
																					<td colspan="2" rowspan="2">电子密码</td>
																					<td colspan="2" rowspan="2">短信认证</td>
																					<td colspan="2" rowspan="2">电子口令卡</td>
																				</tr>
																				<tr>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>100万</td>
																					<td>100万</td>
																					<td>50万</td>
																					<td>100万</td>
																					<td>2000</td>
																					<td>5000</td>
																					<td>500</td>
																					<td>1000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank01050000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="9bankName" value="9" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" width="100%"
																				class="tab" id="bank01050000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="9">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">建设银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">网银盾1代</td>
																					<td colspan="2">网银盾2代</td>
																					<td colspan="2">动态口令</td>
																					<td colspan="2">账号直接支付</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>5万</td>
																					<td>10万</td>
																					<td>50万</td>
																					<td>50万</td>
																					<td>5000</td>
																					<td>5000</td>
																					<td>1000</td>
																					<td>1000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank03080000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="6bankName" value="6" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank03080000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td colspan="6" class="th">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">招商银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">专业版 ( 签约客户 )</td>
																					<td colspan="4">大众版 ( 非签约客户 )</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td colspan="2">单笔限额</td>
																					<td colspan="2">每日限额</td>
																				</tr>
																				<tr>
																					<td>大额借记卡</td>
																					<td>客户自行设定</td>
																					<td>客户自行设定</td>
																					<td colspan="2">500</td>
																					<td colspan="2">500</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03010000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="13bankName" value="13" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank03010000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td colspan="6" class="th">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3" nowrap>交通银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">短信密码版</td>
																					<td colspan="2">证书认证版</td>
																					<td colspan="2" rowspan="2">备注</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>借记卡、准贷记卡</td>
																					<td>5000</td>
																					<td>5000</td>
																					<td>1000000</td>
																					<td>1000000</td>
																					<td colspan="2"
																						style="text-align: left; padding-left: 10px">您如果需要进行网上支付，<br />
																						需要开通网上银行（手机<br /> 注册版或证书认证版），使<br />
																						用签约的主卡或下挂卡支付。
																					</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank01000000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="14bankName" value="14" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank01000000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td colspan="7" class="th">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">邮储</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">手机短信客户</td>
																					<td colspan="2">电子令牌客户</td>
																					<td colspan="2">Ukey+短信客户</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>借记卡</td>
																					<td>10000</td>
																					<td>10000</td>
																					<td>100000</td>
																					<td>100000</td>
																					<td>1000000</td>
																					<td>1000000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank01040000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="5bankName" value="5" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" width="100%"
																				class="tab" id="bank01040000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="3">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">中国银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">USBKey证书认证、令牌+动态口令</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>5万</td>
																					<td>不限</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank03100000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="16bankName" value="16" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank03100000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">上海浦发银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">手机动态密码</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>20万</td>
																					<td>20万</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank03050000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="15bankName" value="15" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank03050000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">民生银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">U宝用户</td>
																					<td colspan="2">贵宾版</td>
																					<td colspan="2">大众版</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>50万</td>
																					<td>50万</td>
																					<td>5000</td>
																					<td>5000</td>
																					<td>300</td>
																					<td>300</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03030000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="11bankName" value="11" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" width="100%"
																				class="tab" id="bank03030000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="5">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">光大银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">令牌动态密码及阳光网盾验证方式</td>
																					<td colspan="2">手机动态密码</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>50万</td>
																					<td>50万</td>
																					<td>1万</td>
																					<td>1万</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static2.niwodai.com/Public/Static/201404/images/bank/bank03020000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="7bankName" value="7" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" width="100%"
																				class="tab" id="bank03020000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="5">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">中信银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">U盾</td>
																					<td colspan="2">手机动态密码</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>100万</td>
																					<td>100万</td>
																					<td>1000</td>
																					<td>5000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03090000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="10bankName" value="10" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" width="100%"
																				class="tab" id="bank03090000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="5">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">兴业银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">U盾</td>
																					<td colspan="2">手机动态密码</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>30万</td>
																					<td>30万</td>
																					<td>日累积范围内无限额</td>
																					<td>初始5000（可至网点加大）</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03070000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="3bankName" value="3" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank03070000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="3">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="2">平安银行</td>
																					<td>卡种</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>无限额</td>
																					<td>5万</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank04012900.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="17bankName" value="17" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank04012900paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="5">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">上海银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">办理E盾证书版个人网银</td>
																					<td colspan="2">办理动态密码版个人网银（含文件证书）</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>50万</td>
																					<td>100万</td>
																					<td>6000</td>
																					<td>1万</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03060000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="2bankName" value="2" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" width="100%"
																				class="tab" id="bank03060000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">广东发展银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">key令</td>
																					<td colspan="2">key盾</td>
																					<td colspan="2">手机动态密码</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>5万</td>
																					<td>5万</td>
																					<td>100万</td>
																					<td>100万</td>
																					<td>5000</td>
																					<td>5000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank04243010.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="19bankName" value="19" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank04243010paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td colspan="5" class="th">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">南京银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">专业版客户</td>
																					<td colspan="2">手机动态密码版客户</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>借记卡</td>
																					<td>50000</td>
																					<td>50000</td>
																					<td>10000</td>
																					<td>10000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank04031000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="4bankName" value="4" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank04031000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="6">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">北京银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">证书版</td>
																					<td colspan="2">动态密码版</td>
																					<td>普通版</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>借记卡</td>
																					<td>100万</td>
																					<td>100万</td>
																					<td>1000</td>
																					<td>5000</td>
																					<td>300</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank04083320.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="18bankName" value="18" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank04083320paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td colspan="7" class="th">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">宁波银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">U-KEY</td>
																					<td colspan="2">动态密码令牌用户</td>
																					<td colspan="2">短信动态密码用户</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>借记卡</td>
																					<td>不限</td>
																					<td>不限</td>
																					<td>50000</td>
																					<td>50000</td>
																					<td>50000</td>
																					<td>50000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank64296510.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="145bankName" value="145" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank64296510paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">成都银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">大众版</td>
																					<td colspan="2">手机动态</td>
																					<td colspan="2">数字证书</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>300</td>
																					<td>1000</td>
																					<td>1000</td>
																					<td>5000</td>
																					<td>50000</td>
																					<td>100000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static3.niwodai.com/Public/Static/201404/images/bank/bankBJRCB.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="147bankName" value="147" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="6">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">北京农村商业银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">手机认证</td>
																					<td colspan="2">UKEY认证</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>10万</td>
																					<td>50万</td>
																					<td>不限</td>
																					<td>不限</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static3.niwodai.com/Public/Static/201404/images/bank/bank04233310.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="61bankName" value="61" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="6">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">杭州银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">专业版（签约用户）</td>
																					<td colspan="2">大众（非签约用户）</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>不限</td>
																					<td>不限</td>
																					<td>300</td>
																					<td>300</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank04403600.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="30bankName" value="30" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">徽商银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">动态密码</td>
																					<td colspan="2">浏览器认证</td>
																					<td colspan="2">USBKEY</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>1000</td>
																					<td>5000</td>
																					<td>1000</td>
																					<td>5000</td>
																					<td>50万</td>
																					<td>200万</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bankCZB.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="148bankName" value="148" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">浙商银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">专业版（签约用户）</td>
																					<td colspan="2">大众（非签约用户）</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>不限</td>
																					<td>不限</td>
																					<td>200</td>
																					<td>1000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03170000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="146bankName" value="146" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%">
																				<tr>
																					<td class="th">银行</td>
																					<td class="th" colspan="7">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">渤海银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">大众版</td>
																					<td colspan="2">手机动态</td>
																					<td colspan="2">数字证书</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>储蓄卡</td>
																					<td>300</td>
																					<td>1000</td>
																					<td>1000</td>
																					<td>5000</td>
																					<td>50000</td>
																					<td>100000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
																<li onclick="selectBankWeb(this);" class="bank"><a
																	href="#"><img
																		src="http://static4.niwodai.com/Public/Static/201404/images/bank/bank03200000.gif"></a>
																	<em class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em>
																	<span> <input type="hidden" name="bankRadio_1"
																		id="21bankName" value="21" title="" />
																		<div style="display: none">
																			<table cellspacing="0" cellpadding="0" class="tab"
																				width="100%" id="bank03200000paymentDetail">
																				<tr>
																					<td class="th">银行</td>
																					<td colspan="7" class="th">限额信息</td>
																				</tr>
																				<tr>
																					<td rowspan="3">东亚银行</td>
																					<td rowspan="2">卡种</td>
																					<td colspan="2">Ukey+手机动态密码用户</td>
																					<td colspan="2">手机动态密码用户</td>
																					<td colspan="2">数字证书+手机动态密码用户</td>
																				</tr>
																				<tr>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																					<td>单笔限额</td>
																					<td>每日限额</td>
																				</tr>
																				<tr>
																					<td>借记卡</td>
																					<td>1000000</td>
																					<td>1000000</td>
																					<td>5000</td>
																					<td>20000</td>
																					<td>50000</td>
																					<td>200000</td>
																				</tr>
																			</table>
																		</div>
																</span></li>
															</ul>
														</div>
													</div> <span class="prompt_1 error_1" id="bank_MSG_1"
													style="display: none"></span>
													<div id="xeinfo"></div>

												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

							<div class="tab_box hide" style="display: none;">
								<div class="pad_l20">
									<div class="bank_tips pad_t20 pad_b20">
										<span class="fc_f60 fs_16 fb" style="font-style: italic;">注：需跳转第三方支付平台页面进行充值</span>
									</div>
									<table width="100%" cellpadding="0" cellspacing="0"
										class="table_addmoney">
										<tr>
											<th class="pad_t10 fc_6" style="vertical-align: top;">选择平台</th>
											<td class="pad_t10">
												<div class="my_moneysite">
													<ul>
														<li><a href="#"><img
																src="http://static3.niwodai.com/Public/Static/201603/manage/images/bank/quick1.png"></a><em
															class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em></li>
														<li><a href="#"><img
																src="http://static3.niwodai.com/Public/Static/201603/manage/images/bank/quick2.png"></a><em
															class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em></li>
														<li><a href="#"><img
																src="http://static3.niwodai.com/Public/Static/201603/manage/images/bank/quick3.png"></a><em
															class="icon_23 nwd_icon nwd_icon_BankSelect">&nbsp;</em></li>
													</ul>
												</div>
											</td>
										</tr>
										<tr>
											<th class="fc_6">&nbsp;</th>
											<td class="pad_t10 pad_b10"><input type="button"
												value="确认" class="btn btn_size120 btn_bgf60"
												onclick="javascript:document.location.href='bank_moneyOk.html'"></td>
										</tr>
									</table>
								</div>
							</div>


							<div class="">
								<div class="pad_l20">
									<table width="100%" cellpadding="0" cellspacing="0"
										class="table_addmoney" id="tab_info">
										<tr>
											<th class="fc_6 vertical_top">真实姓名</th>
											<td class="pad_b10"><label class="bankmember input_w200">
													<input type="text"
													class="input input_w200 input_readonly user_name 1"
													name="realname_read" disabled="disabled" value="">
											</label> <span class="prompt_1 error_1" id="realnameMSG"
												style="display: none"><i></i></span></td>
										</tr>
										<tr>
											<th class="fc_6 vertical_top pad_t10">证件号码</th>
											<td class="pad_t10 pad_b10"><label
												class="bankmember input_w200"> <input type="text"
													class="input Numfont input_w200 input_readonly"
													name="identity_read" value="" disabled="disabled">
											</label> <span class="prompt_1 error_1" id="identityMSG"
												style="display: none"><i></i></span></td>
										</tr>
										<tr>
											<th class="fc_6 pad_t10" style="vertical-align: top;">充值金额</th>
											<td class="pad_t10 pad_b10"><label class="bankmember">
													<input type="text"
													class="input input_w150 Numfont mar_r10 money mb_5"
													name="money" name="amount" id="amount" maxlength="11"
													autocomplete="off"
													onkeyup="this.value=this.value.replace(/\D/g,'')"
													onafterpaste="this.value=this.value.replace(/\D/g,'')">
													元
											</label> <span class="prompt_1 error_1 " id="amountMSG"
												style="display: none;"></span></td>
										</tr>
										<tr class="serviceFeeTR">
											<th class="leftTd">手续费：</th>
											<td class="pl24 lh_32"><input type="hidden"
												id="isPrivilege" value="0" /> <span id="serviceFee1">
													0.00 </span> <span id="serviceFee" style="display: none"> 0
											</span> <em id="serviceFee1Other" class="fs_14">元</em> <span
												class="why" id="helpSpan" style="display: none"> <i></i>
													<span class="fs_12 lh1em ml_5"> <em></em>
														注：当前有借款（不含净值贷）的用户收取充值金额的0.5%（通过第三方支付平台充值，上限100元）
												</span>
											</span></td>
										</tr>
										<tr class="serviceFeeTR">
											<th class="leftTd">实际支付：</th>
											<td class="pl24 lh_32"><span id="factamountView">0.00</span>元</td>
											<input type="hidden" id="factamount" name="factamount">
										</tr>
										<tr style="display: none" id="phoneTr">
											<th class="fc_6 vertical_top pad_t10">手机号</th>
											<td class="pad_t10 pad_b20"><label class="bankmember ">
													<input type="text" class="input Numfont input_w200    tel"
													name="phone" id="phone" maxlength="11" disabled="disabled">
													<span class="tip_default2">办卡时在银行预留的手机号</span>
											</label></td>
										</tr>
										<tr style="display: none" id="validateCodeTr">
											<th class="leftTd vertical_top pad_t5">验证码</th>
											<td class="pl24 lh_32"><input type="text"
												class="yzminput mb_5 input input_w100 Numfont mar_r10"
												name="validCode" id="validCode" maxlength="6"> <a
												href="javascript:void(0)"
												class="yzm-fous btn btn_size120 btn_bord_blue"
												id="btnSendDyn">点击获取</a>
											<!--<em class="yzm-time">59秒后重发</em>--> <!--yzm-blue点击获取验证码之后的样式-->
												<br>
											<span style="display: none;"
												class="prompt_2 error_2 lin_20 pad_t10" id="sendDynMSG"><i></i>报错！</span>
										</tr>

										<tr>
											<th class="fc_6">&nbsp;</th>
											<td class="pad_t10 pad_b10">
												<!--<a href="bank_moneyOk.html" class="btn btn_bgf60 btn_size120 pop-close">确认充值</a>-->

												<input type="button" id="confirmBut" value="确认充值"
												class="btn btn_bgf60 btn_size120 btnSize_1 btn_orange" /> <span
												style="display: none" class="errorthirdTip yellow">
													<div class="lefts fl  mr_5"></div>
													<span id="rechargeBtnMSG"></span>
											</span>

												<div class="errorTips" style="display: none">
													<div class="yellow clearfix">
														<div class="lefts fl mr_5"></div>
														<div class="right fl fs_12" style="line-height: 22px;">
															充值遇到问题？请点击<a href="/pay/kuaiqian/rechargeFail.do"
																target="_blank">获得帮助</a>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>


							<div class="wxtips mar_t20">
								<h3 class="title_wxtips pad_l20 pad_t30 pad_b10 fb fs_16">温馨提示</h3>
								<p class="fc_9 pad_l20">•
									系统将于每天23:55:00-00:05:00期间进行核算，核算期间充值将延时到账，如有问题，请联系客服。</p>
								<p class="fc_9 pad_l20">•
									为保障资金安全，充值提现采用同卡进出原则，即提现银行卡需与充值银行卡一致。</p>
								<p class="fc_9 pad_l20">• 判定为风险、可疑交易的提现，充值金额将通过第三方平台退回原卡。</p>
								<p class="fc_9 pad_l20">•
									为了您的账户安全，请在充值前进行实名认证、银行卡绑定以及交易密码设置。</p>
								<p class="fc_9 pad_l20">•
									禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用。</p>
								<p class="fc_9 pad_l20">
									• 充值前请确认您的银行卡限额。如充值成功后未能及时到账，请联系客服：<span class="fc_f60">400-7910-888。</span>
								</p>
							</div>
						</div>



					</form>

				</div>
			</div>
		</div>
	</div>


	<script
		src="https://qiyukf.com/script/214b54c481c5063fd62c3426b6ad0a99.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$(function() {
			var userid = '0';
			userid = "3820150020947754";
			ysf.config({
				uid : userid
			});

			// 事件监听方式
			ysf.on({
				unread : function(msg) {
					if (msg.total) {
						if (msg.total) {
							if (msg.total > 9) {
								$("#onlineQQService_blank").html('<em></em>');
							} else {
								$("#onlineQQService_blank").html(
										'<em>' + msg.total + '</em>');
							}
						}
					}
				}

			});

			$("#onlineQQService_blank").click(function() {
				$("#onlineQQService_blank").html("");
			});

			//获取文章
			$.ajax({
				type : "post",
				url : "/common/accountfooter.do",
				success : function(result) {
					if (result != null) {
						$("#footStart").html(result.artContent);
					}
				}
			});
		});
	</script>
	<!-- 底部开始 -->
	<div id="footStart"></div>

	<!-- 微信弹窗 start -->
	<div class="windowmask" style="display: none"></div>
	<div class="panelbox wid_w420 footWx">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">关注你我贷官方微信</span> <span
					class="panelclose nwd_icon nwd_icon_close wxclose"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
				<div class="container">
					<div class="analog-success">
						<div class="form clearfix txt_center">
							<div>
								<img
									src="http://static3.niwodai.com/Public/Static/201603/css/font/wexin.png">
							</div>
							<p class="fc_blue">微信号：niwodai-2011</p>
							<p class="fc_9">理财服务，随时随地</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 微信弹窗 end -->
	<!-- 底部结束 -->
	<div class="panelbox wid_w740" id="myphoto-pop-manage">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">修改头像</span> <span
					class="panelclose nwd_icon nwd_icon_close pop-close" id="closeImg"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30 clearfix">
				<div class="container clearfix">
					<div class="analog-success pad_l30 cleafix">
						<a class="myphoto radius94 fl" href="#"><img
							id="chooseImgShow" src=""> <!--<span class="radius94"></span>-->
						</a>
						<div class="fl pad_l30 pad_t10">
							<div class="files form relative">

								<form action="uploadMemberPhoto.do" method="post"
									enctype="multipart/form-data" name="memberPhoto" id="mbPhoto">

									<input type="text" name="textfields" id="mbphoto"
										class="txt ui-input w200-input" /> <input type="button"
										class="btn_up button button-w120 mar_l10" value="浏览..." /> <input
										type="file" name="file" class="file" size="23"
										style="width: 340px;"
										onchange="document.getElementById('mbphoto').value=this.value" />
									<input type="button" class="btn_up button button-w120 mar_l10"
										name="photoSubmit" onclick="savembphoto()" value="点击上传" />

								</form>
							</div>

							<p class="fc_9 pad_t15">
								注意：请选择jpg、gif、bmp、png、jpeg格式，尺寸为200*200，<br />
								<i style="displany: inline-block; width: 42px;">&nbsp;</i>且文件大小不超过2M的图片。
							</p>

						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="display: none;">
		<script type="text/javascript"
			src="http://static2.niwodai.com/Public/Static/201404/js/baiduStatisticSyn.js?v=20170313040112.js"></script>
	</div>
	<script type="text/javascript" desc="Adobe omniture">
		if (typeof (npo) != "undefined") {
			npo.autoExecute_t();
		}
	</script>
	<!-- Google Tag Manager -->
	<noscript>
		<iframe src="//www.googletagmanager.com/ns.html?id=GTM-T6K884"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>
	<script>
		(function(w, d, s, l, i) {
			w[l] = w[l] || [];
			w[l].push({
				'gtm.start' : new Date().getTime(),
				event : 'gtm.js'
			});
			var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l='
					+ l
					: '';
			j.async = true;
			j.src = '//www.googletagmanager.com/gtm.js?id=' + i + dl;
			f.parentNode.insertBefore(j, f);
		})(window, document, 'script', 'dataLayer', 'GTM-T6K884');
	</script>
	<!-- End Google Tag Manager -->


	<!-- 快捷支付新增银行卡 -->
	<div class="panelbox wid_w624" id="addBank-pop-kj">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">选择银行</span> <span
					class="panelclose nwd_icon nwd_icon_close pop-close"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l20 pad_r20">
				<div class="container">
					<div class="analog-success">
						<div class="pad_b10">
							<div class="citySelect clearfix">
								<dl class="quickmoney fl wid_w180">
									<dt class="">
										<i
											class="fr mar_t10 icon_base icon_base_selectdown mar_r10 mar_l5"></i><span
											class="sp pad_l10 fc_blue"><b>上海市</b></span>
									</dt>
									<dd style="display: none;">
										<p>
											<strong>华北</strong><span><a href="javascript:;"
												onclick="moreBankPage.clickCity('110')">北京市</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('120')">天津市</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('130')">河北省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('140')">山西省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('150')">内蒙古自治区</a></span>
										</p>
										<p>
											<strong>东北</strong><span><a href="javascript:;"
												onclick="moreBankPage.clickCity('210')">辽宁省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('230')">黑龙江省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('220')">吉林省</a></span>
										</p>
										<p>
											<strong>华东</strong><span><a href="javascript:;"
												onclick="moreBankPage.clickCity('310')">上海市</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('320')">江苏省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('330')">浙江省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('340')">安徽省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('350')">福建省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('360')">江西省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('370')">山东省</a></span>
										</p>
										<p>
											<strong>中南</strong><span><a href="javascript:;"
												onclick="moreBankPage.clickCity('410')">河南省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('420')">湖北省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('430')">湖南省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('440')">广东省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('460')">海南省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('450')">广西壮族自治区</a></span>
										</p>
										<p>
											<strong>西南</strong><span><a href="javascript:;"
												onclick="moreBankPage.clickCity('500')">重庆市</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('510')">四川省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('520')">贵州省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('530')">云南省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('540')">西藏自治区</a></span>
										</p>
										<p>
											<strong>西北</strong><span><a href="javascript:;"
												onclick="moreBankPage.clickCity('610')">陕西省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('620')">甘肃省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('630')">青海省</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('640')">宁夏回族自治区</a><a
												href="javascript:;" onclick="moreBankPage.clickCity('650')">新疆维吾尔自治区</a></span>
										</p>
										<span class="icon_user icon_user_cityclose closespan"></span>
									</dd>
								</dl>
							</div>
						</div>
						<div class="new-shortcut-allbank     dtl-cont bankBox" style="">
							<ul id="areaBank" class="clearfix dtl-main banList"
								style="transition: all 0.3s ease-out 0s; transform: translate3d(0px, 0px, 0px);">

							</ul>
						</div>
						<div class="allbank txt_left">
							找不到银行？<a id="by-Cardbtn" class="pop-close" href="javascript:;">通过卡号选择银行</a><span
								class="prompt_1 error_1" id="choseBankMSG"
								style="display: none; margin-left: 50px;"></span>
						</div>
						<div class="btnbox txt_center">
							<a class="btn btn_36c btn_size120 pop-close" href="javascript:;"
								onClick="moreBankPage.surebank1()" id="surebank1">确认</a> <a
								class="btn btn_borblue btn_size120 mar_l20 pop-close"
								href="javascript:;" onClick="rechargeBase.cancel()">取消</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 快捷支付通过银行卡号寻找银行 -->
	<div class="panelbox wid_w624" id="bycard-pop-kj">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">卡号查找</span> <span
					class="panelclose nwd_icon nwd_icon_close pop-close"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
				<div class="container">
					<div class="analog-success">
						<div class="form">
							<div class="item clearfix">
								<span class="label">银行卡号</span>

								<div class="bank-info ">
									<div class="fl">
										<div id="newcard" class=" bank-list">

											<input type="text" id="i_bank"
												class="itxt number inputStyle wid_w310    input_1"
												maxlength="23" onfocus="moreBankPage.fmCardNo()"
												onblur="moreBankPage.fmCardNOblue()" /> <span id="bank-boc"
												class="bank-logo" style="display: inline-block;"></span>
											<div id="newcard-type-span" class="type"
												style="display: inline-block;">
												<em id="newcard-type" class="saving">储蓄卡</em>
											</div>
											<div class="magnifier"></div>
											<em id="showBankName"></em> <i class="j_bank"></i>
										</div>
										<div class="clearfix">
											<span id="choseBankNoMSG"
												class="point point-ico2 nwd_icon nwd_icon_false"
												style="display: none;">银行卡号错误提示信息</span>
										</div>
									</div>
								</div>
							</div>
							<div class="item clearfix">
								<span class="label">&nbsp</span> <a
									class="btn btn_36c btn_size120 pop-close" href="javascript:;"
									onClick="moreBankPage.surebank1()" id="surebank1">确认</a> <a
									class="btn btn_borblue btn_size120 mar_l20 pop-close"
									href="javascript:;" onClick="rechargeBase.cancel()">取消</a>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>


	<div class="panelbox wid_w624   refreshPage" id="plusBankContainer1">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">请付款</span> <span
					class="panelclose nwd_icon nwd_icon_close pop-close"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
				<div class="container">
					<dl class="sucMoneyDailog pad_t10 clearfix">
						<dd class="fl">
							<i class="icon_base icon_32 icon_base_tipnote32"></i>
						</dd>
						<dt class="fl">
							<p class="">
								请您在新打开的页面上完成账户充值<br> 充值未完成请不要关闭此页面<br>
								充值成功后请根据您的情况点击下面的按钮
							</p>
							<div class="pad_t10 pad_b20">
								<p>
									<a class="btn btn btn_sizeauto btn_bgf60" id="linkSuccess"
										href="/member/crushlog.html">已完成充值</a> <a
										class="btn btn_sizeauto btn_bord_blue"
										href="/member/crushFail.html">付款遇到问题</a>
								</p>
								<p class="pad_t15">
									<a class="" href="/member/rechargeStep.do">
										&gt;&gt;返回选择其他支付方式</a>
								</p>
							</div>
						</dt>
					</dl>
				</div>
			</div>
		</div>
	</div>



	<div id="plusBankContainer3" desc="实名认证|手机认证|弹框位置"></div>



	<!-- 实名认证弹框 start -->
	<!--实名认证 certIdentity.js -->
	<div class="real-name show-real-name">
		<div class="topper clearfix">
			<span class="fl fs_18">实名认证</span> <a class="fr plus_c"
				onClick="close_RealName()"></a>
		</div>
		<!--初始状态-->
		<div class="middle">
			<div class="sm01 fs_16 mb_10">为保障账户和资金安全，建议您完成实名认证</div>
			<table class="table_2 no3">
				<tr>
					<th>真实姓名：</th>
					<td><input type="text" class="input_all" name="realname"
						id="realname" /></td>
				</tr>
				<tr>
					<th>身份证号：</th>
					<td><input type="text" class="input_all" name="identity"
						id="identity" /> <br>
					<span class="prompt_2 error_2 msg-real-name" style="display: none;"><i></i></span>
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<button class="btn btnSize_1 btn_blue mr_5" onclick="save();"
							id="boxIdentityBtn">认证</button> <span style="margin-left: 15px;">非大陆人士<a
							href="/member/realnameInformation.do" class="blue pl10 showNext">请点击这里</a></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--成功状态-->
	<div class="real-name mini success-real-name">
		<div class="topper clearfix">
			<span class="fl fs_18"></span> <a class="fr"
				onClick="close_RealNameSuc()"></a>
		</div>
		<div class="middle">
			<div class="content">
				<i class="ico_all size24 img_true24"></i><span class="fs_16 pl10">实名认证已成功</span>
			</div>
			<div class="btnbox">
				<input type="button" class="btn btnSize_1 btn_blue" value="确认"
					onClick="close_RealNameSuc()">
			</div>
		</div>
	</div>
	<!-- 实名认证弹框 end -->
	<!--zhuzy 黑名单start-->
	<input type="hidden" id="blackUser" value="0" />

	<!--zhuzy 黑名单end-->


	<!-- 易连相关 start-->
	<div class="panelbox wid_w870  refreshPage" style="display: none;"
		id="yilian_instruction1">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">银联语音支付介绍</span> <span
					class="panelclose nwd_icon nwd_icon_close pop-close"
					onClick="ylPay.hideYiLianInstruction(1)"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
				<div class="container">
					<div class="yl_tipsDiv clearfix">
						<dl class="clearfix">
							<dt>
								<img
									src="http://static2.niwodai.com/Public/Static/201404/images/yl_pic01.png" />
							</dt>
							<dd>
								<p>
									<span><em
										style="line-height: 22px; display: block; padding-top: 20px; text-align: left; padding-left: 8px;">什么是<br>语音支付？
									</em></span> <label>语音支付是中国银联系统全新的支付方式，保证用户信息安全。首次充值前需要对银行卡进行认证，认证转账金额随机（1~5元），认证后及时返还原卡，<em>不收取任何费用。</em></label>
								</p>
								<p>
									<span>优势</span> <label><i>&nbsp;</i>无需开通网银、无卡支付等功能；<br>
										<i>&nbsp;</i>无需在银行端预留手机号；<br> <i>&nbsp;</i>充值限额更高。 </label>
								</p>
								<p>
									<span>充值流程</span> <label> <i>&nbsp;</i>
										首次充值需接听银联（广州）电话：020-96585；<br> <i>&nbsp;</i>
										按语音提示，输入银行卡密码;<br> <i>&nbsp;</i> 通话结束，系统为您自动完成充值；<br>
										<img
										src="http://static3.niwodai.com/Public/Static/201404/images/safe_deng.png">
										再次充值凭短信验证码即可完成，操作更便捷。
									</label>
								</p>
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="panelbox wid_w870 refreshPage" id="yilian_instruction2">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
				<span class="text">充值确认</span> <span
					class="panelclose nwd_icon nwd_icon_close pop-close"
					onClick="ylPay.hideYiLianInstruction(2)"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
				<div class="container">
					<div class="yl_overDiv clearfix">
						<dl>
							<dt>
								<img
									src="http://static4.niwodai.com/Public/Static/201404/images/yl_pic01.png" />
							</dt>
							<dd>
								<p class="safe-tip-p">您正使用银联语音支付，仅首次充值需认证银行卡，操作步骤如下：</p>
								<p>
									<span><img
										src="http://static3.niwodai.com/Public/Static/201404/images/safe_tel.png" /></span>
									<label> <strong>1. 接听银联（广州）来电：<b>020-96585</b></strong>
										<em>接听手机：187****4329<br>
											10分钟后仍未收到来电，说明您的认证未通过，请正确填写银行卡等信息。
									</em>
									</label>
								</p>
								<p>
									<span><img
										src="http://static4.niwodai.com/Public/Static/201404/images/safe_safe.png" /></span>
									<label> <strong>2. 按语音提示，输入银行卡密码</strong> <em>语音提示：“您好！这里是银联语音支付系统，您有一个待支付的订单，商户名称快捷支付，理财商户交易，名称为你我贷，充值<i
											id="ylVerifyAmount" style="font-weight: bold;"></i>元，首次充值请开通认证，认证转账金额随机（1~5元，稍后将及时返还原卡，<i
											style="color: #f60;">不收取任何费用</i>）。请输入6位借记卡密码，以#号键结束。”
									</em> <em><img
											src="http://static3.niwodai.com/Public/Static/201404/images/safe_deng.png">
											输入密码错误2次后，将自动挂机，请重新充值。</em>
									</label>
								</p>
								<p>
									<span><img
										src="http://static4.niwodai.com/Public/Static/201404/images/safe_ok.png" /></span>
									<label> <strong>3. 通话结束，稍后将短信通知您充值结果</strong> <em>如长时间未收到短信通知，请联系客服：400-7910-888</em>
									</label>
								</p>
								<div class="btn_con clearfix">
									<p>
										<a href="javascript:void(0)" id="ylLinkSuccess"
											class="btn btn btn_sizeauto btn_bgf60">已完成充值</a> <a
											href="/pay/yilian/rechargeFail.do"
											class="btn btn_sizeauto btn_bord_blue">付款遇到问题</a>
									</p>
									<p>
										<a href="/member/rechargeStep.do" class="blue">&gt;&gt;返回选择其他支付方式</a>
									</p>
								</div>
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 易连相关 end-->

	<script type="text/javascript">
		//同盾统计脚本
		var nwdtokenId = 'FD6A60918B56774DD3180CC150B';
		var tokenId = 'NW' + nwdtokenId;
		var _fmOpt = _fmOpt || {
			partner : 'niwodai',
			appName : 'niwodai',
			token : tokenId
		};
		(function() {
			var cimg = new Image(1, 1);
			cimg.onload = function() {
				_fmOpt.imgLoaded = true;
			};
			cimg.src = "https://fp.fraudmetrix.cn/fp/clear.png?partnerCode=niwodai&appName=niwodai&tokenId="
					+ tokenId;
			var fp = document.createElement('script');
			fp.type = 'text/javascript';
			fp.async = true;
			fp.src = ('https:' == document.location.protocol ? 'https://'
					: 'http://')
					+ 'static.fraudmetrix.cn/fm.js?ver=0.1';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(fp, s);
		})();
	</script>


	<script type="text/javascript">
		$(document).ready(function() {
			rechargeBase.init();
		});
	</script>


	<script>
		var popUp = null;
		seajs.use('popup', function(popup) {
			popUp = popup;
		});
		//快捷支付新增银行卡
		$(document).on('click', '#addBank-btn', function() {
			var attr = new Attention(popUp, '#addBank-pop-kj', true);
			attr.event();
		});
		$(".pop-close").click(function() {
			popUp.hideLayer($("#addBank-pop-kj"))
		});
		//快捷支付更多银行
		$(document).on('click', '#morebank-btn-kj', function() {
			var attr = new Attention(popUp, '#addBank-pop-kj', true);
			attr.event();
		});
		$(".pop-close").click(function() {
			popUp.hideLayer($("#addBank-pop-kj"))
		});
		//通过银行卡号查找银行
		$(document).on('click', '#by-Cardbtn', function() {
			var attr = new Attention(popUp, '#bycard-pop-kj', true);
			attr.event();
		});
		$(".pop-close").click(function() {
			popUp.hideLayer($("#bycard-pop-kj"))
		});

		$(".pop-close").click(function() {
			popUp.hideLayer($("#plusBankContainer1"))
		});
		$(".pop-close").click(function() {
			popUp.hideLayer($("#yilian_instruction1"))
		});
		$(".pop-close").click(function() {
			popUp.hideLayer($("#yilian_instruction2"))
		});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			_bank($(".user_bank"))
			_bank($(".findCard "))
		});

		/**
		
		//选择更多银行下拉框
		var csDt=$('.citySelect dl dt');
		var csDd=$('.citySelect dl dd a');
		csDt.on('click',function(){
			var oC=$(this).siblings('dd');
			if(oC.is(':visible')){
				oC.hide();
			}else{
				oC.show();
			}
			return false;
		});
		
		$(document).on('click',function(){
			csDt.siblings().hide();
		});
		csDd.each(function(){
			$(this).click(function(){
				var _index=($(this).html());
				csDt.html('<i class="icon_base icon_base_selectdown mar_r10 mar_l5 fr mar_t10"></i><span class="sp pad_l10 fc_blue">'+_index+'</span>');
				setTimeout(function(){
						csDt.siblings('dd').hide();
						$(".unknows").show();
			    		$(".inputbank").hide();
						$(".allbank").show();
				});
			});
		});
		 **/
	</script>





	<script type="text/javascript">
		$(function() {

			$(".banktrNewUser .bankShowLi").click(function() {

				var selectedLi = $(this);//当前选中的  li
				var selectedUl = $(this).closest("ul");//当前选中的ul
				var morebankli = $(selectedUl).find(".morebankli");//更多银行  后面需要append到 ul中

				$(selectedUl).empty();//清空ul						
				$(selectedUl).append(selectedLi);
				$(selectedUl).append(morebankli);

			});

			$(".banktrOldUser .bankShowLi").click(
					function() {
						//获取信息
						$(this).addClass("curronli").siblings().removeClass(
								"curronli");

						$("#selectedBank img").attr("src",
								$(this).find("img").attr("src"));
						$("#selectedBank input").attr("id",
								$(this).find("input").attr("id"));
						$("#selectedBank input").attr("value",
								$(this).find("input").attr("value"));
						$("#selectedBank .showBankidSpan").text(
								$(this).find(".showBankidSpan").text());

						$('#myotherbank').hide();

					});
		});

		function clearCurronliStyle(ele) {
			$(ele).removeClass("curronli");
		}
	</script>

	<script type="text/javascript">
		//网银支付
		$(function() {
			$(".new-shortcut-wy li").bind(
					'click',
					function() {
						$(this).addClass('curronli').siblings().removeClass(
								'curronli');
						var newselectbank = $(this).children().children().attr(
								'src');
						//alert(newselectbank)
						$('#newbank').attr('src', newselectbank)
					});
		});
	</script>

	<script type="text/javascript">
		$(function() {
			$("#scroll_fir").mouseScrollCont({
				left : 0,
				top : 5
			});
		});
	</script>

</body>
</html>
