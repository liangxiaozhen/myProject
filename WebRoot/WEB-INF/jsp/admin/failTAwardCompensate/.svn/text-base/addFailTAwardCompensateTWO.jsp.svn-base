<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>failTAwardCompensate_Inst</title>
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
<script
	src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
	<script
	src="${pageContext.request.contextPath }/js/validate/jquery.metadata.js"></script>
	<script
	src="${pageContext.request.contextPath }/js/validate/messages_zh.js"></script>
	<link rel="stylesheet" media="screen" href="http://static.runoob.com/assets/jquery-validation-1.14.0/demo/css/screen.css">
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
	});
	//利息补偿方式添加金额段
	$(document)
			.ready(
					function() {
						var MaxInputs = 15; //maximum input boxes allowed  
						var InputsWrapper = $("#InputsWrapperone"); //Input boxes wrapper ID  
						var AddButton = $("#AddMoreFileBoxone"); //Add button ID 

						var x = InputsWrapper.length; //initlal text box count  
						var FieldCount = 1; //to keep track of text box added  

						/* 加载页面后把定额和百分比的div隐藏 */
						$(AddButton)
								.click(
										function(e) //on add input button click  
										{
											if (x <= MaxInputs) //max input box allowed  
											{
												FieldCount++; //text box added increment  
												var oDiv = $('<div class="wapperclassone"></div>');
												oDiv
														.html('<div class="form-group">'
																+ '<label for="inputminMoney" class="col-sm-3 control-label">投标累计金额最低-最高</label>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" name="awardCompensates[' + x + '].minmoneyaward" id="inputminMoney" placeholder="投标累计金额最低" class="form-control" />'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-3">'
																+ '<div class="input-group">'
																+ '<input type="text" name="awardCompensates[' + x + '].maxmoneyaward" id="inputmaxMoney" placeholder="投标累计金额最高" class="form-control" />'
																+ '<span class="input-group-addon">元</span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="col-sm-1">'
																+ '<span><button name="removebutton" id="removebuttonone'+x+'" class="btn btn-default removeclassone"><strong>-</strong></button></span>'
																+ '</div>'
																+ '</div>'
																+ '<div class="form-group">'
																+ '<label class="col-sm-3 control-label">奖品类型</label>'
																+ '<div class="col-sm-3">'
																+ '<select name="awardCompensates[' + x + '].awardno" id="type'+x+'" class="form-control">'
																+ '<option value="">请选择</option>'
																+ '<option value="mbiphone5s'+x+'">iphone5s</option>'
																+ '<option value="mbCNmade'+x+'">国产机</option>'
																+ '<option value="other'+x+'">其他</option>'
																+ '</select>'
																+ '</div>'
																+ '<div class="col-sm-3">'
																+ '<input type="text" placeholder="1"/>份'
																+ '</dvi>'
																+ '</div>');
												$(InputsWrapper).append(oDiv);
												$('form')
														.bootstrapValidator(
																'addField',
																'awardCompensates['
																		+ x
																		+ '].minmoneyaward',
																{
																	validators : {
																		notEmpty : {
																			message : '最低金额不能为空'
																		},
																		regexp : { //匹配规则
																			regexp : /^(\d*\.)?\d+$/,
																			message : '金额必须为浮点数如:100或100.0'
																		}
																	}
																});
												$('form')
														.data(
																'bootstrapValidator')
														.addField(
																'awardCompensates['
																		+ x
																		+ '].maxmoneyaward',
																{
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
												$("#quotainput_div" + x).hide(); //影藏定额
												$("#dayawardrate_div" + x)
														.hide(); //影藏日奖励费率
												x++;
											}
											if (x == MaxInputs) {
												$(this).attr("disabled",
														"disabled");
												return true;
											}
											return false;
										});
						//刪除標籤	
						$("body").on(
								"click",
								".removeclassone",
								function(e) { //user click on remove text  
									var $row = $(this).parents(
											'.wapperclassone'), $option = $row
											.find('[name="option[]"]');
									$row.remove();
									$('form').bootstrapValidator('removeField',
											$option);
								});
					});
</script>
<script type="text/javascript">
$.validator.setDefaults({
	/* debug:true, */
	submitHandler:function(form){
		if($("#istemplet").val() == '0'){
			$("#citySelect").attr("class","error").html('请选择区域').show();
			$("#istemplet").attr("class","error");
			}else{
			$("#istemplet").attr("class","valid");
			$("#citySelect").attr("class","valid").html('success').show();
        alert("submitted");   
        form.submit();
			}
    }
});
$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
  $("#defaultForm").validate({
	  //这里是设置错误提示（不加就使用默认）
	  /* errorPlacement : function(error, element) {
		  if (element.is(":radio"))
		  error.appendTo(element.parent());
		  else if (element.is(":checkbox"))
		  error.appendTo(element.parent().parent());
		  else
		  error.appendTo(element.parent().next());
		  } , */
		  
	    rules: {
	    	awardugrades: {
		        required: "#intUGradetwo:checked",
		        minlength: 1
		      },
		      'awardCompensates[0].minmoneyaward': {
		    	  required:true,
		    	  number:true,
		      },
		      'awardCompensates[0].maxmoneyaward': {
			        required: true,
			        number:true,
			        amountLimit:true
			  },
		      'awardCompensates[0].awardno': {
			        required: true,
			  },
			  isaudit:{
				  required: true,
			  },
			  istemplet:{
				  required: true,
			  },
	    },
	    messages: {
	    	"awardCompensates[0].awardno": "请选择奖品项",
	    	"awardCompensates[0].minmoneyaward":{number:"请输入最低金额"},
	    	"awardCompensates[0].maxmoneyaward":{number:"请输入最高金额"},
	      awardugrades: "至少选择一个会员等级"
	    }
	});
});
 
