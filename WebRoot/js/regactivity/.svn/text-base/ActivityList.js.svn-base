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
	/** 备注tips */
	$("[data-toggle='tooltip']").tooltip({
		html : true
	});
})

/** 分页查询注册活动规则列表 */
function queryAllPerson(pageNum, pageSize) {
	$("#pageNum").val(pageNum);
	$("#pageSize").val(pageSize);
	$("#regActivityRuleForm").submit()
}

var regList = {
		/** 手动新增注册活动规则 */
		insert_activity_rule : function(obj) {
			window.location.href = "admin/activity/toSetRule.action";
		},
		/** 模板新增注册活动规则 */
		insertByTemplet : function(){
			var url = "admin/activity/queryActivityTemplet.action";
			var callback = function(data) {
				if(data == "logout"){
					window.location.href = "admin/login.action";
				}
				$("#templetModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#templetModal-body").html(data);
			}
			$.post(url, callback);
		},
		/** 继续完善注册活动规则 */
		continueFinishRule : function(id) {
			window.location.href = "admin/activity/toSetAwardRule.action?id="+id;
		},
		/** 查看注册活动规则详情 */
		queryDetail : function(actno) {
			var url = "admin/activity/queryActivityDetail.action";
			var params = {
					"actno" : actno
			}
			var callback = function(data) {
				if(data == "logout"){
					window.location.href = "admin/login.action";
				}
				$("#myModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#modal-body").html(data);
			}
			$.post(url, params, callback);
		},
		/** 编辑册活动规则 */
		editRule : function(id){
			window.location.href = "admin/activity/toEditRegActRule.action?id="+id;
		},
		/** 修改注册活动状态 */
		updateStatus : function(id,obj){
			var text = $(obj).text();
			if(confirm("是否确定"+text+"该活动？")){
				var operation = $(obj).data("operation");
				var url = "admin/activity/updateRegActivityRuleStatus.action";
				var params = {
						"id" : id,
						"operation" : operation
				}
				var callback = function(data) {
					if(data == "logout"){
						window.location.href = "admin/login.action";
					}
					var json = $.parseJSON(data);
					alert(json.result);
					window.location.href = "admin/activity/queryActivityList.action";
				}
				$.post(url, params, callback);
			}
		},
		/** 删除注册活动规则 */
		deleteRule : function(id){
			if(confirm("是否确定删除该活动？")){
				var url = "admin/activity/deleteRegActivityRule.action";
				var params = {
						"id" : id
				}
				var callback = function(data) {
					if(data == "logout"){
						window.location.href = "admin/login.action";
					}
					var json = $.parseJSON(data);
					alert(json.result);
					window.location.href = "admin/activity/queryActivityList.action";
				}
				$.post(url, params, callback);
			}
		},
		/** 重置查询条件 */
		reset : function() {
			$("#actno").val('');
		}
}