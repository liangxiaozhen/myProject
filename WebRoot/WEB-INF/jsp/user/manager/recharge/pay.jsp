<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="robots" content="all">

<title>账户详情_你我贷会员中心</title>
<meta name="keywords" content="你我贷、借出、借款">
<meta name="description" content="你我贷">
<meta http-equiv="X-UA-Compatible" content="IE=8"> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/pay.css" rel="stylesheet" type="text/css"> 

</head>


<body>
	<!-- 头部 -->
	<!-- 内容 -->
	<div class="top">
		<div class="grid-990">
			<div class="logo"></div>
		</div>
	</div>
	<!-- 2 -->
	<div class="orderInfo model" id="orderInfo">
		<span class="orderTip_icon">&nbsp;</span>
		<p class="commodity_info"></p>
		<p class="amount_info"><span class="amount" id="show_orderAmt">${money}</span>元</p>
		<script type="text/javascript">
			
		</script>
		<input id="OrderAmt" type="hidden" name="OrderAmt" value="000000000200">
		<input id="pt" type="hidden" name="pt" value="2">
		<div class="clear"></div>
		<div class="detail" id="orderInfo_detail">
		<div style="float:left;width:80%">
			<p>订单号：${orderno}</p>
			<input type="hidden" id="orderno" value="${orderno}" style="width: 500px">
			<p>商户名： 徽商银行股份有限公司（B2C）</p>
			<p>交易币种： 人民币</p>
		</div>
		</div>
		
		<div class="clear"></div>
	</div>
	<!--  -->
	<div id="paymode" class="grid-990" style="display:none">
		<label><input id="apt" type="radio" name="PayType" value="0" checked="checked">全额支付</label>
		<label><input id="spt" type="radio" name="PayType" value="1">组合支付</label>
	</div>
	<div id="splitpay" class="grid-990 hide" style="">
		<div id="splitpay_info" class="model">
			<span class="splitpay_amount_label">请输入本次分笔交易金额</span>
			<input id="TransAmt" name="TransAmt" class="splitpay_amount">
			
			<span class="splitpay_amount_label">元</span>
			<a href="#paymode"><button type="button" class="splitpay_amount_btn">确认</button></a>
			<table class="splitpay_his_tab">
				
			</table>
			<div id="split_his_detail_tab" class="hide">
				<table class="splitpay_his_tab detailTab">
					

				</table>
			</div>
		</div>
		<!--splitpay_info-->
		<div id="splitpay_progress" class="model">
			<table>
				<tbody>
					<tr>
						<td>
							已支付：<span class="payed amount_int" id="hasPayAmt">0.00</span>元
							<script type="text/javascript">
								
							</script>
							<input id="PayAmt" type="hidden" name="PayAmt" value="0">
						</td>
						<td class="txtr">
							待支付：<span class="unpayed amount_int" id="unPayAmt">2.00</span>元
							<script type="text/javascript">
								
							</script>
							<input id="UnPayAmt" type="hidden" name="UnPayAmt" value="200">
						</td>
					</tr>
				</tbody>
			</table>
			<div class="splitpay_progress_back">
				<div id="progress_left"></div>
				<div id="progress_middle" style="width: 0px;"></div>
				<div id="progress_right"></div>
			</div>
			<div class="fr tip">
				<span>请在输入分笔交易金额后选择支付方式完成支付</span>
			</div>
		</div>

		<div id="worn" class="clear grid-990">
		请在网上支付前仔细核对您的订单信息是否为您<span class="highlight">确认的支付信息</span>
		</div>
	</div>
	<!-- allpay -->
	<div id="allpay" class="grid-990" style="">
		<div id="worn" class="clear grid-990">
			请在网上支付前仔细核对您的订单信息是否为您<span class="highlight">确认的支付信息</span>
		</div>
		
		<div id="pay_channel" class="grid-990">
			<div class="left">
				<ul>
					<li class="on" id="left0001"><input type="hidden" name="TranType" value="${sum}" id="TranType"><a href="javascript:void(0);" onclick="showDiv('0001');">个人网银</a>
					</li>
				</ul>
			</div>
			<div id="right0001" class="right peb" style="display: block;">
				<div class="bank_icons show">
					<ul> 
						<li name="700000000000001" class="700000000000001 on" onclick="showBankInfo(this);" style="background-position: 0px -33px;">
							<input type="radio" value="700000000000001" class="hide"><div class="selectedDiv"></div>
						</li>
						<li name="700000000000002" class="700000000000002" onclick="showBankInfo(this);" style="background-position: -135px 0px;">
							<input type="radio" value="700000000000002" class="hide">
						</li>
					
						<li name="700000000000003" class="700000000000003" onclick="showBankInfo(this);" style="background-position: -270px 0px;">
							<input type="radio" value="700000000000003" class="hide">
						</li>
						<li name="700000000000006" class="700000000000006" onclick="showBankInfo(this);" style="background-position: -405px -33px;">
							<input type="radio" value="700000000000006" class="hide">
						</li>
					
						<li name="700000000000011" class="700000000000011" onclick="showBankInfo(this);" style="background-position: -405px 0px;">
							<input type="radio" value="700000000000011" class="hide">
						</li>
					
						<li name="700000000000008" class="700000000000008" onclick="showBankInfo(this);" style="background-position: -405px -66px;">
							<input type="radio" value="700000000000008" class="hide">
						</li>
					
						<li name="700000000000015" class="700000000000015" onclick="showBankInfo(this);" style="background-position: 0px -99px;">
						<input type="radio" value="700000000000015" class="hide">
						</li>
					</ul>
				</div>
				<!-- <form class="pebPayment" action="" method="post"> -->
					<button type="submit" class="bank_pay_btn" style="display: block;"></button>					
					<!-- <input type="hidden" name="TranType" value="0001">
					<input type="hidden" name="BankInstNo" value="700000000000001">
					<input type="hidden" name="TransAmt" value="000000000200">
					<input type="hidden" name="AcqSeqId" value="0000000179265725">
					<input type="hidden" name="AcqDate" value="20170327">
					<input type="hidden" name="PageSignature" value="40d9f953d45ddbc79f29d12a0507003f">
					<input type="hidden" name="BusiType" value="0001">
					<input type="hidden" name="PayType" value="0">
				</form> -->
				<div class="bankInfo0001" style="margin-top:10px;">
					<table id="quota">
						<thead>
							<tr>
								<td><div class="serv_tel"><span class="serv_tel_text">客服电话：</span><br><span id="hotline">95566</span></div></td>
								<td>卡种</td>
								<td>单笔限额（元）</td>
								<td>每日限额（元）</td>
								<td></td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td rowspan="2" class="t1"><div class="bank_icon b700000000000001 icon_center" style="width: 135px; background-position: 0px -33px;"></div></td>
								<td>借记卡</td>
								<td>5万元</td>
								<td>10万元</td>
								<td class="marginRight"></td>
							</tr>
							<tr>
								<td>信用卡</td>
								<td>5000元或以持卡人信用额度为准</td>
								<td>10万元或以持卡人信用额度为准</td>
								<td class="marginRight"></td>
							</tr>
						</tbody>
					</table>
				</div>
		   </div>
		   <!--right0001-->
		</div><!--pay_channel-->
	</div>
	<!-- 广告 -->
	<div id="ads" class="grid-990">
		<div class="ads_frame">
			<div class="ad model">
				<a href="javascript:;">
					<img src="${basePath}/resources/resource/Images/ad01.jpeg">
				</a>
			</div>
			<div class="ad model">
				<a href="javascript:;">
					<img src="${basePath}/resources/resource/Images/ad02.jpg">
				</a>
			</div>
			<div class="ad model">
				<a href="javascript:;">
					<img src="${basePath}/resources/resource/Images/ad05.jpg">
				</a>
			</div>
		</div>
	</div>
	<!-- 底部 -->
	<p style="text-align: center;padding: 30px 0">Copyright © 2016 干将网贷　版权所有；杜绝借款犯罪，倡导合法借贷，信守借款合约</p>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var basepath = "${basePath}";
