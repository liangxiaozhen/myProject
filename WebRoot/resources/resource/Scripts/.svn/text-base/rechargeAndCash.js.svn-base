function recharge(){
	var isBlackUser = $("#blackUser").val();
	if(isBlackUser=="1"){
		var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，您已不能充值。</p>";
		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
		$(".plusBank1 .content").html(msg);
		$(".plusBank1 button.btn").unbind('click');
		$(".plusBank1 button.btn").click(function() {
			// 关闭提示信息弹出框
			//window.location.reload();
			closeAll_1();
		});
		showCon_1();
		return;
	}else{
		window.location.href="/member/rechargeStep.do";
	}
}

//zhuzy 2014-12-22 TASK#2379 黑名单操作受限与页面提现提示
function withdrawDeposit(){
	var isBlackUser = $("#blackUser").val();
	if(isBlackUser=="1"){
		var msg = "<p style='text-align:left;text-indent:2em'>尊敬的用户，您在你我贷平台上有高风险操作，为了保护您和他人的资产安全，";
		msg+="您的提现资金可能会退回到充值使用的银行卡。无法为您提现到指定银行卡，对此深表歉意。</p>";
		msg += "<p style='text-align:left;text-indent:2em'>如果您有疑问，可拔打客户电话400-7910-888</p>";
		$(".plusBank1 .content").html(msg);
		$(".plusBank1 button.btn").unbind('click');
		$(".plusBank1 button.btn").click(function() {
			// 关闭提示信息弹出框
			//window.location.reload();
			closeAll_1();
			window.location.href="/member/carry.html";
		});
		showCon_1();
		return;
	}else{
		window.location.href="/member/carry.html";
	}
}