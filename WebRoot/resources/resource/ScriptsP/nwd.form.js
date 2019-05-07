// JavaScript Document
//银行卡分段输出
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
        $('.tip_default').hide();   
     }).blur(function(){
     	$(this).siblings('i').remove();
		if($(this).val()==''){
				 $('.tip_default').show()
			}
		else{
				$('.tip_default').hide();		 
			}
       $(this).siblings('i').hide();
		
     });
}

$(document).ready(function() {
    $(".code").click(function(event) {               
                $('.code_box').slideToggle();
                $(".code .arrow").toggleClass('hover');           
         
        });


/*input 获取光标和失去光标节点*/
    $(".code_box .inputOut").click(function(event) {
        if($(".tui").val()==""){
            $('.input_sp').hide();       
              $('.input_sp').css('font-family', 'Microsoft YaHei');
              $('.tui').focus();
        }
        else
        {
             $(this).css('font-family', 'arial');
        } 
    });
    $(".code_box .inputOut input").blur(function(){
        if($(".tui").val()=="")
        {
             $(".input_sp").show();
             $('.input_sp').css('font-family', 'Microsoft YaHei');
        }      
    });


    //page41 交互
    $(".btn_p1").click(function(event) {
            
            if($('.classc-com .form1').css('display')=='block'){
                $('.classc-com .form1').slideUp();
                $('.classc-com .form1').siblings('.form').slideDown(); 
                $('.m_form').removeClass('current');
                 $('.classc-com .form2').slideDown().parent('.m_form').addClass('current');              
                $(".res1").show();

                // $(".form2 .input_b").focus();    
            }
            else{
            
                $('.classc-com .form1').slideDown(); 

            }       
        });

    $(".btn_p2").click(function(event) {
            
            if($('.classc-com .form2').css('display')=='block'){
                 $('.classc-com .form2').slideUp();
                 $('.classc-com .form2').siblings('.form').slideDown(); 
                 $('.m_form').removeClass('current');
                 $('.classc-com .form3').slideDown().parent('.m_form').addClass('current');
                 $(".res2").show();
            }
            else{    
                $('.classc-com .form2').slideDown();       
            }       
        });
    $(".btn_p3").click(function(event) {
            
            if($('.classc-com .form3').css('display')=='block'){
                $('.classc-com .form3').slideUp();
                $('.classc-com .form3').siblings('.form').slideDown(); 
                $('.m_form').removeClass('current');
                $('.classc-com .form4').slideDown().parent('.m_form').addClass('current'); 
                $(".res3").show();
            }
            else{    
                $('.classc-com .form3').slideDown();
           
            }       
        });

    // $(".btn_p4").click(function(event) {
            
    //         if($('.classc-com .form4').css('display')=='block'){
    //             $('.classc-com .form4').slideUp();
    //             $('.classc-com .form4').siblings('.form').slideDown(); 
    //             $(".res4").show();
                
    //         }
               
    //     });

    $(".res1").click(function(){
        $('.classc-com .form').show();
        $('.classc-com .form1').siblings('.form').hide();
        //$(this).parents('.m_form').siblings('.m_form').find('.hidden').slideUp();
        $('.m_form').removeClass('current');
        $('.classc-com .form1').slideDown().parent('.m_form').addClass('current');
        $('.classc-com .form2').slideUp();
        $('.classc-com .form3').slideUp();
        $('.classc-com .form4').slideUp();
        // $('.classc-com .form1').siblings('.form').slideUp(); 
    })

    $(".res2").click(function(){
        $('.classc-com .form').show();
        $('.classc-com .form2').siblings('.form').hide();
        $('.classc-com .form1').slideUp();
        $('.m_form').removeClass('current');
        $('.classc-com .form2').slideDown().parent('.m_form').addClass('current');
        $('.classc-com .form3').slideUp();
        $('.classc-com .form4').slideUp();
    })

    $(".res3").click(function(){
      $('.classc-com .form').show();
       $('.classc-com .form3').siblings('.form').hide();
        $('.classc-com .form1').slideUp();
        $('.classc-com .form2').slideUp();
        $('.m_form').removeClass('current');
        $('.classc-com .form3').slideDown().parent('.m_form').addClass('current');
        $('.classc-com .form4').slideUp();
        
    })

    // $(".res4").click(function(){
    //   $('.classc-com .form').show();
    //   $('.classc-com .form4').siblings('.form').hide();
    //   //$(this).parents('.m_form').find('.form4').slideDown();
    //   //$(this).parents('.m_form').siblings('.m_form').find('.hidden').slideUp();
    //     $('.classc-com .form1').slideUp();
    //     $('.classc-com .form2').slideUp();
    //     $('.classc-com .form3').slideUp();
    //     $('.classc-com .form4').slideDown();
    // })
    $(".more").click(function(event) {    

            var nowing2=0;

             if(nowing2==0){
                $(".page41 .sow").show();
                nowing2=1;
            }          
        });
     $(".more label").click(function(event) {               
            $(".page41 .sow textarea").slideToggle(); 
        });
});


