<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="calendar/WdatePicker.js"></script>
<title>RiskGuarantyMoney_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/riskguarantymoney/updateRiskGuarantyMoney.js"></script>

<script type="text/javascript">
$(function(){
	/*如果收费类型是:结标收取 */
	if($("#chargetype").val()==1){
		$("#chargetype2").hide();
		/*如果收费类型是:投标时收取 */
	}else{
			$("#chargetype1").hide();
			$("#chan_shu").hide();
			//获取radio当前选择的状态
			var $radioVal = $("input[name='ugrade']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 1) {
				$("#insert-ugrade-checkbox-div").hide();
			} else {
				$("#insert-ugrade-checkbox-div").show();
			}
	};
	
	var type=$("#type").find("option:selected").val();
	if("iequota"==type){
		$("#iepercent").hide();
		$("#iequota").show();
	}else if("iepercent"==type){
		$("#iequota").hide();
		$("#iepercent").show();
	}
	
	//结标收取与投标时收取选择改变时的事件
	$("#chargetype").change(function() {
		if ($(this).val() == 1) {
			$("#chargetype2").hide();
			$("#chargetype2 input").val("");
			$("#insert-ugrade-checkbox-div :checkbox").each(function(){
				this.checked=false;
			});
			$("#chargetype1").show();
		} else if($(this).val() == 2){
			//隐藏结标收取的div 并清空文本值
			$("#chargetype1 input").val("");
			$("#chargetype1").hide();
			$("#chan_shu input").val("");
			$("#chan_shu").hide();
			
			$("#selectAll").val("1");
			$("#selectActivity").val("2");
			$("#selectActivity").attr("checked","checked");
			$("#chargetype2").show();
			
			$("#insert-ugrade-checkbox-div").hide();
		}else{
			$("#chargetype1").hide();
			$("#chargetype2").hide();
			$("#chan_shu").hide();
		}
	});
	
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
	$("#submitBtu").click(function(){
			if(check().form()){
				$("form:first").submit();
			}
	});
});

	function callback(){
		   window.location.href="${pageContext.request.contextPath }/admin/riskGuarantyMoney/selectRiskGuarantyMoneyByCondition.action";
	}
</script>
	<style type="text/css">
	#id{
		margin:40px;
	} 
    hr{
		margin: 5px;
	} 
