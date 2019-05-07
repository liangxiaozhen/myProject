<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
${message}
<input type="hidden" value = "${result}" id="repayMentResult"/> 
<script type="text/javascript">
	 var result = document.getElementById("repayMentResult").value;
	 if(result != null){
		 if(result == "logout"){
			 alert("因操作超时！操作失败！请重新登录操作！");
			 window.location.href = basePath + "/user/tologin.action";
		 }else if(result == "params_error"){
			 alert("操作失败！参数错误！请重新操作！");
			 window.location.href = basePath + "/user/userLoanApp/list.action";
		 }
	 } 
 </script>