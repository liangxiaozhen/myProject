$(function(){
	 $(".tzui-tips").tzTip();
});
   	var interestExpense = {
 			//跳转标的利息管理费设置设置页面
 			low_save_button:function(){
 				$.tzAdminAjax.request({
 					model:"admin/interestExpense",
 					method:"edit.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				});
 			},
 			//标的利息管理费设置查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/interestExpense/detail.action?opid="+opid,
 					callback:function(iframe,$dialog,opts){
 						if(iframe){
 							$dialog.next().remove();
 							$dialog.remove();
 						}
   					}
 				});
 			},
 			//跳转修改标的利息管理费页面
 			low_update:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzAdminAjax.request({
 					model:"admin/interestExpense",
 					method:"edit.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				},{"opid":opid});
 			},
 			low_delete:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzConfirm({
 					title:"删除提示",
 					content:"您确定删除？",
 					animate:"fadeOut",
 					callback:function(ok){
 						if(ok){
  							$.tzAdminAjax.request({
 			 					model:"admin/interestExpense",
 			 					method:"delete.action",
  			 					callback:function(data){
  			   						 if(data.result == "success"){
 			   							 alert(data.msg);
 			   							 window.location.href = basePath + "/admin/interestExpense/list.action";
 			   						 }else {
 			   							alert(data.msg);
 			   						    window.location.href = basePath + "/admin/interestExpense/list.action";
 			   						 }
 			  					}
 			 				},{"opid":opid});
 						}
 					}
  				});
 			},
 			low_search:function(obj){
 				$("#interestExpense_search").text("查询中..");
 				var parmas = this.low_getParams();
 				$.tzAdminAjax.request({
 		  			model:"admin/interestExpense",
 		  			method:"template.action",
 		  			error:function(){$("#interestExpense_search").text("查询");},
 		  			callback:function(data){
 		 				$("#interestExpense_search").text("查询");
  		   				$("#queryall_list").html(data);
  		   				$(".tzui-tips").tzTip();
 		  			}
 		  		},parmas);
 			},
 			low_reset:function(obj){
 				$("#gfitype").val("");
 				$("#isaudit").val("");
 				$("#istemplet").val("");
 			},
 			low_getParams:function(){
 				var gfitype = $("#gfitype").val();
 				var isaudit = $("#isaudit").val();
 				var istemplet = $("#istemplet").val();
 				var params = {"gfitype":gfitype,"isaudit":isaudit,"istemplet":istemplet};
 				return params;
  			}
   	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = interestExpense.low_getParams();
  		params.pageSize = "20";
  		params.pageNo = pageNo;
 		$.tzAdminAjax.request({
  			model:"admin/interestExpense",
  			method:"template.action",
  			callback:function(data){
   				$("#queryall_list").html(data);
   				$(".tzui-tips").tzTip();
  			}
  		},params);
   	}
 