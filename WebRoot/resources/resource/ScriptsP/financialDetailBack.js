var pwdFlag = false;
$(document).ready(function() {
	$("input[name='startdate']").datepicker({
 	   changeMonth: true,changeYear: true,closeText:'关闭',
			prevText : '&#x3c;上月',
			nextText : '下月&#x3e;',
			currentText : '今天',
			monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			weekHeader : '周',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : true,
			yearSuffix : '年',
			dateFormat : "yy-mm-dd",
			maxDate: new Date()//最小日期
	   });
    $("input[name='enddate']").datepicker({
 	   changeMonth: true,changeYear: true,closeText:'关闭',
			prevText : '&#x3c;上月',
			nextText : '下月&#x3e;',
			currentText : '今天',
			monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			weekHeader : '周',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : true,
			yearSuffix : '年',
			dateFormat : "yy-mm-dd"
			// minDate: new Date($("#startdate").val())//最小日期
    });
    
    var cur_dh = $('#tz_nwd_2');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');

    
    var fid = $("#fp_id").val();//产品id
    if(fid){
        $.ajax({
       		type : "POST", 
       		url : "/member/ajax/interestFinancialInfo.do", 
       		data: "fpIds="+fid,
       		dataType:"json",
       		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       		success : function(result) { 
       			result = eval(result);
       			var content = "";
       			var contentd = "";
       			var lid = "";
       			if(result.size>0){
       				for(var j = 0; j < result.size; j++){  
    	   				lid = result[j][0].lid;
    	   				if(result[j][1]['data'].length>0){
    	   					
    	   					for(var i = 0; i < result[j][1]['data'].length; i++){
    	   						if(result[j][1]['data'][i].QJJX>0){
    	   							contentd+= "全局"+(result[j][1]['data'][i].QJJX)/100+"%";
    	   						}
    	   						if(result[j][1]['data'][i].QJJX>0 && result[j][1]['data'][i].PTJX>0 ){
    	   							contentd+= "+";
    	   						}
    	   						if(result[j][1]['data'][i].PTJX>0 ){
    	   							contentd+= "加息券"+(result[j][1]['data'][i].PTJX)/100+"%";
    	   						}
    	   						if(result[j][1]['data'][i].PTJX==null && result[j][1]['data'][i].QJJX==null){
    	   							contentd+= "无";
    	   						}
    	   						content += "<td>"+result[j][1]['data'][i].JOINTIME+"</td>" +
    	   								"<td>"+formatNum(result[j][1]['data'][i].JOINAMOUNT/1000)+"元</td>" +
    	   								"<td><i class=\"fc_f60\">"+contentd+" </i></td></tr>"; 
    	   						contentd= "";
    	   					}
    	   				} else {
    	   					var i = 0;
    	   					if(result[j][1]['data'][i].QJJX>0){
       							contentd+= "全局"+(result[j][1]['data'][i].QJJX)/100+"%";
       						}
       						if(result[j][1]['data'][i].QJJX>0 && result[j][1]['data'][i].PTJX>0 ){
       							contentd+= "+";
       						}
       						if(result[j][1]['data'][i].PTJX>0 ){
       							contentd+= "加息券"+(result[j][1]['data'][i].PTJX)/100+"%";
       						}
       						if(result[j][1]['data'][i].PTJX==null && result[j][1]['data'][i].QJJX==null){
       							contentd+= "无";
       						}
       						content += "<td>"+result[j][1]['data'][i].JOINTIME+"</td>" +
       								"<td>"+formatNum(result[j][1]['data'][i].JOINAMOUNT/1000)+"元</td>" +
       								"<td><i class=\"fc_f60\">"+contentd+" </i></td></tr>"; 
       						contentd= "";
    	   				}
    	   				$("#interestDesc_"+lid).html("<tr><th>加入时间</th><th>单笔加入金额</th><th>年利率浮动</th></tr><tr>" +content+"</tr>");
    	   				content="";    	   				
    	   			} 
       			}
       			
       		} 
       	});    	
    }    

    
    var fpIdsStr = $("#fpIdsStr").val();//产品id
    if(fpIdsStr){
        $.ajax({
       		type : "POST", 
       		url : "/member/ajax/setContinueSignFlag.do", 
       		data: "fpIds="+fpIdsStr,
       		dataType:"json",
       		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       		success : function(result) { 
       			result = eval(result);
       			var content = "";
       			var contentd = "";
       			var lid = "";
       			if(result.length>0){
       				var continuueEms = $(".continutFlag");
       				for(var j = 0; j < result.length; j++){
       					var t = result[j];
       					continuueEms.eq(j).css("display",t==true?"block":"none");
    	   			} 
       			}
       			
       		} 
       	});    	
    }
    //账户中心嘉财列表续签标签
    var fpIdsList = $('#fpIdsList').val();
    if(fpIdsList){
        $.ajax({
       		type : "POST", 
       		url : "/member/ajax/checkRenewFlag.do", 
       		data: "fpIds="+fpIdsList,
       		dataType:"json",
       		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
       		success : function(result) { 
       			result = eval(result);
       			var content = "";
       			var contentd = "";
       			var lid = "";
       			if(result.length>0){
       				for(var j = 0; j < result.length; j++){
       					var t = result[j];
       					var flag = t.flag;
       					var fid = t.fid;
       					var has_spanId = "#hasrenew_"+fid;
   						var un_spanId = "#unrenew_"+fid;
   						var early_spanId = "#earlyrenew_"+fid;
       					//-2不支持续签  -1不可续签 0 取消了续签 1 可以续签,但未续签 2 已经续签,但未成功跑批 4 己跑批
       					if(flag==2 || flag==4){//已续签
       						var un_aId = "#hasrenewaid_"+fid;
       						var url = "/member/financialDetailBack.do?fp_id="+fid;
       						$(has_spanId).attr("style","display:block");
       						$(un_aId).attr("href",url);
       						$(un_spanId).remove();
       						$(early_spanId).remove();
       					}else if(flag== 0 || flag == 1){//未续签(可续签)
       						var un_aId = "#unrenewaid_"+fid;
       						var url = "/member/financialDetailBack.do?fp_id="+fid;
       						$(un_spanId).attr("style","display:block");
       						$(un_aId).attr("href",url);
       						$(has_spanId).remove();
       						$(early_spanId).remove();
       					}else if(flag == -1){//未续签(不到续签期限)
       						$(early_spanId).attr("style","display:block");
       						$(un_spanId).remove();
       						$(has_spanId).remove();
       					}else{//不支持续签不显示标签
       						$(early_spanId).remove();
       						$(un_spanId).remove();
       						$(has_spanId).remove();
       					}
    	   			} 
       			}
       		} 
       	});    	
    }
    $("input.nwd_icon_inputtime").change(function() {
   		var regex = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");
    	   	var dateValue = $(this).val();
    	   	if (dateValue != '' && !regex.test(dateValue)) {
//    	   		showMsg("日期格式不正确！(正确格式：2012-12-12)");
//    	   		$("#msgContent").html("日期格式不正确！(正确格式：2012-12-12)");
    	   		$("#tipMsg").text("日期格式不正确！(正确格式：2012-12-12)");
    	   		showCon_0();
    	    	$(this).val("");
    	    } else {
    	    	if (compareDate($("#startdate").val(),$("#enddate").val())) {
    	    		// 直接输入开始日期或结束日期时，则为自定义日期查询
    	    		// 更改日期类型, 为自定义日期类型
    	    		$("#dateType").val(3);
    				// 表单提交
    	    		document.forms[0].submit();
    			}
    	    }
   	});
    
    	var dateTypeButtons = $(".a_btn .lei");
    
		dateTypeButtons.each(function(){
			var dataTypeBtn=$(this);
			dataTypeBtn.click(function () {
				   var dateType = $(this).index();
					// 将数据类型值赋给dateType隐藏域
					$("#dateType").val(dateType-3);
					// 表单提交
					document.forms[0].submit();
				});
		});
    
		//确定按钮提交后台
		$("#baoSelect").click(function(){
			var incomset=$("input[name='incomset']:checked").val();
			var syszview=$("input[name='incomset']:checked").next("label").text();
			var fp_id=$("#fp_id").val();
			/**
			 * 提交时没有token。通过访问URL直接提交Bug修复
			 */
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
			$.ajax({
				type: "POST",
				url: "/financial/updateIncomsetByFpid.do",
				data: $.param({"fp_id":fp_id,"incomeset":incomset,"stok":stok}),
				dataType:"json",
				contentType:'application/x-www-form-urlencoded;charset=UTF-8',
				success: function(result,status) {
					 if(result=="ok"){
						//调用后台设置收益配置
						 $("#syszview").html("");
						 $("#syszview").html(syszview);
					 }else{
						 alert("系统出现未知错误,请稍后再试!");
					 }
				}
			});
			
		});
		
		$("#clearSelect").click(function(){
			
		});
    
		
		$("a#close").live("click", function() {
			showCon_1();
		});
		
		$("#closeConfirm").click(function() {
			closeAll_1();
			/**
			 * 提交时没有token。通过访问URL直接提交Bug修复
			 */
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
			$.ajax({
				type : "POST",
				data : {
					fsid : $("#fsid").val(),
					stok : stok
				},
				url : "/member/financial/closeFinancialSecretary.do",
				async : false,
				success : function(data) {
					
					if (!data) {
						$("#tipMsg").text("关闭嘉财小秘书失败");
						showCon_0();
//						showMsg("停用预约定投失败");
					}
					var json = eval("(" + data + ")");
					if (json.state == true) {
						/** 效果切换 * */
						// 隐藏排名信息
						$("#rank").html();
						//$("#queuingAmount").html();
						$("#queuingNumber").html();
						$("#rankInfo").addClass("hidden");
						$("#activeState").removeClass("btn_orange").addClass("btn_gray1").html(" 未开启 ");
						$("#close").attr("id", "active").html("开启");
					} else {
						if (!json.message) {
							$("#tipMsg").text("关闭嘉财小秘书失败,请确认信息后，再次提交");
							showCon_0();
//							showMsg("停用预约定投失败,请确认信息后，再次提交");
						} else {
							//showMsg(json.message);
							$("#tipMsg").text(json.message);
							showCon_0();
						}
					}
				},
				error : function(e) {
					showMsg("出错：" + e);
				}
			});
		});
		
		$("a#active").live("click", function() {
			/**
			 * 提交时没有token。通过访问URL直接提交Bug修复
			 */
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
			$.ajax({
				type : "POST",
				data : {
					fsid : $("#fsid").val(),
					stok : stok
				},
				url : "/member/financial/activeFinancialSecretary.do",
				async : false,
				success : function(data) {
					if (!data) {
						$("#tipMsg").text("开启嘉财小秘书失败");
						showCon_0();
//						showMsg("停用预约定投失败");
					}
					var json = eval("(" + data + ")");
					if (json.state == true) {
						/** 效果切换 * */
						// 隐藏排名信息
						$("#rank").html(json.rank);
						//$("#queuingAmount").html(json.queuingAmount);
						if (json.canInvestmentPeriod == 0) {
							$("#queuingNumber").removeClass("bold fc_orange").html("");
							$("#queuingNumber").next("span").text("当期可投");
							$("#queuingNumber").prev("span").text("");
						} else {
							$("#queuingNumber").addClass("bold fc_orange").html(" " + json.canInvestmentPeriod + " ");
							$("#queuingNumber").next("span").text("期后可投");
							$("#queuingNumber").prev("span").text("预计");
						}
						$("#rankInfo").removeClass("hidden");
						$("#activeState").removeClass("btn_gray1").addClass("btn_orange").html(" 已开启 ");
						$("#active").attr("id", "close").html("关闭");
					} else {
						if (!json.message) {
							$("#tipMsg").text("开启嘉财小秘书失败,请确认信息后，再次提交");
							showCon_0();
//							showMsg("停用预约定投失败,请确认信息后，再次提交");
						} else {
							//showMsg(json.message);
							$("#tipMsg").text(json.message);
							showCon_0();
						}
					}
				},
				error : function(e) {
					showMsg("出错：" + e);
				}
			});
		});
		
		//退出验证交易密码
		$("#pwd").blur(function(){
	        //阉割掉这个验证，交互不合理
	        var pwd = $("#pwd").val();
	        if(pwd==""){
	            pwdFlag = false;
	            $("#pwdMSG").html("<i></i>请输入交易密码");
	            $('#pwdMSG').hide();
	            $('#pwdMSG').fadeIn("slow");
	        }else{
	            pwdFlag = true;
	            $('#pwdMSG').css("display","none");
	            $("#pwdMSG").html("");
	        }
	    });
});

