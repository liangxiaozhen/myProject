//弹框
var plusBankBg,showForm1,showForm;

function showCon_0(){
	plusBankBg=$('.plusBankBg');
	showForm=$('.plusBank');
	plusBankBg.show();
	showForm.slideDown();
}


function showCon_1(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank1');
	plusBankBg.show();
	showForm1.slideDown();
	if($('.inputFocus1').size()>0){
		$('.inputFocus1').focus();
	}
}
function showCon_2(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank2');
	plusBankBg.show();
	showForm1.slideDown();
}

function closeAll_0(){
	plusBankBg=$('.plusBankBg');
	showForm=$('.plusBank');
	plusBankBg.hide();
	showForm.slideUp();
}

function closeAll_1(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank1');
	plusBankBg.hide();
	showForm1.slideUp();
}
function closeAll_2(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank2');
	plusBankBg.hide();
	showForm1.slideUp();
}
function closeAll_5(){
	plusBankBg=$('.plusBankBg');
	showForm1=$('.plusBank5');
	plusBankBg.hide();
	showForm1.slideUp();
}




$(function($) {
	$("a.fr").each(function(){
		$(this).click(function(){
			closeAll_0();
			closeAll_1();
			closeAll_2();
			//closeAll_3();
		});
	});
});


	//--输入框内提示-------------
			$(".touzi01 .input_1").each(function(){
				 var thisVal=$(this).val();
				 if(thisVal!=""){
				   $(this).siblings("span").hide();
				  }else{
				   $(this).siblings("span").show();
				  }
				 $(this).focus(function(){
				   $(this).siblings("span").hide();
				  }).blur(function(){
					var val=$(this).val();
					if(val!=""){
					 $(this).siblings("span").hide();
					}else{
					 $(this).siblings("span").show();
					} 
				  });
				});	
				
				function SwapTab(name,title,content,Sub) {
				$(name+' '+title).mousedown(function(){
					var phoneLog = ($(this).attr('data-msg')=='phoneLog')
					var gerLog = ($(this).attr('data-msg')=='gerLog')
					var phoneLogCon = $(content+' '+Sub).eq(0)
					var gerLogCon = $(content+' '+Sub).eq(1)
					if(phoneLog) {
						$(this).html('使用普通方式登录');
						$(this).attr('data-msg','gerLog');
						$(this).attr('title','使用普通方式登录');
						phoneLogCon.addClass('hidden');
						gerLogCon.removeClass('hidden');
					}
					else if(gerLog)
					{
						$(this).html('手机动态密码登录');
						$(this).attr('data-msg','phoneLog')
						$(this).attr('title','手机动态密码登录')
						gerLogCon.addClass('hidden');
						phoneLogCon.removeClass('hidden');
					}
					
				  });
				};
				new SwapTab(".tab_u",".SwapTabBtn",".login-module-content",".nr");