/***非大陆人士实名认证*/

$(function(){
	 showpersonCont(); 	
	 $("input[name=radio]").click(function(){
	  showpersonCont();
	 });
	 function showpersonCont(){
	 switch($("input[name=radio]:checked").attr("id")){
	  case "gaperson":
	   //alert("one");
	   $("#gapersonTab").show();
	   $("#wjpersonTab").hide();
	   $("#twpersonTab").hide();
	   $("#jgpersonTab").hide();
	   break;
	  case "twperson":
	   $("#twpersonTab").show();
	   $("#wjpersonTab").hide();
	   $("#gapersonTab").hide();
	   $("#jgpersonTab").hide();
	   break;
	   case "wjperson":
	   $("#wjpersonTab").show();
	   $("#twpersonTab").hide();
	   $("#gapersonTab").hide();
	   $("#jgpersonTab").hide();
	   break;
	   case "jgperson":
	   $("#jgpersonTab").show();
	   $("#twpersonTab").hide();
	   $("#gapersonTab").hide();
	   $("#wjpersonTab").hide();
	   break;
		default:
	   break;
	 }
	}
		$("#gaselect").change(function(){
			if($("#gaselect").val()==0){
				$(".gashenfenzheng").addClass("hidden");
				$(".gahuzhao").addClass("hidden");
				}
			else if($("#gaselect").val()==1){
				$(".gashenfenzheng").removeClass("hidden");
				$(".gahuzhao").addClass("hidden");
				}
			else if($("#gaselect").val()==2){
				$(".gashenfenzheng").addClass("hidden");
				$(".gahuzhao").removeClass("hidden");
				}	
			else{
				$(".gashenfenzheng").addClass("hidden");
				$(".gahuzhao").addClass("hidden");
				}
			})
			
		$("#twselect").change(function(){
			if($("#twselect").val()==0){
				$(".twshenfenzheng").addClass("hidden");
				$(".twhuzhao").addClass("hidden");
				}
			else if($("#twselect").val()==1){
				$(".twshenfenzheng").removeClass("hidden");
				$(".twhuzhao").addClass("hidden");
				}
			else if($("#twselect").val()==2){
				$(".twshenfenzheng").addClass("hidden");
				$(".twhuzhao").removeClass("hidden");
				}	
			else{
				$(".twshenfenzheng").addClass("hidden");
				$(".twhuzhao").addClass("hidden");
				}
			})
			
	//var numsize = $(".gapicDiv p");
//	var gatsize = $(".gatpicDiv p")
//	if(numsize.length>0){
//		$(".comminpic").hide();
//		}
//		else{
//			$(".comminpic").show();
//			};
//	if(gatsize.length>0){
//		$(".gatbtn").hide();
//		}
//		else{
//			$(".gatbtn").show();
//			}
});