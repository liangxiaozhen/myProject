var userloanApp = (function(){
	return {
		low_paymentSel:function(obj){
 			var status = $(obj).data("status");
 			 $.ajax({
  				 type:"post",
  				 url:basePath + "/user/userLoanApp/list.action",
  				 data:{"status":status},
  				 error:function(){},
  				 success:function(data){
  					 if(data =="logout"){
  						logout();
  					 }
     				$("body").html(data);
  				 }
   			 });
		},
  		//显示标的信息
  		low_showAllTendItemDetail:function(obj){
  			var opid = $(obj).data("opid");
  			 if(isEmpty(opid)){
  				 return ;
  			 }
   			 $.ajax({
  				 type:"post",
  				 url:basePath + "/user/userLoanApp/showAllTendItemDetail.action",
  				 data:{"opid":opid},
  				 error:function(){},
  				 success:function(data){
  					if(data =="logout"){
  						logout();
  					 }
     				$("#repayMent_box1").html(data);
  				 }
   			 });
   		},
   		
  		//显示标的具体还款计划详情
  		low_showRepaymentPlanDateil:function(obj){
  			var opid = $(obj).data("opid");
    		if(isEmpty(opid)){
 				 return ;
 			 }
    		 $.ajax({
  				 type:"post",
  				 url:basePath + "/user/userLoanApp/showRepaymentPlanDateil.action",
  				 data:{"opid":opid},
  				 error:function(){},
  				 success:function(data){
  					if(data =="logout"){
  						logout();
  					 }
  					$("#detailCallBack").show();
     				$("#repayMent_box1").html(data);
  				 }
   			 });
   		},
  		
 		//正常还款 选择部分还款或者全部还款
		low_doRepayMentSelect:function(obj){
 			var opid = $(obj).data("opid");
 			var token = $(obj).data("token");
			if(isEmpty(opid)){
 				return ;
			}
			$("#apartnormalRepay").attr("data-opid",opid);
			$("#doRepayMentNormalAll").attr("data-opid",opid);
			$("#doRepayMentNormalAll").attr("data-token",token);
 			$("#normalRepaySelect").show();
			center("#normalRepaySelect");
			$(".bg").show();
 		},
 		
 		//正常还款 部分还款  显示投资人
 		low_doRepayMentPartSelect:function(obj){
 			userloanApp.normalRepaySelectColoe();
 			var opid = $(obj).data("opid");
   			 $.ajax({
  				 type:"post",
  				 url:basePath + "/user/userLoanApp/doRepayMentPartSelect.action",
  				 data:{"opid":opid},
  				 error:function(){},
  				 success:function(data){
  					if(data =="logout"){
  						logout();
  					 }
     				$("#repayMent_box1").html(data);
  				 }
   			 });
  		},
  		
 		//正常还款  部分还款 选择部分投资人后提交
 		low_doRepayMentNormalPart:function(obj){
 			 var normalSelect = $(".normalRepay").length;
 			 var token = $("#doRepayMentNormalPart").data("token");
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
 			 $("#doRepayMentNormalPart").text("处理中...").removeAttr("onclick");
 			 $.ajax({
  				 type:"post",
  				 url:basePath + "/borrowingUser/repayMent/doNormalRepayMentPart.action",
  				 data:{"opid":opid,"token":token},
  				 error:function(){$("#doRepayMentNormalPart").attr("onclick","userloanApp.low_doRepayMentNormalPart(this)").text("立即还款");},
  				 success:function(data){
  					 $("#doRepayMentNormalPart").attr("onclick","userloanApp.low_doRepayMentNormalPart(this)").text("立即还款");
  					 $("#contentErrorID").text("").hide();
  					 var obj = $.parseJSON(data);
					 if(obj.result == "logout"){
						 logout("contentErrorID");
					 }else if(obj.result == "params_error"){
						 showErrorMessage("contentErrorID",obj.message);
						 window.location.href= basePath+"/user/userLoanApp/list.action";
					 }else if(obj.result == "success"){
						showErrorMessage("contentErrorID",obj.message);
 						window.location.href= basePath+"/user/userLoanApp/list.action";
 					 } 
  				 }
   			 });
  		},
  		
		//正常还款  全部还款
		low_doRepayMentNormalAll:function(obj){
 			var opid  = $(obj).data("opid");
 			var token = $(obj).data("token");
  			if(isEmpty(token)){
 				alert("参数错误");
 				return;
 			}
			$("#doRepayMentNormalAll").removeAttr("onclick").text("处理中...");
			$.ajax({
				type:"post",
				url:basePath + "/borrowingUser/repayMent/doManualRepayMentNormal.action",
				data:{"opid":opid,"token":token},
				error:function(){$("#doRepayMentNormalAll").attr("onclick","userloanApp.low_doRepayMentNormalAll(this)").text("全部还款");showErrorMessage("contentErrorID","网络异常...");},
				success:function(data){
  					$("#doRepayMentNormalAll").attr("onclick","userloanApp.low_doRepayMentNormalAll(this)").text("全部还款");
					$("#contentErrorID").text("").hide();
 					var obj = $.parseJSON(data);
					if(obj.result == "params_error"){
						showErrorMessage("contentErrorID",obj.message);
						window.location.href= basePath+"/user/userLoanApp/list.action";
					}else if(obj.result == "success"){
						showErrorMessage("contentErrorID",obj.message);
						userloanApp.normalRepaySelectColoe();
						window.location.href= basePath+"/user/userLoanApp/list.action";
					}else if(obj.result =="logout"){
 						logout("contentErrorID");
 					} 
				}
				
			});
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
  			$.ajax({
 				type:"post",
 				url:basePath + "/borrowingUser/repayMent/aheadRepayMent.action",
 				error:function(){$("#aheadRepayMentSelectSubmit").text("提前还款").attr("onclick","userloanApp.low_aheadRepayMentSelectSubmit(this)");},
 				data:{"opid":opid,"periods":periods},
 				success:function(data){
   					$("#aheadRepayMentSelectSubmit").text("提前还款").attr("onclick","userloanApp.low_aheadRepayMentSelectSubmit(this)");
 					var obj = $.parseJSON(data);
 					if(obj.result == "logout"){
 						 logout("contentErrorID");
 					}else if(obj.result == "params_error"){
 						showErrorMessage("contentErrorID",obj.message);
 						window.location.href= basePath+"/user/userLoanApp/list.action";
 					}else if(obj.result == "apartSelet_success"){
  						$(".bg").show();
 						$("#aheadRepaySelect").show();
 						center("#aheadRepaySelect");
 					}else if(obj.result == "allAheadApartInterest_success"){
 						 var allAheadApartInterestHtml2 = "";
 						 $.each(JSON.parse(obj.list),function(index,content){
 							allAheadApartInterestHtml2 +="<tr class='table fc_6 mar_t5 bor_t'>"+
				 							"	   				<th class='fc_3'>"+content.username+"</th>"+
				 							"	   				<th class='fc_3'>"+content.ramount+"</th>"+
				 							"	   				<th class='fc_3'>"+content.rinterest+"</th>"+
				 							"	    			<th class='fc_3'>"+content.holdrinterest+"</th>"+
				 							"	   				<th class='fc_3'>"+content.harvestfine+"</th>"+
				 							"	     	</tr>";
  						 });
 						 var allAheadApartInterestHtml = "<table class='table fc_6 mar_t5 bor_t'>"+
							 						"   		<thead>"+
							 						"   			<tr class='table fc_6 mar_t5 bor_t'>"+
							 						"   				<th class='fc_3'>用户名</th>"+
							 						"   				<th class='fc_3'>本金</th>"+
							 						"   				<th class='fc_3'>原应得利息</th>"+
							 						"    			    <th class='fc_3'>持有利息</th>"+
							 						"   				<th class='fc_3'>欠收利息罚金</th>"+
							 						"     		</tr>"+
							 						"   		</thead>"+
							 						"   		"+
							 						"   		<tbody>"+
							 					    "					"+allAheadApartInterestHtml2+""+
							 						"   		</tbody>"+
							 						"</table>"+
							 						"<span>合计："+obj.count+"</span><br /><br />"+
							 						"<button type='button' class='btn btn_bgf60 btn_size100' onclick='userloanApp.low_aheadAllRepayMentTask(this)' data-token='"+obj.token+"'>确定提前还款</button>"
							$("#repayMent_box1").html(allAheadApartInterestHtml); 						
 					 }else if(obj.result == "allAheadAllInterest_success"){
 						 var allAheadAllInterest2 = "";
 						 $.each(JSON.parse(obj.list),function(index,content){
 							allAheadAllInterest2 +="<tr class='table fc_6 mar_t5 bor_t'>"+
		 	 						"	   				<th class='fc_3'>"+content.username+"</th>"+
		 	 						"	   				<th class='fc_3'>"+content.ramount+"</th>"+
		 	 						"	   				<th class='fc_3'>"+content.rinterest+"</th>"+
		 	 						"	    			<th class='fc_3'>"+content.holdrinterest+"</th>"+
		 	 						"	   				<th class='fc_3'>"+content.harvestfine+"</th>"+
		 	 						"	     		</tr>";
 						 });
 						 
 						 var allAheadAllInterest = "<table class='table fc_6 mar_t5 bor_t'>"+
							 						"   		<thead>"+
							 						"   			<tr class='table fc_6 mar_t5 bor_t'>"+
							 						"   				<th class='fc_3'>用户名</th>"+
							 						"   				<th class='fc_3'>本金</th>"+
							 						"   				<th class='fc_3'>原应得利息</th>"+
							 						"    				<th class='fc_3'>持有利息</th>"+
							 						"   				<th class='fc_3'>欠收利息罚金</th>"+
							 						"     		</tr>"+
							 						"   		</thead>"+
							 						"   		"+
							 						"   		<tbody>"+
							 						"						"+allAheadAllInterest2+""+
							 						"   		</tbody>"+
							 						"</table>"+
							 						"<span>合计："+obj.count+"</span><br /><br />"+
							 						"<button type='button' class='btn btn_bgf60 btn_size100' onclick='userloanApp.low_aheadAllRepayMentTask(this)' data-token = '"+obj.token+"'>确定提前还款</button>";
 						$("#repayMent_box1").html(allAheadAllInterest); 	
  					 }
  				}
 			});
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
 			$("#apartAheadRepay").removeAttr("onclick").text("处理中...");
   			$.ajax({
  				type:"post",
  				url:basePath + "/borrowingUser/repayMent/selectPartAheadRepayMent.action",
  				error:function(){$("#apartAheadRepay").attr("onclick","userloanApp.low_selectPartAheadRepayMent(this)").text("部分还款");userloanApp.aheadRepaySelectColoe();},
  				success:function(data){
  					if(data =="logout"){
  						logout();
  					 }
   					$("#apartAheadRepay").attr("onclick","userloanApp.low_selectPartAheadRepayMent(this)").text("部分还款");
  					userloanApp.aheadRepaySelectColoe();
  					$("#repayMent_box1").html(data);
  				}
  			});
  		},
  		
 		//提前还款 全部提前还款选择(允许部分还款)
 		low_selectAllAheadRepayMent:function(obj){
 			userloanApp.aheadRepaySelectColoe();
    		$.ajax({
    			type:"post",
    			url:basePath + "/borrowingUser/repayMent/selectAllAheadRepayMent.action",
    			error:function(){},
    			success:function(data){
    				if(data =="logout"){
  						logout();
  					 }
     				$("#repayMent_box1").html(data);
    			}
    		});
   		},
   		
  		//提前还款 全部提前还款选择 提交还款（允许部分还款）
  		low_selectAllAheadRepayMentTask:function(obj){
  			var token = $(obj).data("token");
  			if(isEmpty(token)){ 
  				alert("参数错误！请重新操作或联系客服");
  				return ;
  			}
   			$.ajax({
  				type:"post",
  				url:basePath + "/borrowingUser/repayMent/selectAllAheadRepayMentTask.action",
  				data:{"token":token},
   				success:function(data){
   					if(data =="logout"){
  						logout();
  					 }
    				var obj = $.parseJSON(data);
    				if(obj.result == "params_error"){
 						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "logout"){
 						alert(obj.message);
  						window.location.href = basePath + "/user/tologin.action";
  					}else if(obj.result == "success"){
  						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "fail"){
  						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					} 
  				 }
   			});
   		},
   		
 		//提前还款部分选择 全选
 		low_AheadRepayPartSelet:function(obj){
  			var selectRepay = $(".AheadRepayPartSeletUtorderno").length;  
 			if(obj.checked){
  				 for(var i = 0;i<selectRepay;i++){
 					 $(".AheadRepayPartSeletUtorderno")[i].checked =obj.checked;
				 }
			}else{
 				for(var i = 0;i<selectRepay;i++){
					 $(".AheadRepayPartSeletUtorderno")[i].checked =obj.checked;
				 } 
			}
 		},
 		
 		//提前还款 部分选择后提交处理
 		low_AheadRepayPartSelectSubmit:function(obj){
 			var selectRepay = $(".AheadRepayPartSeletUtorderno").length;
  			var opid = "",count = 0;
			for(var i = 0;i<selectRepay;i++){
				 if($(".AheadRepayPartSeletUtorderno")[i].checked){
 					 opid += $(".AheadRepayPartSeletUtorderno").eq(i).val() + ",";
 					 count ++;
				 }
			}
			if(count < 0){
				alert("请选择一个投资人进行还款操作");
				return false;
			}
 			opid = opid.substring(0,opid.lastIndexOf(","));
			$.ajax({
				type:"post",
				url:basePath + "/borrowingUser/repayMent/partAheadAfter.action",
				data:{"opid":opid},
				success:function(data){
					if(data =="logout"){
  						logout();
  					 }
 					$("#repayMent_box1").html(data);
				}
			});
   		},
 		
 		//部分提前还款 调用接口操作
 		low_aheadPartRepayMentTask:function(obj){
 			var token = $(obj).data("token");
 			if(isEmpty(token)){
 				alert("参数错误");
 				return ;
 			}
 			$("#aheadPartRepayMentTask").text("处理中...").removeAttr("onclick");
 			$.ajax({
 				type:"post",
 				url:basePath + "/borrowingUser/repayMent/aheadPartRepayMentTask.action",
 				data:{"token":token},
 				error:function(){$("#aheadPartRepayMentTask").text("确定提前还款").attr("onclick","userloanApp.low_aheadPartRepayMentTask(this)");},
 				success:function(data){
 					$("#aheadPartRepayMentTask").text("确定提前还款").attr("onclick","userloanApp.low_aheadPartRepayMentTask(this)");
 					var obj = $.parseJSON(data);
 					if(obj.result == "logout"){
 						alert(obj.message); 
 						setTimeout(function(){
  							window.location.href = basePath + "/user/tologin.action";
 						},2000);
 					}else if(obj.result == "params_error"){
 						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "success"){
 						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "fail"){
 						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}   
 				}
 			});
  		},
  		
 		//提前还款 全部还款 调用接口（不允许部分还款）
 		low_aheadAllRepayMentTask:function(obj){
 			var token = $(obj).data("token");
 			if(isEmpty(token)){ 
  				alert("参数错误！请重新操作或联系客服");
  				return ;
  			}
  			$.ajax({
 				type:"post",
 				url:basePath + "/borrowingUser/repayMent/aheadAllRepayMentTask.action",
 				data:{"token":token},
 				error:function(){},
 				success:function(data){
 					var obj = $.parseJSON(data);
 					if(obj.result == "params_error"){
 						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "logout"){
  						alert(obj.message);
  						window.location.href = basePath + "/user/tologin.action";
  					}else if(obj.result == "success"){
  						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "fail"){
  						alert(obj.message);
  						window.location.href = basePath + "/user/userLoanApp/list.action";
  					} 
 				}
 			});
  		},
  		
 		//逾期还款
 		low_doOverdueRepayMentSelect:function(obj){
  			var opid = $(obj).data("opid");
  			if(isEmpty(opid)){
  				alert("操作失败！参数错误！请重新操作或联系客服！");
  				return ;
  			}
  			$.ajax({
  				type:"post",
  				url:basePath + "/borrowingUser/repayMent/doOverdueRepayMentSelect.action",
  				error:function(){},
  				data:{"opid":opid},
  				success:function(data){
   					var obj = $.parseJSON(data);
  					if(obj.result =="apartrepay"){
   						$("#overdueRepaySelect").show();
    					center("#overdueRepaySelect");
   						$(".bg").show();
   					}else if(obj.result == "param_error"){
 						alert(obj.message);
 						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "fail"){
 						alert(obj.message);
 						window.location.href = basePath + "/user/userLoanApp/list.action";
  					}else if(obj.result == "success"){
 						var html2 = ""; 
   						$.each(JSON.parse(obj.list),function(index,content){
   						      html2 +=  "<tr class='table fc_6 mar_t5 bor_t'>"+
									   "	 <th class='fc_3'>"+content.username+"</th>"+
									   "	 <th class='fc_3'>"+content.ramount+"</th>"+
									   "	 <th class='fc_3'>"+content.rinterest+"</th>"+
  									   "	 <th class='fc_3'>"+content.overdueAmount+"</th>"+
									   "	 <th class='fc_3'>"+content.count+"</th>"+
									   " </tr>";
  						});
   						var html = "<table class='table fc_6 mar_t5 bor_t'>"+
						   "   		<thead>"+
						   "   			<tr class='table fc_6 mar_t5 bor_t'>"+
						   "  				<th class='fc_3'>用户名</th>"+
						   "  				<th class='fc_3'>本金</th>"+
						   "  				<th class='fc_3'>本金利息</th>"+
  						   "  				<th class='fc_3'>逾期滞纳金</th>"+
						   "  				<th class='fc_3'>合计</th>"+
						   "    		</tr>"+
						   "  		</thead>"+
						   "  		<tbody>"+
						   "			"+html2+""+
						   "   		</tbody>"+
						   "</table>"+
						   "<span class='fc_3'>合计："+obj.count+"</span><br /><br />"+
						   "<button type='button' class='btn btn_bgf60 btn_size100' onclick='userloanApp.low_OverdueRepayMentTask(this)' data-token = '"+obj.token+"'>逾期还款</button>";
   						  $("#repayMent_box1").html(html);
  					}
   				}
  			});
  		},
  		
 		//部分逾期还款（选择）
 		low_doOverdueRepayMentPartSelect:function(obj){
 			userloanApp.overdueRepaySelectColoe();
 			$.ajax({
 				type:"post",
 				url:basePath + "/borrowingUser/repayMent/doOverdueRepayMentPartSelect.action",
 				success:function(data){
 					var obj = $.parseJSON(data);
 					if(obj.result == "params_error"){
 						alert(obj.message);
 						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}else if(obj.result == "success"){
  						var html2 = ""; 
   						$.each(JSON.parse(obj.list),function(index,content){
   							html2 +=  "<tr class='table fc_6 mar_t5 bor_t'>"+
   						    		   "	 <th class='fc_3'><input type = 'checkbox' class='overduerorderno' value='"+content.rorderno+"' /></th>"+
									   "	 <th class='fc_3'>"+content.username+"</th>"+
									   "	 <th class='fc_3'>"+content.ramount+"</th>"+
									   "	 <th class='fc_3'>"+content.rinterest+"</th>"+
									   "	 <th class='fc_3'>"+content.rvoucher+"</th>"+
									   "	 <th class='fc_3'>"+content.rvoucherint+"</th>"+
									   "	 <th class='fc_3'>"+content.overdueAmount+"</th>"+
									   "	 <th class='fc_3'>"+content.count+"</th>"+
									   " </tr>";
  						});
   						var html = "<table class='table fc_6 mar_t5 bor_t'>"+
						   "   		<thead>"+
						   "   			<tr class='table fc_6 mar_t5 bor_t'>"+
						   "  				<th class='fc_3'>选择</th>"+
						   "  				<th class='fc_3'>用户名</th>"+
						   "  				<th class='fc_3'>本金</th>"+
						   "  				<th class='fc_3'>本金利息</th>"+
						   "  				<th class='fc_3'>类现金</th>"+
						   "   				<th class='fc_3'>类现金利息</th>"+
						   "  				<th class='fc_3'>逾期滞纳金</th>"+
						   "  				<th class='fc_3'>合计</th>"+
						   "    		</tr>"+
						   "  		</thead>"+
						   "  		<tbody>"+
						   "			"+html2+""+
						   "   		</tbody>"+
						   "</table>"+
						   "&nbsp;&nbsp;<input type='checkbox' id='overdue_allsel' onclick='userloanApp.low_partOverdueRepaySelect(this)'/> 全选 &nbsp;&nbsp;&nbsp;&nbsp;"+
 						   "<button type='button' class='btn btn_bgf60 btn_size100' onclick='userloanApp.low_doOverdueRepayMentPartSelectTask(this)' data-token = '"+obj.token+"'>逾期还款</button>";
   						  $("#repayMent_box1").html(html);
 					}
 				}
 			});
   		},
   		
 		//全部逾期还款（选择）
 		low_doOverdueRepayMentNormalAll:function(obj){
 			userloanApp.overdueRepaySelectColoe();
 			$.ajax({
 				type:"post",
 				url:basePath + "/borrowingUser/repayMent/doOverdueRepayMentNormalAll.action",
 				success:function(data){
 					var obj = $.parseJSON(data);
 					if(obj.result == "param_error"){
 						alert(obj.message);
 						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}else if(obj.result == "success"){
 						var htmlStr = "";
 						$.each(JSON.parse(obj.list),function(index,content){
 							htmlStr +=  "<tr class='table fc_6 mar_t5 bor_t'>"+
									   "	 <th class='fc_3'>"+content.username+"</th>"+
									   "	 <th class='fc_3'>"+content.ramount+"</th>"+
									   "	 <th class='fc_3'>"+content.rinterest+"</th>"+
  									   "	 <th class='fc_3'>"+content.overdueAmount+"</th>"+
									   "	 <th class='fc_3'>"+content.count+"</th>"+
									   " </tr>";
  						});
   						var htmlStr2 = "<table class='table fc_6 mar_t5 bor_t'>"+
						   "   		<thead>"+
						   "   			<tr class='table fc_6 mar_t5 bor_t'>"+
						   "  				<th class='fc_3'>用户名</th>"+
						   "  				<th class='fc_3'>本金</th>"+
						   "  				<th class='fc_3'>本金利息</th>"+
 						   "  				<th class='fc_3'>逾期滞纳金</th>"+
						   "  				<th class='fc_3'>合计</th>"+
						   "    		</tr>"+
						   "  		</thead>"+
						   "  		<tbody>"+
						   "			"+htmlStr+""+
						   "   		</tbody>"+
						   "</table>"+
						   "<span class='fc_3'>合计："+obj.count+"</span><br /><br />"+
						   "<button type='button' class='btn btn_bgf60 btn_size100' onclick='userloanApp.low_doOverdueRepayMentNormalAllTask(this)' data-token='"+obj.token+"'>逾期还款</button>";
   						  $("#repayMent_box1").html(htmlStr2);
 					}
 				}
 			}); 
  		},
  		//逾期还款 部分选择提交(允许部分逾期还款)
 		low_doOverdueRepayMentPartSelectTask:function(obj){
 			var token = $(obj).data("token");
 			if(isEmpty(token)){
 				alert("参数非法！请重新操作或联系客服");
 				return ;
 			}
 			var count = 0,rorderno = "";
 			var rordernoLength = $(".overduerorderno").length;
 			for(var i = 0 ; i < rordernoLength;i++){
 				if($(".overduerorderno")[i].checked){
 					 count ++;
 					rorderno += $(".overduerorderno")[i].value + ",";
 				}
 			}
 			if(!(count > 0)){
 				alert("至少选中一个投资人进行还款操作！");
 				return ;
 			}
 			rorderno = rorderno.substring(0, rorderno.lastIndexOf(","));
  			$.ajax({
 				type:"post",
 				data:{"token":token,"rorderno":rorderno},
 				url:basePath + "/borrowingUser/repayMent/doOverdueRepayMentPartSelectTask.action",
 				success:function(data){
  					var obj = $.parseJSON(data);
 					if(obj.result == "params_error"){
  						alert(obj.message);
  						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}else if(obj.result == "success"){
 						alert(obj.message);
 						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}else if(obj.result == "fail"){
 						alert(obj.message);
 						window.location.href = basePath +"/user/userLoanApp/list.action";
 					}
 				}
 			});
  		},
  		//逾期部分选择   全选
  		low_partOverdueRepaySelect:function(obj){
     		var selectRepay = $(".overduerorderno").length;  
 			if(obj.checked){
  				 for(var i = 0;i<selectRepay;i++){
 					 $(".overduerorderno")[i].checked =obj.checked;
				 }
			}else{
 				for(var i = 0;i<selectRepay;i++){
					 $(".overduerorderno")[i].checked =obj.checked;
				 } 
			}
  		},
  		
 		//逾期还款 全部选择提交(允许部分逾期还款)
 		low_doOverdueRepayMentNormalAllTask:function(obj){
 			var token = $(obj).data("token");
 			if(isEmpty(token)){
 				alert("提示：操作失败！参数错误！请重新操作或联系客服！");
 				return ;
 			}
  			$.ajax({
 				type:"post",
 				data:{"token":token},
 				url:basePath + "/borrowingUser/repayMent/doOverdueRepayMentNormalAllTask.action",
 				success:function(data){
  					var obj = $.parseJSON(data);
 					if(obj.result == "params_error"){
 						alert(obj.message);
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if (obj.result == "success"){
 						alert(obj.message);
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if(obj.result == "fail"){
 						alert(obj.message);
  						window.location.href= basePath+"/user/userLoanApp/list.action";
 					}
 				}
 			});
  		},
  		
  		//全部逾期还款提交(不允许部分逾期还款)
 		low_OverdueRepayMentTask:function(obj){
 			var token = $(obj).data("token");
 			if(isEmpty(token)){
 				alert("操作失败!参数错误！请重新操作或联系客服");
 				return ;
 			}
 			$.ajax({
 				type:"post",
 				data:{"token":token},
 				url:basePath + "/borrowingUser/repayMent/OverdueRepayMentTask.action",
 				success:function(data){
  					var obj = $.parseJSON(data);
 					if(obj.result == "params_error"){
 						alert(obj.message);
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if (obj.result == "success"){
 						alert(obj.message);
 						window.location.href = basePath + "/user/userLoanApp/list.action";
 					}else if(obj.result == "fail"){
 						alert(obj.message);
  						window.location.href= basePath+"/user/userLoanApp/list.action";
 					}
 				}
 			});
  		},
  		//清空全部
 		allSelectEmpty:function(){
   			var allAheadRepaySelectLength = $(".allAheadRepaySelect").length;
 			for(var i = 0;i < allAheadRepaySelectLength ; i++){
 				if($(".allAheadRepaySelect").eq(i).is(":checked")){
  					$(".allAheadRepaySelect")[i].checked = "";
 				}
  			}
 		},
 		
 		//关闭部分/全部逾期选择框
 		overdueRepaySelectColoe:function(obj){
 			$(".bg").hide();
 			$("#overdueRepaySelect").hide();
 		},
 		
 		//关闭部分/全部正常还款选择框
 		normalRepaySelectColoe:function(){
 			$(".bg").hide();
 			$("#normalRepaySelect").hide();
 		},
 		
 		//关闭部分/全部还款提前还款选择框
 		aheadRepaySelectColoe:function(){
 			$(".bg").hide();
 			$("#aheadRepaySelect").hide();
 		},
 		
 		low_conditions:function(){
 			window.location.href = basePath +"/user/userLoanApp/list.action";
 		}
 		
	};
})();

//错误提示
function showErrorMessage(contentID,message){
//	if(!isEmpty(contentID)){
//		$("#"+contentID+"").show().text(message);
//	 }
	alert(message);
}

 //居中
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
 
$(function(){
 	$(window).scroll(function(){
 		center("#normalRepaySelect");
 		center("#aheadRepaySelect");
 		center("#overdueRepaySelect");
	});
	
	$(window).resize(function(){
 		center("#normalRepaySelect");
 		center("#aheadRepaySelect");
 		center("#overdueRepaySelect");
	});
});
 
//重新登录
function logout(contentID){
	 if(!isEmpty(contentID)){
		$("#"+contentID+"").show().text("操作超时！请重新登录！");
	 }
	 setTimeout(function(){
 		 window.location.href = basePath + "/user/tologin.action";
	 },1000);
}

//空判断
function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}


//分页查询通用方法
function queryAllPerson(pageNo,pageSize){
	$("#repayMent_box1").html("数据正在加载中.....");
	var status = $("#low_paymentSel_status").val();
 	pageSize = "20";
    $.ajax({
    	type:"post",
    	url:basePath+"/user/userLoanApp/listTemplate.action",
    	data:{"pageNo":pageNo,"pageSize":pageSize,"status":status},
    	success:function(data){
     		$("#repayMent_box1").html(data);
    	}
    });
}

function queryNormalAllPerson(pageNo,pageSize,opid){
	$("#repayMent_box1").html("数据正在加载中.....");
    pageSize = "15";
    $.ajax({
    	type:"post",
    	url:basePath+"/user/userLoanApp/listNormalTemplate.action",
    	data:{"pageNo":pageNo,"pageSize":pageSize,"opid":opid},
    	success:function(data){
    		$("#repayMent_box1").html(data);
    	}
    });
}

 