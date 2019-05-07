	var timer = null;
   	var repayMent = {
  			//还款记录查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"580",cancelText:"返回列表",height:"660",title:"用户还款记录详情信息",
 					content:basePath+"/admin/repayMent/detail.action?opid="+opid,callback:function(iframe,$dialog,opts){
 						if(iframe){
 							$dialog.next().remove();
 							$dialog.remove();
 						}
 					} 
 				});
 			},
 			//显示审核modal
 			low_isaudit_modal:function(obj){
 				 var rbatchno = $(obj).data("rbatchno");
 				 var tno = $(obj).data("tno");
 				 var loginnamestr = $(obj).data("loginnamestr");
 				 var realnamestr = $(obj).data("realnamestr");
 				 var isproxyrepay = $(obj).data("isproxyrepay");
 				 var isoverdue = $(obj).data("isoverdue");
 				 var isahead = $(obj).data("isahead");
				 
    			 $("#repayMent_rbatchno").text(rbatchno);
 				 $("#repayMent_tno").text(tno);
 				 $("#repayMent_loginnamestr").text(loginnamestr);
 				 $("#repayMent_realnamestr").text(realnamestr);
 				 $("#repayMent_params").attr("data-isproxyrepay",isproxyrepay);
 				 $("#repayMent_params").attr("data-isoverdue",isoverdue);
 				 $("#repayMent_params").attr("data-isahead",isahead);
 				 $("#repayMent_params").attr("data-rbatchno",rbatchno);
				 
    			$("#repayMent_isaudit").modal("show");
 			},
  			 
    		//重置
   			low_reset:function(){
   				$("#planstatus").val("");
   		 		$("#isdarepay").val("");
   		 		$("#rmode").val("");
   		 		$("#isaudit").val("");
   		 		$("#repaystatus").val("");
   		 	    $("#tenderid").val("");
   		 	    $("#periods").val("");
    	 		$("#tno").val("");
    	 		$("#tname").val("");
   	 		    $("#inloginname").val("");
   	 		    $("#outloginname").val("");
   	 		    $("#proxyloginname").val("");
   	 		    $("#utorderno").val("");
   	 		    $("#daorderno").val("");
   	 		    $("#rorderno").val("");
     	   		$("#isproxyrepay").val("");
   	   		    $("#isoverdue").val("");
   	   		    $("#isahead").val("");
   	   		    $("#rbatchno").val("");
   	   		 
   			},
    		//全选 
   			low_allisaudit:function(obj){
    			var ifd$ = $(".repay_isaudit");
   		  		var cid$ = $("#repay_isaudit");
   		  	 	for(var i = 0;i<ifd$.length;i++){
   		 			if(cid$[0].checked){
   		  				if(ifd$[i].checked){
   		   					ifd$[i].checked = cid$[0].checked;
   		 					ifd$[i].checked = "";
   		 				}else{
   		 					ifd$[i].checked = cid$[0].checked;
   		 				}
   		 			}else{
   		   				if(ifd$[i].checked){
   		 					ifd$[i].checked = "";
   		 				}else{
   		 					ifd$[i].checked = "";
   		 					ifd$[i].checked = "true";
   		 				}
   		   			}
   		  		}
   			},
     		//点击标号查询
    		bulrTno:function(obj){
    			var bulrTno = $(obj).data("tno");
    			$("#tno").val(bulrTno);
      			$.tzAdminAjax.request({
    	  			model:"admin/repayMent",
    	  			method:"template.action",
    	  			callback:function(data){
     	  				$("#queryall_list").html(data);
    	  			}
    	  		},{"tno":bulrTno});
    		},
    		bulrRbatchno:function(obj){
    			var rbatchno = $(obj).data("rbatchno");
    			$("#rbatchno").val(rbatchno);
      			$.tzAdminAjax.request({
    	  			model:"admin/repayMent",
    	  			method:"template.action",
    	  			callback:function(data){
     	  				$("#queryall_list").html(data);
    	  			}
    	  		},{"rbatchno":rbatchno});
    		}
   	};
     //还款查看分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var prams = getParams();
  		prams.pageSize = "20";
  		prams.pageNo = pageNo;
  		$.tzAdminAjax.request({
  			model:"admin/repayMent",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},prams);
   	}
 	 
   //还款计划查看模糊搜索
 	function search(obj){
 		$("#repayMent_search").text("查询中...");
  		var prams = getParams();
  		$.tzAdminAjax.request({
  			model:"admin/repayMent",
  			method:"template.action",
  			error:function(){$("#repayMent_search").text("查询");},
  			callback:function(data){
  				$("#repayMent_search").text("查询");
  			   $("#queryall_list").html(data);  			
  			}
  		},prams);
  	}
 	  
 	
 	function DivRepayRest(){
 		$("#DivRepay_tenderid").val("");
 		 $("#DivRepay_periods").val("");
 	}
 	  
 	function getParams(){
 		var planstatus = $("#planstatus").val();
 		var isdarepay = $("#isdarepay").val();
 		var rmode = $("#rmode").val();
 		var isaudit = $("#isaudit").val();
 		var repaystatus = $("#repaystatus").val();
 		var tno = $("#tno").val();
 		var tname = $("#tname").val();
 		var inloginname = $("#inloginname").val();
 		var outloginname = $("#outloginname").val();
 		var proxyloginname = $("#proxyloginname").val();
 		var utorderno = $("#utorderno").val();
 		var daorderno = $("#daorderno").val();
 		var rorderno = $("#rorderno").val();
 		var rbatchno = $("#rbatchno").val();
 		 
    	var periods = $("#periods").val();
   		var isproxyrepay = $("#isproxyrepay").val();
   		var isoverdue = $("#isoverdue").val();
   		var isahead = $("#isahead").val();
   		
   	 
    	var params = {"isahead":isahead,"rbatchno":rbatchno,"isproxyrepay":isproxyrepay,"isoverdue":isoverdue,"planstatus":planstatus,"isdarepay":isdarepay,"rmode":rmode,"isaudit":isaudit,"repaystatus":repaystatus,"periods":periods,"tno":tno,"tname":tname,"inloginname":inloginname,"outloginname":outloginname,"proxyloginname":proxyloginname,"utorderno":utorderno,"daorderno":daorderno,"rorderno":rorderno};
   		return params;
 	}
 	 
