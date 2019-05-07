$(function() {
		//选择会员等级div隐藏
		//$("#update-ugrade-checkbox-div").hide();
		//比例 div 隐藏
		$("#update-rate-div").hide();
		//等级 radio change监听事件
		$(".update-ugrade-radio").change(function() {
			var $radioVal = $("input[name='ugrade']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 1) {
				$("#update-ugrade-checkbox-div").hide();
				//取消提示语
				$("#update-ugrade-lb").html("");
			} else {
				$("#update-ugrade-checkbox-div").show();
			}
		});
		//比例 div 隐藏
		$("#update-rate-div").hide();
		//等级 radio change监听事件
		$(".update-removeno-radio").change(function() {
			var $radioVal = $("input[name='removenameno']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 1) {
				$("#update-removeno-checkbox-div").hide();
				//取消提示语
				$("#update-removeno-lb").html("");
			} else {
				$("#update-removeno-checkbox-div").show();
			}
		});
		//编辑的时候会员等级的默认选中
		 var ulist = $("#ulist").val();
    	if(ulist.length>0){
    		var u = ulist.split(",");
    		$("#ugradeselect").attr("checked",true);
    		$.each(u,function(index,value){
    			$("#update-ugrade-checkbox-div input[type='checkbox']").each(function(){
    					if($(this).val()==value){
    					$(this).attr("checked",true);
    				}
    			});
    		});
    	}  
    	//编辑的时候排除人名单的默认选中
    	 var removeNos = $("#removenos").val();
    	if(removeNos.length>0){
    		var r = removeNos.split(",");
    		$.each(r,function(index,value){
    			$("#update-removeno-checkbox-div input[type='checkbox']").each(function(){
    					if($(this).val()==value){
    					$(this).attr("checked",true);
    				}
    			});
    		});
    	}  	
    	var $radioVal = $("#selfpayratio").val();
    	if ($radioVal == 0) {//表示他付
    		$("#proxypaymandiv").show();
    		$("#proxypayratiodiv").show();
    		$("#proxypayratio").val("100");
    	} else {//表示自付
    		$("#proxypaymandiv").hide();
    		$("#proxypayratiodiv").hide();
    		$("#proxypayratio").val("0");
    	}
	//单击 选择等级checkbox 取消提示语
	$(".checkbox-inline").click(function() {
		$("#update_ugrade_lb").html("");
	});
		//排除人员名单
   $(".checkbox-inline").click(function() {
			$("#update_removeno_lb").html("");
		});
	//充值费率 输入 取消提示语
	$("#paypany").change(function() {
		$("#update_paycompany_lb").html("");
	});
	//日充值金额text输入 取消提示语
	$("#daymoney").change(function() {
		$("#update_daymoneyrest_lb").html("");
	});
	//单笔最低充值金额text输入 取消提示语
	$("#zxmoney").change(function() {
		$("#update_lowestmoney_lb").html("");
	});
	//单笔最高充值金额text输入 取消提示语
	$("#hightmoney").change(function() {
		$("#update_hightestmoney_lb").html("");
	});
	//充值人自付验证text输入 取消提示语
	$("#selfpayratio").change(function() {
		$("#update_selfpayratio_lb").html("");
	});
	//设置人
	$("#addman").change(function() {
		$("#update_addman_lb").html("");
	});
	//设置时间
	$("#addtimeStr").change(function() {
		$("#update_addtime_lb").html("");
	});
	//审核人
	$("#auditman").change(function() {
		$("#update_auditman_lb").html("");
	});
	//审核时间
	$("#audittimeStr").change(function() {
		$("#update_audittime_lb").html("");
	});
	$("#proxypayman_edit").change(function() {
		$("#update_proxypayman_lb").html("");
	});

});
//验证充值人自付比例来显示代付人和代付比例
/*$(".update-selfpayratio-radio").change(function() {
	var $radioVal = $("input[name='selfpayratio']:checked").val();
	//当选中全部等级时 选择等级div隐藏，反之显示
	if ($radioVal == 0) {
		$("#proxypaymandiv").show();
		$("#proxypayratiodiv").show();
		$("#proxypayratio").val("100");
	} else {
		$("#proxypaymandiv").hide();
		$("#proxypayratiodiv").hide();
		$("#proxypayratio").val("0");
	}
});*/

