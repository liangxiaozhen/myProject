<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>overdueRecovery_Inst</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
<script type="text/javascript">
$(function(){
	$("#quotainput_div").hide(); //影藏定额
	$("#dayawardrate_div").hide(); //影藏日奖励费率 
	//$("#precoveryon").hide();//隱藏平台追偿设置信息
	/* $("#selecisprecoveryon").change(function(){
	    var isprecoveryon=$(this).val();
	    if("0"==isprecoveryon||""==isprecoveryon){
		     $("#precoveryon input").val("");
		     $("#precoveryon").hide();
	    }else if("1"==isprecoveryon){
		   $("#precoveryon").show();
	       }
	  }); */
	//站岗利息补偿方式	
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
});
$(document).ready(function() {
	var MaxInputs = 15;  //maximum input boxes allowed  
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
				oDiv.html('<div class="form-group">'
		                   +'<label class="control-label col-sm-3" for="dayawardrate">借款人逾期本金低值与高值</label>'
		                   +'<div class="col-sm-3">'
			               +'<div class="input-group">'
				           +'<input type="text" readonly="readonly" name="recoveries['+x+'].ocminmoney" id="inputocminmoney" placeholder="借款人逾期本金低值" class="form-control" />'
				           +'<span class="input-group-addon">元</span>'
			               +'</div>'
		                   +'</div>'
		                   +'<div class="col-sm-3">'
			               +'<div class="input-group">'
				           +'<input type="text" readonly="readonly" value="999999999999" name="recoveries['+x+'].ocmaxmoney" id="inputocmaxmoney" placeholder="借款人逾期本金高值" class="form-control maxMoney" />'
				           +'<span class="input-group-addon">元</span>'
			               +'</div>'
		                   +'</div>'
		                   +'<div class="col-sm-1">'
			               +'<span><button id="removeButton" class="btn btn-default removeclass"><strong>-</strong></button></span>'
		                   +'</div>'
	                       +'</div>'
	                       +'<div class="form-group">'
		                   +'<label class="control-label col-sm-3" for="istemplet">类型</label>'
		                   +'<div class="col-sm-3">'
			               +'<select id="type'+x+'" name="type" class="form-control">'
				           +'<option value="">请选择</option>'
				           +'<option value="iequota'+x+'">定额</option>'
				           +'<option value="iepercent'+x+'">百份比</option>'
			               +'</select>'
		                   +'</div>'
	                       +'</div>'
	                       +'<div class="form-group" id="quotainput_div'+x+'">'
		                   +'<label class="control-label col-sm-3" for="inputoccquota">定额</label>'
		                   +'<div class="col-sm-3">'
			               +'<input type="text" name="recoveries['+x+'].occquota" id="inputoccquota" placeholder="定额收费金额" class="form-control">'
		                   +'</div>'
	                       +'</div>'
	                       +'<div id="dayawardrate_div'+x+'">'
	                       +'<div class="form-group">'
		                   +'<label class="control-label col-sm-3" for="inputoccquota">追偿收费费率</label>'
		                   +'<div class="col-sm-3">'
			               +'<div class="input-group">'
			               +'<input type="text" name="recoveries['+x+'].toccrate" id="inputtoccrate" placeholder="追偿收费费率" class="form-control">'
			               +'<span class="input-group-addon">%</span>'
			               +'</div>'
		                   +'</div>'
	                       +'</div>'
	                       +'<div class="form-group">'
		                   +'<label class="control-label col-sm-3" for="inputoccquota">追偿金额最低及最高收费</label>'
		                   +'<div class="col-sm-3">'
			               +'<div class="input-group">'
			               +'<input type="text" name="recoveries['+x+'].mincfees" id="inputmincfees" placeholder="追偿金额最低收费" class="form-control">'
			               +'<span class="input-group-addon">元</span>'
			               +'</div>'
		                   +'</div>'
		                   +'<div class="col-sm-3">'
			               +'<div class="input-group">'
			               +'<input type="text" name="recoveries['+x+'].maxcfees" id="inputmaxcfees" placeholder="追偿金额最高收费" class="form-control">'
			               +'<span class="input-group-addon">元</span>'
			               +'</div>'
		                   +'</div></div></div>'
				);
				$(InputsWrapper).append(oDiv);
				var lastInput=$(InputsWrapper).children().last().find("input").eq(1);
				var lastBeforeInput=$(InputsWrapper).children().last().prev().find("input").eq(1);
				lastBeforeInput.attr("readonly",false);
				lastBeforeInput.val("");
			/*	$('form').bootstrapValidator('addField', 'recoveries[' + x + '].ocminmoney', {
					validators: {
						notEmpty: {
							message: '最低金额不能为空'
						},
						regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '金额必须为浮点数如:100或100.0'
						}
					}
				});*/
				$('form').bootstrapValidator('addField', 'type', {
					validators: {
						notEmpty: {
							message: '不能为空'
						}
					}
				})
				$('form').data('bootstrapValidator').addField('recoveries[' + x + '].ocmaxmoney', {
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
				$('form').data('bootstrapValidator').addField('recoveries[' + x + '].occquota', {
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
				$('form').data('bootstrapValidator').addField('recoveries[' + x + '].toccrate', {
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
				$('form').data('bootstrapValidator').addField('recoveries[' + x + '].mincfees', {
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
				$('form').data('bootstrapValidator').addField('recoveries[' + x + '].maxcfees', {
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
				$("#quotainput_div"+x).hide(); //影藏定额
	            $("#dayawardrate_div"+x).hide(); //影藏日奖励费率
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
});

$(function(){
	  $('form').bootstrapValidator({
          message: 'This value is not valid',
         feedbackIcons: {/*input状态样式图片*/
         valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
         validating: 'glyphicon glyphicon-refresh'
       },
  fields: {/*验证：规则*/
	  'recoveries[0].ocminmoney': {//借款人逾期本金低值与高值
          validators: {
              notEmpty: {//非空
                  message: '不能为空'
              },
              regexp:{//匹配规则
            	  regexp:/^(\d*\.)?\d+$/,
            	  message:'金额必须为浮点数'
            }
          }
      },
      'recoveries[0].ocmaxmoney':{
    	  validators: {
              notEmpty: {//非空
                  message: '不能为空'
              },
              regexp:{//匹配规则
            	  regexp:/^(\d*\.)?\d+$/,
            	  message:'金额必须为浮点数'
            }
          }
      },
      'recoveries[0].occquota':{
    	  validators: {
              notEmpty: {//非空
                  message: '不能为空'
              },
              regexp:{//匹配规则
            	  regexp:/^(\d*\.)?\d+$/,
            	  message:'定额必须为浮点数'
            }
          }
      },
      'recoveries[0].toccrate':{
    	  validators: {
              notEmpty: {//非空
                  message: '不能为空'
              },
              regexp:{//匹配规则
            	  regexp:/^(\d*\.)?\d+$/,
            	  message:'费率必须为小数'
            }
          }
      },
      'recoveries[0].mincfees':{
    	  validators: {
              notEmpty: {//非空
                  message: '不能为空'
              },
              regexp:{//匹配规则
            	  regexp:/^(\d*\.)?\d+$/,
            	  message:'金额必须为浮点数'
            }
          }
      },
	  'type':{
		  validators: {
			  notEmpty: {//非空
				  message: '不能为空'
			  }
		  }
	  },
      'recoveries[0].maxcfees':{
    	  validators: {
              notEmpty: {//非空
                  message: '不能为空'
              },
              regexp:{//匹配规则
            	  regexp:/^(\d*\.)?\d+$/,
            	  message:'金额必须为浮点数'
            }
          }
      }
  }
  });
})
</script>
</head>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的逾期</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的逾期平台追偿设置</a>
</div>
        <div class="container" style="margin-top: 25px;">
           <form class="form-horizontal" role="form" id="defaultForm" method="post" action="${pageContext.request.contextPath}/admin/overdueCompensate/insertoverdueRecovery.action">
                 <!-- <div class="form-group">
					<label for="selecisprecoveryon" class="col-sm-3 control-label">平台追偿费开关设置</label>
					<div class="col-sm-3">
						<select name="isprecoveryon" id="selecisprecoveryon" class="form-control">
							<option value="">请选择</option>
							<option value="1">开</option>
							<option value="0">关</option>
						</select>
					</div>
				</div> -->
				<!--平台追偿费收款人-->
				<div id="precoveryon">
				<div class="form-group">
					<input type="hidden" name="tid" value="${tid}">
					<label class="col-sm-3 control-label" for="">平台追偿费收款人</label>
					<div class="col-sm-3">
						<input type="text" name="pmiscrecman" class="form-control" id="inputpmiscrecman" placeholder="平台追偿费收款人" value="MDT000001" readonly/>
					</div>
				</div>
				<input type="hidden" name="pmiscrecmanid" id="PMiscRecManId" value="0"/>
				<!--借款人逾期本金-->
				<div id="InputsWrapper">
					<div class="wapperclass">
					<div class="form-group">
						<label class="control-label col-sm-3" for="dayawardrate">借款人逾期本金低值与高值</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" readonly="readonly" value="0" name="recoveries[0].ocminmoney" id="inputocminmoney" placeholder="借款人逾期本金低值" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text"  readonly="readonly"value="999999999999" name="recoveries[0].ocmaxmoney" id="inputocmaxmoney" placeholder="借款人逾期本金高值" class="form-control maxMoney" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button id="AddMoreFileBox" class="btn btn-default"><strong>+</strong></button></span>
						</div>
					</div>
					<!--类型选择-->
					<div class="form-group">
						<label class="control-label col-sm-3" for="istemplet">类型</label>
						<div class="col-sm-3">
							<select id="type" name="type" class="form-control">
								<option value="">请选择</option>
								<option value="iequota">定额</option>
								<option value="iepercent">百份比</option>
							</select>
						</div>
					</div>
					<!--类型选定额-->
					<div class="form-group" id="quotainput_div">
						<label class="control-label col-sm-3" for="inputoccquota">定额</label>
						<div class="col-sm-3">
							<input type="text" name="recoveries[0].occquota" id="inputoccquota" placeholder="定额收费金额" class="form-control">
						</div>
					</div>
					<!--类型选百分比-->
					<div id="dayawardrate_div">
					<div class="form-group">
						<label class="control-label col-sm-3" for="inputoccquota">追偿收费费率</label>
						<div class="col-sm-3">
							<div class="input-group">
							<input type="text" name="recoveries[0].toccrate" id="inputtoccrate" placeholder="追偿收费费率" class="form-control">
							<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<!--类型选百分比最低及最高收費-->
					<div class="form-group">
						<label class="control-label col-sm-3" for="inputoccquota">追偿金额最低及最高收费</label>
						<div class="col-sm-3">
							<div class="input-group">
							<input type="text" name="recoveries[0].mincfees" id="inputmincfees" placeholder="追偿金额最低收费" class="form-control">
							<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
							<input type="text" name="recoveries[0].maxcfees" id="inputmaxcfees" placeholder="追偿金额最高收费" class="form-control">
							<span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
				</div>
				</div>
					</div>
				</div>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
						<select name="isaudit" class="form-control" id="isAudit">
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
						<button type="submit" class="btn btn-default" id="overButton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>