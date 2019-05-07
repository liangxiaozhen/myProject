<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	var setRule = "${unrm.setrule}";
	var nameRule = "${unrm.namerule}";
	var presetStr = "${unrm.presetstr}";
	var randomLength = "${unrm.randomlength}";
	var userNameLength = "${unrm.usernamelength}";
	var userNameMaxLength = "${unrm.usernamemaxlength}";
	var registerType = "${unrm.registertype}";
	var isChinese = "${unrm.ischinese}";
	var isuse="${unrm.isuse}";
	$(function() {
		$("#update-setrule-select").val(setRule);
		$("#update-isCustom-select").val("${unrm.iscustom}");
		$("input[name='isuse'][value="+isuse+"]").attr("checked",true);
		//用户名长度获取焦点 lb提示取消
		$("#update-userNameLength-text").change(function() {
			$("#update-userNameLength-lb").html("");
		});
		//预设前缀字符获取焦点 lb取消
		$("#update-presetStr-text").change(function() {
			$("#update-presetStr-lb").html("");
		});
		//随机位数获取焦点 lb取消
		$("#update-randomLength-text").change(function() {
			$("#update-randomLength-lb").html("");
		});
		//是否允许中文
		$("#update-isChinese-select").change(function() {
			$("#update-isChinese-lb").html("");
		});
		//用户名默认规则
		$("#update-namerule-select").change(function() {
			var value = $(this).val();
			//label
			$("#update-namerule-lb").html("");
			$("#update-presetStr-lb").html("");
			$("#update-randomLength-lb").html("");
			//DIV
			$("#randomLength-div").hide();
			$("#update-randomLength-text").val("");
			$("#update-presetStr-text").val("");
			$("#presetStr-div").hide();
			$("#userNameLength-div").hide();
			$("#update-userNameLength-text").val("");
			if (value == "a") {
				$("#presetStr-div").show();
				$("#randomLength-div").show();
			}
			if (value == "b") {
				$("#randomLength-div").show();
			}
		});
		//设置规则
		$("#update-setrule-select").change(function() {
			var value = $(this).val();
			//label
			$("#update-setrule-lb").html("");
			$("#update-userNameLength-lb").html("");
			$("#update-namerule-lb").html("");
			$("#update-isChinese-lb").html("");
			//用户名规则+长度
			$("#userNameLength-div").hide();
			$("#update-userNameLength-text").val("");
			$("#update-userNameMaxLength-text").val("");
			//是否允许中文
			$("#isChinese-div").hide();
			$("#update-isChinese-select").val("");
			//用户名默认规则
			$("#nameRule-div").hide();
			$("#update-namerule-select").val("");
			//预设字符+随机数
			ranDomPresetStrHide();
			if (value == "a" || value == "b") {
				$("#userNameLength-div").show();
				$("#isChinese-div").show();
			}
			if (value == "d") {
				$("#nameRule-div").show();
			}
		});
		$("#update-isCustom-select").change(function() {
			$("#update-isCustom-lb").html("");
		});
		//预设字符+随机数 text 隐藏
		function ranDomPresetStrHide() {
			$("#randomLength-div").hide();
			$("#update-randomLength-text").val("");
			$("#presetStr-div").hide();
			$("#update-presetStr-text").val("");
		}
	});
	//编辑修改验证
	function validateUpdate() {
		//设置规则
		if ($("#update-setrule-select").val() == "") {
			$("#update-setrule-lb").html("*必须选择设置规则");
			return false;
		}
		var setRule = $("#update-setrule-select").val();
		if (setRule == "a" || setRule == "b") {
			if ($("#update-isChinese-select").val() == "") {
				$("#update-isChinese-lb").html("*必须选择是否允许中文");
				return false;
			}
			if ($("#update-userNameLength-text").val() == "") {
				$("#update-userNameLength-lb").html("*必须填写用户名最小长度");
				return false;
			}
			if ($("#update-userNameMaxLength-text").val() == "") {
				$("#update-userNameLength-lb").html("*必须填写用户名最大长度");
				return false;
			}
			if (parseInt($("#update-userNameLength-text").val()) >= parseInt($(
					"#update-userNameMaxLength-text").val())) {
				$("#update-userNameLength-lb").html("*用户名最大长度必须大于最小长度");
				return false;
			}
		}
		var update_nameRule = $("#update-namerule-select").val();
		//预设字符+N位随机数
		if (setRule == "d") {
			var loginNameReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
			if (update_nameRule == "") {
				$("#update-namerule-lb").html("*请选择用户名默认规则");
				return false;
			}
			if (update_nameRule == "a") {
				if ($("#update-presetStr-text").val() == "") {
					$("#update-presetStr-lb").html("*必须填写预设前缀字符");
					return false;
				}
			}
			if (!loginNameReg.test($("#update-presetStr-text").val().trim())) {
				$("#update-presetStr-lb").html("*用户名包含特殊字符");
				return;
			}
			//N位随机数
			if ($("#update-randomLength-text").val() == "") {
				$("#update-randomLength-lb").html("*必须填写随机位数长度");
				return false;
			}
			if (parseInt($("#update-randomLength-text").val()) < 4
					|| parseInt($("#update-randomLength-text").val()) > 7) {
				$("#update-randomLength-lb").html("*随机数长度限制为4-7");
				return false;
			}
		}
		var registerType = "${unrm.registertype}";
		if (parseInt(registerType) == 5) {
			if ($("#update-isCustom-select").val() == "") {
				$("#update-isCustom-lb").html("*请选择用户名是否自定义");
				return false;
			}
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form" method="post">
		<input type="hidden" value="${unrm.id }" name="id"> <input
			type="hidden" value="${unrm.registertype}" name="registertype">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>用户名规则编辑</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-11 col-md-offset-1">
				注册规则： <label>${unrm.registerStr }</label>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-11 col-md-offset-1">
				是否启用：<input type="radio" name="isuse" value=1>启用  &nbsp;
				<input type="radio" name="isuse" value=0>停用
			</div>
		</div>
		<div id="setRule-div">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					设置规则：
					<c:if test="${unrm.registertype eq 5 }">
						<select name="setrule" id="update-setrule-select">
							<option value="d">用户名代填，系统按照规则代设置</option>
						</select>
					</c:if>
					<c:if test="${unrm.registertype != 5 }">
						<select name="setrule" id="update-setrule-select">
							<c:choose>
								<c:when
									test="${unrm.registertype eq 4 or unrm.registertype eq 3}">
									<option value="">--请选择规则--</option>
									<option value="a">用户名自填，可以与手机号相同</option>
									<option value="b">用户名自填，不能与手机号相同</option>
									<option value="c">用户名代填，系统自动同步手机号</option>
									<option value="d">用户名代填，系统按照规则代设置</option>
								</c:when>
								<c:otherwise>
									<option value="">--请选择规则--</option>
									<option value="a">用户名自填，可以与手机号相同</option>
									<option value="b">用户名自填，不能与手机号相同</option>
								</c:otherwise>
							</c:choose>
						</select>
					</c:if>
					&nbsp;&nbsp;&nbsp;<span style="color: red"><label
						id="update-setrule-lb"></label></span>
				</div>
			</div>
		</div>
		<div id="nameRule-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					用户名默认规则：<select id="update-namerule-select" name="namerule">
						<option value="">--请选择规则--</option>
						<option value="a">预设字符+N位随机数</option>
						<option value="b">N位随机数</option>
					</select> &nbsp;&nbsp;&nbsp;<span style="color: red"><label
						id="update-namerule-lb"></label></span>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			if (registerType == 3 || registerType == 4 || registerType == 5) {
				if (nameRule != "") {
					$("#nameRule-div").show();
					$("#update-namerule-select").val(nameRule);
				}
			}
		</script>
		<div id="presetStr-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					预设前缀字符：<input type="text" id="update-presetStr-text"
						name="presetstr"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="填写预设字符">&nbsp;&nbsp;&nbsp;<span
						style="color: red"><label id="update-presetStr-lb"></label></span>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			if (nameRule == "a") {
				$("#presetStr-div").show();
				$("#update-presetStr-text").val(presetStr);
			}
		</script>
		<div id="randomLength-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					随机位数长度：<input type="text" id="update-randomLength-text"
						name="randomlength" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="填写随机位数长度">&nbsp;&nbsp;&nbsp;<span
						style="color: red"><label id="update-randomLength-lb"></label></span>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			if (nameRule != "") {
				$("#randomLength-div").show();
				$("#update-randomLength-text").val(randomLength);
			}
		</script>
		<div id="isChinese-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					用户名中文：<select id="update-isChinese-select" name="ischinese">
						<option value="">--请选择--</option>
						<option value="1">允许</option>
						<option value="2">不允许</option>
					</select> <span style="color: red"><label id="update-isChinese-lb"></label></span>
					<div style="margin-top: 15px;">
						<span style="color: red">*注：修改后，网站前端页面需要做对应的修改。</span>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			if (isChinese != '') {
				$("#isChinese-div").show();
				$("#update-isChinese-select").val(isChinese);
			}
		</script>
		<div id="userNameLength-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					用户名字数限制：<input type="text" id="update-userNameLength-text"
						name="usernamelength" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="用户名最小长度"> - <input type="text"
						id="update-userNameMaxLength-text" name="usernamemaxlength"
						onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="用户名最大长度">&nbsp;&nbsp;&nbsp; <span
						style="color: red"><label id="update-userNameLength-lb"></label></span>
					<div style="margin-top: 15px;">
						<span style="color: red">*注：修改后，网站前端页面需要做对应的修改。</span>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			if (setRule == "a" || setRule == "b") {
				$("#userNameLength-div").show();
				$("#update-userNameLength-text").val(userNameLength);
				$("#update-userNameMaxLength-text").val(userNameMaxLength);
			}
		</script>
		<c:if test="${unrm.registertype eq 5 }">
			<div id="isCustom-div">
				<hr>
				<div class="row" style="line-height: 0px;">
					<div class="col-md-11 col-md-offset-1">
						用户名自定义：<select name="iscustom" id="update-isCustom-select">
							<option value="">---请选择---</option>
							<option value="1">允许自定义</option>
							<option value="2">不允许自定义</option>
						</select>&nbsp;&nbsp;&nbsp; <span style="color: red"><label
							id="update-isCustom-lb"></label></span>
					</div>
				</div>
			</div>
		</c:if>
	</form>
</body>
</html>