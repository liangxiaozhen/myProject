//验证提交
function validataFrom() {
	//汇款订单号
	var $rechargenolb = $("#rechargenolb").val();
	var number = /^[0-9]\d{10,30}$/;
	if ($rechargenolb == "" ||$rechargenolb==null) {
		$("#rechargeno_lb").html("<font color='red'>请输入正确的订单号</font>");
		return false;
	}else if(!number.test($rechargenolb)){
		return false;
	}
		
	//验证汇款银行
	var  $bankname_select = $("#bankname_select").val();
	if($bankname_select==null || $bankname_select==''){
		$("#bankname_lb").html("<font style='color:red;'>请选择汇款银行</font>");
		return false;
	}
	//验证汇款卡号
	var  $cardnolb = $("#cardnolb").val();
	if($cardnolb==null || $cardnolb==''){
			$("#cardno_lb").html("<font style='color:red;'>请输入正确的卡号</font>");
			return false;
		}
	//验证汇款金额
	var  $amountlb = $("#amountlb").val();
		if($amountlb==null || $amountlb==""){
			$("#amount_lb").html("<font style='color:red;'>请输入正确的金额</font>");
			return false;
		}
	return true;
	
}
function rechargenoBlur(){
	var $rechargenolb = $("#rechargenolb").val();
	var number = /^[0-9]\d{10,30}$/;
	if ($rechargenolb == "" ||$rechargenolb==null) {
		$("#rechargeno_lb").html("<font color='red'>请输入正确的订单号</font>");
	}else if(!number.test($rechargenolb)){
		alert("我来了");
		$("#rechargeno_lb").html("<font color='red'>请输入30位以内的纯数字</font>");
	}else{
		$("#rechargeno_lb").html("");
	}
}
function banknameBlur(){
	var  $bankname_select = $("#bankname_select").val();
	if($bankname_select==null || $bankname_select==''){
		$("#bankname_lb").html("<font style='color:red;'>请选择汇款银行</font>");
	}else{
		$("#bankname_lb").html("");
	}
}
function cardnoBlur(){
	var  $cardnolb = $("#cardnolb").val();
	if($cardnolb==null || $cardnolb==''){
			$("#cardno_lb").html("<font style='color:red;'>请输入正确的卡号</font>");
		}else{
			$("#cardno_lb").html("");
		}
}
	//当前元素失去焦点时触发的事件 
	function checkBlur(obj) {
		//为了去除最后一个. 
		obj.value = obj.value.replace(/\.$/g, "");
		var  $amountlb = $("#amountlb").val();
		if($amountlb==null || $amountlb==""){
			$("#amount_lb").html("<font style='color:red;'>请输入正确的金额</font>");
		}else{
			$("#amount_lb").html("");
		}
	}
	//当键盘上某个按键被按放开时触发的事件
	function checkUp(event, obj) {
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证只有出现一个.而没有多个. 
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
	}
