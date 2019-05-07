<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AheadRepay_Inst.jsp</title>
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
	<a href="#">标的提前还款个人利息奖励设置</a>
</div>
<%-- <%@ include file="AheadRepayAward_Inst.jsp" %>
<%@ include file="AheadRepayPlatform_Inst.jsp" %> --%>
<div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" id="defaultForm" method="post" action="${pageContext.request.contextPath}/admin/aheadRepay/insertAheadRepay.action">
				<!--本金利息补偿开关-->
				<!-- <div class="form-group">
					<label for="ispicompensateon" class="col-sm-3 control-label">本金利息补偿开关</label>
					<div class="col-sm-3">
						<select name="ispicompensateon" id="inputispicompensateon" class="form-control">
							<option value="">请选择</option>
							<option value="1">补偿</option>
							<option value="0">不补偿</option>
						</select>
					</div>
				</div> -->
				<!--单个投资人累计本金欠收最小利息与最高利息-->
				<input type="hidden" name="tid" value="${tid}">
				<div id="InputsWrapperone" class="wapperclass">
					<div class="form-group">
						<label for="inputminnoreceiveint" class="col-sm-3 control-label">单个投资人累计本金欠收利息最低-最高</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" readonly="readonly"  value="0" name="aheadRepays[0].minnoreceiveint" id="inputminnoreceiveint" placeholder="累计本金欠收最小利息" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text"readonly="readonly" value="999999999999" name="aheadRepays[0].maxnoreceiveint" id="inputmaxnoreceiveint" placeholder="累计本金欠收最高利息" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="AddMoreFileBoxone" class="btn btn-default addButtontwo"><strong>+</strong></button></span>
						</div>
					</div>
					<!--奖励方式-->
					<div class="form-group">
						<label for="inputdaylatefeerate" class="col-sm-3 control-label">奖励方式</label>
						<div class="col-sm-3">
							<select name="aheadRepays[0].awardtype" id="inputawardtype" class="form-control">
								<option value="">请选择</option>
								<option value="1">惩罚借款人</option>
								<option value="2">平台奖励</option>
								<option value="3">惩罚借款人且平台奖励</option>
							</select>
						</div>
					</div>
					<!--借款人罚金奖励名称-->
					<div id="loanpp_div" >
						<div class="form-group">
							<label class="col-sm-3 control-label" for="loanpenaltyname">借款人罚金奖励名称</label>
							<div class="col-sm-3">
								<input type="text" name="aheadRepays[0].loanpenaltyname" id="loanpenaltyname" class="form-control" placeholder="借款人罚金奖励名称" />
							</div>
						</div>
						<!--类型-->
						<div class="form-group">
							<label class="col-sm-3 control-label">类型</label>
							<div class="col-sm-3">
								<select name="typethr" id="typethr" class="form-control">
									<option value="">请选择</option>
									<option value="iequotathr">定额</option>
									<option value="iepercentthr">百份比</option>
								</select>
							</div>
						</div>
						<!--罚金定额-->
						<div class="form-group" id="quotainput_divthr">
							<label class="col-sm-3 control-label" for="penaltyquota">罚金定额</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="aheadRepays[0].penaltyquota" id="penaltyquota" placeholder="罚金定额" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
						<!--罚金百分比最大值-->
						<div class="form-group" id="dayawardrate_divthr">
							<label class="col-sm-3 control-label" for="penaltyrate">罚金百分比-罚金最大值</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="aheadRepays[0].penaltyrate" id="penaltyrate" placeholder="罚金百分比" class="form-control" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="aheadRepays[0].maxpenalty" id="maxpenalty" placeholder="罚金最大值" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
					<!--平台奖励奖品名称及编号-->
					<div id="paward_div">
					    <div class="form-group">
						<label class="col-sm-3 control-label" for="pawardname">平台奖励奖品名称</label>
						<div class="col-sm-3">
						<c:if test="${!empty awards}">
							<select name="aheadRepays[0].pawardname" id="inputpawardname" class="form-control">
							<option value="">请选择</option>
							<c:forEach items="${awards}" var="awd">
							   <option value="${awd.ano},${awd.aname}">${awd.aname}</option>
							</c:forEach>
							</select>
						</c:if>
						</div>
						</div>
						<div class="form-group">
						    <label class="col-sm-3 control-label">奖品份数</label>
						    <div class="col-sm-3">
						       <div class="input-group">
						          <input type="text" name="aheadRepays[0].pawardcount" placeholder="奖励奖品份数" class="form-control"/>
						          <span class="input-group-addon">份</span>
						       </div>
						    </div>
						</div>
					</div>
				</div>
				
				<!----我是可耻的分割线-->
					<div id="InputsWrappertwo" class="hide form-remove wapperclass">
					<div class="form-group">
						<label for="inputminnoreceiveint" class="col-sm-3 control-label">单个投资人累计本金欠收利息最低-最高</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" readonly="readonly"name="minnoreceiveint" id="inputminnoreceiveint" placeholder="累计本金欠收最小利息" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text"  readonly="readonly" value="999999999999" name="maxnoreceiveint" id="inputmaxnoreceiveint" placeholder="累计本金欠收最高利息" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="AddMoreFileBoxone" class="btn btn-default removeButtontwo"><strong>-</strong></button></span>
						</div>
					</div>
					<!--奖励方式-->
					<div class="form-group">
						<label for="inputdaylatefeerate" class="col-sm-3 control-label">奖励方式</label>
						<div class="col-sm-3">
							<select name="awardtype" id="inputawardtype" class="form-control">
								<option value="">请选择</option>
								<option value="1">惩罚借款人</option>
								<option value="2">平台奖励</option>
								<option value="3">惩罚借款人且平台奖励</option>
							</select>
						</div>
					</div>
					<!--借款人罚金奖励名称-->
					<div id="loanpp_div">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="loanpenaltyname">借款人罚金奖励名称</label>
							<div class="col-sm-3">
								<input type="text" name="loanpenaltyname" id="loanpenaltyname" class="form-control" placeholder="借款人罚金奖励名称" />
							</div>
						</div>
						<!--类型-->
						<div class="form-group">
							<label class="col-sm-3 control-label">类型</label>
							<div class="col-sm-3">
								<select name="typethr" id="typethr" class="form-control">
									<option value="">请选择</option>
									<option value="iequotathr">定额</option>
									<option value="iepercentthr">百份比</option>
								</select>
							</div>
						</div>
						<!--罚金定额-->
						<div class="form-group" id="quotainput_divthr">
							<label class="col-sm-3 control-label" for="penaltyquota">罚金定额</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="penaltyquota" id="penaltyquota" placeholder="罚金定额" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
						<!--罚金百分比最大值-->
						<div class="form-group" id="dayawardrate_divthr">
							<label class="col-sm-3 control-label" for="penaltyrate">罚金百分比-罚金最大值</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="penaltyrate" id="penaltyrate" placeholder="罚金百分比" class="form-control" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="maxpenalty" id="maxpenalty" placeholder="罚金最大值" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</div>
					<!--平台奖励奖品名称及编号-->
					<div id="paward_div">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="pawardname">平台奖励奖品名称</label>
						<div class="col-sm-3">
						<c:if test="${!empty awards}">
							<select name="pawardname" id="inputpawardname" class="form-control">
							<option value="">请选择</option>
							<c:forEach items="${awards}" var="awd">
							   <option value="${awd.ano},${awd.aname}">${awd.aname}</option>
							</c:forEach>
							</select>
						</c:if>
						</div> 
					</div>
					<div class="form-group">
					   <label class="col-sm-3 control-label">奖品份数</label>
					   <div class="col-sm-3">
					      <div class="input-group">  
					         <input type="text" name="pawardcount" id="Pawardcount" placeholder="请输入奖品的份数" class="form-control"/>
					         <span class="input-group-addon">份</span>
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
	function callback(){
		var url="${pageContext.request.contextPath}/admin/aheadRepay/selectAheadRepayByCondition.action";
		window.location.href=url;
	}
	$(function(){
		//$("#InputsWrapperone").hide(); //本金利息补偿
		$("#loanpp_div").hide(); //本金利息补偿方式惩罚借款人
		$("#paward_div").hide(); //本金利息补偿方式平太奖励
		$("#dayawardrate_divthr").hide(); //百分比隐藏
		$("#quotainput_divthr").hide(); //定额隐藏   
		//补偿投资人(本金利息补偿)开关
		/* $("#inputispicompensateon").change(function() {
			var teonval = $(this).val();
			if(teonval == 1) { //开
				$("#InputsWrapperone").show();
			} else {
				$("#InputsWrapperone input").val("");
				$("#InputsWrapperone select").val("");
				$("#InputsWrapperone").hide();
			}
		}); */
		//奖励方式(本金利息补偿)
		$("#inputawardtype").change(function() {
			var typeval = $(this).val();
			if(typeval == 1) { //惩罚借款人
				$("#paward_div select").val("");
				$("#paward_div").hide();
				$("#loanpp_div").show();
			} else if(typeval == 2) { //平太奖励
				$("#loanpp_div input").val("");
				$("#loanpp_div select").val("");
				$("#loanpp_div").hide();
				$("#paward_div").show();
			} else if(typeval == 3) {
				$("#loanpp_div input").val("");
				$("#paward_div select").val("");
				$("#loanpp_div select").val("");
				$("#loanpp_div").show();
				$("#paward_div").show();
			} else {
				$("#paward_div select").val("");
				$("#loanpp_div input").val("");
				$("#loanpp_div select").val("");
				$("#loanpp_div").hide();
				$("#paward_div").hide();
			}
		});
		//类型(本金利息补偿)
		//类型选择(增益利息补偿)
		$("#typethr").change(function() {
			if($(this).val() == "iequotathr") {
				$("#dayawardrate_divthr input").val("");
				$("#dayawardrate_divthr").hide();
				$("#quotainput_divthr").show();
			} else if($(this).val() == "iepercentthr") {
				$("#dayawardrate_divthr").show();
				$("#quotainput_divthr input").val("");
				$("#quotainput_divthr").hide();
			} else {
				$("#quotainput_divthr").hide();
				$("#dayawardrate_divthr").hide();
				$("#quotainput_divthr input").val("");
				$("#dayawardrate_divthr input").val("");
			}
		});
	})
	$(document).ready(function(){
		var minnoreceiveint={//累计本金欠收最小利息
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                },
	                regexp:{//匹配规则
                  	  regexp:/^(\d*\.)?\d+$/,
                  	  message:'利息必须为整数或小数'
                  }
			}
		},
		maxnoreceiveint={//累计本金欠收最高利息
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                },
	                regexp:{//匹配规则
	                  	  regexp:/^(\d*\.)?\d+$/,
	                  	  message:'利息必须为整数或小数'
	                  }
			}
		},
		awardtype={//奖励方式
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                }
			}
		},
		loanpenaltyname={//罚金名称
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                }
			}
		},
		penaltyquota={//定额
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                },
	                regexp:{//匹配规则
	                  	  regexp:/^(\d*\.)?\d+$/,
	                  	  message:'定额必须为整数或小数'
	                  }
			}
		},
		penaltyrate={//百分比
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                },
	                regexp:{//匹配规则
	                  	  regexp:/^(\d*\.)?\d+$/,
	                  	  message:'百分比必须为整数或小数'
	                  }
			}
		},
		maxpenalty={//最大值
			validators:{
				notEmpty: {
	                    message: '不能为空'
	                },
	                regexp:{//匹配规则
	                  	  regexp:/^(\d*\.)?\d+$/,
	                  	  message:'最大值必须为整数或小数'
	                  }
			}
		},
		bookIndex = 0;
		
		$("#defaultForm").bootstrapValidator({
			framework: 'bootstrap',
	            icon: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	             fields: {
	                'aheadRepays[0].minnoreceiveint': minnoreceiveint,
	                'aheadRepays[0].maxnoreceiveint': maxnoreceiveint,
	                'aheadRepays[0].awardtype': awardtype,
	                'aheadRepays[0].loanpenaltyname': loanpenaltyname,
	                'aheadRepays[0].penaltyquota': penaltyquota,
	                'aheadRepays[0].penaltyrate': penaltyrate,
	                'aheadRepays[0].maxpenalty': maxpenalty
	            }
		})
		
		  // Add button click handler
	        .on('click', '.addButtontwo', function() {
				var lastBeforeInput=$(InputsWrappertwo).prev().find("input").eq(1);
				lastBeforeInput.attr("readonly",false);
				lastBeforeInput.addClass("maxMoney");
				lastBeforeInput.val("");
	            bookIndex++;
	            var $template = $('#InputsWrappertwo'),
	                $clone    = $template
	                                .clone()
	                                .removeClass('hide')
	                                .removeAttr('id')
	                                .attr('data-book-index', bookIndex)
	                                .insertBefore($template);

	            // Update the name attributes
	            $clone
	                .find('[name="maxnoreceiveint"]').attr('name', 'aheadRepays[' + bookIndex + '].maxnoreceiveint').end()
	                .find('[name="awardtype"]').attr('name', 'aheadRepays[' + bookIndex + '].awardtype').end()
	                .find('[name="loanpenaltyname"]').attr('name', 'aheadRepays[' + bookIndex + '].loanpenaltyname').end()
	                .find('[name="penaltyquota"]').attr('name', 'aheadRepays[' + bookIndex + '].penaltyquota').end() 
	                .find('[name="maxpenalty"]').attr('name', 'aheadRepays[' + bookIndex + '].maxpenalty').end()  
	                .find('[name="pawardname"]').attr('name', 'aheadRepays[' + bookIndex + '].pawardname').end()  
	                .find('[id="typethr"]').attr('id', 'typethr'+bookIndex).end()
	                .find('[id="dayawardrate_divthr"]').attr('id', 'dayawardrate_divthr'+bookIndex).end()
	                .find('[id="quotainput_divthr"]').attr('id', 'quotainput_divthr'+bookIndex).end()
	                .find('[id="inputawardtype"]').attr('id', 'inputawardtype'+bookIndex).end()
	                .find('[id="paward_div"]').attr('id', 'paward_div'+bookIndex).end()
	                .find('[id="loanpp_div"]').attr('id', 'loanpp_div'+bookIndex).end()
	                .find('[name="penaltyrate"]').attr('name', 'aheadRepays[' + bookIndex + '].penaltyrate').end();
	            // Add new fields
	            // Note that we also pass the validator rules for new field as the third parameter
	            $('#defaultForm')
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].minnoreceiveint', minnoreceiveint)
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].maxnoreceiveint', maxnoreceiveint)
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].awardtype', awardtype)
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].loanpenaltyname', loanpenaltyname)
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].penaltyquota', penaltyquota)
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].maxpenalty', maxpenalty)
	                .bootstrapValidator('addField', 'aheadRepays[' + bookIndex + '].penaltyrate', penaltyrate);
	            
	            $("#paward_div" + bookIndex).hide(); //影藏定额
				$("#loanpp_div" + bookIndex).hide(); //影藏日奖励费率
				$("#dayawardrate_divthr" + bookIndex).hide(); //百分比
				$("#quotainput_divthr" + bookIndex).hide(); //定额
	        })
	        
	           // Remove button click handler
	        .on('click', '.removeButtontwo', function() {
	            var $row  = $(this).parents('.form-remove'),
	                index = $row.attr('data-book-index');

	            // Remove fields
	            $('#defaultForm')
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].minnoreceiveint"]'))
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].maxnoreceiveint"]'))
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].awardtype"]'))
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].loanpenaltyname"]'))
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].penaltyquota"]'))
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].maxpenalty"]'))
	                .bootstrapValidator('removeField', $row.find('[name="aheadRepays[' + index + '].penaltyrate"]'));
	            // Remove element containing the fields
				var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
				var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
				ntInput.val(bfInput.val());
	            $row.remove();
				bookIndex--;
				var lastInput=$(InputsWrappertwo).prev().find("input").eq(1);
				lastInput.val("999999999999");
				lastInput.attr("readonly",true);
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

			$(function() {
				$('#defaultForm').bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: { /*input状态样式图片*/
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: { //验证
						minnoreceiveint: { //单个投资人累计本金欠收最小利息
							validators: {
								notEmpty: {
									message: '利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息只能为浮点数如1.0或1'
								}
							}
						},
						maxnoreceiveint: { //单个投资人累计本金欠收最高利息
							validators: {
								notEmpty: {
									message: '利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息只能为浮点数如1.0或1'
								},
								callback: {
									message: '最高利息不能低于最低利息',
									callback: function(value, validator) {
										var minciveint = $("#inputminnoreceiveint").val(),
											sum = parseFloat(minciveint);
										return value >= sum;
									}
								}
							}
						},
						loanpenaltyname: { //借款人罚金奖励名称
							validators: {
								notEmpty: {
									message: '名称不能为空'
								},
								stringLength: {
									max: 16,
									message: '长度不能超过16位'
								}
							}
						},
						penaltyquota: { //借款人罚金定额
							validators: {
								notEmpty: {
									message: '定额不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								}
							}
						},
						penaltyrate: { //借款人罚金百分比
							validators: {
								notEmpty: {
									message: '百分比不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '百分比为数字如1.0或1'
								}
							}
						},
						maxpenalty: { //借款人罚金最大值
							validators: {
								notEmpty: {
									message: '最大值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								}
							}
						},
						pawardquota: { //平台奖励定额
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
						pawardrate: { //平台奖励百分比
							validators: {
								notEmpty: {
									message: '百分比不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '百分比为小数如1.0或1'
								}
							}
						},
						maxpaward: { //平台奖励最大值
							validators: {
								notEmpty: {
									message: '最大值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								}
							}
						},
						minplusnoreceiveint: { //单个投资人累计增益欠收最小利息
							validators: {
								notEmpty: {
									message: '最小利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息浮点数如1.0或1'
								}
							}
						},
						maxplusnoreceiveint: { //单个投资人累计增益欠收最高利息
							validators: {
								notEmpty: {
									message: '最高利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息浮点数如1.0或1'
								},
								callback: {
									message: '最高利息不能低于最低利息',
									callback: function(value, validator) {
										var minoreint = $("#minplusnoreceiveint").val(),
											sum = parseFloat(minoreint);
										return value >= sum;
									}
								}
							}
						},
						pluspenaltyname: { //增益平台罚金奖励名称
							validators: {
								notEmpty: {
									message: '最高利息不能为空'
								},
								stringLength: {
									max: 16,
									message: '长度不能超过16位'
								}
							}
						},
						pluspenaltyquota: { //增益平台罚金定额
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
						pluspenaltyrate: { //增益平台罚金百分比
							validators: {
								notEmpty: {
									message: '百分比不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '百分比为数字如1.0或1'
								}
							}
						},
						plusmaxpenalty: { //增益平台罚金最大值
							validators: {
								notEmpty: {
									message: '最大值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								}
							}
						},
						pluspawardquota: { //增益平台奖励定额
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
						pluspawardrate: { //增益平台奖励百分比
							validators: {
								notEmpty: {
									message: '定额不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '百分比为数字如1.0或1'
								}
							}
						},
						plusmaxpaward: { //增益平台奖励最大值
							validators: {
								notEmpty: {
									message: '最大值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如100.0或100'
								}
							}
						},
						awardrecman: { //奖励平台收款人
							validators: {
								notEmpty: {
									message: '收款人不能为空'
								},
								stringLength: {
									max: 16,
									message: '长度不能超过16位'
								}
							}
						},
						minallnoreceiveint: { //奖励平台投资人总欠收最小利息
							validators: {
								notEmpty: {
									message: '利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息为浮点数如10.0或10'
								}
							}
						},
						minallnoreceiveint: { //奖励平台投资人总欠收最高利息
							validators: {
								notEmpty: {
									message: '利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息为浮点数如10.0或10'
								},
							}
						},
						maxallnoreceiveint: { //奖励平台投资人总欠收最高利息
							validators: {
								notEmpty: {
									message: '利息不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '利息为浮点数如10.0或10'
								},
								callback: {
									message: '最高利息不能低于最低利息',
									callback: function(value, validator) {
										var minNoint = $("#minAllNoReceiveInt").val(),
											sum = parseFloat(minNoint);
										return value >= sum;
									}
								}
							}
						},
						awardplatquota: { //奖励平台定额
							validators: {
								notEmpty: {
									message: '定额不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如10.0或10'
								}
							}
						},
						awardplatrate: { //奖励平台百份比
							validators: {
								notEmpty: {
									message: '百份比不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '百份比为数字如10.0或10'
								}
							}
						},
						awardplatminmoney: { //奖励平台最小值
							validators: {
								notEmpty: {
									message: '最小值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如10.0或10'
								}
							}
						},
						awardplatmaxmoney: { //奖励平台最大值
							validators: {
								notEmpty: {
									message: '最大值不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额为浮点数如10.0或10'
								},
								callback: {
									message: '最大值不能低于最低值',
									callback: function(value, validator) {
										var minlatint = $("#AwardPlatMinMoney").val(),
											sum = parseFloat(minlatint);
										return value >= sum;
									}
								}
							}
						},

					}
				});

				/* $("#aheButton").click(function() {
					$('#defaultForm').bootstrapValidator('validate');
				}); */
			});
		</script>
</body>
</html>