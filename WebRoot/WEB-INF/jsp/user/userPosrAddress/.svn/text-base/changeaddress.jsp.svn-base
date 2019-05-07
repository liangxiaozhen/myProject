<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/";
%>
<!DOCTYPE html >
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form
		action="${pageContext.request.contextPath}/userAddrss/wirtePostAddress.action?id=${address.id}"
		method="post" role="form">
		<div class="row" style="margin-top: auto">
			<label class="col-md-4 text-right">账户ID：</label> <input type="text"
				value="${address.baseid }" name="baseid" class="col-md-4" disabled />
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">省份：</label>
			<div class="col-md-4 text-left">
				<select name="addressProvince" id="addressProvince"
					class="form-control">
					<option value="${address.addressProvince }">${address.addressProvince }</option>
				</select>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">市：</label>
			<div class="col-md-4">
				<select name="addressCity" id="addressCity" class="form-control">
					<option value="${address.addressCity }">${address.addressCity }</option>
				</select>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">区：</label>
			<div class="col-md-4">
				<select name="addressDistrict" id="addressDistrict"
					class="form-control">
					<option value="${address.addressDistrict }">${address.addressDistrict }</option>
				</select>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">详细地址：</label>
			<div class="col-md-4 ">
				<textarea rows="3" cols="80" class="col-md-4 form-control"
					name="addressStreet" id="addressStreet" maxlength="60">${address.addressStreet }</textarea>
				<%-- <input type="text"  value="${address.addressStreet }"  name="addressStreet"  class="col-md-4" /> --%>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">邮政编码：</label> <input type="text"
				onkeyup='this.value=this.value.replace(/\D/gi,"")'
				value="${address.zipcode }" name="zipcode" class="col-md-4"
				required="required" maxlength="10" />
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">收货人姓名：</label> <input type="text"
				value="${address.recipients }" name="recipients" class="col-md-4"
				maxlength="25" />
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">手机号码：</label> <input type="text"
				onkeyup='this.value=this.value.replace(/\D/gi,"")'
				value="${address.mobliephone }" name="mobliephone" class="col-md-4"
				maxlength="11" />
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right">电话号码：</label> <input type="text"
				onkeyup="this.value=this.value.replace(/[^\d-]/g,'');"
				value="${address.telephone }" name="telephone" class="col-md-4"
				maxlength="20" />
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right"> 备注：</label> <input type="text"
				value="${address.remark }" name="remark" class="col-md-4"
				maxlength="100" />
		</div>
		<div class="row" style="margin-top: 10px;">
			<label class="col-md-4 text-right"></label> <input type="submit"
				value="修改" class=" btn btn-primary btn-lg " />
		</div>
	</form>
</body>
</html>