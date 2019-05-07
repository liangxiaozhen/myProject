<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<style type="text/css">
	.laber_from {
		font-weight: normal;	
	}
</style>
</head>
<body style="label-family:'微软雅黑'; ">
	<form id="updateDiffAwardForm" class="form-horizontal" action="${pageContext.request.contextPath}/admin/diffAwardSwitch/updateDiffAward.action" method="post">
		<div class="container" style="margin-top: 80px;" >
		
			<!-- 奖品总开关列表的id -->
			<input type="hidden" name="id" value="${das.id}">
			
			<!--奖品属性名称-->
			<div class="form-group">
				<label class="col-sm-3 control-label laber_from">奖品属性名称：</label>
				<div class="col-sm-3">
					<input type="text" id="operateMan" class="form-control" value="${awardType}" readonly="readonly"/>
				</div>
			</div>
			
			<!--奖品交易方式总开关-->
			<div class="form-group">
				<label for="isdebtaudit" class="col-sm-3 control-label laber_from">交易方式总开关：</label>
				<div class="col-sm-3">
					<select name="allSwitch" id="allSwitch" class="form-control">
           			    <option value="">请选择</option>
           			    <option value="1" <c:if test="${das.allSwitch eq 1}">selected</c:if>>开</option>
           			    <option value="2" <c:if test="${das.allSwitch eq 2}">selected</c:if>>关</option>
					</select>
				</div>
			</div>
			
			<!-- 操作时间 -->
			<%-- <div class="form-group">
				<label for="isdebtaudit" class="col-sm-3 control-label laber_from">操作时间：</label>
				<div class="col-sm-3">
					<input type="text" name="operateTime" value="<fmt:formatDate value="${das.operateTime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>" id="operateTime" class="Wdate form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
				</div>
			</div> --%>
			
			<!-- 操作人 -->
			<div class="form-group">
				<label for="isdebtaudit" class="col-sm-3 control-label laber_from">操作人：</label>
				<div class="col-sm-3">
					<input type="text" id="operateMan" class="form-control" name="operateMan" value="${adminuser.username}" readonly="readonly"/>
				</div>
			</div>
			
			<!--备注-->
			<div class="form-group">
				<label for="isdebtaudit" class="col-sm-3 control-label laber_from">备注：</label>
				<div class="col-sm-3">
					  <textarea rows="3" class="form-control" name="remark">${das.remark}</textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-1">
					<button type="submit" class="btn btn-default">保存</button>
				</div>
				<div class="col-sm-1">
					<button type="button" class="btn btn-default" onclick="goBackList();">返回列表</button>
				</div>
			</div>
			
		</div>
	</form>
</body>
<script type="text/javascript">
	/*返回列表*/
	function goBackList(){
		window.location.href="<%=basePath%>admin/diffAwardSwitch/selectDiffAwardByCondition.action";
	}
	
	/*表单验证*/
	$().ready(function(){
		$("#updateDiffAwardForm").validate({
			rules:{
				allSwitch:"required"
				/* operateTime:"required" */
			}
		});
	});
	
</script>
</html>
