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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户邮寄地址</title>
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
/* 查看用户站外奖品详情 */
function query_useraward_detail(id) {
	var action = "${pageContext.request.contextPath}/userAddrss/queryuserAddressDetail.action";
	var param = {
		"id" : id
	}
	var callback = function(data) {
		$("#detailModal").modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#modal-body").html(data);
	}
	$.post(action, param, callback);
}

</script>
</head>
<body>
	<div class="container">
		<table class="table table-hover" style="width: 80%;">
			<tr>
				<td><label class="col-md-4 text-right">账户ID：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.baseid }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">省份：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.addressProvince }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">市：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.addressCity }</label>
				</td>
			</tr>

			<tr>
				<td><label class="col-md-4 text-right">区：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.addressDistrict }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">详细地址：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.addressStreet }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">邮政编码：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.zipcode }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">收货人姓名：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.recipients }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">手机号码：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.mobliephone }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">电话号码：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.telephone }</label>
				</td>
			</tr>
			<tr>
				<td><label class="col-md-4 text-right">备注：</label> <label
					id="ptype" class="col-md-4 col-md-offset-1">${address.remark }</label>
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" class="btn btn-default  col-md-offset-5"
						onclick="query_useraward_detail('${address.id}')">修改信息</button>
				</td>
			</tr>
		</table>
		<%-- 		<input type="submit"  value="修改"  class="btn btn-default"  onclick="query_useraward_detail(' ${address.id}')"/> --%>

		<!-- 详情模态框（Modal） -->
		<form
			action="${pageContext.request.contextPath}/userAddrss/wirtePostAddress.action?id=${address.id}"
			method="post" role="form">
			<div id="detailModal" class="modal fade bs-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">用户站外奖品详情</h4>
						</div>
						<div class="modal-body" id="modal-body"></div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-default">提交</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>