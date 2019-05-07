
function conblck(){
	window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/query.action";
}
//验证定向名单是否正确
function snlidfun(obj){
	var id = $(obj).attr("id");
	var str = id.split("_");
	var str1 = str[1];
	var code = $(obj).val();//获取当前的值
	
if(code!=""){
	if(parseInt(str1)==0){
		  fengzhuang(obj);
		
	}else if(parseInt(str1)==1){
		var code_0 = $("#code_0").val();
		if(code==code_0){
			$("#code_1").val("");
			alert("不能填入相同的值,请重新输入");
		}else{
			fengzhuang(obj);
		}
		
	}else{//2
		var count = 0;
		for(var i=0;i<parseInt(str1);i++){
			//获取每个的值
			var $value = $("#code_"+i).val();
			if($value==code){
				$(obj).val("");
				alert("不能填入相同的值");
				break;
			}else{
				count++;
			}
		}
		if(count==parseInt(str1)){//说明几次都不一样
			fengzhuang(obj);
		}
	
	}
}
} 
//封装请求到后台校验定向名单的方法
function fengzhuang(obj){
	var code = $(obj).val();
	var action = "${pageContext.request.contextPath}/admin/rechargeRate/getSpecialNameList.action";
	var params = {
			"code":code
	}
	$.post(action,params,function(data){
		 if(data=="inputerror"){
			 $(obj).val("");
			 alert("输入格式错误,请重新输入");
			 $('#failButton').attr("disabled",true);
		 }else if(data=="no"){ 
			 $(obj).val("");
		 	alert("您输入的业务类型不匹配,请重新输入");
		 	 $('#failButton').attr("disabled",true);
		 }else{
			 $('#failButton').attr("disabled",false);
			 // $('#failButton').removeAttr("disabled");
		 }
	},'json');
}
$(function(){
	$('#updateForm').bootstrapValidator(
			{
				message : 'This value is not valid',
				feedbackIcons : { /*input状态样式图片*/
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : { 
					'rechargeQuotaFee[0].minmoney': {
						validators : {
							notEmpty : {
								message : '最低金额不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							}
						}
					},
					'rechargeQuotaFee[0].maxmoney': {
						validators : {
							notEmpty : {
								message : '最高金额不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							}/* ,
							callback : {
								message : '定向金额高值不能低于低值',
								callback : function(value,validator) {
									var minmoney = $("#inputminMoney_0").val(), sum = parseFloat(minmoney);
									return value < sum;
								}
							} */
						}
					}, 
					minmoney: {
						validators : {
							notEmpty : {
								message : '充值金额最低值不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							}
						}
					},
				
					maxmoney: {
						validators : {
							notEmpty : {
								message : '充值金额最高值不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							},
							callback : {
								message : '充值金额高值不能低于低值',
								callback : function(value,validator) {
									var dsminmoney = $("#dsminmoney").val(), sum = parseFloat(dsminmoney);
									return value >= sum;
								}
							}
						}
					},
					minfee: {
						validators : {
							notEmpty : {
								message : '最低收费不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							}
						}
					},
					
					maxfee: {
						validators : {
							notEmpty : {
								message : '最高收费不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							},
							callback : {
								message : '手续费高值不能低于低值',
								callback : function(value,validator) {
									var minfee = $("#dsminfee").val(), sum = parseFloat(minfee);
									return value >= sum;
								}
							}
						}
					},
					charrate: {
						validators : {
							notEmpty : {
								message : '充值费率不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							}
						}
					},
					'rechargeQuotaFee[0].quotafee': {
						validators : {
							notEmpty : {
								message : '定额不能为空'
							},
							regexp : {
								regexp : /^(\d*\.)?\d+$/,
								message : '金额为浮点数如10.0或10'
							}
						}
					},
					'rechargeSNLLink[0].code': {
						validators : {
							notEmpty : {
								message : '定性名单编号不能为空'
							}
						}
					},
			
					
				}
			});
		});
//当这条记录是定额的时候,修改页面的按钮操作		
$(document).ready(
		function() {
			var MaxInputs = 15; //最大能添加多少个
			var InputsWrapper = $("#InputsWrapperone"); //定额金额父节点
			var AddButton = $("#AddMoreFileBoxone"); //定额按钮
			
			var snidbtn = $("#snidbtn"); //定向名单按钮
			var snidAll = $("#snidAll"); //定向名单父节点
			var y = snidAll.length; //获取定向名单长度
			
			var x = InputsWrapper.length;
			// 点击前获取InputsWrapper中wapperclass的个数
			var feecount = $("#feecount").val();
			for(var i=1;i<=parseInt(feecount);i++){
				$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].maxmoney',{
					validators : {
						notEmpty : {
							message : '最高金额不能为空'
						},
						regexp : { //匹配规则
							regexp : /^(\d*\.)?\d+$/,
							message : '金额必须为浮点数如:100或100.0'
						}
					}
				});
			/* 	$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].minmoney',{
					validators : {
						notEmpty : {
							message : '最低金额不能为空333'
						},
						regexp : { //匹配规则
							regexp : /^(\d*\.)?\d+$/,
							message : '金额必须为浮点数如:100或100.0'
						}
					}
				}); */
				$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].quotafee',{
					validators : {
						notEmpty : {
							message : '定额手续费不能为空'
						},
						regexp : { //匹配规则
							regexp : /^(\d*\.)?\d+$/,
							message : '金额必须为浮点数如:100或100.0'
						}
					}
				});
			}
		
			//原本就是定额的时候的点击添加按钮
			$(AddButton).click(
							function(e){
								if (feecount <= MaxInputs){
									var oDiv = $('<div class="wapperclass"></div>');
									oDiv.html('<div class="form-group">'
													+ '<label for="inputminMoney" class="col-sm-3 control-label">定额金额范围</label>'
													+ '<div class="col-sm-3">'
													+ '<div class="input-group">'
													+ '<input type="text" readonly="readonly" name="rechargeQuotaFee[' + feecount + '].minmoney" id="inputminMoney_'+feecount+'" placeholder="定额最低" class="form-control minMoney" />'
													+ '<span class="input-group-addon">元</span>'
													+ '</div>'
													+ '</div>'
													+ '<div class="col-sm-3">'
													+ '<div class="input-group">'
													+ '<input type="text" readonly="readonly" value="999999999999"name="rechargeQuotaFee[' + feecount + '].maxmoney" id="inputmaxMoney_'+feecount+'" placeholder="定额最高" class="form-control maxMoney"/>'
													+ '<span class="input-group-addon">元</span>'
													+ '</div>'
													+ '</div>'
													
													+ '<div class="col-sm-1">'
													+ '<span><button name="removebutton" id="removebuttonone'+feecount+'" class="btn btn-default removeclassone" type="button"><strong>-</strong></button></span>'
													+ '</div>'
													+ '</div>'
													
													+'<div class="form-group">'
													+'<label class="col-sm-3 control-label" for="inputquota">定额</label>'
													+'<div class="col-sm-3">'
													+'<div class="input-group">'
													+'<input type="text" name="rechargeQuotaFee[' + feecount + '].quotafee" id="inputquota" class="form-control" placeholder="定额" />'
													+'<span class="input-group-addon">元</span>'
													+'</div>'
													+'</div>'
													+'</div>'
													
													+ '</div>');
									$(InputsWrapper).append(oDiv);
									var feecount1  = feecount++; 
									$("#feecount").val(feecount1);
									var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
									var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);//获取添加过后第二个输入框
									lastBeforeInput.attr("readonly",false);
									// lastBeforeInput.val("");
									//获取最后一个之前的最大值
									var lastMax = $("#InputsWrapperone").children(".wapperclass:last-child").prev(".wapperclass").find(".maxMoney");
									lastMax.val("");
								
									for(var i=1;i<=parseInt(feecount);i++){
										$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].maxmoney',{
											validators : {
												notEmpty : {
													message : '最高金额不能为空'
												},
												regexp : { //匹配规则
													regexp : /^(\d*\.)?\d+$/,
													message : '金额必须为浮点数如:100或100.0'
												}
											}
										});
									/* 	$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].minmoney',{
											validators : {
												notEmpty : {
													message : '最低金额不能为空'
												},
												regexp : { //匹配规则
													regexp : /^(\d*\.)?\d+$/,
													message : '金额必须为浮点数如:100或100.0'
												}
											}
										}); */
										$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].quotafee',{
											validators : {
												notEmpty : {
													message : '定额手续费不能为空'
												},
												regexp : { //匹配规则
													regexp : /^(\d*\.)?\d+$/,
													message : '金额必须为浮点数如:100或100.0'
												}
											}
										});
										// $('#defaultForm').data('bootstrapValidator').validate();
										// if(!$('#insertRegActAwardForm').data('bootstrapValidator').isValid()) 
											//return ; 
									}
								
								}
								//判断如果等于设置的最大值15个的时候就不让其继续添加下去了
								if (feecount == MaxInputs) {
									$(this).attr("disabled","disabled");
									return true;
								}
								return false;
							});
			//定额删除	
			$("body").on("click",".removeclassone",
					function(e) {
						var feeid = $("#feeid_"+feecount).val();
						var $row = $(this).parents('.wapperclass'), $option = $row.find('[name="option[]"]');
						var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
						var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
						ntInput.val(bfInput.val());//删除之后替换后面一个输入框的值
						$row.remove();
						var action = "${pageContext.request.contextPath}/admin/rechargeRate/deletefee.action";
						var params = {
								"feeid":feeid
						}
						$.post(action,params,function(data){
							if(data=="success"){
								var id = $("#rechargeId").val();
								window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/queryEdits.action?id="+id;
							}
						},'json');
						var feecount2 = fecount--;
						$("#feecount").val(feecount2);//覆盖隐藏域的值
						var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
						lastInput.val("999999999999");
						lastInput.attr("readonly","readonly");
						$('form').bootstrapValidator('removeField',$option);
					});
			$("body").on("keyup", ".maxMoney", function(e) {
				var nextInput=$(this).parents('.wapperclass').next().find("input").eq(0);
				nextInput.val($(this).val());
			});
 
			$("body").on("blur", ".maxMoney", function(e) {
				
				var min = $(this).parents(".col-sm-3").prev(".col-sm-3").find('input').val();//获取最小值
				var max = $(this).val();//最大值输入框的值
				var xmin =$(this).parents(".wapperclass").next(".wapperclass").find(".form-group").find(".minMoney")
				var smin =xmin.val();//获取下一个最小值
				
				if(min!=""&& max!=""){
					if(parseInt(min)>=parseInt(max)){
						alert("输入的值不能小于前面的最小值");
						$(this).val("");
						xmin.val("");
					}
				}
				if(max!="" && smin!=""){
					if(parseInt(max)>parseInt(smin)){
						alert("输入的值不能大于下一个最小值");
						$(this).val("");
						xmin.val("");
					}
				}
			});
