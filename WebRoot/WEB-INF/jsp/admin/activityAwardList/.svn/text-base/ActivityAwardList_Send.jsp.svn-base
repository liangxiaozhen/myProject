<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<title>ActivityAwardList_List</title>
<style type="text/css">
.remark-p {
	width: 85px;
}
td {
	text-align: center;
	vertical-align: text-top !important;
	border: 1px solid #666;
}
</style>
<script type="text/javascript">
	
	/* 限制备注显示字符个数 */
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

	$(function() {
		$(".ugrade").each(function(i) {
			var num = $(this).html();
			if (num.length > 7) {
				$(this).html(num.substr(0, 7) + "...");
			}
		});
		
		//重置
		$("#reset_").click(function(){
			document.getElementById("username").value="";
			document.getElementById("actid").value="";
			document.getElementById("awardname").value="";
			document.getElementById("status").options[0].selected=true;
		});
	});

	//查询所有数据
	function queryAllPerson(pageNum, pageSize) {
		selectawardBySend(pageNum,pageSize);
	}
	function selectawardBySend(pageNum, pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#selectawardBySend").submit();
	}
	
	//详情
	function todetailUi(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/selectActivityAwardListByPrimaryKey.action";
		var id=$(obj).data("opid");
		var param={
			"id":id
		};
		var callback = function(data){
			$("#detailModal").modal({
				backdrop:'static',
				keyboard:false
			});
			$("#detailModal-body").html(data);
		};
		$.post(action,param,callback);
	}

	//用户的收货信息有问题，需要用户再次确认
	function sureRemarkAgain(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/sureRemarkAgain.action";
		var id=$(obj).data("opid");
		var status=$(obj).data("status");
		var remark=$(obj).data("remark");
		var awardname=$(obj).data("awardname");
		var param ={
			"id":id,
			"status":status,
			"remark":remark,
			"awardname":awardname
		}
		var callback = function(data){
			$("#sureModal").modal({
				backdrop:"static",
				keyboard:false
			})
			$("#sureModal-body").html(data);
		};
		$.post(action,param,callback);
	}
	
	//给获奖的用户发放奖品
	function sendActivityAward(obj){
		var awardattribute=$(obj).data("awardattribute");
		if(awardattribute==1){
			
			$.ajaxSetup({
				async: false
			});
			
			//现金红包
			var id = $(obj).data("opid");
			//alert("id====="+id);
			/* 汇付接口 */
			<%-- var action = "<%=basePath%>huifu/giro/transferAccounts.action"; --%>
			/* 乾多多接口 */
			<%-- var action = "<%=basePath%>moneymoremore/giro/transferAccounts.action"; --%>
			/* 徽商接口 */
			var action = "<%=basePath%>huishang/giro/redTransferAccounts.action";

			var params = {
				"id":id
			};
			var callback = function(data){
				
				var str = $.parseJSON(data);
				//alert("str==="+str);
				if(str=="false"){
					alert("用户没有开通托管账户");
				}else if(str=="success"){
					alert("操作成功");
					$("#selectawardBySend").submit();
				}else if(str=="unEnough"){
					alert("账户余额不足");
				}else{
					alert("操作失败");
				}
			}
			$.post(action,params,callback);
		}else{
			//非现金红包
			var action ="${pageContext.request.contextPath}/admin/activityAwardList/sendActivityAward.action";
			var id=$(obj).data("opid");
			//alert("id====="+id);
			var status=$(obj).data("status");
			//alert("status====="+status);
			var param = {
				"id":id,
				"status":status,
				"awardattribute":awardattribute
			};
			var callback=function(data){
				var json=$.parseJSON(data);
				alert(json["result"]);
				$("#selectawardBySend").submit();
			} 
			$.post(action,param,callback);
		}
	}
	
	//用户已领取奖品
	function alreadyReceived(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/alreadyReceived.action";
		var id=$(obj).data("opid");
		var status=$(obj).data("status");
		var param ={
			"id":id,
			"status":status
		}
		var callback= function(data){
			var json =$.parseJSON(data);
			alert(json["result"]);
			//window.location.href="${pageContext.request.contextPath}/activityAwardList/selectActivityAwardListByCondition.action";
			$("#selectawardBySend").submit();
		}
		$.post(action,param,callback);
	}
	
	//用户奖品领取失败
	function receiveFailed(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/receiveFailed.action";
		var id=$(obj).data("opid");
		var status=$(obj).data("status");
		var param ={
			"id":id,
			"status":status
		}
		var callback= function(data){
			var json =$.parseJSON(data);
			alert(json["result"]);
			//window.location.href="${pageContext.request.contextPath}/activityAwardList/selectActivityAwardListByCondition.action";
			$("#selectawardBySend").submit();
		}
		$.post(action,param,callback);
	}
	
	//不通过
	function passedFailure(obj){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/passedFailure.action";
		var id=$(obj).data("opid");
		var status=$(obj).data("status");
		var param={
			"id":id,
			"status":status
		}
		var callback = function(data){
			var json = $.parseJSON(data);
			alert(json["result"]);
			$("#selectawardBySend").submit();
		}
		$.post(action,param,callback);
	}
	
	//管理员填写完管理员备注后确定，提示用户的收货信息有误
	function adminRemarkSure(){
		var action="${pageContext.request.contextPath}/admin/activityAwardList/toSureRemark.action";
		var id=$("#actAwardId").text();
		//alert("id: "+id);
		var adminRemark=$("#adminAwardRemark").val();
		var param={
			"id":id,
			"adminRemark":adminRemark
		}
		var callback= function(data){
			var json=$.parseJSON(data);
			alert(json["result"]);
			//window.location.href="${pageContext.request.contextPath}/activityAwardList/selectActivityAwardListByCondition.action?pageNum="+${pagehelper.pageNum};
			$("#selectawardBySend").submit();
		}
		$.post(action,param,callback);
	}
	
	//全选或者全不选
	function select_toggle(cb){
		
		var checkboxs = $(".checkbox_id");
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked = cb.checked;
		}
	}
	
	//批量发货
	function batchDeliver(){
		
		var checkboxs = $(".checkbox_id:checked");
		var size = checkboxs.size();
		//alert("size:　"+size);
		
		var ids = "";
		var money = 0;
		for(var i=0;i<size;i++){
			var status = checkboxs.eq(i).data("status");
			if(status == 2 || status == 5){
				//待处理或者已确认
				ids += checkboxs.eq(i).val()+",";
				money = money + checkboxs.eq(i).data("amount")*checkboxs.eq(i).data("num");
			}
		}
		
		ids = ids.substring(0,ids.lastIndexOf(","));
		
		//alert("ids:"+ids);
		//alert("money:"+money);
		
		if(ids){
			$.ajax({
				url:"<%=basePath%>admin/activityAwardList/batchDeliver.action",
				type:"post",
				dataType:"json",
				data:{
					"ids":ids,
					"money":money
				},
				async:false,
				success:function(data){
					if(data["result"]=="success"){
						alert("操作成功");
					}else if(data["result"]=="fail"){
						alert("操作失败");
					}else if(data["result"]=="unEnough"){
						alert("账户余额不足");
					}
					$("#selectawardBySend").submit();
				}
			});
		}else{
			alert("请选择要发货的奖品");
		}
	}
	
	//撤销发红包
	function revokeRed(obj){
		
		var action = "<%=basePath%>huishang/giro/redRevokeAccounts.action";
		
		$.ajax({
			url:action,
			type:"post",
			dataType:"json",
			data:{
				"id":"1"
			}, 
			success:function(data){
				
			}
		});
	}
