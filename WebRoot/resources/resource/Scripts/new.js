	//头像上传
	$(document).ready(function(){
		$("#myphototip").click(function(){
			$("#myphoto-pop-manage").show();
			$(".bg").show();
		});
		$("#closeImg").click(function() {
			$("#myphoto-pop-manage").hide();
			$(".bg").hide();
		});

	})
