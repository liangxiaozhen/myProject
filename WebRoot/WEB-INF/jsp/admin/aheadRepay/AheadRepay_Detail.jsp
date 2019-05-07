<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>AheadRepay</title>
<script type="text/javascript">
$(function(){
	/*第一层判断：开或关*/
	var ispicompensateonLabelValue=$.trim($("#ispicompensateonLabel").text());
	var ispluscompensateonLabelValue=$.trim($("#ispluscompensateonLabel").text());
	var isforplatformonLabelValue=$.trim($("#isforplatformonLabel").text());
	if("关"==ispicompensateonLabelValue){
		$("#ben_jin_bu_chang").hide();
	}
	if("关"==ispluscompensateonLabelValue){
		$("#zheng_yi_bu_chang").hide();
	}
	if("关"==isforplatformonLabelValue){
		$("#bu_chang_ping_tai").hide();
	}
	/*第二层判断：惩罚借款人或平台奖励或两个并存*/
	var awardtypeLabelValue=$.trim($("#awardtypeLabel").text());
	if("惩罚借款人"==awardtypeLabelValue){
		$("#ping_tai").hide();
	}
	if("平台奖励"==awardtypeLabelValue){
		$("#jie_kuan_ren").hide();
	}
	
	/*第二层判断：平台罚金或平台奖励或两个并存*/
	var plusawardtypeLabelValue=$.trim($("#plusawardtypeLabel").text());
	if("平台罚金"==plusawardtypeLabelValue){
		$("#zheng_yi_ping_tai_jiang_li").hide();
	}
	if("平台奖励"==awardtypeLabelValue){
		$("#zheng_yi_ping_tai_fa_jin").hide();
	}
	
	/* 第三层判断：定额或百分比 */
	var penaltyquota=$.trim($("#label-penaltyquota").text());
	var penaltyrate=$.trim($("#label-penaltyrate").text());
	if(penaltyquota==null||penaltyquota==""){
		$("#penaltyquota").hide();
	}
	if(penaltyrate==null||penaltyrate==""){
		$("#penaltyrate").hide();
	}
	
	
	/* 第三层判断：定额或百分比 */  
	var pluspenaltyquota=$.trim($("#label-pluspenaltyquota").text());
	var pluspenaltyrate=$.trim($("#label-pluspenaltyrate").text());
	if(pluspenaltyquota==null||pluspenaltyquota==""){
		$("#pluspenaltyquota").hide();
	}
	if(pluspenaltyrate==null||pluspenaltyrate==""){
		$("#pluspenaltyrate").hide();
	}
	
	/* 第三层判断：定额或百分比 */  
	var awardplatquota=$.trim($("#label-awardplatquota").text());
	var awardplatrate=$.trim($("#label-awardplatrate").text());
	if(awardplatquota==null||awardplatquota==""){
		$("#awardplatquota").hide();
	}
	if(awardplatrate==null||awardplatrate==""){
		$("#awardplatrate").hide();
	}
	   
	$(".grade").each(function(i) {
		var num = $(this).text();
		if (num.length > 5) {
			$(this).text(num.substr(0,20) + "...");
		}
	});
	
});
function gotoAheadRepayList(){
	   window.location.href="${pageContext.request.contextPath }/aheadRepay/selectAheadRepayByCondition.action";
}

</script>
<style type="text/css">
#id {
	margin: 40px;
}

