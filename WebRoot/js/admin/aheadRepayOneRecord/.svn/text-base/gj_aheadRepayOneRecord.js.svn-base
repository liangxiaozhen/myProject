    	var aheadRepayOneRecord = {
  			//标的提前还款奖励单个投资人记录查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/aheadRepayOneRecord/detail.action?opid="+opid
 				});
 			},
    		low_findAll:function(){
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/aheadRepayOneRecord/list.action");
   			},
   			//审核
   			low_audit:function(){
   				$("#my_audit").modal();
   			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var username = $("#search_username").val();
 		var pageSize = "20";
 		$.tzAdminAjax.request({
  			model:"admin/aheadRepayOneRecord",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},{"pageSize":pageSize,"pageNo":pageNo,"username":username});
   	}
   //模糊搜索
 	function search(obj){
 		var username = $("#search_username").val();
  		if(isEmpty(username)){
 			alert("请输入活动编号搜索....");
 			$("#search_username").select();
 			return false;
 		}
 		$.tzAdminAjax.request({
  			model:"admin/aheadRepayOneRecord",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},{"actno":username});
  	}
 	 
