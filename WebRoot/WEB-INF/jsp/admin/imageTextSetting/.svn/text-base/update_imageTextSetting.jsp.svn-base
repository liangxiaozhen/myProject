<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>

<script type="text/javascript">
	$(function() {
		$("#update-itino-select").val("${imageTextSetting.itino}");
		var isuse = "${imageTextSetting.isuse}";
		var pcterminal="${imageTextSetting.pcterminal}";
		var androidterminal="${imageTextSetting.androidterminal}";
		var iosterminal="${imageTextSetting.iosterminal}";
		var wapterminal="${imageTextSetting.wapterminal}";
		$("input[name='isuse'][value="+isuse+"]").attr("checked", true);
		$("input[name='pcterminal'][value="+pcterminal+"]").attr("checked", true);
		$("input[name='androidterminal'][value="+androidterminal+"]").attr("checked", true);
		$("input[name='iosterminal'][value="+iosterminal+"]").attr("checked", true);
		$("input[name='wapterminal'][value="+wapterminal+"]").attr("checked", true);
		$.tzUpload({
			img : basePath + "/js/swfupload/copy/imagebtn.png",
			targetId : "update-titleUpload",
			url : basePath + "/admin/imageTextSetting/upload.action",
			type : "*.jpg;*.png;*.gif;*.jpeg",
			size : "5 MB",
			postName : "update-titlePic",
			single : true,
			callback : function(data) {
				var json = $.parseJSON(data);
				$("#update-titlePic").val(basePath+json);
				$("#update-titleSrc").attr("src",basePath+json);
			}
		});
		$("#del-img").click(function(){
			$("#update-titlePic").val("");
			$("#update-titleSrc").attr("src","");
		});
	});

	/*
	 * 编辑校验
	 */
	function validateUpdate() {
		var itino = $("#update-itino-select").val();
		if (itino == null || itino == "") {
			$("#update-itino-lb").html("*必须选择项目名称");
			return false;
		}
		var title = $("#update-title-text").val();
		if (title == null || title == "") {
			$("#update-title-lb").html("*必须填写标题");
			$("#update-title-text").focus();
			return false;
		}
		var serialno = $("#update-serialno-text").val();
		if (serialno == null || serialno == "") {
			$("#update-serialno-lb").html("*必须填写序号");
			$("#update-serialno-text").focus();
			return false;
		}
		var subtitle1 = $("#update-subtitle1-text").val();
		if (subtitle1.length > 50) {
			$("#update-subtitle1-lb").html("*副标题1长度限制15字数以内");
			$("#update-subtitle1-text").focus();
			return false;
		}
		var subtitle2 = $("#update-subtitle2-text").val();
		if (subtitle2.length > 50) {
			$("#update-subtitle2-lb").html("*副标题2长度限制15字数以内");
			$("#update-subtitle2-text").focus();
			return false;
		}
		var subtitle3 = $("#update-subtitle3-text").val();
		if (subtitle3.length > 50) {
			$("#update-subtitle3-lb").html("**副标题3长度限制15字数以内");
			$("#update-subtitle3-text").focus();
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
				<font color="red"><b>图文项目编辑</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				<input type="hidden" name="id" value="${imageTextSetting.id }" />
				项目名称： <select id="update-itino-select" name="itino">
					<option value="">---请选择---</option>
					<c:forEach items="${list }" var="item">
						<option value="${item.id }">${item.itiname }</option>
					</c:forEach>
				</select> <span style="color: red;"><label id="update-itino-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				标 题 ： <input type="text" name="title" id="update-title-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写标题" value="${imageTextSetting.title }" /> <span
					style="color: red;"><label id="update-title-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				序 号 ： <input type="text" name="serialno" id="update-serialno-text"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					style="width: 100px; text-align: center; line-height: 18px;"
					placeholder="请填写序号" value="${imageTextSetting.serialno }" /> <span
					style="color: red;"><label id="update-serialno-lb"></label></span>
			</div>
		</div>
		<hr>
				<div class="row">
			<div class="col-md-5 col-md-offset-1">
				PC端： <input type="radio" name="pcterminal" value="1" checked="checked">可用&nbsp;&nbsp;
				<input type="radio" name="pcterminal" value="0">不可用
			</div>
			<div class="col-md-6">
				Android端： <input type="radio" name="androidterminal" value="1" checked="checked">可用&nbsp;&nbsp;
				<input type="radio" name="androidterminal" value="0">不可用
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-5 col-md-offset-1">
				IOS端： <input type="radio" name="iosterminal" value="1" checked="checked">可用&nbsp;&nbsp;
				<input type="radio" name="iosterminal" value="0">不可用
			</div>
			<div class="col-md-6">
				WAP端： <input type="radio" name="wapterminal" value="1" checked="checked">可用&nbsp;&nbsp;
				<input type="radio" name="wapterminal" value="0">不可用
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				是否可用： <input type="radio" name="isuse" value="1" checked="checked">可用&nbsp;&nbsp;
				<input type="radio" name="isuse" value="0">不可用
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				副标题1 ： <input type="text" name="subtitle1"
					id="update-subtitle1-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写副标题1" value="${imageTextSetting.subtitle1 }" />(非必填)
				<span style="color: red;"><label id="update-subtitle1-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				副标题2 ： <input type="text" name="subtitle2"
					id="update-subtitle2-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写副标题2" value="${imageTextSetting.subtitle2 }" />(非必填)
				<span style="color: red;"><label id="update-subtitle2-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				副标题3 ： <input type="text" name="subtitle3"
					id="update-subtitle3-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写副标题3" value="${imageTextSetting.subtitle3 }" />(非必填)
				<span style="color: red;"><label id="update-subtitle3-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="row">
				<input type="hidden" name="titlepic" id="update-titlePic"  value="${imageTextSetting.titlepic}"/>
				<div class="col-md-4 col-md-offset-1">
					标题图:
					<div id="update-titleUpload"></div>
					&nbsp;&nbsp;
					<button class="btn btn-default" type="button" id="del-img">移除</button>
				</div>
				<div class="col-md-5">
					<img alt="无缩略图" src="" id="update-titleSrc">
				</div>
				<c:if test="${!empty imageTextSetting.titlepic}">
					<script type="text/javascript">
						$("#update-titleSrc").attr("src",
								"${imageTextSetting.titlepic}");
					</script>
				</c:if>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				超级链接： <input type="text" name="hyperlink" id="update-hyperlink-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写超级链接" value="${imageTextSetting.hyperlink }" />(非必填)
				<span style="color: red;"><label id="update-hyperlink-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">内容简介：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入内容简介：" class="form-control" name="resume"
					id="update-remark-text" onkeyup="LimitTextArea(this)"
					onkeydown="LimitTextArea(this)">${imageTextSetting.resume }</textarea>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control" name="remark"
					id="update-remark-text" onkeyup="LimitTextArea(this)"
					onkeydown="LimitTextArea(this)">${imageTextSetting.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>