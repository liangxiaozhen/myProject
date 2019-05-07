/**
 * 弹框组件；
 *
 */

(function($){
	$.extend({
		nwdDialog:function(options) {
			
			this.options = $.extend({
				content: "",		// 内容
				dialogType: 1,		// 1：为 confirm 的自传内容 2：为成功或警告的提示
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
		    //console.log(obj);
		    //obj.css({"top": winScrollTop + Math.max(0, (winHeight - objH) / 2) + "px", "margin-left": -objW / 2 | 0 + "px"});
		    obj.css({"margin-left": -objH / 2 | 0 + "px", "margin-left": -objW / 2 | 0 + "px"});
		},
		open:function(){
			abc();
			var _this = this;
			if (_this.options.closeOnEsc) {
				$(document).bind("keydown", function(e) {
					if (e.keyCode == 27) {
						_this.close();
					}
				});
			}
			$(".close").click(function() {
				_this.close();
			});
			$(".downSet").click(function(){
				if($.isFunction(_this.options.yesCallBack)){
					_this.close();
                    _this.options.yesCallBack.call(); 
                }
			});
			return _this;
		},
		close:function(){
			this.element.remove();
			$(".nwdDialog-mask").remove();
		},
		creatDialog:function(){
			var bodyHeight = $('body').height();
			//console.log($('body').height());
			var $html = $(this.getDialogHtml().replace("{this.options.content}", this.options.content));
			//console.log($html);
			//document.write('<script type="text/javascript" src="${rc.contextPath}/js/common/slidingValidateCodeLogin.js?.tmp='+ (new Date().getTime().toString(36)) +'"><\/script>');

			var mask;
			mask = $('<div>').addClass("nwdDialog-mask");
			var _this = this;
			mask.css({
				'height':bodyHeight
			});
			mask.appendTo(document.body);
			_this.element = $html.appendTo(document.body);
			mask.show();
			_this.element.slideDown();
			$.autoCenter($html);
			$.open();
			
		},
		getDialogHtml : function() {
			var html = [];
			 if(this.options.dialogType == 1){
				html = [
					'<div class="newPop middlePop downPop">',
					'	<div class="newPop-head clearfix">',
					'    	<span class="fl fs_18">提示</span>',
					'        <a class="fr nwd_icon close"></a>',
					'   </div>',
					'   <div class="newPop-content">',
					'		<div class="content">',
					'			{this.options.content}',
					'       </div>',
					'   </div>',
					'</div>'
				].join("");
				return html;
			}
			
		}
	});
	
	

})(jQuery);