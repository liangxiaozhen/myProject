<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta http-equiv="Content-Language" content="zh-cn">
<meta name="robots" content="all">

<title>投资管理-投资计划</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${basePath}/resources/resource/Css/new.css" rel="stylesheet" type="text/css" >
<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
<link href="${basePath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
.box{border: none;}
</style>
<script type="text/javascript">
/*** 分页查询投标记录 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#investment").submit();
}
</script>
</head>
<body>
	<!-- 头部-->
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
		<!--左侧-->
		<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
		<!-- 右侧 -->
		<div class="fl perCerterR bor_l">
			<div class="fl pad_30 wid_w900 clearfix">
				<div class="loadDiv fc_9 clearfix">
					<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我的债权</span> 
					<span class="fr fs_14"><i class="nwd_icon nwd_icon_qiangbiao"></i>智能抢标<a href="{:U('Message/autoLoan')}" class="showqichi  mar_l15">查看</a></span>
				</div>
				<!-- 我的债权  -->
				<div class="pad_t20 pad_l20 clearfix">
					<ul class="zhaiquan_all clearfix">
						<li>
							<p class="fc_9 mar_b5">昨日收益</p>								
							<p class="fc_f60 fs_14 dlDecimal">
								<em class="fs_22 Numfont" id="yesterdayCredtioProfit">0</em><i class="fs_16 mar_r5">.00</i><em class="fs_14 fc_3">元</em>
							</p>
						</li>
						<li>
							<p class="fc_9 mar_b5">历史累计收益</p>								
							<p class="fc_6 fs_14 dlDecimal">
								<em class="fs_22 Numfont">0</em><i class="fs_16 mar_r5">.00</i><em class="fs_14 fc_3">元</em>&nbsp;<a href="https://member.niwodai.com/member/getProfitItemsSearchShow.do?tabIndex=1&amp;moreCode=2">查看</a>
							</p>
						</li>
						<li>
							<p class="fc_9 mar_b5">待收本金</p>
							<p class="fc_6 fs_14 dlDecimal">
								<em class="fs_22 Numfont">0</em><i class="fs_16 mar_r5">.00</i><em class="fs_14 fc_3">元</em>
							</p>							
						</li>						
						
						<li>
							<p class="fc_9 mar_b5">下期收款金额</p>
							<p class="fc_6 fs_14 dlDecimal"><em class="fs_22 Numfont" id="nextGatheringInfoAmount">0</em><i class="fs_16 mar_r5">.00</i><em class="fs_14 fc_3">元</em>
							<a href="https://member.niwodai.com/member/refundList.html"> 查看收款期次</a>
							</p>
							
							<em class="fc_9">下期收款日期</em>
							<em class="fc_6 mar_l5 Numfont">
													
							</em>
						</li>
						
						<li class="smallli" style="float: right;">
							<!-- <span class="fs_18 Numfont"><em class="fs_18 zhaiquandate mar_l5 fc_blue">23</em></span>
							<span class="fc_9 mar_b10">回款日历</span> -->
						</li>
					</ul>
				</div>
				<!-- 智能抢标 开启状态 -->
				<!--<div class="fl mar_t20  pad_b20 bor_t  clearfix bidding_on" style="width: 100%;">
					<div class="clearfix pad_t20 pad_b20 bidding_set">
						<h3 class="fl fs_14 pad_l20">智能抢标 <span class="fc_9 fs_14">（红包也能用于抢标，参与用户已达6879人）</span></h3><span class="fr mar_r20"><a href="zhinengqiangbiao.html">设置</a></span>
					</div>
					<div class="mar_t10 fl clearfix bidding_box">
					<div class="mar_l20 mar_r20">
						<span class="bidding_con1 fl"><em class="mar_r20 icon_base icon_16 icon_base_tipright16 fl"></em>预约产品： 季季丰</span><span class="bidding_con2">设置预约金额：<em class="fc_f3">5000元—5000元</em></span><span class="bidding_con3">投资期限： 3 个月</span><span  class=" bidding_con4">抵用券：使用</span>
					</div>
						 
					</div>
				</div>-->
				<!-- 智能抢标 未开启状态 -->
				<!--<div class="fl mar_t20  pad_b20 bor_t  clearfix bidding_on" style="width: 100%;">
					<div class="clearfix pad_t20 pad_b20 bidding_set">
						<h3 class="fl fs_14 pad_l20">智能抢标 <span class="fc_9 fs_14">（红包也能用于抢标，参与用户已达6879人）</span></h3><span class="fr mar_r20">
					</div>
					<div class="mar_t10 fl clearfix bidding_box">
					<div class="mar_l20 mar_r20">
						<span class="bidding_con1 fl"><em class="mar_r10 icon_base  icon_base_error fl"></em>你还没有开启智能抢标</span><a class="bidding_con4 fr" href="zhinengqiangbiao.html">立即开启</a>
					</div>
						 
					</div>
				</div>-->
			<form id="investment" name="investment" action="${basePath}/user/tender/myTenderRecord.action" method="post" autocomplete="off">
			<input type="hidden" id="pageNum" name="pageNum" value="" /> 
			<input type="hidden" id="pageSize" name="pageSize" value="" />
            <input type="hidden" name="lState" id="lState" value="1">
            <input type="hidden" name="dateType" id="dateType" value="all">
            <!--  用作查询明细用，现在明细移到祥情中了，故不需要了 <input type="hidden" name="lids" id="lids"  value=""/>-->
				
				
				<!-- 列表 -->
				<div class="fl mar_t20 pad_b10 tab indexTab clearfix indexHandle ui-select-listBox">
					<ul class="tab_title clearfix indexTab">
						<li class="fl ui-select-listBox-list ui-select-listBox-list--now" value="1"><a class="pad_b10" href="javascript:;">收款中</a></li>
						<li class="fl ui-select-listBox-list" value="2"><a class="pad_b10" href="javascript:;">已结清</a></li>
						<li class="fl ui-select-listBox-list" value="3"><a class="pad_b10" href="javascript:;">投标中</a></li>
						<li class="fl ui-select-listBox-list" value="4"><a class="pad_b10" href="javascript:;">满标待审</a></li>
						<li class="fl ui-select-listBox-list" value="5"><a class="pad_b10" href="javascript:;">投标失败</a></li>
					</ul>
					<div class="ui-select-listBox-line">
			            <div class="ui-select-listBox-l-blue" style="left: 20px; width: 83.3333px;"></div>
			        </div>
				</div>
				
				<div class="tab_content clearfix">
						<div class="tab_box" style="display: block;">
							<div class="searchCon pad_t10 pad_b10 pad_l20 fc_9 clearfix">
	                            <dl class="fl searchdl clearfix">
	                                <dt class="fl fc_6 pad_t5">加入日期</dt>
	                                <dd class="fl a_btn">
	                                    <input id="startdate1" name="startdate" class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime nullflag hasDatepicker" value="${startdate}">-
	                                    <input id="enddate2" name="enddate" class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime nullflag hasDatepicker" value="${enddate}">
	                                    <a href="javascript:;" class="fc_6 mar_l5 mar_r5 active" data-value="all">全部</a>
		                                <a href="javascript:;" class="fc_6 mar_l5 mar_r5" data-value="week">最近一周</a>
		                                <a href="javascript:;" class="fc_6 mar_l5 mar_r5" data-value="oneMonth">最近一个月</a>
		                                <a href="javascript:;" class="fc_6 mar_l5 mar_r5" data-value="threeMonth">最近三个月</a>
                              
	                                </dd>
	                            </dl>
	                     </div>
						<!-- 收款中 -->
						<div class="box box1">
							<table class="table fc_9 mar_t5 bor_t " cellspacing="0"
								cellpadding="0">
								<tbody>
									<tr>
										<th class="fc_3">投标日期</th>
										<th class="fc_3">标号</th>
										<th class="fc_3">投资金额</th>
										<th class="fc_3">借款年利率</th>
										<th class="fc_3">待收本金</th>
										<th class="fc_3">待收利息</th>
										<th class="fc_3">下期收款日</th>
										<th class="fc_3">操作</th>
									</tr>
									<c:if test="${!empty pagehelper.list}">
										<c:forEach items="${pagehelper.list}" var="tender">
											<tr>
												<td>${tender.tbegintimeStr}</td>
												<td>${tender.tenderNo}</td>
												<td><fmt:formatNumber value="${tender.amount}" type="currency" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.tinterest}" type="percent" /></td>
												<td>${tender.amount}</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<c:if test="${empty pagehelper.list}">
								<div class="h-noRecord">
									<div class="h-ulist">
										<div class="noRecord"></div>
									</div>
								</div>
							</c:if>
							<c:if test="${!empty pagehelper.list}">
								<div id="page_div"><%@ include
									file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
							</c:if>
						</div>
						<!-- 已结清 -->
						<div class="box box2">
							<table class="table fc_9 mar_t5 bor_t " cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<th class="fc_3">投标日期</th>
										<th class="fc_3">债权ID</th>
										<th class="fc_3">投资金额</th>
										<th class="fc_3">借款年利率</th>
										<th class="fc_3">待收本金</th>
										<th class="fc_3">操作</th>	                            
									</tr>
									<c:if test="${!empty pagehelper.list}">
										<c:forEach items="${pagehelper.list}" var="tender">
											<tr>
												<td>${tender.tbegintimeStr}</td>
												<td>${tender.orderno}</td>
												<td><fmt:formatNumber value="${tender.amount}" type="currency" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.tinterest}" type="percent" /></td>
												<td>--</td>
												<td>--</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<c:if test="${empty pagehelper.list}">
								<div class="h-noRecord">
									<div class="h-ulist">
										<div class="noRecord"></div>
									</div>
								</div>
							</c:if>
							<c:if test="${!empty pagehelper.list}">
								<div id="page_div"><%@ include
									file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
							</c:if>
						</div>		
						<!-- 投标中 -->
						<div class="box box3">
							<table class="table fc_9 mar_t5 bor_t " cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<th class="fc_3">投标日期</th>
										<th class="fc_3">债权ID</th>
										<th class="fc_3">投资金额</th>
										<th class="fc_3">借款年利率</th>
										<th class="fc_3">借款期限</th>
										<th class="fc_3">投资进度</th>	                            
									</tr>
									<c:if test="${!empty pagehelper.list}">
										<c:forEach items="${pagehelper.list}" var="tender">
											<tr>
												<td>${tender.tbegintimeStr}</td>
												<td>${tender.orderno}</td>
												<td><fmt:formatNumber value="${tender.amount}" type="currency" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.tinterest}" type="percent" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.loantime}" />${tender.tenderitem.dayormonth}</td>
												<td>--</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<c:if test="${empty pagehelper.list}">
								<div class="h-noRecord">
									<div class="h-ulist">
										<div class="noRecord"></div>
									</div>
								</div>
							</c:if>
							<c:if test="${!empty pagehelper.list}">
								<div id="page_div"><%@ include
									file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
							</c:if>
						</div>	
						<!-- 满标待审 -->
						<div class="box box4">
							<table class="table fc_9 mar_t5 bor_t " cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<th class="fc_3">投标日期</th>
										<th class="fc_3">债权ID</th>
										<th class="fc_3">投资金额</th>
										<th class="fc_3">借款年利率</th>
										<th class="fc_3">借款期限</th>
										<th class="fc_3">满标时间</th>	                          
									</tr>
									<c:if test="${!empty pagehelper.list}">
										<c:forEach items="${pagehelper.list}" var="tender">
											<tr>
												<td>${tender.tbegintimeStr}</td>
												<td>${tender.orderno}</td>
												<td><fmt:formatNumber value="${tender.amount}" type="currency" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.tinterest}" type="percent" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.loantime}" />${tender.tenderitem.dayormonth}</td>
												<td>--</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<c:if test="${empty pagehelper.list}">
								<div class="h-noRecord">
									<div class="h-ulist">
										<div class="noRecord"></div>
									</div>
								</div>
							</c:if>
							<c:if test="${!empty pagehelper.list}">
								<div id="page_div"><%@ include
									file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
							</c:if>
						</div>	
						<!-- 投标失败 -->
						<div class="box box5">
							<table class="table fc_9 mar_t5 bor_t " cellspacing="0" cellpadding="0">
								<tbody>
									<tr>
										<th class="fc_3">投标日期</th>
										<th class="fc_3">债权ID</th>
										<th class="fc_3">投资金额</th>
										<th class="fc_3">借款年利率</th>
										<th class="fc_3">借款期限</th>                           
									</tr>
									<c:if test="${!empty pagehelper.list}">
										<c:forEach items="${pagehelper.list}" var="tender">
											<tr>
												<td>${tender.tbegintimeStr}</td>
												<td>${tender.orderno}</td>
												<td><fmt:formatNumber value="${tender.amount}" type="currency" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.tinterest}" type="percent" /></td>
												<td><fmt:formatNumber value="${tender.tenderitem.loantime}" />${tender.tenderitem.dayormonth}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<c:if test="${empty pagehelper.list}">
								<div class="h-noRecord">
									<div class="h-ulist">
										<div class="noRecord"></div>
									</div>
								</div>
							</c:if>
							<c:if test="${!empty pagehelper.list}">
								<div id="page_div"><%@ include
									file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
							</c:if>
						</div>	
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script src="${basePath}/resources/resource/Scripts/jquery-ui.min.js"></script>
<script src="${basePath}/resources/resource/Scripts/mobiscroll_date.js" charset="gb2312"></script> 
<script src="${basePath}/resources/resource/Scripts/mobiscroll.js"></script> 

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

	$("#startdate1,#enddate2").mobiscroll($.extend(opt['date'], opt['default']));
	$(".side_nav").find("dl").siblings().removeClass("navcurron").eq(2).addClass("navcurron");

});
//判断结束时间是否小于开始时间
$('.dwb-s .dwb').on("click",function (){
	var onetome=$("#startdate1").val();
	var endtime=$("#enddate2").val();
	if(onetome!='' && endtime!=''){
		if(endtime<onetome){
			$("#endtime").val('');
			alert('结束时间不能小于开始时间');
		}
	}
})

$(document).ready(function(){
	$(".box2,.box3,.box4,.box5").hide();
	$(".tab_title.clearfix  li").click(function(){
		$(".tab_title.clearfix  li").removeClass("ui-select-listBox-list--now");
		var num = $(this).val();
		$("#lState").val(num);
		$(this).addClass("ui-select-listBox-list--now");
		$("div .ui-select-listBox-l-blue").css("left",(num-1)*125+20+"px");
		$(".box").hide();
		$(".box"+num).show();
		$("#startdate1").val('');
		$("#enddate2").val('');
		$("#investment").submit();
	});
	    
	$(".fl.a_btn .fc_6").click(function(){
		$(".fl.a_btn .fc_6").removeClass("active");
		$(this).addClass("active");
		var num=$(this).data("value");
		$("#dateType").val(num);
		$("#lState").val("${lState}");
		$("#startdate1").val('');
		$("#enddate2").val('');
		$("#investment").submit();
	})
	    
	$(".tab_title.clearfix  li").mouseover(function(){
		var num = $(this).val();
		$("div .ui-select-listBox-l-blue").css("left",(num-1)*125+20+"px");
	});	
	$(".tab_title.clearfix  li").mouseout(function(){
		var num = $("#lState").val();
		$("div .ui-select-listBox-l-blue").css("left",(num-1)*125+20+"px");
	});
})

$(function() {
	var state = ${lState};
	$("#lState").val(state);
	$(".tab_title.clearfix").find("li").each(function(index, value) {
		$(this).removeClass("ui-select-listBox-list--now");
		if (index == state-1) {
			$(this).addClass("ui-select-listBox-list--now");
			$("div .ui-select-listBox-l-blue").css("left",index*125+20+"px");
			$(".box").hide();
			$(".box"+state).show();
		}
	});
	var type = "${dateType}";
	$("#dateType").val(type);
	$(".fl.a_btn").find("a").each(function(index, value) {
		var num=$(this).data("value");
		$(this).removeClass("active");
		if (num == type) {
			$(this).addClass("active");
		}
	});
})

$("#startdate1").bind("change",function(){
	$("#investment").submit();
});
$("#enddate2").bind("change",function(){
	$("#investment").submit();
});

</script>
</body>
</html>