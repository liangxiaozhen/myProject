<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#apurchasergrades_div").hide();
	   $("#APurchaserGradeone").attr("checked", "checked");
	   
		//购买人全部等级与选择等级的change监听事件
		$(".insert-ugrade-radio-apurchasergrade").change(function() {
			var $radioVal = $(".insert-ugrade-radio-apurchasergrade:checked").val();
			if($radioVal == 1) {
				$("#apurchasergrades_div").hide();
				$("#apurchasergrades_div :checkbox").each(function() {
					this.checked = false;
				});
			} else {
				$("#apurchasergrades_div").show();
			}
		});
   })
</script>
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
	<a href="#">标的债权转让购买人设置</a>
</div>
<div class="container" style="margin-top: 25px;"> 
 <form action="${pageContext.request.contextPath}/admin/debtAttorn/insertDebtAttornBuyer.action" class="form-horizontal" role="form" method="post">
                 <%-- <!--允许购买人债转的等级-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="APurchaserGrade">允许购买人的等级</label>
					<div class="col-sm-6">
						<label class="radio-inline">
			 			<input type="radio" name="apurchasergrade" id="APurchaserGradeone" value="1" class="insert-ugrade-radio-apurchasergrade"/>全部等级
			 		</label>
						<label class="radio-inline">
			 			<input type="radio" name="apurchasergrade" id="APurchaserGradetwo" value="2" class="insert-ugrade-radio-apurchasergrade"/>部分等级
			 		</label>
					</div>
				</div>
				<!--会员等级-->
				<div class="form-group" id="apurchasergrades_div">
					<label class="col-sm-3 control-label" for="apurchasergrades"></label>
					<div class="col-sm-6">
					<c:if test="${!empty uGrades}">
					  <c:forEach items="${uGrades}" var="des" varStatus="sta">
					     <label class="checkbox-inline" style="width:120px;">
			 			    <input type="checkbox" name="apurchasergrades" id="apurchasergrades" value="${des.ugrade}"/>${des.ugradename}
			 		     </label>
			 		     <c:if test="${sta.count%4==0}"><br></c:if>
					  </c:forEach>
					</c:if>
					</div>
				</div>
				<!--不允许购买的用户名单-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="noAPNameNo">不允许购买的用户名单</label>
					<div class="col-sm-6">
					<c:if test="${!empty removeNames}">
					    <c:forEach items="${removeNames}" var="rna" varStatus="tus">
					       <label class="checkbox-inline">
			 	 		      <input type="checkbox" name="noapnameno" id="noAPNameNo" value="${rna.nameno}"/>${rna.name}
			 	 	       </label>
			 	 	       <c:if test="${tus.count%4==0}"><br></c:if>
					    </c:forEach>
					</c:if>
					</div>
				</div> --%>
					 <input type="hidden" name="tid" value="${tid}">
				<!-- 定向名单列表Id -->
				<div class="form-group">
				    <label class="col-sm-3 control-label laber_from" for="IntDisable">定向名单列表</label>
				    <div class="col-sm-3">
				       <select name="intdisable" id="IntDisable" class="form-control">
				       <option value="">请选择</option>
				       <c:if test="${!empty snl2}">
				          <c:forEach items="${snl2}" var="sn">
				               <option value="${sn.id}">${sn.businessName}</option>
				          </c:forEach>
				       </c:if>
				       </select>
				    </div>
				</div>
				<!--购买金额设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isABuyAllOrPart">购买金额设置</label>
					<div class="col-sm-3">
						<select name="isabuyallorpart" id="isABuyAllOrPart" class="form-control">
							<option value="">请选择</option>
							<option value="1">全额购买</option>
							<option value="2">部分购买</option>
						</select>
					</div>
				</div>
				<!-- 是否为模板 -->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
					<div class="col-sm-3">
						<select name="istemplet" id="isTemplet" class="form-control">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<!--备注-->
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">备注</label>
					<div class="col-sm-3">
						  <textarea rows="3" class="form-control" name="remark"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="debtBtton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default">返回列表</button>
					</div>
				</div>
				 </form>
</div>
<script type="text/javascript">
   $(function(){
	   $("form").bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons: { /*input状态样式图片*/
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: { //验证
				apurchasergrades:{
					validators:{
						notEmpty:{
							message:'请至少选择一项'
						}
					}
				},
				isabuyallorpart:{
					validators:{
						notEmpty:{
							message:'不能为空'
						}
					}
				}
			}
		});
   })
</script>
</body>
</html>