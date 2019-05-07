$(document).ready(function(){
	var cur_dh = $('#xinxi_nwd_5');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
});

function remindSetSave(){
	var mbMobileVerify = 1;
	//alert($('input[name="mbMobileVerify"]:checked').val());
	mbMobileVerify = $('input[name="mbMobileVerify"]:checked').val();
	/*if($("#mbMobileVerify1").hasClass("radio checked")){
		mbMobileVerify = 1;
	}
	if($("#mbMobileVerify2").parents("span").hasClass("radio checked")){
		mbMobileVerify = 2;
	}
	if($("#mbMobileVerify3").parents("span").hasClass("radio checked")){
		mbMobileVerify = 3;
	}
	if($("#mbMobileVerify4").parents("span").hasClass("radio checked")){
		mbMobileVerify = 4;
	}*/
	var mbMobile=$("#mbMobile").val();
	if(mbMobile==mbMobileVerify){
		$('#remindSet1').addClass('none');
		$('#remindSet2').removeClass('none');
		setTimeout(function(){ 
			location.reload();
			},2000)
		return;
	}
		$('#MSG').css("display","none");
		$("#MSG").html("");
		/**
		 * 提交时没有token。通过访问URL直接提交借款Bug修复
		 */
        var stok = "";
        if(document.getElementById ("stok")){
            stok  = document.getElementById ("stok").value;
        }
		
		$.ajax({
        	type: "post",
       	 	url: "/member/saveRemindSet.do",
        	dataType: "json",
        	data:{
        		mbMobileVerify:mbMobileVerify,
        		stok:stok
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){
        			$('#remindSet1').addClass('none');
        			$('#remindSet2').removeClass('none');
        			setTimeout(function(){ 
        				location.reload();
        				},2000)
        		}else if(msg==0){//设置失败
        			$('#remindSet1').addClass('none');
        			$('#remindSet2').addClass('none');
        			$('#remindSet3').removeClass('none');
        			setTimeout(function(){ 
        				location.reload();
        				},2000)
        		}
        	}   
      }); 
}