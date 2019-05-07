<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FailTAwardCompensate_Inst</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
<script type="text/javascript">
$(function(){
	$("#intugrades_divtwo").hide();//隐藏全部等级
	$("#intUGradeAwardone").attr("checked", "checked");
	//全部等级与选择等级的change监听事件
	$(".insert-ugrade-radiotwo").change(function() {
		var $radioVal = $(".insert-ugrade-radiotwo:checked").val();
		if ($radioVal == 1) {
			$("#intugrades_divtwo").hide();
			$("#intugrades_divtwo :checkbox").each(function() {
				this.checked = false;
			});
		} else {
			$("#intugrades_divtwo").show();
		}
	});
	//全选按钮选中当前全部等级
	$("#selectAll").click(function () {
		if(this.checked){
			$("#intugrades_divtwo  :checkbox").prop("checked",true);
		}else{
			$("#intugrades_divtwo  :checkbox").prop("checked",false);
		}
	})
	/* //奖品补偿方式开关
	$("#isAwardCompensateOn").change(function() {
		if ($(this).val() == 1) {
			$("#intUGradeAwardone").val("1");
			$("#intUGradeAwardtwo").val("2");
			$("#AwardCompensateOn_div").show();
		} else {
			$("#intugrades_divtwo :checkbox").each(function() {
				this.checked = false;
			});
			$("#AwardCompensateOn_div input").val("");
			$("#AwardCompensateOn_div select").val("");
			$("#AwardCompensateOn_div").hide();
		}
	}); */
})
</script>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyph-icon icon-chevron-right"></i>
    <a href="#">标的流标奖品补偿设置</a>
