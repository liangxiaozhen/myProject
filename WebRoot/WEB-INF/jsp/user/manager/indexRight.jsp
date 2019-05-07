<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <script type="text/javascript" src="<%=request.getContextPath() %>/resources/resource/Scripts/managecommon.js"></script>   
<script>
$(function(){
	var loginname="${sessionScope.user.loginname}";//获取session里面的用户名
    var	action ="<%=request.getContextPath()%>/user/userAccount/getUserByLoginName.action";
	   var param={
			   "loginname":loginname
	   }
	   $.post(action,param,function(returndata){
		  var userAccount=jQuery.parseJSON(returndata);
		   $(".totalamount").html(userAccount.totleMoney);
		  $(".Availablebalance").html(userAccount.avBalance);
		  $(".freeze").html(userAccount.freeMoney);
	   });
  });
$(function(){
	//通过用户名获取积分
	var loginname="${sessionScope.user.loginname}";//去空格
	var action ="<%=request.getContextPath()%>/user/userAccount/userBounsPointsByLoginName.action";
	   var param={
			   "loginname":loginname
	   }
	   $.post(action,param,function(returndata){
		   if(returndata!="null"){
			   $("#mCount").html(returndata);
			   var text=$("#mCount").text();
				 if($.trim(text)==null ||$.trim(text)==''){
					$("#dhHref").html();
				 }else{
					$("#dhHref").html("兑换");
				 } 
		   }else{
			   $("#dhHref").html("去获取？");
		   }
		
	   });
  });

  
//通过用户名获取红包
$(function(){
	
	var loginname="${sessionScope.user.loginname}";//去空格
     var action ="<%=request.getContextPath()%>/user/userAccount/redBaoByLoginName.action";
	   var param={
			   "loginname":loginname
	   }
	 $.post(action,param,function(data){
		if(data!="null"){
			 $("#coupons").html(data);
			 var text=$("#coupons").text();
			 if($.trim(text)==null ||$.trim(text)==''){
					$("#couponsHref").html();
				 }else{
					if(text=="0.0"){
						 $("#coupons").html("");
						 $("#couponsHref").html("去获取？"); 
					 }else{
						 $("#couponsHref").html("使用");
					 }
				}  
		 }else{
			 $("#couponsHref").html("去获取？");
		 }
		
		  
		}); 
  }); 
  function recharge(){
	  var action = "${pageContext.request.contextPath}/user/userRecharge/rechargePre.action";
	  $.post(action,function(result){
		   if(result=="1"){
			   $("#txt_center").text("");
			   $("#txt_center").text("你还没有开户,请到安全中心开户");
			   document.getElementById("links").href="";
			   document.getElementById("links").href="${basePath}/user/securitycenter/findopenAnAccountList.action";
			   $("#identification,.bg").show();
		  }else if(result=="3"){
			  $("#txt_center").text("");
			  $("#txt_center").text("您还没有绑卡,请前往去绑卡");
			  document.getElementById("links").href="";
			  document.getElementById("links").href="${basePath}/user/bankcard/bindCard.action";
			  $("#identification,.bg").show();
		  }else if(result=="0"){
			  $("#txt_center").text("");
			  $("#txt_center").text("您还没有实名认证,请前往认证");
			  document.getElementById("links").href="";
			  document.getElementById("links").href="${basePath}/user/securitycenter/findopenAnAccountList.action";
			  $("#identification,.bg").show();
		  }else{//表示已经开户,并且绑卡
			  window.location.href="${basePath}/user/userRecharge/rechargeList.action";
		  }
	  },'json');
  }
  
  function withdrawscash(){ 
  	  var action = "${pageContext.request.contextPath}/user/userwithdrawscash/withdrawPro.action";
  	  $.post(action,function(result){
  		   if(result=="1"){
  			   $("#txt_center").text("");
  			   $("#txt_center").text("你还没有开户,请到安全中心开户");
  			   document.getElementById("links").href="";
  			   document.getElementById("links").href="${pageContext.request.contextPath}/user/securitycenter/findopenAnAccountList.action";
  			   $("#identification,.bg").show();
  		  }else if(result=="0"){
  			  $("#txt_center").text("");
  			  $("#txt_center").text("您还没有绑卡,请前往去绑卡");
  			  document.getElementById("links").href="";
  			  document.getElementById("links").href="${pageContext.request.contextPath}/user/bankcard/bindCard.action";
  			  $("#identification,.bg").show();
  		  }else if(result=="2"){
  			  $("#txt_center").text("");
  			  $("#txt_center").text("您还没有实名认证,请前往认证");
  			  document.getElementById("links").href="";
  			  document.getElementById("links").href="${pageContext.request.contextPath}/user/securitycenter/findopenAnAccountList.action";
  			  $("#identification,.bg").show();
  		  }else if(result=="3"){
			  $("#txt_center").text("");
			  $("#txt_center").text("请先设置交易密码");
			  document.getElementById("links").href="";
			  document.getElementById("links").href="${pageContext.request.contextPath }/user/securitycenter/list.action";
			  $("#identification,.bg").show();
		  }else{//表示已经开户,并且绑卡
  			  window.location.href="${pageContext.request.contextPath}/user/userwithdrawscash/withdraw.action";
  		  }
  	  },'json');
    }
  
