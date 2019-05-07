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
	
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>  	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>  	
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css" type="text/css"></link>
<title>DebtAttorn_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/debtattorn/updateDebtAttorn.js"></script>
<script type="text/javascript">
$(function(){
	//获取radio当前选择的状态
	var $radioVal = $("input[name='aownergrade']:checked").val();
	//当选中全部等级时 选择等级div隐藏，反之显示
	if ($radioVal == 1) {
		$("#insert-ugrade-checkbox-div-aownergrade").hide();
	} else if($radioVal == 2) {
		$("#insert-ugrade-checkbox-div-aownergrade").show();
	};
	
	//获取radio当前选择的状态
	var $radioVal = $("input[name='apurchasergrade']:checked").val();
	//当选中全部等级时 选择等级div隐藏，反之显示
	if ($radioVal == 1) {
		$("#insert-ugrade-checkbox-div-apurchasergrade").hide();
	} else if($radioVal == 2) {
		$("#insert-ugrade-checkbox-div-apurchasergrade").show();
	};
	
	//获取radio当前选择的状态
	var $radioVal = $("input[name='ugrade']:checked").val();
	//当选中全部等级时 选择等级div隐藏，反之显示
	if ($radioVal == 1) {
		$("#insert-ugrade-checkbox-div-ugrade").hide();
	} else if($radioVal == 2) {
		$("#insert-ugrade-checkbox-div-ugrade").show();
	};
	
	
	var $isadafeeon=$("#isadafeeon").val();
	if($isadafeeon==0){
		$("#block").hide();
		$("#changshu").hide();
	}
	//回显按比例或按等额
	var type=$("#type").find("option:selected").val();
	if("iequota"==type){
		$("#iepercent").hide();
		$("#iequota").show();
	}else if("iepercent"==type){
		$("#iequota").hide();
		$("#iepercent").show();
	};
	
	//全部等级与选择等级的change监听事件
	$(".insert-ugrade-radio-aownergrade").change(function() {
		var $radioVal = $(".insert-ugrade-radio-aownergrade:checked").val();
		if ($radioVal == 1) {
			$("#insert-ugrade-checkbox-div-aownergrade").hide();
			$("#insert-ugrade-checkbox-div-aownergrade :checkbox").each(function(){
				this.checked=false;
			});
			
		} else {
			$("#insert-ugrade-checkbox-div-aownergrade").show();
		}
	});
	
	//全部等级与选择等级的change监听事件
	$(".insert-ugrade-radio-apurchasergrade").change(function() {
		var $radioVal = $(".insert-ugrade-radio-apurchasergrade:checked").val();
		if ($radioVal == 1) {
			$("#insert-ugrade-checkbox-div-apurchasergrade").hide();
			$("#insert-ugrade-checkbox-div-apurchasergrade :checkbox").each(function(){
				this.checked=false;
			});
			
		} else {
			$("#insert-ugrade-checkbox-div-apurchasergrade").show();
		}
	});
	
	//全部等级与选择等级的change监听事件
	$(".insert-ugrade-radio-ugrade").change(function() {
		var $radioVal = $(".insert-ugrade-radio-ugrade:checked").val();
		if ($radioVal == 1) {
			$("#insert-ugrade-checkbox-div-ugrade").hide();
			$("#insert-ugrade-checkbox-div-ugrade :checkbox").each(function(){
				this.checked=false;
			});
			
		} else {
			$("#insert-ugrade-checkbox-div-ugrade").show();
		}
	});
	$("#submitBtu").click(function(){
			if(check().form()){
				$("form:first").submit();
			}
	});
});
	function gotoDebtAttornList(){
		   window.location.href="${pageContext.request.contextPath }/admin/debtAttorn/selectDebtAttornByCondition.action";
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
	<a href="#">标的债权转让修改页面</a>
</div>
<div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/debtAttorn/updateDebtAttorn.action" id="defaultForm" method="post">
			<c:if test="${empty debtAttorn}">
			    <label>暂无数据</label>
			</c:if>
			<c:if test="${!empty debtAttorn}">
			<input type="hidden" name="id" value="${debtAttorn.id}"/>
				<!--债权转让审核-->
				<div class="form-group">
					<label for="isdebtaudit" class="col-sm-3 control-label laber_from">债权转让是否需要审核</label>
					<div class="col-sm-3">
						<select name="isdebtaudit" id="isdebtaudit" class="form-control">
						   <c:if test="${debtAttorn.isdebtaudit eq 1}">
						      <option value="1">需要</option>
						   </c:if>
						   <c:if test="${debtAttorn.isdebtaudit eq 0}">
						      <option value="0">不需要</option>
						   </c:if>
						</select>
					</div>
				</div>
				<!--是否逾期债转-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isOCDebt">是否支持逾期债转</label>
					<div class="col-sm-3">
						<select name="isocdebt" id="isOCDebt" class="form-control">
						<c:if test="${debtAttorn.isocdebt eq 1}">
						    <option value="1">支持</option>
						</c:if>
						<c:if test="${debtAttorn.isocdebt eq 0}">
						    <option value="0">不支持</option>
						</c:if>
						</select>
					</div>
				</div>
				<!-- 债转增益设置 -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from" for="IntDisable">债转增益处理</label>
				    <div class="col-sm-3">
				       <select name="intdisable" id="IntDisable" class="form-control">
				       <c:if test="${debtAttorn.intdisable eq 1}">
				          <option value="1">全部失效</option>
				       </c:if>
				       <c:if test="${debtAttorn.intdisable eq 2}">
				          <option value="2">按债转金额比例失效</option>
				       </c:if>
				       <c:if test="${debtAttorn.intdisable eq 3}">
				          <option value="3">不作废</option>
				       </c:if>
				       </select>
				    </div>
				</div>
				<!--登等级选择-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">允许债权人债转等级</label>
					<div class="col-sm-3">
						<label class="radio-inline">
			 			<input type="radio" name="aownergrade" id="aownergradeone" value="1" class="insert-ugrade-radio-aownergrade"/>全部等级
			 		</label>
						<label class="radio-inline">
			 			<input type="radio" name="aownergrade" id="aownergradetwo" value="2" class="insert-ugrade-radio-aownergrade"/>部分等级
			 		</label>
					</div>
				</div>
				<!--允许转让人的等级-->
				<div class="form-group" id="aownergrade_div">
					<label class="col-sm-3 control-label laber_from" for="aOwnerGrade"></label>
					<div class="col-sm-6">
					<c:if test="${!empty uGrades}">
					    <c:forEach items="${uGrades}" var="ugr" varStatus="status">
					      <label class="checkbox-inline" style="width:120px;">
			   	 		     <input type="checkbox" name="aownergrades" id="aOwnerGrade1" value="${ugr.ugrade}"/>${ugr.ugradename}
			   	 	      </label>
			   	 	      <c:if test="${status.count%4==0}"><br></c:if>
					    </c:forEach>
					</c:if>
					</div>
				</div>
				<!--持有时间-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="holdDay">持有时间</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="holdday" id="holdDay" value="${debtAttorn.holdday}" class="form-control" />
							<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!--距离下个还款天数-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="intervalDay">距离下个还款日天数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="intervalday" id="intervalDay" class="form-control" value="${debtAttorn.intervalday}"/>
							<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!--逾期前多少天可转让-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">逾期前多少天可债转</label>
					<div class="col-sm-3">
						<div class="input-group">
						<input type="text" name="aheadocday" id="AheaDocDay" value="${debtAttorn.aheadocday}" class="form-control"/>
						<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!-- 债转期限 -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from">债转期限</label>
				    <div class="col-sm-3">
				        <div class="input-group">
				          <input type="text" name="deadline" id="DeadLine" class="form-control" value="${debtAttorn.deadline}"/>
				          <span class="input-group-addon">天</span>
				        </div>
				    </div>
				</div>
			    <!--债转次数方式限制  -->
			    <div class="form-group">
			        <label class="col-sm-3 control-label laber_from">债转次数方式限制</label>
			        <div class="col-sm-3">
			            <select name="dattrestrict" id="DATTRestrict" class="form-control">
			            <c:if test="${debtAttorn.dattrestrict eq 1}">
			               <option value="1">层级次数</option>
			            </c:if>
			            <c:if test="${debtAttorn.dattrestrict eq 2}">
			               <option value="2">每人次数</option>
			            </c:if>
			            </select>
			        </div>
			    </div>
			    <!-- 债转次数限制 -->
			    <c:if test="${debtAttorn.dattrestrict eq 2}">
				<div class="form-group" id="DATimesci">
				     <label class="col-sm-3 control-label laber_from" for="DATimes">债转次数限制</label>
				     <div class="col-sm-3">
				        <div class="input-group">
				           <input type="text" name="datimes" id="DATimes" class="form-control" value="${debtAttorn.datimes}"/>
				           <span class="input-group-addon">次</span>
				        </div>
				     </div>
				</div>
				</c:if>
				<c:if test="${debtAttorn.dattrestrict eq 1}">
				<div class="form-group" id="DATimesceng">
				     <label class="col-sm-3 control-label laber_from" for="DATimes">债转次数限制</label>
				     <div class="col-sm-3">
				        <div class="input-group">
				           <input type="text" name="datimes" id="DATimes" class="form-control" value="${debtAttorn.datimes}"/>
				           <span class="input-group-addon">层</span>
				        </div>
				     </div>
				</div>
				</c:if>
				<!--起息日时间点-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">起息日时间点</label>
					<div class="col-sm-3">
						<input type="text" name="valuepoint" id="valuePoint" value="${debtAttorn.valuepoint}" class="form-control"/>
					</div>
				</div>
				<!--起息规则-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from">起息规则</label>
					<div class="col-sm-3">
						<select name="valuerule" id="valueRule" class="form-control">
						  <c:if test="${debtAttorn.valuerule eq 1}">
						    <option value="1">承接日当天</option>
						  </c:if>
						   <c:if test="${debtAttorn.valuerule eq 2}">
						    <option value="2">承接日次日</option>
						  </c:if>
						   <c:if test="${debtAttorn.valuerule eq 3}">
						    <option value="3">固定时间点</option>
						  </c:if>
						</select>
					</div>
				</div>
				<!--挂单期利息-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="Isfixed">挂单期利息</label>
					<div class="col-sm-3">
						<select name="isfixed" id="Isfixed" class="form-control">
						<c:if test="${debtAttorn.isfixed eq 1}">
						    <option value="1">固定</option>
						</c:if>
						<c:if test="${debtAttorn.isfixed eq 0}">
						    <option value="0">不固定</option>
						</c:if>
						</select>
					</div>
				</div>
				<!--转让金额设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AttornMoneyLow">转让金额设置低值-高值</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="attornmoneylow" id="AttornMoneyLow"  value="${debtAttorn.attornmoneylow}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="attornmoney" id="AttornMoney" value="${debtAttorn.attornmoney}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--转让系数设定-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minAttornRatio">转让系数设定低值-高值</label>
					<div class="col-sm-3">
						<input type="text" id="minAttornRatio" name="minattornratio"  value="${debtAttorn.minattornratio}" class="form-control" />
					</div>
					<div class="col-sm-3">
						<input type="text" id="maxAttornratio" name="maxattornratio" value="${debtAttorn.maxattornratio}" class="form-control" />
					</div>
				</div>
				<!--是否允许拆分-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAsplit">是否允许拆分</label>
					<div class="col-sm-3">
						<select name="isasplit" id="isAsplit" class="form-control">
						<c:if test="${debtAttorn.isasplit eq 1}">
						   <option value="1">是</option>
						</c:if>
						<c:if test="${debtAttorn.isasplit eq 0}">
						   <option value="0">否</option>
						</c:if>
						</select>
					</div>
				</div>
				<!--是否为模板-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
					<div class="col-sm-3">
						<select name="istemplet" id="isTemplet" class="form-control">
						   <c:if test="${debtAttorn.istemplet eq 1}">
						     <option value="1">是</option>
						   </c:if>
						   <c:if test="${debtAttorn.istemplet eq 0}">
						     <option value="0">否</option>
						   </c:if>
						</select>
					</div>
				</div>
				<!--备注-->
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label laber_from">备注</label>
					<div class="col-sm-3">
						  <textarea rows="3" class="form-control" name="remark">${debtAttorn.remark}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="debtBtton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);">返回列表</button>
					</div>
				</div>
				</c:if>
			</form>
		</div>



<%-- <div id="id"></div>
<form action="${pageContext.request.contextPath}/debtAttorn/updateDebtAttorn.action" method="post" id="updateDebtAttornForm">
<input type="hidden" value="${debtAttorn.id }" name="id">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="5">修改债权转让参数</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">基本信息</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">债权转让审核</font>&nbsp;&nbsp;：
										<select name="isdebtaudit" id="isdebtaudit" style="width:100px;height:25px;font-size: 15px">
											<option value="">请选择</option>
											<option value="1" <c:if test='${debtAttorn.isdebtaudit==1 }'>selected="selected"</c:if>>需要</option>
											<option value="0" <c:if test='${debtAttorn.isdebtaudit==0 }'>selected="selected"</c:if>>不需要</option>
										</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">是否支持逾期债转</font>&nbsp;&nbsp;：
										<select name="isocdebt" id="isocdebt" style="width:100px;height:25px;font-size: 15px">
											<option value="">请选择</option>
											<option value="1" <c:if test='${debtAttorn.isocdebt==1 }'>selected="selected"</c:if>>支持</option>
											<option value="0" <c:if test='${debtAttorn.isocdebt==0 }'>selected="selected"</c:if>>不支持</option>
										</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
									<font size="3">是否为模板</font>&nbsp;&nbsp;：
										<select name="istemplet" id="istemplet" style="width:100px;height:25px;font-size: 15px">
											<option value="">请选择</option>
											<option value="1" <c:if test='${debtAttorn.istemplet==1 }'>selected="selected"</c:if>>是</option>
											<option value="0" <c:if test='${debtAttorn.istemplet==0 }'>selected="selected"</c:if>>否</option>
										</select>
						</div>
					</div>
					<hr>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">转让人设置信息</font></label>
						</div>
					</div>	
					<hr>
					<div class="row"  >
								<div class="col-md-3 col-md-offset-1" >
									<font size="3">债转次数限制： </font>
									<input  name="datimes"  value="${debtAttorn.datimes }"  style="width:80px;"  style="width:200px;height:25px;font-size: 15px">&nbsp;&nbsp;&nbsp;次
								</div>
						</div>
						<hr>
						
						<div class="row"  >
								<div class="col-md-3 col-md-offset-1" >
									允许债权人债转的等级： 
									<label class="radio-inline"><input class="insert-ugrade-radio-aownergrade" type="radio" name="aownergrade" value="1"  <c:if test='${aownergrade==1 }'>checked="checked"</c:if>  id="selectAllAownergrade" > 全部等级</label>
									 <label class="radio-inline"><input class="insert-ugrade-radio-aownergrade" type="radio" name="aownergrade" value="2"   <c:if test='${aownergrade==2 }'>checked="checked"</c:if>  id="selectActivityAownergrade">选择等级 </label> &nbsp;&nbsp;  
								</div>
						</div>
						<div class="row">
									<div class="col-md-12 col-md-offset-1">
										<div style="padding-left: 30px; padding-top: 12px;" id="insert-ugrade-checkbox-div-aownergrade">
											<input type="hidden" id="aownergrade1" value="${aownergrade1 }">
											<c:forEach items="${uGrades}" var="item" varStatus="status">
												<label class="checkbox-inline"> 
													<input type="checkbox" name="aownergrades" value="${item.ugrade }">${item.ugradename }
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
										 var aownergrade1 =eval("("+$("#aownergrade1").val()+")");
						                 if (aownergrade1.length>0) {
						                     $.each(aownergrade1, function(index, value){
						                         $("#insert-ugrade-checkbox-div-aownergrade input[type=checkbox]").each(function () {
						                             if($(this).val() == value) {
						                                 $(this).attr("checked",true);
						                             }
						                         });
						                     });
						                 }
									</script>
					<hr>
					<div class="row" >
						<div class="col-md-4 col-md-offset-1">
								<font size="3">债转排除名单编号:</font>
						</div>
						<div class="col-md-12 col-md-offset-1" id="checkbox_removenameno">
											<div style="padding-left: 30px; padding-top: 12px;" id="remo">
																<input type="hidden"  value="${debtAttorn.removenameno }"  id="removenameno1">
																<c:forEach items="${removeNames}" var="item" varStatus="status">
																	<label class="checkbox-inline"> 
																		<input type="checkbox" name="removenameno" value="${item.nameno }">${item.name }
																	</label>
																	<c:if test="${status.count%8==0 }">
																		<br />
																		<br />
																	</c:if>
																</c:forEach>
											</div>
							</div>
					</div>
					<!-- 回显 -->
									<script type="text/javascript">
								    	var removenameno1 = $("#removenameno1").val();
								    	if (removenameno1.length>0) {
								    	    var removenameno1arr = removenameno1.split(',');
								    	    $.each(removenameno1arr, function(index, value){
								    	        $("#checkbox_removenameno input[type=checkbox]").each(function () {
								    	            if($(this).val() == value) {
								    	                $(this).attr("checked",true);
								    	            }
								    	        });
								    	    });
								    	}
									</script>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">持有时间</font>&nbsp;&nbsp;：
							<input  name="holdday"     value="${debtAttorn.holdday }"   style="width:200px;height:25px;font-size: 15px">&nbsp;&nbsp;&nbsp;天
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">距离下个还款日天数</font>&nbsp;&nbsp;：
							<input  name="intervalday"     value="${debtAttorn.intervalday }"   style="width:80px;"  style="width:200px;height:25px;font-size: 15px">&nbsp;&nbsp;&nbsp;天
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
								<font size="3">是否允许拆分</font>&nbsp;&nbsp;：
										<select name="isasplit" id="isasplit" style="width:200px;height:25px;font-size: 15px">
											<option value="">请选择</option>
											<option value="1" <c:if test='${debtAttorn.isasplit==1 }'>selected="selected"</c:if>>可拆分（可部分出售）</option>
											<option value="0" <c:if test='${debtAttorn.isasplit==0 }'>selected="selected"</c:if> >不可拆分（全部出售）</option>
										</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">转让金额</font>&nbsp;&nbsp;：
							<input type="text"  name="attornmoney"    value="${debtAttorn.attornmoney }"  style="width:200px;height:25px;font-size: 15px"></input>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-10 col-md-offset-1">
							<font size="3">转让系数最低与最高</font>&nbsp;&nbsp;：
							<input type="text" name="minattornratio"    value="${debtAttorn.minattornratio }"   style="width:80px;"   style="width:100px;height:25px;font-size: 15px;"/> &nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input type="text" name="maxattornratio"    value="${debtAttorn.maxattornratio }"  style="width:80px;"     style="width:100px;height:25px;font-size: 15px;"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<hr>
					
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">购买人设置信息</font></label>
						</div>
					</div>	
					<hr>
						<div class="row"  >
								<div class="col-md-3 col-md-offset-1" >
									允许购买人债转的等级： 
									<label class="radio-inline"><input class="insert-ugrade-radio-apurchasergrade" type="radio" name="apurchasergrade" value="1"  <c:if test='${apurchasergrade==1 }'>checked="checked"</c:if> id="selectAllApurchasergrade" > 全部等级</label>
									 <label class="radio-inline"><input class="insert-ugrade-radio-apurchasergrade" type="radio" name="apurchasergrade" value="2"  <c:if test='${apurchasergrade==2 }'>checked="checked"</c:if>  id="selectActivityApurchasergrade">选择等级 </label> &nbsp;&nbsp;  
								</div>
							</div>
							<div class="row">
									<div class="col-md-12 col-md-offset-1">
										<div style="padding-left: 30px; padding-top: 12px;" id="insert-ugrade-checkbox-div-apurchasergrade">
											<input type="hidden" id="apurchasergrade1" value="${apurchasergrade1 }">
											<c:forEach items="${uGrades}" var="item" varStatus="status">
												<label class="checkbox-inline"> 
													<input type="checkbox" name="apurchasergrades" value="${item.ugrade }">${item.ugradename }
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
										 var apurchasergrade1 =eval("("+$("#apurchasergrade1").val()+")");
						                 if (apurchasergrade1.length>0) {
						                     $.each(apurchasergrade1, function(index, value){
						                         $("#insert-ugrade-checkbox-div-apurchasergrade input[type=checkbox]").each(function () {
						                             if($(this).val() == value) {
						                                 $(this).attr("checked",true);
						                             }
						                         });
						                     });
						                 }
									</script>
					<hr>
					<div class="row">
							<div class="col-md-12 col-md-offset-1">
								<font size="3" >不允许购买的用户名单表编号:</font>
							</div>
							<div class="col-md-12 col-md-offset-1" id="checkbox_noapnameno">
											<div style="padding-left: 30px; padding-top: 12px;" id="noa">
																<input type="hidden"  value="${debtAttorn.noapnameno }"  id="noapnameno1">
																<c:forEach items="${removeNames}" var="item" varStatus="status">
																	<label class="checkbox-inline"> 
																		<input type="checkbox" name="noapnameno" value="${item.nameno }">${item.name }
																	</label>
																	<c:if test="${status.count%8==0 }">
																		<br />
																		<br />
																	</c:if>
																</c:forEach>
											</div>
							</div>
					</div>
					<!-- 回显 -->
									<script type="text/javascript">
								    	var noapnameno1 = $("#noapnameno1").val();
								    	if (noapnameno1.length>0) {
								    	    var noapnameno1arr = noapnameno1.split(',');
								    	    $.each(noapnameno1arr, function(index, value){
								    	        $("#checkbox_noapnameno input[type=checkbox]").each(function () {
								    	            if($(this).val() == value) {
								    	                $(this).attr("checked",true);
								    	            }
								    	        });
								    	    });
								    	}
									</script>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">购买人允许全额或部分购买</font>&nbsp;&nbsp;：
										<select name="isabuyallorpart" id="isabuyallorpart" style="width:100px;height:25px;font-size: 15px">
											<option value="">请选择</option>
											<option value="1" <c:if test="${debtAttorn.isabuyallorpart==1 }">selected="selected"</c:if>>全额购买</option>
											<option value="0" <c:if test="${debtAttorn.isabuyallorpart==0 }">selected="selected"</c:if>>部分购买</option>
										</select>
						</div>
					</div>
					<hr>
					
						<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">债转手续费设置信息</font></label>
						</div>
					</div>	
					<hr>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">债转手续费开关</font>&nbsp;&nbsp;：
								<select id="isadafeeon" name="isadafeeon" style="width:200px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1"  <c:if test="${debtAttorn.isadafeeon==1 }">selected="selected"</c:if> >开</option>
									<option value="0" <c:if test="${debtAttorn.isadafeeon==0 }">selected="selected"</c:if> >关</option>
								</select>
						</div>
					</div>
					<script type="text/javascript">
							$("#isadafeeon").change(function(){
								var isadafeeonVal=$("#isadafeeon").val();
								/* 债转手续费：开 */
								if(1==isadafeeonVal){
									$("#selectAllUgrade").val("1");
									$("#selectActivityUgrade").val("2");
									$("#selectAllUgrade").attr("checked","checked");
									$("#insert-ugrade-checkbox-div-ugrade").hide();  
									$("#block").show();
									/* 债转手续费：关 */
								}else{
									$("#block input").val("");
									$("#block select").val("");
									$("#insert-ugrade-checkbox-div-ugrade :checkbox").each(function(){
										this.checked=false;
									});
									$("#changshu").hide();
									$("#block").hide();
								}
								
							});
					</script>
					<hr>
					
				<div id="block">	
								<div class="row"  >
										<div class="col-md-3 col-md-offset-1" >
											允许的转让人等级： 
											<label class="radio-inline"><input class="insert-ugrade-radio-ugrade" type="radio" name="ugrade" value="1" id="selectAllUgrade"  <c:if test='${ugrade==1 }'>checked="checked"</c:if> > 全部等级</label>
									 		<label class="radio-inline"><input class="insert-ugrade-radio-ugrade" type="radio" name="ugrade" value="2" id="selectActivityUgrade" <c:if test='${ugrade==2 }'>checked="checked"</c:if> >选择等级 </label> &nbsp;&nbsp;  
								</div>
								</div>
								<!-- 把会员等级迭代出来 -->
								<div class="row">
											<div class="col-md-12 col-md-offset-1">
												<div style="padding-left: 30px; padding-top: 12px;" id="insert-ugrade-checkbox-div-ugrade">
													<input type="hidden" id="ugrades1" value="${ugrades1 }">
													<c:forEach items="${uGrades}" var="item" varStatus="status">
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
						                         $("#insert-ugrade-checkbox-div-ugrade input[type=checkbox]").each(function () {
						                             if($(this).val() == value) {
						                                 $(this).attr("checked",true);
						                             }
						                         });
						                     });
						                 }
									</script>
							<hr>
						
								<div class="row" >
												<div class="col-md-2 col-md-offset-1" >
														<font size="3">类型</font>
														<select id="type"  name="type"  style="width:80px;height:25px;font-size: 15px" >
															<option value="">请选择</option>
															<option  value="iequota" <c:if test="${debtAttorn.quota!=null }">selected="selected"</c:if> >定额</option>
															<option value="iepercent" <c:if test="${debtAttorn.attornrate!=null }">selected="selected"</c:if> >百份比</option>
														</select>
													</div>
													<script type="text/javascript">
														$("#type").change(function(){
															if($(this).val()=="iequota"){
																$("#changshu").show();
																$("#iepercent input").val("");
																$("#iepercent").hide();
																$("#iequota").show();
															}else if($(this).val()=="iepercent"){
																$("#changshu").show();
																$("#iepercent").show();
																$("#iequota input").val("");
																$("#iequota").hide();
															}else{
																$("#changshu").hide();
															}
														});
												</script>
												<div class="col-md-7" >
													<font size="3">债转金额段：</font>&nbsp;&nbsp;<input type="text"   name="minattornmoney"   value="${debtAttorn.minattornmoney }"  style="width:100px;height:25px;font-size: 15px"/>元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input type="text"   name="maxattornmoney"   value="${debtAttorn.maxattornmoney }"  style="width:100px;height:25px;font-size: 15px"/>元
												</div>
								</div>
								<hr>
								
								<div id="changshu">
										<div class="row">
												<div>
														<div  id="iequota" class="col-md-6 col-md-offset-1">
															<font size="3">定额</font>&nbsp;&nbsp;：<input type="text"   name="quota"   value="${debtAttorn.quota }"  style="width:100px;height:25px;font-size: 15px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</div>
														<div id="iepercent" >
																<div class="col-md-3 col-md-offset-1">
																		<font size="3">百份比</font>&nbsp;&nbsp;：<input type="text"     name="attornrate"  value="${debtAttorn.attornrate }"  style="width:100px;height:25px;font-size: 15px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																</div>
																<div class="col-md-6 ">
																		<font size="3">手续费最低与最高：</font>&nbsp;&nbsp;<input type="text"    name="minfee"   value="${debtAttorn.minfee }"  style="width:100px;height:25px;font-size: 15px"/>元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input type="text"    name="maxfee"   value="${debtAttorn.maxfee }"  style="width:100px;height:25px;font-size: 15px"/>元
																</div>
														</div>
												</div>
										</div>
										<hr>
								</div>
			</div>
					
					
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-5">
							<input class="btn btn-primary"  type="button"  id="submitBtu" style="margin-left: 200px" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="backid" class="btn btn-success" name="backid" type="button" onclick="gotoDebtAttornList()">返回列表</button>
						</div>
					</div>
				</form> --%>
					
					
					
</body>
</html>
