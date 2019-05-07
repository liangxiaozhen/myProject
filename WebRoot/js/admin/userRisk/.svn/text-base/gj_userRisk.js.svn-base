$(function(){
	 $(".tzui-tips").tzTip();
});
   	var userRisk = {
 			//跳转用户风控设置页面
 			low_save_button:function(obj){
 				$("#saveRisk").text("正在跳转中...");
 				$.tzAjax.request({
 					model:"admin/userRisk",
 					method:"insert.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				});
 			},
 			//用户风控查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",title:"用户风控详情",
 					content:basePath+"/admin/userRisk/detail.action?opid="+opid
 				});
 			},
 			//跳转修改用户风控页面
 			low_delete:function(obj){
 				var opid=$(obj).data("opid");
 				var uid=$(obj).data("uid");
    		 $.ajax({
  					 type:"post",
  					 url:basePath+"/admin/userRisk/update.action",
  					 data:{"opid":opid,"uid":uid},
  					 success:function(data){
  						 var obj = $.parseJSON(data);
  						 if(data == "logout"){
  							 window.location.href =basePath+"/admin/login.action";
  						 }else if(obj.result == "fail"){
  							 alert("移除失败...");
  						 }else if(obj.result == "success"){
  							 alert("移除成功...");
  							 window.location.href =basePath+"/admin/userRisk/list.action";
  						 }else if(obj.result == "error"){
  							 alert("参数错误...");
  						 }
   					 }
  				 });
 			},
    		low_findAll:function(obj){
    			$(obj).text("查询中...");
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/userRisk/list.action");
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
			url:basePath+"/admin/userRisk/template.action",
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
 			alert("请输入用户名搜索....");
 			$("#search_username").select();
 			return false;
 		}
 		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/userRisk/template.action",
 			  data:{"username":username},
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
