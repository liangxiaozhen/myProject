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
<title>AheadRepay_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/aheadrepay/updateAheadRepay.js"></script>
<script type="text/javascript">
$(function(){
	
	/* 得到三个总开关的状态进行回显 */ 
	var ispicompensateon=$("#ispicompensateon").val();
	var ispluscompensateon=$("#ispluscompensateon").val();
	var isforplatformon=$("#isforplatformon").val();
	if(ispicompensateon==0){
		$("#picompensateon").hide();
		$("#jie_kuan_ren").hide();
		$("#fa_jin").hide();
		$("#ping_tai").hide();
	}
	
	if(ispluscompensateon==0){
		$("#pluscompensateon").hide();
		$("#zheng_yi_ping_tai_fa_jin").hide();
		$("#zypt").hide();
		$("#zheng_yi_ping_tai_zhang_li").hide();
	}
	
	if(isforplatformon==0){
		$("#forplatformon").hide();
		$("#type5").hide();
		$("#jlpt").hide();
	}
	/* 根据奖励方式的回显来显示该显示的隐藏该隐藏的 */
		var awardtype=$("#awardtype").val();
		if(awardtype=="1"){
			$("#ping_tai").hide();
		}else if(awardtype=="2"){
			$("#jie_kuan_ren").hide();
		}
		
		var plusawardtype=$("#plusawardtype").val();
		if(plusawardtype=="1"){
			$("#zheng_yi_ping_tai_zhang_li").hide();
		}else if(plusawardtype=="2"){
			$("#zheng_yi_ping_tai_fa_jin").hide();
		}
		
	/* 根据定额或比例回显相应的定额div或比例div */
		var type=$("#type").val();
		if(type=="iequota"){
			$("#penaltyrate").hide();
			$("#maxpenalty").hide();
		}
		if(type=="iepercent"){
			$("#penaltyquota").hide();
		}
		
		
		var type2=$("#type2").val();
		if(type2=="iequota"){
			$("#pluspenaltyrate").hide();
			$("#plusmaxpenalty").hide();
		}
		if(type2=="iepercent"){
			$("#pluspenaltyquota").hide();
		}
		
		
		var type3=$("#type3").val();
		if(type3=="iequota"){
			$("#awardplatrate").hide();
			$("#min_max").hide();
		}
		if(type3=="iepercent"){
			$("#awardplatquota").hide();
		}
	
	
	
	
	/*总开关1的change事件 */
	$("#ispicompensateon").change(function(){
		var is=$(this).val();
		if("0"==is||""==is){
			$("#picompensateon input").val("");
			$("#picompensateon select").val("");
			$("#picompensateon").hide();
		}else if("1"==is){
			$("#picompensateon").show();
		}
	});
	
	
	/*总开关2的change事件 */
		$("#ispluscompensateon").change(function(){
			var is=$(this).val();
			if("0"==is||""==is){
				$("#pluscompensateon input").val("");
				$("#pluscompensateon select").val("");
				$("#pluscompensateon").hide();
			}else if("1"==is){
				$("#pluscompensateon").show();
			}
		});
	
		/*总开关3的change事件 */
		$("#isforplatformon").change(function(){
			var is=$(this).val();
			if("0"==is||""==is){
				$("#jlpt input").val("");
				$("#jlpt").hide();

				$("#type5 select").val(""); 
				$("#type5").hide(); 
				
				$("#forplatformon input").val("");
				$("#forplatformon").hide();
				
				
			}else if("1"==is){
				$("#forplatformon").show();
				$("#type5").show();
			}
		});
					
	
	
					/* 本金利息补偿信息：奖励方式的change事件*/
					$("#awardtype").change(function(){
						if($(this).val()=="1"){
							$("#ping_tai input").val("");
							$("#ping_tai").hide();
							$("#jie_kuan_ren").show();
						}else if($(this).val()=="2"){
							$("#jie_kuan_ren input").val("");
							$("#ping_tai").show();
							$("#jie_kuan_ren").hide();
						}else if($(this).val()=="3"){
							$("#ping_tai").show();
							$("#jie_kuan_ren").show();
						}else{
							$("#jie_kuan_ren").hide();
							$("#ping_tai").hide();
						}
					});
	
					/*增益利息补偿信息中：增益奖励方式的change事件*/
					$("#plusawardtype").change(function(){
							if($(this).val()=="1"){
								$("#zheng_yi_ping_tai_zhang_li input").val("");
								$("#zheng_yi_ping_tai_zhang_li").hide();
								$("#zheng_yi_ping_tai_fa_jin").show();
							}else if($(this).val()=="2"){
								$("#zheng_yi_ping_tai_fa_jin input").val("");
								$("#zheng_yi_ping_tai_fa_jin").hide();
								$("#zheng_yi_ping_tai_zhang_li").show();
							}else if($(this).val()=="3"){
								$("#zheng_yi_ping_tai_fa_jin").show();
								$("#zheng_yi_ping_tai_zhang_li").show();
							}else{
								$("#zheng_yi_ping_tai_fa_jin").hide();
								$("#zheng_yi_ping_tai_zhang_li").hide();
							}
						});
					
					
					/*本金利息补偿信息中：定额与百分比的change事件  */
					$("#type").change(function(){
						if($(this).val()=="iequota"){
							$("#fa_jin").show();
							$("#penaltyrate input").val("");
							$("#penaltyrate").hide();
							$("#maxpenalty input").val("");
							$("#maxpenalty").hide();
							
							$("#penaltyquota").show();
						}else if($(this).val()=="iepercent"){
							$("#fa_jin").show();
							$("#penaltyquota input").val("");
							$("#penaltyquota").hide();
							
							$("#penaltyrate").show();
							$("#maxpenalty").show();
						}else{
							$("#fa_jin").hide();
						}
					});
					
					
					/*增益利息补偿信息：定额与百分比的change事件*/
					$("#type2").change(function(){
							if($(this).val()=="iequota"){
								$("#zypt").show();
								$("#pluspenaltyrate input").val("");
								$("#pluspenaltyrate").hide();
								$("#plusmaxpenalty input").val("");
								$("#plusmaxpenalty").hide();
								
								$("#pluspenaltyquota").show();
							}else if($(this).val()=="iepercent"){
								$("#zypt").show();
								$("#pluspenaltyquota input").val("");
								$("#pluspenaltyquota").hide();
								
								$("#pluspenaltyrate").show();
								$("#plusmaxpenalty").show();
							}else{
								$("#zypt").hide();
							}
						});
							
					
				
					/*还款人补偿平台信息：定额与百分比的change事件*/
					$("#type3").change(function(){
							if($(this).val()=="iequota"){
								$("#jlpt").show();
								$("#awardplatrate input").val("");
								$("#awardplatrate").hide();
								$("#min_max input").val("");
								$("#min_max").hide();
								
								$("#awardplatquota").show();
							}else if($(this).val()=="iepercent"){
								$("#jlpt").show();
								$("#awardplatquota input").val("");
								$("#awardplatquota").hide();
								
								$("#awardplatrate").show();
								$("#min_max").show();								
							}else{
								$("#jlpt").hide();
							}
						});
					//提交表单
					$("#submitBtu").click(function(){
							if(check().form()){
								$("form:first").submit();
							}
					});
					 
});


	function gotoAheadRepayList(){
		   window.location.href="${pageContext.request.contextPath }/admin/aheadRepay/selectAheadRepayByCondition.action";
	}
