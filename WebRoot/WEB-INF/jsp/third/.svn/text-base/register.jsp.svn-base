<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- <%@include file="/WEB-INF/jsp/common/taglib.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link>
<title>用户注册 - 干将网贷</title>
<script type="text/javascript"
	src="<%=basePath%>resources/Js/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/Js/appmeasurement-1.2.1-min.js"></script>
<link
	href="<%=basePath%>resources/Css/common.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=basePath%>resources/Css/sea.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=basePath%>resources/Css/style.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=basePath%>resources/Js/jquery-1.11.3.min.js"></script>

<style type="text/css">
a:active {
	star: expression(this.onFocus = this.blur ())
}

a:focus, button:focus {
	outline: 0;
	-moz-outline: 0
}

:focus {
	outline: 0
}

body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form,
	fieldset, input, textarea, p, blockquote, th, td, img, area, param {
	margin: 0;
	padding: 0
}

html, body {
	height: 100%
}

select {
	*behavior: url("select.htc")
}

ol, ul {
	padding-left: 0;
	list-style-type: none;
	list-style: none
}

img {
	border: 0;
	display: inline-block;
	vertical-align: middle
}

th {
	font-style: normal;
	font-weight: normal
}

h1, h2, h3, h4, h5, h6 {
	font-size: 100%;
	font-weight: normal
}

address, caption, cite, code, dfn, em, th, var, strong {
	font-style: normal;
	font-weight: normal
}

input {
	display: inline-block;
	vertical-align: middle
}

fieldset {
	border: 0
}

legend {
	display: none
}

body {
	font-family: \5FAE\8F6F\96C5\9ED1, arial, sans-serif;
	color: #333;
	-webkit-text-size-adjust: none;
	font-size: 14px;
	line-height: 20px
}

.title-1 {
	line-height: 30px;
	font-size: 20px;
	text-align: center;
	margin-bottom: 20px
}

.title-2 {
	line-height: 30px;
	font-size: 18px;
	font-weight: 600
}

.number {
	height: 24px;
	text-align: right;
	padding-right: 10%;
	margin-bottom: 30px
}

#embed-captcha {
	width: 300px;
	margin: 0 auto;
}

.show {
	display: block;
}

.hide {
	display: none;
}

#notice {
	color: red;
}
/* 以下遮罩层为demo.用户可自行设计实现 */
#mask {
	display: none;
	position: fixed;
	text-align: center;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	overflow: auto;
}
/* 可自行设计实现captcha的位置大小 */
.popup-mobile {
	position: relative;
}

#popup-captcha-mobile {
	position: fixed;
	display: none;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	-webkit-transform: translate(-50%, -50%);
	z-index: 9999;
}
#registerSecondStepsInit{
	position: relative;
}
.right_all{
	position: absolute;
    top: 0;
    right: 0;
}
.right_all h3{margin: 10px;}
.color{
	    width: 200px;
        padding: 13px 5px;
    color: #fff;
    border-radius: 6px;
    -moz-border-radius: 6px;
-ms-border-radius: 6px;
-o-border-radius: 6px;
cursor: pointer;
margin: 10px;
}
.color.color1{
	background: #42adff;
}
.color.color2{
	background: #4cd974;
}
.color.color3{
	background: #fd6e3c;
}
.color span{    padding: 0 10px 0 50px;}

</style>
<script type="text/javascript">
	$(function() {
		/*
		$(".tuiarrow").click(function() {
			$(".code_box").toggle();
		});
		 */
	});
