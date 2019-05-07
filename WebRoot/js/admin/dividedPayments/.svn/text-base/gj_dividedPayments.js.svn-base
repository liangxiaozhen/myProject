$(function(){
	 $(".tzui-tips").tzTip();
});
   	var dividedPayments = {
 			//标的分期还款计划查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/dividedPayments/detail.action?opid="+opid
 				});
 			},
 			//查询全部
   			low_findAll:function(){
  				$("#findAll").text("查询中..");
  				$("body").load(basePath+"/admin/dividedPayments/list.action");
   			},
   			//重置
   			low_reset:function(){
   				$("#iscomplete").val("");
   		 		$("#isoverdue").val("");
   		 		$("#userBaseInfo_tno").val("");
   		 		$("#userBaseInfo_periods").val("");
   			},
   			//点击标号自动查询
   			bulrTno:function(obj){
   				var bulrTno = $(obj).data("tno");
   				$("#userBaseInfo_tno").val(bulrTno);
   				$.tzAdminAjax.request({
   		   	    	model:"admin/dividedPayments",
   		   	    	method:"template.action",
   		   	    	callback:function(data){
   		   	    		$("#queryall_list").html(data);
   		   	    	}
   		   	    },{"tno":bulrTno});
   			},
   			//设置线下还款
   			low_installOfflinePayment:function(obj){
    			$.tzConfirm({
   					title:"警告",
   					content :"<span style='color:red;'>您在设置线下还款！确定后本期将还款完成！请谨慎操作！</span>",
   					callback:function(ok){
   						if(ok){
    						var opid = $(obj).data("opid");
   							$.tzAdminAjax.request({
   			   		   	    	model:"admin/dividedPayments",
   			   		   	    	method:"installOfflinePayment.action",
    			   		   	    callback:function(data){
     			   		   	    	 var obj = $.parseJSON(data);
   			   		   	    		 if(obj.result == "fail"){
   			   		   	    			 alert(obj.Msg);
   			   		   	    		 }else if(obj.result == "success"){
   			   		   	    			 alert(obj.Msg);
    			   		   	    		 window.location.href = basePath + "/admin/dividedPayments/list.action";
   			   		   	    		 } 
   			   		   	    	}
   			   		   	    },{"opid":opid});
   						}
   					}
   				});
   			},
   			//正常代偿
   			low_normalCompensatory:function(obj){
   				var opid = $(obj).data("opid");
   				$.tzAdminAjax.request({
   		   	    	model:"admin/dividedPayments",
   		   	    	method:"normalCompensatory.action",
	   		   	    callback:function(data){
 		   		   	    	 var obj = $.parseJSON(data);
	   		   	    		 if(obj.result == "fail"){
	   		   	    			 alert(obj.Msg);
	   		   	    		 }else if(obj.result == "success"){
	   		   	    			 alert(obj.Msg);
	   		   	    			 window.location.href = basePath + "/admin/dividedPayments/list.action";
	   		   	    		 }else if(obj.result == "part"){
	   		   	    			 $("#normalCompensatoryPartRepayMentOpid").val(obj.opid);
	   		   	    			 $("#normalCompensatoryAllRepayMentOpid").val(obj.opid);
 	   		   	    			 $("#normalCompensatoryPart").modal(); 
	   		   	    		 }
	   		   	    	}
	   		   	    },{"opid":opid});
   			},
   			//部分代偿显示投资人
   			low_normalCompensatoryPartRepayMent:function(obj){
   				var opid = $("#normalCompensatoryPartRepayMentOpid").val();
   				$.tzAdminAjax.request({
   		   	    	model:"admin/dividedPayments",
   		   	    	method:"normalCompensatoryPartRepayMent.action",
	   		   	    callback:function(data){
  		   		   	    	 var obj = $.parseJSON(data);
 		   		   	    	 if(obj.result == "fail"){
 		   		   	    		 alert(obj.Msg);
 		   		   	    		 window.location.href = basePath + "/admin/dividedPayments/list.action";
 		   		   	    	 }else if(obj.result == "success"){
    		   		   	    	 var tobodyHtml = "";
     	   		   	    		 $.each(obj.detail.list,function(index,content){
 	   		   	    				  tobodyHtml += "<tr class='table fc_6 mar_t5 bor_t'>"+
		 	   						    		   "	 <th class='fc_3'><input type = 'checkbox' class='normalCompensatory' value='"+content.rorderno+"' /></th>"+
		 										   "	 <th class='fc_3'>"+content.intname+"</th>"+
		 										   "	 <th class='fc_3'>"+content.ramount+"</th>"+
		 										   "	 <th class='fc_3'>"+content.rinterest+"</th>"+
		 										   "	 <th class='fc_3'>"+content.rvoucher+"</th>"+
		 										   "	 <th class='fc_3'>"+content.rvoucherint+"</th>"+
		 										   "	 <th class='fc_3'>"+content.disablevoucher+"</th>"+
		 										   "	 <th class='fc_3'>"+content.disablevoucherint+"</th>"+
		 										   "	 <th class='fc_3'>"+content.count+"</th>"+
		 										   " </tr>";
	   		   	    			  });
 		   		   	    	
 		   		   	    		var normalComponentHtml =  "<table class='table table-hover'>"+
					 		   		   	 "	<thead>"+
						 		   		   "   			<tr class='table fc_6 mar_t5 bor_t'>"+
										   "  				<th class='fc_3'>选择</th>"+
										   "  				<th class='fc_3'>用户名</th>"+
										   "  				<th class='fc_3'>本金</th>"+
										   "  				<th class='fc_3'>本金利息</th>"+
										   "  				<th class='fc_3'>类现金</th>"+
										   "   				<th class='fc_3'>类现金利息</th>"+
										   "   				<th class='fc_3'>失效类现金</th>"+
										   "   				<th class='fc_3'>失效类现金利息</th>"+
  										   "  				<th class='fc_3'>合计</th>"+
										   "    		</tr>"+
					 		   		   	 "	</thead>"+
					 		   		   	 "	<tbody>"+
					 		   		   	 ""+tobodyHtml+""+
					 		   		   	 "	</tbody>"+
										 "</table>"+
										" <div id='page_div'>"+
										 ""+getPageHelper(obj.detail)+""+
										 "</div>"+
										 "&nbsp;&nbsp;<label><input type='checkbox' id='overdue_allsel' onclick='dividedPayments.low_normalCompensatorySelect(this)'/> 全选</label> &nbsp;&nbsp;&nbsp;&nbsp;"+
				 						   "<button type='button' class='btn btn_bgf60 btn_size100' onclick='dividedPayments.low_normalCompensatoryPartRepayMentTask(this)' data-opid='"+obj.opid+"' id='NormalComponentSubmit'>提交</button>";
  		   		   	    		$("#normalCompensatoryPart").modal("hide"); 
   		   		   	    		$("#queryall_list").html(normalComponentHtml);
 		   		   	    	 }
 	   		   	    	}
	   		   	    },{"opid":opid});
   			},
   			//部分代偿 提交
   			low_normalCompensatoryPartRepayMentTask:function(obj){
   				var opid = "",count = 0;
   				var rordernoLength = $(".normalCompensatory").length;
   	 			for(var i = 0 ; i < rordernoLength;i++){
   	 				if($(".normalCompensatory")[i].checked){
   	 					 count ++;
   	 					 opid += $(".normalCompensatory")[i].value + ",";
   	 				}
   	 			}
   				
	   	 		if(!(count > 0)){
	 				alert("至少选中一个投资人进行还款操作！");
	 				return ;
	 			}
	   	 		opid = opid.substring(0, opid.lastIndexOf(","));
	   	 		$.tzAdminAjax.request({
		   	    	model:"admin/dividedPayments",
		   	    	method:"normalCompensatoryPartRepayMentTask.action",
		   	    	callback:function(data){
		   		   	    	var obj = $.parseJSON(data);
		   		   	    	if(obj.result == "fail"){
		   		   	    		alert(obj.Msg);
		   		   	    		window.location.href = basePath + "/admin/dividedPayments/list.action";
		   		   	    	}else if(obj.result == "success"){
				   		   	  	alert(obj.Msg);
		   		   	    		window.location.href = basePath + "/admin/dividedPayments/list.action";
		   		   	    	}
		   		   	    	
	   		   	    }
   		   	    },{"opid":opid});
    		},
   			//全部代偿
   			low_normalCompensatoryAllRepayMent:function(obj){
   				var opid = $("#normalCompensatoryAllRepayMentOpid").val();
   				$.tzAdminAjax.request({
   		   	    	model:"admin/dividedPayments",
   		   	    	method:"normalCompensatoryAllRepayMentTask.action",
	   		   	    callback:function(data){
 		   		   	      var obj = $.parseJSON(data);
	 		   		   	  if(obj.result == "fail"){
		   		   	    		alert(obj.Msg);
		   		   	    		window.location.href = basePath + "/admin/dividedPayments/list.action";
		   		   	    	}else if(obj.result == "success"){
				   		   	  	alert(obj.Msg);
		   		   	    		window.location.href = basePath + "/admin/dividedPayments/list.action";
		   		   	    	}
 	   		   	    }
	   		   	  },{"opid":opid});
   			},
   			//部分代偿全选
   			low_normalCompensatorySelect:function(obj){
    			var lengthsd = $(".normalCompensatory").length;
   				if(obj.checked){
     				 for(var i = 0;i<lengthsd;i++){
    					 $(".normalCompensatory")[i].checked =obj.checked;
     				 }
	   			 }else{
	    				for(var i = 0;i<lengthsd;i++){
	   					 $(".normalCompensatory")[i].checked =obj.checked;
	   				 } 
	   			 }
   			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
   		$("#queryall_list").html("数据正在加载中.....");
   	    var params = getParams();
   	    params.pageSize = "20";
   	    params.pageNo = pageNo;
   	    $.tzAdminAjax.request({
   	    	model:"admin/dividedPayments",
   	    	method:"template.action",
   	    	callback:function(data){
   	    		$("#queryall_list").html(data);
   	    	}
   	    },params);
   	}
   //模糊搜索
 	function search(){
 		 $("#baseAndFsa_search").text("查询中..");
 		  var params = getParams();
 		  $.tzAdminAjax.request({
 			  model:"admin/dividedPayments",
 			  method:"template.action",
 			  callback:function(data){
 				 $("#baseAndFsa_search").text("查询");
 				 $("#queryall_list").html(data);
 			  }
 		  },params);
  	}
 	
 	function getParams(){
 		var iscomplete = $("#iscomplete").val();
 		var isoverdue = $("#isoverdue").val();
 		var tno = $("#userBaseInfo_tno").val();
  		var periods = $("#userBaseInfo_periods").val();
  		var params = {"iscomplete":iscomplete,"isoverdue":isoverdue,"tno":tno,"periods":periods};
 		return params;
  	}
 	
 	//分页
 	function queryAllPersonByNormalComponent(pageNo,pageSize){
     	pageSize = "20";
     	var opid = $("#NormalComponentSubmit").data("opid");
	    $.tzAdminAjax.request({
	    	model:"admin/dividedPayments",
	    	method:"normalComponentTemplate.action",
	    	callback:function(data){
	    		var obj = $.parseJSON(data);
	    		if(obj.result == "success"){
  		   	    	 var tobodyHtml = "";
		   	    		 $.each(obj.detail.list,function(index,content){
	   	    				  tobodyHtml += "<tr class='table fc_6 mar_t5 bor_t'>"+
   						    		   "	 <th class='fc_3'><input type = 'checkbox' class='normalCompensatory' value='"+content.rorderno+"' /></th>"+
									   "	 <th class='fc_3'>"+content.intname+"</th>"+
									   "	 <th class='fc_3'>"+content.ramount+"</th>"+
									   "	 <th class='fc_3'>"+content.rinterest+"</th>"+
									   "	 <th class='fc_3'>"+content.rvoucher+"</th>"+
									   "	 <th class='fc_3'>"+content.rvoucherint+"</th>"+
									   "	 <th class='fc_3'>"+content.disablevoucher+"</th>"+
									   "	 <th class='fc_3'>"+content.disablevoucherint+"</th>"+
									   "	 <th class='fc_3'>"+content.count+"</th>"+
									   " </tr>";
	   	    			  });
		   	    	
		   	    		var normalComponentHtml =  "<table class='table table-hover'>"+
		 		   		   	 "	<thead>"+
			 		   		   "   			<tr class='table fc_6 mar_t5 bor_t'>"+
							   "  				<th class='fc_3'>选择</th>"+
							   "  				<th class='fc_3'>用户名</th>"+
							   "  				<th class='fc_3'>本金</th>"+
							   "  				<th class='fc_3'>本金利息</th>"+
							   "  				<th class='fc_3'>类现金</th>"+
							   "   				<th class='fc_3'>类现金利息</th>"+
							   "   				<th class='fc_3'>失效类现金</th>"+
							   "   				<th class='fc_3'>失效类现金利息</th>"+
								   "  				<th class='fc_3'>合计</th>"+
							   "    		</tr>"+
		 		   		   	 "	</thead>"+
		 		   		   	 "	<tbody>"+
		 		   		   	 ""+tobodyHtml+""+
		 		   		   	 "	</tbody>"+
							 "</table>"+
							" <div id='page_div'>"+
							 ""+getPageHelper(obj.detail)+""+
							 "</div>"+
							 "&nbsp;&nbsp;<label><input type='checkbox' id='overdue_allsel' onclick='dividedPayments.low_normalCompensatorySelect(this)'/> 全选</label> &nbsp;&nbsp;&nbsp;&nbsp;"+
	 						   "<button type='button' class='btn btn_bgf60 btn_size100' onclick='dividedPayments.low_normalCompensatoryPartRepayMentTask(this)' data-opid='"+obj.opid+"' id='NormalComponentSubmit'>提交</button>";
		   	    		$("#normalCompensatoryPart").modal("hide"); 
 		   	    		$("#queryall_list").html(normalComponentHtml);
		   	    	 }else if(obj.result == "fail"){
		 	    		alert(obj.Msg);
		 	    		window.location.href = basePath + "/admin/dividedPayments/list.action";
		 	    	}
	    	} 
   	    },{"pageNo":pageNo,"pageSize":pageSize,"opid":opid});
 	}
 	
 	function getPageHelper(obj){
  		var isFirstPageHtml = "";
 		if(!obj.isFirstPage){
 			isFirstPageHtml = "<li>"+
 					"				<a href='javascript:queryAllPersonByNormalComponent("+obj.prePage+","+obj.pageSize+");'>&lt;前一页</a>"+
 					"			</li>";
 		}
  		var navigatepageNumsHtml = "";
 		$.each(obj.navigatepageNums,function(index,content){
  			if((index + 1) == obj.pageNum){
  				navigatepageNumsHtml += "<li class='active'>"+
					"					<a href='javascript:queryAllPersonByNormalComponent("+(index + 1)+", "+obj.pageSize+");'>"+(index + 1)+"</a>"+
					"				</li>";
 			}
  			if((index + 1) != obj.pageNum){
 				navigatepageNumsHtml +="<li>"+
						"					<a href='javascript:queryAllPersonByNormalComponent("+(index + 1)+", "+obj.pageSize+");'>"+(index + 1)+"</a>"+
						"				</li>";
 			}
  		});
 		
 		var isLastPageHtml = "";
 		if(!obj.isLastPage){
 			isLastPageHtml = "<li> "+
				"				<a href='javascript:queryAllPersonByNormalComponent("+obj.nextPage+", "+obj.pageSize+");'>下一页&gt;</a>"+
				"			</li>";
 		}
 		var html =  "<div class='message'>"+
					"	共<i class='blue'>"+obj.total+"</i>条记录，当前显示第&nbsp;<i class='blue'>"+obj.pageNum+"/"+obj.pages+"</i>&nbsp;页"+
					"</div>"+
					"<div style='text-align: center;' id='pageCon'>"+
					"	<ul class='pagination'>"+
					"		<!-- <li><a href='#'>&laquo;</a></li> -->"+
					"		<li id='liOne'>"+
					"			<a href='javascript:queryAllPersonByNormalComponent("+obj.firstPage+", "+obj.pageSize+");'>&lt;&lt;首页</a>"+
					"		</li>"+
					 ""+isFirstPageHtml+""+
					 ""+navigatepageNumsHtml+""+
					""+isLastPageHtml+""+
					"		<li>"+
					"			<a href='javascript:queryAllPersonByNormalComponent("+obj.lastPage+", "+obj.pageSize+");'>尾页&gt;&gt;</a>"+
					"		</li>"+
 					"	</ul>"+
					"</div>";
 		return html;
 	}
