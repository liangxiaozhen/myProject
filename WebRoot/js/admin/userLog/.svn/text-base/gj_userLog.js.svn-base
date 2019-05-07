$(function(){
	 $(".tzui-tips").tzTip();
});
   	var userLog = {
  			//用户日志表查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/userLog/detail.action?opid="+opid
 				});
 			},
 			low_reset:function(obj){
 				$("#userLog_username").val("");
 		 		$("#userLog_usertype").val("");
 		 		$("#userLog_IP").val("");
 		 		$("#userLog_cookie").val("");
  			}
    	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = getParams();
  		params.pageSize = "20";
  		params.pageNo = pageNo;
  		$.tzAdminAjax.request({
  			model:"admin/userLog",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
   	}
   //模糊搜索
 	function search(obj){
  		var params = getParams(); 
 		$.tzAdminAjax.request({
  			model:"admin/userLog",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
  	}
 	 
 	
 	function getParams(){
 		var username = $("#userLog_username").val();
 		var usertype = $("#userLog_usertype").val();
 		var cookie = $("#userLog_cookie").val();
 		var ip = $("#userLog_IP").val();
  		var params = {"username":username,"usertype":usertype,"cookie":cookie,"ip":ip};
 		return params;
 	}
