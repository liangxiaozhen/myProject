<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金记录 - 干将网贷</title>
<link
	href="${basePath }/resources/resource/Css/pagehelper.css"
	rel="stylesheet" type="text/css">
<link
	href="${basePath }/resources/resource/Css/nwd_common.css"
	rel="stylesheet" type="text/css">
<link
	href="${basePath }/resources/resource/Css/nwd_perCenter.css"
	rel="stylesheet" type="text/css">
<link
	href="${basePath }/resources/resource/Css/nwd_vipstyle.css"
	rel="stylesheet" type="text/css">
<link
	href="${basePath }/resources/resource/Css/mobiscroll.css"
	rel="stylesheet" />
<link
	href="${basePath }/resources/resource/Css/mobiscroll_date.css"
	rel="stylesheet" />
<link
	href="${basePath }/resources/resource/Css/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
</head>
<body>
	<!-- 头部 -->
	<%@include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧-->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- 右侧-->
			<div class="fl perCerterR  bor_r bor_l">
				<!-- 资金记录 -->
				<div class="fl pad_l30 pad_t30 pad_b40 pad_r30 wid_w900 clearfix">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">资金记录</span>
					</div>
					<div class="pad_b10 pad_t30 clearfix">
						<div class="pad_l20 clearfix">
							<ul class="zhijinCon clearfix">
								<li>
									<p class="fc_9 mar_b5">可用余额</p>
									<p class="fc_f60 fs_18 dlDecimal">
										<em class="fs_28 Numfont" id="accAmount">${avlBalanceA }</em><i
											class="fs_16 mar_r5">${avlBalanceB }</i><em
											class="fs_14 fc_3">元</em>
									</p>
								</li>
								<li class="smallli bor_l">
									<p class="fc_9 mar_b5">冻结金额</p>
									<p class="fc_f6 fs_18 dlDecimal">
										<em class="fs_28 Numfont" id="freezeAmount">${freezeBalanceA }</em><i
											class="fs_16 mar_r5">${freezeBalanceB }</i><em
											class="fs_14 fc_3">元</em>
									</p>
								</li>
								<li class=""><a
									href="${basePath }/user/userRecharge/rechargeList.action"
									class="btn btn_bgf60 btn_size100">充值</a> <a
									href="${basePath }/user/userwithdrawscash/withdraw.action"
									class="btn btn_bgfff btn_size100">提现</a></li>
							</ul>
						</div>
					</div>
					<!--账户总资产-->
					<!-- 切换 -->
					<div
						class="fl pad_b10 pad_t20 tab indexTab clearfix indexHandle ui-select-listBox">
						<ul class="tab_title clearfix">
							<li
								class="fl ui-select-listBox-list--now ui-select-listBox-list "
								value="1"><a
								href="${basePath }/user/userAccInExRecord/list.action"
								class="pad_b10">资金流动</a></li>
							<li class="fl ui-select-listBox-list" value="2"><a
								href="${basePath }/user/UsrUnFreeze/UsrUnFreezeRecord.action" class="pad_b10">资金冻结</a></li>
							<li class="fl ui-select-listBox-list active" value="3"><a
								href="javascript:;" class="pad_b10">充值记录</a></li>
							<li class="fl ui-select-listBox-list " value="4"><a
								href="${basePath }/user/userAccInExRecord/carryLog.action" class="pad_b10">提现记录</a></li>
						</ul>
						<div class="ui-select-listBox-line">
							<div class="ui-select-listBox-l-blue"
								style="left: 20px; width: 83.3333px;"></div>
						</div>
					</div>
					<form action="rechargeRecord.action" id="carryLog" method="post"
						class="nwd-formUi">
						<input type="hidden" id="sign" name="sign" value="${sign }">
						<input type="hidden" id="status" name="status" value="${status}">
						<div class="tab_content clearfix">
							<div class="tab_box" style="display: block;">
								<div class="searchCon pad_t10 pad_b10 pad_l20 fc_9 clearfix">
									<dl class="fl searchdl clearfix">
										<dt class="fl fc_6 pad_t5">投资日期：</dt>
										<dd class="fl b_btn" id="conditionMore">
											<input
												class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime "
												id="startdate1" name="startdate">- <input
												class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime "
												id="enddate2" name="enddate"> <a href="javascript:;"
												id="all" class="fc_6 mar_l5 mar_r5 active">全部</a> <a
												href="javascript:;" id="week" class="fc_6 mar_l5 mar_r5 ">最近一周</a>
											<a href="javascript:;" id="month" class="fc_6 mar_l5 mar_r5 ">最近一个月</a>
											<a href="javascript:;" id="threeMonth"
												class="fc_6 mar_l5 mar_r5 ">最近三个月</a>
										</dd>
									</dl>
								</div>
								<div class="searchCon pad_t5 pad_b10 pad_l20 fc_9 clearfix">
									<dl class="fl searchdl">
										<dt class="fl fc_9">类型：</dt>
										<dd class="fl a_btn wid_800">
											<span> <a href="javascript:;" id="type0"
												class="fc_6 mar_l5 mar_r5  active">全部</a> <a
												href="javascript:void(0)" id="type1"
												class="fc_6 mar_l5 mar_r5">成功</a> <a
												href="javascript:void(0)" id="type2"
												class="fc_6 mar_l5 mar_r5">失败</a> <a
												href="javascript:void(0)" id="type4"
												class="fc_6 mar_l5 mar_r5">处理中</a> <!--<a
												href="javascript:void(0)" id="type4"
												class="fc_6 mar_l5 mar_r5">提现手续费</a> <a
												href="javascript:void(0)" id="type5"
												class="fc_6 mar_l5 mar_r5">投标</a> <a
												href="javascript:void(0)" id="type6"
												class="fc_6 mar_l5 mar_r5">还款</a> <a
												href="javascript:void(0)" id="type7"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">会员升级费</a>
												<a href="javascript:void(0)" id="type8" value="8"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">站岗利息</a>
												 
												<a href="javascript:void(0)" id="fa_15type" value="15"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">VIP费</a>
												<a href="javascript:void(0) " id="fa_16type" value="16"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">认证费</a>
												<a href="javascript:void(0)" id="fa_17type" value="17"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">网站奖励</a>
												<a href="javascript:void(0)" id="fa_18type" value="18"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">网站退款</a>
												 
												<a href="javascript:void(0)" id="fa_130type" value="130"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">加入有道智投</a>
												<a href="javascript:void(0)" id="fa_131type" value="131"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">退出有道智投</a>
												<a href="javascript:void(0)" id="fa_79type" value="79"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">担保管理费</a>
												<a href="javascript:void(0)" id="fa_81type" value="81"
												class="fc_6 mar_l5 mar_r5 dp" style="display: none;">担保服务费</a>
												-->
											</span>
											<!--  <a href="javascript:void(0)"
												class="fc_blue mar_l5 mar_r5 mar_b5 more">更多</a>-->
										</dd>
									</dl>
								</div>
								<!-- 提现记录-->
								<div class="box box4">
									<table class="table fc_6 mar_t5 bor_t">
										<tbody>
											<tr>
												<td>充值时间</td>
												<td>充值金额</td>
												<td>手续费</td>
												<td>充值方式</td>
												<td>处理状态</td>
												<!-- <td>备注</td> -->
											</tr>
											<c:forEach items="${pagehelper.list}" var="item"
												varStatus="status">
												<tr class="text-center">

													<td><c:if test="${!empty item.starttime}">${sf.format(item.starttime)}</c:if>
													</td>
													<td><c:if test="${!empty item.amount}">${df.format(item.amount)}</c:if>
														<c:if test="${empty item.amount}">0.00</c:if></td>
													<td><c:if test="${!empty item.recharfee}">${df.format(item.recharfee)}</c:if>
														<c:if test="${empty item.recharfee}">0.00</c:if></td>

													<td>
														<c:if test="${item.rechargetype eq '1'}">个人网银</c:if> 
														<c:if test="${item.rechargetype eq '2'}">快捷支付</c:if> <c:if
															test="${item.rechargetype eq '3'}">企业网银</c:if> <c:if
															test="${item.rechargetype eq '4'}">汇款充值</c:if></td>

													<td>
														<c:if test="${item.status==1}">
															<font style="color: green">成功</font>
														</c:if> <c:if test="${item.status==2}">
															<font style="color: red">失败</font>
														</c:if> <c:if test="${item.status==3}">
															取消
														</c:if> <c:if test="${item.status==4}">
															<font style="color: blue">待充值</font>
														</c:if>
													</td>
												<%-- 	<td>
														<c:if test="${!empty item.remark}">${item.remark}</c:if>
													</td> --%>
												<%-- 	<td><c:if
															test="${item.isblending==1 or item.ismanblending==1}">
															<c:if
																test="${item.status==3 and item.urid eq 0 and item.isexceptions==0}">
																<input type="button" onclick="rechargeagain(${item.id})"
																	style="color: blue;" value="再次充值" />
															</c:if>
														</c:if> <c:if test="${item.urid eq 1}">
																已再次充值
																</c:if></td> --%>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="page_div">
							<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--layout end-->

	<script type="text/javascript"
		src="${basePath }/resources/resource/Scripts/jquery.js"></script>
	<script type="text/javascript"
		src="${basePath }/resources/resource/Scripts/jquery-1.7.2.min.js"></script>

	<script
		src="${basePath }/resources/resource/Scripts/jquery-ui.min.js"></script>
	<script
		src="${basePath }/resources/resource/Scripts/mobiscroll_date.js"
		charset="gb2312"></script>
	<script
		src="${basePath }/resources/resource/Scripts/mobiscroll.js"></script>
	<!-- 时间 -->
	<script type="text/javascript">
