<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	window.onload=function(){
		var i=3;
		var s =document.getElementById("second");
		s.innerHTML=i--;
		var handler=setInterval(function(){
			if(i<=0){
				clearInterval(handler);
				window.open("about:blank","_self").close()   
			}else{
				s.innerHTML=i--;
			}
		},1000);
	};
</script>
</head>
<body>
	订单号：${ordId}, 充值金额：${transAmt},手续费：${feeAmt},平台手续费 ${FeePlatform}, ${message} ！！！
	<span id="second"></span>秒后关闭
</body>
</html>