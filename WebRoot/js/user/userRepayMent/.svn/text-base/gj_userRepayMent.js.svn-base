$(function(){
	 $(".tzui-tips").tzTip();
});
   	var userRepayMent = {
   			//查询全部
  			low_findAll:function(){
  				$("#findAll").text("查询中...");
   				$("#box").load(basePath+"/user/repayMent/list.action");
   			}
  	};
   
     //分页查询通用方法
   	var queryAllPersonTimer = null;
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var param = getMetaDate();
  		param.pageSize = "20";
  		param.pageNo = pageNo;
  		clearTimeout(queryAllPersonTimer);
 		queryAllPersonTimer = setTimeout(function(){
 			$.ajax({
 				type:"post",
 				data:param,
 				url:basePath+"/user/repayMent/template.action",
 				success:function(data){
 					if(data == "logout"){
						window.location.href=basePath+"/user/tologin.action";
					}
 					$("#queryall_list").html(data);
 					$(".tzui-tips").tzTip();
  				}
 			});
  		},200);
  	}
   //模糊搜索
 	var searchTimer = null;
 	function search(obj){
 		$("#userRepatMent_search").text("查询中...");
  		clearTimeout(searchTimer);
		searchTimer = setTimeout(function(){
			$.ajax({
				type:"post",
				url:basePath+"/user/repayMent/template.action",
				data:getMetaDate(),
				success:function(data){
					if(data == "logout"){
						window.location.href=basePath+"/user/tologin.action";
					}
					$("#userRepatMent_search").text("查询");
					$("#queryall_list").html(data);
					$(".tzui-tips").tzTip();
 				}
			});
		},200); 
 	}
  	
 	function getMetaDate(){
 		var isaudit = $("#userRepayMent_isaudit").val();
 		var rmode = $("#userRepayMent_rmode").val();
 		var isdarepay = $("#userRepayMent_isdarepay").val();
 		var tname = $("#userRepayMent_tname").val();
 		var rtime = $("#userRepayMent_rtime").val();
 		var repaystatus = $("#userRepayMent_repaystatus").val();
 		var datamark = $("#userRepayMent_datamark").val();
      	var params = {"isaudit":isaudit,"rmode":rmode,"isdarepay":isdarepay,"tenderitemtenderid.tname":tname,"rtime":rtime,"repaystatus":repaystatus,"datemark":datamark};
   		return params;
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
 	function userRepatMent_reset(){
 		$("#userRepayMent_isaudit").val("");
 		$("#userRepayMent_rmode").val("");
 		$("#userRepayMent_isdarepay").val("");
 		$("#userRepayMent_tname").val("");
 		$("#userRepayMent_rtime").val("");
 		$("#userRepayMent_repaystatus").val("");
 		$("#userRepayMent_datamark").val(""); 
  	}
