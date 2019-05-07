<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>等级升级规则添加页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" role="form" method="post"
			action="${pageContext.request.contextPath}/admin/gradeRule/adduserRule.action">
			<div class="form-group">
				<label class="col-md-2 control-label">级别：</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="inputPassword"
						name="grade" placeholder="请输入级别">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-md-2 control-label">升级方式：</label>
				<div class="col-md-4">
					<select name="method" id="method" class="form-control">
						<c:if test="${!empty  methodmaps}">
							<c:forEach items="${methodmaps}" var="methmap">
								<c:if test="${methmap.key!=3 }">
									<option value="${methmap.key}">${methmap.value}</option>
								</c:if>
							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-md-2 control-label">时间有效期：</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="effecttime"
						name="effecttime" placeholder="请输入时间有效期">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-md-2 control-label">支付金额：</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="payamount"
						name="payamount" placeholder="请输入支付金额">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-md-2 control-label">需要积分：</label>
				<div class="col-md-4">
					<input type="text" class="form-control" id="needbonuspoints"
						name="needbonuspoints" placeholder="请输入积分">
				</div>
			</div>
			<div class="form-group">
				<label for="unit" class="col-md-2 control-label">单位(年/月/日)：</label>
				<div class="col-md-4">
					<ul class="list-inline">
						<li>
							<div class="radio">
								<label> <input type="radio" name="gender" value="male"
									id="gender1" checked="checked" /> 年
								</label>
							</div>
						</li>
						<li>
							<div class="radio">
								<label> <input type="radio" name="gender" id="gender2"
									value="female" /> 月
								</label>
							</div>
						</li>
						<li>
							<div class="radio">
								<label> <input type="radio" name="gender" id="gender3"
									value="other" /> 日
								</label>
							</div>
						</li>
					</ul>
					<input type="text" id="inputyeae"
						onkeyup="this.value=this.value.replace(/[^\d-]/g,'');"
						name="unit_year" class="form-control col-md-3"
						placeholder="请输入单位年如:1" /> <input type="text" id="inpumouth"
						onkeyup="this.value=this.value.replace(/[^\d-]/g,'');"
						name="unit_mouth" class="form-control col-md-3"
						placeholder="请输入单位月如:1" /> <input type="text" id="inoutday"
						onkeyup="this.value=this.value.replace(/[^\d-]/g,'');"
						name="unit_day" class="form-control col-md-3"
						placeholder="请输入单位日如:1" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-md-2 control-label">备注：</label>
				<div class="col-md-4">
					<input type="text" class="form-control col-md-3" id="remark"
						name="remark" placeholder="请输入备注">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-md-2 control-label">&nbsp;</label>
				<div class="col-sm-10">
					<div class="row col-sm-8">
						<input type="submit" class="btn btn-primary" value="添加" /> <a
							href="${pageContext.request.contextPath}/admin/gradeRule/jump.action?flag=jump_right"
							class="btn btn-default">返回</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script>
			$(function() {
				encodeURI($("#remark").val());
				$('form').bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
						grade: {
							message: '级别验证失败',
							validators: {
								notEmpty: {
									message: '级别不能为空'
								},
								regexp: {
									regexp: /^[0-9]+$/,
									message: '级别只能为数值'
								}
							}
						},
						effecttime: {
							validators: {
								notEmpty: {
									message: '时间有效期不能为空'
								},
								regexp: {
									regexp: /^[0-9]+$/,
									message: '有效期只能为数值'
								}
							}
						},
						payamount: {
							validators: {
								regexp: {
									regexp: /^[0-9\.]+$/,
									message: '支付金额由数字和小数点组成'
								}
							}
						},
						needbonuspoints: {
							validators: {
								regexp: {
									regexp: /^[0-9]+$/,
									message: '积分由数字组成'
								}
							}
						},
						remark: {
							validators: {
								stringLength: {
									max: 80,
									message: '用户名长度必须在80位之间'
								}
							}
						},
						unit_year:{
							validators:{
								notEmpty:{
									message:'单位不能为空'
								},
								stringLength:{
									max: 2,
									message:'单位长度需在2位之间'
								}
							}
						},
						unit_mouth:{
							validators:{
								notEmpty:{
									message:'单位不能为空'
								},
								stringLength:{
									max: 2,
									message:'单位长度需在2位之间'
								}
							}
						},
						unit_day:{
							validators:{
								notEmpty:{
									message:'单位不能为空'
								},
								stringLength:{
									max: 2,
									message:'单位长度需在2位之间'
								}
							}
						},
					}
				});
			});
			//
			$(function() {
				showCont();
				$("input[name=gender]").click(function() {
					showCont();
				});
			});
           //切换年月日
			function showCont() {
				switch($("input[name=gender]:checked").attr("id")) {
					case "gender1":
						$("#inputyeae").show();
						$("#inpumouth").hide();
						$("#inoutday").hide();
						break;
					case "gender2":
						$("#inpumouth").show();
						$("#inputyeae").hide();
						$("#inoutday").hide();
						break;
					case "gender3":
						$("#inoutday").show();
						$("#inputyeae").hide();
						$("#inpumouth").hide();
						break;
					default:
						break;
				}
				
			}
		</script>
</body>
</html>