<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>DebtAttorn</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 日历 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
   <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
<script type="text/javascript">
	function queryAllPerson(pageNum, pageSize){
		selectdebtAttornByCondition(pageNum,pageSize);
	}
	$(function(){
		$(".grade,.remono").each(function(i) {
			var num = $(this).text();
			if (num.length > 5) {
				$(this).text(num.substr(0, 5) + "...");
			}
		});
		$("#reset").click(function() {
			$("#debtattornno").val("");
			$("#isdebtaudit").val("");
		});
	});
	
	function selectdebtAttornByCondition(pageNum, pageSize){
		var atype=$("#atype1").val();
		if(atype=="请选择"){
			$("#atype1").val("");
		}
		
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectdebtAttornByCondition").submit();
	}
	
	//查看详情
	function todetailUi(tid){
		var action="${pageContext.request.contextPath}/admin/debtAttorn/selectDebtAttornByPrimaryKey.action";
		var param={
				"tid":tid
		}
		var callback=function(data){
			$("#myModal").modal({
	    		backdrop : 'static',
				keyboard : false
	    	});
	    	$("#modal-body").html(data);
		}
		$.post(action,param,callback);
	}
	
	function toUpdateUi(id){
		url="${pageContext.request.contextPath}/admin/debtAttorn/toUpdateUi.action?tid="+id;
		window.location.href=url;
}
	
	
	function insert_DebtAttorn_Ui(){
		var url="admin/debtAttorn/insert_DebtAttorn_Ui.action";
		window.location.href=url;
	}
	
	
	function deleteById(id,tid){
		var url="admin/debtAttorn/deleteDebtAttorn.action";
		var data={
				"id":id,
				"tid":tid
		}
		var deleteCallBack=function(data){
			if($.trim(data)=="删除成功"){
				alert(data);
				window.location.href="${pageContext.request.contextPath }/admin/debtAttorn/selectDebtAttornByCondition.action";
			}else{
				alert(data);
			}
		}
		 var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");   
         if(flag){
        	 jQuery.post(url, data, deleteCallBack);
         }
	}
	
	//通过会员等级添加
	function selectdebbytid(tid){
		var url="${pageContext.request.contextPath}/admin/debtAttorn/seldebBytid.action?tid="+tid;
        window.location.href=url;
	};
</script>

</head>

<body  style="font-family:'微软雅黑'; font-size: 13px;">

<div class="container" style="width:100%">
		<div class="row clearfix">
			<div class="col-md-12 column">
	<h3>债权转让设置列表</h3>
		<form id="selectdebtAttornByCondition" method="post" action="admin/debtAttorn/selectDebtAttornByCondition.action">
			<input type="hidden" id="pageNum" name="pageNum"/>
			<input type="hidden" id="pageSize" name="pageSize"/>
			<label>债权转让编号</label><input type="text" name="debtattornno" id="debtattornno" value="${debtAttorn.debtattornno }"> 
			<label>债权转让审核</label><input type="text" name="isdebtaudit" id="isdebtaudit" value="${debtAttorn.isdebtaudit }"> 
			<div>
					<button id="query_btn" class="btn btn-default" onclick="selectdebtAttornByCondition(1,9)">查询</button>
					<input type="button" value="重置" class="btn btn-default" id="reset" />
			</div>
			<div  class="btn btn-default" onclick="insert_DebtAttorn_Ui()" style="margin-left: 87%">债转</div>
			<a href="${pageContext.request.contextPath}/admin/debtAttorn/insert_DebtAttornfee_Ui.action" class="btn">手续费</a>
			<a href="${pageContext.request.contextPath}/admin/debtAttorn/insert_DebtAttornbuyer_Ui.action" class="btn">购买人</a>
		</form>
				<table class="table table-hover" id="personList_table">
					<thead>
						<tr class="text-center" style="background: #ccc;">
						    <td>序号</td>
							<td>债权编号</td>
							<td>是否支持逾期债转</td>
							<td>债转增益处理</td>
							<td>定向名单</td>
							<td>持有时间</td>
							<td>距离下个还款日天数</td>
							<td>逾期前多少天可债转</td>
							<td>债转期限</td>
							<td>债转次数方式</td>
							<td>债转次数限制</td>
							<td>起息点</td>
							<td>起息规则</td>
							<td>是否拆分</td>
							<td>转让金额低值-高值</td>
							<td>转让系数低值-高值</td>
							<td>备注</td>
							<td style="width: 220px;">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="debtAttorn" varStatus="debsta">
							<tr class="text-center">
							    <td>${debsta.count}</td>
								<td>${debtAttorn.debtattornno }</td>
								<td>
									<c:choose>
										<c:when test="${debtAttorn.isocdebt eq 1 }">
											  支持
										</c:when>
										<c:when test="${debtAttorn.isocdebt eq 0 }">
											   不支持
										</c:when>
									</c:choose>
								</td>
								<td>
								  <c:if test="${debtAttorn.intdisable eq 1}">全部失效</c:if>
								  <c:if test="${debtAttorn.intdisable eq 2}">按债转金额比例失效</c:if>
								</td>
								<td>${debtAttorn.snlid}</td>
								<td>${debtAttorn.holdday}</td>
								<td>${debtAttorn.intervalday}</td>
								<td>${debtAttorn.aheadocday}</td>
								<td>${debtAttorn.deadline}</td>
								<td>
								  <c:if test="${debtAttorn.dattrestrict eq 1}">
								     层级次数
								  </c:if>
								  <c:if test="${debtAttorn.dattrestrict eq 2}">
								     每人次数
								  </c:if>
								</td>
								<td>${debtAttorn.datimes}</td>
								<td>${debtAttorn.valuepoint}</td>
								<td>
								   <c:if test="${debtAttorn.valuerule eq 1}">承接日当天</c:if>
								   <c:if test="${debtAttorn.valuerule eq 2}">承接日次日</c:if>
								   <c:if test="${debtAttorn.valuerule eq 3}">规定时间点</c:if>
								</td>
								<td>
								   <c:choose>
										<c:when test="${debtAttorn.isasplit eq 1 }">
											是
										</c:when>
										<c:when test="${debtAttorn.isasplit eq 0 }">
											否
										</c:when>
									</c:choose>
								</td>
								<td>${debtAttorn.attornmoneylow}~${debtAttorn.attornmoney}元</td>
								<td>${debtAttorn.minattornratio}~${debtAttorn.maxattornratio}元</td>
								<td>${debtAttorn.remark}</td>
								<td>
										<button type="button" class="btn btn-default"  onclick="todetailUi('${debtAttorn.tid}')" >详细信息</button>
										<button class="btn btn-default" onclick="toUpdateUi('${debtAttorn.tid}')">修改</button>
										<button class="btn btn-default" id="modify" onclick="deleteById('${debtAttorn.id }','${debtAttorn.tid}');">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 模态框 -->
				<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel"></h4>
							</div>
							<div class="modal-body" id="modal-body">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
							</div>
						</div>
					</div>
				</div>
				<div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
</div>
</body>
</html>
