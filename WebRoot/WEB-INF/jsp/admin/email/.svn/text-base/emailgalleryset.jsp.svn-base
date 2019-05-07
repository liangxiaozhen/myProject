<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件通道添加</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<title>邮箱通道设置</title>
<style type="text/css">
#ht {
	margin-left: 45%;
}
</style>
</head>
<body>
	<div class="container" id="finishBaseInfo">
		<div id="ht">
			<h2>
				<span class="glyphicon glyphicon-dashboard"></span><em>邮箱通道设置</em>
			</h2>
		</div>
		<div class="col-md-12 column">
			<form
				action="${pageContext.request.contextPath}/admin/emaill/insert.action"
				method="post" class="form-horizontal" id="defaultForm">
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">邮箱接口名称：</label>
					<div class="col-sm-9">
						<input type="text" name="emailcname" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">邮箱公司：</label>
					<div class="col-sm-9">
						<input type="text" name="emailccompany" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">邮箱版本：</label>
					<div class="col-sm-9">
						<input type="text" name="emailvertion" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">邮箱SMTP：</label>
					<div class="col-sm-9">
						<input type="text" name="emailsmtp" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">发送端邮箱：</label>
					<div class="col-sm-9">
						<input type="text" name="emailsend" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">邮箱密码：</label>
					<div class="col-sm-9">
						<input type="text" name="emailpass" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">是否启用：</label>
					<div class="col-sm-9">
						<input type="radio" name="isuse" value="0" checked="checked">是
						<input type="radio" name="isuse" value="1">否
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">添加人：</label>
					<div class="col-sm-9">
						<input type="text" name="addman" class="form-control"
							value="${adminuser.username}" readonly id="inputSuccess3"
							aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group  has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">备注：</label>
					<div class="col-sm-9">
						<textarea class="form-control" rows="3" name="remark"></textarea>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3"></label>
					<div class="col-sm-4">
						<button type="submit" class="btn btn-warning">
							<span class="glyphicon glyphicon-pencil"></span>添加
						</button>
					</div>
					<div class="col-sm-4">
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-refresh"></span>重置
						</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript"
			src="<%=basePath%>dist/js/formValidation.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>dist/js/framework/bootstrap.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>dist/js/language/zh_CN.js"></script>
		<script type="text/javascript">
	$(document).ready(function() {
	    $('#defaultForm').formValidation({
	        message: 'This value is not valid',
	        icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	emailcname: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入邮箱接口名称！谢谢！'
	                    }
	                }
	            },
	            emailccompany: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入邮箱公司名称！谢谢！'
	                    }
	                }
	            },
	            emailvertion: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入邮箱版本！谢谢！'
	                    }
	                }
	            },
	            emailsmtp: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入邮箱SMTP！谢谢！'
	                    },
	                    regexp: {
	                         regexp: '^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$',
	                         message: '请输入正确的邮箱，谢谢！'
	                     }
	                }
	            },
	            emailsend: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入邮箱，谢谢！'
	                    },
	                    regexp: {
	                         regexp: '^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$',
	                         message: '请输入正确的邮箱，谢谢！'
	                     }
	                }
	            },
	            emailpass: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入邮箱密码！谢谢！'
	                    },
	                    regexp: {
	                         regexp: '^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$',
	                         message: '请输入正确的邮箱，谢谢！'
	                     }
	                }
	            }
	        }
	    });
	});
	</script>
	</div>
</body>
</html>