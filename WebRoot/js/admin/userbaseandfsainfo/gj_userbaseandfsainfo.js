   	var baseAndFsa = {
  			low_detail:function(obj){
  				var opid=$(obj).data("opid");
  				$.tzIframe({width:"400",cancelText:"返回列表",height:"560",title:"用户信息详情",
 					content:basePath+"/admin/baseAndFsa/detail.action?opid="+opid
 				});
 			},
    		low_reset:function(){
   			  $("#userBaseInfo_loginname").val("");
   	   		  $("#userBaseInfo_realname").val("");
   	   		  $("#userBaseInfo_mobilephone").val("");
   	   		  $("#userBaseInfo_email").val("");
   	   		  $("#userBaseInfo_startregdate").val("");
   	   		  $("#userBaseInfo_endregdate").val("");
   	   		  $("#accounttype").val("");
   	   		  $("#isopenfsinfo").val("");
 		  
    	    }
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  	    var params = getParams();
  	    params.pageSize = "20";
  	    params.pageNo = pageNo;
  		$.ajax({
			type:"post",
			data:params,
			url:basePath+"/admin/baseAndFsa/template.action",
			success:function(data){
				$("#queryall_list").html(data);
				$(".tzui-tips").tzTip();
  			}
		});
  	}
   //模糊搜索
 	function search(obj){
 		 $("#baseAndFsa_search").text("查询中...");
   		  var params = getParams();
  		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/baseAndFsa/template.action",
 			  data:params,
 			  error:function(){$("#baseAndFsa_search").text("查询");},
 			  success:function(data){
 				  $("#baseAndFsa_search").text("查询");
    			  $("#queryall_list").html(data);
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
 	
function getParams(){
	 var loginname = $("#userBaseInfo_loginname").val();
	 var realname = $("#userBaseInfo_realname").val();
	 var mobilephone = $("#userBaseInfo_mobilephone").val();
	 var email = $("#userBaseInfo_email").val();
	 var startregdate = $("#userBaseInfo_startregdate").val();
	 var endregdate =	$("#userBaseInfo_endregdate").val();
	 var accounttype = $("#accounttype").val();
	 var isopenfsinfo =	  $("#isopenfsinfo").val();
	 var params = {"accounttype":accounttype,"isopenfsinfo":isopenfsinfo,"loginname":loginname,"realname":realname,"mobilephone":mobilephone,"email":email,"startregdate":startregdate,"endregdate":endregdate};
	 return params;

}
