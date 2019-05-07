<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/userRecharge/css/rechargebuju.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
</head>
<script>
	$(function() {
		if ("${accountType}" == 1) { //个人网银
			$("#middle01_02_nr").css("display", "none");
			$("#middle01_01_nr").css("display", "block");
		}
		if ("${accountType}" == 0) {//企业网银
			$("#middle01_01_nr").css("display", "none");
			$("#middle01_02_nr").css("display", "block");
		}
		//点击快捷支付
		$("#top02_02").click(function() {
			$("#zhifu").val("2");
			$("#top02_02").css("border-bottom", "3px red solid");
			$("#top02_02").css("color", "red");
			$("#top02_01").css("border-bottom", "1px #F6F7F8 solid");
			$("#top02_01").css("color", "black");
			$("#kjnr").css("display", "block");
			$("#middle01_01_nr").css("display", "none"); //个人网银的内容显示
			$("#middle01_02_nr").css("display", "none"); //企业网银的内容显示
			if ("${userBankCard}" == null) { //假如没有绑定快捷卡
				$("#kjnr_div1").css("display", "none");
				$("#kjnr_divmoren").css("display", "block");
			} else {
				$("#kjnr_div1").css("display", "block");
				$("#kjnr_divmoren").css("display", "none");
				$("#kjnr_div1_01").html("快捷卡");
				$("#kjnr_div1_02").html("${bankname}");
				$("#kjnr_div1_03").html("${cardNo}");
			}

		});
		//点击网银支付
		$("#top02_01").click(function() {
			$("#zhifu").val("1");
			$("#top02_01").css("color", "red");
			$("#top02_02").css("color", "black");
			$("#top02_01").css("border-bottom", "3px red solid");
			$("#top02_02").css("border-bottom", "1px #F6F7F8 solid");
			$("#kjnr").css("display", "none");
			if ("${accountType}" == 1) { //个人网银
				$("#middle01_02_nr").css("display", "none");
				$("#middle01_01_nr").css("display", "block");

			}
			if ("${accountType}" == 0) {//企业网银
				$("#middle01_01_nr").css("display", "none");
				$("#middle01_02_nr").css("display", "block");
			}
		});
		//点击充值记录的时候
		$("#czjl").click(function() {
			$("#diyi").hide();
			$("#czjl_nr").show();
		});
	});
