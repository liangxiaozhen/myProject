//验证提交表单
function check() {
	return $("#userbankForm").validate({
		// 定位提示节点
		errorPlacement : function(error, element) {
			var p = $("<em></em>").append(error);
			p.appendTo(element.parent().parent());
			p.css({
				"color" : "red"
			});
		},
		rules : {
			bankname : {
				"required" : true
			},
			cardno : {
				"required" : true
			},
			province : {
				"required" : true
			},
			city : {
				"required" : true
			},
			subbranch : {
				"required" : true
			},
			cardtype : {
				"required" : true
			}
		},
		messages : {
			bankname : {
				"required" : " *请输入银行名称！"
			},
			cardno : {
				"required" : " *请输入卡号！"
			},
			province : {
				"required" : " *请选择开户行省！"
			},
			city : {
				"required" : " *请选择开户行市！"
			},
			subbranch : {
				"required" : " *请输入分支行！"
			},
			cardtype : {
				"required" : " *请选择卡类型！"
			}
		}
	})
}

var CardList = {
		/* 查看用户银行卡详情 */
		queryDetail : function(id) {
			var action = basePath+"/admin/userbankcard/queryBankInfoDetail.action";
			var param = {
					"id" : id
			}
			var callback = function(data) {
				$("#myModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#modal-body").html(data);
			}
			$.post(action, param, callback);
		},
		/* 回调函数 */
		callback : function(data) {
			$("#area_div").html(data);
		},
		/* 跳转到修改用户银行卡信息页面 */
		editbankinfo : function(id) {
			var action = basePath+"/admin/userbankcard/gotoUpdatePage.action";
			var param = {
					"id" : id
			}
			$.post(action, param, this.callback);
		},
		/* 删除用户银行卡信息 */
		deletebankinfo : function(id) {
			var result = confirm('是否删除！');
			if (result) {
				var action = basePath+"/admin/userbankcard/deleteUserBankInfo.action";
				var param = {
						"id" : id
				}
				var delete_success_callback = function(returnData) {
					var obj = $.parseJSON(returnData);
					if (obj.result == "success") {
						$("#bank_tr_" + obj.personId).remove();
					} else {
						alert(obj.result);
					}
				}
				$.post(action, param, delete_success_callback);
			}
		},
		/* 跳转到新增用户银行卡信息页面 */
		insert_bank_info : function() {
			var action = basePath+"/admin/userbankcard/gotoInsertPage.action";
			$.post(action, this.callback);
		},
		/* 获取选中的省份，关联出相应的城市 */
		change : function() {
			var province = $("#province").val();
			var action = basePath+"/admin/userbankcard/getCitysList.action"
				var param = {
					"province" : province
			}
			var callback = function(jsonStr) {
				var citys = document.getElementById("city");
				citys.options[0] = new Option("--请选择--", '');
				citys.options[0].selected = true;
				json = eval(jsonStr);
				for ( var p in json) {
					citys.options[citys.length] = new Option(json[p].name,
							json[p].name);
				}
			}
			/* 清空select所有option项 */
			document.getElementById("city").length = 0;
			$.post(action, param, callback);
		},
		/* 重置查询条件 */
		reset : function() {
			document.getElementById("username").value = '';
		},
		/* 新增页面的返回 */
		returnback : function() {
			location.href = basePath+"/admin/userbankcard/queryBankInfoList.action";
		}
	};

	/* 备注显示字符个数限制 */
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
			//				$(this).attr("title", objString);
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}
	$(function() {
		$("[limit]").limit();
	})
	/* 分页查询用户银行卡信息列表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#bankform").submit()
	}
	$(function() {
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
	});



//var userbank = {
//		/* 获取选中的省份，关联出相应的城市 */
//		change : function() {
//			var province = $("#province").val();
//			var action = "user/bankcard/getCitysList.action"
//				var param = {
//					"province" : province
//			}
//			var callback = function(jsonStr) {
//				var citys = document.getElementById("city");
//				citys.options[0] = new Option("--请选择--", '');
//				citys.options[0].selected = true;
//				json = eval(jsonStr);
//				for ( var p in json) {
//					citys.options[citys.length] = new Option(json[p].name,
//							json[p].name);
//				}
//			}
//			/* 清空select所有option项 */
//			document.getElementById("city").length = 0;
//			$.post(action, param, callback);
//		},
//		// 验证银行卡卡号
//		checkCardNo : function() {	
//			var cResult = true;
//			var bankNo = $("#cardno").val();
//			if (bankNo == '') { // 校验卡号非空
//				$("#number").html('');
//				cResult = false;
//				return;
//			}
//			var numreg = new RegExp("^[0-9]*$");
//			if (!numreg.test(bankNo) || bankNo.length < 16) { //校验卡号长度
//				$("#number").html(" *请准确输入银行卡号！");
//				cResult = false;
//				return;
//			}
//			if (this.luhmCheck(bankNo)) {
//				//后台校验银行卡是否已绑定
//				$.ajax({
//					type: 'POST',
//					url: 'user/bankcard/checkCard.action',
//					data: {CardNo: bankNo},
//					dataType: 'json',
//					success: function(json) {
//						if (json== "-1") {
//							$("#number").html(' *卡号已绑定！');
//							cResult = false;
//						}
//					}
//				});
//			}
//			return cResult;
//		},
//		//保存
//		save : function() {
//			// 获取输入的验证码并去掉首尾空格
//			var smscode = $.trim($("#txt_smsCodebank").val()); 
//			// 校验form表单和验证码输入框
//			if (!check().form() && !this.checkTelCode(smscode)) 
//				return;
//			// 校验验证码输入框
//			if (!this.checkTelCode(smscode))
//				return;
//			// 校验银行卡号
//			if (!this.checkCardNo())
//				return;
//			var params = $("#userbankForm").serializeArray();
//			var url = "user/bankcard/bindBankCard.action";
//			var callback = function(data) {
//				var json = $.parseJSON(data);
//				alert(json.result);
//			};
//			$.post(url, params, callback);
//		},
//		//Luhm校验规则：16位银行卡号（19位通用）:
//
//		//1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
//		//2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
//		//3.将加法和加上校验位能被 10 整除。
//
//		//方法步骤很清晰，易理解，需要在页面引用Jquery.js    
//
//		//bankno为银行卡号 banknoInfo为显示提示信息的DIV或其他控件
//		luhmCheck : function(bankno) {
//			var lastNum = bankno.substr(bankno.length - 1, 1);//取出最后一位（与luhm进行比较）
//
//			var first15Num = bankno.substr(0, bankno.length - 1);//前15或18位
//			var newArr = new Array();
//			for (var i = first15Num.length - 1; i > -1; i--) { //前15或18位倒序存进数组
//				newArr.push(first15Num.substr(i, 1));
//			}
//			var arrJiShu = new Array(); //奇数位*2的积 <9
//			var arrJiShu2 = new Array(); //奇数位*2的积 >9
//
//			var arrOuShu = new Array(); //偶数位数组
//			for (var j = 0; j < newArr.length; j++) {
//				if ((j + 1) % 2 == 1) {//奇数位
//					if (parseInt(newArr[j]) * 2 < 9)
//						arrJiShu.push(parseInt(newArr[j]) * 2);
//					else
//						arrJiShu2.push(parseInt(newArr[j]) * 2);
//				} else
//					//偶数位
//					arrOuShu.push(newArr[j]);
//			}
//
//			var jishu_child1 = new Array();//奇数位*2 >9 的分割之后的数组个位数
//			var jishu_child2 = new Array();//奇数位*2 >9 的分割之后的数组十位数
//			for (var h = 0; h < arrJiShu2.length; h++) {
//				jishu_child1.push(parseInt(arrJiShu2[h]) % 10);
//				jishu_child2.push(parseInt(arrJiShu2[h]) / 10);
//			}
//
//			var sumJiShu = 0; //奇数位*2 < 9 的数组之和
//			var sumOuShu = 0; //偶数位数组之和
//			var sumJiShuChild1 = 0; //奇数位*2 >9 的分割之后的数组个位数之和
//			var sumJiShuChild2 = 0; //奇数位*2 >9 的分割之后的数组十位数之和
//			var sumTotal = 0;
//			for (var m = 0; m < arrJiShu.length; m++) {
//				sumJiShu = sumJiShu + parseInt(arrJiShu[m]);
//			}
//
//			for (var n = 0; n < arrOuShu.length; n++) {
//				sumOuShu = sumOuShu + parseInt(arrOuShu[n]);
//			}
//
//			for (var p = 0; p < jishu_child1.length; p++) {
//				sumJiShuChild1 = sumJiShuChild1 + parseInt(jishu_child1[p]);
//				sumJiShuChild2 = sumJiShuChild2 + parseInt(jishu_child2[p]);
//			}
//			//计算总和
//			sumTotal = parseInt(sumJiShu) + parseInt(sumOuShu)
//			+ parseInt(sumJiShuChild1) + parseInt(sumJiShuChild2);
//
//			//计算Luhm值
//			var k = parseInt(sumTotal) % 10 == 0 ? 10 : parseInt(sumTotal) % 10;
//			var luhm = 10 - k;
//
//			if (lastNum == luhm) {
//				$("#number").html(" *Luhm验证通过！");
//				return true;
//			} else {
//				$("#number").html(" *银行卡号必须符合Luhm校验！");
//				return false;
//			}
//		},
//		timer : null,
//		timer_second : 60,
//		timer_clear : function() {
//			clearInterval(userbank.timer);
//			$("#btnSendVoiceMsgBank").show();
//			$("#btnSendMsgBank").show();
//			$("#btnSendMsgBank").attr("onclick", userbank.send_onclick);
//			$("#btnSendMsgBank").css("cursor", "pointer").css("width", "106px")
//			.html('获取短信验证码').removeClass('reset_get_code');
//		},
//		send_smscheck : function() {
//			var cResult = check().form() && this.checkCardNo();
//			if (cResult) {
//				userbank.send_smscode();
//			}
//			return cResult;
//		},
//		send_onclick : null,
//		//发送验证码
//		send_smscode : function() {
//			userbank.send_onclick = $("#btnSendMsgBank").attr("onclick");
//			$("#btnSendMsgBank").attr("onclick", "");
//			$("#btnSendMsgBank").css("cursor", "default");
//			$("#btnSendMsgBank").css("text-decoration", "none");
//			$('#btnSendMsgBank').removeClass('reset_get_code').css("width", "163px").html("发送中...");
//			$("div.reg-message1 div").removeClass('font-red').html("");
//
//			$.ajax({
//				url : "user/bankcard/sendMsgCodeBank.action",
//				type : "post",
//				dataType : "json",
//				data : {
//					Cmd : "getUsersMSCodeEditBank"
//				},
//				success : function(json) {
//					var result = json.result;
//					if (result == "success") {
//						$("#txt_smsCodebank").focus();
//						$("div.reg-message1 div").removeClass('font-red').html(
//						"验证码已发送，请查收您的短信");
//					} else if (result == "fail") {
//						$("div.reg-message1 div").addClass('font-red').html(
//						"发送验证码失败，请稍后再试");
//					} else {
//						location.href = "user/tologin.action";
//					}
//
//					userbank.timer_second = 60;
//					userbank.timer = setInterval(function() {
//						if (userbank.timer_second < 1) {
//							userbank.timer_clear();
//							$("div.reg-message1 div").removeClass('font-red').html(
//							"获取不到短信验证码？请重新获取！");
//							return;
//						} else {
//							$('#btnSendMsgBank').addClass('reset_get_code').html(
//									"<b>" + userbank.timer_second + "</b>S重新获取");
//						}
//						userbank.timer_second--;
//					}, 1000);
//				}
//			});
//		},
//		//校验输入的验证码
//		checkTelCode : function(param) {
//			var sResult = true;
//			if (param == "") {
//				$("#txt_smsCodebank").addClass("red-border-input");
//				userbank.add_error('dvtxt_allMsg', ' *请输入验证码！');
//				sResult = false;
//			} else if(param != "" && !this.send_onclick){
//				$("#txt_smsCodebank").addClass("red-border-input");
//				userbank.add_error('dvtxt_allMsg', ' *请点击获取验证码按钮！');
//				return;
//			} else if (param != "" && param.length < 6) {
//				$("#txt_smsCodebank").addClass("red-border-input");
//				userbank.add_error('dvtxt_allMsg', ' *验证码错误！');
//				sResult = false;
//			} else {
//				$("#txt_smsCodebank").removeClass("red-border-input");
//				userbank.add_error('dvtxt_allMsg', '');
//			}
//			if (!sResult)
//				return;
//
//			$.ajax({
//				url : "user/bankcard/checkMsgCodeBank.action",
//				type : "post",
//				dataType : "json",
//				data : {
//					code : param
//				},
//				success : function(json) {
//					var result = json.result;
//					var sResult = true;
//					if (result == "success") {
//						$("#txt_smsCodebank").addClass("red-border-input");
//						userbank.add_error('dvtxt_allMsg', ' *验证成功！');
//					} else if (result == "fail") {
//						$("#txt_smsCodebank").focus();
//						$("#txt_smsCodebank").addClass("red-border-input");
//						userbank.add_error('dvtxt_allMsg', ' *手机短信码不一致！');
//					} else if (result == "code_null") {
//						$("#txt_smsCodebank").focus();
//						$("#txt_smsCodebank").addClass("red-border-input");
//						userbank.add_error('dvtxt_allMsg', ' *手机短信码为空！');
//					} else if (result == "code_error") {
//						$("#txt_smsCodebank").focus();
//						$("#txt_smsCodebank").addClass("red-border-input");
//						userbank.add_error('dvtxt_allMsg', ' *短信未发送！');
//					} else {
//						location.href = "user/tologin.action";
//					}
//				}
//			})
//		},
//		// 短信验证提示div
//		add_error : function(id, msg) {
//			$("#" + id)
//			.html("<div class='reg_wrong' "+ (msg.length > 11 ? "style='line-height:16px;color:red'": "") + ">" + msg + "</div>");
//		}
//}
