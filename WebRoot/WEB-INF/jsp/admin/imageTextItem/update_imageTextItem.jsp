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
		$("#update-itiname-text").change(function() {
			$("#update-itiname-lb").html("");
		});
		var isuse = "${imageTextItem.isuse}";
		if (isuse == 1) {
			$("input[name='isuse'][value=1]").attr("checked", true);
		}
		if (isuse == 0) {
			$("input[name='isuse'][value=0]").attr("checked", true);
		}
		var sort = "${imageTextItem.sort}";
		if (sort == 1) {
			$("input[name='sort'][value=1]").attr("checked", true);
		}
		if (sort == 0) {
			$("input[name='sort'][value=0]").attr("checked", true);
		}
	});
	/*
	 * 编辑校验项目名称不为空，长度不超过15
	 */
	 function validateUpdate(){
		var itiname=$("#update-itiname-text").val();
		if(itiname==null||itiname==""){
			$("#update-itiname-lb").html("*必须填写项目名称");
			$("#insert-itiname-text").focus();
			return;
		}
		if(itiname.length>15){
			$('#update-itiname-lb').html("*长度限制15字数以内");
			$("#insert-itiname-text").focus();
			return;
		}
		return true;
	}
</script>
</head>
<body>
	<form id="update-form">
		<div class="row" style="line-height: 0px;">
			<div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
				<font color="red"><b>图文项目编辑</b></font> <input type="hidden"
					name="id" value="${imageTextItem.id }">
			</div>
			<hr>
		</div>
		<!--  
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
			
				序 号 ： <input type="text" name="itino" id="update-itino-text"
					onblur="checkNum(this)" onkeyup="clearNoNum(event,this)"
					style="width: 100px; text-align: center; line-height: 18px;"
					placeholder="请输入序号" value="${imageTextItem.itino }" /> <span
					style="color: red;"><label id="update-itino-lb"></label></span>
			</div>
		</div>
		<hr>
		-->
		<div class="row">
			<div class="col-md-12 col-md-offset-1">
				项目名称： <input type="text" name="itiname" id="update-itiname-text"
					style="width: 150px; text-align: center; line-height: 18px;"
					placeholder="请输入项目名称" value="${imageTextItem.itiname }" /> <span
					style="color: red;"><label id="update-itiname-lb"></label></span>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				是否可用： <input type="radio" name="isuse" value="1">可用&nbsp;&nbsp;
				<input type="radio" name="isuse" value="0">不可用
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col-md-11 col-md-offset-1">
				排列顺序： <input type="radio" name="sort" value="0" checked="checked">正序&nbsp;&nbsp;
				<input type="radio" name="sort" value="1">反序
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
					onkeydown="LimitTextArea(this)">${imageTextItem.remark }</textarea>
			</div>
		</div>
	</form>
</body>
</html>