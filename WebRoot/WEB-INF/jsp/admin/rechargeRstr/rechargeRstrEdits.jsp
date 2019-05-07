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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/rechargerstr/updaterechargerstr.js"></script>
<!-- <script type="text/javascript">
	function updateProxypayratio(){
	//平台他付比例
	var  selfpayratio = $("#selfpayratio").val();
	var  proxypayratio = $("#proxypayratio").val();
	if( parseFloat(selfpayratio)<0 ||  parseFloat(selfpayratio)>100){
		$("#update_selfpayratio_lb").html("<font style='color:red;'>请输入0到100之间的数字</font>");
	}else if(selfpayratio==null || selfpayratio==''){
		$("#update_selfpayratio_lb").html("<font style='color:red;'>请输入相应的比例</font>");
	}else{
		if(parseFloat(selfpayratio)==100){
			$("#proxypayratio").val("0");
		}
		if(parseFloat(selfpayratio)==0){
			$("#proxypayratio").val("100");
		}
		if(parseFloat(selfpayratio)!=100 && parseFloat(selfpayratio)!=0){
			var proxypayratio2 = 100-parseFloat(selfpayratio);
			document.getElementById("proxypayratio").value=proxypayratio2;
			
		}
	}
}
	</script> -->
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
			$("#snlid").val(arr[1]);
		},'json');
	}
	
}

