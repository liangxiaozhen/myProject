<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>提抵卷活动规则设置编辑页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<div class="tab-content" id="tab-content">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)">提抵卷活动规则设置</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 提低卷修改页面 开始 -->
	<form id="ugradeLow">
		<div id="tab1" data-opid="${coupon.id}">
			<div class="row" style="margin-top: 60px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">活动编号</label>
					<div class="col-sm-6">
						<input type="hidden" readonly="readonly" class="form-control"
							id="id" name="id" value="${coupon.id}"> <input
							type="text" class="form-control" readonly="readonly" id="actno"
							name="actno" value="${coupon.actno}" placeholder="请输入活动编号....">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">活动名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="actname"
							name="actname" value="${coupon.actname}"
							placeholder="请输入活动名称....">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">制表人</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" readonly="readonly"
							id="addman" name="addman" value="${coupon.addman}"
							placeholder="请输入制表人名字....">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
					<div class="col-sm-6">
						<textarea class="form-control textarea" id="remark" name="remark"
							data-remark="${coupon.remark}" placeholder="请输入150字以内的备注信息...."></textarea>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">活动生效日期</label>
					<div class="col-sm-6">
						<input type="text" class="Wdate" value="${coupon.actbtimestr}"
							id="actbtime" name="actbtime" placeholder="请选中日期...."
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">活动截止日期</label>
					<div class="col-sm-6">
						<input type="text" class="Wdate" id="actetime"
							value="${coupon.actetimestr}" name="actetime"
							placeholder="请选中日期...."
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">执行时间点</label>
					<div class="col-sm-6">
						<input type="text" class="Wdate" value="${coupon.executetime}"
							id="executetime" name="executetime" placeholder="请选中日期...."
							onclick="WdatePicker({dateFmt:'HH:mm:ss'})">
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-9">
					<label for="exampleInputName2" class="col-sm-2 text-right">获奖名单是否审核</label>
					<div class="col-sm-7">
						<select name="isauditalist" id="isauditalist"
							data-isauditalist="${coupon.isauditalist }">
							<option value="">--请选择是否需要审核--</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">活动规则</label>
					<c:if test="${coupon.actrule==1}">
						<label> <input type="radio" name="actrule" value="1"
							checked="checked">升降级只发一次
						</label>
						<label> <input type="radio" name="actrule" value="2">升降级重发取消之前奖品
						</label>
					</c:if>

					<c:if test="${coupon.actrule==2}">
						<label> <input type="radio" name="actrule" value="1">升降级只发一次
						</label>
						<label> <input type="radio" name="actrule" value="2"
							checked="checked">升降级重发取消之前奖品
						</label>
					</c:if>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">会员等级设置</label>
					<label> <input type="radio" name="ugrade"
						class="insert-ugrade-radio" value="1">全部会员等级
					</label> <label> <input type="radio" name="ugrade"
						class="insert-ugrade-radio" value="2" checked="checked">选择会员等级
					</label>
				</div>
			</div>

			<!-- 选择会员等级选择开始 -->
			<div class="row" id="ugrade-checkbox-div" style="margin-top: 20px;">
				<div class="col-md-12 col-md-offset-1">
					<input type="hidden" value="${params}" id="ulist" />
					<c:forEach items="${userGrades}" var="user">
						<label class="checkbox-inline"><input type="checkbox"
							class="ugrades" name="ugrades" value="${user.ugrade}">${user.ugradename}
						</label>
					</c:forEach>
				</div>
			</div>
			<!-- 选择会员等级选择结束 -->

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">排除名单表设置</label>
					<div class="col-sm-6">
						<label> <input type="radio" name="removenameno"
							class="insert-removenameno-radio" value="1">全部排除名单表编号
						</label> <label> <input type="radio" name="removenameno"
							class="insert-removenameno-radio" value="2" checked="checked">选择排除名单表编号
						</label>
					</div>
				</div>
			</div>

			<!-- 选择排除名单表开始 -->
			<div class="row" id="removeName-checkbox-div"
				style="margin-top: 20px;">
				<div class="col-md-12 col-md-offset-1">
					<input type="hidden" value="${coupon.removenameno}"
						id="removenamenos" />
					<c:forEach items="${removeNames}" var="remove">
						<label class="checkbox-inline"> <input type="checkbox"
							name="removenamenos" class="removenamenos"
							value="${remove.nameno}" /> 名单表类型：<span class="red">${remove.nametype}</span>&nbsp;&nbsp;
							名单表名称：<span class="red">${remove.name}</span>&nbsp;&nbsp; 名单表编号：<span
							class="red">${remove.nameno}</span>
						</label>
					</c:forEach>
				</div>
			</div>
			<!-- 选择排除名单表结束 -->

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8 text-left">
					<button class="btn btn-success" type="button" id="low_save"
						onclick="withdrawsCashCouponEdit.low_save(this)">保存</button>
					<button class="btn btn-info" type="button"
						onclick="withdrawsCashCouponEdit.low_callback(this)">返回列表</button>
				</div>
			</div>
		</div>
		<!-- 提抵卷活动规则 结束 -->
	</form>
	<!-- 提低卷修改页面 结束-->

	<script type="text/javascript">
 	 $(function(){
 		 //备注信息注入
 		 $("#remark").val($("#remark").data("remark"));
 		 //获奖名单是否审核 默认选中
  		 $("#isauditalist").val($("#isauditalist").data("isauditalist"));
 		 
 		//编辑的时候排除名单表编号时默认选中
 		 var removenamenos = $("#removenamenos").val();
   	    if(removenamenos.length > 0){
  	   		var u = removenamenos.split(",");
   	    		$.each(u,function(index,value){
   	   			$("#removeName-checkbox-div input[type='checkbox']").each(function(){
    	   					if($(this).val()==value){
  	   						$(this).attr("checked",true);
  	   					}
  	   			});
  	   		});
  	   	} 
 		//编辑的时候会员等级的默认选中
 		var ulist = $("#ulist").val();
 	    if(ulist.length>0){
 	   		var u = ulist.split(",");
 	    		$.each(u,function(index,value){
 	   			$("#ugrade-checkbox-div input[type='checkbox']").each(function(){
 	   					if($(this).val()==value){
 	   					$(this).attr("checked",true);
 	   				}
 	   			});
 	   		});
 	   	} 
 		$(".insert-ugrade-radio,.insert-removenameno-radio").change(function(){
 			//会员等级选择  1 为全部会员 2 部分会员
 			var $val = $("input[name='ugrade']:checked").val();
 			//排除名单编号选择  1 为全部2 部分
 			var $erval = $("input[name='removenameno']:checked").val();
 			
			if($val==1){
				$("#ugrade-checkbox-div").hide();
			}else if($val == 2){
				$("#ugrade-checkbox-div").show();
			}
			
			if($erval==1){
				$("#removeName-checkbox-div").hide();
			}else if($erval == 2){
				$("#removeName-checkbox-div").show();
			}
 		});
 	 });
 	 
 	 var  withdrawsCashCouponEdit = {
 			 low_save:function(obj){
 				 var params = $("#ugradeLow").serializeArray();
 				 var json = this.jsonParams(params);
 				 if(isEmpty(json["actname"])){
 					 $("#actname").select();
 					 loading("请输入活动名称",4);
 					 return false;
 				 }
 				 var remark = $("#remark").val();
 				 if(remark.length >=150){
 					 loading("您输入的备注信息超过150字限制了...",4);
 					 return false;
 				 }
 				if(isEmpty(json["actbtime"])){
 					 loading("请选择活动生效日期...",4);
					 return false;
				 }
 				if(isEmpty(json["actetime"])){
 					 loading("请选择活动截止日期...",4);
					 return false;
				 }
 				if(isEmpty(json["executetime"])){
 					 loading("请选择活动执行时间...",4);
					 return false;
				 }
 				 //部分会员等级选择 验证
 				 if($(".insert-ugrade-radio")[1].checked){
	 				 var ugrades = $(".ugrades").length;
	  				 var count1 = 0;
	  				 for(var i = 0;i<ugrades;i++){
	 					 if($(".ugrades")[i].checked){
	 						count1 +=1;
	 					 }
	  				 }
	 				if(count1 == 0){
						 loading("请至少选择一个会员等级",4);
						 return false;
					 }
  				 }
 				 //排除名单表部分  验证
  				 if($(".insert-removenameno-radio")[1].checked){
	 				 //排除名单表部分选择length
	 				 var length = $(".removenamenos").length;
	  				 var count = 0;
	 				 for(var i = 0;i<length;i++){
	 					 if($(".removenamenos")[i].checked){
	 						 count +=1;
	 					 }
	  				 }
	 				 if(count == 0){
	 					 loading("请至少选择一个排除名单表",4);
	 					 return false;
	 				 }
   				 }
 				 $(obj).removeAttr("onclick").text("保存中...");
 				  $.tzAjax.request({
 					  model:"admin/withdrawsCashCoupon",
 					  method:"update.action",
 					  error:function(){
  						 $(obj).attr("onclick","withdrawsCashCouponEdit.low_save(this)").text("保存");
  					  },
 					  callback:function(data){
 						 $("#low_save").attr("onclick","withdrawsCashCouponEdit.low_save(this)").text("保存");
 						  var obj = $.parseJSON(data);
 						  if(obj.result=="update_success"){
 							  alert("更新成功");
 				 	 		window.location.href=basePath+"/admin/withdrawsCashCoupon/list.action";
  						  }else if(obj.result =="update_fail"){
 							  loading("更新失败,请重新操作",4);
 						  }
 					  }
 				  },params);
 			 },
 	 		low_callback:function(obj){
 	 			window.location.href=basePath+"/admin/withdrawsCashCoupon/list.action";
 	 		},
 	 		jsonParams:function(params){
					var jsonarr = {};
				if(params){
					var length = params.length;
					for(var i=0;i<length;i++){
						var data = params[i];
						jsonarr[data.name] = data.value;
					}
				}
				return jsonarr;
			}
 	 };
 </script>
</body>
</html>
