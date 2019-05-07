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
<title>借款申请详情页</title>
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
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
				<span class="glyphicon glyphicon-text-size"></span><em>借款信息详情即修改</em>
			</h2>
		</div>
		<div class="col-md-12 column">
			<form action="<%=basePath%>loan/updateloanapp.action" method="post"
				id="defaultForm" class="form-horizontal">
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
					<label class="control-label col-sm-2" for="inputSuccess3">标题名称:</label>
					<div class="col-sm-9">
						<input type="text" name="loanname" class="form-control"
							value="${loanapp.loanname}" id="inputSuccess3"
							aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">用途描述:
					</label>
					<div class="col-sm-9">
						<textarea class="form-control" rows="3" name="loanpurposedesc">${loanapp.loanpurposedesc}</textarea>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">借款类型:
					</label>
					<div class="col-sm-9">
						<input type="radio" name="loantype" value="1"
							${(loanapp.loantype==1 )? 'checked':''}>普通借款 <input
							type="radio" name="loantype" value="2"
							${(loanapp.loantype==2 )? 'checked':''}>净值标
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">借款期限:
					</label>
					<div class="col-sm-5">
						<input type="text" name="appday" value="${loanapp.appday}"
							class="form-control" id="inputSuccess3"
							aria-describedby="inputSuccess3Status">
					</div>
					<div class="col-sm-4">
						<select name="unit" class="form-control">
							<option selected="selected" value="">--请选择--</option>
							<option ${(loanapp.unit=='天' )? 'selected':''} value="天">天</option>
							<option ${(loanapp.unit=='月' )? 'selected':''} value="月">月</option>
							<option ${(loanapp.unit=='年' )? 'selected':''} value="年">年</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">是否约标:
					</label>
					<div class="col-sm-9">
						<input type="radio" name="isappointtender" value="0"
							${(loanapp.isappointtender==0 )? 'checked':''} id="inlineRadio1">是
						<input type="radio" name="isappointtender" value="1"
							${(loanapp.isappointtender==1 )? 'checked':''} id="inlineRadio2">否
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">发标前是否通知:
					</label>
					<div class="col-sm-9">
						<input type="radio" name="isneedconfirm" value="0"
							${(loanapp.isneedconfirm==0 )? 'checked':''} id="inlineRadio1">是
						<input type="radio" name="isneedconfirm" value="1"
							${(loanapp.isneedconfirm==1 )? 'checked':''} id="inlineRadio2">否
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">自动还款:
					</label>
					<div class="col-sm-9">
						<input type="radio" name="isautorepay" value="0"
							${(loanapp.isautorepay==0 )? 'checked':''} id="inlineRadio1">是
						<input type="radio" name="isautorepay" value="1"
							${(loanapp.isautorepay==1 )? 'checked':''} id="inlineRadio2">否
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">借款金额:</label>
					<div class="col-sm-9">
						<input type="text" name="loanamount" value="${loanapp.loanamount}"
							class="form-control" id="inputSuccess3"
							aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">借款利率:</label>
					<div class="col-sm-8 ">
						<input type="text" name="loanrate" value="${loanapp.loanrate}"
							class="form-control" id="inputSuccess3"
							aria-describedby="inputSuccess3Status">
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">还款方式:</label>
					<div class="col-sm-9">
						<select name="repaymenttype" class="form-control">
							<option selected="selected" value="">--请选择--</option>
							<option value="1" ${(loanapp.repaymenttype==1 )? 'selected':''}>一次还本付息</option>
							<option value="2" ${(loanapp.repaymenttype==2 )? 'selected':''}>等额本金</option>
							<option value="3" ${(loanapp.repaymenttype==3 )? 'selected':''}>等额本息</option>
							<option value="4" ${(loanapp.repaymenttype==4 )? 'selected':''}>按期付息到期还本</option>
						</select>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3"></label>
					<div class="col-sm-4">
						<input type="hidden" name="baseid" value="${user.id}">
					</div>
					<div class="col-sm-4">
						<input type="hidden" name="id" value="${loanapp.id}">
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
	        	loanname: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款标题！谢谢！'
	                    }
	                }
	            },
	            loanpurposedesc: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款用途！谢谢！'
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
	            loanamount: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款金额！谢谢！'
	                    },
	                    lessThan: {
	                        value: 200000,
	                        inclusive: true,
	                        message: '一次不能超过20万的且字符不能为英文、拼音，符号等'
	                    },
	                    greaterThan: {
	                        value: 0,
	                        message: ' '
	                    },
	                }
	            },
	            loanrate: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款利率！谢谢！'
	                    },
	                    lessThan: {
	                        value: 30,
	                        inclusive: true,
	                        message: '利率在5%~30%，谢谢！'
	                    },
	                    greaterThan: {
	                        value: 5,
	                        message: '利率在5%~30%，谢谢！'
	                    }
	                }
	            },
	            appday: {
	                row: '.col-sm-4',
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款期限！谢谢！'
	                    },
	                    lessThan: {
	                        value: 31,
	                        inclusive: true,
	                        message: '数值只能在1~31之间，谢谢！'
	                    },
	                    greaterThan: {
	                        value: 1,
	                        message: ' '
	                    }
	                }
	            },
	            unit: {
	                validators: {
	                    notEmpty: {
	                        message: '请输入借款期限！谢谢！'
	                    }
	                }
	            },
	            repaymenttype: {
	                validators: {
	                    notEmpty: {
	                        message: '请选择还款方式！谢谢！'
	                    }
	                }
	            },
	            loantype: {
	                validators: {
	                    notEmpty: {
	                    	message:'请输入借款类型！谢谢！'
	                    }
	                }
	            },
	            isappointtender: {
	                validators: {
	                    notEmpty: {
	                    	message:'请输入是否约标！谢谢！'
	                    }
	                }
	            },
	            isneedconfirm: {
	                validators: {
	                    notEmpty: {
	                    	message:'请输入是否发标！谢谢！'
	                    }
	                }
	            },
	            isautorepay: {
	                validators: {
	                    notEmpty: {
	                    	message:'请输入是否自动还款！谢谢！'
	                    }
	                }
	            }
	        }
	    });
	});
	</script>
			</form>
		</div>
	</div>
</body>
</html>