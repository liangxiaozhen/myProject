//这是选择收费类型的,显示哪些隐藏哪些
function xuanz() {
	var chargetypeValue = $("#chargetype_select").val();
	alert(chargetypeValue);
	if (chargetypeValue == 1) { //假如充值方式为定额的时候
		$("#InputsWrapperone").show();
		$("#ds1").hide();
		$("#ds2").hide();
		$("#ds3").hide();
	}
	if (chargetypeValue == 2) {//充值方式为百分比的时候
		$("#InputsWrapperone").hide();
		$("#ds1").show();
		$("#ds2").show();
		$("#ds3").show();
	}
}
//点击充值方式的时候限制手续费收费类型
function rechartypexuanz(){
	var rechartype = $("#rechartype").val();//充值方式
	var chargetypeValue = $("#chargetype_select").val();//收费类型:1为定额 2为百分比
	if(rechartype==2){//企业网银的时候只能定额收费
		$("#chargetype_select option[value='1']").remove(); //删除Select中索引值为0的Option(第一个) 
		$("#chargetype_select option[value='2']").remove(); //删除Select中索引值为0的Option(第一个) 
		$("#chargetype_select").append("<option value='1'>定额</option>");
		$("#InputsWrapperone").show();
		$("#ds1").hide();
		$("#ds2").hide();
		$("#ds3").hide();
	}else{
		$("#chargetype_select option[value='1']").remove(); //删除Select中索引值为0的Option(第一个) 
		$("#chargetype_select option[value='2']").remove(); //删除Select中索引值为0的Option(第一个) 
		if(chargetypeValue==1){
			$("#InputsWrapperone").show();
			$("#ds1").hide();
			$("#ds2").hide();
			$("#ds3").hide();
			$("#chargetype_select").append("<option value='1' selected='selected'>定额</option>");
			$("#chargetype_select").append("<option value='2'>百分比</option>"); //为Select追加一个Option(下拉项) 
		}else{
			$("#InputsWrapperone").hide();
			$("#ds1").show();
			$("#ds2").show();
			$("#ds3").show();
			$("#chargetype_select").append("<option value='1' >定额</option>");
			$("#chargetype_select").append("<option value='2' selected='selected'>百分比</option>"); //为Select追加一个Option(下拉项) 
		}
	}
}


$(function() {
	alert("w jinlai l ");
	//这是为了点击编辑的时候默认是什么
	var m = $("#chargetype_select").val();
	alert(m);
	if (m == 1) {
		$("#InputsWrapperone").show();
		$("#ds1").hide();
		$("#ds2").hide();
		$("#ds3").hide();

	}
	if (m == 2) {
		$("#InputsWrapperone").hide();
		$("#ds1").show();
		$("#ds2").show();
		$("#ds3").show();
	}

	//单击 选择等级checkbox 取消提示语
	$(".checkbox-inline").click(function() {
		$("#update_ugrade_lb").html("");
	});
	//充值费率 输入 取消提示语
	$("#paycompany").change(function() {
		$("#update_paycompany_lb").html("");
	});
	//最小金额text输入 取消提示语
	$("#minMoney").change(function() {
		$("#update_money_lb").html("");
	});
	//最大金额text输入 取消提示语
	$("#maxMoney").change(function() {
		$("#update_money_lb").html("");
	});
	//定额 输入  取消提示语
	$("#quoTa").change(function() {
		$("#update_quota_lb").html("");
	});
	//充值费率 输入 取消提示语
	$("#charRate").change(function() {
		$("#update_charrate_lb").html("");
	});
	//充值最低收费金额
	$("#minFee").change(function() {
		$("#update_feeMoney_lb").html("");
	});
	//充值最高收费金额
	$("#maxFee").change(function() {
		$("#update_feeMoney_lb").html("");
	});
	//设置人
	$("#addman").change(function() {
		$("#update_addman_lb").html("");
	});
	//审核人
	$("#auditman").change(function() {
		$("#update_auditman_lb").html("");
	});
});

//修改的验证
function  UpdateForm(){
	//验证会员等级选择第二个时又不选择会员等级的情况下
	var $ugradeVal = $("input[name='ugrade']:checked").val();
	//验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#update_ugrade_lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}
	//验证最小金额
	var  minmoney = $("#minMoney").val();
	var number = /^([1-9]\d{0,7}|0)(\.\d{1,2})?$/;
		if(minmoney==null || minmoney==''|| minmoney.length == 0 ||!number.test(minmoney)){
			$("#update_money_lb").html("<font style='color:red;'>请输入最小金额</font>");
			return false;
		}
	
	//验证最大金额必须大于最小金额
		var  maxmoney = $("#maxMoney").val();
		if(maxmoney==null || maxmoney==''|| parseFloat(maxmoney) <= parseFloat(minmoney) || maxmoney.length == 0 ||!number.test(maxmoney)){
			$("#update_money_lb").html("<font style='color:red;'>最大金额必须大于最小金额</font>");
			return false;
		}
	//验证支付公司	
	var  paycompany = $("#paycompany").val();
	if(paycompany==null || paycompany==''){
		$("#update_paycompany_lb").html("<font style='color:red;'>请输入支付公司</font>");
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
	//验证收费类型
	var chargetype =  $("#chargetype_select").val();
	if(chargetype==1){
		//验证定额
		var  quota = $("#quoTa").val();
		if(quota==null || quota==''){
			$("#update_quota_lb").html("<font style='color:red;'>请输入固定金额</font>");
			return false;
		}else{
			if(!number.test(minmoney)){
				$("#update_money_lb").html("<font style='color:red;'>请输入有效数字quota</font>");
				return false;
			}
		}
	}
	if(chargetype==2){
		//验证充值费率
		var  charrate = $("#charRate").val();
		if(charrate==null || charrate==''){
			$("#update_charrate_lb").html("<font style='color:red;'>请输入充值费率</font>");
			return false;
		}else{
			if(!number.test(minmoney)){
				$("#update_money_lb").html("<font style='color:red;'>请输入有效数字charrate</font>");
				return false;
			}
		}
		//验证最小收费金额
		var  minfee = $("#minFee").val();
		if(minfee==null || minfee==''){
			$("#update_feeMoney_lb").html("<font style='color:red;'>请输入最小收费金额</font>");
			return false;
		}else{
			if(!number.test(minmoney)){
				$("#update_money_lb").html("<font style='color:red;'>请输入有效数字minfee</font>");
				return false;
			}
		}
		//验证最大收费金额
		var  maxFee = $("#maxFee").val();
		if(parseFloat(maxFee)<=parseFloat(minfee) || maxFee==null || maxFee==''){
			$("#update_feeMoney_lb").html("<font style='color:red;'>最大收费金额必须大于最小收费金额</font>");
			return false;
		}else{
			if(!number.test(minmoney)){
				$("#update_money_lb").html("<font style='color:red;'>请输入有效数字maxfee</font>");
				return false;
			}
		}
	}
	return true;
}
