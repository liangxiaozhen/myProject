/* 验证备注显示个数*/
/*  	$(function(){
		$("[data-toggle='tooltip']").tooltip({
		html : true
	}) ;
		$(".remark-p").each(function(i) {
		var num = $(this).html();
		if (num.length > 5) {
			$(this).html(num.substr(0, 5) + "...");
		}
	});
});*/


function details(daorderno){
	var action = "admin/userdebtattorn/details.action";
	var params = {
			"daorderno" : daorderno,
	};
	var callback = function(data) {
		$("#mymodal").html(data);
	}
	$.post(action, params, callback);
}
	
	function audit(id){
		var action = "admin/userdebtattorn/queryById.action";
		var params = {
				"id":id
		}
		$.post(action,params,function(data){
			$("#mymodalaudit").html(data);
		});
	}
	//审核通过
	function auditSuccess() {
		var id = $("#audit-id-text").val();
		var remark = $("#audit-remark-text").val();
		var action = "admin/userdebtattorn/audit.action";
		var params = {
			"id" : id,
			"remark" : remark
		};
		$.post(action, params, function(data) {
 				/*if(eval(data)!=undefined){*/
 					if(data=="success"){
 						alert("审核通过");
 					}
 					if(data=="false"){
 						alert("距离下个还款日太近");
 					}
 					refurbish();
 				/*}*/
 				
 			},'json');
	}
	function refurbish() {
		//window.location.href = "${pageContext.request.contextPath}/admin/rechargeRate/query.action?pageNum=${pagehelper.pageNum}";
	  queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
	}
	function queryAllPerson(pageNum, pageSize) {
		alert("点击下一个");
		/*var action = "rechargeScreen.action";
		var num = "";
		var num1=$(".time li.on").attr("data-value");//这句必须在前
		var num2 = $(".auto .on").attr("value");
		if(num2!=="" && num2!==null && num2!== undefined){//假如搜索栏选中,那么点击下一页的时候就翻页搜索栏查询到的翻页,如果没有就是前面标签选择的翻页
			num = num2;
		}else{
			num = num1;
		}
		var starttime=$("#starttime").val();
		var endtime=$("#endtime").val();
		var param = {
				"pageNum":pageNum,
				"moreCode":num,
				"starttime":starttime,
				"endtime":endtime
				};
		 $.post(action,param,function(data){
			 $("#rechargerocord").html(data);
		 });*/
	}
	