/* 	//充值记录 
	function selectByCondition() {
		var url = "${pageContext.request.contextPath}/user/userRecharge/rechargeRecord.action";
		var params = {
			"status" : $("#status_select").val(),
			"starttime" : $("#starttime_select").val()
		}
		var callback = function(data) {
			$("#czjl_nr").html(data);
		}
		$.post(url, params, callback);
	}  */
	//点击银行列表(点击图片的时候选中单选框)
	$(function() {
		$("#middle01_01_nr li").click(
				function() {
					$(this).children("input[type='radio']").prop("checked",
							true);
					$(this).siblings().children("input[type='radio']")
							.removeAttr("checked");
					$('.' + $(this).data('link')).show().siblings().hide();
				});
	});
	//企业银行列表(点击图片的时候选中单选框)
	$(function() {
		$("#middle01_02_nr li").click(
				function() {
					$(this).children("input[type='radio']").prop("checked",
							true);
					$(this).siblings().children("input[type='radio']")
							.removeAttr("checked");
				})
	});
	//当键盘上某个按键被按放开时触发的事件
	function checkUp(event, obj) {
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证只有出现一个.而没有多个. 
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
	}
	function checkBlur(obj) {
		//为了去除最后一个. 
		obj.value = obj.value.replace(/\.$/g, "");
	}
	//点击确认跳转
	function drawmoney() {
		 var select = $("#zhifu").val();
		 var transAmt = $("#transAmt").val();
			 if(transAmt==null || isNaN(transAmt) ||transAmt==""){
				 $("#txt_Amount_span").text("请输入合法数据!");
			 }else{
				 var action = "${pageContext.request.contextPath}/user/userRecharge/dmoney.action";
				 var params = {
				 "transAmt":transAmt,
				 "select" : select
				 };
				 $.post(action, params,function(data){
					 if(data!="0"){
						 var flagremovename = data.flagremovename;
						 var min = data.lowestmoney;
						 var max = data.hightestmoney;
						 var totalamount = data.totalamoount;
						 var dayamount = data.daymoneyrest;
						 var canrecharge = dayamount-parseInt(totalamount);
						 if(flagremovename==1){
							 alert("是谁!报上名来!");
							 $("#txt_Amount_span").text("对不起!您娃娃是黑名单用户!乱整哇!");
						 }else{
							 if(parseInt(transAmt)<min){
								 $("#txt_Amount_span").text("最低限额为"+min+"元，请重新输入!");
							 	}else{
								 if(parseInt(transAmt)>max){
									 $("#txt_Amount_span").text("最高限额为"+max+"元，请重新输入!");
								 }else if((parseInt(totalamount)+parseInt(transAmt))>dayamount){
									 $("#txt_Amount_span").text("日限额为"+dayamount+"元，请重新输入!您还能充值"+canrecharge+"元");
								 }else{
									 $("#txt_Amount_span").text("");
									 submitRecharge();
									 
								 } 
							 	} 
							 }
					 }else{
						 submitRecharge();
					 }
				 }, 'json');
			 }  

	}
	//点击确认跳转页面
	function submitRecharge() {
		var select = $("#zhifu").val();
		var transAmt = $("#transAmt").val();
		if("${accountType}"==1){ //个人用户
			var bankCode = $(".bank_list input[name='bank_type']:checked").val();
			var name = $(".bank_list input[type='radio']").attr("name");
		}
		if("${accountType}"==0){//企业用户
			var bankCode = $(".ers_list input[name='bank_type_en']:checked").val();
			var name = $(".ers_list input[type='radio']").attr("name");
		} 
	 	 $("#rechargeModal").css({
			margin : '200px auto'
		}).modal({
			backdrop : 'static',
			keyboard : false
		}).modal('show');   
		window.open("${pageContext.request.contextPath}/user/userRecharge/RechargeConfirmation.action?transAmt="+transAmt+"&bankCode="+bankCode+"&name="+name+"&select="+select);  
	}
	//点击模态框中的完成充值的时候跳转到资金详细页面
	function rechargeSuccess() {
		location.href = "${pageContext.request.contextPath}/user/userAccInExRecord/queryAll.action";
	}
	//重新选择充值方式
	function rechargeFail() {
		location.href = "${pageContext.request.contextPath}/user/userRecharge/rechargeList.action";
	}
