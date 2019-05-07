<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AheadRepayMode_Inst</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的提前还款方式设置</a>
</div>
<div class="container" style="margin-top: 20px;">
		<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/admin/aheadRepay/aheadRepayMode.action">
			 <!--还款方式-->
			<input type="hidden" name="tid" value="${tid}">
			 <c:if test="${!empty repaytype}">
				<div class="form-group">

					<label class="control-label col-sm-3" for="istemplet">还款方式</label>
					<div class="col-sm-3">
						 <select name="repaytype" class="form-control" id="RepayType">
						 	<c:if test="${repaytype eq 2}">0
						 	  <option value="2">等额本金</option>
						 	</c:if>
						 	<c:if test="${repaytype eq 3}">1
						 	  <option value="3">等额本息</option>
						 	</c:if>
						 	<c:if test="${repaytype eq 4}">2
						 	  <option value="4">先息后本</option>
						 	</c:if>
						 	<c:if test="${repaytype eq 1}">3
						 	  <option value="1">一次性还本付息</option>
						 	</c:if>
						 </select>
					</div>
				</div>
				<!--提前还款类型-->
				  <div class="form-group" id="ARepayModeone">
					<label class="control-label col-sm-3">提前还款类型</label>
					<div class="col-sm-3">
					<c:choose>
				        <c:when test="${repaytype eq 1}">
				           <select name="arepaymode" class="form-control" id="ARepayMode">
				            <option value="">请选择</option>
							<option value="1">全部提前</option>
						</select>
				        </c:when>
				         <c:when test="${repaytype eq 4}">
				           <select name="arepaymode" class="form-control" id="ARepayModexxhb">
				            <option value="">请选择</option>
							<option value="1">全部提前</option>
							<option value="2">部分提前</option>
						</select>
				        </c:when>
				    <c:otherwise>
				    <select name="arepaymode" class="form-control" id="ARepayMode">
							<option value="">请选择</option>
							<option value="1">全部提前</option>
							<option value="2">部分提前</option>
						</select>
				    </c:otherwise>
				</c:choose>
				</div>
				</div>	
				<!--提前期数-->
				<div id="APeriods_div"> 
				<div class="form-group">
					<label class="col-sm-3 control-label">提前期数</label>
					<div class="col-sm-3">
						<select name="aperiods" class="form-control" id="APeriods">
							<option value="">请选择</option>
							<option value="1">当期提前</option>
							<option value="2">多期提前</option>
						</select>
					</div>
				</div>
				<div class="form-group" id="APeriodsqi">
					<label class="col-sm-3 control-label">具体期数</label>
					<div class="col-sm-3">
						<div class="input-group">
						<input type="text" name="aperiodqi" class="form-control"/>
						<span class="input-group-addon">期</span>
						</div>
					</div>
				</div>
				</div>
				<!--利息设置-->
				<div class="form-group" id="IntMode">
					<label class="control-label col-sm-3">利息设置</label>
					<div class="col-sm-3">
						<select name="intmode" id="intmode" class="form-control">
							<option value="">请选择</option>
							<option value="1">占天计息</option>
							<option value="2">全额利息</option>
						</select>
					</div>
				</div>
				<!--利息设置-->
				<div class="form-group" id="IntModeonequanx">
					<label class="control-label col-sm-3">利息设置</label>
					<div class="col-sm-3">
						<select name="intmodequane" class="form-control" id="intmodeonequan">
							<option value="2">全额利息</option>
						</select>
					</div>
				</div>
				<!--利息设置-->
				<div class="form-group" id="IntModezhant">
					<label class="control-label col-sm-3">利息设置</label>
					<div class="col-sm-3">
						<select name="intmodetian" class="form-control" id="intmodezhant">
							<option value="1">占天计息</option>
						</select>
					</div>
				</div>
				</c:if>
				<div class="form-group">
				   <label class="control-label col-sm-3">备注</label>
				   <div class="col-sm-3">
				      <textarea rows="3" name="remark" class="form-control"></textarea>
				   </div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="aheButton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
					</div>
				</div>
		</form>
</div>
<script type="text/javascript">
  $(function(){
	  $("#APeriods_div").hide();//提前期数设置
	  $("#APeriodsqi").hide();//期数
	  $("#IntMode").hide();//利息设置
	  $("#IntModeonequanx").hide();//全额利息
	  $("#IntModezhant").hide();//占天计息
	  //提前还款类型
	  $("#ARepayMode").change(function(){
		  if($(this).val()==2){//部分提前
			  $("#APeriods_div").show();
			  $("#IntMode").hide();//利息设置
		  }else if($(this).val()==1){//全部提前
			  $("#IntMode").show();//利息设置
			  $("#IntModeonequanx").hide();//全额
			  $("#IntModezhant").hide();//占天
			  $("#APeriodsqi").hide();
			  $("#APeriods_div").hide();
			  $("#APeriods_div select").val("");
			  $("#APeriodsqi input").val("");
		  }else{
			  $("#APeriods_div").hide();
			  $("#IntMode").hide();
			  $("#IntModeonequanx").hide();
			  $("#IntModezhant").hide();
		  }
	  });
	  //提前期数设置
	  $("#APeriods").change(function(){
		  if($(this).val()==2){//多期提前
			  $("#IntModezhant").hide();//占天计息
			  $("#APeriodsqi").show();
			  $("#IntModeonequanx").show();//全额利息
		  }else if($(this).val()==1){//当期提前
			  $("#APeriodsqi").hide();
		      $("#IntModeonequanx").hide();
			  $("#APeriodsqi input").val("");
			  $("#IntModezhant").show();//占天计息
		  }else{//请选择
			  $("#IntModeonequanx").hide();//全额
			  $("#IntModezhant").hide();//占天
			  $("#APeriodsqi").hide();
			  $("#APeriodsqi input").val("");
		  }
	  });
	  //先息后本
	  $("#ARepayModexxhb").change(function(){
		   if($(this).val()==2){//部分提前
			   $("#IntMode").hide();//利息设置
			   $("#APeriods_div").show();
			   $("#APeriods").change(function(){
				   $("#IntModeonequanx").show();//全额利息
				   $("#IntModezhant").hide();//占天计息
			   });
		   }else if($(this).val()==1){//全部提前
			      $("#IntMode").show();//利息设置
				  $("#IntModeonequanx").hide();//全额
				  $("#IntModezhant").hide();//占天
				  $("#APeriodsqi").hide();
				  $("#APeriods_div").hide();
				  $("#APeriods_div select").val("");
				  $("#APeriodsqi input").val("");
		   }
	  });
  })
</script>
</body>
</html>