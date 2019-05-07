<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AheadRepayPlatform_Inst</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
<script src="${pageContext.request.contextPath }/js/tenderitem/changetwo.js"></script>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的提前还款奖励平台设置</a>
</div>
           <div class="container" style="margin-top: 25px;">
			 <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/admin/aheadRepay/insertAheadRepayPlatform.action">
                <!--还款人补偿平台开关-->
				<!-- <div class="form-group">
					<label class="col-sm-3 control-label" for="inputisForPlatformOn">还款人补偿平台开关</label>
					<div class="col-sm-3">
						<select name="isforplatformon" id="inputisForPlatformOn" class="form-control">
							<option value="">请选择</option>
							<option value="1">补偿</option>
							<option value="0">不补偿</option>
						</select>
					</div>
				</div> -->
				<!--平台收款人-->
				 <input type="hidden" name="tid" value="${tid}">
				<div id="InputsWrapperthr" >
					<div class="form-group">
						<label class="col-sm-3 control-label" for="AwardRecMan">平台收款人</label>
						<div class="col-sm-3">
							<input type="text" name="awardrecman" id="AwardRecMan" placeholder="平台收款人" class="form-control" value="MDT000001" readonly/>
						</div>
					</div>
					<div class="wapperclass">
					<!--投资人总欠收利息-->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="minAllNoReceiveInt">投资人总欠收利息低值-高值</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" readonly="readonly" value="0" name="platforms[0].minallnoreceiveint" id="minAllNoReceiveInt" placeholder="投资人总欠收利息低值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" readonly="readonly" value="999999999999" name="platforms[0].maxallnoreceiveint" id="maxAllNoReceiveInt" placeholder="投资人总欠收利息高值" class="form-control maxMoney" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="AddMoreFileBoxthr" class="btn btn-default"><strong>+</strong></button></span>
						</div>
					</div>
					<!--奖励平台类型选择-->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="typefour">奖励平台类型</label>
						<div class="col-sm-3">
							<select name="type" id="type" class="form-control">
								<option value="">请选择</option>
								<option value="iequota">定额</option>
								<option value="iepercent">百分比</option>
							</select>
						</div>
					</div>
					<!--奖励平台定额-->
					<div class="form-group" id="quotainput_div">
						<label class="col-sm-3 control-label" for="AwardPlatQuota">奖励平台定额</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="platforms[0].awardplatquota" id="AwardPlatQuota" placeholder="奖励平台定额" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<!--奖励平台百分比-->
					<div id="dayawardrate_div">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="AwardPlatRate">奖励平台百分比</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="platforms[0].awardplatrate" id="AwardPlatRate" placeholder="奖励平台百分比" class="form-control" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						<!--奖励平台最大值-最小值-->
						<div class="form-group">
							<label class="col-sm-3 control-label" for="AwardPlatMinMoney">奖励平台最小值-最大值</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="platforms[0].awardplatminmoney" id="AwardPlatMinMoney" placeholder="奖励平台最小值" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="platforms[0].awardplatmaxmoney" id="AwardPlatMaxMoney" placeholder="奖励平台最大值" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
					</div>
				</div>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label" for="isAudit">资金清算是否需要审核</label>
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
						<button type="submit" class="btn btn-primary" id="aheButton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
					</div>
				</div>
				</form>
				</div>
