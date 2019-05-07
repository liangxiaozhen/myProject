<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//System.out.println(path);
	//System.out.println(basePath);
	
%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>assets/css/dpl-min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>assets/css/bui-min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>assets/css/main-min.css" rel="stylesheet"
	type="text/css" />
</head>
<body style="font-family: '微软雅黑';">

	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>

		<div class="dl-log">
			欢迎您，<span class="dl-log-user">${adminuser.username }</span><a
				href="<%=basePath%>logout.action" title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">后台管理</div></li>
				<!-- <li class="nav-item dl-selected"><div class="nav-item-inner nav-order">业务管理</div></li> -->

			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>assets/js/bui-min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>assets/js/common/main-min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>assets/js/config-min.js"></script>
	<script>
			
			BUI.use('common/main', function() {
				
				var config = [ {
					id : '1',
					menu : [ {
						text : '用户管理',
						collapsed : true,//默认二级导航收缩
						items : [ {
							id : '12',
							text : '用户基本信息',
							href : '<%=basePath%>admin/userBaseAccountInfo/userBaseInfoList.action'
						},{
							id : '6',
							text : '用户账户信息',
							href : '<%=basePath%>admin/userAccount/accountInfoList.action'
						},
						/* {
							id : '20',
							text : '冻结解冻列表',
							href : '${pageContext.request.contextPath}/admin/UsrUnFreeze/jdqueryAllList.action'
						},{
							id : '21',
							text : '冻结解冻记录',
							href : '${pageContext.request.contextPath}/admin/UsrUnFreeze/UsrUnFreezeRecord.action'
						}, */
						{
							id : '5',
							text : '用户收支记录',
							href : '<%=basePath%>admin/userAccInExRecord/userAccInExRecord.action'
						}, {
							id : '11',
							text : '用户银行卡信息',
							href : '${pageContext.request.contextPath}/admin/userbankcard/queryBankInfoList.action'
						},{
							id : '10',
							text : '用户红包信息',
							href : '<%=basePath%>admin/bonus/queryBonusList.action'
						}, {
							id : '13',
							text : '用户托管开户信息',
							href : '<%=basePath%>/admin/baseAndFsa/list.action'
						},{
							id : '9',
							text : '用户积分信息',
							href : '<%=basePath%>admin/points/queryPointsList.action'
						},{
							id : '8',
							text : '用户使用券信息',
							href : '<%=basePath%>admin/coupon/queryCouponList.action'
						},{
							id : '15',
							text : '用户站外奖品信息',
							href : '${pageContext.request.contextPath}/admin/outaward/queryOutawardList.action'
						},{
							id : '16',
							text : '用户奖品邮寄地址',
							href : '${pageContext.request.contextPath}/admin/useraddress/queryUserAddressList.action'
						},{
							id : '99',
							text : '标的站岗利息记录',
							href : '<%=basePath%>admin/gfundsIntRecord/queryGfundsIntRecord.action'
						} ,{
							id : '98',
							text : '标的流标补偿记录',
							href : '<%=basePath%>admin/failTCRecord/queryFailTCRecordLsit.action'
						}]
					},
					/* {
						text : '担保公司',
						collapsed : true,
						items : [ {
							id : '12',
							text : '担保公司资料',
							href : '${pageContext.request.contextPath }/guarantee/queryGuaranteeList.action'
						} ]
					},  */
					{
						text : '活动管理',
						collapsed : true,
						items : [ {
							id : '81',
							text : '注册活动查看',
							href : '<%=basePath%>admin/activity/queryActivityList.action'
						}, {
							id : '23',
							text : '提低卷活动设置',
							href : '${pageContext.request.contextPath }/admin/withdrawsCashCoupon/list.action'
						}, {
							id : '24',
							text : '提低卷活动奖励设置',
							href : '${pageContext.request.contextPath }/admin/wCCAwardRule/list.action'
						}, {
							id : '25',
							text : '标的活动规则设置',
							href : '${pageContext.request.contextPath }/admin/activityRule/list.action'
						}, {
							id : '26',
							text : '标的活动奖励规则设置',
							href : '${pageContext.request.contextPath }/admin/activityAwardRule/list.action'
						},{
							id : '27',
							text : '手动颁奖活动查看',
							href : '${pageContext.request.contextPath }/admin/manual/manualActivityQuery.action'
						},{
							id : '28',
							text : '活动列表查看',
							href : '${pageContext.request.contextPath }/admin/manual/activityListQuery.action'
						},{
							id : '29',
							text : '获奖名单审核',
							href : '${pageContext.request.contextPath }/admin/activityAwardList/selectActivityAwardListByCondition.action'
						},{
							id : '30',
							text : '活动结果处理',
							href : '${pageContext.request.contextPath }/admin/activityAwardList/selectActivityAwardListSend.action'
						}]
					},{
						text : '充值管理',
						collapsed : true,
						items : [{
							id : '12',
							text : '充值费率', 
							href : '${pageContext.request.contextPath }/admin/rechargeRate/query.action'
						},/*  {
							id : '15',
							text : '充值费率记录',
							href : '${pageContext.request.contextPath }/admin/rechargeRate/queryrecord.action'
						}, */{
							id : '3',
							text : '充值记录',
							href : '${pageContext.request.contextPath }/admin/userRecharge/query.action'
						} /* ,{
							id : '4',
							text : '充值设置',
							href : '${pageContext.request.contextPath }/admin/rechargeRstr/queryAll.action'
						},{
							id : '5',
							text : '充值设置记录',
							href : '${pageContext.request.contextPath }/admin/rechargeRstr/queryAllRecrod.action'
					
						} */ ]
					},{
						text : '会员管理',
						collapsed : true,
						items : [ {
							id : '12',
							text : '会员等级设置',
							href : '${pageContext.request.contextPath }/admin/userGrade/queryAll.action'
						},{
							id : '37',
							text : '体验会员等级设置',
							href : '${pageContext.request.contextPath}/admin/userGradeExp/queryAll.action'
						},{
							id : '29',
							text : '用户等级升级记录',
							href : '${pageContext.request.contextPath}/admin/userUpgradeRecord/queryAll.action'
						},/* ,{
							id:'3',
							text:'新增会员',
							href:'${pageContext.request.contextPath}/userGrade/insert.action'
						} */]
					},{
						text : '提现管理',
						collapsed : true,
						items : [{
							id : '14',
							text : '提现记录',
							href : '${pageContext.request.contextPath}/admin/userWithdrawsCash/queryAll.action'
						},
						/*{
							id : '15',
							text : '提现待审核',
							href : '${pageContext.request.contextPath}/admin/userWithdrawsCash/queryAllForAudit.action'
						}, {
							id : '16',
							text : '提现待取消',
							href : '${pageContext.request.contextPath}/admin/userWithdrawsCash/queryAllForCancel.action'
						},*/{
							id : '17',
							text : '提现费率',
							href : '${pageContext.request.contextPath}/admin/withdrawsCashRate/queryAll.action;'
						}/*,{
							id : '18',
							text : '提现设置记录',
							href : '${pageContext.request.contextPath}/admin/withdrawsCashRstr/queryAll.action;'
						} */,{
							id : '19',
							text : '提现设置',
							href : '${pageContext.request.contextPath}/admin/withdrawsCashRstr/queryAll.action;'
						}  ]
					},
					/* {
						text : '对账管理',
						collapsed : true,
						items : [{
							id : '41',
							text : '充值人工对账',
							href : '${pageContext.request.contextPath }/admin/userRecharge/fileUpload.action'
							}, {
							id : '42',
							text : '提现人工对账',
							href : '${pageContext.request.contextPath}/admin/userWithdrawsCash/artificialReconciliatio.action'
						},{
							id : '43',
							text : '充值对账',
							href : '${pageContext.request.contextPath}/admin/userRecharge/queryBlending.action'
						}, {
							id : '44',
							text : '提现对账',
							href : '${pageContext.request.contextPath}/admin/userWithdrawsCash/queryAllForUpdate.action'
						},{
							id : '45',
							text : '现金红包转账对账',
							href : '${pageContext.request.contextPath}/admin/checkRecord/queryAllRedByCondition.action'
						}]
					},  */
					{
						text : '名单管理',
						collapsed : true,
						items : [ {
							id : '51',
							text : '名单目录查看',
							href : '${pageContext.request.contextPath}/admin/removeName/queryAllNameType.action'
						},{
							id : '52',
							text : '名单目录设置',
							href : '${pageContext.request.contextPath}/admin/removeName/queryAllNameTypeForUpdate.action'
						}, {
							id : '53',
							text : '名单列表',
							href : '${pageContext.request.contextPath}/admin/removeName/queryAll.action'
						},{
							id : '54',
							text : '名单设置',
							href : '${pageContext.request.contextPath}/admin/removeName/queryAllForUpdate.action'
						},
						{
							id : '55',
							text : '定向名单查看',
							href : '${pageContext.request.contextPath }/admin/nameList/directionNameQuery.action'
						}]
					},{
						text : '时间管理',
						collapsed : true,
						items : [ {
							id : '32',
							text : '关闭时间列表',
							href : '${pageContext.request.contextPath}/admin/closeTime/queryAll.action'
						},{
							id : '33',
							text : '关闭时间设置',
							href : '${pageContext.request.contextPath}/admin/closeTime/queryAllForUpdate.action'
						},{
							id : '34',
							text : '特定时间列表',
							href : '${pageContext.request.contextPath}/admin/specialTime/queryAll.action'
						},{
							id : '34',
							text : '特定时间设置',
							href : '${pageContext.request.contextPath}/admin/specialTime/queryAllForUpdate.action'
						}]
					},{
						text : '投标管理',
						collapsed : true,
						items : [ {
							id : '93',
							text : '投标记录',
							href : '<%=basePath%>admin/tender/queryBidRecordList.action'
						}, {
							id : '97',
							text : '投标待审核',
							href : '<%=basePath%>admin/tender/queryBidRecordForAudit.action'
						}, {
							id : '94',
							text : '自动投标计划',
							href : '<%=basePath%>admin/autoTenderPan/queryAutoTender.action'
						},/* {
							id : '95',
							text : '投标放款',
							href : '<%=basePath%>admin/tenderLoans/queryAllForLoan.action'
						},*/ {
							id : '96',
							text : '投标放款记录',
							href : '<%=basePath%>admin/userMakeALoan/queryAll.action'
						}]
					},{
						text : '奖品管理',
						collapsed : true,
						items : [{
							id : '12',
							text : '奖品交易总开关',
							href : '${pageContext.request.contextPath }/admin/diffAwardSwitch/selectDiffAwardByCondition.action'
						},{
							id : '13',
							text : '奖品管理',
							href : '${pageContext.request.contextPath }/admin/award/selectAwardByCondition.action'
						},{
							id : '132',
							text : '奖品包管理',
							href : '${pageContext.request.contextPath }/admin/awardPackage/selectAwardPackageByCondition.action'
						}]
				
					},{
						text : '还款计划管理',
						collapsed : true,
						items : [ {
							id : '13',
							text : '还款计划查看',
							href : '${pageContext.request.contextPath }/admin/dividedPayments/list.action'
						},{
							id : '18',
							text : '利息管理费记录',
							href : '${pageContext.request.contextPath }/admin/interestExpenseRecord/list.action'
						},{
							id : '19',
							text : '还款审核',
							href : '${pageContext.request.contextPath }/admin/repayMent/list.action'
						},{
							id : '19',
							text : '还款处理',
							href : '${pageContext.request.contextPath }/admin/repayMent/listbyDivRepay.action'
						},{
							id : '19',
							text : '增益清算记录',
							href : '${pageContext.request.contextPath }/admin/plusPayoutRecord/recordList.action'
						},{
							id : '19',
							text : '增益清算审核',
							href : '${pageContext.request.contextPath }/admin/plusPayoutRecord/list.action'
						},{
							id : '19',
							text : '提前还款奖励记录',
							href : '${pageContext.request.contextPath }/admin/aheadRepayOneRecord/list.action'
						},{
							id : '19',
							text : '还款冻结解冻记录',
							href : '${pageContext.request.contextPath }/admin/repaymentFrz/list.action'
						}]
					},{
						text : '标管理',
						collapsed : true,
						items : [{
							id : '15-21',
							text : '建标',
							href : '${pageContext.request.contextPath }/admin/tenderItem/selectloanAppbyappStatus.action'
						},{
							id : '15-27',
							text : '审核标',
							href : '${pageContext.request.contextPath }/admin/tenderItem/selectTenderItemByaudit.action'
						},{
							id : '15-0',
							text : '标管理',
							href : '${pageContext.request.contextPath }/admin/tenderItem/selectTenderItemByCondition.action'
						},{
							id : '15-1',
							text : '标的站岗利息管理',
							href : '${pageContext.request.contextPath }/admin/gfundsInt/selectGfundsIntByCondition.action'
						},{
							id : '15-2',
							text : '标的流标补偿管理',
							href : '${pageContext.request.contextPath }/admin/failTCompensate/selectFailTCompensateByCondition.action'
						},{
							id : '15-28',
							text : '标的流标奖品补偿',
							href : '${pageContext.request.contextPath }/admin/failTAwardCompensate/getFailTAwardCompensateByCondition.action'
						}, {
							id : '15-3',
							text : '标的逾期代偿管理',
							href : '${pageContext.request.contextPath }/admin/overdueCompensate/selectOverdueCompensateByCondition.action'
						}, {
							id : '15-4',
							text : '标的提前还款本金利息',
							href : '${pageContext.request.contextPath }/admin/aheadRepay/selectAheadRepayByCondition.action'
						},{
							id : '15-5',
							text : '标的提前还款方式设置',
							href : '${pageContext.request.contextPath }/admin/aheadRepay/selectAheadRepayPlatStyle.action'
						},{
							id : '15-6',
							text : '标的提前还款增益补偿个人',
							href : '${pageContext.request.contextPath }/admin/aheadRepay/selectAheadRepayZYStyle.action'
						},{
							id : '15-7',
							text : '标的提前还款欠收利息补偿平台',
							href : '${pageContext.request.contextPath }/admin/aheadRepay/selectAheadRepayPlatStyleBy.action'
						},{
							id : '15-8',
							text : '标的债权转让管理',
							href : '${pageContext.request.contextPath }/admin/debtAttorn/selectDebtAttornByCondition.action'
						},{
							id : '15-9',
							text : '标的增益设置管理',
							href : '${pageContext.request.contextPath }/admin/plus/selectPlusByCondition.action'
						},{
							id : '15-10',
							text : '标的居间费设置管理',
							href : '${pageContext.request.contextPath }/admin/mediacyFee/selectMediacyFeeByCondition.action'
						},{
							id : '15-11',
							text : '标的担保费率设置管理',
							href : '${pageContext.request.contextPath }/admin/guaranteeFee/selectGuaranteeFeeByCondition.action'
						},{
							id : '15-12',
							text : '标的利息管理费设置',
							href : '${pageContext.request.contextPath }/admin/interestExpense/selectInterestExpenseByCondition.action'
						},{
							id : '15-13',
							text : '标的风险保证金管理管理',
							href : '${pageContext.request.contextPath }/admin/riskGuarantyMoney/selectRiskGuarantyMoneyByCondition.action'
						},{
							id : '15-12',
							text : '增加标的详情说明',
							href : '${pageContext.request.contextPath}/admin/tenderItem/ItemDetailDesc.action'
						},{
							id : '15-13',
							text : '标的前端信息',
							href : '${pageContext.request.contextPath}/admin/tenderFrontEnd/list.action'
						},]
					},{
						text : '系统管理',
						collapsed : true,
						items : [{
							id : '3',
							text : '角色管理',
							href : '${pageContext.request.contextPath }/admin/systemrole/list.action'
						}, {
							id : '4',
							text : '系统用户管理',
							href : '${pageContext.request.contextPath }/admin/adminuser/list.action'
						}, {
							id : '6',
				        	text : '菜单管理',
							href : '${pageContext.request.contextPath }/admin/memu/list.action'
						},{
							id:'8',
							text :'全局管理',
							href :'${pageContext.request.contextPath}/admin/globalSetting/sove.action'
						}]
 
 					},
					{
						text : '借款审核管理',
						collapsed : true,
						items : [{
							id : '8-25',
							text : '标类型设置',
							href : '${pageContext.request.contextPath }/admin/loantype/selectAll.action'
						},{
							id : '8-27',
							text : '自填类项目设置',
							href : '${pageContext.request.contextPath }/admin/loanInfo/selectAllNeed.action'
						},{
							id : '8-29',
							text : '选择类资料设置',
							href : '${pageContext.request.contextPath }/admin/loanInfo/selectAllPreset.action'
						},{
							id : '8-32',
							text : '选择类子项目设置',
							href : '${pageContext.request.contextPath }/admin/loanInfo/selectAllContentSet.action'
						},{
							id : '8-30',
							text : '项目引用设置',
							href : '${pageContext.request.contextPath }/admin/loanItem/selectAllQuote.action'
						},{
							id : '8-39',
							text : '公共/补充资料审核',
							href : '${pageContext.request.contextPath }/admin/loanmaterial/selectAllUserLoanmat.action'
						},
						{
							id : '8-23',
							text : '借款申请审核',
							href : '${pageContext.request.contextPath }/admin/loan/selectloanappAll.action'
						},
						{
							id : '8-22',
							text : '借款代提交',
							href : '${pageContext.request.contextPath }/admin/loan/replacesubmit.action'
						}
						]
					},{
						text : '信息管理',
						collapsed : true,
						items : [{
							id : '8-0',
							text : '邮件信息管理',
							href : '${pageContext.request.contextPath }/admin/emailRecord/list.action'
						}, 
						{
							id : '8-10',
							text : '邮件通道设置',
							href : '${pageContext.request.contextPath }/admin/emaill/selectAll.action'
						},{
							id : '8-1',
							text : '短信信息管理',
							href : '${pageContext.request.contextPath }/admin/sMSSendRecord/list.action' 
						}, {
							id : '8-4',
							text : '短信通道设置',
							href : '${pageContext.request.contextPath }/admin/sMSChannel/queryAll.action' 
						}, {
							id : '8-2',
							text : '短信模板设置',
							href : '${pageContext.request.contextPath }/admin/messageTemplate/queryAll.action' 
						}, {
							id : '8-5',
							text : '系统通知业务设置',
							href : '${pageContext.request.contextPath }/admin/sysNoticeBiz/queryAll.action' 
						},{
								id : '8-6',
								text : '用户消息通知设置',
								href : '${pageContext.request.contextPath }/admin/msg/toMsgSet.action'
							}]
					},{
						text : '规则模块',
						collapsed : true,
						items : [{
							id : '90',
							text : '注册设置',
							href : '${pageContext.request.contextPath }/admin/userNameRuleModule/queryAll.action'
						}]
					},{
						text : '图文模块',
						collapsed : true,
						items : [{
							id : '60',
							text : '图文项目',
							href : '${pageContext.request.contextPath }/admin/imageTextItem/list.action'
						},{
							id : '61',
							text : '图文内容',
							href : '${pageContext.request.contextPath }/admin/imageTextSetting/list.action'
						}]
					},{
						text : '推广模块',
						collapsed : true,
						items : [{
							id : '111',
							text : '推广码注册用户记录',
							href : '${pageContext.request.contextPath }/admin/promoRegInfo/queryAll.action'
						},{
							id : '110',
							text : '用户推广设置',
							href : '${pageContext.request.contextPath }/admin/userPromo/queryAll.action'
						},{
							id : '113',
							text : '推广限制设置',
							href : '${pageContext.request.contextPath }/admin/promoRestrict/queryAll.action'
						},{
							id : '112',
							text : '用户推广级别设置',
							href : '${pageContext.request.contextPath }/admin/promoAgentGradeProfit/queryGradeAll.action'
						},{
							id : '115',
							text : '第三方推广设置',
							href : '${pageContext.request.contextPath }/admin/agentGradePromoAuth/queryAll.action'
						}]
					},{
						text : '商户账户模块',
						collapsed : true,
						items : [{
							id : '150',
							text : '商户账户查询',
							href : '${pageContext.request.contextPath }/admin/merchantaccountquery/queryAll.action'
						}]
					},{
						text : '用户债转审核',
						collapsed : true,
						items : [{
							id : '150',
							text : '用户债转审核',
							href : '${pageContext.request.contextPath}/admin/userdebtattorn/queryList.action'
						} ]
					},{
						text : '风险控制模块',
						collapsed : true,
						items : [{
							id : '12',
							text : '用户风险控制管理',
							href : '<%=basePath%>/admin/userRisk/listUI.action'
						},{
							id : '11000006',
							text : '用户风险控制查看',
							href : '<%=basePath%>/admin/userRisk/FenghongListUI.action'
						},{
							id : '11666666',
							text : '屏蔽安全验证管理',
							href : '<%=basePath%>/admin/cancelValidate/listUI.action'
						}, {
							id : '1166',
							text : '黑名单查看',
							href : '<%=basePath%>/admin/userRisk/BlackListUI.action'
						},{
							id : '11666',
							text : '风险名单查看',
							href : '<%=basePath%>/admin/userRisk/SuspiciousListUI.action'
						} ,{
							id : '1166666',
							text : '应急改密名单查看',
							href : '<%=basePath%>/admin/userRisk/YingjigaimiListUI.action'
						},{
							id : '116',
							text : '白名单查看',
							href : '<%=basePath%>/admin/userRisk/WhiteListUI.action'
						}]
					},{
						text : '批量文件模块',
						collapsed : true,
						items : [{
							id : '1500',
							text : '批量文件记录',
							href : '${pageContext.request.contextPath}/admin/batchFile/queryList.action'
						} ]
					}]
				} ];
				new PageUtil.MainPage({
					modulesConfig : config
				});
			});
		</script>
	<br />
	<br />
	<br />

</body>
</html>