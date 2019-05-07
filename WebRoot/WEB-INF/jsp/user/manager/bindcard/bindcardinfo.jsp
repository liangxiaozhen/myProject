<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户管理-银行卡管理</title>
<link href="${basePath}/resources/resource/Css/new.css" rel="stylesheet" type="text/css" >
<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${basePath}/resources/Css/tanc.css">

<%-- <link href="${basePath}/resources/resource/Css/demo.css" rel="stylesheet" type="text/css" > --%>
<%-- <link href="${basePath}/resources/resource/Css/layout.css" rel="stylesheet" type="text/css" > --%>
<style type="text/css">
.tipBox-mark.nwd_icon.nwd_icon_newtootips.mar_l5{cursor: pointer;}
.tipBox-mark.nwd_icon.nwd_icon_newtootips.mar_l5:hover .tips.toortipBox-con.r_tip-con.j_eye-hide{    opacity: 1;
    visibility: inherit;
    z-index: 10;}
</style>
<script type="text/javascript">var basePath = "${basePath}";</script>
</head>
<body>
	<!-- 头部-->
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!--左侧-->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- 右侧-->
			<input type="hidden" value="" id="staticCss">
			<div class="fl perCerterR  bor_r bor_l">
				<div class="fl pad_30 wid_w900 clearfix">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_6">银行卡管理</span>
					</div>
					<div class="pad_b10 pad_t20 clearfix mar_t30">
						<div class="pad_l20 bankCon">
							<ul class="bank_card fl clearfix">
								<c:if test="${!empty boundCard}">
									<li class="pad_b20 pad_t20">
										<div class="bank_name">
											<i class="mar_l20"><img
												src="${basePath}/resources/resource/Images/bank01040000.gif"
												width="130" height="34"></i> <span class="fr mar_r30 fc_9">
												储蓄卡 </span>
										</div>
										<div class="txt_center mar_t20 mar_b20">
											<span class="fc_3"><b>${boundCard.cardno}</b></span>
										</div>
										<div class="card_set pad_l10 pad_r10 clearfix">
											<div class="fl">
												<i class="dot"></i> <span class="fc_9">&nbsp;持卡人：</span> <span
													class="fc_9">${boundCard.username}</span>
											</div>
											<div class="fr">
<!-- 												<a href="javascript:;" class="fr mar_r10" id="altBank-btn">修改</a> -->
												<a href="javascript:;" id="deleteBank-btn"
													class="fr mar_r10">删除</a>
											</div>
										</div>
									</li>
								</c:if>
								<c:if test="${empty boundCard}">
									<li class="pad_b20 pad_t20" id="addBank">
										<div class="bank_add txt_center fc_9 pad_t20 pad_b20">
											<input type="hidden" id="flagRe" value="${flagRe}" /> <a
												href="javascript:;" id="addBankcard-btn"
												class="icon_user  icon_use_addbankcard"> </a>
											<p class="pad_t10">添加银行卡</p>
										</div>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 头部-->
<%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery.cxselect.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/addMemberBank1.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/addMemberBank2.js"></script>
<div id="show"></div>
<!--添加银行卡star-->
<div class="panelbox wid_w624" id="addBankcard-pop-tx" style="display: none;">
		    <div class="panelbg"></div>
		    <div class="panelwrap">
		        <div class="paneltitle">
		            <span class="text" id="addUpBank">添加银行卡</span>
		            <span class="panelclose nwd_icon nwd_icon_close pop-close"><!-- id="close" onclick="closeOpenWid();"--></span>
		        </div>
		        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30" style="height: 485px;">
		            <div class="container">
			            <div class="addBankTab pad_b30">
			            	<h1 class="tabs">
				            	<span class="active-title" name="1">1身份验证</span>
				            	<span name="2">2添加银行卡</span>
				            </h1>
			            </div>
		                <div class="analog-success pad_t20">
		                
			               <div class="form clearfix step_sfyz boxs boxs1">
				                <!-- 手机号 -->
				                <div class="u-input clearfix mar_b20">
						            <div class="u-label fl">手机号</div>
						            <div class="fl input-text input-text-cur">
							            <input type="text" id="addBankPhone" maxlength="6" value="${mobilephoneStr}" class="ui-input w320-input" value="" readonly="readonly">
						            </div>
				                </div>
				                <!-- 图形验证码 -->
