     /*
	 * 转换为 yyyy-MM-dd HH:mm:ss 格式 
	 */
	function datetimeFormat(datetime) {
		var date = new Date(datetime);
		var y = date.getFullYear();
		var m = date.getMonth();
		var month = m + 1; // 月份是从0开始计数
		var d = date.getDate();
		if (month.toString().length == 1) {
			month = "0" + month.toString();
		}
		if (d.toString().length == 1) {
			d = "0" + d.toString();
		}
		var h = date.getHours();
		var m = date.getMinutes();
		var s = date.getSeconds();
		if (h.toString().length == 1) {
			h = "0" + h.toString();
		}
		if (m.toString().length == 1) {
			m = "0" + m.toString();
		}
		if (s.toString().length == 1) {
			s = "0" + s.toString();
		}
		return y + "-" + month + "-" + d + " " + h + ":" + m + ":" + s;
	};