$.validator.addMethod("amountLimit", function(value, element) {  
	var returnVal = false;  
	var amountStart = $("#inputminMoney").val();
	var amountEnd = $("#inputmaxMoney").val();
	if(parseFloat(amountEnd)>parseFloat(amountStart)){
	returnVal = true;
	}
	return returnVal;   
	},"最高金额不能低于最低金额");
</script>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyph-icon icon-chevron-right"></i>
    <a href="#">标的流标补偿设置页面</a>
</div>
	<div class="container" style="margin-top: 25px;">
		<form class="form-horizontal" role="form" id="defaultForm" action="${pageContext.request.contextPath}/admin/failTAwardCompensate/addNewFailTAwardCompensates.action">
			<!--投标等级-->
			<div id="CompensateOn_div">
				<div class="form-group">
					<label class="col-sm-3 control-label">投标人等级</label>
					<div class="col-sm-3">
						<label class="radio-inline"> <input type="radio"
							name="awardugrade" id="intUGradeone" value="1"
							class="insert-ugrade-radio" />全部等级
						</label> <label class="radio-inline"> <input type="radio"
							name="awardugrade" id="intUGradetwo" value="2"
							class="insert-ugrade-radio" />部分等级
						</label>
					</div>
				</div>
				<!--会员等级-->
				<div class="form-group" id="intugrades_div">
					<label class="col-sm-3 control-label"></label>
					<div class="col-sm-6">
						<c:if test="${!empty uGrades}">
							<c:forEach var="ugs" items="${uGrades}" varStatus="status">
								<label class="checkbox-inline" style="width: 110px;"> <input
									type="checkbox" name="awardugrades" id="intugrades"
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
					<div class="form-group">
						<label for="inputminMoney" class="col-sm-3 control-label">投标累计金额最低-最高</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="awardCompensates[0].minmoneyaward" id="inputminMoney"
									placeholder="投标累计金额最低" class="form-control" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="awardCompensates[0].maxmoneyaward" id="inputmaxMoney"
									placeholder="投标累计金额最高" class="form-control" /> <span
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
						<label class="col-sm-3 control-label">奖品类型</label>
						<div class="col-sm-3">
							<select title="Please select at least two fruits" name="awardCompensates[0].awardno" id="roleidForSelect" class="form-control">
								<option grade="" value="">请选择</option>
								<option grade="1" value="mbiphone6s">iphone6s</option>
								<option grade="2" value="mbxiaomi">小米手机</option>
								<option grade="3" value="other">其他</option>
							</select>
						</div>
						<div class="col-sm-3">
							<input type="text" name="awardCompensates[0].awardcopies" placeholder="1"/>份
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
				<div id="citySelect" style="display:none width:50px height:30px border:1px solid"></div>
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
					<button type="button" class="btn btn-default" onclick="conblck()">返回列表</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	  function conblck(){
		  var url="${pageContext.request.contextPath}/admin/failTAwardCompensate/getfailTAwardCompensateByCondition.action";
		  window.location.href=url;
	  }
	</script>
