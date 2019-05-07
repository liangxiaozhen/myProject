$(function(){
	 $(".tzui-tips").tzTip();
});
   	var repaymentDetail = {
  			//还款批量详情记录查看详情
 			low_detail:function(obj){
 				var opid = $(obj).data("opid");
 				$.tzIframe({width:"560",cancelText:"返回列表",height:"560",title:"批次详情计划信息",
 					content:basePath+"/admin/repaymentDetail/detail.action?opid="+opid
 				});
 			},
 			//点击标号查询
 			low_tnobulr:function(obj){
 				var tno = $(obj).data("tno");
 				$("#repaymentDetail_tno").val(tno);
  				$.tzAdminAjax.request({
 		  			model:"admin/repaymentDetail",
 		  			method:"template.action",
 		  			callback:function(data){
 		  				$("#queryall_list").html(data);
 		  			}
 		  		},{"tno":tno});
 			},
 			//点击还款流水号查询
 			low_rordernobulr:function(obj){
 				var opid = $(obj).data("opid");
 				$.tzIframe({width:"560",cancelText:"返回列表",height:"560",title:"原还款计划详情信息",
 					content:basePath+"/admin/repayMent/detail.action?opid="+opid
 				});
 			},
 			//点击批次号查询
 			low_rbatchnobulr:function(obj){
 				var rbatchno = $(obj).data("rbatchno");
 				$("#repaymentDetail_rbatchno").val(rbatchno);
  				$.tzAdminAjax.request({
 		  			model:"admin/repaymentDetail",
 		  			method:"template.action",
 		  			callback:function(data){
 		  				$("#queryall_list").html(data);
 		  			}
 		  		},{"rbatchno":rbatchno});
 			},
 			low_reset:function(obj){
 				  $("#repaymentDetail_isproxyrepay").val("");
 		 		 $("#repaymentDetail_isoverdue").val("");
 		 		 $("#repaymentDetail_isahead").val("");
 		 		  $("#repaymentDetail_isdarepay").val("");
 		 		  $("#repaymentDetail_rmode").val("");
 		 		  $("#repaymentDetail_isaudit").val("");
 		 		 $("#repaymentDetail_repaystatus").val("");
 		 		  $("#repaymentDetail_tno").val("");
 		 		 $("#repaymentDetail_tname").val("");
 		 		$("#repaymentDetail_inloginname").val("");
 		 		$("#repaymentDetail_outloginname").val("");
 		 		$("#repaymentDetail_proxyloginname").val("");
 		 		 $("#repaymentDetail_utorderno").val("");
 		 		 $("#repaymentDetail_rorderno").val("");
 		 		$("#repaymentDetail_rbatchno").val("");
 		 		$("#repaymentDetail_periods").val("");
 			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = getParams();
   		params.pageSize = "20";
   		params.pageNo = pageNo;
 		$.tzAdminAjax.request({
  			model:"admin/repaymentDetail",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
   	}
 	
   //模糊搜索
 	function search(obj){
 		$("#repaymentDetail_search").text("查询中...");
 		var params = getParams();
  		$.tzAdminAjax.request({
  			model:"admin/repaymentDetail",
  			method:"template.action",
  			callback:function(data){
  				$("#repaymentDetail_search").text("查询");
  				$("#queryall_list").html(data);
  			}
  		},params);
  	}
 	
 	 function getParams(){
 		 var isproxyrepay = $("#repaymentDetail_isproxyrepay").val();
 		 var isoverdue = $("#repaymentDetail_isoverdue").val();
 		 var isahead = $("#repaymentDetail_isahead").val();
 		 var isdarepay = $("#repaymentDetail_isdarepay").val();
 		 var rmode = $("#repaymentDetail_rmode").val();
 		 var isaudit = $("#repaymentDetail_isaudit").val();
 		 var repaystatus = $("#repaymentDetail_repaystatus").val();
 		 var tno = $("#repaymentDetail_tno").val();
 		 var tname = $("#repaymentDetail_tname").val();
 		 var inloginname = $("#repaymentDetail_inloginname").val();
 		 var outloginname = $("#repaymentDetail_outloginname").val();
 		 var proxyloginname = $("#repaymentDetail_proxyloginname").val();
 		 var utorderno = $("#repaymentDetail_utorderno").val();
 		 var rorderno = $("#repaymentDetail_rorderno").val();
 		 var rbatchno = $("#repaymentDetail_rbatchno").val();
 		 var periods = $("#repaymentDetail_periods").val();
 		 var params = {
 				"isproxyrepay":isproxyrepay,
 				"isoverdue":isoverdue,
 				"isahead":isahead,
 				"isdarepay":isdarepay,
 				"rmode":rmode,
 				"isaudit":isaudit,
 				"repaystatus":repaystatus,
 				"tno":tno,
 				"tname":tname,
 				"inloginname":inloginname,
 				"outloginname":outloginname,
 				"proxyloginname":proxyloginname,
 				"utorderno":utorderno,
 				"rorderno":rorderno,
 				"rbatchno":rbatchno,
 				"periods":periods
  		 }
 		 return params;
 	 }