/**定向名单 */			
var count = $("#snlidcount").val();//获取隐藏域的值
for(var i=1;i<=parseInt(count);i++){
	$('form').data('bootstrapValidator').addField('rechargeSNLLink['+ i + '].code',{
		validators : {
			notEmpty : {
				message : '定向名单不能为空'
			},
		}
	});
}
$(snidbtn).click(
				function(e){
					if (count <= MaxInputs){
						var snidDiv = $('<div class="snidclass"></div>');
						snidDiv.html('<div class="form-group">'
								+'<label class="col-sm-3 control-label">定向排出名单</label>'
								+'<div class="col-sm-3">'
								+'<div class="input-group" style="width: 263px">'
								+'<input type="text" name="rechargeSNLLink['+count+'].code" id="code_'+count+'" class="form-control" placeholder="定向排出名单" onblur="snlidfun(this)"/>'
								+ '</div>'
								+ '</div>'
								+ '<div class="col-sm-1">'
								+ '<span><button name="removesnid" id="removesnid'+count+'" class="btn btn-default removesnid" type="button"><strong>-</strong></button></span>'
								+ '</div>'
								+ '</div>');
						$(snidAll).append(snidDiv);
						var count1 = count++;  
						$("#snlidcount").val(count1);//覆盖隐藏域的值
						
						for(var i=1;i<=parseInt(count);i++){
							$('form').data('bootstrapValidator').addField('rechargeSNLLink['+ i + '].code',{
								validators : {
									notEmpty : {
										message : '定向名单不能为空'
									},
								
								}
							});
						}
				
					}
					
					
					if (count == MaxInputs) {
						$(this).attr("disabled","disabled");
						return true;
					}
					return false;
				});
