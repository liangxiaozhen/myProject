//百度搜录代码
(function(){
	var protocol = window.location.protocol;
	if (protocol != undefined) {
		protocol = protocol.replace(":","")
		if ("http" == protocol) {
		    var bp = document.createElement('script');
		    bp.src = '//push.zhanzhang.baidu.com/push.js';
		    var s = document.getElementsByTagName("script")[0];
		    s.parentNode.insertBefore(bp, s);
		}
	}
})();
