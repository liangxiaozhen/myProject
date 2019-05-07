//这是选择收费类型的,显示哪些隐藏哪些
function xuanz() {
	var chargetypeValue = $("#chargetype_select").val();
	if (chargetypeValue == 1) { //假如充值方式为定额的时候
		$("#quota_select").show();
		$("#dis1").hide();
		$("#dis2").hide();
		$("#dis3").hide();
	}
	if (chargetypeValue == 2) {//充值方式为百分比的时候
		$("#quota_select").hide();
		$("#dis1").show();
		$("#dis2").show();
		$("#dis3").show();
	}
}
	function insertRechargeRate(){
		var action = "admin/rechargeRate/insert.action";
		$.post(action,callback);
	}
	var callback = function(data) {
		$("#itemList_table").html(data)
	} 
	
	
		/*
		 * 点击删除按钮执行此函数
		 * 	函数功能：提交	ajax请求
		 */
		function deleteFun(id) {
			/* 删除的时候弹出一个对话框,选择确定的时候执行删除,选择取消的时候返回空*/
			if(confirm("你正在删除id为"+id+"的数据")){
			var action = "admin/rechargeRate/delete.action";
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
				$("#rechargeRateId" + obj.rechargeRateId).remove();
				refurbish();
				
			} else {
				alert("删除失败!");
			}
		}
		
	//请求转发 审核页面UI   --3
		function forwardAuditUI(id) {
			var action = "admin/rechargeRate/queryById.action";
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
			var action = "admin/rechargeRate/auditrate.action";
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
			var action="admin/rechargeRate/isUse.action";
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
			var action="admin/rechargeRate/cancelUse.action";
			var params={
				"id":id
			};
			$.post(action,params,function(data){
				refurbish();
			});
		}		
/*//设置窗口可以拖拽
window.onload=function (){
	var oDiv1=document.getElementById('myModal');
	var oDiv2=document.getElementById('auditModal');
	var oDiv3=document.getElementById('isuseModal');
	var oDiv4=document.getElementById('cancelUseModal');
	var disX=0;
	var disY=0;
	if(oDiv1){
		 drag(oDiv1);
	}
	if(oDiv2){
		drag(oDiv2);
	}
	if(oDiv3){
		drag(oDiv3);
	}
	if(oDiv4){
		drag(oDiv4);
	}
	
	
};
function drag(arg){
	arg.onmousedown=function (ev){
		var oEvent=ev||event;
		
		disX=oEvent.clientX-arg.offsetLeft;
		disY=oEvent.clientY-arg.offsetTop;
		
		document.onmousemove=function (ev){
			var oEvent=ev||event;
			
			arg.style.left=oEvent.clientX-disX+'px';
			arg.style.top=oEvent.clientY-disY+'px';
		};
		
		document.onmouseup=function (){
			document.onmousemove=null;
			document.onmouseup=null;
		};
		
		return false;
	};
}*/


	