</script>
<body style="font-family: 微软雅黑">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-10 column">
				<form method="post"
					action="${pageContext.request.contextPath}/admin/rechargeRstr/update.action"
					onsubmit="return UpdateForm()">
					<div style="display: none;">
						<input id="id" name="id" value="${rstr.id}" />
					</div>
					<p
						style="font-size: 15px; color: #666; font-weight: bold; margin-top: 10px; margin-left: 270px">编辑页面</p>
					<hr />

					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">会员等级:</label> <label
								class="radio-inline"> <input class="update-ugrade-radio"
								type="radio" name="ugrade" value="1" checked="checked">
								全部等级
							</label> <label class="radio-inline"> <input
								class="update-ugrade-radio" type="radio" name="ugrade" value="2"
								id="ugradeselect"> 选择等级
							</label> &nbsp;&nbsp; <label id="update_ugrade_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<div id="update-ugrade-checkbox-div"
								style="padding-left: 100px; padding-top: 12px; margin-left: 160px">
								<c:forEach items="${ugrades}" var="item" varStatus="status">
									<input id="ulist" value="${uList}" type="hidden" />
									<label class="checkbox-inline"> <input type="checkbox"
										name="ugrades" value="${item.ugrade}">
										${item.ugradename}
									</label>
									<c:if test="${status.count%3==0 }">
										<br />
										<br />
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>

					<!-- ******************************* -->
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">充值方式:</label> <select
								name="rechartype" id="rechartype_edit">
								<option value="0"
									<c:if test="${rstr.rechartype ==0}">selected="selected"</c:if>>网银</option>
								<option value="4"
									<c:if test="${rstr.rechartype ==4}">selected="selected"</c:if>>企业网银</option>
								<option value="2"
									<c:if test="${rstr.rechartype ==2}">selected="selected"</c:if>>快捷</option>
								<option value="3"
									<c:if test="${rstr.rechartype ==3}">selected="selected"</c:if>>汇款充值</option>
							</select>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">充值手续费付款方:</label> <select
								id="selfpayratio" style="width: 155px;" name="selfpayratio"
								onclick="xuanze()">
								<option value="0"
									<c:if test="${rstr.selfpayratio eq '0'}">selected="selected"</c:if>>他付</option>
								<option value="100"
									<c:if test="${rstr.selfpayratio eq '100'}">selected="selected"</c:if>>自付</option>
							</select>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">手续费类型:</label> <select
								id="feetype" style="width: 155px;" name="feetype" onchange="">
								<option value="0"
									<c:if test="${rstr.feetype eq '0'}">selected="selected"</c:if>>不付</option>
								<option value="1"
									<c:if test="${rstr.feetype eq '1'}">selected="selected"</c:if>>自付</option>
								<option value="2"
									<c:if test="${rstr.feetype eq '2'}">selected="selected"</c:if>>他付</option>
							</select>
						</div>
					</div>
					<%-- <div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">充值人自付比例:</label>
							<!-- <input type="text" id="selfpayratio" name="selfpayratio" maxlength="25" onkeyup="checkUp(event,this)" onblur="proxypayratioVidata()"/>% -->
							<!-- <select id="selfpayratio" style="width: 155px;" name="selfpayratio" onblur="proxypayratioVidata()" onclick="xuanze()">
								<option value="0">0</option>
								<option value="100">100</option>
							</select> -->
							<input class="update-selfpayratio-radio" type="radio" name="selfpayratio" value=0 ${rstr.selfpayratio==0?"checked='checked'":"" }>他付
							<input class="update-selfpayratio-radio" type="radio" name="selfpayratio" value=100 ${rstr.selfpayratio==100?"checked='checked'":"" }>自付
								<label id="insert_selfpayratio_lb"></label>
						</div>
					</div> --%>
					<div class="row" style="margin-top: 10px; display: none"
						id="proxypaymandiv">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">代付人:</label> <select
								style="width: 155px;" name="proxypayman" id="proxypayman_edit">
								<option value="0"
									<c:if test="${rstr.proxypayman =='0'}">selected="selected"</c:if>>请选择----</option>
								<option value="MDT000001"
									<c:if test="${rstr.proxypayman =='MDT000001'}">selected="selected"</c:if>>莫邪软件</option>
								<option value="SDT000001"
									<c:if test="${rstr.proxypayman =='SDT000001'}">selected="selected"</c:if>>担保账户1</option>
								<option value="SDT000002"
									<c:if test="${rstr.proxypayman =='SDT000002'}">selected="selected"</c:if>>风险保证金账户1</option>
								<option value="BASEDT"
									<c:if test="${rstr.proxypayman =='BASEDT'}">selected="selected"</c:if>>BASEDT</option>
							</select><label id="update_proxypayman_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px; display: none"
						id="proxypayratiodiv">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">平台代付比例:</label> <input
								type="text" id="proxypayratio" name="proxypayratio"
								maxlength="25" onkeyup="checkUp(event,this)" disabled='disabled'
								value="${rstr.proxypayratio}" />% <label
								id="update_proxypayratio_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">日充值限额:</label><input
								value="${df1.format(rstr.daymoneyrest)}" id="daymoney"
								name="daymoneyrest" maxlength="15" onblur="checkBlur(this)"
								onkeyup="checkUp(event,this)" /> <label
								id="update_daymoneyrest_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">单笔充值最低金额:</label><input
								value="${df1.format(rstr.lowestmoney)}" id="zxmoney"
								name="lowestmoney" maxlength="15" onblur="checkBlur(this)"
								onkeyup="checkUp(event,this)" /> <label
								id="update_lowestmoney_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">单笔充值最高金额:</label><input
								value="${df1.format(rstr.hightestmoney)}" id="hightmoney"
								maxlength="15" name="hightestmoney" onblur="checkBlur(this)"
								onkeyup="checkUp(event,this)" /> <label
								id="update_hightestmoney_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">支付公司:</label><input
								value="${rstr.paycompany }" id="paypany" name="paycompany" /> <label
								id="update_paycompany_lb"></label>
						</div>
					</div>
					<%-- 		<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">是否审核:</label> 
							<select name="isaudit" id="isaudit_edit">
								<option value="0"
									<c:if test="${rstr.isaudit ==0}">selected="selected"</c:if>>审核失败</option>
								<option value="1"
									<c:if test="${rstr.isaudit ==1}">selected="selected"</c:if>>审核通过</option>
							</select>
							<input type="radio" name="isaudit" value=0 ${rstr.isaudit==0?"checked='checked'":"" }> 待审核
							<input type="radio" name="isaudit" value=1 ${rstr.isaudit==1?"checked='checked'":"" }> 审核通过
						</div>
					</div> --%>
					<%-- 	<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">是否启用:</label> 
							<select name="isuse" id="isuse_edit">
								<option value="0"
									<c:if test="${rstr.isuse ==0}">selected="selected"</c:if>>关闭</option>
								<option value="1"
									<c:if test="${rstr.isuse ==1}">selected="selected"</c:if>>启用</option>
							</select>
							<input type="radio" name="isuse" value=0 ${rstr.isuse==0?"checked='checked'":"" }> 关闭
							<input type="radio" name="isuse" value=1 ${rstr.isuse==1?"checked='checked'":"" }> 启用
						</div>
					</div> --%>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">是否模板:</label>
							<%-- <select name="istemplet" id="isuse_edit">
								<option value="0"
									<c:if test="${rstr.istemplet ==0}">selected="selected"</c:if>>否</option>
								<option value="1"
									<c:if test="${rstr.istemplet ==1}">selected="selected"</c:if>>是</option>
							</select> --%>
							<input type="radio" name="istemplet" value=0
								${rstr.istemplet==0?"checked='checked'":"" }> 否 <input
								type="radio" name="istemplet" value=1
								${rstr.istemplet==1?"checked='checked'":"" }> 是
						</div>
					</div>

					<!-- ********************************************** -->
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">定向名单编号: </label>
							 <select id="businessNo" name="businessNo" onchange="businessNoselect();">
								<c:forEach items="${snlList}" var="sl">
									<option value="${sl.businessNo}" <c:if test="${sl.id eq rstr.snlid}">selected="selected"</c:if>>${sl.businessNo}</option>
								</c:forEach>
							 
							 </select>
							<label id="update_removeno_lb"></label>
						</div>
					</div>
					<input type="hidden" name="snlid" id="snlid" value="${rstr.snlid}"/>

			

					<!-- ********************************************** -->
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">设置人:</label><input
								value="${rstr.addman}" id="addman" name="addman" /> <label
								id="update_addman_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">审核人:</label><input
								value="${rstr.auditman}" id="auditman" name="auditman" /> <label
								id="update_auditman_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">设置时间:</label><input
								type="text" id="addtimeStr" value="${rstr.addtimeStr}"
								name="addtime" class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								name="addtimeStr" /> <label id="update_addtime_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-10 col-md-offset-1">
							<label class="col-sm-4 text-right">审核时间:</label><input
								type="text" id="audittimeStr" value="${rstr.audittimeStr}"
								name="audittime" class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								name="audittimeStr" /> <label id="update_audittime_lb"></label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-10 col-md-offset-1">

							<label class="col-sm-4 text-right">备注:</label>
							<textarea id="beizhu" name="remark">${rstr.remark}</textarea>
						</div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-sm-6 text-right">
							<button class="btn btn-primary">保存</button>
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