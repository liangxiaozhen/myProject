<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FailTcompensate_Inst</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/rechargerate/insertrechargerate.js"></script>

<script type="text/javascript">
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
 
 
function conblck(){
	window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/query.action";
}

$(function(){
	$('#defaultForm').bootstrapValidator(
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
							},
							callback : {
								message : '定向金额高值不能低于低值',
								callback : function(value,validator) {
									var minmoney = $("#inputminMoney").val(), sum = parseFloat(minmoney);
									return value >= sum;
								}
							}
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

	//定额手续费 
	$(document).ready(
					function() {
						var MaxInputs = 15; //maximum input boxes allowed  
						var InputsWrapper = $("#InputsWrapperone"); //Input boxes wrapper ID  
						var AddButton = $("#AddMoreFileBoxone"); //Add button ID 
						var x = InputsWrapper.length; //initlal text box count  
						var FieldCount = 1; //to keep track of text box added  
						
						var snidbtn = $("#snidbtn"); //定向名单按钮
						var snidAll = $("#snidAll"); //定向名单父节点
						var y = snidAll.length; //获取定向名单长度
						
						/* 加载页面后把定额和百分比的div隐藏 */
						$(AddButton).click(
										function(e){
											if (x <= MaxInputs){
												FieldCount++; //text box added increment  
												var oDiv = $('<div class="wapperclass"></div>');
												oDiv.html('<div class="form-group">'
																+ '<label for="inputminMoney" class="col-sm-3 control-label">定额金额范围</label>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" readonly="readonly" name="rechargeQuotaFee[' + x + '].minmoney" id="inputminMoney" placeholder="投标累计金额最低" class="form-control" />'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" readonly="readonly" value="999999999999"name="rechargeQuotaFee[' + x + '].maxmoney" id="inputmaxMoney" placeholder="投标累计金额最高" class="form-control maxMoney" />'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-1">'
																+ '<span><button name="removebutton" id="removebuttonone'+x+'" class="btn btn-default removeclassone"><strong>-</strong></button></span>'
																+ '</div>'
																+ '</div>'
																
																+'<div class="form-group">'
																+'<label class="col-sm-3 control-label" for="inputquota">定额</label>'
																+'<div class="col-sm-3">'
																+'<div class="input-group">'
																+'<input type="text" name="rechargeQuotaFee[' + x + '].quotafee" id="inputquota" class="form-control" placeholder="定额"/>'
																+'<span class="input-group-addon">元</span>'
																+'</div>'
																+'</div>'
																+'</div>'
																);
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
																			message : '定额不能为空后面'
																		},
																		regexp : { //匹配规则
																			regexp : /^(\d*\.)?\d+$/,
																			message : '定额补偿金必须为浮点数如:100或100.0'
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
						
					
						$(snidbtn).click(
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
												+ '<span><button name="removesnid" id="removesnid'+ y +'" class="btn btn-default removesnid"><strong>-</strong></button></span>'
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
				//定向名单删除
					$("body").on("click",".removesnid",
						function(e) {
							var $row = $(this).parents('.snidclass'), $option = $row.find('[name="option[]"]');
							
							$row.remove();
							y--;
							$('form').bootstrapValidator('removeField',$option);
						});
				
						
						//刪除標籤	
						$("body").on("click",".removeclassone",
								function(e) { //user click on remove text  
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

						$("body").on("blur", ".maxMoney", function(e) { //user click on remove text
							var beforeInput=$(this).parents('.wapperclass').find("input").eq(0);
							var nextInput=$(this).parents('.wapperclass').next().find("input").eq(0);
							var nextMaxInput=$(this).parents('.wapperclass').next().find("input").eq(1);
							if(beforeInput.val()!=""&&$(this).val()!=""){
								if(parseInt(beforeInput.val())>=parseInt($(this).val())){
									alert("输入的值不能小于前面的最小值");
									$(this).val("");
									nextInput.val("");
								}
							}
							if(nextMaxInput.val()!=""&&$(this).val()!=""){
								if(parseInt(nextMaxInput.val())<=parseInt($(this).val())){
									alert("输入的值不能大于后面的最大值");
									$(this).val("");
									nextInput.val("");
								}
							}
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
						//验证百分比的时候的手续费最低和最高
						$("body").on("blur", "#dsmaxfee", function(e) {
							var dsminfee = $("#dsminfee").val();
							var dsmaxfee = $("#dsmaxfee").val();
							if(dsminfee!="" && dsmaxfee!=""){
								if(parseInt(dsmaxfee)<=parseInt(dsminfee)){
									alert("输入的值不能小于前面的最小值");
									$("#dsmaxfee").val("");
								}
							}
						});
					});

	function save(){
		var action = "${pageContext.request.contextPath}/admin/rechargeRate/savehas.action" ;
		var params = $("#defaultForm").serializeArray();
		$.post(action,params,function(data){
			if(data=="has"){
				alert("已经存在相同充值类型的费率设置,请重新设置");
			}else{
				if(data=="yes"){
					window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/query.action";
				}
			}
		},'json');
		$('#defaultForm').data('bootstrapValidator').validate(); //重置表单验证
	}
</script>
</head>
<body>
		<div class="container" style="margin-top: 25px;">
		<form class="form-horizontal" role="form" id="defaultForm"  method="POST">
			<!--投标等级-->
			<div id="CompensateOn_div">
				
			<div class="form-group">
				<label class="control-label col-sm-3" for="istemplet">充值方式</label>
				<div class="col-sm-3">
					<select name="rechartype" class="form-control" id="rechartype" onchange="rechartypexuanz()">
						<option value="1">个人网银</option>
						<option value="3">企业网银</option>
						<option value="2">快捷支付</option>
						<option value="4">汇款充值</option>
					</select>
				</div>
			</div> 
			
			<div class="form-group">
					<label class="col-sm-3 control-label">类型</label>
					<div class="col-sm-3">
						<select name="chargetype" id="chargetype_select" class="form-control" onchange="xuanz()">
							<option value="1">定额</option>
							<option value="2">百分比</option>
						</select>
					</div>
			</div>
			
			<!--充值金额范围:主要针对充值金额-->
			<div class="form-group" id="ds1" style="display: none">
					<label class="col-sm-3 control-label">充值金额范围</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="minmoney" id="dsminmoney" class="form-control" placeholder="充值金额最低值" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxmoney" id="dsmaxmoney" class="form-control" placeholder="充值金额最高值" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
			</div>
			
			<!-- 比例金额范围:主要针对手续费-->
			<div class="form-group" id="ds2" style="display: none">
				<label class="col-sm-3 control-label">比例金额范围</label>
				<div class="col-sm-3">
					<div class="input-group">
						<input type="text" name="minfee" id="dsminfee" class="form-control" placeholder="手续费最低值" />
						<span class="input-group-addon">元</span>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<input type="text" name="maxfee" id="dsmaxfee" class="form-control" placeholder="手续费最高值" /> 
						<span class="input-group-addon">元</span>
					</div>
				</div>
			</div>
				
			<div class="form-group" id="ds3" style="display: none">
				 <label class="col-sm-3 control-label">充值费率</label>
				 <div class="col-sm-3">
					 <div class="input-group">
						 <input type="text" name="charrate" id="charrate" class="form-control" placeholder="充值费率"/>
						 <span class="input-group-addon">%</span>
				 	</div>
				 </div>
			 </div>
							
			<!--定额金额最高最低-->
			<div id="InputsWrapperone">
				<div class="wapperclass">
				<div class="form-group">
					<label for="inputminMoney" class="col-sm-3 control-label">定额金额范围</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" value="0" readonly="readonly" name="rechargeQuotaFee[0].minmoney" id="inputminMoney" 
							placeholder="定额金额最低" class="form-control" /> 
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" value="999999999999" readonly="readonly" name="rechargeQuotaFee[0].maxmoney" id="inputmaxMoney"
							 placeholder="定额金额最高" class="form-control maxMoney" /> 
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-1">
						<span>
							<button name="addmorefileboxone" type="button"
								id="AddMoreFileBoxone">
								<strong>+</strong>
							</button></span>
					</div>
					<br/>
				</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">定额</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="rechargeQuotaFee[0].quotafee" id="inputquota"
									class="form-control" placeholder="定额" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
				
				</div>
			</div>
		</div>
		
		<div id="snidAll">
			<div class="snidclass">
				<div class="form-group">
						<label class="col-sm-3 control-label">定向排出名单</label>
						<div class="col-sm-3">
							<div class="input-group" style="width: 263px">
								<input type="text"  name="rechargeSNLLink[0].code" id="code_0" placeholder="定向排出名单" class="form-control code" onblur="snlidfun(this)"/>
							</div>
						</div>
						<div class="col-sm-1">
							<span>
								<button  id="snidbtn"  type="button">
									<strong>+</strong>
								</button>
							</span>
						</div>
				</div>
				</div>
			</div>
		
		
		
		<!--是否为模板-->
		<div class="form-group">
			<label class="control-label col-sm-3" for="istemplet">是否为模板</label>
			<div class="col-sm-3">
				<select name="istemplet" class="form-control" id="istemplet">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		
			
		<!-- 备注 -->
		<div class="form-group">
		   <label class="control-label col-sm-3">备注</label>
		   <div class="col-sm-3">
		     <textarea rows="3" name="remark" class="form-control"></textarea>
		   </div>
		</div>
		<div class="form-group">
				<div class="col-sm-offset-3 col-sm-1">
					<button type="button" class="btn btn-default" id="failButton" onclick="save();">保存</button>
				</div>
				<div class="col-sm-1">
					<button type="button" class="btn btn-default" onclick="conblck()">返回列表</button>
				</div>
		</div>
	</form>
</div>
</body>
</html>