<!-- 				                <div class="u-input clearfix mar_b20"> -->
<!-- 						            <div class="u-label fl">图形验证码</div> -->
<!-- 						            <div class="fl input-text input-text-cur"> -->
<!-- 							            <input type="text" name="code" id="code" maxlength="6" class="ui-input w200-input itxt yzrightBord"> -->
<!-- 							            <span class="inputVlue" style=""></span> -->
<!-- 						            </div> -->
<%-- 					            	<a href="javascript:;" class="b-btn btn-getsms yzleftBord" id="refush"><img title="点击刷新验证码" alt="" src="${basePath}/Kaptcha.jpg" name="imgCode" id="imgCode"></a> --%>
<!--                                    	 <p class="prompt_1 error_1 lin_20 tx_ml_8 " id="verifyimgCode" style="display:none;padding-left:140px;*padding-left:148px;"></p> -->
<!-- 				                </div> -->
				                <!-- 短信验证 -->
				                <div class="u-input clearfix mar_b20">
						            <div class="u-label fl">短信验证</div>
						            <div class="fl input-text input-text-cur">
							            <input type="text" name="verify" id="verify" maxlength="6" class="ui-input w200-input yzrightBord itxt">
							            <span class="inputVlue"></span>
						            </div>
					            	<a href="javascript:;" class="b-btn btn-getsms yzleftBord" id="click1" onclick="gain()">获取验证码</a>
					            	<a href="javascript:;" class="b-btn btn-getsms yzleftBord" id="yybtn" style="display:none">语音验证码</a>
					            	<a href="javascript:;" class="b-btn btn-getsms yzleftBord" id="yybtn2" style="display:none">语音验证码</a>
					            	<input type="button" class="b-btn btn-getsms yzleftBord" style="display: none;color: red;" id="countDown"><!--倒计时-->
	                				<span class="tishik">
		                        		<div class="tankuan tankuan-b hidden">
		                            		<span class="jian"></span>
			                                <div class="content">
			                                	1分钟内未收到校验码短信，请点击语音验证码。<br> 
												你我贷将通过 400-7910-888 官方电话拨打您手机 , 播报语音验证码 。
											</div>
	                         		</div>
                    				</span>
                    				
                                   	 <p class="prompt_1 error_1 lin_20 tx_ml_8 " id="verifyMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>
                                    
				                </div>
				                <!-- 身份证号 -->
