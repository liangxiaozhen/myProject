﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico">
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>static/h-ui.admin/css/style.css" />
<script type="text/javascript">var basePath ="<%=basePath%>";</script>

<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>干将投资理财平台</title>
<meta name="keywords" content="干将投资,理财">
<meta name="description" content="干将投资理财平台。">
</head>
<body>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">干将理财平台</a>
				<a class="logo navbar-logo-m f-l mr-10 visible-xs"
					href="/aboutHui.shtml"></a> <span
					class="logo navbar-slogan f-l mr-10 hidden-xs"></span> <a
					aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
					href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav">
					<ul class="cl">
						<!-- <li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
						</ul>
					</li> -->
					</ul>
				</nav>
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li>欢迎您 ， &nbsp;</li>
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">${user.loginname}<i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:void(0)" onclick="logout(this)">退出</a></li>
							</ul></li>
						<!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt>
					<i class="Hui-iconfont">&#xe616;</i> 用户信息<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a
							_href="<%=basePath%>user/userBaseInfo/toBaseInfo.action"
							data-title="基本信息" href="javascript:;">基本信息</a></li>
						<li><a _href="<%=basePath%>/user/securitycenter/list.action"
							data-title="安全中心" href="javascript:;">安全中心</a></li>
						<li><a
							_href="<%=basePath%>user/bankcard/queryBankCard.action"
							data-title="银行卡管理" href="javascript:;">银行卡管理</a></li>
						<li><a _href="member-scoreoperation.html" data-title="我的消息"
							href="javascript:;">我的消息</a></li>
						<li><a _href="<%=basePath%>/userAddrss/toAddress.action"
							data-title="奖品邮寄管理" href="javascript:;">奖品邮寄地址</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i>站外奖品<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<li><a
						_href="<%=basePath%>user/outsideAward/queryAwardInfo.action"
						data-title="用户奖品信息" href="javascript:;">站外奖品信息</a></li>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i>我的资金<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="<%=basePath%>/user/repayMent/list.action"
							data-title="还款记录" href="javascript:void(0)">还款记录</a></li>
						<li><a
							_href="<%=basePath%>user/userAccount/accountBalance.action"
							data-title="资金统计" href="javascript:void(0)">资金统计</a></li>
						<li><a
							_href="<%=basePath%>user/userAccInExRecord/queryAll.action"
							data-title="资金明细" href="javascript:void(0)">收支明细</a></li>
						<%-- <li><a
							_href="<%=basePath%>user/userRecharge/rechargeList.action"
							data-title="充值" href="javascript:void(0)">充值</a></li>
						<li><a --%>
						 <li><a
							_href="<%=basePath%>moneymoremore/userRecharge/rechargeList.action"
							data-title="充值" href="javascript:void(0)">充值</a></li>
						<li><a 
							_href="<%=basePath%>user/userRecharge/remitRecharge.action"
							data-title="汇款充值" href="javascript:void(0)">汇款充值</a></li>
						<li><a
							_href="<%=basePath%>/user/userwithdrawscash/withdraw.action"
							data-title="提现" href="javascript:void(0)">提现</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i>我的积分<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a
							_href="<%=basePath%>user/userAccount/accountBonusPoints.action"
							data-title="我的积分" href="javascript:void(0)">我的积分</a></li>
						<li><a
							_href="<%=basePath%>user/userPoints/queryMyBonusPoints.action"
							data-title="积分明细" href="javascript:void(0)">积分明细</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i>我的红包<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a
							_href="<%=basePath%>user/userAccount/accountRedEnvelope.action"
							data-title="我的余额" href="javascript:void(0)">我的余额</a></li>
						<li><a
							_href="<%=basePath%>user/userPacket/queryMyRedPacket.action"
							data-title="红包明细" href="javascript:void(0)">红包明细</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe613;</i>我的使用券<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a
							_href="<%=basePath%>user/InterestRateCoupon/queryMyVoucher.action"
							data-title="使用券明细" href="javascript:void(0)">使用券明细</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-product">
				<dt>
					<i class="Hui-iconfont">&#xe620;</i> 我的投资<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a
							_href="<%=basePath%>user/initiativeBid/queryTender.action"
							data-title="我要理财" href="javascript:void(0)">我要理财</a></li>
						<li><a
							_href="<%=basePath%>user/autoTender/autoTenderList.action"
							data-title="自动投标" href="javascript:void(0)">自动投标</a></li>
						<li><a
							_href="<%=basePath%>user/tender/queryMyTenderRecord.action"
							data-title="投资记录" href="javascript:void(0)">投资记录</a></li>
						<li><a
							_href="<%=basePath%>user/undertakedebtattorn/obligatoryright.action"
							data-title="债权" href="javascript:void(0)">债权</a></li>
						<li><a
							_href="<%=basePath%>user/gfundsIntNotes/queryMyGfundsIntNotes.action"
							data-title="标的站岗利息记录" href="javascript:void(0)">标的站岗利息记录</a></li>
						<li><a _href="<%=basePath%>user/failtcrecord/query.action"
							data-title="标的流标补偿记录" href="javascript:void(0)">标的流标补偿记录</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-comments">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> 我的借款<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a
							_href="<%=basePath%>loan/seleceBaseByid.action?id=${user.id}"
							data-title="借款人审核" href="javascript:;">借款人审核</a></li>
						<li><a _href="<%=basePath%>user/loan/isaudit.action?id=${user.id}"
							data-title="借款申请" href="javascript:void(0)">借款申请</a></li>
						<li><a
							_href="<%=basePath%>user/loan/selectbaseid.action?id=${user.id}"
							data-title="借款记录" href="javascript:void(0)">借款记录</a></li>
						<li><a
							_href="<%=basePath%>user/loan/jumpusercommon.action?baseid=${user.id}"
							data-title="公共资料" href="javascript:void(0)">公共资料</a></li>
						<li><a _href="<%=basePath%>user/userLoanApp/list.action"
							data-title="借款记录" href="javascript:void(0)">借款记录</a></li>

					</ul>
				</dd>
			</dl>
			<dl id="menu-product">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> 我的推广<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="<%=basePath%>user/userPromo/userPromo.action"
							data-title="推广信息" href="javascript:;">推广信息</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-product">
				<dt>
					<i class="Hui-iconfont">&#xe622;</i> 会员专区<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a _href="<%=basePath%>user/userGrade/userGrade.action"
							data-title="我的会员" href="javascript:;">我的会员</a></li>
					</ul>
				</dd>
			</dl>
		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面"
						data-href="<%=basePath%>user/welcome.action">我的桌面</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0"
					src="<%=basePath%>user/welcome.action"></iframe>
			</div>
		</div>
	</section>
	<script type="text/javascript"
		src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
	<!-- <script type="text/javascript" src="<%=basePath%>lib/layer/2.1/layer.js"></script> -->
	<script type="text/javascript"
		src="<%=basePath%>static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript">
 /*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
 }
 /*退出登录*/
 function logout(obj){
	 var flag = confirm("您确定退出吗？");
	 if(flag){
 		 $.ajax({
			 type:"post",
			 url:basePath+"/user/logout.action",
			 success:function(data){
				 if(data=="success"){
					 window.location.href=basePath+"/user/tologin.action";
				 }
			 }
		 });
	 }
 }
</script>
	<!-- <script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s)})();
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script> -->
</body>
</html>