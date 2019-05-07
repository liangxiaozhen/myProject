<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InterestExpense_Detail</title>
<script type="text/javascript">
/* 备注显示字符个数限制*/
jQuery.fn.limit = function() {
	var self = $("[limit]");
	self.each(function() {
		var objString = $(this).text();
		var objLength = $(this).text().length;
		var num = $(this).attr("limit");
		if (objLength > num) {
			objString = $(this).text(objString.substring(0, num) + "...");
		}
	})
}

$(function() {
	$("[limit]").limit();
})

/* 备注tips */
$(function() {
	$("[data-toggle='tooltip']").tooltip({
		html : true
	});
});
</script>
<style type="text/css">
    hr{
		margin: 10px;
	} 
</style>
</head>
<body>
        <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">利息管理费编号：</font>
				<label id="addman-lb" class="col-sm-4">${interestExpense.intexpno}</label>
		</div>
		<hr>
		<div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">利息管理费收款人：</font>
				<label id="addman-lb" class="col-sm-4">${interestExpense.ierecman}</label>
		</div>
		<hr>
		<c:if test="${!empty interestExpenses}">
		   <c:forEach items="${interestExpenses}" var="pense" varStatus="sta">
		      <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">会员等级：</font>
				<label id="addman-lb" class="col-sm-6" limit="30" data-toggle="tooltip" title="<h5>${pense.ugrade}</h5>">${pense.ugrade}</label>
		     </div>
		     <hr>
		      <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">利息管理费百份比：</font>
				<label id="addman-lb" class="col-sm-4">${pense.iepercent}</label>
		     </div>
		     <hr>
		      <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">利息管理收费金额低值：</font>
				<label id="addman-lb" class="col-sm-4">${pense.miniefee}-${pense.maxiefee}元</label>
		     </div>
		     <hr>
		   </c:forEach>
		 </c:if>
		 <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">资金清算是否需要审核：</font>
				<label id="addman-lb" class="col-sm-4">${interestExpense.isaudit}</label>
		</div>
		<hr>
		 <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">是否为模板：</font>
				<label id="addman-lb" class="col-sm-4">${interestExpense.isaudit}</label>
		</div>
		<hr>
		 <div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">添加人：</font>
				<label id="addman-lb" class="col-sm-4">${interestExpense.addman}</label>
		</div>
		<hr>
		<div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">设置时间：</font>
				<label id="addman-lb" class="col-sm-4"><fmt:formatDate value="${interestExpense.addtime}" type="both"/></label>
		</div>
		<hr>
		<div class="row" style="line-height: 10px;">
				<font size="3" class="col-sm-4 text-right">备注：</font>
				<label id="addman-lb" class="col-sm-4">${interestExpense.remark}</label>
		</div>
</body>
</html>