<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loanTypeObjectQuote_Inst</title>
</head>
<body>
<c:if test="${empty loanTypeObjectQuote}">
   <div class="container">
			<form id="insert-form" action="${pageContext.request.contextPath}/admin/loantype/addloantype.action" method="post" class="form-horizontal">

				<div class="form-group">
					<label class="col-sm-1 control-label">对象名称</label>
					<div class="col-sm-3">
						<input type="text" name="objectname" id="ObjectName" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">是否启用</label>
					<div class="col-sm-3">
					     <select name="isuse" id="ISUSE" class="form-control">
					        <option value="">请选择</option>
					        <option value="1">是</option>
					        <option value="0">否</option>
					     </select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">备注</label>
					<div class="col-sm-3">
						 <textarea name="remark" rows="3" id="Remark" class="form-control"></textarea>
					</div>
				</div>
			</form>
		</div>
</c:if>
<c:if test="${!empty loanTypeObjectQuote}">
  <div class="container">
			<form id="update-form" action="${pageContext.request.contextPath}/admin/loantype/updateloantype.action" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-1 control-label">对象名称</label>
					<div class="col-sm-3">
						<input type="text" name="objectname" id="ObjectName" class="form-control" value="${loanTypeObjectQuote.objectname}"/>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-sm-1 control-label">是否启用</label>
				    <div class="col-sm-3">
				       <select name="isuse" id="ISUSE" class="form-control">
				          <c:if test="${loanTypeObjectQuote.isuse eq 1}">
				            <option value="1" selected="selected">是</option>
				            <option value="0">否</option>
				          </c:if>
				          <c:if test="${loanTypeObjectQuote.isuse eq 0}">
				            <option value="0" selected="selected">否</option>
				            <option value="1">是</option>
				          </c:if>
				       </select>
				    </div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">备注</label>
					<div class="col-sm-3">
						 <textarea name="remark" rows="3" id="Remark" class="form-control">${loanTypeObjectQuote.remark}</textarea>
					</div>
				</div>
				<input type="hidden" name="id" id="loanTypeObjectQuoteId" value="${loanTypeObjectQuote.id}"/>
				<input type="hidden" name="serialno" id="SerialNo" value="${loanTypeObjectQuote.serialno}"/>
			</form>
		</div>
</c:if>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>

<script type="text/javascript">
    var basePath = "${basePath}"
</script>


<script type="text/javascript">
    //客户端验证
    function checkInsert() {
        return $("#insert-form").validate({
            rules: {
                objectname: {
                    required: true,
                },
                isuse: {
                    required: true,
                },

            },
            messages: {
                objectname: {
                    required: "必填",
                },
                isuse: {
                    required: "必填",
                },

            }
        }).form();
    }

    function checkUpdate() {
        return $("#update-form").validate({
            rules: {
                objectname: {
                    required: true,
                },
                isuse: {
                    required: true,
                },

            },
            messages: {
                objectname: {
                    required: "必填",
                },
                isuse: {
                    required: "必填",
                },

            }
        }).form();
    }


</script>
</body>
</html>