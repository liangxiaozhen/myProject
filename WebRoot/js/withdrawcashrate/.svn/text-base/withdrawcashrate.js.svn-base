function delByID() {// 根据ID删除

	var id = $("#id-hidden").val();
	var action = "delete.action";
	var params = {
		"id" : id
	};
	$.post(action, params, function(data) {
		alert(data);
		queryAllPerson("", "");
	}, 'json');
}

function forwardDelUI(id,index) {// 请求转发 删除页面 UI ---1
	var action="del_UI.action";
	var params={"id":id,"index":index};
	var callback=function(data){
		$("#del-modal-body").html(data);
	};
	$.post(action,params,callback);
}

function forwardIsUserUI(id,index) {// 启用 UI
	$("#isuseidlb").html("<font color='red'>" + index + "</font>");
	$("#isuseid").val(id);
}

function isUse() {// 启用
	var id = $("#isuseid").val();
	var action = "isUse.action";
	var params = {
		"id" : id
	};
	$.post(action, params, function(data) {
		queryAllPerson("", "");
	});
}

function forwardCancelUserUI(id,index) {// 停用ID
	$("#canceluseidlb").html("<font color='red'>" + index + "</font>");
	$("#canceluseid").val(id);
}

function cancelUse() {// 停用
	var id = $("#canceluseid").val();
	var action ="cancelUse.action";
	var params = {
		"id" : id
	};
	$.post(action, params, function(data) {
		queryAllPerson('', '');
	});
}

function forwardInsertUI() {// 请求添加页面 UI
	var action = "insert_UI.action";
	var callback = function(data) {
		$("#insert-modal-body").html(data);
	};
	$.post(action, null, callback);
}

function forwardDetailUI(id) {// 请求转发 详情页面UI --1
	var action = "queryById.action";
	var params = {
		"id" : id,
		"uid" : 1
	};
	var callback = function(data) {
		$("#detail-modal-body").html(data);
	}
	$.post(action, params, callback);
}

function forwardUpdateUI(id) {// 请求转发 修改页面UI --2
	var action = "queryById.action";
	var params = {
		"id" : id,
		"uid" : 2
	};
	var callback = function(data) {
		$("#update-modal-body").html(data);
	}
	$.post(action, params, callback);
}

function forwardAuditUI(id) {// 请求转发 审核页面UI --3
	var action = "queryById.action";
	var params = {
		"id" : id,
		"uid" : 3
	};
	var callback = function(data) {
		$("#audit-modal-body").html(data);
	}
	$.post(action, params, callback);
}

function auditSuccess() {// 审核通过
	var id = $("#audit-id-text").val();
	var remark = $("#audit-remark-text").val();
	var action = "auditrate.action";
	var params = {
		"id" : id,
		"remark" : remark
	};
	$.post(action, params, function(data) {
		if (eval(data) != undefined) {
			alert(eval(data));
		} else {
			queryAllPerson('', '');
		}
	});
}
function insert() {// 保存
	if (validateInsertForm()) {
		var action = "addRate.action";
		var callback = function(data) {
			alert(data);
			queryAllPerson("", "");
		}
		$.post(action, $('#insert-rate-form').serialize(), callback, 'json');
	}
}
function update() {// 编辑更新
	if (validateUpdateForm()) {
		var action = "update.action";
		var callback = function(data) {
			alert(data);
			queryAllPerson("", "");
		}
		$.post(action, $('#update-rate-form').serialize(), callback, 'json');
	}
}
function validateUpdateForm() {// 验证表单数据
	/*
	 * var $ugradeVal = $("input[name='ugrade']:checked").val(); //验证会员等级 当选择
	 * 选择等级radio 并没有选择具体等级checkbox return false if ($ugradeVal == 2 &&
	 * $("input[name='ugrades']:checked").size() == 0) {
	 * $("#update-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
	 * return false; }
	 */
	// 验证最小金额
	var $minmoney = $("#update-minmoney-text").val();
	if ($minmoney == "" || $minmoney == null) {
		$("#update-money-lb").html("<font color='red'> * 请输入最小金额</font>");
		return false;
	}
	var $maxmoney = $("#update-maxmoney-text").val();
	if (parseFloat($maxmoney) <= parseFloat($minmoney) && $maxmoney != ""
			&& $maxmoney != null) {
		$("#update-money-lb").html("<font color='red'> * 最大金额必须大于最小金额</font>");
		return false;
	}
	// 验证提现处理费
	var $fixedfee = $("#update-fixedfee-text").val();
	if ($fixedfee == "" || $fixedfee == null) {
		$("#update-fixedfee-lb").html("<font color='red'> * 请输入处理费金额</font>");
		return false;
	}
	// 手续费类型
	var $wdcmode = $("#update-wdcmode-select").val();
	if ($wdcmode == 1) {
		// 验证固定金额
		var $quota = $("#update-quota-text").val();
		if ($quota == "" || $quota == null) {
			$("#update-quota-lb").html("<font color='red'> * 请输入固定金额</font>");
			return false;
		}
	}
	if ($wdcmode == 2) {
		// 验证提现费率
		var $wdcrate = $("#update-wdcrate-text").val();
		if ($wdcrate == "" || $wdcrate == null) {
			$("#update-wdcrate-lb").html("<font color='red'> * 请输入提现费率</font>");
			return false;
		}
		// 验证最小金额
		var $minfee = $("#update-minfee-text").val();
		if ($minfee == "" || $minfee == null) {
			$("#update-minfee-lb").html("<font color='red'> *请输入金额 </font>");
			return false;
		}
		// 验证最大金额
		var $maxfee = $("#update-maxfee-text").val();
		if ($maxfee == "" || $maxfee == null) {
			$("#update-maxfee-lb").html("<font color='red'> *请输入金额  </font>");
			return false;
		}
		if (parseFloat($minfee) > parseFloat($maxfee)) {
			$("#update-maxfee-lb").html(
					"<font color='red'> *最小金额必须小于最大金额  </font>");
			return false;
		}
	}
	return true;
}

