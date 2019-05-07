<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>GfundsInt_inst</title>
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
	<style type="text/css">
		*{margin: 0px;padding: 0px;}
		.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; }
		.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; }
		.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; }
		.route_bg a:hover{ color: #888; text-decoration: none;}
	</style>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的站岗利息设置页面</a>
</div>
<form class="form-horizontal" role="form" id="gfundsIntForm" action="${pageContext.request.contextPath }/admin/gfundsInt/insertGfundsInttwo.action"  method="post">
	<div class="container" style="margin-top: 25px;" >
		<input type="hidden" name="tid" value="${tid}">
		<!--站岗利息清算方式-->
		<div class="form-group">
			<label for="clearmethod" class="col-sm-3 control-label">站岗利息清算方式</label>
			<div class="col-sm-3">
				<select name="clearmethod" id="clearmethod" class="form-control">
					<option value="1">结标清算</option>
				</select>
			</div>
		</div>
		<!--投标人等级-->
		<div class="form-group">
			<label for="ugradeone" class="col-sm-3 control-label">投标人等级</label>
			<div class="col-sm-3">
				<c:if test="${isPart==0}">
					<label class="radio-inline">
						<input type="radio" name="ugrade" id="ugradeone" value="1"  class="ugrade_radio"/>全部等级
					</label>
				</c:if>
				<label class="radio-inline" >
					<input type="radio" name="ugrade" id="ugradetwo" value="2"  class="ugrade_radio"/>选择等级
				</label>

			</div>
		</div>
		<!--允许投标的会员等级-->
		<div id="ugrades_div">

			<div class="form-group"  >
				<label class="radio-inline" >
					<input type="checkbox" name="ugrades" id="selectAll" />全选
				</label>
				<label for="ugrestricts" class="col-sm-3 control-label"></label>
				<div class="col-sm-6" id="ugrestrict_div" >
					<c:if test="${!empty uGrades }">
						<c:forEach items="${uGrades }" var="ugr" varStatus="status">
							<label class="checkbox-inline"  style="width:80px;">
								<input type="checkbox" id="ugrestricts"  name="ugrades" value="${ugr.ugrade}" />${ugr.ugradename}
							</label>
							<c:if test="${status.count%4==0 }">
								<br />
							</c:if>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
		<!--分段投资最低与最高金额-->
		<div id="InputsWrapper">
			<div class="wapperclass">
				<div class="form-group">
					<label for="inputminmoney0" class="col-sm-3 control-label">分段投资金额最低与最高</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" readOnly="readonly" value="0" name="gfundsInts[0].minmoney" id="inputminmoney0" placeholder="分段投资金额最低" class="form-control minMoney"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" readOnly="readonly" value="999999999999" name="gfundsInts[0].maxmoney" id="inputmaxmoney0" placeholder="分段投资金额最高" class="form-control maxMoney"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-1">
						<span><button id="AddMoreFileBox" class="btn btn-default"><strong>+</strong></button></span>
					</div>
				</div>
				<!--站岗利息补偿方式-->
				<div class="form-group">
					<label class="col-sm-3 control-label">站岗利息补偿方式</label>
					<div class="col-sm-3">
						<select name="type"  id="type" class="form-control">
							<option value="">请选择</option>
							<option value="iequota">定额补偿</option>
							<option value="iepercent">日奖费率</option>
						</select>
					</div>
				</div>
				<!--定额-->
				<div class="form-group"  id="quotainput_div">
					<label class="col-sm-3 control-label" for="quotainput">定额补偿金</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="gfundsInts[0].quota" class="form-control" id="quotainput" placeholder="定额补偿金" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--日奖费率-->
				<div id="dayawardrate_div">
					<div class="form-group">
						<label class="control-label col-sm-3" for="dayawardrate">日奖励费率</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="gfundsInts[0].dayawardrate" id="dayawardrate" placeholder="日奖励费率" class="form-control"/>
								<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<!--最高补偿金额-->
					<div class="form-group" >
						<label class="control-label col-sm-3" for="maxcompensate">最高补偿金额</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="gfundsInts[0].maxcompensate" id="maxcompensate" placeholder="最高补偿金额" class="form-control"/>
								<span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--资金清算是否需要审核-->
		<div class="form-group">
			<label class="control-label col-sm-3" for="isaudit">资金清算是否需要审核</label>
			<div class="col-sm-3">
				<select name="isaudit" class="form-control" id="isaudit">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
		<!--是否为模板-->
	<%--	<div class="form-group">
			<label class="control-label col-sm-3" for="istemplet">是否为模板</label>
			<div class="col-sm-3">
				<select name="istemplet" class="form-control" id="istemplet">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>--%>
		<!-- 备注 -->
		<div class="form-group">
			<label class="control-label col-sm-3">备注</label>
			<div class="col-sm-3">
				<textarea rows="3" name="remark" class="form-control"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-1">
				<button type="submit" class="btn btn-primary" >保存</button>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-success" onclick="javascript:;history.go(-1)">返回列表</button>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">

	$(document).ready(function() {
		var MaxInputs = 19; //maximum input boxes allowed
		var InputsWrapper = $("#InputsWrapper"); //Input boxes wrapper ID
		var AddButton = $("#AddMoreFileBox"); //Add button ID

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
						'<label for="inputminmoney" class="col-sm-3 control-label">分段投资金额最低与最高</label>' +
						'<div class="col-sm-3">' +
						'<div class="input-group">' +
						'<input type="text" readOnly="true" name="gfundsInts[' + x + '].minmoney" id="inputminmoney'+x+'" placeholder="分段投资金额最低" class="form-control required minMoney" />' +
						'<span class="input-group-addon">元</span>' +
						'</div>' +
						'</div>' +
						'<div class="col-sm-3">' +
						'<div class="input-group">' +
						'<input type="text" readOnly="true" value="999999999999" name="gfundsInts[' + x + '].maxmoney" id="inputmaxmoney'+x+'" placeholder="分段投资金额最高" class="form-control maxMoney" />' +
						'<span class="input-group-addon">元</span>' +
						'</div></div>' +
						'<div class="col-sm-1">' +
						'<span><button id="removeButton" class="btn btn-default removeclass" name="option[]"><strong>-</strong></button></span>' +
						'</div>' +
						'</div>' +
						'<div class="form-group">' +
						'<label for="inputloantime" class="col-sm-3 control-label">站岗利息补偿方式</label>' +
						'<div class="col-sm-3">' +
						'<select name="type" id="type' + x + '" class="form-control type">' +
						'<option value="">请选择</option>' +
						'<option value="iequota' + x + '">定额补偿</option>' +
						'<option value="iepercent' + x + '">日奖费率</option>' +
						'</select>' +
						'</div>' +
						'</div>' +
						'<div class="form-group" id="quotainput_div' + x + '">' +
						'<label class="col-sm-3 control-label" for="">定额补偿金</label>' +
						'<div class="col-sm-3">' +
						'<div class="input-group"> '+
						'<input type="text" name="gfundsInts[' + x + '].quota" class="form-control" id="quotainput" placeholder="定额补偿金" />' +
						'<span class="input-group-addon">元</span>' +
						'</div>'+
						'</div>' +
						'</div>' +
						'<div id="dayawardrate_div' + x + '">' +
						'<div class="form-group">' +
						'<label class="control-label col-sm-3" for="dayawardrate">日奖励费率</label>' +
						'<div class="col-sm-3">' +
						'<div class="input-group">' +
						'<input type="text" name="gfundsInts[' + x + '].dayawardrate" id="dayawardrate" placeholder="日奖励费率" class="form-control" />' +
						'<span class="input-group-addon">%</span>' +
						'</div>' +
						'</div>' +
						'</div>' +
						'<div class="form-group">' +
						'<label class="control-label col-sm-3" for="maxcompensate">最高补偿金额</label>' +
						'<div class="col-sm-3">' +
						'<div class="input-group">'+
						'<input type="text" name="gfundsInts[' + x + '].maxcompensate" id="maxcompensate" placeholder="最高补偿金额" class="form-control" />' +
						'<span class="input-group-addon">元</span>'+
						'</div>'+
						'</div>' +
						'</div>' +
						'</div>'
				);
				$(InputsWrapper).append(oDiv);
				var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
				var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);
				lastBeforeInput.attr("readonly",false);
				lastBeforeInput.val("");
//				$('form').bootstrapValidator('addField', 'gfundsInts[' + x + '].minmoney', {
//					validators: {
//						trigger:"change",
//						notEmpty: {
//							message: '最低金额不能为空'
//						},
//						regexp: { //匹配规则
//							regexp: /^(\d*\.)?\d+$/,
//							message: '投资最低金额必须为浮点数如:100或100.0'
//						}
//					}
//				});

				$('form').bootstrapValidator('addField', 'type', {
					validators: {
						notEmpty: {
							message: '不能为空'
						}
					}
				});

				$('form').data('bootstrapValidator').addField('gfundsInts[' + x + '].maxmoney', {
					validators: {
						notEmpty: {
							message: '最高金额不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '投资最高金额必须为浮点数如:100或100.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('gfundsInts[' + x + '].quota', {
					validators: {
						notEmpty: {
							message: '定额补偿金不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '定额补偿金必须为浮点数如:100或100.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('gfundsInts[' + x + '].dayawardrate', {
					validators: {
						notEmpty: {
							message: '日奖励费率不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '日奖励费率必须为小数如:1或1.0'
						}
					}
				});
				$('form').data('bootstrapValidator').addField('gfundsInts[' + x + '].maxcompensate', {
					validators: {
						notEmpty: {
							message: '最高金额不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '最高补偿金额必须为小数如:100或100.0'
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

		//删除
		$("body").on("click", ".removeclass", function(e) { //user click on remove text
			var $row = $(this).parents('.wapperclass'),
					$option = $row.find('[name="option[]"]');
				var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
				var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
				ntInput.val(bfInput.val());
			$row.remove();
			x--;
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


		//选择类型
		$("body").on("change", "#type1", function(e) {
			if($(this).val() == ("iequota" + 1)) {
				$("#dayawardrate_div" + 1 + " " + "input").val("");
				$("#dayawardrate_div" + 1).hide();
				$("#quotainput_div" + 1).show();
			} else if($(this).val() == ("iepercent" + 1)) {
				$("#dayawardrate_div" + 1).show();
				$("#quotainput_div" + 1 + " " + "input").val("");
				$("#quotainput_div" + 1).hide();
			} else {
				$("#quotainput_div" + 1).hide();
				$("#dayawardrate_div" + 1).hide();
				$("#quotainput_div" + 1 + " " + "input").val("");
				$("#dayawardrate_div" + 1 + " " + "input").val("");
			}
		});

		$("body").on("change", "#type2", function(e) {
			if($(this).val() == ("iequota" + 2)) {
				$("#dayawardrate_div" + 2 + " " + "input").val("");
				$("#dayawardrate_div" + 2).hide();
				$("#quotainput_div" + 2).show();
			} else if($(this).val() == ("iepercent" + 2)) {
				$("#dayawardrate_div" + 2).show();
				$("#quotainput_div" + 2 + " " + "input").val("");
				$("#quotainput_div" + 2).hide();
			} else {
				$("#quotainput_div" + 2).hide();
				$("#dayawardrate_div" + 2).hide();
				$("#quotainput_div" + 2 + " " + "input").val("");
				$("#dayawardrate_div" + 2 + " " + "input").val("");
			}
		});
	});

	$("#ugradeone").attr("checked","checked");//默认选择全部等级
	$("#ugrades_div").hide();//影藏全部等级
	$("#quotainput_div").hide();//影藏定额
	$("#dayawardrate_div").hide();//影藏日奖励费率
	$(".ugrade_radio").change(function(){
		var $radioval=$(".ugrade_radio:checked").val();
		if($radioval==1){
			$("#ugrades_div").hide();

		}else{
			$("#ugrades_div").show();
		}
	});
	$("#selectAll").click(function () {
		if(this.checked){
			$("#ugrestrict_div  :checkbox").prop("checked",true);
		}else{
			$("#ugrestrict_div  :checkbox").prop("checked",false);
		}
	})
	//站岗利息补偿方式
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
	$(function(){
		$('form').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: {/*input状态样式图片*/
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields:{
				ugrade:{
					validators : {
						notEmpty : {
							message : '不能为空'
						}
					}
				},
				ugrades:{
						validators : {
							notEmpty : {
								message : '不能为空'
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
				isaudit:{
					validators : {
						notEmpty : {
							message : '不能为空'
						}
					}
				},
				'gfundsInts[0].minmoney':{//投资最低金额
					trigger:"change",
					validators:{
						notEmpty:{
							message:'投资最低金额不能空'
						},
						regexp:{//匹配规则
							regexp:/^(\d*\.)?\d+$/,
							message:'投资最低金额必须为浮点数如:100或100.0'
						}
					}
				},
				'gfundsInts[0].maxmoney':{//投资最高金额
					validators:{
						notEmpty:{
							message:'投资最高金额不能空'
						},
						regexp:{//匹配规则
							regexp:/^(\d*\.)?\d+$/,
							message:'投资最高金额必须为浮点数如:100或100.0'
						}
					}
				},
				'gfundsInts[0].quota':{ //定额补偿金
					validators:{
						notEmpty:{
							message:'定额补偿金不能空'
						},
						regexp:{//匹配规则
							regexp:/^(\d*\.)?\d+$/,
							message:'定额补偿金必须为浮点数如:100或100.0'
						}
					}
				},
				'gfundsInts[0].dayawardrate':{//日奖励费率
					validators:{
						notEmpty:{
							message:'日奖励费率不能空'
						},
						regexp:{//匹配规则
							regexp:/^(\d*\.)?\d+$/,
							message:'日奖励费率必须为小数如:100或100.0'
						}
					}
				},
				'gfundsInts[0].maxcompensate':{//最高补偿金额
					validators:{
						notEmpty:{
							message:'最高补偿金额不能为空'
						},
						regexp:{//匹配规则
							regexp:/^(\d*\.)?\d+$/,
							message:'最高补偿金额必须为小数如:100或100.0'
						}
					}
				}
			}
		});
	});
</script>
</body>
</html>