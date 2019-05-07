<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				/**
				 * 获取选中的省份，关联出相应的城市
				 */
				var province = $("#province").val();
				var action = "userbankcard/getCitysList.action"
				var param = {
					"province" : province
				}
				var callback = function(jsonStr) {
					json = eval(jsonStr);
					var item = "${bankinfo.city}";
					for ( var p in json) {
						var citys = document.getElementById("city");
						// 获取城市列表
						citys.options[0] = new Option("--请选择--", 0);
						citys.options[citys.length] = new Option(json[p].name,
								json[p].name);
						// 回显城市
						if (item == json[p].name) {
							citys.options[parseInt(p) + 1].selected = true;
						}
					}
				}
				$.post(action, param, callback);
			})
	$(function() {
		/* 返回 */
		$("#return").bind("click",function() {
			window.location.href = "${pageContext.request.contextPath}/userbankcard/queryBankInfoList.action";
		})
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<form id="inform" method="post"
					action="${pageContext.request.contextPath}/userbankcard/updateUserBankInfo.action">
					<input type="hidden" name="id" value="${bankinfo.id}">
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>姓名：&nbsp;</label><input type="text" name="username"
								id="username" value="${bankinfo.username}"
								style="width: 200px; height: 25px" />
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>银行：&nbsp;</label><input type="text" name="bankname"
								id="bankname" value="${bankinfo.bankname}"
								style="width: 200px; height: 25px" />
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>账户：&nbsp;</label><input type="text" name="cardno"
								id="cardno" value="${bankinfo.cardno}"
								style="width: 200px; height: 25px" />
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>开户行省：&nbsp;</label><select name="province" id="province"
								onchange="change()">
								<option value="">--请选择--</option>
								<c:forEach items="${provinces}" var="ptype">
									<c:if test="${bankinfo.province==ptype.name}">
										<option value="${ptype.name}" selected="selected">${ptype.name}</option>
									</c:if>
									<option value="${ptype.name}">${ptype.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>开户行市：&nbsp;</label><select name="city" id="city">
								<option value="">--请选择--</option>
							</select>
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>分支行：&nbsp;</label><input type="text" name="subbranch"
								id="subbranch" value="${bankinfo.subbranch}"
								style="width: 200px; height: 25px" />
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>卡类型：&nbsp;</label><select name="cardtype" id="cardtype">
								<option value="">请选择卡类型</option>
								<c:if test="${!empty cardtype}">
									<c:forEach items="${cardtype}" var="ctype">
										<c:choose>
											<c:when test="${bankinfo.cardtype==ctype.key}">
												<option value="${ctype.key}" selected="selected">${ctype.value}</option>
											</c:when>
											<c:otherwise>
												<option value="${ctype.key}">${ctype.value}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>绑定银行卡时间：&nbsp;</label><input type="text" name="bindtime"
								id="bindtime" class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								value="${bankinfo.bindtimeStr}"
								style="width: 200px; height: 25px" />
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 0px">
						<div class="col-md-4 col-md-offset-1">
							<label>绑定方式：&nbsp;</label>
							<c:if test="${bankinfo.bindmode=='1'}">
									接口：<input type="radio" name="bindmode" id="bindmode" value="1"
									checked="checked" />
									人工：<input type="radio" name="bindmode" id="bindmode" value="0" />
							</c:if>
							<c:if test="${bankinfo.bindmode=='0'}">
									接口：<input type="radio" name="bindmode" id="bindmode" value="1" />
									人工：<input type="radio" name="bindmode" id="bindmode" value="0"
									checked="checked" />
							</c:if>
						</div>
					</div>
					<hr />
					<div class="row" style="line-height: 10px">
						<div class="col-md-4 col-md-offset-1">
							<label>备注：&nbsp;</label>
							<textarea rows="5" cols="120" name="remark" id="remark"
								maxlength="200">${bankinfo.remark}</textarea>
						</div>
					</div>
					<hr />
					<div align="center">
						<button type="submit" class="btn">保存</button>
						<button type="button" class="btn" id="return">返回</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>