function validateInsertForm() {// 验证表单数据
	var $ugradeVal = $("input[name='ugrade']:checked").val();
	// 验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#insert-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}
	// 验证最小金额
	var $minmoney = $("#insert-minmoney-text").val();
	if ($minmoney == "" || $minmoney == null) {
		$("#insert-money-lb").html("<font color='red'> * 请输入最小金额</font>");
		return false;
	}
	// 验证 最大金额 大于最小金额
	var $maxmoney = $("#insert-maxmoney-text").val();
	if (parseFloat($maxmoney) <= parseFloat($minmoney) && $maxmoney != ""
			&& $maxmoney != null) {
		$("#insert-money-lb").html("<font color='red'> * 最大金额必须大于最小金额</font>");
		return false;
	}
	// 验证提现处理费
	var $fixedfee = $("#insert-fixedfee-text").val();
	if ($fixedfee == "" || $fixedfee == null) {
		$("#insert-fixedfee-lb").html("<font color='red'> * 请输入处理费金额</font>");
		return false;
	}
	// 手续费类型
	var $wdcmode = $("#insert-wdcmode-select").val();
	if ($wdcmode == 1) {
		// 验证固定金额
		var $quota = $("#insert-quota-text").val();
		if ($quota == "" || $quota == null) {
			$("#insert-quota-lb").html("<font color='red'> * 请输入固定金额</font>");
			return false;
		}
	}
	if ($wdcmode == 2) {
		// 验证提现费率
		var $wdcrate = $("#insert-wdcrate-text").val();
		if ($wdcrate == "" || $wdcrate == null) {
			$("#insert-wdcrate-lb").html("<font color='red'> * 请输入提现费率</font>");
			return false;
		}
		// 验证最小金额
		var $minfee = $("#insert-minfee-text").val();
		if ($minfee == "" || $minfee == null) {
			$("#insert-minfee-lb").html("<font color='red'> *请输入最小金额 </font>");
			return false;
		}
		// 验证最大金额
		var $maxfee = $("#insert-maxfee-text").val();
		if ($maxfee == "" || $maxfee == null) {
			$("#insert-maxfee-lb").html("<font color='red'> *请输入最大金额 </font>");
			return false;
		}
		if (parseFloat($minfee) > parseFloat($maxfee)) {
			$("#insert-minfee-lb").html(
					"<font color='red'> *最小金额必须小于最大金额  </font>");
			return false;
		}
	}
	return true;
}

/*
 function createAddMoney() {// 动态添加 var $tb = $("#tbody"); var $tr = $("<tr>");
  var $td = $("<td>"); var $minmoney = $("<input type='text'
  class='addminmoney' onblur='checkNum(this)'onkeyup='clearNoNum(event,this)'
  name='minmoneys' style='text-align:center;' size='5'>"); var $fspan = $("<span>
  元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;</span>"); var $maxmoney = $("<input
  type='text' class='addmaxmoney' name='maxmoneys' size='5'
  onblur='checkNum(this)'onkeyup='clearNoNum(event,this)'
  style='text-align:center;line-height: 18px' placeholder='无穷'>"); var $aspan =
  $("<span> 元&nbsp;&nbsp;&nbsp; </span>"); var $btn = $("<input type='button'
  class='btn' value='-' />"); $btn.click(function() {
  $(this).parent().parent().remove(); }); $td.append($minmoney);
  $td.append($fspan); $td.append($maxmoney); $td.append($aspan);
 $td.append($btn); $tr.append($td); $tb.append($tr); }
 */