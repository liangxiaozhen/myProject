<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript"> var basePath = "${pageContext.request.contextPath }";</script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/rechargerate/updaterate.js"></script> --%>
<script type="text/javascript">

function conblck(){
	window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/query.action";
}

//验证所有除了本输入框之外的其他输入框,有没有和本输入框相同的值,如果有,就把本输入框置空
function snlidfun(obj){
	var max = 15;
	var id = $(obj).attr("id");
	var str = id.split("_");
	var str1 = str[1];
	var code = $(obj).val();//获取当前的值
	
	var codeSArray = new Array();
	for(var i=0;i<15;i++){//遍历所有
		var code_i = $("#code_"+i).val();
		if(code_i!=null){
			codeSArray[i] = code_i;
		}
	}
	if(code!=""){
		var count = 0
		//剩下的就是除了本输入框外的所有其他元素
		for(var k=0;k<codeSArray.length;k++){
			if(code==codeSArray[k]){//如果有其他输入框的值和本输入框的值相同!就提示本输入框不能输入值,并且置空,给出提示
				count++;
				if(count>=2){//如果是和非本身比,那么count就是2,和本身比count就是1
					break;
				}
			}
		}
		if(count<2){
			fengzhuang(obj);
		}else{
			$(obj).val("");
			alert("不能填入相同的值,请重新输入");
		}
	}
	
	
}

