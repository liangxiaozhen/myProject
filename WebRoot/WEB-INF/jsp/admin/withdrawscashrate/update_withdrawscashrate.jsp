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
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {

		//选择会员等级div隐藏
		//$("#update-ugrade-checkbox-div").hide();

		//等级 radio change监听事件
		$(".update-ugrade-radio").change(function() {
			//当选中全部等级时 选择等级div隐藏，反之显示
			if ($("input[name='ugrade']:checked").val() == 1) {
				$("#update-ugrade-checkbox-div").hide();
				//取消提示语
				$("#update-ugrade-lb").html("");
			} else {
				$("#update-ugrade-checkbox-div").show();
			}
		})
		//单击 选择等级checkbox 取消提示语
		$(".checkbox-inline").click(function() {
			$("#update-ugrade-lb").html("");
		});

		//比例 div 隐藏
		$("#update-rate-div").hide();
		//收费类型 select change监听事件
		$("#update-wdcmode-select").change(function() {
			if ($(this).val() == 1) {
				//隐藏比例div 并清空文本值
				$("#update-rate-div").hide();
				$("#update-quota-div").show();
				$("#update-rate-div input").val("");
			} else {
				//隐藏定额div 并清空文本值
				$("#update-quota-div").hide();
				$("#update-rate-div").show();
				$("#update-quota-div input").val("");
			}
		});
		var $wdcmodeVal = "${wdcRate.wdcmode}";
		$("#update-wdcmode-select").val($wdcmodeVal);
		if ($wdcmodeVal == 1) {
		} else {
			$("#update-quota-div").hide();
			$("#update-rate-div").show();
		}

		//最小金额text输入 取消提示语
		$("#update-minmoney-text").change(function() {
			$("#update-money-lb").html("");
		});
		//最小金额text输入 取消提示语
		$("#update-maxmoney-text").change(function() {
			$("#update-money-lb").html("");
		});
		//定额 输入  取消提示语
		$("#update-quota-text").change(function() {
			$("#update-quota-lb").html("");
		});
		//提现费率 输入 取消提示语
		$("#update-wdcrate-text").change(function() {
			$("#update-wdcrate-lb").html("");
		});
		//最低收费金额 取消提示语
		$("#update-minfee-text").change(function() {
			$("#update-minfee-lb").html("");
			$("#update-maxfee-lb").html("");
		});
		//最高收费金额 取消提示语
		$("#update-maxfee-text").change(function() {
			$("#update-maxfee-lb").html("");
			$("#update-minfee-lb").html("");
		});
	})
	function validateUpdateForm() {// 验证表单数据
		/*
		 * var $ugradeVal = $("input[name='ugrade']:checked").val(); //验证会员等级 当选择
		 * 选择等级radio 并没有选择具体等级checkbox return false if ($ugradeVal == 2 &&
		 * $("input[name='ugrades']:checked").size() == 0) {
		 * $("#update-ugrade-lb").html("<font color='red'> * 请选择会员等级</font>");
		 * return false; }
		 */
		// 验证最小金额
		var $minmoney = $("#update-minmoney-text").val();
		if ($minmoney == "" || $minmoney == null) {
			$("#update-minmoney-text").focus();
			$("#update-money-lb").html("<font color='red'> * 请输入最小金额</font>");
			return false;
		}
		var $maxmoney = $("#update-maxmoney-text").val();
		if (parseFloat($maxmoney) <= parseFloat($minmoney) && $maxmoney != ""
				&& $maxmoney != null) {
			$("#update-maxmoney-text").focus();
			$("#update-money-lb").html(
					"<font color='red'> * 最大金额必须大于最小金额</font>");
			return false;
		}
		// 手续费类型
		var $wdcmode = $("#update-wdcmode-select").val();
		if ($wdcmode == 1) {
			// 验证固定金额
			var $quota = $("#update-quota-text").val();
			if ($quota == "" || $quota == null) {
				$("#update-quota-text").focus();
				$("#update-quota-lb").html(
						"<font color='red'> * 请输入固定金额</font>");
				return false;
			}
		}
		if ($wdcmode == 2) {
			// 验证提现费率
			var $wdcrate = $("#update-wdcrate-text").val();
			if ($wdcrate == "" || $wdcrate == null) {
				$("#update-wdcrate-lb").html(
						"<font color='red'> * 请输入提现费率</font>");
				return false;
			}
			if (parseFloat($wdcrate) < 0 || parseFloat($wdcrate) > 100) {
				$("#update-wdcrate-text").focus();
				$("#update-wdcrate-lb").html(
						"<font color='red'> * 提现费率正确范围 0~100</font>");
				return false;
			}	
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-rate-form" method="post"
		action="${pageContext.request.contextPath}/withdrawscashrate/update.action">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>基本</b></font>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<input type="hidden" name="id" value="${wdcRate.id }"> <input
					type="hidden" name="ugrade" value="${wdcRate.ugrade }">
				会员等级：
				<!--  
				<label class="radio-inline"> <input
					class="update-ugrade-radio" type="radio" name="ugrade" value="1">
					全部等级
				</label> <label class="radio-inline"> <input
					class="update-ugrade-radio" type="radio" name="ugrade" value="2"
					checked="checked"> 选择等级
				</label> &nbsp;&nbsp; <label id="update-ugrade-lb"></label>-->
			</div>
			<div>
				<label>${wdcRate.ugradeStr}</label>
			</div>
		</div>
		<!--  
		<div class="row">
			<div class="col-md-12 col-md-offset-2">
				<div id="update-ugrade-checkbox-div"
					style="padding-left: 24px; padding-top: 12px;">
					<input type="hidden" value="${wdcRate.ugrade }" id="ugradeIndex">
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
					$("#update-ugrade-checkbox-div input[type='checkbox']").each(function() {
								if ($(this).val() == value) {
									$(this).attr("checked", true);
								}
							});
				});
			}
		</script> -->
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px">
				金额范围：</div>
			<div>
				<c:set var="minmoney">
					<fmt:formatNumber pattern="#.##" value="${wdcRate.minmoney }" />
				</c:set>
				<c:set var="maxmoney">
					<fmt:formatNumber pattern="#.##" value="${wdcRate.maxmoney }" />
				</c:set>
				<input type="text"
					style="width: 100px; text-align: center; line-height: 18px"
					name="minmoney" id="update-minmoney-text" placeholder="请填写金额"
					value="${minmoney}">元&nbsp;&nbsp;&nbsp;- &nbsp;&nbsp;&nbsp;<input
					style="width: 100px; text-align: center; line-height: 18px"
					type="text" id="update-maxmoney-text" name="maxmoney"
					placeholder="请填写金额" onblur="checkNum(this)"
					onkeyup="clearNoNum(event,this)" value="${maxmoney }">
				元&nbsp;&nbsp; <label id="update-money-lb"></label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>类型</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-3 col-md-offset-1" style="line-height: 18px">
				手续费类型：</div>
			<div>
				<select name="wdcmode" id="update-wdcmode-select">
					<option value="1">定额收费</option>
					<option value="2">比例收费</option>
				</select>
			</div>
		</div>
		<!-- 定额 -->
		<div id="update-quota-div">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px;">
					固定金额&nbsp;&nbsp;&nbsp;：</div>
				<div>
					<c:set var="quota">
						<fmt:formatNumber pattern="#.##" value="${wdcRate.quota }" />
					</c:set>
					<input class="rate-inputtext" name="quota" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)" type="text"
						style="width: 100px; text-align: center; line-height: 18px"
						placeholder="请填写固定金额" id="update-quota-text" value="${quota}">
					元&nbsp;&nbsp; <label id="update-quota-lb"></label>
				</div>
			</div>
		</div>
		<!-- 比例 -->
		<div id="update-rate-div">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px">
					提现费率&nbsp;&nbsp;&nbsp;：</div>
				<div>
					<c:set var="wdcrate">
						<fmt:formatNumber pattern="#.##" value="${wdcRate.wdcrate }" />
					</c:set>
					<input onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
						class="rate-inputtext" onkeypress="keyPress()" type="text"
						style="width: 100px; text-align: center; line-height: 18px"
						placeholder="请填写提现费率" name="wdcrate" id="update-wdcrate-text"
						value="${wdcrate }"> % &nbsp;&nbsp; <label
						id="update-wdcrate-lb"></label>
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px">
					最低收费金额：</div>
				<div>
					<c:set var="minfee">
						<fmt:formatNumber pattern="#.##" value="${wdcRate.minfee }" />
					</c:set>
					<input type="text" class="rate-inputtext" name="minfee"
						style="width: 100px; text-align: center; line-height: 18px;"
						onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
						placeholder="请填写金额" id="update-minfee-text" value="${minfee }">
					元&nbsp;<label id="update-minfee-lb"></label>
				</div>
			</div>
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-3 col-md-offset-1" style="line-height: 18px">
					最高收费金额：</div>
				<div>
					<c:set var="maxfee">
						<fmt:formatNumber pattern="#.##" value="${wdcRate.maxfee }" />
					</c:set>
					<input type="text" class="rate-inputtext" name="maxfee"
						style="width: 100px; text-align: center; line-height: 18px;"
						onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
						placeholder="请填写金额" id="update-maxfee-text" value="${maxfee }">
					元&nbsp;<label id="update-maxfee-lb"></label>
				</div>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea class="form-control" name="remark" id="remark-text">${wdcRate.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>