//改变收益配置
function changeIncomeSet(){
	art.dialog.load('../ftl/financial/incomset.html',{lock:true,id:'showProtocolBox'}, false).title('收益设置');
} 
var _data="";

//退出嘉财其次 确认
function sureExitFinancial(fp_id){
	if(_data==""){
		$.ajax({
			type: "post",
			url: "/member/sureExitFinancial.do",
			dataType: "json",
			data:{
				fp_id:fp_id
			},
			async: false,
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data){
				_data=data;
				validateData(_data,fp_id);
			}
		}); 
	}else{
		validateData(_data,fp_id);
	}
}
function validateData(data,fp_id){
	if(data){
		if(data.result == 'success'){//成功
			if(data.model.viewStatus=='0'){
				art.dialog.alert("退出失败,该嘉财有道期次还在锁定期!");
				return false;
			}else if(data.model.isFreeze=='1'){
				art.dialog.confirm("您有部分资金正在投标锁定中，退出周期将会延长1-2天,确定退出吗?",function(topWin){
					closeDialog();
					setTimeout(callExitFinancial(data,fp_id),500);
					//exitFinancial(data,fp_id);
				});
			}else{
				exitFinancial(data,fp_id);
			}  
		}else{
			art.dialog.alert("退出失败,请稍后再试或者联系客服!");
		}
	}
}

