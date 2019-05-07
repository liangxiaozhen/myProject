<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>借款申请</title>
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<script type="text/javascript">
    $(function(){
    	$("#splitNum").hide();//隐藏拆标数
        $("#IsMultTender").change(function(){
        	var der=$(this).val();
        	if(der == 1){//允许拆标
        		$("#splitNum").show();
        	}else if(der == 0){//不拆标
        		$("#splitNum").hide();
        		$("#splitNum input").val("");
        	}else{
        		$("#splitNum").hide();
        		$("#splitNum input").val("");
        	}
        	 
        });    	
    });
</script>
<style type="text/css">
#ht{
	margin-left: 40%;
	}
</style>
</head>
<body>
<div class="container" id="finishBaseInfo">
<div id="ht"><h2><span class="glyphicon glyphicon-yen"></span><em>借款申请</em></h2></div>
 <div class="col-md-12 column">	
	<form action="<%=basePath%>user/loan/insertloanapp.action" method="post" id="defaultForm" class="form-horizontal">
	    <input type="hidden" name="apptype" value="1"/>
		<div class="form-group has-success has-feedback">
   				 <label class="control-label col-sm-2" for="inputSuccess3">用户姓名：</label>
   				 <label class="control-label col-sm-1" for="inputSuccess3" style="color: red;">${user.realname}</label>
  		</div>
  		<div class="form-group has-success has-feedback">
   				 <label class="control-label col-sm-2" for="inputSuccess3">标题名称:</label>
   						 <div class="col-sm-9">
      						<input type="text" name="loanname" class="form-control" id="inputSuccess3" aria-describedby="inputSuccess3Status">
   						 </div>
  		</div>
		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">用途描述: </label>
   						<div class="col-sm-9">
      						<textarea class="form-control" rows="3" name="loanpurposedesc"></textarea>
   						 </div>
  		</div>
  		
  		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">借款期限: </label>
   						<div class="col-sm-5">
      						<input type="text" name="appday" class="form-control" id="inputSuccess3" aria-describedby="inputSuccess3Status">
   						 </div>
   						 <div class="col-sm-4">
      						<select name="unit" class="form-control">
      								<option value="" selected="selected">--请选择--</option>
      								<option value="天">天</option>
      								<option value="月">月</option>
      								<option value="年">年</option>
      						</select>
   						 </div>
  		</div>
      
      	<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">借款类型: </label>
   						<div class="col-sm-3">
   						<select name="loantype" class="form-control">
   						   <option value="">请选择</option>
   						   <c:if test="${!empty objectQuotes}">
   						   <c:forEach items="${objectQuotes}" var="quota">
   						      <option value="${quota.serialno}">${quota.objectname}</option>
   						   </c:forEach>
   						</c:if>
   						</select>
   					</div>
  		 </div>
  		 
  		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">是否同意自动还款: </label>
   					<div class="col-sm-3">
   					   <select name="isautorepay" class="form-control">
   					     <option value="">请选择</option>
   					     <option value="0">是</option>
   					     <option value="1">否</option>
   					   </select>
  					</div>
  		</div>
  		
		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">是否约标: </label>
   						<div class="col-sm-3">
   						<select name="isappointtender" class="form-control">
   						    <option value="">请选择</option>
   						    <option value="0">是</option>
   						    <option value="1">否</option>
   						</select>
   						 </div>
  		</div>
		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">发标前是否通知: </label>
   						<div class="col-sm-3" >
   						  <select name="isneedconfirm" class="form-control">
   						      <option value="">请选择</option>
   						      <option value="0">是</option>
   						      <option value="1">否</option>
   						  </select>
   						 </div>
  		</div>
  	    <div class="form-group has-success has-feedback">
  	        <label class="control-label col-sm-2">是否拆标</label>
  	        <div class="col-sm-3">
  	            <select name="ismulttender" id="IsMultTender" class="form-control">
  	                 <option value="">请选择</option>
  	                 <option value="1">是</option>
  	                 <option value="0">否</option>
  	            </select>
  	        </div>
  	    </div>
  	    <div class="form-group has-success has-feedback" id="splitNum">
  	        <label class="control-label col-sm-2">拆标数</label>
  	        <div class="col-sm-3">
  	             <input type="text" name="splitnum" class="form-control"/>
  	        </div>
  	    </div>
  		 
  		 <!-- <div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">借款利率: </label>
   						<div class="col-sm-3" >
   						  <div class="input-group">
      						<input type="text" name="loanrate" class="form-control" id="inputSuccess3">
   						    <span class="input-group-addon">%</span>
   						    </div>
   						 </div>
  		</div> -->
  		
  		<div class="form-group has-success has-feedback">
   				 <label class="control-label col-sm-2" for="inputSuccess3">借款金额:</label>
   						 <div class="col-sm-9">
   						    <div class="input-group">
      						<input type="text" name="loanamount" class="form-control" id="inputSuccess3">
   						    <span class="input-group-addon">元</span>
   						    </div>
   						 </div>
  		</div>	
		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3">还款方式:</label>
   						<div class="col-sm-9">
      						<select name="repaymenttype" class="form-control">
										<option selected="selected" value="">--请选择--</option>
										<option value="1">一次还本付息</option>
										<option value="2">等额本金</option>
										<option value="3">等额本息</option>
										<option value="4">按期付息到期还本</option>
								</select>
   						 </div>
  		</div>
		<div class="form-group has-success has-feedback">
   				 <label  class="control-label col-sm-2" for="inputSuccess3"></label>
   						<div class="col-sm-4">
      						<input type="hidden" name="baseid" value="${user.id}">
   						 </div>
   						 <div class="col-sm-4">
      						<input type="hidden" name="appstatus" value="0">
   						 </div>
  		</div>
  		<div class="form-group has-success has-feedback" >
  			<label class="control-label col-sm-2" for="inputSuccess3"></label>
   						<div class="col-sm-4">
      						<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>添加</button>
   					   </div>
   					   <div class="col-sm-4">
      						<button type="reset" class="btn btn-info"><span class="glyphicon glyphicon-refresh"></span>重置</button>
   					   </div>
  		</div>
  		  <script type="text/javascript" src="<%=basePath%>dist/js/formValidation.js"></script>
   	    <script type="text/javascript" src="<%=basePath%>dist/js/framework/bootstrap.js"></script>
    	<script type="text/javascript" src="<%=basePath%>dist/js/language/zh_CN.js"></script>
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
	                    regexp: { //匹配规则
							regexp: /^(\d*\.)?\d+$/,
							message: '金额必须为小数如:100或100.0'
						}
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
	                    regexp: {
							regexp: /^(0|[1-9]\d*)$/,
							message: '周期只能为正整数'
						},
						stringLength: {
							max: 5,
							message: '数字不能超过5位数'
						},
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