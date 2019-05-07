//验证提交表单
function check() {
	jQuery.validator.addMethod("telphone", function(value, element) {
		var tel = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		var mobile = /^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$/;
		return this.optional(element) || (tel.test(value) || mobile.test(value));
	}, " *请输入有效电话号码！");
	jQuery.validator.addMethod("chinese", function(value, element) {
		var lan = /^[\u4e00-\u9fa5]{1,50}$/;
		return this.optional(element) || (lan.test(value));
	}, " *请输入1-50位中文字符！");
	return $("#updateguaranteeForm").validate({
		// 定位提示节点
		errorPlacement: function (error, element) {
			var p = $("<em></em>").append(error);
			p.appendTo(element.parent().parent());
			p.css({"color":"red"});
		},
		rules : {
			name : {
				"required" : true,
				"chinese" : true
			},
			regfunds : {
				"required" : true,
				"number" : true,
				"min" : true
			},
			regtime : {
				"required" : true
			},
			website : {
				"required" : true,
				"url" : true
			},
			addr : {
				"required" : true
			},
			phone : {
				"required" : true,
				"telphone" : true
			},
			description : {
				"maxlength" : 200
			},
			remark : {
				"maxlength" : 200
			}
		},
		messages : {
			name : {
				"required" : " *请输入公司名称！"
			},
			regfunds : {
				"required" : " *请输入注册资金！",
				"number" : " *请输入有效数字！",
				"min" : " *请输入非负数！"
			},
			regtime : {
				"required" : " *请输入注册时间！"
			},
			website : {
				"required" : " *请输入公司网站！",
				"url" : " *请输入有效网站！"
			},
			addr : {
				"required" : " *请输入公司地址！"
			},
			phone : {
				"required" : " *请输入联系电话！"
			},
			description : {
				"maxlength" : " *最多输入200个字符！"
			},
			remark : {
				"maxlength" : " *最多输入200个字符！"
			}
		}
	})
}

$(document).ready(function() {
	/* 上传担保公司资质图片 */
	$.tzUpload({
		img : basePath + "/js/swfupload/copy/imagebtn.png",
		targetId : "upload1",
		url : basePath + "/guarantee/upload.action",
		type : "*.jpg;*.png;*.gif",
		postName : "qualificationspic",
		single : true,
		callback : function(data) {
			var json = $.parseJSON(data);
			$("#qualificationspic").val(json.path);
			var src = "/upload/" + json.name;
			$("#quali").attr("src", src);
		}
	});
	/* 上传营业执照图片 */
	$.tzUpload({
		img : basePath + "/js/swfupload/copy/imagebtn.png",
		targetId : "upload2",
		url : basePath + "/guarantee/upload.action",
		type : "*.jpg;*.png;*.gif",
		postName : "qualificationspic",
		single : true,
		callback : function(data) {
			var json = $.parseJSON(data);
			$("#licencepic").val(json.path);
			var src = "/upload/" + json.name;
			$("#licen").attr("src", src);
		}
	});
	/* 上传组织机构代码图片*/
	$.tzUpload({
		img : basePath + "/js/swfupload/copy/imagebtn.png",
		targetId : "upload3",
		url : basePath + "/guarantee/upload.action",
		type : "*.jpg;*.png;*.gif",
		postName : "qualificationspic",
		single : true,
		callback : function(data) {
			var json = $.parseJSON(data);
			$("#orgcodepic").val(json.path);
			var src = "/upload/" + json.name;
			$("#org").attr("src", src);
		}
	});
})

var updateguarantee = {
	// 修改担保公司资料
	update : function(obj){
		if (!check().form())
			return;
		var url = basePath + "/guarantee/updateGuarantee.action";
		var params = $("#updateguaranteeForm").serializeArray();
		var callback = function(data){
			var json = $.parseJSON(data);
			alert(json.result);
			location.href = basePath + "/guarantee/queryGuaranteeList.action";
		}
		$.post(url, params, callback);
	},
	// 返回列表页
	returnback : function(obj){
		location.href = basePath + "/guarantee/queryGuaranteeList.action";
	},
}