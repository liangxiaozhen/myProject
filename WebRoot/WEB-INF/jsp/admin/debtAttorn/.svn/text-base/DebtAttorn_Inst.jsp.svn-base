<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>debtAttorn_insert</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
<script src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<style type="text/css">
	.laber_from {
		color: #222;
		font-weight: normal;
		}
</style>
	<script type="text/javascript">
		function backTest(){
		   window.history.go(-1);
		}

	</script>
	<script type="text/javascript">
	$(function() {
		$("#aownergrade_div").hide(); //允许转让人的等级
 		$("#aownergradeone").attr("checked", "checked");
		 
		//转让人全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio-aownergrade").change(function() {
			var $radioVal = $(".insert-ugrade-radio-aownergrade:checked").val();
			if($radioVal == 1) {
				$("#aownergrade_div").hide();
				$("#aownergrade_div :checkbox").each(function() {
					this.checked = false;
				});
			} else {
				$("#aownergrade_div").show();
			}
		});

		//收费类型
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
		
		$("#DATimesci").hide();
		$("#DATimesceng").hide();
        //债转次数方式限制
        $("#DATTRestrict").change(function(){
        	if($(this).val()==1){//层级次数
        		$("#DATimesceng").show();
        		$("#DATimesci").hide();
        		$("#DATimesci input").val("");
        	}else if($(this).val()==2){//每人次数
        		$("#DATimesci").show();
        		$("#DATimesceng").hide();
        		$("#DATimesceng input").val("");
        	}
        });
	});
			////用户选择全部购买
			/* $(document).ready(function() {
				var MaxInputs = 15; //maximum input boxes allowed  
				var InputsWrapper = $("#isADAFeeOn_div"); //Input boxes wrapper ID  
				var AddButton = $("#AddMoreFileBox"); //Add button ID 

				var x = InputsWrapper.length; //initlal text box count  
				var FieldCount = 1; //to keep track of text box added  
 */
				/* 加载页面后把定额和百分比的div隐藏 */
			/* 	$(AddButton).click(function(e) //on add input button click  
					{
						if(x <= MaxInputs) //max input box allowed  
						{
							FieldCount++; //text box added increment  
							var oDiv = $('<div class="wapperclass"></div>');
							oDiv.html('<div class="form-group">' +
								'<label class="control-label col-sm-3 laber_from" for="minAttornMoney">债转金额段</label>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttorns[' + x + '].minattornmoney" id="minAttornMoney" placeholder="债转金额段低值" class="form-control" />' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttorns[' + x + '].maxattornmoney" id="maxAttornMoney" placeholder="债转金额段高值" class="form-control" />' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div>' +
								'<div class="col-sm-1">' +
								'<span><button id="removeButton" class="btn btn-default removeclass"><strong>-</strong></button></span>' +
								'</div>' +
								'</div>' +
								'<div class="form-group">' +
								'<label class="control-label col-sm-3 laber_from">收费类型</label>' +
								'<div class="col-sm-3">' +
								'<select id="type' + x + '" name="type" class="form-control">' +
								'<option value="">请选择</option>' +
								'<option value="iequota' + x + '">定额</option>' +
								'<option value="iepercent' + x + '">百份比</option>' +
								'</select>' +
								'</div>' +
								'</div>' +
								'<div class="form-group" id="quotainput_div' + x + '">' +
								'<label class="control-label col-sm-3 laber_from" for="inputquota">定额</label>' +
								'<div class="col-sm-3">' +
								'<input type="text" name="debtAttorns[' + x + '].quota" id="inputquota" placeholder="手续费定额" class="form-control">' +
								'</div>' +
								'</div>' +
								'<div id="dayawardrate_div' + x + '">' +
								'<div class="form-group">' +
								'<label class="control-label col-sm-3 laber_from" for="inputattornrate">百分比</label>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttorns[' + x + '].attornrate" id="inputattornrate" placeholder="百分比" class="form-control">' +
								'<span class="input-group-addon">%</span>' +
								'</div>' +
								'</div>' +
								'</div>' +
								'<div class="form-group">' +
								'<label class="control-label col-sm-3 laber_from" for="inputoccquota">手续费最低-最高</label>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttorns[' + x + '].minfee" id="inputminFee " placeholder="手续费最低值" class="form-control">' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttorns[' + x + '].maxfee" id="inputmaxFee" placeholder="手续费最高值" class="form-control">' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div></div></div>'
							);
							$(InputsWrapper).append(oDiv);
							$('form').bootstrapValidator('addField', 'debtAttorns[' + x + '].minattornmoney', {
								validators: {
									notEmpty: {
										message: '最低金额不能为空'
									},
									regexp: { //匹配规则
										regexp: /^(\d*\.)?\d+$/,
										message: '金额必须为浮点数如:100或100.0'
									}
								}
							});
							$('form').data('bootstrapValidator').addField('debtAttorns[' + x + '].maxattornmoney', {
								validators: {
									notEmpty: {
										message: '最高金额不能为空'
									},
									regexp: { //匹配规则
										regexp: /^(\d*\.)?\d+$/,
										message: '金额必须为浮点数如:100或100.0'
									}
								}
							});
							$('form').data('bootstrapValidator').addField('debtAttorns[' + x + '].quota', {
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
							$('form').data('bootstrapValidator').addField('debtAttorns[' + x + '].attornrate', {
								validators: {
									notEmpty: {
										message: '追偿收费费率不能为空'
									},
									regexp: { //匹配规则
										regexp: /^(\d*\.)?\d+$/,
										message: '费率必须为小数如:1或1.0'
									}
								}
							});
							$('form').data('bootstrapValidator').addField('debtAttorns[' + x + '].minfee', {
								validators: {
									notEmpty: {
										message: '最低收费不能为空'
									},
									regexp: { //匹配规则
										regexp: /^(\d*\.)?\d+$/,
										message: '金额必须为小数如:100或100.0'
									}
								}
							});
							$('form').data('bootstrapValidator').addField('debtAttorns[' + x + '].maxfee', {
								validators: {
									notEmpty: {
										message: '最高收费不能为空'
									},
									regexp: { //匹配规则
										regexp: /^(\d*\.)?\d+$/,
										message: '金额必须为小数如:100或100.0'
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
				$("body").on("click", ".removeclass", function(e) { //user click on remove text  
					var $row = $(this).parents('.wapperclass'),
						$option = $row.find('[name="option[]"]');
					$row.remove();
					$('form').bootstrapValidator('removeField', $option);
				});
			}); */
			$(function() {
				$("#defaultForm").bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: { /*input状态样式图片*/
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: { //验证
						holdday: { //持有时间
							validators: {
								notEmpty: {
									message: '持有时间不能为空'
								},
								stringLength: {
									max: 5,
									message: '持有时间不能超过5位数'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '天数只能为正整数'
								}
							}
						},
						intervalday: { //距离下个还款日天数
							validators: {
								notEmpty: {
									message: '天数不能为空'
								},
								stringLength: {
									max: 5,
									message: '天数不能超过5位数'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '天数只能为正整数'
								}
							}
						},
						attornmoneylow: { //可转让金额低值
							validators: {
								notEmpty: {
									message: '金额不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								}
							}
						},
						attornmoney: { //可转让金额高值
							validators: {
								notEmpty: {
									message: '金额不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								},
								callback: {
									message: '高值不能低于低值',
									callback: function(value, validator) {
										var minLowint = $("#AttornMoneyLow").val(),
											sum = parseFloat(minLowint);
										return value >= sum;
									}
								}
							}
						},
						minattornratio: { ////转让系数低值(需限制在0-2之间)
							validators: {
								notEmpty: {
									message: '转让系数低值不能为空'
								},
								between:{
									min:0,
									max:2,
									message:'转让系数需在0~2之间设置'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '系数为浮点数如1.0或1'
								},
								between:{
									min:0,
									max:2,
									message:'转让系数需在0~2之间设置'
								},
								/* callback: {
									message: '系数高值不能低于低值',
									callback: function(value, validator) {
										var mintioint = $("#minAttornRatio").val(),
											sum = parseFloat(mintioint);
										return value >= sum;
									}
								} */
							}
						},
						maxattornratio: { //转让系数高值
							validators: {
								notEmpty: {
									message: '转让系数高值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '系数为浮点数如1.0或1'
								},
								between:{
									min:0,
									max:2,
									message:'转让系数需在0~2之间设置'
								},
								callback: {
									message: '系数高值不能低于低值',
									callback: function(value, validator) {
										var mintioint = $("#minAttornRatio").val(),
											sum = parseFloat(mintioint);
										return value >= sum;
									}
								}
							}
						},
						hadday: { //持有时间
							validators: {
								notEmpty: {
									message: '持有时间不能为空'
								},
								stringLength: {
									max: 5,
									message: '天数不能超过5位数'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '天数只能为正整数'
								}
							}
						},
						minattornmoney: { //债转金额低值
							validators: {
								notEmpty: {
									message: '金额低值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								}
							}
						},
						maxattornmoney: { //债转金额高值
							validators: {
								notEmpty: {
									message: '金额高值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								},
								callback: {
									message: '高值不能低于低值',
									callback: function(value, validator) {
										var minornint = $("#minAttornMoney").val(),
											sum = parseFloat(minornint);
										return value >= sum;
									}
								}
							}
						},
						quota: { //定额收费
							validators: {
								notEmpty: {
									message: '定额不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								}
							}
						},
						attornrate: { //债转金额百分比
							validators: {
								notEmpty: {
									message: '百分比不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '百分比为数字'
								}
							}
						},
						minfee: { //收取最低值
							validators: {
								notEmpty: {
									message: '最低值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								}
							}
						},
						maxfee: { //收取最高值
							validators: {
								notEmpty: {
									message: '最高值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								},
								callback: {
									message: '高值不能低于低值',
									callback: function(value, validator) {
										var minFee = $("#minFee").val(),
											sum = parseFloat(minFee);
										return value >= sum;
									}
								}
							}
						},
						aheadocday:{
							validators:{
								notEmpty:{
									message:"不能为空"
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '天数只能为正整数'
								}
							}
						},
						deadline:{
							validators:{
								notEmpty:{
									message:"不能为空"
								},
						        regexp:{
						        	regexp:/^(0|[1-9]\d*)$/,
						        	message:"天数只能为正数"
						        }
							}
						}
					}
				});
				/* $('#debtBtton').click(function() {
					$("#defaultForm").bootstrapValidator('validate');
				}); */
			});
		</script>
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
<%-- <%@ include file="DebtAttornBuyer_Inst.jsp" %>
<%@ include file="debtAttornFee_Inst.jsp" %> --%>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的债权转让设置页面</a>
</div>
<div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/debtAttorn/insertDebtAttorntwo.action" id="defaultForm" method="post">
				<!--债权转让审核-->
				<input type="hidden" name="tid" value="${tid}">
				<div class="form-group">
					<label for="isdebtaudit" class="col-sm-3 control-label laber_from">债权转让是否需要审核</label>
					<div class="col-sm-3">
						<select name="isdebtaudit" id="isdebtaudit" class="form-control">
							<option value="">请选择</option>
							<option value="1">需要</option>
							<option value="0">不需要</option>
						</select>
					</div>
				</div>
				<!--是否逾期债转-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isOCDebt">是否支持逾期债转</label>
					<div class="col-sm-3">
						<select name="isocdebt" id="isOCDebt" class="form-control">
							<option value="">请选择</option>
							<option value="1">支持</option>
							<option value="0">不支持</option>
						</select>
					</div>
				</div>
				<!-- 债转增益设置 -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from" for="IntDisable">债转增益处理</label>
				    <div class="col-sm-3">
				       <select name="intdisable" id="IntDisable" class="form-control">
				          <option value="">请选择</option>
				          <option value="1">全部失效</option>
				          <option value="2">按债转金额比例失效</option>
				          <option value="3">不作废</option>
				       </select>  
				    </div>
				</div>
				<!-- 定向名单列表Id -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from" for="IntDisable">定向名单列表</label>
				    <div class="col-sm-3">
				       <select name="intdisable" id="IntDisable" class="form-control">
				       <option value="">请选择</option>
				       <c:if test="${!empty snl2}">
				          <c:forEach items="${snl2}" var="sn">
				               <option value="${sn.id}">${sn.businessName}</option>
				          </c:forEach>
				       </c:if>
				       </select>
				    </div>
				</div>
				
				<%-- <!--登等级选择-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">允许债权人债转等级</label>
					<div class="col-sm-3">
						<label class="radio-inline">
			 			<input type="radio" name="aownergrade" id="aownergradeone" value="1" class="insert-ugrade-radio-aownergrade"/>全部等级
			 		</label>
						<label class="radio-inline">
			 			<input type="radio" name="aownergrade" id="aownergradetwo" value="2" class="insert-ugrade-radio-aownergrade"/>部分等级
			 		</label>
					</div>
				</div>
				<!--允许转让人的等级-->
				<div class="form-group" id="aownergrade_div">
					<label class="col-sm-3 control-label laber_from" for="aOwnerGrade"></label>
					<div class="col-sm-6">
					<c:if test="${!empty uGrades}">
					    <c:forEach items="${uGrades}" var="ugr" varStatus="status">
					      <label class="checkbox-inline" style="width:120px;">
			   	 		     <input type="checkbox" name="aownergrades" id="aOwnerGrade1" value="${ugr.ugrade}"/>${ugr.ugradename}
			   	 	      </label>
			   	 	      <c:if test="${status.count%4==0}"><br></c:if>
					    </c:forEach>
					</c:if>
					</div>
				</div>
				<!--债转排除名单表编号-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="removenameno">债转排除名单</label>
					<div class="col-sm-6">
					<c:if test="${!empty removeNames}">
					  <c:forEach items="${removeNames}" var="rem" varStatus="statu">
					     <label class="checkbox-inline">
			 			    <input type="checkbox" name="removenameno" id="removenameno" value="${rem.nameno}"/>${rem.name}
			 		     </label>
			 		     <c:if test="${statu.count%4==0}"><br></c:if>
					  </c:forEach>
					</c:if>
					</div>
				</div> --%>
				<!--持有时间-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="holdDay">持有时间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="holdday" id="holdDay" placeholder="投标成功后多少天" class="form-control" />
							<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!--距离下个还款天数-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="intervalDay">距离下个还款日天数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="intervalday" id="intervalDay" class="form-control" placeholder="距离下一还款日多少天可转让" />
							<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!--逾期前多少天可转让-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">逾期前多少天可债转</label>
					<div class="col-sm-3">
						<div class="input-group">
						<input type="text" name="aheadocday" id="AheaDocDay" placeholder="逾期前多少天可债转" class="form-control"/>
						<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!-- 债转期限 -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from">债转期限</label>
				    <div class="col-sm-3">
				        <div class="input-group">
				          <input type="text" name="deadline" id="DeadLine" class="form-control" placeholder="需在规定时间承接,否则下架"/>
				          <span class="input-group-addon">天</span>
				        </div>
				    </div>
				</div>
			    <!--债转次数方式限制  -->
			    <div class="form-group">
			        <label class="col-sm-3 control-label laber_from">债转次数方式限制</label>
			        <div class="col-sm-3">
			            <select name="dattrestrict" id="DATTRestrict" class="form-control">
			               <option value="">请选择</option>
			               <option value="1">层级次数</option>
			               <option value="2">每人次数</option>
			            </select>
			        </div>
			    </div>
			    <!-- 债转次数限制 -->
				<div class="form-group" id="DATimesci_a">
				     <label class="col-sm-3 control-label laber_from" for="DATimes">债转次数限制</label>
				     <div class="col-sm-3">
				        <div class="input-group">
				           <input type="text" name="datimes" id="DATimes" class="form-control"/>
				           <span class="input-group-addon">次</span>
				        </div>
				     </div>
				</div>
				<!--起息日时间点-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">起息日时间点</label>
					<div class="col-sm-3">
						<input type="text" name="valuepoint" id="valuePoint" placeholder="起息日时间点" class="form-control" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
					</div>
				</div>
				<!--起息规则-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">起息规则</label>
					<div class="col-sm-3">
						<select name="valuerule" id="valueRule" class="form-control">
							<option value="">请选择</option>
							<option value="1">承接日当天</option>
							<option value="2">承接日次日</option>
							<option value="3">固定时间点</option>
						</select>
					</div>
				</div>
				<!--挂单期利息-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="Isfixed">挂单期利息</label>
					<div class="col-sm-3">
						<select name="isfixed" id="Isfixed" class="form-control">
							<option value="">请选择</option>
							<option value="1">固定</option>
							<option value="0">不固定</option>
						</select>
					</div>
				</div>
				<!--转让金额设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AttornMoneyLow">转让金额设置低值-高值</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="attornmoneylow" id="AttornMoneyLow" placeholder="可转让金额低值" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="attornmoney" id="AttornMoney" placeholder="可转让金额高值" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--转让系数设定-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minAttornRatio">转让系数设定低值-高值</label>
					<div class="col-sm-3">
						<input type="text" id="minAttornRatio" name="minattornratio" placeholder="转让系数低值如0.2" class="form-control" />
					</div>
					<div class="col-sm-3">
						<input type="text" id="maxAttornratio" name="maxattornratio" placeholder="转让系数高值如1.5" class="form-control" />
					</div>
				</div>
				<!--是否允许拆分-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAsplit">是否允许拆分</label>
					<div class="col-sm-3">
						<select name="isasplit" id="isAsplit" class="form-control">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<!--是否为模板-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
					<div class="col-sm-3">
						<select name="istemplet" id="isTemplet" class="form-control">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<!--备注-->
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">备注</label>
					<div class="col-sm-3">
						  <textarea rows="3" class="form-control" name="remark"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="debtBtton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="backTest()">返回列表</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>