//定向名单删除:点击减号的时候到后台删除数据 
	$("body").on("click",".removesnid",
			function(e) {
			var relinkid = $("#snlikid_"+count).val();
			var $row = $(this).parents('.snidclass'), $option = $row.find('[name="option[]"]');
			$row.remove();
			var action = "${pageContext.request.contextPath}/admin/rechargeRate/delete.action";
			var params = {
					"relinkid":relinkid
			}
			var count2 = count--;
			$("#snlidcount").val(count2);//覆盖隐藏域的值
			$('form').bootstrapValidator('removeField',$option);	 
			$.post(action,params);	
			
		});
		

	//验证百分比的时候的充值最高和最低金额
	$("body").on("blur", "#dsmaxmoney", function(e) {
		var dsminmoney = $("#dsminmoney").val();
		var dsmaxmoney = $("#dsmaxmoney").val();
		if(dsminmoney!="" && dsmaxmoney!=""){
			if(parseInt(dsmaxmoney)<=parseInt(dsminmoney)){
				alert("输入的值不能小于前面的最小值");
				$("#dsmaxmoney").val("");
			}
		}
	});
		
});
//当是定额的时候修改结束标志



//选择定额或者百分比的时候
function xuanz() {
	var chargetypeValue = $("#chargetype_select").val();
	if (chargetypeValue == 1) { //假如充值方式为定额的时候
		$("#InputsWrapperone").show();
		$("#ds2").hide();//手续费最低隐藏
		$("#ds1").hide();//手续费最高隐藏
		$("#ds4").hide();//复选框隐藏
		$("#ds3").hide(); //费率隐藏
	}
	if (chargetypeValue == 2) {//充值方式为百分比的时候
		$("#InputsWrapperone").hide();
		$("#ds4").show();
		$("#ds3").show();
		 if ($('#pitchon').is(':checked')) {//选中
			 	$("#ds2").show();
			 	$("#ds1").show();
			}else{//取消选中
				$("#ds2").hide();
				$("#ds1").hide();
			}
	}
}