<!-- 				                <div class="u-input clearfix mar_b20"> -->
<!-- 						            <div class="u-label fl">身份证号</div> -->
<!-- 						            <div class="fl"> -->
<!-- 							            <input type="text" name="idCard" id="idCard" class="ui-input w320-input" value=""> -->
<!-- 							            <span class="tipBox inline_block">  -->
<!-- 											<div class="tipBox-mark nwd_icon nwd_icon_newtootips mar_l5">  -->
<!-- 												<div style="left: 0px; top: -39px;width:300px;" class="tips toortipBox-con r_tip-con j_eye-hide">  -->
<!-- 												<i class="nwd_icon nwd_icon_toortiparrow"></i>  -->
<!-- 												<p class="fc_9">港澳人士请输入港澳通行证号，台湾人士请输入<br>台胞证号，外籍人士请输入护照号</p>  -->
<!-- 												</div>  -->
<!-- 											</div>  -->
<!-- 										</span> -->
<!-- 						            </div> -->
<!-- 							       	<p class="prompt_1 error_1 lin_20 tx_ml_8" id="idCardMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p> -->
<!-- 				                </div> -->
				                <!-- 下一步按钮 -->
				                <div class="u-input clearfix">
						            <div class="u-label fl">&nbsp;</div>
						            <div class="fl input-text input-text-cur">
							            <a href="javascript:addBankNext()" class="btn btn_36c btn_size120 nexSetp_btn tx_ml_8" id="nextBtn">下一步</a>
						            </div>
				                </div>
			                </div>
			                
			                
			                <div class="form clearfix step_add boxs boxs2" style="display:none;">
				               
				                <!-- 持卡人 -->
				                <div class="u-input clearfix mar_b20">
						            <div class="u-label fl">持卡人</div>
						            <div class="fl input-text input-text-cur">
							            <input type="text" id="realName" maxlength="6" value="${realName}" class="ui-input w320-input" readonly="readonly" disabled="disabled" style="color:#666;">
							            <span id="accountName"></span>
						            </div>
				                </div>
				                <!-- 银行卡号 -->
				                <div class="u-input clearfix mar_b20 bank-info ">
						            <div class="u-label fl">银行卡号</div>
						            <div class="fl input-text input-text-cu">
							            <input type="text" name="mbbCardNo" id="mbbCardNo" class="ui-input w320-input ui-input-cardNo">
							            <br>
						            </div>
						            <p class="prompt_1 error_1 lin_20 tx_ml_8" id="mbbCardNoMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>
				                </div>
				                 <!-- 选择银行 -->
				                <div class="u-input clearfix mar_b20">
						            <div class="u-label fl">选择银行</div>
						            <div class="fl input-text input-text-cur">
						              <select name="bankName" id="bankName" class="ui-input w150-input">
                                      <option value="-1" selected="selected">请选择</option><option value="1">华夏银行</option><option value="2">广发银行</option><option value="3">平安银行</option><option value="4">北京银行</option><option value="5">中国银行</option><option value="6">招商银行</option><option value="7">中信银行</option><option value="8">中国工商银行</option><option value="9">中国建设银行</option><option value="10">兴业银行</option><option value="11">中国光大银行</option><option value="12">中国农业银行</option><option value="13">交通银行</option><option value="14">中国邮政储蓄银行</option><option value="15">中国民生银行</option><option value="16">浦发银行</option><option value="17">上海银行</option><option value="18">宁波银行</option><option value="19">南京银行</option><option value="20">上海农商银行</option><option value="21">东亚银行</option><option value="22">吉林省农村信用社</option><option value="23">凉山州商业银行</option><option value="24">宁波东海银行</option><option value="25">泉州银行</option><option value="26">深圳农商银行</option><option value="27">太仓农村商业银行</option><option value="29">玉溪市商业银行</option><option value="30">徽商银行</option><option value="31">自贡市商业银行</option><option value="32">福建海峡银行</option><option value="34">沧州银行</option><option value="35">广西农村信用社</option><option value="36">河北省农村信用社</option><option value="37">葫芦岛银行</option><option value="38">九江银行</option><option value="39">柳州银行</option><option value="40">南充市商业银行</option><option value="41">青岛银行</option><option value="42">顺德农村商业银行</option><option value="43">台州银行</option><option value="44">许昌银行(中原银行)</option><option value="45">浙江稠州商业银行</option><option value="46">温州银行</option><option value="48">安阳银行(中原银行)</option><option value="49">东莞农村商业银行</option><option value="50">广州农村商业银行</option><option value="51">哈尔滨银行</option><option value="52">晋城银行</option><option value="53">江阴农村商业银行</option><option value="54">昆仑银行</option><option value="55">内蒙古银行</option><option value="56">齐鲁银行</option><option value="57">深圳福田银座村镇银行</option><option value="58">山西省农村信用社联合社</option><option value="59">乌海银行</option><option value="60">雅安市商业银行</option><option value="61">杭州银行</option><option value="62">张家港农村商业银行</option><option value="63">重庆农村商业银行</option><option value="65">常熟农村商业银行</option><option value="66">富滇银行</option><option value="67">贵阳银行</option><option value="68">湖北省农村信用社联合社</option><option value="69">吉林银行</option><option value="70">乐山市商业银行</option><option value="71">宁夏黄河农商银行</option><option value="72">曲靖市商业银行</option><option value="73">苏州银行</option><option value="74">唐山市商业银行</option><option value="75">乌鲁木齐市商业银行</option><option value="76">云南省农村信用社</option><option value="77">江苏银行</option><option value="78">周口银行(中原银行)</option><option value="79">安顺市商业银行(贵州银行)</option><option value="80">长治市商业银行</option><option value="81">广州银行</option><option value="82">鹤壁银行(中原银行)</option><option value="83">邯郸银行</option><option value="84">昆山农村商业银行</option><option value="85">漯河银行(中原银行)</option><option value="86">平顶山银行</option><option value="87">三门峡银行(中原银行)</option><option value="88">四川省农村信用合作社</option><option value="89">潍坊银行</option><option value="90">邢台银行</option><option value="91">浙江民泰商业银行</option><option value="92">河北银行</option><option value="94">保定银行</option><option value="95">鄂尔多斯银行</option><option value="96">赣州银行</option><option value="97">海南省农村信用社联合社</option><option value="98">江西农村信用联合社</option><option value="99">江南农村商业银行</option><option value="100">龙江银行</option><option value="101">内蒙古农村信用社</option><option value="102">秦皇岛市商业银行</option><option value="103">商丘银行(中原银行)</option><option value="104">泰安市商业银行</option><option value="105">吴江农商银行</option><option value="106">阳泉市商业银行</option><option value="108">张家口市商业银行</option><option value="109">郑州银行</option><option value="110">大华银行</option><option value="111">承德银行</option><option value="112">广西北部湾银行</option><option value="115">晋中市商业银行</option><option value="116">临商银行</option><option value="117">南昌银行</option><option value="118">青海省农村信用社</option><option value="119">遂宁市商业银行</option><option value="120">天津滨海农村商业银行</option><option value="121">厦门银行</option><option value="122">营口银行</option><option value="123">金华银行</option><option value="124">遵义市商业银行</option><option value="126">德阳银行</option><option value="127">广东农村信用社</option><option value="128">衡水市商业银行</option><option value="129">嘉兴银行</option><option value="130">江苏省农村信用社联合社</option><option value="131">库尔勒市商业银行</option><option value="132">兰州银行</option><option value="133">攀枝花市商业银行</option><option value="134">上饶银行</option><option value="135">山东省农村信用社</option><option value="136">威海市商业银行</option><option value="137">宜宾市商业银行</option><option value="138">朝阳银行</option><option value="139">包商银行</option><option value="140">渣打银行</option><option value="141">成都农村商业银行</option><option value="142">抚顺银行</option><option value="143">桂林银行</option><option value="144">湖南省农村信用社联合社</option><option value="145">成都银行</option><option value="146">渤海银行</option><option value="147">北京农村商业银行</option><option value="148">浙商银行</option><option value="149">广东南海农村商业银行</option><option value="150">江门新会农村商业银行</option><option value="151">信阳银行(中原银行)</option><option value="152">驻马店银行(中原银行)</option><option value="153">大庆市商业银行(龙江银行)</option><option value="154">宜昌市商业银行(湖北银行)</option><option value="155">华融湘江银行</option><option value="156">无锡农村商业银行</option><option value="157">武进农村商业银行</option><option value="158">锦州银行</option><option value="159">昆明农联社</option><option value="160">宁波鄞州农村合作银行</option><option value="161">重庆三峡银行</option><option value="162">重庆银行</option><option value="163">南粤银行</option><option value="164">盘县万和村镇银行</option></select>
                                      <br>
						            </div>
						            <p class="prompt_1 error_1 lin_20 tx_ml_8" id="mbbNameMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>	
				                </div>
				                <!-- 开户城市 -->
				                <!-- 城市 -->
				                <div id="city_china_val2" class="demo">
									<p style="margin: 0 0 19px 0;">
										<span style="padding: 0 30px 0px 52px;">所在地区</span>
										<select class="province cxselect" id="bankProvCode" data-value="选择省" data-first-title="选择省" disabled="disabled"></select>
										<select class="city cxselect" id="bankCityCode" data-value="选择市" data-first-title="选择市" disabled="disabled"></select>
										<select class="area cxselect" id="bankAreaCode" data-value="选择地区" data-first-title="选择地区" disabled="disabled"></select>
									</p>
						            <p class="prompt_1 error_1 lin_20 tx_ml_8" id="provMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>
						            <p class="prompt_1 error_1 lin_20 tx_ml_8" id="cityNameMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>
						            <p class="prompt_1 error_1 lin_20 tx_ml_8" id="areaNameMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>
								</div>
				                <!-- 开户支行 -->
				                <div class="u-input clearfix mar_b20">
						            <div class="u-label fl">开户支行</div>
						            <div class="fl input-text input-text-cur">
							            <input type="text" name="mbbOpeningBranch" id="mbbOpeningBranch" maxlength="20" class="ui-input w320-input itxt">
							            <br>
						            </div>
						            <p class="prompt_1 error_1 lin_20 tx_ml_8" id="mbbOpeningBranchMSG" style="display:none;padding-left:140px;*padding-left:148px;"></p>
				                </div>
				               <span id="errorMsg" style="display:none;padding-left:140px;*padding-left:148px;color:red"></span>
				               <!-- 确认添加按钮 -->
				                <div class="u-input clearfix">
						            <div class="u-label fl">&nbsp;</div>
						            <div class="fl input-text input-text-cur">
							            <a class="btn btn_36c btn_size120 tx_ml_8" id="bindCardSave" onclick="save()">确认添加</a>
						            </div>
				                </div>
			                </div>
		                </div>
		            </div>
		        </div>
		    </div>
