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
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>OverdueCompensate</title>
<script type="text/javascript">
/* 
$(function(){
	
	$(".grade").each(function(i) {
		var num = $(this).text();
		if (num.length > 5) {
			$(this).text(num.substr(0,20) + "...");
		}
	});
		//根据是否设置平台追偿来显示或隐藏div 
		var isprecoveryon=$.trim($("#isprecoveryon").text());
		if("否"==isprecoveryon){
			$("#precoveryon").hide();
		}
		
		//根据是否开通会员垫付来显示或隐藏div 
		var isupayon=$.trim($("#isupayon").text());
		if("不开通"==isupayon){
			$("#upayon").hide();
		}
		
		
		//回显定额与比率 
		var occquota=$.trim($("#label-occquota").text());
		if(occquota==null||occquota==""){
			$("#occquota").hide();
		}
		
		var toccrate=$.trim($("#label-toccrate").text());
		if(toccrate==null||toccrate==""){
			$("#toccrate").hide();
		}
		
}); */
/* function gotoOverdueCompensateList(){
	   window.location.href="${pageContext.request.contextPath }/overdueCompensate/selectOverdueCompensateByCondition.action";
} */

/* 备注显示字符个数限制*/
jQuery.fn.limit = function() {
	var self = $("[limit]");
	self.each(function() {
		var objString = $(this).text();
		var objLength = $(this).text().length;
		var num = $(this).attr("limit");
		if (objLength > num) {
			objString = $(this).text(objString.substring(0, num) + "...");
		}
	})
}

$(function() {
	$("[limit]").limit();
})

/* 备注tips */
$(function() {
	$("[data-toggle='tooltip']").tooltip({
		html : true
	});
});
</script>
	<style type="text/css">
    hr{
		margin: 10px;
	} 
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
                    <c:if test="${!empty overdueCompensate}">
                      <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">标号Id：</font>
				       <label id="addman-lb" class="col-sm-4">${overdueCompensate.tid}</label>
		              </div>
		              <hr>
		              <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">逾期代偿编号：</font>
				       <label id="addman-lb" class="col-sm-4">${overdueCompensate.overduecno}</label>
		              </div>
		              <hr>
                      <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">逾期代偿人：</font>
				       <label id="addman-lb" class="col-sm-4">${overdueCompensate.cmanno}</label>
		              </div>
		              <hr>
		              <div class="row" style="line-height: 10px;">
				       <font size="3" class="col-sm-4 text-right">是否开通会员垫付：</font>
				       <c:if test="${!empty overdueCompensate.isupayon}">
				          <c:if test="${overdueCompensate.isupayon eq 1}">
				             <label id="addman-lb" class="col-sm-4">是</label>
				          </c:if>
				          <c:if test="${overdueCompensate.isupayon eq 0}">
				             <label id="addman-lb" class="col-sm-4">否</label>
				          </c:if>
				       </c:if>
		              </div>
		              <hr>
		              <c:if test="${overdueCompensate.isupayon eq 1}">
		              <c:forEach items="${overdueCompensates}" var="over">
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">会员等级：</font>
				         <label id="addman-lb" class="col-sm-6" limit="30" data-toggle="tooltip" title="<h5>${over.ugrade}</h5>">${over.ugrade}</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">本金垫付比例：</font>
				         <label id="addman-lb" class="col-sm-4">${over.pfprincipalrate}%</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">本金垫付最高金额：</font>
				         <label id="addman-lb" class="col-sm-4">${over.maxpfprincipal}元</label>
		                </div>
		                <hr>
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">利息垫付比例：</font>
				         <label id="addman-lb" class="col-sm-4">${over.pfintrate}%</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">利息垫付最高金额：</font>
				         <label id="addman-lb" class="col-sm-4">${over.maxpfint}元</label>
		                </div>
		                <hr>
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">滞纳金垫付比例：</font>
				         <label id="addman-lb" class="col-sm-4">${over.latefeerate}%</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">滞纳金垫付最高金额：</font>
				         <label id="addman-lb" class="col-sm-4">${over.maxlatefee}元</label>
		                </div>
		                <hr>
		              </c:forEach>
		              </c:if>
		              <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">资金清算是否需要审核：</font>
				         <c:if test="${!empty overdueCompensate.isaudit}">
				             <c:if test="${overdueCompensate.isaudit eq 1}">
				               <label id="addman-lb" class="col-sm-4">是</label>
				             </c:if>
				             <c:if test="${overdueCompensate.isaudit eq 0}">
				               <label id="addman-lb" class="col-sm-4">否</label>
				             </c:if>
				         </c:if>
		                </div>
		                <hr>
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">是否为模板：</font>
				         <c:if test="${!empty overdueCompensate.istemplet}">
				             <c:if test="${overdueCompensate.istemplet eq 1}">
				               <label id="addman-lb" class="col-sm-4">是</label>
				             </c:if>
				             <c:if test="${overdueCompensate.istemplet eq 0}">
				               <label id="addman-lb" class="col-sm-4">否</label>
				             </c:if>
				         </c:if>
		                </div>
		                <hr>
		                 <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">添加人：</font>
				               <label id="addman-lb" class="col-sm-4">${overdueCompensate.addman}</label>
		                </div>
		                <hr>
		                <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">设置时间：</font>
				               <label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${overdueCompensate.addTime}" type="both"/></label>
		                </div>
		                <hr>
		                  <div class="row" style="line-height: 10px;">
				         <font size="3" class="col-sm-4 text-right">备注：</font>
				               <label id="addman-lb" class="col-sm-4">${overdueCompensate.remark}</label>
		                </div>
                    </c:if>
                    <c:if test="${empty overdueCompensate}">
                      <label>暂无数据</label>
                    </c:if>
</body>
</html>