</script>
<script type="text/javascript">
	//点击到网上银行充值
	$(document).on("click","button.bank_pay_btn",function(){
		var name= $("#right0001 li.on").attr("name");//获取银行代码
		var money = $("#show_orderAmt").text();//充值金额
		var sum = $("#TranType").val();//充值类型 网银为1
		var orderno = $("#orderno").val(); //充值订单号
		location.href="${basePath}/user/userRecharge/RechargeConfirmation.action?name="+name+"&money="+money+"&sum="+sum+"&orderno="+orderno;	 
	});
	var banks = {
	"700000000000001":{"position":{"x":0,"y":-33},"name":"中国银行","iconWidth":135},	
	"700000000000002":{"position":{"x":-135,"y":0},"name":"中国农业银行","iconWidth":135},
	"700000000000003":{"position":{"x":-270,"y":0},"name":"中国工商银行","iconWidth":135},
	"700000000000004":{"position":{"x":0,"y":0},"name":"中国建设银行","iconWidth":135},
	"700000000000005":{"position":{"x":-135,"y":-99},"name":"中国光大银行","iconWidth":135},
	"700000000000006":{"position":{"x":-405,"y":-33},"name":"交通银行","iconWidth":135},
	'700000000000007':{"position":{"x":-270,"y":-99},"name":"上海浦东发展银行","iconWidth":135},
	"700000000000008":{"position":{"x":-405,"y":-66},"name":"中国民生银行","iconWidth":135},
	"700000000000009":{"position":{"x":-405,"y":-99},"name":"兴业银行","iconWidth":135},
	"700000000000010":{"position":{"x":0,"y":-66},"name":"招商银行","iconWidth":135},
	"700000000000011":{"position":{"x":-405,"y":0},"name":"中国邮政储蓄银行","iconWidth":135},
	"700000000000012":{"position":{"x":-0,"y":-132},"name":"广发银行","iconWidth":135},
	"700000000000013":{"position":{"x":-270,"y":-33},"name":"中信银行","iconWidth":135},
	"700000000000014":{"position":{"x":-270,"y":-66},"name":"平安银行","iconWidth":135},
	"700000000000015":{"position":{"x":0,"y":-99},"name":"华夏银行","iconWidth":135},
	"700000000000016":{"position":{"x":-135,"y":-33},"name":"北京银行","iconWidth":135},
	"700000000000017":{"position":{"x":0,"y":-297},"name":"UPOP","iconWidth":135},
	"700000000000018":{"position":{"x":-270,"y":-132},"name":"渤海银行","iconWidth":135},
	"700000000000019":{"position":{"x":-135,"y":-132},"name":"深圳发展银行","iconWidth":135},
	"700000000000020":{"position":{"x":0,"y":-231},"name":"东亚银行","iconWidth":135},
	"700000000000021":{"position":{"x":-135,"y":-165},"name":"杭州银行","iconWidth":135},
	"700000000000022":{"position":{"x":-270,"y":-396},"name":"青岛银行","iconWidth":135},
	"700000000000023":{"position":{"x":-405,"y":-165},"name":"上海农商行","iconWidth":135},
	"700000000000024":{"position":{"x":-405,"y":-297},"name":"北京农商行","iconWidth":135},
	"700000000000025":{"position":{"x":-270,"y":-165},"name":"上海银行","iconWidth":135},
	"700000000000026":{"position":{"x":0,"y":-297},"name":"UPOP 2.0","iconWidth":135},
	"700000000000027":{"position":{"x":-540,"y":-528},"name":"御航宝","iconWidth":135},
	"700000000000028":{"position":{"x":-405,"y":-528},"name":"易商旅","iconWidth":135},
	"700000000000029":{"position":{"x":-540,"y":-495},"name":"南航易航宝","iconWidth":135},
	"700000000000030":{"position":{"x":-405,"y":-528},"name":"国航易商旅","iconWidth":135},
	"700000000000031":{"position":{"x":-405,"y":-264},"name":"万事达国际-建行外卡支付","iconWidth":135},
	"700000000000032":{"position":{"x":-540,"y":0},"name":"福建海峡银行","iconWidth":135},	
	"700000000000033":{"position":{"x":-540,"y":-33},"name":"富滇银行","iconWidth":135},
	"700000000000034":{"position":{"x":-540,"y":-66},"name":"广州银行","iconWidth":135},
	"700000000000035":{"position":{"x":-540,"y":-99},"name":"汉口银行","iconWidth":135},
	"700000000000036":{"position":{"x":-405,"y":-132},"name":"恒发银行","iconWidth":135},
	"700000000000037":{"position":{"x":-540,"y":-132},"name":"河北银行","iconWidth":135},
	"700000000000038":{"position":{"x":0,"y":-165},"name":"浙商银行","iconWidth":135},
	"700000000000039":{"position":{"x":-540,"y":-165},"name":"鹤壁银行","iconWidth":135},
	"700000000000040":{"position":{"x":0,"y":-198},"name":"星辰银行","iconWidth":135},
	"700000000000041":{"position":{"x":-135,"y":-198},"name":"IBK企业银行(中国)","iconWidth":135},
	"700000000000042":{"position":{"x":-270,"y":-198},"name":"大新银行(中国)","iconWidth":135},
	"700000000000043":{"position":{"x":-405,"y":-198},"name":"花旗银行","iconWidth":135},
	"700000000000044":{"position":{"x":-540,"y":-198},"name":"徽商银行","iconWidth":135},
	"700000000000045":{"position":{"x":-135,"y":-231},"name":"渣打银行","iconWidth":135},
	"700000000000046":{"position":{"x":-270,"y":-231},"name":"新韩银行","iconWidth":135},
	"700000000000047":{"position":{"x":-405,"y":-231},"name":"大华银行","iconWidth":135},
	"700000000000048":{"position":{"x":-540,"y":-231},"name":"晋城银行","iconWidth":135},
	"700000000000049":{"position":{"x":0,"y":-264},"name":"法国兴业银行","iconWidth":135},
	"700000000000050":{"position":{"x":-135,"y":-264},"name":"南洋商业银行","iconWidth":135},
	"700000000000051":{"position":{"x":-270,"y":-264},"name":"韩亚银行","iconWidth":135},
	"700000000000052":{"position":{"x":-540,"y":-264},"name":"晋中市商业银行","iconWidth":135},
	"700000000000053":{"position":{"x":-135,"y":-297},"name":"美国运通公司","iconWidth":135},
	"700000000000054":{"position":{"x":-270,"y":-297},"name":"万事达国际-农行外卡支付","iconWidth":135},
	"700000000000055":{"position":{"x":-540,"y":-297},"name":"九江银行","iconWidth":135},
	"700000000000056":{"position":{"x":0,"y":-330},"name":"永德银行","iconWidth":135},
	"700000000000057":{"position":{"x":-135,"y":-330},"name":"大连银行","iconWidth":135},
	"700000000000058":{"position":{"x":-270,"y":-330},"name":"德州银行","iconWidth":135},
	"700000000000059":{"position":{"x":-405,"y":-330},"name":"东莞银行","iconWidth":135},
	"700000000000060":{"position":{"x":-540,"y":-330},"name":"漯河银行","iconWidth":135},
	"700000000000061":{"position":{"x":0,"y":-363},"name":"东营银行","iconWidth":135},
	"700000000000062":{"position":{"x":-135,"y":-363},"name":"南京银行","iconWidth":135},
	"700000000000063":{"position":{"x":-270,"y":-363},"name":"宁波银行","iconWidth":135},
	"700000000000064":{"position":{"x":-405,"y":-363},"name":"宁夏银行","iconWidth":135},
	"700000000000065":{"position":{"x":-540,"y":-363},"name":"平顶山银行","iconWidth":135},
	"700000000000066":{"position":{"x":0,"y":-396},"name":"齐鲁银行","iconWidth":135},
	"700000000000068":{"position":{"x":-675,"y":-231},"name":"东海证券","iconWidth":135},
	"700000000000067":{"position":{"x":-135,"y":-396},"name":"齐商银行","iconWidth":135},
	"700000000000069":{"position":{"x":-405,"y":-396},"name":"青海银行","iconWidth":135},
	"700000000000070":{"position":{"x":-540,"y":-396},"name":"曲靖市商业银行","iconWidth":135},
	"700000000000071":{"position":{"x":0,"y":-429},"name":"日照银行","iconWidth":135},
	"700000000000072":{"position":{"x":-135,"y":-429},"name":"重庆三峡银行","iconWidth":135},
	"700000000000073":{"position":{"x":-270,"y":-429},"name":"厦门银行","iconWidth":135},
	"700000000000074":{"position":{"x":-405,"y":-429},"name":"上饶银行","iconWidth":135},
	"700000000000075":{"position":{"x":-540,"y":-429},"name":"盛京银行","iconWidth":135},
	"700000000000076":{"position":{"x":-0,"y":-462},"name":"顺德农商银行","iconWidth":135},
	"700000000000077":{"position":{"x":-135,"y":-462},"name":"苏州银行","iconWidth":135},
	"700000000000078":{"position":{"x":-270,"y":-462},"name":"泰安市商业银行","iconWidth":135},
	"700000000000079":{"position":{"x":-405,"y":-462},"name":"温州银行","iconWidth":135},
	"700000000000080":{"position":{"x":-540,"y":-462},"name":"乌鲁木齐市商业银行","iconWidth":135},
	"700000000000081":{"position":{"x":0,"y":-495},"name":"宜宾市商业银行","iconWidth":135},
	"700000000000082":{"position":{"x":-135,"y":-495},"name":"玉溪市商业银行","iconWidth":135},
	"700000000000083":{"position":{"x":-270,"y":-495},"name":"张家港农村商业银行","iconWidth":135},
	"700000000000084":{"position":{"x":-405,"y":-495},"name":"长沙银行","iconWidth":135},
	"700000000000085":{"position":{"x":0,"y":-528},"name":"中国银联","iconWidth":135},
	"700000000000086":{"position":{"x":-135,"y":-528},"name":"重庆农村商业银行","iconWidth":135},
	"700000000000087":{"position":{"x":-270,"y":-528},"name":"周口银行","iconWidth":135},
	"700000000000088":{"position":{"x":-675,"y":-264},"name":"光大证券","iconWidth":135},
	"700000000000089":{"position":{"x":-135,"y":-66},"name":"江苏省农村信用社联合社","iconWidth":135},
	"700000000000090":{"position":{"x":-405,"y":-561},"name":"湖南省农村信用社联合社","iconWidth":135},
	"700000000000091":{"position":{"x":-540,"y":-561},"name":"海南省农村信用社联合社","iconWidth":135},
	"700000000000092":{"position":{"x":-405,"y":-594},"name":"广西北部湾银行","iconWidth":135},
	"700000000000093":{"position":{"x":-540,"y":-594},"name":"驻马店市商业银行","iconWidth":135},
	"700000000000094":{"position":{"x":-675,"y":0},"name":"成都农村商业银行","iconWidth":135},
	"700000000000095":{"position":{"x":-675,"y":-33},"name":"深圳农村商业银行","iconWidth":135},
	"700000000000096":{"position":{"x":-675,"y":-66},"name":"威海市商业银行","iconWidth":135},
	"700000000000097":{"position":{"x":-675,"y":-99},"name":"江苏银行","iconWidth":135},
	"700000000000098":{"position":{"x":-675,"y":-132},"name":"三门峡市商业银行","iconWidth":135},
	"700000000000099":{"position":{"x":-675,"y":-297},"name":"东方证券","iconWidth":135},
	"700000000000100":{"position":{"x":-675,"y":-165},"name":"宜昌市商业银行","iconWidth":135},
	"700000000000101":{"position":{"x":-675,"y":-198},"name":"尧都农商行","iconWidth":135},
	"700000000000102":{"position":{"x":0,"y":-561},"name":"威士国际组织-建行外卡","iconWidth":135},
	"700000000000103":{"position":{"x":-135,"y":-561},"name":"威士国际组织-农行外卡","iconWidth":135},
	"700000000000104":{"position":{"x":-270,"y":-561},"name":"威士国际组织-中行外卡","iconWidth":135},
	"700000000000106":{"position":{"x":0,"y":-594},"name":"银商+快捷账户支付","iconWidth":135},
	"700000000000107":{"position":{"x":-135,"y":-594},"name":"银商账户支付","iconWidth":135},
	"700000000000108":{"position":{"x":-270,"y":-594},"name":"快捷账户支付","iconWidth":135},
	"700000000000109":{"position":{"x":-675,"y":-330},"name":"广发证券","iconWidth":135},
	"700000000000110":{"position":{"x":-675,"y":-363},"name":"恒泰证券","iconWidth":135},
	"700000000000111":{"position":{"x":-675,"y":-396},"name":"中投证券","iconWidth":135},
	"700000000000112":{"position":{"x":-675,"y":-429},"name":"中信证券","iconWidth":135},
	"700000000000121":{"position":{"x":-675,"y":-563},"name":"珠海农村信用社","iconWidth":135},
	"700000000000122":{"position":{"x":-675,"y":-497},"name":"翼支付(BestPay)","iconWidth":135},
	"700000000000123":{"position":{"x":-675,"y":-530},"name":"中银通(NewCUPSecurePin)","iconWidth":135},
	"700000000000125":{"position":{"x":-675,"y":-596},"name":"商旅平台手机支付(SCWriteOff)","iconWidth":135},
	"700000000000126":{"position":{"x":-675,"y":-462},"name":"上海金融IC卡(ICCardB2C)","iconWidth":135},
	"700000000000132":{"position":{"x":-810,"y":-33},"name":"平安付","iconWidth":135},
	"700000000000133":{"position":{"x":-810,"y":0},"name":"珠海农商银行","iconWidth":135},
	"700000000000134":{"position":{"x":-810,"y":-165},"name":"广东南粤银行","iconWidth":135},
	"700000000000147":{"position":{"x":-810,"y":-66},"name":"集利财富","iconWidth":135},
	"700000000000148":{"position":{"x":-810,"y":-99},"name":"南海农商银行","iconWidth":135},
	"700000000000149":{"position":{"x":-810,"y":-132},"name":"百联集团","iconWidth":135},
	
	"01050000":{"position":{"x":0,"y":0},"name":"中国建设银行","iconWidth":135},
	"03010000":{"position":{"x":-405,"y":-33},"name":"交通银行","iconWidth":135},
	"03100000":{"position":{"x":-270,"y":-99},"name":"上海浦东发展银行","iconWidth":135},
	"01020000":{"position":{"x":-270,"y":0},"name":"中国工商银行","iconWidth":135},
	"03040000":{"position":{"x":0,"y":-99},"name":"华夏银行","iconWidth":135},
	"03010000":{"position":{"x":-405,"y":-33},"name":"交通银行","iconWidth":135},
	"03030000":{"position":{"x":-135,"y":-99},"name":"中国光大银行","iconWidth":135},
	"63030000":{"position":{"x":-135,"y":-99},"name":"中国光大银行","iconWidth":135},
	"03020000":{"position":{"x":-270,"y":-33},"name":"中信银行","iconWidth":135},
	"03050000":{"position":{"x":-405,"y":-66},"name":"中国民生银行","iconWidth":135},
	"03040000":{"position":{"x":0,"y":-99},"name":"华夏银行","iconWidth":135},
	"01040000":{"position":{"x":0,"y":-33},"name":"中国银行","iconWidth":135}
};


	function showBankInfo(obj){
		$(".selectedDiv").hide();
		$("#right0001 li").removeClass("on");
		var now=$(obj).attr("name");

		$(obj).addClass("on");
		$(obj).append('<div class="selectedDiv"></div>');
		var now=$(obj).attr("name");
		
		function icon(){
			var id=$("#quota .t1 .bank_icon").attr("id",now);
			//console.log(banks[now].position.x+"px "+banks[now].position.y+"px");
			$("#now,#quota .t1 .bank_icon").css("background-position",banks[now].position.x+"px "+banks[now].position.y+"px");
			
		}icon()
	};

	//设置银行图标位置

	$("#right0001 .bank_icons li").each(function(){
		var bank=($(this).attr("name"));
		//console.log(bank);
		function setBankIcon(){
			$("."+bank).css("background-position",banks[bank].position.x+"px "+banks[bank].position.y+"px");
		}setBankIcon()
	})

</script>
</body>
</html>