<script type="text/javascript">
//补偿平台
$(document).ready(function() {
	var MaxInputs = 15; //maximum input boxes allowed  
	var InputsWrapper = $("#InputsWrapperthr"); //Input boxes wrapper ID  
	var AddButton = $("#AddMoreFileBoxthr"); //Add button ID 

	var x = InputsWrapper.length; //initlal text box count  
	var FieldCount = 1; //to keep track of text box added  
	/* 加载页面后把定额和百分比的div隐藏 */
	$(AddButton).click(function(e) //on add input button click  
		{
			if(x <= MaxInputs) //max input box allowed  
			{
				FieldCount++; //text box added increment  
				var oDiv = $('<div class="wapperclass"></div>');
				oDiv.html('<div class="form-group">' +
					'<label class="col-sm-3 control-label" for="minAllNoReceiveInt">投资人总欠收利息低值-高值</label>' +
					'<div class="col-sm-3">' +
					'<div class="input-group">' +
					'<input type="text" readonly="readonly" name="platforms[' + x + '].minallnoreceiveint" id="minAllNoReceiveInt" placeholder="投资人总欠收利息低值" class="form-control" />' +
					'<span class="input-group-addon">元</span>' +
					'</div>' +
					'</div>' +
					'<div class="col-sm-3">' +
					'<div class="input-group">' +
					'<input type="text" readonly="readonly" value="999999999999" name="platforms[' + x + '].maxallnoreceiveint" id="maxAllNoReceiveInt" placeholder="投资人总欠收利息高值" class="form-control maxMoney" />' +
					'<span class="input-group-addon">元</span>' +
					'</div>' +
					'</div>' +
					'<div class="col-sm-1">' +
					'<span><button id="removeButtonthr" class="btn btn-default removeclassthr"><strong>-</strong></button></span>' +
					'</div>' +
					'</div>' +
					'<div class="form-group">' +
					'<label class="col-sm-3 control-label" for="typefour">奖励平台类型</label>' +
					'<div class="col-sm-3">' +
					'<select name="type" id="type' + x + '" class="form-control">' +
					'<option value="">请选择</option>' +
					'<option value="iequota' + x + '">定额</option>' +
					'<option value="iepercent' + x + '">百分比</option>' +
					'</select>' +
					'</div>' +
					'</div>' +
					'<div class="form-group" id="quotainput_div' + x + '">' +
					'<label class="col-sm-3 control-label" for="AwardPlatQuota">奖励平台定额</label>' +
					'<div class="col-sm-3">' +
					'<div class="input-group">' +
					'<input type="text" name="platforms[' + x + '].awardplatquota" id="AwardPlatQuota" placeholder="奖励平台定额" class="form-control" />' +
					'<span class="input-group-addon">元</span>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<div id="dayawardrate_div' + x + '">' +
					'<div class="form-group">' +
					'<label class="col-sm-3 control-label" for="AwardPlatRate">奖励平台百分比</label>' +
					'<div class="col-sm-3">' +
					'<div class="input-group">' +
					'<input type="text" name="platforms[' + x + '].awardplatrate" id="AwardPlatRate" placeholder="奖励平台百分比" class="form-control" />' +
					'<span class="input-group-addon">%</span>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'<div class="form-group">' +
					'<label class="col-sm-3 control-label" for="AwardPlatMinMoney">奖励平台最小值-最大值</label>' +
					'<div class="col-sm-3">' +
					'<div class="input-group">' +
					'<input type="text" name="platforms[' + x + '].awardplatminmoney" id="AwardPlatMinMoney" placeholder="奖励平台最小值" class="form-control" />' +
					'<span class="input-group-addon">元</span>' +
					'</div>' +
					'</div>' +
					'<div class="col-sm-3">' +
					'<div class="input-group">' +
					'<input type="text" name="platforms[' + x + '].awardplatmaxmoney" id="AwardPlatMaxMoney" placeholder="奖励平台最大值" class="form-control" />' +
					'<span class="input-group-addon">元</span>' +
					'</div>' +
					'</div>' +
					'</div>' +
					'</div>'
				);
				$(InputsWrapper).append(oDiv);
				var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
				var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);
				lastBeforeInput.attr("readonly",false);
				lastBeforeInput.val("");
				$('form').bootstrapValidator('addField', 'AheadRepay[' + x + '].minallnoreceiveint', {
					validators: {
						notEmpty: {
							message: '总欠收利息低值不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '金额必须为浮点数如:100或100.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('AheadRepay[' + x + '].maxallnoreceiveint', {
					validators: {
						notEmpty: {
							message: '总欠收利息高值不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '金额必须为浮点数如:100或100.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('AheadRepay[' + x + '].awardplatquota', {
					validators: {
						notEmpty: {
							message: '定额不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '定额补偿金必须为浮点数如:100或100.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('AheadRepay[' + x + '].awardplatrate', {
					validators: {
						notEmpty: {
							message: '奖励平台百分比不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '百分比必须为小数如:1或1.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('AheadRepay[' + x + '].awardplatminmoney', {
					validators: {
						notEmpty: {
							message: '奖励平台最小值不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '金额必须为浮点数如:100或100.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('AheadRepay[' + x + '].awardplatmaxmoney', {
					validators: {
						notEmpty: {
							message: '奖励平台最大值不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '金额必须为浮点数数如:100或100.0'
						}
					}
				});
				$("#quotainput_div" + x).hide(); //影藏定额
				$("#dayawardrate_div" + x).hide(); //影藏日奖励费率
				x++;
			}
			if(x == MaxInputs) {
				$(this).attr("disabled", "disabled");
				return true;
			}
			return false;
		});
	//刪除標籤	
	$("body").on("click", ".removeclassthr", function(e) { //user click on remove text  
		var $row = $(this).parents('.wapperclass'),
			$option = $row.find('[name="option[]"]');
		var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
		var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
		ntInput.val(bfInput.val());
		$row.remove();
		var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
		lastInput.val("999999999999");
		lastInput.attr("readonly",true);
		$('form').bootstrapValidator('removeField', $option);
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
   $(function(){
		$("#dayawardrate_div").hide(); //百分比隐藏
		$("#quotainput_div").hide(); //定额隐藏
		//$("#InputsWrapperthr").hide();
	 //补偿平台(开关)
		/* $("#inputisForPlatformOn").change(function() {
			var monval = $(this).val();
			if(monval == 1) {
				$("#InputsWrapperthr").show();
			} else {
				$("#InputsWrapperthr input").val("");
				$("#InputsWrapperthr select").val("");
				$("#InputsWrapperthr").hide();
			}
		}); */
		//类型选择
		$("#type").change(function() {
			if($(this).val() == "iequota") {
				$("#dayawardrate_div input").val("");
				$("#dayawardrate_div").hide();
				$("#quotainput_div").show();
			} else if($(this).val() == "iepercent") {
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
   })
   
</script>
</body>
</html>