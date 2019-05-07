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
<title>OverdueCompensate_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/overduecompensate/updateOverdueCompensate.js"></script>
<script type="text/javascript">
$(function(){
	/* 根据是否设置平台追偿来显示或隐藏div */
	var isprecoveryon=$("#isprecoveryon :checked").text();
	if("否"==isprecoveryon){
		$("#precoveryon").hide();
		$("#occquota").hide();
		$("#toccrate").hide();
	}
	
	/* 根据是否开通会员垫付来显示或隐藏div */
	var isupayon=$("#isupayon :checked").text();
	if("否"==isupayon){
		$("#upayon").hide();
	}
	
	/* 回显会员等级的全选或部分选择 */
	 var $radioVal=$(".insert-ugrade-radio:checked").val();
	//当选中全部等级时 选择等级div隐藏，反之显示
	if ($radioVal == 1) {
		$("#insert-ugrade-checkbox-div").hide();
	} else {
		$("#insert-ugrade-checkbox-div").show();
	}
	
	/* 回显定额和百分比 */
	var $typeVal=$("#type").val();
	if($typeVal=="occquota"){
		$("#toccrate").hide();
	}else if($typeVal=="toccrate"){
		$("#occquota").hide();
	}
	
/* 是否设置平台追偿开关change事件 */
	$("#isprecoveryon").change(function(){
		var isprecoveryon=$(this).val();
		if("0"==isprecoveryon||""==isprecoveryon){
			$("#precoveryon input").val("");
			$("#precoveryon").hide();
		}else if("1"==isprecoveryon){
			$("#precoveryon").show();
		}
	});
	
	/* 是否开通会员垫付开关change事件 */
	$("#isupayon").change(function(){
		var isupayon=$(this).val();
		if("0"==isupayon||""==isupayon){
			$("#upayon input").val("");
			$("#upayon").hide();
		}else if("1"==isupayon){
			$("#upayon").show();
			$("#selectAll").attr("checked","checked");
			$("#insert-ugrade-checkbox-div").hide();  
		}
	});
	

/*定额与百分比的change事件  */
$("#type").change(function(){
	if($(this).val()=="occquota"){ 
		$("#toccrate input").val("");
		$("#toccrate").hide();
		$("#occquota").show();
	}else if($(this).val()=="toccrate"){
		$("#occquota input").val("");
		$("#occquota").hide();
		$("#toccrate").show();
	}else{
		$("#occquota").hide();
		$("#toccrate").hide();
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
	/* 通过标的设置来到这里的就有nextPage */
		if(check().form()){
			$("form:first").submit();
		}
});

});
	function gotoOverdueCompensateList(){
		   window.location.href="${pageContext.request.contextPath }/admin/overdueCompensate/selectOverdueCompensateByCondition.action";
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
	<a href="#">标的逾期代偿修改页面</a>
</div>
 <div class="container" style="margin-top: 25px;">
			<form class="form-horizontal" role="form" id="defaultForm" method="post" action="${pageContext.request.contextPath}/admin/overdueCompensate/updateOverdueCompensate.action">
				<c:if test="${empty overdueCompensate}">
				    <label>暂无数据</label>
				</c:if>
				<c:if test="${!empty overdueCompensate}">
				<input type="hidden" name="id" value="${overdueCompensate.id}"/>
				 <!--逾期代偿人-->
				 <div class="form-group">
					<label for="sleccmanno" class="col-sm-3 control-label">逾期代偿人</label>
					<div class="col-sm-3">
						<input type="text" name="cmanno" id="inputcmanno" value="${overdueCompensate.cmanno}" class="form-control"/>
					</div>
				</div>
				<!--会员垫付开关-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="isupayon">会员垫付开关</label>
					<div class="col-sm-3">
						<select name="isupayon" id="inputisupayon" class="form-control">
						<c:if test="${overdueCompensate.isupayon eq 1}">
						    <option value="1">开</option>
						</c:if>
						<c:if test="${overdueCompensate.isupayon eq 0}">
						    <option value="0">关</option>
						</c:if>
						</select>
					</div>
				</div>
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
				<!--本金垫付规则-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="inutpfprincipalrate">本金垫付比例-本金垫付最高金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="pfprincipalrate" id="inutpfprincipalrate" value="${overdueCompensate.pfprincipalrate}" class="form-control"/>
							<span class="input-group-addon">%</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxpfprincipal" id="inutpmaxpfprincipal" value="${overdueCompensate.maxpfprincipal}" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--利息垫付规则-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="inutppfintrate">利息垫付比例-利息垫付最高金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="pfintrate" id="inutppfintrate" value="${overdueCompensate.pfintrate}" class="form-control"/>
							<span class="input-group-addon">%</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxpfint" id="inutpmaxpfint" value="${overdueCompensate.maxpfint}" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--滞纳金垫付垫付规则-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="inutplatefeerate">滞纳金垫付比例-滞纳金垫付最高金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="latefeerate" id="inutplatefeerate" value="${overdueCompensate.latefeerate}" class="form-control"/>
							<span class="input-group-addon">%</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxlatefee" id="inutpmaxpfint" value="${overdueCompensate.maxlatefee}" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
						<select name="isaudit" class="form-control" id="isAudit">
						<c:if test="${overdueCompensate.isaudit eq 1}">
						    <option value="1">是</option>
						</c:if>
						<c:if test="${overdueCompensate.isaudit eq 0}">
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
						<c:if test="${overdueCompensate.istemplet eq 1}">
						    <option value="1">是</option>
						</c:if>
						<c:if test="${overdueCompensate.istemplet eq 0}">
						    <option value="0">否</option>
						</c:if>
						</select>
					</div>
				</div>
				<!-- 备注 -->
				<div class="form-group">
				   <label class="control-label col-sm-3">备注</label>
				   <div class="col-sm-3"> 
				      <textarea rows="3" name="remark" class="form-control">${overdueCompensate.remark}</textarea>
				   </div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="overButton">修改</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);">返回列表</button>
					</div>
				</div>
				</c:if>
			</form>
		</div>

<%-- <div id="id"></div>
<form action="${pageContext.request.contextPath}/overdueCompensate/updateOverdueCompensate.action" method="post" id="updateOverdueCompensateForm">
<input type="hidden" value="${overdueCompensate.id }" name="id">
<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="6">修改逾期参数</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">逾期代偿人</font>&nbsp;&nbsp;：<input type="text" name="cmanno"  value="${overdueCompensate.cmanno }"   style="width:200px;height:25px;font-size: 15px"/> 
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">逾期宽限期</font>&nbsp;&nbsp;：<input type="text" name="graceperiod"  value="${overdueCompensate.graceperiod }"  style="width:200px;height:25px;font-size: 15px"/>天 
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">日滞纳金率</font>&nbsp;&nbsp;：<input type="text" name="daylatefeerate"  value="${overdueCompensate.daylatefeerate }"  style="width:200px;height:25px;font-size: 15px"/> 
						</div>
					</div>
					<hr>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">平台追偿设置信息</font></label>
						</div>
					</div>	
					<hr>
					
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">是否设置平台追偿</font>&nbsp;&nbsp;：
								<select name="isprecoveryon"  id="isprecoveryon" style="width:100px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1"  <c:if test="${overdueCompensate.isprecoveryon==1 }">selected="selected"</c:if> >是</option>
									<option value="0"  <c:if test="${overdueCompensate.isprecoveryon==0 }">selected="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>
								
					<div id="precoveryon">
								<div class="row" style="line-height: 0px;">
									<div class="col-md-4 col-md-offset-1">
										<font size="3">平台追偿费收款人</font>&nbsp;&nbsp;：<input type="text" name="pmiscrecman"   value="${overdueCompensate.pmiscrecman }"  style="width:200px;height:25px;font-size: 15px"/> 
									</div>
								</div>
								<hr>
								<div class="row" style="line-height: 0px;">
									<div class="col-md-6 col-md-offset-1">
										<font size="3">分段逾期本金最低值与最高值</font>&nbsp;&nbsp;：
										<input type="text" name="ocminmoney"  value="${overdueCompensate.ocminmoney }"  style="width:100px;height:25px;font-size: 15px"/>元-<input type="text" name="ocmaxmoney"   value="${overdueCompensate.ocmaxmoney }"  style="width:100px;height:25px;font-size: 15px"/>元
									</div>
								</div>
								<hr>
								
								<div class="row" style="line-height: 0px;">
										 <div class="col-md-2 col-md-offset-1" >
													 <font size="3">类型</font>
													 <select id="type"  name="type" style="width:80px;height:25px;font-size: 15px" >
															 <option value="">请选择</option>
															 <option  value="occquota" <c:if test="${overdueCompensate.occquota!=null }">selected="selected"</c:if> >定额</option>
															 <option value="toccrate" <c:if test="${overdueCompensate.toccrate!=null }">selected="selected"</c:if> >百份比</option>
													 </select>
										 </div>
										<div class="col-md-3 " id="occquota">
											<font size="3">追偿定额收费金额</font>&nbsp;&nbsp;：<input type="text" name="occquota"   value="${overdueCompensate.occquota }"  style="width:80px;height:25px;font-size: 15px"/> 
										</div>
										<div id="toccrate">
												<div class="col-md-3 ">
													<font size="3">追偿收费费率</font>&nbsp;&nbsp;：<input type="text" name="toccrate"   value="${overdueCompensate.toccrate }"  style="width:80px;height:25px;font-size: 15px"/> 
												</div>
												<div class="col-md-6">
													<font size="3">追偿该段金额最低及最高收费</font>&nbsp;&nbsp;：
													<input type="text" name="mincfees"   value="${overdueCompensate.mincfees }"  style="width:80px;height:25px;font-size: 15px"/> 元-<input type="text" name="maxcfees"   value="${overdueCompensate.maxcfees }"  style="width:80px;height:25px;font-size: 15px"/> 
												</div>
										</div>
								</div>
								<hr>
					</div>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">会员垫付设置信息</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">是否开通会员垫付</font>&nbsp;&nbsp;：
								<select name="isupayon"  id="isupayon" style="width:100px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1" <c:if test="${overdueCompensate.isupayon==1 }">selected="selected"</c:if> >是</option>
									<option value="0" <c:if test="${overdueCompensate.isupayon==0 }">selected="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>
					
					<div id="upayon">
									<div class="row"  >
										<div class="col-md-4 col-md-offset-1">
										会员等级： 
													<label class="radio-inline"><input class="insert-ugrade-radio" type="radio" name="ugrade"  value="1"   id="selectAll"   <c:if test="${ugrade==1 }">checked="checked"</c:if> > 全部等级</label>
													<label class="radio-inline"><input class="insert-ugrade-radio" type="radio" name="ugrade"  value="2"  id="selectActivity"  <c:if test="${ugrade==2 }">checked="checked"</c:if> >选择等级  &nbsp;&nbsp;  </label>
										</div>
									</div>
									<!--  -->
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
					                         $("#insert-ugrade-checkbox-div input[type=checkbox]").each(function () {
					                             if($(this).val() == value) {
					                                 $(this).attr("checked",true);
					                             }
					                         });
					                     });
					                 }
								</script>
									<hr>
									<div class="row" style="line-height: 0px;">
										<div class="col-md-3 col-md-offset-1">
											<font size="3">本金垫付比例</font>&nbsp;&nbsp;：<input type="text" name="pfprincipalrate"   value="${overdueCompensate.pfprincipalrate }"  style="width:50px;height:25px;font-size: 15px"/>
										</div>
										<div class="col-md-6">
											<font size="3">本金垫付最高金额</font>&nbsp;&nbsp;：<input type="text" name="maxpfprincipal"   value="${overdueCompensate.maxpfprincipal }"  style="width:100px;height:25px;font-size: 15px"/> 
										</div>
									</div>
									<hr>
									<div class="row" style="line-height: 0px;">
										<div class="col-md-3 col-md-offset-1">
											<font size="3">利息垫付比例</font>&nbsp;&nbsp;：<input type="text" name="pfintrate"   value="${overdueCompensate.pfintrate }"  style="width:50px;height:25px;font-size: 15px"/> 
										</div>
										<div class="col-md-6">
											<font size="3">利息垫付最高金额</font>&nbsp;&nbsp;：<input type="text" name="maxpfint"   value="${overdueCompensate.maxpfint }"  style="width:100px;height:25px;font-size: 15px"/> 
										</div>
									</div>
									<hr>
									<div class="row" style="line-height: 0px;">
										<div class="col-md-3 col-md-offset-1">
											<font size="3">滞纳金垫付比例</font>&nbsp;&nbsp;：<input type="text" name="latefeerate"   value="${overdueCompensate.latefeerate }"  style="width:50px;height:25px;font-size: 15px"/> 
										</div>
										<div class="col-md-6 ">
											<font size="3">滞纳金垫付最高金额</font>&nbsp;&nbsp;：<input type="text" name="maxlatefee"  value="${overdueCompensate.maxlatefee }"   style="width:100px;height:25px;font-size: 15px"/>
										</div>
									</div>
									<hr>
						</div>
									
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">是否为模板</font>&nbsp;&nbsp;：
								<select  name="istemplet"    style="width:80px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1"  <c:if test="${overdueCompensate.istemplet==1 }">selected="selected"</c:if> >是</option>
									<option value="0"    <c:if test="${overdueCompensate.istemplet==0 }">selected="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-5">
							<input class="btn btn-primary"  type="button"  id="submitBtu" style="margin-left: 200px" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="backid" class="btn btn-success" name="backid" type="button" onclick="gotoOverdueCompensateList()">返回列表</button>
						</div>
					</div>
				</form> --%>
</body>
</html>
