//验证提交表单
var faIcon = {
	/*input状态样式图片*/
	valid: 'glyphicon glyphicon-ok',
	invalid: 'glyphicon glyphicon-remove',
	validating: 'glyphicon glyphicon-refresh'
}
$(function(){
	$('#insertRegActivityForm').bootstrapValidator({
		feedbackIcons: faIcon,
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
          		  		regexp:/^[\u4e00-\u9fa5_a-zA-Z0-9]{1,50}$/,
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
	$('#insertRegActAwardForm').bootstrapValidator({
		feedbackIcons: faIcon,
		fields: {/*验证：规则*/
			crestrict: { // 客户端限制
				validators: {
					notEmpty: {//非空
						message: '请选择客户端限制'
					}
				}
			},
			business: {	// 指定业务
				validators: {
					notEmpty: {//非空
						message: '请选择指定业务'
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
})

var regInsert = {
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
		queryname : function(number){
			if(number != ''){
				var url = "admin/actAward/queryAwardName.action";
				var param = {
					"awardno" : number
				};
				var callback = function(data){
					var json = $.parseJSON(data);
					if(json == null){
						$("#awardname").val('');
						$("#awardid").val('');
						$("#aname").text('');
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
			}
		},
		checkForm : function(){
			if($("#actbtime").val() == ''){
				$("#btime").html("请选择开始日期");
				return false;
			}else{
				$("#btime").html("");
			}
			if($("#actetime").val() == ''){
				$("#etime").html("请选择结束日期");
				return false;
			}else{
				$("#etime").html("");
			}
		},
		/** 保存活动规则 */
		save : function(){
			$('#insertRegActAwardForm').data('bootstrapValidator').validate(); 
			if(!$('#insertRegActAwardForm').data('bootstrapValidator').isValid()) 
				return ; 
			var url = "admin/actAward/saveRegActAwardRule.action";
			var param = $("#insertRegActAwardForm").serializeArray();
			var callback = function(data){
				var json = $.parseJSON(data);
				alert(json.result);
				window.location.href="admin/activity/queryActivityList.action";
			};
			$.post(url, param, callback);
		},
		/** 查询活动模板 */
		queryTemplet : function(value){
			var templetId = $("#templetId").val();
			var actid = $("#actid").val();
			if(value==''){
				alert("请选择业务类型");
			}else{
				var url = "admin/actAward/fillByTemplet.action";
				var param = {
					"business" : value,
					"templetId" : templetId,
					"actid" : actid
				};
				var callback = function(data){
					$("body").html(data);
				};
				$.post(url, param, callback);
			}
		},
		selectBusiness : function(value){
			if(value != 1){
				$("#limit_time").removeAttr("hidden");
			}
		},
		/** 新增时返回上一页 */
		previousPage : function(){
			var form = document.getElementById("insertRegActAwardForm");
			form.action = "admin/actAward/queryPreviousRule.action";
			form.submit();
		},
		/** 查询奖品剩余数 */
		queryCopies : function(){
			var url = "admin/actAward/queryRestCopies.action";
			var params = $("#insertRegActAwardForm").serializeArray();
			var callback = function(data) {
				$("#copies").html(data);
				if(data != ''){
					$("#awardcopies").val('');
				}
			}
			$.post(url, params, callback);
		}
}