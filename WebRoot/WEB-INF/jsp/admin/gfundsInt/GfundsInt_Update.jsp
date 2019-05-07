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
<title>GfundsInt_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/gfundsint/updateGfundsInt.js"></script>
<script type="text/javascript">
	$(function(){
		
		/* 根据 全部等级与选择等级的选中状态来隐藏相应的div*/
		var $radioVal=$(".insert-ugrade-radio:checked").val();
		//当选中全部等级时 选择等级div隐藏，反之显示
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
			/* 通过标的设置来到这里的就有nextPage，不会回调回来 */
				if(check().form()){
					$("form:first").submit();
				}
		});
	});
	function gotoGfundsIntList(){
		   window.location.href="${pageContext.request.contextPath }/admin/gfundsInt/selectGfundsIntByCondition.action";
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
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/admin/gfundsInt/updateGfundsInt.action"  method="post">
<div class="container" style="margin-top: 25px;" >
				<!--站岗利息清算方式-->
				<c:if test="${empty GfundsInt}">
				  <label>暂无数据</label>
				</c:if>
				<c:if test="${!empty GfundsInt}">
				    <div class="form-group">
					<label for="clearmethod" class="col-sm-3 control-label">站岗利息清算方式</label>
					<div class="col-sm-3">
						<select name="clearmethod" id="clearmethod" class="form-control">
						    <c:if test="${GfundsInt.clearmethod eq 1}">
						      <option value="1">结标清算</option>
						    </c:if>
						</select>
					</div>
				</div>
				
				<!--投标人等级-->
				<div class="form-group">
					<label for="ugradeone" class="col-sm-3 control-label">投标人等级</label>
					<div class="col-sm-3">
						  <label class="radio-inline">
						 	<input type="radio" name="ugrade" id="ugradeone" value="1"  class="ugrade_radio"/>全部等级
						 </label>
						 <label class="radio-inline">
						 	<input type="radio" name="ugrade" id="ugradetwo" value="2"   class="ugrade_radio"/>选择等级
						 </label>
					</div>
				</div>
				<!--允许投标的会员等级-->
				<div id="ugrades_div">
				<div class="form-group"  id="ugrestrict_div">
					<label for="ugrestrict" class="col-sm-3 control-label"></label>
					<div class="col-sm-6">
						  <c:if test="${!empty uGrades }">
					        <c:forEach items="${uGrades }" var="ugr" varStatus="status">
					            <label class="checkbox-inline"  style="width:80px;">
							          <input type="checkbox" id="ugrestricts"  name="ugrades" value="${ugr.ugrade}" />${ugr.ugradename}
						         </label>
						         <c:if test="${status.count%4==0 }">
						          <br />
								</c:if>
					        </c:forEach>
					    </c:if>
					</div>
				</div>
				</div>
				<!--分段投资最低与最高金额-->
				<c:forEach items="${GfundsInts}" var="gfun" varStatus="sta">
				<input type="hidden" name="gfundsInts[${sta.index}].id" value="${gfun.id}"/>
				<div class="form-group">
					<label for="inputminmoney" class="col-sm-3 control-label">分段投资金额最低与最高</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="gfundsInts[${sta.index}].minmoney" id="inputminmoney" value="${gfun.minmoney}" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="gfundsInts[${sta.index}].maxmoney" id="inputmaxmoney" value="${gfun.maxmoney}" class="form-control"/>
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				<!--定额-->
				<c:if test="${!empty gfun.quota}">
				<div class="form-group"  id="quotainput_div">
					<label class="col-sm-3 control-label" for="quotainput">定额补偿金</label>
					<div class="col-sm-3">
					<div class="input-group">
					<input type="text" name="gfundsInts[${sta.index}].quota" class="form-control" id="quotainput" value="${gfun.quota}"/>
					<span class="input-group-addon">元</span>
					</div>
					</div>
				</div>
				</c:if>
				<!--日奖费率-->
				<c:if test="${!empty gfun.dayawardrate}">
				<div class="form-group">
					<label class="control-label col-sm-3" for="dayawardrate">日奖励费率</label>
					<div class="col-sm-3">
						<div class="input-group">
						<input type="text" name="gfundsInts[${sta.index}].dayawardrate" id="dayawardrate"  value="${gfun.dayawardrate*100}" class="form-control"/>
						<span class="input-group-addon">%</span>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${!empty gfun.maxcompensate}">
				<!--最高补偿金额-->
				<div class="form-group" >
					<label class="control-label col-sm-3" for="maxcompensate">最高补偿金额</label>
					<div class="col-sm-3">
					     <div class="input-group">
						<input type="text" name="gfundsInts[${sta.index}].maxcompensate" id="maxcompensate" value="${gfun.maxcompensate}" class="form-control"/>
						<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</c:if>
				</c:forEach>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="control-label col-sm-3" for="isaudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
						 <select name="isaudit" class="form-control" id="isaudit">
						 <c:if test="${GfundsInt.isaudit eq 1}">
						     <option value="1">是</option>
						 </c:if>
						 <c:if test="${GfundsInt.isaudit eq 0}">
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
						 <c:if test="${GfundsInt.istemplet eq 1}">
						    <option value="1">是</option>
						 </c:if>
						 <c:if test="${GfundsInt.istemplet eq 0}">
						    <option value="0">否</option>
						 </c:if>
						 </select>
					</div>
				</div>
				<!-- 备注 -->
				<div class="form-group">
				   <label class="control-label col-sm-3">备注</label>
				   <div class="col-sm-3">
				       <textarea rows="3" name="remark" class="form-control">${GfundsInt.remark}</textarea>
				   </div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-success" onclick="gotoGfundsIntList()">返回列表</button>
					</div>
				</div>
				</c:if>
		</div>
		</form>


<%-- <form action="${pageContext.request.contextPath}/gfundsInt/updateGfundsInt.action" method="post" id="updateGfundsIntForm">
	<input type="hidden" value="${gfundsInt.id }" name="id">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="5">修改站岗利息参数</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-10 col-md-offset-1">
							<font size="3">清算方式</font>&nbsp;&nbsp;：
								<select name="clearmethod" style="width:150px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1"  <c:if test="${gfundsInt.clearmethod==1 }">selected="selected"</c:if> >结标清算</option> 
									<option value="2"  <c:if test="${gfundsInt.clearmethod==2 }">selected="selected"</c:if> >最后一期还款清算</option>
								</select>
						</div>
					</div>
					<hr>
							    <div class="row"  >
										<div class="col-md-4 col-md-offset-1">
										会员等级： 
													<label class="radio-inline"><input class="insert-ugrade-radio" type="radio" name="ugrade"  value="1"  id="selectAll"  <c:if test="${ugrade==1 }">checked="checked"</c:if> > 全部等级</label>
													<label class="radio-inline"><input class="insert-ugrade-radio" type="radio" name="ugrade"  value="2"  id="selectActivity"  <c:if test="${ugrade==2 }">checked="checked"</c:if> >选择等级  &nbsp;&nbsp;  </label>
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
									<hr>
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
					<div class="row" style="line-height: 0px;">
						<div class="col-md-10 col-md-offset-1">
							<font size="3">分段最低与最高投资金额</font>&nbsp;&nbsp;：<input type="text" name="minmoney"  value="${gfundsInt.minmoney }" style="width:100px;height:25px;font-size: 15px"/>元-<input type="text" name="maxmoney"   value="${gfundsInt.maxmoney }"  style="width:100px;height:25px;font-size: 15px"/>元 
						</div>
					</div>
					<hr>
					<div class="row">		
									<div class="col-md-2 col-md-offset-1" >
													<font size="3">类型</font>
													<select id="type"  name="type" style="width:80px;height:25px;font-size: 15px" >
														<option value="">请选择</option>
														<option  value="iequota"  <c:if test="${gfundsInt.quota!=null }">selected="selected"</c:if> >定额</option>
														<option value="iepercent"  <c:if test="${gfundsInt.dayawardrate!=null }">selected="selected"</c:if> >百份比</option>
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
											
											<div  id="iequota" class="col-md-5">
													<font size="3">定额补偿金</font>&nbsp;&nbsp;：<input type="text" name="quota"    value="${gfundsInt.quota }"  style="width:100px;height:25px;font-size: 15px"/>元
											</div>
											
											<div id="iepercent" >
													<div class="col-md-3">
															<font size="3">日奖励费率</font>&nbsp;&nbsp;：<input type="text" name="dayawardrate"  value="${gfundsInt.dayawardrate }"   style="width:100px;height:25px;font-size: 15px"/> 
													</div>
													<div class="col-md-5">
															<font size="3">最高补偿金额：</font>&nbsp;&nbsp;<input type="text" name="maxcompensate"   value="${gfundsInt.maxcompensate }"  style="width:100px;height:25px;font-size: 15px"/> 
													</div>
											</div>
							</div>
							<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<font size="3">是否为模板</font>&nbsp;&nbsp;：
								<select  name="istemplet" style="width:100px;height:25px;font-size: 15px">
									<option value="">请选择</option>
									<option value="1"  <c:if test="${gfundsInt.istemplet==1 }">selected="selected"</c:if> >是</option>
									<option value="0"  <c:if test="${gfundsInt.istemplet==0 }">seletced="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>	
					<div class="row" style="line-height: 0px;">
						<div class="col-md-5">
							<input class="btn btn-primary"  type="button"  id="submitBtu" style="margin-left: 200px" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="backid" class="btn btn-success" name="backid" type="button" onclick="gotoGfundsIntList()">返回列表</button>
						</div>
					</div>
				</form> --%>
</body>
</html>
