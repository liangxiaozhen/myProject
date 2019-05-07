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
	$(function() {
		//用户名长度获取焦点 lb提示取消
		$("#insert-userNameLength-text").change(function() {
			$("#insert-userNameLength-lb").html("");
		});
		//预设前缀字符获取焦点 lb取消
		$("#insert-presetStr-text").change(function() {
			$("#insert-presetStr-lb").html("");
		});
		//随机位数获取焦点 lb取消
		$("#insert-randomLength-text").change(function() {
			$("#insert-randomLength-lb").html("");
		});
		//用户名默认规则
		$("#insert-namerule-select").change(function() {
			var value = $(this).val();
			//label
			$("#insert-namerule-lb").html("");
			$("#insert-presetStr-lb").html("");
			$("#insert-randomLength-lb").html("");
			//
			$("#randomLength-div").hide();
			$("#insert-randomLength-text").val("");
			$("#insert-presetStr-text").val("");
			$("#presetStr-div").hide();
			$("#userNameLength-div").hide();
			$("#insert-userNameLength-text").val("");
			if (value == "a") {
				$("#presetStr-div").show();
				$("#randomLength-div").show();
			}
			if (value == "b") {
				$("#randomLength-div").show();
			}
		});
		//设置规则
		$("#insert-setrule-select").change(function() {
			var value = $(this).val();
			//label
			$("#insert-setrule-lb").html("");
			$("#insert-userNameLength-lb").html("");
			$("#insert-namerule-lb").html("");
			//用户名规则+长度
			$("#userNameLength-div").hide();
			$("#insert-userNameLength-text").val("");
			$("#nameRule-div").hide();
			$("#insert-namerule-select").val("");
			//预设字符+随机数
			ranDomPresetStrHide();
			var needType = $("input[name='needtype']:checked").val();
			if (value != '') {
				if (needType == 2 && value == "b") {
					$("#nameRule-div").show();
				}
				if (needType == 1) {
					$("#userNameLength-div").show();
				}
			}
		});
		//注册规则
		$("#insert-registertype-select").change(function() {
			var value = $(this).val();
			//label
			$("#insert-setrule-lb").html("");
			$("#insert-userNameLength-lb").html("");
			$("#insert-namerule-lb").html("");
			$("#insert-registertype-lb").html("");
			//用户名规则+长度
			$("#userNameLength-div").hide();
			$("#insert-userNameLength-text").val("");
			$("#nameRule-div").hide();
			$("#insert-namerule-select").val("");
			$("#setRule-div").hide();
			$("#insert-setrule-select").val("");
			//预设字符+随机数
			ranDomPresetStrHide();
			if (value != '') {
				$("#setRule-div").show();
			}
		});
		//是否填写
		$("input[name='needtype']").change(
				function() {
					var needType = document
							.getElementById("insert-setrule-select");
					var registertype = document
							.getElementById("insert-registertype-select");
					needType.options.length = 0;
					registertype.options.length = 0;
					//label
					$("#insert-setrule-lb").html("");
					$("#insert-namerule-lb").html("");
					$("#insert-registertype-lb").html("");
					//用户名规则+长度
					$("#userNameLength-div").hide();
					$("#nameRule-div").hide();
					$("#setRule-div").hide();
					$("#insert-namerule-select").val("");
					//预设字符+随机数
					ranDomPresetStrHide();
					var value = $(this).val();
					if (value == 1) {
						needType.options[0] = new Option("--请选择规则--", "");
						needType.options[1] = new Option("可以与手机号相同", "a");
						needType.options[2] = new Option("不能与手机号相同", "b");
						//注册规则
						registertype.options[0] = new Option("--请选择规则--", "");
						registertype.options[1] = new Option("用户名", "1");
						registertype.options[2] = new Option("用户名+手机号", "2");
					} else if (value == 2) {
						needType.options[0] = new Option("--请选择规则--", "");
						needType.options[1] = new Option("系统自动同步手机号", "a");
						needType.options[2] = new Option("系统按照规则代设置", "b");
						//注册规则
						registertype.options[0] = new Option("--请选择规则--", "");
						registertype.options[1] = new Option("手机号", "3");
					}
				});

	})
	//预设字符+随机数 text 隐藏
	function ranDomPresetStrHide() {
		$("#randomLength-div").hide();
		$("#insert-randomLength-text").val("");
		$("#presetStr-div").hide();
		$("#insert-presetStr-text").val("");
	}
	//新增验证
	function validateInsert() {
		var registertype = $("#insert-registertype-select").val();
		if (registertype == "") {
			$("#insert-registertype-lb").html("*必须选择注册规则");
			return false;
		}
		//设置规则
		var needType = $("input[name='needtype']:checked").val();
		if ($("#insert-setrule-select").val() == "") {
			$("#insert-setrule-lb").html("*必须选择设置规则");
			return false;
		}
		//填写
		if (needType == 1) {
			if ($("#insert-userNameLength-text").val() == "") {
				$("#insert-userNameLength-lb").html("*必须填写用户名长度");
				return false;
			}
			if (parseInt($("#insert-userNameLength-text").val()) > 20
					|| parseInt($("#insert-userNameLength-text").val()) < 4) {
				$("#insert-userNameLength-lb").html("*用户名长度限制为4-20");
				return false;
			}
		}
		//不填 按系统代设置规则
		var setRule = $("#insert-setrule-select").val()
		if (needType == 2 && setRule == "b") {
			//用户名默认选择
			var nameRule = $("#insert-namerule-select").val();
			if (nameRule == "") {
				$("#insert-namerule-lb").html("*必须选择用户名默认规则");
				return false;
			}
			//预设字符+N位随机数
			if (nameRule == "a") {
				var loginNameReg = /^[^@\/\'\\\"#$%&\^\*]+$/;
				if ($("#insert-presetStr-text").val() == "") {
					$("#insert-presetStr-lb").html("*必须填写预设前缀字符");
					return false;
				}
				if (!loginNameReg
						.test($("#insert-presetStr-text").val().trim())) {
					$("#insert-presetStr-lb").html("*用户名包含特殊字符");
					return;
				}
				if ($("#insert-randomLength-text").val() == "") {
					$("#insert-randomLength-lb").html("*必须填写随机位数长度");
					return false;
				}
				if (parseInt($("#insert-randomLength-text").val()) < 4
						|| parseInt($("#insert-randomLength-text").val()) > 7) {
					$("#insert-randomLength-lb").html("*随机数长度限制为4-7");
					return false;
				}
			}
			//N位随机数
			if (nameRule == "b") {
				if ($("#insert-randomLength-text").val() == "") {
					$("#insert-randomLength-lb").html("*必须填写随机位数长度");
					return false;
				}
				if (parseInt($("#insert-randomLength-text").val()) < 4
						|| parseInt($("#insert-randomLength-text").val()) > 7) {
					$("#insert-randomLength-lb").html("*随机数长度限制为4-7");
					return false;
				}
			}
		}
		return true;
	}
</script>
</head>
<body>
	<form id="insert-form" method="post">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>用户名规则设置</b></font>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-11 col-md-offset-1">
				是否填写：<input type="radio" name="needtype" value="1" checked="checked">必填&nbsp;&nbsp;&nbsp;
				<input type="radio" name="needtype" value="2">不填
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-11 col-md-offset-1">
				注册规则： <select name="registertype" id="insert-registertype-select">
					<option value="">--请选择规则--</option>
					<option value="1">用户名</option>
					<option value="2">用户名+手机号</option>
				</select>&nbsp;&nbsp;&nbsp;<span style="color: red"><label
					id="insert-registertype-lb"></label></span>
			</div>
		</div>
		<div id="setRule-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					设置规则： <select name="setrule" id="insert-setrule-select">
						<option value="">--请选择规则--</option>
						<option value="a">可以与手机号相同</option>
						<option value="b">不能与手机号相同</option>
					</select>&nbsp;&nbsp;&nbsp;<span style="color: red"><label
						id="insert-setrule-lb"></label></span>
				</div>
			</div>
		</div>
		<div id="nameRule-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					用户名默认规则：<select id="insert-namerule-select" name="namerule">
						<option value="">--请选择规则--</option>
						<option value="a">预设字符+N位随机数</option>
						<option value="b">N位随机数</option>
					</select> &nbsp;&nbsp;&nbsp;<span style="color: red"><label
						id="insert-namerule-lb"></label></span>
				</div>
			</div>
		</div>
		<div id="presetStr-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					预设前缀字符：<input type="text" id="insert-presetStr-text"
						name="presetstr"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="填写预设字符">&nbsp;&nbsp;&nbsp;<span
						style="color: red"><label id="insert-presetStr-lb"></label></span>
				</div>
			</div>
		</div>
		<div id="randomLength-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					随机位数长度：<input type="text" id="insert-randomLength-text"
						name="randomlength" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="填写随机位数长度">&nbsp;&nbsp;&nbsp;<span
						style="color: red"><label id="insert-randomLength-lb"></label></span>
				</div>
			</div>
		</div>
		<div id="userNameLength-div" style="display: none">
			<hr>
			<div class="row" style="line-height: 0px;">
				<div class="col-md-11 col-md-offset-1">
					用户名长度：<input type="text" id="insert-userNameLength-text"
						name="usernamelength" onblur="checkNum(this)"
						onkeyup="clearNoNum(event,this)"
						style="text-align: center; width: 100px; line-height: 18px;"
						placeholder="填写用户名长度">&nbsp;&nbsp;&nbsp; <span
						style="color: red"><label id="insert-userNameLength-lb"></label></span>
				</div>
			</div>
		</div>
	</form>
</body>
</html>