var userRiskEdit = {
		low_findAll:function(obj){
			$(obj).text("查询中...");
			$("#search_username").val("");
			$("#box").load(basePath+"/admin/userRisk/insert.action");
		},
		low_callback:function(){
			window.location.href = basePath+"/admin/userRisk/list.action";
		},
		//显示model静态框
		low_insert:function(obj){
			var opid = $(obj).data("opid");
			var username = $(obj).data("username");
			var regip = $(obj).data("regip");
			var regcookie = $(obj).data("regcookie");
			var mobilephone = $(obj).data("mobilephone");
			var email = $(obj).data("email");
  			$("#baseid").val(opid);
 			$("#username").val(username);
 			$("#ip").val(regip);
 			$("#cookie").val(regcookie);
 			$("#mobile").val(mobilephone);
 			$("#email").val(email);
 			$("#userRisk_model").modal('show');
  		},
  		low_save:function(obj){
  			$("#userRisk_model").modal('hide');
  		    var params =$("#userRisk_edit_form").serialize();
   		    $.ajax({
  		    	type:"post",
  		    	url:basePath+"/admin/userRisk/save.action",
  		    	data:params,
   		    	success:function(data){
   		    		var obj = $.parseJSON(data);
  		    		if(data == "logout"){
  		    			window.location.href = basePath+"/admin/login.action";
  		    		}else if (obj.result == "success"){
  		    			alert("保存成功！");
  		    			window.location.href = basePath+"/admin/userRisk/insert.action";
  		    		}else if(obj.result == "fail"){
  		    			loading(" 保存失败！请请后再试...",4);
  		    		}else if(obj.result == "error"){
  		    			loading("参数错误...",4);
  		    		}
  		    		 
  		    	}
  		    });
  		}
 }

//分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var username = $("#search_username").val();
 		var pageSize = "20";
  		$.ajax({
			type:"post",
			data:{"pageSize":pageSize,"pageNo":pageNo,"username":username},
			url:basePath+"/admin/userRisk/userTemplate.action",
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
   		$("#search").text("正在搜索中...");  
 		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/userRisk/userTemplate.action",
 			  data:{"email":username,"realname":username,"loginname":username,"mobilephone":username},
 			  error:function(){$("#search").text("搜索");},
 			  success:function(data){
 				 $("#search").text("搜索"); 
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