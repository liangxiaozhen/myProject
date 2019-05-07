   	var activityAwardRule = {
 			//跳转标的活动奖励规则设置页面
 			low_save_button:function(){
 				$.tzAdminAjax.request({
 					model:"admin/activityAwardRule",
 					method:"insert.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				});
 			},
 			//标的活动奖励规则查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",title:"标的活动奖励规则详情",
 					content:basePath+"/admin/activityAwardRule/detail.action?opid="+opid
 				});
 			},
 			//跳转修改标的活动奖励规则页面
 			low_update:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzAdminAjax.request({
 					model:"admin/activityAwardRule",
 					method:"edit.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				},{"opid":opid});
 			},
 			//标的活动奖励规则审核操作
 			low_reviewed:function(obj){
 				var actno = $(obj).data("actno");
 				var opid = $(obj).data("opid");
 				$.tzAlert({"title":"审核提示","content":"您确定通过活动编号为<span class='red'>"+actno+"</span>的审核吗",callback:function(ok){
 					if(ok){
 						$.tzAdminAjax.request({
 							model:"admin/activityAwardRule",
 							method:"reviewed.action",
 							callback:function(data){
  								var obj = $.parseJSON(data);
 								if(obj.result =="fail"){
 									loading("审核失败,请重新操作",4);
 								}else if(obj.result=="success"){
 									alert("审核成功");
 									window.location.href=basePath+"/admin/activityAwardRule/list.action";
 								}
 							}
 						},{"id":opid});
 					}
 				}});
 			},
 			//启用停用活动设置操作
 			low_status:function(obj){
 				var actno = $(obj).data("actno");
 				var opid = $(obj).data("opid");
 				var status = $(obj).data("status");
 				var mark = (status ==1 ? 0:1); 
 				var parasm = (status ==1?'启用':'停用');
 				$.tzAlert({"title":""+parasm+"提示","content":"您确定通过活动编号为<span class='red'>"+actno+"</span>的"+parasm+"设置吗",callback:function(ok){
 					if(ok){
 						$.tzAdminAjax.request({
 							model:"admin/activityAwardRule",
 							method:"status.action",
 							callback:function(data){
  								var obj = $.parseJSON(data);
 								if(obj.result =="fail"){
 									loading("审核失败,请重新操作",4);
 								}else if(obj.result=="success"){
 									alert("设置成功");
 									window.location.href=basePath+"/admin/activityAwardRule/list.action";
 								}
 							}
 						},{"id":opid,"status":mark});
 					}
 				}});
  			},
  			low_findAll:function(){
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/activityAwardRule/list.action");
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
			url:basePath+"/admin/activityAwardRule/template.action",
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
 			  url:basePath+"/admin/activityAwardRule/template.action",
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
