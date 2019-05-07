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
<title>interestExpense_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/interestExpense/updateinterestExpense.js"></script>
<script type="text/javascript">
	$(function(){
		//获取radio当前选择的状态
		var $radioVal = $("input[name='ugrade']:checked").val();
		//当选中全部等级时 选择等级div隐藏，反之显示
		if ($radioVal == 1) {
			$("#insert-ugrade-checkbox-div").hide();
		} else if($radioVal == 2) {
			$("#insert-ugrade-checkbox-div").show();
		};
		
		
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
	function gotointerestExpenseList(){
		   window.location.href="${pageContext.request.contextPath}/admin/interestExpense/selectInterestExpenseByCondition.action";
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
	.left_class{
	    width:170px;text-align: right;margin-right: 10px;
	}
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的利息管理费修改页面</a>
</div>
<form action="${pageContext.request.contextPath}/admin/interestExpense/updateInterestExpense.action" method="post" id="updateinterestExpenseForm">
<input type="hidden" value="${interestExpense.id }" name="id">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="5">修改利息管理费参数</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
						    <label class="left_class">利息管理费编号:</label>
							<input type="text" name="intexpno" style="width:200px;height:25px;font-size: 15px" value="${interestExpense.intexpno }"/>
						</div>
					</div>
					<hr>			
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
						    <label class="left_class">利息管理费收款人:</label>
							<input type="text" name="ierecman" style="width:200px;height:25px;font-size: 15px" value="${interestExpense.ierecman }"/>
						</div>
					</div>
					<hr>			
					<%-- <div>
						<div class="row"  >
								<div class="col-md-3 col-md-offset-1" >
								<label class="left_class">会员等级:</label>
								<c:if test='${ugrade eq 1}'>
								     <label class="radio-inline"><input id="selectAll" class="insert-ugrade-radio" type="radio" name="ugrade" value="1" checked="checked"/>全部等级</label>
								</c:if>
							    <c:if test='${ugrade eq 2}'>
							         <label class="radio-inline"><input id="selectActivity" class="insert-ugrade-radio" type="radio" name="ugrade" value="2"  checked="checked" readonly="readonly">选择等级 </label>  
							    </c:if>
								</div>
							</div>
								<!-- 把checkbox迭代出来 -->
								<c:if test="${ugrade eq 2}">
								    <div class="row">
										<div class="col-md-12 col-md-offset-1">
											<div style="padding-left: 30px; padding-top: 12px;" id="insert-ugrade-checkbox-div">
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
								</c:if>
									<!-- 回显 -->
									<script type="text/javascript">
										 var ugrades1 =eval("("+$("#ugrades1").val()+")");
						                 if (ugrades1.length>0) {
						                     $.each(ugrades1, function(index, value){
						                         $("input[type=checkbox]").each(function () {
						                             if($(this).val() == value) {
						                                 $(this).attr("checked",true);
						                                 $(this).attr("disabled",true);
						                             }
						                         });
						                     });
						                 }
									</script>
								<div style="margin-bottom: 22px"></div>
					</div> --%>
                     <c:if test="${!empty interestExpenses}">
                       <c:forEach items="${interestExpenses}" var="pen" varStatus="sta">
                          <div class="row" style="line-height: 0px;">
						   <div class="col-md-4 col-md-offset-1">
						    <label class="left_class">会员等级:</label>
							<input type="text" name="ugrages" style="width:200px;height:25px;font-size: 15px" value="${pen.ugrade}"/>
						   </div>
					       </div>
					       <hr>
							<div class="row" style="line-height: 0px;">
								<div class="col-md-4 col-md-offset-1">
									<label class="left_class">利息管理费百份比:</label><input type="text" name="expenses[${sta.index}].iepercent" style="width:200px;height:25px;font-size: 15px" value="${pen.iepercent*100}"/>
								</div>
								
							</div>
							<hr>
							<div class="row" style="line-height: 0px;">
							  <div class="col-md-4 col-md-offset-1">
									<label class="left_class">利息最低与最高收费金额:</label><input type="text"  value="${pen.miniefee }" name="expenses[${sta.index}].miniefee" style="width:100px;height:25px;font-size: 15px"/>元&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<input type="text"  value="${pen.maxiefee }" name="expenses[${sta.index}].maxiefee" style="width:100px;height:25px;font-size: 15px"/>元
								</div>
								</div>
							 <hr>
                       </c:forEach>
                     </c:if>
                     <div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">资金清算是否需要审核:</label>
								<select name="istemplet" style="width:7	0px;height:25px;font-size: 15px">
									<option value="1"  <c:if test="${interestExpense.istemplet==1 }">selected="selected"</c:if> >是</option>
									<option value="0" <c:if test="${interestExpense.istemplet==0 }">selected="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">是否为模板:</label>
								<select name="istemplet" style="width:7	0px;height:25px;font-size: 15px">
									<option value="1"  <c:if test="${interestExpense.istemplet==1 }">selected="selected"</c:if> >是</option>
									<option value="0" <c:if test="${interestExpense.istemplet==0 }">selected="selected"</c:if> >否</option>
								</select>
						</div>
					</div>
					<hr>			
					<div class="row" style="line-height: 0px;">
						<div class="col-md-5">
							<button class="btn btn-primary" type="submit" style="margin-left: 200px">修改</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="backid" class="btn btn-success" name="backid" type="button" onclick="javascript:history.back(-1);">返回列表</button>
						</div>
					</div>
				</form>
</body>
</html>
