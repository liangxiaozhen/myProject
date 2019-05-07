var userloanApp = {
		//显示标的信息
  		low_showAllTendItemDetail:function(obj){
  			var opid = $(obj).data("opid");
  			 if(isEmpty(opid)){
  				 return ;
  			 }
   			 $.tzAjax.request({
  				 model:"user/userLoanApp",
  				 method:"showAllTendItemDetail.action",
   				 callback:function(data){
   					$("#item-content").html(data);
  				 }
  			 },{"opid":opid});
  		},
  		//显示标的具体还款计划详情
  		low_showRepaymentPlanDateil:function(obj){
  			var opid = $(obj).data("opid");
    		if(isEmpty(opid)){
 				 return ;
 			 }
  			 $.tzAjax.request({
 				 model:"user/userLoanApp",
 				 method:"showRepaymentPlanDateil.action",
  				 callback:function(data){
   					$("#item-content").html(data);
 				 }
 			 },{"opid":opid});
  		},
  		
 		//正常还款 选择部分还款或者全部还款
		low_doRepayMentSelect:function(obj){
			var opid = $(obj).data("opid");
			if(isEmpty(opid)){
				loading("网络异常,请稍后再试...",4);
				return ;
			}
			var html = "<button type='button' onclick='userloanApp.low_doRepayMentPartSelect(this)' data-opid='"+opid+"'"+
						"class='btn btn-primary' id='low_partSelect'>部分还款</button>"+
						"<button type='button' onclick='userloanApp.low_doRepayMentNormalAll(this)' data-opid='"+opid+"'"+
						"class='btn btn-primary' id='low_normal'>全部还款</button>";
 			$("#myRepayModal_body").html(html);
			$("#myRepayModal").modal();
 		},
 		//正常还款 部分还款  显示投资人
 		low_doRepayMentPartSelect:function(obj){
 			var opid = $(obj).data("opid");
  			$("#myRepayModal").modal("hide");
  			$.tzAjax.request({
  				model:"user/userLoanApp",
  				method:"doRepayMentPartSelect.action",
  				callback:function(data){
   					$("#item-content").html(data);
  				}
  			},{"opid":opid});
 		},
 		//正常还款  部分还款 选择部分投资人后提交
 		low_doRepayMentNormalPart:function(){
 			 var normalSelect = $(".normalRepay").length;
 			 var count = 0;var opid = "";
 			 for(var i = 0;i < normalSelect;i++){
 				 if($(".normalRepay")[i].checked){
 					 count ++;
 					 opid +=$(".normalRepay").eq(i).data("opid")+",";
 				 }
 			 }
 			 if(count == 0){
 				 alert("请至少选中一个投资人还款");
 				 return false;
 			 }
 			 opid = opid.substring(0,opid.lastIndexOf(","));
 			 $("#low_normal").text("处理中...").removeAttr("onclick");
 			 $.tzAjax.request({
 				 model:"borrowingUser/repayMent",
 				 method:"doNormalRepayMentPart.action",
 				 callback:function(data){
 					$("#low_normal").attr("onclick","userloanApp.low_doRepayMentNormalPart(this)").text("立即还款");
   					 var obj = $.parseJSON(data);
 					 if(obj.result == "logout"){
 						 loading("session 失效",4);
 						 setTimeout(function(){
 							 window.location.href= basePath+"/user/tologin.action";
  						 },1000);
 					 }else if(obj.result == "opid_null"){
 						loading("参数非法...",4);
 					 }else if(obj.result == "avlbal_insufficient"){
 						loading("账号可用余额不足",4);
 					 }else if(obj.result == "success"){
 						loading("提交成功",4);
 						setTimeout(function(){
 							window.location.href= basePath+"/user/userLoanApp/list.action";
 						},2000);
 					 }else if(obj.result == "fail"){
 						loading("处理失败,请稍后重试...",4);
 					 }else if(obj.result == "isaudit_success"){
 						loading("提交客服审核成功...",2000);
 						setTimeout(function(){
 							window.location.href= basePath+"/user/userLoanApp/list.action";
 						},2000);
 					 }else if(obj.result == "repay_null"){
 						loading("没有找到还款对象...",4);
 						setTimeout(function(){
 							window.location.href= basePath+"/user/userLoanApp/list.action";
 						},2000);
 					 } 
 				 }
 			 },{"opid":opid});
 		},
		//正常还款  全部还款
		low_doRepayMentNormalAll:function(obj){
			var opid = $(obj).data("opid");
			$("#low_normal").removeAttr("onclick").text("处理中...");
  			$.tzAjax.request({
				model:"borrowingUser/repayMent",
				method:"doManualRepayMentNormal.action",
				error:function(){$("#low_normal").attr("onclick","userloanApp.low_doRepayMentNormalAll(this)").text("全部还款");loading("网络异常...",4);},
				callback:function(data){
					$("#low_normal").attr("onclick","userloanApp.low_doRepayMentNormalAll(this)").text("全部还款");
					$("#myRepayModal").modal("hide");
					var obj = $.parseJSON(data);
					if(obj.result == "fail"){
						loading("处理失败",4);
					}else if(obj.result == "success"){
						loading("还款成功",4);
						setTimeout(function(){
							loanApp.low_conditions();
						},2000);
					}else if(obj.result =="logout"){
						loading("session失效 请重新登录",4);
						setTimeout(function(){
 							window.location.href = basePath+"/user/tologin.action";
						},1000);
 					}else if(obj.result =="direp_null"){
						loading("借款计划对象为null",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="accountInfo2_null"){
						loading("第三方账号对象为null",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="AvlBal_null"){
						loading("余额查询失败",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="repay_null"){
						loading("没有找到可以进行的还款",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="balance_deficiency"){
						loading("账号可用余额不足",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="item_null"){
						loading("标的对象找不到",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="isaudit_success"){
						loading("提交客服审核成功",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result =="opid_null"){
						loading("非法参数",4);
						setTimeout(function(){
							userloanApp.low_conditions();
						},1000);
 					}else if(obj.result == "isaudit_fail"){
 						loading("已全部提交客服审核了...",4);
 						setTimeout(function(){
 							window.location.href= basePath+"/user/userLoanApp/list.action";
 						},2000);
 					 }
				}
			},{"opid":opid});
		},
		//正常还款 全选
  		low_allNormalRepaySelect:function(obj){
    		var selectRepay = $(".normalRepay").length;  
 			if(obj.checked){
  				 for(var i = 0;i<selectRepay;i++){
 					 $(".normalRepay")[i].checked =obj.checked;
				 }
			}else{
 				for(var i = 0;i<selectRepay;i++){
					 $(".normalRepay")[i].checked =obj.checked;
				 } 
			}
  		},
		//条件查询
		low_conditions:function(obj){
			$("#item-content").html("查询中,请稍后...");
			 var appstatus = $(obj).data("appstatus");			
  			 $(obj).parent().addClass("active").siblings().removeClass("active");
  			 var params ={"appstatus":appstatus};
  			 $.tzAjax.request({
  				model:"user/userLoanApp",
  				method:"listTemplate.action",
  				callback:function(data){
  	 				$("#item-content").html(data);
  				}
  			},params);
		},
		//提前还款 模块开始
 		//提前还款 选择期数提交
		low_aheadRepayMentSelectSubmit:function(obj){
 			var selectRepay = $(".allAheadRepaySelect").length;
  			var count = 0,opid = "",periods = "";
 			for(var i = 0;i<selectRepay;i++){
				if($(".allAheadRepaySelect")[i].checked){
 					count ++;
					opid += $(".allAheadRepaySelect").eq(i).data("opid")+",";
					periods += $(".allAheadRepaySelect").eq(i).data("periods")+",";
   				}
			}
 			if(count == 0){
   				alert("请至少选中一个还款");
   				return;
   			}
 			opid = opid.substring(0,opid.lastIndexOf(","));
 			periods = periods.substring(0,periods.lastIndexOf(","));
 			$("#aheadRepayMentSelectSubmit").text("提交处理中...").removeAttr("onclick");
  			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"aheadRepayMent.action",
 				error:function(){
 					$("#aheadRepayMentSelectSubmit").text("提前还款").attr("onclick","userloanApp.low_aheadRepayMentSelectSubmit(this)");
 				},
 				callback:function(data){
	  				$("#aheadRepayMentSelectSubmit").text("提前还款").attr("onclick","userloanApp.low_aheadRepayMentSelectSubmit(this)");
	  				$("#item-content").html(data);
   				}
 			},{"opid":opid,"periods":periods});
 		},
 		//提前还款 期数全选
 		low_allAheadRepaySelect:function(obj){
   			var selectRepay = $(".allAheadRepaySelect").length;  
 			if(obj.checked){
  				 for(var i = 0;i<selectRepay;i++){
 					 $(".allAheadRepaySelect")[i].checked =obj.checked;
				 }
			}else{
 				for(var i = 0;i<selectRepay;i++){
					 $(".allAheadRepaySelect")[i].checked =obj.checked;
				 } 
			}
  		},
 		//提前还款 部分提前还款选择
 		low_selectPartAheadRepayMent:function(obj){
  			$("#selectPartAheadRepayMent").text("提交处理中...").attr("onclick");
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"selectPartAheadRepayMent.action",
 				error:function(){
 					$("#selectPartAheadRepayMent").text("部分提前还款").attr("onclick","userloanApp.low_selectPartAheadRepayMent(this)");
 				},
 				callback:function(data){
 					$("#selectPartAheadRepayMent").text("部分提前还款").attr("onclick","userloanApp.low_selectPartAheadRepayMent(this)");
  					$("#myRepayModal").modal("hide");
 					$("#item-content").html(data);
 				}
 			});
 		},
 		//提前还款 全部提前还款选择(允许部分还款)
 		low_selectAllAheadRepayMent:function(obj){
    		$("#selectAllAheadRepayMent").text("提交处理中...").removeAttr("onclick");
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"selectAllAheadRepayMent.action",
 				error:function(){
 					$("#selectAllAheadRepayMent").text("全部提前还款").attr("onclick","userloanApp.low_selectAllAheadRepayMent(this)");
 				},
 				callback:function(data){
    				$("#selectAllAheadRepayMent").text("全部提前还款").attr("onclick","userloanApp.low_selectAllAheadRepayMent(this)");
    				$("#myRepayModal").modal("hide");
    				$("#item-content").html(data);	
 				}
 			});
  		},
  		//提前还款 全部提前还款选择 提交还款（允许部分还款）
  		low_selectAllAheadRepayMentTask:function(obj){
  			$("#selectAllAheadRepayMentTask").text("处理中...").removeAttr("onclick");
   			 $.tzAjax.request({
  				 model:"borrowingUser/repayMent",
  				 method:"selectAllAheadRepayMentTask.action",
  				 error:function(){$("#selectAllAheadRepayMentTask").text("确定提前还款").attr("onclick","userloanApp.low_selectAllAheadRepayMentTask(this)");},
  				 callback:function(data){
  					$("#selectAllAheadRepayMentTask").text("确定提前还款").attr("onclick","userloanApp.low_selectAllAheadRepayMentTask(this)");
   					var obj = $.parseJSON(data);
    					if(obj.result == "opid_null"){
 						alert("参数错误!请重新操作...");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "logout"){
 						alert("session失效!请重新操作...");
  						window.location.href = basePath + "/user/tologin.action";
  					}else if(obj.result == "allAheadRepay_partDays_isauditSuccess"){
 						alert("本次提前还款提交客服审核成功");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "AvlBal_error"){
 						alert("余额不足,请充值");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allAheadRepay_partDays_success"){
 						alert("本次提前还款成功");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allAheadRepay_partDays_fail"){
 						alert("本次提前还款失败");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allAheadRepay_allDays_success"){
 						alert("本次提前还款成功");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allAheadRepay_allDays_fail"){
 						alert("本次提前还款失败");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allAheadRepay_allDays_isauditSuccess"){
 						alert("本次提前还款提交客服审核成功，请等待客服审核通过");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allAheadRepay_allDays_isaudited"){
 						alert("已经提交客服审核了，请等待客服审核通过");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}
  				 }
  			 });
  		},
 		//提前还款部分选择 全选
 		low_AheadRepayPartSelet:function(obj){
  			var selectRepay = $(".AheadRepayPartSelet").length;  
 			if(obj.checked){
  				 for(var i = 0;i<selectRepay;i++){
 					 $(".AheadRepayPartSelet")[i].checked =obj.checked;
				 }
			}else{
 				for(var i = 0;i<selectRepay;i++){
					 $(".AheadRepayPartSelet")[i].checked =obj.checked;
				 } 
			}
 		},
 		//提前还款 部分选择后提交处理
 		low_AheadRepayPartSelectSubmit:function(obj){
 			var selectRepay = $(".AheadRepayPartSelet").length; 
 			var opid = "",count = 0;
			for(var i = 0;i<selectRepay;i++){
				 if($(".AheadRepayPartSelet")[i].checked){
					 opid += $(".AheadRepayPartSelet").eq(i).data("opid") + ",";
					 count ++;
				 }
			}
			if(count < 0){
				alert("请选择一个投资人进行还款操作");
				return false;
			}
			opid = opid.substring(0,opid.lastIndexOf(","));
 		    $.tzAjax.request({
		    	model:"huifu/huifuRepayMent",
		    	method:"partAheadAfter.action",
		    	callback:function(data){
		    		$("#item-content").html(data);
  		    	}
		    },{"opid":opid});
			 
 		},
 		
 		//部分提前还款 调用接口操作
 		low_aheadPartRepayMentTask:function(obj){
 			$("#aheadPartRepayMentTask").text("处理中...").removeAttr("onclick");
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",  
 				method:"aheadPartRepayMentTask.action",
 				error:function(){$("#aheadPartRepayMentTask").text("确定提前还款").attr("onclick","userloanApp.low_aheadPartRepayMentTask(this)");},
 				callback:function(data){
  					$("#aheadPartRepayMentTask").text("确定提前还款").attr("onclick","userloanApp.low_aheadPartRepayMentTask(this)");
 					var obj = $.parseJSON(data);
 					if(obj.result == "logout"){
 						loading("session 失效,请重新登录",4);
 						setTimeout(function(){
  							window.location.href = basePath + "/user/tologin.action";
 						},2000);
 					}else if(obj.result == "param_null"){
 						loading("session 失效,请重新登录",4);
 						setTimeout(function(){
 							window.location.href = basePath + "/user/tologin.action";
 						},2000);
 					}else if(obj.result == "AvlBal_error"){
 						alert("余额不足,请充值");
 					}else if(obj.result == "AheadRepay_jump"){
 						alert("不允许跳期还款");
 					}else if(obj.result == "perDiem_submit_serviceAudit_success"){
 						alert("提交客服审核成功！请等待客服审核通过");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if(obj.result == "alldays_isauditsuccess"){
 						alert("提交客服审核成功！请等待客服审核通过");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if(obj.result == "alldays_success"){
 						alert("提前还款成功");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if(obj.result == "partDayAheadRepay_success"){
 						alert("提前还款成功");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if(obj.result == "partDayAheadRepay_fail"){
 						alert("处理失败");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}
  				}
 			});
 		},
 		//提前还款 全部还款 调用接口（不允许部分还款）
 		low_aheadAllRepayMentTask:function(obj){
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"aheadAllRepayMentTask.action",
 				error:function(){},
 				callback:function(data){
 					var obj = $.parseJSON(data);
 					if(obj.result == "opid_null"){
 						alert("参数错误!请重新操作...");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "logout"){
 						alert("session失效!请重新操作...");
  						window.location.href = basePath + "/user/tologin.action";
  					}else if(obj.result == "allaheadrepay_alldays_success"){
 						alert("本次提前还款成功");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allaheadrepay_alldays_isauditSuccess"){
 						alert("本次提前还款提交客服审核成功,请等待客服审核通过");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allaheadrepay_partdays_isauditSuccess"){
 						alert("本次提前还款提交客服审核成功,请等待客服审核通过");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "AvlBal_error"){
 						alert("余额不足，请及时充值");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allaheadrepay_partdays_success"){
 						alert("本次提前还款成功");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "allaheadrepay_partdays_fail"){
 						alert("本次提前还款失败");
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}
 				}
 			});
 		},
 		//逾期还款
 		low_doOverdueRepayMentSelect:function(obj){
  			var opid = $(obj).data("opid");
   			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"doOverdueRepayMentSelect.action",
 				callback:function(data){
  					var obj = $.parseJSON(data);
  					if(obj.result =="apartrepay"){
 						var html = "<button type='button' onclick='userloanApp.low_doOverdueRepayMentPartSelect(this)' data-opid='"+opid+"'"+
						"class='btn btn-primary' id='low_partSelect'>部分逾期还款</button>"+
						"<button type='button' onclick='userloanApp.low_doOverdueRepayMentNormalAll(this)' data-opid='"+opid+"'"+
						"class='btn btn-primary' id='low_normal'>全部逾期还款</button>";
 						$("#myRepayModal_body").html(html);
 						$("#myRepayModal").modal();
 					}else if(obj.result == "repayMentSize_error"){
 						alert("没有找到具体的还款计划");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "fail"){
 						alert("操作失败");
 						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "success"){
 						var html2 = ""; 
   						$.each(JSON.parse(obj.list),function(index,content){
   						    var  html3 =  "<tr>"+
									   "	 <th>"+content.username+"</th>"+
									   "	 <th>"+content.ramount+"</th>"+
									   "	 <th>"+content.rinterest+"</th>"+
									   "	 <th>"+content.rvoucher+"</th>"+
									   "	 <th>"+content.rvoucherint+"</th>"+
									   "	 <th>"+content.overdueAmount+"</th>"+
									   "	 <th>"+content.count+"</th>"+
									   " </tr>";
   						    html2 += html3;
 						});
   						var html = "<table class='table table-hover'>"+
						   "   		<thead>"+
						   "   			<tr>"+
						   "  				<th>用户名</th>"+
						   "  				<th>本金</th>"+
						   "  				<th>本金利息</th>"+
						   "  				<th>类现金</th>"+
						   "   				<th>类现金利息</th>"+
						   "  				<th>逾期滞纳金</th>"+
						   "  				<th>合计</th>"+
						   "    		</tr>"+
						   "  		</thead>"+
						   "  		<tbody>"+
						   "			"+html2+""+
						   "   		</tbody>"+
						   "</table>"+
						   "<span>合计："+obj.count+"</span><br /><br />"+
						   "<button type='button' class='btn btn-primary' onclick='userloanApp.low_OverdueRepayMentTask(this)'>逾期还款</button>";
   						  $("#item-content").html(html);
  					}
 				}
 			},{"opid":opid});
 		},
 		//部分逾期还款（选择）
 		low_doOverdueRepayMentPartSelect:function(obj){
 			$("#myRepayModal").modal("hide");
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"doOverdueRepayMentPartSelect.action",
 				callback:function(data){
  					var obj = $.parseJSON(data);
 					if(obj.result == "param_error"){
 						alert("参数错误...");
 						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}else if(obj.result == "success"){
  						var html2 = ""; 
   						$.each(JSON.parse(obj.list),function(index,content){
   						    var  html3 =  "<tr>"+
   						    		   "	 <th><input type = 'checkbox' class='' data-orde='"+content.rorderno+"' /></th>"+
									   "	 <th>"+content.username+"</th>"+
									   "	 <th>"+content.ramount+"</th>"+
									   "	 <th>"+content.rinterest+"</th>"+
									   "	 <th>"+content.rvoucher+"</th>"+
									   "	 <th>"+content.rvoucherint+"</th>"+
									   "	 <th>"+content.overdueAmount+"</th>"+
									   "	 <th>"+content.count+"</th>"+
									   " </tr>";
   						    html2 += html3;
 						});
   						var html = "<table class='table table-hover'>"+
						   "   		<thead>"+
						   "   			<tr>"+
						   "  				<th>选择</th>"+
						   "  				<th>用户名</th>"+
						   "  				<th>本金</th>"+
						   "  				<th>本金利息</th>"+
						   "  				<th>类现金</th>"+
						   "   				<th>类现金利息</th>"+
						   "  				<th>逾期滞纳金</th>"+
						   "  				<th>合计</th>"+
						   "    		</tr>"+
						   "  		</thead>"+
						   "  		<tbody>"+
						   "			"+html2+""+
						   "   		</tbody>"+
						   "</table>"+
						   "&nbsp;&nbsp;<input type='checkbox' id='overdue_allsel'/> 全选 &nbsp;&nbsp;&nbsp;&nbsp;"+
 						   "<button type='button' class='btn btn-primary' onclick='userloanApp.low_doOverdueRepayMentPartSelectTask(this)'>逾期还款</button>";
   						  $("#item-content").html(html);
 					}
  				}
 			});
 		},
 		//全部逾期还款（选择）
 		low_doOverdueRepayMentNormalAll:function(obj){
 			$("#myRepayModal").modal("hide");
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"doOverdueRepayMentNormalAll.action",
 				callback:function(data){
  					var obj = $.parseJSON(data);
 					if(obj.result == "param_error"){
 						alert("参数错误...");
 						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}else if(obj.result == "success"){
 						var htmlStr = "";
 						$.each(JSON.parse(obj.list),function(index,content){
   						    var  html3 =  "<tr>"+
									   "	 <th>"+content.username+"</th>"+
									   "	 <th>"+content.ramount+"</th>"+
									   "	 <th>"+content.rinterest+"</th>"+
									   "	 <th>"+content.rvoucher+"</th>"+
									   "	 <th>"+content.rvoucherint+"</th>"+
									   "	 <th>"+content.overdueAmount+"</th>"+
									   "	 <th>"+content.count+"</th>"+
									   " </tr>";
   						    htmlStr += html3;
 						});
   						var htmlStr2 = "<table class='table table-hover'>"+
						   "   		<thead>"+
						   "   			<tr>"+
						   "  				<th>用户名</th>"+
						   "  				<th>本金</th>"+
						   "  				<th>本金利息</th>"+
						   "  				<th>类现金</th>"+
						   "   				<th>类现金利息</th>"+
						   "  				<th>逾期滞纳金</th>"+
						   "  				<th>合计</th>"+
						   "    		</tr>"+
						   "  		</thead>"+
						   "  		<tbody>"+
						   "			"+htmlStr+""+
						   "   		</tbody>"+
						   "</table>"+
						   "<span>合计："+obj.count+"</span><br /><br />"+
						   "<button type='button' class='btn btn-primary' onclick='userloanApp.low_doOverdueRepayMentNormalAllTask(this)'>逾期还款</button>";
   						  $("#item-content").html(htmlStr2);
 					}
 				}
 			});
 		},
 		//逾期还款提交(不允许部分逾期还款)
 		low_OverdueRepayMentTask:function(obj){
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"OverdueRepayMentTask.action",
 				callback:function(data){
 					alert(data);
 				}
 			});
 		},
 		//逾期还款 部分选择提交(允许部分逾期还款)
 		low_doOverdueRepayMentPartSelectTask:function(obj){
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"doOverdueRepayMentPartSelectTask.action",
 				callback:function(data){
 					
 				}
 			});
 		},
 		//逾期还款 全部选择提交(允许部分逾期还款)
 		low_doOverdueRepayMentNormalAllTask:function(obj){
 			$.tzAjax.request({
 				model:"borrowingUser/repayMent",
 				method:"doOverdueRepayMentNormalAllTask.action",
 				callback:function(data){
 					
 				}
 			});
 		},
 		selectPeriodsCheck:function(obj){
   			var allAheadRepaySelectLength = $(".allAheadRepaySelect").length;
   			if($(".allAheadRepaySelect").eq(0).is(":checked")){
    				var firstPeriod = $(".allAheadRepaySelect").eq(0).data("periods");
   			}else{
   				alert("请");
   				return ;
   			}
  			if(isEmpty(firstPeriod)){
  				this.allSelectEmpty();
 				return ;
 			}
  			var period = "" ;
   			for(var i = 1;i < allAheadRepaySelectLength ; i++){
  				if($(".allAheadRepaySelect").eq(i).is(":checked")){
   					period += $(".allAheadRepaySelect").eq(i).data("periods") + ",";
  				}
 			}
  			period = period.substring(period,period.lastIndexOf(","));
   			var ayysObj = new Array();
  			ayysObj = period.split(",");
   		},
 		//清空全部
 		allSelectEmpty:function(){
   			var allAheadRepaySelectLength = $(".allAheadRepaySelect").length;
 			for(var i = 0;i < allAheadRepaySelectLength ; i++){
 				if($(".allAheadRepaySelect").eq(i).is(":checked")){
  					$(".allAheadRepaySelect")[i].checked = "";
 				}
  			}
 		}
   }

	//分页查询通用方法
	function queryAllPerson(pageNo,pageSize){
		$("#item-content").html("数据正在加载中.....");
		var appstatus = $("#box_going_repay").find(".active a").data("appstatus");
 		var params = {"appstatus":appstatus};
		params.pageSize = "20";
		params.pageNo = pageNo;
		$.tzAjax.request({
			model:"user/userLoanApp",
			method:"listTemplate.action",
			callback:function(data){
 				$("#item-content").html(data);
			}
		},params);
  	} 

 