/**
 * 添加
 */
function insertRechargeRstr() {
  			var action = "admin/rechargeRstr/insert.action";
  		    $.post(action,function(data) {
  	  			$("#itemList_table").html(data);
  		    });
  		}
/*详情*/
 function detalis(id){
		var  action = "admin/rechargeRstr/queryDetails.action";
		var params = {
				"id" : id
			};
			$.post(action,params,function queryRechargeRate_success_callback(returnData) {
				$(".modal-body").html(returnData);
			});
	}

	/* 编辑 */
	function editFun(id){
  			window.location.href="admin/rechargeRstr/queryEdits.action?id="+id
	}	 
/*
 * 点击删除按钮执行此函数
 * 	函数功能：提交	ajax请求
 */
function deleteFun(id) {
	/* 删除的时候弹出一个对话框,选择确定的时候执行删除,选择取消的时候返回空*/
	if(confirm("你正在删除id为"+id+"的数据")){
	var action = "admin/rechargeRstr/delete.action";
	var params = {
		"id" : id
	};
	$.post(action, params, deleteRechargeRate_success_callback);
	}else{
		return null;
	}
}
/*
 * 删除成功后的回调函数 returnData标识contorller返回的jsonStr字符串
 */
function deleteRechargeRate_success_callback(returnData) {
	var obj = $.parseJSON(returnData);
	if (obj.result == "success") {
		$("#rechargeRstrId" + obj.rechargeRateId).remove();
		refurbish();
		
	} else {
		alert("删除失败!");
		alert(obj.result);
	}
}

//请求转发 审核页面UI   --3
	function forwardAuditUI(id) {
		var action = "admin/rechargeRstr/queryById.action";
		var params = {
			"id" : id,
		};
		var callback = function(data) {
			$("#audit-modal-body").html(data);
		}
		$.post(action, params, callback);
	}
//审核通过
	function auditSuccess() {
		var id = $("#audit-id-text").val();
		var remark = $("#audit-remark-text").val();
		var action = "admin/rechargeRstr/auditrate.action";
		var params = {
			"id" : id,
			"remark" : remark
		};
		$.post(action, params, function(data) {
 				if(eval(data)!=undefined){
 					alert(eval(data));
 				}else{
 					refurbish();
 				}
 			});
	}
	function forwardIsUserUI(id) {//启用模态框
		$("#isuseidlb").html("<font color='red'>" + id + "</font>");
		$("#isuseid").val(id);
	}

	function isUse(){//启用
		var id=$("#isuseid").val();
		var action="admin/rechargeRstr/isUse.action";
		var params={
			"id":id
		};
		$.post(action,params,function(data){
			alert("提示：启用成功。");
			refurbish();
		});
	}

	function forwardCancelUserUI(id) {//停用模态框
		$("#canceluseidlb").html("<font color='red'>" + id + "</font>");
		$("#canceluseid").val(id);
	}

	function cancelUse(){//停用
		var id=$("#canceluseid").val();
		var action="admin/rechargeRstr/cancelUse.action";
		var params={
			"id":id
		};
		$.post(action,params,function(data){
			refurbish();
		});
	}	