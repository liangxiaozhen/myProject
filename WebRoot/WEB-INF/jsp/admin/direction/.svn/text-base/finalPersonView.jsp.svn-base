<%@ page language="java"
	import="java.util.*,com.ptpl.model.UserBaseAccountInfo"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body style="font-family: 微软雅黑;">
	<table class="table table-hover">

		<thead>
			<tr class="text-center" style="background: #ccc;">
				<td>序号</td>
				<td>登录名</td>
				<td>用户ID</td>
				<td>真实姓名</td>
				<td>手机号</td>
			</tr>
		</thead>
		<tbody>
			<%-- <c:forEach items="${ubaiList}" var="ul" varStatus="vs"></c:forEach> --%>

			<!-- 最终的用户名单 -->
			<%
					List ubaiList = (List)request.getAttribute("ubaiList");
					if(!ubaiList.isEmpty() && ubaiList!=null){
						for(int i= 0;i<ubaiList.size();i++){
							UserBaseAccountInfo ubai = (UserBaseAccountInfo)ubaiList.get(i);
				%>
			<tr id="tr_<%=i+1%>" class="text-center" style="display: none;">
				<!-- 序号 -->
				<td><%=i+1%></td>
				<!-- 登录名 -->
				<td><%=ubai.getLoginname()%></td>
				<!-- 用户ID -->
				<td><%=ubai.getId()%></td>
				<!-- 真实姓名 -->
				<td><%=ubai.getRealname()%></td>
				<!-- 手机号 -->
				<td><%=ubai.getMobilephone()==null?"--":ubai.getMobilephone()%></td>
			</tr>
			<%		}
					}
				%>

			<%-- <!-- 最终的排除名单 -->
			<%
					List removeList = (List)request.getAttribute("removeList");
					if(!removeList.isEmpty() && removeList!=null){
						for(int i=0;i<removeList.size();i++){
							UserBaseAccountInfo u = (UserBaseAccountInfo)removeList.get(i);
				%>
			<tr id="tr_<%=i+1%>" class="text-center" style="display: none;">
				<!-- 序号 -->
				<td><%=i+1%></td>
				<!-- 登录名 -->
				<td><%=u.getLoginname()%></td>
				<!-- 用户ID -->
				<td><%=u.getId()%></td>
				<!-- 真实姓名 -->
				<td><%=u.getRealname()%></td>
				<!-- 手机号 -->
				<td><%=u.getMobilephone()==null?"--":u.getMobilephone()%></td>
			</tr>
			<%
						}
					}
				%> --%>
		</tbody>
		<tfoot>
			<tr class="text-center">
				<td colspan="4"><input type="button"
					class="btn btn-default btn-sm" value="首页" onclick="first();">
					<input id="back" type="button" class="btn btn-default btn-sm"
					value="上一页" onclick="back();"> <!-- <span id="select_page"></span> -->
					<input id="next" type="button" class="btn btn-default btn-sm"
					value="下一页" onclick="next();"> <input type="button"
					value="末页" class="btn btn-default btn-sm" onclick="last();">
					<input type="hidden" value="1" id="pageId" /> <select
					id="choose_page" class="selectpicker" onclick="choose_page();"
					style="height: 29px; border-radius: 3px;"></select></td>
				<td>第<span id="pageSpan">1</span>页|共<span id="sumSpan"></span>页&nbsp;&nbsp;共<span
					id="record"></span>条
				</td>
			</tr>
		</tfoot>
	</table>
	<script type="text/javascript">
	
		var usize = "${fn:length(ubaiList)}";
		//alert("usize===="+usize);
		var rsize = "${fn:length(removeList)}";
		//alert("rsize==="+rsize);
		var total =0;
		if(usize!=0){
			total=usize;
			$("#record").html(total);
		}
		/* if(usize==0  && rsize!=0 ){
			total=rsize;
			$("#record").html(total);
		} */
	//	alert("total===="+total);
		var pageSize = 9;//每页显示的数目
		var pages = Math.floor(total/pageSize);//总页数
		
		if(total%pageSize!=0){
			pages+=1;
		}
		
		$("#sumSpan").html(pages);
		
		/* //显示分页页码
		for(var i=1;i<=pages;i++){
			var html = '<input id="'+i+'" type="button" class="btn btn-default btn-sm" value="'+i+'" onclick="select_page(this);">'
			$("#select_page").append(html);
		} */
		
		//显示下拉页码
		var cp = document.getElementById("choose_page");
		cp.options[0] = new Option("--请选择--","0");
		for(var i=1;i<=pages;i++){
			cp.options[i] = new Option(i,i,true);
			
		} 
		//选择下拉页码
		function choose_page(){
			
			var page = $("#choose_page").find("option:checked").val();
			if(page!=0){
				if(page==1){
					$("#back").attr("disabled",true);
				}else{
					$("#back").attr("disabled",false);
				}
				if(page==pages){
					$("#next").attr("disabled",true);
				}else{
					$("#next").attr("disabled",false);
				}
				hide();
				showPage(page);
			}
		}
		
		//单击分页页码
		function select_page(bt){
			hide();
			btValue = bt.value;//按钮的value值
			showPage(btValue);			
			if(btValue==1){
				$("#back").attr("disabled",true);
			}else{
				$("#back").attr("disabled",false);
			}
			if(btValue==pages){
				$("#next").attr("disabled",true);
			}else{
				$("#next").attr("disabled",false);
			}
		}
		
		//显示当页的数据
		function showPage(currentPage){
			var start = (currentPage-1)*pageSize;
			var end = currentPage*pageSize;
			
			for(var i=start;i<end;i++){
				if(i<total){
					document.getElementById("tr_"+(i+1)).style.display="";
				}
			}
			$("#pageId").val(currentPage);
			$("#pageSpan").html(currentPage);
		}
		
		//隐藏全部数据
		function hide(){
			//alert("total==="+total);
			for(var i=0;i<total;i++){
				document.getElementById("tr_"+(i+1)).style.display="none";
			}
		}
		
		//下一页
		function next(){
			
			//点击下一页时需要隐藏全部
			hide();
			var page = $("#pageId").val();
		//	alert("page==="+page);
			var currentPage = parseInt(page)+1;
		//	alert("当前页==="+currentPage);
			showPage(currentPage);
			//alert($("#pageId").val())
			if($("#pageId").val()==parseInt(pages)){
				//为最后一页时
				$("#next").attr("disabled",true);
			}
			//上一页可用
			$("#back").attr("disabled",false);
		}
		
		//上一页
		function back(){
			
			hide();//隐藏所有
			var page =  $("#pageId").val();
			var currentPage = parseInt(page)-1;
			showPage(currentPage);
			
			if($("#pageId").val()==1){
				//为第一页时
				$("#back").attr("disabled",true);
			}
			$("#next").attr("disabled",false);
		}
		
		//首页
		function first(){
			/* if(usize==0  && rsize!=0 ){
				if(rsize>9){
					$("#next").attr("disabled",false);
				}
			}else{ */
				if(usize>9){
					$("#next").attr("disabled",false);
				}
			/* } */
			
			hide();//隐藏所有
			showPage(1);
			if($("#pageId").val()==1){
				//为最后一页时
				$("#back").attr("disabled",true);
			}
		}
		
		//末页
		function last(){
			hide();//隐藏所有
			showPage(pages);
			if($("#pageId").val()==pages){
				//为最后一页时
				$("#next").attr("disabled",true);
			}
			/* if(usize==0  && rsize!=0 ){
				 if(rsize>9){
					$("#back").attr("disabled",false);
				} 
			}else{ */
				if(usize>9){
					$("#back").attr("disabled",false);
				}
			/* } */
			
		}
		
		//默认显示第一页
		for(var i=0;i<pageSize;i++){
			//上一页不可用
			$("#back").attr("disabled",true);
			
			if($("#pageId").val()==parseInt(pages)){
				//为最后一页时
				$("#next").attr("disabled",true);
			}
			if(i<total){
				document.getElementById("tr_"+(i+1)).style.display="";
			}
		}
			
	</script>
</body>
</html>