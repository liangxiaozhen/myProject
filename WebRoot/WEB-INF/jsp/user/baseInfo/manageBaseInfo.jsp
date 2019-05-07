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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>
<!-- 省市区/县 -->
<script type="text/javascript" src="<%=basePath%>js/pdata.js"></script>
<!-- 日历 -->
<script type="text/javascript"
	src="<%=basePath%>calendar/WdatePicker.js"></script>

<script type="text/javascript">
$(function(){
	
	//-----------------------------------------------------------------
	
	
	
	//昵称修改
	$("#loginNameSubmit").click(function(){
		var newLoginName = $("#newLoginName").val();
		if(newLoginName==null || newLoginName==''){
			alert("新昵称不能为空!!");
			return ;
		}else{
			$('#updateLoginName').submit();
		}
	});
	//手机号修改
	$("#mobileSubmit").click(function(){
		var newMobilephone = $("#newMobilephone").val();
		var moblieVerifyCode = $("#moblieVerifyCode").val();
					
		if(newMobilephone=='' || newMobilephone==null || moblieVerifyCode=='' || moblieVerifyCode==null){
			alert("新手机号且验证码不能为空!!");
			return ;
		}else{
			$("#updateMobilePhone").submit();
		}
	});
});

	//发短信
	function SMSVerifyCode(){
		timer(30);
		var action = "smsSend.action";
		var updateId = $("#updateId").val();
		var mobilephone = ${ubai.mobilephone};
		
		var params = {
			"updateId" : updateId,
			"mobilephone" : mobilephone
		};
		$.post(action, params);
	}
	//定时30秒禁止重发
	function timer(time) {
		var btn = $("#sendsmsID");
		btn.attr("disabled", true); //按钮禁止点击
		btn.html((time <= 0) ? "发送短信验证码" : ("" + (time) + "秒后可再发送"));
		var hander = setInterval(function() {
			if (time <= 0) {
				clearInterval(hander); //清除倒计时
				//document.getElementById("sendsmsID").style.background="";
				btn.html("发送短信验证码");
				btn.attr("disabled", false);
				return false;
			} else {
				btn.html("" + (time--) + "秒后可再发送");
			}
		}, 1000);
	}


</script>

<script type="text/javascript">
	$(function () {
		var html = "<option value=''>-- 请选择 --</option>"; $("#addressCity").append(html); $("#addressDistrict").append(html);
		$.each(pdata,function(idx,item){
			if (parseInt(item.level) == 0) {
   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
   			}
		});
		$("#addressProvince").append(html);

		$("#addressProvince").change(function(){
			if ($(this).val() == "") return;
			$("#addressCity option").remove(); $("#addressDistrict option").remove();
			var code = $(this).find("option:selected").attr("exid"); code = code.substring(0,2);
			var html = "<option value=''>-- 请选择--</option>"; $("#addressDistrict").append(html);
			$.each(pdata,function(idx,item){
				if (parseInt(item.level) == 1 && code == item.code.substring(0,2)) {
	   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
	   			}
			});
			$("#addressCity").append(html);		
		});

		$("#addressCity").change(function(){
			if ($(this).val() == "") return;
			$("#addressDistrict option").remove();
			var code = $(this).find("option:selected").attr("exid"); code = code.substring(0,4);
			var html = "<option value=''>-- 请选择 --</option>";
			$.each(pdata,function(idx,item){
				if (parseInt(item.level) == 2 && code == item.code.substring(0,4)) {
	   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
	   			}
			});
			$("#addressDistrict").append(html);		
		});
		//绑定
		//$("#addressProvince").val("广东省");$("#addressProvince").change();
		//$("#addressCity").val("深圳市");$("#addressCity").change();
		//$("#addressDistrict").val("罗湖区");

	});
</script>

<style type="text/css">
label {
	width: 100px;
}
</style>

