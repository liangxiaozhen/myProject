<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${address!=null }">
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">编号：</label><label id="ptype"
				class="col-md-4">${address.id }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">用户名：</label><label id="ptype"
				class="col-md-4">${address.userBaseAccountInfo.loginname }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">奖品编号：</label><label id="ptype"
				class="col-md-4">${address.awardno }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">省份：</label><label id="ptime"
				class="col-md-4">${address.addressProvince}</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">市：</label><label id="pdtime"
				class="col-md-4">${address.addressCity }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">区：</label><label id="pstatus"
				class="col-md-4">${address.addressDistrict}</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">详细地址：</label><label id="purpose"
				class="col-md-4">${address.addressStreet}</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">邮政编码：</label><label id="audit"
				class="col-md-4">${address.zipcode }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">收货人姓名：</label><label id="auditman"
				class="col-md-4">${address.recipients }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">手机号码：</label><label id="audittime"
				class="col-md-4">${address.mobliephone }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">电话号码：</label><label id="remark"
				class="col-md-4">${address.telephone }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">备注：</label><label id="remark"
				class="col-md-4">${address.remark }</label>
		</div>
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">是否默认地址：</label> <label
				class="col-md-4"> <c:choose>
					<c:when test="${address. isdefaddress==1}">
						<td><c:out value="默认地址"></c:out></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="否"></c:out></td>
					</c:otherwise>
				</c:choose>
			</label>
		</div>
	</c:if>
	<c:if test="${address==null}">
		<label>暂无数据！</label>
	</c:if>
</body>
</html>