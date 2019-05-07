<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账户详情_会员中心</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
<link href="${basePath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css">
  <style>
 	.overdueRepay,a:hover{color:#fff;}
 </style>
 </head>
<body style="height:2000px;">
<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
<!-- layout start -->
<div class="nwd_main bor_l bor_r bor_b clearfix">
	<div class="fl perCenterBg">
		<!-- 左侧 -->
		<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
 		<!--  右侧start -->
			<div class="fl perCerterR  bor_r bor_l">
							<!-- 资金记录 -->
							<div class="fl pad_l30 pad_t30 pad_b40 pad_r30 wid_w900 clearfix">
								<div class="loadDiv fc_9 clearfix">
									<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要还款</span>
								</div>
								<div class="pad_b10 pad_t30 clearfix">
									<div class="pad_l20 clearfix">
										<ul class="zhijinCon clearfix">
<!-- 											<li style="margin: 0 10px 0 0px;"> -->
<!-- 												<p class="fc_9 mar_b5">本期应还</p> -->
<!-- 												<p class="fc_f60 fs_18 dlDecimal" style="overflow:hidden"> -->
<!-- 													<em class="fs_28 Numfont" id="accAmount">0</em><i class="fs_16 mar_r5">.00</i><em class="fs_14 fc_3">元</em><span style="color:#626262;font-size:14px;display: inline;">（第-期）</span> -->
<!-- 													<span class="pad_t20" style="float: right;"> -->
<!-- 														<input type="button" class="btn  button-100  " onclick="javascript:toDelTranBuyApply('10449834247766')" disabled="disabled" value="立即还款" style="line-height:26px;"> -->
<!-- 													</span> -->
<!-- 												</p> -->
<!-- 												<p class="fc_9 pad_t10">剩余期数：<em class="fc_6 mar_r5"> -->
<!-- 													-/- -->
<!-- 													</em><em class="fc_6 mar_r5">期</em>  -->
													
<!-- 													待还本息：<em class="fc_red"> -->
<!-- 													0.00 -->
<!-- 													</em> <em class="fc_red">元</em> -->
<!-- 												</p> -->
												
<!-- 											</li> -->
 											<li class="smallli bor_l">
												<p class="fc_9 mar_b5">账户可用余额</p>
												<p class="fc_f6 fs_18 dlDecimal">
													<em class="fs_28 Numfont" id="freezeAmount">${userAccount.avlbalance}</em>
 												</p>
  											</li>
											<li class="smallli bor_l">
												<a href="${basePath}/user/userRecharge/rechargeList.action" class="btn btn_bgf60 btn_size100">充值</a>
											</li>
										</ul>
									</div>
							</div>
							<!--账户总资产-->
							
							<form id="investment" name="investment" action="${basePath}/user/tender/myTenderRecord.action" method="post" autocomplete="off">
							<!-- 切换 -->
							<div class="fl pad_b10 pad_t20 tab indexTab clearfix indexHandle ui-select-listBox">
								<input type="hidden" id="low_paymentSel_status" value="${status}" />
								<ul class="tab_title clearfix">
									<li class="fl ui-select-listBox-list active" value="1"><a href="javascript:void(0);" class="pad_b10 low_paymentSel" onclick="userloanApp.low_paymentSel(this)" data-status="0">全部</a></li>
									<li class="fl ui-select-listBox-list" value="1"><a href="javascript:void(0);" class="pad_b10 low_paymentSel" onclick="userloanApp.low_paymentSel(this)" data-status="1">还款中</a></li>
									<li class="fl ui-select-listBox-list" value="1"><a href="javascript:void(0);" class="pad_b10 low_paymentSel" onclick="userloanApp.low_paymentSel(this)" data-status="2">已还款</a></li>
   								</ul>
								<div class="ui-select-listBox-line">
						            <div class="ui-select-listBox-l-blue" style="left: 20px; width: 83.3333px;"></div>
 						        </div>
 						        <div style="width:100px;height:32px;position:absolute;top:15px;right:0;display:none;" id="detailCallBack">
 						        	<a href= "${basePath}/user/userLoanApp/list.action" class="btn btn_bgf60" style="width: 60px;height: 30px;line-height:30px;">返回</a>
 						        </div>
<%--  						        href="${basePath}/user/userLoanApp/list.action" --%>
							</div>
 		                    	<input type="hidden" name="moreCode" id="moreCode" value="-1">
		                    	<input type="hidden" name="timeCondition" id="timeCondition" value="all">
		                    	<!-- tab_content start -->
								<div class="tab_content clearfix">
									<!-- tab_box start -->
									<div class="tab_box" style="display: block;">
										<!-- box1 start -->
										<div class="box box1" id="repayMent_box1">
											<jsp:include page="/WEB-INF/jsp/user/manager/repayment/listTemplateTenderItemDetail.jsp"></jsp:include>
 										</div>
										<!-- box1 end -->
 									</div>
									<!-- tab_box end -->
									
									
									<!-- test start -->
											
									<!-- test end -->
									
									
								</div>
								<!-- tab_content end -->
								
								<div class="nwd_fy mar_t30">
							       <div class="r">
							         	<div class="fy" id="contentErrorID">
										</div>
									</div>
								</div>
							</form>
 							</div>
 							 
			</div>
	 		<!-- 右侧 end -->
 	</div>
</div>

<!-- normalRepaySelect start -->
<div class="panelbox wid_w480" id="normalRepaySelect" style="display: none;">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">选择正常还款方式</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="userloanApp.normalRepaySelectColoe(this)"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item txt_center clearfix">
 		                    <a class="btn btn_36c btn_size120 overdueRepay" id="apartnormalRepay" onclick="userloanApp.low_doRepayMentPartSelect(this)">部分还款</a>
		                    <a class="btn btn_bgf60 btn_size120 overdueRepay" id="doRepayMentNormalAll" onclick="userloanApp.low_doRepayMentNormalAll(this)">全部还款</a>
  	                    </div>
 		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
 <!-- normalRepaySelect end -->
 
<!-- aheadRepaySelect start -->
<div class="panelbox wid_w480" id="aheadRepaySelect" style="display: none;">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">选择提前还款方式</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="userloanApp.aheadRepaySelectColoe(this)"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item txt_center clearfix">
		                    <a class="btn btn_36c btn_size120 overdueRepay" id="apartAheadRepay" onclick="userloanApp.low_selectPartAheadRepayMent(this)">部分还款</a>
		                    <a class="btn btn_bgf60 btn_size120 overdueRepay" id="allAheadRepay" onclick="userloanApp.low_selectAllAheadRepayMent(this)">全部还款</a>
  	                    </div>
 		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
 <!-- aheadRepaySelect end -->

<!-- overdueRepaySelect start -->
<div class="panelbox wid_w480" id="overdueRepaySelect" style="display: none;">
    <div class="panelbg"></div>
    <div class="panelwrap">
        <div class="paneltitle">
            <span class="text">选择逾期还款方式</span>
            <span class="panelclose nwd_icon nwd_icon_close pop-close" onclick="userloanApp.overdueRepaySelectColoe(this)"></span>
        </div>
        <div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
            <div class="container">
                <div class="analog-success">
	                <div class="form">
		                <div class="item txt_center clearfix">
		                    <a class="btn btn_36c btn_size120 overdueRepay" id="apartOverdueRepay" onclick="userloanApp.low_doOverdueRepayMentPartSelect(this)">部分逾期还款</a>
		                    <a class="btn btn_bgf60 btn_size120 overdueRepay" id="allOverdueRepay" onclick="userloanApp.low_doOverdueRepayMentNormalAll(this)">全部逾期还款</a>
  	                    </div>
 		            </div>   
                </div>
            </div>
        </div>
    </div>
</div>
 <!-- overdueRepaySelect end -->
 
<div class="bg" style="display:none;"></div>
<!-- layou end -->
<!-- 尾部 -->
<%--  <%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> --%>
 
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/userloanApp.js"></script>
<script type="text/javascript">
$(function(){
	 $(".side_nav").find("dl").siblings().removeClass("navcurron").eq(3).addClass("navcurron");
	 var status = $("#low_paymentSel_status").val();
	 $(".tab_title.clearfix  li").removeClass("active");
	 $(".tab_title.clearfix  li").eq(status).addClass("active");
	 
	 
	 
	 
	 $.post()
 });
 
$(document).ready(function(){
	$(".fc_blue.mar_l5.mar_r5.mar_b5.more").click(function(){
		$(".fl.a_btn.wid_800 span a").toggle();
	});
	
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
 
</body>
</html>