function yesorno(){
	 if ($('#pitchon').is(':checked')) {//选中
		 	$("#ds2").show();
		 	$("#ds1").show();
		}else{//取消选中
			$("#ds2").hide();
			$("#ds1").hide();
		}
}
/*$("#pitchon").click(function(){
	 if($("#pitchon").attr("checked") == "checked"){
		 alert("选中");
	 }else{
		 alert("未选中");
	 }
});*/
//点击充值方式的时候限制手续费收费类型
function rechartypexuanz(){
	var rechartype = $("#rechartype").val();//充值方式
	var chargetypeValue = $("#chargetype_select").val();//收费类型:1为定额 2为百分比
	if(rechartype==2){//企业网银的时候只能定额收费
		$("#chargetype_select option[value='1']").remove(); //删除Select中索引值为0的Option(第一个) 
		$("#chargetype_select option[value='2']").remove(); //删除Select中索引值为0的Option(第一个) 
		$("#chargetype_select").append("<option value='1'>定额</option>");
		$("#InputsWrapperone").show();
		$("#ds1").hide();
		$("#ds2").hide();
		$("#ds3").hide();
	}else{
		$("#chargetype_select option[value='1']").remove(); //删除Select中索引值为0的Option(第一个) 
		$("#chargetype_select option[value='2']").remove(); //删除Select中索引值为0的Option(第一个) 
		if(chargetypeValue==1){
			$("#InputsWrapperone").show();
			$("#ds1").hide();
			$("#ds2").hide();
			$("#ds3").hide();
			$("#chargetype_select").append("<option value='1' selected='selected'>定额</option>");
			$("#chargetype_select").append("<option value='2'>百分比</option>"); //为Select追加一个Option(下拉项) 
		}else{
			$("#InputsWrapperone").hide();
			$("#ds1").show();
			$("#ds2").show();
			$("#ds3").show();
			$("#chargetype_select").append("<option value='1' >定额</option>");
			$("#chargetype_select").append("<option value='2' selected='selected'>百分比</option>"); //为Select追加一个Option(下拉项) 
		}
	}
}

//当点击到这个页面的时候页面默认显示的数据
$(function(){
	var m = $("#chargetype_select").val();
	if (m == 1) {
		$("#InputsWrapperone").show();
		$("#ds1").hide();
		$("#ds2").hide();
		$("#ds3").hide();

	}
	if (m == 2) {
		$("#InputsWrapperone").hide();
		$("#ds1").show();
		$("#ds2").show();
		$("#ds3").show();
	}
});

