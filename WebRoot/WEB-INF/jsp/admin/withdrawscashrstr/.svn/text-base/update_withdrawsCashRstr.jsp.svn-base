<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		//选择会员等级div隐藏
		//等级 radio change监听事件
		$(".update-ugrade-radio").change(function() {
			var $radioVal = $("input[name='ugrade']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 1) {
				$("#update-ugrade-checkbox-div").hide();
				//取消提示语
				$("#update-ugrade-lb").html("");
			} else {
				$("#update-ugrade-checkbox-div").show();
			}
		});
		//名单 radio change监听事件
		$(".update-rname-radio").change(function() {
			var $radioVal = $("input[name='removenameno']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 2) {
				$("#u_rname_div").show();
			} else {
				$("#u_rname_div").hide();
				//取消提示语
				$("#update-rname-lb").html("");
			}
		});
		//关闭时间 radio change监听事件
		$(".update-ctime-radio").change(function() {
			var $radioVal = $("input[name='closetimeno']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 2) {
				$("#u_ctime_div").show();
			} else {
				$("#u_ctime_div").hide();
				//取消提示语
				$("#update-ctime-lb").html("");
			}
		});
		//特定时间 radio change监听事件
		$(".update-stime-radio").change(function() {
			var $radioVal = $("input[name='specialtimeno']:checked").val();
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($radioVal == 2) {
				$("#u_stime_div").show();
			} else {
				$("#u_stime_div").hide();
				//取消提示语
				$("#update-stime-lb").html("");
			}
		});
		//单击 选择等级checkbox 取消提示语
		$(".checkbox-inline").click(function() {
			$("#update-ugrade-lb").html("");
		});
		//单笔金额范围
		$("#update-lowestmoney-text").focus(function() {
			$("#update-money-lb").html("");
		});
		$("#update-highestmoney-text").focus(function() {
			$("#update-money-lb").html("");
		});
		//日提现金额限制
		$("#update-daymoneyrest-text").focus(function() {
			$("#update-daymoneyrest-lb").html("");
		});
		//日提现次数限制
		$("#update-daytimesrest-text").focus(function() {
			$("#update-daytimesrest-lb").html("");
		});
	})
	function validateUpdateForm() {// 编辑验证

		//var $ugradeVal = $("input[name='ugrade']:checked").val();
		// 验证会员等级 当选择 选择等级radio 并没有选择具体等级checkbox return false
		//if ($ugradeVal == 2 && $("input[name='ugrades']:checked").size() == 0) {
		//	$("#update-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
		//	return false;
		//}
		// 单笔最低金额范围
		var $lowestmoney = $("#update-lowestmoney-text").val();
		if ($lowestmoney == null || $lowestmoney == "") {
			$("#update-lowestmoney-text").focus();
			$("#update-money-lb").html("<font color='red'> * 请输入金额范围</font>");
			return false;
		}
		// 单笔最高金额范围
		var $highestmoney = $("#update-highestmoney-text").val();
		if ($highestmoney == null || $highestmoney == "") {
			$("#update-highestmoney-text").focus();
			$("#update-money-lb").html("<font color='red'> * 请输入金额范围</font>");
			return false;
		}
		// 单笔最低金额必须大于最高金额
		if (parseFloat($lowestmoney) >= parseFloat($highestmoney)) {
			$("#update-highestmoney-text").focus();
			$("#update-money-lb").html(
					"<font color='red'> * 单笔最低金额必须大于最高金额</font>");
			return false;
		}
		// 日提现金额
		var $daymoneyrest = $("#update-daymoneyrest-text").val();
		if ($daymoneyrest == null || $daymoneyrest == "") {
			$("#update-daymoneyrest-text").focus();
			$("#update-daymoneyrest-lb").html(
					"<font color='red'> * 请输入日提现金额</font>");
			return false;
		}
		// 日提现次数
		var $daytimesrest = $("#update-daytimesrest-text").val();
		if ($daytimesrest == null || $daytimesrest == "") {
			$("#update-daytimesrest-text").focus();
			$("#update-daytimesrest-lb").html(
					"<font color='red'> * 请输入日提现次数</font>");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1">
				<input type="hidden" name="id" value="${wdcRstr.id}"> <input
					type="hidden" name="ugrade" value="${wdcRstr.ugrade }">
				会员等级：
			</div>
			<div>
				<label>${wdcRstr.ugradeStr }</label>
			</div>
		</div>
		<!--  
		<div class="row">
			<div class="col-md-12 col-md-offset-2">
				<div id="update-ugrade-checkbox-div" style="margin-top: 10px;">
					<input type="hidden" value="${wdcRstr.ugrade }" id="ugradeIndex">
					<c:forEach items="${ugrades}" var="item" varStatus="status">
						<label class="checkbox-inline"> <input type="checkbox"
							name="ugrades" value="${item.ugrade }"> ${item.ugradename }
						</label>
						<c:if test="${status.count%4==0 }">
							<br />
							<br />
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>-->
		<!-- 回显会员等级 
		<script type="text/javascript">
			var ugradeIndex = $("#ugradeIndex").val();
			if (ugradeIndex.length > 0) {
				var ugradeArr = ugradeIndex.split(",");
				$.each(ugradeArr, function(index, value) {
					$("#update-ugrade-checkbox-div input[type='checkbox']")
							.each(function() {
								if ($(this).val() == value) {
									$(this).attr("checked", true);
								}
							});
				});
			}
		</script>-->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				单笔金额范围&nbsp;&nbsp;&nbsp;：</div>
			<div>
				<c:set var="lowestmoney">
					<fmt:formatNumber pattern="#.##" value="${wdcRstr.lowestmoney }"/>
				</c:set>
					<c:set var="highestmoney">
					<fmt:formatNumber pattern="#.##" value="${wdcRstr.highestmoney }"/>
				</c:set>
				<input type="text"
					style="width: 85px; text-align: center; line-height: 18px;"
					value="${lowestmoney }" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" name="lowestmoney"
					id="update-lowestmoney-text">&nbsp;&nbsp;-&nbsp;&nbsp; <input
					type="text" name="highestmoney"
					style="width: 85px; text-align: center; line-height: 18px;"
					value="${highestmoney}" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" id="update-highestmoney-text">&nbsp;&nbsp;
				<label id="update-money-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				日提现金额限制&nbsp;&nbsp;：</div>
			<div>
				<c:set var="daymoneyrest">
					<fmt:formatNumber pattern="#.##" value="${wdcRstr.daymoneyrest }"/>
				</c:set>
				<input type="text"
					style="width: 85px; text-align: center; line-height: 18px;"
					value="${daymoneyrest }" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" name="daymoneyrest"
					id="update-daymoneyrest-text">&nbsp;&nbsp; <label
					id="update-daymoneyrest-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
				日提现次数限制&nbsp;&nbsp;：</div>
			<div>
				<input type="text" style="width: 85px; text-align: center;"
					value="${wdcRstr.daytimesrest }" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" name="daytimesrest"
					id="update-daytimesrest-text">&nbsp;&nbsp; <label
					id="update-daytimesrest-lb"></label>
			</div>
		</div>
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				手续费类型&nbsp;&nbsp;：&nbsp;&nbsp; <label class="radio-inline">
					<input type="radio" class="proxypay" checked="checked"
					name="proxypay" value=1> 自付
				</label> <label class="radio-inline"> <input type="radio"
					name="proxypay" class="proxypay" value=2> 代付
				</label>
			</div>
			<div class="col-md-5" id="update-proxypayman-div">
				代付账户：<select id="update-proxypayman-select" name="proxypayman">
					<option value="">--请选择账户--</option>
					<option value="MDT000001">MDT000001</option>
					<option value="SDT000001">SDT000001</option>
					<option value="SDT000002">SDT000002</option>
					<option value="BASEDT">BASEDT</option>
				</select>
			</div>
		</div>
		<hr>
		<script type="text/javascript">
			$("#update-proxypayman-div").hide();
			if ("${wdcRstr.proxypayman}" != '') {
				$("#update-proxypayman-div").show();
				$(".proxypay[value=2]").attr("checked", true);
				$("#update-proxypayman-select").val("${wdcRstr.proxypayman}");
			}
		</script>-->
		<!--  
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				是否需要审核&nbsp;&nbsp;&nbsp;&nbsp;：&nbsp;&nbsp; <label
					class="radio-inline"> <input type="radio" name="ismanaudit"
					value=1 ${wdcRstr.ismanaudit==1?"checked='checked'":"" }> 是
				</label> <label class="radio-inline"> <input type="radio"
					name="ismanaudit" value=2
					${wdcRstr.ismanaudit==1?"":"checked='checked'" }> 否
				</label> &nbsp;&nbsp;
			</div>
		</div>-->
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				是否可以取消提现&nbsp;&nbsp;： <label class="radio-inline"> <input
					type="radio" name="iscancel" value=1
					${wdcRstr.iscancel==1?"checked='checked'":"" }> 是
				</label> <label class="radio-inline"> <input type="radio"
					name="iscancel" value=2
					${wdcRstr.iscancel==1?"":"checked='checked'" }> 否
				</label>
			</div>
		</div>-->
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				名单列表：<label></label> <label class="radio-inline"> <input
					class="update-rname-radio" type="radio" name="removenameno"
					value="1"> 全部名单
				</label> <label class="radio-inline"> <input
					class="update-rname-radio" type="radio" name="removenameno"
					value="2" checked="checked"> 选择名单
				</label> <label class="radio-inline"> <input
					class="update-rname-radio" type="radio" id="rnamenoselect"
					name="removenameno" value="3">不选择名单
				</label> &nbsp;&nbsp; <label id="update-rname-lb"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2" id="u_rname_div"
				style="margin-top: 10px;">
				<input type="hidden" id="rNameVal" value="${wdcRstr.removenameno}">
				<c:forEach items="${rNames }" var="item" varStatus="status">
					<label class="checkbox-inline"> <input type="checkbox"
						name="rnamenos" value="${item.nameno }"> <font color='red'>${item.nametype}</font>&nbsp;${item.name}
					</label>
					<c:if test="${status.count%3==0 }">
						<br />
					</c:if>
				</c:forEach>
			</div>
		</div>-->
		<!--回显人员名单
		<script type="text/javascript">
			var rNameVal = $("#rNameVal").val();
			if (rNameVal.length > 0) {
				var arr = rNameVal.split(",");
				$.each(arr, function(index, value) {
					$("#u_rname_div input[type='checkbox']").each(function() {
						if ($(this).val() == value) {
							$(this).attr("checked", true);
						}
					});
				});
			} else {
				$("#u_rname_div").hide();
				$("#rnamenoselect").attr("checked", true);
			}
		</script> -->
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				关闭时间：<label></label> <label class="radio-inline"> <input
					class="update-ctime-radio" type="radio" name="closetimeno"
					value="1"> 全部时间
				</label> <label class="radio-inline"> <input
					class="update-ctime-radio" type="radio" name="closetimeno"
					value="2" checked="checked"> 选择时间
				</label> <label class="radio-inline"> <input
					class="update-ctime-radio" type="radio" name="closetimeno"
					value="3" id="ctimenoselect">不选择时间
				</label> &nbsp;&nbsp; <label id="update-ctime-lb"></label>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2" id="u_ctime_div"
				style="margin-top: 10px;">
				<input type="hidden" id="cTimeVal" value="${wdcRstr.closetimeno }">
				<c:forEach items="${cTimes }" var="item" varStatus="status">
					<label class="checkbox-inline"> <input type="checkbox"
						name="ctimenos" value="${item.timeno }"> ${item.timename}
					</label>
					<c:if test="${status.count%4==0 }">
						<br />
						<br />
					</c:if>
				</c:forEach>
			</div>
		</div>-->
		<!-- 关闭时间 回显 
		<script type="text/javascript">
			var cTimeVal = $("#cTimeVal").val();
			if (cTimeVal.length > 0) {
				var arr = cTimeVal.split(",");
				$.each(arr, function(i, val) {
					$("#u_ctime_div input[type='checkbox']").each(function() {
						if ($(this).val() == val) {
							$(this).attr("checked", true);
						}
					});
				});
			} else {
				$("#u_ctime_div").hide();
				$("#ctimenoselect").attr("checked", true);
			}
		</script>-->
		<!--  
		<hr>
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				特定时间：<label></label> <label class="radio-inline"> <input
					class="update-stime-radio" type="radio" name="specialtimeno"
					value="1"> 全部时间
				</label> <label class="radio-inline"> <input
					class="update-stime-radio" type="radio" name="specialtimeno"
					value="2" checked="checked"> 选择时间
				</label> <label class="radio-inline"> <input
					class="update-stime-radio" type="radio" name="specialtimeno"
					value="3" id="stimenoselect">不选择时间
				</label> &nbsp;&nbsp; <label id="update-stime-lb"></label>

			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-2" id="u_stime_div"
				style="margin-top: 10px;">
				<input type="hidden" id="sTimeVal" value="${wdcRstr.specialtimeno }">
				<c:forEach items="${sTimes }" var="item" varStatus="status">
					<label class="checkbox-inline"> <input type="checkbox"
						name="stimenos" value="${item.timeno }"> <font color='red'>${item.timetype}</font>&nbsp;${item.timename}
					</label>
					<c:if test="${status.count%4==0 }">
						<br />
						<br />
					</c:if>
				</c:forEach>
			</div>
		</div>-->
		<!-- 特定时间 
		<script type="text/javascript">
			var sTimeVal = $("#sTimeVal").val();
			if (sTimeVal.length > 0) {
				var arr = sTimeVal.split(",");
				$.each(arr, function(i, val) {
					$("#u_stime_div input[type='checkbox']").each(function() {
						if ($(this).val() == val) {
							$(this).attr("checked", true);
						}
					});
				});
			} else {
				$("#u_stime_div").hide();
				$("#stimenoselect").attr("checked", true);
			}
		</script>-->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注&nbsp;&nbsp;：</div>
			<div class="row" style="line-height: 0px; margin-top: 12px;">
				<div class="col-md-10 col-md-offset-1">
					<textarea placeholder="请输入备注：" class="form-control" name="remark"
						id="update-remark-text">${wdcRstr.remark }</textarea>
				</div>
			</div>
		</div>
	</form>
</body>
</html>