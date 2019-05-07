function edit(mbbId){
	/*
	 * 先判断当前银行卡是否可以被修改，比如处于提现中就不可以被修改
	 */
	$.ajax({
    	type: "post",
   	 	url: "/member/canUpdate.do",
    	dataType: "json",
    	data:{mbbId:mbbId},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == -3){
    			$(".plusBank1 .content").html('您的部分资金正在使用该银行卡提现中，不能修改该卡。');
    			$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
    			showCon_1();
    			$.breakFlag = true;
    		}
    	}
	});
	
	if($.breakFlag)
		return;
	
	$.ajax({
    	type: "post",
   	 	url: "/member/addBankzStep.do",
    	dataType: "json",
    	data:{
    		doWhat:"edit",
    		mbbId:mbbId
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(result){
    		$('#verifyBank').val('');
    		$('#idCardBank').val('');
    		
    		$('#manageBank').addClass('none');
    		$('#addBankStep3').addClass('none');
    		$('#bankCardAdd').removeClass('none');
    		$('#addBankStep1').removeClass('none');
    		
    		$('#bankStep1').attr("class","active");
			$('#bankStep2').attr("class","");
			$('#bankStep3').attr("class","");
    		
    		
    		$('#bankPhone').text(result['memberPhone']);
    		$("#addBankPhoneHidden").attr("value",result['phone']);
    		$('#doWhat').val(result['doWhat']);
    		$('#mbbId').val(mbbId);
    		$('#changeable').text('2.修改银行卡');
    		$('#save').text('修改');
    	}  
  });
	//7月8号修改 点击修改清空多余只显示第一步
	$('#addBankStep1').removeClass('none');
	$('#addBankStep2').addClass('none');
	$('#addBankStep3').addClass('none');
	//window.location.href="/member/addBankzStep.do?doWhat=edit&mbbId="+mbbId;
	
}

$(document).ready(function(){
	var cur_dh = $('#xinxi_nwd_8');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');
});
//显示银行卡
$(document).ready(function(){
	$('#findBankList').click(function(){
		showCardList();
	});
});

$(document).ready(function(){
	if($("#withdrawnRedirectFlag").val() == '1'){
		$('#findBankList').trigger("click");
		document.getElementsByTagName('body')[0].scrollTop = 600;
	}
});

//展开管理银行卡列表
function showCardList(){
	var staticCss = $("#staticCss").val();
	var showbtn = $('#showBank').attr('class');
	if(showbtn != 'none'){
		$.ajax({
	    	type: "post",
	   	 	url: "/member/bankManage.do",
	    	dataType: "json",
	    	data:{},
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(result){
	    		//mbbId  mbbNumber
	    		//alert(msg['0']['mbbCardNo']);
	    		$('#bankCardList').html("");
	    		data = result;
    			$(data).each(function(index,domEle){ 
/*	    		var bankList = "<li><div class='plusBank_center_top clearfix'>" +
	    				"<span class='position_"+domEle['bid']+" fl'></span></div><div class='plusBank_center_num clearfix'>" +
	    				"<div class='plusBank_center_left fc_3 fs_14 fl'>"+domEle['mbbCardNo']+"</div>" +
	    				"<a href='javascript:;' class='fl blue cen' onclick='edit("+domEle['mbbId']+");' title='修改'>修改</a>" +
	    				"<a href='javascript:;' class='fl blue thir' onclick='delete1("+domEle['mbbId']+");' title='删除'>删除</a></div></li>" ;
	    		*/
	    		var bankList =  '<li>'+
	    							'<div class="plusBank_center_top clearfix">'+
	    								'<span class="fl"><img src="'+staticCss+'/'+domEle["bankpic"]+'"></span>'+
	    								'<em class="fr ff">储蓄卡</em>'+
	    							'</div>'+
	    							'<div class="plusBank_center_num clearfix">'+
	    								'<div class="fc_3 fs_14 fl">'+domEle["cardno"]+'</div>'+
	    								'<a href="javascript:;" class="fl blue cen" onclick="edit('+domEle['mbcid']+');" title="修改">修改</a>'+
	    								'<a href="javascript:;" class="fl blue thir" onclick="delete1('+domEle['mbcid']+');" title="删除">删除</a>'+
	    							'</div>'+
	    						'</li>';
	    		
	    		$('#bankCardList').append(bankList);
    			});
	    	}   
		});
	}else{
		
	}


}

//管理银行卡添加
function add(){
	$('#manageBank').addClass('none');
	$('#bankCardAdd').removeClass('none');
	$('#addBankStep1').removeClass('none');
	
	$.ajax({
    	type: "post",
   	 	url: "/member/addBankzStep.do",
    	dataType: "json",
    	data:{
    		doWhat:"add"
    	},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		$('#bankPhone').text(msg['memberPhone']);
    		$("#addBankPhoneHidden").attr("value",msg['phone']);
    		$('#doWhat').val(msg['doWhat']);
    	}
	});
}

