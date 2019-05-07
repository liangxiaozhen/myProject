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
<c:if test="${empty tenderFeiType}">
    <div class="container">
        <form action="${pageContext.request.contextPath}/admin/loantype/addloantype.action" method="post" id="insert-form" class="form-horizontal">

            <div class="form-group">
                <label class="col-sm-1 control-label">类别名称</label>
                <div class="col-sm-3">
                    <input type="text" name="typename"  class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">备注</label>
                <div class="col-sm-3">
                    <textarea name="remark" rows="3"  class="form-control"></textarea>
                </div>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${!empty tenderFeiType}">
    <div class="container">
        <form action="${pageContext.request.contextPath}/admin/loantype/updateloantype.action" method="post" id="update-form" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">类别名称</label>
                <div class="col-sm-3">
                    <input type="text" name="typename"  class="form-control" value="${tenderFeiType.typename}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">备注</label>
                <div class="col-sm-3">
                    <textarea name="remark" rows="3"  class="form-control">${tenderFeiType.remark}</textarea>
                </div>
            </div>
            <input type="hidden" name="id" id="loanTypeObjectQuoteId" value="${tenderFeiType.id}"/>
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
                typename: {
                    required: true,
                }
            },
            messages: {
                typename: {
                    required: "必填",
                }
            }
        }).form();
    }

</script>
</body>
</html>