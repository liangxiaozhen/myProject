 /* 
 * 数字验证!验证输入的金额的
 */  
/* onblur="checkMoney()" onkeyup="checkUp()" */
	//当前元素失去焦点时触发的事件 
	function checkBlur(obj) {
		//为了去除最后一个. 
		obj.value = obj.value.replace(/\.$/g, "");
	}
	//当键盘上某个按键被按放开时触发的事件
	function checkUp(event, obj) {
		//响应鼠标事件，允许左右方向键移动 
		event = window.event || event;
		if (event.keyCode == 37 | event.keyCode == 39) {
			return;
		}
		//先把非数字的都替换掉，除了数字和. 
		obj.value = obj.value.replace(/[^\d.]/g, "");
		//必须保证第一个为数字而不是. 
		obj.value = obj.value.replace(/^\./g, "");
		//保证只有出现一个.而没有多个. 
		obj.value = obj.value.replace(/\.{2,}/g, ".");
		//保证.只出现一次，而不能出现两次以上 
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
	}
