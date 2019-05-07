<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/userdebtattorn/userdebtattornlist.js"></script> --%>
<script type="text/javascript">var basePath="${basePath}";</script>
<style>
table tr td {
	text-align: center;
	vertical-align: middle !important;
}

.text-center2 td {
	border: 1px solid #666;
}
</style>
<!--     <script type="text/javascript">
    	/* 设置是否定向转让 */
	 	$(".isattornlb").change(function(){
	  		var isattorn = $("input[name='isattorn']:checked").val();
	  		if(isattorn==1){
				$("#passowrddiv").show();
			}else{
				$("#passowrddiv").hide();
			}
	  	});
    
    </script> -->
</head>
<body>
	<div style="width: 100%; margin-right: auto;" id="frozen-content">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<table class="table table-condensed">
					<thead>
						<tr style="background-color: #CCCCCC;" class="text-center2">
							<td>序号</td>
							<td>投标订单号</td>
							<td>还款期数</td>
							<!-- <td>投标金额</td> -->
							<td>待收本金</td>
							<!-- <td>标类型</td> -->
							<!-- <td>还款方式</td> -->
							<!-- <td>持有天数</td> -->
							<td>下一个还款日</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody id="text" style="text-align: center;">
						<!-- 这里面${item.id }是点的model里面的属性 -->
						<c:forEach items="${rmList}" var="item" varStatus="status">

							<tr id="item_tr_${item.id }" class="text-center2">
								<td>${status.count}</td>
								<td>${item.utorderno}</td>
								<td>${item.periods}</td>
								<%-- <td>
									<c:if test="${!empty item.amount}">${df.format(item.amount)}</c:if>
								</td> --%>
								<td><c:if test="${!empty item.totalamont}">${df.format(item.totalamont)}</c:if>
								</td>
								<%-- <td>
									${item.tenderitem.tpro}
								</td> --%>

								<td>${sf.format(item.rtime)}</td>
								<%-- <td>${item.repayment.rtime}</td> --%>
								<td>
									<%-- <button class="btn"  data-toggle="modal"
									data-dismiss="modal" data-target="#myModal" onclick="install('${item.orderno}')">发布</button> --%>
									<button class="btn" data-toggle="modal" data-dismiss="modal"
										data-target="#myModal"
										onclick="install('${item.utorderno}','${item.periods}')">发布</button>


								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				<%-- <div id="page_div"><%@ include file="/WEB-INF/jsp/common/pagehelper.jsp"%></div> --%>
			</div>
		</div>
	</div>
</body>
</html>
