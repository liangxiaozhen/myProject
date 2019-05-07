<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>干将网贷，安全透明灵活的网贷平台、网上投资理财、网上贷款借款、投融资信息中介平台</title>
		<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
		<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
		<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">		
		<link rel="stylesheet" href="${basePath}/resources/resource/Css/index.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/resources/resource/Css/claim.css">
		<script type="text/javascript"></script>
		<style type="text/css">.investCon:hover .invest_list{display: block;}</style>
		<body>
	<div class="header">
		<div class="nwd_top">
			<div class="nwd_main pad_t10 clearfix">
				<span class="fl fs_16 fc_9"><i class="icon icon_tel">&nbsp;</i>客服热线<em class="Numfont mar_l10">&nbsp;</em></span>
				<span class="fr fc_9 mar_l10">市场有风险，投资需谨慎</span>
				<span class="fr"><a href="{:U('Index/index')}" target="_blank" class="link_weixin"><i class="icon icon_weixin">&nbsp;</i></a></span>
				<span class="fr mar_r10"><a href="javascript:()" class="link_weibo mar_r10" target="_blank"><i class="icon icon_weibo">&nbsp;</i>微信客服<img src="${basePath}/resources/resource/Picture/topbg_b14925.png" class="weixinImg"></a></span>
				<span class="fr"><a href="{:U('Index/index')}" class="link_mobile mar_r20" target="_blank"><i class="icon icon_moible">&nbsp;</i>手机客户端<img src="${basePath}/resources/resource/Picture/topbg_b14926.png" usemap="#Map" class="mobileImg">
				<map name="Map">
                  <area shape="rect" coords="154,40,256,153" href="javascript:;" target="_blank">
                  <area shape="rect" coords="424,41,517,150" href="javascript:;" target="_blank">
                </map>
				</a></span>
				  <input type="hidden" value="" id="stok"/>
				  <input type="hidden" value="" id="log_userid">
		          <input type="hidden" value="" id="log_username">
		          	  <span class="fr fc_9" id="login_bt">
					  		您好，<a href="{:U('Log/login')}" class="mar_l10 toplogin"></a>
							<a href="{:U('Log/register')}" id="loginOut" class="mar_r10 mar_l10 toplogin">退出</a>
					  </span>
			</div>
		</div>
		<div class="nwd_menu">
			<div class="nwd_main">
				<h1 class="fl logo mar_t20"><a title="给梦想可能" href="{:U('Index/index')}"><img height="52" alt="干将网贷" src="${basePath}/resources/resource/Images/logo.png"></a></h1>
				<div class="fl slogan" id="headLogo" >
					<a href="{:U('Index/index')}" class="pad_t20"><img src="${basePath}/resources/resource/Picture/slogan.png" align="给梦想可能" /></a>
				</div>
				<div class="fr nwd_navCon  mar_t20">
					<ul class="nav clearfix">
						<li>
							<a title="首页" href="{:U('Index/index')}" rel="nofollow" class="nav_link">首页</a>
						</li>
						<li class="two">
							<div class="investCon">
								<div class="invest_name">
									<a href="{:U('Xiangmu/xiangmu')}"  class="fs_16">我要投资</a>
									<em class="icon_base  icon_base_navedown  mar_l10"></em>
								</div>
								<div class="invest_list">
										<a href="{:U('Xiangmu/financialDetail')}" class="investName_link">嘉财有道</a>
									<a href="{:U('Xiangmu/newPeople')}" class="investName_link">新手专区</a>
									<a href="{:U('Xiangmu/xiangmu')}" class="investName_link">新标专区</a>
									<a href="{:U('Xiangmu/zhuanrang')}" class="investName_link">转让专区</a>
								</div>
							</div>
							
						</li>
						<li class="rela">
							<a title="新手导航" href="{:U('Activity/activity')}" class="nav_link">新手导航 </a>
						</li>
						<li class="two"> 
							<a href="{:U('Disclosure/disclosure_index')}" class="nav_link">信息披露</a> 
						</li>
						<li class="myuserCon" style="margin-right:0;">
							<div class="LedgerCon wid_w180">
								<a class="navphoto fs_16 fl mar_l10" href="{:U('Message/Investors')}" title="我的账户">
								<i class="mar_r5 relative">
								<img id="small-tx" src="${basePath}/resources/resource/Picture/smalltx.png"> 
								<img id="smallImg" alt="头像" src="${basePath}/resources/resource/Picture/touxiang.jpg" class="smallmytxPic">
								</i></a>
								
								<div class="Ledger_name pad_t5">
									<a title="我的账户" href="{:U('Log/login')}" class="fs_16">我的账户<!--<span class="vip_icon step5_head mar_l5"></span>--></a>
									<em class="icon_base  icon_base_navedown mar_l10"></em>
								</div>
								<div class="Ledger_list wid_w180  pad_l20  pad_r20 clearfix">
										<a href="{:U('Message/Investors')}" class="listName_link">消息中心</a>
										<a href="{:U('Message/fundsLog')}" class="listName_link">资金记录</a>
											<a href="{:U('Message/findFinancial')}" class="listName_link">嘉财有道</a>
										<a href="{:U('Message/myacceptList')}" class="listName_link">我的债权</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		//热线电话
        $.get("${basePath}/ui/imagetextsetting/getbyitiname.action?itino=86",function(a){
            var s = JSON.parse(a);
            $(".Numfont.mar_l10").html('&nbsp;'+s.list[0].title)
        })
	</script>
</body>
</html>