function callExitFinancial(data,fp_id){
    return function()
    {
    	exitFinancial(data,fp_id);
    }
}

function exitFinancial(data,fp_id){
	var isYYS=$("#isYYS").val();
	var webPlanSign=$("#webPlanSign").val();
	var webVersionNo=$("#webVersionNo").val();
	var webShowWebsite=$("#webShowWebsite").val();
	var webClearingWay = $("#webClearingWay").val();//是否是浮动利率标识，11为动态利率，21和22为固定利率
	var fp_sub_name = $.trim($("#fp_sub_name").html());//产品名称
	$("#exitMsg").css('display','none');//隐藏错误提示
    $(".pop-close").click(function(){ popUp.hideLayer($("#out1-pop-mx"))});
    $(".pop-close").click(function(){ popUp.hideLayer($("#out2-pop-mx")); window.location.reload();});
	/**
	 * 支持部分退出产品：
	 * 季季丰 版本号1&D
	 * 双季赢 版本号1&G
	 * 嘉利宝 版本号1&F
	 */
	if((webClearingWay!="11" && webVersionNo==1 && (webPlanSign == "D" || webPlanSign == "G" || webPlanSign == "F"))){
		//alert('veiw:'+data.model.viewStatus+":fee:"+data.model.earlyQuitFee);
		//viewStatus==2标示是到期退出，1标示是提前退出
		if(data.model.viewStatus=='2' || data.model.viewStatus=='1'){
			//alert('balance:'+fp_sub_name);
			$("#currProName").text(fp_sub_name+data.model.period_no);
			$("#diogAmountJoin").text(data.model.amount_join/1000);//加入金额
			$("#inputExitAmount").val(data.model.exitPartAmount/1000);//退出金额
			$("#gsExitAmount").text(data.model.exitPartAmount/1000);//退出金额
			$("#gsRate").text(data.model.rateMin/100);//退出费率
			$("#gsHoldDay").text(parseInt(data.model.holdDay)-parseInt(data.model.bidding_period));//持有天数（不包含投标期）
			$("#exitIncomeVal").text((data.model.expectProfit/1000).toFixed(2));//退出收益
			$("#exitManagerFee").text((data.model.earlyQuitFee/1000).toFixed(2));//提前退出费用
			$("#actualAmount").text((data.model.balance/1000).toFixed(2));//实际到账金额
			$("#earlyExitRate").text(data.model.earlyExitRate/100);//未到期的退出手续费利率
			//alert('fee:'+data.model.earlyQuitFee);
			$("#webExpirTimeFlag").val(data.model.contractPeriodflag);//是否到期
			$("#webPercent").val(data.model.percent);//会员等级特权
			$("#hasFreezeAmount").val(data.model.hasFreezeAmount);//是否有冻结资金
			if(data.model.earlyQuitFee <= 0){
				$("#releaseFeeHtml").css('display','none');
			}else{
				$("#releaseFeeHtml").css('display','block');
			}
			//退出标识 partExitFlag
			$("#partExitFlag").val(data.model.partExitFlag);
			//因为之前查询接口信息数据由部分问题，所以要采用js来计算界面的值，但是又需要原接口里面的部分数据（会员等级特权之类的）,所以现在采用这种方式重新调用一下js方法重新給界面赋值
			changeMoneyMethod(2);//js计算部分退出数据值
			/*$('.plusBankBg').show();
			$('.pop1').slideDown();
			$('.box').show();
			$('.box2').hide();
			$('.next_out').show();
			$('.next_out2').hide();
			$('.nextli').addClass('next');
		    $('.nextstp2').removeClass('step-3').addClass('step-2');*/
			var attr1 = new Attention( popUp , '#out1-pop-mx' ,  true );
			attr1.event();
		}
		
		//初始化确认退出的事件
		$('#out2_btn').click(function(){
			var inputExitAmount = $("#inputExitAmount").val();//本次退出金额
			var diogAmountJoin = $("#diogAmountJoin").text();
			if(!inputExitAmount){
				$("#exitMsg").text('请输入退出金额！');
				$("#exitMsg").css('display','block');
			}else if(parseInt(inputExitAmount) > parseInt(diogAmountJoin)){
				$("#exitMsg").text('对不起，您的退出金额超出投资额！');
				$("#exitMsg").css('display','block');
			}else if(parseInt(inputExitAmount) <=0){
				$("#exitMsg").text('对不起，您输入的退出金额有误！');
				$("#exitMsg").css('display','block');	
			}else if(parseInt(inputExitAmount) < 100){
				$("#exitMsg").text('对不起，退出金额必须大于100！');
				$("#exitMsg").css('display','block');
			}else{
				$("#exitMsg").css('display','none');
				$("#pwd").blur();
				if(pwdFlag){
					pwdFlag = false;
					
					if(getCookie("countMSG"+$('#mbId').val()+"")==""){//存在
			            setCookie("countMSG"+$('#mbId').val()+"",5);//初始化为5
			        }
					
					$.ajax({
			            type: "post",
			            url: "/member/memberCheckPwd.do",
			            dataType: "json",
			            data:{
			                atPresentPwd:$("#pwd").val(),
			                type:"repwd"
			            },
			            async: false,
			            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			            success: function(msg){
			                if(msg['msg'] == 1){
			                    if(getCookie("countMSG"+$('#mbId').val()+"") > 0){
			                    	pwdFlag = true;
			                    }
			                    else{
			                    	$("#pwdMSG").css("display","");
			                    	$("#pwdMSG").html("<i></i>对不起，您的交易密码已被锁定，请明日再试");
			                    }
			                }
			                else if(msg['msg']==0){
			                    var countMSG = parseInt(getCookie("countMSG"+$('#mbId').val()));
			                    if(countMSG<0){
			                    	countMSG = 0
			                    }

			                    if(countMSG <= 0){
			                    	$("#pwdMSG").css("display","");
			                    	$("#pwdMSG").html("<i class='fl'></i><span class='inline_block wid_w240'>对不起，您的交易密码已被锁定，请24小时后再来<span>");
			                    }else{
			                    	setCookie("countMSG"+$('#mbId').val(),countMSG-1);
			                    	$("#pwdMSG").css("display","");
			                    	$("#pwdMSG").html("<i class='fl'></i><span class='inline_block wid_w240'>交易密码错误,您还剩"+parseInt(getCookie("countMSG"+$('#mbId').val()))+"次输入机会，请重新输入！<span>");
			                    }

			                }
			            }
			        });
				}
				/*$('.box').hide();
			    $('.box2').show();
			    $(this).hide();
			    $('.next_out2').show();*/
				if(pwdFlag){
					popUp.hideLayer($("#out1-pop-mx"));
					var attr = new Attention( popUp , '#out2-pop-mx' ,  true );
		            attr.event();
			        //确定退出
					$.ajax({
				    	type: "post",
				   	 	url: "/member/sureExitFinancial.do",
				    	dataType: "json",
				    	data:{
				    		fp_id:fp_id,
				    		isExitFlag:"true",
				    		exitAmount:inputExitAmount
				   		 },
				    	async: false,
				    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				    	success: function(returnDate){
				    		if(returnDate&&returnDate.result == 'success'){//成功
				    			//window.location.reload();//刷新页面
				    			//alert('balance:'+returnDate.model.balance+':diogAmountJoin:'+diogAmountJoin+':剩余金额:'+inputExitAmount);
				    			var daozhangMoney=(returnDate.model.balance/1000).toFixed(2);//接口返回的实际到账金额
				    			var rateTemp = (returnDate.model.rateMin/100).toFixed(2);
				    			if(returnDate.model.balance ==undefined || returnDate.model.balance == null){
				    				var actualAmount = $("#actualAmount").text();//预期到账金额(这个值是之前界面上计算好的。)
				    				daozhangMoney = actualAmount;
				    			}
				    			if(returnDate.model.rateMin ==undefined || returnDate.model.rateMin == null){
				    				rateTemp = $("#gsRate").text();
				    			}
				    			
				    			$("#creditedAmount").text(daozhangMoney);//实际到账金额
				    			if(parseInt(diogAmountJoin)-parseInt(inputExitAmount)==0){
				    				$("#surplusdAmount").parent().css("display","none");
				    			}else if(parseInt(diogAmountJoin)-parseInt(inputExitAmount) > 0){
				    				$("#surplusdAmount").parent().css("display","block");
				    				$("#surplusdAmount").text(parseInt(diogAmountJoin)-parseInt(inputExitAmount));//剩余金额
				    			}
				    			$("#proRate").text(rateTemp);//收益
				    			$('.nextli').addClass('next');
				    	        $('.nextstp2').removeClass('step-2').addClass('step-3');
				    		}else{
				    			$('#exitResult').html("退出失败,请稍后再试或者联系客服!");
			    				//art.dialog.alert("退出失败,请稍后再试或者联系客服!");
			    			}
				    	}
					});
				}
			}
	    });
	}else{
		var hhMsg="";
		if((webVersionNo==1 && (webPlanSign == "H"))){
			$.ajax({
		    	type: "post",
		   	 	url: "/financial/getUserAddPointInfo.do",
		    	dataType: "json",
		    	//data:{fp_id:fp_id},
		    	async: false,
		    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    	success: function(returnDate){
		    		if(returnDate != '' && returnDate.maxInterest > 0){
		    			hhMsg = "<br>您有一张"+(returnDate.maxInterest/100)+"%加息券可用，<a href='http://www.niwodai.com/financial/financial_F.html' class='blue'>立即使用</a>";
		    		}else{
		    			hhMsg = "";
					}
		    	}
			});
		}
		
		//走老的退出逻辑（只支持全部退出）
		var msg="您持有的嘉财有道-" + fp_sub_name + " " + data.model.period_no+"期还未到约定期,"+
		"<br>现在退出将收取"+data.model.amount_exit_name+"的"+data.model.amount_exit_rate+"%作为退出费。"+
		hhMsg+
		//"<br>预计退出后回收总额 "+(data.model.exit_amount)+"元"+
		"<br>确定要退出吗？";
		
		//正常退出
		if(data.model.viewStatus=='2'){
			msg="您持有的嘉财有道-" + fp_sub_name + " " +data.model.period_no+"已过约定期,"+
			"<br>现在退出将不收取退出费。"+
			//"<br>预计退出后回收总额 "+(data.model.exit_amount)+"元"+
			"<br>退出后资金将于2个工作日内返回至您的账户余额（提示：如有提现计划请关注假期公告）"+
			hhMsg+
			"<br>确定要退出吗？";
			if(isYYS=='1'){
				msg="您持有的嘉财有道-" + fp_sub_name + " " +data.model.period_no+"已过约定期,"+
				"<br>现在退出将不收取退出费。"+
				"<br>退出后资金将于2个工作日内返回至您的账户余额（提示：如有提现计划请关注假期公告）"+
				hhMsg+
				"<br>确定要退出吗？";
			}
		}
		
		art.dialog.confirm(msg,function(){
			//确定退出
			$.ajax({
		    	type: "post",
		   	 	url: "/member/sureExitFinancial.do",
		    	dataType: "json",
		    	title: "退出嘉财有道",
		    	data:{
		    		fp_id:fp_id,
		    		isExitFlag:"true"
		   		 },
		    	async: false,
		    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    	success: function(returnDate){
		    		if(returnDate&&returnDate.result == 'success'){//成功
		    			window.location.reload();//刷新页面
		    		}else{
	    				art.dialog.alert("退出失败,请稍后再试或者联系客服!");
	    			}
		    	}
			});
		});
	}
}