</div>
<div class="container" style="margin-top: 25px;">
    <form action="${pageContext.request.contextPath}/admin/failTCompensate/insertfailTAwardCompensate.action" method="post" role="form" class="form-horizontal" id="bookFormtwo">
 		<!--奖品补偿方式-->
			<!--会员等级-->
			<div id="AwardCompensateOn_div">
				<div class="form-group">
					<input type="hidden" name="tid" value="${tid}">
					<label class="col-sm-3 control-label">会员等级</label>
					<div class="col-sm-3">
						<c:if test="${isPart==0}">
						<label class="radio-inline"> <input type="radio"
							name="awardugrade" id="intUGradeAwardone" value="1"
							class="insert-ugrade-radiotwo" />全部等级
						</label>
							</c:if>
						<label class="radio-inline"> <input type="radio"
							name="awardugrade" id="intUGradeAwardtwo" value="2"
							class="insert-ugrade-radiotwo" />部分等级
						</label>
					</div>
				</div>
				<!--会员等级-->
				<div class="form-group" id="intugrades_divtwo">
					<label class="col-sm-3 control-label"></label>
					<label class="radio-inline" >
						<input type="checkbox" name="awardugrades" id="selectAll"  />全选
					</label>
					<div class="col-sm-6">
						<c:if test="${!empty uGrades}">
							<c:forEach items="${uGrades}" var="ade" varStatus="statctow">
								<label class="checkbox-inline" style="width: 110px;"> <input
									type="checkbox" name="awardugrades" id="intugradeawards"
									value="${ade.ugrade}" />${ade.ugradename}
								</label>
								<c:if test="${statctow.count%4==0}">
									<br>
								</c:if>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<!--分段最低投资金额-最高金额-->
				<div id="InputsWrappertwo" class="wapperclass">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="minMoneyAward">分段投资最低金额-最高金额</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text"value="0" readonly="readonly" name="awardCompensates[0].minmoneyaward" id="minMoneyAward"
									class="form-control" placeholder="分段投资金额最低" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" value="999999999999" readonly="readonly" name="awardCompensates[0].maxmoneyaward" id="maxMoneyAward"
									class="form-control maxMoney" placeholder="分段投资金额最高" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button name="AddMoreFileBoxtwo" id="AddMoreFileBoxtwo" class="btn btn-default addButtontwo">
									<strong>+</strong>
								</button></span>
						</div>
					</div>
					<!--奖品名称-->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="AwardName">奖品名称</label>
						<div class="col-sm-3">
						<c:if test="${!empty awards}">
							<select name="awardCompensates[0].awardno" id="AwardName" class="form-control">
							<option value="">请选择</option>
							<c:forEach items="${awards}" var="ard">
							   <option value="${ard.ano},${ard.aname}">${ard.aname}</option>
							</c:forEach>
							</select>
						</c:if>
						</div>
					</div>
				</div>
				
				<div id="InputsWrapper" class="hide form-remove wapperclass">
					<div class="form-group">
						<label class="col-sm-3 control-label" for="minMoneyAward">分段投资最低金额-最高金额</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" readonly="readonly" name="minmoneyaward" id="minMoneyAward" class="form-control" placeholder="分段投资金额最低" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" value="999999999999" readonly="readonly" name="maxmoneyaward" id="maxMoneyAward" class="form-control " placeholder="分段投资金额最高" />
								   <span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-1">
							<span><button name="AddMoreFileBoxtwo" class="btn btn-default removeButtontwo"><strong>-</strong></button></span>
						</div>
					</div>
					<!--奖品名称-->
					<div class="form-group">
						<label class="col-sm-3 control-label" for="AwardName">奖品名称</label>
						<div class="col-sm-3">
						<c:if test="${!empty awards}">
							<select name="awardno" id="AwardName" class="form-control">
							<option value="">请选择</option>
							<c:forEach items="${awards}" var="ard">
							   <option value="${ard.ano},${ard.aname}">${ard.aname}</option>
							</c:forEach>
							</select>
						</c:if>
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
					<button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
				</div>
			</div>
			    </form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var maxMoneyAward = {
		validators:{
			notEmpty:{
				message:'不能为空'
			},
			regexp: {
				regexp: /^(\d*\.)?\d+$/,
				message: '金额必须为浮点数如100.0或100'
			}
		}
	},
	AwardName = {
		validators:{
			notEmpty:{
				message:'不能为空'
			}
		}
	},
	awardugrades={
		validators:{
			notEmpty:{
				message:'请至少选择一个'
			}	
		}	
	},
	bookIndex = 0;
	
	$("#bookFormtwo").bootstrapValidator({
		framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
             fields: {
                'awardCompensates[0].minmoneyaward': minMoneyAward,
                'awardCompensates[0].maxmoneyaward': maxMoneyAward,
                'awardCompensates[0].awardno': AwardName,
                'awardugrades':awardugrades
            }
	})
	
	  // Add button click handler
        .on('click', '.addButtontwo', function() {
			var lastBeforeInput=$(InputsWrapper).prev().find("input").eq(1);
			lastBeforeInput.attr("readonly",false);
			lastBeforeInput.addClass("maxMoney");
			lastBeforeInput.val("");
            bookIndex++;
            var $template = $('#InputsWrapper'),
                $clone    = $template
                                .clone()
                                .removeClass('hide')
                                .removeAttr('id')
                                .attr('data-book-index', bookIndex)
                                .insertBefore($template);

            // Update the name attributes
            $clone
                .find('[name="minmoneyaward"]').attr('name', 'awardCompensates[' + bookIndex + '].minmoneyaward').end()
                .find('[name="maxmoneyaward"]').attr('name', 'awardCompensates[' + bookIndex + '].maxmoneyaward').end()
                .find('[name="awardno"]').attr('name', 'awardCompensates[' + bookIndex + '].awardno').end();

            // Add new fields
            // Note that we also pass the validator rules for new field as the third parameter
            $('#bookFormtwo')
                .bootstrapValidator('addField', 'awardCompensates[' + bookIndex + '].maxmoneyaward', maxMoneyAward)
                .bootstrapValidator('addField', 'awardCompensates[' + bookIndex + '].awardno', AwardName);

        })
            // Remove button click handler
        .on('click', '.removeButtontwo', function() {
            var $row  = $(this).parents('.form-remove'),
                index = $row.attr('data-book-index');

            // Remove fields
			var bfInput=$(this).parents('.wapperclass').prev().find("input").eq(1);
			var ntInput=$(this).parents('.wapperclass').next().find("input").eq(0);
			ntInput.val(bfInput.val());

            // Remove element containing the fields
            $row.remove();
			bookIndex--;
			var lastInput=$("#InputsWrapper").prev().find("input").eq(1);
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

</script>
</body>
</html>