</body>
<!-- $('#defaultForm').bootstrapValidator({
							message : 'This value is not valid',
							/* excluded : [':disabled', ':hidden', ':not(:visible)'], */
							excluded : [':disabled'],//[':disabled', ':hidden', ':not(:visible)'](bootstrap-validator默认情况下hidden,disabled的组件不验证，可以通过excluded属性更改)
							feedbackIcons : { /*input状态样式图片*/
								valid : 'glyphicon glyphicon-ok',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							fields : { //验证
								awardugrades : {//会员等级
									validators : {
										notEmpty : {
											message : '会员等级不能为空'
										}
									}
								},
								'awardCompensates[0].minmoneyaward': {//投标累计金额最低
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
								'awardCompensates[0].maxmoneyaward': {//投标累计金额最高
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
				                'awardCompensates[0].awardno': {
				                	validators : {
										notEmpty : {
											message : '奖品编号不可为空'
										},
										callback : {
											callback : function(value,
													validator) {
												alert("弹出："+value);
												var objS = document.getElementById("roleidForSelect"),grade = objS.options[objS.selectedIndex].grade;
												return value=grade;
											}
										}
									},
				                    isaudit: {
				                       notEmpty: {
				                            message: '奖品编号不可为空'
				                        }
				                    }
				                },
							}
						})
						 $('#validateBtn').click(function() {
							 $('#defaultForm').bootstrapValidator('validate');
							 });
	}); -->
<!-- on('success.form.bv', function(e) {//点击提交之后
					         // Prevent form submission
					         e.preventDefault();

					         // Get the form instance
					         var $form = $(e.target);

					         // Get the BootstrapValidator instance
					         var bv = $form.data('bootstrapValidator');
					         
					         var tmpSelected = $('#roleidForSelect').val();
					            if(tmpSelected != null){
					                $('#roleid').val(tmpSelected);
					            }else {
					                $('#roleid').val("");
					            }

					    //由于input为hidden，验证会出现一些bug，此处手动验证隐藏的input组件
					            /* $('#defaultForm').data('bootstrapValidator').updateStatus('roleid', 'NOT_VALIDATED').validateField('roleid'); */

					         // Use Ajax to submit form data 提交至form标签中的action，result自定义
					         $.post($form.attr('action'), $form.serialize(), function(result) {
									alert("成功提交");
							});
					     });  -->
					     
					     
					     <!--  $("#failButton").on("click",function(){
      var validate = $("#myform").validate({
        debug: true, //调试模式取消submit的默认提交功能  
        //errorClass: "label.error", //默认为错误的样式类为：error  
        focusInvalid: false, //当为false时，验证无效时，没有焦点响应 
        onkeyup: false,  
        submitHandler: function(form){  //表单提交句柄,为一回调函数，带一个参数：form  
          alert("提交表单");  
          form.submit();  //提交表单  
        },  
        rules:{
          myname:{
            required:true
          },
          email:{
            required:true,
            email:true
          },
          password:{
            required:true,
            rangelength:[3,10]
          },
          confirm_password:{
            equalTo:"#password"
          }          
        },
        messages:{
          myname:{
            required:"必填"
          },
          email:{
            required:"必填",
            email:"E-Mail格式不正确"
          },
          password:{
            required: "不能为空",
            rangelength: $.format("密码最小长度:{0}, 最大长度:{1}。")
          },
          confirm_password:{
            equalTo:"两次密码输入不一致"
          }                  
        }
      });  
    });  -->
</html>