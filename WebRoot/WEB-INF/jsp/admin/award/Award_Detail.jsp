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
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>award_detail</title>
<script type="text/javascript">
	//返回列表
	function gotoAwardList(pageNum){
		   window.location.href="${pageContext.request.contextPath }/admin/award/selectAwardByCondition.action?pageNum="+pageNum;
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
<body style="font-family:'微软雅黑';">
<div class="container">
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="3">基本信息</font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">奖品编号：</font><font id="addman-lb">${award.ano }</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		
		<font class="col-md-2 text-right">奖品名称：</font><font id="addtime-lb">${award.aname }</font>
		
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">奖品类型：</font>
		<font id="addman-lb">
			<c:forEach items="${atype_map }" var="am">
				<c:choose>
					<c:when test="${award.atype==am.key }">${am.value }</c:when>
				</c:choose>
			</c:forEach>
		</font>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		
		<font class="col-md-2 text-right">奖品属性： </font>
		<font id="addtime-lb">
			<c:forEach items="${attributeData_map }" var="at">
				<c:choose>
						<c:when test="${award.attribute==at.key }">${at.value }</c:when>
				</c:choose>
			</c:forEach>
		</font>
			
	</div>
	<hr>
	
	<%-- <c:if test="${award.attribute==5}">
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">加息对象： </font>
			<c:forEach items="${cashSet}" var="cs">
				<font>${cs}</font>
			</c:forEach>		
		</div>
		<hr>
	</c:if> --%>
	
	<c:if test="${award.atype eq 1}">
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">奖额：</font>
			<font>
				<c:if test="${!empty award.cashorvoucher}">
					${award.cashorvoucher}
				</c:if>
				<c:if test="${empty award.cashorvoucher}">
					--
				</c:if>
			</font>
		</div>
		<hr>
	</c:if>
	
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">定向名单编号： </font>
		<font>${s.businessNo}</font>
	</div>
	<hr>
	
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">是否下架：  </font>
		<font >
    		<c:choose>
    			<c:when test="${award.iscancel==0 }">
    				下架
    			</c:when>
    			<c:when test="${award.iscancel==1 }">
    				上架
    			</c:when>
    		</c:choose>
		</font>
	</div>
	<hr>
	
	<%-- <div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">是否为模板：  </font>
		<font >
    		<c:choose>
    			<c:when test="${award.isTemplet==1}">
    				是
    			</c:when>
    			<c:when test="${award.isTemplet==2 }">
    				否
    			</c:when>
    		</c:choose>
		</font>
	</div>
	<hr> --%>
	
	<div class="row" style="line-height: 0px;">
			
		<font class="col-md-2 text-right">奖品交易方式子开关： </font>
		<font id="addtime-lb">
				<c:choose>
	    			<c:when test="${award.subswitch==0 }">
	    				关
	    			</c:when>
	    			<c:when test="${award.subswitch==1 }">
	    				开
	    			</c:when>
	    		</c:choose>
		</font>
		
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		
			<font class="col-md-2 text-right">奖品交易方式： </font>
			<font id="addman-lb">
				<c:forEach items="${tradetype_map }" var="tm">
						<c:if test="${award.tradetype==tm.key }">${tm.value }</c:if>
				</c:forEach>
			</font>
		
	</div>
	<hr>
	
	<div class="row" style="line-height: 0px;">
			
		<font class="col-md-2 text-right">有效期： </font>
		<font id="addtime-lb">
			<fmt:formatDate value='${award.endtime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
		</font>
			
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		
			<font class="col-md-2 text-right">奖品总份数： </font>
			<font id="addtime-lb">
				${award.quantityall}份
			</font>
		
	</div>
	<hr>	
	
	<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">奖品剩余数： </font>
			<font id="addtime-lb">
				${award.quantityrest}份
			</font>
	</div>
	<hr>
	
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">添加时间：</font>
		<font id="addman-lb">
			<fmt:formatDate value='${award.addtime}' type='both' pattern="yyyy-MM-dd HH:mm:ss"/>
		</font>
    </div>
	<hr>
	
	<div class="row" style="line-height: 0px;">
		
		<font class="col-md-2 text-right">添加人： </font>
		<font id="addtime-lb">
			${award.addman}
		</font>
		
	</div>
	<hr>	
	
	<c:if test="${award.atype ne 1}">
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">领奖规则： </font>
			<font>
				${award.awardRules}
			</font>
				
		</div>
		<hr>					
	</c:if>
	
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">备注：</font>
		<font id="addman-lb">
			${award.remark}
		</font>
	</div>
	<hr>
		
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font><font size="3">投标使用限制</font></font>
		</div>
	</div>
	<hr>
	
	<c:if test="${!empty tiList}">
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">指定标的名称： </font>
			<font>
				<c:forEach items="${tiList}" var="tl">
					${tl}
				</c:forEach>
			</font>
		</div>
		<hr>
	</c:if>
	
	
	<c:if test="${empty tiList}">
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">投标属性限制： </font>
			<font>
				<c:forEach items="${bidSet}" var="bs">
					${bs}
				</c:forEach>
			</font>
		</div>
		<hr>
		
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">投标期限限制最低值： </font>
			<font>
				${award.tdayLimitl}天
			</font>
		</div>
		<hr>
			
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">投标期限限制最高值： </font>
			<font>
				${award.tdayrestrict}天
			</font>
		</div>
		<hr>	
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">投标收益限制最低值：  </font>
			<font id="addtime-lb">
				${award.tmlrrestrict}
			</font>
		</div>
		<hr>	
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">投标收益限制最高值： </font>
			<font>
				${award.tmhrrestrict}
			</font>
		</div>
		<hr>	
		
		<div class="row" style="line-height: 0px;">
			<font class="col-md-2 text-right">投标还款方式： </font>
			<font>
				<c:set var="trt" value="${award.trtype}"></c:set>
	   			<c:choose>
	   				<c:when test="${trt==1}">
	   					一次还本付息  
	   				</c:when>
	   				<c:when test="${trt==2}">
	   					等额本金
	   				</c:when>
	   				<c:when test="${trt==3}">
	   					等额本息
	   				</c:when>
	   				<c:when test="${trt==4}">
	   					按期付息到期还本
	   				</c:when>
	   			</c:choose>
			</font>
		</div>
		<hr>
	</c:if>	
	
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">投标金额限制低值：  </font>
		<font>
			${award.tminmoney}元
		</font>
	</div>
	<hr>	
	
	<div class="row" style="line-height: 0px;">
		<font class="col-md-2 text-right">投标金额限制高值： </font>
		<font>
			${award.tmaxmoney}元
		</font>
	</div>
	<hr>	
	
	<div class="row" style="line-height: 0px;">
		<div class="col-md-5 col-md-offset-1">
			<button id="backid" class="btn btn-default" name="backid" type="button" onclick="gotoAwardList('${pageNum}');">返回列表</button>
		</div>
	</div>
</div>				
</body>
</html>
