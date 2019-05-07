$(function(){
	 $(".tzui-tips").tzTip();
});
   	var interestExpenseRecord = {
  			//标的利息管理费记录查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/interestExpenseRecord/detail.action?opid="+opid
 				});
 			},
 			low_reset:function(){
  		 		$("#isaudit").val("");
  		 		$("#isdeal").val("");
  		 		$("#ietype").val("");
  		 		$("#ieproperty").val("");
  		 		$("#tno").val("");
  		 		$("#tname").val("");
  		 		$("#batchno").val("");
  		 		$("#rorderno").val("");
  		 		$("#loginname").val("");
  			},
  			low_tnobulr:function(obj){
  				var tno = $(obj).data("tno").trim();
  				$("#tno").val(tno);
  				$.tzAdminAjax.request({
  		  			model:"admin/interestExpenseRecord",
  		  			method:"template.action",
  		  			callback:function(data){
  		  				$("#queryall_list").html(data);
  		  			}
  		  		},{"tno":tno});
  			},
  			low_batchnobulr:function(obj){
  				var batchno = $(obj).data("batchno").trim();
  				$("#batchno").val(batchno);
  				$.tzAdminAjax.request({
  		  			model:"admin/interestExpenseRecord",
  		  			method:"template.action",
  		  			callback:function(data){
  		  				$("#queryall_list").html(data);
  		  			}
  		  		},{"batchno":batchno});
  			}
  			 
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = getParams();
  		params.pageSize = "20";
  		params.pageNo = pageNo;
  		$.tzAdminAjax.request({
  			model:"admin/interestExpenseRecord",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
   	}
   //模糊搜索
 	function search(obj){
 		$("#interestExpenseRecord_search").text("查询中...");
 		var params = getParams();
 		$.tzAdminAjax.request({
 			model:"admin/interestExpenseRecord",
 			method:"template.action",
 			callback:function(data){
 				$("#interestExpenseRecord_search").text("查询");
 				$("#queryall_list").html(data);
 			}
 		},params);
  	}
 	
 	function getParams(){
  		var isaudit = $("#isaudit").val().trim();
 		var isdeal = $("#isdeal").val().trim();
 		var ietype = $("#ietype").val().trim();
 		var ieproperty = $("#ieproperty").val().trim();
 		var tno = $("#tno").val().trim();
 		var tname = $("#tname").val().trim();
 		var batchno = $("#batchno").val().trim();
 		var rorderno = $("#rorderno").val().trim();
 		var loginname = $("#loginname").val().trim();
  		var params = {"isaudit":isaudit,"isdeal":isdeal,"ietype":ietype,"ieproperty":ieproperty,"tno":tno,"tname":tname,"batchno":batchno,"rorderno":rorderno,"loginname":loginname};
 		return params;
 	}
 