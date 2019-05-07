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
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>DebtAttorn</title>
<script type="text/javascript">
$(function(){
	var quota=$.trim($("#label-quota").text());
	var attornrate=$.trim($("#label-attornrate").text());
	if(quota==null||quota==""){
		$("#quota").hide();
	}
	if(attornrate==null||attornrate==""){
		$("#attornrate").hide();
	}
	$(".grade").each(function(i) {
		var num = $(this).text();
		if (num.length > 5) {
			$(this).text(num.substr(0,20) + "...");
		}
	});
});

function gotoDebtAttornList(){
	   window.location.href="${pageContext.request.contextPath }/debtAttorn/selectDebtAttornByCondition.action";
}
</script>
	<style type="text/css">
	#id{
		margin:40px;
	} 
    hr{
		margin: 10px;
	} 
</style>
</head>
<body   style="font-family:'微软雅黑'; ">
               <c:if test="${!empty debtAttorn}">
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">债权转让编号:</font>
		            <label class="col-sm-4">${debtAttorn.debtattornno}</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">债权转让审核:</font>
		         <c:if test="${debtAttorn.isdebtaudit eq 1}">
		           <label class="col-sm-4">需要</label>
		         </c:if>
		         <c:if test="${debtAttorn.isdebtaudit eq 0}">
		           <label class="col-sm-4">不需要</label>
		         </c:if>  
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">是否支持逾期债转:</font>
		         <c:if test="${debtAttorn.isocdebt eq 1}">
		           <label class="col-sm-4">支持</label>
		         </c:if>
		         <c:if test="${debtAttorn.isocdebt eq 0}">
		           <label class="col-sm-4">不支持</label>
		         </c:if>  
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">债转增益处理:</font>
		         <c:if test="${debtAttorn.intdisable eq 1}">
		           <label class="col-sm-4">全部失效</label>
		         </c:if>
		         <c:if test="${debtAttorn.intdisable eq 2}">
		           <label class="col-sm-4">按债转金额比例失效</label>
		         </c:if>
		         <c:if test="${debtAttorn.intdisable eq 3}">
		           <label class="col-sm-4">不作废</label>
		         </c:if>   
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">允许债权人债转的等级:</font>
		            <label class="col-sm-4">${debtAttorn.aownergrade}</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">持有时间:</font>
		            <label class="col-sm-4">${debtAttorn.holdday}天</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">距离下个还款日天数:</font>
		            <label class="col-sm-4">${debtAttorn.intervalday}天</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">逾期前多少天可债转:</font>
		            <label class="col-sm-4">${debtAttorn.aheadocday}天</label>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">债转期限:</font>
		            <label class="col-sm-4">${debtAttorn.deadline}天</label>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">债转次数方式限制:</font>
		            <c:if test="${debtAttorn.dattrestrict eq 1}">
		              <label class="col-sm-4">层级次数</label>
		           </c:if>
		           <c:if test="${debtAttorn.dattrestrict eq 2}">
		              <label class="col-sm-4">每人次数</label>
		           </c:if>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">债转次数限制:</font>
		            <c:if test="${debtAttorn.dattrestrict eq 1}">
		              <label class="col-sm-4">${debtAttorn.datimes}</label>
		           </c:if>
		           <c:if test="${debtAttorn.dattrestrict eq 2}">
		              <label class="col-sm-4">${debtAttorn.datimes}</label>
		           </c:if>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">起息日时间点:</font>
		            <label class="col-sm-4">${debtAttorn.valuepoint}</label>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">起息规则:</font>
		         <c:if test="${!empty debtAttorn.valuerule}">
		             <c:if test="${debtAttorn.valuerule eq 1}">
		                <label class="col-sm-4">承接日当天</label>
		             </c:if>
		             <c:if test="${debtAttorn.valuerule eq 2}">
		                <label class="col-sm-4">承接日次日</label>
		             </c:if>
		             <c:if test="${debtAttorn.valuerule eq 3}">
		                <label class="col-sm-4">固定时间点</label>
		             </c:if>
		         </c:if>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">是否允许拆分:</font>
		         <c:if test="${!empty debtAttorn.isasplit}">
		           <c:if test="${debtAttorn.isasplit eq 1}">
		             <label class="col-sm-4">是</label>
		           </c:if>
		           <c:if test="${debtAttorn.isasplit eq 0}">
		             <label class="col-sm-4">否</label>
		           </c:if>
		         </c:if>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">可转让金额低值-高值:</font>
		             <label class="col-sm-4">${debtAttorn.attornmoneylow}-${debtAttorn.attornmoney}元</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">转让系数低值-高值:</font>
		             <label class="col-sm-4">${debtAttorn.minattornratio}-${debtAttorn.maxattornratio}</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">是否为模板:</font>
		         <c:if test="${!empty debtAttorn.istemplet}">
		            <c:if test="${debtAttorn.istemplet eq 1}">
		                <label class="col-sm-4">是</label>
		            </c:if>
		            <c:if test="${debtAttorn.istemplet eq 0}">
		                <label class="col-sm-4">否</label>
		            </c:if>
		         </c:if>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">添加人:</font>
		             <label class="col-sm-4">${debtAttorn.addman}</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">设置时间:</font>
		             <label class="col-sm-4"><fmt:formatDate value="${debtAttorn.addtime}" type="both"/></label>
		       </div>
               <hr>
                <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">备注:</font>
		             <label class="col-sm-4">${debtAttorn.remark}</label>
		       </div>
              </c:if>
              <c:if test="${empty debtAttorn}">
                 <label class="col-sm-4">暂无数据!</label>
              </c:if>
</body>
</html>