</script>
<body style="background: #F6F7F8" style="font-family: 微软雅黑">
	<div id="allDiv">
		<div id="top01" style="background: #FFFFFF">
			<div id="top01_01">
				<img />充值
			</div>
			<div id="top01_02">
				<a id="czjl">充值记录</a>
			</div>
		</div>

		<div id="diyi">
			<form id="userRechargeFrom">
				<div id="top02">
					<div id="top02_01">
						<input type="hidden" value="1" id="zhifu" name="zhifu" />网银充值
					</div>
					<div id="top02_02">快捷支付充值</div>
				</div>
				<div id="middle01_01_nr">
					<ul class="bank_list">
						<li class="bank_01"><input name="bank_type"
							class="bank_01_list" value="ICBC" type="radio" checked="checked"></li>
						<li class="bank_02"><input name="bank_type"
							class="bank_02_list" value="CMB" type="radio"></li>
						<li class="bank_03"><input name="bank_type"
							class="bank_03_list" value="CCB" type="radio"></li>
						<li class="bank_04"><input type="radio" name='bank_type'
							class="bank_04_list" value="ABC"></li>
						<li class="bank_05"><input name="bank_type"
							class="bank_05_list" value="BOC" type="radio"></li>
						<li class="bank_06"><input name="bank_type"
							class="bank_06_list" value="BOS" type="radio"></li>
						<li class="bank_07"><input name="bank_type"
							class="bank_07_list" value="SPDB" type="radio"></li>
						<li class="bank_08"><input name="bank_type"
							class="bank_08_list" value="CEB" type="radio"></li>
						<li class="bank_09"><input name="bank_type"
							class="bank_09_list" value="CMBC" type="radio"></li>
						<li class="bank_10"><input name="bank_type"
							class="bank_10_list" value="BOCOM" type="radio"></li>
						<li class="bank_11"><input name="bank_type"
							class="bank_11_list" value="CIB" type="radio"></li>
						<li class="bank_12"><input name="bank_type"
							class="bank_12_list" value="SRCB" type="radio"></li>
						<li class="bank_13"><input name="bank_type"
							class="bank_13_list" value="BCCB" type="radio"></li>
						<li class="bank_14"><input name="bank_type"
							class="bank_14_list" value="BJRCB" type="radio"></li>
						<li class="bank_15"><input name='bank_type'
							class="bank_15_list" value="CBHB" type="radio"></li>
						<li class="bank_16"><input name="bank_type"
							class="bank_16_list" value="CITIC" type="radio"></li>
						<li class="bank_17"><input name="bank_type"
							class="bank_17_list" value="CZB" type="radio"></li>
						<li class="bank_18"><input name="bank_type"
							class="bank_18_list" value="GDB" type="radio"></li>
						<li class="bank_19"><input name="bank_type"
							class="bank_19_list" value="HKBEA" type="radio"></li>
						<li class="bank_20"><input name="bank_type"
							class="bank_20_list" value="HXB" type="radio"></li>
						<li class="bank_21"><input name="bank_type"
							class="bank_21_list" value="HZCB" type="radio"></li>
						<li class="bank_22"><input name="bank_type"
							class="bank_22_list" value="NJCB" type="radio"></li>
						<li class="bank_23"><input name="bank_type"
							class="bank_23_list" value="PINGAN" type="radio"></li>
						<li class="bank_24"><input name="bank_type"
							class="bank_24_list" value="PSBC" type="radio"></li>
						<li class="bank_25"><input name="bank_type"
							class="bank_25_list" value="SDB" type="radio"></li>
						<div style="clear: both;"></div>
					</ul>
				</div>
				<div id="middle01_02_nr">
					<ul class="ers_list">
						<li class="bank_001"><input checked="checked"
							name="bank_type_en" value="ICBC" type="radio"></li>
						<li class="bank_002"><input name="bank_type_en" value="CMB"
							type="radio"></li>
						<li class="bank_003"><input name="bank_type_en" value="CCB"
							type="radio"></li>
						<li class="bank_004"><input type="radio" name="bank_type_en"
							value="ABC"></li>
						<li class="bank_005"><input name="bank_type_en" value="BOC"
							type="radio"></li>
						<li class="bank_006"><input name="bank_type_en" value="BJRCB"
							type="radio"></li>
						<li class="bank_007"><input name="bank_type_en" value="SPDB"
							type="radio"></li>
						<li class="bank_008"><input name="bank_type_en" value="CEB"
							type="radio"></li>
						<li class="bank_009"><input name="bank_type_en" value="SDB"
							type="radio"></li>
						<li class="bank_010"><input name="bank_type_en" value="CIB"
							type="radio"></li>
						<!-- <li class="bank_011"><input name="bank_type_en" value="BOCOM" type="radio"></li> -->
						<div style="clear: both;"></div>
					</ul>
				</div>
				<!-- <p id="p1">您尚未开通快捷支付</p>
							<p id="p2">充值即可开通</p> -->
				<div id="kjnr">
					<label>快捷支付:</label>
					<div id="kjnr_div1">
						<div id="kjnr_div1_01"></div>
						<div id="kjnr_div1_02"></div>
						<div id="kjnr_div1_03"></div>
					</div>
					<div id="kjnr_divmoren">
						<p id="p1">您尚未开通快捷支付</p>
						<p id="p2">充值即可开通</p>
					</div>

					<div id="kjnr_div2">
						<p id="p3">什么是快捷支付？</p>
						<p id="p4">1、快捷支付仅支持绑定一张银行卡，绑定后将默认为取现卡，如需修改，请点击解除绑定按钮；</p>
						<p id="p5">2、此前绑定的取现卡将同时作废，暂不支持设置多张快捷支付卡；</p>
						<p id="p6">3、开通快捷支付后，不影响使用本人其他银行卡或他人银行卡代充值。</p>
					</div>
				</div>
				<!-- 工商银行 -->
				<div id="gsyh" style="display: none">
					<table class="table table-bordered  table-condensed">
						<thead>
							<tr>
								<th rowspan="3">银行名称</th>
								<th rowspan="3">支付卡种</th>
								<th rowspan="3">覆盖范围</th>
								<th rowspan="3">支付密码</th>
								<th colspan="5">支付限额</th>
							</tr>
							<tr>
								<th colspan="3">无证书客户</th>
								<th colspan="2">证书客户(U盾客户)</th>
							</tr>
							<tr>
								<th>单笔支付限额</th>
								<th>日累计支付限额</th>
								<th>总支付限额</th>
								<th>单笔支付限额</th>
								<th>日累计支付限额</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td rowspan="6">中国工商银行</td>
								<td>理财金账户</td>
								<td rowspan="6">全国</td>
								<td rowspan="6"><p>静态或动态支付密</p>
									<p>码(无证书客户)</p>
									<p>ATM取款密码</p>
									<p>(U盾客户)</p></td>
								<td colspan="3">（柜面注册）</td>
								<td rowspan="6">无限额</td>
								<td rowspan="6">无限额</td>
							</tr>
							<tr>
								<td>牡丹灵通卡</td>
								<td>300元</td>
								<td>300元</td>
								<td>300元</td>
							</tr>
							<tr>
								<td>牡丹信用卡</td>
								<td colspan="3">（电子银行口令）</td>
							</tr>
							<tr>
								<td>牡丹贷记卡</td>
								<td>2000元</td>
								<td>5000元</td>
								<td>无限额</td>
							</tr>
							<tr>
								<td rowspan="2">牡丹国际卡</td>
								<td colspan="3">（电子银行口令）</td>
							</tr>
							<tr>
								<td>500元</td>
								<td>1000元</td>
								<td>无限额</td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- 招商银行小额 -->
				<div id="zsyh" style="display: none">
					<table class="table table-bordered  table-condensed">
						<thead>
							<tr>
								<th rowspan="3">银行名称</th>
								<th rowspan="3">支付卡种</th>
								<th rowspan="3">覆盖范围</th>
								<th rowspan="3">支付密码</th>
								<th colspan="4">支付限额</th>
							</tr>
							<tr>
								<th colspan="2">无证书客户（网上个人银行大众版）</th>
								<th colspan="2">证书客户（网上个人银行专业版）</th>
							</tr>
							<tr>
								<th>单笔支付限额</th>
								<th>日累计支付限额</th>
								<th>单笔支付限额</th>
								<th>日累计支付限额</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td rowspan="2">中国工商银行</td>
								<td>一卡通借记卡</td>
								<td rowspan="2">全国</td>
								<td><p>静态或动态支付密</p>
									<p>码(无证书客户)</p>
									<p>ATM取款密码</p>
									<p>(U盾客户)</p></td>
								<td>500元</td>
								<td>500元</td>
								<td>无限额</td>
								<td>无限额</td>
							</tr>
							<tr>
								<td>招行信用卡</td>
								<td>网上支付密码</td>
								<td colspan="4">单笔限额500元，无日累计限制</td>
							</tr>

						</tbody>
					</table>
				</div>
				<!-- 建设银行 -->
				<div id="jsyh" style="display: none">
					<table class="table table-bordered  table-condensed">
						<thead>
							<tr>
								<th rowspan="3">覆盖范围</th>
								<th rowspan="3">支付密码</th>
								<th colspan="5">支付限额</th>
							</tr>
							<tr>
								<th colspan="3">网上银行签约客户（龙卡贷记卡暂未开通）</th>
								<th colspan="2">账号支付（无需开通网银）</th>
							</tr>
							<tr>
								<th>单笔支付限额</th>
								<th>日累计支付限额</th>
								<th>备注</th>
								<th>单笔支付限额</th>
								<th>日累计支付限额</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td rowspan="6">全国</td>
								<td rowspan="6"><p>网银交易密码</p>
									<p>（网银签约客户）</p></td>
								<td>100万元</td>
								<td>100万元</td>
								<td>USB Key客户</td>
								<td rowspan="6">1000元</td>
								<td rowspan="6">1000元</td>
							</tr>
							<tr>
								<td>100万元</td>
								<td>100万元</td>
								<td>USB Key+动态口令卡客户</td>
							</tr>
							<tr>
								<td>3000元</td>
								<td>3000元</td>
								<td>文件证书+刮刮卡客户</td>
							</tr>
							<tr>
								<td>1000元</td>
								<td>1000元</td>
								<td>动态口令卡（刮刮卡）客户</td>
							</tr>
							<tr>
								<td>1000元</td>
								<td>1000元</td>
								<td>短信动态口令客户</td>
							</tr>
							<tr>
								<td>100万元</td>
								<td>100万元</td>
								<td><p>除文件证书客户不开放支付限额</p>
									<p>外，其他未提及的支付限额均以</p>
									<p>此限额为准</p></td>
							</tr>

						</tbody>
					</table>
				</div>

				<div id="kjnr_div3">
					<div>
						总资产:<span style="font-size: 25px; color: red">${df.format(uc.balance)}</span>元&nbsp;&nbsp;&nbsp;&nbsp;
						可用余额:<span style="font-size: 25px; color: red">${df.format(uc.avlbalance)}</span>元&nbsp;&nbsp;&nbsp;&nbsp;
						冻结余额:<span style="font-size: 25px; color: red">${df.format(uc.freezebalance)}</span>元
					</div>
					<div>
						充值金额: <input name="TransAmt" id="transAmt"
							onblur="checkBlur(this)" onkeyup="checkUp(event,this)"
							maxlength="9" />元 <span id="txt_Amount_span"
							style="color: red; margin-left: 10px; font-size: 15px"></span>
					</div>
					<div>
						<button type="button" onclick="drawmoney()">确认充值</button>
					</div>
				</div>

			</form>
		</div>


		<div id="czjl_nr">
			<%@include file="/WEB-INF/jsp/user/recharge/rechargeRecord.jsp"%>
		</div>

		<div id="disan">
			<div id="kjnr_bottom">
				<p id="p7">温馨提示:</p>
				<p>1.充值过程收取相应的费用;</p>
				<p>2.最低充值金额应不少于 1 元；</p>
				<p>3.充值期间，请勿关闭浏览器，待充值成功并返回首页后，所充资金才能入账，如有疑问，请联系客服</p>
				<p>400-888-6268；</p>
				<p>4.网银充值时企业用户只能使用企业网银,个人用户只能使用个人网银</p>
				<p>
					5.支付限额请参照支付说明。（<a href="http://www.baidu.com">
						http://www.baidu.com</a>）
				</p>
			</div>
		</div>

	</div>
	<!-- 充值提示框 -->
	<div class="modal fade" id="rechargeModal" tabindex="-1" role="dialog"
		aria-labelledby="rechargeModalable" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="rechargeModalable">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示
					</h3>
				</div>
				<div id="withdraw-modal-body" class="modal-body" align="center">
					<font size="3">请在新打开的汇付天下页面进行充值,充值完成前不要关闭该窗口</font>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="rechargeFail()">选择其他方式</button>
					<button type="button" class="btn btn-primary"
						onclick="rechargeSuccess()">已完成充值</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>