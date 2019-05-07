<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误显示页面</title>
</head>
<body>
<input type="hidden" value="${aheadFineError}" id="aheadFineError"/>
<input type="hidden" value="${message}" id="message"/>

<input type="hidden" value="${ramountCount}" id="ramountCount"/>
<input type="hidden" value="${interestCount}" id="interestCount"/>
<input type="hidden" value="${count}" id="count"/>

<script type="text/javascript">
	$(function(){
		 var aheadFineError = $("#aheadFineError").val();
		 var ramountCount   = $("#ramountCount").val();
		 var interestCount  = $("#interestCount").val();
		 var count = $("#count").val();
   		 if(aheadFineError == "用户没有登录"){
			 alert(aheadFineError);
 			 window.top.location.href = basePath + "/user/tologin.action";
		 }else if(aheadFineError == "允许部分提前还款"){
			  var html ="<button type='button' class='btn btn-default' onclick='userloanApp.low_selectPartAheadRepayMent(this)' id='selectPartAheadRepayMent'>部分提前还款</button>"+
			  "<button type='button' class='btn btn-default' onclick='userloanApp.low_selectAllAheadRepayMent(this)'  id='selectAllAheadRepayMent'>全部提前还款</button>";
			  $("#myRepayModal").modal();
			  $("#myRepayModal_body").html(html);
 		 }else{
 			 alert(aheadFineError);
			 window.location.href = basePath + "/user/userLoanApp/list.action";
		 }
 	})
</script>
</body>
</html>