</style>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的风险保证金修改页面</a>
</div>
<div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" id="defaultForm" action="${pageContext.request.contextPath}/admin/riskGuarantyMoney/updateRiskGuarantyMoney.action">
				<!--风险保证金收款人-->
				<div class="form-group">
					<label for="RGMRecMan" class="col-sm-3 control-label laber_from">风险保证金收款人</label>
					<div class="col-sm-3">
						<input type="text" name="rgmrecman" id="RGMRecMan" placeholder="风险保证金收款人" class="form-control" value="${riskGuarantyMoney.rgmrecman}" />
					</div>
				</div>
				<input type="hidden" name="rgmrecmanid" id="RGMRecManId" value="0" />
				<!--风险保证金收费方式设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="chargeType">收费类型</label>
					<div class="col-sm-3">
						<c:if test="${!empty riskGuarantyMoney.chargetype}">
						 <select name="chargetype" class="form-control">
						      <c:if test="${riskGuarantyMoney.chargetype eq 1}">
						         <option value="1">结标收取</option>
						      </c:if>
						      <c:if test="${riskGuarantyMoney.chargetype eq 2}">
						         <option value="2">投标时收取</option>
						      </c:if>
						 </select>
						</c:if>
					</div>
				</div>
				<!--结标后收取-->
				<c:if test="${riskGuarantyMoney.chargetype eq 1}" >
				<c:forEach items="${riskGuarantyMoneys}" var="mon" varStatus="staues">
				<input type="hidden" name="riskGuarantyMoneys[${staues.index}].id" value="${mon.id}"/>
				  <div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minRGMMoney">结标分段金额低值-高值</label>
					<div class="col-sm-3">
						<div class="input-group">
							<!--结标分段金额低值及高值-->
							<input type="text" name="riskGuarantyMoneys[${staues.index}].minrgmmoney" id="minRGMMoney"  value="${mon.minrgmmoney}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="riskGuarantyMoneys[${staues.index}].maxrgmmoney" id="maxRGMMoney" value="${mon.maxrgmmoney}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				  </div>
				   <c:if test="${!empty mon.rgmquota}">
				     <div class="form-group" id="quotainput_div">
					   <label class="col-sm-3 control-label laber_from" for="RGMQuota">风险保证金定额</label>
					   <div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="riskGuarantyMoneys[${staues.index}].rgmquota" id="RGMQuota" value="${mon.rgmquota}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					 </div>
				     </div>
				   </c:if>
				   <c:if test="${!empty mon.rgmpercent}">
				   <div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="RGMPercent">风险保证金百份比</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="riskGuarantyMoneys[${staues.index}].rgmpercent" id="RGMPercent"  value="${mon.rgmpercent*100}" class="form-control" />
							<span class="input-group-addon">%</span>
						</div>
					</div>
				    </div>
				   </c:if>
				   <c:if test="${!empty mon.maxrgmfee}">
				   <div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="maxRGMFee">该段最高风险保证金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="riskGuarantyMoneys[${staues.index}].maxrgmfee" id="maxRGMFee" value="${mon.maxrgmfee}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				    </div>
				   </c:if>
				</c:forEach>
				</c:if>
				<!--投标时收取-->
				<!--会员等级-->
				<c:if test="${riskGuarantyMoney.chargetype eq 2}">
				<div id="toubiao_div">
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="uGrade">会员等级</label>
					<div class="col-sm-3">
					   <label class="radio-inline">
						 	<input type="radio" name="ugrade" id="uGradeone" value="1" class="uGradeone_radio" <c:if test='${ugrade==1 }'>checked="checked"</c:if>/>全部等级
						 </label>
						<label class="radio-inline">
						 	<input type="radio" name="ugrade" id="uGradetwo" value="2" class="uGradeone_radio" <c:if test='${ugrade==2 }'>checked="checked"</c:if>/>部分等级
						 </label>
					</div>
				</div>
				<!--会员等级(部分等级)-->
				<c:if test="${ugrade eq 2}">
				   <div class="form-group" id="ugrades_div">
					<label class="col-sm-3 control-label laber_from"></label>
					<div class="col-sm-6">
					<input type="hidden" value="${ugrades1}">/>
					    <c:if test="${!empty uGrades}">
					    <c:forEach items="${uGrades}" var="ugr" varStatus="status">
					        <label class="checkbox-inline" style="width: 80px;">
							   <input type="checkbox" name="ugrades" id="ugrades" value="${ugr.ugrade}"/>${ugr.ugradename}
						    </label>
						    <c:if test="${status.count%4==0}"><br></c:if>
					    </c:forEach>
					    </c:if>
					</div>
				   </div>
				</c:if>
				
				<!--风险保证金费率(投标时收取)-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="RGMRate">风险保证金费率</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="rgmrate" id="RGMRate" placeholder="风险保证金费率" class="form-control" />
							<span class="input-group-addon">%</span>
						</div>
					</div>
				</div>
				<!--风险保证金最高收费(投标时收取)-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="maxRGMAmount">风险保证金最高收费</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxrgmamount" id="maxRGMAmount" placeholder="风险保证金最高收费" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</div>
				</c:if>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
					<c:if test="${!empty riskGuarantyMoney.isaudit}">
						<select name="isaudit" id="isAudit" class="form-control">
						   <c:if test="${riskGuarantyMoney.isaudit eq 1}">
						      <option value="1">是</option>
						   </c:if>
						   <c:if test="${riskGuarantyMoney.isaudit eq 0}">
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
				    <c:if test="${!empty riskGuarantyMoney}">
				        <select name="istemplet" class="form-control">
				        <c:if test="${riskGuarantyMoney.istemplet eq 1}">
				            <option value="1">是</option>
				        </c:if>
				        <c:if test="${riskGuarantyMoney.istemplet eq 0}">
				            <option value="0">否</option>
				        </c:if>
				        </select>
				    </c:if>
					</div>
				</div>
				<!-- 备注 -->
			   <div class="form-group">
			      <label class="control-label col-sm-3">备注</label>
			      <div class="col-sm-3">
			      <textarea rows="3" name="remark" class="form-control">${riskGuarantyMoney.remark}</textarea>
			      </div>
			   </div>
			   <!--添加 -->
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="riskbutton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);">返回列表</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>
