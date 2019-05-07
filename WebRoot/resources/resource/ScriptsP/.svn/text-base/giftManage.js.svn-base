var realnameFlag = false;
var mobilephoneFlag = false;
var addressFlag = false;
var emailFlag = false;
var zipCodeFlag = false;

$(document).ready(function(){
	var cur_dh = $('#xinxi_nwd_6');
    cur_dh.addClass('active');
    cur_dh.parent('ul').prev('h4').attr('class','blue-minus');

	$(".tab_u span").click(function(){
		var actionUrl = $(this).attr("action");
		if (actionUrl) {
			window.location.href = actionUrl;
		}
	});
	
	if ($(".add_0 .line1").size() == 1) {
		var $newAddress = $('.add_0 .add_l');
		$newAddress.addClass('bg1').children('input').attr('checked',true);
		$newAddress.siblings('.line1').removeClass('bg1 bg').children('input').attr('checked',false).siblings('a').hide();
		$newAddress.siblings('.line2').show();	
	}
	
	/**
	 * 绑定省份城市列表的change事件
	 */
	$("#province").change(function() {
		var provCode = $(this).val(); 
		$.ajax({
			type : "post", 
			url : "/member/getProvSelect1.do", 
			data : "provCode=" + provCode, 
			success : function(result) { 
				data = result; 
				//遍历json的数据
				var array = [];
				$(data).each(function(index,domEle){ 
					array.push("<option value='"+domEle.id+"'>"+domEle.name+"</option>");
				});
				$("#city option").remove(); 
				$("#city").append('<option value="">请选择</option>').append(array.join(""));
			} 
		}); 
	});
	
	/**
	 * 绑定城市列表的change事件
	 */
	$("#city").change(function() {
		var cityCode = $(this).val(); 
		if (cityCode != "") {
			$("#cityMSG").removeClass().html("").show();
		} else {
			$("#cityMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请填写完整的地区信息！").show();
		}
	});
	
	/**
	 * 绑定地址修改按钮事件
	 */
	$(".xugai1").click(function(){
		// 判断$('.add_out')是否是隐藏, 是显示则对其进行隐藏，是隐藏的则对其显示
		if ($('.add_out').hasClass("hidden")) {
			// 显示地址修改输入框， 并传入地址值
			var addressObject = {
				id : $(this).attr("aid"),
				cityCode: $(this).attr("cityCode"),
				provinceCode: $(this).attr("provinceCode"),
				realname: $(this).attr("realname"),
				address: $(this).attr("address"),
				mobilephone: $(this).attr("mobilephone"),
				zipCode: $(this).attr("zipCode")
			};
			
			$(".add_0 .line1").each(function() {
				var addressRadio = $(this).find("input");
				if (addressRadio.val() == addressObject.id) {
					addressRadio.attr("checked", true);
					$(this).click();
					/*$("#aid").val(addressObject.id);
					$("#realname").val(addressObject.realname);
					$("#address").val(addressObject.address);
					$("#mobilephone").val(addressObject.mobilephone);
					$("#zipCode").val(addressObject.zipCode);
					
					// 设置省份信息选中
					$("#province option").each(function() {
						if ($(this).attr("value") == addressObject.provinceCode) {
							$(this).attr("selected", "selected");
						}
					});
					
					// 如果有省份信息，就获取对应的城市信息，并设置该城市为选中
					if (addressObject.provinceCode != "") {
						$.ajax({
							type : "post", 
							url : "/member/getProvSelect1.do", 
							data : "provCode=" + addressObject.provinceCode, 
							success : function(result) { 
								data = result; 
								//遍历json的数据
								var array = [];
								$(data).each(function(index, domEle){ 
									if (addressObject.cityCode == domEle.id) {
										array.push("<option selected='selected' value='" + domEle.id + "'>" + domEle.name + "</option>");
									} else {
										array.push("<option value='" + domEle.id + "'>" + domEle.name + "</option>");
									}
								});
								$("#city option").remove(); 
								$("#city").append('<option value="">请选择</option>').append(array.join(""));
							} 
						}); 
					} else {
						// 没有省份信息，城市列表只有请选择一项
						$("#city option").remove(); 
						$("#city").append('<option value="">请选择</option>');
					}*/
					
					//$(this).siblings('.line2').slideDown();	
					return false;
				}
			});
			
			// 清除隐藏样式
			$('.add_out').removeClass("hidden");
		} else {
			$('.add_out').slideUp().addClass("hidden");
			// 隐藏地址输入框
			$('.line2').slideUp().addClass("hidden");
		}
	});
	
	/**
	 * 绑定每条地址后面的修改的事件
	 */
	$(".line1 > a").live("click", function(event) {
		// 获取选中的地址记对象
		var $addressLine = $(this).parent();
		var $input = $addressLine.find("input");
		if ($(this).html() == "修改") {
			//if ($input.attr("checked") != "checked") {
				// 获取地址
				var addressObject = {
					id : $input.attr("value"),
					cityCode: $input.attr("cityCode"),
					provinceCode: $input.attr("provinceCode"),
					cityName: $input.attr("cityName"),
					provinceName: $input.attr("provinceName"),
					realname: $input.attr("realname"),
					address: $input.attr("address"),
					mobilephone: $input.attr("mobilephone"),
					zipCode: $input.attr("zipCode")
				};
				
				// 填充地址信息
				$("#aid").val(addressObject.id);
				$("#realname").val(addressObject.realname);
				$("#address").val(addressObject.address);
				$("#mobilephone").val(addressObject.mobilephone);
				$("#zipCode").val(addressObject.zipCode);
				
				// 设置省份和城市信息
				$("#province option").each(function() {
					if ($(this).attr("value") == addressObject.provinceCode) {
						$(this).attr("selected", "selected");
					}
				});
				
				// 如果有省份信息，就获取对应的城市信息，并设置该城市为选中
				if (addressObject.provinceCode != "") {
					$.ajax({
						type : "post", 
						url : "/member/getProvSelect1.do", 
						data : "provCode=" + addressObject.provinceCode, 
						success : function(result) { 
							data = result; 
							//遍历json的数据
							var array = [];
							$(data).each(function(index, domEle){ 
								if (addressObject.cityCode == domEle.id) {
									array.push("<option selected='selected' value='" + domEle.id + "'>" + domEle.name + "</option>");
								} else {
									array.push("<option value='" + domEle.id + "'>" + domEle.name + "</option>");
								}
							});
							$("#city option").remove(); 
							$("#city").append('<option value="">请选择</option>').append(array.join(""));
						} 
					}); 
				} else {
					// 没有省份信息，城市列表只有请选择一项
					$("#city option").remove(); 
					$("#city").append('<option value="">请选择</option>');
				}
				
				$input.attr("checked", true);
				$addressLine.siblings('.line2').slideDown();
			//}
		} else if ($(this).html() == "删除") {
			/**
			 * 提交时没有token。通过访问URL直接提交借款Bug修复
			 */
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
			// 删除操作
			var addressObject = {
				id : $input.val(),
				stok : stok
			}; 
			// 服务器请求
			$.ajax({
				type : "post",
				data: addressObject,
				url: "/member/deleteAddress.do",
		   	 	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    	dataType: "json",
				success: function(result) {
					if (result.errorCode == 0) {
						// 成功回调
						// 获取当前地址的索引位
						var index = $(".add_0 .line1").index($addressLine);
						$addressLine.remove();
						// 判断当前地址记录数
						var addressCount = $(".add_0 .line1").size();
						
						if (index == 0) {
							// 删除第一条地址记录, 并且还有记录
							if (addressCount > 1) {
								// 获取第一条地址记录
								var $firstAddressLine = $(".add_0 .line1:first").find("input");
								// 将地址栏数据换成第一条地址记录
								$("#addressTable tr:last").find("td").each(function(i, obj) {
									if (i == 0) {
										// 设置收货人
										$(this).html($firstAddressLine.attr("realname"));
									} if (i == 1) {
										// 设置省份
										$(this).html($firstAddressLine.attr("provinceName"));
									} if (i == 2) {
										// 设置城市
										$(this).html($firstAddressLine.attr("cityName"));
									} if (i == 3) {
										// 设置地址
										$(this).html($firstAddressLine.attr("address"));
									} if (i == 4) {
										// 设置手机号
										$(this).html($firstAddressLine.attr("mobilephone"));
									} if (i == 5) {
										var a = $(this).find("a");
										a.attr("cityCode", $firstAddressLine.attr("cityCode")).attr("provinceCode", $firstAddressLine.attr("provinceCode")).attr("aid", $firstAddressLine.attr("value"))
										 .attr("address", $firstAddressLine.attr("address")).attr("mobilephone", $firstAddressLine.attr("mobilephone")).attr("realname", $firstAddressLine.attr("realname"))
										 .attr("zipCode", $firstAddressLine.attr("zipCode"));
									}
								});
								//$(".add_0 .line1 input:first").click();
							} else {
								//清空地址表格中的数据，并隐藏地址表格
								$("#addressTable").parent().addClass("hidden");
								$("#addressTable tr:last").find("td").each(function(i, obj) {
									if (i == 5) {
										var a = $(this).find("a");
										a.attr("cityCode", "").attr("provinceCode", "").attr("aid", "")
										 .attr("address", "").attr("mobilephone", "").attr("realname", "")
										 .attr("zipCode", "");
									} else {
										$(this).html("");
									}
								});
								$('.add_0 .add_l').click();
							}
							
							// 判断删除的记录是否是在被修改的记录
							if ($("#aid").val() == addressObject.id) {
								// 关闭修改按钮框
								$('.line2').slideUp();
								// 并选中第一个记录
								$(".add_0 .line1 input:first").click();

								// 清空填充数据
								$("#aid").val("");
								$("#realname").val("");
								$("#address").val("");
								$("#mobilephone").val("");
								$("#zipCode").val("");
								
								// 清空城市列表并设置省份列表为请选择
								$("#city option").remove(); 
								$("#city").append('<option value="">请选择</option>');
								$("#province option:first").attr("selected", "selected"); 
								
								// 清空提示信息内容
				    			$(".prompt_1").css("display", "none").html("");
							}
							
						} else {
							// 判断删除的记录是否是在被修改的记录
							if ($("#aid").val() == addressObject.id) {
								// 关闭修改按钮框
								$('.line2').slideUp();
								// 并选中第一个记录
								$(".add_0 .line1 input:first").click();
								
								// 清空填充数据
								$("#aid").val("");
								$("#realname").val("");
								$("#address").val("");
								$("#mobilephone").val("");
								$("#zipCode").val("");
								
								// 清空城市列表并设置省份列表为请选择
								$("#city option").remove(); 
								$("#city").append('<option value="">请选择</option>');
								$("#province option:first").attr("selected", "selected"); 
								// 清空提示信息内容
				    			$(".prompt_1").css("display", "none").html("");
							} 
						}
						
					} else {
						$('.content').html("删除失败，请重试！");
						// 显示提示框
						showCon_1();
					}
				}
			});
			
			// 阻止事件冒泡
			event.stopPropagation();
		}
	});
	
	/** 绑定在.line1上点击事件 **/
	$(".line1").live("click", function(event) {
		var target = event.target;
		var tagName = target.tagName.toUpperCase();
		if (tagName == "INPUT" || tagName == "LI" || tagName == "SPAN") {
			
			$(this).siblings('.line1').removeClass('bg1 bg').children('input').attr('checked',false).siblings('a').hide();
			
			var $input = $(this).find("input");
			// 判断是否是选中使用新地址
			if ($input.attr("value") != "") {
				// 隐藏地址输入框
				$(this).siblings('.line2').slideUp();
				
				var addressObject = {
					id : $input.attr("value"),
					cityCode: $input.attr("cityCode"),
					provinceCode: $input.attr("provinceCode"),
					cityName: $input.attr("cityName"),
					provinceName: $input.attr("provinceName"),
					realname: $input.attr("realname"),
					address: $input.attr("address"),
					mobilephone: $input.attr("mobilephone"),
					zipCode: $input.attr("zipCode")
				};
				
				// 填充地址信息
				$("#aid").val(addressObject.id);
				$("#realname").val(addressObject.realname);
				$("#address").val(addressObject.address);
				$("#mobilephone").val(addressObject.mobilephone);
				$("#zipCode").val(addressObject.zipCode);
				
				// 设置省份和城市信息
				$("#province option").each(function() {
					if ($(this).attr("value") == addressObject.provinceCode) {
						$(this).attr("selected", "selected");
					}
				});
				
				// 如果有省份信息，就获取对应的城市信息，并设置该城市为选中
				if (addressObject.provinceCode != "") {
					$.ajax({
						type : "post", 
						url : "/member/getProvSelect1.do", 
						data : "provCode=" + addressObject.provinceCode, 
						success : function(result) { 
							data = result; 
							//遍历json的数据
							var array = [];
							$(data).each(function(index, domEle){ 
								if (addressObject.cityCode == domEle.id) {
									array.push("<option selected='selected' value='" + domEle.id + "'>" + domEle.name + "</option>");
								} else {
									array.push("<option value='" + domEle.id + "'>" + domEle.name + "</option>");
								}
							});
							$("#city option").remove(); 
							$("#city").append('<option value="">请选择</option>').append(array.join(""));
						} 
					}); 
				} else {
					// 没有省份信息，城市列表只有请选择一项
					$("#city option").remove(); 
					$("#city").append('<option value="">请选择</option>');
				}
				
				$input.attr("checked", true);
			} 
		}
	});
	
	$(".plusBank1 button.btn").click(function() {
		// 关闭计算结果框
		closeAll_1();
	});
	
	/**
	 * 绑定保存地址按钮事件
	 */
	$('.add_btn').unbind("click");
	$('.add_btn button').click(function() {
		
		$("#realname").blur();
		$("#mobilephone").blur();
		$("#address").blur();
		$("#zipCode").blur();
		
		// 验证省份和城市信息
		if ($("#province").val() == "" || $("#city").val() == "") {
			$("#cityMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请填写完整的地区信息！").show();
			return; 
		} else {
			$("#cityMSG").removeClass().html("").hide();
		}
		
		if(realnameFlag == true && mobilephoneFlag == true && addressFlag == true && zipCodeFlag == true){
			/**
			 * 提交时没有token。通过访问URL直接提交借款Bug修复
			 */
	        var stok = "";
	        if(document.getElementById ("stok")){
	            stok  = document.getElementById ("stok").value;
	        }
			// 验证信息
			var addressObject = {
				id : $("#aid").val(),
				cityCode: $("#city option:selected").val(),
				provinceCode: $("#province option:selected").val(),
				cityName: $("#city option:selected").text(),
				provinceName: $("#province option:selected").text(),
				realname: $("#realname").val(),
				address: $("#address").val(),
				mobilephone: $("#mobilephone").val(),
				zipCode: $("#zipCode").val(),
				stok:stok
			};
			// 提交数据
			$.ajax({
				type : "post",
				data: addressObject,
				url: "/member/saveGiftManage.do",
		   	 	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    	dataType: "json",
				success: function(result){
		    		if(result.msg == 1){
		    			// 保存成功
		    			// 弹框优化 | 去除提示 | zhenhua.xi | 20140916
		    			// $('.content').html("保存成功！");
		    			// 显示提示框
		    			// showCon_1();
		    			
		    			$(".plusBank1 .btn_blue").unbind("click").click(function() {
		    				// 关闭提示框
		    				closeAll_1();
		    				// 修改地址信息收起
		    				$('.add_out').slideUp().addClass("hidden");
		    				
		    				// 隐藏地址输入框
		    				$('.line2').slideUp().addClass("hidden");
		    			});
		    			
		    			$(".plusBank1 .plus_c").unbind("click").click(function() {
		    				// 关闭提示框
		    				closeAll_1();
		    				// 修改地址信息收起
		    				$('.add_out').slideUp().addClass("hidden");
		    				
		    				// 隐藏地址输入框
		    				$('.line2').slideUp().addClass("hidden");
		    			});
		    			
		    			if (addressObject.id == "" ||  typeof addressObject.id == "undefined") {
		    				addressObject.id = result.id;
						}
		    			
		    			// 将地址栏数据换成修改后的数据
		    			$("#addressTable tr:last").find("td").each(function(i, obj) {
		    				if (i == 0) {
		    					// 设置收货人
		    					$(this).text(addressObject.realname);
		    				} if (i == 1) {
		    					// 设置省份
		    					$(this).html(addressObject.provinceName);
		    				} if (i == 2) {
		    					// 设置城市
		    					$(this).html(addressObject.cityName);
		    				} if (i == 3) {
		    					// 设置地址
		    					$(this).text(addressObject.address);
		    				} if (i == 4) {
		    					// 设置手机号
		    					$(this).html(addressObject.mobilephone);
		    				} if (i == 5) {
		    					var a = $(this).find("a");
		    					a.attr("cityCode", addressObject.cityCode).attr("provinceCode", addressObject.provinceCode)
		    					 .attr("address", addressObject.address).attr("mobilephone", addressObject.mobilephone).attr("realname", addressObject.realname)
		    					 .attr("zipCode", addressObject.zipCode).attr("aid", addressObject.id);
		    				}
		    			});
		    			
		    			// 判断当前是修改地址还是新增地址
		    			if ($("#aid").val() != "") {
		    				// 获取当前地址对象
		    				var addressLine = $(".line1 input:checked").parent();
		    				
		    				// 将地址栏的地址进行同步修改
		    				addressLine.find("input")
		    				           .attr("cityCode", addressObject.cityCode)
		    				           .attr("provinceCode", addressObject.provinceCode)
		    				           .attr("address", addressObject.address)
		    				           .attr("mobilephone", addressObject.mobilephone)
		    				           .attr("address", addressObject.address)
		    				           .attr("realname", addressObject.realname)
		    				           .attr("cityName", addressObject.cityName)
		    				           .attr("provinceName", addressObject.provinceName)
		    				           .attr("zipCode", addressObject.zipCode);
		    				
		    				addressLine.find("span").eq(0).html(addressObject.realname);
		    				addressLine.find("span").eq(1).html(addressObject.provinceName + addressObject.cityName + addressObject.address);
		    				addressLine.find("span").eq(2).html(addressObject.mobilephone);
		    				
		    				var index = $(".add_0 .line1").index(addressLine);
		    				if (index != 0) {
		    					// 将当前地址信息移到第一条记录
		    					$(".add_0").prepend(addressLine);
		    				}
		    			} else {
		    				// 新加地址信息
		    				// 构造地址记录html
		    				var addressLine = '<li class="line1">' + 
		    						          '<input name="address"'+ 
		    						          '    	  cityCode="' + addressObject.cityCode + '"' + 
		    						          '	      provinceCode="' + addressObject.provinceCode + '"' + 
		    						          '       address="' + addressObject.address + '"' + 
		    						          '       mobilephone="' + addressObject.mobilephone + '"' +
		    						          '       realname="' + addressObject.realname + '"' + 
		    						          '       cityName="' + addressObject.cityName + '"' +
		    						          '       provinceName="' + addressObject.provinceName + '"' +
		    						          '       zipCode="' + addressObject.zipCode + '"' +
		    						          '       value="' + addressObject.id + '"' +
		    						          ' 	  type="radio">'+
		    						          '<span class="f mar_l30 mar_r30">' + addressObject.realname + '</span><span>' + addressObject.provinceName + addressObject.cityName + addressObject.address + '</span><span class="mar_l30">' + addressObject.mobilephone + '</span>' +
		    						          '<a href="javascript:void(0);" class="blue hidden mar_l20">修改</a><a href="javascript:void(0);" class="blue hidden mar_l20">删除</a>' +
		    						          '</li>';
		    				// 将当前地址信息移到第一条记录
		    				$(".add_0").prepend(addressLine);
		    			}
		    			
		    			// 显示地址table内容
		    			$("#addressTable").parent().removeClass("hidden");
		    			
		    			// 清空地址输入框中记录
		    			$("#aid").val("");
		    			$("#realname").val("");
		    			$("#address").val("");
		    			$("#mobilephone").val("");
		    			$("#zipCode").val("");
		    			
		    			// 清空城市列表并设置省份列表为请选择
		    			$("#city option").remove(); 
		    			$("#city").append('<option value="">请选择</option>');
		    			$("#province option:first").attr("selected", "selected"); 
		    			
		    			// 清空提示信息内容
		    			$(".prompt_1").css("display", "none").html("");
		    			
		    		}else if(result.msg == 2){
						$('.content').html("最多只能添加5个收货地址！");
		    			// 显示提示框
		    			/*$('.plusBankBg').show();
		    			$('.plusBank1').slideDown();*/
						// 显示提示框
						showCon_1();
						/*setTimeout(function () {
		    				// 关闭提示框
		    				$('.plusBank1 .plus_c').click();
		    		    }, 1000);*/
		    		}else if(result.msg == 3){
		    			$('.content').html("请输入正确的手机号码");
		    			showCon_1();
		    		}else if(result.msg == 4){
		    			$('.content').html("请选择正确的省市");
		    			showCon_1();
		    		}else if(result.msg == 5){
		    			$('.content').html("请输入正确的邮编");
		    			showCon_1();
		    		}else{
		    			$('.content').html("操作失败，请再次操作");
		    			showCon_1();
		    		}
		    	}
			});
		}
	});	
	
	/**
	 * 绑定使用新地址事件
	 */
	$('.add_0 .add_l').click(function() {
		// 清空地址输入框中记录
		$("#aid").val("");
		$("#realname").val("");
		$("#address").val("");
		$("#mobilephone").val("");
		$("#zipCode").val("");
		
		// 清空城市列表并设置省份列表为请选择
		$("#city option").remove(); 
		$("#city").append('<option value="">请选择</option>');
		$("#province option:first").attr("selected", "selected"); 
	});
	
	$("#realname").blur(function(){
		var realname = $("#realname").val().replace(/(^\s*)|(\s*$)/g,'');
		$("#realname").val(realname);
		if(realname==""){
			realnameFlag = false;
			$('#realnameMSG').css("display","");
			$("#realnameMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入收件人姓名！");
		}else if(realname.length > 20){
			realnameFlag = false;
			$('#realnameMSG').css("display","");
			$("#realnameMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>收件人姓名不超过20个字符！");
		}else{
			realnameFlag = true;
			//$('#realnameMSG').css("display","");
			$("#realnameMSG").removeClass().html("").hide();
		}
 	});
	
	$("#mobilephone").blur(function(){
		var phone = $("#mobilephone").val().replace(/(^\s*)|(\s*$)/g,'');
		$("#mobilephone").val(phone);
		var reg = /^\d{11}$/;
		var reg1 = /^\d{3,4}-\d{7,8}$/;
		var reg2 = /^\d{7,8}$/;
		if(phone==""){
			mobilephoneFlag = false;
			$('#mobilephoneMSG').css("display","");
			$("#mobilephoneMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入收货人电话号码！");
		}else if(reg.test(phone) || reg1.test(phone) || reg2.test(phone)){
			mobilephoneFlag = true;
			//$('#mobilephoneMSG').css("display","");
			$("#mobilephoneMSG").removeClass().html("").hide();
		}else{
			mobilephoneFlag = false;
			$('#mobilephoneMSG').css("display","");
			$("#mobilephoneMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请填写正确的手机号码！");
		}
 	});
	
	$("#address").blur(function(){
		var address = $("#address").val().replace(/(^\s*)|(\s*$)/g,'');
		$("#address").val(address);
		if(address==""){
			addressFlag = false;
			$('#addressMSG').css("display","");
			$("#addressMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>请输入收货人地址！");
		}else if(address.length > 100){
			addressFlag = false;
			$('#addressMSG').css("display","");
			$("#addressMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>收货人地址不超过100个字符！");
		} else if (/^(\d)+$/.test(address)) {
			$('#addressMSG').css("display","");
			$("#addressMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>详细地址不能全为数字！");
		} else{
			addressFlag = true;
			//$('#addressMSG').css("display","");
			$("#addressMSG").removeClass().html("").hide();
		}
 	});
	
	$("#zipCode").blur(function(){
		var zipCode = $("#zipCode").val().replace(/(^\s*)|(\s*$)/g,'');
		$("#zipCode").val(zipCode);
		var reg = /^\d{6}$/;
		if(zipCode==""){
			zipCodeFlag = true;
			$("#zipCodeMSG").removeClass().html("").hide();
		}else if(!reg.test(zipCode)){
			zipCodeFlag = false;
			$('#zipCodeMSG').css("display","");
			$("#zipCodeMSG").removeClass().addClass("prompt_1 error_1").html("<i></i>收货人邮编格式有误！");
		}else{
			zipCodeFlag = true;
			//$('#zipCodeMSG').css("display","");
			$("#zipCodeMSG").removeClass().html("").hide();
		}
	});
});