<title>${manageBaseInfo}</title>
</head>
<body style="padding: 20px">
	<div class="container" id="baseInfo">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab"> 用户基本信息
			</a></li>
			<li><a href="#image" data-toggle="tab">用户头像设置</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="col-md-12 column tab-pane fade in active" id="home">

				<table class="table">
					<caption></caption>
					<tbody>
						<tr height="60px">

							<td>用户头像</td>
							<td><img src="<%=rootPath%>pic/${ubai.imagepath}"
								width="90px" height="90px"></td>
							<td><c:if test="${ubai.imagepath==null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#imageModal">立即填写</button> -->
								</c:if> <c:if test="${ubai.imagepath!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#imageModal">修改</button> -->
								</c:if></td>
						</tr>
						<tr height="60px">

							<td>用户类型</td>
							<td><c:choose>
									<c:when test="${ubai.accounttype==1}">
							个人用户
						</c:when>
									<c:otherwise>
							企业用户
						</c:otherwise>
								</c:choose></td>
							<td></td>
						</tr>
						<tr height="60px">

							<td>用户昵称</td>
							<td>${ubai.loginname}</td>
							<td><c:if test="${ubai.loginname==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#myModal">立即填写</button>
								</c:if> <c:if test="${ubai.loginname!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#myModal"></button> -->
								</c:if></td>
						</tr>

						<tr height="60px">

							<td>用户手机</td>
							<td>${ubai.mobilephone}</td>
							<td><c:if test="${ubai.mobilephone==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#mobileModal">立即填写</button>
								</c:if> <c:if test="${ubai.mobilephone!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#mobileModal">修改</button> -->
								</c:if></td>
						</tr>
						<tr height="60px">

							<td>用户邮件</td>
							<td>${ubai.email}</td>
							<td><c:if test="${ubai.email==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#emailModal">立即填写</button>
								</c:if> <c:if test="${ubai.email!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#emailModal">修改</button> -->
								</c:if></td>
						</tr>
						<tr height="60px">

							<td>用户姓名</td>
							<td>${ubai.realname}</td>
							<td><c:if test="${ubai.realname==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#realNameModal">立即填写</button>
								</c:if> <c:if test="${ubai.realname!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#realNameModal">查看</button> -->
								</c:if></td>
						</tr>
						<tr height="60px">

							<td>证件类型</td>
							<td><c:if test="${ubai.certificationtype==1}">
								身份证
								</c:if> <c:if test="${ubai.certificationtype==2}">
								营业执照
								</c:if></td>
							<td><c:if test="${ubai.certificationtype==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#realNameModal">立即填写</button>
								</c:if> <c:if test="${ubai.certificationtype!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#realNameModal">查看</button> -->
								</c:if></td>
						</tr>
						<tr height="60px">

							<td>证件号码</td>
							<td>${ubai.certificationnumber}</td>
							<td><c:if test="${ubai.certificationnumber==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#realNameModal">立即填写</button>
								</c:if> <c:if test="${ubai.certificationnumber!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#realNameModal">查看</button> -->
								</c:if></td>
						</tr>
						<tr height="60px">

							<td>用户地址</td>
							<td>${ubai.addressProvince}${ubai.addressCity}${ubai.addressDistrict}${ubai.addressStreet}</td>
							<td><c:if
									test="${ubai.addressProvince==null||ubai.addressCity==null||ubai.addressDistrict==null||ubai.addressStreet==null}">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#addrModal">立即填写</button>
								</c:if> <c:if
									test="${ubai.addressProvince!=null||ubai.addressCity!=null||ubai.addressDistrict!=null||ubai.addressStreet!=null}">
									<!-- <button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal" data-target="#addrModal">修改</button> -->
								</c:if></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="col-md-12 column tab-pane fade" id="image">
				<br /> 设置展示自己头像
				<!-- <input id="lefile" type="file" style="display:none">
				<div class="input-append">
					<input id="photoCover" class="input-large" type="text" style="height:30px;">
					<button type="button" onclick="$('input[id=lefile]').click();">选择</button>
				</div>
				 
				<script type="text/javascript">
					$('input[id=lefile]').change(function() {
						$('#photoCover').val($(this).val());
					});
				</script> -->
				<form action="upload.action" enctype="multipart/form-data"
					method="post">
					<br /> <input type="file" name="file" /> <br /> <input
						class="btn btn-primary btn-sm" type="submit" value="上传" />
				</form>
			</div>
		</div>
	</div>


	<!-- 模态框（Modal） -->
	<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改用户昵称</h4>
				</div>
				<div class="modal-body">
					<form action="updateLoginName.action" id="updateLoginName">
						<label for="newLoginName">用户昵称：</label> &nbsp;<input
							id="newLoginName" name="newLoginName" value="${ubai.loginname}" />
						<input type="hidden" id="id" name="id" value="${ubai.id}" />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="loginNameSubmit">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<!-- 手机模态框（mobileModal） -->
	<div class="modal fade bs-example-modal-sm" id="mobileModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改手机号</h4>
				</div>
				<div class="modal-body">
					<form action="updateMobilePhone.action" id="updateMobilePhone">
						<label for="mobilephone">原手机号：</label> &nbsp;<label>${ubai.mobilephone}</label>
						<br /> <label for="newMobilephone">新手机号：</label> &nbsp;<input
							id="newMobilephone" name="newMobilephone" value="" /> <br /> <label
							for="moblieVerifyCode">验证码：</label> &nbsp;<input
							id="moblieVerifyCode" name="moblieVerifyCode" />
						<button type="button" id="sendsmsID" onclick="SMSVerifyCode()">发送短信验证码</button>
						<input type="hidden" id="id_1" name="id_1" value="${ubai.id}" /> <input
							type="hidden" id="updateId" name="updateId" value="1" />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="mobileSubmit">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>