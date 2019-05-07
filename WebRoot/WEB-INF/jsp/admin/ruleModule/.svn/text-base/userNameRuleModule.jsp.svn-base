<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>

<script type="text/javascript">
    

	$(document).ready(function(){
		
		/* $("#needtype1").click(function(){
			
			/* $("#a").show();
			$("#b").show();
			$("#c").hide();
			$("#d").hide(); 
		}); */
		
		$("#needtype2").click(function(){
			$("#a").show();
			$("#b").show();
			$("#c").show();
			$("#d").show();		
		});
				
		$("#needtype3").click(function(){
			$("#a").hide();
			$("#b").hide();
			$("#c").show();
			$("#d").hide();
		});
		
		$("#needtype4").click(function(){
			$("#a").hide();
			$("#b").hide();
			$("#c").hide();
			$("#d").show();
		});
		
		//提交保存数据
		$("#saveButton").click(function(){
			var needtype1 = $("#needtype1").val();
			var needtype2 = $("#needtype2").val();
			var needtype3 = $("#needtype3").val();
			var needtype4 = $("#needtype4").val();
			var setruleerror = $("#setruleerror").val();
			var nameruleerror = $("#nameruleerror").val();
			var presetstrerror = $("#presetstrerror").val();
			var randomlengtherror = $("#randomlengtherror").val();
			var usernamelengtherror = $("#usernamelengtherror").val();
			var isuseerror = $("#isuseerror").val();
			if((needtype1==null || needtype1=="")&&
					(needtype2==null || needtype2=="")&&
					(needtype3==null || needtype3=="")&&
					(needtype4==null || needtype4=="")){
				alert("请选择填写要求！");
				return;
			}
			if( (setruleerror!=null && setruleerror!="") ||
				(nameruleerror!=null && nameruleerror!="") ||
				(randomlengtherror!=null && randomlengtherror!="") ||
				(presetstrerror!=null && presetstrerror!="") ||
				(usernamelengtherror!=null && usernamelengtherror!="") ||
				(isuseerror!=null && isuseerror!="")	
			){
				alert("信息有误！");
				return;
			}else{
				if(!checksetrule()){
					alert("规则内容为空!");
					return ;
				}else if(!checknamerule()){
					alert("用户名规则内容!");
					return ;
				}else if(!checkpresetstr()){
					alert("请填写预设字符!");
					return ;
				}else if(!checkrandomlength()){
					alert("请选择随机数长度!");
					return ;
				}else if(!checkusernamelength()){
					alert("请填写用户名长度!");
					return ;
				}else if(!checkisuse()){
					alert("请选择是否启用!");
					return ;
				}else {
					$("#userNameRuleInfoForm").submit();
				}
			}
				
			
		});
	});
</script>
<script type="text/javascript">

	function bitian(){
		//alert(12312);
		document.getElementById("a").style.display="block";
		document.getElementById("b").style.display="block";
		document.getElementById("c").style.display="none";
		document.getElementById("d").style.display="none";
	}
	//判断规则内容
	function checksetrule() { 
		var setrule = $("#setrule").val();
		var result = false;
		if(setrule==null || setrule==''){
			$("#setruleerror").html("请选择规则内容");
			result = false;
		}else{
			$("#setruleerror").html("");
			result = true; 
		}
		return result;
	}
	
	//判断用户名内容规则
	function checknamerule() { 
		var namerule = $("#namerule").val();
		var result = false;
		if(namerule==null || namerule==''){
			$("#nameruleerror").html("请选择用户名规则内容");
			result = false;
		}else{
			$("#nameruleerror").html("");
			result = true; 
		}
		return result;
	}
	
	//判断预设字符
	function checkpresetstr(){
		var presetstr = $("#presetstr").val();
		var result = false;
		if(presetstr==null || presetstr==''){
			$("#presetstrerror").html("请填写预设字符");
			result = false;
		}else{
			$("#presetstrerror").html("");
			result = true; 
		}
		return result;
	}
	//判断随机数
	function checkrandomlength(){
		var randomlength = $("#randomlength").val();
		var result = false;
		if(randomlength==null || randomlength==''){
			$("#randomlengtherror").html("请填写随机数");
			result = false;
		}else{
			$("#randomlengtherror").html("");
			result = true; 
		}
		return result;
	}
	//用户名长度设置
	function checkusernamelength(){
		var usernamelength = $("#usernamelength").val();
		var result = false;
		if(usernamelength==null || usernamelength==''){
			$("#usernamelengtherror").html("请填写用户名长度设置");
			result = false;
		}else{
			$("#usernamelengtherror").html("");
			result = true; 
		}
		return result;
	}
	//是否启用
	function checkisuse(){
		var isuse = $("#isuse").val();
		var result = false;
		if(isuse==null || isuse==''){
			$("#isuseerror").html("请选择启用设置");
			result = false;
		}else{
			$("#isuseerror").html("");
			result = true; 
		}
		return result;
	}
	
