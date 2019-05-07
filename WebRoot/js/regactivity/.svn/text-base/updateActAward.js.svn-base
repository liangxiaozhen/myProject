$(function(){
	//编辑的时候客户端限制的回显
	var key = $("#keylist").val();
	if (key.length > 0) {
		for ( var p in key) {
			$("input[type='checkbox']").each(function() {
				if ($(this).val() == key[p]) {
					$(this).attr("checked", true);
				}
			});
		}
	}	
	$('#updateRegActAwardForm').bootstrapValidator({
		feedbackIcons: {
			/*input状态样式图片*/
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {/*验证：规则*/
			crestrict: { // 客户端限制
				validators: {
					notEmpty: {//非空
						message: '请选择客户端限制'
					}
				}
			},
			finishtime : { // 限定时间
				validators: {   
					notEmpty: {//非空
						message: '限定时间不能为空'
					},
					stringLength:{
						max:5,
						message:'限定时间长度必须在5位之间'
					},
          		  	regexp:{
          		  		regexp:/^\+?[1-9][0-9]*$/,
          		  		message:'限定时间为正整数'
          		  	}
				}
			},
			awardno : { // 奖品编号
				validators: {
					notEmpty: {//非空
						message: '奖品编号不能为空'
					}
				}
			},
			awardname : { // 奖品名称
				validators: {
					notEmpty: {//非空
						message: '奖品名称不能为空'
					}
				}
			},
			awardcopies : { // 奖品份数
				validators: {
					notEmpty: {//非空
						message: '奖品份数不能为空'
					},
					stringLength:{
						max:5,
						message:'奖品份数长度必须在5位之间'
					},
          		  	regexp:{
          		  		regexp:/^\+?[1-9][0-9]*$/,
          		  		message:'奖品份数为正整数'
          		  	}
				}
			},
			quota : { // 奖品金额
				validators: {
					notEmpty: {//非空
						message: '奖品金额不能为空'
					},
          		  	regexp:{
          		  		regexp:/^\+?[1-9][0-9]*$/,
          		  		message:'奖品金额为正整数'
          		  	}
				}
			},
			remark : { // 是否为模板
				validators: {
					stringLength:{
						max:66,
						message:'备注长度必须在66位之间'
					}
				}
			}
		}
	});
	var business = $("#business").val();
	if(business != 1){
		$("#limit_time").removeAttr("hidden");
	}
})

var regUpdate = {
	queryname : function(no){
		var url = "admin/actAward/queryAwardName.action";
		var param = {
				"awardno" : no
		};
		var callback = function(data){
			var json = $.parseJSON(data);
			$("#awardcopies").val('');
			if(json == null){
				$("#awardname").val('');
				$("#aname").text('');
				$("#awardid").val('');
				$("#number").html("奖品编号不存在");
				$("#awardno").val('');
			}else{
				$("#awardname").val(json.aname);
				$("#aname").text(json.aname);
				$("#awardid").val(json.id);
				$("#number").html("");
				if(json.isDefineAmount==2){
					$("#cost").attr("hidden","hidden");
					$("#quota").val(json.cashorvoucher);
				}
			}
		};
		$.post(url, param, callback);
	},
	save : function(obj) {
		$('#updateRegActAwardForm').data('bootstrapValidator').validate(); 
		if(!$('#updateRegActAwardForm').data('bootstrapValidator').isValid()) 
			return ; 
		var url = "admin/actAward/update.action";
		var params = $("#updateRegActAwardForm").serializeArray();
		var callback = function(data) {
			var json = $.parseJSON(data);
			alert(json.result);
			window.location.href = "admin/activity/queryActivityList.action";
		}
		$.post(url, params, callback);
	},
	/** 删除本活动 */
	deleteRule : function(id){
		var form = document.getElementById("updateRegActAwardForm");
		form.action = "admin/actAward/deletePreviousRegActAwardRule.action";
		form.submit();
	},
	/** 新增子活动 */
	insertRule : function(id){
		$('#updateRegActAwardForm').data('bootstrapValidator').validate(); 
		if(!$('#updateRegActAwardForm').data('bootstrapValidator').isValid()) 
			return ; 
		var form = document.getElementById("updateRegActAwardForm");
		form.action = "admin/actAward/insertRegAwardRule.action";
		form.submit();
	},
	/** 编辑时删除子活动 */
	removeRule : function(id){
		var form = document.getElementById("updateRegActAwardForm");
		form.action = "admin/actAward/deleteAwardRule.action";
		form.submit();
	},
	/** 编辑时返回上一页 */
	previousPage : function(){
		var form = document.getElementById("updateRegActAwardForm");
		form.action = "admin/actAward/returnPreviousRule.action";
		form.submit();
	},
	/** 查询奖品剩余数 */
	queryCopies : function(){
		var url = "admin/actAward/queryRestCopies.action";
		var params = $("#updateRegActAwardForm").serializeArray();
		var callback = function(data) {
			$("#copies").html(data);
			if(data != ''){
				$("#awardcopies").val('');
			}
		}
		$.post(url, params, callback);
	}
}