//验证定向名单是否正确
/* function snlidfun2(obj){
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
			alert("不能填入相同的值,请重新输入1");
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
}   */
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
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
							}
						}
					},
					'rechargeQuotaFee[0].maxmoney': {
						validators : {
							notEmpty : {
								message : '最高金额不能为空'
							},
							regexp : {
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
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
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
							}
						}
					},
				
					maxmoney: {
						validators : {
							notEmpty : {
								message : '充值金额最高值不能为空'
							},
							regexp : {
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
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
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
							}
						}
					},
					
					maxfee: {
						validators : {
							notEmpty : {
								message : '最高收费不能为空'
							},
							regexp : {
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
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
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
							}/* ,
							callback : {
								message : '充值费率不能超过100%',
								callback : function(value,validator) {
									var charrate = $("#charrate").val();
									return parseFloat(charrate)>parseFloat(100.00);
								}
							} */ 
						}
					},
					'rechargeQuotaFee[0].quotafee': {
						validators : {
							notEmpty : {
								message : '定额不能为空'
							},
							regexp : {
								regexp : /^\d+(?:\.\d{1,2})?$/,
								message : '金额为浮点数如10.0或10.00'
							}
						}
					},
					'rechargeSNLLink[0].code': {
						validators : {
							notEmpty : {
								message : '定性名单编号不能为空'
							},
							regexp : {
								regexp : /^[A-Za-z0-9]*$/,
								message : '输入字母和数字的组合'
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
							regexp : /^\d+(?:\.\d{1,2})?$/,
							message : '金额必须为浮点数如:100.0或100.00'
						}
					}
				});
			$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].minmoney',{
					validators : {
						notEmpty : {
							message : '最低金额不能为空'
						},
						regexp : { //匹配规则
							regexp : /^(\d*\.)?\d+$/,
							message : '金额必须为浮点数如:100.0或100.00'
						}
					}
				}); 
				$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].quotafee',{
					validators : {
						notEmpty : {
							message : '定额手续费不能为空'
						},
						regexp : { //匹配规则
							regexp : /^\d+(?:\.\d{1,2})?$/,
							message : '金额必须为浮点数如:100.0或100.00'
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
													+ '<label for="inputminMoney" class="col-sm-3 control-label">充值金额范围</label>'
													+ '<div class="col-sm-2">'
													+ '<div class="input-group">'
													+ '<input type="text"  name="rechargeQuotaFee[' + feecount + '].minmoney" id="inputminMoney_'+feecount+'" placeholder="定额最低" class="form-control minMoney" />'
													+ '</div>'
													+ '</div>'
													+'<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px"><span>元</span></div>'
													+ '<div class="col-sm-2">'
													+ '<div class="input-group">'
													+ '<input type="text"  value="999999999999"name="rechargeQuotaFee[' + feecount + '].maxmoney" id="inputmaxMoney_'+feecount+'" placeholder="定额最高" class="form-control maxMoney"/>'
													+ '</div>'
													+ '</div>'
													+'<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px"><span>元</span></div>'
													+ '<div class="col-sm-1">'
													+ '<span><button name="removebutton" id="removebuttonone'+feecount+'" class="btn btn-default removeclassone" type="button" style="width: 100px;height: 30px;color: red">-删除</button></span>'
													+ '</div>'
													+ '</div>'
													
													+'<div class="form-group">'
													+'<label class="col-sm-3 control-label" for="inputquota">定额手续费</label>'
													+'<div class="col-sm-3">'
													+'<div class="input-group">'
													+'<input type="text" name="rechargeQuotaFee[' + feecount + '].quotafee" id="inputquota" class="form-control" placeholder="定额手续费" />'
													+'</div>'
													+'</div>'
													+'<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px"><span>元</span></div>'
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
									/* var lastMax = $("#InputsWrapperone").children(".wapperclass:last-child").prev(".wapperclass").find(".maxMoney");
									lastMax.val(""); */

									for(var i=1;i<=parseInt(feecount);i++){
										$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].maxmoney',{
											validators : {
												notEmpty : {
													message : '最高金额不能为空'
												},
												regexp : { //匹配规则
													regexp : /^\d+(?:\.\d{1,2})?$/,
													message : '金额必须为浮点数如:100.0或100.00'
												}
											}
										});
										$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].minmoney',{
											validators : {
												notEmpty : {
													message : '最低金额不能为空'
												},
												regexp : { //匹配规则
													regexp : /^(\d*\.)?\d+$/,
													message : '金额必须为浮点数如:100.0'
												}
											}
										}); 
										$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ i + '].quotafee',{
											validators : {
												notEmpty : {
													message : '定额手续费不能为空'
												},
												regexp : { //匹配规则
													regexp : /^\d+(?:\.\d{1,2})?$/,
													message : '金额必须为浮点数如:100.0或100.00'
												}
											}
										});
										
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
						var $idvalue = $(this).parents('.wapperclass').find("input").eq(1).val();//获取当前节点的父节点之下的第二个input输入框的值,也就是id值
						var $row = $(this).parents('.wapperclass'), $option = $row.find('[name="option[]"]');
						var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
						var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
						ntInput.val(bfInput.val());//删除之后替换后面一个输入框的值
						$row.remove();
						if($idvalue!="" && $idvalue!=undefined && $idvalue!=null){
							var action = "${pageContext.request.contextPath}/admin/rechargeRate/deletefee.action";
							var params = {
									"feeid":$idvalue
							}
							$.post(action,params,function(data){
								if(data=="success"){
									var id = $("#rechargeId").val();
									window.location.href="${pageContext.request.contextPath}/admin/rechargeRate/queryEdits.action?id="+id;
								}
							},'json');
						}
						var feecount2 = fecount--;
						$("#feecount").val(feecount2);//覆盖隐藏域的值
						var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
						lastInput.val("999999999999");
						$('form').bootstrapValidator('removeField',$option); 
					});
			$("body").on("keyup", ".maxMoney", function(e) {
				var nextInput=$(this).parents('.wapperclass').next().find("input").eq(0);
				nextInput.val($(this).val());
			});
			
