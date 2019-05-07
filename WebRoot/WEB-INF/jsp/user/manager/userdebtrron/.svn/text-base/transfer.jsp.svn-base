<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%-- <script type="text/javascript" src="${basePath}/resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script> --%>
<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
<link href="${basePath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<%-- <link href="${basePath}/resources/resource/Css/new.css" rel="stylesheet"/> --%>
<link href="${basePath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css">

<style type="text/css">#YSF-BTN-HOLDER{position: fixed;max-width:70px;max-height:70px;right: 30px; bottom: 0px; cursor: pointer; overflow: visible; filter: alpha(opacity=100);opacity:1;z-index: 9990} #YSF-BTN-HOLDER:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-HOLDER img{ display: block;overflow: hidden; } #YSF-BTN-CIRCLE{display: none;position: absolute;right: -5px;top: -5px;width: auto;min-width: 12px;height: 20px;padding: 0 4px;background-color: #f00;font-size: 12px;line-height: 20px;color: #fff;text-align: center;white-space: nowrap;font-family: sans-serif;border-radius: 10px;z-index:1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-CIRCLE{top: -30px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-CIRCLE{top: -5px;} #YSF-BTN-BUBBLE{display: none;position: absolute;left: -274px;bottom:0px;width: 278px;height: 80px;box-sizing: border-box;padding: 14px 22px;filter: alpha(opacity=100);opacity:1;background: url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg2x.png) no-repeat;background:url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg.png)9; background-size: 278px 80px; z-index: 1;} #YSF-BTN-HOLDER.layer-1 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-2 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-3 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-4 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-5 #YSF-BTN-BUBBLE{bottom:9px;} #YSF-BTN-HOLDER.layer-6 #YSF-BTN-BUBBLE{bottom:-6px;} #YSF-BTN-BUBBLE:hover{filter: alpha(opacity=95);opacity:.95} #YSF-BTN-CONTENT{height:45px;padding: 0;white-space: normal;word-break: break-all;text-align: left;font-size: 14px;line-height: 1.6;color: #222;overflow: hidden;z-index: 0;} #YSF-BTN-ARROW{ display: none; } #YSF-BTN-CLOSE{position: absolute; width:15px; height:15px;right: 4px;top: -3px; filter: alpha(opacity=90); opacity:.9; cursor: pointer; background: url(https://qiyukf.com/sdk//res/img/sdk/btn-close.png) no-repeat;z-index: 1} #YSF-BTN-CLOSE:hover{filter: alpha(opacity=100); opacity: 1;} #YSF-PANEL-CORPINFO.ysf-chat-layeropen{ width: 511px; height: 470px; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-CORPINFO{ position: fixed; bottom: 0px; right: 20px; width: 0; height: 0; z-index: 99999; } #YSF-PANEL-INFO.ysf-chat-layeropen{ width: 311px; height: 470px; filter: alpha(opacity=100);opacity:1; box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);} #YSF-PANEL-INFO{ position: fixed; bottom: 0px; right: 20px; width: 0px; height: 0px; filter: alpha(opacity=0);opacity:0;z-index: 99999;} #YSF-PANEL-INFO .u-btn{background-color: #F2A654} #YSF-CUSTOM-ENTRY{background-color: #F96868;} #YSF-CUSTOM-ENTRY-0{position: relative;bottom: 24px;width:auto;background-color: #F2A654;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-1{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 14px; box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-2{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 0;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-3{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-4{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 50%;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-5{position: relative;bottom: 24px;width:auto;background-color: #F2A654;border-radius: 5px;box-shadow: 0px 6px 10px 0px rgba(0,0,0,0.25);} #YSF-CUSTOM-ENTRY-6{position: relative;bottom: 0px;width:auto;background-color: #F2A654;border-radius:5px;border-bottom-left-radius: 0;border-bottom-right-radius: 0;} #YSF-CUSTOM-ENTRY-0 img{max-width: 300px;height:auto;} #YSF-CUSTOM-ENTRY-1 img{width:28px;height:auto;} #YSF-CUSTOM-ENTRY-2 img{width:58px;height:auto;} #YSF-CUSTOM-ENTRY-3 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-4 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-5 img{width:60px;height:auto;} #YSF-CUSTOM-ENTRY-6 img{width:115px;height:auto;} #YSF-IFRAME-LAYER{ border:0; outline:none; } .ysf-online-invite-mask{z-index:10000;position:fixed;_position:absolute;top:0;left:0;right:0;bottom:0;width:100%;height:100%;background:#fff;-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";filter:alpha(opacity=0);opacity:0;} .ysf-online-invite-wrap{z-index:10001;position:fixed;_position:absolute;top:50%;left:50%;width:250px;} .ysf-online-invite{position:relative;top:-50%;left:-50%;cursor:pointer;} .ysf-online-invite img{display:block;width:250px;} .ysf-online-invite .text{position:absolute;top:-11px;left:0;right:0;overflow:hidden;margin: 36px 20px 0 67px;line-height:140%;color:#526069;font-size:14px;font-family:"Microsoft YaHei","微软雅黑",tahoma,arial,simsun,"宋体";text-align:left;white-space:normal;word-wrap:break-word;} .ysf-online-invite .close{position:absolute;top:-6px;right:-6px;width:32px;height:32px;background:url(https://qiyukf.com/sdk/res/img/invite-close.png) no-repeat;cursor:pointer;}</style></head>
<script type="text/javascript">
/*** 分页查询投标记录 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#investment").submit();
}
</script>
<body>
	<!-- 头部-->
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!--左侧-->	
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- 右侧 -->
			<form id="investment" name="investment" action="${basePath}/user/userdebtattorn/userdebtrron.action" method="post">
			<input type="hidden" id="pageNum" name="pageNum" value="" /> 
			<input type="hidden" id="pageSize" name="pageSize" value="" />
            <input type="hidden" name="lState" id="lState" value="1">
            <input type="hidden" name="dateType" id="dateType" value="all">
				<div class="fl perCerterR  bor_r bor_l">
					<div class="fl pad_t30 pad_r30 pad_b40 pad_l30 wid_w900 clearfix">
						<div class="loadDiv fc_9 clearfix">
							<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">债权转让</span>
						</div>
						<!-- 列表 -->
						<div class="fl mar_t20 pad_b10 tab indexTab clearfix indexHandle ui-select-listBox">
							<ul class="tab_title clearfix indexTab">
								<li class="fl ui-select-listBox-list ui-select-listBox-list--now active" value="1"><a class="pad_b10" href="javascript:void(0);">可转让</a></li>
								<li class="fl ui-select-listBox-list" value="2"><a class="pad_b10" href="javascript:void(0);">转让中</a></li>
								<li class="fl ui-select-listBox-list" value="3"><a class="pad_b10" href="javascript:void(0);">已转让</a></li>
								<li class="fl ui-select-listBox-list" value="4"><a class="pad_b10" href="javascript:void(0);">转让记录</a></li>
							</ul>
							<div class="ui-select-listBox-line">
					            <div class="ui-select-listBox-l-blue" style="left: 20px; width: 83.3333px;"></div>
					        </div>
						</div>
						<!-- 内容 -->
						<div class="searchCon pad_t10 pad_b5 pad_l20 fc_9 tab_content clearfix">
							<!-- 筛选条件 -->
							<dl class="searchdl clearfix">
		                    	<dt class="fl fc_6 pad_t5">投资日期：</dt>
		                        <dd class="fl a_btn">
		                            <input class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime hasDatepicker" id="startdate" name="startdate" value="${startdate}">-
		                            <input class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime hasDatepicker" id="enddate" name="enddate" value="${enddate}">
		                          	<a href="javascript:;" data-value="all" class="fc_6 mar_l5 mar_r5 active">全部</a>
		                            <a href="javascript:;" data-value="week" class="fc_6 mar_l5 mar_r5 ">最近一周</a>
		                            <a href="javascript:;" data-value="oneMonth" class="fc_6 mar_l5 mar_r5 ">最近一个月</a>
		                            <a href="javascript:;" data-value="threeMonth" class="fc_6 mar_l5 mar_r5 ">最近三个月</a>
		                        </dd>
		                    </dl>
		                    <!--债权转让 :可转让 -->
		                    <div class="box box1">
					    		<div class="tab_content clearfix">
									<div class="">
										<div class="noRecordBox">
				                        	<table class="table fc_9 bor_t mar_t5 " cellspacing="0" cellpadding="0">
												<tbody>
													<tr>
														<th class="tr">投资日期</th>
														<th class="tr">债权ID</th>
														<th class="tr">待收本金</th>
														<th class="tr">借款年利率</th>
														<th class="tr">操作</th>
													</tr> 
													
													<c:if test="${!empty dat}">
														<c:if test="${!empty pagehelper.list}">
															<c:forEach items="${pagehelper.list}" var="tender">
																
																	<tr>
																		<td class="">${sf.format(tender.tbegintime)}</td>
																		<td class="fc_3">${tender.orderno}</td>
																		<td class="fc_3">${tender.amount}</td>
																		<td class="fc_3">
																			<c:if test="${tender.utproperty=='1'}">
																				${tender.tenderitem.tinterest}
																			</c:if>
																			<c:if test="${tender.utproperty=='2'}">
																				${tender.yearprofit}
																			</c:if>
																		</td>
																		<td class="fc_3">转让</td>
																	</tr>
															</c:forEach>
													    </c:if>
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
										<div class="countDiv pad_10 mar_t10 clearfix">
											<span class="fl fc_blue mar_r30 mar_l10"><i class="nwd_icon icon_25 nwd_icon_listCount">&nbsp;</i>统计</span><span class="fl mar_r30 mar_l30 fc_9">待收金额：<em class="fc_f60">140元</em></span><span  class="fl mar_r30 fc_9">已收金额：<em class="fc_yellow">140元</em></span><span class="fr"><a href="#"><i class="nwd_icon icon_25 nwd_icon_listDown">&nbsp;</i>下载查询结果</a></span>
										</div>
									</div>
									<!--温馨提示-->
									<div class="wxtips mar_t20">
										<h3 class="title_wxtips pad_l20 pad_t30 pad_b10 fb fs_16">温馨提示</h3>
										<p class="fc_9 pad_l20">• 只有按月还款的标才能申请债权转让。</p>
										<p class="fc_9 pad_l20">• 用户投标成功并在审核通过后，开始计算可转让日期，锁定期90天，超过90天，用户可以提出债权转让。</p>
										<p class="fc_9 pad_l20">• 债权转让时，如果当前债权逾期且仍未还款，则该笔债权不可转让。</p>
										<p class="fc_9 pad_l20">• 限制转让时间必须距离下一个还款日&gt;=24小时。</p>
										<p class="fc_9 pad_l20">• 债权转让时该笔债权的剩余期数&lt;3期，则该笔债权不可转让。</p>
										<p class="fc_9 pad_l20">• 债权转让金额必须&gt;=50元。</p>
									</div>
								</div>
							</div>
							<!-- 转让中 -->
							<div class="box box2">
								<div class="tab_content clearfix">
									<div class="">
										<div class="noRecordBox">
				                        	<table class="table fc_9 bor_t mar_t5 " cellspacing="0" cellpadding="0">
												<tbody>
													<tr>
														<th class="tr">投资日期</th>
														<th class="tr">债权ID</th>
														<th class="tr">待收金额</th>
														<th class="tr">借款年利率</th>
														<th class="tr">操作</th>
													</tr>
													<c:if test="${!empty datt}">
													<c:if test="${!empty pagehelper.list}">
														<c:forEach items="${pagehelper.list}" var="userdebttron">
															
																<tr>
																	<td class="Numfont table_timelog">${sf.format(userdebttron.settime)}</td>
																	<td class="fc_3">${userdebttron.torderno}</td>
																	<td class="fc_3">${userdebttron.daamount}</td>
																	<td class="fc_3">${userdebttron.yearprofit}</td>
																	<td class="fc_3">转让中</td>
																</tr>
														
														</c:forEach>
											    	</c:if>
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
										<div class="countDiv pad_10 mar_t10 clearfix">
											<span class="fl fc_blue mar_r30 mar_l10"><i class="nwd_icon icon_25 nwd_icon_listCount">&nbsp;</i>统计</span><span class="fl mar_r30 mar_l30 fc_9">待收金额：<em class="fc_f60">140元</em></span><span  class="fl mar_r30 fc_9">已收金额：<em class="fc_yellow">140元</em></span><span class="fr"><a href="#"><i class="nwd_icon icon_25 nwd_icon_listDown">&nbsp;</i>下载查询结果</a></span>
										</div>
									</div>
									<!--温馨提示-->
									<div class="wxtips mar_t20">
										<h3 class="title_wxtips pad_l20 pad_t30 pad_b10 fb fs_16">温馨提示</h3>
										<p class="fc_9 pad_l20">* 转让中的债权，一旦用户开始投标便不可撤消！</p>
									</div>
								</div>
							</div>
							<!-- 已转让 -->
							<div class="box box3">
								<div class="tab_content clearfix">
									<div class="">
										<div class="noRecordBox">
				                        	<table class="table fc_9 bor_t mar_t5 " cellspacing="0" cellpadding="0">
												<tbody>
													<tr>
														<th class="tr">转让日期</th>
														<th class="tr">债权ID</th>
														<th class="tr">债权价值</th>
														<th class="tr">转让价格：</th>
														<th class="tr">手续费</th>
														<th class="tr">实收金额</th>
														<th class="tr">转让状态</th>
													</tr>
													<c:if test="${!empty datt}">
													<c:if test="${!empty pagehelper.list}">
														<c:forEach items="${pagehelper.list}" var="userdebttron">
															<tr>
																<td class="Numfont table_timelog">${sf.format(userdebttron.settime)}</td>
																<td class="fc_3">${userdebttron.torderno}</td>
																<td class="fc_3">${userdebttron.daamount}</td>
																<td class="fc_3">--</td>
																<td class="fc_3">--</td>
																<td class="fc_3">--</td>
																<td class="fc_3">转让成功</td>
															</tr>
														</c:forEach>
											    	</c:if>
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
										<div class="countDiv pad_10 mar_t10 clearfix">
											<span class="fl fc_blue mar_r30 mar_l10"><i class="nwd_icon icon_25 nwd_icon_listCount">&nbsp;</i>统计</span><span class="fl mar_r30 mar_l30 fc_9">待收金额：<em class="fc_f60">140元</em></span><span  class="fl mar_r30 fc_9">已收金额：<em class="fc_yellow">140元</em></span><span class="fr"><a href="#"><i class="nwd_icon icon_25 nwd_icon_listDown">&nbsp;</i>下载查询结果</a></span>
										</div>
									</div>
									<!--温馨提示-->
									<div class="wxtips mar_t20">
										<h3 class="title_wxtips pad_l20 pad_t30 pad_b10 fb fs_16">温馨提示</h3>
										<p class="fc_9 pad_l20">* 转让中的债权，一旦用户开始投标便不可撤消！</p>
										
									</div>
						    	</div>
							</div>
							<!-- 转让记录 -->
							<div class="box box4">
								<div class="tab_content clearfix">
									<div class="">
										<div class="noRecordBox">
				                        	<table class="table fc_9 bor_t mar_t5 " cellspacing="0" cellpadding="0">
												<tbody>
													<tr>
														<th class="tr">转让日期</th>
														<th class="tr">债权ID</th>
														<th class="tr">债权价值</th>
														<th class="tr">转让价格：</th>
														<th class="tr">手续费</th>
														<th class="tr">实收金额</th>
														<th class="tr">转让状态</th>
													</tr>
													<c:if test="${!empty datt}">
													<c:if test="${!empty pagehelper.list}">
														<c:forEach items="${pagehelper.list}" var="userdebttron">
															<tr>
																<td class="Numfont table_timelog">${sf.format(userdebttron.settime)}</td>
																<td class="fc_3">${userdebttron.torderno}</td>
																<td class="fc_3">${userdebttron.daamount}</td>
																<td class="fc_3">--</td>
																<td class="fc_3">--</td>
																<td class="fc_3">--</td>
																<td class="fc_3">已下架</td>
															</tr>
														</c:forEach>
											    	</c:if>
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
										<div class="countDiv pad_10 mar_t10 clearfix">
											<span class="fl fc_blue mar_r30 mar_l10"><i class="nwd_icon icon_25 nwd_icon_listCount">&nbsp;</i>统计</span><span class="fl mar_r30 mar_l30 fc_9">待收金额：<em class="fc_f60">140元</em></span><span  class="fl mar_r30 fc_9">已收金额：<em class="fc_yellow">140元</em></span><span class="fr"><a href="#"><i class="nwd_icon icon_25 nwd_icon_listDown">&nbsp;</i>下载查询结果</a></span>
										</div>
									</div>
									<!--温馨提示-->
									<div class="wxtips mar_t20">
										<h3 class="title_wxtips pad_l20 pad_t30 pad_b10 fb fs_16">温馨提示</h3>
										<p class="fc_9 pad_l20">* 转让中的债权，一旦用户开始投标便不可撤消！</p>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
  	 </div>
   <!--layout end-->

	<!--confirm Start-->
	<!-- 转让信息确认 -->
	<div class="panelbox wid_w750" id="tranDiv">
		<div class="panelwrap">
	        <div class="paneltitle">
	            <span class="text">转让信息</span>
	            <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
	        </div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
			    <div class="container">
			    	<!-- 导航 -->
	                <div class="middle" id="middle clearfix"> 
		                <div class="flowstep" style="margin:0 auto; width:480px;">           
				            <div id="headStep" class="steps steps_3 pad_t20 clearfix pad_b20">
				                <ul class="active  wid_w160 fl step-first txt_center" id="step1Title">
				                    <li class="sz"><span>1</span></li>
				                    <li class="sm">1.确认协议</li>
				                </ul>
				                <ul class="wid_w160 fl txt_center" id="changeOldStep2">
				                    <li class="sz"><span>2</span></li>
				                    <li class="sm">2.确认信息</li>
				                </ul>
				                <ul class="wid_w160 fl step-last txt_center" id="changeOldStep3">
				                    <li class="sz step-no"><span>3</span></li>
				                    <li class="sm">3. 发布完成</li>
				                </ul>
				            </div>
	        			</div>
	            	</div>
	            	<!-- 切换内容 -->
					<!--step1 Start-->
					<div class="stepCon" id="step1">
		                <div class="xieyi pad_t15 pad_b15">
		                    <p>&nbsp;&nbsp;&nbsp;&nbsp;甲、乙双方就甲方通过上海你我贷互联网金融信息服务有限公司限公司运营管理的你我贷网站www.niwodai.com（下称“你我贷平台”指上海你我贷互联网金融信息服务有限公司及你我贷网站的统称）向乙方转让债权事宜，经双方协商一致，达成如下协议： </p>
		                    <p>&nbsp;</p>
		                    <p>第一条&nbsp;主要内容 </p>
		                    <p>1.1&nbsp;债权转让标的 </p>
		                    <p>甲方同意将其通过你我贷平台形成的个人贷款债权（详见下表，下称“债权标的”）转让给乙方，乙方同意受让该债权。 </p>
		                    <p>债权标的信息 </p>
		                    
		                    <p>&nbsp;</p>
		                    <p>1.2&nbsp;转让流程 </p>
		                    <p>1.2.1&nbsp;甲、乙双方同意并确认，双方通过自行或授权有关方根据你我贷平台相关的规则和说明，在你我贷平台进行债权转让和受让购买操作等方式来确认签署本协议。 </p>
		                    <p>1.2.2&nbsp;双方同意，本协议通过你我贷平台审核通过时，本协议成立，在乙方转让价款支付完成时生效。协议成立的同时，甲方不可撤销地授权你我贷平台贷委托第三方支付平台将债权标的转让价款在扣除甲方应支付你我贷平台的相关款项后（包括但不限于转让管理费）划转给甲方，上述债权标的转让价款划转完成即视为本协议生效且债权标的转让成功。同时乙方不可撤销地授权你我贷继续保管债权标的之借款人出具的《电子借条》及《承诺书》。1.2.3&nbsp;本协议生效且债权标的转让成功后，双方特此委托你我贷平台以其名义将债权标的转让事项及有关信息通过站内短信、电话、书面等形式通知债权标的对应的借款人。 </p>
		                    <p>1.2.3自债权标的转让成功之日起，乙方成为债权标的债权人，承继电子借条及承诺书涉及出借人的权利及义务。 </p>
		                    <p>&nbsp;</p>
		                    <p>第二条&nbsp;保证与承诺 </p>
		                    <p>2.1&nbsp;甲方保证其转让的债权标的系合法、有效的债权，不存在转让的限制。甲方同意并承诺，按有关协议及你我贷平台的相关规则向你我贷平台支付债权转让管理费。 </p>
		                    <p>2.2&nbsp;乙方保证其所用于受让债权标的资金来源合法，乙方是该资金的合法所有人。如果第三方对资金归属、合法性问题发生争议，乙方应自行负责解决并承担相关责任。受让后，乙方将履行甲方与债权标的借款人签署的《电子借条》及《承诺书》，遵守你我贷平台相关交易规则。 </p>
		                    <p>&nbsp;</p>
		                    <p>第三条&nbsp;违约责任 </p>
		                    <p>3.1&nbsp;双方同意，如果一方违反其在本协议中所作的保证、承诺或任何其他义务，致使其他方遭受或发生损害、损失等责任，违约方须向守约方赔偿守约方因此遭受的一切经济损失。 </p>
		                    <p>3.2&nbsp;双方均有过错的，应根据双方实际过错程度，分别承担各自的违约责任。 </p>
		                    <p>&nbsp;</p>
		                    <p>第四条&nbsp;适用法律和争议解决 </p>
		                    <p>4.1&nbsp;本协议的订立、效力、解释、履行、修改和终止以及争议的解决适用中国的法律。 </p>
		                    <p>4.2&nbsp;本协议在履行过程中，如发生任何争执或纠纷，双方应友好协商解决；若协商不成，任何一方均有权向有管辖权的人民法院提起诉讼。 </p>
		                    <p>&nbsp;</p>
		                    <p>第五条&nbsp;其他 </p>
		                    <p>5.1&nbsp;本协议采用电子文本形式制成，甲方和乙方在你我贷注册且通过你我贷平台债权转让即视为同意本借款协议全部内容。甲、乙方同意，本协议通过你我贷平台审核通过时，本协议成立,在债权标的转让价款支付完成时生效。各方均认可该形式的协议效力及本协议内容。各方委托你我贷平台保管所有与本协议有关的书面文件和电子信息。各方确认你我贷平台提供的与本协议有关的书面文件和电子信息在无与本协议明显冲突及错误情况下，应作为本协议有关事项的终局证明。 </p>
		                    <p>5.2&nbsp;甲、乙方均同意并确认，甲、乙方（借款方）通过你我贷平台账户和其银行账户的行为均通过第三方支付平台进行，所产生的法律效果及法律责任归属于甲、乙方。同时，甲、乙方委托你我贷平台根据本协议采取的全部行动和措施的法律后果均归属于甲、乙方，你我贷平台不因此承担责任。 </p>
		                    <p>5.3&nbsp;如果本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条款的效力。 </p>
		                    <p>5.4&nbsp;本协议项下的附件和补充协议构成本协议不可分割的一部分，本协议项下无约定的事项以你我贷平台公布的相关规则为准。 </p>
		                    <p>甲、乙方声明：对于本协议条款，你我贷平台已应甲、乙方要求进行充分解释及说明，甲、乙方对本协议内容及相应风险已完全知悉并理解。</p>
		                </div>
		                <div class="bottoms" id="bottoms" style="text-align:center;">
					     	<a href="javascript::void(0)" onclick="next2(this)" class="btn btnSize_1 btn_blue mr_5">接受</a>
					        <a href="" class="btn btnSize_1 btn_white plus_c" id="refuseAgreement">拒绝</a>
					     </div>
				    </div>
					<!--step1 End-->
				     <!--Step2 Start-->
				     <form action="" method="post" id="checkPwdFromconfirmAttorn">
				        <div class="stepCon hidden" id="step2">
				            <table border="0" cellspacing="0" cellpadding="0" class="table_zhuanran">
					            <tbody>
					            	<tr>
						                <th>项目号：</th>
						                <td id="lid" style="padding-right:220px;"></td>
						                <th>转让时效：</th>
						                <td>48小时</td>
						            </tr>
					                <tr>
					                    <th>债权价值：</th>
					                    <td id="zqjz"></td>
					                    <th>转让手续费：</th>
					                    <td>
					                        <span id="zrsxf"></span>
					                        <span class="tipBox inline_block" id="zhekou"> 
												<div class="tipBox-mark nwd_icon nwd_icon_newtootips mar_l5"> 
													<div style="left: 25px; top: -20.5px; width:115px;" class="tips toortipBox-con r_tip-con j_eye-hide"> 
													<i class="nwd_icon nwd_icon_toortiparrow"></i> 
													<p class="fc_9" id="tpd"></p> 
													</div> 
												</div> 
											</span>
					                    </td>
					                </tr>
					                <tr>
					                    <th>转让折扣：</th>
					                    <td>
					                    	<ul class="percent">
					                        	<li onmousedown="resetPrice(0.000);" class="cur">0%<img src="/Public/Scripts/tr.jpg" class="pos" width="10" height="10" style="display: block;"></li>
					                            <li onmousedown="resetPrice(0.005);">0.5%<img src="/Public/Scripts/tr.jpg" class="pos" width="10" height="10" style="display: none;"></li>
					                            <li onmousedown="resetPrice(0.010);">1%<img src="/Public/Scripts/tr.jpg" class="pos" width="10" height="10" style="display: none;"></li>
					                            <li onmousedown="resetPrice(0.015);">1.5%<img src="/Public/Scripts/tr.jpg" class="pos" width="10" height="10" style="display: none;"></li>
					                            <li onmousedown="resetPrice(0.020);">2%<img src="/Public/Scripts/tr(1).jpg" class="pos" width="10" height="10" style="display: none;"></li>
					                        </ul>
					                    </td>
					                    <th>剩余期次：</th>
					                    <td id="syqc">0个月</td>
					                </tr>
					                <tr>
					                    <th>转让价格：</th>
					                    <td>
					                    	<em class="fc_orange bold fs_16" id="zrjg"></em><i class="fc_orange bold ml_5">元</i>
					                    </td>
					                    <th>还款方式：</th>
					                    <td id="hkfs">按月还款</td>
					                </tr>
					                <tr>
					                    <td colspan="4" class="te">注：债权转让手续费定义为成交金额的1%。持有满365天免手续费</td>
					                  </tr>
					                  <tr>
					                    <th>实收金额：</th>
					                    <td colspan="3">
						                    <span id="ssje"></span>
						                    <span class="tkOut tkOut_r">
						                        <i class="ico_all size15 img_icon s_wen"></i>
						                        <div class="tkIn tkIn4 hidden">
						                            <span class="jian"></span>
						                            <span class="tknr tknr4">实收金额 = 债权价值 ×（1-转让折扣）- 转让手续费</span>
						                        </div>
						                    </span>
					                    </td>
					                </tr>
					                <tr>
					                    <th>交易密码：</th>
					                    <td colspan="3" style="padding:0"><input type="password" value="" name="pwd" id="pwd" class="pass input " autocomplete="off">&nbsp;&nbsp;<a href="javascript:;" target="_blank" class="blue">忘记密码</a>
					                    	<span class="red" style="display:none;" id="errorMsg">请输入正确的交易密码！</span>
					                    </td>
					                </tr>
					            </tbody>
					        </table>
					        <div class="bottoms" style="text-align:center; padding-top:10px;">
						     	<a href="javascript:;" class="btn btnSize_1 btn_blue" onclick="okAgreement()">确认</a>
						     </div>
				    	</div>
				     </form>
					<!--Step2 End-->
					<!--step3 Start-->
				    <div class="stepCon hidden" id="step3">
				        <table border="0" cellspacing="0" cellpadding="0" class="table_zhuanran">
				            <tbody>
					            <tr>
					                <th>项目号：</th>
					                <td id="lid2" style="padding-right:220px;"></td>
					                <th>转让发布时间：</th>
					                <td>48小时</td>
					            </tr>
					            <tr>
					                <th>债权价值：</th>
					                <td id="zqjz2"></td>
					                <th>剩余期次：</th>
					                <td id="syqc2">0个月</td>
					            </tr>
					            <tr>
					                <th>转让折扣：</th>
					                <td id="zrzk2">0%</td>
					                <th>还款方式：</th>
					                <td id="hkfs2">按月还款</td>
					             </tr>
					             <tr>
					                <th>转让价格：</th>
					                <td><em class="fc_orange bold fs_16" id="zrjg2">0.00</em><i class="fc_orange bold ml_5">元</i></td>
					                <th></th>
					                <td></td>
					             </tr>
						        <tr>
					                <th>转让手续费：</th>
					                <td colspan="3" id="zrsxf2">0元</td>
					            </tr>
					            <tr>
					                <th>实收金额：</th>
					                <td colspan="3"><span id="ssje2"></span><span class="in-block why"></span><em class="link">&nbsp;<i class="in-block"></i>(实收金额 = 债权价值 ×（1-转让折扣）- 转让手续费</em>)</td>
					            </tr>
					            <tr>
					                <td colspan="4" class="te">
					                注：<br>
					                ● 转让成功后，你我贷将扣除手续费；<br>
					                ● 如果在<span id="xxrq2"></span>仍未成交，转让申请将自动下线。
					                </td>
					            </tr>
				        	</tbody>
						</table>
				        <div class="bottoms" style="text-align:center; padding-top:10px;">
				            <a href="javascript:;" class="btn btnSize_1 btn_blue" onclick="closeDialog()">关闭</a>
				         </div>
				    </div>
				</div>
	     <!--step3 End-->
			</div>
		</div>
	</div>
	<!--提示start-->
	<div class="panelbox wid_w480" id="openMsg">
		<div class="panelbg"></div>
		<div class="panelwrap">
			<div class="paneltitle">
			    <span class="text">友情提示</span>
			    <span class="panelclose nwd_icon nwd_icon_close pop-close"></span>
			</div>
			<div class="panelcon pad_t30 pad_b30 pad_l30 pad_r30">
			    <div class="container" style="text-align: center;">
			       <div class="middle" id="changyong"> 
						<div class="content" id="msgContent">转让不可撤回</div> 
						<div class="btnbox"><button class="btn btnSize_1 btn_blue plus_c" id="msgClose">确认</button></div> 
					</div> 
			    </div>
			</div>
		</div>
	</div>
<!-- 底部-->
<%@ include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%> 
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery.js"></script>
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/resource/Scripts/jquery-ui.min.js"></script>
<script src="${basePath}/resources/resource/Scripts/mobiscroll_date.js" charset="gb2312"></script> 
<script src="${basePath}/resources/resource/Scripts/mobiscroll.js"></script> 
<script type="text/javascript">
 $(document).ready(function(){
    //手势右滑 回到上一个画面
    $('#myCarousel').bind('swiperight swiperightup swiperightdown',function(){
        $("#myCarousel").carousel('prev');
    })
    //手势左滑 进入下一个画面
    $('#myCarousel').bind('swipeleft swipeleftup swipeleftdown',function(){
        $("#myCarousel").carousel('next');
    });
});
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

	$("#startdate,#enddate").mobiscroll($.extend(opt['date'], opt['default']));
	$(".side_nav").find("dl").siblings().removeClass("navcurron").eq(2).addClass("navcurron");

});
//判断结束时间是否小于开始时间
$('.dwb-s .dwb').live("click",function (){
	var onetome=$("#startdate").val();
	var endtime=$("#enddate").val();
	if(onetome!='' && endtime!=''){
		if(endtime<onetome){
			$("#endtime").val('');
			alert('结束时间不能小于开始时间');
		}
	}
})
// 切换
$(document).ready(function(){
	$(".box2,.box3,.box4,.box5").hide();
    $(".tab_title.clearfix  li").click(function(){
        $(".tab_title.clearfix  li").removeClass("active")
        $(".tab_title.clearfix  li").removeClass("ui-select-listBox-list--now")
        var num = $(this).val();
        $("#lState").val(num);
        $(this).addClass("active")
        $(this).addClass("ui-select-listBox-list--now")
        $(".box").hide();
        $(".box"+num).show();
        $("div .ui-select-listBox-l-blue").css("left",(num-1)*125+20+"px");
    	$("#investment").submit();
    });
	$(".fl.a_btn .fc_6").click(function(){
		$(".fl.a_btn .fc_6").removeClass("active");
		$(this).addClass("active");
		var num=$(this).attr("data-value");
		$("#dateType").val(num);
		$("#lState").val("${lState}");
		$("#startdate").val('');
		$("#enddate").val('');
		$("#investment").submit();
		//console.log(num)
	})
})
//数据回显
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
	})
	 var type = "${dateType}"; 
	 $("#dateType").val(type);
	$(".fl.a_btn").find("a").each(function(index, value) {
		var num=$(this).attr("data-value");
		$(this).removeClass("active");
		if (num == type) {
			$(this).addClass("active");
		}

	});
}) 
$("#startdate").bind("change",function(){
	$("#investment").submit();
});
$("#enddate").bind("change",function(){
	$("#investment").submit();
});
// 弹窗居中
$(document).ready(function(){
	showDiv();
		function showDiv(obj){
		 $(obj).show();
		 center(obj);
		 $(window).scroll(function(){
		  center(obj);
		 });
		 $(window).resize(function(){
		  center(obj);
		 }); 
		}
	function center(obj){
		 var windowWidth = document.documentElement.clientWidth;   
		 var windowHeight = document.documentElement.clientHeight;   
		 var popupHeight = $(obj).height();   
		 var popupWidth = $(obj).width();    
		 $(obj).css({   
		  "position": "absolute",   
		  "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),   
		  "left": (windowWidth-popupWidth)/2   
		 });  
		}
		//$(".bg").show();
	//提示
	$(".zhuangtai").click(function(){
		$("#openMsg,.bg").show();
		showDiv("#openMsg");
	})
	//转让信息
	$(".xinxi").click(function(){
		$("#tranDiv,.bg").show();
		showDiv("#tranDiv");
	})
	//关闭
	$(".pop-close").click(function(){
		$("#openMsg,.bg,#tranDiv").hide();
	})
	
})
//转让信息切换
	function next2(id){
		$("#step1").hide();
		$("#step2").show();
		$("#changeOldStep2").addClass('active');
	}
	function okAgreement(){
		$("#step2").hide();
		$("#step3").show();
		$("#changeOldStep3").addClass('active');
	}
	function closeDialog(){
		$("#step3,.bg").hide();
	}
</script>
</body>
</html>