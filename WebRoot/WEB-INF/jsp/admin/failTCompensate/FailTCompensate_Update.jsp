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
<title>FailTCompensate_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/failtcompensate/updateFailTCompensate.js"></script>
<script type="text/javascript">
$(function(){
	/* 根据利息方式补偿开关回显相应的div */
	var isintcompensateon=$("#isintcompensateon").val();
	if(isintcompensateon==0){
		$("#intcompensateon").hide();
		$("#iequota").hide();
		$("#iepercent").hide();
		/*初始化默认选中全部等级 */
		$("#selectAll").attr("checked","checked");
		$("#insert-ugrade-checkbox-div").hide();
	}
	
	/* 根据奖品补偿方式开关回显相应的div */
	var isawardcompensateon=$("#isawardcompensateon").val();
	if(isawardcompensateon==0){
		$("#awardcompensateon").hide();
	}
	
	/* 如果利息方式补偿开关和奖品补偿方式开关都是关的状态就把金额段也隐藏 */
	if($("#intcompensateon").is(":hidden")&&$("#awardcompensateon").is(":hidden")){ 
		$("#minmoney-maxmoney").hide();
	}
	
	/* 当选中全部等级时 选择等级div隐藏，反之显示 */
	var $radioVal = $(".insert-ugrade-radio:checked").val();
	if ($radioVal == 1) {
		$("#insert-ugrade-checkbox-div").hide();
	} else {
		$("#insert-ugrade-checkbox-div").show();
	}

	/* 回显定额和百分比 */
	var $typeVal=$("#type").val();
	if($typeVal=="iequota"){
		$("#iepercent").hide();
	}else if($typeVal=="iepercent"){
		$("#iequota").hide();
	}

	
	/*利息方式补偿开关change事件  */
	$("#isintcompensateon").change(function(){ 
		var  isintcompensateon= $(this).val();
		if(isintcompensateon==0||isintcompensateon==""){
			$("#minmoney-maxmoney input").val("");
			$("#intcompensateon input").val("");
			$("#insert-ugrade-checkbox-div :checkbox").each(function(){
				this.checked=false;
			});
			$("#intcompensateon").hide();
			
		}else if(isintcompensateon==1){
			$("#selectAll").val("1");
			$("#selectActivity").val("2");
			$("#intcompensateon").show();
			$("#minmoney-maxmoney").show();
		}
		if($("#intcompensateon").is(":hidden")&&$("#awardcompensateon").is(":hidden")){ 
			$("#minmoney-maxmoney").hide();
		}
	});
	
	/*奖品补偿方式开关change事件*/
	$("#isawardcompensateon").change(function(){ 
		var  isawardcompensateon= $(this).val();
		if(isawardcompensateon==0||isawardcompensateon==""){
			$("#awardcompensateon input").val("");
			$("#awardcompensateon").hide();
		}else if(isawardcompensateon==1){
			$("#awardcompensateon").show();
			$("#minmoney-maxmoney").show();
		}
		if($("#intcompensateon").is(":hidden")&&$("#awardcompensateon").is(":hidden")){ 
			$("#minmoney-maxmoney").hide();
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
	
	$("#submitBtn").click(function(){
		/* 通过标的设置来到这里的就有nextPage，不会回调回来 */
			if(check().form()){
				$("form:first").submit();
		}
	});
});

	function gotoFailTCompensateList(){
		   window.location.href="${pageContext.request.contextPath }/admin/failTCompensate/selectFailTCompensateByCondition.action";
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
	<a href="#">标的流标补偿修改页面表</a>
</div>
<div class="container" style="margin-top: 25px;">
		<form class="form-horizontal" role="form" id="defaultForm" action="${pageContext.request.contextPath}/admin/failTCompensate/updateFailTCompensate.action">
			<c:if test="${empty failTCompensate}">
			     <label>暂无数据</label>
			</c:if>
			<c:if test="${!empty failTCompensate}">
			   <c:forEach items="${failTCompensates}" var="fail" varStatus="sta">
				<div class="form-group">
					<label class="col-sm-3 control-label">投标人等级</label>
					<div class="col-sm-3">
						<label class="radio-inline"> <input type="radio"
							name="intugrade" id="intUGradeone" value="1"
							class="insert-ugrade-radio" />全部等级
						</label> <label class="radio-inline"> <input type="radio"
							name="intugrade" id="intUGradetwo" value="2"
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
									type="checkbox" name="intugrades" id="intugrades"
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
				<input type="hidden" name="failTCompensates[${sta.index}].id" value="${fail.id}"/>
					<div class="form-group">
						<label for="inputminMoney" class="col-sm-3 control-label">投标累计金额最低-最高</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[${sta.index}].minmoney" id="inputminMoney"
									 value="${fail.minmoney}" class="form-control" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[${sta.index}].maxmoney" id="inputmaxMoney"
									 value="${fail.maxmoney}" class="form-control" /> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<!--定额补偿金-->
					<c:if test="${!empty fail.quota}">
					<div class="form-group" id="quotainput_div">
						<label class="col-sm-3 control-label" for="inputquota">定额补偿金</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[${sta.index}].quota" id="inputquota"
									class="form-control"  value="${fail.quota}"/> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					</c:if>
					<!--日奖励费率 +++最高补偿金-->
					<c:if test="${!empty fail.dayawardrate}">
					<div class="form-group" id="dayawardrate_div">
						<label class="col-sm-3 control-label" for="dayAwardRate">日奖励费率-最高补偿金</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[${sta.index}].dayawardrate" id="dayAwardRate"
									class="form-control" value="${fail.dayawardrate}"/> <span
									class="input-group-addon">%</span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="failTCompensates[${sta.index}].maxcompensate" id="maxCompensate"
									class="form-control" value="${fail.maxcompensate}"/> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					</c:if>
				 </c:forEach>
			<!--资金清算是否需要审核-->
			<div class="form-group">
				<label class="col-sm-3 control-label">资金清算是否需要审核</label>
				<div class="col-sm-3">
					<select name="isaudit" id="isAudit" class="form-control">
					<c:if test="${failTCompensate.isaudit eq 1}">
					    <option value="1">是</option>
					</c:if>
					<c:if test="${failTCompensate.isaudit eq 0}">
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
					<c:if test="${failTCompensate.istemplet eq 1}">
					    <option value="1">是</option>
					</c:if>
					<c:if test="${failTCompensate.istemplet eq 0}">
					    <option value="0">否</option>
					</c:if>
					</select>
				</div>
			</div>
			<!-- 备注 -->
			<div class="form-group">
			   <label class="control-label col-sm-3">备注</label>
			   <div class="col-sm-3">
			     <textarea rows="3" name="remark" class="form-control">${failTCompensate.remark}</textarea>
			   </div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-1">
					<button type="submit" class="btn btn-default" id="failButton">保存</button>
				</div>
				<div class="col-sm-1">
					<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);">返回列表</button>
				</div>
			</div>
			</c:if>
		</form>
	</div>



<%-- <div id="id"></div>
<form action="${pageContext.request.contextPath}/failTCompensate/updateFailTCompensate.action" method="post" id="updateFailTCompensateForm">
<input type="hidden" value="${failTCompensate.id }" name="id">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="5">修改流标补偿参数</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4  " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">利息方式补偿设置与奖品补偿方式设置</font></label>
						</div>
					</div>
					
					
					<div class="row" style="line-height: 0px;" id="minmoney-maxmoney">
							<div class="col-md-10   col-md-offset-1"  >
										 <font size="3">分段最低及最高金额：</font>&nbsp;&nbsp;<input type="text" name="minmoney"  value="${failTCompensate.minmoney }"  style="width:100px;height:25px;font-size: 15px"/> 元-<input type="text" name="maxmoney"   value="${failTCompensate.maxmoney }"  style="width:100px;height:25px;font-size: 15px"/> 元
							 </div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-3  col-md-offset-1">
							<font size="3">利息方式补偿开关</font>&nbsp;&nbsp;：
								<select name="isintcompensateon"  id="isintcompensateon" style="width:100px;height:25px;font-size: 15px">
									<option value="">请选择</option>								
									<option value="1" <c:if test="${failTCompensate.isintcompensateon==1 }">selected="selected"</c:if> >开</option>								
									<option value="0" <c:if test="${failTCompensate.isintcompensateon==0 }">selected="selected"</c:if> >关</option>							
								</select>
						</div>
						<div class="col-md-4  ">
							<font size="3">奖品补偿方式开关</font>&nbsp;&nbsp;：
								<select name="isawardcompensateon"  id="isawardcompensateon" style="width:100px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1" <c:if test="${failTCompensate.isawardcompensateon==1 }">selected="selected"</c:if> >开</option>
									<option value="0" <c:if test="${failTCompensate.isawardcompensateon==0 }">selected="selected"</c:if> >关</option>
								</select>
						</div>
					</div>
					<hr>
					<div id="intcompensateon">
								<div class="row"  >
										<div class="col-md-4 col-md-offset-1">
										会员等级： 
													<label class="radio-inline"><input class="insert-ugrade-radio" type="radio" name="ugrade"  value="1"  id="selectAll" <c:if test="${ugrade==1 }">checked="checked"</c:if> > 全部等级</label>
													<label class="radio-inline"><input class="insert-ugrade-radio" type="radio" name="ugrade"  value="2"  id="selectActivity" <c:if test="${ugrade==2 }">checked="checked"</c:if> >选择等级  &nbsp;&nbsp;  </label>
										</div>
								</div>
									<!-- 把所有等级迭代出来 -->
									<div class="row">
													<div class="col-md-12 col-md-offset-1">
														<div style="padding-left: 30px; padding-top: 12px;" id="insert-ugrade-checkbox-div">
														<input type="hidden" id="ugrades1" value="${ugrades1 }">
															<c:forEach items="${ugs}" var="item" varStatus="status">
																<label class="checkbox-inline"> 
																	<input type="checkbox" name="ugrades" value="${item.ugrade }">${item.ugradename }
																</label>
																<c:if test="${status.count%4==0 }">
																	<br />
																	<br />
																</c:if>
															</c:forEach>
														</div>
													</div>
									</div>
									<!-- 回显 -->
									<script type="text/javascript">
										 var ugrades1 =eval("("+$("#ugrades1").val()+")");
						                 if (ugrades1.length>0) {
						                     $.each(ugrades1, function(index, value){
						                         $("input[type=checkbox]").each(function () {
						                             if($(this).val() == value) {
						                                 $(this).attr("checked",true);
						                             }
						                         });
						                     });
						                 }
									</script>
									<hr>
							<div class="row">		
									<div class="col-md-2 col-md-offset-1" >
													<font size="3">类型</font>
													<select id="type"  name="type" style="width:80px;height:25px;font-size: 15px" >
														<option value="">请选择</option>
														<option  value="iequota" <c:if test="${failTCompensate.quota!=null }">selected="selected"</c:if> >定额</option>
														<option value="iepercent" <c:if test="${failTCompensate.dayawardrate!=null }">selected="selected"</c:if> >百份比</option>
													</select>
												</div>
												<script type="text/javascript">
													$("#type").change(function(){
														if($(this).val()=="iequota"){
															$("#iepercent input").val("");
															$("#iepercent").hide();
															$("#iequota").show();
														}else if($(this).val()=="iepercent"){
															$("#iepercent").show();
															$("#iequota input").val("");
															$("#iequota").hide();
														}else{
															$("#iepercent").hide();
															$("#iequota").hide();
														}
													});
											</script>
											
											<div  id="iequota" class="col-md-4">
													<font size="3">定额补偿金</font>&nbsp;&nbsp;：<input type="text" name="quota"   value="${failTCompensate.quota }"  style="width:100px;height:25px;font-size: 15px"/>元
											</div>
											<div id="iepercent" >
													<div  class="col-md-3">
															<font size="3">日奖励费率</font>&nbsp;&nbsp;：<input type="text" name="dayawardrate"    value="${failTCompensate.dayawardrate }"  style="width:100px;height:25px;font-size: 15px"/> 
													</div>
													<div  class="col-md-5">
															<font size="3">最高补偿金额：</font>&nbsp;&nbsp;<input type="text" name="maxcompensate"  value="${failTCompensate.maxcompensate }"  style="width:100px;height:25px;font-size: 15px"/> 
													</div>
											</div>
							</div>
				<hr>
				</div>
				
				<div id="awardcompensateon">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">奖品名称</font>&nbsp;&nbsp;：
								<select name="awardname" style="width:150px;height:25px;font-size: 15px">
													<option value="">请选择</option>
													<option value="1">类现金红包</option>
													<option value="2">假现金红包</option>
													<option value="3">加息卷</option>
													<option value="4">手机充值券</option>
													<option value="5">手机</option>
													<option value="6">电影券</option>
								</select>
						</div>
					</div>
					<hr>
				</div>
					
					<div style="margin-bottom: 30px"></div>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">是否设为模板</font>&nbsp;&nbsp;：
								<select name="istemplet" style="width:200px;height:25px;font-size: 15px">
										<option value="">请选择</option>
										<option value="1" <c:if test="${failTCompensate.istemplet==1 }">selected="selected"</c:if> >是</option>
										<option value="0" <c:if test="${failTCompensate.istemplet==0 }">selected="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>	
					<div class="row" style="line-height: 0px;">
						<div class="col-md-5">
							<input class="btn btn-primary"  type="button"  id="submitBtn" style="margin-left: 200px" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="backid" class="btn btn-success" name="backid" type="button" onclick="gotoFailTCompensateList()">返回列表</button>
						</div>
					</div>
				</form> --%>
</body>
</html>
