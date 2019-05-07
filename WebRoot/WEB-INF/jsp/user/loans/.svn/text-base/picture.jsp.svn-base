<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<title>图片资料查看</title>
<style type="text/css">
#ht{
margin-left: 40%;
}
</style>
<script type="text/javascript">
  //修改
  function btnupdate(){
	  var url="${pageContext.request.contextPath}/user/loan/usercommonupdate.action";
	  window.location.href=url;
  }
</script>
</head>
<body style="font-family:'微软雅黑';font-size: 15px;">
<div id="ht"><h2><span class="glyphicon glyphicon-user"></span><em>公共资料查看</em></h2></div>
<%-- <div class="col-md-12 column tab-pane fade in active" id="home">
<c:if test="${!empty piclist}">
	<table class="table table-hover">
		<c:forEach var="item" items="${piclist}">
		  <tr>
			<th><span class="glyphicon glyphicon-ice-lolly" id="creattype"><b>${item.certname}:</b></span></th>
			<td><img src="http://localhost:8080/pic/${item.certinfopath}" width="300px" height="300px" class="img-rounded"></td>
		 </tr>
		</c:forEach>
		<tr>
			<td colspan="2" align="center"><a href="<%=basePath%>/loan/seleceBaseByid.action?id=${user.id}" ><span class="font">回到资料审核<span class="glyphicon glyphicon-chevron-right"></span><span class="glyphicon glyphicon-chevron-right"></span></span></a></td>
		</tr>
	</table>
</c:if> --%>
<%-- <c:if test="${!empty list}">
	<table class="table table-hover">
		<c:forEach var="item" items="${list}">
		  <tr>
			<td><span class="glyphicon glyphicon-ice-lolly" id="creattype"><b>${item.materialname}:</b></span></td>
			<td>
			<c:if test="${!empty item.materialcontent}">
			   <span style="width:300px, height:300px;text-align: center;line-height: 300px;">${item.materialcontent}</span>
			</c:if>
			<c:if test="${!empty item.materialpic}">
			<img src="http://localhost:8080/pic/${item.materialpic}" width="300px" height="300px" class="img-rounded">
			 </c:if>
			</td>
		 </tr>
		</c:forEach>
		<tr>
		</tr>
	</table>
</c:if>
</div> --%>


 <div class="container" style="width:50%;">
         <c:if test="${!empty list}">
		 	 <table class="table table-hover" style="border-bottom: 1px solid #e6e6e6;">
		 	     <c:forEach var="item" items="${list}">
		 	        <tr>
		 	           <td style="padding: 15px;"></td>
		 	 	 	     <td style="padding: 15px;"><label>${item.materialname}:</label></td>
		 	 	 	   <td style="padding: 15px;">
		 	 	 	       <c:if test="${!empty item.materialcontent}">
		 	 	 	           ${item.materialcontent}
		 	 	 	       </c:if>
			 	 	 	   <c:if test="${!empty item.materialpic}">
			 	 	 	     <a href="#" onclick="open('http://localhost:8080/pic/${item.materialpic}','介绍','width=500,height=440,left=550,top=250,resizable=no,scrollbars=no,status=yes,toolbar=no,location=no,menubar=no,menu=yes')">查看图片</a>
			 	 	 	   </c:if>
		 	 	 	    </td>
		 	 	    </tr>
		 	     </c:forEach>
		 	 </table>
		 	 <button class="btn btn-primary" style="margin-left: 365px;" onclick="btnupdate()">修改</button>
	 	 </c:if>
</div>
</body>
</html>