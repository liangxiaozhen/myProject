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
	
<title>Plus_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/plus/updatePlus.js"></script>
<script type="text/javascript">
$(function(){
	var isaintValue=$("#isaint").val();
	var isavoucherValue=$("#isavoucher").val();
	var isalikevoucherValue=$("#isalikevoucher").val();
	if("0"==isaintValue){
		$("#aint").hide();
	}
	if("0"==isavoucherValue){
		$("#avoucher").hide();
	}
	if("0"==isalikevoucherValue){
		$("#alikevoucher").hide(); 
	}
	$("#submitBtu").click(function(){
		/* 通过标的设置来到这里的就有nextPage */
			if(check().form()){
				$("form:first").submit();
			}
	});
});
	function gotoPlusList(){
		   window.location.href="${pageContext.request.contextPath }/admin/plus/selectPlusByCondition.action";
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
	.left_class{
	    width:150px;text-align: right;margin-right: 10px;
	} 
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
<div class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的增益修改</a>
</div>
<hr>
<form action="${pageContext.request.contextPath}/admin/plus/updatePlus.action" method="post" id="updatePlusForm">
<input type="hidden" value="${plus.id }" name="id">
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">基本信息</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-6 col-md-offset-1">
							<label class="left_class">单次允许的增益方式 :</label>
							<input type="text" name="aonceplusmode" value="${plus.aonceplusmode}"/>&nbsp;&nbsp;种
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-6 col-md-offset-1">
							<label class="left_class">总计允许的增益方式 :</label>
							<input type="text" name="atotalplusmode" value="${plus.atotalplusmode}"/>&nbsp;&nbsp;种
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">增益清算付款人 :</label>
							<input type="text" name="payforplusman" value="${plus.payforplusman }"/> 
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">清算方式 :</label>
								<select name="clearmode" style="width:100px;height:25px;font-size: 15px">
								    <c:if test="${plus.clearmode eq 1}"><option value="1">结标清算</option></c:if>
								    <c:if test="${plus.clearmode eq 2}"><option value="2">首期清算</option></c:if>
								    <c:if test="${plus.clearmode eq 3}"><option value="3">每期清算</option></c:if>
								    <c:if test="${plus.clearmode eq 4}"><option value="4">尾期清算</option></c:if>
								</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">是否为模板 :</label>
										<select name="istemplet" id="istemplet" style="width:100px;height:25px;font-size: 15px">
											<c:if test="${plus.istemplet eq 1}">
											  <option value="1">是</option>
											</c:if>
											<c:if test="${plus.istemplet eq 0}">
											   <option value="0">否</option>
											</c:if>
										</select>
						</div>
					</div>
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">加息卷设置</font></label>
						</div>
					</div>	
					<hr>
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">是否允许加息卷 :</label>
										<select name="isaint" id="isaint" style="width:100px;height:25px;font-size: 15px">
										    <c:if test="${plus.isaint eq 1}">
										       <option value="1">允许</option>
										    </c:if>
											 <c:if test="${plus.isaint eq 0}">
										       <option value="0">不允许</option>
										    </c:if>
										</select>
						</div>
					</div>
					<hr>
					<c:if test="${plus.isaint eq 1}">
					<div id="aint">
							<div class="row" style="line-height: 0px;">
								<div class="col-md-5 col-md-offset-1">
									<label class="left_class">单次允许使用加息张数 :</label>
									<input  name="aonceint"   value="${plus.aonceint }"/>&nbsp;&nbsp;张
								</div>
							</div>
							<hr>
							<div class="row" style="line-height: 0px;">
								<div class="col-md-5 col-md-offset-1">
									<label class="left_class">允许使用加息总张数 :</label>
									<input name="atotalint" value="${plus.atotalint }" />&nbsp;&nbsp;张
								</div>
							</div>
							<hr>
							<div class="row" style="line-height: 0px;">
								<div class="col-md-4 col-md-offset-1">
									<label class="left_class">允许单张加息收益 :</label>
									<input type="text" name="aoneqrofit" value="${plus.aoneqrofit }"/>&nbsp;&nbsp;元 
								</div>
							</div>
							<hr>
					</div>
					</c:if>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">类现金设置</font></label>
						</div>
					</div>	
					<hr>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">是否允许类现金卷 :</label>
										<select name="isavoucher" id="isavoucher" style="width:100px;height:25px;font-size: 15px">
											<c:if test="${plus.isavoucher eq 1}">
											  <option value="1">允许</option>
											</c:if>
											<c:if test="${plus.isavoucher eq 0}">
											  <option value="0">不允许</option>
											</c:if>
										</select>
						</div>
					</div>
					<hr>
					<c:if test="${plus.isavoucher eq 1}">
					<div id="avoucher">
							<div class="row" style="line-height: 0px;">
								<div class="col-md-5 col-md-offset-1">
									<label class="left_class">单次允许类现金卷张数 :</label>
									<input  type="text" name="aoncevoucher" value="${plus.aoncevoucher }" />&nbsp;&nbsp;张
								</div>
							</div>
							<hr>
							<div class="row" style="line-height: 0px;">
								<div class="col-md-5 col-md-offset-1">
									<label class="left_class">总计允许类现金卷张数 :</label>
									<input  type="text" name="atotalvoucher" value="${plus.atotalvoucher }"/>&nbsp;&nbsp;张
								</div>
							</div>
							<hr>
							<div class="row" style="line-height: 0px;">
								<div class="col-md-4 col-md-offset-1">
									<label class="left_class">允许单张类现金额度 :</label>
									<input type="text"  name="aonevamount" value="${plus.aonevamount }"/>&nbsp;&nbsp;元
								</div>
							</div>
							<hr>
					</div>
					</c:if>
						<div class="row" style="line-height: 0px;">
						<div class="col-md-4 " style="margin-top: 10px;margin-bottom: 10px">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><font size="4">假现金设置</font></label>
						</div>
					</div>	
					<hr>
					
					
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-4 col-md-offset-1">
							<label class="left_class">是否允许假现金卷 :</label>
								<select name="isalikevoucher" id="isalikevoucher" style="width:100px;height:25px;font-size: 15px">
											<c:if test="${plus.isalikevoucher eq 1}">
											   <option value="1">允许</option>
											</c:if>
											<c:if test="${plus.isalikevoucher eq 0}">
											   <option value="0">不允许</option>
											</c:if>
										</select>
						</div>
					</div>
					<script type="text/javascript">
					/* 	$("#isalikevoucher").change(function(){
							var is=$(this).val();
							if("0"==is||""==is){
								$("#alikevoucher input").val("");
								$("#alikevoucher").hide();
							}else if("1"==is){
								$("#alikevoucher").show();
							}
						}); */
					</script>
					<hr>
					<c:if test="${plus.isalikevoucher eq 1}">
					<div id="alikevoucher">
								<div class="row" style="line-height: 0px;">
									<div class="col-md-5 col-md-offset-1">
										<label class="left_class">单次允许假现金卷张数 :</label>
										<input  name="aoncelikevoucher"  type="text"  value="${plus.aoncelikevoucher }" >&nbsp;&nbsp;张
									</div>
								</div>
								<hr>
								<div class="row" style="line-height: 0px;">
									<div class="col-md-5 col-md-offset-1">
										<label class="left_class">总计允许假现金卷张数 :</label>
										<input type="text" name="atotallikevoucher" value="${plus.atotallikevoucher }">&nbsp;&nbsp;张
									</div>
								</div>
								<hr>
								<div class="row" style="line-height: 0px;">
									<div class="col-md-5 col-md-offset-1">
										<label class="left_class">允许单张假现金额度 :</label>
										<input type="text"  name="aonelvamount" value="${plus.aonelvamount }">&nbsp;&nbsp;元
									</div>
								</div>
								<hr>
					</div>
					</c:if>
					
					<div class="row" style="line-height: 0px;">
						<div class="col-md-5">
							<input class="btn btn-primary"  type="button"  id="submitBtu" style="margin-left: 200px" value="修改"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="backid" class="btn btn-success" name="backid" type="button" onclick="javascript:history.back(-1);">返回列表</button>
						</div>
					</div>
				</form>
					
</body>
</html>
