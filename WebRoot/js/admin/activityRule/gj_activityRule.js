$(function(){
	 $(".tzui-tips").tzTip();
});
var activityRule = {
 			//跳转标的活动规则设置页面
 			low_save_button:function(){
 				$.tzAjax.request({
 					model:"admin/activityRule",
 					method:"insert.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				});
 			},
 			//标的活动规则查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"880",cancelText:"返回列表",height:"600",title:"标的活动规则详情",
 					content:basePath+"/admin/activityRule/detail.action?opid="+opid
 				});
 			},
 			//跳转修改标的活动规则页面
 			low_update:function(obj){
 				var opid=$(obj).data("opid");
  				$.tzAjax.request({
 					model:"admin/activityRule",
 					method:"edit.action",
 					callback:function(data){
    					$("#box").html(data);
  					}
 				},{"opid":opid});
 			},
   			low_findAll:function(){
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/activityRule/list.action");
   			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var username = $("#search_username").val();
 		var pageSize = "20";
  		$.ajax({
			type:"post",
			data:{"pageSize":pageSize,"pageNo":pageNo,"username":username},
			url:basePath+"/admin/activityRule/template.action",
			success:function(data){
				$("#queryall_list").html(data);
				$(".tzui-tips").tzTip();
				//关键词高亮
				gjKeywordHi(username);
			}
		});
  	}
   //模糊搜索
 	function search(obj){
 		var username = $("#search_username").val();
  		if(isEmpty(username)){
 			alert("请输入活动编号搜索....");
 			$("#search_username").select();
 			return false;
 		}
 		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/activityRule/template.action",
 			  data:{"actno":username},
 			  success:function(data){
    			  $("#queryall_list").html(data);
    			  $(".tzui-tips").tzTip();
    			  gjKeywordHi(username);
   			  }
 		  });
 	}
 	
 	//代码高亮
 	function gjKeywordHi(keyword){
 		$("#queryall_list").find(".gj_keyword").each(function(){
			  var text = $(this).text();
 			  var regex = new RegExp(keyword,"ig");
			  var rtext = text.replace(regex,"<span class='red'>"+keyword+"</span>");
			  $(this).html(rtext);
		  });
 	}