</div>
<!--添加银行卡end-->
<!--修改银行卡start-->
<div class="panelbox wid_w624" id="altBank-pop-manage" style="display: none;">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">修改银行卡</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form clearfix">
	                <input type="hidden" name="bankId" id="bankId" value=""><!--银行卡记录id-->
	                <input type="hidden" name="updBankName" id="updBankName" value="">
	                 <input type="hidden" name="bankNum" id="bankNum" value=""><!--银行名称编号-->
		                 <div class="u-input clearfix mar_b20">
		                    <div class="u-label fl">所属银行</div>
		                    <div class="fl ui-input w180-input tx_ml_8">
			                    <span class="clearfix"><img id="loginPic" src="/Public/Scripts/saved_resource"></span>
		                    </div>
	                    </div>
		                <!-- 持卡人 -->
		                <div class="u-input clearfix mar_b20">
				            <div class="u-label fl">持卡人</div>
				            <div class="fl input-text input-text-cur">
				                <input type="text" name="realName" id="realName" maxlength="6" class="ui-input w320-input" readonly="readonly"  style="color:#666;" value="远航">
				            </div>
		                </div>
	                    <!-- 银行卡号 -->
		                <div class="u-input clearfix mar_b20 bank-info ">
				            <div class="u-label fl">银行卡号</div>
				            <div class="fl input-text input-text-cur">
					            <input type="text" name="cardNo" id="cardNo" class="ui-input w320-input ui-input-cardNo" readonly="readonly" disabled="disabled"  value="389257027680756">
					            <input type="hidden" id="mbbCardNo">
					            <div class="magnifier"></div>
				            </div>
		                </div>
	                    <!-- 预留手机号 -->
	                    <!-- 开户城市 -->
	                    <div id="city_china_val" class="demo">
							<p style="margin: 0 0 19px 0;">
								<span style="padding: 0 30px 0px 52px;">所在地区</span>
								<select class="province cxselect" data-value="浙江省" data-first-title="选择省" disabled="disabled"></select>
								<select class="city cxselect" data-value="杭州市" data-first-title="选择市" disabled="disabled"></select>
								<select class="area cxselect" data-value="西湖区" data-first-title="选择地区" disabled="disabled"></select>
							</p>
						</div>
		                
	                    <!-- 开户支行 -->
		                <div class="u-input clearfix mar_b20">
				            <div class="u-label fl">开户支行</div>
				            <div class="fl input-text input-text-cur">
					            <input type="text" name="openingBranch" id="openingBranch" class="ui-input w320-input itxt">
					            <br><p class="prompt_1 error_1 lin_20 tx_ml_8" id="openingBranchMSG1" style="display:none"></p>
				            </div>
		                </div>
	                    <div class="u-input clearfix">
				            <div class="u-label fl">&nbsp;</div>
				            <div class="fl input-text input-text-cur">
					            <a class="btn btn_36c btn_size120 tx_ml_8" >确认修改</a><br>
					            <span class="prompt_1 error_1 tx_ml_8 lin_20" id="updError" style="display:none"></span>
				            </div>
		                </div>
	                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--修改银行卡end-->
