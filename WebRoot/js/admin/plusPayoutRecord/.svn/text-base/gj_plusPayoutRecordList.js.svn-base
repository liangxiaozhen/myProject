    var plusPayoutRecordList = {
  			//标的增益清算记录查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"560",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/plusPayoutRecord/detail.action?opid="+opid
 				});
 			},
  			//查询全部
    		low_findAll:function(){
    			$("#low_findAll").text("查询中...");
   				$("#box").load(basePath+"/admin/plusPayoutRecord/recordList.action");
   			},
   			low_reset:function(){
   				$("#inname").val("");
   		 		$("#isaudit").val("");
   		 		$("#isgrant").val("");
   			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
   		$("#queryall_list").html("数据正在加载中.....");
  		var pageSize = "20";
  		$.tzAdminAjax.request({
  			model:"admin/plusPayoutRecord",
  			method:"recordTemplate.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},{"pageSize":pageSize,"pageNo":pageNo,});
   	}
    
 	 //模糊搜索
 	function search(obj){
 		var params = getParams();
  		$.tzAdminAjax.request({
  			model:"admin/plusPayoutRecord",
  			method:"recordTemplate.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);  		  
 	}
 	
 	function getParams(){
 		var inname = $("#inname").val();
 		var isaudit = $("#isaudit").val();
 		var isgrant = $("#isgrant").val();
 		var params = {"inname":inname,"isaudit":isaudit,"isgrant":isgrant};
 		return params;
 	}
 	