hr {
	margin: 10px;
}
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
                   <c:if test="${!empty aheadRepay}">
                    <div class="row" style="line-height:10px;">
		                <font size="3" class="col-sm-4 text-right">标号Id:</font>
		                <label class="col-sm-4">${aheadRepay.tid}</label>
		             </div>
                     <hr>
                      <div class="row" style="line-height:10px;">
		                <font size="3" class="col-sm-4 text-right">提前还款个人利息奖励编号:</font>
		                <label class="col-sm-4">${aheadRepay.aheadrepayno}</label>
		             </div>
                     <hr>
                     <c:forEach items="${aheadRepays}" var="pay">
                        <div class="row" style="line-height:10px;">
		                <font size="3" class="col-sm-4 text-right">单个投资人累计本金欠收最小利息:</font>
		                <label class="col-sm-4">${pay.minnoreceiveint}~${pay.maxnoreceiveint}元</label>
		                </div>
                        <hr>
                         <div class="row" style="line-height:10px;">
		                <font size="3" class="col-sm-4 text-right">奖励方式:</font>
		                <c:if test="${!empty pay.awardtype}">
		                   <c:if test="${pay.awardtype eq 1}">
		                     <label class="col-sm-4">惩罚借款人</label>
		                   </c:if>
		                    <c:if test="${pay.awardtype eq 2}">
		                     <label class="col-sm-4">平台奖励</label>
		                   </c:if>
		                    <c:if test="${pay.awardtype eq 3}">
		                     <label class="col-sm-4">惩罚借款人及平台奖励</label>
		                   </c:if>
		                </c:if>
		                </div>
                        <hr>
                        <c:if test="${!empty pay.loanpenaltyname}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">借款人罚金奖励名称:</font>
		                   <label class="col-sm-4">${pay.loanpenaltyname}</label>
		                  </div>
                          <hr>
                        </c:if>
                        <c:if test="${!empty pay.penaltyquota}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">借款人罚金定额:</font>
		                   <label class="col-sm-4">${pay.penaltyquota}元</label>
		                  </div>
                          <hr>
                        </c:if>
                        <c:if test="${!empty pay.penaltyrate}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">借款人罚金百分比:</font>
		                   <label class="col-sm-4">${pay.penaltyrate*100}%</label>
		                  </div>
                          <hr>
                        </c:if>
                        <c:if test="${!empty pay.maxpenalty}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">借款人罚金最大值:</font>
		                   <label class="col-sm-4">${pay.maxpenalty}元</label>
		                  </div>
                          <hr>
                        </c:if>
                        <c:if test="${!empty pay.pawardname}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">平台奖励奖品名称:</font>
		                   <label class="col-sm-4">${pay.pawardname}</label>
		                  </div>
                          <hr>
                        </c:if>
                         <c:if test="${!empty pay.pawardno}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">平台奖励奖品编号:</font>
		                   <label class="col-sm-4">${pay.pawardno}</label>
		                  </div>
                          <hr>
                        </c:if>
                         <c:if test="${!empty pay.pawardquota}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">平台奖励定额:</font>
		                   <label class="col-sm-4">${pay.pawardquota}元</label>
		                  </div>
                          <hr>
                        </c:if>
                         <c:if test="${!empty pay.pawardrate}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">平台奖励百分比:</font>
		                   <label class="col-sm-4">${pay.pawardrate}%</label>
		                  </div>
                          <hr>
                        </c:if>
                         <c:if test="${!empty pay.maxpaward}">
                           <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">平台奖励最大值:</font>
		                   <label class="col-sm-4">${pay.maxpaward}元</label>
		                  </div>
                          <hr>
                        </c:if>
                     </c:forEach>
                          <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">资金清算是否需要审核:</font>
		                   <c:if test="${aheadRepay.isaudit eq 1}">
		                      <label class="col-sm-4">是</label>
		                   </c:if>
		                   <c:if test="${aheadRepay.isaudit eq 0}">
		                      <label class="col-sm-4">否</label>
		                   </c:if>
		                  </div>
                          <hr>
                          <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">是否为模板:</font>
		                   <c:if test="${aheadRepay.istemplet eq 1}">
		                      <label class="col-sm-4">是</label>
		                   </c:if>
		                   <c:if test="${aheadRepay.istemplet eq 0}">
		                      <label class="col-sm-4">否</label>
		                   </c:if>
		                  </div>
                          <hr>
                          <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">添加人:</font>
		                      <label class="col-sm-4">${aheadRepay.addman}</label>
		                  </div>
                          <hr>
                          <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">设置时间:</font>
		                      <label class="col-sm-4"><fmt:formatDate value="${aheadRepay.addtime}" type="both"/></label>
		                  </div>
                          <hr>
                          <div class="row" style="line-height:10px;">
		                   <font size="3" class="col-sm-4 text-right">备注:</font>
		                      <label class="col-sm-4">${aheadRepay.remark}</label>
		                  </div>
                          <hr>
                   </c:if>
                   <c:if test="${empty aheadRepay}">
                       <label>暂无数据!</label>
                   </c:if>
</body>
</html>
