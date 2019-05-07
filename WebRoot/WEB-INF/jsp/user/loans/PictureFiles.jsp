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
<title>个人资料图片上传</title>
</head>
<body>
	<div class="container" id="finishBaseInfo">
		<div id="ht">
			<h2>
				<span class="glyphicon glyphicon-yen"></span><em>第二步，上传图片资料</em>
			</h2>
		</div>
		<div class="col-md-12 column">
			<FORM METHOD="POST" action="<%=basePath%>picpath/PictureFiles.action"
				ENCTYPE="multipart/form-data" id="defaultForm"
				class="form-horizontal">
				<div class="form-group has-warning has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3">证明资料类型：</label>
					<div class="col-sm-9">
						<input type="radio" name="certtype" value="1">个人 <input
							type="radio" name="certtype" value="2">企业 <input
							type="hidden" value="${user.id}" name="baseid"> <input
							type="hidden" value="${actNo}" name="liano">
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">机动车登记证书：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="机动车登记证书" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">身份证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="身份证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">房产证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="房产证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">学历证明：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="学历证明" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>

					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">营业执照：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="营业执照" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>

					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">税务登记证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="税务登记证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">组织机构代码证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="组织机构代码证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">银行开户许可证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="银行开户许可证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">财政登记证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="财政登记证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group  has-warning has-feedback">
					<label class="control-label col-sm-2">机构信用代码证：</label>
					<div class="col-lg-7">
						<input type="file" class="form-control" name="机构信用代码证" /> <span
							class="help-block">请上传单张不超过1MB的图片，谢谢！</span>
					</div>
				</div>
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2" for="inputSuccess3"></label>
					<div class="col-sm-4">
						<button type="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-plus"></span>上传她
						</button>
					</div>
					<div class="col-sm-4">
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-refresh"></span>重置
						</button>
					</div>
				</div>
			</FORM>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>dist/js/formValidation.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>dist/js/framework/bootstrap.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>dist/js/language/zh_CN.js"></script>
	<!--  	   <script type="text/javascript">
		$(document).ready(function() {
		    $('#defaultForm').formValidation({
		        icon: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	机动车登记证书: {
		                validators: {
		                    file: {
		                    	 extension: 'pdf',
			                     type: 'application/pdf',
		                        message: '请选择图片，谢谢！'
		                    }
		                }
		            },
		           		     身份证: {
		                validators: {
		                    file: {
		                    	extension: '.jpg',
		                        type: 'application/jpg',
		                        minSize: 1024*1024,
		                        message: 'Please choose a pdf file with a size more than 1M.'
		                    }
		                }
		            },
		           		    房产证: {
		                validators: {
		                    file: {
		                    	extension: '.jpg',
		                        type: 'application/pdf',
		                        maxSize: 10*1024*1024,
		                        message: 'Please choose a pdf file with a size less than 10M.'
		                    }
		                }
		            },
		            	学历证明: {
		                validators: {
		                    file: {
		                    	extension: '.jpg',
		                        type: 'application/pdf',
		                        minSize: 1024*1024,
		                        maxSize: 10*1024*1024,
		                        message: 'Please choose a pdf file with a size between 1M and 10M.'
		                    }
		                }
		            }
		        },
		       		     营业执照: {
	                   validators: {
	                       file: {
	                    	   extension: '.jpg',
	                        type: 'application/pdf',
	                        minSize: 1024*1024,
	                        maxSize: 10*1024*1024,
	                        message: 'Please choose a pdf file with a size between 1M and 10M.'
	                    }
	                }
	            },
	            	   税务登记证: {
	                   validators: {
	                       file: {
	                    	   extension: '.jpg',
	                        type: 'application/pdf',
	                        minSize: 1024*1024,
	                        maxSize: 10*1024*1024,
	                        message: 'Please choose a pdf file with a size between 1M and 10M.'
	                    }
	                }
	            },
	                   组织机构代码证: {
                       validators: {
                          file: {
                        	  extension: '.jpg',
                           type: 'application/pdf',
                           minSize: 1024*1024,
                           maxSize: 10*1024*1024,
                           message: 'Please choose a pdf file with a size between 1M and 10M.'
                        }
                     }
                 },
	            银行开户许可证: {
                validators: {
                   file: {
                	   extension: '.jpg',
                    type: 'application/pdf',
                    minSize: 1024*1024,
                    maxSize: 10*1024*1024,
                    message: 'Please choose a pdf file with a size between 1M and 10M.'
                 }
              }
          },
          财政登记证: {
              validators: {
                 file: {
                	 extension: '.jpg',
                  type: 'application/pdf',
                  minSize: 1024*1024,
                  maxSize: 10*1024*1024,
                  message: 'Please choose a pdf file with a size between 1M and 10M.'
               }
            }
        },
          财政登记证: {
              validators: {
                 file: {
                	 extension: '.jpg',
                  type: 'application/pdf',
                  minSize: 1024*1024,
                  maxSize: 10*1024*1024,
                  message: 'Please choose a pdf file with a size between 1M and 10M.'
               }
            }
        }
		    })
		    .on('success.form.fv', function(e) {
		        e.preventDefault();
		        $('#defaultForm').data('formValidation').disableSubmitButtons(true);
		    });
		});
	</script>	-->
</body>
</html>
