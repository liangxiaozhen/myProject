<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	您好！提现银行卡绑定结果： ${Message} ! 如有问题请联系客服
	<span id="timer"></span>
	<script type="text/javascript">
		timer(5);
		var timerParams = null;
		function timer(timer) {
			var timerDom = document.getElementById("timer");
			timerParams = setInterval(function() {
				if (timer <= 0) {
					clearInterval(timerParams);
					window.open("about:blank", "_self").close();
				} else {
					timerDom.innerHTML = (timer--) + "秒后关闭";
				}
			}, 1000);
		}
	</script>
</body>
</html>