//设置cookies
function setCookie(name, value, d) {
    var exp = new Date();
    if (d > 0)
        Days = d;
    exp.setTime(exp.getTime() + 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value)
        + ";path=/;expires=" + exp.toGMTString();
}
//读取cookies
function getCookie(name) {
    if (document.cookie.length > 0) {
        c_start = document.cookie.indexOf(name + "=");
        if (c_start != -1) {
            c_start = c_start + name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1)
                c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";

}

/*
 * 动态改变退出金额一系列值的变化
 * */
function changeMoneyMethod(obj){
	var diogAmountJoin = $.trim($("#diogAmountJoin").text());//理财总金额
	var inputAmount = $.trim($("#inputExitAmount").val());//退出金额
	
	var holdDay = $.trim($("#gsHoldDay").text());//持有时间
	var rate = $.trim($("#gsRate").text());//产品利息
	var earlyExitRate= $.trim($("#earlyExitRate").text());//未到期退出手续费
	var webExpirTimeFlag= $.trim($("#webExpirTimeFlag").val());//是否在合约期限
	var webPercent= $.trim($("#webPercent").val());//会员折扣比例
	var exitFee = 0;
	if(parseInt(obj) == 3){
		//全部
		inputAmount = diogAmountJoin;
		$("#inputExitAmount").val(inputAmount);
	}
	if (!inputAmount){
		$("#exitMsg").text('请输入退出金额');
		$("#exitMsg").css('display','block');
		return false; 
	}else if(parseInt(inputAmount) < 0){
		$("#exitMsg").text('退出金额必须大于0');
		$("#exitMsg").css('display','block');
		return false; 
	}else if(parseInt(inputAmount) < 100){
		//金额限制
		$("#exitMsg").text('退出金额必须大于100');
		$("#exitMsg").css('display','block');
		return false; 
	}else if(parseInt(inputAmount) > parseInt(diogAmountJoin)){
		$("#exitMsg").text('退出金额超限');
		$("#exitMsg").css('display','block');
		return false; 
	}else{
		$("#exitMsg").css('display','none');
		//obj==0 表示减，1表示加
		if(obj==0){
			if(parseInt(inputAmount)-parseInt(100)>=0 && parseInt(inputAmount)-parseInt(100)>=100){
				inputAmount = parseInt(inputAmount)-parseInt(100);
			}
		}else if(obj==1){
			if(parseInt(inputAmount) + parseInt(100) <= parseInt(diogAmountJoin)){
				inputAmount = parseInt(inputAmount) + parseInt(100);
			}
		}
		var interestAmount = (parseInt(inputAmount)*(rate/100)*parseInt(holdDay))/365;//收益
		//alert('interestAmount:'+interestAmount);
		if(webExpirTimeFlag == "true"){
			//合约期限内收取退出费
			exitFee = (earlyExitRate*0.01)*(Number(inputAmount)+Number(interestAmount));
			if(Number(webPercent)!=0){
				exitFee = (exitFee*Number(webPercent)*0.01);
			}
			$("#releaseFeeHtml").css('display','block');
			$("#exitManagerFee").text(exitFee.toFixed(2));//提前退出费用
		}
		$("#inputExitAmount").val(inputAmount);//文本框展示的退出金额
		$("#gsExitAmount").text(inputAmount);//计算公式展示的退出金额
		$("#exitIncomeVal").text(interestAmount.toFixed(2));//退出收益
		$("#actualAmount").text((Number(inputAmount)+Number(interestAmount.toFixed(2))-Number(exitFee.toFixed(2))).toFixed(2));//退出总到账金额(本金+收益-手续费)
	}
}

function btnBid(){
	var amount = $("#investmentAmount").val();
	var memberAmount = $("#ma1").text();//账户余额
	var needAmount = $("#needAmount").text();//还需金额  
	var smallAmount = $("#smallAmount").text(); //最小金额
	if(!$("#agreement").attr("checked")) 
	{ 
		showMsg("未同意《借款协议》"); 
		return false; 
	}
	if(amount%1000!='0'){
		
		showMsg('亲，您输入的数值不是1000的整数倍，请重新输入^_^！');
		return;
	}
		
	
    if(parseFloat(amount) < parseFloat(smallAmount)){
    	showMsg('亲，您输入的数值不能小于最小投资金额 ^_^！');
    	return;
    }
	
    if(parseFloat(amount) > parseFloat(needAmount)){
    	showMsg('亲，您输入的数值不能大于所需投资金额 ^_^！');
    	return;
    }
    
    if(parseFloat(memberAmount) < parseFloat(amount)){//账户余额 < 投资金额，投资金额框显示账户余额
		showMsg('亲，您的财户余额不足,请充值  ^_^！');
		
	}else{
	    $.ajax({
	    	type: "post",
	   	 	url: "/financial/btnBidSure.do",
	    	dataType: "json",
	    	data:{
	    		amount:amount,
	    		fpId:$("#fpId").val()
	   		 },
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(date){
	    		if(date['state'] == '1'){//成功
	    			art.dialog.data("periodNo", $("#periodNo").text());
	    			art.dialog.data("investmentAmount", $("#investmentAmount").val());
	    			art.dialog.data("cPeriodId", $("#cPeriodId").text());
	    			art.dialog.data("rate", $("#rateMinId").text()+"%-"+$("#rateManId").text()+"%");
	    			var radioTemp = document.getElementsByName("proRadio");
	    			var radioId;
	    			for(var i=0;i<radioTemp.length;i++){
	    				if(radioTemp[i].checked){
	    					if(radioTemp[i].value==0){
	    						radioId = radioTemp[i].value;
	    						art.dialog.data("radioId", "收益再投资");
	    					}else{
	    						radioId = radioTemp[i].value;
	    						art.dialog.data("radioId", "收益返还");
	    					}
	    				}
	    			}
	    			art.dialog.load("../ftl/financial/confirmFinancial.html",{
        				ok: function () {
        					//保存redid:redid,          //优惠券ID redAmount:redAmount   //优惠券抵用金额
        					var url="/financial/btnBidSave.do?amount="+amount + "&fpId="+$("#fpId").val()
        					+"&radioId="+radioId;
        					$("#bidForm").attr("action",url);
        					$("#bidForm").method="post";
        					$("#bidForm").submit();
        			        return false;
        			    },
        			    cancelVal: '取消',
        			    cancel: true
        			},false).title('请确认您的投资信息！');
	    		}
	    	}
	    });
	}
}


//借款协议
function lockAgreement(lidStr){
	//window.showModalDialog('/member/ajax/loanAgreement.do?lidStr='+lidStr,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
	window.open('/member/ajax/loanAgreement.do?lidStr='+lidStr,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
}

//嘉财期协议
function loanOnOneAgreement(fpId){
	//window.showModalDialog('../ftl/financial/financialProtocol.html?fpId='+fpId,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
	window.open('../ftl/financial/financialProtocol.html?fpId='+fpId,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
}

/**
 * 新版理财投资协议_废弃
 * @param fpId
 */
function newFinancialAgreement(fpId) {
	window.open('../ftl/financial/newFinancialProtocol.html?fpId='+fpId,'','dialogWidth=1000px;dialogHeight=600px,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,dialogTop=20px,dialogLeft=20px,resizable=no');
}

function closeDialog() { 
	var list = art.dialog.list; 
	for (var i in list) { 
		list[i].close(); 
	}; 
}

function compareDate(checkStartDate, checkEndDate) {      
    var arys1= new Array();      
    var arys2= new Array();      
    if(checkStartDate != null && checkEndDate != null){      
    	arys1=checkStartDate.split('-');      
    	var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);      
    	arys2=checkEndDate.split('-');      
    	var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);      
    	if(sdate > edate){      
    		$("#errorMsg").text("日期开始时间不能大于结束时间！");        
    		return false;         
    	}else{    
    		return true;      
    	}   
    }      
} 

/**
 * 到期处理
 */
function doProcessMode(){
	
	var fp_id=$("#fp_id").val();
	if(fp_id==undefined||$.trim(fp_id)==""){
		alert("产品ID不能为空");
		return;
	}
	var processMode;
	$("input[name='process_mode']").each(function(i,e){
		if($(e).attr("checked")){
			processMode=$(e).val();
		}
	});
	var timeToken=$("#timeToken").val();
	if(timeToken==undefined||$.trim(timeToken)==""){
		alert("令牌无效");
		return;
	}
	if(processMode){
		$.post("/member/doProcessMode.do",{fp_id:fp_id,processMode:processMode,timeToken:timeToken},function(data){
			if(data){
				var message=data['message'];
				if(message){
					if("保存成功"==message){
						if("autoInvest"==data['processMode']){
							$("#process_mode").html("自动续投");
						}else{
							$("#process_mode").html("到期退出");
						}
					}
					alert(message);
				}
			}else{
				alert("服务器发生错误！");
			}
		},"json");
	}else{
		alert("到期处理方式不能为空");
		return;
	}
}

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

$(function(){
	//leftnavX  祥见  leftNav2016.ftl
	$("#leftnav2").addClass("navcurron").siblings().removeClass("navcurron");
});

//退出嘉财其次 确认
function cancelExitFinancial(){
	$(".pop-close").click(function(){ popUp.hideLayer($("#out3-pop-mx"));});
	var attr = new Attention( popUp , '#out3-pop-mx' ,  true );
    attr.event();
}


function sureCancelExitFinancial(fp_id){
	var fp_sub_name = $.trim($("#fp_sub_name").html());//产品名称
	//确定退出
	$.ajax({
    	type: "post",
   	 	url: "/member/cancelExitFinancial.do",
    	dataType: "json",
    	data:{
    		fp_id:fp_id
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(returnDate){
    		if(returnDate&&returnDate.result == 'success'){//成功
    			$("#out3-pop-mx").find("dl dd").html('<i class="icon_base icon_50 icon_base_tipright50"></i>');
    		}else{
    			$("#out3-pop-mx").find("dl dd").html('<i class="icon_base icon_32 icon_base_tiperror32"></i>');
			}
    		$("#out3-pop-mx").find("dl dt p:last a:last").hide();//隐藏取消按钮
    		$("#out3-pop-mx").find("dl dt p:last a:first").removeAttr("href");//移除事件
    		//alert($("#out3-pop-mx").find("dl dt p:last a:first").attr("href"));
    		$("#out3-pop-mx").find("dl dt p:last a:first").click(function(){ popUp.hideLayer($("#out3-pop-mx")); window.location.reload();});
    		$("#out3-pop-mx").find("dl dt p:first").html(returnDate.msg);//消息
    	}
	});
}