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
		$("#insert-itiname-text").change(function() {
			$("#insert-itiname-lb").html("");
		});
	});
	
	/*
	 * 验证项目名称
	 */
</script>
</head>
<body>
	<form id="insert-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>图文项目设置</b></font>
			</div>
			<hr>
		</div>
		<!-- 
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				序 号 ： <input type="text" name="itino" id="insert-itino-text"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					style="width: 100px; text-align: center; line-height: 18px;"
					placeholder="请输入序号"  /> <span
					style="color: red;"><label id="insert-itino-lb"></label></span>
			</div>
		</div>
		<hr> -->
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				项目名称： <input type="text" name="itiname" id="insert-itiname-text"
					style="width: 150px; text-align: center; line-height: 18px;"
					placeholder="请输入项目名称" /> <span style="color: red;"><label
					id="insert-itiname-lb"></label></span>
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
				排列顺序： <input type="radio" name="sort" value="1" checked="checked">反序&nbsp;&nbsp;
				<input type="radio" name="sort" value="0">正序
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