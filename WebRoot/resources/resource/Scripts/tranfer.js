	 //初始化数据加载交易记录
	getUserDebttrons("1");
	
	/**
	 * 获取资金记录
	 */
	function getUserDebttrons(typeObj){
		if(typeObj == "undefined" || typeObj == null || typeObj == ""){
			typeObj == "1";
		}
		var html ="";
		if(typeObj == "2"){//转让中
			$.ajax({
				type : "POST", 
				url :  basePath+"/user/userRecharge/rechargeDatails.action", 
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) { 
					var arr =result;
					html="";
					html ='<tr class=""><th><span>时间</span></th><th>类型</th><th>金额</th><th>状态</th><th>操作</th></tr>';
					if(result.list.length >0){
						for (var int = 0; int < arr.length; int++) {
							if(int < 5){
								var transaction=arr[int];
								
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
				}
			});
		}else if(typeObj == "rechargeAttr"){
			
			$.ajax({
				type : "POST", 
				url : basePath+"/user/userRecharge/rechargeDatails.action", 
				dataType:"json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) { 
				
					var arr =result;
					html="";
					html ='<tr class=""><th>充值时间</th><th>充值金额</th><th>手续费</th><th>充值方式</th><th>处理状态</th><th>处理时间</th><th>备注</th></tr>';
					if(arr.length >0){
						for (var int = 0; int < arr.length; int++) {
							if(int <5){
								var crush=arr[int];
								var recharge_methods =crush.rechargetype;//充值方式
								var rechargeDesc="";
								var status = crush.status;//充值状态
								var statusDesc="";
								var businessTime=crush.starttimeStr;
								if(recharge_methods == "0"){
									rechargeDesc = "无";
								}else if(recharge_methods == "1"){
									rechargeDesc = "个人网银";
								}else if(recharge_methods == "2"){
									rechargeDesc = "企业网银";
								}else if(recharge_methods == "3"){
									rechargeDesc = "快捷充值";
								}else{
									rechargeDesc = "汇款充值";
								}
								if(status ==  "1"){
									statusDesc ="成功";
								}else if(status ==  "2"){
									statusDesc ="失败";
								}else if(status ==  "3"){
									statusDesc ="取消";
								}else if(status ==  "4"){
									statusDesc ="待充值";
								}
								if(businessTime == null){
									businessTime = "";
								}
								html +='<tr><td class="Numfont table_timelog">'+businessTime+'</td><td class="Numfont">'
								+(crush.amount*0.001).toFixed(2)+'</td><td class="Numfont">'+(crush.recharfee*0.001).toFixed(2)+
								'</td><td>'+rechargeDesc+'</td><td>'+statusDesc+'</td><td class="Numfont table_timelog">'
								+crush.endtimeStr+'</td><td><span class="explain_td">'+crush.rechargeno+'</span></td></tr>'
							}
						}
						
						$("#crushTable").html(html);
						
					}else{
						var noDataInfo = '<div class="noRecordBox"><div class="ui-noRecord"><div class="h-ulist"><div class="noRecord"></div></div></div></div>';
						var tableHtml='<table class="table fc_9" cellspacing="0" cellpadding="0" id="crushTable">'+html+'</table>';
						$("#crushTable").parent().html(tableHtml+noDataInfo);
					}
				}
			});
		}else if(typeObj == "withdrawAttr"){
			$.ajax({
				type : "POST", 
				url : "/member/financialRecords.do", 
				dataType:"json",
				data: "moreCode="+typeObj,
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) { 
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
				}
			});
		}else if(typeObj == "refundRecord"){
			$.ajax({
				type : "POST", 
				url : "/member/financialRecords.do", 
				dataType:"json",
				data: "moreCode="+typeObj,
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				success : function(result) { 
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
			});
		}
	};
	
	
	//交易明细
	$("ul li a.pad_b10").click(function(){
		//交易，充值，提现
		getUserDebttrons($(this).attr("value"))
	});
	