/* 限制备注显示字符个数 */
jQuery.fn.limit = function() {
	var self = $("[limit]");
	self.each(function() {
		var objString = $(this).text();
		var objLength = $(this).text().length;
		var num = $(this).attr("limit");
		if (objLength > num) {
			objString = $(this).text(objString.substring(0, num) + "...");
		}
	})
}

$(function() {
	$("[limit]").limit();
})

/* 备注tips */
$(function() {
	$("[data-toggle='tooltip']").tooltip({
		html : true
	});
});

/* 分页查询注册活动奖励规则列表 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#regActAwardRuleForm").submit()
}

var regAwardList = {
		/* 查看注册活动奖励规则详情 */
		queryDetail : function(id) {
			var url = "admin/actAward/queryActAwardDetail.action";
			var params = {
					"id" : id
			}
			var callback = function(data) {
				$("#myModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#modal-body").html(data);
			}
			$.post(url, params, callback);
		},
		/* 修改注册活动奖励规则 */
		editAwardRule : function(id) {
			window.location.href="admin/actAward/toEditRegActAwardRule.action?id="+id;
		},
		deleteAwardRule : function(id){
			if(confirm("是否确定删除此活动奖励规则？")){
				var url = "admin/actAward/deleteRegActAwardRule.action";
				var params = {
						"id" : id
				}
				var callback = function(data) {
					var json = $.parseJSON(data);
					alert(json.result);
					window.location.href="admin/actAward/queryActAwardList.action";
				}
				$.post(url, params, callback);
			}
		},
		/* 重置查询条件 */
		reset : function() {
			$("#actno").val('');
			$("#business").val('');
		}
}