</script>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
	hr{
		margin: 5px;
	} 
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
<div class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的提前还款个人利息奖励修改页面</a>
</div>
<div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" id="defaultForm" method="post" action="${pageContext.request.contextPath}/admin/aheadRepay/updateAheadRepay.action">
				<c:if test="${empty aheadRepay}">
				    <label>暂无数据</label>
				</c:if>
				<c:if test="${!empty aheadRepay}">
				<!--单个投资人累计本金欠收最小利息与最高利息-->
				<c:forEach items="${aheadRepays}" var="ahe" varStatus="sta">
				      <div class="form-group">
						<label for="inputminnoreceiveint" class="col-sm-3 control-label">单个投资人累计本金欠收利息最低-最高</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="aheadRepays[${sta.index}].minnoreceiveint" id="inputminnoreceiveint" value="${ahe.minnoreceiveint}" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="aheadRepays[${sta.index}].maxnoreceiveint" id="inputmaxnoreceiveint" value="${ahe.maxnoreceiveint}" class="form-control" />
								<span class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputdaylatefeerate" class="col-sm-3 control-label">奖励方式</label>
						<div class="col-sm-3">
							<select name="aheadRepaysawardtype" id="inputawardtype" class="form-control">
							  <c:if test="${ahe.awardtype eq 1}">
							     <option value="1">惩罚借款人</option>
							  </c:if>
							  <c:if test="${ahe.awardtype eq 2}">
							     <option value="2">平台奖励</option>
							  </c:if>
							  <c:if test="${ahe.awardtype eq 3}">
							     <option value="3">惩罚借款人且平台奖励</option>
							  </c:if>
							</select>
						</div>
					</div>
					<c:if test="${!empty ahe.loanpenaltyname}">
					<div class="form-group">
							<label class="col-sm-3 control-label" for="loanpenaltyname">借款人罚金奖励名称</label>
							<div class="col-sm-3">
								<input type="text" name="aheadRepaysloanpenaltyname" id="loanpenaltyname" class="form-control" value="${ahe.loanpenaltyname}" />
							</div>
				    </div>
				    </c:if>
				    <c:if test="${!empty ahe.penaltyquota}">
					<div class="form-group" id="quotainput_divthr">
							<label class="col-sm-3 control-label" for="penaltyquota">罚金定额</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="aheadRepays[${sta.index}].penaltyquota" id="penaltyquota"  value="${ahe.penaltyquota}" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${!empty ahe.penaltyrate}">
					<div class="form-group" id="dayawardrate_divthr">
							<label class="col-sm-3 control-label" for="penaltyrate">罚金百分比-罚金最大值</label>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="aheadRepays[${sta.index}].penaltyrate" id="penaltyrate" value="${ahe.penaltyrate}" class="form-control" />
									<span class="input-group-addon">%</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" name="aheadRepays[${sta.index}].maxpenalty" id="maxpenalty" value="${ahe.maxpenalty}" class="form-control" />
									<span class="input-group-addon">元</span>
								</div>
							</div>
						</div>
						</c:if>
						<c:if test="${!empty ahe.pawardname}">
						<div class="form-group" id="paward_div">
						<label class="col-sm-3 control-label" for="pawardname">平台奖励奖品名称</label>
						<div class="col-sm-3">
							<select name="aheadRepayspawardname" id="inputpawardname" class="form-control">
							  <option value="">${ahe.pawardname}</option>
							  </select>
						 </div>
					     </div>
					     </c:if>
				</c:forEach>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
						<select name="isaudit" id="isAudit" class="form-control">
						<c:if test="${aheadRepay.isaudit eq 1}">
						  <option value="1">是</option>
						</c:if>
						<c:if test="${aheadRepay.isaudit eq 0}">
						  <option value="0">否</option>
						</c:if>
						</select>
					</div>
				</div>
				<!--是否为模板-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="istemplet">是否为模板</label>
					<div class="col-sm-3">
						<select name="istemplet" class="form-control" id="istemplet">
						  <c:if test="${aheadRepay.istemplet eq 1}">
						     <option value="1">是</option>
						  </c:if>
						  <c:if test="${aheadRepay.istemplet eq 0}">
						     <option value="0">否</option>
						  </c:if>
						</select>
					</div>
				</div>
				<!-- 备注 -->
				<div class="form-group">
				   <label class="control-label col-sm-3">备注</label>
				   <div class="col-sm-3">
				     <textarea rows="3" name="remark" class="form-control">${aheadRepay.remark}</textarea>
				   </div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-primary" id="aheButton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="gotoAheadRepayList()">返回列表</button>
					</div>
				</div>
				</c:if>
			</form>
		</div>
</body>
</html>
