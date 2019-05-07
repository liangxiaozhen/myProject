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
<title>Plus</title>
<!-- <script type="text/javascript">
$(function(){
	var isaintLabelValue=$.trim($("#isaintLabel").text());
	var avoucherLabelValue=$.trim($("#avoucherLabel").text());
	var alikevoucherLabelValue=$.trim($("#alikevoucherLabel").text());
	if("不允许"==isaintLabelValue){
		$("#aint").hide();
	}
	if("不允许"==avoucherLabelValue){
		$("#avoucher").hide();
	}
	if("不允许"==alikevoucherLabelValue){
		$("#alikevoucher").hide();
	}
});
function gotoPlusList(){
	   window.location.href="${pageContext.request.contextPath }/plus/selectPlusByCondition.action";
}
</script> -->
	<style type="text/css">
    hr{
		margin: 10px;
	} 
</style>
</head>
<body  style="font-family:'微软雅黑'; ">

              <c:if test="${!empty plus}">
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">增益设置编号:</font>
		            <label class="col-sm-4">${plus.plusno}</label>
		       </div>
               <hr>
               <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">是否允许加息卷:</font>
		         <c:if test="${!empty plus.isaint}">
		           <c:if test="${plus.isaint eq 1}">
		           <label class="col-sm-4">是</label>
		           </c:if>
		           <c:if test="${plus.isaint eq 0}">
		           <label class="col-sm-4">否</label>
		           </c:if>
		         </c:if>
		       </div>
		       <hr>
		       <!-- 允许使用加息券 -->
               <c:if test="${plus.isaint eq 1}">
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">单次允许使用加息张数:</font>
		            <label class="col-sm-4">${plus.aonceint}张</label>
		           </div>
                   <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">允许使用加息总张数:</font>
		            <label class="col-sm-4">${plus.atotalint}张</label>
		           </div>
                   <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">允许单张加息收益:</font>
		            <label class="col-sm-4">${plus.aoneqrofit}%</label>
		           </div>
                   <hr>
               </c:if>
               <!-- 是否允许类现金券 -->
                 <div class="row" style="line-height:10px;">
		         <font size="3" class="col-sm-4 text-right">是否允许类现金卷:</font>
		         <c:if test="${!empty plus.isavoucher}">
		           <c:if test="${plus.isavoucher eq 1}">
		           <label class="col-sm-4">是</label>
		           </c:if>
		           <c:if test="${plus.isavoucher eq 0}">
		           <label class="col-sm-4">否</label>
		           </c:if>
		         </c:if>
		       </div>
		       <hr>
		       <c:if test="${plus.isavoucher eq 1}">
		          <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">单次允许类现金卷张数:</font>
		            <label class="col-sm-4">${plus.aoncevoucher}张</label>
		          </div>
                  <hr>
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">总计允许类现金卷张数:</font>
		            <label class="col-sm-4">${plus.atotalvoucher}张</label>
		          </div>
                  <hr>
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">允许单张类现金额度:</font>
		            <label class="col-sm-4">${plus.aonevamount}元</label>
		          </div>
                  <hr>
		       </c:if>
		       <!-- 是否允许假现金券 -->
		         <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">是否允许假现金卷:</font>
		            <c:if test="${!empty plus.isalikevoucher}">
		               <c:if test="${plus.isalikevoucher eq 1}">
		                  <label class="col-sm-4">是</label>
		               </c:if>
		               <c:if test="${plus.isalikevoucher eq 0}">
		                  <label class="col-sm-4">否</label>
		               </c:if>
		            </c:if>
		          </div>
                  <hr>
                 <c:if test="${plus.isalikevoucher eq 1}">
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">单次允许假现金卷张数:</font>
		            <label class="col-sm-4">${plus.aoncelikevoucher}张</label>
		          </div>
                  <hr>
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">总计允许假现金卷张数:</font>
		            <label class="col-sm-4">${plus.atotallikevoucher}张</label>
		          </div>
                  <hr>
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">允许单张假现金额度:</font>
		            <label class="col-sm-4">${plus.aonelvamount}元</label>
		          </div>
                  <hr>
                 </c:if>
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">单次允许的增益方式:</font>
		            <label class="col-sm-4">${plus.aonceplusmode}</label>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">总计允许的增益方式:</font>
		            <label class="col-sm-4">${plus.atotalplusmode}</label>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">增益清算付款人:</font>
		            <label class="col-sm-4">${plus.payforplusman}</label>
		          </div>
                  <hr>
                  <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">清算方式:</font>
		            <c:if test="${!empty plus.clearmode}">
		              <c:if test="${plus.clearmode eq 1}">
		                <label class="col-sm-4">结标清算</label>
		              </c:if>
		              <c:if test="${plus.clearmode eq 2}">
		                <label class="col-sm-4">首期清算</label>
		              </c:if>
		              <c:if test="${plus.clearmode eq 3}">
		                <label class="col-sm-4">每期清算</label>
		              </c:if>
		              <c:if test="${plus.clearmode eq 4}">
		                <label class="col-sm-4">尾期清算</label>
		              </c:if>
		            </c:if>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">资金清算是否需要审核:</font>
		            <c:if test="${! empty plus.isaudit}">
		               <c:if test="${plus.isaudit eq 1}">
		                  <label class="col-sm-4">是</label>
		               </c:if>
		               <c:if test="${plus.isaudit eq 0}">
		                  <label class="col-sm-4">否</label>
		               </c:if>
		            </c:if>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">是否为模板:</font>
		            <c:if test="${! empty plus.istemplet}">
		               <c:if test="${plus.istemplet eq 1}">
		                  <label class="col-sm-4">是</label>
		               </c:if>
		               <c:if test="${plus.istemplet eq 0}">
		                  <label class="col-sm-4">否</label>
		               </c:if>
		            </c:if>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">是否为模板:</font>
		               <c:if test="${! empty plus.addman}">
		                  <label class="col-sm-4">${plus.addman}</label>
		               </c:if>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">设置时间:</font>
		               <c:if test="${! empty plus.addtime}">
		                  <label class="col-sm-4"><fmt:formatDate value="${plus.addtime}" type="both"/></label>
		               </c:if>
		          </div>
                  <hr>
                   <div class="row" style="line-height:10px;">
		            <font size="3" class="col-sm-4 text-right">备注:</font>
		               <c:if test="${! empty plus.remark}">
		                  <label class="col-sm-4">${plus.remark}</label>
		               </c:if>
		          </div>
              </c:if>
              <c:if test="${empty plus}">
                  <label class="col-sm-4">暂无数据！</label>
              </c:if>
</body>
</html>
