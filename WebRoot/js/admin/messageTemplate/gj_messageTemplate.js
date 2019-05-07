$(function(){
	 $(".tzui-tips").tzTip();
 });
   	var messageTemplate = {
 			//跳转短信模板设置页面
 			low_save_button:function(){
 				$("#low_insert").text("跳转中...");
 				$.tzAdminAjax.request({
 					model:"admin/messageTemplate",
 					method:"insert.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				});
 			},
 			//短信模板查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"300",cancelText:"返回列表",height:"380",title:"短信模板详情",	
 					content:basePath+"/admin/messageTemplate/detail.action?opid="+opid
 				});
 			},
 			//跳转修改短信模板页面
 			low_update_edit:function(obj){
 				var opid=$(obj).data("opid");
 				$("#low_update_edit_"+opid).text("跳转中...");
 				$.tzAdminAjax.request({
 					model:"admin/messageTemplate",
 					method:"edit.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				},{"opid":opid});
  				 
 			},
 			low_delete:function(obj){
  				var opid = $(obj).data("opid");
 				$.tzConfirm({
 					title:"删除提示",
 					content:"您确定删除吗",
 					callback:function(ok){
 						if(ok){
 							$.tzAdminAjax.request({
 								model:"admin/messageTemplate",
 								method:"delete.action",
 								callback:function(data){
									var obj = $.parseJSON(data);
			 						if(obj.result == "success"){
			 							alert("删除成功");
			 							window.location.href=basePath+"/admin/messageTemplate/list.action";
			 						}else if(obj.result == "fail"){
			 							alert("删除失败");
			  						}
 								}
 							},{"id":opid}); 
 						}
 					}
 				});
 			},
   			//查询全部信息
  			low_findAll:function(){
  				$("#search_username").val("");
  				$("#low_findAll").text("查询中...");
   				$("#box").load(basePath+"/admin/messageTemplate/list.action");
   			},
   			 
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var username = $("#search_username").val();
 		var pageSize = "20";
  		$.ajax({
			type:"post",
			data:{"pageSize":pageSize,"pageNo":pageNo,"username":username},
			url:basePath+"/admin/messageTemplate/template.action",
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
 			alert("请输入关键字搜索....");
 			$("#search_username").select();
 			return false;
 		}
 		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/messageTemplate/template.action",
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
 	
 	 