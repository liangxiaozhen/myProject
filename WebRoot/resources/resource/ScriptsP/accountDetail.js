$(document).ready(function(){
	//资产媒体列表
    new Playimgone("mediaListCon",{
      time:0,
      navo:""
    });
    
    //加载帐户过去七天收益信息
	var sevenDayIncomeDate;
    $.ajax({
		type : "POST", 
		url : "/member/accountIncomeAjax.do", 
		dataType:"json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(result) { 
			sevenDayIncomeDate = result.sevenDayIncomeList;//七日收益数据
			 function Charts (){
					var indexData = {
				            success: true,
				            maxIncome: sevenDayIncomeDate.maxIncome,
				            status: 3,
				            data: sevenDayIncomeDate.incomeData,
				            url:'/member/getProfitItems.do?tabIndex=2'
				        };
				        // 首页图表渲染
				        seajs.use('indexChart', function(indexChart){
				            new indexChart({
				                renderWrap: '#J_indexChart',
				                width: 270,
				                height: 60,
				                // 结果数据
				                data: indexData
				            });
				        });
			    }Charts();
		}
    });
   
    //账户详情
    $.ajax({
		type : "POST", 
		url : "/member/investorsAjax.do", 
		dataType:"json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(result) {  
//			var isGrateUp=result.isGrateUp;
//			$("#isGrateUp").val(isGrateUp);
			$("#accAmount").text(formatNum(result.fun.zhye/1000));//账户余额
			$("i[name=accAmount1]").text(formatNum(result.fun.zhye/1000));//账户余额
			dealwithDecimal();//处理金额小数位比整数位大的方法
			$("#freezeAmount").text(formatNum(result.fun.freeze/1000));
			$("#balanceAmount").text(formatNum(result.fun.balance/1000));
			$("#lasset").text(formatNum(result.fun.asset/1000));
			$("i[name=inteBidzc]").text(formatNum(result.fun.inteBidzc/1000));//智能投标服务
			$("i[name=llcjhzc]").text(formatNum(result.fun.lcjhzc/1000));//嘉财有道
			$("i[name=lzqzc]").text(formatNum(result.fun.zqzc/1000));//债权
			$("i[name=xinshouzx]").text(formatNum(result.fun.xinshou/1000));//新手
			$("i[name=lforceAmount]").text(formatNum((result.fun.forceAmount*0.001).toFixed(2)));//负债
			
			$("i[name=inteBidzc]").text(formatNum(result.fun.inteBidzc/1000));//新嘉财有道
			
			
			
			//线下用户资产开关
			var excellenceWealthSwitch = result.excellenceWealthSwitch;
			var excellenceWealth = result.excellenceWealth;//线下用户的资产
			//var mbProperty = result.mbProperty;//线下用户标识
			var balance = (result.fun.asset/1000);//线上资产
			if(excellenceWealthSwitch == true && excellenceWealth > 0){
				$("#excellenceLi").show();//账户中心首页卓越资产（有线下用户有资产才展示信息）
				$("i[name=zyzqBanlance]").text(formatNum(excellenceWealth/1000));//线下用户资产
				$("#excellenceNav").show();//卓越专区--左边导航
				if(balance > 0){
					$("#fundRecordLi").text("线上资金记录");//资金记录--有线下资产的用户需变更该导航文案
					$("#incomeRecordLi").text("线上收益明细");//收益明细--有线下资产的用户需变更该导航文案
					$("#incomeP").text("线上投资累计收益");//账户中心首页
				}
				if((excellenceWealth/1000) >0){
					$("#myBill").hide();//账单隐藏
				}
				
				$("#zyzqAmount").text(formatNum(excellenceWealth/1000));
			}
			

			//积分
			if(Number(result.grade) >0 ){
				$("#mCount").css("display","inline-block");
				$("#mCount").html(result.grade);
				$("#dhHref").html("兑换");
				$('#dhHref').attr("href","/member/exchange.html"); 
			}else{
				$("#mCount").css("display","none");
				$("#dhHref").html("如何获得？");
				$('#dhHref').attr("href","/help/integral.html"); 
			}
			//红包
			if(Number(result.couponCount) >0){
				$("#coupons").css("display","inline-block");
				$("#coupons").html((result.couponCount)/1000+"元");
				$('#couponsHref').attr("href","/member/coupon.do");//查看 
				$("#couponsHref").html("使用");
			}else{
				$("#coupons").css("display","none");
				$('#couponsHref').attr("href","/member/exchange.html");//如何获取红包
				$("#couponsHref").html("如何获得？");
			}
			//加息券 
			if(Number(result.plusCouponsCount) >0){
				$("#plusCoupons").css("display","inline-block");
				$("#plusCoupons").html(result.plusCouponsCount+"张");
				var hrefString = "/financial/financialDetail.do";
				var inteBidSwitch = $("#inteBidSwitch").val();
				if(inteBidSwitch == "on"){
					hrefString = "/inteBid/inteBidPeriodDetail.do";
				}
				$('#plusCouponsHref').attr("href",hrefString);//查看 
				$("#plusCouponsHref").html("使用");
			}else{
				$("#plusCoupons").css("display","none");
				$('#plusCouponsHref').attr("href","/tj2015.html?intcmp=hompage_kv_1");//获取
				$("#plusCouponsHref").html("如何获得？");
			}
			
			//减息券
			if(Number(result.reduceCouponsCount) >0){
				$("#reduceCoupons").css("display","inline-block");
				$("#reduceCoupons").html(result.reduceCouponsCount+"张");
			}
			
			if(parseInt(result.tyj)>0){
				$("#tyjCount").css("display","inline-block");
				$("#tyjCount").html((result.tyj)/1000+"元");
				$("#tyjHref").attr("href","/LCB/moneyTreasureDetail.do");//使用
				$("#tyjHref").html("使用");//使用
			}else{
				$("#tyjHref").css("display","none");
				$("#tyjCount").css("display","none");
			}
			if(result.fun.xinshou <= 0) {
				$("#xinshou").hide();
				$("#laoshou").show();
				$("#laoshou").show();
			} else {
				$("#xinshou").show();
				$("#laoshou").hide();
			}
//			$("#ph").val(result.baseInfo.photo);
//			$("#exists").attr('src',"/member1/memberPhotoView.do?imgPath="+result.baseInfo.photo); 
			
			
			$("#mmsg").parent().attr("href","/member/memberMsg.do");//消息中心
			
			//账户中心最新一条消息
			var msgObj = result.memberMsg;//内容
			if(msgObj!=null){
				$("#msgDiv").css('display','block');
				var msgContent=result.memberMsg.content;
				if(msgContent){
					msgContent = msgContent.replace(/<[^>]+>/g,"");
					msgContent = msgContent.length >50 ? msgContent.substring(0,50)+"..." : msgContent;
					$("#msgInfo").html(msgContent);
				}else{
					$("#msgDiv").css('display','none');//无消息隐藏
				}
			}else{
				$("#msgDiv").css('display','none');//无消息隐藏
			}
			
		} 
	});
    $.ajax({
		type : "POST", 
		url : "/member/accountIncomeAjax.do", 
		dataType:"json",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(result) { 
			$("#totalIncome").html(formatNum(result.totalFinancialInfo.totalIncome/1000));//累计收益(历史累计)
			$("i[name=financialIncome]").text(formatNum(result.totalFinancialInfo.totalFinancialIncome/1000));//嘉财有道的累计收益
			$("i[name=bidIncome]").text(formatNum(result.totalFinancialInfo.totalCreditoIncome/1000));//债券的历史累计收益
			$("i[name=newProductIncome]").text(formatNum(result.totalNewHandProfit/1000));//新手的历史累计收益
			
			sevenDayIncomeDate = result.sevenDayIncomeList;//七日收益数据
			 function Charts (){
					var indexData = {
				            success: true,
				            maxIncome: sevenDayIncomeDate.maxIncome,
				            status: 3,
				            data: sevenDayIncomeDate.incomeData,
				            url:'/member/getProfitItems.do?tabIndex=2'
				        };
				        // 首页图表渲染
				        seajs.use('indexChart', function(indexChart){
				            new indexChart({
				                renderWrap: '#J_indexChart',
				                width: 270,
				                height: 60,
				                // 结果数据
				                data: indexData
				            });
				        });
			    }Charts();
		}
    });
	
	 //初始化数据加载交易记录
	getFinancialRecords("transactionAttr");
	
	/**
	 * 获取资金记录
	 */
	function getFinancialRecords(typeObj){
		if(typeObj == "undefined" || typeObj == null || typeObj == ""){
			typeObj == "transactionAttr";
		}
		var html ="";
		$.ajax({
			type : "POST", 
			url : "/member/financialRecords.do", 
			dataType:"json",
			data: "moreCode="+typeObj,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result) { 
				if(typeObj == "transactionAttr"){
					html="";
					html ='<tr class=""><th><span>时间</span></th><th>类型</th><th>金额</th><th>状态</th><th>操作</th></tr>';
					if(result.list.length >0){
						for (var int = 0; int < result.list.length; int++) {
							if(int < 5){
								var transaction=result.list[int];
								var type=transaction.PROD_TYPE;
								var id=transaction.FP_ID;
								var encryptLid=transaction.encryptLid;
								var typeDesc="";
								var href="#";
								var status = transaction.STATE;
								if(type==0){
									typeDesc ="嘉财有道";
									status ="交易成功";
									href ="/member/findFinancial2Page.do";
								}else if(type==1){
									typeDesc ="新手产品";
									status ="交易成功";
									href ="/member/noviceArea.do";
								}else if(type==2){
									typeDesc ="散标";
									href ="/member/myAcceptList.html";
								}else if(type==3){
									typeDesc ="转让标";
									href ="/member/myAcceptList.html";
								}else if(type==4){
									typeDesc ="有道智投";
									status ="交易成功";
									href ="/member/findinteBidPage.do";
								}
								html +='<tr><td class="Numfont table_timelog"><span >'+transaction.TIME_JOIN+'</span></td><td>'+transaction.TITLE+'</td><td class="Numfont">-'+(transaction.AMOUNT_JOIN*0.001).toFixed(2)+'元</td><td>'+status+'</td><td><a href="'+href+'">详情</a></td></tr>';
							}
						}
						$("#transactionTable").html(html);
					}else{
						var noDataInfo = '<div class="noRecordBox"><div class="ui-noRecord"><div class="h-ulist"><div class="noRecord"></div></div></div></div>';
						var tableHtml='<table class="table fc_9" cellspacing="0" cellpadding="0" id="transactionTable">'+html+'</table>';
						$("#transactionTable").parent().html(tableHtml+noDataInfo);
					}
					
				}else if(typeObj == "rechargeAttr"){
					html="";
					html ='<tr class=""><th>充值时间</th><th>充值金额</th><th>手续费</th><th>充值方式</th><th>处理状态</th><th>处理时间</th><th>备注</th></tr>';
					if(result.List.length >0){
						for (var int = 0; int < result.List.length; int++) {
							if(int <5){
								var crush=result.List[int];
								var recharge_methods =crush.rechargeMethods;//充值方式
								var rechargeDesc="";
								var status = crush.status;//充值状态
								var statusDesc="";
								var businessTime=crush.businesstime;
								if(recharge_methods == "0"){
									rechargeDesc = "系统充值";
								}else if(recharge_methods == "1"){
									rechargeDesc = "网上充值";
								}else if(recharge_methods == "2"){
									rechargeDesc = "招领充值";
								}else if(recharge_methods == "3"){
									rechargeDesc = "快捷充值";
								}else{
									rechargeDesc = "系统充值";
								}
								if(status ==  "0"){
									statusDesc ="未付款";
								}else if(status ==  "1"){
									statusDesc ="处理中";
								}else if(status ==  "2"){
									statusDesc ="充值成功";
								}else if(status ==  "3"){
									statusDesc ="充值失败";
								}
								if(businessTime == null){
									businessTime = "";
								}
								html +='<tr><td class="Numfont table_timelog">'+crush.createtime+'</td><td class="Numfont">'+(crush.amount*0.001).toFixed(2)+'</td><td class="Numfont">'+(crush.poundage*0.001).toFixed(2)+'</td><td>'+rechargeDesc+'</td><td>'+statusDesc+'</td><td class="Numfont table_timelog">'+businessTime+'</td><td><span class="explain_td">无</span></td></tr>'
							}
						}
						$("#crushTable").html(html);
					}else{
						var noDataInfo = '<div class="noRecordBox"><div class="ui-noRecord"><div class="h-ulist"><div class="noRecord"></div></div></div></div>';
						var tableHtml='<table class="table fc_9" cellspacing="0" cellpadding="0" id="crushTable">'+html+'</table>';
						$("#crushTable").parent().html(tableHtml+noDataInfo);
					}
					
				}else if(typeObj == "withdrawAttr"){
					html="";
					html='<tr class=""><th>提现时间</th><th>提现金额</th><th>手续费</th><th>处理状态</th><th>处理时间</th></tr>';
					if(result.List.length >0){
						for (var int = 0; int < result.List.length; int++) {
							if(int <5){
								var carry=result.List[int];
								var status=carry.status;
								var statusDesc="";
								var auditTime=carry.auditTime;
								if(status ==  0){
									statusDesc ="新增";
								}else if(status ==  "1"){
									statusDesc ="处理中";
								}else if(status ==  "2"){
									statusDesc ="提现成功";
								}else if(status ==  "3"){
									statusDesc ="提现失败";
								}
								if(auditTime==null){
									auditTime="";
								}
								html +='<tr><td class="Numfont table_timelog"><span>'+carry.createTime+'</span></td><td>'+(carry.amount*0.001).toFixed(2)+'</td><td>'+(carry.poundage*0.001).toFixed(2)+'</td><td>'+statusDesc+'</td><td class="Numfont table_timelog">'+auditTime+'</td></tr>';
							}
						}
						$("#carryTable").html(html);
					}else{
						var noDataInfo = '<div class="noRecordBox"><div class="ui-noRecord"><div class="h-ulist"><div class="noRecord"></div></div></div></div>';
						var tableHtml='<table class="table fc_9" cellspacing="0" cellpadding="0" id="carryTable">'+html+'</table>';
						$("#carryTable").parent().html(tableHtml+noDataInfo);
					}
				}else if(typeObj == "refundRecord"){
					//回款
					html="";
					html='<tr class=""><th>收款时间</th><th>收款类型</th><th>收款金额</th><th>备注</th></tr>';
					if(result.fundDetailList.DATA_PAGE.result.length >0){
						//$(".ui-noRecord").css("display","none");
						for (var int = 0; int < result.fundDetailList.DATA_PAGE.result.length; int++) {
							if(int <5){
								var record= result.fundDetailList.DATA_PAGE.result[int];
								var returnType="";
								for (var j = 0; j < result.fundActionList.length; j++) {
									var fa=result.fundActionList[j];
									if(fa.dbCode == record.fundAction){
										returnType =fa.showName;
									}
								}
								html+='<tr><td class="Numfont table_timelog">'+record.createDate+'</td><td class="Numfont">'+returnType+'</td><td class="Numfont">'+(record.amount*0.001).toFixed(2)+'元</td><td><span class="explain_td" title="'+record.remark+'">'+record.remark+'</span></td></tr>';
							}else{
								break;
							}
						}
						$("#refundRecord").html(html);
					}else{
						var noDataInfo = '<div class="noRecordBox"><div class="ui-noRecord"><div class="h-ulist"><div class="noRecord"></div></div></div></div>';
						var tableHtml='<table class="table fc_9" cellspacing="0" cellpadding="0" id="refundRecord">'+html+'</table>';
						$("#refundRecord").parent().html(tableHtml+noDataInfo);
					}
				}
				
			} 
		});
	};
	
	
	//交易明细
	$("ul li a.pad_b10").click(function(){
		//交易，充值，提现
		getFinancialRecords($(this).attr("value"))
	});
	
	
	
	
	//活动页面头部
		$.ajax({
	    	type: "post",
	   	 	url: "/index.do?method=showLogo",
	    	dataType: "json",
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(returnDate){
	    		if(returnDate){
	    		 $("#headLogo").html(returnDate.artContent);
	    		}
	    	}
		});
});
//格式化金额
function formatNum(pamenth){
	pamenth = pamenth.toString().replace(/\$|\,/g,'');
    if(isNaN(pamenth)){
    	pamenth = "0";
    }
    var sign = (pamenth == (pamenth = Math.abs(pamenth)));
    pamenth = Math.floor(pamenth*100+0.50000000001);
    var cents = pamenth%100;
    pamenth = Math.floor(pamenth/100).toString();
    if(cents<10){
	    cents = "0" + cents;
    }
	for (var i = 0; i < Math.floor((pamenth.length-(1+i))/3); i++){
	    pamenth = pamenth.substring(0,pamenth.length-(4*i+3))+','+pamenth.substring(pamenth.length-(4*i+3));
	}
	return (((sign)?'':'-') + pamenth + '.' + cents);
}

//关闭升级提示框
function off(){
	grateUp.style.visibility="hidden";
}
