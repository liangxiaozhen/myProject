<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>莫邪科技</title>
<script type="text/javascript">
	$(function() {
		$.tzUpload({
			img : basePath + "/js/swfupload/copy/imagebtn.png",
			targetId : "titleUpload",
			url : basePath + "/admin/imageTextSetting/upload.action",
			type : "*.jpg;*.png;*.gif;*.jpeg",
			postName : "titlePic",
			size:"5 MB",
			single : true,
			callback : function(data) {
				var json = $.parseJSON(data);
				$("#titlePic").val(basePath+json);
				$("#titleSrc").attr("src", basePath+json);
			}
		});
		$("#insert-itino-select").change(function(){
			$("#insert-itino-lb").html("");
		});
	});
	
	/*
	 * 新增校验
	 */
	 function validateInsert(){
		var itino=$("#insert-itino-select").val();
		if(itino==null||itino==""){
			$("#insert-itino-lb").html("*必须选择项目名称");
			return false;
		}
		var title=$("#insert-title-text").val();
		if(title==null||title==""){
			$("#insert-title-lb").html("*必须填写标题");
			$("#insert-title-text").focus();
			return false;
		}
		var subtitle1=$("#insert-subtitle1-text").val();
		if(subtitle1.length>50){
			$("#insert-subtitle1-lb").html("*副标题1长度限制15字数以内");
			$("#insert-subtitle1-text").focus();
			return false;
		}
		var subtitle2 = $("#insert-subtitle2-text").val();
		if (subtitle2.length>50) {
			$("#insert-subtitle2-lb").html("*副标题2长度限制15字数以内");
			$("#insert-subtitle2-text").focus();
			return false;
		}
		var subtitle3 = $("#insert-subtitle3-text").val();
		if (subtitle3.length>50) {
			$("#insert-subtitle3-lb").html("**副标题3长度限制15字数以内");
			$("#insert-subtitle3-text").focus();
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
img {
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>图文项目设置</b></font>
			</div>
			<hr>
		</div>
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				项目名称： <select id="insert-itino-select" name="itino">
					<option value="">---请选择---</option>
					<c:forEach items="${list }" var="item">
						<option value="${item.id }">${item.itiname }</option>
					</c:forEach>
				</select> <span style="color: red;"><label id="insert-itino-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				标 题 ： <input type="text" name="title" id="insert-title-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写标题" /> <span style="color: red;"><label
					id="insert-title-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				序 号 ： <input type="text" name="serialno" id="insert-serialno-text"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					style="width: 100px; text-align: center; line-height: 18px;"
					placeholder="请填写序号" />(非必填) <span style="color: red;"><label
					id="insert-serialno-lb"></label></span>
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
					id="insert-subtitle1-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写副标题1" />(非必填) <span style="color: red;"><label
					id="insert-subtitle1-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				副标题2 ： <input type="text" name="subtitle2"
					id="insert-subtitle2-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写副标题2" />(非必填) <span style="color: red;"><label
					id="insert-subtitle2-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				副标题3 ： <input type="text" name="subtitle3"
					id="insert-subtitle3-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写副标题3" />(非必填) <span style="color: red;"><label
					id="insert-subtitle3-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
		<input type="hidden" name="titlepic" id="titlePic"/>
			<div class="col-md-4 col-md-offset-1">
				标题图:
				<div id="titleUpload"></div>
			</div>
			<div class="col-md-5">
				<img alt="无缩略图" src="" id="titleSrc">
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				超级链接： <input type="text" name="hyperlink" id="insert-hyperlink-text"
					style="width: 200px; text-align: center; line-height: 18px;"
					placeholder="请填写超级链接" />(非必填) <span style="color: red;"><label
					id="insert-hyperlink-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">内容简介：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入内容简介：" class="form-control" name="resume"
					id="insert-remark-text" onkeyup="LimitTextArea(this)"
					onkeydown="LimitTextArea(this)"></textarea>
			</div>
		</div>
		<hr>
		<div class="row" style="line-height: 0px;">
			<div class="col-md-10 col-md-offset-1">备注：</div>
		</div>
		<div class="row" style="line-height: 0px; margin-top: 12px;">
			<div class="col-md-10 col-md-offset-1">
				<textarea placeholder="请输入备注：" class="form-control" name="remark"
					id="insert-remark-text" onkeyup="LimitTextArea(this)"
					onkeydown="LimitTextArea(this)"></textarea>
			</div>
		</div>
	</form>
</body>
</html>