
function clearNoNum(event, obj) {
	// 响应鼠标事件，允许左右方向键移动
	event = window.event || event;
	if (event.keyCode == 37 | event.keyCode == 39) {
		return;
	}
	
	// 先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g, "");
	// 必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g, "");
	// 保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g, ".");
	// 保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
}
function checkNum(obj) {
	// 为了去除最后一个.
	obj.value = obj.value.replace(/\.$/g, "");
}

/*
 * 限制备注字数
 */
function LimitTextArea(field) {
	maxlimit = 200;
	if (field.value.length > maxlimit) {
		field.value = field.value.substring(0, maxlimit);
	}
}
function queryAllPerson(pageNum, pageSize) {
	// 查询所有数据
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#form-select").submit();
}
