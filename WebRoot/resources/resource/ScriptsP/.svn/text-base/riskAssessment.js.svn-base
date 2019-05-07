(function($){
	$.extend({
		jrDialog:function(options) {
			this.options = $.extend({
				content: "",		// 内容
				dialogType: 1,	// 1：为 confirm 的自传内容 2：为成功或警告的提示
				dialogTitle:"",
				dialogStatus: 1,	// dialogtype为2时 成功或警告，1：成功 
				modal: true,		// 是否为模式，带遮罩层
				closeOnEsc: true,	//是否支持esc关闭对话框
				yesCallBack: null	// 点击确定按钮执行的回调
			}, options);

			this.creatDialog();
		},
		autoCenter:function(obj) {

		    if (!obj[0])return false;
		    var winHeight = $(window).height();
		    var winScrollTop = $(window).scrollTop();
		    var objH = obj.height();
		    var objW = obj.width();
		    //obj.css({"top": winScrollTop + Math.max(0, (winHeight - objH) / 2) + "px", "margin-left": -objW / 2 | 0 + "px"})
		},
		open:function(){
			var _this = this;
			if (_this.options.closeOnEsc) {
				$(document).bind("keydown", function(e) {
					if (e.keyCode == 27) {
						_this.close();
					}
				});
			}
			$(".close,.btn-dialog-cancel,.btn-dialog-sure").click(function() {
				_this.close();
			});
			$(".btn-dialog-confirm").click(function(){
				if($.isFunction(_this.options.yesCallBack)){
					_this.close();
                    _this.options.yesCallBack.call(); 
                }
			});
			return _this;
		},
		close:function(){
			this.element.remove();
			$(".mask").remove();
		},
		creatDialog:function(){
			
			var $html = $(this.getDialogHtml().replace("{resultContent}",this.options.content).replace("{dialogTitle}",this.options.dialogTitle));
			var mask;
			mask = $('<div>').addClass("mask");
			var _this = this;
			mask.appendTo(document.body);
			_this.element = $html.appendTo(document.body);
			$html.slideDown();
			$.autoCenter($html);
			
			$.open();
		},
		getDialogHtml : function() {
			var html = [];
			if(this.options.dialogType == 2){
				html = [
					'<div class="jrDialog-2 jrDialog">',
					'    <div class="jrDialogWrap">',
					'        <div class="paneltitle">',
					'            <span class="text">{dialogTitle}</span>',
					'            <span class="nwd_icon nwd_icon_close close"></span>',
					'        </div>',
					'        <div class="jrDialog-content pad_l20 pad_r20 pad_t20 pad_b20">{resultContent}</div>',
					'    </div>',
					'</div>'
				].join("");
				
				
				return html;
			}
			else
				if(this.options.dialogType == 1) {
					html = [
						'<div class="jrDialog-1 jrDialog">',
						'    <div class="jrDialogWrap">',
						'        <div class="paneltitle">',
						'            <span class="text">{dialogTitle}</span>',
						'            <span class="nwd_icon nwd_icon_close close"></span>',
						'        </div>',
						'        <div class="jrDialog-content pad_l20 pad_r20 pad_t20 pad_b20">{resultContent}</div>',
						'    </div>',
						'</div>'
					].join("");
					return html;
				}
			else
				if(this.options.dialogType == 3) {
					html = [
						'<div class="jrDialog-3 jrDialog">',
						'    <div class="jrDialogWrap">',
						'        <div class="paneltitle">',
						'            <span class="text">{dialogTitle}</span>',
						'            <span class="nwd_icon nwd_icon_close close"></span>',
						'        </div>',
						'        <div class="jrDialog-content pad_l20 pad_r20 pad_t20 pad_b20">{resultContent}</div>',
						'    </div>',
						'</div>'
					].join("");
					return html;
				}
			
			
		}
	});

})(jQuery);




function goEvaFun () {
	var certIdentity= $('#certIdentity').val();
	if("1" == certIdentity){
		var times=$('#riskTimes').val()*1;
		if(times > 0){
			window.location.href = '/member/riskAssessment.do';
			return;
		}else{
			var d = new Date();
			var year = d.getFullYear()+1;
			var title="从"+year+"-01-01起可重新评估。";
		}
		
		$.jrDialog({
			content: [
				'<div class="goEvl">',
		    	'	<div class="fc_3 ta-c mar_t30">今年的测试次数已达到上限。</div>',
		    	'	<div class="fc_3 ta-c">'+title+'</div>',
		    	'	<div class="ta-c"><a href="javascript:;" class="btn btn_size100 btn_blue mar_t30 btn-dialog-sure">确定</a></div>',
		    	'</div>'
			].join(""),
			dialogTitle:"风险评估测试",
			dialogType: 1,  // 
			dialogStatus: 1 //
		})	
		
	}else{
		$.jrDialog({
			content: [
				'<div class="goEvl">',
		    	'	<div class="fc_3 ta-c mar_t30">风险评估测试前，请先进行实名认证。</div>',
		    	'	<div class="ta-c"><a href="javascript:;" class="btn btn_size100 btn_blue mar_t30 btn-dialog-sure">确定</a></div>',
		    	'</div>'
			].join(""),
			dialogTitle:"风险评估测试",
			dialogType: 1,  // 
			dialogStatus: 1 //
		})	
	}	
			
}



