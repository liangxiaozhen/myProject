<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/dateproving.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/numbervalidate/moneyproving.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script> --%>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/rechargerstr/insertRechargerstr.js"></script>
</head>
<script type="text/javascript">
function businessNoselect(){
	var businessNo = $("#businessNo").find("option:selected").val();
	if(businessNo!=""){
		var action = "${pageContext.request.contextPath}/admin/rechargeRstr/codeForName.action"
		var params = {
				"code":businessNo
		}	
		$.post(action,params,function(data){
			var arr = data.split(",");
			$("#codeforname").text(arr[0]);
			$("#snlid").val(arr[1]);
		},'json');
	}
	
}

</script>
<body style="font-family: 微软雅黑">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-10 column">
				<form id="rechargeRate_form" method="POST"
					action="${pageContext.request.contextPath}/admin/rechargeRstr/save.action"
					onsubmit="return InsertForm()">
					<div class="row">
						<p
							style="font-size: 15px; color: #666; font-weight: bold; margin-top: 10px; margin-left: 270px">添加页面</p>
						<hr />
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">会员等级：</label> <label
								class="radio-inline"> <input class="insert-ugrade-radio"
								type="radio" name="ugrade" value="1" checked="checked">
								全部等级
							</label> <label class="radio-inline"> <input
								class="insert-ugrade-radio" type="radio" name="ugrade" value="2">
								选择等级
							</label> &nbsp;&nbsp; <label id="insert_ugrade_lb"></label>
						</div>
					</div>

					<div class="row" style="text-align: right; margin-right: 300px">
						<div class="col-md-12 col-md-offset-2" style="margin-left: 342px">
							<div id="insert-ugrade-checkbox-div" style="text-align: left;">
								<c:forEach items="${ugrades}" var="item" varStatus="status">
									<label class="checkbox-inline"> <input type="checkbox"
										name="ugrades" value="${item.ugrade}">
										${item.ugradename }
									</label>
									<c:if test="${status.count%4==0 }">
										<br />
										<br />
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">充值方式:</label>
							 <select id="rechartype" style="width: 155px;" name="rechartype"  onchange="select()">
								<option value="0">网银</option>
								<option value="4">企业网银</option>
								<option value="2">快捷</option>
								<option value="3">汇款充值</option>
								<!-- <option value="1">代扣充值</option> -->
							</select> 
							<label id="insert_rechartype_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">手续费类型:</label> 
							<select id="feetype" style="width: 155px;" name="feetype">
								<option value="0">无</option>
								<!-- <option value="1">全额自付</option>
								<option value="2">全额他付</option>  -->
								<!-- <option value="3">充值成功时从充值人账户扣除与提现手续费的差值</option>
								<option value="4">充值成功时从平台自有账户扣除与提现手续费的差值</option> -->
							</select> 
							<label id="insert_rechartype_lb"></label>
						</div>
					</div> 

					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">支付公司:</label><input
								type="text" id="paycompany" name="paycompany" /> <label
								id="insert_paycompany_lb"></label>
						</div>
					</div>

					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">日充值金额限制:</label><input
								type="text" id="daymoneyrest" name="daymoneyrest" maxlength="12"
								onkeyup="checkUp(event,this)" /> <label
								id="insert_daymoneyrest_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">单笔最低充值金额限制:</label><input
								type="text" id="lowestmoney" name="lowestmoney" maxlength="12"
								onkeyup="checkUp(event,this)" /> <label
								id="insert_lowestmoney_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">单笔最高充值金额限制:</label><input
								type="text" id="hightestmoney" name="hightestmoney"
								maxlength="12" onkeyup="checkUp(event,this)" /> <label
								id="insert_hightestmoney_lb"></label>
						</div>
					</div>


					<!-- ************************************* -->
					<!-- 	<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">是否启用:</label><input type="radio" name="isuse" value="0" checked="checked"/>否 <input
								type="radio" name="isuse" value="1" />是
						</div>
					</div> -->
					<!-- <div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">是否审核:</label><input type="radio" name="isaudit" value="0" checked="checked"/>否 <input
								type="radio" name="isaudit" value="1" />是
						</div>
					</div> -->
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">是否模板:</label><input
								type="radio" name="istemplet" value="0" checked="checked" />否 <input
								type="radio" name="istemplet" value="1" />是
						</div>
					</div>
				 <div class="row" style="margin-top: 10px;">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">定向名单编号: </label>
							 <select id="businessNo" name="businessNo" onchange="businessNoselect();">
							 	<option value="">--请选择--</option>
								<c:forEach items="${snlList}" var="sl">
									<option value="${sl.businessNo}">${sl.businessNo}</option>
								</c:forEach>
							 
							 </select>
							<label id="insert_removenameno_lb"></label>
						</div>
					</div> 
					 <div class="row" style="margin-top: 10px;">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">定向名单名称: </label>
							<span id="codeforname"></span>
							<label id="insert_removenameno_lb"></label>
						</div>
					</div> 
					<input type="hidden" name="snlid" id="snlid" value=""/>
					
					<%-- <div class="row" style="text-align: right; margin-right: 300px">
						<div class="col-md-12 col-md-offset-2" style="margin-left: 342px">
							<div id="insert-removeno-checkbox-div" style="text-align: left;">
								<c:forEach items="${removenamenos}" var="item"
									varStatus="status">
									<label class="checkbox-inline"> <input type="checkbox"
										name="removenamenos" value="${item.nameno}">
										${item.name}
									</label>
									<c:if test="${status.count%4==0 }">
										<br />
										<br />
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div> --%>

					<!-- ************************************* -->
					<!-- <p
						style="font-size: 15px; color: #666; font-weight: bold; margin-top: -15px; margin-bottom: -15px">时间</p>
					<hr /> -->

					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">充值手续费付款方:</label>
							<!-- <input type="text" id="selfpayratio" name="selfpayratio" maxlength="25" onkeyup="checkUp(event,this)" onblur="proxypayratioVidata()"/>% -->
							<!-- <select id="selfpayratio" style="width: 155px;" name="selfpayratio" onblur="proxypayratioVidata()" onclick="xuanze()">
								<option value="0">0</option>
								<option value="100">100</option>
							</select> -->
							<input class="insert-selfpayratio-radio" type="radio"
								name="selfpayratio" value="100" checked="checked">自付 <input
								class="insert-selfpayratio-radio" type="radio"
								name="selfpayratio" value="0">他付 <label
								id="insert_selfpayratio_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1" id="df"
							style="display: none">
							<label class="col-sm-4 text-right">代付人:</label> <select
								id="proxypayman" style="width: 155px;" name="proxypayman">
								<option value="0">请选择---</option>
								<option value="MDT000001">莫邪软件</option>
								<option value="SDT000001">担保账户1</option>
								<option value="SDT000002">风险保证金账户1</option>
								<option value="BASEDT">BASEDT</option>
							</select> <label id="insert_proxypayman_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1" id="dfbl"
							style="display: none">
							<label class="col-sm-4 text-right">平台代付比例:</label> <input
								type="text" id="proxypayratio" name="proxypayratio"
								maxlength="25" onkeyup="checkUp(event,this)" disabled='disabled' />%
							<label id="insert_proxypayratio_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">备注:</label>
							<textarea id="remark" name="remark" maxlength="100"></textarea>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-sm-6 text-right">
							<button id="save_rechargeRate" class="btn btn-primary">保存</button>
							<a type="button" class="btn btn-primary"
								href="${pageContext.request.contextPath}/admin/rechargeRstr/queryAll.action">返回</a>
						</div>

					</div>
				</form>
			</div>

		</div>


	</div>

</body>
</html>
