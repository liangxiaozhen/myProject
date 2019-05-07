<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
  $(function(){
	   //$("#FeeMode_div").hide();//手续费计算方式
	   $("#uGadewapper").hide();//转让人会员等级
	   $("#HadDay_div").hide();//持有时间
	   $("#ugrades_div").hide();//会员等级
	   $("#uGrade1").attr("checked","checked");
	  //全选按钮选中当前全部等级
	  $("#selectAll").click(function () {
		  if(this.checked){
			  $("#ugrades_div  :checkbox").prop("checked",true);
		  }else{
			  $("#ugrades_div  :checkbox").prop("checked",false);
		  }
	  })

	  //债转手续费计算方式
	    $("#FeeMode").change(function(){
	    	var modeval=$(this).val();
	    	if(modeval==1){//会员等级
	    		 $("#uGadewapper").show();
	    		 $("#HadDay_div").hide();
	    		 $("#isADAFeeOn_divtwo").show();
	    	}else if(modeval==2){//持有时间
	    		  $("#HadDay_div").show();
	    		  $("#uGadewapper").hide();
				  $("#isADAFeeOn_divtwo").show();
	    	}else{
	    		 $("#uGadewapper").hide();
	    		 $("#HadDay_div").hide();
	    	}
	    });
		  if('${feeMode}'==1){
			  $("#uGadewapper").show();
			  $("#HadDay_div").hide();
			  $("#isADAFeeOn_divtwo").show();
			  $("#isADAFeeOn_divcysj").hide();
			  $("#isADAFeeOn_divcysj input").val("");
			  $("#HadDay_div input").val("");
		  }
		//全额够买:手续费转让人等级全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio-ugrade").change(function() {
			var $radioVal = $(".insert-ugrade-radio-ugrade:checked").val();
			if($radioVal == 1) {
				$("#ugrades_div").hide();
				$("#ugrades_div :checkbox").each(function() {
					this.checked = false; 
				});
			} else {
				$("#ugrades_div").show();
			}
		});
  })
            //用户选择部分购买
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
								'<input type="text" readonly="readonly" name="debtAttornFees[' + x + '].minattornmoney" id="minAttornMoney" placeholder="债转金额段低值" class="form-control" />' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" readonly="readonly" value="999999999999" name="debtAttornFees[' + x + '].maxattornmoney" id="maxAttornMoney" placeholder="债转金额段高值" class="form-control maxMoney"  />' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div>' +
								'<div class="col-sm-1">' +
								'<span><button id="removeButtontwo" class="btn btn-default removeclass"><strong>-</strong></button></span>' +
								'</div>' +
								'</div>' +
								'<div class="form-group">' +
								'<label class="control-label col-sm-3 laber_from" for="inputattornrate">百分比</label>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttornFees[' + x + '].attornrate" id="inputattornrate" placeholder="百分比" class="form-control">' +
								'<span class="input-group-addon">%</span>' +
								'</div>' +
								'</div>' +
								'</div>' +
								'<div class="form-group">' +
								'<label class="control-label col-sm-3 laber_from" for="inputoccquota">手续费最低-最高</label>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttornFees[' + x + '].minfee" id="inputminFee " placeholder="手续费最低值" class="form-control">' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div>' +
								'<div class="col-sm-3">' +
								'<div class="input-group">' +
								'<input type="text" name="debtAttornFees[' + x + '].maxfee" id="inputmaxFee" placeholder="手续费最高值" class="form-control">' +
								'<span class="input-group-addon">元</span>' +
								'</div>' +
								'</div></div>'
							);
							$(InputsWrapper).append(oDiv);
							var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
							var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);
							lastBeforeInput.attr("readonly",false);
							lastBeforeInput.val("");
							$('form').bootstrapValidator('addField', 'debtAttornFees[' + x + '].minattornmoney', {
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
							$('form').data('bootstrapValidator').addField('debtAttornFees[' + x + '].maxattornmoney', {
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
							$('form').data('bootstrapValidator').addField('debtAttornFees[' + x + '].attornrate', {
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
							$('form').data('bootstrapValidator').addField('debtAttornFees[' + x + '].minfee', {
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
							$('form').data('bootstrapValidator').addField('debtAttornFees[' + x + '].maxfee', {
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
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的债权转让手续费设置</a>
</div>
<div class="container" style="margin-top: 25px;"> 
 <form action="${pageContext.request.contextPath}/admin/debtAttorn/insertDebtAttornFee.action" class="form-horizontal" role="form" method="post">
 	<!--债权转让手续费开关-->
				<!-- <div class="form-group" id="feeondiv">
					<label class="col-sm-3 control-label laber_from" for="isADAFeeOn">债转手续费开关</label>
					<div class="col-sm-3">
						<select name="isadafeeon" id="isADAFeeOn" class="form-control">
							<option value="">请选择</option>
							<option value="1">开</option>
							<option value="0">关</option>
						</select>
					</div>
				</div> -->
				 <input type="hidden" name="tid" value="${tid}">
				<!--收取转让人债转手续费类型-->
				<div id="FeeMode_div">
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="FeeMode">债转手续费计算方式</label>
						<div class="col-sm-3">
							<c:choose>
								<c:when test="${feeMode==1}">
									<select name="feemode" id="FeeMode" class="form-control">
										<option value="1" selected="selected">用户等级</option>
									</select>
								</c:when>
								<c:otherwise>
									<select name="feemode" id="FeeMode" class="form-control">
										<option value="">请选择</option>
										<option value="1">用户等级</option>
										<option value="2">持有时间</option>
									</select>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<!--转让人等级-->
					<div id="uGadewapper">
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from">转让人等级</label>
							<div class="col-sm-4">
								<c:if test="${isPart==0}">
								<label class="radio-inline">
			 			<input type="radio" name="ugrade" id="uGrade1" value="1" class="insert-ugrade-radio-ugrade"/>全部等级
			 					</label>
								</c:if>
								<label class="radio-inline">
			 			<input type="radio" name="ugrade" id="uGrade2" value="2" class="insert-ugrade-radio-ugrade"/>部分等级
			 		</label>
							</div>
						</div>
							<!--转让人等级-->
						<div class="form-group" id="ugrades_div">
							<label class="col-sm-3 control-label"></label>
							<label class="radio-inline" >
								<input type="checkbox" name="ugrades" id="selectAll"  />全选
							</label>
							<div class="col-sm-6">
							<c:if test="${!empty uGrades}">
							   <c:forEach items="${uGrades}" var="gra" varStatus="vst">
							       <label class="checkbox-inline" style="width:120px">
			 			              <input type="checkbox" name="ugrades" id="ugrades" value="${gra.ugrade}"/>${gra.ugradename}
			 		               </label>
			 		               <c:if test="${vst.count%4==0}"><br></c:if>
							   </c:forEach>
							</c:if>
							</div>
						</div>
					</div>
					<!--持有时间-->
					<div class="form-group" id="HadDay_div">
						<label class="col-sm-3 control-label laber_from" for="HadDay">持有时间</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttornFees[0].hadday" id="HadDay" placeholder="持有时间" class="form-control" />
								<span class="input-group-addon">天</span>
							</div>
						</div>
					</div>
					</div>
					<div id="isADAFeeOn_divtwo">
						<div class="wapperclass">
					<!--债转金额段-->
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="minAttornMoney">债转金额段</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" value="0" readonly="readonly"name="debtAttornFees[0].minattornmoney" id="minAttornMoney" placeholder="债转金额低值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" value="999999999999" readonly="readonly"name="debtAttornFees[0].maxattornmoney" id="maxAttornMoney" placeholder="债转金额高值" class="form-control maxMoney" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="debtBttontwo" class="btn btn-default"><strong>+</strong></button></span>
						</div>
					</div>
					<!--百分比-->
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="AttornRate">百分比</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttornFees[0].attornrate" id="AttornRate" class="form-control" placeholder="按百分比计算" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						<!--收费金额最低和最高-->
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="minFee">手续费最低-最高</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttornFees[0].minfee" id="minFee" class="form-control" placeholder="手续费最低值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttornFees[0].maxfee" id="maxFee" class="form-control" placeholder="手续费最高值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
						</div>
				</div>
				
				<!-- -_--_-我是可耻的分割线-_--_- -->
					<%--<div id="isADAFeeOn_divcysj">
					<!--债转金额段-->
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="minAttornMoney">债转金额段</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttornFees2[0].minattornmoney" id="minAttornMoney" placeholder="债转金额低值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="debtAttornFees2[0].maxattornmoney" id="maxAttornMoney" placeholder="债转金额高值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="debtBttonone" class="btn btn-default"><strong>+</strong></button></span>
						</div>
					</div>
					<!--百分比-->
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="AttornRate">百分比</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttornFees2[0].attornrate" id="AttornRate" class="form-control" placeholder="按百分比计算" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						<!--收费金额最低和最高-->
						<div class="form-group">
							<label class="col-sm-3 control-label laber_from" for="minFee">手续费最低-最高</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttornFees2[0].minfee" id="minFee" class="form-control" placeholder="手续费最低值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="debtAttornFees2[0].maxfee" id="maxFee" class="form-control" placeholder="手续费最高值" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
				   </div>--%>
					<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAudit">资金清算是否需要审核</label>
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
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="debtBtton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
					</div>
				</div>
 </form>
 </div>
 <script type="text/javascript">
   $(function(){
	   $("form").bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: { /*input状态样式图片*/
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: { //验证
				feemode:{
					validators:{
						notEmpty:{
							message:'不能为空'
						}
					}
				},
				ugrades:{
					validators:{
						notEmpty:{
							message:'请至少选择一项'
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
							message: '持有时间不能超过5位数'
						},
						regexp: {
							regexp: /^(0|[1-9]\d*)$/,
							message: '天数只能为正整数'
						}
					}
				},
				'debtAttornFees[0].minattornmoney':{
					validators:{
						notEmpty: {
							message: '金额不能为空'
						},
						regexp: {
							regexp: /^(\d*\.)?\d+$/,
							message: '金额只能为浮点数如100.0或100'
						}
					}
				},
				'debtAttornFees[0].maxattornmoney':{
					validators:{
						notEmpty: {
							message: '金额不能为空'
						},
						regexp: {
							regexp: /^(\d*\.)?\d+$/,
							message: '金额只能为浮点数如100.0或100'
						}
					}
				},
				'debtAttornFees[0].attornrate':{
					validators:{
						notEmpty: {
							message: '百分比不能为空'
						},
						regexp: {
							regexp: /^(\d*\.)?\d+$/,
							message: '百分比为数字10.0或10'
						}
					}
				},
				'debtAttornFees[0].minfee':{
					validators:{
						notEmpty: {
							message: '手续费不能为空'
						},
						regexp: {
							regexp: /^(\d*\.)?\d+$/,
							message: '金额只能为浮点数如100.0或100'
						}
					}
				},
				'debtAttornFees[0].maxfee':{
					validators:{
						notEmpty: {
							message: '手续费不能为空'
						},
						regexp: {
							regexp: /^(\d*\.)?\d+$/,
							message: '金额只能为浮点数如100.0或100'
						}
					}
				},
			}
		});
   })
 </script>
</body>
</html>