</script>
<title>Insert title here</title>
</head>
<body style="padding: 20px">
	<div class="container" id="userNameRuleInfo">
		<div class="col-md-12 column">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名规则设置&nbsp;</label>
				</div>
				<div class="col-sm-3"></div>
				<div class="col-sm-3"></div>
			</form>
		</div>
		<div class="col-md-12 column">

			<form class="form-horizontal" role="form"
				action="updateUserNameRuleInfo.action" id="userNameRuleInfoForm"
				method="post">
				<%-- <div class="form-group">
						<label for="module" class="col-sm-2 control-label">模块:</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="module" id="module" value="${unrm.module}" maxlength="15"
								placeholder="4-15个字">
						</div>
						<div class="col-sm-3"><span id="moduleerror" style="color:red"></span></div>
					</div> --%>
				<div class="form-group">
					<label for="needtype" class="col-sm-2 control-label">填写要求:</label>
					<div class="col-sm-3">
						<input type="hidden" id="unrmId" name="unrmId" value="${unrm.id}" />
						<input type="radio" name="needtype" id="needtype1" value="1"
							${unrm.needtype == 1 ? 'checked' : ''} onclick="bitian()" />必填
						&nbsp; <input type="radio" name="needtype" id="needtype2"
							value="2" ${unrm.needtype == 2 ? 'checked' : ''} />选填&nbsp; <input
							type="radio" name="needtype" id="needtype3" value="3"
							${unrm.needtype == 3 ? 'checked' : ''} />不填 &nbsp; <input
							type="radio" name="needtype" id="needtype4" value="4"
							${unrm.needtype == 4 ? 'checked' : ''} />代填

					</div>
					<div class="col-sm-3">
						<span id="needtypeerror" style="color: red"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="setrule" class="col-sm-2 control-label">规则内容:</label>
					<div class="col-lg-3">
						<select name="setrule" id="setrule" onblur="checksetrule()">
							<option value="">--请选择--</option>
							<option id="a" value="a" ${unrm.setrule == 'a' ? 'selected' : ''}>可以用手机号相同</option>
							<option id="b" value="b" ${unrm.setrule == 'b' ? 'selected' : ''}>不能与手机号相同</option>
							<option id="c" value="c" ${unrm.setrule == 'c' ? 'selected' : ''}>系统自动同步手机号</option>
							<option id="d" value="d" ${unrm.setrule == 'd' ? 'selected' : ''}>系统按照用户名内容规则代设置</option>
						</select>
					</div>
					<div class="col-sm-3">
						<span id="setruleerror" style="color: red"></span>
					</div>
				</div>


				<div class="form-group">
					<label for="namerule" class="col-sm-2 control-label">用户名内容组成:</label>
					<div class="col-sm-3">
						<select name="namerule" id="namerule" onblur="checknamerule()">
							<option value="">--请选择--</option>
							<option value="a" ${unrm.namerule == 'a' ? 'selected' : ''}>预设汉字+4~7位随机数</option>
							<option value="b" ${unrm.namerule == 'b' ? 'selected' : ''}>4~7位随机数</option>
						</select>
					</div>
					<div class="col-sm-3">
						<span id="nameruleerror" style="color: red"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="presetstr" class="col-sm-2 control-label">预设字符:</label>

					<div class="col-lg-3">
						<input type="text" class="form-control" name="presetstr"
							id="presetstr" value="${unrm.presetstr}"
							onblur="checkpresetstr()" maxlength="30" placeholder="输入字符">
					</div>
					<div class="col-sm-3">
						<span id="presetstrerror" style="color: red"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="randomlength" class="col-sm-2 control-label">随机数位数限制:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="randomlength"
							id="randomlength" value="${unrm.randomlength}"
							onblur="checkrandomlength()" maxlength="30"
							placeholder="随机数位数4-7">
					</div>
					<div class="col-sm-3">
						<span id="randomlengtherror" style="color: red"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="usernamelength" class="col-sm-2 control-label">用户名长度:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="usernamelength"
							id="usernamelength" value="${unrm.usernamelength}"
							onblur="checkusernamelength()" maxlength="100"
							placeholder="用户名长度4-20">
					</div>
					<div class="col-sm-3">
						<span id="usernamelengtherror" style="color: red"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="isuse" class="col-sm-2 control-label">启用状态:</label>
					<div class="col-sm-3">
						<%-- <input type="text" class="form-control" name="isuse" id="isuse" value="${unrm.isuse == 1 ? '启用' : '未启用'}" onblur="checkisuse()" maxlength="100" placeholder="设置状态"> --%>
						<select name="isuse" id="isuse" onblur="checkisuse()">
							<option value="">--请选择--</option>
							<option id="weiqiyong" value="0"
								${unrm.isuse == 0 ? 'selected' : ''}>未启用</option>
							<option id="qiyong" value="1"
								${unrm.isuse == 1 ? 'selected' : ''}>启用</option>
						</select>
					</div>
					<div class="col-sm-3">
						<span id="isuseerror" style="color: red"></span>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="saveButton" type="button" class="btn btn-default">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>