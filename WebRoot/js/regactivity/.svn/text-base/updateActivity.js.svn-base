//验证提交表单
$(function(){
	$('#updateRegActivityForm').bootstrapValidator({
		feedbackIcons: {
			/*input状态样式图片*/
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {/*验证：规则*/
			actno: {	// 活动编号
				validators: {
					notEmpty: {//非空
						message: '活动编号不能为空'
					},
					stringLength:{
						max:30,
						message:'活动编号长度必须在30位之间'
					},
          		  	regexp:{
          		  		regexp:/^[a-zA-Z0-9]{1,30}$/,
          		  		message:'活动编号为字母或数字'
          		  	}
				}
			},
			actname: {	// 活动名称
				validators: {
					notEmpty: {//非空
						message: '活动名称不能为空'
					},
					stringLength:{
						max:16,
						message:'活动名称长度必须在16位之间'
					},
          		  	regexp:{
          		  		regexp:/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
          		  		message:'活动名称为中文、英文字母和数字'
          		  	}
          		  	
				}
			},
			isconsiderregtime : { // 是否判断注册时间
				validators: {
					notEmpty: {//非空
						message: '请选择是否判断注册时间'
					}
				}
			},
			isauditalist : { // 是否审核
				validators: {
					notEmpty: {//非空
						message: '请选择是否审核'
					}
				}
			},
			istemplet : { // 是否为模板
				validators: {
					notEmpty: {//非空
						message: '请选择是否为模板'
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
})

var regUpdate = {
	/* 保存修改的内容  */
	save : function(obj) {
		$('#updateRegActivityForm').data('bootstrapValidator').validate(); 
		if(!$('#updateRegActivityForm').data('bootstrapValidator').isValid()) 
			return ;
		var url = "admin/activity/updateRegActivityRule.action";
		var params = $("#updateRegActivityForm").serializeArray();
		var callback = function(data) {
			var json = $.parseJSON(data);
			alert(json.result);
			location.href = "admin/activity/queryActivityList.action";
		}
		$.post(url, params, callback);
	},
	check_bDate : function(dp) {
		var btime = dp.cal.getDateStr();
		var etime = $("#actetime").val();
		$("#btime").html("");
		$("#etime").html("");
		if(etime != ''){
			if (btime > etime) {
				$("#etime").html("结束日期须在开始日期之后！");
				$("#actetime").val('');
			}
		}
	},
	check_eDate : function(dp) {
		var etime = dp.cal.getDateStr();
		var btime = $("#actbtime").val();
		$("#btime").html("");
		$("#etime").html("");
		if (btime == '') {
			$("#etime").html("请先选择开始日期！");
			$("#actetime").val('');
		}else{
			if (btime > etime) {
				$("#etime").html("结束日期须在开始日期之后！");
				$("#actetime").val('');
			}
		}
	},
	insert : function(){
		$('#updateRegActivityForm').data('bootstrapValidator').validate(); 
		if(!$('#updateRegActivityForm').data('bootstrapValidator').isValid()) 
			return ; 
		var form = document.getElementById("updateRegActivityForm");
		form.action = "admin/actAward/insertRegAwardRule.action";
		form.submit();
	}
}