//根据充值方式选择手续费类型
function select(){
	var rechartype = $("#rechartype").val();
	if(rechartype == 0){
		$('#feetype option[value="1"]').remove();
		$('#feetype option[value="2"]').remove();
		$("#feetype").append("<option value='0'>无</option>"); 
	}else{
		$("#feetype").empty();
		$('#feetype option[value="0"]').remove();
		$("#feetype").append("<option value='1'>全额自付</option>"); 
		$("#feetype").append("<option value='2'>全额他付</option>"); 
	}
}
function xuanze(){
	var $radioVal = $("#selfpayratio").val();
	if ($radioVal == 0) {//表示他付
		$("#proxypaymandiv").show();
		$("#proxypayratiodiv").show();
		$("#proxypayratio").val("100");
	} else {//表示自付
		$("#proxypaymandiv").hide();
		$("#proxypayratiodiv").hide();
		$("#proxypayratio").val("0");
		//设置自付的时候把代付子账户号归0
		$("#proxypayman_edit").val("0");
	}
}
//修改的验证
function  UpdateForm(){
	//验证输入的数字不是00001这种形式的
	var number = /^([1-9]\d{0,7}|0)(\.\d{1,2})?$/;
	//验证会员等级选择第二个时又不选择会员等级的情况下
	var $ugradeVal = $("input[name='ugrade']:checked").val();
	//验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#update_ugrade_lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}
	//验证支付公司	
	var  paycompany = $("#paypany").val();
	if(paycompany==null || paycompany==''){
		$("#update_paycompany_lb").html("<font style='color:red;'>请输入支付公司</font>");
		return false;
	}
	//验证日充值限额
	var  daymoneyrest = $("#daymoney").val();
		if(daymoneyrest==null || daymoneyrest==''){
			$("#update_daymoneyrest_lb").html("<font style='color:red;'>请输入日充值限额</font>");
			return false;
		}
		//验证充值人自付比例
		var  selfpayratio = $("#selfpayratio").val();
			if( parseFloat(selfpayratio)<0 ||  parseFloat(selfpayratio)>100){
				$("#update_selfpayratio_lb").html("<font style='color:red;'>请输入0到100之间的数字</font>");
				return false;
			}
			if(selfpayratio==null || selfpayratio==''){
				$("#update_selfpayratio_lb").html("<font style='color:red;'>请输入相应的比例</font>");
				return false;
			}
	//验证单笔充值最低金额
	var  lowestmoney = $("#zxmoney").val();
		if(lowestmoney==null || lowestmoney==''){
				$("#update_lowestmoney_lb").html("<font style='color:red;'>请输入单笔充值最低金额</font>");
				return false;
		}
		if(parseFloat(lowestmoney) >= parseFloat(daymoneyrest)){
			$("#update_lowestmoney_lb").html("<font style='color:red;'>单笔充值最低金额不能大于日充值限额</font>");
			return false;
		}
	//验证单笔最高充值金额	
		var  hightestmoney = $("#hightmoney").val();
		if(hightestmoney==null || hightestmoney==''){
			$("#update_hightestmoney_lb").html("<font style='color:red;'>请输入单笔最高充值金额</font>");
			return false;
		}
		if(parseFloat(hightestmoney)>= parseFloat(daymoneyrest)){
			$("#update_hightestmoney_lb").html("<font style='color:red;'>单笔最高充值金额不能大于日充值限额</font>");
			return false;
		}
		if(parseFloat(hightestmoney)<= parseFloat(lowestmoney)){
			$("#update_hightestmoney_lb").html("<font style='color:red;'>单笔最高充值金额不能小于单笔最低充值金额</font>");
			return false;
		}
		//验证会员等级选择第二个时又不选择会员等级的情况下
		var $removenamenoVal = $("input[name='removenameno']:checked").val();
		//验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
		if ($removenamenoVal == 2 && $("input[name='removenamenos']:checked").size() == 0) {
			$("#update_removeno_lb").html("<font color='red'> * 请选择排除人名单</font>");
			return false;
		}
	//验证设置人
	var  addman = $("#addman").val();
	if(addman==null || addman==''){
		$("#update_addman_lb").html("<font style='color:red;'>请填写设置人</font>");
		return false;
	}
	//验证审核人
	var  auditman = $("#auditman").val();
	if(auditman==null || auditman==''){
		$("#update_auditman_lb").html("<font style='color:red;'>请填写审核人</font>");
		return false;
	}
	//验证设置时间
	var  addtime = $("#addtimeStr").val();
	if(addtime==null || addtime==''){
		$("#update_addtime_lb").html("<font style='color:red;'>请选择时间</font>");
		return false;
	}
	//验证审核时间
	var  audittime = $("#audittimeStr").val();
	if(audittime==null || audittime==''){
		$("#update_audittime_lb").html("<font style='color:red;'>请选择审核时间</font>");
		return false;
	}
	var $selfpayratio = $("#selfpayratio").val();
	var $proxypayman = $("#proxypayman_edit").val();
	if($selfpayratio=="0"){
		if($proxypayman=="0"){
			$("#update_proxypayman_lb").html("<font color='red'> * 请选择代付子账号</font>");
			return false;
		}
	}
	return true;
}

