<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FailTcompensate_Inst</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
<script type="text/javascript">
	$(function() {
		$("#quotainput_div").hide(); //隐藏定额
		$("#dayawardrate_div").hide(); //隐藏日奖励费率
		$("#intUGradeone").attr("checked", "checked");//默认选中全部等级
		$("#intugrades_div").hide();//隐藏全部等级
		 
		//息补偿方式类型选择
		$("#type").change(function() {
			if ($(this).val() == "iequota") {
				$("#dayawardrate_div input").val("");
				$("#dayawardrate_div").hide();
				$("#quotainput_div").show();
			} else if ($(this).val() == "iepercent") {
				$("#dayawardrate_div").show();
				$("#quotainput_div input").val("");
				$("#quotainput_div").hide();
			} else {
				$("#quotainput_div").hide();
				$("#dayawardrate_div").hide();
				$("#quotainput_div input").val("");
				$("#dayawardrate_div input").val("");
			}
		});
		//全选按钮选中当前全部等级
		$("#selectAll").click(function (e) {
			if(this.checked){
				$("#intugrades_div  :checkbox").prop("checked",true);
			}else{
				$("#intugrades_div  :checkbox").prop("checked",false);
			}
		})
		//全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio").change(function() {
			var $radioVal = $(".insert-ugrade-radio:checked").val();
			if ($radioVal == 1) {
				$("#intugrades_div").hide();
				$("#intugrades_div :checkbox").each(function() {
					this.checked = false;
				});
			} else {
				$("#intugrades_div").show();
			}
		});

		$('#defaultForm')
				.bootstrapValidator(
						{
							message : 'This value is not valid',
							feedbackIcons : { /*input状态样式图片*/
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : { //验证
								intugrades : {//会员等级
									validators : {
										notEmpty : {
											message : '不能为空'
										}
									}
								},
								'failTCompensates[0].minmoney': {//投标累计金额最低
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
								'failTCompensates[0].maxmoney': {//投标累计金额最高
									validators : {
										notEmpty : {
											message : '最高金额不能为空'
										},
										regexp : {
											regexp : /^(\d*\.)?\d+$/,
											message : '金额为浮点数如10.0或10'
										},
										callback : {
											message : '本金高值不能低于低值',
											callback : function(value,
													validator) {
												var minmoney = $(
														"#inputminMoney").val(), sum = parseFloat(minmoney);
												return value >= sum;
											}
										}
									}
								},
								'failTCompensates[0].quota': {//定额收费金额
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
								'failTCompensates[0].dayawardrate' : {//日奖励费率
									validators : {
										notEmpty : {
											message : '不能为空'
										},
										regexp : {
											regexp : /^(\d*\.)?\d+$/,
											message : '费率为小数如10.0或10'
										}
									}
								},
								'failTCompensates[0].maxcompensate' : {//最高补偿金额
									validators : {
										notEmpty : {
											message : '不能为空'
										},
										regexp : {
											regexp : /^(\d*\.)?\d+$/,
											message : '金额为浮点数如10.0或10'
										}
									}
								},
								type:{
									validators : {
										notEmpty : {
											message : '不能为空'
										}
									}
								},
								awardname : {//奖品名称
									validators : {
										notEmpty : {
											message : '不能为空'
										},
									}
								},
								intugradeawards : {//奖品补偿会员等级
									validators : {
										notEmpty : {
											message : '不能为空'
										}
									}
								},
								minmoneyaward : {//奖品补偿金额段低值
									validators : {
										notEmpty : {
											message : '金额低值不能为空'
										},
										regexp : {
											regexp : /^(\d*\.)?\d+$/,
											message : '金额为浮点数如10.0或10'
										}
									}
								},
								maxmoneyaward : {//奖品补偿金额段高值
									validators : {
										notEmpty : {
											message : '金额段高值不能为空'
										},
										regexp : {
											regexp : /^(\d*\.)?\d+$/,
											message : '金额为浮点数如100.0或100'
										},
										callback : {
											message : '金额高值不能低于低值',
											callback : function(value,
													validator) {
												var mincfees = $(
														"#minMoneyAward").val(), sum = parseFloat(mincfees);
												return value >= sum;
											}
										}
									}
								},
							}
						});
		/* $("#failButton").click(function() {
			$('#defaultForm').bootstrapValidator('validate');
		}); */
	});
	//利息补偿方式添加金额段
	$(document).ready(
					function() {
						var MaxInputs = 15; //maximum input boxes allowed  
						var InputsWrapper = $("#InputsWrapperone"); //Input boxes wrapper ID  
						var AddButton = $("#AddMoreFileBoxone"); //Add button ID 
						var x = InputsWrapper.length; //initlal text box count  
						var FieldCount = 1; //to keep track of text box added  
						
						/* 加载页面后把定额和百分比的div隐藏 */
						$(AddButton).click(
										function(e){
											if (x <= MaxInputs){
												FieldCount++; //text box added increment  
												var oDiv = $('<div class="wapperclass"></div>');
												oDiv.html('<div class="form-group">'
																+ '<label for="inputminMoney" class="col-sm-3 control-label">投标累计金额最低-最高</label>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" readonly="readonly" name="failTCompensates[' + x + '].minmoney" id="inputminMoney" placeholder="投标累计金额最低" class="form-control" />'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" readonly="readonly" value="999999999999"name="failTCompensates[' + x + '].maxmoney" id="inputmaxMoney" placeholder="投标累计金额最高" class="form-control maxMoney" />'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-1">'
																+ '<span><button name="removebutton" id="removebuttonone'+x+'" class="btn btn-default removeclassone"><strong>-</strong></button></span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="form-group">'
																+ '<label class="col-sm-3 control-label">类型</label>'
																+ '<div class="col-sm-3">'
																+ '<select name="type" id="type'+x+'" class="form-control">'
																+ '<option value="">请选择</option>'
																+ '<option value="iequota'+x+'">定额</option>'
																+ '<option value="iepercent'+x+'">百分比</option>'
																+ '</select>'
																+ '</div>'
																+ '</div>'
																+ '<div class="form-group" id="quotainput_div'+x+'">'
																+ '<label class="col-sm-3 control-label" for="inputquota">定额补偿金</label>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" name="failTCompensates[' + x + '].quota" id="inputquota" class="form-control" placeholder="定额补偿金"/>'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '</div>'
																+ '<div class="form-group" id="dayawardrate_div'+x+'">'
																+ '<label class="col-sm-3 control-label">日奖励费率-最高补偿金</label>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" name="failTCompensates[' + x + '].dayawardrate" id="dayAwardRate" class="form-control" placeholder="日奖励费率"/>'
																+ '<span class="input-group-addon">%</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" name="failTCompensates[' + x + '].maxcompensate" id="maxCompensate" class="form-control" placeholder="最高补偿金额"/>'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '</div>');
												$(InputsWrapper).append(oDiv);
												var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
												var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);//获取添加过后第二个输入框
												lastBeforeInput.attr("readonly",false);
												lastBeforeInput.val("");
										
												$('form').bootstrapValidator('addField', 'type', {
													validators: {
														notEmpty: {
															message: '不能为空'
														}
													}
												});
												//判断最大金额
												$('form').data('bootstrapValidator').addField('failTCompensates['+ x + '].maxmoney',{
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
												$('form').data('bootstrapValidator').addField('failTCompensates['+ x + '].quota',{
																	validators : {
																		notEmpty : {
																			message : '定额不能为空'
																		},
																		regexp : { //匹配规则
																			regexp : /^(\d*\.)?\d+$/,
																			message : '定额补偿金必须为浮点数如:100或100.0'
																		}
																	}
																});
												//验证费率
												$('form').data('bootstrapValidator').addField('failTCompensates['+ x+ '].dayawardrate',{
																	validators : {
																		notEmpty : {
																			message : '费率不能为空'
																		},
																		regexp : { //匹配规则
																			regexp : /^(\d*\.)?\d+$/,
																			message : '费率必须为小数如:1或1.0'
																		}
																	}
																});
												//验证最高补偿金额
												$('form').data('bootstrapValidator').addField('failTCompensates['+ x+ '].maxcompensate',{
																	validators : {
																		notEmpty : {
																			message : '最高补偿金额不能为空'
																		},
																		regexp : { //匹配规则
																			regexp : /^(\d*\.)?\d+$/,
																			message : '金额必须为小数如:100或100.0'
																		}
																	}
																});

												$("#quotainput_div" + x).hide(); //影藏定额
												$("#dayawardrate_div" + x).hide(); //影藏日奖励费率
												x++;
											}
											if (x == MaxInputs) {
												$(this).attr("disabled","disabled");
												return true;
											}
											return false;
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

						$("body").on("keyup", ".maxMoney", function(e) { //user click on remove text
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
					});

</script>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyph-icon icon-chevron-right"></i>
    <a href="#">标的流标补偿设置页面</a>
</div>
	<div class="container" style="margin-top: 25px;">
		<form class="form-horizontal" role="form" id="defaultForm" action="${pageContext.request.contextPath}/admin/failTCompensate/insertFailTCompensate.action">
			<!--投标等级-->
			<div id="CompensateOn_div">
				<div class="form-group">
					<input type="hidden" name="tid" value="${tid}">
					<label class="col-sm-3 control-label">投标人等级</label>
					<div class="col-sm-3">
						<c:if test="${isPart==0}">
							<label class="radio-inline"> <input type="radio"
																name="intugrade" id="intUGradeone" value="1"
																class="insert-ugrade-radio" />全部等级
							</label>
						</c:if>

						<label class="radio-inline"> <input type="radio"
							name="intugrade" id="intUGradetwo" value="2"
							class="insert-ugrade-radio" />部分等级
						</label>
					</div>
				</div>
				<!--会员等级-->
				<div class="form-group" id="intugrades_div">
					<label class="col-sm-3 control-label"></label>
					<label class="radio-inline" >
						<input type="checkbox" name="intugrades" id="selectAll"  />全选
					</label>
					<div class="col-sm-6">
						<c:if test="${!empty uGrades}">
							<c:forEach var="ugs" items="${uGrades}" varStatus="status">
								<label class="checkbox-inline" style="width: 110px;"> <input
									type="checkbox" name="intugrades" id="intugrades"
									value="${ugs.ugrade}" />${ugs.ugradename}
								</label>
								<c:if test="${status.count%4==0}">
									<br>
								</c:if>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<!--投标累计金额分段最低与最高-->
				<div id="InputsWrapperone">
					<div class="wapperclass">
					<div class="form-group">
						<label for="inputminMoney" class="col-sm-3 control-label">投标累计金额最低-最高</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" value="0" readonly="readonly" name="failTCompensates[0].minmoney" id="inputminMoney"
									placeholder="投标累计金额最低" class="form-control" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" value="999999999999" readonly="readonly" name="failTCompensates[0].maxmoney" id="inputmaxMoney"
									placeholder="投标累计金额最高" class="form-control maxMoney" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button name="addmorefileboxone"
									id="AddMoreFileBoxone" class="btn btn-default">
									<strong>+</strong>
								</button></span>
						</div>
					</div>
					<!--补偿类型-->
					<div class="form-group">
						<label class="col-sm-3 control-label">类型</label>
						<div class="col-sm-3">
							<select name="type" id="type" class="form-control">
								<option value="">请选择</option>
								<option value="iequota">定额</option>
								<option value="iepercent">百分比</option>
							</select>
						</div>
					</div>
					<!--定额补偿金-->
					<div class="form-group" id="quotainput_div">
						<label class="col-sm-3 control-label" for="inputquota">定额补偿金</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[0].quota" id="inputquota"
									class="form-control" placeholder="定额补偿金" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<!--日奖励费率 +++最高补偿金-->
					<div class="form-group" id="dayawardrate_div">
						<label class="col-sm-3 control-label" for="dayAwardRate">日奖励费率-最高补偿金</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[0].dayawardrate" id="dayAwardRate"
									class="form-control" placeholder="日奖励费率" /> <span
									class="input-group-addon">%</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[0].maxcompensate" id="maxCompensate"
									class="form-control" placeholder="最高补偿金额" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
			<!--资金清算是否需要审核-->
			<div class="form-group">
				<label class="col-sm-3 control-label">资金清算是否需要审核</label>
				<div class="col-sm-3">
					<select name="isaudit" id="isAudit" class="form-control">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
			<!--是否为模板-->
			<div class="form-group">
				<label class="control-label col-sm-3" for="istemplet">是否为模板</label>
				<div class="col-sm-3">
					<select name="istemplet" class="form-control" id="istemplet">
						<option value="">请选择</option>
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
					<button type="submit" class="btn btn-default" id="failButton">保存</button>
				</div>
				<div class="col-sm-1">
					<button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	  function conblck(){
		  var url="${pageContext.request.contextPath}/admin/failTCompensate/selectFailTCompensateByCondition.action";
		  window.location.href=url;
	  }
	</script>
</body>
</html>