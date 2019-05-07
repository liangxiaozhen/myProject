<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
			<font color="red"><b>基本</b></font>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-2 col-md-offset-1">
			<input type="hidden" id="audit-id-text" value="${wdcRstr.id} ">
			会员等级：
		</div>
		<div class="col-md-9">
			<label>${wdcRstr.ugradeStr }</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">单笔金额范围：</div>
		<div>
			<label><fmt:formatNumber pattern="0.00"
					value="${wdcRstr.lowestmoney}" /> 元 -<fmt:formatNumber
					pattern="0.00" value="${wdcRstr.highestmoney } " /> 元</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">
			日提现金额限制：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>
			<label><fmt:formatNumber pattern="0.00"
					value="${wdcRstr.daymoneyrest}" /> 元</label>
		</div>
	</div>
	<hr>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">
			日提现次数限制：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>
			<label>${wdcRstr.daytimesrest} 次</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">
			提现占余额比例：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>
			<label><fmt:formatNumber pattern="0.00"
					value="${wdcRstr.proportion}" /> %</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">
			是否需要审核：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>
			<label> <c:if test="${wdcRstr.ismanaudit eq 1}">
				是
			</c:if> <c:if test="${wdcRstr.ismanaudit eq 2}">
				否
			</c:if>
			</label>
		</div>
	</div>
	<hr>

	<div class="row" style="line-height: 0px;">
		<div class="col-md-3 col-md-offset-1">
			是否可取消提现：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div>
			<label> <c:if test="${wdcRstr.iscancel eq 1}">
				是
			</c:if> <c:if test="${wdcRstr.iscancel eq 2}">
				否
			</c:if>
			</label>
		</div>
		<hr>
	</div>
	<c:if test="${!empty rNames}">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>排除名单</b></font>
			</div>
		</div>
		<hr>
		<c:forEach items="${rNames}" var="rName" varStatus="status">
			<div class="row" style="line-height: 0px;" id="${status.count }">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 70px;">
						<font><b><a href="javascript:void(0)"
								onclick="show('audit_rname_div_${status.count }')">${rName[0].name }</a></b>&nbsp;&nbsp;&nbsp;&nbsp;${rName[0].nametype}</font>
					</div>
				</div>
				<div style="display: none; line-height: 0px; margin-top: 20px;"
					class="row" id="audit_rname_div_${status.count }">
					<div class="col-md-10 col-md-offset-1">
						<table class="table table-hover">
							<tr class="text-center" style="background: #ccc;">
								<td>登录名</td>
								<td>真实姓名</td>
							</tr>
							<c:forEach items="${rName}" var="item">
								<tr class="text-center">
									<td>${item.loginname }</td>
									<td>${item.realname }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<hr>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${!empty cTimes}">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>关闭时间</b></font>
			</div>
		</div>
		<hr>
		<c:forEach items="${cTimes}" var="cTime" varStatus="status">
			<div class="row" style="line-height: 0px;" id="${status.count }">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 70px;">
						<font><b> <a href="javascript:void(0)"
								onclick="show('audit_ctime_div_${status.count }')">${cTime[0].timename }</a></b>&nbsp;&nbsp;&nbsp;&nbsp;
							${cTime[0].timetype}</font>
					</div>
				</div>
				<div class="row"
					style="display: none; line-height: 0px; margin-top: 20px;"
					id="audit_ctime_div_${status.count }">
					<div class="col-md-10 col-md-offset-1">
						<table class="table table-hover">
							<tr class="text-center" style="background: #ccc;">
								<td>开始时间</td>
								<td>结束时间</td>
							</tr>
							<c:forEach items="${cTime}" var="item">
								<tr class="text-center">
									<td>${item.btimeStr }</td>
									<td>${item.etimeStr }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<hr>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${!empty sTimes}">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>特定时间</b></font>
			</div>
		</div>
		<hr>
		<c:forEach items="${sTimes}" var="sTime" varStatus="status">
			<div class="row" style="line-height: 0px;" id="${status.count }">
				<div class="row" style="line-height: 0px;">
					<div class="col-md-4 col-md-offset-1" style="padding-left: 70px;">
						<font><b><a href="javascript:void(0)"
								onclick="show('audit_stime_div_${status.count }')">${sTime[0].timename }</a></b>&nbsp;&nbsp;&nbsp;&nbsp;${sTime[0].timetype}</font>
					</div>
				</div>
				<div style="display: none; line-height: 0px; margin-top: 20px;"
					class="row" id="audit_stime_div_${status.count }">
					<div class="col-md-10 col-md-offset-1">
						<table class="table table-hover">
							<tr class="text-center" style="background: #ccc;">
								<td>优先级</td>
								<td>提现金额</td>
								<td>开始时间</td>
								<td>结束时间</td>
							</tr>
							<c:forEach items="${sTime}" var="item">
								<tr class="text-center">
									<td><c:choose>
											<c:when test="${empty item.wlevel }">
									默认</c:when>
											<c:otherwise>
								${item.wlevel } </c:otherwise>
										</c:choose></td>
									<td>${item.minmoney }~${item.maxmoney }</td>
									<td>${item.btimeStr }</td>
									<td>${item.etimeStr }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<hr>
			</div>
		</c:forEach>
	</c:if>
	<div class="row" style="line-height: 0px;">
		<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control" name="remark"
					id="audit-remark-text">${wdcRstr.remark}</textarea>
			</div>
		</div>
	</div>
</body>
</html>