/**定向名单 */			
var count = $("#snlidcount").val();//获取隐藏域的值
for(var i=1;i<=parseInt(count);i++){
	$('form').data('bootstrapValidator').addField('rechargeSNLLink['+ i + '].code',{
		validators : {
			notEmpty : {
				message : '定向名单不能为空'
			},
			regexp : {
				regexp : /^[A-Za-z0-9]*$/,
				message : '输入字母和数字的组合'
			}
		}
	});
}
$(snidbtn).click(
				function(e){
					if (count <= MaxInputs){
						var snidDiv = $('<div class="snidclass"></div>');
						snidDiv.html('<div class="form-group">'
								+'<label class="col-sm-3 control-label">定向排除名单</label>'
								+'<div class="col-sm-3">'
								+'<div class="input-group" style="width: 263px">'
								+'<input type="text" name="rechargeSNLLink['+count+'].code" id="code_'+count+'" class="form-control" placeholder="定向排除名单" onblur="snlidfun(this)"/>'
								+ '</div>'
								+ '</div>'
								+ '<div class="col-sm-1">'
								+ '<span><button name="removesnid" id="removesnid'+count+'" class="btn btn-default removesnid" type="button" style="width: 100px;height: 30px;color: red">-删除</button></span>'
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
			var $idvalue = $(this).parents('.snidclass').find("input").eq(1).val();//获取当前节点的父节点之下的第二个input输入框的值,也就是id值
			var $row = $(this).parents('.snidclass') , $option = $row.find('[name="option[]"]') ;
			$row.remove(); 
			 if($idvalue!="" && $idvalue!=undefined && $idvalue!=null){
				var action = "${pageContext.request.contextPath}/admin/rechargeRate/delete.action";
				var params = {
						"relinkid":$idvalue
				}
				var count2 = count--;
				$("#snlidcount").val(count2);//覆盖隐藏域的值
				$('form').bootstrapValidator('removeField',$option);	
				$.post(action,params);
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
		
});
$(function(){
	$("body").on("blur", ".maxMoney", function(e) {
	/* $(".maxMoney").blur(function(e) { */
		var min = $(this).parents(".form-group").find('.minMoney').val();  //获取最小值
		var max = $(this).val();//最大值输入框的值
		var xmin =$(this).parents(".wapperclass").next(".wapperclass").find(".form-group").find(".minMoney");
		var smin =xmin.val();//获取下一个最小值
		if(min!=""&& max!=""){
			if(parseFloat(max)<=parseFloat(min)){
				alert("输入的值不能小于或则等于前面的最小值");
				$(this).val("");
			}
		}
		if(max!="" && smin!=""){
			if(parseFloat(max)<parseFloat(smin)){
				alert("输入的值不能小于下一个最小值");
				$(this).val("");
				
			}
		}
	});
	$("body").on("blur", ".minMoney", function(e) {
	 /* $(".minMoney").blur(function(e) { */
				    var jmax = $(this).parents(".wapperclass").prev(".wapperclass").find(".form-group").find(".maxMoney");
					var max =jmax.val();//上一个节点的最大值
					var min = $(this).val();//获取当前输入框的值
					var xjmax = $(this).parents(".form-group").find('.maxMoney');//获取当前节点的同级下个节点的最大值
					var xmax = xjmax.val();
					
						
					if(min!=""&& max!="" && max!= undefined){//当前最小值输入框的值不能小于前面最大值输入框的值
						if(parseFloat(min)!=parseFloat(max)){
							alert("输入的值必须和上一个值相等");
							$(this).val("");
						}
					}
				
					if(xmax!="" && min!=""){//当前最小输入框的值不能大于后面最大值输入框的值
						if(parseFloat(min)>=parseFloat(xmax)){
							alert("输入的值不能大于或者等于下一个最大值");
							$(this).val("");
						}
					}
			 });	
	
});
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
//是否选中
function yesorno(){
	 if ($('#pitchon').is(':checked')) {//选中
		 	$("#ds2").show();
		 	$("#ds1").show();
		}else{//取消选中
			$("#ds2").hide();
			$("#ds1").hide();
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
//跳转到页面的时候显示复选框是选中还是不选中
$(function(){
	var ck = $("#ck").val();
	var pitch = document.getElementById('pitchon');
	if(ck=="1"){//必须默认选中
		$("#ds4").show();//复选框显示出来
		$("#ds2").show();
	 	$("#ds1").show();
	 	pitch.checked = true;
	}else{
		$("#ds4").hide();//复选框隐藏
		$("#ds2").hide();
	 	$("#ds1").hide();
	 	pitch.checked = false;
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
													+ '<label for="inputminMoney" class="col-sm-3 control-label">充值金额范围</label>'
													+ '<div class="col-sm-2">'
													+ '<div class="input-group">'
													+ '<input type="text"  name="rechargeQuotaFee[' + x + '].minmoney"  placeholder="定额最低" class="form-control minMoney"  id="inputminMoney"/>'
													+ '</div>'
													+ '</div>'
													+'<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px"><span>元</span></div>'
													+ '<div class="col-sm-2">'
													+ '<div class="input-group">'
													+ '<input type="text"  value="999999999999" name="rechargeQuotaFee[' + x + '].maxmoney"  id="inputmaxMoney" placeholder="定额最高" class="form-control maxMoney"/>'
													+ '</div>'
													+ '</div>'
													+'<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px"><span>元</span></div>'
													+ '<div class="col-sm-1">'
													+ '<span><button name="removecode" id="removecode'+x+'" class="btn btn-default removecode" style="width: 100px;height: 30px;color: red">-删除</button></span>'
													+ '</div>'
													+ '</div>'
													
													+'<div class="form-group">'
													+'<label class="col-sm-3 control-label" for="inputquota">定额手续费</label>'
													+'<div class="col-sm-2">'
													+'<div class="input-group">'
													+'<input type="text" name="rechargeQuotaFee[' + x + '].quotafee" class="form-control" placeholder="定额手续费" />'
													+'</div>'
													+'</div>'
													+'<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px"><span>元</span></div>'
													+'</div>'
													
													+ '</div>');
									$(InputsWrapper).append(oDiv);
									
									/* var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
									var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);//获取添加过后第二个输入框
									lastBeforeInput.attr("readonly",false);
									lastBeforeInput.val(""); */
							
									//判断最大金额
									$('form').data('bootstrapValidator').addField('rechargeQuotaFee['+ x + '].maxmoney',{
														validators : {
															notEmpty : {
																message : '最高金额不能为空'
															},
															regexp : { //匹配规则
																regexp : /^\d+(?:\.\d{1,2})?$/,
																message : '金额必须为浮点数如:100.0或100.00'
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
																regexp : /^\d+(?:\.\d{1,2})?$/,
																message : '定额手续费必须为浮点数如:100.0或100.00'
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
						/* lastInput.val("999999999999"); */
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
									+ '<label for="inputminMoney" class="col-sm-3 control-label">定向排除名单</label>'
									+ '<div class="col-sm-3">'
									+ '<div class="input-group" style="width: 263px">'
									+ '<input type="text"  name="rechargeSNLLink['+ y +'].code" id="code_' + y +'" placeholder="排除人名单编号" class="form-control"  onblur="snlidfun(this)"/>'
									
									+ '</div>'
									+ '</div>'
									+ '<div class="col-sm-1">'
									+ '<span><button name="removesnidedis" id="removesnidedis'+ y +'" class="btn btn-default removesnidedis" style="width: 100px;height: 30px;color: red">-删除</button></span>'
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
						
						if (y == MaxInputs){
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
$(function(){
	$("#charrate").blur(function(){
		var charrate = $("#charrate").val();
		if(charrate!=""){
			if(parseFloat(charrate)>100){
				alert("充值费率不能大于100%");
				 $("#charrate").val("");
			}
		}
	});
});	 

</script>
</head>

<body style="font-family: 微软雅黑;">
	<div class="container" style="margin-top: 25px;">
		<input type="hidden" value="${ck}" id="ck"/>
		<form class="form-horizontal" id="updateForm" action="${pageContext.request.contextPath}/admin/rechargeRate/update.action" method="POST">
			<!--投标等级-->
			<div id="CompensateOn_div">
				<input value="${rr.id}" name="id" type="hidden" id="rechargeId"/>
				
				<div class="form-group">
					<label class="control-label col-sm-3" for="istemplet">充值方式</label>
					<div class="col-sm-3">
						<div class="input-group" style="margin-top: 7px">
							<c:if test="${rr.rechartype ==1}">
								个人网银
							</c:if>
							<c:if test="${rr.rechartype ==2}">
								快捷支付
							</c:if>
							<c:if test="${rr.rechartype ==3}">
								企业网银
							</c:if>
							<c:if test="${rr.rechartype ==4}">
								汇款充值
							</c:if>
						</div>	
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-3 control-label">类型</label>
					<div class="col-sm-2">
						<select name="chargetype" id="chargetype_select" class="form-control" onchange="xuanz()">
							<option value="1" <c:if test="${rr.chargetype ==1}">selected="selected"</c:if>>定额</option>
							<option value="2" <c:if test="${rr.chargetype ==2}">selected="selected"</c:if>>比例</option>
						</select>
					</div>
				</div>
			
		<div id="snidAll">
			 <c:if test="${!empty rr.rechargeSNLLinkList}">
				<c:forEach items="${rr.rechargeSNLLinkList}" var="item2" varStatus="status2">
					<div class="snidclass">
					<input type="hidden" value="${rr.rechargeSNLLinkList.size()}" id="snlidcount" name="snlidcount"/>
					<input type="hidden" value="${item2.id}"  name="rechargeSNLLink[${status2.index}].id" id="snlikid_${status2.count}"/> 
						<c:if test="${status2.count eq 1}">
						 <div class="form-group">
								<label class="col-sm-3 control-label">定向排除名单</label>
								<div class="col-sm-3">
									<div class="input-group" style="width: 263px">
										<input type="text"  name="rechargeSNLLink[${status2.index}].code" id="code_${status2.index}" value="${item2.code}" class="form-control" onblur="snlidfun(this)"/>
									</div>
								</div>
								 <div class="col-sm-1">
									<span><button  id="snidbtn"  type="button" style="width: 100px;height: 30px">+继续添加</button></span>
								</div>
						    </div>
						</c:if>
						<c:if test="${status2.count != 1}">
						 	<div class="form-group">
								<label class="col-sm-3 control-label">定向排除名单</label>
								<div class="col-sm-3">
									<div class="input-group" style="width: 263px">
										<input type="text"  name="rechargeSNLLink[${status2.index}].code" id="code_${status2.index}" value="${item2.code}" class="form-control" onblur="snlidfun(this)"/>
									</div>
								</div>
								 <div class="col-sm-1">
									<span>
										<button name="removesnid" id="removesnid'+${status2.index}+'" class="btn btn-default removesnid" type="button" style="width: 100px;height: 30px;color: red">
										-删除</button>
									</span>
								</div>
						    </div>
						</c:if>
					</div>
				</c:forEach>
			</c:if>  
			<!-- 当没有定向名单的时候 -->
			<c:if test="${empty rr.rechargeSNLLinkList}">
				<div class="snidclass">
					<div class="form-group">
						<label class="col-sm-3 control-label">定向排除名单</label>
						<div class="col-sm-3">
							<div class="input-group" style="width: 263px">
								<input type="text" name="rechargeSNLLink[0].code" id="code"
									value="" class="form-control" onblur="snlidfun(this)" placeholder="定向排除名单"/>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="addsnidbtn" type="button" style="width: 100px;height: 30px">
									+继续添加
								</button></span>
						</div>
					</div>
				</div>
			</c:if>
		</div> 

			
				<div class="form-group" id="ds3" style="display: none">
					 <label class="col-sm-3 control-label">充值费率</label>
					 <div class="col-sm-2">
						 <div class="input-group">
						 	 <c:if test="${!empty rr.charrate}">
							 	<input type="text" name="charrate" id="charrate" class="form-control" value="${df1.format(rr.charrate)}"/>
						 	 </c:if>	
						 	 <c:if test="${empty rr.charrate}">
							 	<input type="text" name="charrate" id="charrate" class="form-control" value="0"/>
						 	 </c:if>	
					 	</div>
					 </div>
					 <div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
						 	<span>%</span>
					 </div>
				 </div> 
			 
			 <div class="form-group" id="ds4" style="display: none">
			 	<label class="col-sm-3 control-label"></label>
				 <div class="col-sm-3">
					 <div class="input-group">
					 	 <input type="checkbox" name="radio" value="1" onclick="yesorno();" id="pitchon">设置手续费最低、最高金额
				 	</div>
				 </div>
			 </div> 
			 
			 
			 
			<!-- 比例金额范围:主要针对手续费-->
			<div class="form-group" id="ds2" style="display: none">
				<label class="col-sm-3 control-label">最低手续费</label>
				<div class="col-sm-2">
					<div class="input-group">
						 <c:if test="${!empty rr.minfee}">
							<input type="text" name="minfee" id="dsminfee" class="form-control" value="${df1.format(rr.minfee)}"/>
						</c:if>
						 <c:if test="${empty rr.minfee}">
						 	<input type="text" name="minfee" id="dsminfee" class="form-control" value="0"/>
						 </c:if>
					</div>
				</div>
				<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 	<span>元</span>
				 </div>
			</div>
			
		<div class="form-group" id="ds1" style="display: none">
		 	<label class="col-sm-3 control-label">最高手续费</label>
				<div class="col-sm-2">
					<div class="input-group">
						 <c:if test="${!empty rr.maxfee}">
							<input type="text" name="maxfee" id="dsmaxfee" class="form-control" value="${df1.format(rr.maxfee)}"/>
						</c:if> 
						 <c:if test="${empty rr.maxfee}">
						 	<input type="text" name="maxfee" id="dsmaxfee" class="form-control" value="0"/>
						 </c:if>
					</div>
				</div>
				<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 	<span>元</span>
				 </div>
			</div>	
				

	<!--定额金额最高最低-->
 	<div id="InputsWrapperone">
		<c:if test="${!empty rr.rechargeQuotaFeeList}">
			<c:forEach items="${rr.rechargeQuotaFeeList}" var="item" varStatus="status">
				<div class="wapperclass">
					<input type="hidden" id="feecount" value="${rr.rechargeQuotaFeeList.size()}"/>
					<input type="hidden" value="${item.id}"  name="rechargeQuotaFee[${status.index}].id" id="feeid_${status.count}"/> 
					<c:if test="${status.count eq 1}">
					<div class="form-group">
						<label for="inputminMoney" class="col-sm-3 control-label">充值金额范围</label>
						<div class="col-sm-2 min">
							<div class="input-group">
								<c:if test="${!empty item.minmoney}">
									<input type="text" value="${df1.format(item.minmoney)}"  name="rechargeQuotaFee[${status.index}].minmoney" id="inputminMoney_${status.index}"
									placeholder="定额金额最低" class="form-control minMoney"/> 
								</c:if>
								<c:if test="${empty item.minmoney}">
									<input type="text" value="0"  name="rechargeQuotaFee[${status.index}].minmoney" id="inputminMoney_${status.index}"
									placeholder="定额金额最低" class="form-control minMoney"/> 
								</c:if>
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
						<div class="col-sm-2">
							<div class="input-group">
								<c:if test="${!empty item.maxmoney}">
									<input type="text" value="${df1.format(item.maxmoney)}"  name="rechargeQuotaFee[${status.index}].maxmoney"  id="inputmaxMoney_${status.index}"
									 placeholder="定额金额最高" class="form-control maxMoney" />
								</c:if>
								<c:if test="${empty item.maxmoney}">
									<input type="text" value="0"  name="rechargeQuotaFee[${status.index}].maxmoney"  id="inputmaxMoney_${status.index}"
									 placeholder="定额金额最高" class="form-control maxMoney" />
								</c:if>
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
						<div class="col-sm-1">
							<span><button name="addmorefileboxone" type="button"id="AddMoreFileBoxone" style="width: 100px;height: 30px">+继续添加</button></span>
						</div>
						<br/>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">定额手续费</label>
						<div class="col-sm-3">
							<div class="input-group">
								<c:if test="${!empty item.quotafee}">
									<input type="text" name="rechargeQuotaFee[${status.index}].quotafee"  value="${df1.format(item.quotafee)}"
										class="form-control" placeholder="定额手续费" /> 
								</c:if>
								<c:if test="${empty item.quotafee}">
									<input type="text" name="rechargeQuotaFee[${status.index}].quotafee"  
										class="form-control" placeholder="定额手续费" /> 
								</c:if>
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
					</div>
				</c:if>
				<c:if test="${status.count != 1}">
					<div class="form-group">
						<label for="inputminMoney" class="col-sm-3 control-label">充值金额范围</label>
						<div class="col-sm-2 min">
							<div class="input-group">
								<c:if test="${!empty item.minmoney}">
									<input type="text" value="${df1.format(item.minmoney)}"  name="rechargeQuotaFee[${status.index}].minmoney" id="inputminMoney_${status.count}"
									placeholder="定额金额最低" class="form-control minMoney" />
								</c:if>	 
								<c:if test="${empty item.minmoney}">
									<input type="text" value="0"  name="rechargeQuotaFee[${status.index}].minmoney" id="inputminMoney_${status.count}"
									placeholder="定额金额最低" class="form-control minMoney" />
								</c:if>	 
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
						<div class="col-sm-2">
							<div class="input-group">
								<c:if test="${!empty item.maxmoney}">
									<input type="text" value="${df1.format(item.maxmoney)}"  name="rechargeQuotaFee[${status.index}].maxmoney" id="inputmaxMoney_${status.count}"
									 placeholder="定额金额最高" class="form-control maxMoney" /> 
								</c:if>	 
								<c:if test="${empty item.maxmoney}">
									<input type="text" value="0"  name="rechargeQuotaFee[${status.index}].maxmoney" id="inputmaxMoney_${status.count}"
									 placeholder="定额金额最高" class="form-control maxMoney" /> 
								</c:if>	 
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
						<div class="col-sm-1">
							<span><button name="removebutton" id="removebuttonone'+${status.count}+'" class="btn btn-default removeclassone" style="width: 100px;height: 30px;color: red">-删除</button></span>
						</div>
						<br/>
					</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">定额手续费</label>
							<div class="col-sm-3">
								  <div class="input-group">
								   <c:if test="${!empty item.quotafee}">
										<input type="text" name="rechargeQuotaFee[${status.index}].quotafee"  value="${df1.format(item.quotafee)}"  id="quotafee_${status.count}"
										class="form-control quotafee" placeholder="定额手续费" /> 
								   </c:if>
								   <c:if test="${empty item.quotafee}">
										<input type="text" name="rechargeQuotaFee[${status.index}].quotafee"   id="quotafee_${status.count}"
										class="form-control quotafee" placeholder="定额手续费" /> 
								   </c:if>
									</div>
							 </div>
								<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 				<span>元</span>
				 				</div>
						</div>
					</c:if>
				</div>
			</c:forEach>
		</c:if>  
			
			<!-- ***************************************************************************************************** -->
		 <c:if test="${empty rr.rechargeQuotaFeeList}"> 
			<div class="wapperclass">
					<div class="form-group">
						<label for="inputminMoney" class="col-sm-3 control-label">充值金额范围</label>
						<div class="col-sm-2">
							<div class="input-group">
								<input type="text" value="0"   name="rechargeQuotaFee[0].minmoney"  id="inputminMoney"
								placeholder="定额金额最低" class="form-control minMoney"/> 
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
						
						<div class="col-sm-2">
							<div class="input-group">
								<input type="text" value="999999999999"  name="rechargeQuotaFee[0].maxmoney"  id="inputmaxMoney"
								 placeholder="定额金额最高" class="form-control maxMoney" /> 
							</div>
						</div>
						<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
					 		<span>元</span>
				 		</div>
						<div class="col-sm-1">
							<span><button name="Addquotafee" type="button" id="Addquotafee" style="width: 100px;height: 30px">+继续添加</button></span>
						</div>
						<br/>
					</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">定额手续费</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" name="rechargeQuotaFee[0].quotafee" 
										class="form-control" placeholder="定额手续费" /> 
								</div>
							</div>
							<div class="col-sm-1" style="margin-top: 8px;margin-left: -20px">
								 	<span>元</span>
							 </div>
						</div>
				</div>
			</c:if>
	<!-- ***************************************************************************************************** -->
		</div>  	<!-- 定额div结束 -->	
</div><!-- 第二个div结束 -->
	

			
		<!-- 备注 -->
		<div class="form-group">
		   <label class="control-label col-sm-3">备注</label>
		   <div class="col-sm-3">
		     <textarea rows="3" name="remark" class="form-control">${rr.remark}</textarea>
		   </div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-1">
				<button type="submit" class="btn btn-default" id="failButton">保存</button>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-default" onclick="conblck()">返回列表</button>
			</div>
		</div>
		
	</form>
</div>
</body>
</html>
