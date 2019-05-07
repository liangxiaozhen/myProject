<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定向添加到风险名单</title>
<script type="text/javascript">
	//先选择名单后才能添加定向编号
	$(function(){
		$("#InputDingXiang").hide();
		$("#dingXiangType").change(function(){
			if("#dingXiangType.val()!=''"){
				$("#InputDingXiang").show();
			}
		})
	});
	
	function dingXiangButton(){
		//给按钮订一个开关
		var flag = true;
		//获取前端的type值
		var type = $("#dingXiangType").val();
		//获取定向编号的值
		var businessNo = $("#businessNo").val();
		if(businessNo==""){
			//改变按钮的状态 使其无法点击添加按钮
			flag = false;
			alert("请输入定向编号!");
		}
		
		//方法的运行路径
		var action = "isDingXiang.action";
		//设置返回的参数
		var params = {
				"businessNo":businessNo
		};
		//回调函数
		var callback = function(data){
				for(var key in data) {
					//alert("属性：" + key + ",值："+ data[key])
					if(key == 'businessName'){
						$("#businessName").val(data[key]);
					}
					if(key == 'businessType'){
						var mode=data[key];
						if(mode == 1){
							$("#nameMode").val("大小名单");
						}
						if(mode == 2){
							$("#nameMode").val("会员等级方式");
						}
					}
					if(key == 'num'){
						$("#num").val(data[key]+"人");
					}
					if(key == 'result'){
						alert(data[key]);
					}
				}
		};
		$.post(action,params,callback,'json');
	}
</script>
</head>
<body>
	<form id="dingXiang-form" class="form-horizontal" role="form" method="post">
		<div class="form-group">
			<label class="col-sm-3 control-label">请选择名单 </label>
			<div class="col-sm-3" id="xz">
				<select name="type" id="dingXiangType" class="form-control">						
					<option value="">---请选择---</option>
					<option value="4">风险名单</option>
					<option value="3">应急改密名单</option>
					<option value="2">黑名单</option>
					<option value="1">白名单</option>
				</select>
		     </div>
		</div>
		<br>
		
		<div id="InputDingXiang">
			<div class="form-group">
				<label class="col-sm-3 control-label">定向编号</label>
			<div class="col-sm-4">
				<div class="input-group">
					<input type="text" name="businessNo" class="form-control" id="businessNo">
				</div>
			</div>
			<div class="col-sm-2">
				<span><button id="" type="button" class="btn btn-default" onclick="dingXiangButton()"><strong>添加</strong></button></span>
			</div>
		</div>
		<br>
		
		<div class="form-group" id="div1">
			<label class="col-sm-3 control-label">定向方式</label>
				<div class="col-sm-4">
					<input type="text" name="nameMode" class="form-control" id="nameMode">
					
				</div>
		</div>
		<br>
		
		<div class="form-group" id="div2">
		<label class="col-sm-3 control-label">定向名称</label>
			<div class="col-sm-4">
				<input type="text" name="businessName" class="form-control" id="businessName">
			</div>
		</div>
		<br>
		
		<div class="form-group" id="div3">
		<label class="col-sm-3 control-label">用户人数</label>
			<div class="col-sm-4">
				<input type="text" name="" id="num" class="form-control">
			</div>
		</div>
		<br>
		</div>
	</form>
</body>
</html>