var Evaluation = {};
var eventLists = {
	selectOpt : {
		'type': 'click',
		'target': '.selectOpt',
		'handle': selectOptFun
	},
	evlBtn : {
		'type': 'click',
		'target': '.evlBtn',
		'handle': evlBtnFun
	}
	
	
};

//事件处理函数
Evaluation.emmit = function () {
    for (var e in eventLists) {
        var event = eventLists[e];
        (function (event) {
            $(document).on(event.type, event.target, function (ev) {
                event.handle.call(this, ev);
            });
        })(event);
    };
};
function selectOptFun () {
	var $this = $(this)
	var $oRadio = $this.find('.oRadio');
	var $input = $this.find("input").eq(0);
	var $brothers = $this.siblings().find('.oRadio');
	$oRadio.hasClass("oRadio_act") ? $oRadio.removeClass("oRadio_act") : $oRadio.addClass("oRadio_act");
	$brothers.removeClass('oRadio_act');
	
}

Evaluation.init = function () {
	this.emmit();
	
};
Evaluation.init();


function evlBtnFun () {
	var riskTimes=$('#riskTimes').val()*1;
	if(riskTimes < 1){
		$.jrDialog({
			content: [
				'<div class="goEvl">',
		    	'	<div class="fc_3 ta-c mar_t30">今年的测试次数已达到上限</div>',
		    	'	<div class="ta-c"><a href="javascript:;" class="btn btn_size100 btn_blue mar_t30 btn-dialog-sure">确定</a></div>',
		    	'</div>'
			].join(""),
			dialogTitle:"风险评估测试",
			dialogType: 1,  // 
			dialogStatus: 1 //
	   });	
		
	   return;
	}
	
	//判断是否全部选择
	var riskType=1;
	var total=0;
	var answerNum=0;
	var answer="";
	$(".oRadio_act").each(function(){  
		var selecteNum=$(this).parent().find('input').eq(0).val()*1;
		total+=selecteNum;
		
		var selecteIten=$(this).parent().find('.optionCol').eq(0).text().trim();
		selecteIten=selecteIten.charAt(0);
		answer=answer+selecteIten;
		
		answerNum=answerNum+1;
    });
	
	if(answerNum < 10){		
		
		$.jrDialog({
			content: [
				'<div class="goEvl">',
		    	'	<div class="fc_3 ta-c mar_t30">请先将所有题目回答完毕</div>',
		    	'	<div class="ta-c"><a href="javascript:;" class="btn btn_size100 btn_blue mar_t30 btn-dialog-sure">确定</a></div>',
		    	'</div>'
			].join(""),
			dialogTitle:"风险评估测试",
			dialogType: 1,  // 
			dialogStatus: 1 //
	   });	
		
	   return;		
	}
	
	if(total < 40){
		riskType=0;
	}else if(total < 55){
		riskType=1;
	}else if(total < 60){
		riskType=2;
	}else if(total < 80){
		riskType=3;
	}else{
		riskType=4;
	}
	
	$.ajax({
    	type: "post",
   	 	url: "/member/saveRiskType.do",
    	dataType: "json",
    	data:{
    		riskType:riskType,
    		answer:answer,
    		score:total
   		 },
    	async: false,
    	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    	success: function(result){
    		if("1" == result.status){
    			var resuleIcon=result.nowPic;
    			var riskTimes=result.riskTimes;
    			var riskDesc=result.riskDesc;
    			
    			var contextPath= $('#contextPath').val();
    			
    			var show='您可以 <a href="'+contextPath+'/member/riskAssessment.do"><em class="fc_ffa200">重新评估风险承受类型</em></a>';
    			
    			if(riskTimes < 1){
    				show="";
    			} 
    			
    			$('#riskTimes').val(riskTimes);
    			
    			$.jrDialog({
    				content: [
    					'<div class="evalResult evalResult-l fl">',
    					'	<div class="evalResultName txt_center">您当前的评估类型为：<em>'+riskDesc+'</em></div>',
    					'	<div class="fs_18 mar_t25">您今年还有 <em class="fc_3">'+riskTimes+'次</em> 评估机会</div>',	
    					'	<div class="fc_6 mar_t10">当您的状况有重大转变时，对您的风险评估进行检视，确保您的投资决定与您对风险所持的态度一致。</div>',
    					'	<div class="fc_9 mar_t20">'+show+'</div>',
    					'</div>',
    					'<div class="evalResult-r fr">',
    					'	<div class="resuleIcon resuleIcon-keep"></div>',
    					'	<div class="ta-c"><a href="'+contextPath+'/financial/financialDetail.do" class="btn btn_size100 btn_blue mar_t30 btn-dialog-sure">立即投资</a></div>',
    					'</div>'
    				].join(""),
    				dialogTitle:"评测结果",
    				dialogType: 3,  // 
    				dialogStatus: 1 //

    			})	
    			
    		}else{
    			
    			$.jrDialog({
    				content: [
    					'<div class="goEvl">',
    			    	'	<div class="fc_3 ta-c mar_t30">暂不能进行风险评估，请稍后再试！</div>',
    			    	'	<div class="ta-c"><a href="javascript:;" class="btn btn_size100 btn_blue mar_t30 btn-dialog-sure">确定</a></div>',
    			    	'</div>'
    				].join(""),
    				dialogTitle:"风险评估测试",
    				dialogType: 1,  // 
    				dialogStatus: 1 //
    		   });	
    		}
    		
    	}   
    }); 		
	
}