function delete1(mbbId){
	$.ajax({
    	type: "post",
   	 	url: "/member/delOrUpCan.do",
    	dataType: "json",
    	data:{
    		mbbId:mbbId
    	},
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(msg){
    		if(msg == -2){
    			$(".plusBank1 .content").html('您的部分资金需要使用该银行卡才能提现，不能删除该卡。');
    			$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
    			showCon_1();
    			return false;
    		}if(msg == -3){
    			$(".plusBank1 .content").html('您的部分资金正在使用该银行卡提现中，不能删除该卡。');
    			$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
    			showCon_1();
    			return false;
    		}else{
//    			art.dialog.data('mbbId', mbbId);
//    			var _url = '../ftl/memberBank/deleteBankConfirm.html'; 
//    			art.dialog.load(_url, false).title('删除确认');
//    			return false;
    		    var stok = "";
    		    if(document.getElementById ("stok")){
    		        stok  = document.getElementById ("stok").value;
    		    }
    		   
    		    // $("#plusBankContainer1").css("display","none");
    		    var html = '<div id="bg_09171739" class="plusBankBg"></div>';
    		    html += '<div class="plusBank1 mini">';
    		    html += '<div class="topper clearfix">';
    		    html += '<span class="fl fs_18"></span>';
    		    html += '<a class="fr" onClick="closeAll_1()"></a>';
    		    html += '</div>';
    		    html += '<div class="middle">';
    		    html += '<div class="content">';
    		    html += '<i class="ico_all size24 img_false24"></i>';
    		    html += '您确定删除绑定银行卡吗？';
    		    html += '</div>';
    		    html += '<div class="btnbox">';
    		    html += '<input id="btn_09171706" type="button" class="btn btnSize_1 btn_blue" value="确定" />';
    		    html += '<input type="button" class="btn btnSize_1 btn_white" value="取消" onClick="closeAll_1()"/>';
    		    html += '</div>';
    		    html += '</div>';
    		    html += '</div>';
    		    $("#plusBankContainer1").html(html);
    		    // 使背景图的高和document的高一致
    		    $("#bg_09171739").height($(document).height());

    		    $("#btn_09171706").click(function(){
    				$.ajax({
    			    	type: "post",
    			   	 	url: "/member/deleteBank.do",
    			    	dataType: "json",
    			    	data:{
    			    		mbbId:mbbId,
    		                stok:stok
    			    	},
    			    	async: false,
    			    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    			    	success: function(result){
    			    		if(result.status == 1){
    			    			showCardList();
    			    			closeAll_1();
    			    		}
//    			    		else if(msg == -2){
//    			    			//alert('删除失败,您的部分资金需要使用该银行卡才能提现，不能删除该卡。');
//    			    			$(".plusBank1 .content").html('删除失败,您的部分资金需要使用该银行卡才能提现，不能删除该卡。');
//    			    			$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
//    			    			showCon_1();
//    			    		}else if(msg == -3){
//    			    			$(".plusBank1 .content").html('删除失败,您的部分资金正在使用该银行卡提现中，不能删除该卡。');
//    			    			$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
//    			    			showCon_1();
//    			    		}
    			    		else{
    			    			//alert('删除失败');
    			    			$(".plusBank1 .content").html(result.msg);
    			    			$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
    			    			showCon_1();
    			    		}
    			    	}   
    				});
    		    });
    		    showCon_1();
    		}
    	}   
	});

}


function addBank(){
	var certIdentity = $("#certIdentity").val();
	if(certIdentity==0){
		$(".plusBank1 .content").html('为保障账户信息安全，绑定银行卡前需要进行实名认证');
		//点击确定跳转到实名认证页面 by：cy 2014-09-17
		$(".plusBank1 .topper .plus_c").attr('onclick','closeConfirm();');
		$(".plusBank1 .btnbox").html('<button type="button" class="btn btnSize_1 btn_blue" onclick="popBoxConfirm();">立即认证</button>');
		showCon_1();
	}else if(certIdentity==1){
		$.ajax({
	    	type: "post",
	   	 	url: "/member/bankCount.do",
	    	dataType: "json",
	    	data:{},
	    	async: false,
	    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	    	success: function(msg){
	    		if(msg == 1){
	    			add();
	    		}else if(msg==0){
	    			$(".plusBank1 .content").html('最多添加6张银行卡！');
	    			showCon_1();
	    		}
	    	}
	  });
	  //7月8号修改 点击添加清空多余只显示第一步
	  $('#bankStep1').attr("class","active");
	  $('#bankStep2').attr("class","");
	  $('#bankStep3').attr("class","");
	  $('#changeable').text("2.添加银行卡");
	  
	  $('#addBankStep1').removeClass('none');
	  $('#addBankStep2').addClass('none');
	  $('#addBankStep3').addClass('none');
	}
}

function yes(){
	closeDialog();
    var stok = "";
    if(document.getElementById ("stok")){
        stok  = document.getElementById ("stok").value;
    }
	window.location.href="/member/deleteBank.do?mbbId="+art.dialog.data('mbbId')+"&stok="+stok;
}

function no(){
	closeDialog();
}

//关闭页面所有弹出层------------------------------- 
function closeDialog() { 
	var list = $.dialog.list; 
	for (var i in list) { 
		list[i].close(); 
	}; 
}

/**
 * 未实名认证提示跳转
 */
function popBoxConfirm(){
	//还原关闭X按钮
	$(".plusBank .plus_c").removeAttr("onclick");
	//还原原来内容
	$(".plusBank .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
	//关闭弹出框
	closeAll_1();
	//跳转到实名认证
	window.location.href = '/member/identityAuthentication.do';
}

/**
 * 替换后的弹出框关闭X按钮
 */
function closeConfirm(){
	//还原关闭X按钮
	$(".plusBank1 .plus_c").removeAttr("onclick");
	//还原原来内容
	$(".plusBank1 .btnbox").html('<button class="btn btnSize_1 btn_blue" onClick="closeAll_1();">确认</button>');
	//关闭弹出框
	closeAll_1();
}
