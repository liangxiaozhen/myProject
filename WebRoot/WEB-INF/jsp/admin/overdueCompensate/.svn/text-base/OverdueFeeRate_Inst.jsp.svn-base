<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OverdueFeeRate_Inst</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
			 	$(document).ready(function() {
				var MaxInputs = 15;  //maximum input boxes allowed  
				var InputsWrapper = $("#FeeRate_divtwo"); //Input boxes wrapper ID  
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
							oDiv.html('<div class="form-group">'+
							          '<label class="col-sm-3 control-label">分段开始时间-分段结束时间</label>'+
	     		                      '<div class="col-sm-3">'+
	     			                  '<div class="input-group">'+
	     				              '<input type="text" name="overduefeerates['+x+'].beginday" id="BeginDay" class="form-control"/>'+
	     				              '<span class="input-group-addon">天</span>'+
	     			                  '</div>'+
	     		                      '</div>'+
	     		                      '<div class="col-sm-3">'+
	     			                  '<div class="input-group">'+
	     				              '<input type="text" name="overduefeerates['+x+'].endday" id="endDay" class="form-control"/>'+
	     				              '<span class="input-group-addon">天</span>'+
	     			                  '</div>'+
	     		                      '</div>'+
	     		                      '<div class="col-sm-1">'+
					                  '<span><button id="removeButton" class="btn btn-default removeclass"><strong>-</strong></button></span>'+
				                      '</div>'+
				                      '</div>'+
				                      '<div class="form-group">'+
	     		                      '<label class="col-sm-3 control-label">滞纳金率</label>'+
	     		                      '<div class="col-sm-3">'+
	     		                      '<div class="input-group">'+
	     			                  '<input type="text" name="overduefeerates['+x+'].feerate" id="feeRate" class="form-control"/>'+
	     		                      '<span class="input-group-addon">%</span>'+
	     			                  '</div>'+
	     		                      '</div>'+
	     	                          '</div>');
							$(InputsWrapper).append(oDiv);
							$('form').bootstrapValidator('addField', 'overduefeerates['+x+'].beginday', {
								validators: {
									notEmpty: {
										message: '天数不能为空'
									},
									regexp: {
									 regexp: /^(0|[1-9]\d*)$/,
									 message: '天数只能为正整数'
								    }
								}
							});
							$('form').data('bootstrapValidator').addField('overduefeerates['+x+'].endday', {
								validators: {
									notEmpty: {
										message: '天数不能为空'
									},
									regexp: {
									 regexp: /^(0|[1-9]\d*)$/,
									 message: '天数只能为正整数'
								    }
								}
							});
							$('form').data('bootstrapValidator').addField('overduefeerates['+x+'].feerate', {
								validators: {
									notEmpty: {
										message: '滞纳金率不能为空'
									},
									regexp: { //匹配规则
										regexp: /^(\d*\.)?\d+$/,
										message: '滞纳金率必须为浮点数如:100或100.0'
									}
								}
							});
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
			});
			$(function(){
				$("#FeeRate_div").hide();//等比
				$("#FeeRate_divtwo").hide();//不等比
				$("#feeRateType").change(function(){
					if($(this).val()==1){//等比例
						$("#endDaythree").val("100000");
						$("#FeeRate_div").show();
						$("#FeeRate_divtwo input").val("");
						$("#FeeRate_divtwo").hide();
					}else if($(this).val()==2){//不等比
						$("#FeeRate_divtwo").show();
						$("#FeeRate_div input").val("");
						$("#FeeRate_div").hide();
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
	<a href="#">标的逾期</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">逾期滞纳金费率设置</a>
</div>
   	<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/admin/overdueCompensate/insertoverdueFeeRate.action">
	     <div class="container" style="margin-top: 25px;">
	     	<!--费率计算方式-->
			 <input type="hidden" name="tid" value="${tid}" id="tid">
			 <div class="form-group">
	     		<label class="col-sm-3 control-label">费率计算方式</label>
	     		<div class="col-sm-3">
	     			<select name="feeratetype" id="feeRateType" class="form-control">
	     				<option value="">请选择</option>
	     				<option value="1">等比例</option>
	     				<option value="2">不等比例</option>
	     			</select>
	     		</div>
	     	</div>
	     	<!--分段开始时间-->
	     	<div id="FeeRate_div">
	     	<div class="form-group">
	     		<label class="col-sm-3 control-label">分段开始时间-分段结束时间</label>
	     		<div class="col-sm-3">
	     			<div class="input-group">
	     				<input type="text" name="beginday" id="BeginDay" class="form-control"/>
	     				<span class="input-group-addon">天</span>
	     			</div>
	     		</div>
	     		<div class="col-sm-3">
	     			<div class="input-group">
	     				<input type="text" name="endday" id="endDaythree" class="form-control"  readonly="readonly"/>
	     				<span class="input-group-addon">天</span>
	     			</div>
	     		</div>
	     	</div>
	     	<!--迟纳金率-->
	     	<div class="form-group">
	     		<label class="col-sm-3 control-label">滞纳金率</label>
	     		<div class="col-sm-3">
	     		<div class="input-group">
	     		     <input type="text" name="feerate" id="feeRate" class="form-control"/>
	     		     <span class="input-group-addon">%</span>
	     		</div>
	     		</div>
	     	</div>
	       </div>
	       <!-- >>>>>>>>+++<<<<<<<< -->
	       	<!--分段开始时间-->
	     	<div id="FeeRate_divtwo">
	     	<div class="form-group">
	     		<label class="col-sm-3 control-label">分段开始时间-分段结束时间</label>
	     		<div class="col-sm-3">
	     			<div class="input-group">
	     				<input type="text" name="overduefeerates[0].beginday" id="BeginDay" class="form-control"/>
	     				<span class="input-group-addon">天</span>
	     			</div>
	     		</div>
	     		<div class="col-sm-3">
	     			<div class="input-group">
	     				<input type="text" name="overduefeerates[0].endday" id="endDay" class="form-control"/>
	     				<span class="input-group-addon">天</span>
	     			</div>
	     		</div>
	     		<div class="col-sm-1">
					<span><button id="AddMoreFileBox" class="btn btn-default"><strong>+</strong></button></span>
				</div>
	     	</div>
	     	<!--迟纳金率-->
	     	<div class="form-group">
	     		<label class="col-sm-3 control-label">滞纳金率</label>
	     		<div class="col-sm-3">
	     		<div class="input-group">
	     			<input type="text" name="overduefeerates[0].feerate" id="feeRate" class="form-control"/>
	     			<span class="input-group-addon">%</span>
	     			</div>
	     		</div>
	     	</div>
	       </div>
	     	<!--备注-->
	     	<div class="form-group">
	     		<label class="col-sm-3 control-label">备注</label>
	     		<div class="col-sm-3">
	     			<textarea rows="3" class="form-control" name="remark"> </textarea>
	     		</div>
	     	</div>
	     	<div class="form-group">
	     		<label class="col-sm-3 control-label"></label>
	     		<div class="col-sm-1">
	     			<input type="submit" value="提交" class="btn btn-default"/>
	     		</div>
	     		<div class="col-sm-1">
	     			<input type="button" value="返回" onclick="continuteFinish();"  class="btn btn-default"/>
	     		</div>
	     	</div>
	     </div>
	    </form>
<script>
		/*返回*/
        function continuteFinish() {
            var id=$("#tid").val();
            var url = "${pageContext.request.contextPath }/admin/tenderItem/continuteFinish.action?id=" + id;
            var explorer = window.navigator.userAgent;
            //ie
            if (explorer.indexOf("MSIE") >= 0) {
                url = "${pageContext.request.contextPath }/admin/tenderItem/continuteFinish.action?id=" + id;
            }
            window.location.href = url;
        }
</script>
</body>
</html>