</script>
</head>
<body style="font-family: '微软雅黑';">
	<div class="container" style="margin-top:20px;">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<h3>奖品发放</h3>
				<form id="selectawardBySend" method="post"
					action="${pageContext.request.contextPath}/admin/activityAwardList/selectActivityAwardListSend.action">
					<input type="hidden" id="pageNum" name="pageNum"
						value="${pagehelper.pageNum}" /> <input type="hidden"
						id="pageSize" name="pageSize" value="${pagehelper.pageSize}" /> <label>用户名：</label><input
						type="text" name="username" id="username" value="${aal.username }"
						placeholder="--请输入用户名--"> <label>活动编号：</label><input
						type="text" name="actid" id="actid" value="${aal.actid }"
						placeholder="--请输入活动编号--">
					<%-- <label>奖品编号：</label><input type="text" name="awardno" id="awardno" value="${aal.awardno }" placeholder="--请输入奖品编号--">  --%>
					<label>奖品名称：</label><input type="text" name="awardname"
						id="awardname" value="${aal.awardname }" placeholder="--请输入奖品名称--">
					<label>发放状态：</label> <select name="status" id="status">
						<option value="">请选择</option>
						<c:if test="${!empty statussendmaps}">
							<c:forEach items="${statussendmaps }" var="st">
								<c:choose>
									<c:when test="${aal.status==st.key }">
										<option value="${st.key }" selected="selected">${st.value }</option>
									</c:when>
									<c:otherwise>
										<option value="${st.key }">${st.value }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</select>
					<button id="query_btn" class="btn btn-default"
						onclick="selectawardBySend(1,9)">查询</button>
					<button type="button" id="reset_" class="btn btn-default">重置</button>
					<button type="button" class="btn btn-default" onclick="batchDeliver();">批量发货</button>
				</form>
				<table class="table  table-hover" id="tb_personlist">
					<thead>
						<tr class="text-center" style="background: #ccc;">
							<td><input type="checkbox" onclick="select_toggle(this);"/></td>
							<td>序号</td>
							<!-- <td>奖品ID</td> -->
							<td>用户名</td>
							<td>活动编号</td>
							<!-- <td>奖品编号</td> -->
							<td>奖品名称</td>
							<td>奖品个数</td>
							<td>发放状态</td>
							<td>备注</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagehelper.list }" var="activityAwardList"
							varStatus="status">
							<tr class="text-center">
								<td><input type="checkbox" class="checkbox_id" value="${activityAwardList.id}" data-amount="${activityAwardList.awardmoney}" data-num="${activityAwardList.awardquantity}" data-status="${activityAwardList.status}"></td>
								<td>${status.index+1}</td>
								<%-- <td>${activityAwardList.id }</td> --%>
								<td>${activityAwardList.username }</td>
								<td>${activityAwardList.actid }</td>
								<%-- <td>${activityAwardList.awardno }</td> --%>
								<td class="ugrade">${activityAwardList.awardname }</td>
								<td>${activityAwardList.awardquantity }</td>
								<!-- 发放状态 -->
								<td><c:forEach items="${statussendmaps }" var="st">
										<c:choose>
											<c:when test="${activityAwardList.status==st.key }">
												${st.value }
											</c:when>
										</c:choose>
									</c:forEach></td>
								<td><p id="promptExplanation" data-toggle="tooltip"
										class="remark-p text-center"
										title="<h5>${activityAwardList.remark }<h5>" limit="10">${activityAwardList.remark }
									</p></td>
								<td><c:choose>
										<c:when test="${activityAwardList.status ==2}">
											<button class="btn" onclick="sendActivityAward(this)"
												data-opid="${activityAwardList.id }"
												data-status="${activityAwardList.status}"
												data-awardattribute="${activityAwardList.awardattribute}">去发货</button>
										</c:when>
										<c:when test="${activityAwardList.status ==5}">
											<button class="btn" onclick="sureRemarkAgain(this)"
												data-opid="${activityAwardList.id }"
												data-status="${activityAwardList.status}"
												data-remark="${activityAwardList.remark}"
												data-awardname="${activityAwardList.awardname}">再确认</button>
											<button class="btn" onclick="sendActivityAward(this)"
												data-opid="${activityAwardList.id }"
												data-status="${activityAwardList.status}"
												data-awardattribute="${activityAwardList.awardattribute}">去发货</button>
										</c:when>
										<c:when test="${activityAwardList.status ==6}">
											<button class="btn" onclick="sureRemarkAgain(this)"
												data-opid="${activityAwardList.id }"
												data-status="${activityAwardList.status}"
												data-remark="${activityAwardList.remark}"
												data-awardname="${activityAwardList.awardname}">再确认</button>
											<button class="btn" onclick="alreadyReceived(this)"
												data-opid="${activityAwardList.id }"
												data-status="${activityAwardList.status}">已领取</button>
											<button class="btn" onclick="receiveFailed(this)"
												data-opid="${activityAwardList.id }"
												data-status="${activityAwardList.status}"
												data-remark="${activityAwardList.remark}">领取失败</button>
										</c:when>
									</c:choose>
									<button type="button" class="btn" onclick="todetailUi(this)" data-opid="${activityAwardList.id }">详情</button>
									<%-- <button type="button" class="btn" onclick="revokeRed(this)" data-opid="${activityAwardList.id }">撤销</button> --%>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<!-- 详情模态框（Modal） -->
	<div id="detailModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看用户使用券详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 需要用户再次确认（Modal） -->
	<div id="sureModal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在进行再确认操作
					</h4>
				</div>
				<div class="modal-body" id="sureModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success"
						onclick="adminRemarkSure(this)">确认</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>

</html>