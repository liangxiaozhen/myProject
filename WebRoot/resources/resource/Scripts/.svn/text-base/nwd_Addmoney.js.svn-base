$(function(){
	//新手
	$(".first_newbank li").click(function(){
		if($(this).hasClass("otherbank")){
		}
		else{
			$(this).addClass("curronli").siblings().removeClass("curronli");
		}
	})
	
	$(".my_moneybank").click(function () {
		if($('.person_bank').hasClass('none')){
			$('.person_bank').removeClass('none').addClass('block')
			$(this).addClass('down')
		}
		else if($('.person_bank').hasClass('block')){
			$('.person_bank').removeClass('block').addClass('none')
			$(this).removeClass('down')
		}
	});
	$(".bankPay").click(function () {
		if($('.bankPaySlect').hasClass('none')){
			$('.bankPaySlect').removeClass('none').addClass('block')
			$(this).addClass('down')
		}
		else if($('.bankPaySlect').hasClass('block')){
			$('.bankPaySlect').removeClass('block').addClass('none')
			$(this).removeClass('down')
		}
	});
	$(".selecBank").click(function () {
		if($('.cashBank ').hasClass('none')){
			$('.cashBank ').removeClass('none').addClass('block')
			$(this).addClass('down')
		}
		else if($('.cashBank ').hasClass('block')){
			$('.cashBank').removeClass('block').addClass('none')
			$(this).removeClass('down')
		}
	});
	$(".my_moneybank ").mouseover(function () {
		$(this).addClass('hover');
	});
	$(".bankPay").mouseover(function () {
		$(this).addClass('hover');
	});
	$(".selecBank").mouseover(function () {
		$(this).addClass('hover');
	});
	$(".my_moneybank ").mouseout(function () {
		$(this).removeClass('hover');
	});
	$(".bankPay ").mouseout(function () {
		$(this).removeClass('hover');
	});
	$(".selecBank ").mouseout(function () {
		$(this).removeClass('hover');
	});
	$('.bankclose1').click(function () {
		$('.person_bank').addClass('none').removeClass('block');
		$('.my_moneybank ').removeClass('down');
	});
	$('.bankclose2').click(function () {
		$('.bankPaySlect').addClass('none').removeClass('block');
		$('.bankPay ').removeClass('down');
	});
	$('.bankclose3').click(function () {
		$('.cashBank').addClass('none').removeClass('block');
		$('.selecBank  ').removeClass('down');
	});
	$(".person_bank  li").click(function(){
			$(".person_bank ").addClass('none').removeClass('block');
			$('.my_moneybank ').removeClass('down');
	})
	$(".bankPaySlect li").click(function(){
			$(".bankPaySlect").addClass('none').removeClass('block');
			$('.bankPay ').removeClass('down');
	})
	$(".cashBank li").click(function(){
			$(".cashBank").addClass('none').removeClass('block');
			$('.selecBank  ').removeClass('down');
	})
	
	$(".my_moneysite li").click(function(){
		$(this).addClass("curronli").siblings().removeClass("curronli");
	});
	$(".bankmember .input").each(function(){
		var thisVal=$(this).val();
		if(thisVal!="")
		    {
			   $(this).siblings("span").hide();
		    }
			else
			{
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
	
	
    })

//银行卡号分段输出
function _bank(bank){
	bank.keyup(function(event) {
     	this.value =this.value.replace(/\s/g,'').replace(/(\d{4})(?=\d)/g,"$1 ");
         var str = bank.val();
         $(this).siblings('.j_bank').html(str);    
         $(this).siblings('i').fadeTo(200,1);  
     })
     bank.focus(function(){
        $(this).parent().append("<i class=j_bank></i>");
        if($(this).val()!=''){
            var str = bank.val();
            $(this).siblings('.j_bank').html(str);    
            $(this).siblings('i').fadeTo(200,1);     
        }
        $('.tip_default1').hide();
     }).blur(function(){
     	$(this).siblings('i').remove();
		if($(this).val()==''){
				 $('.tip_default1').show()
			}
		else{
				$('.tip_default1').hide();		 
			}
       $(this).siblings('i').hide();
		
     });
}
_bank($(".inCard"));