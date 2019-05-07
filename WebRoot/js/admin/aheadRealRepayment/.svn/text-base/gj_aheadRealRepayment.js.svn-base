$(function(){
	 $(".tzui-tips").tzTip();
});
   	var aheadRealRepayment = {
 			 
 			//提前实际还款记录实体查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",title:"提前还款实际记录详情",
 					content:basePath+"/admin/aheadRealRepayment/detail.action?opid="+opid
 				});
 			},
 			low_reset:function(){
 				$("#aheadRealRepayment_repaystatus").val("");
 				$("#aheadRealRepayment_rorderno").val("");
 				$("#aheadRealRepayment_rbatchno").val("");
  			},
  			bulrrrealbatchno:function(obj){
  				var rrealbatchno = $(obj).data("rrealbatchno");
  				$("#aheadRealRepayment_rbatchno").val(rrealbatchno);
  				$.tzAdminAjax.request({
  		  			model:"admin/aheadRealRepayment",
  		  			method:"template.action",
  		  			callback:function(data){
  		  				$("#queryall_list").html(data);
  		  			}
  		  		},{"rrealbatchno":rrealbatchno});
  			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = getParams();
  		params.pageSize = "20";
  		params.pageNo = pageNo;
 		$.tzAdminAjax.request({
  			model:"admin/aheadRealRepayment",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
   	}
   //模糊搜索
 	function search(obj){
 		$("#aheadRealRepayment_search").text("查询中...");
 		var params = getParams();
  		$.tzAdminAjax.request({
  			model:"admin/aheadRealRepayment",
  			method:"template.action",
  			error:function(){$("#aheadRealRepayment_search").text("查询");},
  			callback:function(data){
  				$("#aheadRealRepayment_search").text("查询");
  				$("#queryall_list").html(data);
  			}
  		},params);
  	}
  
 	
 	function getParams(){
 		var repaystatus = $("#aheadRealRepayment_repaystatus").val().trim();
 		var rorderno = $("#aheadRealRepayment_rorderno").val().trim();
		var rbatchno = $("#aheadRealRepayment_rbatchno").val().trim();
		return {"repaystatus":repaystatus,"rorderno":rorderno,"rrealbatchno":rbatchno};
 	}
