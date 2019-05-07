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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
<title>管理员后台-用户红包</title>
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style type="text/css">
th {
	text-align: center;
	background: #ccc;
}

td {
	text-align: center;
	vertical-align: text-top !important;
	border: 1px solid #666;
}

body {
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript">
	/*** 备注显示字符个数限制 */
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				//				$(this).attr("title", objString);
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	};

	$(function() {
		$("[limit]").limit();
		/*** 备注tips */
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
		/* 重置查询条件 */
		$("#reset").click(function() {
			
			document.getElementById("ureno").value = '';
			document.getElementById("loginname").value = '';
			document.getElementById("retype").options[0].selected = true;
			document.getElementById("rectype").options[0].selected = true;
			document.getElementById("status").options[0].selected = true;
			document.getElementById("isuse").options[0].selected = true;
			
		});
		/*** 拖拽模态框*/
		var oDiv = document.getElementById("detailModal");
		oDiv.onmousedown = function(e) {
			var e = e || window.event;
			var _this = this;
			var diffX = e.clientX - _this.offsetLeft;
			var diffY = e.clientY - _this.offsetTop;
			document.onmousemove = function(e) {
				var e = e || window.event;
				_this.style.left = e.clientX - diffX + "px";
				_this.style.top = e.clientY - diffY + "px";
			};
			document.onmouseup = function() {
				this.onmousemove = null;
				this.onmouseup = null;
			}
		};
	})

	/*** 分页查询红包表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#redform").submit();
	}
	
	//查看详情   转账
	var userRedEnvelope = {
		action : "<%=basePath%>admin/bonus/queryBonusDetail.action",
		/*** 查看用户红包详情 */
		query_red_detail : function(id){
			param = {
				"id" : id,
			};
			var callback = function(data) {
				$("#detailModal").modal({
					backdrop : 'static',
					keyboard : false
				});
				$("#detailModal-body").html(data);
			};
			$.post(this.action, param, callback);
		}
	}
	
	/**现金红包转账*/
	<%-- function transfer_accounts(obj){
		var action = "<%=basePath%>huifu/giro/transferAccounts.action";
		var id = $(obj).data("opid");
		var baseid = $(obj).data("baseid");
		//alert("id====="+id);
		//alert("baseid======"+baseid);
		var params = {
			"id":id,
			"baseid":baseid	
		};
		var callback = function(data){
			$("#redform").submit();
		}
		$.post(action,params,callback);
	} --%>
	
	//作废
	function redEnvInvalid(obj){
		var action="<%=basePath%>admin/bonus/redEnvInvalid.action"
		var id=$(obj).data("opid");
		var param ={
			"id":id	
		}
		var callback = function(data){
			var json =$.parseJSON(data);
			alert(json["result"]);
			$("#redform").submit();
		}
		$.post(action,param,callback);
	}
</script>
</head>

