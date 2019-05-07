//这是选择收费类型的,显示哪些隐藏哪些
function xuanz() {
	var chargetypeValue = $("#chargetype_select").val();
	
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


//添加定额金额段和定额手续费
$(document).ready(
		function() {
			//设置一个最大值,也就是最多可以添加几条数据
			var max = 25;
			//获取添加多个参数的长度
			var quotaAll = $("#quotaAll");
			var addbtn = $("#addbtn");
			var x = quotaAll.length; 
			$(addbtn).click(
				function(e){
					if(x<=max){
						var oDiv = $('<div class="moneyInput"></div>');
						oDiv.html(
								'<div class="rowbox">'
								+'<br/>'
								+'<div class="row">'
								+'<div class="col-md-10 col-md-offset-1" id="quota_amount">'
								+'<span class="col-sm-4 text-right">定额金额范围:</span>'
								+'<input id="minMoney" onblur="checkBlur(this)" onkeyup="checkUp(event,this)" name="rechargeQuotaFee['+x+'].minmoney" maxlength="5"  placeholder="最低充值金额" />元'
								+'..... <input id="maxMoney" onblur="checkBlur(this)" onkeyup="checkUp(event,this)" name="rechargeQuotaFee['+x+'].maxmoney" maxlength="5" placeholder="最高充值金额" />元 '
								+'<input type="button" value="-" onclick="reducequota(this);"/>'
								+'<label id="insert_feeMoney_lb"></label>'
								+'</div>'
								+'</div><br id="br"/>'
								+'<div class="row">'
								+'<div class="col-md-10 col-md-offset-1" id="quota_select">'
								+'<span class="col-sm-4 text-right">定额手续费:</span>'
								+'<input id="quotaFee" onblur="checkBlur(this)" onkeyup="checkUp(event,this)" name="rechargeQuotaFee['+x+'].quotafee" maxlength="5" />元'
								+'<label id="insert_quota_lb"></label>'
								+'</div>'
								+'</div>'
								
								+'</div>'
						);
						$(quotaAll).append(oDiv);
						x++;
						}
					}
			);
			var snidAll = $("#snidAll");
			var snlidbtn = $("#snlidbtn");
			var y = snidAll.length;
			//排除人名单
			$(snlidbtn).click(
					function(e){
						alert(y);
						if(y<=max){
							var snlidDiv = $('<div class="snlidInput"></div>');
							snlidDiv.html(
									'<div class="row">'
									+'<br/>'
									+'<div class="col-md-10 col-md-offset-1">'
									+'<span class="col-sm-4 text-right">定向排出编号:</span>'
									+'<input type="text" id="snid" name="rechargeSNLLink['+y+'].snlid" maxlength="25" placeholder="请到定向名单列表查询" />'
									+'<input type="button" value="-" onclick="removesnid(this);" />'
									+'<label id="insert_paycompany_lb"></label>'
									+'</div>'
									+'</div>'
							);
							$(snidAll).append(snlidDiv);
							y++;
						}
					}
			);
	
		});

//删除定额金额段和定额手续费
function reducequota(obj){
	$(obj).parent().parent().parent().remove();
}
function removesnid(obj){
	$(obj).parent().parent().parent().remove();
}


$(function() {
	$("#insert-ugrade-checkbox-div").hide();
	//比例 div 隐藏
	$("#insert-rate-div").hide();
	//等级 radio change监听事件
	$(".insert-ugrade-radio").change(function() {
		//选择单选框的值
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
	//单击 选择等级checkbox 取消提示语
	$(".checkbox-inline").click(function() {
		$("#insert_ugrade_lb").html("");
	});
	//充值费率 输入 取消提示语
	$("#paycompany").change(function() {
		$("#insert_paycompany_lb").html("");
	});
	//最小金额text输入 取消提示语
	$("#minmoney").change(function() {
		$("#insert_money_lb").html("");
	});
	//最大金额text输入 取消提示语
	$("#maxmoney").change(function() {
		$("#insert_money_lb").html("");
	});
	//定额 输入  取消提示语
	$("#quota").change(function() {
		$("#insert_quota_lb").html("");
	});
	//充值费率 输入 取消提示语
	$("#charrate").change(function() {
		$("#insert_charrate_lb").html("");
	});
	//充值最低收费金额
	$("#minfee").change(function() {
		$("#insert_feeMoney_lb").html("");
	});
	//充值最高收费金额
	$("#maxfee").change(function() {
		$("#insert_feeMoney_lb").html("");
	});
	/*//设置人
	$("#addman").change(function() {
		$("#insert_addman_lb").html("");
	});
	//设置时间
	$("#addtime").change(function() {
		$("#insert_addtime_lb").html("");
	});
	//审核人
	$("#auditman").change(function() {
		$("#insert_auditman_lb").html("");
	});
	//审核时间
	$("#audittime").change(function() {
		$("#insert_audittime_lb").html("");
	});*/

});
//验证添加
function InsertForm() {
	//验证会员等级选择第二个时又不选择会员等级的情况下
	var $ugradeVal = $("input[name='ugrade']:checked").val();
	//验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#insert_ugrade_lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}

	//验证最小金额
	var  minmoney = $("#minmoney").val();
	var number = /^([1-9]\d{0,7}|0)(\.\d{1,2})?$/;
	if(minmoney==null || minmoney=='' || minmoney.length == 0 ){
			$("#insert_money_lb").html("<font style='color:red;'>请输入最小金额</font>");
			return false;
		}else{
			if(!number.test(minmoney)){
				$("#insert_money_lb").html("<font style='color:red;'>请输入有效数字</font>");
			}
		}
	//验证最大金额必须大于最小金额
	var  maxmoney = $("#maxmoney").val();
		if(maxmoney==null || maxmoney=='' || parseFloat(maxmoney)<= parseFloat(minmoney)){
			$("#insert_money_lb").html("<font style='color:red;'>最大金额必须大于最小金额</font>");
			return false;
		}else{
			if(!number.test(minmoney)){
				$("#insert_money_lb").html("<font style='color:red;'>请输入有效数字</font>");
			}
		}
	//验证收费类型
	var chargetype =  $("#chargetype_select").val();
	if(chargetype==1){
		//验证定额
		var  quota = $("#quota").val();
		if(quota==null || quota==''){
			$("#insert_quota_lb").html("<font style='color:red;'>请输入固定金额</font>");
			return false;
		}else{
			if(!number.test(quota)){
				$("#insert_quota_lb").html("<font style='color:red;'>请输入有效数字</font>");
			}
		}
	}else if(chargetype==2){
		//验证充值费率
		var  charrate = $("#charrate").val();
		if(charrate==null || charrate==''){
			$("#insert_charrate_lb").html("<font style='color:red;'>请输入充值费率</font>");
			return false;
		}/*else{
			if(!number.test(quota)){
				$("#insert_charrate_lb").html("<font style='color:red;'>请输入有效数字2</font>");
			}
		}*/
		//验证最小收费金额
		var  minfee = $("#minfee").val();
		if(minfee==null || minfee==''){
			$("#insert_feeMoney_lb").html("<font style='color:red;'>请输入最小收费金额</font>");
			return false;
		}else{
			if(!number.test(minfee)){
				$("#insert_feeMoney_lb").html("<font style='color:red;'>请输入有效数字</font>");
			}
		}
		//验证最大收费金额
		var  maxfee = $("#maxfee").val();
		if(parseFloat(maxfee)<=parseFloat(minfee) || maxfee==null || maxfee==''){
			$("#insert_feeMoney_lb").html("<font style='color:red;'>最大收费金额必须大于最小收费金额</font>");
			return false;
		}else{
			if(!number.test(maxfee)){
				$("#insert_feeMoney_lb").html("<font style='color:red;'>请输入有效数字</font>");
			}
		}
	}	
	/*//验证设置人
	var  addman = $("#addman").val();
	if(addman==null || addman==''){
		$("#insert_addman_lb").html("<font style='color:red;'>请填写设置人</font>");
		return false;
	}
	//验证设置时间
	var  addtime = $("#addtime").val();
	if(addtime==null || addtime==''){
		$("#insert_addtime_lb").html("<font style='color:red;'>请选择时间</font>");
		return false;
	}
	//验证审核人
	var  auditman = $("#auditman").val();
	if(auditman==null || auditman==''){
		$("#insert_auditman_lb").html("<font style='color:red;'>请填写审核人</font>");
		return false;
	}
	//验证审核时间
	var  audittime = $("#audittime").val();
	if(audittime==null || audittime==''){
		$("#insert_audittime_lb").html("<font style='color:red;'>请选择审核时间</font>");
		return false;
	}*/
	return true;
	
}