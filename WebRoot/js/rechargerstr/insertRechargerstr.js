//验证自付他付
$(".insert-selfpayratio-radio").change(function() {
	var $selfpayratioValue = $("input[name='selfpayratio']:checked").val();
	if ($selfpayratioValue == 0) { //假如是他付
		$("#df").show();
		$("#dfbl").show();
		$("#proxypayratio").val("100");
	}
	if ($selfpayratioValue == 100) {//假如是自付
		$("#df").hide();
		$("#dfbl").hide();
		$("#proxypayratio").val("0");
	}
});
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

$(function() {
		//选择会员等级div隐藏
		$("#insert-ugrade-checkbox-div").hide();
		//比例 div 隐藏
		$("#insert-rate-div").hide();
		//等级 radio change监听事件
		$(".insert-ugrade-radio").change(function() {
			var $radioVal = $("input[name='ugrade']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 1) {
				$("#insert-ugrade-checkbox-div").hide();
				//取消提示语
				$("#insert-ugrade-lb").html("");
			} else {
				$("#insert-ugrade-checkbox-div").show();
			}
		});
		//选择会员等级div隐藏
		$("#insert-removeno-checkbox-div").hide();
		//比例 div 隐藏
		$("#insert-rate-div").hide();
		//等级 radio change监听事件
		$(".insert-removeno-radio").change(function() {
			var $radioVal = $("input[name='removenameno']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 1) {
				$("#insert-removeno-checkbox-div").hide();
				//取消提示语
				$("#insert-removeno-lb").html("");
			} else {
				$("#insert-removeno-checkbox-div").show();
			}
		});

	//单击 选择等级checkbox 取消提示语
	$(".checkbox-inline").click(function() {
		$("#insert_ugrade_lb").html("");
	});
		//排除人员名单
   $(".checkbox-inline").click(function() {
			$("#insert_removenameno_lb").html("");
		});
	//充值费率 输入 取消提示语
	$("#paycompany").change(function() {
		$("#insert_paycompany_lb").html("");
	});
	//日充值金额text输入 取消提示语
	$("#daymoneyrest").change(function() {
		$("#insert_daymoneyrest_lb").html("");
	});
	//单笔最低充值金额text输入 取消提示语
	$("#lowestmoney").change(function() {
		$("#insert_lowestmoney_lb").html("");
	});
	//单笔最高充值金额text输入 取消提示语
	$("#hightestmoney").change(function() {
		$("#insert_hightestmoney_lb").html("");
	});
	//代付子账号text输入 取消提示语
	$("#proxypayman").change(function() {
		$("#insert_proxypayman_lb").html("");
	});
	var  selfpayratio = $("#selfpayratio").val();
	var  proxypayratio = $("#proxypayratio").val();
	if(selfpayratio=="100"){
		$("#proxypayratio").val("0");
	}
	if(selfpayratio=="0"){
		$("#proxypayratio").val("100");
	}
});

//验证添加
function InsertForm() {
	//验证输入的数字不是00001这种形式的
	var number = /^([1-9]\d{0,7}|0)(\.\d{1,2})?$/;
	//验证会员等级选择第二个时又不选择会员等级的情况下
	var $ugradeVal = $("input[name='ugrade']:checked").val();
	//验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#insert_ugrade_lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}
	//验证支付公司	
	var  paycompany = $("#paycompany").val();
	if(paycompany==null || paycompany==''){
		$("#insert_paycompany_lb").html("<font style='color:red;'>请输入支付公司</font>");
		return false;
	}
	//验证日充值限额
	var  daymoneyrest = $("#daymoneyrest").val();
		if(daymoneyrest==null || daymoneyrest==''){
			$("#insert_daymoneyrest_lb").html("<font style='color:red;'>请输入日充值限额</font>");
			return false;
		}
	//验证单笔充值最低金额
	var  lowestmoney = $("#lowestmoney").val();
		if(lowestmoney==null || lowestmoney==''){
				$("#insert_lowestmoney_lb").html("<font style='color:red;'>请输入单笔充值最低金额</font>");
				return false;
		}
		if(parseFloat(lowestmoney) >= parseFloat(daymoneyrest)){
			$("#insert_lowestmoney_lb").html("<font style='color:red;'>单笔充值最低金额不能大于日充值限额</font>");
			return false;
		}
	//验证单笔最高充值金额	
		var  hightestmoney = $("#hightestmoney").val();
		if(hightestmoney==null || hightestmoney==''){
			$("#insert_hightestmoney_lb").html("<font style='color:red;'>请输入单笔最高充值金额</font>");
			return false;
		}
		if(parseFloat(hightestmoney)>= parseFloat(daymoneyrest)){
			$("#insert_hightestmoney_lb").html("<font style='color:red;'>单笔最高充值金额不能大于日充值限额</font>");
			return false;
		}
		if(parseFloat(hightestmoney)<= parseFloat(lowestmoney)){
			$("#insert_hightestmoney_lb").html("<font style='color:red;'>单笔最高充值金额不能小于单笔最低充值金额</font>");
			return false;
		}
		//验证会员等级选择第二个时又不选择会员等级的情况下
		var $removenamenoVal = $("input[name='removenameno']:checked").val();
		//验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
		if ($removenamenoVal == 2 && $("input[name='removenamenos']:checked").size() == 0) {
			$("#insert_removenameno_lb").html("<font color='red'> * 请选择排除人名单</font>");
			return false;
		}
	
		var $selfpayratio = $("input[name='selfpayratio']:checked").val();
		var $proxypayman = $("#proxypayman").val();
		if($selfpayratio==0){
			if($proxypayman==0){
				$("#insert_proxypayman_lb").html("<font color='red'> * 请选择代付子账号</font>");
				return false;
			}
		}

	return true;
	
}