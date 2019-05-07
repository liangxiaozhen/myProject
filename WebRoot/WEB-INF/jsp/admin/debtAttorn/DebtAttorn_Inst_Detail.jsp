<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DebtAttorn_Inst_Detail</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<style type="text/css">
   .laber_from {
		color: #222;
		font-weight: normal;
		}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var MaxInputs = 15; //maximum input boxes allowed  
	var InputsWrapper = $("#isADAFeeOn_divtwo"); //Input boxes wrapper ID  
	var AddButton = $("#debtBttontwo"); //Add button ID 

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
					'<span><button id="removeButtontwo" class="btn btn-default removeclasstwo"><strong>-</strong></button></span>' +
					'</div>' +
					'</div>' +
					'<div id="dayawardrate_divtwo' + x + '">' +
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
	$("body").on("click", ".removeclasstwo", function(e) { //user click on remove text  
		var $row = $(this).parents('.wapperclass'),
			$option = $row.find('[name="option[]"]');
		$row.remove();
		$('form').bootstrapValidator('removeField', $option);
	});
});
$(function() {
	$("#defaultForm").bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: { /*input状态样式图片*/
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: { 
			'debtAttorns[0].minattornmoney': { //债转金额低值
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
			'debtAttorns[0].maxattornmoney': { //债转金额高值
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
			'debtAttorns[0].attornrate': { //债转金额百分比
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
			'debtAttorns[0].minfee': { //收取最低值
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
			'debtAttorns[0].maxfee': { //收取最高值
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
		}
   });
});
</script>
</head>
<body>
    <div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/debtAttorn/insertDebtAttorntwo.action" id="defaultForm">
				<!--债权转让审核-->
				<div class="form-group">
					<label for="isdebtaudit" class="col-sm-3 control-label laber_from">债权转让是否需要审核</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isdebtaudit}">
						<select name="isdebtaudit" id="isdebtaudit" class="form-control">
						<c:if test="${debtattorn.isdebtaudit eq 1}">
						      <option value="1">需要</option>
						</c:if>
						<c:if test="${debtattorn.isdebtaudit eq 0}">
						      <option value="0">不需要</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<!--是否逾期债转-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isOCDebt">是否支持逾期债转</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isocdebt}">
						<select name="isocdebt" id="isOCDebt" class="form-control">
						<c:if test="${debtattorn.isocdebt eq 1}">
						     <option value="1">支持</option>
						</c:if>
						<c:if test="${debtattorn.isocdebt eq 0}">
						    <option value="0">不支持</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<!-- 债转增益设置 -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from" for="IntDisable">债转增益处理</label>
				    <div class="col-sm-3">
				    <c:if test="${!empty debtattorn.intdisable}">
				       <select name="intdisable" id="IntDisable" class="form-control">
				             <c:if test="${debtattorn.intdisable eq 1}">
				                 <option value="1">全部失效</option>
				             </c:if>
				              <c:if test="${debtattorn.intdisable eq 2}">
				                 <option value="2">按债转金额比例失效</option>
				             </c:if>
				              <c:if test="${debtattorn.intdisable eq 3}">
				                <option value="3">不作废</option>
				             </c:if>
				       </select>
				     </c:if>
				    </div>
				</div>
				<!-- 债转次数限制 -->
				<div class="form-group">
				     <label class="col-sm-3 control-label laber_from" for="DATimes">债转次数限制</label>
				     <div class="col-sm-3">
				     <c:if test="${!empty debtattorn.datimes}">
				        <div class="input-group">
				           <input type="text" name="datimes" id="DATimes" value="${debtattorn.datimes}" class="form-control" readonly/>
				           <span class="input-group-addon">次</span>
				        </div>
				     </c:if>
				     </div>
				</div>
				<h3>转让人设置</h3>
				<!--登等级选择-->
				 <div class="form-group">
					<label class="col-sm-3 control-label laber_from">等级选择</label>
					<div class="col-sm-3">
					<c:if test="${!empty aowners}">
						<label class="radio-inline">
			 			  <input type="radio" name="aownergrade" id="aownergradetwo" value="2" checked="checked"/>部分等级
			 		   </label>
			 		</c:if>
					</div>
				</div>
				<!--允许债权人债转的等级-->
				 <div class="form-group" id="aownergrade_div">
					<label class="col-sm-3 control-label laber_from" for="aOwnerGrade">允许债权人债转等级</label>
					<div class="col-sm-6">
					<c:if test="${!empty aowners}">
					    <c:forEach items="${aowners}" var="ugr" varStatus="status">
					      <label class="checkbox-inline" style="width:120px;">
			   	 		     <input type="checkbox" name="aown" id="aOwnerGrade1" checked="checked" readonly/>${ugr}
			   	 	      </label>
			   	 	      <c:if test="${status.count%4==0}"><br></c:if>
					    </c:forEach>
					    <input type="hidden" name="aownergrades" value="${debtattorn.aownergrade}"/>
					</c:if>
					</div>
				</div> 
				<!--债转排除名单表编号-->
				<%-- <div class="form-group">
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
					<c:if test="${!empty debtattorn.holdday}">
						<div class="input-group">
							<input type="text" name="holdday" id="holdDay"  value="${debtattorn.holdday}" class="form-control" readonly/>
							<span class="input-group-addon">天</span>
						</div>
					</c:if>
					</div>
				</div>
				<!--距离下个还款天数-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="intervalDay">距离下个还款日天数</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.intervalday}">
						<div class="input-group">
							<input type="text" name="intervalday" id="intervalDay" class="form-control" value="${debtattorn.intervalday}" readonly/>
							<span class="input-group-addon">天</span>
						</div>
					</c:if>
					</div>
				</div>
				<!--逾期前多少天可转让-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">逾期前几天可转让</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.aheadocday}">
						<div class="input-group">
						<input type="text" name="aheadocday" id="AheaDocDay" class="form-control" value="${debtattorn.aheadocday}" readonly/>
						<span class="input-group-addon">天</span>
						</div>
					</c:if>
					</div>
				</div>
				<!--起息日时间点-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">起息日时间点</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.valuepoint}">
						<input type="text" name="valuepoint" id="valuePoint"  class="form-control" value="${debtattorn.valuepoint}" readonly/>
					</c:if>
					</div>
				</div>
				<!--起息规则-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">起息规则</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.valuerule}">
						<select name="valuerule" id="valueRule" class="form-control">
						<c:if test="${debtattorn.valuerule eq 1}">
						    <option value="1">承接日当天</option>
						</c:if>
						<c:if test="${debtattorn.valuerule eq 2}">
						    <option value="2">承接日次日</option>
						</c:if>
						<c:if test="${debtattorn.valuerule eq 3}">
						    <option value="3">承接日固定时间之前</option>
						</c:if>
						<c:if test="${debtattorn.valuerule eq 4}">
						    <option value="4">承接日固定时间之后</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<!--挂单期利息-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="Isfixed">挂单期利息</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isfixed}">
						<select name="isfixed" id="Isfixed" class="form-control">
						<c:if test="${debtattorn.isfixed eq 1}">
						      <option value="1">固定</option>
						</c:if>
						<c:if test="${debtattorn.isfixed eq 0}">
						      <option value="0">不固定</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<!-- 债转期限 -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from">债转期限</label>
				    <div class="col-sm-3">
				    <c:if test="${!empty debtattorn.deadline}">
				        <div class="input-group">
				          <input type="text" name="deadline" id="DeadLine" class="form-control" value="${debtattorn.deadline}" readonly/>
				          <span class="input-group-addon">天</span>
				        </div>
				     </c:if>
				    </div>
				</div>
				<!--转让金额设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AttornMoneyLow">转让金额设置低值-高值</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.attornmoneylow}">
						<div class="input-group">
							<input type="text" name="attornmoneylow" id="AttornMoneyLow" class="form-control" value="${debtattorn.attornmoneylow}" readonly/>
							<span class="input-group-addon">元</span>
						</div>
					</c:if>
					</div>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.attornmoney}">
						<div class="input-group">
							<input type="text" name="attornmoney" id="AttornMoney" class="form-control" value="${debtattorn.attornmoney}" readonly/>
							<span class="input-group-addon">元</span>
						</div>
					</c:if>
					</div>
				</div>
				<!--转让系数设定-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minAttornRatio">转让系数设定低值-高值</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.minattornratio}">
						<input type="text" id="minAttornRatio" name="minattornratio" class="form-control" value="${debtattorn.minattornratio}" readonly/>
					</c:if>
					</div>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.maxattornratio}">
						<input type="text" id="maxAttornratio" name="maxattornratio" class="form-control" value="${debtattorn.maxattornratio}" readonly/>
					</c:if>
					</div>
				</div>
				<!--是否允许拆分-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAsplit">是否允许拆分</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isasplit}">
						<select name="isasplit" id="isAsplit" class="form-control">
						<c:if test="${debtattorn.isasplit eq 1}">
						      <option value="1">是</option>
						</c:if>
						<c:if test="${debtattorn.isasplit eq 0}">
						      <option value="0">否</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<h3>购买人设置</h3>
				<!--允许购买人债转的等级-->
				 <div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="APurchaserGrade">允许购买人的等级</label>
					<div class="col-sm-6">
						<label class="radio-inline">
			 			  <input type="radio" name="apurchasergrade" id="APurchaserGradetwo" value="2" checked="checked"/>部分等级
			 		   </label>
					</div>
				</div>
				<!--会员等级-->
				 <div class="form-group" id="apurchasergrades_div">
					<label class="col-sm-3 control-label" for="apurchasergrades"></label>
					<div class="col-sm-6">
					<c:if test="${!empty chasers}">
					  <c:forEach items="${chasers}" var="des" varStatus="sta">
					     <label class="checkbox-inline" style="width:120px;">
			 			    <input type="checkbox" name="apurchasergrades" id="apurchasergrades" readonly checked="checked"/>${des}
			 		     </label>
			 		     <c:if test="${sta.count%4==0}"><br></c:if>
					  </c:forEach>
					  <input type="hidden" name="apurchasergrades" value="${debtattorn.apurchasergrades}"/>
					</c:if>
					</div>
				</div>
				<c:if test="${!empty debtattorn.removenameno}">
				    <input type="hidden" name="removenameno" value="${debtattorn.removenameno}"/>
				</c:if>
				<c:if test="${!empty debtattorn.removenameno}">
				    <input type="hidden" name="noapnameno" value="${debtattorn.noapnameno}"/>
				</c:if>
				<!--不允许购买的用户名单-->
				<%-- <div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="noAPNameNo">不允许购买的用户名单</label>
					<div class="col-sm-6">
					<c:if test="${!empty removeNames}">
					    <c:forEach items="${removeNames}" var="rna" varStatus="tus">
					       <label class="checkbox-inline">
			 	 		      <input type="checkbox" name="noapnameno" id="noAPNameNo" value="${rna.nameno}"/>${rna.name}
			 	 	       </label>
			 	 	       <c:if test="${tus.count%4==0}"><br></c:if>
					    </c:forEach>
					</c:if>
					</div>
				</div> --%>
				<!--购买金额设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isABuyAllOrPart">购买金额设置</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isabuyallorpart}">
						<select name="isabuyallorpart" id="isABuyAllOrPart" class="form-control">
						<c:if test="${debtattorn.isabuyallorpart eq 1}">
						      <option value="1">全额购买</option>
						</c:if>
						<c:if test="${debtattorn.isabuyallorpart eq 0}">
						      <option value="0">部分购买</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<h3>收取转让人债转手续费</h3>
				<!--债权转让手续费开关-->
				<div class="form-group" id="feeondiv">
					<label class="col-sm-3 control-label laber_from" for="isADAFeeOn">债转手续费开关</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isadafeeon}">
						<select name="isadafeeon" id="isADAFeeOn" class="form-control">
						<c:if test="${debtattorn.isadafeeon eq 1}">
						      <option value="1">开</option>
						</c:if>
						<c:if test="${debtattorn.isadafeeon eq 0}">
						      <option value="0">关</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<!--收取转让人债转手续费类型-->
				<!-- <div id="FeeMode_div"> -->
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="FeeMode">债转手续费计算方式</label>
						<div class="col-sm-3">
						<c:if test="${!empty debtattorn.feemode}">
							<select name="feemode" id="FeeMode" class="form-control">
							<c:if test="${debtattorn.feemode eq 1}">
							     <option value="1">用户等级</option>
							</c:if>
							<c:if test="${debtattorn.feemode eq 2}">
							     <option value="2">持有时间</option>
							</c:if>
							</select>
						</c:if>
						</div>
					</div>
					<!--转让人等级-->
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from">转让人等级</label>
							<div class="col-sm-4">
							<c:if test="${!empty debtattorn.ugrade}">
								<label class="radio-inline">
			 			           <input type="radio" name="ugrade" id="uGrade2" value="2" checked="checked"/>全部等级
			 		            </label>
			 		       </c:if>
							</div>
						</div>
						<!--转让人等级-->
						<div class="form-group">
							<label class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
							<c:if test="${!empty grades2}">
							   <c:forEach items="${grades2}" var="gra" varStatus="vst">
							       <label class="checkbox-inline" style="width:120px">
			 			              <input type="checkbox" name="ugrades" id="ugrades" value="${gra.ugrade}"/>${gra.ugradename}
			 		               </label>
			 		               <c:if test="${vst.count%4==0}"><br></c:if>
							   </c:forEach>
							</c:if>
							</div>
						</div>
					<!--债转金额段-->
				<!-- 	<div id="isADAFeeOn_div">
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="minAttornMoney">债转金额段</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttorns[0].minattornmoney" id="minAttornMoney" placeholder="债转金额低值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttorns[0].maxattornmoney" id="maxAttornMoney" placeholder="债转金额高值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
							</div>
						<div class="col-sm-1">
							<span><button id="AddMoreFileBox" class="btn btn-default"><strong>+</strong></button></span>
						</div>
					</div>
					收费类型
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="type">收费类型</label>
						<div class="col-sm-3">
							<select name="type" id="type" class="form-control">
								<option value="">请选择</option>
								<option value="iequota">定额</option>
								<option value="iepercent">百分比</option>
							</select>
						</div>
					</div>
					定额
					<div class="form-group" id="quotainput_div">
						<label class="col-sm-3 control-label laber_from" for="quota">手续费定额</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttorns[0].quota" id="quota" placeholder="手续费定额" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					百分比
					<div id="dayawardrate_div">
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="AttornRate">百分比</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttorns[0].attornrate" id="AttornRate" class="form-control" placeholder="按百分比计算" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						收费金额最低和最高
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="minFee">手续费最低-最高</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttorns[0].minfee" id="minFee" class="form-control" placeholder="手续费最低值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttorns[0].maxfee" id="maxFee" class="form-control" placeholder="手续费最高值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
				</div> -->
					<div id="isADAFeeOn_divtwo">
					<!--债转金额段-->
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="minAttornMoney">债转金额段</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttorns[0].minattornmoney" id="minAttornMoney" placeholder="债转金额低值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttorns[0].maxattornmoney" id="maxAttornMoney" placeholder="债转金额高值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="debtBttontwo" class="btn btn-default"><strong>+</strong></button></span>
						</div>
					</div>
					<!--百分比-->
					<div id="dayawardrate_div">
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="AttornRate">百分比</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttorns[0].attornrate" id="AttornRate" class="form-control" placeholder="按百分比计算" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						<!--收费金额最低和最高-->
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="minFee">手续费最低-最高</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttorns[0].minfee" id="minFee" class="form-control" placeholder="手续费最低值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttorns[0].maxfee" id="maxFee" class="form-control" placeholder="手续费最高值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.isaudit}">
						<select name="isaudit" id="isAudit" class="form-control">
						<c:if test="${debtattorn.isaudit eq 1}">
						      <option value="1">是</option>
						</c:if>
						<c:if test="${debtattorn.isaudit eq 0}">
						      <option value="0">否</option>
						</c:if>
						</select>
					</c:if>
					</div>
				</div>
				<!--是否为模板-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
					<div class="col-sm-3">
					<c:if test="${!empty debtattorn.istemplet}">
						<select name="istemplet" id="isTemplet" class="form-control">
						<c:if test="${debtattorn.istemplet eq 1}">
						      <option value="1">是</option>
						</c:if>
						<c:if test="${debtattorn.istemplet eq 0}">
						      <option value="0">否</option>
						</c:if>
						</select>
					</c:if>
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
						<button type="button" class="btn btn-default">返回列表</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>