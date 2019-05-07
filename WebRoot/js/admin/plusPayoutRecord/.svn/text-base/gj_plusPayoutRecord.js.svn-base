$(function(){
	$("#checkAll").on("click",function(){
   		var ifd$ = $(".checkAll");
  		var cid$ = $("#checkAll");
  	 	for(var i = 0;i<ifd$.length;i++){
 			if(cid$[0].checked){
 				$("#checkAllText").text("反选");
 				if(ifd$[i].checked){
   					ifd$[i].checked = cid$[0].checked;
 					ifd$[i].checked = "";
 				}else{
 					ifd$[i].checked = cid$[0].checked;
 				}
 			}else{
 				$("#checkAllText").text("全选");
  				if(ifd$[i].checked){
 					ifd$[i].checked = "";
 				}else{
 					ifd$[i].checked = "";
 					ifd$[i].checked = "true";
 				}
   			}
  		}
	});
});   
var plusPayoutRecord = {
  			//标的增益清算记录查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"560",cancelText:"返回列表",height:"560",
 					content:basePath+"/admin/plusPayoutRecord/detail.action?opid="+opid
 				});
 			},
 			low_operation:function(obj){
 				var opid = $(obj).data("opid");
 				var pporderno = $(obj).data("pporderno");
 				var intprofit = $(obj).data("intprofit");
 				var rorderno = $(obj).data("rorderno");
 				var likevoucherprofit = $(obj).data("likevoucherprofit");
 				var remark = $(obj).data("remark");
   				$("#prd_id").val(opid);
 				$("#prd_pporderno").text(pporderno);
 				$("#prd_rorderno").text(rorderno);
 				$("#prd_intprofit").text(intprofit);
 				$("#prd_likevoucherprofit").text(likevoucherprofit);
 				$("#prd_remark").text(remark);
 				$("#warning-block").hide();
 				$("#myAudit").modal();
 			},
 			//审核
 			low_audit:function(obj){
  				var audit = $(obj).data("audit");
 				var prd_id = $("#prd_id").val();
 				var prd_pporderno = $("#prd_pporderno").text();
 				var prd_rorderno = $("#prd_rorderno").text();
 				var prd_intprofit = $("#prd_intprofit").text();
 				var prd_likevoucherprofit = $("#prd_likevoucherprofit").text();
 				var prd_remark = $("#prd_remark").val();
 				if(prd_remark.length >= 300){
  					$("#warning-block").show();
  					$("#pg_context").text("备注信息字数不能超过300");
 					$("#prd_remark").focus();
 					return false;
 				}else{
 					$("#warning-block").hide();
 				}
   				var params = {"audit":audit,"id":prd_id,"pporderno":prd_pporderno,"rorderno":prd_rorderno,"intprofit":prd_intprofit,"likevoucherprofit":prd_likevoucherprofit,"remark":prd_remark};
    			if(audit == 1){//通过审核
 					$("#audit_operation1").text("审核处理中...");
  				}else if(audit == 0){
  					$("#audit_operation").text("审核处理中...");
 				}
    			$.tzAdminAjax.request({
    				model:"admin/plusPayoutRecord",
    				method:"doAudit.action",
    				error:function(){$("#audit_operation1").text("审核通过");
									$("#audit_operation").text("审核不通过");},
    				callback:function(data){
    					$("#audit_operation1").text("审核通过");
    					$("#audit_operation").text("审核不通过");
    					var obj = $.parseJSON(data);
    					if(obj.result == "fail"){
    						$("#warning-block").show();
    	  					$("#pg_context").text("操作失败！请稍后重新操作...");
    					}else if(obj.result == "success"){
    						alert("操作成功..");
    						$("#myAudit").modal("hide");
    						window.location.href = basePath+"/admin/plusPayoutRecord/list.action";
    					} 
    				}
    			},params);
    			
   			},
 			//查询全部
    		low_findAll:function(){
    			$("#low_findAll").text("查询中...");
   				$("#box").load(basePath+"/admin/plusPayoutRecord/list.action");
   			},
   			//批量操作
   			low_batchgo:function(obj){
   				var ifd$ = $(".checkAll");
   				var mark = $(obj).data("mark");
   				var opid = "";
   				var count = 0;
   				for(var i = 0;i<ifd$.length;i++){
   					if(ifd$[i].checked){
    					count++
   						opid +=  ifd$[i].value +",";
   					}
   				}
   				if(count == 0){
   					alert("请至少选中一个审核");
   					return false;
   				}
   				opid = opid.substring(0,opid.lastIndexOf(","));
   				var content = mark == 1 ? " 批量审核通过 " : " 批量审核不通过 ";
   				$.tzConfirm({
   					title:"批量操作",
   					content:"您选择的是<span class='red'>"+content+"</span>操作",
   					callback:function(ok){
   						 if(ok){
   							 $("#batchgo1").removeAttr("onclick").text("批量处理中...");
   							 $("#batchgo").removeAttr("onclick").text("批量处理中...");
    						 $.tzAdminAjax.request({
   								 model:"admin/plusPayoutRecord",
   								 method:"batchgo.action",
   								 error:function(){$("#batchgo1").attr("onclick","plusPayoutRecord.low_batchgo(this)").text("批量审核通过");
   								 				  $("#batchgo").attr("onclick","plusPayoutRecord.low_batchgo(this)").text("批量审核不通过");},
   								 callback:function(data){
   									$("#batchgo1").attr("onclick","plusPayoutRecord.low_batchgo(this)").text("批量审核通过");
						 			$("#batchgo").attr("onclick","plusPayoutRecord.low_batchgo(this)").text("批量审核不通过");
   									 var obj = $.parseJSON(data);
   									 if(obj.result == "no_null"){
   										 loading("参数错误...",4);
   										 setTimeout(function(){
   											window.location.href = basePath + "/admin/plusPayoutRecord/list.action";
   										 },2000);
   									 }else if(obj.result == "fail"){
   										 loading("处理失败,请稍后重新操作...",4);
   										 setTimeout(function(){
   											window.location.href = basePath + "/admin/plusPayoutRecord/list.action";
   										 },2000);
     								 }else if(obj.result == "success"){
    									 loading("处理成功...",4);
    									 setTimeout(function(){
    											window.location.href = basePath + "/admin/plusPayoutRecord/list.action";
    									},2000);   									 }
   								 }
   							 },{"opid":opid,"mark":mark});
    					}
   					}
    			});
   			},
   			low_reset:function(){
   				$("#inname").val("");
   		 		$("#isaudit").val("");
   		 		$("#isgrant").val("");
   			}
   	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
   		$("#queryall_list").html("数据正在加载中.....");
  		var pageSize = "20";
  		$.tzAdminAjax.request({
  			model:"admin/plusPayoutRecord",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},{"pageSize":pageSize,"pageNo":pageNo,});
   	}
   //模糊搜索
 	function search(obj){
 		var params = getParams();
 		$.tzAdminAjax.request({
  			model:"admin/plusPayoutRecord",
  			method:"template.action",
  			callback:function(data){
  				$("#queryall_list").html(data);
  			}
  		},params);  		  
 	}
 	 
	function getParams(){
 		var inname = $("#inname").val();
 		var isaudit = $("#isaudit").val();
 		var isgrant = $("#isgrant").val();
 		var params = {"inname":inname,"isaudit":isaudit,"isgrant":isgrant};
 		return params;
 	}
 	