</script>
</head>
<body>
	<!--head-->

	<div class="clearfix simpleHead01">
		<div class="fl">
			<a href="http://test.ganjiangps.com"><img
				src="<%=basePath %>resources/Images/logo.png"
				width="173" height="57" alt="干将网贷" /></a><span>注册</span>
		</div>
		<div class="fr">
			<span class="fc_6">已有帐号？<a href="<%=basePath %>user/tologin.action"
				class="blue">立即登录</a></span>
		</div>
	</div>
	<!--head end-->

	<div class="main page147">
		<div class="clearfix fluid">
			<div class="module padding">
				<div class="stepX stepX_3">
					<ul class="active">
						<li class="sz"><span>1</span></li>
						<li class="sm">填写帐户信息</li>
					</ul>
					<ul class="">
						<li class="sz"><span>2</span></li>
						<li class="sm">手机验证</li>
					</ul>
					<ul>
						<li class="sz"><span>3</span></li>
						<li class="sm">注册成功</li>
					</ul>
				</div>
				<div class="content">
					<form action="registerSecondSteps.action" method="post"
						class="nwd-formUi" id="registerSecondStepsInit">
						<input type="hidden" name="num" id="num" />
						<!-- 左侧 -->
						<table class="k touzi01">
							<tr>
								<th style="padding-top: 20px; padding-bottom: 5px;"></th>
								<td style="padding-top: 20px; padding-bottom: 5px;"><span
									class="fc_orange">注册就送668元红包,关注微信公众号“干将网贷投资”，登陆再领20元红包</span></td>
							</tr>
							<tr>
								<th>用户名</th>
								<td>
									<div class="inputOut">
										<input type="text" class="input_b" id="uname" name="uname"
											maxlength="15"> <span id="unameMSG"></span>
									</div>
								</td>
							</tr>
							<tr>
								<th>密码</th>
								<td>
									<div class="inputOut">
										<input type="password" class="input_b " id="pwd" name="pwd"
											maxlength="20" onkeyup="pwStrength(this.value)"
											onblur="pwStrength(this.value)"> <span id="pwdMSG"></span>
									</div>
									<div class="mimaout" id="mima1" style="display: none">
										<span class="mima2"> <em id="weak">弱</em><em
											id="centre">中</em><em id="strong">强</em>
										</span>
									</div>
								</td>
							</tr>
							<tr>
								<th>确认密码</th>
								<td>
									<div class="inputOut">
										<input type="password" class="input_b" id="repwd" name="repwd"
											maxlength="20"> <span id="repwdMSG"></span>
									</div>
								</td>
							</tr>
							<tr>
								<th>手机号码</th>
								<td>
									<div class="inputOut">
										<input type="text" class="input_b " id="mobile" name="mobile"
											maxlength="11"> <span id="mobileMSG"></span>
									</div>
								</td>
							</tr>
							<tr>
								<th>验证码</th>
								<td>
									<div id="div_embed" ></div><input type="hidden" id="imgcode"
									name="imgcode" /> <span id="imgCodeMSG" style="display: none"></span>
								</td>
							</tr>
							<tr class="hight_auto">
								<th></th>
								<td>
									<div class="code">
										<i class="arrow hover"></i> <a class="tuiarrow"
											href="javascript:void(0)">推荐码（选填）</a>
									</div> <!-- 推荐码 -->
									<div class="code_box" style="display: none;">
										<div class="fl code_hd"
											style="width: 50px; margin-right: 10px">推荐码</div>
										<div class="inputOut fl"
											style="width: 360px; position: relative">
											<input type="text" class="input_b tui" id="promoCode"
												placeholder="推荐人手机或专属推荐码（选填）"
												onkeyup="checkPromoCode(this.value)" maxlength="11">
											<span class="tuijianMSG"></span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th></th>
								<td><input type="button" class="btn special02"
									value="同意协议并注册" onclick="registerBtnClick();" />
									<div class="mb_10 fc_6 bt_tx">
										注册即视为同意<a id="userAgreement" href="javascript:void(0)">《干将网贷用户协议》</a>
									</div> <span id="acpMSG" style="display: none"></span></td>
							</tr>
						</table>
						<!-- 右侧 -->
						<div class="right_all">
							<h3>您也可以使用第三方账户直接注册</h3>
							<div class="color color1" id="qq-login-id" onclick="thirdLoginFn(this)">
								<span><img style="width: 20px;" src="<%=basePath%>resources/Images/qq.png"></span>QQ登录
							</div>
							<div class="color color2" id="weixin-login-id" onclick="thirdLoginFn(this)">
								<span><img style="width: 24px;" src="<%=basePath%>resources/Images/wx.png"></span>微信登录
							</div>
							<div class="color color3" id="weibo-login-id" onclick="thirdLoginFn(this)">
								<span><img style="width: 24px;" src="<%=basePath%>resources/Images/wb.png"></span>新浪微博登录
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
		<div class="fs_12 fc_6 Numfont txt_center pt_15">Copyright ©
			2016 你我贷（www.niwodai.com） 版权所有；杜绝借款犯罪，倡导合法借贷，信守借款合约</div>
	</div>

	<script type="text/javascript"
		src="<%=basePath%>resources/Js/register1.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>resources/Js/commonquick.js"></script>

	<div class="plusBankBg"></div>
	<div class="plusBank1">
		<div class="topper clearfix">
			<span class="fl fs_18">干将网贷用户协议</span> <a href="javascript:void(0);"
				class="fr plus_c" onclick="closeAll_1()"></a>
		</div>
		<div class="middle page145 h425"
			style="padding: 10px 10px 35px 35px; height: 325px; overflow: auto">
			<div style="padding: 0px;">
				<h1 class="title-1">
					尊敬的客户，欢迎您注册成为干将网贷用户。<br />在注册前请您仔细阅读如下服务条款
				</h1>
				<p>本服务协议双方为上海干将网贷互联网金融信息服务有限公司（以下简称“干将网贷”）与干将网贷客户，本服务协议具有合同效力。您确认本服务协议后，本服务协议即在您和干将网贷之间产生法律效力。请您务必在注册之前认真阅读全部服务协议内容，如有任何疑问，可向干将网贷咨询。
					无论您事实上是否在注册之前认真阅读了本服务协议，只要您通过干将网贷网站（www.niwodai.com）以及相关网站、移动设备客户端注册成为干将网贷用户，您的行为仍然表示您同意并签署了本服务协议。</p>
				<h2 class="title-1">协议细则</h2>

				<h3 class="title-2">1、干将网贷服务条款的确认和接纳</h3>
				<p>干将网贷各项服务的所有权和运作权归干将网贷拥有。</p>
				<h3 class="title-2">2、您必须：</h3>
				<p>(1)自行配备上网的所需设备， 包括个人电脑、调制解调器或其他必备上网装置。</p>
				<p>(2)自行负担个人上网所支付的与此服务有关的电话费用、 网络费用。</p>
				<h3 class="title-2">3、您在干将网贷交易平台上不得发布下列违法信息：</h3>
				<p>(1)反对宪法所确定的基本原则的；</p>
				<p>(2)危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；</p>
				<p>(3)损害国家荣誉和利益的；</p>
				<p>(4)煽动民族仇恨、民族歧视，破坏民族团结的；</p>
				<p>(5)破坏国家宗教政策，宣扬邪教和封建迷信的；</p>
				<p>(6)散布谣言，扰乱社会秩序，破坏社会稳定的；</p>
				<p>(7)散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；</p>
				<p>(8)侮辱或者诽谤他人，侵害他人合法权益的；</p>
				<p>(9)含有法律、行政法规禁止的其他内容的。</p>
				<h3 class="title-2">4、有关个人资料</h3>
				<p>您同意：</p>
				<p>(1) 提供及时、详尽及准确的个人资料。</p>
				<p>(2).同意接收来自干将网贷的信息。</p>
				<p>(3) 不断更新注册资料，符合及时、详尽准确的要求。所有原始键入的资料将引用为注册资料。</p>
				<h3 class="title-2">5、电子邮件</h3>
				<p>您在注册时应当选择稳定性及安全性相对较好的电子邮箱，并且同意接受并阅读干将网贷发往您的各类电子邮件。如您未及时从自己的电子邮箱接受电子邮件或因您的电子邮箱或您的电子邮件接收及阅读程序本身的问题使电子邮件无法正常接收或阅读的，只要干将网贷成功发送了电子邮件，应当视为您已经接收到相关的电子邮件。电子邮件在发信服务器上所记录的发出时间视为送达时间。</p>
				<h3 class="title-2">6、服务条款的修改</h3>
				<p>干将网贷有权在必要时修改服务条款，干将网贷服务条款一旦发生变动，将会在重要页面上提示修改内容。如果不同意所改动的内容，您可以主动取消获得的干将网贷信息服务。如果您继续享用干将网贷信息服务，则视为接受服务条款的变动。干将网贷保留随时修改或中断服务而不需通知用户的权利。干将网贷行使修改或中断服务的权利，不需对用户或第三方负责。</p>
				<h3 class="title-2">7、用户的帐号、密码和安全性</h3>
				<p>您一旦注册成功成为用户，您将得到一个密码和帐号。如果您不保管好自己的帐号和密码安全，将负全部责任。另外，每个用户都要对其帐户中的所有活动和事件负全责。您可随时根据指示改变您的密码，原则上每个用户只允许开设一个干将网贷账户。用户同意若发现任何非法使用用户帐号或安全漏洞的情况，请立即通知干将网贷。</p>
				<h3 class="title-2">8、拒绝提供担保</h3>
				<p>您明确同意信息服务的使用由您个人承担风险。干将网贷不担保服务不会受中断，对服务的及时性，安全性，出错发生都不作担保，但会在能力范围内，避免出错。</p>
				<h3 class="title-2">9、有限责任</h3>
				<p>干将网贷对任何直接、间接、偶然、特殊及继起的损害不负责任，这些损害来自：不正当使用干将网贷服务，或用户传送的信息不符合规定等。这些行为都有可能导致干将网贷形象受损，所以干将网贷事先提出这种损害的可能性，同时会尽量避免这种损害的发生。</p>
				<h3 class="title-2">10、信息的储存及限制</h3>
				<p>干将网贷有判定您的行为是否符合干将网贷服务条款的要求和精神的权利，如果您违背干将网贷服务条款的规定，干将网贷有权中断其服务的帐号。</p>
				<h3 class="title-2">11、用户管理</h3>
				<p>您必须遵循：</p>
				<p>(1) 使用信息服务不作非法用途。</p>
				<p>(2) 不干扰或混乱网络服务。</p>
				<p>(3) 遵守所有使用服务的网络协议、规定、程序和惯例。用户的行为准则是以因特网法规，政策、程序和惯例为根据的。</p>
				<p>(4)
					个人会员同意，干将网贷有权在提供干将网贷服务过程中以各种方式投放各种商业性广告或其他任何类型的商业信息（包括但不限于在干将网贷平台的任何页面上投放广告），并且，个人会员同意接受干将网贷通过短信或其他方式向个人会员发送商品促销或其他相关商业信息。</p>
				<h3 class="title-2">12、保障</h3>
				<p>您同意保障和维护干将网贷全体成员的利益，负责支付由您使用超出服务范围引起的律师费用，违反服务条款的损害补偿费用，其它人使用您的电脑、帐号和其它知识产权的追索费。</p>
				<h3 class="title-2">13、结束服务</h3>
				<p>您或干将网贷可随时根据实际情况中断一项或多项服务。干将网贷不需对任何个人或第三方负责而随时中断服务。您若反对任何服务条款的建议或对后来的条款修改有异议，或对干将网贷服务不满，您可以行使如下权利：</p>
				<p>(1) 不再使用干将网贷信息服务。</p>
				<p>(2) 通知干将网贷停止对您的服务。</p>
				<p>干将网贷结束对您的服务后，您使用干将网贷服务的权利马上中止。从那时起，您没有权利，干将网贷也没有义务传送任何未处理的信息或未完成的服务给您或第三方。</p>
				<h3 class="title-2">14、通告</h3>
				<p>所有发给您的通告都可通过重要页面的公告或电子邮件或常规的信件传送。服务条款的修改、服务变更、或其它重要事件的通告都会以此形式进行。</p>
				<h3 class="title-2">15、信息内容的所有权</h3>
				<p>干将网贷定义的信息内容包括：文字、软件、声音、相片、录象、图表；在广告中全部内容；干将网贷为您提供的其它信息。所有这些内容受版权、商标、标签和其它财产所有权法律的保护。所以，您只能在干将网贷和广告商授权下才能使用这些内容，而不能擅自复制、再造这些内容、或创造与内容有关的派生产品。</p>
				<h3 class="title-2">16、法律</h3>
				<p>干将网贷信息服务条款要与中华人民共和国的法律解释一致。您和干将网贷一致同意服从干将网贷所在地有管辖权的法院管辖。</p>
				<h3 class="title-2">17、用户信息及隐私权保护</h3>
				<p>1）用户信息的提供、搜集及核实</p>
				<p>您有义务在注册时提供自己的真实资料，并保证诸如电子邮件地址、联系电话、联系地址、邮政编码等内容的有效性、安全性和及时更新，以便干将网贷为您提供服务并与您进行及时、有效的联系。您应完全独自承担因通过这些联系方式无法与您取得联系而导致的您在使用本服务过程中遭受的任何损失或增加任何费用等不利后果。</p>
				<p>干将网贷可能自公开及私人资料来源收集您的额外资料，以更好地了解干将网贷用户，并为其度身订造干将网贷服务、解决争议和确保在网站进行交易的安全性。干将网贷仅收集干将网贷认为就此目的及达成该目的所必须的关于您的个人资料。</p>
				<p>您同意干将网贷可以自行或通过合作的第三方机构对您提交或干将网贷搜集的用户信息（包括但不限于您的个人身份证信息等）进行核实，并对获得的核实结果根据本协议及有关文件进行查看、使用和留存等操作。</p>
				<p>干将网贷按照您在干将网贷上的行为自动追踪关于您的某些资料。干将网贷利用这些资料进行有关干将网贷之用户的人口统计、兴趣及行为的内部研究，以更好地了解您以便向您和干将网贷的其他用户提供更好的服务。</p>
				<p>干将网贷在干将网贷的某些网页上使用诸如“Cookies”的资料收集装置。“Cookies”是设置在您的硬盘上的小档案，以协助干将网贷为您提供度身订造的服务。干将网贷亦提供某些只能通过使用“Cookies”才可得到的功能。干将网贷还利用“Cookies”使您能够在某段期间内减少输入密码的次数。“Cookies”还可以协助干将网贷提供专门针对您的兴趣而提供的资料。</p>
				<p>如果您将个人通讯信息（例如：手机短信、电邮或信件）交付给干将网贷，或如果其他用户或第三方向干将网贷发出关于您在干将网贷上的活动或登录事项的通讯信息，干将网贷可以将这些资料收集在您的专门档案中。</p>
				<p>2）用户信息的使用和披露</p>
				<p>您同意干将网贷可使用关于您的个人资料（包括但不限于干将网贷持有的有关您的档案中的资料，及干将网贷从您目前及以前在干将网贷上的活动所获取的其他资料）以解决争议、对纠纷进行调停、确保在干将网贷进行安全交易，并执行干将网贷的服务协议及相关规则。干将网贷有时候可能调查多个用户以识别问题或解决争议，特别是干将网贷可审查您的资料以区分使用多个用户名或别名的用户。为限制在网站上的欺诈、非法或其他刑事犯罪活动，使干将网贷免受其害，您同意干将网贷可通过人工或自动程序对您的个人资料进行评价。</p>
				<p>您同意干将网贷可以使用您的个人资料以改进干将网贷的推广和促销工作、分析网站的使用率、改善干将网贷的内容和产品推广形式，并使干将网贷的网站内容、设计和服务更能符合用户的要求。这些使用能改善干将网贷的网页，以调整干将网贷的网页使其更能符合您的需求，从而使您在使用干将网贷服务时得到更为顺利、有效、安全及度身订造的交易体验。</p>
				<p>您同意干将网贷利用您的资料与您联络并（在某些情况下）向您传递针对您的兴趣而提供的信息，例如：有针对性的广告条、行政管理方面的通知、产品提供以及有关您使用干将网贷的通讯。您接受本协议即视为您同意收取这些资料。</p>
				<p>您注册成功后应妥善保管您的用户名和密码。您确认，无论是您还是您的代理人，用您的用户名和密码登录干将网贷后在干将网贷的一切行为均代表您并由您承担相应的法律后果。</p>
				<p>干将网贷对于您提供的、自行收集到的、经认证的个人信息将按照本协议及有关规则予以保护、使用或者披露。干将网贷将采用行业标准惯例以保护您的个人资料，但鉴于技术限制，干将网贷不能确保您的全部私人通讯及其他个人资料不会通过本协议中未列明的途径泄露出去。</p>
				<p>您使用干将网贷服务进行交易时，您即授权本公司将您的包括但不限于真实姓名、联系方式、信用状况等必要的个人信息和交易信息披露给与您交易的另一方或干将网贷的合作机构（仅限于干将网贷为完成拟向您提供的服务而合作的机构）。</p>
				<p>干将网贷有义务根据有关法律要求向司法机关和政府部门提供您的个人资料。在您未能按照与本协议、干将网贷有关规则或者与干将网贷其他用户签订的有关协议的约定履行自己应尽的义务时（包括但不限于当您作为借款人借款逾期或有其他违约时），干将网贷有权根据自己的判断、有关协议和规则、国家生效裁决文书或者与该笔交易有关的其他用户的合理请求披露您的个人资料（包括但不限于在干将网贷及互联网络上公布您的违法、违约行为，并有关将该内容记入任何与您相关的信用资料、档案或数据库），并且作为出借人的其他用户可以采取发布您的个人信息的方式追索债权或通过司法部门要求干将网贷提供相关资料，干将网贷对此不承担任何责任。</p>
				<p>3）您对其他用户信息的使用</p>
				<p>在干将网贷提供的交易活动中，您无权要求干将网贷提供其他用户的个人资料，除非符合以下条件：</p>
				<p>（1） 您已向法院起诉其他用户的在干将网贷活动中的违约行为；</p>
				<p>（2） 与您有关的其他用户逾期未归还借款本息；</p>
				<p>（3） 干将网贷被吊销营业执照、解散、清算、宣告破产或者其他有碍于您收回借款本息的情形。</p>
				<p>如您通过签署有关协议等方式获得其他用户的个人信息，您同意不将该等个人信息用于除还本付息或向借款人追索债权以外的其他任何用途，除非该等信息根据适用的法律规定、被有管辖权的法院或政府部门要求披露。</p>
			</div>
		</div>
		<div class="botpage145">
			<a class="btn btnSize_1 btn_blue" href="javascript:void(0)"
				onclick="closeAll_1()">关闭</a>
		</div>
	</div>
	<script src="<%=basePath%>resources/Js/gt.js"></script>
	<script>
		var handlerEmbed = function(captchaObj) {
			captchaObj.appendTo("#div_embed");
			captchaObj.onSuccess(function(data) {
			     var validate = captchaObj.getValidate();
		            $.ajax({
		                url: "/pc-geetest/validate", // 进行二次验证
		                type: "post",
		                dataType: "json",
		                data: {
		                    // 二次验证所需的三个值
		                    geetest_challenge: validate.geetest_challenge,
		                    geetest_validate: validate.geetest_validate,
		                    geetest_seccode: validate.geetest_seccode
		                },
		                success: function (data) {
		                    if (data && (data.status === "success")) {
		                      $("#imgcode").val("success");
		                      $('#imgCodeMSG').css("display", "none");
		          			$("#imgCodeMSG").removeClass();
		                    } else {
		                       
		                    }
		                }
		            });
		        });
		};
		// 验证开始需要向网站主后台获取id，challenge，success（是否启用failback）
		$.ajax({
			url : "/pc-geetest/register?t="
					+ (new Date()).getTime(), // 加随机数防止缓存
			type : "get",
			dataType : "json",
			success : function(data) {
				// 使用initGeetest接口
				// 参数1：配置参数
				// 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
				initGeetest({
					gt : data.gt,
					challenge : data.challenge,
					product : "float", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
					offline : !data.success,
					https : true
				// 表示用户后台检测极验服务器是否宕机，一般不需要关注
				// 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
				}, handlerEmbed);
			}
		});
	</script>
	<script type="text/javascript">
		var basePath="${basePath}";
		console.log(basePath);
		function thirdLoginFn(txt){
			var id=txt.id;
			console.log(basePath);
			if(id=="weixin-login-id"){
				
			}else if(id=="qq-login-id"){
				window.location.href="<%=basePath%>qq/register.action";
			}else{
				window.location.href="<%=basePath%>weibo/registerForWB.action";
			}
		}
	</script>
</body>
</html>