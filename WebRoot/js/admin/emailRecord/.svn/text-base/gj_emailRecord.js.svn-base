$(function(){
	 $(".tzui-tips").tzTip();
});
 var emailRecord = {
		  low_repeat:function(obj){
		    	var opid  =  $(obj).data("opid");
		    	var email =  $(obj).data("email");
 		    	$.tzConfirm({
		    		title:"补发邮件",
		    		content:"你确定要给<span class='red'>"+email+"</span>补发邮件吗？",
 		    		callback:function(ok){
		    			if(ok){
		    				loading("发送中...");
 		    				$.tzAjax.request({
 		    					model:"admin/emailRecord",
 		    					method:"repeat.action",
 		    					error:function(){loading("发送失败...",1);},
 		    					callback:function(data){
 		    					    loading("发送中...",1);
   		    						var obj = $.parseJSON(data);
 		    						if(obj.result == "error"){
 		    						    loading("邮件补发失败",4);	
 		    						}else if(obj.result == "fail"){
 		    							loading("邮件补发失败",4);
 		    						}else if(obj.result == "success"){
 		    							loading("邮件补发成功！",4);
  		    						}
 		    					}
 		    					
 		    				},{"opid":opid});
		    			}
		    		}
		    	});
		    }, 
  			low_findAll:function(obj){
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/emailRecord/list.action");
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
			url:basePath+"/admin/emailRecord/template.action",
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
 			  url:basePath+"/admin/emailRecord/template.action",
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
