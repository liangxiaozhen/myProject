<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OverdueCompensate_Insert</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
<script type="text/javascript">
$(function(){
	//$("#upayon").hide();//隱藏会员垫付规则
	/*初始化默认选中全部等级 */
    $("#ugradeone").attr("checked","checked");
    $("#insert-ugrade-checkbox-div").hide();//隐藏会员等级
	
	/* 是否开通会员垫付开关change事件 */
   /*  $("#inputisupayon").change(function(){
      if($(this).val()==0||$(this).val()==""){
	    $("#upayon input").val("");
	    $("#insert-ugrade-checkbox-div :checkbox").each(function(){
		this.checked=false;
	    });
	    $("#upayon").hide();
     }else if($(this).val()==1){
	   $("#ugradeone").val("1");
	   $("#ugradetwo").val("2");
	   $("#upayon").show();
     }
  }); */
  
  	//全部等级与选择等级的change监听事件
   $(".insert-ugrade-radio").change(function() {
    var $radioVal = $(".insert-ugrade-radio:checked").val();
    if ($radioVal == 1) {
    $("#insert-ugrade-checkbox-div").hide();
    $("#insert-ugrade-checkbox-div :checkbox").each(function(){
	this.checked=false; 
    });
    } else {
    $("#insert-ugrade-checkbox-div").show();
    }
    });

  	$('#defaultForm').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: { /*input状态样式图片*/
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: { //验证
			graceperiod:{//逾期宽限期
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					stringLength:{
						max:16,
						message:'长度不能超过16位'
					},
					regexp:{
						regexp:/^(0|[1-9]\d*)$/,
						message:'天数只能为正整数'
					}
				}
			},
			daylatefeerate:{//日滞纳金率
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金率为小数如10.0或10'
					}
				}
			},
			ocminmoney:{//分段逾期本金低值
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					}
				}
			},
			ocmaxmoney:{//分段逾期本金高值
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					},
					callback:{
						message:'本金高值不能低于低值',
						callback:function(value,validator){
							var minmoney=$("#inputocminmoney").val(),
							sum=parseFloat(minmoney);
							return value>=sum;
						}
					}
				}
			},
			occquota:{//逾期本金追偿定额收费金额
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					}
				}
			},
			toccrate:{//逾期本金追偿收费费率
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '费率为小数如10.0或10'
					}
				}
			},
			mincfees:{//逾期本金追偿该段金额最低收费
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					}
				}
			},
			maxcfees:{//逾期本金追偿该段金额最高收费
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					},
					callback:{
						message:'本金高值不能低于低值',
						callback:function(value,validator){
							var mincfees=$("#inputmincfees").val(),
							sum=parseFloat(mincfees);
							return value>=sum;
						}
					}
				}
			},
			pfprincipalrate:{//本金垫付比例
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '比例为小数如10.0或10'
					}
				}
			},
			maxpfprincipal:{//本金垫付最高金额
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					}
				}
			},
			pfintrate:{//利息垫付比例
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '比例为小数如10.0或10'
					}
				}
			},
			maxpfint:{//利息垫付最高金额
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					}
				}
			},
			latefeerate:{//滞纳金垫付比例
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '比例为小数如10.0或10'
					}
				}
			},
			maxlatefee:{//滞纳金垫付最高金额
				validators:{
					notEmpty:{
						message:'不能为空'
					},
					regexp: {
						regexp: /^(\d*\.)?\d+$/,
						message: '金额为浮点数如10.0或10'
					}
				}
			},
		}
	});
	/* $("#overButton").click(function(){
		$('#defaultForm').bootstrapValidator('validate');
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
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的逾期</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的逾期代偿设置</a>
</div>
	 <div class="container" style="margin-top: 25px;">
			<form class="form-horizontal" role="form" id="defaultForm" method="post" action="${pageContext.request.contextPath}/admin/overdueCompensate/insertOverdueCompensate.action">
				<input type="hidden" name="tid" value="${tid}">
				<!--逾期代偿人-->
				<div class="form-group">
					<label for="sleccmanno" class="col-sm-3 control-label">逾期代偿人</label>
					<div class="col-sm-3">
						<input type="text" name="cmanno" id="inputcmanno" placeholder="逾期代偿人" class="form-control" value="MDT000001"/>
					    <input type="hidden" name="cmanid" id="CManId" value="0"/>
					</div>
				</div>
				<!--会员垫付开关-->
				<!-- <div class="form-group">
					<label class="control-label col-sm-3" for="isupayon">会员垫付开关</label>
					<div class="col-sm-3">
						<select name="isupayon" id="inputisupayon" class="form-control">
							<option value="">请选择</option>
							<option value="1">开</option>
							<option value="0">关</option>
						</select>
					</div>
				</div> -->
				<!--会员等级-->
				<div id="upayon">
				<div class="form-group">
					<label class="col-sm-3 control-label" for="">会员等级</label>
					<div class="col-sm-5">
					<label class="radio-inline">
						<input type="radio" name="ugrade" id="ugradeone" value="1" class="insert-ugrade-radio"/>全部等级
					</label>
					<label class="radio-inline">
						<input type="radio" name="ugrade" id="ugradetwo" value="2" class="insert-ugrade-radio"/>部分等级
					</label>
					</div>
				</div>
				<!--会员等级列表-->
				<div class="form-group" id="insert-ugrade-checkbox-div">
					<label class="col-sm-3 control-label"></label>
					<div class="col-sm-6">
					<c:if test="${!empty uGrades}">
					      <c:forEach items="${uGrades}" var="ade" varStatus="status">
					         <label class="checkbox-inline" style="width:110px;">
						 	 <input type="checkbox" name="ugrades" id="inputugrades" value="${ade.ugrade}"/>${ade.ugradename}
						  </label>
						  <c:if test="${status.count%4==0}"><br></c:if>
					      </c:forEach>
					</c:if>
					</div>
				</div>
				<!--本金垫付规则-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="inutpfprincipalrate">本金垫付比例-本金垫付最高金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="pfprincipalrate" id="inutpfprincipalrate" placeholder="本金垫付比例" class="form-control"/>
							<span class="input-group-addon">%</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxpfprincipal" id="inutpmaxpfprincipal" placeholder="本金垫付最高金额" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--利息垫付规则-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="inutppfintrate">利息垫付比例-利息垫付最高金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="pfintrate" id="inutppfintrate" placeholder="利息垫付比例" class="form-control"/>
							<span class="input-group-addon">%</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxpfint" id="inutpmaxpfint" placeholder="利息垫付最高金额" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--滞纳金垫付垫付规则-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="inutplatefeerate">滞纳金垫付比例-滞纳金垫付最高金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="latefeerate" id="inutplatefeerate" placeholder="滞纳金垫付比例" class="form-control"/>
							<span class="input-group-addon">%</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxlatefee" id="inutpmaxpfint" placeholder="滞纳金垫付最高金额" class="form-control"/>
							<span class="input-group-addon">元</span>
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