//关闭按钮
	function closefun(){
		$("#identification").hide();
		$(".bg").hide();
  }
	
  </script>
  
	
	<div class="panelbox wid_w480" id="identification" style="display: none;width: 300px;height:100px;">
    <div class="panelbg"></div>
    <div class="panelwrap3">
        <div class="paneltitle">
         <span class="text">友情提示</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="closefun();"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item clearfix">
		                   <p class="txt_center" id="txt_center"></p>
	                    </div>
		                <div class="item clearfix txt_center">
		                    <a href="" class="btn btn_36c btn_size120" id="links">确认</a>
	                    </div>
		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
  
  
  <!-- <div id="rechargeAll"> --> 
    <!--right start-->

    <div class="fl perCerterR bor_l bor_r wid_w960">
		<!-- 网站公告 s  -->
		<div class="site_msg bor_b " id="msgDiv">
			<p class="fl pad_l10 pad_r10 pad_l20"><a href="http://test.ganjiangps.com/"><span class="nwd_icon nwd_icon_gonggao mar_r10">&nbsp;</span><span id="msgInfo" class="vertical_middle">欢迎来到干将软件</span></a></p>
			<!-- <ul class="fl">
				<li><a href="#">公告一</a></li>
				<li><a href="#">公告一</a></li>
			</ul> -->
			<span class="fr nwd_icon icon_24 nwd_icon_close ggClose mar_t10 mar_r10" style="top:-3px;">&nbsp;</span>
		</div>
		<!-- 网站公告 e  -->
		<div class="pad_l20 pad_t30 pad_r20">
			<!-- 账户余额 -->
			<div class="pad_l30 clearfix pad_r20 pad_b10">
				<div class="fl moneyall clearfix">
					<div class="fs_16 pad_b10 title_manIndex"><span class="title_word fc_3">账户余额</span></div>
					<div class="pad_t10 clearfix">
							<p class="fl fc_6 fs_16 w320  dlDecimal">
								<em class="mar_r0 fs_28 fc_3 Numfont" id="accAmount"></em><i class="fs_16 mar_r5"></i><em class="fs_14 fc_3"><span class="totalamount" style="font-size: 20px;">0.00</span></em>
							</p>						
							<p class="fl">
								<a  class="btn btn_bgf60 btn_size100 mar_r20" onclick="recharge();">充值</a>
								<a  class="btn btn_bgfff btn_size100" onclick="withdrawscash();" >提现</a>
							</p>
					</div>
					<div class="pad_t10 clearfix">
						<p class="fc_6 pad_b5  fl">可用余额：<span class="Availablebalance">0.00</span><i class="fc_6 mar_r20 Numfont fs_16" id="balanceAmount"></i>冻结资金：<span class="freeze">0.00</span><i class="fc_6 Numfont fs_16" id="freezeAmount"></i>
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
						<span class="index_jifeng"><img src="<%=request.getContextPath() %>/resources/Images/jifen.png"/>&nbsp;&nbsp;积分<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/member/exchange.html" id="mCount"></a><a href="#" id="dhHref" style="color:#2577E3;"></a></span>&nbsp;&nbsp;
						<span class="index_hongbao"><img src="<%=request.getContextPath() %>/resources/Images/redbao.png"/>&nbsp;&nbsp;红包<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/member/coupon.do" id="coupons"></a><a class="wordlink mar_l10" href="javascript:;" id="couponsHref" style="color:#2577E3;"></a></span>&nbsp;&nbsp;
						<span class="index_jiaxigquan"><img src="<%=request.getContextPath() %>/resources/Images/percenter-icon-jiaxi.png"/>&nbsp;&nbsp;加息券<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/member/actInterest.do?voucherType=INTEREST" id="plusCoupons"></a><a style="color:#2577E3;" class="wordlink mar_l10" href="javascript:;" id="plusCouponsHref">如何获得？</a></span>&nbsp;&nbsp;
						<span class="index_jianxiquan"><img src="<%=request.getContextPath() %>/resources/Images/percenter-icon-jiaxi.png"/>&nbsp;&nbsp;减息券<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/member/reduceInterest.do?voucherType=reduce" id="reduceCoupons" style="color:#2577E3;"></a></span>&nbsp;&nbsp;
						<span class="index_tiyanjin"><img src="<%=request.getContextPath() %>/resources/Images/tiyan.png"/>&nbsp;&nbsp;体验金<a class="wordlink_fc6 fs_14 Numfont mar_l5 mar_r5" href="/LCB/getLCBXPMoney.do" id="tyjCount"></a><a class="wordlink mar_l15" href="javascript:;" id="tyjHref" style="color:#2577E3;"></a></span>
				</p>
			</div>
			<!-- 我的投资 -->
			<div class="pad_l30 clearfix pad_r20 pad_t40 pad_b10">
				<div class="pad_b20 title_manIndex clearfix">
					<span class="fl title_word fs_16 fc_3">账户净资产<i class="Numfont fc_6 fs_18 mar_l30" id="lasset"><span class="Availablebalance">0.00</span></i></span>
					
					<a class="fr title_word fs_14" href="#" id="myBill" style="color:#2577E3;"><img src="<%=request.getContextPath() %>/resources/Images/zhangdan.png"/>&nbsp;我的账单</a>
				</div>
				<div class="proContent clearfix slide-box">
					<div class="clearfix proWrap" id="mediaListCon">
						<div class="proList play-list-con clearfix">
							<ul class="my_property clearfix">
							<!--新嘉财  start   阿朱-->
								<li class="fl pad_t10 txt_center">
									<h6 class="fc_6">有道智投</h6>
									<div class="nwd_amount">
										<p class="fc_3 hold_money_num pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="inteBidzc">0.00</i></p>
										<p class="fc_3 summ_oney_num pad_b5 none" style="top:-30px; opcity:0;"><i class="fs_22 Numfont" name="inteBidIncome"></i></p>
										<p class="fc_9 hold_money" style="top: 0px; opacity: 1;">资产</p>
										<p class="fc_9  none summ_oney" style="top: 30px; opacity: 0;">收益</p>
									</div>
									<a href="/inteBid/loadingInteBidPage.do" class="btn btn_bordf60 btn_size100 mar_t5">投资</a>
	
									<p class="pad_t10"><a href="/member/findinteBidPage.do" class="wordlink_fc6">详情</a></p>
								</li>
								<!--新嘉财  end  阿朱-->
								<li class="fl pad_t10 txt_center">
									<h6 class="fc_6">嘉财有道</h6>
									<div class="nwd_amount">
										<p class="fc_3 hold_money_num pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="llcjhzc">0.00</i></p>
										<p class="fc_3 summ_oney_num pad_b5 none" style="top:-30px; opcity:0;"><i class="fs_22 Numfont" name="financialIncome"></i></p>
										<p class="fc_9 hold_money" style="top: 0px; opacity: 1;">资产</p>
										<p class="fc_9  none summ_oney" style="top: 30px; opacity: 0;">收益</p>
									</div>
									<a href="/inteBid/loadingInteBidPage.do" class="btn btn_bordf60 btn_size100 mar_t5">投资</a>
	
									<p class="pad_t10"><a href="/member/findFinancial2Page.do" class="wordlink_fc6">详情</a></p>
								</li>
								<li class="fl pad_t10 txt_center"  id="excellenceLi" style="display:none">
									<h6 class="fc_6">卓越专区</h6>
									<div class="nwd_amount">
										<p class="fc_3 pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="zyzqBanlance">0.00</i></p>										
										<p class="pad_t20">&nbsp</p>
									</div>
									<a href="/financial/financialDetail.do" class="btn btn_bordf60 btn_size100 mar_t5">投资</a>
	
									<p class="pad_t10"><a href="/member/findFinancialNFIPage.do" class="wordlink_fc6">详情</a></p>
								</li>
								<li class="fl pad_t10 txt_center">
									<h6 class="fc_6">债权</h6>
									<div class="nwd_amount">
										<p class="fc_3 hold_money_num pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="lzqzc">0.00</i></p>
										<p class="fc_3 summ_oney_num pad_b5 none" style="top:-30px; opcity:0;"><i class="fs_22 Numfont" name="bidIncome"></i></p>
										<p class="fc_9 hold_money" style="top: 0px; opacity: 1;">资产</p>
										<p class="fc_9  none summ_oney" style="top: 30px; opacity: 0;">收益</p>
									</div>
									<a href="/xiangmu/" class="btn btn_bordf60 btn_size100 mar_t5">投资</a>
	
									<p class="pad_t10"><a href="/member/myAcceptList.html" class="wordlink_fc6">详情</a></p>
								</li>
								<!-- 新手专享 -->
								<li class="fl pad_t10 txt_center">
									<h6 class="fc_6">新手专享</h6>
									<div class="nwd_amount">
										<p class="fc_3 hold_money_num pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="xinshouzx">0.00</i></p>
										<p class="fc_3 summ_oney_num pad_b5 none" style="top:-30px; opcity:0;"><i class="fs_22 Numfont" name="newProductIncome"></i></p>
										<p class="fc_9 hold_money" style="top: 0px; opacity: 1;">资产</p>
										<p class="fc_9  none summ_oney" style="top: 30px; opacity: 0;">收益</p>
									</div>
									<a href="http://test.ganjiangps.com/index.php/Home/Xiangmu/xiangmu.html" class="btn btn_bordf60 btn_size100 mar_t5">投资</a>
	
									<p class="pad_t10"><a href="/member/noviceArea.do" class="wordlink_fc6">详情</a></p>
								</li>
								<!-- 账户余额 -->
								<li class="fl pad_t10 txt_center">
									<h6 class="fc_6">账户余额</h6>
									<div class="nwd_amount">
										<p class="fc_3 pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="accAmount1"></i></p>										
										<p class="pad_t20">&nbsp</p>
									</div>
									<a href="${pageContext.request.contextPath}/user/userRecharge/rechargeList.action" class="btn btn_bordf60 btn_size100 mar_t5">充值</a>
	
									<p class="pad_t10"><a href="/member/carry.html" class="wordlink_fc6">提现</a></p>
								</li>
								<!-- 我的借款状态-->
								<li class="fl pad_t10 txt_center">
									<h6 class="fc_6">我的借款</h6>
									<div class="nwd_amount">
										<p class="fc_green hold_money_num pad_b5" style="top: 0px; opacity: 1;"><i class="fs_22 Numfont" name="lforceAmount"></i></p>
										<p class="fc_3 summ_oney_num pad_b5 none" style="top:-30px; opcity:0;"><i class="fs_22 Numfont" name="payableFund" id = "payableFund"></i></p>
										<p class="fc_9 hold_money" style="top: 0px; opacity: 1;">总负债</p>
										<p class="fc_9  none summ_oney" style="top: 30px; opacity: 0;">本月还款额</p>
										<p class="fc_9 "><em class="nwd_icon icon_25 switchBtn  nwd_icon_switchBtn"></em></p>
									</div>
										<a href="/member/repaymentDetail.do" class="btn btn_bordf60 btn_size100 mar_t5">还款</a>
										<p class="pad_t10"><a href="/member/borrowingRecord.do" class="wordlink_fc6">详情</a></p>
								</li>
							</ul>
						</div>
						<a class="actbtn perbtn nwd_icon nwd_icon_pre" href="javascript:void(0);"></a>
						<a class="actbtn nextbtn nwd_icon nwd_icon_next" href="javascript:void(0);"></a>
					</div>
				</div>
			</div>
			 
			<div class="mya-line mar_t20 mar_b20">
			     <div class="ui-dbline icon_user icon_user_line "></div>
			</div>
		
			<!-- 操作列表 -->
				<div class="tab indexTab clearfix indexHandle ui-select-listBox" style="width:auto;">
					<ul class="tab_title clearfix" style="border-bottom:none;">
						<li class="fl ui-select-listBox-list  ui-select-listBox-list--now active" value="1" ><a class="pad_b10" href="javascript:;" onclick="javascript:;" value="transactionAttr">最近交易</a></li>
						<li class="fl ui-select-listBox-list " value="2 style="border-bottom:none "><a class="pad_b10" href="javascript:;" onclick="javascript:;"   value="rechargeAttr">充值记录</a></li>
						<li class="fl ui-select-listBox-list" value="3" ><a class="pad_b10" href="javascript:;" onclick="javascript:;"  value="withdrawAttr">提现记录</a></li>
						<li class="fl ui-select-listBox-list" value="4" ><a class="pad_b10" href="javascript:;" onclick="javascript:;" value="refundRecord">收款记录</a></li>
					</ul>
					<div class="ui-select-listBox-line">
			            <div class="ui-select-listBox-l-blue" style="left: -1px; width: 80px;"></div>
			        </div>
					<a class="pad_l20  pad_b10 tradeRecord" href="${pageContext.request.contextPath}/user/userAccInExRecord/list.action" style="color:#2577E3;"><img src="<%=request.getContextPath() %>/resources/Images/zijin.png"/>资金记录</a>
				</div>
			<!--最近交易  -->
								<div class="tab_box box1 box">
									<table class="table" cellspacing="0" cellpadding="0" id="transactionTable">
									</table>
								</div>
								<!-- 充值记录 -->
								<div class="tab_box box2 box" >
									<table class="table" cellspacing="0" cellpadding="0" id="crushTable">
										<thead>
											<tr class="">
												<th class="">充值订单号</th>
												<th class="">充值时间</th>
												<th class="">充值金额</th>
												<th class="">手续费</th>
												<th class="">充值方式</th>
												<th class="">处理状态</th>
											</tr>
										<thead>
										<tbody id="text" style="text-align: center;">
											<c:if test="${!empty userRecharge}">
												<c:forEach items="${userRecharge}" var="item" varStatus="status">
													<tr>
														<td>${item.rechargeno}</td>
														<td>${item.starttimeStr}</td>
														<td>${item.amount}</td>
														<td>${item.recharfee}</td>
														<td>
															<c:if test="${item.rechargetype=='1'}">个人网银</c:if>
															<c:if test="${item.rechargetype=='2'}">企业网银</c:if>
															<c:if test="${item.rechargetype=='3'}">快捷支付</c:if>
															<c:if test="${item.rechargetype=='4'}">汇款充值</c:if>
														</td>
														<td>
															<c:if test="${item.status=='1'}">成功</c:if>
															<c:if test="${item.status=='2'}">失败</c:if>
															<c:if test="${item.status=='3'}">取消</c:if>
															<c:if test="${item.status=='4'}">待处理</c:if>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table> 
								</div>
								<!-- 提现记录 -->
								<div class="tab_box box3 box">
									<table class="table" cellspacing="0" cellpadding="0" id="carryTable">
										<tr class="">
											<th class="">提现时间</th>
											<th class="">提现金额</th>
											<th class="">手续费</th>
											<th class="">处理状态</th>
											<th class="">处理时间</th>
										</tr>
										<tr char="tr_contrnt">
											
										</tr>
									</table>
								</div>
								<!-- 收款记录 -->
								<div class="tab_box box4 box">
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
				<div class="mya-line mar_t20">
				
				<div class="ui-dbline icon_user icon_user_line "></div>
 				
				</div>
			</div>
		</div>
		<!-- </div> -->
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".box2,.box3,.box4").hide();
	    $(".tab_title.clearfix  li").click(function(){
	        $(".tab_title.clearfix  li").removeClass("active")
	        var num = $(this).val();
	        $(this).addClass("active")
	        $(".box").hide();
	        $(".box"+num).show();
	    });
	})
</script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
 --%><%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/userRecharge/recharge.js"></script>  --%>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/accountDetail.js"></script>
<script type="text/javascript">
$(function(){
	 $(".side_nav").find("dl").siblings().removeClass("navcurron").eq(0).addClass("navcurron");
});
</script>
 
