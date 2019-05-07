<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Data_list.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
#ht{
margin-left: 40%;
}
</style>
<script type="text/javascript">
  //修改(第一步)
  function Datachange(id,baseid,linno,materialContent,loanno){
	  var action="${pageContext.request.contextPath}/user/loan/Datachange.action";
	  var param={
			"id":id,
			"baseid":baseid,
			"linno":linno,
			"materialContent":materialContent,
			"loanno":loanno
	  };
	  var callback=function(data){
		  $("#updateModal").modal({
				backdrop : 'static',
				keyboard : false
		  });
		  $("#modal-body-update").html(data);
	  };
	  $.post(action,param,callback);
  }
  
  //修改(第二步)
  function updateloantype(){
	  var id=$("#LoanMaterialID").val();//补充资料id
	  var medium=$("#medium").val();//获取资料的类型
	  var materialcontent; 
	  if(medium == "z"||medium == "litt"){//若果是文本内自填和选择内单选
		  materialcontent=$("#materialContent").val();//获取资料的内容
		  var action="${pageContext.request.contextPath}/user/loan/updatLoanMaterial.action";
		  var param={
				"id":id,
				"materialcontent":materialcontent
		  };
		  var callback=function(data){
			    $("#updateModal").modal("hide");
				if(data == "succ"){//如果成功则刷新页面
					alert("提示：更新成功!");
					//window.location.href="${pageContext.request.contextPath}/admin/loanInfo/selectAllNeed.action";
				}
				if(data == "fail"){
					alert("提示：更新失败!");
				}
		  };
		  $.post(action,param,callback,'json');
	  }
	    if(medium == "p"){//如果资料是图片自填类
		    return false;
	   }  
	  if(medium == "more"){//如果资料时候选择类多选
		  var action="${pageContext.request.contextPath}/user/loan/updatemore.action";
	       var callback=function(data){
	    	   $('#updateModal').modal("hide");
				if(data == "succ"){//如果成功则刷新页面
					alert("提示：更新成功!");
					//window.location.href="${pageContext.request.contextPath}/admin/loanInfo/selectAllNeed.action";//需要修改
				}
				if(data == "fail"){
					alert("提示：更新失败!");
				}
	       }
		  $.post(action,$("#moreform").serialize(),callback,'json');
	  }
  }
</script>
</head>
<body style="font-family:'微软雅黑';font-size: 14px;">
<div id="ht"><h2><span class="glyphicon glyphicon-user"></span><em>补充资料查看</em></h2></div>
 <div class="container" style="width:50%;">
         <c:if test="${!empty loanMaterials}">
		 	 <table class="table table-bordered  table-hover text-center">
		 	     <tr>
		 	          <td><strong>资料名称</strong></td>
		 	          <td><strong>资料内容</strong></td>
		 	          <td><strong>审核状态</strong></td>
		 	          <td><strong>操作</strong></td>
		 	     </tr>
		 	     <c:forEach var="item" items="${loanMaterials}">
		 	        <tr>
		 	 	 	   <td style="padding: 15px;"><label>${item.materialname}</label></td>
		 	 	 	   <td style="padding: 15px;">
		 	 	 	       <c:if test="${!empty item.materialcontent}">
		 	 	 	           ${item.materialcontent}
		 	 	 	       </c:if>
			 	 	 	   <c:if test="${!empty item.materialpic}">
			 	 	 	     <a href="#" onclick="open('http://localhost:8080/pic/${item.materialpic}','介绍','width=500,height=440,left=600,top=270,resizable=no,scrollbars=no,status=yes,toolbar=no,location=no,menubar=no,menu=yes')">查看图片</a>
			 	 	 	   </c:if>
		 	 	 	    </td>
		 	 	 	    <td>
		 	 	 	        <c:choose>
		 	 	 	           <c:when test="${item.auditstatus eq 0}">待审核</c:when>
		 	 	 	           <c:when test="${item.auditstatus eq 1}">审核中</c:when>
		 	 	 	           <c:when test="${item.auditstatus eq 2}">合格</c:when>
		 	 	 	           <c:when test="${item.auditstatus eq 3}">不合格</c:when>
		 	 	 	        </c:choose>
		 	 	 	    </td>
		 	 	 	    <td>
		 	 	 	       <c:if test="${item.auditstatus eq 3}">
		 	 	 	           <button title="修改资料" onclick="Datachange('${item.id}','${item.baseid}','${item.linno}','${item.materialcontent}','${item.loanno}')">修改</button>
		 	 	 	       </c:if>
		 	 	 	    </td>
		 	 	    </tr>
		 	     </c:forEach>
		 	 </table>
			  <!-- 修改模态框（Modal） -->
			  <div id="updateModal" class="modal fade bs-example-modal-lg"
				 tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">借款类型对象设置</h4>
						</div>
						<div class="modal-body" id="modal-body-update">
						</div>
						<div class="modal-footer">
						    <button type="button" class="btn btn-default" onclick="updateloantype()" id="btnupdate">修改</button>
							<button type="button" class="btn btn-default"  data-dismiss="modal" >关闭</button>
						</div>
					</div>
				</div>
			</div>
	 	 </c:if>
	 	 <c:if test="${empty loanMaterials}">
	 	   <label>暂无数据！</label>
	 	 </c:if>
</div>
</body>
</html>