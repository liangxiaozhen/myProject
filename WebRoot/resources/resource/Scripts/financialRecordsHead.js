$(document).ready(function(){
	//账户金额
	 $.ajax({
			type : "POST", 
			url : "/member/investorsAjax.do", 
			dataType:"json",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(result) { 
				$("#accAmount").text(formatNum(result.fun.balance/1000));//账户余额
				$("#freezeAmount").text(formatNum(result.fun.freeze/1000));//冻结资金
				
				dealwithDecimal();
			}
	 });
});

//格式化金额
function formatNum(pamenth){
	pamenth = pamenth.toString().replace(/\$|\,/g,'');
    if(isNaN(pamenth)){
    	pamenth = "0";
    }
    var sign = (pamenth == (pamenth = Math.abs(pamenth)));
    pamenth = Math.floor(pamenth*100+0.50000000001);
    var cents = pamenth%100;
    pamenth = Math.floor(pamenth/100).toString();
    if(cents<10){
	    cents = "0" + cents;
    }
	for (var i = 0; i < Math.floor((pamenth.length-(1+i))/3); i++){
	    pamenth = pamenth.substring(0,pamenth.length-(4*i+3))+','+pamenth.substring(pamenth.length-(4*i+3));
	}
	return (((sign)?'':'-') + pamenth + '.' + cents);
}