<!-- 删除银行卡start -->
<div class="panelbox wid_w480" id="deleteBan-pop-manage" style="display: none;">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">删除银行卡</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item clearfix">
		                   <input type="hidden" id="baseid" name="baseid" value="${baseid}">
		                   <p class="txt_center" id="delMsg">您确定要删除(${boundCard.cardno})此卡吗</p>
	                    </div>
		                <div class="item clearfix txt_center" id="operationAction">
		                    <a class="btn btn_36c btn_size120" id="delSure" >确认</a>
		                    <a class="btn btn_bgfff btn_size120 pop-close" id="cancelDel">取消</a>
	                    </div>
		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 删除银行卡end -->	

<!-- 实名认证 -->
<div class="panelbox wid_w480" id="identification" style="display: none;">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">友情提示</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item clearfix">
		                   <p class="txt_center" id="plusBank"></p>
	                    </div>
		                <div class="item clearfix txt_center" id="operationAction">
		                    <a class="btn btn_36c btn_size120" id="ascert">确认</a>
	                    </div>
		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>

<script>
$.cxSelect.defaults.url = '${basePath}/resources/resource/Scripts/cityData.min.json';

$('#city_china_val').cxSelect({
	selects: ['province', 'city', 'area'],
	nodata: 'none'
});
$('#city_china_val2').cxSelect({
	selects: ['province', 'city', 'area'],
	nodata: 'none'
});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		
		changeCode();
		$("#imgCode").bind("click", changeCode);
		
		//修改
		$("#altBank-btn").click(function(){
			$("#altBank-pop-manage,.bg").show();
			showDiv("#altBank-pop-manage");
		})
		
		//关闭
		$(".panelclose.nwd_icon.nwd_icon_close.pop-close").click(function(){
			$("#addBankcard-pop-tx,#deleteBan-pop-manage,#altBank-pop-manage,.bg,#identification,.bg").hide();
		})
	})
	
	function genTimestamp() {
		var time = new Date();
		return time.getTime();
	}

	function changeCode() {
		$("#imgCode").attr("src", "${basePath}/Kaptcha.jpg?d=" + genTimestamp());
	}
	
	$(function(){
 		$("#delSure").bind("click",function(){
 			var baseid = $("#baseid").val();
			$.post(basePath+"/huishang/relieveBindCard/removeBindCard.action",{baseid : baseid},function(data){
 				$("#addBankcard-pop-tx,#deleteBan-pop-manage,#altBank-pop-manage,.bg,#identification,.bg").hide();
  				show(data,"deleCard(this);");
 			});
		})
	});
	
	function deleCard(){
 		window.location.href=basePath+ "/user/bankcard/bindCard.action";
	}
</script>

</body>
</html>