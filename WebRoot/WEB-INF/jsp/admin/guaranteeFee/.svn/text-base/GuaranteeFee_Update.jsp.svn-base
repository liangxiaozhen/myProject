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
<title>GuaranteeFee_updateUI</title>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script src="${pageContext.request.contextPath}/js/guaranteefee/updateGuaranteeFee.js"></script>
<script type="text/javascript">
$(function(){
	var chargetype=$("#chargetype").find("option:selected").val();
	/* 收费类型:截标后收取 */
	if("1"==chargetype){
		$("#chargetype1").show();
		$("#chargetype2").hide();
		/* 类型：定额 */
		if("iequota"==$("#type").val()){
			$("#iequota").show();
			$("#iepercent").hide();
			/* 类型：百分比 */
		}else if("iepercent"==$("#type").val()){
			$("#iequota").hide();
			$("#iepercent").show();
		}
		/* 收费类型:投标时收取 */
	}else if("2"==chargetype){
		$("#chargetype1").hide();
		$("#chan_shu").hide();
		$("#chargetype2").show();
	}
	
	//截标后收取与投标时收取选择改变时的事件
	$("#chargetype").change(function() {
		if ($(this).val() == 1) {
			//隐藏投标时收取的div 并清空文本值和checkbox的已选中状态
			$("#chargetype2 :input").val("");
			$("#chargetype2").hide();
			$("#chan_shu").hide();
			
			$("#chargetype1").show();
		} else if($(this).val() == 2){
			//隐藏结标收取的div 并清空文本值
			$("#chargetype1").hide();
			$("#chargetype1 :input").val("");
			$("#chan_shu input").val("");
			$("#chan_shu").hide();
			
			$("#chargetype2").show();
		}
	});
	$("#submitBtu").click(function(){
			if(check().form()){
				$("form:first").submit();
			}
	});
});

	function gotoGuaranteeFeeList(){
		   window.location.href="${pageContext.request.contextPath }/admin/guaranteeFee/selectGuaranteeFeeByCondition.action";
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
</style>
</head>
<body  style="font-family:'微软雅黑'; ">
<div class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的担保费率修改页面</a>
</div>
<c:if test="${!empty guaranteeFee}">
<div class="container"  style="margin-top: 25px;">
			<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/guaranteeFee/updateGuaranteeFee.action" id="defaultForm">
				<!--担保公司-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="guaranteeId">担保公司</label>
					<div class="col-sm-3">
					<select name="gfrec" id="GFRecManId" class="form-control">
					        <c:if test="${!empty usrname}">
					            <option value="">${usrname}</option>
					        </c:if>
					</select> 
					</div>
				</div>
				<!--担保费收款人-->
				<div class="form-group">
					<label for="GFRecMan" class="col-sm-3 control-label laber_from">担保费收款人</label>
					<div class="col-sm-3">
						<input type="text" name="gfrecman" id="GFRecMan" value="${guaranteeFee.gfrecman}" class="form-control" />
					</div>
				</div>
				<!--担保费收费方式设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="chargeType">收费类型</label>
					<div class="col-sm-3">
						<select name="chargetype" id="chargeType" class="form-control">
                            <c:if test="${! empty guaranteeFee.chargetype}">
                               <c:if test="${guaranteeFee.chargetype eq 1}">
                                 <option value="1">结标收取</option>
                               </c:if>
                              <c:if test="${guaranteeFee.chargetype eq 2}">
                                 <option value="2">投标时收取</option>
                               </c:if>
                            </c:if>
						</select>
					</div>
				</div>
				<!-- 结标收取 -->
				<c:if test="${guaranteeFee.chargetype eq 1}">
				<c:forEach items="${guaranteeFees}" var="fees" varStatus="sta">
				<input type="hidden" name="guaranteefees[${sta.index}].id" value="${fees.id}"/>
				<!--结标分段金额低值及高值-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minNMMoney">结标分段金额低值-高值</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="guaranteefees[${sta.index}].minnmmoney" id="minNMMoney" value="${fees.minnmmoney}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="guaranteefees[${sta.index}].maxnmmoney" id="maxNMMoney" value="${fees.maxnmmoney}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				 <c:if test="${!empty fees.gfquota}">
				<div class="form-group" id="quotainput_div">
					<label class="col-sm-3 control-label laber_from" for="GFQuota">担保费定额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="guaranteefees[${sta.index}].gfquota" id="GFQuota" value="${fees.gfquota}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${!empty fees.gfpercent}">
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="GFPercent">担保费百份比</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="guaranteefees[${sta.index}].gfpercent" id="GFPercent" value="${fees.gfpercent}" class="form-control" />
							<span class="input-group-addon">%</span>
						</div>
					</div>
				</div>
				</c:if>
				<!--收费最小值-收费最大值-->
				<c:if test="${!empty fees.mingffee}">
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minGFFee">收费最小金额-收费最大金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="guaranteefees[${sta.index}].mingffee" id="minGFFee" value="${fees.mingffee}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="guaranteefees[${sta.index}].maxgffee" id="maxGFFee" value="${fees.maxgffee}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</c:if>
				</c:forEach>
				</c:if>
				<!-- 投标时收取 -->
				<c:if test="${guaranteeFee.chargetype eq 2}">
				<input type="hidden" name="id" value="${guaranteeFee.id}"/>
				   <div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="GFRate">担保费费率</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="gfrate" id="GFRate" value="${guaranteeFee.gfrate}" class="form-control" />
							<span class="input-group-addon">%</span>
						</div>
					</div>
				</div>
				<!--担保费最低收费-最高收费(投标时收取)-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="minGFAmount">担保费最低收费-最高收费</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="mingfamount" id="minGFAmount" value="${guaranteeFee.mingfamount}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="maxgfamount" id="maxGFAmount" value="${guaranteeFee.maxgfamount}" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</c:if>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
						<select name="isaudit" id="isAudit" class="form-control">
						<c:if test="${guaranteeFee.isaudit eq 1}">
						    <option value="1">是</option>
						</c:if>
						<c:if test="${guaranteeFee.isaudit eq 0}">
						    <option value="0">否</option>
						</c:if>
						</select>
					</div>
				</div>
				<!--是否为模板-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
					<div class="col-sm-3">
						<select name="istemplet" id="isTemplet" class="form-control">
						<c:if test="${guaranteeFee.istemplet eq 1}">
						    <option value="1">是</option>
						</c:if>
						<c:if test="${guaranteeFee.istemplet eq 0}">
						    <option value="0">否</option>
						</c:if>
						</select>
					</div>
				</div>
				<!-- 备注 -->
			   <div class="form-group">
			      <label class="control-label col-sm-3 laber_from">备注</label>
			      <div class="col-sm-3">
			      <textarea rows="3" name="remark" class="form-control">${guaranteeFee.remark}</textarea>
			      </div>
			   </div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="guarButton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);">返回列表</button>
					</div>
				</div>
			</form>
		</div>
</c:if>
<c:if test="${empty guaranteeFee}">
  <label>暂无数据</label>
</c:if>
</body>
</html>