//当是百分比改定额的时候要用到的方法
$(document).ready(
		function() {
			var MaxInputs = 15; //最大能添加多少个
			var InputsWrapper = $("#InputsWrapperone"); //定额金额父节点
			var Addquotafee = $("#Addquotafee"); //定额按钮
			var x = InputsWrapper.length; //获取定额里面的长度  
		
			var FieldCount = 1; //设定初始值

			var addsnidbtn = $("#addsnidbtn");  //定向名单按钮
			var snidAll = $("#snidAll"); //定向名单父节点
			var y = snidAll.length; //获取定向名单长度
			
			/* 加载页面后把定额和百分比的div隐藏 */
			$(Addquotafee).click(
							function(e){
								if (x <= MaxInputs){
									var oDiv = $('<div class="wapperclass"></div>');
									oDiv.html('<div class="form-group">'
													+ '<label for="inputminMoney" class="col-sm-3 control-label">定额金额范围</label>'
													+ '<div class="col-sm-2">'
													+ '<div class="input-group">'
													+ '<input type="text" readonly="readonly" name="rechargeQuotaFee[' + x + '].minmoney"  placeholder="定额最低" class="form-control"  id="inputminMoney"/>'
													+ '<span class="input-group-addon">元</span>'
													+ '</div>'
													+ '</div>'
													+ '<div class="col-sm-2">'
													+ '<div class="input-group">'
													+ '<input type="text" readonly="readonly" value="999999999999"name="rechargeQuotaFee[' + x + '].maxmoney"  id="inputmaxMoney" placeholder="定额最高" class="form-control maxMoney"/>'
													+ '<span class="input-group-addon">元</span>'
													+ '</div>'
													+ '</div>'
													
													+ '<div class="col-sm-1">'
													+ '<span><button name="removecode" id="removecode'+x+'" class="btn btn-default removecode"><strong>-</strong></button></span>'
													+ '</div>'
													+ '</div>'
													
													+'<div class="form-group">'
													+'<label class="col-sm-3 control-label" for="inputquota">定额</label>'
													+'<div class="col-sm-2">'
													+'<div class="input-group">'
													+'<input type="text" name="rechargeQuotaFee[' + x + '].quotafee" class="form-control" placeholder="定额" />'
													+'<span class="input-group-addon">元</span>'
													+'</div>'
													+'</div>'
													+'</div>'
													
													+ '</div>');
									$(InputsWrapper).append(oDiv);
									
									var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
									var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);//获取添加过后第二个输入框
									lastBeforeInput.attr("readonly",false);
									lastBeforeInput.val("");
							
									//判断最大金额
									$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ x + '].maxmoney',{
														validators : {
															notEmpty : {
																message : '最高金额不能为空'
															},
															regexp : { //匹配规则
																regexp : /^(\d*\.)?\d+$/,
																message : '金额必须为浮点数如:100或100.0'
															}
														}
													});
									//验证定额
									$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ x + '].quotafee',{
														validators : {
															notEmpty : {
																message : '定额不能为空'
															},
															regexp : { //匹配规则
																regexp : /^(\d*\.)?\d+$/,
																message : '定额手续费必须为浮点数如:100或100.0'
															}
														}
													});
									
									x++;
								}
								if (x == MaxInputs) {
									$(this).attr("disabled","disabled");
									return true;
								}
								return false;
							});
			
			
			
			//定额删除	
			$("body").on("click",".removecode",
					function(e) {
						var $row = $(this).parents('.wapperclass'), $option = $row.find('[name="option[]"]');
						var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
						var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
						ntInput.val(bfInput.val());//删除之后替换后面一个输入框的值
						$row.remove();
						x--;
						var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
						lastInput.val("999999999999");
						lastInput.attr("readonly","readonly");
						$('form').bootstrapValidator('removeField',$option);
					});

			$("body").on("keyup", ".maxMoney", function(e) {
				var nextInput=$(this).parents('.wapperclass').next().find("input").eq(0);
				nextInput.val($(this).val());
			});
			
			
			$(addsnidbtn).click(
					function(e){
						if (y <= MaxInputs){
							FieldCount++; //text box added increment  
							var snidDiv = $('<div class="snidclass"></div>');
							snidDiv.html('<div class="form-group">'
									+ '<label for="inputminMoney" class="col-sm-3 control-label">定向排出名单</label>'
									+ '<div class="col-sm-3">'
									+ '<div class="input-group" style="width: 263px">'
									+ '<input type="text"  name="rechargeSNLLink['+ y +'].code" id="code_' + y +'" placeholder="排除人名单编号" class="form-control"  onblur="snlidfun(this)"/>'
									
									+ '</div>'
									+ '</div>'
									+ '<div class="col-sm-1">'
									+ '<span><button name="removesnidedis" id="removesnidedis'+ y +'" class="btn btn-default removesnidedis"><strong>-</strong></button></span>'
									+ '</div>'
									+ '</div>');
							$(snidAll).append(snidDiv);
							
							$('form').data('bootstrapValidator').addField('rechargeSNLLink['+ y + '].code',{
								validators : {
									notEmpty : {
										message : '定向名单编号不能为空'
									},
									
								}
							});
							y++;
						}
						
						if (y == MaxInputs) {
							$(this).attr("disabled","disabled");
							return true;
						}
						return false;
					}); 
			$("body").on("click",".removesnidedis",
					function(e) {
						var $row = $(this).parents('.snidclass'), $option = $row.find('[name="option[]"]');
						
						$row.remove();
						y--;
						$('form').bootstrapValidator('removeField',$option);
					});
		
		});
