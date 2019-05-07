function forwardDetailUI(id) {// 跳转详情页面
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

function forwardUpdateUI(id) {// 跳转编辑页面
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

function forwardInsertUI() {// 跳转新增页面
	var action = "insert_UI.action";
	var callback = function(data) {
		$("#insert-modal-body").html(data);
	}
	$.post(action, null, callback);
}

function forwardDelUI(id, index) {// 跳转 删除 UI
	var action = "del_UI.action";
	var params = {
		"id" : id,
		"index" : index
	};
	var callback = function(data) {
		$("#del-modal-body").html(data);
	};
	$.post(action, params, callback);
}

function update() {// 编辑更新
	if (validateUpdateForm()) {
		var action = "update.action"
		var callback = function(data) {
			alert(data);
			queryAllPerson("", "");
		}
		$.post(action, $('#update-form').serialize(), callback, 'json');
	}
}

function insert() {// 保存
	if (validateForm()) {
		var action = "addWdcRstr.action"
		var callback = function(data) {
			alert(data);
			queryAllPerson("", "");
		}
		$.post(action, $('#insert-form').serialize(), callback, 'json');
	}
}

function delByID() {// 删除
	var id = $("#id-hidden").val();
	var action = "delete.action";
	var params = {
		"id" : id
	};
	var callback = function(data) {
		alert(data);
		queryAllPerson("", "");
	};
	$.post(action, params, callback, 'json');
}

function forwardIsUserUI(id, index) {// 启用 UI
	$("#isuseidlb").html("<font color='red'>" + index + "</font>");
	$("#isuseid").val(id);
}

function isUse() {// 启用
	var id = $("#isuseid").val();
	var action = "isUse.action";
	var params = {
		"id" : id
	};
	var callback = function(data) {
		alert("提示：操作成功。");
		queryAllPerson('', '');
	};
	$.post(action, params, callback);
}

function forwardCancelUserUI(id, index) {// 停用ID
	$("#canceluseidlb").html("<font color='red'>" + index + "</font>");
	$("#canceluseid").val(id);
}

function cancelUse() {// 停用
	var id = $("#canceluseid").val();
	var action = "cancelUse.action";
	var params = {
		"id" : id
	};
	$.post(action, params, function(data) {
		alert("提示：操作成功。");
		queryAllPerson('', '');
	});
}

function show(id) {// 详情DIV 显示 隐藏
	$("#" + id).toggle();
}

function auditSuccess() {// 审核通过
	var id = $("#audit-id-text").val();
	var remark = $("#audit-remark-text").val();
	var action = "auditWdcRstr.action";
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
function validateUpdateForm() {// 编辑验证

	var $ugradeVal = $("input[name='ugrade']:checked").val();
	// 验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#update-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}
	// 单笔最低金额范围
	var $lowestmoney = $("#update-lowestmoney-text").val();
	if ($lowestmoney == null || $lowestmoney == "") {
		$("#update-lowestmoney-text").focus();
		$("#update-money-lb").html("<font color='red'> * 请输入金额范围</font>");
		return false;
	}
	// 单笔最高金额范围
	var $highestmoney = $("#update-highestmoney-text").val();
	if ($highestmoney == null || $highestmoney == "") {
		$("#update-highestmoney-text").focus();
		$("#update-money-lb").html("<font color='red'> * 请输入金额范围</font>");
		return false;
	}
	// 单笔最低金额必须大于最高金额
	if (parseFloat($lowestmoney) > parseFloat($highestmoney)) {
		$("#update-money-lb")
				.html("<font color='red'> * 单笔最低金额必须大于最高金额</font>");
		return false;
	}
	// 日提现金额
	var $daymoneyrest = $("#update-daymoneyrest-text").val();
	if ($daymoneyrest == null || $daymoneyrest == "") {
		$("#update-daymoneyrest-text").focus();
		$("#update-daymoneyrest-lb").html(
				"<font color='red'> * 请输入日提现金额</font>");
		return false;
	}
	// 日提现次数
	var $daytimesrest = $("#update-daytimesrest-text").val();
	if ($daytimesrest == null || $daytimesrest == "") {
		$("#update-daytimesrest-text").focus();
		$("#update-daytimesrest-lb").html(
				"<font color='red'> * 请输入日提现次数</font>");
		return false;
	}
	// 提现占余额比例
	var $proportion = $("#update-proportion-text").val();
	if ($proportion == null || $proportion == "") {
		$("#update-proportion-text").focus();
		$("#update-proportion-lb").html(
				"<font color='red'> * 请输入提现占余额比例</font>");
		return false;
	}
	if (parseFloat($proportion) > 100) {
		$("#update-proportion-text").focus();
		$("#update-proportion-lb").html(
				"<font color='red'> * 提现占余额比例不能超过100%</font>");
		return false;
	}
	var proxypay = $("input[name='proxypay']:checked").val();
	if (proxypay == 2) {
		if ($("#update-proxypayman-select").val() == '') {
			alert("提示：请选择代付账户");
			return false;
		}
	}
	return true;
}

function validateForm() {
	// 保存验证
	var $ugradeVal = $("input[name='ugrade']:checked").val();
	// 验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
	if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		$("#insert-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
		return false;
	}
	// 单笔金额范围
	var $lowestmoney = $("#insert-lowestmoney-text").val();
	if ($lowestmoney == null || $lowestmoney == "") {
		$("#insert-lowestmoney-text").focus();
		$("#insert-money-lb").html("<font color='red'> * 请输入金额范围</font>");

		return false;
	}
	var $highestmoney = $("#insert-highestmoney-text").val();
	if ($highestmoney == null || $highestmoney == "") {
		$("#insert-highestmoney-text").focus();
		$("#insert-money-lb").html("<font color='red'> * 请输入金额范围</font>");
		return false;
	}
	if (parseFloat($lowestmoney) >= parseFloat($highestmoney)) {
		$("#insert-money-lb")
				.html("<font color='red'> * 单笔最低金额必须大于最高金额</font>");
		return false;
	}
	var $daymoneyrest = $("#insert-daymoneyrest-text").val();
	if ($daymoneyrest == null || $daymoneyrest == "") {
		$("#insert-daymoneyrest-text").focus();
		$("#insert-daymoneyrest-lb").html(
				"<font color='red'> * 请输入日提现金额</font>");
		return false;
	}
	var $daytimesrest = $("#insert-daytimesrest-text").val();
	if ($daytimesrest == null || $daytimesrest == "") {
		$("#insert-daytimesrest-text").focus();
		$("#insert-daytimesrest-lb").html(
				"<font color='red'> * 请输入日提现次数</font>");
		return false;
	}
	var $proportion = $("#insert-proportion-text").val();
	if ($proportion == null || $proportion == "") {
		$("#insert-proportion-text").focus();
		$("#insert-proportion-lb").html(
				"<font color='red'> * 请输入提现占余额比例</font>");
		return false;
	}
	if (parseFloat($proportion) > 100) {
		$("#insert-proportion-text").focus();
		$("#insert-proportion-lb").html(
				"<font color='red'> * 提现占余额比例不能超过100%</font>");
		return false;
	}
	var proxypay = $("input[name='proxypay']:checked").val();
	if (proxypay == 2) {
		if ($("#proxypayman-select").val() == '') {
			alert("提示：请选择代付账户");
			return false;
		}
	}
	return true;
}