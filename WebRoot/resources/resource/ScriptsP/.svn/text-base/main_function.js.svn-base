/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-09-07 20:34:04
 * @version $Id$
 */
// 事件源

var newNwd = {};
newNwd = {
	emmit : function () {
			  for (var e in eventLists) {
			        var event = eventLists[e];
			        (function (event) {
			            $(document).on(event.type, event.target, function (ev) {
			                event.handle.call(this, ev);
			            });
			        })(event);
			    };
			},
	downFun : function (opt) {
		var date = new Date();
	    var nowYear = date.getFullYear();
	    var year = nowYear-1;
		var contentHtml = [
			'<div class="form clearfix">',
			'			<div class="u-input clearfix">',
			'	            <div class="u-label fl">选择年份</div>',
			'	            <div class="fl input-text input-text-cur">',
			'		            <select class="ui-input w150-input" id="findYear" name="findYear">',
			'						<option value="'+nowYear+'">'+nowYear+'</option>',
			'						<option value="'+year+'">'+year+'</option>',
			'					</select>',
			'	            </div>',
			'	            <div class="verify clearfix">',
			'	            	<div class="verify-l fl">',
			'	            	</div>',
			'	            	<div class="verify-r fl">',
			'	            		<div class="verfify-con"></div>',
			'	            	</div>',
			'	            </div>',
		    '           </div>',
		    '            <div class="u-input clearfix">',
			'	            <div class="u-label fl">验证码</div>',
			'	            <div class="fl input-text input-text-cur">',
			'		            <div id="div_geetest_two"></div><div id="div_id_embed_two"></div><input class="channel" type="hidden" name="channel" value="3"><input type="hidden" id="imgquickcode" name="imgquickcode">',
			'	            </div>',
			'	            <div class="verify clearfix">',
			'	            	<div class="verify-l fl">',            		
			'	            	</div>',
			'	            	<div class="verify-r fl">',
			'	            		<div class="verfify-con"></div>',
			'	            	</div>',
			'	            </div>',
		    '           </div>',
		    '             <div class="u-input clearfix">',
			'	            <div class="u-label fl">&nbsp</div>',
			'	            <div class="fl input-text input-text-cur">',
			'		            <button class="btn btn_36c btn_size120 tx_ml_8 downSet">点击下载</button>',
			'	            </div>',
		    '           </div>',
			'		</div>'
		].join("");
		
		//var contentHtml =  '<div></div>';
		$.nwdDialog({
			content: contentHtml,
			dialogType: 1,      // 1：为 confirm 的自传内容 2：为成功或警告的提示
			yesCallBack:function(){
				alert(123);
				/*$.nwdDialog({
					content: '',
					dialogType: 4,  // 1：为 confirm 的自传内容 2：为成功或警告的提示类型
					dialogStatus: 1 // dialogtype为2时 成功或警告，1：成功

				})*/
			}
		})
	}
}
var eventLists = {
  downBtn: {
    'type': 'click',
    'target': '.downBtn',
    'handle': newNwd.downFun
  }
}
newNwd.emmit();
