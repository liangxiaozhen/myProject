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
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<title>借款人资料详情</title>
<style type="text/css">
#ht {
	margin-left: 40%;
}
</style>
</head>
<body>
	<div class="container" id="finishBaseInfo">
		<div id="ht">
			<h2>
				<span class="glyphicon glyphicon-menu-down"></span><em>借款信息修改</em>
			</h2>
		</div>
		<div class="col-md-12 column">
			<form class="form-horizontal"
				action="<%=basePath%>/loan/updateioaninfo.action" method="post"
				id="defaultForm">
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">用户姓名：</label>
					<div class="col-sm-9">
						<input type="text" value="${user.realname}" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status"
							readonly> <span
							class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">教育程度：</label>
					<div class="col-sm-9">
						<select name="education" class="form-control">
							<option selected="selected">--请选择--</option>
							<option value="1" ${(loaninfoaudit.education==1 )? 'selected':''}>初中</option>
							<option value="2" ${(loaninfoaudit.education==2 )? 'selected':''}>职高</option>
							<option value="3" ${(loaninfoaudit.education==3 )? 'selected':''}>高中</option>
							<option value="4" ${(loaninfoaudit.education==4 )? 'selected':''}>专科</option>
							<option value="5" ${(loaninfoaudit.education==5 )? 'selected':''}>本科</option>
							<option value="6" ${(loaninfoaudit.education==6 )? 'selected':''}>硕士</option>
							<option value="7" ${(loaninfoaudit.education==7 )? 'selected':''}>博士</option>
							<option value="8" ${(loaninfoaudit.education==8 )? 'selected':''}>其他</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">毕业院校：</label>
					<div class="col-sm-9">
						<input type="text" name="gradinst"
							value="${loaninfoaudit.gradinst}" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">婚姻状况：</label>
					<div class="col-sm-9">
						<select name="maritalstatus" class="form-control">
							<option selected="selected">--请选择--</option>
							<option value="0"
								${(loaninfoaudit.maritalstatus==0 )? 'selected':''}>已婚</option>
							<option value="1"
								${(loaninfoaudit.maritalstatus==1 )? 'selected':''}>未婚</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">公司名称：</label>
					<div class="col-sm-9">
						<input type="text" name="company" value="${loaninfoaudit.company}"
							class="form-control" id="inputSuccess3"
							aria-describedby="inputSuccess3Status"> <span
							class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">月&nbsp;&nbsp;薪：</label>
					<div class="col-sm-9">
						<input type="text" name="salary" value="${loaninfoaudit.salary}"
							class="form-control" id="inputSuccess3"
							aria-describedby="inputSuccess3Status"> <span
							class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">收入说明：</label>
					<div class="col-sm-9">
						<input type="text" name="salaryinfo"
							value="${loaninfoaudit.salaryinfo }" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">是否购车：</label>
					<div class="col-sm-9">
						<select name="iscarow" class="form-control">
							<option selected="selected">--请选择--</option>
							<option value="0" ${(loaninfoaudit.iscarow==0 )? 'selected':''}>已购</option>
							<option value="1" ${(loaninfoaudit.iscarow==1 )? 'selected':''}>暂未</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">车辆信息：</label>
					<div class="col-sm-9">
						<input type="text" name="carinfo" value="${loaninfoaudit.carinfo}"
							class="form-control" id="inputSuccess3"
							aria-describedby="inputSuccess3Status"> <span
							class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">是否购房：</label>
					<div class="col-sm-9">
						<select name="ishouseow" class="form-control">
							<option selected="selected">--请选择--</option>
							<option value="0" ${(loaninfoaudit.ishouseow==0 )? 'selected':''}>已购</option>
							<option value="1" ${(loaninfoaudit.ishouseow==1 )? 'selected':''}>暂未</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">房产信息：</label>
					<div class="col-sm-9">
						<input type="text" name="houseinfo"
							value="${loaninfoaudit.houseinfo }" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">其他资产：</label>
					<div class="col-sm-9">
						<textarea class="form-control" rows="3" name="other">${loaninfoaudit.other }</textarea>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">紧急联系人：</label>
					<div class="col-sm-9">
						<input type="text" name="contactsman"
							value="${loaninfoaudit.contactsman }" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">紧急联系人电话：</label>
					<div class="col-sm-9">
						<input type="text" name="contactsphone"
							value="${loaninfoaudit.contactsphone}" class="form-control"
							id="inputSuccess3" aria-describedby="inputSuccess3Status">
						<span class="glyphicon glyphicon-ok form-control-feedback"
							aria-hidden="true"></span> <span id="inputSuccess3Status"
							class="sr-only">(success)</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">紧急联系人关系：</label>
					<div class="col-sm-9">
						<select name="contactsration" class="form-control">
							<option selected="selected">--请选择--</option>
							<option value="0"
								${(loaninfoaudit.contactsration==0 )? 'selected':''}>父子</option>
							<option value="1"
								${(loaninfoaudit.contactsration==1 )? 'selected':''}>母子</option>
							<option value="2"
								${(loaninfoaudit.contactsration==2 )? 'selected':''}>兄妹</option>
							<option value="3"
								${(loaninfoaudit.contactsration==3 )? 'selected':''}>兄弟</option>
							<option value="4"
								${(loaninfoaudit.contactsration==4 )? 'selected':''}>亲朋</option>
							<option value="5"
								${(loaninfoaudit.contactsration==5 )? 'selected':''}>其他</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<div class="col-sm-9">
						<input type="hidden" value="${user.id}" name="baseid"> <input
							type="hidden" value="0" name="auditstatus"> <input
							type="hidden" name="id" value="${loaninfoaudit.id }">
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3"></label>
					<div class="col-sm-4">
						<button type="submit" class="btn btn-warning">
							<span class="glyphicon glyphicon-pencil"></span>修改
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
	        	maritalstatus: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入婚姻状况！谢谢！'
	                    }
	                }
	            },
	            education: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请选择你当前学历！谢谢！'
	                    }
	                }
	            },
	            gradinst: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入你毕业院校！谢谢！'
	                    }
	                }
	            },
	            company: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入公司名称！谢谢！'
	                    }
	                }
	            },
	            salary: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款金额！谢谢！'
	                    },
	                    lessThan: {
	                        value: 200000,
	                        inclusive: true,
	                        message: '一次不能超过20万的。。。'
	                    }
	                }
	            },
	            salaryinfo: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入收入说明！谢谢！'
	                    }
	                }
	            },
	            iscarow: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入是否购车！谢谢！'
	                    }
	                }
	            },
	            ishouseow: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入是否购房！谢谢！'
	                    }
	                }
	            },
	            contactsman: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入紧急联系人！谢谢！'
	                    }
	                }
	            },
	            contactsphone: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入紧急联系人电话！谢谢！'
	                    },
	                    stringLength: {
	                        min: 11,
	                        max:11,
	                        country: 'US',
	                        message: ' '
	                    },
	                    phone: {
	                    	 country: 'US',
	                    	 message: '手机号为11位！手机号不能有除数字以外的字符，谢谢！'
	                    }
	                }
	            },
	            contactsration: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入紧急联系人关系！谢谢！'
	                    }
	                }
	            }
	        }
	    });
	});
	</script>
</body>
</html>