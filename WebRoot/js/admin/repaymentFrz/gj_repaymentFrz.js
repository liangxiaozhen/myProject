$(function(){
	 $(".tzui-tips").tzTip();
});
   	var repaymentFrz = {
   			low_detail:function(obj){
   				var opid = $(obj).data("opid");
  				$.tzIframe({width:"1280",cancelText:"返回列表",height:"460",title:"批次还款详情信息",
 					content:basePath+"/admin/repaymentFrz/detail.action?opid="+opid,callback:function(iframe,$dialog,opts){
 						if(iframe){
 							$dialog.next().remove();
 							$dialog.remove();
 						}
 					} 
 				});
   			},
   			//点击标号查询
   			low_tnobulr:function(obj){
				var tno = $(obj).data("tno");
				$("#tno").val(tno);
				$.tzAdminAjax.request({
		  			model:"admin/repaymentFrz",
		  			method:"template.action",
		  			callback:function(data){
		  				$("#queryall_list").html(data);
		  			}
		  		},{"product":tno});
			},
			//点击标号查询[查看]
   			low_tnobulrview:function(obj){
				var tno = $(obj).data("tno");
				$("#tno").val(tno);
				$.tzAdminAjax.request({
		  			model:"admin/repaymentFrz",
		  			method:"templatebyview.action",
		  			callback:function(data){
		  				$("#queryall_list").html(data);
		  			}
		  		},{"product":tno});
			},
			//审核
			low_isaudit:function(obj){
				var opid = $("#isauditModal_opid").val();
				var status = $(obj).data("status");
  				$("#low_isaudit_1").text("处理中...").removeAttr("onclick");
				$("#low_isaudit_0").text("处理中...").removeAttr("onclick");
				$.tzAdminAjax.request({
		  			model:"admin/repaymentFrz",
		  			method:"isaudit.action",
		  			error:function(){
		  				$("#low_isaudit_1").text("审核通过").attr("onclick","repaymentFrz.low_isaudit(this)");
		  				$("#low_isaudit_0").text("审核失败").attr("onclick","repaymentFrz.low_isaudit(this)");
		  			},
		  			callback:function(data){
		  				$("#low_isaudit_1").text("审核通过").attr("onclick","repaymentFrz.low_isaudit(this)");
						$("#low_isaudit_0").text("审核失败").attr("onclick","repaymentFrz.low_isaudit(this)");
						$("#isauditModal").modal("hide");
		  				 var obj = $.parseJSON(data);
		  				 if(obj.result =="success"){
		  					 alert(obj.message);
		  					 window.location.href = basePath + "/admin/repaymentFrz/list.action";
		  				 }else{
		  					 alert(obj.message);
		  					 window.location.href = basePath + "/admin/repaymentFrz/list.action";
 		  				 }
		  			}
		  		},{"opid":opid,"status":status});
			},
			//显示审核modal
			low_isauditModalShow:function(obj){
				var opid = $(obj).data("opid");
				var tno = $(obj).data("tno");
				var batchno = $(obj).data("batchno");
				var realname = $(obj).data("realname");
				var loginname = $(obj).data("loginname");
				$("#isauditModal_opid").val(opid);
				$("#isauditModal_tno").text(tno);
				$("#isauditModal_batchno").text(batchno);
				$("#isauditModal_loginname").text(loginname);
				$("#isauditModal_realname").text(realname);
 				$("#isauditModal").modal("show");
			},
			//文件上传
			low_repayMentFileUpload:function(obj){
 				var opid = $(obj).data("opid");
  				$.tzAdminAjax.request({
		  			model:"admin/repaymentFrz",
		  			method:"repayMentFileUpload.action",
 		  			callback:function(data){
 		  				 var obj = $.parseJSON(data);
		  				 if(obj.result =="success"){
		  					 alert(obj.message);
		  					 window.location.href = basePath + "/admin/repaymentFrz/list.action";
		  				 }else{
		  					 alert(obj.message);
		  					 window.location.href = basePath + "/admin/repaymentFrz/list.action";
 		  				 }
		  			}
		  		},{"opid":opid});
			},
			//解冻
			low_unfreezeDeal:function(obj){
				var opid = $(obj).data("opid");
				$("#low_unfreezeDeal_"+opid).text("解冻中...").removeAttr("onclick");
				$.tzAdminAjax.request({
		  			model:"admin/repaymentFrz",
		  			method:"unfreezeDeal.action",
		  			error:function(){$("#low_unfreezeDeal_"+opid).text("解冻").attr("onclick","repaymentFrz.low_unfreezeDeal(this)");},
 		  			callback:function(data){
 		  				 var obj = $.parseJSON(data);
 		  				 $("#low_unfreezeDeal_"+opid).text("解冻").attr("onclick","repaymentFrz.low_unfreezeDeal(this)");
		  				 if(obj.result =="success"){
		  					 alert(obj.message);
		  					 window.location.href = basePath + "/admin/repaymentFrz/list.action";
		  				 }else{
		  					 alert(obj.message);
		  					 window.location.href = basePath + "/admin/repaymentFrz/list.action";
 		  				 }
		  			}
		  		},{"opid":opid});
			},
			//重置
			low_reset:function(obj){
				$("#isproxyrepay").val("");
		 		$("#isoverdue").val("");
		 		$("#isahead").val("");
		 		$("#isdarepay").val("");
		 		$("#status").val("");
		 		$("#tno").val("");
		 		$("#tname").val("");
		 		$("#outloginname").val("");
		 		$("#rbatchno").val("");
			}
   	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = getParams();
  		params.pageSize = "20";
  		params.pageNo = pageNo;
 		$.tzAdminAjax.request({
  			model:"admin/repaymentFrz",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
   	}
 	
 	function search(){
 		$("#repaymentFrz_search").text("查询中...");
 		var params = getParams();
  		$.tzAdminAjax.request({
  			model:"admin/repaymentFrz",
  			method:"template.action",
  			callback:function(data){
  				$("#repaymentFrz_search").text("查询");
  				$("#queryall_list").html(data);
  			}
  		},params);
 	}
 	
 	function getParams(){
 		var isproxyrepay = $("#isproxyrepay").val().trim();
 		var isoverdue = $("#isoverdue").val().trim();
 		var isahead = $("#isahead").val().trim();
 		var isdarepay = $("#isdarepay").val().trim();
 		var status = $("#status").val().trim();
 		var tno = $("#tno").val().trim();
 		var tname = $("#tname").val().trim();
 		var outloginname = $("#outloginname").val().trim();
 		var rbatchno = $("#rbatchno").val().trim();
 		var params = {"isproxyrepay":isproxyrepay,"isoverdue":isoverdue,"isahead":isahead,"isdarepay":isdarepay,"status":status,"tno":tno,"tname":tname,"outloginname":outloginname,"batchno":rbatchno};
 		return params;
 	}
 	
 	 //分页查询通用方法[查看]
 	function queryAllPersonByView(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var params = getParamsByView();
  		params.pageSize = "20";
  		params.pageNo = pageNo;
 		$.tzAdminAjax.request({
  			model:"admin/repaymentFrz",
  			method:"templatebyview.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);
   	}
 	
 	function searchByView(){
 		$("#repaymentFrz_search").text("查询中...");
 		var params = getParamsByView();
  		$.tzAdminAjax.request({
  			model:"admin/repaymentFrz",
  			method:"templatebyview.action",
  			callback:function(data){
  				$("#repaymentFrz_search").text("查询");
  				$("#queryall_list").html(data);
  			}
  		},params);
 	}
 	
 	function getParamsByView(){
 		var isproxyrepay = $("#isproxyrepay").val().trim();
 		var isoverdue = $("#isoverdue").val().trim();
 		var isahead = $("#isahead").val().trim();
 		var isdarepay = $("#isdarepay").val().trim();
 		var status = $("#status").val().trim();
 		var tno = $("#tno").val().trim();
 		var tname = $("#tname").val().trim();
 		var outloginname = $("#outloginname").val().trim();
 		var rbatchno = $("#rbatchno").val().trim();
 		var params = {"isproxyrepay":isproxyrepay,"isoverdue":isoverdue,"isahead":isahead,"isdarepay":isdarepay,"status":status,"tno":tno,"tname":tname,"outloginname":outloginname,"batchno":rbatchno};
 		return params;
 	}
 	
 	
 	function isEmpty(val) {
 		val = $.trim(val);
 		if (val == null)
 			return true;
 		if (val == undefined || val == 'undefined')
 			return true;
 		if (val == "")
 			return true;
 		if (val.length == 0)
 			return true;
 		if (!/[^(^\s*)|(\s*$)]/.test(val))
 			return true;
 		return false;
 	}
 