<body>
	<div class="container" style="width: 75%">

		<div class="row clearfix">
			<div class="col-md-12 column"
				style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
				<div id="area_div" style="margin-top: 50px">
					<h4>用户红包信息</h4>
					<form method="post" role="form" id="redform"
						action="<%=basePath%>admin/bonus/queryBonusList.action">
						<input type="hidden" id="pageNum" name="pageNum"
							value="${pagehelper.pageNum }" /> <input type="hidden"
							id="pageSize" name="pageSize" value="${pagehelper.pageSize}" />
						<ul class="list-inline">
							<li><label>红包编号：</label> <input type="text" id="ureno"
								name="ureno" value="${redEnv.ureno}" placeholder="--请输入红包编号--" /></li>
							<li><label>用户名：</label> <input type="text" id="loginname"
								name="name.loginname" value="${redEnv.name.loginname}"
								placeholder="--请输入用户名--" /></li>
							<li><label>红包类型：</label> <select name="retype" id="retype">
									<option value="">--请选择--</option>
									<c:if test="${!empty retypemaps}">
										<c:forEach items="${retypemaps}" var="retypemap">
											<c:choose>
												<c:when test="${redEnv.retype eq retypemap.key}">
													<option value="${retypemap.key}" selected="selected">${retypemap.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${retypemap.key}">${retypemap.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<li><label>获取方式：</label><select name="rectype" id="rectype">
									<option value="">--请选择--</option>
									<c:if test="${!empty rectypemaps }">
										<c:forEach items="${rectypemaps }" var="rectype">
											<c:choose>
												<c:when test="${redEnv.rectype eq rectype.key }">
													<option value="${rectype.key}" selected="selected">${rectype.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${rectype.key}">${rectype.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>
							<li><label>发放状态：</label><select id="status" name="status">
									<option value="">--请选择--</option>
									<c:if test="${! empty statusmaps }">
										<c:forEach items="${statusmaps}" var="stmap">
											<c:choose>
												<c:when test="${redEnv.status eq stmap.key}">
													<option value="${stmap.key }" selected="selected">${stmap.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${stmap.key }">${stmap.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if></li>
							</select>

							<li><label>红包状态：</label><select id="isuse" name="isuse">
									<option value="">--请选择--</option>
									<c:if test="${!empty isusemaps }">
										<c:forEach items="${isusemaps}" var="isusemap">
											<c:choose>
												<c:when test="${redEnv.isuse eq isusemap.key}">
													<option value="${isusemap.key}" selected="selected">${isusemap.value}</option>
												</c:when>
												<c:otherwise>
													<option value="${isusemap.key}">${isusemap.value}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
							</select></li>

							<li><input type="submit" value="查询" class="btn" /> <input
								type="button" value="重置" class="btn" id="reset" /></li>
						</ul>
					</form>
					<div class="table-responsive">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th>序号</th>
									<!-- <th>红包ID</th> -->
									<th>用户名</th>
									<th>红包编号</th>
									<th>获取方式</th>
									<th>红包类型</th>
									<th>红包价值</th>
									<th>发放状态</th>
									<th>红包状态</th>
									<!-- <th>发放时间</th> -->
									<!-- <th>失效时间</th> -->
									<!-- <th>是否审核</th> -->
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty pagehelper.list}">
									<c:forEach items="${pagehelper.list}" var="red" varStatus="vs">
										<tr>
											<!-- 序号 -->
											<td>${vs.index+1}</td>
											<!-- ID -->
											<%-- <td>${red.id}</td> --%>
											<!-- 用户名 -->
											<td>${red.name.loginname}</td>
											<!-- 红包编号 -->
											<td>${red.ureno}</td>
											<!-- 获取方式 -->
											<td><c:choose>
													<c:when test="${red.rectype==1}">注册</c:when>
													<c:when test="${red.rectype==2}">累投</c:when>
													<c:when test="${red.rectype==3}">单标</c:when>
													<c:when test="${red.rectype==4}">首投</c:when>
													<c:when test="${red.rectype==5}">手动颁奖</c:when>
													<c:when test="${red.rectype==6}">自主颁奖</c:when>
													<c:when test="${red.rectype==8}">流标补偿</c:when>
												</c:choose></td>
											<!-- 红包类型 -->
											<td><c:choose>
													<c:when test="${red.retype==1}">现金</c:when>
													<c:when test="${red.retype==2}">类现金</c:when>
													<c:when test="${red.retype==3}">假现金</c:when>
												</c:choose></td>

											<!-- 红包价值 -->
											<td>${red.redenvelope}</td>
											<!-- 发放状态 -->
											<td><c:choose>
													<c:when test="${red.status==1}">待审核</c:when>
													<c:when test="${red.status==2}">待处理</c:when>
													<c:when test="${red.status==3}">已领取</c:when>
													<c:when test="${red.status==4}">待确认</c:when>
													<c:when test="${red.status==5}">已经确认</c:when>
													<c:when test="${red.status==6}">发货中</c:when>
													<c:when test="${red.status==7}">领取失败</c:when>
												</c:choose></td>
											<!-- 红包状态 -->
											<td><c:choose>
													<c:when test="${red.isuse==1 }">未到期</c:when>
													<c:when test="${red.isuse==2 }">可使用</c:when>
													<c:when test="${red.isuse==3 }">已冻结</c:when>
													<c:when test="${red.isuse==4 }">已使用</c:when>
													<c:when test="${red.isuse==5 }">已到期</c:when>
													<c:when test="${red.isuse==6 }">已作废</c:when>
												</c:choose></td>
											<!-- 发放时间 -->
											<%-- <td>
												<fmt:formatDate value="${red.restime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td> --%>
											<!-- 失效时间 -->
											<%-- <td>
												<fmt:formatDate value="${red.refailtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
											</td> --%>
											<!-- 是否审核 -->
											<%-- <td>
												<c:choose>
													<c:when test="${red.isaudit==0}">是</c:when>
													<c:when test="${red.isaudit==1}">否</c:when>
												</c:choose>
											</td> --%>
											<!-- 操作 -->
											<td><c:if
													test="${red.status==3 && red.retype!=1 && red.isuse!=6}">
													<button class="btn" onclick="redEnvInvalid(this)"
														data-opid="${red.id}">
														<font color="red">作废</font>
													</button>
												</c:if>
												<button class="btn"
													onclick="userRedEnvelope.query_red_detail('${red.id}');">详情</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty pagehelper.list}">
									<tr>
										<td colspan="100">没有相关数据</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					<div id="page_div">
						<%@ include file="./../../common/pagehelper.jsp"%>
					</div>
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
						<span class="glyphicon glyphicon-exclamation-sign"></span>提示：您正在查看用户红包详情
					</h4>
				</div>
				<div class="modal-body" id="detailModal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>