$(function () {
	$(".side_nav").find("dl").siblings().removeClass("navcurron").eq(1).addClass("navcurron");
	var startdate = "${startdate}";
	var enddate= "${enddate}";
	var sign="${sign}";
	var status="${status}";
	$("#startdate1").val(startdate);
	$("#enddate2").val(enddate);
	if(startdate!=''||enddate!=''){
		$(".fl.b_btn .fc_6.mar_l5.mar_r5").removeClass("active");
	}
	$(".fl.b_btn .fc_6.mar_l5.mar_r5").click(function(){
		$(".fl.b_btn .fc_6.mar_l5.mar_r5").removeClass("active");
		$(this).addClass("active");
		$("#startdate1").val("");
		$("#enddate2").val("");
		$("#sign").val($(this).attr("id"));
		$("#carryLog").submit();
	});
	$(".fl.a_btn.wid_800 span a").click(function(){
		var status=$(this).attr("id");
		status=status.substring(4);
		if(status==0){
			$("#status").val("");
		}else{
			$("#status").val(status);
		}
		$("#carryLog").submit();
	});
	if(sign!=''){
		$(".fl.b_btn .fc_6.mar_l5.mar_r5").removeClass("active");
		$("#"+sign).addClass("active");
	}
	if(status!=''){
		$(".fl.a_btn.wid_800 span a").removeClass("active");
		$("#type"+status).addClass("active");
	}
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
	 
	 $("#startdate1,#enddate2").change(function(){
		 $("#sign").val("");
		 $("#carryLog").submit();
	 });

});
$(document).ready(function(){
	$(".fc_blue.mar_l5.mar_r5.mar_b5.more").click(function(){
		$(".fl.a_btn.wid_800 span dp").toggle();
	});
})

	//跳转页面	
	function queryAllPerson(pageNum, pageSize) {
		location.href = "rechargeRecord.action?pageNum=" + pageNum + "&pageSize="
				+ pageSize+"&btime="+"${startime}"+"&etime="+"${enddate}"+"&sign="+"${sign}"+"&type="+"${type}"+"&status="+"${status}";
	}

</script>

</body>
</html>