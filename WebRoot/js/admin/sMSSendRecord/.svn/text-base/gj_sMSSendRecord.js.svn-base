$(function(){
	 $(".tzui-tips").tzTip();
});
   	var sMSSendRecord = {
   			low_repeat:function(obj){
   				
   			},  
  			low_findAll:function(){
  				//$("#search_username").val("");
  				//$("#box").load(basePath+"/admin/sMSSendRecord/list.action");
  				queryAllPerson('1','20');
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
			url:basePath+"/admin/sMSSendRecord/template.action",
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
 			  url:basePath+"/admin/sMSSendRecord/template.action",
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
